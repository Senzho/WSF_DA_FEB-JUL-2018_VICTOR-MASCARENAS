package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;
import android.widget.TextView;

import com.victorjavier.workbook.Entidades.Posicion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObtenerDistanciaTask extends AsyncTask<Void, Void, Boolean> {
    private Posicion posicionX;
    private Posicion posicionY;
    private String distacia;
    private TextView textDistancia;

    public ObtenerDistanciaTask(Posicion posicionX, Posicion posicionY, TextView textDistancia){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.textDistancia = textDistancia;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean exito = true;
        try{
            URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" + this.posicionX.getLatitud() + "," + this.posicionX.getLongitud() + "&destinations=" + this.posicionY.getLatitud() + "," + this.posicionY.getLongitud() + "&key=AIzaSyCrmegsWTIh5-9FTyCca6shBrroq4oBiZo");
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
            String cadena = "";
            String line = bufferedReader.readLine();
            while(line != null){
                cadena = cadena + line;
                line = bufferedReader.readLine();
            }
            JSONObject json = new JSONObject(cadena);
            this.distacia = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");
        }catch(IOException excepcion){
            excepcion.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exito;
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        if (success) {
            this.textDistancia.setText(this.distacia);
        }
    }
}
