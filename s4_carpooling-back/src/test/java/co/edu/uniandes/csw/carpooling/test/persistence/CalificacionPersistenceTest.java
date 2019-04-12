/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.persistence.CalificacionPersistence;
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
 * @author Julio Morales
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest {

    @Inject
    private CalificacionPersistence cp;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    List<TrayectoEntity> trayectoData = new ArrayList<TrayectoEntity>();

    List<UsuarioEntity> usuarioData = new ArrayList<UsuarioEntity>();
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
     * Limpia las tablas relacionadas con las pruebas.
     */
    private void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from TrayectoEntity").executeUpdate();
    }

    /**
     * Inserta los datos de prueba.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(entity);
            data.add(entity); 
            
        }
        
        for (int i = 0; i < 3; i++) {
            TrayectoEntity entity = factory.manufacturePojo(TrayectoEntity.class);
            em.persist(entity);
            trayectoData.add(entity);
            
             
        }
        
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            usuarioData.add(entity);
            
        }
        
        
        data.get(0).setTrayecto(trayectoData.get(0));
        data.get(2).setTrayecto(trayectoData.get(0));
        
        

    }

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive deployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Prueba crear una calificación.
     */
    @Test
    public void createCalificacionTest() {

        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newCalificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity ee = cp.create(newCalificacionEntity);
        Assert.assertNotNull(cp);
        CalificacionEntity entity = em.find(CalificacionEntity.class, ee.getId());
        Assert.assertEquals(newCalificacionEntity.getPuntaje(), entity.getPuntaje());
    }

    /**
     * Prueba obtener una calificación.
     */
    @Test
    public void CalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPuntaje(), newEntity.getPuntaje());
    }

    /**
     * Prueba para encontrar todos las calificaciones.
     */
    @Test
    public void findAllCalificacionTest(){
        List<CalificacionEntity> lista = cp.findAll();
        Assert.assertEquals(data.size(), lista.size());
        for (CalificacionEntity ent : lista) {
            boolean found = false;
            for (CalificacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
    
    /**
     * Prueba para consultar la lista de calificaciones de un trayecto.
    
    @Test
    public void findCalificacionByTrayectoTest()
    {
        TrayectoEntity trayecto = trayectoData.get(0);
        List<CalificacionEntity> lista = cp.findByTrayecto(trayecto.getId());
        for(CalificacionEntity ent : lista)
        {
            
            boolean found = false;
            for(CalificacionEntity entity : data)
            {
                if(entity.getId().equals(ent.getId()))
                {
                    found = true;
                }
                
            }
            Assert.assertTrue(found);
        }
            
    }
    */
    
    /**
     * Prueba para actualizar una calificación.
     */
    @Test
    public void updateCalificacionrTest() {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getPuntaje(), resp.getPuntaje());
    }

    /**
     * Prueba borrar una calificación.
     */
    @Test
    public void deleteCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        cp.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    
     
    
    
    
}
