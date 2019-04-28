/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class UsuarioTrayectoLogic {
 
    @Inject
    private UsuarioPersistence usuarioPersistence;

    @Inject
    private TrayectoPersistence trayectoPersistence;
    
    public TrayectoEntity addTrayectoConductor(String username, TrayectoEntity trayecto) {
        TrayectoEntity trayectoEntity = trayectoPersistence.create(trayecto);
        UsuarioEntity usuarioEntity = usuarioPersistence.findByUserName(username);
        trayectoEntity.setConductor(usuarioEntity);
        usuarioEntity.addTrayectoConductor(trayectoEntity);
        return trayectoEntity;
    }
    
}
