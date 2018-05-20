package com.victorjavier.workbook.Entidades;

import com.victorjavier.workbook.Dates;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.Date;

public class Solicitante implements Serializable {
    private int idSolicitante;
    private String nombreSolicitante;
    private String correoSolicitante;
    private String telefonoSolicitante;
    private String direccionSolicitante;
    private Date fechaNacimiento;
    private int genero;

    public Solicitante(){

    }

    public Solicitante(JSONObject json) throws JSONException {
        this.idSolicitante = json.getInt("idSolicitante");
        this.nombreSolicitante = json.getString("nombreSolicitante");
        this.correoSolicitante = json.getString("correoSolicitante");
        this.telefonoSolicitante = json.getString("telefonoSolicitante");
        this.direccionSolicitante = json.getString("direccionSolicitante");
        this.fechaNacimiento = Dates.toDate(json.getString("fechaNacimiento"));
        this.genero = json.getInt("generoSolicitante");
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }
    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }
    public String getNombreSolicitante() {
        return nombreSolicitante;
    }
    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }
    public String getCorreoSolicitante() {
        return correoSolicitante;
    }
    public void setCorreoSolicitante(String correoSolicitante) {
        this.correoSolicitante = correoSolicitante;
    }
    public String getTelefonoSolicitante() {
        return telefonoSolicitante;
    }
    public void setTelefonoSolicitante(String telefonoSolicitante) {
        this.telefonoSolicitante = telefonoSolicitante;
    }
    public String getDireccionSolicitante() {
        return direccionSolicitante;
    }
    public void setDireccionSolicitante(String direccionSolicitante) {
        this.direccionSolicitante = direccionSolicitante;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public int getGenero() {
        return genero;
    }
    public void setGenero(int genero) {
        this.genero = genero;
    }
}
