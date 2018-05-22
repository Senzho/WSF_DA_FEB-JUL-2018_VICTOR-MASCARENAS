package com.victorjavier.workbook.Entidades;

import org.json.JSONException;
import org.json.JSONObject;

public class Estudio {
    private int idEstudio;
    private String estudio;

    public Estudio(){

    }
    public Estudio(JSONObject json) throws JSONException{
        this.idEstudio = json.getInt("idEstudio");
        this.estudio = json.getString("estudio");
    }

    public int getIdEstudio() {
        return idEstudio;
    }
    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }
    public String getEstudio() {
        return estudio;
    }
    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
}
