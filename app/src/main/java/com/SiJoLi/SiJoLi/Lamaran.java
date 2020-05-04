package com.SiJoLi.SiJoLi;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class Lamaran extends Fragment {

    ViewFlipper viewFlipper;
    private TextView emailshow;
    public TextView emailget,saldo;
    public Long jmlsaldo;
    private Button btnChangePassword, btnRemoveUser,
            changePassword, remove, signOut;
    private TextView email, emailhome;
    private EditText oldEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
//    private GoogleMap mMap;

    private static final int SPLASH_TIME_MS = 2000;
    private Handler mHandler;
    private Runnable mRunnable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lamaran,
                container, false);
Button chat = rootView.findViewById(R.id.btn_chatting);
chat.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                // check if user is already logged in or not
//                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    // if logged in redirect the user to user listing activity
//                } else {
//                    // otherwise redirect the user to login activity
//                    LoginActivity.startIntent(SplashActivity.this);
//                }
//                finish();
            }
        };
    }
});

        return rootView;
    }


}