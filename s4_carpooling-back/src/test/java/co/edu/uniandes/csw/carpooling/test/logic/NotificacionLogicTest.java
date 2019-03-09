/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;


import co.edu.uniandes.csw.carpooling.ejb.NotificacionLogic;
import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.NotificacionPersistence;
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
 * @author ja.morales11
 */
@RunWith(Arquillian.class)
public class NotificacionLogicTest {
    
    List<NotificacionEntity> data = new ArrayList<>();
    
    @Inject
    private NotificacionLogic notificacionLogic;
    
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private UsuarioEntity emisor;
    private UsuarioEntity receptor;
    
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 6; i++) {
            NotificacionEntity entity = factory.manufacturePojo(NotificacionEntity.class);
            
            em.persist(entity); 
            data.add(entity);
        }
        emisor = factory.manufacturePojo(UsuarioEntity.class);
        em.persist(emisor);
        receptor = factory.manufacturePojo(UsuarioEntity.class);
        em.persist(receptor);
    }
    
    @Deployment
   
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(NotificacionEntity.class.getPackage())
                .addPackage(NotificacionPersistence.class.getPackage())
                .addPackage(NotificacionLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    @Test
    public void createNotificacionTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        NotificacionEntity result = notificacionLogic.createNotificacion(newEntity);
        Assert.assertNotNull(result);
        NotificacionEntity entity = em.find(NotificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje()); 
        Assert.assertEquals(newEntity.getId(), entity.getId());
   
    }
    
    
    @Test
    public void addRelacionNotificacionTest() throws BusinessLogicException
    {
       
        NotificacionEntity entity = data.get(0);
        NotificacionEntity resp = notificacionLogic.addRelacionNotificacion(entity.getId(), receptor.getId(), emisor.getId());
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp.getEmisor(),emisor);
        Assert.assertEquals(resp.getReceptor(),receptor);
        
    }
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void deleteNotificacionTest() {
        NotificacionEntity entity = data.get(5);
        notificacionLogic.deleteNotificacion(entity.getId());
        NotificacionEntity deleted = em.find(NotificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    
    
    
    
    
}
