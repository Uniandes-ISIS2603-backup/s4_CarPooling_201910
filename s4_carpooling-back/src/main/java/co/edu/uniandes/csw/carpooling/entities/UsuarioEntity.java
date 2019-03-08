/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.eclipse.persistence.annotations.CascadeOnDelete;
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
 
   @PodamExclude
   @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
   private List<VehiculoEntity> vehiculos = new ArrayList<VehiculoEntity>();
    
   @PodamExclude
   @CascadeOnDelete
   @OneToMany(mappedBy="dueño", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<AlquilerEntity> alquilerDueño = new ArrayList<AlquilerEntity>();
   
   @PodamExclude
   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   private AlquilerEntity alquilerArrendatario;
    
   @PodamExclude
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   private List<NotificacionEntity> notificacionEnviada = new ArrayList<NotificacionEntity>();
    
   @PodamExclude
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   private List<NotificacionEntity> notificacionRecibida = new ArrayList<NotificacionEntity>();
    
   @PodamExclude
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   private List<CalificacionEntity> calificaciones =  new ArrayList<CalificacionEntity>();
    
   @PodamExclude
   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   private PagoEntity pagoARecibir;
    
   @PodamExclude
   @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
   private PagoEntity pagoAHacer;
  
   @PodamExclude
   @OneToOne(mappedBy="conductor",cascade = CascadeType.ALL, orphanRemoval = true)
   private TrayectoEntity trayecetoActualConductor;
   
   @PodamExclude
   @ManyToOne(cascade = CascadeType.ALL)
   private TrayectoEntity trayectoActualPasajero;

    
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
    
    /**
     * @return the alquilerDueño
     */
    public List<AlquilerEntity> getAlquilerDueño() {
        return alquilerDueño;
    }

    /**
     * @param alquilerDueño the alquilerDueño to set
     */
    public void setAlquilerDueño(List<AlquilerEntity> alquilerDueño) {
        this.alquilerDueño = alquilerDueño;
    }

    /**
     * @return the alquilerArrendatario
     */
    public AlquilerEntity getAlquilerArrendatario() {
        return alquilerArrendatario;
    }

    /**
     * @param alquilerArrendatario the alquilerArrendatario to set
     */
    public void setAlquilerArrendatario(AlquilerEntity alquilerArrendatario) {
        this.alquilerArrendatario = alquilerArrendatario;
    }


    /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the pagoARecibir
     */
    public PagoEntity getPagoARecibir() {
        return pagoARecibir;
    }

    /**
     * @param pagoARecibir the pagoARecibir to set
     */
    public void setPagoARecibir(PagoEntity pagoARecibir) {
        this.pagoARecibir = pagoARecibir;
    }

    /**
     * @return the pagoAHacer
     */
    public PagoEntity getPagoAHacer() {
        return pagoAHacer;
    }

    /**
     * @param pagoAHacer the pagoAHacer to set
     */
    public void setPagoAHacer(PagoEntity pagoAHacer) {
        this.pagoAHacer = pagoAHacer;
    }

    /**
     * @return the notificacionesEnviadas
     */
    public List<NotificacionEntity> getNotificacionesEnviadas() {
        return getNotificacionEnviada();
    }

    /**
     * @param notificacionesEnviadas the notificacionesEnviadas to set
     */
    public void setNotificacionesEnviadas(List<NotificacionEntity> notificacionesEnviadas) {
        this.setNotificacionEnviada(notificacionesEnviadas);
    }

    /**
     * @return the notificacionesRecibidas
     */
    public List<NotificacionEntity> getNotificacionesRecibidas() {
        return getNotificacionRecibida();
    }

    /**
     * @param notificacionesRecibidas the notificacionesRecibidas to set
     */
    public void setNotificacionesRecibidas(List<NotificacionEntity> notificacionesRecibidas) {
        this.setNotificacionRecibida(notificacionesRecibidas);
    }

    /**
     * @return the trayecetoActualConductor
     */
    public TrayectoEntity getTrayecetoActualConductor() {
        return trayecetoActualConductor;
    }

    /**
     * @param trayecetoActualConductor the trayecetoActualConductor to set
     */
    public void setTrayecetoActualConductor(TrayectoEntity trayecetoActualConductor) {
        this.trayecetoActualConductor = trayecetoActualConductor;
    }

    /**
     * @return the trayectoActualPasajero
     */
    public TrayectoEntity getTrayectoActualPasajero() {
        return trayectoActualPasajero;
    }

    /**
     * @param trayectoActualPasajero the trayectoActualPasajero to set
     */
    public void setTrayectoActualPasajero(TrayectoEntity trayectoActualPasajero) {
        this.trayectoActualPasajero = trayectoActualPasajero;
    }

    /**
     * @return the vehiculos
     */
    public List<VehiculoEntity> getVehiculos() {
        return vehiculos;
    }

    /**
     * @param vehiculos the vehiculos to set
     */
    public void setVehiculos(List<VehiculoEntity> vehiculos) {
        this.vehiculos = vehiculos;
    }

    /**
     * @return the notificacionEnviada
     */
    public List<NotificacionEntity> getNotificacionEnviada() {
        return notificacionEnviada;
    }

    /**
     * @param notificacionEnviada the notificacionEnviada to set
     */
    public void setNotificacionEnviada(List<NotificacionEntity> notificacionEnviada) {
        this.notificacionEnviada = notificacionEnviada;
    }

    /**
     * @return the notificacionRecibida
     */
    public List<NotificacionEntity> getNotificacionRecibida() {
        return notificacionRecibida;
    }

    /**
     * @param notificacionRecibida the notificacionRecibida to set
     */
    public void setNotificacionRecibida(List<NotificacionEntity> notificacionRecibida) {
        this.notificacionRecibida = notificacionRecibida;
    }

   
    
}
