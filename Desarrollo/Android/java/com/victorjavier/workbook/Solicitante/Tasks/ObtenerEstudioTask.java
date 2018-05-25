package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;
import android.widget.TextView;

import com.victorjavier.workbook.Entidades.Estudio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ObtenerEstudioTask extends AsyncTask<Void, Void, Boolean> {
    private int idPrestador;
    private TextView textEstudio;
    private List<Estudio> estudios;

    public ObtenerEstudioTask(int idPrestador, TextView textEstudio){
        this.idPrestador = idPrestador;
        this.textEstudio = textEstudio;
        this.estudios = new ArrayList();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean obtenido = true;
        try{
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWEstudio/" + this.idPrestador);
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
            try{
                JSONArray responseJson = new JSONArray(cadena);
                for (int i = 0; i < responseJson.length(); i ++) {
                    try{
                        Estudio estudio = new Estudio(responseJson.getJSONObject(i));
                        this.estudios.add(estudio);
                    }catch(JSONException excepcion){
                        excepcion.printStackTrace();
                    }
                }
            }catch(JSONException excepcion){
                obtenido = false;
                excepcion.printStackTrace();
            }
        }catch(IOException excepcion){
            obtenido = false;
            excepcion.printStackTrace();
        }
        return obtenido;
    }
    @Override
    protected void onPostExecute(final Boolean success){
        if (success){
            for (Estudio estudio : this.estudios) {
                this.textEstudio.setText(this.textEstudio.getText() + estudio.getEstudio() + " | ");
            }
        }
    }
}
