/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.PagoDTO;
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
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {
    private static final Logger LOGGER = Logger.getLogger(PagoResource.class.getName());
    @GET
    public List getPagos()
    {
        return null;
    }
    @POST
    public PagoDTO createPago(PagoDTO pago)
    {
        return pago;
    }
    @PUT
    @Path("{id: \\d+}")
    public PagoDTO updatePago(@PathParam("id")Long id, PagoDTO pago)
    {
        return pago;
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deletePago(@PathParam("id")Long id)
    {
        
    }
}