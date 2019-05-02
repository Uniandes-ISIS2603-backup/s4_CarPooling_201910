/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.AlquilerPersistence;
import co.edu.uniandes.csw.carpooling.persistence.VehiculoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author nc.hernandezm
 */
@Stateless
public class VehiculoLogic 
{
    @Inject
    private VehiculoPersistence persistence;
    @Inject
    private AlquilerPersistence alquilerPersistence;
    
    public VehiculoEntity createVehiculo (VehiculoEntity vehiculo) throws BusinessLogicException
    {
        vehiculo =  persistence.create(vehiculo);
        return vehiculo;
    }
    
    public List<VehiculoEntity> get()
    {
        List<VehiculoEntity> vehiculo = persistence.findAll();
        return vehiculo;
    }
    
    public VehiculoEntity get(Long id)
    {
        VehiculoEntity vehiculoEntity = persistence.find(id);
        return vehiculoEntity;
    }
    
    public VehiculoEntity updateVehiculo (Long id, VehiculoEntity ve)
    {
        VehiculoEntity vehiculo = get(id);
        vehiculo.setPlaca(ve.getPlaca());
        persistence.update(vehiculo);        
        return vehiculo;
    }
    public void delete(Long id) throws BusinessLogicException
    {
        AlquilerEntity alquiler = get(id).getAlquilerInfo();
        if (alquiler != null)
        {
            throw new BusinessLogicException ("No se puede borrar el vehículo con id: " + id + " porque tiene un alquiler asociado");
        }
        persistence.delete(id);
    }

    public VehiculoEntity addRelacionAlquiler(Long idVehiculo,Long idAlquiler) throws BusinessLogicException {
        AlquilerEntity alquiler = alquilerPersistence.find(idAlquiler);
        VehiculoEntity vehiculo = persistence.find(idVehiculo);
        if (alquiler == null) {
            throw new BusinessLogicException("Alquiler: " + idAlquiler + " no existe");
        }
        if (vehiculo == null) {
            throw new BusinessLogicException("vehiculo: " + idVehiculo + " no existe");
        }
        vehiculo.setAlquilerInfo(alquiler);
        alquiler.setVehiculoAlquilado(vehiculo);
        alquilerPersistence.update(alquiler);
        return persistence.update(vehiculo);   
    }
    
    
  
}
