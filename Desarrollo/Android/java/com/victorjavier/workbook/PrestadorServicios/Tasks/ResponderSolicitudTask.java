package com.victorjavier.workbook.PrestadorServicios.Tasks;

import android.os.AsyncTask;

import com.victorjavier.workbook.PrestadorServicios.EscuchadorSolicitudAceptada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResponderSolicitudTask extends AsyncTask<Void, Void, Boolean> {
    private EscuchadorSolicitudAceptada escuchador;
    private int idSolicitud;
    private String respuesta;

    public ResponderSolicitudTask(EscuchadorSolicitudAceptada escuchador, int idSolicitud, String respuesta) {
        this.escuchador = escuchador;
        this.idSolicitud = idSolicitud;
        this.respuesta = respuesta;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean exito = true;
        try {
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWSolicitud/aceptar/" + this.idSolicitud + "/" + this.respuesta);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            InputStream inputStream;
            if (connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST){
                inputStream = connection.getInputStream();
            }else{
                inputStream = connection.getErrorStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String cadena = bufferedReader.readLine();
            if (cadena.equals("err")){
                exito = false;
            }
        }catch(IOException excepcion){
            exito = false;
            excepcion.printStackTrace();
        }
        return exito;
    }
    @Override
    protected void onPostExecute(final Boolean success){
        this.escuchador.solicitudAceptada(success);
    }
}
