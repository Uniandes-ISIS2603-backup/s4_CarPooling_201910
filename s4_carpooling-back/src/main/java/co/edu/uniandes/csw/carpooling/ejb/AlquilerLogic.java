/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.AlquilerPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author df.penap
 */
@Stateless
public class AlquilerLogic {
    @Inject
    private AlquilerPersistence persistence;
    
    public AlquilerEntity createAlquiler(AlquilerEntity alquiler) throws BusinessLogicException
    {
        if(alquiler.getDueño()==null)
        {
            throw new BusinessLogicException("No existe un dueño asociado al alquiler");
        }
        if(alquiler.getSeguro()==null)
        {
            throw new BusinessLogicException("No existe un seguro asociado al alquiler");
        }
        if(alquiler.getDueño().equals(alquiler.getArrendatario()))
        {
            throw new BusinessLogicException("El dueño y el arrendatario deben ser distintos");
        }
        /*
        if(persistence.findByVehiculoAlquiladoPlaca(alquiler.getVehiculoAlquilado().getPlaca())=!null)
        {
        throw new BusinessLogicException("Ya existe un alquiler con el vehiculo asociado /*+alquiler.getVehiculoAlquilado().getMatricula()+"")
        }
        */
        alquiler = persistence.create(alquiler);
        return alquiler;
    }
}
