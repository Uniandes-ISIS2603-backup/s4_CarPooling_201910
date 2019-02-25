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
 * @author estudiante
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    private String apellido;
    private Integer documento;
    private Integer celular;
    private String username;
    private String password;
    private String correo;
    /*
   @PodamExclude
   @OneToMany
   private List<VehiculoEntity> vehiculos = new ArrayList<VehiculoEntity>();
    
   @PodamExclude
   @OneToMany(mappedBy="dueño")
   private List<AlquilerEntity> alquilerDueño = new ArrayList<AlquilerEntity>();
    
   @PodamExclude
   @OneToOne
   private AlquilerEntity alquilerArrendatario;
    
   @PodamExclude
   @OneToMany
   private List<NotificacionEntity> notificacionEnviada = new ArrayList<NotificacionEntity>();
    
   @PodamExclude
   @OneToMany
   private List<NotificacionEntity> notificacionRecibida = new ArrayList<NotificacionEntity>();
    
   @PodamExclude
   @OneToMany
   private List<CalificacionEntity> calificaciones =  new ArrayList<CalificacionEntity>();
    
   @PodamExclude
   @OneToOne
   private PagoEntity pagoARecibir;
    
   @PodamExclude
   @OneToOne
   private PagoEntity pagoAHacer;
    
    
   */
   @PodamExclude
   @OneToOne
   private TrayectoEntity traycetoActualConductor;
   
   @PodamExclude
   @OneToOne(mappedBy="conductor")
   private TrayectoEntity traycetoActualPasajero;
   
   
   
    
    public UsuarioEntity(){
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the documento
     */
    public Integer getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(Integer documento) {
        this.documento = documento;
    }
    
    /**
     * @return the celular
     */
    public Integer getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
   
}
