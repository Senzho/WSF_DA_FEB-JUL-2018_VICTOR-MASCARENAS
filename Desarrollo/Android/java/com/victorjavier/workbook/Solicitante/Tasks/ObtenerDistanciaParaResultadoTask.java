package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;
import android.widget.TextView;

import com.victorjavier.workbook.Entidades.Posicion;
import com.victorjavier.workbook.EscuchadorDistancia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObtenerDistanciaParaResultadoTask extends AsyncTask<Void, Void, Boolean> implements EscuchadorPosicion {
    private int idSolicitante;
    private int idPrestador;
    private Posicion posicionX;
    private Posicion posicionY;
    private String distacia;
    private EscuchadorDistancia escuchador;

    public ObtenerDistanciaParaResultadoTask(int idSolicitante, int idPrestador, EscuchadorDistancia escuchador){
        this.idSolicitante = idSolicitante;
        this.idPrestador = idPrestador;
        this.escuchador = escuchador;
        new ObtenerPosicionTask(this.idSolicitante, "solicitante", this).execute();
        new ObtenerPosicionTask(this.idPrestador, "prestador", this).execute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean exito = false;
        while(this.posicionX == null || this.posicionY == null){}
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
            exito = true;
        }catch(IOException excepcion){
            excepcion.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exito;
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        this.escuchador.distanciaObtenida(success?this.distacia:"</>", this.posicionX, this.posicionY);
    }

    @Override
    public void posicionObtenida(Posicion posicion, String usuario) {
        if (posicion != null){
            if (usuario.equals("solicitante")){
                this.posicionX = posicion;
            }else if (usuario.equals("prestador")){
                this.posicionY = posicion;
            }
        }
    }
}
