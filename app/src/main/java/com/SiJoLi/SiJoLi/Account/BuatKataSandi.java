package com.SiJoLi.SiJoLi.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SiJoLi.SiJoLi.R;
import com.SiJoLi.SiJoLi.Start.WelcomeMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuatKataSandi extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText tvpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_kata_sandi);

        auth = FirebaseAuth.getInstance();

        //init
        tvpassword = findViewById(R.id.tv_password);

        //getintent
        Intent intent = getIntent();
        final String mobile = intent.getStringExtra("mobile");
        Toast.makeText(BuatKataSandi.this, "nomer = "+ mobile, Toast.LENGTH_SHORT).show();

        //cobauid
        Button cobauid= findViewById(R.id.btn_cobauid);
        cobauid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser currentUser = auth.getCurrentUser();
                String uid = currentUser.getUid();
                FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();

                Toast.makeText(BuatKataSandi.this,"UID = "+uid,Toast.LENGTH_SHORT).show();

            }
        });

        //Daftar
        Button btn_daftar = findViewById(R.id.btn_daftarkanakundengantelepon);
        btn_daftar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             // Write a message to the database
             String password = tvpassword.getText().toString();

             FirebaseUser currentUser = auth.getCurrentUser();
             String uid = currentUser.getUid();
             FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();

             FirebaseDatabase database = FirebaseDatabase.getInstance();
             DatabaseReference myRef = database.getReference("UserAccount");

             myRef.child(uid).child("UserID").setValue(user);
             myRef.child(uid).child("PhoneNumber").setValue(mobile);
             myRef.child(uid).child("Password").setValue(password);

             Intent i = new Intent(BuatKataSandi.this, WelcomeMenu.class);
             startActivity(i);
         }
     });

    }
}
