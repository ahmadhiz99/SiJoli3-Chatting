package com.SiJoLi.SiJoLi.Account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.SiJoLi.SiJoLi.R;
import com.SiJoLi.SiJoLi.Start.WelcomeMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class LoginPhoneNumber extends AppCompatActivity {

    Button btnpassword;
    TextView hasil;
    EditText etpassword,et_nomerhp;
    CountryCodePicker ccplogin;
    FirebaseUser user;
    FirebaseDatabase db;
    FirebaseAuth auth;
    String pesan2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);
        ccplogin = findViewById(R.id.ccpLogin);
        btnpassword = findViewById(R.id.btn_logindenganphone);
        etpassword = findViewById(R.id.et_passwordLogin);
        et_nomerhp = findViewById(R.id.et_nomerhpLogin);
        hasil = findViewById(R.id.tv_hasilakun);
        mAuth = FirebaseAuth.getInstance();


        btnpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ccpp = ccplogin.getSelectedCountryCodeWithPlus();
                String cccppp = String.valueOf(ccpp);
                final String mobileNo = cccppp + et_nomerhp.getText().toString().trim();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserAccount");
                ref.orderByChild("phoneNumber").equalTo(mobileNo).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                            Toast.makeText(LoginPhoneNumber.this,"Succes... ",Toast.LENGTH_SHORT).show();

                            //String pesan = userSnapshot.getKey();// get id key
                             pesan2= userSnapshot.child("Password").getValue(String.class);//get password
//                            hasil.setText("Password : "+pesan2+"/n"+et_nomerhp.getText().toString());

                            String pass;
                            pass = etpassword.getText().toString();

                            if (pass.equals(pesan2)){
                                Toast.makeText(LoginPhoneNumber.this,"Login Sukses",Toast.LENGTH_SHORT).show();

//                                verifyVerificationCode();
//                                String credential="1";
                                signInWithPhoneAuthCredential();

                                Intent i = new Intent(LoginPhoneNumber.this, WelcomeMenu.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(LoginPhoneNumber.this,"Maaf Username atau Password Salah",Toast.LENGTH_SHORT).show();
                            }

//                            hasil.setText("Passketikan:" + pass + "passdb:"+pesan2);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        throw databaseError.toException();
                    }
                });
            }
        });
    }


//    private void verifyVerificationCode(String code) {
//        //creating the credential
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
//        signInWithPhoneAuthCredential(credential);
//    }


    private void signInWithPhoneAuthCredential(){
        mAuth.signInAnonymously()
                .addOnCompleteListener(LoginPhoneNumber.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //verification successful we will start the profile activity
                                    Intent intent = new Intent(LoginPhoneNumber.this, BuatKataSandi.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                } else {

                                    //verification unsuccessful.. display an error message

                                    String message = "Masukan Kembali Kode Verifikasi";

                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                        message = "Kode Verifikasi Salah atau Nomer HP Sudah Terdaftar";
                                    }
                                    Toast.makeText(LoginPhoneNumber.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }
}
