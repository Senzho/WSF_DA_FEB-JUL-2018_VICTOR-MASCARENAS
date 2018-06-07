package Servicios;

import BL.OperacionesString;
import Modelo.Prestadorservicios;
import Modelo.Solicitud;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("SWPrestador")
public class PrestadorserviciosFacadeREST extends AbstractFacade<Prestadorservicios> {
    @PersistenceContext(unitName = "ServiciosWorkbookPU")
    private EntityManager em;

    public PrestadorserviciosFacadeREST() {
        super(Prestadorservicios.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Prestadorservicios entity) {
        super.create(entity);
    }
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Prestadorservicios entity) {
        super.edit(entity);
    }
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Prestadorservicios find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Prestadorservicios> findAll() {
        return super.findAll();
    }
    @GET
    @Path("{clave}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Prestadorservicios> obtenerPrestadores(@PathParam("clave") String clave){
        List<Prestadorservicios> prestadores = new ArrayList();
        super.findAll().forEach((prestador) -> {
            if (OperacionesString.coincide(clave, prestador.getDescripcionPrestador())
                    || OperacionesString.coincide(clave, prestador.getDireccionPrestador())
                    || OperacionesString.coincide(clave, prestador.getNombrePrestador())
                    || PrestadorserviciosFacadeREST.esCategoria(clave, prestador.getCategoria())){
                prestadores.add(prestador);
            }
        });
        return prestadores;
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Prestadorservicios> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @GET
    @Path("/usuario/{idUsuario}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Prestadorservicios obtenerSolicitanteUsuario(@PathParam("idUsuario") Integer idUsuario){
        Prestadorservicios prestador;
        try{
            prestador = (Prestadorservicios) this.getEntityManager().createNamedQuery("Prestadorservicios.findByIdUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .getSingleResult();
        }catch(Exception excepcion){
            prestador = new Prestadorservicios();
            prestador.setIdPrestador(0);
        } 
        return prestador;
    }
    @GET
    @Path("/promedioPuntuaciones/{idPrestador}")
    @Produces({MediaType.APPLICATION_JSON})
    public String obtenerPromedioPuntuaciones(@PathParam("idPrestador") Integer idPrestador){
        float promedio;
        try{
            List<Solicitud> solicitudes = this.getEntityManager().createNamedQuery("Solicitud.findByIdPrestador")
                    .setParameter("idPrestador", idPrestador)
                    .getResultList();
            float suma = 0;
            int validas = 0;
            for (Solicitud solicitud : solicitudes){
                if (solicitud.getEstrellas() > -1){
                    suma = suma + solicitud.getEstrellas();
                    validas ++;
                }
            }
            if (validas > 0){
                promedio = suma / validas;
            }else{
                promedio = -1;
            }
        }catch(Exception excepcion){
            promedio = -1;
        }
        return PrestadorserviciosFacadeREST.promedioString(promedio);
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public static String promedioString(float valor){
        String promedio = String.valueOf(valor);
        if (promedio.startsWith("-")){
            promedio = "-1";
        }else{
            if (Integer.parseInt(promedio.substring(2, 3)) > 0){
                promedio = promedio.substring(0, 3);
            }else{
                promedio = promedio.substring(0, 1);
            }
        } 
        return promedio;
    }
    public static boolean esCategoria(String clave, int valor){
        boolean es = false;
        String[] categorias = {"Carpintería", "Software", "Repostería", "Cocina", "Fontanería", "Transporte", "Construcción", "Mecánica", "Metalurgia"};
        clave = OperacionesString.sinAcentosYMayusculas(clave);
        for (int i = 0; i < categorias.length; i ++) {
            String categoria = categorias[i];
            categoria = OperacionesString.sinAcentosYMayusculas(categoria);
            if (i == (valor - 1)){
                if (clave.equals(categoria) || OperacionesString.coincide(clave, categoria)){
                    es = true;
                }
                break;
            }
        }
        return es;
    }
}