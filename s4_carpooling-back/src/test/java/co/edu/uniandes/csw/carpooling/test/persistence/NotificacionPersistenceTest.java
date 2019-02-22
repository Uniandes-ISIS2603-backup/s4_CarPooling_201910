/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.NotificacionEntity;
import co.edu.uniandes.csw.carpooling.persistence.NotificacionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
public class NotificacionPersistenceTest {
    List<NotificacionEntity> data = new ArrayList<NotificacionEntity>();
    @Inject
    private NotificacionPersistence np;
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
    
    private void clearData(){
        em.createQuery("delete from NotificacionEntity").executeUpdate();
    }
    
    private  void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++) {
            NotificacionEntity entity = factory.manufacturePojo(NotificacionEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(NotificacionEntity.class.getPackage())
                .addPackage(NotificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    @Test
    public void createNotificacionTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        NotificacionEntity ee = np.create(newEntity);
        Assert.assertNotNull(np);
        NotificacionEntity entity = em.find(NotificacionEntity.class, ee.getId());
        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje());
    }
    
    
    @Test
    public void updateNotificacionTest() {
        NotificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        newEntity.setId((entity.getId()));
        np.update(newEntity);
        NotificacionEntity resp = em.find(NotificacionEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getMensaje(), resp.getMensaje());
    }
}
