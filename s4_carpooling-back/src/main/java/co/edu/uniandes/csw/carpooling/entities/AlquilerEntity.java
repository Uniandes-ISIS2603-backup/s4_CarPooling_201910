/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author df.penap
 */
@Entity
public class AlquilerEntity extends BaseEntity implements Serializable  {
   private String nombre;
    
   @PodamExclude
   @ManyToOne()
   private UsuarioEntity duenio;
   
   @PodamExclude
   @OneToOne(mappedBy="alquilerArrendatario", fetch=FetchType.LAZY)
   private UsuarioEntity arrendatario;
      
   
   @PodamExclude
   @OneToOne
   private VehiculoEntity vehiculoAlquilado;
   
   
   @PodamExclude
   @OneToOne
   private SeguroEntity seguro;
   
   
    public AlquilerEntity()
    {
        
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

    /**
     * @return the seguro
     */
    public SeguroEntity getSeguro() {
        return seguro;
    }

    /**
     * @param seguro the seguro to set
     */
    public void setSeguro(SeguroEntity seguro) {
        this.seguro = seguro;
    }

    /**
     * @return the duenio
     */
    public UsuarioEntity getDuenio() {
        return duenio;
    }

    /**
     * @param duenio the duenio to set
     */
    public void setDuenio(UsuarioEntity duenio) {
        this.duenio = duenio;
    }

    /**
     * @return the arrendatario
     */
    public UsuarioEntity getArrendatario() {
        return arrendatario;
    }

    /**
     * @param arrendatario the arrendatario to set
     */
    public void setArrendatario(UsuarioEntity arrendatario) {
        this.arrendatario = arrendatario;
    }

    /**
     * @return the vehiculoAlquilado
     */
    public VehiculoEntity getVehiculoAlquilado() {
        return vehiculoAlquilado;
    }

    /**
     * @param vehiculoAlquilado the vehiculoAlquilado to set
     */
    public void setVehiculoAlquilado(VehiculoEntity vehiculoAlquilado) {
        this.vehiculoAlquilado = vehiculoAlquilado;
    }

 
}
