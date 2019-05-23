/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
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

    /**
     * Crea un trayecto.
     *
     * @param trayecto
     * @return La entidad que se ha persistido.
     * @throws BusinessLogicException
     */
    public TrayectoEntity createEntity(TrayectoEntity trayecto) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del Trayecto");
        /*if (trayecto.getFechaInicial() == null) {
            throw new BusinessLogicException("Datos invalidos, la fecha incicial no puede ser null");
        }*/
        if (persistence.find(trayecto.getId()) != null) {
            throw new BusinessLogicException("Datos invalidos, ya existe un trayecto con este id");
        }
        trayecto = persistence.create(trayecto);
        LOGGER.log(Level.INFO, "Termina proceso de creación del Trayecto");
        return trayecto;
    }

    /**
     * Obtiene un todos los trayectos.
     *
     * @return Una lista con todos los trayectos.
     */
    public List<TrayectoEntity> getTrayectos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los trayectos");
        List<TrayectoEntity> TRAYECTO = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los trayectos");
        return TRAYECTO;
    }

    /**
     * Borra el trayecto con id pasado por parámetro.
     *
     * @param trayectoId
     * @throws BusinessLogicException Si no encuentra el trayecto.
     */
    public void deletetTrayecto(Long trayectoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Trayecto con id = {0}", trayectoId);
        persistence.delete(trayectoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", trayectoId);
    }

    /**
     * Obtiene el trayecto por id pasado por parámetro.
     *
     * @param idTrayecto
     * @return El trayecto, si lo encuentra.
     */
    public TrayectoEntity getTrayeto(Long idTrayecto) {

        LOGGER.log(Level.INFO, "Inicia proceso de consultar el Trayecto con id = {0}", idTrayecto);
        TrayectoEntity entity = persistence.find(idTrayecto);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "El Trayecto con el id = {0} no existe", idTrayecto);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el Trayecto con id = {0}", idTrayecto);
        return entity;
    }

    /**
     * Obtener el pago del trayecto.
     * @param idTrayecto
     * @return El pago.
     */
    public List<PagoEntity> getPago(Long idTrayecto) {
        TrayectoEntity entity = persistence.find(idTrayecto);
        List<PagoEntity> pago = null;
        if (entity != null) {
            pago = entity.getPago();
        }
        return pago;
    }

    /**
     * Obtiene todos los trayectos.
     *
     * @return Una lista.
     */
    public List<TrayectoEntity> getBooksTrayectos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros");
        List<TrayectoEntity> trayectos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los libros");
        return trayectos;
    }

    /**
     * Actualiza el trayecto con id pasado por parámetro.
     *
     * @param trayectoId
     * @param bookEntity
     * @return El nuevo trayecto.
     * @throws BusinessLogicException Si la fecha inicial es null.
     */
    public TrayectoEntity updateBook(Long trayectoId, TrayectoEntity bookEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el Trayecto con id = {0}", bookEntity);
        if (bookEntity.getFechaInicial() == null) {
            throw new BusinessLogicException("Datos invalidos, la fecha incicial no puede ser null");
        }
        TrayectoEntity newEntity = persistence.update(bookEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Trayecto con id = {0}", bookEntity.getId());
        return newEntity;
    }
}
