package com.victorjavier.workbook;

import android.os.AsyncTask;
import com.victorjavier.workbook.Entidades.Usuario;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InicioSesionTask extends AsyncTask<Void, Void, Boolean> {
    private EscuchadorInicioSesion escuchador;
    private Usuario usuario;

    public InicioSesionTask(EscuchadorInicioSesion escuchador, Usuario usuario){
        this.escuchador = escuchador;
        this.usuario = usuario;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean existe = false;
        try{
            URL url = new URL("http://192.168.43.126:8080/ServiciosWorkbook/webresources/SWUsuario/" + this.usuario.getNombreUsuario() + "/" + this.usuario.getContrasena());
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
            this.usuario = new Usuario(new JSONObject(cadena));
            existe = true;
        }catch (IOException excepcion){
            excepcion.printStackTrace();
        } catch (JSONException excepcion) {
            excepcion.printStackTrace();
        }
        return existe;
    }
    @Override
    protected void onPostExecute(final Boolean success){
        this.escuchador.usuarioExistente(success, this.usuario);
    }
}
