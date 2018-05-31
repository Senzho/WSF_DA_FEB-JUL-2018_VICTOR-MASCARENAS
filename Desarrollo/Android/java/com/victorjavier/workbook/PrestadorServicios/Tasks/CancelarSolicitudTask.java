package com.victorjavier.workbook.PrestadorServicios.Tasks;

import android.os.AsyncTask;
import android.view.View;

import com.victorjavier.workbook.PrestadorServicios.EscuchadorCancelacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CancelarSolicitudTask extends AsyncTask<Void, Void, Boolean> {
    private int idSolicitud;
    private EscuchadorCancelacion escuchador;

    public CancelarSolicitudTask(int idSolicitud, EscuchadorCancelacion escuchador){
        this.idSolicitud = idSolicitud;
        this.escuchador = escuchador;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean exito = true;
        try {
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWSolicitud/denegar/" + this.idSolicitud);
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
        this.escuchador.solicitudCancelada(this.idSolicitud, success);
    }
}
