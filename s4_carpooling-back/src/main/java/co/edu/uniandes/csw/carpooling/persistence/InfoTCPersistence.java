/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import java.util.List;
import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jf.garcia
 */
@Stateless
public class InfoTCPersistence {

    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param infoTCEntity Es la nueva entidad a persistir.
     * @return InfoTCEntity la entidad guardada.
     */
    public InfoTCEntity create(InfoTCEntity infoTCEntity) {
        em.persist(infoTCEntity);
        return infoTCEntity;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param infoTCId El id del registro que se está buscando.
     * @return InfoTCId Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public InfoTCEntity find(Long infoTCId) {
        return em.find(InfoTCEntity.class, infoTCId);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla InfoTCEntity.
     *
     * @return Una lista de entidades.
     */
    public List<InfoTCEntity> findAll() {
        TypedQuery<InfoTCEntity> query = em.createQuery("select u from InfoTCEntity u", InfoTCEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza el registro que entra por parámetro.
     *
     * @param infoTCEntity Es la entidad que se desea actualizar.
     * @return La entidad con los nuevos datos.
     */
    public InfoTCEntity update(InfoTCEntity infoTCEntity) {
        return em.merge(infoTCEntity);
    }

    /**
     * Borra la entidad con el id que se pasa por parámetro.
     *
     * @param infoTCId Id del registro a eliminar.
     */
    public void delete(Long infoTCId) {
        InfoTCEntity entity = em.find(InfoTCEntity.class, infoTCId);
        em.remove(entity);
    }
}
