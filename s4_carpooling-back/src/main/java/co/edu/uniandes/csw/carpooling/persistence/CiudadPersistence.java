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

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param ciudadEntity Es la nueva entidad a persistir.
     * @return ciudadEntity la entidad guardada.
     */
    public CiudadEntity create(CiudadEntity ciudadEntity) {
        em.persist(ciudadEntity);
        return ciudadEntity;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param ciudadId El id del registro que se está buscando.
     * @return CiudadEntity Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public CiudadEntity find(Long ciudadId) {
        return em.find(CiudadEntity.class, ciudadId);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla CiudadEntity.
     *
     * @return Una lista de entidades.
     */
    public List<CiudadEntity> findAll() {
        TypedQuery<CiudadEntity> query = em.createQuery("select u from CiudadEntity u", CiudadEntity.class);
        return query.getResultList();
    }

    /**
     * Busca el registro utilizando el atributo nombre.
     *
     * @param nombre Por el que se busca el registro en la base de datos.
     * @return CiudadEntity si se encuentra una ciudad con ese nombre.
     */
    public CiudadEntity findByName(String nombre) {
        TypedQuery<CiudadEntity> query = em.createQuery("select e from CiudadEntity e where e.nombre = :n", CiudadEntity.class);
        query = query.setParameter("n", nombre);
        List<CiudadEntity> sameNombre = query.getResultList();
        CiudadEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        return result;
    }
}
