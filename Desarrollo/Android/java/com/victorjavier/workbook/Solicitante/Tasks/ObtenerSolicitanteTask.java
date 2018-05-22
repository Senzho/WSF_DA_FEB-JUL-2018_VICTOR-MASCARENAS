package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;
import android.widget.TextView;

import com.victorjavier.workbook.Entidades.Solicitante;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObtenerSolicitanteTask extends AsyncTask<Void, Void, Boolean> {
    private int idSolicitante;
    private TextView textNombre;
    private Solicitante solicitante;

    public ObtenerSolicitanteTask(int idSolicitante, TextView textNombre){
        this.idSolicitante = idSolicitante;
        this.textNombre = textNombre;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean obtenido = false;
        try{
            URL url = new URL("http://192.168.43.126:8080/ServiciosWorkbook/webresources/SWSolicitante/" + this.idSolicitante);
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
            this.solicitante = new Solicitante(new JSONObject(cadena));
            obtenido = true;
        }catch(IOException excepcion){
            excepcion.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obtenido;
    }
    @Override
    protected void onPostExecute(final Boolean success){
        if (success){
            this.textNombre.setText(this.solicitante.getNombreSolicitante());
        }
    }
}
