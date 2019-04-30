/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import co.edu.uniandes.csw.carpooling.persistence.PeajePersistence;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoInfoPersistence;
import javax.ejb.Stateless;

import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class InfoTrayectoPeajeLogic {
    
    @Inject
    private PeajePersistence peajePersistence;

    @Inject
    private TrayectoInfoPersistence trayectoInfoPersistence;
    
    public TrayectoInfoEntity addPeaje(Long infoTrayectoId, Long peajeId) {
        TrayectoInfoEntity trayecto = trayectoInfoPersistence.find(infoTrayectoId);
        PeajeEntity peaje = peajePersistence.find(peajeId);
        trayecto.addPeaje(peaje);
        return trayecto;
    }
    
}
