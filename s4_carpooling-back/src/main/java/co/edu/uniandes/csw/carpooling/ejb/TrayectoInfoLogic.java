/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoInfoPersistence;
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
public class TrayectoInfoLogic {

    private static final Logger LOGGER = Logger.getLogger(TrayectoInfoLogic.class.getName());

    @Inject
    private TrayectoInfoPersistence persistence;

    /**
     * Crea un trayecto.
     *
     * @param info Información que se necesita para el trayecto.
     * @return La entidad creada.
     * @throws BusinessLogicException Si la información no es correcta.
     */
    public TrayectoInfoEntity createEntity(TrayectoInfoEntity info) throws BusinessLogicException {

        if (info.getHoraInicial() == null) {
            throw new BusinessLogicException("El trayecto debe tener una hora de salida");
        }
        if (info.getDuracion() < 0) {
            throw new BusinessLogicException("la duración del trayecto no puede ser negativa");
        }
        if (info.getCosto() < 0) {
            throw new BusinessLogicException("El costo del trayecto no puede ser negativo");
        }
        info = persistence.create(info);
        return info;
    }

    /**
     * Devuelve todos los TrayectoInfos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo TrayectoInfo.
     */
    public List<TrayectoInfoEntity> getTrayectoInfos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los TrayectoInfos");
        List<TrayectoInfoEntity> infos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los TrayectoInfos");
        return infos;
    }

    /**
     * Busca un trayecto con el id pasado por parámetro.
     *
     * @param infoId El id del trayecto a buscar
     * @return El trayecto encontrado, null si no lo encuentra.
     */
    public TrayectoInfoEntity getTrayectoInfo(Long infoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el TrayectoInfo con id = {0}", infoId);
        TrayectoInfoEntity infoEntity = persistence.find(infoId);
        if (infoEntity == null) {
            LOGGER.log(Level.SEVERE, "El libro con el id = {0} no existe", infoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el TrayectoInfo con id = {0}", infoId);
        return infoEntity;
    }

    /**
     * Actualizar un trayecto por ID
     *
     * @param infoId El ID del libro a actualizar
     * @param trayectoEntity La entidad del TrayectoInfo con los cambios
     * deseados
     * @return La entidad del libro luego de actualizarla
     */
    public TrayectoInfoEntity updateTrayecoInfo(Long infoId, TrayectoInfoEntity trayectoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el TrayectoInfo con id = {0}", infoId);
        TrayectoInfoEntity newEntity = persistence.update(trayectoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el TrayectoInfo con id = {0}", trayectoEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un trayecto por ID
     *
     * @param infoId El ID del trayecto a eliminar
     */
    public void deleteTrayectoInfo(Long infoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el libro con id = {0}", infoId);
        persistence.delete(infoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", infoId);
    }
}
