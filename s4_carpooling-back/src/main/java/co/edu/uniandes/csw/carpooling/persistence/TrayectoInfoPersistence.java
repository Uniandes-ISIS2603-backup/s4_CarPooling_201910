/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class TrayectoInfoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TrayectoInfoPersistence.class.getName());
    
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    public TrayectoInfoEntity create(TrayectoInfoEntity info){
        LOGGER.log(Level.INFO, "Comienzó la creación de un nuevo Trayecto");
        em.persist(info);
        LOGGER.log(Level.INFO, "Nuevo Trayecto Creado");
        return info;
    }
    
    public TrayectoInfoEntity find(Long idInfo){
        LOGGER.log(Level.INFO, "Consultando el TrayectoInfo con id={0}", idInfo);
        return em.find(TrayectoInfoEntity.class, idInfo);
    }
    
    public List<TrayectoInfoEntity> findAll(){
        
        Query query = em.createQuery("select u from TrayectoInfoEntity u");
    
        return query.getResultList();
    }
    
    public TrayectoInfoEntity update(TrayectoInfoEntity info){
        LOGGER.log(Level.INFO, "Actualizando el TrayectoInfo con id={0}", info.getId());
        return em.merge(info);
    }
    
    public void delete(Long idInfo){
        LOGGER.log(Level.INFO, "Borrando el TrayectoInfo con id={0}", idInfo);   
        TrayectoInfoEntity del = em.find(TrayectoInfoEntity.class, idInfo);
        em.remove(del);
    }
    
    
    
}
