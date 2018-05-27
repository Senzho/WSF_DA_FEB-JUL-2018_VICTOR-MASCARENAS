package com.victorjavier.workbook.PrestadorServicios.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.R;

import java.util.List;

public class AdaptadorSolicitudes extends ArrayAdapter{
    private List<Solicitud> solicitudes;
    private int layout;
    private Context contexto;

    public AdaptadorSolicitudes(@NonNull Context contexto, int resource, @NonNull List<Solicitud> solicitudes){
        super(contexto, resource, solicitudes);
        this.contexto = contexto;
        this.layout = resource;
        this.solicitudes = solicitudes;
    }

    @Override
    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent) {
        View elemento = convertView;
        elemento = LayoutInflater.from(this.contexto).inflate(R.layout.panel_trabajo_realizado, parent, false);
        Solicitud solicitud = this.solicitudes.get(posicion);
        TextView textNombre = elemento.findViewById(R.id.textNombreSolicitanteTrabajo);
        TextView textDescripcion = elemento.findViewById(R.id.textDescripcionTrabajo);
        TextView textFecha = elemento.findViewById(R.id.textFechaTrabajo);
        ImageView imagen = elemento.findViewById(R.id.imagenSolicitanteTrabajo);
        new ObtenerFotoTask(FotoUsuario.SOLICITANTE, solicitud.getSolicitante().getIdSolicitante(), imagen).execute();
        textNombre.setText(solicitud.getSolicitante().getNombreSolicitante());
        textDescripcion.setText(solicitud.getDescripcion());
        textFecha.setText(Dates.toString(solicitud.getFecha()));
        return elemento;
    }
}
