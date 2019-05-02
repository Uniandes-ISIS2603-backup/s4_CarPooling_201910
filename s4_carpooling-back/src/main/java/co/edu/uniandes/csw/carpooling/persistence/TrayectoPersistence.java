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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class TrayectoPersistence {

    public static final Integer TRAYECTO_PASADO = 1;
    public static final Integer TRAYECTO_ACTUAL = 2;
    public static final Integer TRAYECTO_PLANEADO = 3;
    public static final Integer TRAYECTODENEGADO = 4;

    private static final Logger LOGGER = Logger.getLogger(TrayectoPersistence.class.getName());

    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param trayecto Es la nueva entidad a persistir.
     * @return TrayectoEntity la entidad guardada.
     */
    public TrayectoEntity create(TrayectoEntity trayecto) {
        LOGGER.log(Level.INFO, "Creando un Trayecto");
        em.persist(trayecto);
        LOGGER.log(Level.INFO, "Libro Trayecto");
        return trayecto;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param idTrayecto El id del registro que se está buscando.
     * @return TrayectoEntity Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public TrayectoEntity find(Long idTrayecto) {
        LOGGER.log(Level.INFO, "Consultando el Trayecto con id={0}", idTrayecto);
        return em.find(TrayectoEntity.class, idTrayecto);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla PagoEntity.
     *
     * @return Una lista de entidades.
     */
    public List<TrayectoEntity> findAll() {

        TypedQuery query = em.createQuery("select u from TrayectoEntity u",TrayectoEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza el registro que entra por parámetro.
     *
     * @param trayectoEntity Es la entidad que se desea actualizar.
     * @return La entidad con los nuevos datos.
     */
    public TrayectoEntity update(TrayectoEntity trayectoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el Trayecto con id={0}", trayectoEntity.getId());
        return em.merge(trayectoEntity);
    }

    /**
     * Borra la entidad con el id que se pasa por parámetro.
     *
     * @param trayectoId Id del registro a eliminar.
     */
    public void delete(Long trayectoId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", trayectoId);
        TrayectoEntity bookEntity = em.find(TrayectoEntity.class, trayectoId);
        em.remove(bookEntity);
    }
}
