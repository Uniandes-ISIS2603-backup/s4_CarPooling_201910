/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.SeguroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author df.penap
 */
@Stateless
public class SeguroLogic {
    @Inject
    private SeguroPersistence persistence;
    
    public SeguroEntity create(SeguroEntity seguro) throws BusinessLogicException
    {
        String find = seguro.getTipo();
        if(persistence.findByTipo(find)!=null)
        {
            throw new BusinessLogicException("Ya existe un seguro con el tipo: "+seguro.getTipo());
        }
        seguro = persistence.create(seguro);
        return seguro;
    }
    public List<SeguroEntity> get() {
        
        List<SeguroEntity> seguro = persistence.findAll();
        
        return seguro;
    }
    public SeguroEntity get(Long booksId) {
        
        SeguroEntity seguroEntity = persistence.find(booksId);
        
        return seguroEntity;
    }
    public SeguroEntity update(Long seguroId, SeguroEntity seguroEntity) throws BusinessLogicException {
        
        if (seguroEntity.getTipo() == null || seguroEntity.getTipo().isEmpty()) {
            throw new BusinessLogicException("Debe existir un tipo de seguro valido");
        }
        if((persistence.findByTipo(seguroEntity.getTipo())!=null))
        {
            throw new BusinessLogicException("Ya existe un seguro con el tipo: "+seguroEntity.getTipo());
        }
        SeguroEntity newEntity = persistence.update(seguroEntity);
        
        return newEntity;
    }
    public void delete(Long seguroId) {
        
        persistence.delete(seguroId);
        
    }
}
