/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
import co.edu.uniandes.csw.carpooling.persistence.InfoTCPersistence;
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
 * @author jf.garcia
 */
@RunWith(Arquillian.class)
public class InfoTCPersistenceTest {

    @Inject
    private InfoTCPersistence infoTCPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<InfoTCEntity> data = new ArrayList<InfoTCEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InfoTCEntity.class.getPackage())
                .addPackage(InfoTCPersistence.class.getPackage())
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
        em.createQuery("delete from InfoTCEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            InfoTCEntity entity = factory.manufacturePojo(InfoTCEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un InfoTC.
     */
    @Test
    public void createInfoTCTest() {

        PodamFactory factory = new PodamFactoryImpl();
        InfoTCEntity newEntity = factory.manufacturePojo(InfoTCEntity.class);
        InfoTCEntity result = infoTCPersistence.create(newEntity);

        Assert.assertNotNull(result);

        InfoTCEntity entity = em.find(InfoTCEntity.class, result.getId());

        Assert.assertEquals(newEntity.getT1(), entity.getT1());
        Assert.assertEquals(newEntity.getT2(), entity.getT2());
        Assert.assertEquals(newEntity.getEntidad1(), entity.getEntidad1());
        Assert.assertEquals(newEntity.getEntidad2(), entity.getEntidad2());
    }

    /**
     * Prueba para consultar la lista de tarjetas implicadas en un pago.
     */
    @Test
    public void getInfoTCsTest() {
        List<InfoTCEntity> list = infoTCPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (InfoTCEntity ent : list) {
            boolean found = false;
            for (InfoTCEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar la información de 2 tarjetas implicadas en un pago.
     */
    @Test
    public void getInfoTCTest() {
        InfoTCEntity entity = data.get(0);
        InfoTCEntity newEntity = infoTCPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getT1(), newEntity.getT1());
        Assert.assertEquals(entity.getT2(), newEntity.getT2());
        Assert.assertEquals(entity.getEntidad1(), newEntity.getEntidad1());
        Assert.assertEquals(entity.getEntidad2(), newEntity.getEntidad2());
    }

    /**
     * Prueba para actualizar la información de las tarjetas.
     */
    @Test
    public void updateInfoTCTest() {
        InfoTCEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        InfoTCEntity newEntity = factory.manufacturePojo(InfoTCEntity.class);

        newEntity.setId(entity.getId());

        infoTCPersistence.update(newEntity);

        InfoTCEntity resp = em.find(InfoTCEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getT1(), resp.getT1());
        Assert.assertEquals(newEntity.getT2(), resp.getT2());
        Assert.assertEquals(newEntity.getEntidad1(), resp.getEntidad1());
        Assert.assertEquals(newEntity.getEntidad2(), resp.getEntidad2());
    }

    /**
     * Prueba para eliminar la información de las tarjetas.
     */
    @Test
    public void deleteInfoTCTest() {
        InfoTCEntity entity = data.get(0);
        infoTCPersistence.delete(entity.getId());
        InfoTCEntity deleted = em.find(InfoTCEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
