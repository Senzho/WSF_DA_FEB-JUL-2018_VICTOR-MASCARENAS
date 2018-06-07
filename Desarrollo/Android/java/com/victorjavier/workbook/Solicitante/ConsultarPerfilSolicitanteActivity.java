package com.victorjavier.workbook.Solicitante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Solicitante;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Adaptadores.AdaptadorPeticiones;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerPeticionesTerminadasTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultarPerfilSolicitanteActivity extends AppCompatActivity {
    private TextView textNombreSolicitante;
    private TextView textCorreoSolicitante;
    private TextView textTelefonoSolicitante;
    private TextView textGeneroSolicitante;
    private TextView textEdadSolicitante;
    private TextView textCiudadSolicitante;
    private TextView textDireccionSolicitante;
    private ImageView imagenUsuario;
    private Solicitante solicitante;
    private List<Solicitud> solicitudes;
    private ListView lista;
    private AdaptadorPeticiones adaptador;

    private static final int CODIGO_RESULTADO_PUNTUACION = 200;

    private int calcularEdad(){
        return Dates.getYear(new Date()) - Dates.getYear(this.solicitante.getFechaNacimiento());
    }
    private void cargarSolicitudes(){
        this.solicitudes.clear();
        new ObtenerFotoTask(FotoUsuario.SOLICITANTE, this.solicitante.getIdSolicitante(), this.imagenUsuario).execute();
        new ObtenerPeticionesTerminadasTask(this.solicitudes, this.solicitante.getIdSolicitante(), this.lista).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_perfil_solicitante);
        this.textNombreSolicitante = (TextView) findViewById(R.id.textNombreSolicitante);
        this.textCiudadSolicitante = (TextView) findViewById(R.id.textCiudadSolicitante);
        this.textDireccionSolicitante = (TextView) findViewById(R.id.textDireccionSolicitante);
        this.textEdadSolicitante = (TextView) findViewById(R.id.textEdadSolicitante);
        this.textGeneroSolicitante = (TextView) findViewById(R.id.textGeneroSolicitante);
        this.textTelefonoSolicitante = (TextView) findViewById(R.id.textTelefonoSolicitante);
        this.textCorreoSolicitante = (TextView) findViewById(R.id.textCorreo);
        this.lista = (ListView) findViewById(R.id.listaPeticionesTerminadas);
        this.imagenUsuario = (ImageView) findViewById(R.id.imagenSolicitantePerfil);
        this.getSupportActionBar().setTitle("Mi perfil");
        this.solicitante = (Solicitante) getIntent().getSerializableExtra("solicitante");
        this.cragarSolicitante();
        this.solicitudes = new ArrayList();
        this.cargarSolicitudes();
        this.adaptador = new AdaptadorPeticiones(this, R.layout.panel_peticion_terminada, this.solicitudes);
        this.lista.setAdapter(this.adaptador);
        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Solicitud solicitud = ConsultarPerfilSolicitanteActivity.this.solicitudes.get(i);
                if(solicitud.getEstrellas() == -1){
                    Intent intento = new Intent(ConsultarPerfilSolicitanteActivity.this, PuntuarTrabajadorActivity.class);
                    intento.putExtra("solicitud", solicitud);
                    ConsultarPerfilSolicitanteActivity.this.startActivityForResult(intento, ConsultarPerfilSolicitanteActivity.CODIGO_RESULTADO_PUNTUACION);
                }else{
                    Toast.makeText(ConsultarPerfilSolicitanteActivity.this, "Ya puntuaste este trabajo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.menu_solicitante, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.buscarTrabajadores:
                Intent intento = new Intent(this, BuscarTrabajadorActivity.class);
                intento.putExtra("solicitante", this.solicitante);
                this.startActivity(intento);
                break;
            default:
                break;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ConsultarPerfilSolicitanteActivity.CODIGO_RESULTADO_PUNTUACION){
            this.cargarSolicitudes();
            this.lista.invalidateViews();
        }
    }

    public void cragarSolicitante(){
        this.textNombreSolicitante.setText(this.solicitante.getNombreSolicitante());
        this.textTelefonoSolicitante.setText(this.solicitante.getTelefonoSolicitante());
        this.textDireccionSolicitante.setText(this.solicitante.getDireccionSolicitante());
        this.textCorreoSolicitante.setText(this.solicitante.getCorreoSolicitante());
        this.textGeneroSolicitante.setText(this.solicitante.getGenero()==1?"Masculino.":"Femenino.");
        this.textEdadSolicitante.setText(this.calcularEdad() + " años.");
    }
}
