/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.persistence.AlquilerPersistence;
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
public class AlquilerPersistenceTest {
    List<AlquilerEntity> data = new ArrayList<AlquilerEntity>();
    @Inject
    private AlquilerPersistence ap;
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
        em.createQuery("delete from AlquilerEntity").executeUpdate();
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AlquilerEntity entity = factory.manufacturePojo(AlquilerEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @Deployment
   
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AlquilerEntity.class.getPackage())
                .addPackage(AlquilerPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createAlquilerTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        AlquilerEntity newEntity = factory.manufacturePojo(AlquilerEntity.class);
        AlquilerEntity ae = ap.create(newEntity);
        Assert.assertNotNull(ap);
        AlquilerEntity entity = em.find(AlquilerEntity.class, ae.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
    }
    @Test
    public void finAllAlquilerTEst() {
        List<AlquilerEntity> list = ap.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AlquilerEntity ent : list) {
            boolean found = false;
            for (AlquilerEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void findAlquilerTest() {
        AlquilerEntity entity = data.get(0);
        AlquilerEntity newEntity = ap.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
}
