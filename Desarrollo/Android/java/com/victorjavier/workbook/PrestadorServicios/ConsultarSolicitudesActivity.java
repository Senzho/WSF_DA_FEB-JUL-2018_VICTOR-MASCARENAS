package com.victorjavier.workbook.PrestadorServicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.PrestadorServicios.Adaptadores.AdaptadorSolicitudesPendientes;
import com.victorjavier.workbook.PrestadorServicios.Tasks.ObtenerSolicitudesPendientesTask;
import com.victorjavier.workbook.R;

import java.util.ArrayList;
import java.util.List;

public class ConsultarSolicitudesActivity extends AppCompatActivity {
    private ListView listaSolicitudes;
    private Prestador prestador;
    private List<Solicitud> solicitudes;

    public static int CODIGO_ACEPTACION_SOLICITUD = 100;

    private void cargarSolicitudes(){
        this.solicitudes.clear();
        new ObtenerSolicitudesPendientesTask(this.solicitudes, this.prestador.getIdPrestador(), this.listaSolicitudes).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_solicitudes);
        this.listaSolicitudes = (ListView) findViewById(R.id.listaSolicitudesPrestador);
        this.prestador = (Prestador) getIntent().getSerializableExtra("prestador");
        this.solicitudes = new ArrayList();
        this.cargarSolicitudes();
        AdaptadorSolicitudesPendientes adaptador = new AdaptadorSolicitudesPendientes(this, R.layout.panel_solicitud, this.solicitudes);
        adaptador.setListView(this.listaSolicitudes);
        this.listaSolicitudes.setAdapter(adaptador);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ConsultarSolicitudesActivity.CODIGO_ACEPTACION_SOLICITUD){
            this.cargarSolicitudes();
            this.listaSolicitudes.invalidateViews();
        }
    }
}
