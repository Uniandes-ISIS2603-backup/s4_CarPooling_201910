/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
import co.edu.uniandes.csw.carpooling.persistence.VehiculoPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.Assert;

/**
 *
 * @author nc.hernandezm
 */
@RunWith(Arquillian.class)
public class VehiculoPersistenceTest 
{
     List<VehiculoEntity> data = new ArrayList<VehiculoEntity>();
    @Inject
    private VehiculoPersistence vp;
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
        em.createQuery("delete from VehiculoEntity").executeUpdate();
    }
     
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            VehiculoEntity entity = factory.manufacturePojo(VehiculoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        
     }
       
     @Deployment
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VehiculoEntity.class.getPackage())
                .addPackage(VehiculoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createVehiculoTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);
        VehiculoEntity ve = vp.create(newEntity);
        Assert.assertNotNull(vp);
        VehiculoEntity entity = em.find(VehiculoEntity.class, ve.getId());
        Assert.assertEquals(newEntity.getPlaca(), entity.getPlaca());
        
    }
    @Test
    public void finAllVehiculoTEst() {
        List<VehiculoEntity> list = vp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (VehiculoEntity ent : list) {
            boolean found = false;
            for (VehiculoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void findVehiculoTest() {
        VehiculoEntity entity = data.get(0);
        VehiculoEntity newEntity = vp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPlaca(), newEntity.getPlaca());
    }
    @Test
public void updateVehiculoTest() {
    VehiculoEntity entity = data.get(0);
    PodamFactory factory = new PodamFactoryImpl();
    VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);

    newEntity.setId(entity.getId());

    vp.update(newEntity);

    VehiculoEntity resp = em.find(VehiculoEntity.class, entity.getId());

    Assert.assertEquals(newEntity.getPlaca(), resp.getPlaca());
}
@Test
public void deleteVehiculoTest() {
    VehiculoEntity entity = data.get(0);
    vp.delete(entity.getId());
    VehiculoEntity deleted = em.find(VehiculoEntity.class, entity.getId());
    Assert.assertNull(deleted);
}
}


