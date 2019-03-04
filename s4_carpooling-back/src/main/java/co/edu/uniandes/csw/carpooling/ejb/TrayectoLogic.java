/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alejo
 */
@Stateless
public class TrayectoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TrayectoLogic.class.getName());
    
    @Inject
    private TrayectoPersistence persistence;
    
    public TrayectoEntity createEntity(TrayectoEntity trayecto)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación del Trayecto");
        if(trayecto.getFechaInicial() == null){
            throw new BusinessLogicException("Datos invalidos, la fecha incicial no puede ser null");
        }if(persistence.find(trayecto.getId()) != null){
             throw new BusinessLogicException("Datos invalidos, ya existe un trayecto con este id");
        }
        trayecto = persistence.create(trayecto);
        LOGGER.log(Level.INFO, "Termina proceso de creación del Trayecto");
        return trayecto;
    }
    
     public List<TrayectoEntity> getTrayectos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los trayectos");
        List<TrayectoEntity> TRAYECTO = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los trayectos");
        return TRAYECTO;
     }
    
     
     public void deletetTrayecto(Long trayectoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Trayecto con id = {0}", trayectoId);
        persistence.delete(trayectoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", trayectoId);
    }
    
}
