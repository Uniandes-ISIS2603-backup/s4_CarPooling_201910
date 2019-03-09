/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.CiudadEntity;
import co.edu.uniandes.csw.carpooling.persistence.CiudadPersistence;
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
 * @author nc.hernandezm
 */
@RunWith(Arquillian.class)
public class CiudadPersistenceTest 
{
     List<CiudadEntity> data = new ArrayList<CiudadEntity>();
    @Inject
    private CiudadPersistence cp;
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
        em.createQuery("delete from CiudadEntity").executeUpdate();
    }
     
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CiudadEntity entity = factory.manufacturePojo(CiudadEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        
     }
       
     @Deployment
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CiudadEntity.class.getPackage())
                .addPackage(CiudadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createCiudadTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CiudadEntity newEntity = factory.manufacturePojo(CiudadEntity.class);
        CiudadEntity ve = cp.create(newEntity);
        Assert.assertNotNull(cp);
        CiudadEntity entity = em.find(CiudadEntity.class, ve.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getCoordenadas(), entity.getCoordenadas());
        
    }
    @Test
    public void findAllCiudadTest() {
        List<CiudadEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CiudadEntity ent : list) {
            boolean found = false;
            for (CiudadEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void findCiudadTest() {
        CiudadEntity entity = data.get(0);
       CiudadEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(newEntity.getCoordenadas(), entity.getCoordenadas());
    }

}
