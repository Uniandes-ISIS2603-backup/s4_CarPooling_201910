/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.InfoTCLogic;
import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.InfoTCPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class InfoTCLogicTest {

    List<InfoTCEntity> data = new ArrayList<>();

    @Inject
    private InfoTCLogic InfoTCLogic;

    @Inject
    private InfoTCPersistence ap;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private PagoEntity pago;

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

    private void clearData() {
        em.createQuery("delete from InfoTCEntity").executeUpdate();
        em.createQuery("delete from PagoEntity").executeUpdate();

    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 6; i++) {
            InfoTCEntity entity = factory.manufacturePojo(InfoTCEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        pago = factory.manufacturePojo(PagoEntity.class);
        em.persist(pago);

    }

    @Deployment

    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InfoTCEntity.class.getPackage())
                .addPackage(InfoTCPersistence.class.getPackage())
                .addPackage(InfoTCLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createInfoTCTest() throws BusinessLogicException {

        PodamFactory factory = new PodamFactoryImpl();
        InfoTCEntity newEntity = factory.manufacturePojo(InfoTCEntity.class);

        InfoTCEntity result = InfoTCLogic.createInfoTC(newEntity);

        Assert.assertNotNull(result);
        InfoTCEntity entity = em.find(InfoTCEntity.class, result.getId());
        Assert.assertEquals(newEntity.getT1(), entity.getT1());
        //Assert.assertEquals(newEntity.getT2(), entity.getT2());
        //Assert.assertEquals(newEntity.getEntidad1(), entity.getEntidad1());
        //Assert.assertEquals(newEntity.getEntidad2(), entity.getEntidad2());
    }

    @Test
    public void getInfoTCTest() {

        List<InfoTCEntity> list = InfoTCLogic.getInfoTCs();
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

    @Test
    public void findInfoTCTest() {

        InfoTCEntity entity = data.get(0);
        InfoTCEntity newEntity = InfoTCLogic.getInfoTC(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getT1(), newEntity.getT1());
    }

    @Test
    public void updateInfoTCTest() throws BusinessLogicException {
        InfoTCEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        InfoTCEntity newEntity = factory.manufacturePojo(InfoTCEntity.class);

        InfoTCLogic.updateInfoTC(entity.getId(), newEntity);

        InfoTCEntity resp = em.find(InfoTCEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getT1(), resp.getT1());
    }

    @Test
    public void deleteInfoTCTest() {
        InfoTCEntity entity = data.get(5);
        try {
            InfoTCLogic.deleteInfoTC(entity.getId());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(InfoTCLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        InfoTCEntity deleted = em.find(InfoTCEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
