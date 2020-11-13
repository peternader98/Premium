package com.example.premium;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText firstName, lastName, Email, Password, bdYear;
    private Spinner bdDay, bdMonth;
    private Button signUP;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private String strDay, strMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        auth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        firstName = (EditText) findViewById(R.id.et_first_name);
        lastName = (EditText) findViewById(R.id.et_last_name);
        Email = (EditText) findViewById(R.id.et_email_register);
        Password = (EditText) findViewById(R.id.et_password_register);
        bdDay = (Spinner) findViewById(R.id.s_day);
        bdMonth = (Spinner) findViewById(R.id.s_month);
        bdYear = (EditText) findViewById(R.id.s_year);

        signUP = (Button) findViewById(R.id.btn_sign_up);

        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(Register.this ,R.array.day ,android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bdDay.setAdapter(dayAdapter);

        ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(Register.this ,R.array.month ,android.R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bdMonth.setAdapter(monthAdapter);

        bdDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDay = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bdMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strMonth = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fname = firstName.getText().toString();
                final String lname = lastName.getText().toString();
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                final String birthDay = strDay;
                final String birthMonth = strMonth;
                final String birthYear = bdYear.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        User user = new User(fname, lname, email, password, birthDay, birthMonth, birthYear);
                                        String id = auth.getUid();

                                        databaseReference.child(id).setValue(user);
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Register.this, LogIn.class);
        startActivity(intent);
        finish();
    }

}
