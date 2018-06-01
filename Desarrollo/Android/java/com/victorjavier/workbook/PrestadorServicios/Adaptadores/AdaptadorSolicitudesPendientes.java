package com.victorjavier.workbook.PrestadorServicios.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.victorjavier.workbook.Dates;
import com.victorjavier.workbook.Entidades.Solicitud;
import com.victorjavier.workbook.FotoUsuario;
import com.victorjavier.workbook.ObtenerFotoTask;
import com.victorjavier.workbook.PrestadorServicios.ConsultarSolicitudesActivity;
import com.victorjavier.workbook.PrestadorServicios.EscuchadorCancelacion;
import com.victorjavier.workbook.PrestadorServicios.EscuchadorTermino;
import com.victorjavier.workbook.PrestadorServicios.ResponderSolicitudActivity;
import com.victorjavier.workbook.PrestadorServicios.Tasks.CancelarSolicitudTask;
import com.victorjavier.workbook.PrestadorServicios.Tasks.TerminarSolicitudTask;
import com.victorjavier.workbook.R;

import java.util.List;

public class AdaptadorSolicitudesPendientes extends ArrayAdapter implements EscuchadorCancelacion, EscuchadorTermino {
    private List<Solicitud> solicitudes;
    private int layout;
    private Context contexto;
    private ListView lista;

    private void eliminarSolicitud(int idSolicitud){
        for (Solicitud solicitud : this.solicitudes){
            if (solicitud.getIdSolicitud() == idSolicitud){
                this.solicitudes.remove(solicitud);
                break;
            }
        }
        this.lista.invalidateViews();
    }

    public AdaptadorSolicitudesPendientes(@NonNull Context contexto, int resource, @NonNull List<Solicitud> solicitudes){
        super(contexto, resource, solicitudes);
        this.contexto = contexto;
        this.layout = resource;
        this.solicitudes = solicitudes;
    }

    public void setListView(ListView lista){
        this.lista = lista;
    }

    @Override
    public View getView(int posicion, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View elemento = LayoutInflater.from(this.contexto).inflate(R.layout.panel_solicitud, parent, false);
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
                ((Activity) AdaptadorSolicitudesPendientes.this.contexto).startActivityForResult(intento, ConsultarSolicitudesActivity.CODIGO_ACEPTACION_SOLICITUD);
            }
        });
        denegar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AdaptadorSolicitudesPendientes.this.contexto);
                builder.setTitle("Confirmar acción");
                builder.setMessage("¿Está seguro de negar la solicitud?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new CancelarSolicitudTask(solicitud.getIdSolicitud(), AdaptadorSolicitudesPendientes.this).execute();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder.show();
            }
        });
        terminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AdaptadorSolicitudesPendientes.this.contexto);
                builder.setTitle("Confirmar acción");
                builder.setMessage("¿Está seguro de marcar como terminada la solicitud? terminar la solicitud significa que has terminado el trabajo")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new TerminarSolicitudTask(solicitud.getIdSolicitud(), AdaptadorSolicitudesPendientes.this).execute();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder.show();
            }
        });
        return elemento;
    }

    @Override
    public void solicitudCancelada(int idSolicitud, boolean cancelada) {
        if (cancelada){
            Toast.makeText(this.contexto, "La solicitud fue cancelada", Toast.LENGTH_SHORT).show();
            this.eliminarSolicitud(idSolicitud);
        }else{
            Toast.makeText(this.contexto, "La solicitud no se pudo cancelar, intente de nuevo más tarde", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void solicitudTerminada(int idSolicitud, boolean terminada) {
        if (terminada){
            Toast.makeText(this.contexto, "La solicitud fue marcada como terminada", Toast.LENGTH_SHORT).show();
            this.eliminarSolicitud(idSolicitud);
        }else{
            Toast.makeText(this.contexto, "La solicitud no se pudo marcar como terminada, intente de nuevo más tarde", Toast.LENGTH_SHORT).show();
        }
    }
}
