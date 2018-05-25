package com.victorjavier.workbook;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.victorjavier.workbook.Entidades.Posicion;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private LatLng posicionPrestador;
    private LatLng posicionSolicitante;
    private String nombrePrestador;
    private String nombreSolicitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Posicion posicionPrestador = (Posicion) getIntent().getSerializableExtra("posicionPrestador");
        Posicion posicionSolicitante = (Posicion) getIntent().getSerializableExtra("posicionSolicitante");
        this.nombrePrestador = getIntent().getStringExtra("nombrePrestador");
        this.nombreSolicitante = getIntent().getStringExtra("nombreSolicitante");
        this.posicionPrestador = new LatLng(posicionPrestador.getLatitud(), posicionPrestador.getLongitud());
        this.posicionSolicitante = new LatLng(posicionSolicitante.getLatitud(), posicionSolicitante.getLongitud());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(this.posicionPrestador).title(this.nombrePrestador));
        mMap.addMarker(new MarkerOptions().position(this.posicionSolicitante).title(this.nombreSolicitante));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(this.posicionPrestador));
    }
}
