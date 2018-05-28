package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;

import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Solicitud;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GuardarSolicitudTask extends AsyncTask<Void, Void, Boolean> {
    private Solicitud solicitud;
    private int idSolicitante;
    private int idPrestador;
    private EscuchadorSolicitud escuchador;

    public GuardarSolicitudTask(Solicitud solicitud, int idSolicitante, int idPrestador, EscuchadorSolicitud escuchador){
        this.solicitud = solicitud;
        this.idSolicitante = idSolicitante;
        this.idPrestador = idPrestador;
        this.escuchador = escuchador;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean exito;
        try {
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWSolicitud/" + Dates.toString(this.solicitud.getFechaInicial()) + "/" + this.solicitud.getDescripcion() + "/" + this.idSolicitante + "/" + this.idPrestador);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            InputStream inputStream;
            if (connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST){
                inputStream = connection.getInputStream();
            }else{
                inputStream = connection.getErrorStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String cadena = bufferedReader.readLine();
            if (cadena.equals("ok")){
                exito = true;
            }else{
                exito = false;
            }
        } catch (IOException excepcion) {
            exito = false;
            excepcion.printStackTrace();
        }
        return exito;
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        this.escuchador.solicitudEnviada(success);
    }
}
