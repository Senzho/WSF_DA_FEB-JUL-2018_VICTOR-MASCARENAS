package com.victorjavier.workbook;

import com.victorjavier.workbook.Entidades.Posicion;

public interface EscuchadorDistancia {
    void distanciaObtenida(String distancia, Posicion posicionSolicitante, Posicion posicionPrestador);
}
