/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.AlquilerLogic;
import co.edu.uniandes.csw.carpooling.entities.AlquilerEntity;
import co.edu.uniandes.csw.carpooling.entities.SeguroEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
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
public class AlquilerLogicTest {
    List<AlquilerEntity> data = new ArrayList<>();
    @Inject
    private AlquilerLogic alquilerLogic;
    @Inject
    private AlquilerPersistence ap;
    @PersistenceContext
    private EntityManager em;
    @Inject
    UserTransaction utx;
    private UsuarioEntity arrendatario;
    private UsuarioEntity dueno;
    private SeguroEntity seguro;
    
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
        em.createQuery("delete from SeguroEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        
    }


 private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 6; i++) {
            AlquilerEntity entity = factory.manufacturePojo(AlquilerEntity.class);
            
            em.persist(entity); 
            data.add(entity);
        }
        arrendatario = factory.manufacturePojo(UsuarioEntity.class);
        em.persist(arrendatario);
        dueno = factory.manufacturePojo(UsuarioEntity.class);
        em.persist(dueno);
        seguro = factory.manufacturePojo(SeguroEntity.class);
        em.persist(seguro);
        
        
    }

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static  JavaArchive createDeployment()
    {
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AlquilerEntity.class.getPackage())
                .addPackage(AlquilerPersistence.class.getPackage())
                .addPackage(AlquilerLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Test
    public void createAlquilerTest() throws BusinessLogicException
    {
       
        PodamFactory factory = new PodamFactoryImpl();
        AlquilerEntity newEntity = factory.manufacturePojo(AlquilerEntity.class);
        
        AlquilerEntity result = alquilerLogic.createAlquiler(newEntity);
        
        Assert.assertNotNull(result);
        AlquilerEntity entity = em.find(AlquilerEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre()); 
    }
    
    /**
     * Prueba para añadir una relación.
     * @throws BusinessLogicException 
     */
    @Test
    public void addRelacionAlquilerTest() throws BusinessLogicException
    {
       
        AlquilerEntity entity = data.get(0);
        AlquilerEntity resp = alquilerLogic.addRelacionAlquiler(entity.getId(), dueno.getId(), arrendatario.getId(), seguro.getId());
        Assert.assertNotNull(resp);
        Assert.assertEquals(resp.getArrendatario(),arrendatario);
        Assert.assertEquals(resp.getDuenio(),dueno);
        Assert.assertEquals(resp.getSeguro(),seguro);
        
        
        
    }
    
    /**
     * Prueba crear un alquiler sin dueño.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void addSinDueno() throws BusinessLogicException
    {
        AlquilerEntity entity = data.get(0);
        
        AlquilerEntity resp = alquilerLogic.addRelacionAlquiler(entity.getId(), Long.MIN_VALUE, arrendatario.getId(), seguro.getId());
        
     
    }
    
    /**
     * Prueba crear un alquiler sin seguro.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void addSinSeguro() throws BusinessLogicException
    {
        AlquilerEntity entity = data.get(0);
        AlquilerEntity resp = alquilerLogic.addRelacionAlquiler(entity.getId(), dueno.getId(), arrendatario.getId(), Long.MIN_VALUE);
        
        
        
       
    }
    
    /**
     * Prueba añadir el mismo arrendatario.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void addMismoArrendatario() throws BusinessLogicException
    {
        AlquilerEntity entity = data.get(0);
        AlquilerEntity resp = alquilerLogic.addRelacionAlquiler(entity.getId(), dueno.getId(), dueno.getId(), seguro.getId());
        
        
        
      
         
    }
    
    /**
     * Prueba consultar la lista de alquileres.
     */
    @Test
    public void getAlquilerTest() {
        
        List<AlquilerEntity> list = alquilerLogic.getAlquiler();
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
    
    /**
     * Prueba encontrar un alquiler.
     */
    @Test
    public void findAlquilerTest() {
        
        AlquilerEntity entity = data.get(0);
        AlquilerEntity newEntity = alquilerLogic.getAlquiler(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    /**
     * Prueba borrar un alquiler.
     */
    @Test
    public void deleteAlquilerTest() {
        AlquilerEntity entity = data.get(5);
        alquilerLogic.deleteAlquiler(entity.getId());
        AlquilerEntity deleted = em.find(AlquilerEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba actualizar un alquiler.
     * @throws BusinessLogicException 
     */
    @Test
    public void updateAlquilerTest() throws BusinessLogicException{
        AlquilerEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AlquilerEntity newEntity = factory.manufacturePojo(AlquilerEntity.class);
        
        
        
        alquilerLogic.update(entity.getId(), newEntity);
        
        AlquilerEntity resp = em.find(AlquilerEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba alquiler sin arrendatario.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void replaceAlquilerSinArrendatario() throws BusinessLogicException{
        AlquilerEntity entity = data.get(0);
        alquilerLogic.replaceRelacionArrendatario(entity.getId(), Long.MIN_VALUE);
    }
    
    /**
     * Prueba alquiler con arrendatario igual.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void replaceAlquilerMismoArrendatario() throws BusinessLogicException
    {
        
       AlquilerEntity entity = data.get(0);
       alquilerLogic.addRelacionAlquiler(entity.getId(), dueno.getId(), arrendatario.getId(), seguro.getId());
       alquilerLogic.replaceRelacionArrendatario(entity.getId(), dueno.getId());
       
    }
    
    /**
     * Prueba hacer un alquiler sin seguro.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class)
    public void replaceAlquilerSinSeguro() throws BusinessLogicException{
        
        AlquilerEntity entity = data.get(0);
        alquilerLogic.replaceRelacionSeguro(entity.getId(), Long.MIN_VALUE);
    }
}
