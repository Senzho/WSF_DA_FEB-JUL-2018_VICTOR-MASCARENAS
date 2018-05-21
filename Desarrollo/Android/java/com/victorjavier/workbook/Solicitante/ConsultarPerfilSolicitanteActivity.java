package com.victorjavier.workbook.Solicitante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Solicitante;
import com.victorjavier.workbook.R;
import java.util.Date;

public class ConsultarPerfilSolicitanteActivity extends AppCompatActivity {
    private TextView textNombreSolicitante;
    private TextView textCorreoSolicitante;
    private TextView textTelefonoSolicitante;
    private TextView textGeneroSolicitante;
    private TextView textEdadSolicitante;
    private TextView textCiudadSolicitante;
    private TextView textDireccionSolicitante;
    private Solicitante solicitante;

    private int calcularEdad(){
        return Dates.getYear(new Date()) - Dates.getYear(this.solicitante.getFechaNacimiento());
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
        this.solicitante = (Solicitante) getIntent().getSerializableExtra("solicitante");
        this.cragarSolicitante();
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
                this.startActivity(intento);
                break;
            default:
                break;
        }
        return true;
    }

    public void cragarSolicitante(){
        this.textNombreSolicitante.setText(this.solicitante.getNombreSolicitante());
        this.textTelefonoSolicitante.setText(this.solicitante.getTelefonoSolicitante());
        this.textDireccionSolicitante.setText(this.solicitante.getDireccionSolicitante());
        this.textCorreoSolicitante.setText(this.solicitante.getCorreoSolicitante());
        this.textGeneroSolicitante.setText(this.solicitante.getGenero()==1?"Masculino.":"Femenino.");
        this.textEdadSolicitante.setText(this.calcularEdad() + "años.");
    }
}
