function obtenerUnico(link){
    var parametro;
    if (link.indexOf("?") > 0){
        parametro = link.split("?")[1].split("=")[1];
    }
    return parametro;
}
function obtenerLista(){
    
}