package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;

import com.victorjavier.workbook.Solicitante.EscuchadorSolicitante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PuntuarTrabajadorTask extends AsyncTask<Void, Void, Boolean> {
    private int idSolicitud;
    private int estrellas;
    private String comentario;
    private EscuchadorSolicitud escuchador;

    public PuntuarTrabajadorTask(int idSolicitud, int estrellas, String comentario, EscuchadorSolicitud escuchador){
        this.idSolicitud = idSolicitud;
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.escuchador = escuchador;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean exito = true;
        try {
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWSolicitud/puntuar/" + this.idSolicitud + "/" + this.estrellas + "/" + this.comentario);
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
        this.escuchador.solicitudPuntuada(success);
    }
}
