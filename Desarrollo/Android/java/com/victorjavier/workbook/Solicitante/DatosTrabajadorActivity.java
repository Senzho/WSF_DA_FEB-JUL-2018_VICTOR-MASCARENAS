package com.victorjavier.workbook.Solicitante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.R;
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
    private Prestador prestador;

    private void cargarPrestador(){
        this.textEdad.setText(this.calcularEdad() + " años.");
        this.textDescripcion.setText(this.prestador.getDescripcionPrestador());
        this.textDireccion.setText(this.prestador.getDireccionPrestador());
        this.textCorreo.setText(this.prestador.getCorreoPrestador());
        this.textTelefono.setText(this.prestador.getTelefonoPrestador());
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
        this.prestador = (Prestador) getIntent().getSerializableExtra("prestador");
        this.cargarPrestador();
    }
}
