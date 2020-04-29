package com.SiJoLi.SiJoLi.Start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.SiJoLi.SiJoLi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class TambahkanFoto extends AppCompatActivity {
    int SELECT_IMAGE = 1;
    ImageView imageView;
    Button tambahfoto;
    Button simpanfoto;
    Bitmap bitmap;
    FirebaseAuth auth;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath;
    EditText et;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahkan_foto);
        auth = FirebaseAuth.getInstance();
        et = findViewById(R.id.et6);
        imageView = findViewById(R.id.imageprofile);
        tambahfoto = findViewById(R.id.btn_tambahfoto);
        simpanfoto = findViewById(R.id.btn_simpanfotoprofil);
        btnclick();
        btnsave();
    }

    private void btnsave() {
        simpanfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }
        });
    }

    private void uploadimage() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("UserAccount");

        if(filePath != null)
        {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
        String a = ref.toString();
            Toast.makeText(TambahkanFoto.this,"Download = "+ a,Toast.LENGTH_LONG).show();

            ref.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // getting image uri and converting into string
                                Uri downloadUrl = uri;
                                url = downloadUrl.toString();
//                              Toast.makeText(TambahkanFoto.this, "url:"+url,Toast.LENGTH_LONG).show();
                                String b = url;
                                et.setText(b);
                                auth = FirebaseAuth.getInstance();
                                FirebaseUser currentUser = auth.getCurrentUser();
                                final String uid = currentUser.getUid();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                myRef.child(uid).child("Photo").child("PhotoProfil").setValue(b);
                                startActivity(new Intent(TambahkanFoto.this, TampilFoto.class));
                            }
                        });

                        Toast.makeText(TambahkanFoto.this, "Uploaded => " + url, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//                        progressDialog.dismiss();
                        Toast.makeText(TambahkanFoto.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                .getTotalByteCount());
//                        progressDialog.setMessage("Uploaded "+(int)progress+"%");
                    }
                });
        }
    }

    private void btnclick() {
        tambahfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    filePath = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(TambahkanFoto.this.getContentResolver(), filePath);
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(TambahkanFoto.this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
