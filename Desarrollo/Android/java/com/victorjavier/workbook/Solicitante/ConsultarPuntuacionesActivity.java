package com.victorjavier.workbook.Solicitante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Adaptadores.AdaptadorPuntuaciones;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerPuntuacionesTask;

import java.util.ArrayList;
import java.util.List;

public class ConsultarPuntuacionesActivity extends AppCompatActivity {
    private ListView lista;
    private List<Solicitud> solicitudes;
    private int idPrestador;

    private void cargarPuntuaciones(){
        this.solicitudes.clear();
        new ObtenerPuntuacionesTask(this.solicitudes, this.idPrestador, this.lista).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_puntuaciones);
        this.lista = (ListView) findViewById(R.id.listaPuntuaciones);
        this.solicitudes = new ArrayList();
        this.idPrestador = this.getIntent().getIntExtra("idPrestador", 0);
        this.cargarPuntuaciones();
        AdaptadorPuntuaciones adaptador = new AdaptadorPuntuaciones(this, R.layout.panel_puntuacion, this.solicitudes);
        this.lista.setAdapter(adaptador);
    }
}
