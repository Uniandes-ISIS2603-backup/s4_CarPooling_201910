/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
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
public class TrayectoPersistence {
    
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    public TrayectoEntity create(TrayectoEntity trayecto){
        
        em.persist(trayecto);
        return trayecto;
    }
    
    public TrayectoEntity find(Long idTrayecto){
        
        return em.find(TrayectoEntity.class, idTrayecto);
    }
    
    public List<TrayectoEntity> findAll(){
        
        TypedQuery<TrayectoEntity> query = em.createQuery("select u TrayectoEntity u", TrayectoEntity.class);
        return query.getResultList();
    }
    
    
}
