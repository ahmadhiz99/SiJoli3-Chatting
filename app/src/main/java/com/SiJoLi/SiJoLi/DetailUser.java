package com.SiJoLi.SiJoLi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        Button chat = findViewById(R.id.getuid);
        nama=findViewById(R.id.et_namadetail);
        imgprofil = findViewById(R.id.img_profil_detailuser);
        String namauser = getIntent().getStringExtra(EXTRA_NAMA);
        String src=getIntent().getStringExtra(EXTRA_PHOTO_PROFIL);
        final String getuid = getIntent().getStringExtra(EXTRA_ID);


        nama.setText(namauser);
        Picasso.get().load(src).into(imgprofil);
        retreive();

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Intent i = new Intent(DetailUser.this,Chat.class);
//               i.putExtra(Chat.EXTRA_ID,getuid);
//               i.putExtra(Chat.EXTRA_HOSTNUMBER,"A");
//               startActivity(i);
            }
        });
    }

    private void retreive() {

    }
}
