package com.victorjavier.workbook.Entidades;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasena;

    public Usuario(){

    }
    public Usuario(JSONObject json) throws JSONException {
        this.idUsuario = json.getInt("idUsuario");
        this.nombreUsuario = json.getString("nombreUsuario");
        this.contrasena = json.getString("contrasena");
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
