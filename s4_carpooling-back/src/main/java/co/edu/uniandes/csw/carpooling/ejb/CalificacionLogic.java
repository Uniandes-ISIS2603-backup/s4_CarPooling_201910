/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.morales11
 */
@Stateless
public class CalificacionLogic {

    @Inject
    private CalificacionPersistence persistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    @Inject
    private TrayectoPersistence trayectoPersistence;

    /**
     * Crea una calificación.
     *
     * @param calificacion
     * @return CalificacionEntity creada.
     * @throws BusinessLogicException Si existe una calificación con el mismo id
     * o si el puntaje es menor que 1 o mayor a 5.
     */
    public CalificacionEntity createCalificacion(CalificacionEntity calificacion) throws BusinessLogicException {
        if (calificacion.getPuntaje() < 1 || calificacion.getPuntaje() > 5) {
            throw new BusinessLogicException("Datos inválidos, el puntaje no puede ser menor a 1 o mayor a 5");
        }
        
        calificacion = persistence.create(calificacion);
        return calificacion;
    }

    /**
     * Consulta todas las calificaciones.
     *
     * @return
     */
    public List<CalificacionEntity> getCalificaciones() {
        List<CalificacionEntity> calificaciones = persistence.findAll();
        return calificaciones;
    }

    /**
     * Consulta una calificación.
     *
     * @param idCalificacion
     * @return La califiación si la encuentra.
     */
    public CalificacionEntity getCalificacion(Long idCalificacion) {
        CalificacionEntity calificacion = persistence.find(idCalificacion);
        return calificacion;
    }

    /**
     * Elimina la calificación con el id pasado por parámetro.
     *
     * @param idCalificacion
     */
    public void deleteCalificacion(Long idCalificacion) {
        persistence.delete(idCalificacion);
    }

    
    /**
     * Agrega las relaciones correspondientes a la calificación.
     *
     * @param idCalificacion
     * @param idTrayecto
     * @param idCalificado
     * @param idCalificador
     * @return Una nueva entidad con sus nuevas relaciones.
     * @throws BusinessLogicException Si alguno de los parámetros no existe o si
     * un usuario se califica a sí mismo.
     */
    public CalificacionEntity addRelacionCalificacion(Long idCalificacion, Long idTrayecto, Long idCalificado, Long idCalificador) throws BusinessLogicException {
        UsuarioEntity calificado = usuarioPersistence.find(idCalificado);
        UsuarioEntity calificador = usuarioPersistence.find(idCalificador);
        CalificacionEntity calificacion = persistence.find(idCalificacion);
        TrayectoEntity trayecto = trayectoPersistence.find(idTrayecto);

        if (calificado == null) {
            throw new BusinessLogicException("Usuario calificado: " + idCalificado + " no existe");
        }
        if (calificador == null) {
            throw new BusinessLogicException("Usuario calificador: " + idCalificador + " no existe");
        }
        if (calificado.equals(calificador)) {
            throw new BusinessLogicException("Usuario calificador y calificado son iguales");
        }
        if (trayecto == null) {
            throw new BusinessLogicException("Trayecto : " + idTrayecto + " no existe");
        }

        calificacion.setCalificado(calificado);
        calificacion.setCalificador(calificador);
        calificacion.setTrayecto(trayecto);

        return persistence.update(calificacion);

    }
    
    
    
    /**
     * Reemplaza la relación con el calificador.
     *
     * @param idCalificacion
     * @param usernameCalificador
     * @return Un calificacion con un nuevo calificado.
     * @throws BusinessLogicException
     */
    public CalificacionEntity replaceRelacionCalificador(Long idCalificacion, String usernameCalificador) throws BusinessLogicException {
        CalificacionEntity calificacion = persistence.find(idCalificacion);
        UsuarioEntity calificador = usuarioPersistence.findByUserName(usernameCalificador);
        if (calificador == null || calificacion == null || calificador.equals(calificacion.getCalificado())) {
            throw new BusinessLogicException("Relacion de calificación no valida");
        }
       
        calificacion.setCalificador(calificador);
        return persistence.update(calificacion);
    }
    
     /**
     * Reemplaza la relación con el calificado.
     *
     * @param idCalificacion
     * @param usernameCalificado
     * @return Una calificacion con un nuevo calificado.
     * @throws BusinessLogicException
     */
    public CalificacionEntity replaceRelacionCalificado(Long idCalificacion, String usernameCalificado) throws BusinessLogicException {
        CalificacionEntity calificacion = persistence.find(idCalificacion);
        UsuarioEntity calificado = usuarioPersistence.findByUserName(usernameCalificado);
        if (calificado == null || calificacion == null || calificado.equals(calificacion.getCalificador()) ) {
            throw new BusinessLogicException("Relacion de calificación no valida");
        }
        calificacion.setCalificado(calificado);
        calificado.addCalificacionRecibida(calificacion);
        usuarioPersistence.update(calificado);
        return persistence.update(calificacion);
    }
    
    
    
    
    /**
     * Reemplaza la relación con el trayecto.
     *
     * @param idCalificacion
     * @param idTrayecto
     * @return Una calificacion con un nuevo trayecto.
     * @throws BusinessLogicException
     */
    public CalificacionEntity replaceRelacionTrayecto(Long idCalificacion, Long idTrayecto) throws BusinessLogicException {
        CalificacionEntity calificacion = persistence.find(idCalificacion);
        TrayectoEntity trayecto = trayectoPersistence.find(idTrayecto);
        if (trayecto == null || calificacion == null) {
            throw new BusinessLogicException("Relacion de calificación no valida");
        }
        calificacion.setTrayecto(trayecto);
        return persistence.update(calificacion);
    }
    
    

}
