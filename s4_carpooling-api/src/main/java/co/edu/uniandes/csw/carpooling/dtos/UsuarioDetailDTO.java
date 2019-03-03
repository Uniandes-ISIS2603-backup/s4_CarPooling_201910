/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.dtos;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class UsuarioDetailDTO extends UsuarioDTO {
    
    private List<NotificacionDTO> notificacionesEnviadas;
    private List<NotificacionDTO> notificacionesRecibidas;
    private List<AlquilerDTO> alquilerDueño;
    private AlquilerDTO alquilerArrendatario;
    private List<VehiculoDTO> vehiculos;
    private List<CalificacionDTO> calificaciones;
    
    public UsuarioDetailDTO(){}
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param bookEntity La entidad de la cual se construye el DTO
     */
    public UsuarioDetailDTO(UsuarioEntity usuarioEntity) {
        super(usuarioEntity);
        /*
        if (usuarioEntity.getNotificacionesEnviadas() != null) {
            notificacionesEnviadas = new ArrayList<>();
            for (NotificacionEntity entityNotificaion : usuarioEntity.getNotificacionesEnviadas()) {
                notificacionesEnviadas.add(new NotificacionDTO(entityNotificaion));
            }
        }
        if (usuarioEntity.getNotificacionesRecibidas() != null) {
            notificacionesRecibidas = new ArrayList<>();
            for (NotificacionEntity entityNotificaion : usuarioEntity.getNotificacionesRecibidas()) {
                notificacionesRecibidas.add(new NotificacionDTO(entityNotificaion));
            }
        }
        if (usuarioEntity.getAlquilerDueño() != null) {
            alquilerDueño = new ArrayList<>();
            for (AlquilerEntity entityAlquiler : usuarioEntity.getAlquilerDueño()) {
                alquilerDueño.add(new AlquilerDTO(entityAlquiler));
            }
        }
        if (usuarioEntity.getAlquilerArrendatario() != null) {
            alquilerArrendatario = new AlquilerDTO(usuarioEntity.getAlquilerArrendatario());
        }
        if (usuarioEntity.getVehiculos() != null) {
            vehiculos = new ArrayList<>();
            for (VehiculoEntity entityVehiculo : usuarioEntity.getVehiculos()) {
                vehiculos.add(new VehiculoDTO(entityVehiculo));
            }
        }
        if (usuarioEntity.getCalificaciones()!= null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : usuarioEntity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }*/
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = super.toEntity();
        if (notificacionesEnviadas != null) {
            /*
            List<NotificacionEntity> notificacionEntity = new ArrayList<>();
            for (NotificacionDTO dtoNot : getNotificacionesEnviadas()) {
                notificacionEntity.add(dtoNot.toEntity());
            }
            usuarioEntity.setNotificacionesEnviadas(notificacionEntity);
        }
        if (notificacionesRecibidas != null) {
            List<NotificacionEntity> notificacionEntity = new ArrayList<>();
            for (NotificacionDTO dtoNot : getNotificacionesRecibidas()) {
                notificacionEntity.add(dtoNot.toEntity());
            }
            usuarioEntity.setNotificacionesRecibidas(notificacionEntity);
        }*/
        if (alquilerDueño != null) {
            List<AlquilerEntity> alquilerDueñoEntity = new ArrayList<>();
            for (AlquilerDTO dtoNot : getAlquilerDueño()) {
                alquilerDueñoEntity.add(dtoNot.toEntity());
            }
            usuarioEntity.setAlquilerDueño(alquilerDueñoEntity);
        }
        if (alquilerArrendatario != null) {
            usuarioEntity.setAlquilerArrendatario(alquilerArrendatario.toEntity());
        }/**
        if (vehiculos != null) {
            List<VehiculoEntity> vehiculosEntity = new ArrayList<>();
            for (VehiculoDTO dto : getVehiculos()) {
                vehiculosEntity.add(dto.toEntity());
            }
            usuarioEntity.setVehiculos(vehiculosEntity);
        }
        if (calificaciones != null) {
            List<CalificacionEntity> calificacionEntity = new ArrayList<>();
            for (CalificacionDTO dto : getCalificaciones()) {
                calificacionEntity.add(dto.toEntity());
            }
            usuarioEntity.setCalificaciones(calificacionEntity);*/
        }
        
        return usuarioEntity;
    }
        
    
        
    public List<AlquilerDTO> getAlquilerDueño()
    {
       return alquilerDueño;
    }

    /**
     * @param alquilerDueño the alquilerDueño to set
     */
    public void setAlquilerDueño(List<AlquilerDTO> alquilerDueño) {
        this.alquilerDueño = alquilerDueño;
    }

    /**
     * @return the alquilerArrendatario
     */
    public AlquilerDTO getAlquilerArrendatario() {
        return alquilerArrendatario;
    }

    /**
     * @param alquilerArrendatario the alquilerArrendatario to set
     */
    public void setAlquilerArrendatario(AlquilerDTO alquilerArrendatario) {
        this.alquilerArrendatario = alquilerArrendatario;
    }

    /**
     * @return the notificacionesEnviadas
     */
    public List<NotificacionDTO> getNotificacionesEnviadas() {
        return notificacionesEnviadas;
    }

    /**
     * @param notificacionesEnviadas the notificacionesEnviadas to set
     */
    public void setNotificacionesEnviadas(List<NotificacionDTO> notificacionesEnviadas) {
        this.notificacionesEnviadas = notificacionesEnviadas;
    }

    /**
     * @return the notificacionesRecibidas
     */
    public List<NotificacionDTO> getNotificacionesRecibidas() {
        return notificacionesRecibidas;
    }

    /**
     * @param notificacionesRecibidas the notificacionesRecibidas to set
     */
    public void setNotificacionesRecibidas(List<NotificacionDTO> notificacionesRecibidas) {
        this.notificacionesRecibidas = notificacionesRecibidas;
    }

    /**
     * @return the vehiculos
     */
    public List<VehiculoDTO> getVehiculos() {
        return vehiculos;
    }

    /**
     * @param vehiculos the vehiculos to set
     */
    public void setVehiculos(List<VehiculoDTO> vehiculos) {
        this.vehiculos = vehiculos;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the vehiculos
     
    public List<VehiculoDTO> getVehiculos() {
        return vehiculos;
    }

    /**
     * @param vehiculos the vehiculos to set
     *
    public void setVehiculos(List<VehiculoDTO> vehiculos) {
        this.vehiculos = vehiculos;
    }

    /**
     * @return the calificaciones
     
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     *
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }
    */
}
