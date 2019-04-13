/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
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
public class TrayectoPersistenceTest {

    @Inject
    private TrayectoPersistence ap;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private final List<TrayectoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TrayectoEntity.class.getPackage())
                .addPackage(TrayectoPersistence.class.getPackage())
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
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TrayectoEntity entity = factory.manufacturePojo(TrayectoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Limpia las tablas implicadas.
     */
    private void clearData() {
        em.createQuery("delete from TrayectoEntity").executeUpdate();
    }

    /**
     * Prueba crear un trayecto.
     */
    @Test
    public void createTrayectoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoEntity newEntity = factory.manufacturePojo(TrayectoEntity.class);
        TrayectoEntity ae = ap.create(newEntity);
        Assert.assertNotNull(ap);
        TrayectoEntity entity = em.find(TrayectoEntity.class, ae.getId());
        Assert.assertEquals(newEntity.getFechaInicial().getDay(), entity.getFechaInicial().getDay());
        Assert.assertEquals(newEntity.getId(), entity.getId());

    }

    /**
     * Prueba obtener todos los trayectos.
     */
    @Test
    public void getTrayectosTest() {
        List<TrayectoEntity> list = ap.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TrayectoEntity ent : list) {
            boolean found = false;
            for (TrayectoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba obtener un trayecto.
     */
    @Test
    public void getTrayectoTest() {
        TrayectoEntity entity = data.get(0);
        TrayectoEntity newEntity = ap.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getFechaInicial().getDay(), entity.getFechaInicial().getDay());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba borrar un trayecto.
     */
    @Test
    public void deleteTrayectoTest() {
        TrayectoEntity entity = data.get(0);
        ap.delete(entity.getId());
        TrayectoEntity deleted = em.find(TrayectoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba actualizar un trayecto.
     */
    @Test
    public void updateBookTest() {
        TrayectoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoEntity newEntity = factory.manufacturePojo(TrayectoEntity.class);

        newEntity.setId(entity.getId());

        ap.update(newEntity);

        TrayectoEntity resp = em.find(TrayectoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getConductor(), resp.getConductor());
        Assert.assertEquals(newEntity.getFechaInicial().getDay(), resp.getFechaInicial().getDay());
        Assert.assertEquals(newEntity.getFechaFinal().getDay(), resp.getFechaFinal().getDay());
        //Assert.assertEquals(newEntity.getPago(), resp.getPago());
    }

}
