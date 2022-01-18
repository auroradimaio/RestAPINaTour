package com.example.natour21.Item;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

public class PostItem {

    private String Titolo;
    private String Descrizione;
    double Lat1;
    double Lat2;
    double Lon1;
    double Lon2;
    int Id;
    private String Startpoint;
    private String Username;


    public PostItem(String descr, String tit, double lat1, double lat2, double lon1, double lon2,  int id, String startpoint,String username){

        Titolo=tit;
        Descrizione=descr;
        Lat1=lat1;
        Lat2=lat2;
        Lon1=lon1;
        Lon2=lon2;
        Id=id;
        Startpoint=startpoint;
        Username = username;


    }









    public String getTitolo(){
        return Titolo;
    }

    public String getDescrizione(){
        return Descrizione;
    }

    public double getLat1(){return Lat1;}

    public double getLat2() {
        return Lat2;
    }

    public double getLon1() {
        return Lon1;
    }

    public double getLon2() {
        return Lon2;
    }

    public int getId(){return Id;}

    public String getStartpoint(){return Startpoint;}

    public String getUsername(){return Username;}
}
