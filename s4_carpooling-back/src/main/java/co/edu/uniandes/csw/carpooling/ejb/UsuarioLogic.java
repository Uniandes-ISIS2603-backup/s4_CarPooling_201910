/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Isabela Sarmiento
 */
@Stateless
public class UsuarioLogic {
    
    @Inject
    private UsuarioPersistence persistence;
    
    public UsuarioEntity create(UsuarioEntity usuario) throws BusinessLogicException
    {
        if(persistence.findByUserName(usuario.getUsername())!= null )
        {
            throw new BusinessLogicException("Ya existe una Usuario con el nombre de usuario \"" + usuario.getUsername() + "\"");

        }
        usuario = persistence.create(usuario); //llamar a la persistencia para que cree la editorial
        return usuario;
    }
    
}
