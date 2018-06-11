function obtenerCategoria(numeroCategoria){
    var categoria = "-No definida-";
    var categorias = ["Carpintería", "Software", "Repostería", "Cocina", "Fontanería", "Transporte", "Construcción", "Mecánica", "Metalurgia"];
    for (var i = 0; i < categorias.length; i ++){
        if (i === (numeroCategoria - 1)){
            categoria = categorias[i];
            break;
        }
    }
    return categoria;
}
function obtenerMes(fecha){
    var mes = fecha.getMonth() + 1;
    if (mes < 10){
        mes = "0" + mes;
    }
    return mes;
}
function obtenerDia(fecha){
    var dia = fecha.getDay();
    if (dia < 10){
        dia = "0" + dia;
    }
    return dia;
}