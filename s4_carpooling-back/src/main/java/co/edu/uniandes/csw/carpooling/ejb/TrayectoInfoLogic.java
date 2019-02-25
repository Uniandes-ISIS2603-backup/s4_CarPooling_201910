/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoInfoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alejo
 */
@Stateless
public class TrayectoInfoLogic {
    
    @Inject
    private TrayectoInfoPersistence persistence;
    
    public TrayectoInfoEntity createEntity(TrayectoInfoEntity info){
        
        info = persistence.create(info);
        return info;
    }
}
