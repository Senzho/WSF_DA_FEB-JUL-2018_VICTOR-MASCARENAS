package com.victorjavier.workbook.PrestadorServicios;

public interface EscuchadorTermino {
    void solicitudTerminada(int idSolicitud, boolean terminada);
}
