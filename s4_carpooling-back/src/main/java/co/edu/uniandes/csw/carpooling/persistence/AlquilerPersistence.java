/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author df.penap
 */
@Stateless
public class AlquilerPersistence {
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    public AlquilerEntity create(AlquilerEntity alquilerEntity)
    {
      em.persist(alquilerEntity);
      return alquilerEntity;
    }
    
    public AlquilerEntity find(Long alquilerId)
    {
        return em.find(AlquilerEntity.class, alquilerId);
    }
    public List<AlquilerEntity> findAll()
    {
        TypedQuery<AlquilerEntity> query = em.createQuery("select u from AlquilerEntity u", AlquilerEntity.class);
        return query.getResultList();
    }
    public AlquilerEntity update(AlquilerEntity alquilerEntity) {
        return em.merge(alquilerEntity);
    }
    public void delete(Long alquilerId) {
        AlquilerEntity entity = em.find(AlquilerEntity.class, alquilerId);
        em.remove(entity);
        
    }
}
