/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.PeajePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Isabela Sarmiento
 */
@Stateless
public class PeajeLogic {

    private static final Logger LOGGER = Logger.getLogger(PeajeLogic.class.getName());

    @Inject
    private PeajePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un peaje en la persistencia.
     *
     * @param peajeEntity La entidad que representa la editorial a persistir.
     * @return La entiddad del peaje luego de persistirla.
     * @throws BusinessLogicException Si el peaje a persistir ya existe o tiene
     * algpun dato inválido.
     */
    public PeajeEntity createPeaje(PeajeEntity peajeEntity) throws BusinessLogicException {

        if (persistence.findByName(peajeEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un Peaje con el nombre \"" + peajeEntity.getNombre() + "\"");
        }
        if (peajeEntity.getCosto() == null) {
            throw new BusinessLogicException("Costo de peaje invalido: \"" + peajeEntity.getCosto() + "\"");
        }
        if (peajeEntity.getLatitud() == null) {
            throw new BusinessLogicException("Latitud de peaje invalido: \"" + peajeEntity.getLatitud() + "\"");
        }
        if (peajeEntity.getLongitud() == null) {
            throw new BusinessLogicException("Longitud de peaje invalido: \"" + peajeEntity.getLongitud() + "\"");
        }
        if (peajeEntity.getNombre() == null) {
            throw new BusinessLogicException("Nombre de peaje invalido: \"" + peajeEntity.getNombre() + "\"");
        }
        peajeEntity = persistence.create(peajeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de peaje");
        return peajeEntity;
    }

    /**
     * Obtiene todos los peajes.
     *
     * @return Una lista con los peajes.
     */
    public List<PeajeEntity> get() {

        List<PeajeEntity> Peaje = persistence.findAll();

        return Peaje;
    }

    /**
     * Devuelve el peaje con el id pasado por parámetro.
     *
     * @param peajeId
     * @return El peaje, si lo encuentra.
     */
    public PeajeEntity get(Long peajeId) {

        PeajeEntity PeajeEntity = persistence.find(peajeId);

        return PeajeEntity;
    }

    /**
     * Actualiza un peaje existente.
     *
     * @param PeajeId
     * @param peajeEntity
     * @return El peaje con los nuevos datos.
     * @throws BusinessLogicException
     */
    public PeajeEntity update(Long PeajeId, PeajeEntity peajeEntity) throws BusinessLogicException {

        if (persistence.findByName(peajeEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un Peaje con el nombre \"" + peajeEntity.getNombre() + "\"");
        }
        if (peajeEntity.getCosto() == null) {
            throw new BusinessLogicException("Costo de peaje invalido: \"" + peajeEntity.getCosto() + "\"");
        }
        if (peajeEntity.getLatitud() == null) {
            throw new BusinessLogicException("Latitud de peaje invalido: \"" + peajeEntity.getLatitud() + "\"");
        }
        if (peajeEntity.getLongitud() == null) {
            throw new BusinessLogicException("Longitud de peaje invalido: \"" + peajeEntity.getLongitud() + "\"");
        }
        if (peajeEntity.getNombre() == null) {
            throw new BusinessLogicException("Nombre de peaje invalido: \"" + peajeEntity.getNombre() + "\"");
        }
        PeajeEntity newEntity = persistence.update(peajeEntity);

        return newEntity;
    }

    /**
     * Elimina el peaje con id pasado por parámetro.
     *
     * @param PeajeId
     */
    public void delete(Long PeajeId) {

        persistence.delete(PeajeId);

    }
}
