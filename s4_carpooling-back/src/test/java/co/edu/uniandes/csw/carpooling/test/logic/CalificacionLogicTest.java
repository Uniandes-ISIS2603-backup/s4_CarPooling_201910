/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carpooling.entities.CalificacionEntity;
import co.edu.uniandes.csw.carpooling.entities.TrayectoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
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
 * @author ja.morales11
 */
@RunWith(Arquillian.class)
public class CalificacionLogicTest {
    List<CalificacionEntity> data = new ArrayList<>();
    
    @Inject
    private CalificacionLogic calificacionLogic;
    
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private UsuarioEntity calificado;
    private UsuarioEntity calificador;
    private TrayectoEntity trayecto;
    
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
        em.createQuery("delete from TrayectoEntity").executeUpdate();
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 6; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            
            em.persist(entity); 
            data.add(entity);
        }
        calificado = factory.manufacturePojo(UsuarioEntity.class);
        em.persist(calificado);
        calificador = factory.manufacturePojo(UsuarioEntity.class);
        em.persist(calificador);
        trayecto = factory.manufacturePojo(TrayectoEntity.class);
        em.persist(trayecto);
        
        
    }
    
    @Deployment
   
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }





    
    
    
    
    
    


    
    @Test(expected = BusinessLogicException.class)
    public void addSinTrayecto() throws BusinessLogicException
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resp = calificacionLogic.addRelacionCalificacion(entity.getId(), Long.MIN_VALUE, calificado.getId(), calificador.getId());
    }
    
    
    @Test(expected = BusinessLogicException.class)
    public void addSinCalificado() throws BusinessLogicException
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resp = calificacionLogic.addRelacionCalificacion(entity.getId(), trayecto.getId(), Long.MIN_VALUE,  calificador.getId());
    }

    @Test(expected = BusinessLogicException.class)
    public void addSinCalificador() throws BusinessLogicException
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resp = calificacionLogic.addRelacionCalificacion(entity.getId(), trayecto.getId(), calificado.getId(),   Long.MIN_VALUE);
    }
    
    
    
    @Test(expected = BusinessLogicException.class)
    public void addMismoCalificador() throws BusinessLogicException
    {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resp = calificacionLogic.addRelacionCalificacion(entity.getId(), trayecto.getId(), calificado.getId(), calificado.getId());
       
    }
    
    
    
    
    @Test
    public void getCalificacionTest() {
        
        List<CalificacionEntity> list = calificacionLogic.getCalificacion();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity ent : list) {
            boolean found = false;
            for (CalificacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void findCalificacionTest() {
        
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = calificacionLogic.getCalificacion(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPuntaje(), newEntity.getPuntaje());
    }
    @Test
    public void deleteCalificacionTest() {
        CalificacionEntity entity = data.get(5);
        calificacionLogic.deleteCalificacion(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    
    
}
