/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.AlquilerPersistence;
import co.edu.uniandes.csw.carpooling.persistence.SeguroPersistence;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
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
    /*
    @Inject 
    private VehiculoPersistence vehiculoPersistence;
    */
    
    public AlquilerEntity createAlquiler(AlquilerEntity alquiler) 
    {       
        alquiler = persistence.create(alquiler);
        return alquiler;
    }
    public List<AlquilerEntity> getAlquiler()
    {
        List<AlquilerEntity> alquiler = persistence.findAll();
        return alquiler;
    }
    public AlquilerEntity getAlquiler(Long idAlquiler)
    {
        AlquilerEntity alquiler = persistence.find(idAlquiler);
        return alquiler;
    }
    public AlquilerEntity update(Long idAlquiler, AlquilerEntity newAlquiler) 
    {
        AlquilerEntity alquiler = getAlquiler(idAlquiler);
        
        alquiler.setNombre(newAlquiler.getNombre());
        
        persistence.update(alquiler);
        
        return alquiler;
    }
    public void deleteAlquiler(Long idAlquiler)
    {
        persistence.delete(idAlquiler);
    }
    public AlquilerEntity addRelacionAlquiler(Long idAlquiler,Long idDueno, Long idArrendatario, Long idSeguro) throws BusinessLogicException
    {
        UsuarioEntity dueno = usuarioPersistence.find(idDueno);
        UsuarioEntity arrendatario = usuarioPersistence.find(idArrendatario);
        SeguroEntity seguro = seguroPersistence.find(idSeguro);
        AlquilerEntity alquiler = persistence.find(idAlquiler);
        if(dueno==null)
        {
            throw new BusinessLogicException("Usuario dueño: "+ idDueno +" no existe");
        }
        if(dueno.equals(arrendatario)){
            throw new BusinessLogicException("Usuario dueño y arrendatario son iguales");
        }
        if(seguro==null)
        {
            throw new BusinessLogicException("Seguro: "+ idSeguro +" no existe");
        }
        if(alquiler==null)
        {
            throw new BusinessLogicException("Alquiler: "+ idAlquiler +" no existe");
        }
        alquiler.setArrendatario(arrendatario);
        alquiler.setDueño(dueno);
        alquiler.setSeguro(seguro);
        return persistence.update(alquiler);
            
    }
    public AlquilerEntity replaceRelacionArrendatario(Long idAlquiler, Long idArrendatario) throws BusinessLogicException
    {
        AlquilerEntity alquiler = persistence.find(idAlquiler);
        UsuarioEntity arrendatario = usuarioPersistence.find(idArrendatario);
        if(arrendatario==null||alquiler==null||arrendatario.equals(alquiler.getDueño()))
        {
            throw new BusinessLogicException("Relacion de alquiler no valida");
        }
        alquiler.setArrendatario(arrendatario);
        return persistence.update(alquiler);
    }
    public AlquilerEntity replaceRelacionSeguro(Long idAlquiler, Long idSeguro) throws BusinessLogicException
    {
        AlquilerEntity alquiler = persistence.find(idAlquiler);
        SeguroEntity seguro = seguroPersistence.find(idSeguro);
        if(seguro==null||alquiler==null)
        {
            throw new BusinessLogicException("Relacion de alquiler no valida");
        }
        alquiler.setSeguro(seguro);
        return persistence.update(alquiler);
    }
    

    
}
