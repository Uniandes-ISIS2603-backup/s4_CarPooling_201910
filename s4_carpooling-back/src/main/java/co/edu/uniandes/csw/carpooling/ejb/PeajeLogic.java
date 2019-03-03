/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.PeajePersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Isabela Sarmiento
 */
public class PeajeLogic {
    
    private static final Logger LOGGER = Logger.getLogger(PeajeLogic.class.getName());

    @Inject
    private PeajePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una editorial en la persistencia.
     *
     * @param editorialEntity La entidad que representa la editorial a
     * persistir.
     * @return La entiddad de la editorial luego de persistirla.
     * @throws BusinessLogicException Si la editorial a persistir ya existe.
     */
    public PeajeEntity createPeaje(PeajeEntity peajeEntity) throws BusinessLogicException {
       
        if (persistence.findByName(peajeEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un Peaje con el nombre \"" + peajeEntity.getNombre() + "\"");
        }
        peajeEntity = persistence.create(peajeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la editorial");
        return peajeEntity;
    }
}
