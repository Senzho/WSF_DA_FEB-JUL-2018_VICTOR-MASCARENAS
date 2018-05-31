package com.victorjavier.workbook.PrestadorServicios;

import android.view.View;

public interface EscuchadorCancelacion {
    void solicitudCancelada(int idSolicitud, boolean cancelada);
}
