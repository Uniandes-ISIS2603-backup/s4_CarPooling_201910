/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.VehiculoLogic;
import co.edu.uniandes.csw.carpooling.entities.VehiculoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
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
@RunWith (Arquillian.class)
public class VehiculoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private VehiculoLogic vl;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject 
    private UserTransaction utx;
    private List<VehiculoEntity> data = new ArrayList<VehiculoEntity>();
    
    private List<VehiculoEntity> vehculosData = new ArrayList();
    
    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VehiculoEntity.class.getPackage())
                .addPackage(VehiculoLogic.class.getPackage())
                .addPackage(VehiculoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
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
private void clearData() {
        em.createQuery("delete from VehiculoEntity").executeUpdate();
    }

private void insertData()
{
     for (int i = 0; i < 3; i++) {
            VehiculoEntity vehiculo = factory.manufacturePojo(VehiculoEntity.class);
            em.persist(vehiculo);
            data.add(vehiculo);
        }
}

@Test
public void createVehiculoTest () throws BusinessLogicException
{
    VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);
    VehiculoEntity result = vl.createVehiculo(newEntity);
    Assert.assertNotNull(result);
    VehiculoEntity entity = em.find(VehiculoEntity.class, result.getId());
    Assert.assertEquals(newEntity.getPlaca(), entity.getPlaca());
    Assert.assertEquals(newEntity.getModelo(), entity.getModelo());
    Assert.assertEquals(newEntity.getAlquilado(), entity.getAlquilado());
    
}

@Test
public void getTest () 
{
    List<VehiculoEntity> list= vl.get();
    Assert.assertEquals(data.size(), list.size());
    
    for (VehiculoEntity ent : list)
    {
        boolean encontro = false;
        for (VehiculoEntity data: data)
        {
           if (ent.getPlaca().equals(data.getPlaca()))
           {
               encontro = true;
           }
        }
        Assert.assertTrue(encontro);
        
    }
}
@Test
 public void getTestConId() {
        
        VehiculoEntity entity = data.get(0);
        VehiculoEntity newEntity = vl.get(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPlaca(), newEntity.getPlaca());
    }
   @Test
    public void updateTest() throws BusinessLogicException
{
        VehiculoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VehiculoEntity newEntity = factory.manufacturePojo(VehiculoEntity.class);
        vl.updateVehiculo(entity.getId(), newEntity);        
        VehiculoEntity resp = em.find(VehiculoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getPlaca(), resp.getPlaca());
}

   
 @Test
    public void deleteTest() throws BusinessLogicException
    {
        VehiculoEntity entity = data.get(2);
        vl.delete(entity.getId());
        VehiculoEntity deleted = em.find(VehiculoEntity.class, entity.getId());
        Assert.assertNull(deleted);
   
}
}
