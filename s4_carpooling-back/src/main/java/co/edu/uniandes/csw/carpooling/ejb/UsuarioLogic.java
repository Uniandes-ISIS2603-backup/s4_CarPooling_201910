/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.ejb;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Isabela Sarmiento
 */
@Stateless
public class UsuarioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    
    @Inject
    private UsuarioPersistence persistence;
    
    public UsuarioEntity create(UsuarioEntity usuario) throws BusinessLogicException
    {
        if(persistence.findByUserName(usuario.getUsername())!= null )
        {
            throw new BusinessLogicException("Ya existe una Usuario con el nombre de usuario \"" + usuario.getUsername() + "\"");

        }
        if(!validateUsername(usuario.getUsername()))
        {
            throw new BusinessLogicException("El nombre de usuario no es valido \"" + usuario.getUsername() + "\"");

        }
        usuario = persistence.create(usuario); //llamar a la persistencia para que cree la editorial
        return usuario;
    }
    
    /**
     * Devuelve todos los libros que hay en la base de datos.
     *
     * @return Lista de entidades de tipo libro.
     */
    public List<UsuarioEntity> getUsuarios(){
        //LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros");
        List<UsuarioEntity> usuarios = persistence.findAll();
        //LOGGER.log(Level.INFO, "Termina proceso de consultar todos los libros");
        return usuarios;
    }
    
    
    public UsuarioEntity getUsuario(String username) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con username = {0}", username);
        UsuarioEntity userEntity = persistence.findByUserName(username);
        if (userEntity == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el username = {0} no existe", username);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con username = {0}", username);
        return userEntity;
    }
    
    /**
     * Actualizar un usuario por usurname
     *
     * @param username
     * @param usuarioEntity
     * @return UsuarioEntity
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     */
    public UsuarioEntity updateUsuario(String username, UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con username = {0}", username);
        if (!validateUsername(usuarioEntity.getUsername())) {
            throw new BusinessLogicException("El username es inválido");
        } //revisar que exista
        UsuarioEntity newEntity = persistence.update(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuerio con id = {0}", usuarioEntity.getId());
        return newEntity;
    }
    
    /**
     * Eliminar un usuario por ID
     *
     * @param username El username del usuario libro a eliminar
     * @throws BusinessLogicException si el libro tiene autores asociados
     */
    public void deleteUsuario(String username) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el usuario con username = {0}", username);
        UsuarioEntity usuario = getUsuario(username);
        if(!usuario.getTrayectoActualConductor().isEmpty()|| !usuario.getTrayectoActualPasajero().isEmpty() )
            throw new BusinessLogicException("No se puede borrar el usuario con usuario = " + username + " porque tiene trayectos asociados");
        if(usuario.getAlquilerArrendatario()!=null || !usuario.getAlquilerDuenio().isEmpty() )
            throw new BusinessLogicException("No se puede borrar el usuario con usuario = " + username + " porque tiene alquileres asociados");
        if(usuario.getPagoAHacer()!=null || usuario.getPagoARecibir()!=null )
            throw new BusinessLogicException("No se puede borrar el usuario con usuario = " + username + " porque tiene pagos asociados");
 
        persistence.delete(usuario.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", username);
    }
    
    public void createTrayectoConductor(String username, TrayectoEntity trayectoDeseado) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un trayceto en el que el usuario con username = {0} es conductor", username);
        UsuarioEntity usuario = getUsuario(username);
        if (usuario.getVehiculos().isEmpty())
            throw new BusinessLogicException("El usuario = " + username + " no puede crear un trayecto puesto que no tiene vehiculos");
        List<TrayectoEntity> traycetosActualC = usuario.getTrayectoActualConductor();
        List<TrayectoEntity> traycetosActualP = usuario.getTrayectoActualPasajero();

        for(TrayectoEntity traycetoActualC :traycetosActualC)
        {
            if(traycetoActualC.getFechaInicial().compareTo(trayectoDeseado.getFechaInicial())==0||traycetoActualC.getFechaFinal().compareTo(trayectoDeseado.getFechaFinal())==0)
                throw new BusinessLogicException("El usuario = " + username + "ya tiene porgramado un trayecto actual en esas horas");

        }
        for(TrayectoEntity traycetoActualP :traycetosActualP)
        {
            if(traycetoActualP.getFechaInicial().compareTo(trayectoDeseado.getFechaInicial())==0||traycetoActualP.getFechaFinal().compareTo(trayectoDeseado.getFechaFinal())==0)
                throw new BusinessLogicException("El usuario = " + username + "ya tiene porgramado un trayecto actual en esas horas");

        }
        usuario.getTrayectoActualConductor().add(trayectoDeseado);
        LOGGER.log(Level.INFO, "Termina proceso de crear un trayceto en el que el usuario con username = {0} es conductor", username);

    }
    
    public void createTrayectoPasajero(String username, TrayectoEntity trayectoDeseado) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un trayceto en el que el usuario con username = {0} es pasajero", username);
        UsuarioEntity usuario = getUsuario(username);
        List<TrayectoEntity> traycetosActualC = usuario.getTrayectoActualConductor();
        List<TrayectoEntity> traycetosActualP = usuario.getTrayectoActualPasajero();

        for(TrayectoEntity traycetoActualC :traycetosActualC)
        {
            if(traycetoActualC.getFechaInicial().compareTo(trayectoDeseado.getFechaInicial())==0||traycetoActualC.getFechaFinal().compareTo(trayectoDeseado.getFechaFinal())==0)
                throw new BusinessLogicException("El usuario = " + username + "ya tiene porgramado un trayecto actual en esas horas");

        }
        for(TrayectoEntity traycetoActualP :traycetosActualP)
        {
            if(traycetoActualP.getFechaInicial().compareTo(trayectoDeseado.getFechaInicial())==0||traycetoActualP.getFechaFinal().compareTo(trayectoDeseado.getFechaFinal())==0)
                throw new BusinessLogicException("El usuario = " + username + "ya tiene porgramado un trayecto actual en esas horas");

        }
        usuario.getTrayectoActualPasajero().add(trayectoDeseado);
        LOGGER.log(Level.INFO, "Termina proceso de crear un trayceto en el que el usuario con username = {0} es pasajero", username);
        
    }
    
    public void hacerPago(PagoEntity pago, String uConductor, String uPasajero) throws BusinessLogicException
    {
        UsuarioEntity pasajero = getUsuario(uPasajero);
        if(!pago.getUsuarioHace().getUsername().equals(uPasajero))
            throw new BusinessLogicException("El usuario = " + uPasajero + "no es un pasajero del trayecto que desea pagar");
        pasajero.setPagoAHacer(pago);
        if(!pago.getUsuarioRecibe().getUsername().equals(uConductor))
            throw new BusinessLogicException("El usuario = " + uConductor + "no es el conductor del trayecto que desea pagar");
        UsuarioEntity conductor = getUsuario(uConductor);
        conductor.setPagoARecibir(pago);
    }
    
    
    
    public void solicitarViaje(NotificacionEntity mensaje,TrayectoEntity trayectoDeseado, String username) throws BusinessLogicException
    {
        UsuarioEntity usuario = getUsuario(username);
        List<TrayectoEntity> traycetosActualC = usuario.getTrayectoActualConductor();
        List<TrayectoEntity> traycetosActualP = usuario.getTrayectoActualPasajero();

        for(TrayectoEntity traycetoActualC :traycetosActualC)
        {
            if(traycetoActualC.getFechaInicial().compareTo(trayectoDeseado.getFechaInicial())==0||traycetoActualC.getFechaFinal().compareTo(trayectoDeseado.getFechaFinal())==0)
                throw new BusinessLogicException("El usuario = " + username + " no puede solictar un nuevo viaje puesto que ya tiene porgramado un trayecto actual en esas horas");

        }
        for(TrayectoEntity traycetoActualP :traycetosActualP)
        {
            if(traycetoActualP.getFechaInicial().compareTo(trayectoDeseado.getFechaInicial())==0||traycetoActualP.getFechaFinal().compareTo(trayectoDeseado.getFechaFinal())==0)
                throw new BusinessLogicException("El usuario = " + username + " no puede solicitar un nuevo viaje puesto que ya tiene porgramado un trayecto actual en esas horas");

        }
    
        usuario.getNotificacionEnviada().add(mensaje);
    }
    
  
    
   
    
    /**
     * Verifica que el username no sea invalido.
     *
     * @param username a verificar
     * @return true si el username es valido.
     */
    private boolean validateUsername(String username) {
      return !(username == null || username.isEmpty());

    }
}
