/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.InfoTCDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jf.garcia
 */
@Path("info")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class InfoTCResource {
    private static final Logger LOGGER = Logger.getLogger(InfoTCResource.class.getName());
    @GET
    public List getInfoTC()
    {
        return null;
    }
    @POST
    public InfoTCDTO createInfoTC(InfoTCDTO info)
    {
        return info;
    }
    @PUT
    @Path("{id: \\d+}")
    public InfoTCDTO updateInfoTC(@PathParam("id")Long id, InfoTCDTO info)
    {
        return info;
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deleteInfoTC(@PathParam("id")Long id)
    {
        
    }
}