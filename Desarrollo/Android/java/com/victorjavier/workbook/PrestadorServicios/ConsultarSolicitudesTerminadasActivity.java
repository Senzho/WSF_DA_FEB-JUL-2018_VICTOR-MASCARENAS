package com.victorjavier.workbook.PrestadorServicios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.PrestadorServicios.Adaptadores.AdaptadorSolicitudes;
import com.victorjavier.workbook.PrestadorServicios.Tasks.ObtenerSolicitudesTerminadasTask;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerPromedioTask;

import java.util.ArrayList;
import java.util.List;

public class ConsultarSolicitudesTerminadasActivity extends AppCompatActivity {
    private TextView textEstrellas;
    private ImageView imagenEstrella;
    private ListView listaSolicitudes;
    private Prestador prestador;
    private List<Solicitud> solicitudes;

    private void cargarSolicitudes(){
        this.solicitudes.clear();
        new ObtenerSolicitudesTerminadasTask(this.solicitudes, this.prestador.getIdPrestador(), this.listaSolicitudes).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_solicitudes_terminadas);
        this.textEstrellas = (TextView) findViewById(R.id.textEstrellasSolicitudesTerminadas);
        this.imagenEstrella = (ImageView) findViewById(R.id.imagenEstrellaSolicitudesTerminadas);
        this.listaSolicitudes = (ListView) findViewById(R.id.listaSolicitudesTerminadas);
        this.prestador = (Prestador) getIntent().getSerializableExtra("prestador");
        new ObtenerPromedioTask(this.prestador.getIdPrestador(), this.textEstrellas, this.imagenEstrella).execute();
        this.solicitudes = new ArrayList();
        this.cargarSolicitudes();
        AdaptadorSolicitudes adaptador = new AdaptadorSolicitudes(this, R.layout.panel_trabajo_realizado, this.solicitudes);
        this.listaSolicitudes.setAdapter(adaptador);
    }
}
