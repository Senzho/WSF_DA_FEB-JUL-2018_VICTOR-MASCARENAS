package com.victorjavier.workbook.PrestadorServicios.Tasks;

import android.os.AsyncTask;

import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.PrestadorServicios.EscuchadorPrestador;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObtenerPrestadorSesionTask extends AsyncTask<Void, Void, Boolean> {
    private int idUsuario;
    private Prestador prestador;
    private EscuchadorPrestador escuchador;

    public ObtenerPrestadorSesionTask(int idUsuario, EscuchadorPrestador escuchador){
        this.idUsuario = idUsuario;
        this.escuchador = escuchador;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean obtenido = false;
        try {
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWPrestador/usuario/" + this.idUsuario);
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
            this.prestador = new Prestador(new JSONObject(cadena));
            obtenido = true;
        } catch (IOException excepcion) {
            excepcion.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obtenido;
    }
    @Override
    protected void onPostExecute(final Boolean success){
        this.escuchador.prestadorObtenido(this.prestador);
    }
}
