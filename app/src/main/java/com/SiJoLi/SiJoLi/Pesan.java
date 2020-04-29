package com.SiJoLi.SiJoLi;

import android.content.Intent;
import android.os.Bundle;
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

import com.SiJoLi.SiJoLi.Account.LoginMenu;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;


public class Pesan extends Fragment {

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pesan,
                container, false);

        auth = FirebaseAuth.getInstance();

        Button signout = rootView.findViewById(R.id.btn_signoutpesan);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getActivity(), LoginMenu.class);
                startActivity(intent);
            }
        });

        Button chat = rootView.findViewById(R.id.btn_chat);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Chat.class));
            }
        });

        return rootView;
    }
}