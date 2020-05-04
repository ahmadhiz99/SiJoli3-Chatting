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

    Double latitA;
    Double longitA;
    Double latitB;
    Double longitB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pesan,
                container, false);

        TextView tvshowdistance = rootView.findViewById(R.id.tvshowdistance);

        latitA =-7.883084;
        longitA = 110.128207;
        latitB= -6.217903;
        longitB=  106.830272;

        double a = distance(latitA,longitA,latitB,longitB);
//        int b = (int) a;
        Integer integer = Integer.valueOf((int) Math.round(a));
        String b = String.valueOf(integer);
        tvshowdistance.setText(b);


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

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist*2);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}