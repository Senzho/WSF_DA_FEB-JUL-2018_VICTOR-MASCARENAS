package com.victorjavier.workbook.Solicitante.Tasks;

import java.io.Serializable;

public interface EscuchadorSolicitud {
    void solicitudEnviada(boolean enviada);
    void solicitudPuntuada(boolean piuntuada);
}
