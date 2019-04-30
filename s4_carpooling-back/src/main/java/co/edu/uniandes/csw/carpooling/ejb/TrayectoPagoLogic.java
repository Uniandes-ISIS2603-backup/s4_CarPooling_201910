/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.persistence.PagoPersistence;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class TrayectoPagoLogic {

    @Inject
    private TrayectoPersistence trayectoPersistence;

    @Inject
    private PagoPersistence pagoPersistence;
    
    public PagoEntity createPago(Long trayectoId, PagoEntity pago) {
        TrayectoEntity trayecto = trayectoPersistence.find(trayectoId);
        pago.setTrayecto(trayecto);
        return pagoPersistence.create(pago);
    }
    
    
}
