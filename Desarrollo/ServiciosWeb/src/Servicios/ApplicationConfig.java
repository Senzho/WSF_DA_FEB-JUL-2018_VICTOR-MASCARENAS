/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Victor Javier
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Servicios.EstudioFacadeREST.class);
        resources.add(Servicios.PosicionFacadeREST.class);
        resources.add(Servicios.PrestadorserviciosFacadeREST.class);
        resources.add(Servicios.SolicitanteFacadeREST.class);
        resources.add(Servicios.SolicitudFacadeREST.class);
        resources.add(Servicios.UsuarioFacadeREST.class);
    }
    
}
