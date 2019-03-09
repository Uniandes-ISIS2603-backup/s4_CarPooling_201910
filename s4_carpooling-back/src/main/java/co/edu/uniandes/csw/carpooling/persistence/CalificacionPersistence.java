/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Julio Morales
 */
@Stateless
public class CalificacionPersistence {
    
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    
    public CalificacionEntity create(CalificacionEntity calificacionEntity) {
        em.persist(calificacionEntity);
        return calificacionEntity;
    }
    
    public CalificacionEntity find(Long calificacionEntityId){
        return em.find(CalificacionEntity.class, calificacionEntityId);
    }
    
    public List<CalificacionEntity> findAll() {
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }
    
    public CalificacionEntity update(CalificacionEntity entity)
    {
        return em.merge(entity);
    }
    
    
    public void delete(Long calificacionEntityId){
        CalificacionEntity entity = em.find(CalificacionEntity.class, calificacionEntityId);
        em.remove(entity);
    }
   
    
}
