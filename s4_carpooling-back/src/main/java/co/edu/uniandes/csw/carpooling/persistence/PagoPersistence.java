/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import java.util.List;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jf.garcia
 */
@Stateless
public class PagoPersistence {

    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param pagoEntity Es la nueva entidad a persistir.
     * @return pagoEntity la entidad guardada.
     */
    public PagoEntity create(PagoEntity pagoEntity) {
        em.persist(pagoEntity);
        return pagoEntity;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param pagoId El id del registro que se está buscando.
     * @return PagoEntity Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public PagoEntity find(Long pagoId) {
        return em.find(PagoEntity.class, pagoId);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla PagoEntity.
     *
     * @return Una lista de entidades.
     */
    public List<PagoEntity> findAll() {
        TypedQuery<PagoEntity> query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza el registro que entra por parámetro.
     *
     * @param pagoEntity Es la entidad que se desea actualizar.
     * @return La entidad con los nuevos datos.
     */
    public PagoEntity update(PagoEntity pagoEntity) {
        return em.merge(pagoEntity);
    }

    /**
     * Borra la entidad con el id que se pasa por parámetro.
     *
     * @param pagoId Id del registro a eliminar.
     */
    public void delete(Long pagoId) {
        PagoEntity entity = em.find(PagoEntity.class, pagoId);
        em.remove(entity);
    }
}
