/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.CiudadEntity;
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
public class CiudadPersistence {
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    public CiudadEntity create(CiudadEntity ciudadEntity)
    {
      em.persist(ciudadEntity);
      return ciudadEntity;
    }
    
    public CiudadEntity find(Long ciudadId)
    {
        return em.find(CiudadEntity.class, ciudadId);
    }
    public List<CiudadEntity> findAll()
    {
        TypedQuery<CiudadEntity> query = em.createQuery("select u from CiudadEntity u", CiudadEntity.class);
        return query.getResultList();
    }
    
    }
