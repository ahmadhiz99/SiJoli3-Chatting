package com.SiJoLi.SiJoLi.Start;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.util.Log;
import android.widget.Toast;

import com.SiJoLi.SiJoLi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MapsPermission extends Activity implements LocationListener{
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;
    FirebaseAuth auth;
    Button btnnext;
    Double latitudelocation=0.0 ;
    Double longitudelocation=0.0 ;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_permission);
        txtLat = (TextView) findViewById(R.id.tvlocation);

        btnnext = findViewById(R.id.btn_lanjutkanpermission);
        auth = FirebaseAuth.getInstance();
        btnclickcext();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    private void btnclickcext() {
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = auth.getCurrentUser();
                String uid = currentUser.getUid();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserAccount");

//                myRef.child(uid).child("UserID").setValue(user);
                txtLat = (TextView) findViewById(R.id.tvlocation);
//        double latitude = location.getLatitude();
//        double longitude = location.getLongitude();
                if (latitudelocation.equals(0.0)){
                    Toast.makeText(MapsPermission.this,"Maaf lokasi tak ditemukan harap aktifkan gps anda",Toast.LENGTH_LONG).show();
                }else {
                    myRef.child(uid).child("latitude").setValue(latitudelocation);
                    myRef.child(uid).child("longitude").setValue(longitudelocation);
                    startActivity(new Intent(MapsPermission.this, PengaturanProfilAwal.class));
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onLocationChanged(Location location) {

        latitudelocation = location.getLatitude();
        longitudelocation = location.getLongitude();
        txtLat.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }


}