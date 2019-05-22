/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.NotificacionPersistence;
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
public class NotificacionLogic {

    @Inject
    private NotificacionPersistence persistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    @Inject
    private TrayectoPersistence trayectoPersistence; 

    /**
     * Crea una notificación.
     *
     * @param notificacion
     * @return NotificacionEntity creada.
     */
    public NotificacionEntity createNotificacion(NotificacionEntity notificacion) {
        notificacion = persistence.create(notificacion);
        return notificacion;
    }

    /**
     * Consulta todas las notificaciones.
     *
     * @return Una lista con todas las entidades NotificacionEntity.
     */
    public List<NotificacionEntity> getNotificaciones() {
        List<NotificacionEntity> notificacion = persistence.findAll();
        return notificacion;
    }

    /**
     * Consultar una notificación.
     *
     * @param NotificacionId El id que se desea buscar.
     * @return NotificacionEntity si la encuentra.
     */
    public NotificacionEntity getNotificacion(Long NotificacionId) {
        NotificacionEntity notificacion = persistence.find(NotificacionId);
        return notificacion;
    }

    /**
     * Actualizar una notificación.
     *
     * @param idNotificacion El id de la notificación.
     * @param newNotificacion La notificación nueva.
     * @return La entidad con los nuevos datos.
     */
    public NotificacionEntity update(Long idNotificacion, NotificacionEntity newNotificacion) {
        NotificacionEntity notificacion = getNotificacion(idNotificacion);
        notificacion.setLeido(newNotificacion.isLeido());
        persistence.update(notificacion);
        return notificacion;
    }

    /**
     * Borrar una notificación.
     *
     * @param idNotificacion El id de la entidad a borrar.
     */
    public void deleteNotificacion(Long idNotificacion) {
        NotificacionEntity notificacion = persistence.find(idNotificacion);
        UsuarioEntity emisor = notificacion.getEmisor();
        UsuarioEntity receptor = notificacion.getReceptor();
        emisor.removeNotificacionEnviada(notificacion);
        receptor.removeNotificacionRecibida(notificacion);
        usuarioPersistence.update(emisor);
        usuarioPersistence.update(receptor);
        
        persistence.delete(idNotificacion);
    }

     /**
     * Añadir las relaciones.
     *
     * @param idNotificacion
     * @param idReceptor
     * @param idEmisor
     * @return La notificación con sus relaciones correspondientes.
     * @throws BusinessLogicException Si alguna de las entidades relacionadas no
     * existe o son iguales el receptor y el emisor.
     */
    public NotificacionEntity addRelacionNotificacion(Long idNotificacion, Long idReceptor, Long idEmisor) throws BusinessLogicException {
        NotificacionEntity notificacion = persistence.find(idNotificacion);
        UsuarioEntity emisor = usuarioPersistence.find(idEmisor);
        UsuarioEntity receptor = usuarioPersistence.find(idReceptor);
        if (emisor == null) {
            throw new BusinessLogicException("Usuario emisor: " + idEmisor + " no existe");
        }
        if (receptor == null) {
            throw new BusinessLogicException("Usuario receptor: " + idReceptor + " no existe");
        }
        if (emisor.equals(receptor)) {
            throw new BusinessLogicException("Usuario emisor y receptor son iguales");
        }

        notificacion.setEmisor(emisor);
        notificacion.setReceptor(receptor);
        return persistence.update(notificacion);
    }
    
    /**
     * Reemplaza la relación con el emisor.
     *
     * @param idCalificacion
     * @param usernameEmisor
     * @return Un calificacion con un nuevo emisor.
     * @throws BusinessLogicException
     */
    public NotificacionEntity replaceRelacionEmisor(Long idCalificacion, String usernameEmisor) throws BusinessLogicException {
        NotificacionEntity notificacion = persistence.find(idCalificacion);
        UsuarioEntity emisor = usuarioPersistence.findByUserName(usernameEmisor);
        if (emisor == null || notificacion == null || emisor.equals(notificacion.getReceptor())) {
            throw new BusinessLogicException("Relacion de notificacion no valida");
        }
       
        notificacion.setEmisor(emisor);
        emisor.addNotificacionEnviada(notificacion);
        usuarioPersistence.update(emisor);
        return persistence.update(notificacion);
    }
    
     /**
     * Reemplaza la relación con el receptor.
     *
     * @param idCalificacion
     * @param usernameReceptor
     * @return Una calificacion con un nuevo receptor.
     * @throws BusinessLogicException
     */
    public NotificacionEntity replaceRelacionReceptor(Long idCalificacion, String usernameReceptor) throws BusinessLogicException {
        NotificacionEntity notificacion = persistence.find(idCalificacion);
        UsuarioEntity receptor = usuarioPersistence.findByUserName(usernameReceptor);
        if (receptor == null || notificacion == null || receptor.equals(notificacion.getEmisor()) ) {
            throw new BusinessLogicException("Relacion de notificacion no valida");
        }
        notificacion.setReceptor(receptor);
        receptor.addNotificacionRecibida(notificacion);
        usuarioPersistence.update(receptor);
        return persistence.update(notificacion);
    }
    
    
    
    
    /**
     * Reemplaza la relación con el trayecto.
     *
     * @param idCalificacion
     * @param idTrayecto
     * @return Una calificacion con un nuevo trayecto.
     * @throws BusinessLogicException
     */
    public NotificacionEntity replaceRelacionTrayecto(Long idCalificacion, Long idTrayecto) throws BusinessLogicException {
        NotificacionEntity calificacion = persistence.find(idCalificacion);
        TrayectoEntity trayecto = trayectoPersistence.find(idTrayecto);
        if (trayecto == null || calificacion == null) {
            throw new BusinessLogicException("Relacion de calificación no valida");
        }
        calificacion.setTrayecto(trayecto);
        return persistence.update(calificacion);
    }

    
    
    
    
    
    
    
    
    
}
