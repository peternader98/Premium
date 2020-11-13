package com.example.premium;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;

public class LogIn extends AppCompatActivity {

    private EditText email_login, password_login;
    private Button btn_log_in, btn_register;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        mAuth = FirebaseAuth.getInstance();

        email_login = (EditText) findViewById(R.id.et_email_login);
        password_login = (EditText) findViewById(R.id.et_password_login);
        btn_log_in = (Button) findViewById(R.id.btn_log_in);
        btn_register = (Button) findViewById(R.id.t_register);

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();

                if(user != null){
                    startActivity(new Intent(LogIn.this, MainWindow.class));
                    finish();
                }
            }
        };

        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(email_login.getText().toString()) &&
                        !TextUtils.isEmpty(password_login.getText().toString())){
                    logIn(email_login.getText().toString(), password_login.getText().toString());
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, Register.class));
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(authListener);
    }

    public void logIn(final String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LogIn.this, MainWindow.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
