package com.victorjavier.workbook.Solicitante.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.R;

import java.util.List;

public class AdaptadorPuntuaciones extends ArrayAdapter{
    private List<Solicitud> solicitudes;
    private int layout;
    private Context contexto;

    public AdaptadorPuntuaciones(@NonNull Context contexto, int resource, @NonNull List<Solicitud> solicitudes){
        super(contexto, resource, solicitudes);
        this.contexto = contexto;
        this.layout = resource;
        this.solicitudes = solicitudes;
    }

    @Override
    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent) {
        View elemento = convertView;
        elemento = LayoutInflater.from(this.contexto).inflate(R.layout.panel_puntuacion, parent, false);
        Solicitud solicitud = this.solicitudes.get(posicion);
        TextView textNombre = elemento.findViewById(R.id.textNombreSolicituantePuntuacion);
        TextView textComentario = elemento.findViewById(R.id.textComentarioSolicitantePuntuacion);
        TextView textEstrellas = elemento.findViewById(R.id.textEstrellasPuntuacion);
        textComentario.setText(solicitud.getComentario());
        textEstrellas.setText(solicitud.getEstrellas() + "");
        textNombre.setText(solicitud.getSolicitante().getNombreSolicitante());
        return elemento;
    }
}
