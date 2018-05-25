package com.victorjavier.workbook.Solicitante.Tasks;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import com.victorjavier.workbook.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObtenerPromedioTask extends AsyncTask<Void, Void, Boolean> {
    private int idPrestador;
    private TextView textEstrellas;
    private ImageView image;
    private String promedio;

    public ObtenerPromedioTask(int idPrestador, TextView textEstrellas, ImageView image){
        this.idPrestador = idPrestador;
        this.textEstrellas = textEstrellas;
        this.image = image;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean obtenido = true;
        try{
            URL url = new URL("http://192.168.44.139:8080/ServiciosWorkbook/webresources/SWPrestador/promedioPuntuaciones/" + this.idPrestador);
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
            promedio = bufferedReader.readLine();
        }catch(IOException excepcion){
            obtenido = false;
        }
        return obtenido;
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        if (success) {
            if (this.promedio.equals("-1")){
                this.textEstrellas.setText("0");
                this.image.setImageResource(R.mipmap.star_empty);
            }else{
                this.textEstrellas.setText(this.promedio);
            }
        }
    }
}
