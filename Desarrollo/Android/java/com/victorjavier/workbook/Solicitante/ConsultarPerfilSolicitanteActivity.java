package com.victorjavier.workbook.Solicitante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.victorjavier.workbook.Entidades.Solicitante;
import com.victorjavier.workbook.R;

public class ConsultarPerfilSolicitanteActivity extends AppCompatActivity {
    private TextView textNombreSolicitante;
    private TextView textCorreoSolicitante;
    private TextView textTelefonoSolicitante;
    private TextView textGeneroSolicitante;
    private TextView textEdadSolicitante;
    private TextView textCiudadSolicitante;
    private TextView textDireccionSolicitante;
    private Solicitante solicitante;

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
        this.solicitante = (Solicitante) getIntent().getSerializableExtra("solicitante");
        this.cragarSolicitante();
    }

    public void cragarSolicitante(){
        this.textNombreSolicitante.setText(this.solicitante.getNombreSolicitante());
        this.textTelefonoSolicitante.setText(this.solicitante.getTelefonoSolicitante());
        this.textDireccionSolicitante.setText(this.solicitante.getDireccionSolicitante());
        //this.textCorreoSolicitante.setText(this.solicitante.getCorreoSolicitante());
        this.textGeneroSolicitante.setText(this.solicitante.getGenero()==1?"Masculino":"Femenino");
    }
}
