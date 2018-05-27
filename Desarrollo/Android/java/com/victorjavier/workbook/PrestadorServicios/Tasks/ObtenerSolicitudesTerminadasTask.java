package com.victorjavier.workbook.PrestadorServicios.Tasks;

import android.os.AsyncTask;
import android.widget.ListView;

import com.victorjavier.workbook.Entidades.Solicitud;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ObtenerSolicitudesTerminadasTask extends AsyncTask<Void, Void, Boolean> {
    private List<Solicitud> solicitudes;
    private int idPrestador;
    private ListView lista;

    public ObtenerSolicitudesTerminadasTask(List<Solicitud> solicitudes, int idPrestador, ListView lista){
        this.solicitudes = solicitudes;
        this.idPrestador = idPrestador;
        this.lista = lista;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean obtenidas = true;
        try{
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWSolicitud/terminadas/prestador/" + this.idPrestador);
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
            try {
                JSONArray responseJson = new JSONArray(cadena);
                for (int i = 0; i < responseJson.length(); i ++) {
                    try{
                        Solicitud solicitud = new Solicitud(responseJson.getJSONObject(i));
                        this.solicitudes.add(solicitud);
                    }catch(JSONException excepcion){
                        excepcion.printStackTrace();
                    }
                }
            } catch (JSONException excepcion) {
                obtenidas = false;
                excepcion.printStackTrace();
            }
        }catch(IOException excepcion){
            excepcion.printStackTrace();
        }
        return obtenidas;
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        if (success) {
            this.lista.invalidateViews();
        }else{
            System.out.println("Error al conectar...");
        }
    }
}
