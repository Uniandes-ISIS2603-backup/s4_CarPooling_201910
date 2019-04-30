/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author df.penap
 */
@Stateless
public class AlquilerPersistence {

    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;

    /**
     * Persiste (guarda) un nuevo registro en la base de datos.
     *
     * @param alquilerEntity Es la nueva entidad a persistir.
     * @return alquilerEntity la entidad guardada.
     */
    public AlquilerEntity create(AlquilerEntity alquilerEntity) {
        em.persist(alquilerEntity);
        return alquilerEntity;
    }

    /**
     * Busca un registro de la base de datos
     *
     * @param alquilerId El id del registro que se está buscando.
     * @return AlquilerEntity Si encuentra el registro, devuelve la entidad
     * correspondiente.
     */
    public AlquilerEntity find(Long alquilerId) {
        return em.find(AlquilerEntity.class, alquilerId);
    }

    /**
     * Devuelve todos los registros que se encuentran en la tabla
     * AlquilerEntity.
     *
     * @return Una lista de entidades.
     */
    public List<AlquilerEntity> findAll() {
        //TypedQuery<AlquilerEntity> query = em.createQuery("select u from AlquilerEntity u", AlquilerEntity.class);
        TypedQuery query = em.createQuery("select u from AlquilerEntity u", AlquilerEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza el registro que entra por parámetro.
     *
     * @param alquilerEntity Es la entidad que se desea actualizar.
     * @return La entidad con los nuevos datos.
     */
    public AlquilerEntity update(AlquilerEntity alquilerEntity) {
        return em.merge(alquilerEntity);
    }

    /**
     * Borra la entidad con el id que se pasa por parámetro.
     *
     * @param alquilerId Id del registro a eliminar.
     */
    public void delete(Long alquilerId) {
        AlquilerEntity entity = em.find(AlquilerEntity.class, alquilerId);
        em.remove(entity);

    }

    /**
     * Busca el vehiculo con la placa pasada por parámetro.
     *
     * @param placa
     * @return El vehículo, si lo encuentra.
     */
    public AlquilerEntity findByVehiculoAlquiladoPlaca(String placa) {
        TypedQuery<AlquilerEntity> query = em.createQuery("select e from AlquilerEntity e where e.vehiculoAlquilado.placa = :p", AlquilerEntity.class);
        query = query.setParameter("p", placa);
        List<AlquilerEntity> samePlaca = query.getResultList();
        AlquilerEntity result;
        if (samePlaca == null) {
            result = null;
        } else if (samePlaca.isEmpty()) {
            result = null;
        } else {
            result = samePlaca.get(0);
        }
        return result;
    }
}
