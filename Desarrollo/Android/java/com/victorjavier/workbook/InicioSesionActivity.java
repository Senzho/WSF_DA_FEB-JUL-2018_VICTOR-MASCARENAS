package com.victorjavier.workbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.victorjavier.workbook.Entidades.Solicitante;
import com.victorjavier.workbook.Entidades.Usuario;
import com.victorjavier.workbook.Solicitante.ConsultarPerfilSolicitanteActivity;
import com.victorjavier.workbook.Solicitante.EscuchadorSolicitante;
import com.victorjavier.workbook.Solicitante.ObtenerSolicitanteTask;

public class InicioSesionActivity extends AppCompatActivity implements EscuchadorInicioSesion, EscuchadorSolicitante {
    private TextView textUsuario;
    private TextView textContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        this.textUsuario = (TextView) findViewById(R.id.textUsuario);
        this.textContrasena = (TextView) findViewById(R.id.textContrasena);
    }

    public void iniciarSesion_onClick(View view){
        String nombreUsuario = this.textUsuario.getText().toString().trim();
        String contrasena = this.textContrasena.getText().toString().trim();
        if (nombreUsuario.length() == 0){
            Toast.makeText(this, "Ingresa tu nombre de usuario", Toast.LENGTH_SHORT).show();
        }else if(contrasena.length() == 0){
            Toast.makeText(this, "Ingresa tu contraseña", Toast.LENGTH_SHORT).show();
        }else{
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);
            new InicioSesionTask(this, usuario).execute();
        }
    }

    @Override
    public void usuarioExistente(boolean existe, Usuario usuario) {
        if (existe){
            new ObtenerSolicitanteTask(this, usuario.getIdUsuario()).execute();
        }else{
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void solicitanteObtenido(Solicitante solicitante) {
        if (solicitante != null){
            Intent intento = new Intent(this, ConsultarPerfilSolicitanteActivity.class);
            intento.putExtra("solicitante", solicitante);
            this.startActivity(intento);
        }else{
            Toast.makeText(this, "No se pudo obtener el usuario, intente más tarde", Toast.LENGTH_SHORT).show();
        }
    }
}
