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

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param seguroEntity Es la nueva entidad a persistir.
     * @return SeguroEntity la entidad guardada.
     */
    public SeguroEntity create(SeguroEntity seguroEntity) {
        em.persist(seguroEntity);
        return seguroEntity;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param seguroId El id del registro que se está buscando.
     * @return SeguroEntity Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public SeguroEntity find(Long seguroId) {
        return em.find(SeguroEntity.class, seguroId);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla SeguroEntity.
     *
     * @return Una lista de entidades.
     */
    public List<SeguroEntity> findAll() {
        TypedQuery<SeguroEntity> query = em.createQuery("select u from SeguroEntity u", SeguroEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza el registro que entra por parámetro.
     *
     * @param seguroEntity Es la entidad que se desea actualizar.
     * @return La entidad con los nuevos datos.
     */
    public SeguroEntity update(SeguroEntity seguroEntity) {
        return em.merge(seguroEntity);
    }

    /**
     * Borra la entidad con el id que se pasa por parámetro.
     *
     * @param seguroId Id del registro a eliminar.
     */
    public void delete(Long seguroId) {
        SeguroEntity entity = em.find(SeguroEntity.class, seguroId);
        em.remove(entity);

    }

    /**
     * Busca la entidad pasando el tipo por parámetro.
     *
     * @param tipo
     * @return Una lista de entidades con el mismo tipo.
     */
    public SeguroEntity findByTipo(String tipo) {
        TypedQuery<SeguroEntity> query = em.createQuery("select e from SeguroEntity e where e.tipo = :t", SeguroEntity.class);
        query = query.setParameter("t", tipo);
        List<SeguroEntity> sameTipo = query.getResultList();
        SeguroEntity result;
        if (sameTipo == null) {
            result = null;
        } else if (sameTipo.isEmpty()) {
            result = null;
        } else {
            result = sameTipo.get(0);
        }
        return result;
    }
}
