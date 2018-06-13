package Servicios;

import Modelo.Solicitante;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.commons.codec.binary.Base64;

@Stateless
@Path("SWSolicitante")
public class SolicitanteFacadeREST extends AbstractFacade<Solicitante> {
    @PersistenceContext(unitName = "ServiciosWorkbookPU")
    private EntityManager em;

    public SolicitanteFacadeREST() {
        super(Solicitante.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Solicitante crear(Solicitante entity) {
        super.create(entity);
        return entity;
    }
    @POST
    @Path("/imagen/{idSolicitante}")
    @Consumes({MediaType.TEXT_PLAIN})
    public void guardarFoto(String cadenaBase64, @PathParam("idSolicitante") Integer idSolicitante){
        String imagen = cadenaBase64.split(",")[1];
        Base64 decoder = new Base64();
        byte[] imgBytes = decoder.decode(imagen);
        FileOutputStream osf;
        try {
            String rutaBase = "C:/Users/Victor Javier/Documents/NetBeansProjects/ServiciosWorkbook/web/Fotos/Solicitantes/" + idSolicitante + ".jpg";
            osf = new FileOutputStream(rutaBase);
            osf.write(imgBytes);
            osf.flush();
            osf.close();
        } catch (IOException ex) {
            Logger.getLogger(SolicitanteFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editar(Solicitante entity) {
        super.edit(entity);
    }
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Solicitante find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Solicitante> findAll() {
        return super.findAll();
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Solicitante> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    @GET
    @Path("/usuario/{idUsuario}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Solicitante obtenerSolicitanteUsuario(@PathParam("idUsuario") Integer idUsuario){
        Solicitante solicitante;
        try{
            solicitante = (Solicitante) this.getEntityManager().createNamedQuery("Solicitante.findByIdUsuario")
                    .setParameter("idUsuario", idUsuario)
                    .getSingleResult();
        }catch(Exception excepcion){
            solicitante = new Solicitante();
            solicitante.setIdSolicitante(0);
        } 
        return solicitante;
    }
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}