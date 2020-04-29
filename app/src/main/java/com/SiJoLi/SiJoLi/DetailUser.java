package com.SiJoLi.SiJoLi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailUser extends AppCompatActivity {

    private TextView judulDetail, isiDetail;
    private ImageView image;
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_NAMA = "extra_nama";
    public static final String EXTRA_KOTA = "extra_kota";
    public static final String EXTRA_PHOTO_PROFIL = "extra_photo_profil";
    public static final String EXTRA_LATITUDE = "extra_latitude";
    public static final String EXTRA_LONGITUDE= "extra_longitude";

    TextView nama,kota,latitude,longitude;
    ImageView imgprofil;
    String id,imgsrc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        nama=findViewById(R.id.et_namadetail);
        imgprofil = findViewById(R.id.img_profil_detailuser);
        String namauser = getIntent().getStringExtra(EXTRA_NAMA);
        String src=getIntent().getStringExtra(EXTRA_PHOTO_PROFIL);


        nama.setText(namauser);
        Picasso.get().load(src).into(imgprofil);
        retreive();
    }

    private void retreive() {

    }
}
