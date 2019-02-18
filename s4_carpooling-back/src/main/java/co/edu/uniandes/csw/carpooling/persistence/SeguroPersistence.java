/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class SeguroPersistence {
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    public SeguroEntity create(SeguroEntity seguroEntity)
    {
      em.persist(seguroEntity);
      return seguroEntity;
    }
    
    public SeguroEntity find(Long seguroId)
    {
        return em.find(SeguroEntity.class, seguroId);
    }
    public List<SeguroEntity> findAll()
    {
        TypedQuery<SeguroEntity> query = em.createQuery("select u from SeguroEntity u", SeguroEntity.class);
        return query.getResultList();
    }
    public SeguroEntity update(SeguroEntity alquilerEntity) {
        return em.merge(alquilerEntity);
    }
    public void delete(Long alquilerId) {
        SeguroEntity entity = em.find(SeguroEntity.class, alquilerId);
        em.remove(entity);
        
    }
}
