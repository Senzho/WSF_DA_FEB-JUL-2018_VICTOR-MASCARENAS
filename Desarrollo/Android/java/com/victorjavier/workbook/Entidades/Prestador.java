package com.victorjavier.workbook.Entidades;

import com.victorjavier.workbook.Dates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prestador implements Serializable{
    private int idPrestador;
    private String nombrePrestador;
    private String telefonoPrestador;
    private String correoPrestador;
    private int categoría;
    private String descripcionPrestador;
    private Date fechaNacimiento;
    private String direccionPrestador;
    private int generoPrestador;
    private boolean estado;

    public Prestador(){

    }
    public Prestador(JSONObject json) throws JSONException {
        this.idPrestador = json.getInt("idPrestador");
        this.nombrePrestador = json.getString("nombrePrestador");
        this.telefonoPrestador = json.getString("telefonoPrestador");
        this.correoPrestador = json.getString("correoPrestador");
        this.categoría = json.getInt("categoria");
        this.descripcionPrestador = json.getString("descripcionPrestador");
        this.fechaNacimiento = Dates.toDate(json.getString("fechaNacimiento"));
        this.direccionPrestador = json.getString("direccionPrestador");
        this.generoPrestador = json.getInt("generoPrestador");
        this.estado = json.getInt("estado") == 1;
    }

    public int getIdPrestador() {
        return idPrestador;
    }
    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }
    public String getNombrePrestador() {
        return nombrePrestador;
    }
    public void setNombrePrestador(String nombrePrestador) {
        this.nombrePrestador = nombrePrestador;
    }
    public String getTelefonoPrestador() {
        return telefonoPrestador;
    }
    public void setTelefonoPrestador(String telefonoPrestador) {
        this.telefonoPrestador = telefonoPrestador;
    }
    public String getCorreoPrestador() {
        return correoPrestador;
    }
    public void setCorreoPrestador(String correoPrestador) {
        this.correoPrestador = correoPrestador;
    }
    public int getCategoría() {
        return categoría;
    }
    public void setCategoría(int categoría) {
        this.categoría = categoría;
    }
    public String getDescripcionPrestador() {
        return descripcionPrestador;
    }
    public void setDescripcionPrestador(String descripcionPrestador) {
        this.descripcionPrestador = descripcionPrestador;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getDireccionPrestador() {
        return direccionPrestador;
    }
    public void setDireccionPrestador(String direccionPrestador) {
        this.direccionPrestador = direccionPrestador;
    }
    public int getGeneroPrestador() {
        return generoPrestador;
    }
    public void setGeneroPrestador(int generoPrestador) {
        this.generoPrestador = generoPrestador;
    }
    public boolean getEstado(){
        return this.estado;
    }
    public void setEstado(boolean estado){
        this.estado = estado;
    }
}
