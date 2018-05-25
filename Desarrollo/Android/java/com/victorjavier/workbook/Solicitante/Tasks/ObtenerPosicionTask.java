package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;

import com.victorjavier.workbook.Entidades.Posicion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObtenerPosicionTask extends AsyncTask<Void, Void, Boolean> {
    private int idUsuario;
    private String tipoUsuario;
    private EscuchadorPosicion escuchador;
    private Posicion posicion;

    public ObtenerPosicionTask(int idUsuario, String tipoUsuario, EscuchadorPosicion escuchador){
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.escuchador = escuchador;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean obtenido = true;
        try{
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWPosicion/" + this.tipoUsuario + "/" + this.idUsuario);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();
            InputStream inputStream;
            if (connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST){
                inputStream = connection.getInputStream();
            }else{
                inputStream = connection.getErrorStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String cadena = bufferedReader.readLine();
            this.posicion = new Posicion(new JSONObject(cadena));
        }catch(IOException excepcion){
            obtenido = false;
            excepcion.printStackTrace();
        }catch(JSONException excepcion){
            obtenido = false;
            excepcion.printStackTrace();
        }
        return obtenido;
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        if (success){
            this.escuchador.posicionObtenida(this.posicion, this.tipoUsuario);
        }
    }
}
