/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.SeguroLogic;
import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.SeguroPersistence;
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
 * @author df.penap
 */
@RunWith(Arquillian.class)
public class SeguroLogicTest {
    List<SeguroEntity> data = new ArrayList<SeguroEntity>();
    @Inject 
    SeguroLogic seguroLogic;
    @Inject
    private SeguroPersistence sp;
    @PersistenceContext
    private EntityManager em;
    @Inject
    UserTransaction utx;
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
        em.createQuery("delete from SeguroEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SeguroEntity entity = factory.manufacturePojo(SeguroEntity.class);

            em.persist(entity);
            data.add(entity);
        }
 }
    @Deployment
   
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SeguroEntity.class.getPackage())
                .addPackage(SeguroPersistence.class.getPackage())
                .addPackage(SeguroLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createSeguroTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();
        SeguroEntity newEntity = factory.manufacturePojo(SeguroEntity.class);
        SeguroEntity result = seguroLogic.create(newEntity);
        
        Assert.assertNotNull(result);
        SeguroEntity entity = em.find(SeguroEntity.class, result.getId());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo()); 
    }
    @Test(expected = BusinessLogicException.class)
    public void createSeguroConMismoTipo() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();
        SeguroEntity newEntity = factory.manufacturePojo(SeguroEntity.class);
        newEntity.setTipo(data.get(0).getTipo());
        seguroLogic.create(newEntity);
        
    }
    @Test
    public void getSeguroTest() {
        List<SeguroEntity> list = seguroLogic.get();
        Assert.assertEquals(data.size(), list.size());
        for (SeguroEntity ent : list) {
            boolean found = false;
            for (SeguroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void findSeguroTest() {
        SeguroEntity entity = data.get(0);
        SeguroEntity newEntity = seguroLogic.get(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());
    }
    @Test
    public void deleteSeguroTest() {
        SeguroEntity entity = data.get(0);
        seguroLogic.delete(entity.getId());
        SeguroEntity deleted = em.find(SeguroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    @Test
    public void updateSeguroTest() throws BusinessLogicException
    {
        SeguroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SeguroEntity newEntity = factory.manufacturePojo(SeguroEntity.class);

        newEntity.setId(entity.getId());

        seguroLogic.update(entity.getId(),newEntity);

        SeguroEntity resp = em.find(SeguroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTipo(), resp.getTipo());
    }
    @Test(expected = BusinessLogicException.class)
    public void updateSeguroSinTipoTest() throws BusinessLogicException
    {
        SeguroEntity entity = data.get(0);
        entity.setTipo(null);
        
        seguroLogic.update(entity.getId(),entity);
    }
}
