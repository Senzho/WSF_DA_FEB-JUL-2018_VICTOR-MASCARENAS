package com.victorjavier.workbook.Solicitante.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.victorjavier.workbook.Categorias;
import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.R;

import java.util.List;

public class AdaptadorPeticiones extends ArrayAdapter {
    private List<Solicitud> solicitudes;
    private int layout;
    private Context contexto;

    public AdaptadorPeticiones(@NonNull Context contexto, int resource, @NonNull List<Solicitud> solicitudes){
        super(contexto, resource, solicitudes);
        this.contexto = contexto;
        this.layout = resource;
        this.solicitudes = solicitudes;
    }

    @Override
    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent) {
        View elemento = convertView;
        elemento = LayoutInflater.from(this.contexto).inflate(R.layout.panel_peticion_terminada, parent, false);
        Solicitud solicitud = this.solicitudes.get(posicion);
        TextView textCategoria = elemento.findViewById(R.id.textCategoriaPeticion);
        TextView textNombre = elemento.findViewById(R.id.textNombrePrestadorPeticion);
        TextView textFecha = elemento.findViewById(R.id.textFechaPeticion);
        TextView textEstrellas = elemento.findViewById(R.id.textEstrellasPeticion);
        ImageView imagenPrestador = elemento.findViewById(R.id.imagenPrestadorPeticionTerminada);
        new ObtenerFotoTask(FotoUsuario.PRESTADOR, solicitud.getPrestador().getIdPrestador(), imagenPrestador).execute();
        textFecha.setText(Dates.toString(solicitud.getFechaRealizacion()));
        if (solicitud.getEstrellas() == -1){
            textEstrellas.setText("0");
        }else{
            textEstrellas.setText(solicitud.getEstrellas()+ "");
            ImageView image = elemento.findViewById(R.id.imagenEstrellaPeticion);
            image.setImageResource(R.mipmap.star_fill);
        }
        textCategoria.setText(Categorias.categorias[solicitud.getPrestador().getCategoría()]);
        textNombre.setText(solicitud.getPrestador().getNombrePrestador());
        return elemento;
    }
}
