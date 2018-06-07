package com.victorjavier.workbook.Solicitante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.victorjavier.workbook.Categorias;
import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Tasks.EscuchadorSolicitud;
import com.victorjavier.workbook.Solicitante.Tasks.PuntuarTrabajadorTask;

import java.util.Date;

public class PuntuarTrabajadorActivity extends AppCompatActivity implements EscuchadorSolicitud{
    private TextView textNombrePrestador;
    private TextView textEdadPrestador;
    private TextView textCategoria;
    private TextView textDescripcion;
    private TextView textFecha;
    private TextView textComentario;
    private ImageView imagenEstrellas;
    private Spinner spinnerEstrellas;
    private Solicitud solicitud;
    private String comentarioUtilizado;
    private int estrellasUtilizadas;

    private void cargarSolicitud(){
        this.textNombrePrestador.setText(this.solicitud.getPrestador().getNombrePrestador());
        this.textEdadPrestador.setText(this.calcularEdad() + " años.");
        this.textDescripcion.setText(this.solicitud.getDescripcion());
        this.textCategoria.setText(Categorias.categorias[this.solicitud.getPrestador().getCategoría() - 1]);
        this.textFecha.setText(Dates.getSentence(this.solicitud.getFechaRealizacion()));
        ImageView imagen = (ImageView) findViewById(R.id.imagenPrestadorPuntuar);
        new ObtenerFotoTask(FotoUsuario.PRESTADOR, this.solicitud.getPrestador().getIdPrestador(), imagen).execute();
    }
    private int calcularEdad(){
        return Dates.getYear(new Date()) - Dates.getYear(this.solicitud.getPrestador().getFechaNacimiento());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuar_trabajador);
        this.textCategoria = (TextView) findViewById(R.id.textCategoriaPuntuar);
        this.textComentario = (TextView) findViewById(R.id.textComentarioPuntuar);
        this.textDescripcion = (TextView) findViewById(R.id.textDescripcionSolicitudPuntuar);
        this.textEdadPrestador = (TextView) findViewById(R.id.textEdadPrestadorPuntuar);
        this.textFecha = (TextView) findViewById(R.id.textFechaPuntuar);
        this.textNombrePrestador = (TextView) findViewById(R.id.textNombrePrestadorPuntuar);
        this.spinnerEstrellas = (Spinner) findViewById(R.id.spinnerPuntuar);
        this.imagenEstrellas = (ImageView) findViewById(R.id.imagenEstrellaPuntuar);
        this.solicitud = (Solicitud) getIntent().getSerializableExtra("solicitud");
        this.getSupportActionBar().setTitle("Puntuar - " + solicitud.getPrestador().getNombrePrestador());
        this.cargarSolicitud();
        String[] estrellas = {"0", "1", "2", "3", "4", "5"};
        this.spinnerEstrellas.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estrellas));
    }

    public void botonCancelar_onClick(View view){
        this.finish();
    }
    public void botonPuntuar_onClick(View view){
        this.comentarioUtilizado = this.textComentario.getText().toString().trim();
        if (this.comentarioUtilizado.length() == 0){
            Toast.makeText(this, "Ingresa tu comentario", Toast.LENGTH_SHORT).show();
        }else{
            this.estrellasUtilizadas = Integer.parseInt(this.spinnerEstrellas.getSelectedItem().toString());
            new PuntuarTrabajadorTask(this.solicitud.getIdSolicitud(), this.estrellasUtilizadas, this.comentarioUtilizado, this).execute();
        }
    }

    @Override
    public void solicitudEnviada(boolean enviada) {

    }
    @Override
    public void solicitudPuntuada(boolean puntuada) {
        if (puntuada){
            this.solicitud.setComentario(this.comentarioUtilizado);
            this.solicitud.setEstrellas(this.estrellasUtilizadas);
            Toast.makeText(this, "Publicado", Toast.LENGTH_SHORT).show();
            this.finish();
        }else{
            Toast.makeText(this, "No se pudo publicar, intente más tarde", Toast.LENGTH_SHORT).show();
        }
    }
}
