/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.morales11
 */
@Stateless
public class CalificacionLogic {
    
    @Inject
    private CalificacionPersistence persistence;
    
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    @Inject
    private TrayectoPersistence trayectoPersistence;
    
    public CalificacionEntity createCalificacion (CalificacionEntity calificacion) throws BusinessLogicException
    {
        if(calificacion.getPuntaje() < 1 || calificacion.getPuntaje() > 5)
        {
            throw new BusinessLogicException("Datos inválidos, el puntaje no puede ser menor a 1 o mayor a 5");
        }
        if(persistence.find(calificacion.getId()) != null)
        {
            throw new BusinessLogicException("Ya existe una calificacion con ese id");
        }
        calificacion = persistence.create(calificacion);
        return calificacion; 
    }
    
    public List<CalificacionEntity> getCalificacion ()
    {
        List <CalificacionEntity> calificacion = persistence.findAll();
        return calificacion;
    }
    
    public CalificacionEntity getCalificacion(Long idCalificacion)
    {
        CalificacionEntity calificacion= persistence.find(idCalificacion);
        return calificacion;
    }
    
    public void deleteCalificacion (Long idCalificacion)
    {
        persistence.delete(idCalificacion);
    }
    
    public CalificacionEntity addRelacionCalificacion (Long idCalificacion, Long idTrayecto, Long idCalificado, Long idCalificador) throws BusinessLogicException
    {
        UsuarioEntity calificado = usuarioPersistence.find(idCalificado);
        UsuarioEntity calificador = usuarioPersistence.find(idCalificador);
        CalificacionEntity calificacion = persistence.find(idCalificacion);
        TrayectoEntity trayecto = trayectoPersistence.find(idTrayecto);
        
        if(calificado== null)
        {
            throw new BusinessLogicException("Usuario calificado: "+ idCalificado + " no existe");
        }
        if (calificador== null)
        {
            throw new BusinessLogicException("Usuario calificador: "+ idCalificador + " no existe");
        }
        if(calificado.equals(calificador))
        {
            throw new BusinessLogicException("Usuario calificador y calificado son iguales");
        }
        if(trayecto == null)
        {
            throw new BusinessLogicException("Trayecto : " + idTrayecto + " no existe");
        }
        
        calificacion.setCalificado(calificado);
        calificacion.setCalificador(calificador);
        calificacion.setTrayecto(trayecto);
        
        return persistence.update(calificacion);
        
    }
    
    
}
