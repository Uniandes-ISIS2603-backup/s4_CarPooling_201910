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
public class CiudadLogic {

    @Inject
    private CiudadPersistence persistence;

    /**
     * Crea una ciudad.
     *
     * @param ciudad
     * @return CiudadEntity creada.
     * @throws BusinessLogicException Si existe una ciudad con el mismo id.
     */
    public CiudadEntity create(CiudadEntity ciudad) throws BusinessLogicException {
        if (persistence.findByName(ciudad.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe la ciudad de nombre: " + ciudad.getNombre() + " ya existe");
        }
        ciudad = persistence.create(ciudad);

        return ciudad;
    }

    /**
     * Consultar una ciudad.
     *
     * @param id
     * @return La ciudad, si existe.
     */
    public CiudadEntity get(Long id) {
        CiudadEntity cEntity = persistence.find(id);
        return cEntity;
    }

    /**
     * Consulta todas la ciudades.
     *
     * @return Una lista con todas las ciudades.
     */
    public List<CiudadEntity> get() {
        List<CiudadEntity> ciudad = persistence.findAll();
        return ciudad;
    }
}
