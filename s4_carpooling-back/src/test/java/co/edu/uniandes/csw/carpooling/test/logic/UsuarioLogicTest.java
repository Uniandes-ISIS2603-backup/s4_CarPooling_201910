/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.UsuarioLogic;
import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    
    @Deployment
    public static JavaArchive createDeployment (){
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
        /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity users = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(users);
            data.add(users);
        }
    }
    
    @Test
    public void createUsuarioTest() throws BusinessLogicException {
        
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = usuarioLogic.create(newEntity);
        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getUsername(), entity.getUsername());
    }
    
    /**
     * Prueba para crear un Editorial con el mismo nombre de un Editorial que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConMismoNombreUsuarioTest() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setUsername(data.get(0).getUsername());
        usuarioLogic.create(newEntity);
    }
    
     /**
     * Prueba para crear un Book con ISBN inválido
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUserTestConUsernameInvalido() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setUsername("");
        usuarioLogic.create(newEntity);
    }

    /**
     * Prueba para crear un Book con ISBN inválido
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioTestConISBNInvalido2() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setUsername(null);
        usuarioLogic.create(newEntity);
    }
    
    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> list =usuarioLogic.getUsuarios();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity entity : list) {
            boolean found = false;
            for (UsuarioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = usuarioLogic.getUsuario(entity.getUsername());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getUsername(), resultEntity.getUsername());
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateUsuarioTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);
        pojoEntity.setUsername(entity.getUsername());
        usuarioLogic.updateUsuario(pojoEntity.getUsername(), pojoEntity);
        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertEquals(entity.getId(), resp.getId());
        Assert.assertEquals(entity.getNombre(), resp.getNombre());
        Assert.assertEquals(entity.getUsername(), resp.getUsername());
    }


    /**
     * Prueba para eliminar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    /*@Test
    public void deleteBookTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        entity.setAlquilerArrendatario(null);
        entity.setAlquilerDueño(null);
        entity.setTrayectoActualPasajero(null);
        entity.setTraycetoActualConductor(null);
        entity.setPagoAHacer(null);
        entity.setPagoARecibir(null);
         try {
            utx.begin();
            UsuarioEntity mod = em.merge(entity);
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        usuarioLogic.deleteUsuario(mod.getUsername());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }*/

    /**
     * Prueba para eliminar un Book.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteBookWithAuthorTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(1);
        AlquilerEntity pojoEntity = factory.manufacturePojo(AlquilerEntity.class);
        entity.setAlquilerArrendatario(pojoEntity);
        usuarioLogic.deleteUsuario(entity.getUsername());
    }
    
    
}
