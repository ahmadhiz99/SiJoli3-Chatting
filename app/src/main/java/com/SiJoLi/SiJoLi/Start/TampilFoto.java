package com.SiJoLi.SiJoLi.Start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.SiJoLi.SiJoLi.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class TampilFoto extends AppCompatActivity {
    StorageReference storageReference;
    ImageView imageView;
    String pesan2;
    FirebaseAuth auth;
    String url;
    TextView tv8,tvzodiak,tvumur;
    Button btnselesai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_foto);
        imageView = findViewById(R.id.img_tampilkanfoto);
        tv8 = findViewById(R.id.textView8);
        tvzodiak = findViewById(R.id.tv_zodiak);
        tvumur = findViewById(R.id.tv_umur);
        btnselesai = findViewById(R.id.btn_selesaitampilfoto);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        final String uid = currentUser.getUid();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserAccount").child(uid);
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserAccount").child(uid);
//        storageReference = FirebaseStorage.getInstance().getReference("images/com.google.android.gms.tasks.zzu@1400d34");
//        storageReference = FirebaseStorage.getInstance().getReference("images/com.google.android.gms.tasks.zzu@1400d34");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                        //This might work but it retrieves all the data
                String umur = dataSnapshot.child("Umur").getValue(String.class);
                tvumur.setText(umur);

                String zodiak = dataSnapshot.child("Zodiak").getValue(String.class);
                tvzodiak.setText(zodiak);

                pesan2 = dataSnapshot.child("PhotoProfil").getValue(String.class);//get password
                        Toast.makeText(TampilFoto.this, pesan2,Toast.LENGTH_LONG).show();

                Glide.with(TampilFoto.this)
                        .load(pesan2)
                        .into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnselesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(TampilFoto.this, ));
            }
        });
    }
}