package com.victorjavier.workbook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;

public class ObtenerFotoTask extends AsyncTask<Void, Void, Boolean> {
    private FotoUsuario usuario;
    private int idUsuario;
    private ImageView image;
    private Bitmap bitmap;

    public ObtenerFotoTask(FotoUsuario usuario, int idUsuario, ImageView image){
        this.usuario = usuario;
        this.idUsuario = idUsuario;
        this.image = image;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean exito = true;
        try{
            String link = "http://192.168.43.126:8080/ServiciosWorkbook/Fotos/";
            String tipoUsuario;
            if(this.usuario.equals(FotoUsuario.PRESTADOR)){
                tipoUsuario = "Prestadores";
            }else{
                tipoUsuario = "Solicitantes";
            }
            URL url = new URL(link + tipoUsuario + "/" + this.idUsuario + ".jpg");
            this.bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch(Exception exception){
            exito = false;
        }
        return exito;
    }
    @Override
    protected void onPostExecute(final Boolean success) {
        if (success) {
            this.image.setImageBitmap(this.bitmap);
        }
    }
}
