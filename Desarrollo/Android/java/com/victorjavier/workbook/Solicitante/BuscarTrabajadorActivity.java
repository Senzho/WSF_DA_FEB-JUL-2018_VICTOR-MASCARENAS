package com.victorjavier.workbook.Solicitante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Adaptadores.AdaptadorTrabajadores;

import java.util.ArrayList;
import java.util.List;

public class BuscarTrabajadorActivity extends AppCompatActivity {
    private SearchView textBuscar;
    private ListView listaPrestadores;
    private List<Prestador> prestadores;

    private void cargarListaPrestadaores(){
        this.prestadores.clear();
        new BuscarPrestadoresTask(this.prestadores, listaPrestadores).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_trabajador);
        this.textBuscar = (SearchView) findViewById(R.id.textBuscarTrabajador);
        this.listaPrestadores = (ListView) findViewById(R.id.listaPrestadores);
        this.prestadores = new ArrayList();
        this.cargarListaPrestadaores();
        AdaptadorTrabajadores adaptador = new AdaptadorTrabajadores(this, R.layout.panel_trabajador, this.prestadores);
        this.listaPrestadores.setAdapter(adaptador);
        this.listaPrestadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intento = new Intent(BuscarTrabajadorActivity.this, DatosTrabajadorActivity.class);
                intento.putExtra("prestador", BuscarTrabajadorActivity.this.prestadores.get(i));
                BuscarTrabajadorActivity.this.startActivity(intento);
            }
        });
    }
}
