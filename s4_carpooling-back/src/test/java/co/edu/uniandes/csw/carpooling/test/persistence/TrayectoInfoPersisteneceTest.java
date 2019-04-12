/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoInfoPersistence;
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
public class TrayectoInfoPersisteneceTest {

    @Inject
    private TrayectoInfoPersistence tip;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<TrayectoInfoEntity> data = new ArrayList<TrayectoInfoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TrayectoInfoEntity.class.getPackage())
                .addPackage(TrayectoInfoPersistence.class.getPackage())
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
        em.createQuery("delete from TrayectoInfoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TrayectoInfoEntity entity = factory.manufacturePojo(TrayectoInfoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba obtener la información del trayecto.
     */
    @Test
    public void getTrayectoInfoTest() {
        List<TrayectoInfoEntity> list = tip.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TrayectoInfoEntity ent : list) {
            boolean found = false;
            for (TrayectoInfoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba crear un trayecto.
     */
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoInfoEntity newEntity = factory.manufacturePojo(TrayectoInfoEntity.class);
        TrayectoInfoEntity ae = tip.create(newEntity);
        Assert.assertNotNull(ae);
        TrayectoInfoEntity entity = em.find(TrayectoInfoEntity.class, ae.getId());
        Assert.assertEquals(newEntity.getCombustible(), entity.getCombustible());
        //Assert.assertEquals(newEntity.getHoraFinal(), entity.getHoraFinal());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
    }

    /**
     * Prueba obtener un trayecto.
     */
    @Test
    public void getTrayectoInfTest() {
        TrayectoInfoEntity entity = data.get(0);
        TrayectoInfoEntity newEntity = tip.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCombustible(), entity.getCombustible());
        Assert.assertEquals(newEntity.getHoraFinal().getHours(), entity.getHoraFinal().getHours());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
    }

    /**
     * Prueba borrar un trayecto.
     */
    @Test
    public void deleteTrayectoInfoTest() {
        TrayectoInfoEntity entity = data.get(0);
        tip.delete(entity.getId());
        TrayectoInfoEntity deleted = em.find(TrayectoInfoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba actualizar un trayecto.
     */
    @Test
    public void updateTrayectoInfoTest() {
        TrayectoInfoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoInfoEntity newEntity = factory.manufacturePojo(TrayectoInfoEntity.class);

        newEntity.setId(entity.getId());

        tip.update(newEntity);

        TrayectoInfoEntity resp = em.find(TrayectoInfoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCombustible(), resp.getCombustible());
        Assert.assertEquals(newEntity.getDuracion(), resp.getDuracion());
        Assert.assertEquals(newEntity.getHoraInicial().getHours(), resp.getHoraInicial().getHours());
        Assert.assertEquals(newEntity.getHoraFinal().getHours(), resp.getHoraFinal().getHours());
        Assert.assertEquals(newEntity.getCosto(), resp.getCosto());
    }

}
