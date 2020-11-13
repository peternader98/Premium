package com.example.premium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Contact extends AppCompatActivity {

    private ImageButton btn_back ,btn_send;
    private TextView Name;
    private EditText et_massage;
    private ListView messageView;
    DatabaseReference databaseReference ,dr;

    private String userId;
    private ArrayList<String> message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId = firebaseUser.getUid();
        dr = FirebaseDatabase.getInstance().getReference("Users Message/" + userId);

        Name = (TextView) findViewById(R.id.name);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        et_massage = (EditText) findViewById(R.id.text_massage);
        btn_send = (ImageButton) findViewById(R.id.btn_send);
        messageView = (ListView) findViewById(R.id.message_view);

        message = new ArrayList<>();

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                message.clear();
                for(DataSnapshot msa : dataSnapshot.getChildren()){
                    String value = msa.getValue(String.class);
                    message.add(value);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Contact.this ,android.R.layout.select_dialog_item ,message);
                messageView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Contact.this, "Sorry!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Contact.this, MainWindow.class));
            }
        });

        Name.setText(getIntent().getStringExtra("name"));

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = dr.push().getKey();
                String string = et_massage.getText().toString();
                dr.child(id).setValue(string);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Contact.this, MainWindow.class));
        finish();
    }
}
