/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.resources;

import co.edu.uniandes.csw.carpooling.dtos.TrayectoDetail;
import co.edu.uniandes.csw.carpooling.ejb.UsuarioTrayectoLogic;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class UsuarioTrayectoResource2 {
    
    @Inject
    private UsuarioTrayectoLogic usuarioTrayectoLogic;
    
    /**
     * Crea una nueva reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param booksId El ID del libro del cual se le agrega la reseña
     * @param review {@link ReviewDTO} - La reseña que se desea guardar.
     * @return JSON {@link ReviewDTO} - La reseña guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     */
    @POST
    public TrayectoDetail creatTrayectoPasajero(@PathParam("username") String username, TrayectoDetail trayecto) throws BusinessLogicException {
        TrayectoDetail nuevoReviewDTO = new TrayectoDetail(usuarioTrayectoLogic.addTrayectoPasajero(username, trayecto.toEntity()));
        return nuevoReviewDTO;
    }
    
 
    
}
