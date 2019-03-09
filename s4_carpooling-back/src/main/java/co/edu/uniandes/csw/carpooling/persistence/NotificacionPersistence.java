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
    
    public NotificacionEntity create(NotificacionEntity notificacionEntity) {
        em.persist(notificacionEntity);
        return notificacionEntity;
    }
    
    public NotificacionEntity find(Long notificacionId) {
        return em.find(NotificacionEntity.class, notificacionId);
    }
    
    public List<NotificacionEntity> findAll() {
        TypedQuery query = em.createQuery("Select u from NotificacionEntity u", NotificacionEntity.class);
        return query.getResultList();
    }
    
    
    public NotificacionEntity update(NotificacionEntity entity){
        return em.merge(entity);
    }
    
    public void delete(Long notificacionId)
    {
        NotificacionEntity entity = em.find(NotificacionEntity.class, notificacionId);
        em.remove(entity);
    }
    
    
    
}
