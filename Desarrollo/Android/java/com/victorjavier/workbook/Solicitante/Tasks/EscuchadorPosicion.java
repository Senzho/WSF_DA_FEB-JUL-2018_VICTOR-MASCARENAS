package com.victorjavier.workbook.Solicitante.Tasks;

import com.victorjavier.workbook.Entidades.Posicion;

public interface EscuchadorPosicion {
    void posicionObtenida(Posicion posicion, String usuario);
}
