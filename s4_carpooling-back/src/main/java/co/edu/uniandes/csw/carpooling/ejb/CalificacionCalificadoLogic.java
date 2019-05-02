/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.morales11
 */
@Stateless
public class CalificacionCalificadoLogic {
    
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    
    public CalificacionEntity setCalificado(String username, Long calificacionId){
        UsuarioEntity calificadoEntity = usuarioPersistence.findByUserName(username);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        calificacionEntity.setCalificado(calificadoEntity);
        return calificacionEntity;
    }
        
    
    public void removeCalificado(Long calificacionId){
        CalificacionEntity calificacionEntity = calificacionPersistence.find(calificacionId);
        UsuarioEntity calificadoEntity = usuarioPersistence.findByUserName(calificacionEntity.getCalificado().getUsername());
        calificacionEntity.setCalificado(null);
        calificadoEntity.getCalificaciones().remove(calificacionEntity);
    }
    
    
    
    
    
    
}
