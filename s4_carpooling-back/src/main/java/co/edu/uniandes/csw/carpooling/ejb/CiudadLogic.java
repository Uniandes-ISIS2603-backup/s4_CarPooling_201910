/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.CiudadEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.CiudadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author nc.hernandezm
 */
@Stateless
public class CiudadLogic 
{
    @Inject
    private CiudadPersistence persistence;
    
   
    public CiudadEntity create(CiudadEntity ciudad) throws BusinessLogicException
    {
        if (persistence.findByName(ciudad.getNombre()) != null)
        {
            throw new BusinessLogicException ("Ya existe la ciudad de nombre: " + ciudad.getNombre()+ " ya existe");
        }
        ciudad = persistence.create(ciudad);
       
        return ciudad;
    }
    
     public CiudadEntity get (Long id)
    {
        CiudadEntity cEntity = persistence.find(id);
        return cEntity;
    }
     
     public List<CiudadEntity> get ()   
     {
         List<CiudadEntity> ciudad = persistence.findAll();
        return ciudad;
     }
     
     
}

