/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ja.morales11
 */
@Stateless
public class NotificacionPersistence {

    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param notificacionEntity Es la nueva entidad a persistir.
     * @return NotificacionEntity la entidad guardada.
     */
    public NotificacionEntity create(NotificacionEntity notificacionEntity) {
        em.persist(notificacionEntity);
        return notificacionEntity;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param notificacionId El id del registro que se está buscando.
     * @return NotificacionEntity Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public NotificacionEntity find(Long notificacionId) {
        return em.find(NotificacionEntity.class, notificacionId);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla
     * NotificacionEntity.
     *
     * @return Una lista de entidades.
     */
    public List<NotificacionEntity> findAll() {
        TypedQuery query = em.createQuery("Select u from NotificacionEntity u", NotificacionEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza el registro que entra por parámetro.
     *
     * @param entity Es la entidad que se desea actualizar.
     * @return La entidad con los nuevos datos.
     */
    public NotificacionEntity update(NotificacionEntity entity) {
        return em.merge(entity);
    }

    /**
     * Borra la entidad con el id que se pasa por parámetro.
     *
     * @param notificacionId Id del registro a eliminar.
     */
    public void delete(Long notificacionId) {
        NotificacionEntity entity = em.find(NotificacionEntity.class, notificacionId);
        em.remove(entity);
    }

}
