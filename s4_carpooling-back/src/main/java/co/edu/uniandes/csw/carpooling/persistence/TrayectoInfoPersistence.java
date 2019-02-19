/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
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
public class TrayectoInfoPersistence {
    
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    public TrayectoInfoEntity create(TrayectoInfoEntity info){
        em.persist(info);
        return info;
    }
    
    public TrayectoInfoEntity find(Long idInfo){
        return em.find(TrayectoInfoEntity.class, idInfo);
    }
    
    public List<TrayectoInfoEntity> findAll(){
        
        TypedQuery<TrayectoInfoEntity> query = em.createQuery("select u from TrayectoInfoEntity u", TrayectoInfoEntity.class);
    
        return query.getResultList();
    }
    
}
