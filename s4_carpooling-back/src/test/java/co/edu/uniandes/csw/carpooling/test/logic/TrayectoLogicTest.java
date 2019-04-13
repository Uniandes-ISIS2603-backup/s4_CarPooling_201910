/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.TrayectoLogic;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
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
 * @author Alejo
 */
@RunWith(Arquillian.class)
public class TrayectoLogicTest {

    @Inject
    private TrayectoLogic trayecto;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TrayectoEntity> data = new ArrayList<TrayectoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TrayectoEntity.class.getPackage())
                .addPackage(TrayectoLogic.class.getPackage())
                .addPackage(TrayectoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial del test.
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
     * Limpiar las tablas implicadas.
     */
    private void clearData() {
        em.createQuery("delete from TrayectoEntity").executeUpdate();

    }

    /**
     * Insertar los datos de prueba.
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
     * Prueba crear trayecto.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createTrayectoTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoEntity newEntity = factory.manufacturePojo(TrayectoEntity.class);
        TrayectoEntity ae = trayecto.createEntity(newEntity);
        Assert.assertNotNull(ae);
        TrayectoEntity entity = em.find(TrayectoEntity.class, ae.getId());
        Assert.assertEquals(newEntity.getFechaInicial().getDay(), entity.getFechaInicial().getDay());
        Assert.assertEquals(newEntity.getId(), entity.getId());

    }

    /**
     * Prueba obtener todos los trayectos.
     */
    @Test
    public void getTrayectosTest() {
        List<TrayectoEntity> list = trayecto.getTrayectos();
        Assert.assertEquals(data.size(), list.size());
        for (TrayectoEntity entity : list) {
            boolean found = false;
            for (TrayectoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

}
