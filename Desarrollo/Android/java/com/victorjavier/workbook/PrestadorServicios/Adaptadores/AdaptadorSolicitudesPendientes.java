package com.victorjavier.workbook.PrestadorServicios.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.PrestadorServicios.ResponderSolicitudActivity;
import com.victorjavier.workbook.R;

import java.util.List;

public class AdaptadorSolicitudesPendientes extends ArrayAdapter {
    private List<Solicitud> solicitudes;
    private int layout;
    private Context contexto;

    public AdaptadorSolicitudesPendientes(@NonNull Context contexto, int resource, @NonNull List<Solicitud> solicitudes){
        super(contexto, resource, solicitudes);
        this.contexto = contexto;
        this.layout = resource;
        this.solicitudes = solicitudes;
    }

    @Override
    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent) {
        View elemento = convertView;
        elemento = LayoutInflater.from(this.contexto).inflate(R.layout.panel_solicitud, parent, false);
        final Solicitud solicitud = this.solicitudes.get(posicion);
        TextView textNombre = elemento.findViewById(R.id.textNombreSolicitanteSolicitud);
        TextView textDescripcion = elemento.findViewById(R.id.textDescripcionSolicitud);
        TextView textFecha = elemento.findViewById(R.id.textFechaSolicitud);
        TextView textEstado = elemento.findViewById(R.id.textEstadoSolicitud);
        ImageView imagen = elemento.findViewById(R.id.imagenSolicitanteSolicitud);
        Button aceptar = elemento.findViewById(R.id.botonAceptarSolicitud);
        Button terminar = elemento.findViewById(R.id.botonTerminarSolicitud);
        Button denegar = elemento.findViewById(R.id.botonDenegarSolicitud);
        new ObtenerFotoTask(FotoUsuario.SOLICITANTE, solicitud.getSolicitante().getIdSolicitante(), imagen).execute();
        textNombre.setText(solicitud.getSolicitante().getNombreSolicitante());
        textDescripcion.setText(solicitud.getDescripcion());
        textFecha.setText(Dates.toString(solicitud.getFechaInicial()));
        switch(solicitud.getEstado()){
            case 0:
                textEstado.setText("En espera");
                terminar.setVisibility(View.INVISIBLE);
                break;
            case 1:
                textEstado.setText("Aceptada");
                denegar.setVisibility(View.INVISIBLE);
                aceptar.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(AdaptadorSolicitudesPendientes.this.contexto, ResponderSolicitudActivity.class);
                intento.putExtra("solicitud", solicitud);
                AdaptadorSolicitudesPendientes.this.contexto.startActivity(intento);
            }
        });
        return elemento;
    }
}
