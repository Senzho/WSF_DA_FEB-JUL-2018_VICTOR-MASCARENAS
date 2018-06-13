package Servicios;

import Modelo.Usuario;
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
@Path("SWUsuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "ServiciosWorkbookPU")
    private EntityManager em;

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario crear(Usuario entity) {
        super.create(entity);
        return entity;
    }
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editar(Usuario entity) {
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
    public Usuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    @GET
    @Path("{nombreUsuario}/{contrasena}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario usuarioExistente(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("contrasena") String contrasena){
        Usuario usuario;
        try{
            usuario = (Usuario) this.getEntityManager().createNamedQuery("Usuario.findByUsuario&Contrasena")
                    .setParameter("nombreUsuario", nombreUsuario)
                    .setParameter("contrasena", contrasena)
                    .getSingleResult();
        }catch(Exception excepcion){
            usuario = new Usuario();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);
        }
        return usuario;
    }
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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