package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;
import android.widget.ListView;

import com.victorjavier.workbook.Entidades.Prestador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BuscarPrestadoresTask extends AsyncTask<Void, Void, Boolean> {
    private List<Prestador> prestadores;
    private ListView lista;

    public BuscarPrestadoresTask(List<Prestador> prestadores, ListView lista){
        this.prestadores = prestadores;
        this.lista = lista;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean encontrados = true;
        try{
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWPrestador");
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
                for (int i = 0; i < responseJson.length(); i ++){
                    try{
                        JSONObject jsonPrestador = responseJson.getJSONObject(i);
                        this.prestadores.add(new Prestador(jsonPrestador));
                    }catch (JSONException exception){
                        System.err.println(exception);
                    }
                }
            }catch(JSONException exception){
                encontrados = false;
                exception.printStackTrace();
            }
        }catch(IOException excepcion){
            encontrados = false;
            excepcion.printStackTrace();
        }
        return encontrados;
    }
    @Override
    protected void onPostExecute(final Boolean success){
        if (success){
            this.lista.invalidateViews();
        }else{
            System.out.println("Error al conectar...");
        }
    }
}
