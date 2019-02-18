/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.persistence;

import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Isabela Sarmiento
 */
@Stateless
public class UsuarioPersistence {
    
    @PersistenceContext (unitName = "carpoolingPU")
    protected EntityManager em;
    
    public UsuarioEntity create (UsuarioEntity usuarioEntity){
        em.persist(usuarioEntity);
        return usuarioEntity;
    }
    
    public UsuarioEntity find (Long usuarioId){
        return em.find(UsuarioEntity.class, usuarioId);
    }
    
    public List<UsuarioEntity> findAll() {
        TypedQuery<UsuarioEntity> query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }
    
  
    public UsuarioEntity update(UsuarioEntity usuarioEntity) {
      
        return em.merge(usuarioEntity);
    }
	
    /**
     *
     * Borra un usuario de la base de datos recibiendo como argumento el id
     * del usuario
     *
     * @param usuariosId: id correspondiente al peaje a borrar.
     */
    public void delete(Long usuariosId) {
        //LOGGER.log(Level.INFO, "Borrando usuario con id = {0}", usuariosId);
        // Se hace uso de mismo método que esta explicado en public UsuarioEntity find(Long id) para obtener el usuario a borrar.
        UsuarioEntity entity = em.find(UsuarioEntity.class, usuariosId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PeajeEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        //LOGGER.log(Level.INFO, "Saliendo de borrar el usuario con id = {0}", usuariosId);
    }
	
    /**
     * Busca si hay alguna usuario con el nombre de usuario que se envía de argumento
     *
     * @param name: Nombre de usuario del usuario que se está buscando
     * @return null si no existe ningun usuario con el nombre del argumento.
     * Si existe alguno devuelve el primero.
     */
    public UsuarioEntity findByUserName(String username) {
        //LOGGER.log(Level.INFO, "Consultando peaje por nombre ", username);
        // Se crea un query para buscar usuarios con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.username = :username", UsuarioEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("username", username);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> sameName = query.getResultList();
        UsuarioEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        //LOGGER.log(Level.INFO, "Saliendo de consultar peaje por nombre ", username);
        return result;
    }
}

