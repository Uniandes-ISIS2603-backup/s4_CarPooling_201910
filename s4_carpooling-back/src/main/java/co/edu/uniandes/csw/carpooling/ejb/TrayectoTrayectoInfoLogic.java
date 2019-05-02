/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoInfoPersistence;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class TrayectoTrayectoInfoLogic {
    
    @Inject
    private TrayectoPersistence trayecto;
    
    @Inject
    private TrayectoInfoPersistence info;
    
    
    public TrayectoInfoEntity addInfoTrayecto(Long trayectoId, Long infoTrayectoId) {
        TrayectoInfoEntity infoEntity = info.find(infoTrayectoId);
        TrayectoEntity trayectoEntity = trayecto.find(trayectoId);
        trayectoEntity.setInfoTrayecto(infoEntity);
        return infoEntity;
    }
    
    
    
    public TrayectoInfoEntity createInfoTrayecyo(Long trayectoId, TrayectoInfoEntity infoEntity) {
        TrayectoInfoEntity tempEntity = info.create(infoEntity);
        TrayectoEntity usuarioEntity = trayecto.find(trayectoId);
        usuarioEntity.setInfoTrayecto(tempEntity);
        return tempEntity;
    }
    
    
}
