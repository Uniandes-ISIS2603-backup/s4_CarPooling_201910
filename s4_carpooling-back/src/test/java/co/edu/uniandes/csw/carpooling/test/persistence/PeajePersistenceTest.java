/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.persistence.PeajePersistence;
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
 * @author im.sarmiento
 */
@RunWith(Arquillian.class)
public class PeajePersistenceTest {

    @Inject
    private PeajePersistence peajePersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PeajeEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PeajeEntity.class.getPackage())
                .addPackage(PeajePersistence.class.getPackage())
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
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PeajeEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            PeajeEntity entity = factory.manufacturePojo(PeajeEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un peaje.
     */
    @Test
    public void createPeajeTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        PeajeEntity result = peajePersistence.create(newEntity);

        Assert.assertNotNull(result);

        PeajeEntity entity = em.find(PeajeEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Peajes.
     */
    @Test
    public void getPeajesTest() {
        List<PeajeEntity> list = peajePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PeajeEntity ent : list) {
            boolean found = false;
            for (PeajeEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un peaje.
     */
    @Test
    public void getPeajeTest() {
        PeajeEntity entity = data.get(0);
        PeajeEntity newEntity = peajePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para eliminar un peaje.
     */
    @Test
    public void deletePeajeTest() {
        PeajeEntity entity = data.get(0);
        peajePersistence.delete(entity.getId());
        PeajeEntity deleted = em.find(PeajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un peaje.
     */
    @Test
    public void updateEditorialTest() {
        PeajeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);

        newEntity.setId(entity.getId());

        peajePersistence.update(newEntity);

        PeajeEntity resp = em.find(PeajeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para consultar un peaje por nombre.
     */
    @Test
    public void findEditorialByNameTest() {
        PeajeEntity entity = data.get(0);
        PeajeEntity newEntity = peajePersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = peajePersistence.findByName(null);
        Assert.assertNull(newEntity);
    }
}
