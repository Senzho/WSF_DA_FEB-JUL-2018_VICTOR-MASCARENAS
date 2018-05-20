package com.victorjavier.workbook;

import com.victorjavier.workbook.Entidades.Usuario;

public interface EscuchadorInicioSesion {
    void usuarioExistente(boolean existe, Usuario usuario);
}
