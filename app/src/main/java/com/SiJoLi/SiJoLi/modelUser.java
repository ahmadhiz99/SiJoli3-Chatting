package com.SiJoLi.SiJoLi;

public class modelUser {
    private String Nama, Kota, jarak , PhotoProfil, imglink;
    private Double Latitude, Longitude;

    public String getnama() {
        return Nama;
    }

    public void setnama(String nama) {
        this.Nama = nama;
    }

    public String getkota() {
        return Kota;
    }

    public void setkota(String kota) {
        this.Kota = kota;
    }

    public String getjarak() {
        return jarak;
    }

    public void setjarak(String jarak) {
        this.jarak = jarak;
    }

    public String getPhotoProfil() {
        return PhotoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.PhotoProfil = photoProfil;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }
}