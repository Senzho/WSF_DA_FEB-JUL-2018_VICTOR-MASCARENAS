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

import com.victorjavier.workbook.Entidades.Prestador;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.R;
import com.victorjavier.workbook.Solicitante.Tasks.ObtenerPromedioTask;

import java.util.List;

public class AdaptadorTrabajadores extends ArrayAdapter{
    private List<Prestador> prestadores;
    private int layout;
    private Context contexto;

    public AdaptadorTrabajadores(@NonNull Context contexto, int resource, @NonNull List<Prestador> prestadores){
        super(contexto, resource, prestadores);
        this.contexto = contexto;
        this.layout = resource;
        this.prestadores = prestadores;
    }

    @Override
    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent) {
        View elemento = convertView;
        elemento = LayoutInflater.from(this.contexto).inflate(R.layout.panel_trabajador, parent, false);
        Prestador prestador = this.prestadores.get(posicion);
        TextView textNombre = elemento.findViewById(R.id.textNombreTrabajador);
        TextView distancia = elemento.findViewById(R.id.textDistanciaPrestador);
        TextView textDescripcion = elemento.findViewById(R.id.textDescripcionPrestador);
        TextView textEstrellas = elemento.findViewById(R.id.textEstrellasTrabajador);
        ImageView imagenEstrella = elemento.findViewById(R.id.imagenEstrellaTrabajador);
        ImageView imagenPrestador = elemento.findViewById(R.id.imagenPrestadorBusqueda);
        textNombre.setText(prestador.getNombrePrestador());
        textDescripcion.setText(prestador.getDescripcionPrestador());
        new ObtenerFotoTask(FotoUsuario.PRESTADOR, prestador.getIdPrestador(), imagenPrestador).execute();
        new ObtenerPromedioTask(prestador.getIdPrestador(), textEstrellas, imagenEstrella).execute();
        return elemento;
    }
}
