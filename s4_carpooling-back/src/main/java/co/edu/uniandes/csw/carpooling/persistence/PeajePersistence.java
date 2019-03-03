/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author im.sarmiento
 */
@Stateless
public class PeajePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PeajePersistence.class.getName());
    
    @PersistenceContext(unitName = "carpoolingPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param peajeEntity objeto peaje que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PeajeEntity create(PeajeEntity peajeEntity) {
        LOGGER.log(Level.INFO, "Creando un peaje nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(peajeEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un peaje nuevo");
        return peajeEntity;
    }
	
	/**
     * Devuelve todas los peajes de la base de datos.
     *
     * @return una lista con todos los peajes que encuentre en la base de
     * datos, "select u from PeajeEntity u" es como un "select * from
     * EditorialEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<PeajeEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los peajes");
        // Se crea un query para buscar todas los peajes en la base de datos.
        TypedQuery query = em.createQuery("select u from PeajeEntity u", PeajeEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de peakes.
        return query.getResultList();
    }
	
    /**
     * Busca si hay algun peaje con el id que se envía de argumento
     *
     * @param peajesId: id correspondiente al peaje buscado.
     * @return un peaje.
     */
    public PeajeEntity find(Long peajesId) {
        LOGGER.log(Level.INFO, "Consultando peaje con id={0}", peajesId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from PeajeEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(PeajeEntity.class, peajesId);
    }

	 /**
     * Actualiza un peaje.
     *
     * @param peajeEntity: el peaje con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un peaje con los cambios aplicados.
     */
    public PeajeEntity update(PeajeEntity peajeEntity) {
        LOGGER.log(Level.INFO, "Actualizando peaje con id = {0}", peajeEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el peaje con cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el peaje con id = {0}", peajeEntity.getId());
        return em.merge(peajeEntity);
    }
	
    /**
     *
     * Borra un peaje de la base de datos recibiendo como argumento el id
     * del peaje
     *
     * @param peajesId: id correspondiente al peaje a borrar.
     */
    public void delete(Long peajesId) {
        LOGGER.log(Level.INFO, "Borrando peaje con id = {0}", peajesId);
        // Se hace uso de mismo método que esta explicado en public PeajeEntity find(Long id) para obtener el peaje a borrar.
        PeajeEntity entity = em.find(PeajeEntity.class, peajesId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PeajeEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el peaje con id = {0}", peajesId);
    }
	
    /**
     * Busca si hay alguna peaje con el nombre que se envía de argumento
     *
     * @param name: Nombre del peaje que se está buscando
     * @return null si no existe ningun peaje con el nombre del argumento.
     * Si existe alguno devuelve el primero.
     */
    public PeajeEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando peaje por nombre ", name);
        // Se crea un query para buscar peajes con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From PeajeEntity e where e.nombre = :name", PeajeEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<PeajeEntity> sameName = query.getResultList();
        PeajeEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar peaje por nombre ", name);
        return result;
    }
    
}
