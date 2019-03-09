/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class UsuarioDTO implements Serializable {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer documento;
    private Integer celular;
    private String username;
    private String password;
    private String correo;
    private PagoDTO pagoAHacer;
    private PagoDTO pagoARecibir;

    
    public UsuarioDTO(){}
    
    public UsuarioDTO (UsuarioEntity entity){
        if (entity != null) {
            this.username = entity.getUsername();
            this.nombre = entity.getNombre();
            this.apellido = entity.getApellido();
            this.documento = entity.getDocumento();
            this.celular = entity.getCelular();
            this.password = entity.getPassword();
            this.correo = entity.getCorreo();
            this.id = entity.getId();
            if (entity.getPagoAHacer() != null) {
                this.pagoAHacer = new PagoDTO(entity.getPagoAHacer());
            } else {
                this.pagoAHacer = null;
            }
            if (entity.getPagoARecibir() != null) {
                this.pagoARecibir = new PagoDTO(entity.getPagoARecibir());
            } else {
                this.pagoARecibir = null;
            }
            
        }
    }
    
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del libro asociado.
     */
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(this.id);
        usuarioEntity.setNombre(this.nombre);
        usuarioEntity.setApellido(this.apellido);
        usuarioEntity.setDocumento(this.documento);
        usuarioEntity.setCelular(celular);
        usuarioEntity.setUsername(username);
        usuarioEntity.setPassword(password);
        usuarioEntity.setCorreo(correo);
        if (this.pagoAHacer != null) {
            usuarioEntity.setPagoAHacer(this.pagoAHacer.toEntity());
        }
        if (this.pagoARecibir != null) {
            usuarioEntity.setPagoARecibir(this.pagoARecibir.toEntity());
        }
        
        return usuarioEntity;
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
     * @return the pagoAHacer
     */
    public PagoDTO getPagoAHacer() {
        return pagoAHacer;
    }

    /**
     * @param pagoAHacer the pagoAHacer to set
     */
    public void setPagoAHacer(PagoDTO pagoAHacer) {
        this.pagoAHacer = pagoAHacer;
    }

    /**
     * @return the pagoARecibir
     */
    public PagoDTO getPagoARecibir() {
        return pagoARecibir;
    }

    /**
     * @param pagoARecibir the pagoARecibir to set
     */
    public void setPagoARecibir(PagoDTO pagoARecibir) {
        this.pagoARecibir = pagoARecibir;
    }

}
