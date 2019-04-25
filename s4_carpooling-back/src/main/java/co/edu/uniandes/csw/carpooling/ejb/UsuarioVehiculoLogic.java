/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.carpooling.persistence.VehiculoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Editorial y Book.
 *
 * @author Isabela
 */
@Stateless
public class UsuarioVehiculoLogic {
    
    @Inject
    private VehiculoPersistence vehiculoPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Agregar un book a la editorial
     *
     * @param booksId El id libro a guardar
     * @param editorialsId El id de la editorial en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public VehiculoEntity addVehiculo(String username, Long vehiculosId) {
        VehiculoEntity vehiculoEntity = vehiculoPersistence.find(Long.valueOf("100"));
        UsuarioEntity usuarioEntity = usuarioPersistence.findByUserName(username);
        usuarioEntity.addVehiculo(vehiculoEntity);
        return vehiculoEntity;
    }
}
