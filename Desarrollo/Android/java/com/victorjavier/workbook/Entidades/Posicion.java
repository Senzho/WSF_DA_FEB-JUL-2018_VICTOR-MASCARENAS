package com.victorjavier.workbook.Entidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Posicion implements Serializable{
    private int idPosicion;
    private double latitud;
    private double longitud;

    public Posicion(){

    }
    public Posicion(JSONObject json) throws JSONException {
        this.idPosicion = json.getInt("idPosicion");
        this.latitud = json.getDouble("latitud");
        this.longitud = json.getDouble("longitud");
    }

    public int getIdPosicion() {
        return idPosicion;
    }
    public void setIdPosicion(int idPosicion) {
        this.idPosicion = idPosicion;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
