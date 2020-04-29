
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


public class BlankFragment extends Fragment {

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

    public BlankFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank,
                container, false);

        return rootView;
    }
}