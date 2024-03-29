/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Julio Morales
 */
@Stateless
public class CalificacionPersistence {

    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param calificacionEntity Es la nueva entidad a persistir.
     * @return CalificacionEntity la entidad guardada.
     */
    public CalificacionEntity create(CalificacionEntity calificacionEntity) {
        em.persist(calificacionEntity);
        return calificacionEntity;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param calificacionEntityId El id del registro que se está buscando.
     * @return CalificacionEntity Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public CalificacionEntity find(Long calificacionEntityId) {
        return em.find(CalificacionEntity.class, calificacionEntityId);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla
     * CalificacionEntity.
     *
     * @return Una lista de entidades.
     */
    public List<CalificacionEntity> findAll() {
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza el registro que entra por parámetro.
     *
     * @param entity Es la entidad que se desea actualizar.
     * @return La entidad con los nuevos datos.
     */
    public CalificacionEntity update(CalificacionEntity entity) {
        return em.merge(entity);
    }

    /**
     * Borra la entidad con el id que se pasa por parámetro.
     *
     * @param calificacionEntityId Id del registro a eliminar.
     */
    public void delete(Long calificacionEntityId) {
        CalificacionEntity entity = em.find(CalificacionEntity.class, calificacionEntityId);
        em.remove(entity);
    }
    
    /**
     * Busca si hay calificaciones con el id del trayecto asociado que se envía de argumento
     *
     * @param idTrayecto: id del trayecto asociado a la calificación que se está buscando
     * @return null si no existe ninguna calificación asociada con el id del trayecto del argumento.
     * Si existe devuelve una lista de entidades
    */
    public List<CalificacionEntity> findByTrayecto(Long idTrayecto){
        TypedQuery query = em.createQuery("Select e From CalificacionEntity e where e.trayecto = :idTrayecto", CalificacionEntity.class);
        query = query.setParameter("idTrayecto", idTrayecto);
        return query.getResultList();
    }
     
    
    
    public List<CalificacionEntity> findByCalificado(Long idUsuario)
    {
        TypedQuery query = em.createQuery("Select u From CalificacionEntity u where u.calificado = :calificado", CalificacionEntity.class);
        query = query.setParameter("calificado", idUsuario);
        List<CalificacionEntity> lista = query.getResultList();
        
        return lista;
    }
}
