/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.PagoPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jf.garcia
 */
@Stateless
public class PagoLogic {

    private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());

    @Inject
    private PagoPersistence persistence;

    /**
     * Crea un pago.
     *
     * @param pago
     * @return pago creado
     * @throws BusinessLogicException Si existe un pago con el mismo id.
     */
    public PagoEntity createPago(PagoEntity pago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del pago");
        if (persistence.find(pago.getId()) != null) {
            throw new BusinessLogicException("Ya existe una un pago con el id \"" + pago.getId() + "\"");
        }
        persistence.create(pago);
        LOGGER.log(Level.INFO, "Termina proceso de creación del pago");
        return pago;
    }

    /**
     * Consulta todos los pagos.
     *
     * @return Lista de todos los pagos.
     */
    public List<PagoEntity> getPagos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los pagos");
        List<PagoEntity> pagos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los pagos");
        return pagos;
    }
    
    
    /**
     * Consulta toda la info de los pagos.
     *
     * @return Lista de toda la información de los pagos.
     */
    public List<InfoTCEntity> getAllInfo() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar toda la información");
        List<InfoTCEntity> info = new ArrayList<>();
        List<PagoEntity> pagos = getPagos();
        for (int i = 0; i < pagos.size(); i++) {
            InfoTCEntity newInfo = pagos.get(i).getInfoTC();
            info.add(newInfo);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar toda la información");
        return info;
    }

    /**
     * Consultar un pago.
     *
     * @param pagoId
     * @return El pago, si existe.
     */
    public PagoEntity getPago(Long pagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el pago con id = {0}", pagoId);
        PagoEntity pagoEntity = persistence.find(pagoId);
        if (pagoEntity == null) {
            LOGGER.log(Level.SEVERE, "El pago con el id = {0} no existe", pagoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el pago con id = {0}", pagoId);
        return pagoEntity;
    }

    /**
     * Actualiza el pago.
     *
     * @param pagoId
     * @param pagoEntity
     * @return El nuevo pago.
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     */
    public PagoEntity updatePago(Long pagoId, PagoEntity pagoEntity) throws BusinessLogicException {
        if (pagoEntity.getId() == null) {
            throw new BusinessLogicException("El pago no existe");
        }
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el pago con id = {0}", pagoId);
        PagoEntity newEntity = persistence.update(pagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el pago con id = {0}", pagoEntity.getId());
        return newEntity;
    }

    /**
     * Borra el pago con el id pasado por parámetro.
     *
     * @param pagoId
     * @throws BusinessLogicException
     */
    public void deletePago(Long pagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el pago con id = {0}", pagoId);
        if (persistence.find(pagoId) == null) {
            throw new BusinessLogicException("El pago con el id: " + pagoId + "no existe");
        }
        persistence.delete(pagoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el pago con id = {0}", pagoId);
    }
}
