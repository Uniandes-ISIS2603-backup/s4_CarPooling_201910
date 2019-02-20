/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.persistence;

import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoInfoPersistence;
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
public class TrayectoInfoPersisteneceTest {
    
    @Inject
    private TrayectoInfoPersistence tip;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TrayectoInfoEntity.class.getPackage())
                .addPackage(TrayectoInfoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoInfoEntity newEntity = factory.manufacturePojo(TrayectoInfoEntity.class);
        TrayectoInfoEntity ae = tip.create(newEntity);
        Assert.assertNotNull(tip);
        TrayectoInfoEntity entity = em.find(TrayectoInfoEntity.class, ae.getId());
        Assert.assertEquals(newEntity.getCombustible(), entity.getCombustible());
        //Assert.assertEquals(newEntity.getHoraFinal(), entity.getHoraFinal());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
    }
}
