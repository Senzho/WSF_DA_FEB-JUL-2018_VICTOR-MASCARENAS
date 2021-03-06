package com.victorjavier.workbook.PrestadorServicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.victorjavier.workbook.Categorias;
import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.PrestadorServicios.Tasks.EstadoTask;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerEstudioTask;

import java.util.Date;

public class ConsultarPerfilPrestadorActivity extends AppCompatActivity implements EscuchadorEstado {
    private TextView textNombre;
    private TextView textTelefono;
    private TextView textEdad;
    private TextView textCorreo;
    private TextView textDescripcion;
    private TextView textDireccion;
    private TextView textCategoria;
    private TextView textEstudios;
    private TextView textGenero;
    private Prestador prestador;
    private MenuItem estadoItem;

    private void cargarPrestador(){
        this.textCorreo.setText(this.prestador.getCorreoPrestador());
        this.textTelefono.setText(this.prestador.getTelefonoPrestador());
        this.textNombre.setText(this.prestador.getNombrePrestador());
        this.textDescripcion.setText(this.prestador.getDescripcionPrestador());
        this.textDireccion.setText(this.prestador.getDireccionPrestador());
        this.textEdad.setText(this.calcularEdad() + " años.");
        this.textCategoria.setText(Categorias.categorias[this.prestador.getCategoría() - 1]);
        this.textGenero.setText(this.prestador.getGeneroPrestador()==0?"Femenino":"Masculino");
        ImageView imagen = (ImageView) findViewById(R.id.imagenPrestadorPerfil);
        new ObtenerFotoTask(FotoUsuario.PRESTADOR, this.prestador.getIdPrestador(), imagen).execute();
        new ObtenerEstudioTask(this.prestador.getIdPrestador(), this.textEstudios).execute();
    }
    private int calcularEdad(){
        return Dates.getYear(new Date()) - Dates.getYear(this.prestador.getFechaNacimiento());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_perfil_prestador);
        this.textCorreo = (TextView) findViewById(R.id.textCorreoPrestadorPerfil);
        this.textDescripcion = (TextView) findViewById(R.id.textDescripcionPrestadorPerfil);
        this.textDireccion = (TextView) findViewById(R.id.textDireccionPrestadorPerfil);
        this.textEdad = (TextView) findViewById(R.id.textEdadPrestadorPerfil);
        this.textEstudios = (TextView) findViewById(R.id.textEstudioPrestadorPerfil);
        this.textNombre = (TextView) findViewById(R.id.textNombrePrestadorPerfil);
        this.textTelefono = (TextView) findViewById(R.id.textTelefonoPrestadorPerfil);
        this.textCategoria = (TextView) findViewById(R.id.textCategoriaPerfil);
        this.textGenero = (TextView) findViewById(R.id.textGeneroPrestadorPerfil);
        this.prestador = (Prestador) getIntent().getSerializableExtra("prestador");
        this.getSupportActionBar().setTitle("Mi perfil");
        this.cargarPrestador();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_prestador, menu);
        this.estadoItem = menu.getItem(1);
        if (this.prestador.getEstado()){
            this.estadoItem.setIcon(R.mipmap.chevron_up);
        }else{
            this.estadoItem.setIcon(R.mipmap.chevron_down);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuConsultarSolicitudes:
                Intent intento = new Intent(this, ConsultarSolicitudesActivity.class);
                intento.putExtra("prestador", this.prestador);
                this.startActivity(intento);
                break;
            case R.id.menuEstablecerDisponibilidad:
                new EstadoTask(this.prestador.getIdPrestador(), !this.prestador.getEstado(), this).execute();
                break;
            default:
                break;
        }
        return true;
    }

    public void verTrabajos_onClick(View view){
        Intent intento = new Intent(this, ConsultarSolicitudesTerminadasActivity.class);
        intento.putExtra("prestador", this.prestador);
        this.startActivity(intento);
    }

    @Override
    public void estadoEstablecido(boolean establecido, boolean estado) {
        if (establecido){
            this.prestador.setEstado(estado);
            if (estado){
                this.estadoItem.setIcon(R.mipmap.chevron_up);
            }else{
                this.estadoItem.setIcon(R.mipmap.chevron_down);
            }
            Toast.makeText(this, "Tu estado cambió a " + (estado?"activo":"inactivo"), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No se pudo actualizar el estado", Toast.LENGTH_SHORT).show();
        }
    }
}
