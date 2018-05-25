package Servicios;

import Modelo.Posicion;
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
@Path("SWPosicion")
public class PosicionFacadeREST extends AbstractFacade<Posicion> {
    @PersistenceContext(unitName = "ServiciosWorkbookPU")
    private EntityManager em;

    public PosicionFacadeREST() {
        super(Posicion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Posicion entity) {
        super.create(entity);
    }
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Posicion entity) {
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
    public Posicion find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Posicion> findAll() {
        return super.findAll();
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Posicion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @GET
    @Path("/solicitante/{idSolicitante}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Posicion obtenerPosicionSolicitante(@PathParam("idSolicitante") Integer idSolicitante){
        Posicion posicion;
        try{
            posicion = (Posicion) this.getEntityManager().createNamedQuery("Posicion.findBySolicitante")
                    .setParameter("idSolicitante", idSolicitante)
                    .getSingleResult();
        }catch(Exception excepcion){
            excepcion.printStackTrace();
            posicion = new Posicion();
            posicion.setIdPosicion(0);
        }
        return posicion;
    }
    @GET
    @Path("/prestador/{idPrestador}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Posicion obtenerPosicionPrestador(@PathParam("idPrestador") Integer idPrestador){
        Posicion posicion;
        try{
            posicion = (Posicion) this.getEntityManager().createNamedQuery("Posicion.findByPrestador")
                    .setParameter("idPrestador", idPrestador)
                    .getSingleResult();
        }catch(Exception excepcion){
            excepcion.printStackTrace();
            posicion = new Posicion();
            posicion.setIdPosicion(0);
        }
        return posicion;
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
