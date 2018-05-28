package com.victorjavier.workbook.PrestadorServicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Posicion;
import com.victorjavier.workbook.Entidades.Solicitante;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.MapaActivity;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.PrestadorServicios.Tasks.ResponderSolicitudTask;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Tasks.EscuchadorPosicion;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerDistanciaTask;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerPosicionTask;
import java.util.Date;

public class ResponderSolicitudActivity extends AppCompatActivity implements EscuchadorPosicion, EscuchadorSolicitudAceptada{
    private TextView textDistancia;
    private TextView textCorreo;
    private TextView textFecha;
    private TextView textTelefono;
    private TextView textEdad;
    private TextView textDireccion;
    private TextView textDescripcion;
    private TextView textRespuesta;
    private ImageView imagenMapa;
    private Solicitud solicitud;
    private Posicion posicionSolicitante;
    private Posicion posicionPrestador;

    private int calcularEdad(){
        return Dates.getYear(new Date()) - Dates.getYear(this.solicitud.getSolicitante().getFechaNacimiento());
    }
    private void cargarSolicitud(){
        Solicitante solicitante = this.solicitud.getSolicitante();
        ImageView imagen = (ImageView) findViewById(R.id.imagenSolicitanteResponder);
        new ObtenerFotoTask(FotoUsuario.SOLICITANTE, solicitante.getIdSolicitante(), imagen).execute();
        new ObtenerPosicionTask(solicitante.getIdSolicitante(), "solicitante", this).execute();
        new ObtenerPosicionTask(this.solicitud.getPrestador().getIdPrestador(), "prestador", this).execute();
        this.textDireccion.setText(solicitante.getDireccionSolicitante());
        this.textCorreo.setText(solicitante.getCorreoSolicitante());
        this.textFecha.setText(Dates.getSentence(this.solicitud.getFechaInicial()));
        this.textTelefono.setText(solicitante.getTelefonoSolicitante());
        this.textEdad.setText(this.calcularEdad() + " años.");
        this.textDescripcion.setText(this.solicitud.getDescripcion());
        getSupportActionBar().setTitle(solicitante.getNombreSolicitante());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_solicitud);
        this.textDescripcion = (TextView) findViewById(R.id.textDescripcionResponder);
        this.textEdad = (TextView) findViewById(R.id.textEdadResponder);
        this.textTelefono = (TextView) findViewById(R.id.textTelefonoResponder);
        this.textCorreo = (TextView) findViewById(R.id.textCorreoResponder);
        this.textDireccion = (TextView) findViewById(R.id.textDireccionResponder);
        this.textDistancia = (TextView) findViewById(R.id.textDistanciaResponder);
        this.textFecha = (TextView) findViewById(R.id.textFechaResponder);
        this.textRespuesta = (TextView) findViewById(R.id.textRespuestaResponder);
        this.imagenMapa = (ImageView) findViewById(R.id.imagenMapaResponder);
        this.imagenMapa.setEnabled(false);
        this.solicitud = (Solicitud) getIntent().getSerializableExtra("solicitud");
        this.cargarSolicitud();
    }

    @Override
    public void posicionObtenida(Posicion posicion, String usuario) {
        if (usuario.equals("solicitante")){
            this.posicionSolicitante = posicion;
        }else{
            this.posicionPrestador = posicion;
        }
        if (this.posicionPrestador != null && this.posicionSolicitante != null){
            new ObtenerDistanciaTask(this.posicionPrestador, this.posicionSolicitante, this.textDistancia).execute();
            this.imagenMapa.setEnabled(true);
        }
    }

    public void botonCancelar_onClick(View view){
        this.finish();
    }
    public void botonEnviar_onClick(View view){
        String respuesta = this.textRespuesta.getText().toString().trim();
        if (respuesta.length() == 0){
            Toast.makeText(this, "Ingresa tu respuesta", Toast.LENGTH_SHORT).show();
        }else{
            new ResponderSolicitudTask(this, this.solicitud.getIdSolicitud(), respuesta).execute();
        }
    }
    public void imagenMapa_onClick(View view){
        Intent intento = new Intent(this, MapaActivity.class);
        intento.putExtra("posicionSolicitante", this.posicionSolicitante);
        intento.putExtra("posicionPrestador", this.posicionPrestador);
        intento.putExtra("nombreSolicitante", this.solicitud.getSolicitante().getNombreSolicitante());
        intento.putExtra("nombrePrestador", this.solicitud.getPrestador().getNombrePrestador());
        this.startActivity(intento);
    }

    @Override
    public void solicitudAceptada(boolean aceptada) {
        if (aceptada){
            Toast.makeText(this, "La respuesta fue enviada", Toast.LENGTH_SHORT).show();
            this.finish();
        }else{
            Toast.makeText(this, "La respuesta no fue enviada, intente de nuevo más tarde", Toast.LENGTH_SHORT).show();
        }
    }
}
