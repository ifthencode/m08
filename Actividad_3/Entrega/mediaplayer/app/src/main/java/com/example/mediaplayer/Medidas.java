package com.example.mediaplayer;

public class Medidas {
    String Nombre;
    int Video;
    long Id;
    // Constructor
    public Medidas(long Id, String Nombre, int video) {
        this.Id=Id;
        this.Nombre= Nombre;
        this.Video= video;
    }
    public String getNombre() {
        return Nombre;
    }
    public int getVideo() {
        return Video;
    }
    public long getId() {
        return Id;
    }
}
