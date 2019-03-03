/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
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
public class VehiculoPersistence {
    
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    public VehiculoEntity create (VehiculoEntity vehiculoEntity)
    {
        em.persist(vehiculoEntity);
        return vehiculoEntity;
    }
    public VehiculoEntity find(Long vehiculoId)
    {
      return em.find(VehiculoEntity.class, vehiculoId); 
    }
    public List<VehiculoEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from VehiculoEntity u", VehiculoEntity.class);
        return query.getResultList();
    }
     public VehiculoEntity update(VehiculoEntity vEntity) {
        return em.merge(vEntity);
    }
    public void delete(Long vId) {
        VehiculoEntity entity = em.find(VehiculoEntity.class, vId);
        em.remove(entity);
        
    }
}
