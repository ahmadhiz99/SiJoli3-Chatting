package com.SiJoLi.SiJoLi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MasukanNomer extends AppCompatActivity {

    Button btnContinue;
    EditText nomerteleponuser;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masukan_nomer);

        //initialize fields
        nomerteleponuser = findViewById(R.id.et_nomerhp);
        btnContinue = findViewById(R.id.btn_selanjutnya);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //check whether the user is logged in
        if (currentUser != null) {
            Intent intent = new Intent(MasukanNomer.this, VerifikasiTelepon.class);
            startActivity(intent);
            finish();
        } else {
            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String mobileNo = nomerteleponuser.getText().toString().trim();

                    if (mobileNo.isEmpty() || mobileNo.length() < 12) {
                        nomerteleponuser.setError("Enter a valid mobile");
                        nomerteleponuser.requestFocus();
                        return;
                    }

                    Intent intent = new Intent(MasukanNomer.this, VerifikasiTelepon.class);
                    intent.putExtra("mobile", mobileNo);
                    startActivity(intent);
                }
            });
        }
    }
}
