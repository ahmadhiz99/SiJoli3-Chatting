package com.SiJoLi.SiJoLi;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView tvNama;
    public TextView tvKota;
    public TextView tvJarak;
    public TextView imglink_user;
    public ImageView imageUser;
    public String imgView;
    ImageView imageView;

    public UserViewHolder(View itemView) {
        super(itemView);
        tvNama = itemView.findViewById(R.id.nama_user);
        tvKota = itemView.findViewById(R.id.kota_user);
        tvJarak = itemView.findViewById(R.id.jarak_user);
        imglink_user = itemView.findViewById(R.id.imglink_user);
        imageUser = itemView.findViewById(R.id.img_user);
    }

    public void bindToArtikel(modelUser modelUser, View.OnClickListener onClickListener) {
        Double latitude = modelUser.getLatitude();
        Double longitude = modelUser.getLongitude();
       String latitudeString = String.valueOf(latitude);
       String longitudeString = String.valueOf(longitude);

        tvNama.setText(modelUser.getnama());
        tvKota.setText(modelUser.getkota());
        tvJarak.setText(latitudeString+" and "+longitudeString);
        imglink_user.setText(modelUser.getPhotoProfil());
        String imageslink = modelUser.getPhotoProfil();
        Picasso.get().load(imageslink).into(imageUser);
        imageUser.setOnClickListener(onClickListener);
    }
}