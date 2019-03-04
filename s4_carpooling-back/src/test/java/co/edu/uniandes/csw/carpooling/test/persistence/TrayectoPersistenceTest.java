/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;



import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class TrayectoPersistenceTest {
    
    @Inject
    private TrayectoPersistence ap;
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TrayectoEntity.class.getPackage())
                .addPackage(TrayectoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createAlquilerTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoEntity newEntity = factory.manufacturePojo(TrayectoEntity.class);
        TrayectoEntity ae = ap.create(newEntity);
        Assert.assertNotNull(ap);
        TrayectoEntity entity = em.find(TrayectoEntity.class, ae.getId());
        //Assert.assertEquals(newEntity.getFechaInicial().getDay(), entity.getFechaInicial().getDay());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        
    }
}
