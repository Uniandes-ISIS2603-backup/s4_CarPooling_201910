/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
   @ManyToOne(cascade = CascadeType.PERSIST)
   private UsuarioEntity dueño;
   @PodamExclude
   @OneToOne(mappedBy="alquilerArrendatario", cascade = CascadeType.PERSIST)
   private UsuarioEntity arrendatario;
   /*
   @PodamExclude
   @OneToOne(mappedBy="alquilerInfo")
   private VehiculoEntity vehiculoAlquilado;
   */
   
   @PodamExclude
   @OneToOne(cascade = CascadeType.PERSIST)
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
     * @return the dueño
     */
    public UsuarioEntity getDueño() {
        return dueño;
    }

    /**
     * @param dueño the dueño to set
     */
    public void setDueño(UsuarioEntity dueño) {
        this.dueño = dueño;
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
}
