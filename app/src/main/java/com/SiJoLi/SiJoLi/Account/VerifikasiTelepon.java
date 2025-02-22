package com.SiJoLi.SiJoLi.Account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.SiJoLi.SiJoLi.R;
import com.SiJoLi.SiJoLi.Start.WelcomeMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifikasiTelepon extends AppCompatActivity {

    //three objects needed
    //this is the verification id that will be sent to the user
    private String mVerificationId;

    //ditText to input the code
    private EditText editTextCode;

    //fireBase authentication object
    private FirebaseAuth mAuth;
    Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_telepon);

        //initializing objects
        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);
        btnSignIn = findViewById(R.id.buttonSignIn);

        //getting mobile number from the previous activity
        //and sending the verification code to the number
        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");
        sendVerificationCode(mobile);

        TextView tv_phonenumber = findViewById(R.id.tv_text2);
        tv_phonenumber.setText("+"+mobile);


        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();//editable
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Kode Verifikasi Salah atau Nomer HP Sudah Terdaftar");
                    editTextCode.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(code);
            }
        });

    }

    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+" + mobile,                 //phoneNo that is given by user
                60,                             //Timeout Duration
                TimeUnit.SECONDS,                   //Unit of Timeout
                TaskExecutors.MAIN_THREAD,          //Work done on main Thread
                mCallbacks);                       // OnVerificationStateChangedCallbacks
    }



    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    //Getting the code sent by SMS
                    String code = phoneAuthCredential.getSmsCode();

                    //sometime the code is not detected automatically
                    //in this case the code will be null
                    //so user has to manually enter the code
                    if (code != null) {
                        editTextCode.setText(code);
                        //verifying the code
                        verifyVerificationCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(VerifikasiTelepon.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("TAG",e.getMessage() );
                }

                //when the code is generated then this method will receive the code.
                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);

                    //storing the verification id that is sent to the user
                    mVerificationId = s;
                }
            };

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    //used for signing the user
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifikasiTelepon.this,
                        new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //verification successful we will start the profile activity
                                    Intent intent = new Intent(VerifikasiTelepon.this, WelcomeMenu.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                } else {

                                    //verification unsuccessful.. display an error message

                                    String message = "Masukan Kembali Kode Verifikasi";

                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                        message = "Kode Verifikasi Salah atau Nomer HP Sudah Terdaftar";
                                    }
                                    Toast.makeText(VerifikasiTelepon.this,message,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }

}
