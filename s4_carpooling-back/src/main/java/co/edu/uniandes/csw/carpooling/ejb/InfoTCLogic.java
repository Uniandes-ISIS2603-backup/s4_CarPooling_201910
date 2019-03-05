/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.InfoTCPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author jf.garcia
 */
@Stateless
public class InfoTCLogic {
    
    private static final Logger LOGGER = Logger.getLogger(InfoTCLogic.class.getName());
    
    private InfoTCPersistence persistence;
    
    /**
     * Crea la información de las tarjetas asociadas al pago.
     * @param info
     * @return La info creada.
     * @throws BusinessLogicException Si ya existe una info con ese id.
     */
    public InfoTCEntity createInfoTC(InfoTCEntity info) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la info");
        if (persistence.find(info.getId()) != null) {
            throw new BusinessLogicException("Ya existe una una info con el id \"" + info.getId() + "\"");
        }
        persistence.create(info);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la info");
        return info;
    }
    
    /**
     * Consulta toda la información de tarjetas.
     * @return lista con toda la información.
     */
    public List<InfoTCEntity> getInfoTCs() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar toda la información");
        List<InfoTCEntity> info = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar toda la información");
        return info;
    }
        
    /**
     * Consultar un par de tarjetas.
     * @param infoId
     * @return La información, si existe.
     */
    public InfoTCEntity getInfoTC(Long infoId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la información con id = {0}", infoId);
        InfoTCEntity info = persistence.find(infoId);
        if (info == null) {
            LOGGER.log(Level.SEVERE, "La información con el id = {0} no existe", infoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la información con id = {0}", infoId);
        return info;
    }
     
    /**
     * Actualiza la información de las tarjetas asociadas al pago.
     * @param infoId
     * @param info
     * @return La nueva información.
     */
    public InfoTCEntity updateInfoTC(Long infoId, InfoTCEntity info) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la información con id = {0}", infoId);
        InfoTCEntity newEntity = persistence.update(info);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la información con id = {0}", info.getId());
        return newEntity;
    }
    
    /**
     * Borra la información de las tarjetas con el id pasado por parámetro.
     * @param infoId
     * @throws BusinessLogicException 
     */
    public void deleteInfoTC(Long infoId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la información con id = {0}", infoId);
        if (persistence.find(infoId) == null) {
            throw new BusinessLogicException("La información con el id: " + infoId + "no existe");
        }
        persistence.delete(infoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la información con id = {0}", infoId);
    }
}
