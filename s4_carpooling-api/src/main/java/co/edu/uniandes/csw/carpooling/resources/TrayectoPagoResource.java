/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.PagoDTO;
import co.edu.uniandes.csw.carpooling.ejb.PagoLogic;
import co.edu.uniandes.csw.carpooling.ejb.TrayectoPagoLogic;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Produces("application/json")
@Consumes("application/json")
public class TrayectoPagoResource {
    
    @Inject
    private TrayectoPagoLogic trayectoPagoLogic;
   
    @POST
    public PagoDTO createPago(@PathParam("trayectoId") Long trayectoId, PagoDTO pago) throws BusinessLogicException {
        PagoDTO nuevoPagoDTO = new PagoDTO(trayectoPagoLogic.createPago(trayectoId, pago.toEntity()));
        return nuevoPagoDTO;
    }
}
