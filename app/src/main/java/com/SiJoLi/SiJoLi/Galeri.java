package com.SiJoLi.SiJoLi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SiJoLi.SiJoLi.Start.TambahkanFoto;
import com.SiJoLi.SiJoLi.Start.TampilFoto;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;


public class Galeri extends Fragment {

    Bitmap bitmap;
    private FirebaseAuth auth;
    TextView nama, zodiak, umur;
    ImageView img0,img1, img2, img3, img4, img5, img6;
    String namauser,zodiakuser,umuruser,imglink0,imglink1,imglink2,imglink3,imglink4,imglink5,imglink6;
    int SELECT_IMAGE = 0;
    private Uri filePath;
    String url;
    Button saveiimage;
    FirebaseStorage storage;
    StorageReference storageReference;
    String galeri,galeri1,galeri2,galeri3,galeri4,galeri5,galeri6;
    String post;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_galeri,
                container, false);

        galeri  = "PhotoProfil";
        galeri1 = "PhotoGaleri1";
        galeri2 = "PhotoGaleri2";
        galeri3 = "PhotoGaleri3";
        galeri4 = "PhotoGaleri4";
        galeri5 = "PhotoGaleri5";
        galeri6 = "PhotoGaleri6";

        nama = rootView.findViewById(R.id.tv_galerinama);
        zodiak = rootView.findViewById(R.id.tv_galerizodiak);
        umur = rootView.findViewById(R.id.tv_galeriumur);
        img1 = rootView.findViewById(R.id.img_galeriimage1);
        img2 = rootView.findViewById(R.id.img_galeriimage2);
        img3 = rootView.findViewById(R.id.img_galeriimage3);
        img4 = rootView.findViewById(R.id.img_galeriimage4);
        img5 = rootView.findViewById(R.id.img_galeriimage5);
        img6 = rootView.findViewById(R.id.img_galeriimage6);
        img0 = rootView.findViewById(R.id.profile_galeriimage);
        saveiimage = rootView.findViewById(R.id.btn_simpangaleri);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = auth.getCurrentUser();
        String uid = currentuser.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("UserAccount").child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namauser = dataSnapshot.child("Nama").getValue(String.class);
                nama.setText(namauser);
                umuruser = dataSnapshot.child("Umur").getValue(String.class);
                umur.setText(umuruser);
                zodiakuser = dataSnapshot.child("Zodiak").getValue(String.class);
                zodiak.setText(zodiakuser);

                imglink0 = dataSnapshot.child("Photo").child("PhotoProfil").getValue(String.class);
                Glide.with(getContext())
                        .load(imglink0)
                        .into(img0);
                imglink1 = dataSnapshot.child("Photo").child("PhotoGaleri1").getValue(String.class);
                Glide.with(getContext())
                        .load(imglink1)
                        .into(img1);
                imglink2 = dataSnapshot.child("Photo").child("PhotoGaleri2").getValue(String.class);
                Glide.with(getContext())
                        .load(imglink2)
                        .into(img2);
                imglink3 = dataSnapshot.child("Photo").child("PhotoGaleri3").getValue(String.class);
                Glide.with(getContext())
                        .load(imglink3)
                        .into(img3);
                imglink4 = dataSnapshot.child("Photo").child("PhotoGaleri4").getValue(String.class);
                Glide.with(getContext())
                        .load(imglink4)
                        .into(img4);
                imglink5 = dataSnapshot.child("Photo").child("PhotoGaleri5").getValue(String.class);
                Glide.with(getContext())
                        .load(imglink5)
                        .into(img5);
                imglink6 = dataSnapshot.child("Photo").child("PhotoGaleri6").getValue(String.class);
                Glide.with(getContext())
                        .load(imglink6)
                        .into(img6);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnclick();
//        btnsave();
        return rootView;
    }

    private void btnsave() {
        saveiimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }
        });
    }

    private void btnclick() {
        img0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = "PhotoProfil";
                Intent i = new Intent(getContext(), UploadPhoto.class);
                i.putExtra("srcimage", post);
                startActivity(i);
            }
        });
         img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = galeri1;
                Intent i = new Intent(getContext(), UploadPhoto.class);
                i.putExtra("srcimage", post);
                startActivity(i);
            }
        });
         img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = galeri2;
                Intent i = new Intent(getContext(), UploadPhoto.class);
                i.putExtra("srcimage", post);
                startActivity(i);
            }
        });
         img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = galeri3;
                Intent i = new Intent(getContext(), UploadPhoto.class);
                i.putExtra("srcimage", post);
                startActivity(i);
            }
        });
         img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = galeri4;
                Intent i = new Intent(getContext(), UploadPhoto.class);
                i.putExtra("srcimage", post);
                startActivity(i);
            }
        });
         img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = galeri5;
                Intent i = new Intent(getContext(), UploadPhoto.class);
                i.putExtra("srcimage", post);
                startActivity(i);
            }
        });
         img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = galeri6;
                Intent i = new Intent(getContext(), UploadPhoto.class);
                i.putExtra("srcimage", post);
                startActivity(i);
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
            Toast.makeText(getActivity(),"Download = "+ a,Toast.LENGTH_LONG).show();

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
                                    auth = FirebaseAuth.getInstance();
                                    FirebaseUser currentUser = auth.getCurrentUser();
                                    final String uid = currentUser.getUid();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    myRef.child(uid).child("Photo").child("PhotoProfil").setValue(b);
                                }
                            });
                            Toast.makeText(getActivity(), "Uploaded => " + url, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                        progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    filePath = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                        img0.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}