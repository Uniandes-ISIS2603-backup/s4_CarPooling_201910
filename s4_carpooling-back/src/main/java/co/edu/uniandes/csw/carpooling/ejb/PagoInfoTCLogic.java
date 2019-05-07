/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import co.edu.uniandes.csw.carpooling.persistence.InfoTCPersistence;
import co.edu.uniandes.csw.carpooling.persistence.PagoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jf.garcia
 */
@Stateless
public class PagoInfoTCLogic {

    @Inject
    PagoPersistence pagoPersistence;

    @Inject
    InfoTCPersistence infoPersistence;

    /**
     * Agrega la información al pago.
     *
     * @param pagoId
     * @param infoId
     * @return La entidad de info.
     */
    public InfoTCEntity addInfo(Long pagoId, Long infoId) {
        InfoTCEntity infoEntity = infoPersistence.find(infoId);
        PagoEntity pagoEntity = pagoPersistence.find(pagoId);
        pagoEntity.setInfoTC(infoEntity);
        return infoEntity;
    }

    /**
     * Crear una nueva información asociada a un pago.
     *
     * @param pagoId
     * @param info
     * @return
     */
    public InfoTCEntity createInfo(Long pagoId, InfoTCEntity info) {
        InfoTCEntity infoEntity = infoPersistence.create(info);
        PagoEntity pagoEntity = pagoPersistence.find(pagoId);
        pagoEntity.setInfoTC(infoEntity);
        return infoEntity;
    }

}
