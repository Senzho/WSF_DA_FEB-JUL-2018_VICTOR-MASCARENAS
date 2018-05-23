package com.victorjavier.workbook.Solicitante;

import android.content.Intent;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.Entidades.Solicitante;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerEstudioTask;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerPromedioTask;

import java.util.Date;

public class DatosTrabajadorActivity extends AppCompatActivity {
    private TextView textDistancia;
    private TextView textEdad;
    private TextView textCiudad;
    private TextView textDescripcion;
    private TextView textDireccion;
    private TextView textEstudio;
    private TextView textCorreo;
    private TextView textTelefono;
    private ImageView imagen;
    private Prestador prestador;
    private Solicitante solicitante;

    private void cargarPrestador(){
        getSupportActionBar().setTitle(this.prestador.getNombrePrestador());
        this.textEdad.setText(this.calcularEdad() + " años.");
        this.textDescripcion.setText(this.prestador.getDescripcionPrestador());
        this.textDireccion.setText(this.prestador.getDireccionPrestador());
        this.textCorreo.setText(this.prestador.getCorreoPrestador());
        this.textTelefono.setText(this.prestador.getTelefonoPrestador());
        new ObtenerFotoTask(FotoUsuario.PRESTADOR, this.prestador.getIdPrestador(), this.imagen).execute();
        new ObtenerEstudioTask(this.prestador.getIdPrestador(), this.textEstudio).execute();
    }
    private int calcularEdad(){
        return Dates.getYear(new Date()) - Dates.getYear(this.prestador.getFechaNacimiento());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_trabajador);
        this.textDescripcion = (TextView) findViewById(R.id.textDescripcionProfesional);
        this.textEdad = (TextView) findViewById(R.id.textEdadPrestador);
        this.textCiudad = (TextView) findViewById(R.id.textCiudadPrestador);
        this.textDireccion = (TextView) findViewById(R.id.textDireccionPrestador);
        this.textDistancia = (TextView) findViewById(R.id.textDistanciaPrestador);
        this.textEstudio = (TextView) findViewById(R.id.textEstudio);
        this.textCorreo = (TextView) findViewById(R.id.textCorreoPrestador);
        this.textTelefono = (TextView) findViewById(R.id.textTelefonoPrestador);
        this.imagen = (ImageView) findViewById(R.id.imagenPrestadorDatos);
        TextView textEstrellas = (TextView) findViewById(R.id.textPuntuacionDatosTrabajador);
        ImageView image = (ImageView) findViewById(R.id.imagenEstrellaDatosTrabajador);
        this.solicitante = (Solicitante) getIntent().getSerializableExtra("solicitante");
        this.prestador = (Prestador) getIntent().getSerializableExtra("prestador");
        new ObtenerPromedioTask(this.prestador.getIdPrestador(), textEstrellas, image).execute();
        this.cargarPrestador();
    }

    public void verPuntuaciones_onClick(View view){
        Intent intento = new Intent(this, ConsultarPuntuacionesActivity.class);
        intento.putExtra("idPrestador", this.prestador.getIdPrestador());
        this.startActivity(intento);
    }
    public void enviarPeticion_onClick(View view){
        Intent intento = new Intent(this, EnviarPeticionActivity.class);
        intento.putExtra("solicitante", this.solicitante);
        intento.putExtra("prestador", this.prestador);
        this.startActivity(intento);
    }
}
