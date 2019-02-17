/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author df.penap
 */
@Entity
public class AlquilerEntity extends BaseEntity implements Serializable  {
   private String nombre;
    /*
   @PodamExclude
   @ManyToOne
   private usuarioEntity dueño;
   @PodamExclude
   @OneToOne(mappedBy="alquilerArrendatario")
   private usuarioEntity arrendatario;
   @PodamExclude
   @OneToOne(mappedBy="alquilerInfo")
   private vehiculoEntity vehiculoAlquilado;
   */
   
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
}
