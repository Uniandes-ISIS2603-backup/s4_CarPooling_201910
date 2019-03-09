/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author df.penap
 */
public class AlquilerDTO implements Serializable{
    private Long id;
    private String nombre;
    private UsuarioDTO arrendatario;
    private UsuarioDTO dueño;
    private VehiculoDTO vehiculoAlquilado;
    private SeguroDTO seguro;
    public AlquilerDTO()
    {
        
    }
    public AlquilerDTO(AlquilerEntity entity)
    {
        if(entity!=null)
        {
            nombre=entity.getNombre();
            arrendatario = new UsuarioDTO(entity.getArrendatario());
            dueño = new UsuarioDTO(entity.getDueño());
            seguro = new SeguroDTO(entity.getSeguro());
            id= entity.getId();
            /*
            vehiculoAlquilado = new VehiculoDTO(entity.getVehiculoAlquilado());
            */
            
          
        }
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the seguro
     */
    public SeguroDTO getSeguro() {
        return seguro;
    }

    /**
     * @param seguro the seguro to set
     */
    public void setSeguro(SeguroDTO seguro) {
        this.seguro = seguro;
    }

    /**
     * @return the arrendatario
     */
    public UsuarioDTO getArrendatario() {
        return arrendatario;
    }

    /**
     * @param arrendatario the arrendatario to set
     */
    public void setArrendatario(UsuarioDTO arrendatario) {
        this.arrendatario = arrendatario;
    }

    /**
     * @return the dueño
     */
    public UsuarioDTO getDueño() {
        return dueño;
    }

    /**
     * @param dueño the dueño to set
     */
    public void setDueño(UsuarioDTO dueño) {
        this.dueño = dueño;
    }

    /**
     * @return the vehiculoAlquilado
     */
    public VehiculoDTO getVehiculoAlquilado() {
        return vehiculoAlquilado;
    }

    /**
     * @param vehiculoAlquilado the vehiculoAlquilado to set
     */
    public void setVehiculoAlquilado(VehiculoDTO vehiculoAlquilado) {
        this.vehiculoAlquilado = vehiculoAlquilado;
    }
    public AlquilerEntity toEntity(){
        AlquilerEntity entity = new AlquilerEntity();
        entity.setNombre(nombre);
        entity.setId(id);
        if(arrendatario!=null){
        entity.setArrendatario(arrendatario.toEntity());}
        if(dueño!=null){
        entity.setDueño(dueño.toEntity());}
        /*
        entity.setArrendatario(arrendatario.toEntity);
        entity.setDueño(dueño);
        */
        if(seguro!=null){
        entity.setSeguro(seguro.toEntity());}
         
        return entity;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
