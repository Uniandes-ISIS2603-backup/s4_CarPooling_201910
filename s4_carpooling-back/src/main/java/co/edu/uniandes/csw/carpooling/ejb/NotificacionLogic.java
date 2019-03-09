/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.NotificacionPersistence;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.morales11
 */

@Stateless
public class NotificacionLogic {
   
    @Inject
    private NotificacionPersistence persistence;
    
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    public NotificacionEntity createNotificacion (NotificacionEntity notificacion)
    {
        notificacion = persistence.create(notificacion);
        return notificacion;
    }
    
    
    public List<NotificacionEntity> getNotificacion()
    {
        List<NotificacionEntity> notificacion = persistence.findAll();
        return notificacion;
    }
    
    
    public NotificacionEntity getNotificacion(Long NotificacionId)
    {
        NotificacionEntity notificacion = persistence.find(NotificacionId);
        return notificacion;
    }
    
    
    public NotificacionEntity update(Long idNotificacion, NotificacionEntity newNotificacion)
    {
        NotificacionEntity notificacion = getNotificacion(idNotificacion);
        notificacion.setMensaje(newNotificacion.getMensaje());
        persistence.update(notificacion);
        return notificacion;
    }
    
    
    public void deleteNotificacion (Long idNotificacion)
    {
        persistence.delete(idNotificacion);
    }
    
    
    public NotificacionEntity addRelacionNotificacion (Long idNotificacion, Long idReceptor, Long idEmisor) throws BusinessLogicException
    {
        NotificacionEntity notificacion = persistence.find(idNotificacion);
        UsuarioEntity emisor = usuarioPersistence.find(idEmisor);
        UsuarioEntity receptor = usuarioPersistence.find(idReceptor);
        if(emisor == null)
        {
            throw new BusinessLogicException("Usuario emisor: "+ idEmisor + " no existe");
        }
        if(receptor == null)
        {
            throw new BusinessLogicException("Usuario receptor: "+ idReceptor + " no existe");
        }
        if(emisor.equals(receptor))
        {
             throw new BusinessLogicException("Usuario emisor y receptor son iguales");
        }
    
        notificacion.setEmisor(emisor);
        notificacion.setReceptor(receptor);
        return persistence.update(notificacion);    
    }
    
    
    
    
}
