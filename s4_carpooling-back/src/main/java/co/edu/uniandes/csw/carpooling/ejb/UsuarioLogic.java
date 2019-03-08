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
     */
    public UsuarioEntity updateUsuario(String username, UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con username = {0}", username);
        if (!validateUsername(usuarioEntity.getUsername())) {
            throw new BusinessLogicException("El username es inválido");
        }
        UsuarioEntity newEntity = persistence.update(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuerio con id = {0}", usuarioEntity.getId());
        return newEntity;
    }
    
    /**
     * Eliminar un usuario por ID
     *
     * @param usuername El username del usuario libro a eliminar
     * @throws BusinessLogicException si el libro tiene autores asociados
     */
    public void deleteUsuario(String username) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el usuario con username = {0}", username);
        UsuarioEntity usuario = getUsuario(username);
        if(!usuario.getTrayecetoActualConductor().isEmpty()|| !usuario.getTrayectoActualPasajero().isEmpty() )
            throw new BusinessLogicException("No se puede borrar el usuario con usuario = " + username + " porque tiene trayectos asociados");
        if(usuario.getAlquilerArrendatario()!=null || !usuario.getAlquilerDueño().isEmpty() )
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
        List<TrayectoEntity> traycetosActualC = usuario.getTrayecetoActualConductor();
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
        usuario.getTrayecetoActualConductor().add(trayectoDeseado);
        LOGGER.log(Level.INFO, "Termina proceso de crear un trayceto en el que el usuario con username = {0} es conductor", username);

    }
    
    public void createTrayectoPasajero(String username, TrayectoEntity trayectoDeseado) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de crear un trayceto en el que el usuario con username = {0} es pasajero", username);
        UsuarioEntity usuario = getUsuario(username);
        List<TrayectoEntity> traycetosActualC = usuario.getTrayecetoActualConductor();
        List<TrayectoEntity> traycetosActualP = usuario.getTrayectoActualPasajero();

        for(TrayectoEntity traycetoActualC :traycetosActualC)
        {
            if(traycetoActualC.getFechaInicial()==trayectoDeseado.getFechaInicial()||traycetoActualC.getFechaFinal()==trayectoDeseado.getFechaFinal())
                throw new BusinessLogicException("El usuario = " + username + "ya tiene porgramado un trayecto actual en esas horas");

        }
        for(TrayectoEntity traycetoActualP :traycetosActualP)
        {
            if(traycetoActualP.getFechaInicial()==trayectoDeseado.getFechaInicial()||traycetoActualP.getFechaFinal()==trayectoDeseado.getFechaFinal())
                throw new BusinessLogicException("El usuario = " + username + "ya tiene porgramado un trayecto actual en esas horas");

        }
        usuario.getTrayectoActualPasajero().add(trayectoDeseado);
        LOGGER.log(Level.INFO, "Termina proceso de crear un trayceto en el que el usuario con username = {0} es pasajero", username);
        
    }
    
    public void hacerPago(PagoEntity pago, String uConductor, String uPasajero) throws BusinessLogicException
    {
        UsuarioEntity pasajero = getUsuario(uPasajero);
        /**boolean encontrado = false;
        List<UsuarioEntity> pasajeros = pago.getTrayecto().getPasajeros();
        for(UsuarioEntity p : pasajeros)
        {
            if(p.getUsername().equals(pasajero.getUsername()))
                encontrado = true;
        }
        if(!encontrado)
            throw new BusinessLogicException("El usuario = " + uPasajero + "no es un pasajero del trayecto que desea pagar");
        */
        if(!pago.getUsuarioHace().getUsername().equals(uPasajero))
            throw new BusinessLogicException("El usuario = " + uPasajero + "no es un pasajero del trayecto que desea pagar");
        pasajero.setPagoAHacer(pago);
        if(!pago.getUsuarioRecibe().getUsername().equals(uConductor))
            throw new BusinessLogicException("El usuario = " + uConductor + "no es el conductor del trayecto que desea pagar");
        UsuarioEntity conductor = getUsuario(uConductor);
        conductor.setPagoARecibir(pago);
    }
    
    public void calificar(String calificador, String calificado, CalificacionEntity calificacion) throws BusinessLogicException
    {
        //if(!calificacion.getCalifcador().getUsername.equals(calificador) || !calificacion.getCalificado.getUsername.equals(calificado))
            //throw new BusinessLogicException("La claificacion y los nombres de usuario no coinciden");
        //TrayectoEntity trayecto = calificacion.getTrayecto().getConductor;
        if(calificador.equals(calificado))
            throw new BusinessLogicException("El usuario = " + calificador + "no puede calificarse a sí mismo");
        /**
        boolean encontrado = false;
        List<UsuarioEntity> pasajeros = calificacion.getTrayecto().getPasajeros();
        for(UsuarioEntity p : pasajeros)
        {
            if(p.getUsername().equals(calificado)|| p.getUsername().equals(calificador))
                encontrado = true;
        }
        if((calificacion.getTrayecto().getCondutor.equals(calificador)||calificacion.getTrayecto().getCondutor.equals(calificado))&& !encontrado )
        {
            throw new BusinessLogicException("La calificacion no es válidad puesto que los dos usuarios no estuvieron en el mismo trayecto");
        }*/
        getUsuario(calificado).getCalificaciones().add(calificacion);
    }
    
    public void solicitarViaje(NotificacionEntity mensaje,TrayectoEntity trayectoDeseado, String username) throws BusinessLogicException
    {
        UsuarioEntity usuario = getUsuario(username);
        List<TrayectoEntity> traycetosActualC = usuario.getTrayecetoActualConductor();
        List<TrayectoEntity> traycetosActualP = usuario.getTrayectoActualPasajero();

        for(TrayectoEntity traycetoActualC :traycetosActualC)
        {
            if(traycetoActualC.getFechaInicial()==trayectoDeseado.getFechaInicial()||traycetoActualC.getFechaFinal()==trayectoDeseado.getFechaFinal())
                throw new BusinessLogicException("El usuario = " + username + " no puede solictar un nuevo viaje puesto que ya tiene porgramado un trayecto actual en esas horas");

        }
        for(TrayectoEntity traycetoActualP :traycetosActualP)
        {
            if(traycetoActualP.getFechaInicial()==trayectoDeseado.getFechaInicial()||traycetoActualP.getFechaFinal()==trayectoDeseado.getFechaFinal())
                throw new BusinessLogicException("El usuario = " + username + " no puede solicitar un nuevo viaje puesto que ya tiene porgramado un trayecto actual en esas horas");

        }
    
        usuario.getNotificacionEnviada().add(mensaje);
    }
    
    public void aceptarViaje(NotificacionEntity mensaje,TrayectoEntity trayectoSolicitado, NotificacionEntity rechazo, NotificacionEntity acepto) throws BusinessLogicException
    {
        /**
        UsuarioEntity emisor = mensaje.getEmisor();
        UsuarioEntity receptor = mensaje.getReceptor();
        if(trayectoSolicitado.getInfoTrayecto().getCupos()-trayectoSolicitado.getPasajeros().size()!=0)
        {
            emisor.getNotificacionRecibida().add(rechazo);
            receptor.getNotificacionRecibida().add(rechazo);

        }
        else
        {
            emisor.getNotificacionRecibida().add(acepto);
            receptor.getNotificacionRecibida().add(acepto);   
        }     */
    }
    
    /**public void alquilarVehiculoDueño(String username, String placa)
    {
        
    }*/
    
    /**public void alquilarVehiculoArrendatario(String username, String placa)
    {
        
    }*/
    
    
    /**public void eliminarNotifiacionRecibida(String username, NotificacionEntity notificacion)
    {
        
    }*/
    /**public void eliminarNotifiacionEnviada(String username, NotificacionEntity notificacion)
    {
        
    }*/
    
    /**public void eliminarTrayectoPasajero(String username, TrayectoEntity trayecto)
    {
        
    }*/
    
     /**public void eliminarTrayectoConductor(String usernam, TrayectoEntity trayecto)
    {
        
    }*/
    
    /**public void eliminarAlquilerVehiculoDueño(String username, AlquilerEntity alquiler)
    {
        
    }*/
    
    /**public void eliminarAlquilerVehiculoArrendatario(String username, AlquilerEntity alquiler)
    {
        
    }*/
    
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
