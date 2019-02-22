/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.persistence.CalificacionPersistence;
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
 * @author Julio Morales
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest {
    
    @Inject
    private CalificacionPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    @Before
    public void setUp() {
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData(){
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }
    
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i=0; i<3;i++){
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
        
        
    }
    
    
    
    @Deployment
    public static JavaArchive deployment () {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    } 
    
    
    
    @Test
    public void createCalificacionTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity newCalificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity ee = cp.create(newCalificacionEntity);
        Assert.assertNotNull(cp);
        CalificacionEntity entity = em.find(CalificacionEntity.class, ee.getId());
        Assert.assertEquals(newCalificacionEntity.getPuntaje(), entity.getPuntaje());
    }
    
 
    @Test
    public void getCalificacionTest(){
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPuntaje(), newEntity.getPuntaje());
    }
    
    
    
}
