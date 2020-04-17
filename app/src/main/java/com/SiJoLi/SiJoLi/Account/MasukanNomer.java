package com.SiJoLi.SiJoLi.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.SiJoLi.SiJoLi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class MasukanNomer extends AppCompatActivity {

    Button btnContinue;
    EditText nomerteleponuser;
    FirebaseUser currentUser;

    Button buttoncoba;
    TextView viewcoba;
    EditText etcoba;
    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masukan_nomer);

        //init ccp
//        init();
        ccp=findViewById(R.id.ccp);

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

                    String mobileNo = ccp.getFullNumber()+nomerteleponuser.getText().toString().trim();

                    if (mobileNo.isEmpty() || mobileNo.length() < 5) {
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

//    private void init() {
//        ccp=findViewById(R.id.ccp);
//        etcoba=findViewById(R.id.et_nomerhp);
//        buttoncoba=findViewById(R.id.btn_coba);
//        viewcoba=findViewById(R.id.tv_coba);
//
//        //onclick
//        buttoncoba.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getnumber();
//            }
//        });
//    }

//    private void getnumber() {
//        String fullNumber = ccp.getFullNumber()  + etcoba.getText().toString();
//        viewcoba.setText(fullNumber);
//    }

}
