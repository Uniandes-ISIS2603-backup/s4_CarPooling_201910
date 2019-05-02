/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.AlquilerPersistence;
import co.edu.uniandes.csw.carpooling.persistence.SeguroPersistence;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.carpooling.persistence.VehiculoPersistence;
import java.util.List;
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
    @Inject
    private UsuarioPersistence usuarioPersistence;
    @Inject
    private SeguroPersistence seguroPersistence;

    
    @Inject 
    private VehiculoPersistence vehiculoPersistence;
     

    /**
     * Crea un alquiler.
     *
     * @param alquiler
     * @return alquiler creado
     */
    public AlquilerEntity createAlquiler(AlquilerEntity alquiler) {
        alquiler = persistence.create(alquiler);
        return alquiler;
    }

    /**
     * Consulta todos los alquileres.
     *
     * @return lista de alquileres.
     */
    public List<AlquilerEntity> getAlquiler() {
        List<AlquilerEntity> alquiler = persistence.findAll();
        return alquiler;
    }

    /**
     * Consultar un alquiler.
     *
     * @param idAlquiler
     * @return El alquiler, si existe.
     */
    public AlquilerEntity getAlquiler(Long idAlquiler) {
        AlquilerEntity alquiler = persistence.find(idAlquiler);
        return alquiler;
    }

    /**
     * Actualiza el alquiler.
     *
     * @param idAlquiler
     * @param newAlquiler
     * @return El nuevo alquiler.
     */
    public AlquilerEntity update(Long idAlquiler, AlquilerEntity newAlquiler) {
        AlquilerEntity alquiler = getAlquiler(idAlquiler);

        alquiler.setNombre(newAlquiler.getNombre());

        persistence.update(alquiler);

        return alquiler;
    }

    /**
     * Borra el alquiler con el id pasado por parámetro.
     *
     * @param idAlquiler
     */
    public void deleteAlquiler(Long idAlquiler) {
        persistence.delete(idAlquiler);
    }

    /**
     * Agrega las relaciones correspondientes.
     *
     * @param idAlquiler
     * @param idDueno
     * @param idArrendatario
     * @param idSeguro
     * @return La entidad con las relaciones.
     * @throws BusinessLogicException
     */
    public AlquilerEntity addRelacionAlquiler(Long idAlquiler, Long idDueno, Long idArrendatario, Long idSeguro, Long idVehiculo) throws BusinessLogicException {
        
        UsuarioEntity dueno = usuarioPersistence.find(idDueno);
        UsuarioEntity arrendatario = usuarioPersistence.find(idArrendatario);
        SeguroEntity seguro = seguroPersistence.find(idSeguro);
        AlquilerEntity alquiler = persistence.find(idAlquiler);
        VehiculoEntity vehiculo = vehiculoPersistence.find(idVehiculo);
        
        
        if (dueno == null) {
            throw new BusinessLogicException("Usuario dueño: " + idDueno + " no existe");
        }
        if (dueno.equals(arrendatario)) {
            throw new BusinessLogicException("Usuario dueño y arrendatario son iguales");
        }
        if (seguro == null) {
            throw new BusinessLogicException("Seguro: " + idSeguro + " no existe");
        }
        if (alquiler == null) {
            throw new BusinessLogicException("Alquiler: " + idAlquiler + " no existe");
        }
        if (vehiculo == null) {
            throw new BusinessLogicException("vehiculo: " + idVehiculo + " no existe");
        }
        
        arrendatario.setAlquilerArrendatario(alquiler);
        usuarioPersistence.update(arrendatario);
        alquiler.setVehiculoAlquilado(vehiculo);
        alquiler.setDuenio(dueno);
        alquiler.setSeguro(seguro);
        return persistence.update(alquiler);

    }

    /**
     * Reemplaza la realción con el arrendatario.
     *
     * @param idAlquiler
     * @param idArrendatario
     * @return Un alquiler con un nuevo arrendatario.
     * @throws BusinessLogicException
     */
    public AlquilerEntity replaceRelacionArrendatario(Long idAlquiler, Long idArrendatario) throws BusinessLogicException {
        AlquilerEntity alquiler = persistence.find(idAlquiler);
        UsuarioEntity arrendatario = usuarioPersistence.find(idArrendatario);
        if (arrendatario == null || alquiler == null || arrendatario.equals(alquiler.getDuenio())) {
            throw new BusinessLogicException("Relacion de alquiler no valida");
        }
        alquiler.setArrendatario(arrendatario);
        return persistence.update(alquiler);
    }

    /**
     * Reemplaza la relación con el seguro del alquiler.
     *
     * @param idAlquiler
     * @param idSeguro
     * @return Nueva entidad con nuevo seguro.
     * @throws BusinessLogicException
     */
    public AlquilerEntity replaceRelacionSeguro(Long idAlquiler, Long idSeguro) throws BusinessLogicException {
        AlquilerEntity alquiler = persistence.find(idAlquiler);
        SeguroEntity seguro = seguroPersistence.find(idSeguro);
        if (seguro == null || alquiler == null) {
            throw new BusinessLogicException("Relacion de alquiler no valida");
        }
        alquiler.setSeguro(seguro);
        return persistence.update(alquiler);
    }

}
