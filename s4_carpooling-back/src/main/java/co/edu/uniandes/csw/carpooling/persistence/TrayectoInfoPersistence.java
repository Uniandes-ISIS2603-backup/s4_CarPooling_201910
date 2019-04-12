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

/**
 *
 * @author estudiante
 */
@Stateless
public class TrayectoInfoPersistence {

    private static final Logger LOGGER = Logger.getLogger(TrayectoInfoPersistence.class.getName());

    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param TrayectoInfoEntity Es la nueva entidad a persistir.
     * @return TrayectoEntity la entidad guardada.
     */
    public TrayectoInfoEntity create(TrayectoInfoEntity info) {
        LOGGER.log(Level.INFO, "Comienzó la creación de un nuevo Trayecto");
        em.persist(info);
        LOGGER.log(Level.INFO, "Nuevo Trayecto Creado");
        return info;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param idInfo El id del registro que se está buscando.
     * @return TrayectoEntity Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public TrayectoInfoEntity find(Long idInfo) {
        LOGGER.log(Level.INFO, "Consultando el TrayectoInfo con id={0}", idInfo);
        return em.find(TrayectoInfoEntity.class, idInfo);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla PagoEntity.
     *
     * @return Una lista de entidades.
     */
    public List<TrayectoInfoEntity> findAll() {

        Query query = em.createQuery("select u from TrayectoInfoEntity u");

        return query.getResultList();
    }

    /**
     * Actualiza el registro que entra por parámetro.
     *
     * @param TrayectoInfoEntity Es la entidad que se desea actualizar.
     * @return La entidad con los nuevos datos.
     */
    public TrayectoInfoEntity update(TrayectoInfoEntity info) {
        LOGGER.log(Level.INFO, "Actualizando el TrayectoInfo con id={0}", info.getId());
        return em.merge(info);
    }

    /**
     * Borra la entidad con el id que se pasa por parámetro.
     *
     * @param idInfo Id del registro a eliminar.
     */
    public void delete(Long idInfo) {
        LOGGER.log(Level.INFO, "Borrando el TrayectoInfo con id={0}", idInfo);
        TrayectoInfoEntity del = em.find(TrayectoInfoEntity.class, idInfo);
        em.remove(del);
    }

}
