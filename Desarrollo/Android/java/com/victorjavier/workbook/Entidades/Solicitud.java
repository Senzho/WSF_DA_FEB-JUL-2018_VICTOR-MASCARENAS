package com.victorjavier.workbook.Entidades;

import com.victorjavier.workbook.Dates;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Solicitud {
    private int idSolicitud;
    private Date fechaRealizacion;
    private Date fecha;
    private Date fechaInicial;
    private int estrellas;
    private int estado;//0: en espera; 1: aceptada; 2: cancelada; 3: terminada
    private String descripcion;
    private String comentario;
    private String respuesta;
    private Solicitante solicitante;
    private Prestador prestador;

    public Solicitud(){

    }
    public Solicitud(JSONObject json) throws JSONException{
        this.idSolicitud = json.getInt("idSolicitud");
        this.fechaRealizacion = Dates.toDate(json.getString("fechaRealizacion"));
        this.fecha = Dates.toDate(json.getString("fecha"));
        this.fechaInicial = Dates.toDate(json.getString("fechaInicial"));
        this.estado = json.getInt("estado");
        this.descripcion = json.getString("descripcion");
        this.solicitante = new Solicitante(json.getJSONObject("idSolicitante"));
        this.prestador = new Prestador(json.getJSONObject("idPrestador"));
        try{
            this.estrellas = json.getInt("estrellas");
            this.comentario = json.getString("comentario");
            this.respuesta = json.getString("respuesta");
        }catch (JSONException excepcion){
            excepcion.printStackTrace();
        }
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }
    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getFechaInicial() {
        return fechaInicial;
    }
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
    public int getEstrellas() {
        return estrellas;
    }
    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    public Solicitante getSolicitante() {
        return this.solicitante;
    }
    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Prestador getPrestador(){
        return this.prestador;
    }
    public void setPrestador(Prestador prestador){
        this.prestador = prestador;
    }
}
