/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.InfoTCDTO;
import co.edu.uniandes.csw.carpooling.ejb.InfoTCLogic;
import co.edu.uniandes.csw.carpooling.ejb.PagoInfoTCLogic;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "pago/{id}/info".
 *
 * @author jf.garcia
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PagoInfoTCResource {
    
    private static final Logger LOGGER = Logger.getLogger(PagoInfoTCResource.class.getName());

    @Inject
    private PagoInfoTCLogic pagoInfoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private InfoTCLogic infoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Agrega la información a un pago, asociándolos.
     * @param pagoId
     * @param infoId
     * @return 
     */
    @POST
    @Path ("{infoId: \\d+}")
    public InfoTCDTO addInfo(@PathParam("id") Long pagoId, @PathParam("infoId") Long infoId) {
        if (infoLogic.getInfoTC(infoId) == null) {
            throw new WebApplicationException("El recurso /info/" + infoId + " no existe.", 404);
        }
        InfoTCDTO infoDTO = new InfoTCDTO(pagoInfoLogic.addInfo(pagoId, infoId));
        return infoDTO;
    }
    
    /**
     * Crea una nueva información asociada al pago con id passado por parámetro.
     * @param pagoId
     * @param info
     * @return
     * @throws BusinessLogicException 
     */
    @POST
    public InfoTCDTO createInfo(@PathParam("id") Long pagoId, InfoTCDTO info) throws BusinessLogicException {
        InfoTCDTO infoDTO = new InfoTCDTO(pagoInfoLogic.createInfo(pagoId, info.toEntity()));
        return infoDTO;
    }
}
