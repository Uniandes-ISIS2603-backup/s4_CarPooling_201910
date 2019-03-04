/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private static final Logger LOGGER = Logger.getLogger(TrayectoPersistence.class.getName());
    
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    public TrayectoEntity create(TrayectoEntity trayecto){
        LOGGER.log(Level.INFO, "Creando un libro Trayecto");
        em.persist(trayecto);
        LOGGER.log(Level.INFO, "Libro Trayecto");
        return trayecto;
    }
    
    public TrayectoEntity find(Long idTrayecto){
        
        return em.find(TrayectoEntity.class, idTrayecto);
    }
    
    public List<TrayectoEntity> findAll(){
        
        TypedQuery<TrayectoEntity> query = em.createQuery("select u TrayectoEntity u", TrayectoEntity.class);
        return query.getResultList();
    }

    public void delete(Long trayectoId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", trayectoId);
        TrayectoEntity bookEntity = em.find(TrayectoEntity.class, trayectoId);
        em.remove(bookEntity);}
    
    
}
