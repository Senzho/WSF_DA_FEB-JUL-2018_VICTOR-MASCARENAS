package com.victorjavier.workbook.Solicitante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.victorjavier.workbook.Categorias;
import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.Entidades.Solicitante;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Tasks.EscuchadorSolicitud;
import com.victorjavier.workbook.Solicitante.Tasks.GuardarSolicitudTask;

public class EnviarPeticionActivity extends AppCompatActivity implements EscuchadorSolicitud{
    private TextView textNombre;
    private TextView textCategoria;
    private TextView textFecha;
    private TextView textDescripcion;
    private Solicitante solicitante;
    private Prestador prestador;

    private void cargarPrestador(){
        this.textNombre.setText(this.prestador.getNombrePrestador());
        this.textCategoria.setText(Categorias.categorias[this.prestador.getCategoría()]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_peticion);
        this.textNombre = (TextView) findViewById(R.id.textNombreTrabajadorPeticion);
        this.textCategoria = (TextView) findViewById(R.id.textCategoriaPrestadorPeticion);
        this.textFecha = (TextView) findViewById(R.id.textFechaPeticion);
        this.textDescripcion = (TextView) findViewById(R.id.textDescripcionPeticion);
        this.prestador = (Prestador) getIntent().getSerializableExtra("prestador");
        this.solicitante = (Solicitante) getIntent().getSerializableExtra("solicitante");
        this.cargarPrestador();
    }

    public void botonCancelar_onClick(View view){
        this.finish();
    }
    public void botonEnviar_onClick(View view){
        String fecha = this.textFecha.getText().toString().trim();
        String descripcion = this.textDescripcion.getText().toString().trim();
        if (fecha.length() == 0){
            Toast.makeText(this, "Ingresa la fecha", Toast.LENGTH_SHORT).show();
        }else if(!Dates.isDate(fecha)){
            Toast.makeText(this, "La fecha no es válida (año-mes-dia)", Toast.LENGTH_SHORT).show();
        }else if(descripcion.length() == 0){
            Toast.makeText(this, "Ingresa la descripcion", Toast.LENGTH_SHORT).show();
        }else{
            Solicitud solicitud = new Solicitud();
            solicitud.setFechaInicial(Dates.toDate(fecha));
            solicitud.setDescripcion(descripcion);
            new GuardarSolicitudTask(solicitud, this.solicitante.getIdSolicitante(), this.prestador.getIdPrestador(), this).execute();
        }
    }

    @Override
    public void solicitudEnviada(boolean enviada) {
        if (enviada){
            this.finish();
            Toast.makeText(this, "Solicitud enviada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "La solicitud no pudo enviarse, intente de nuevo más tarde", Toast.LENGTH_SHORT).show();
        }
    }
}
