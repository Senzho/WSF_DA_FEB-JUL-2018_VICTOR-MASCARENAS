package Servicios;

import Modelo.Prestadorservicios;
import Modelo.Solicitante;
import Modelo.Solicitud;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.core.Response;
import modelo.Dates;

@Stateless
@Path("SWSolicitud")
public class SolicitudFacadeREST extends AbstractFacade<Solicitud> {
    @PersistenceContext(unitName = "ServiciosWorkbookPU")
    private EntityManager em;

    public SolicitudFacadeREST() {
        super(Solicitud.class);
    }

    @POST
    @Path("{fecha}/{descripcion}/{idSolicitante}/{idPrestador}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response guardar(@PathParam("fecha") String fecha, @PathParam("descripcion") String descripcion, @PathParam("idSolicitante") Integer idSolicitante, @PathParam("idPrestador") Integer idPrestador) {
        Response response;
        Solicitud solicitud = new Solicitud();
        solicitud.setFecha(new Date());
        solicitud.setFechaInicial(SolicitudFacadeREST.toDate(fecha));
        solicitud.setDescripcion(descripcion);
        solicitud.setEstado(0);
        solicitud.setEstrellas(-1);
        try{
            Solicitante solicitante = (Solicitante) this.getEntityManager().createNamedQuery("Solicitante.findByIdSolicitante")
                    .setParameter("idSolicitante", idSolicitante)
                    .getSingleResult();
            solicitud.setIdSolicitante(solicitante);
            Prestadorservicios prestador = (Prestadorservicios) this.getEntityManager().createNamedQuery("Prestadorservicios.findByIdPrestador")
                    .setParameter("idPrestador", idPrestador)
                    .getSingleResult();
            solicitud.setIdPrestador(prestador);
            this.getEntityManager().persist(solicitud);
            response = Response.status(200).entity("ok").build();
        }catch(Exception excepcion){
            response = Response.status(200).entity("err").build();
            excepcion.printStackTrace();
        }
        return response;
    }
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Solicitud entity) {
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
    public Solicitud find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Solicitud> findAll() {
        return super.findAll();
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Solicitud> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @GET
    @Path("/puntuadas/{idPrestador}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Solicitud> obtenerSolicitudes(@PathParam("idPrestador") Integer idPrestador){
        List<Solicitud> solicitudes = new ArrayList();
        try{
            List<Solicitud> solicitudesTodas = this.getEntityManager().createNamedQuery("Solicitud.findByIdPrestador")
                    .setParameter("idPrestador", idPrestador)
                    .getResultList();
            for (Solicitud solicitud : solicitudesTodas){
                if (solicitud.getEstrellas() != -1){
                    solicitudes.add(solicitud);
                }
            }
        }catch(Exception excepcion){
            excepcion.printStackTrace();
        }
        return solicitudes;
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public static Date toDate(String date){
        Date finalDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            finalDate = simpleDateFormat.parse(date);
        } catch (ParseException exception) {
            finalDate = new Date();
        }
        return finalDate;
    }
}