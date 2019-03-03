/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.PeajeLogic;
import co.edu.uniandes.csw.carpooling.entities.PeajeEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.PeajePersistence;
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
 * @author Isabela Sarmiento
 */
@RunWith(Arquillian.class)
public class PeajeLogicTest {


    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PeajeLogic peajeLogic;


    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PeajeEntity> data = new ArrayList<PeajeEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PeajeEntity.class.getPackage())
                .addPackage(PeajeLogic.class.getPackage())
                .addPackage(PeajePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PeajeEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PeajeEntity peajes = factory.manufacturePojo(PeajeEntity.class);
            em.persist(peajes);
            data.add(peajes);
        }
    }

    /**
     * Prueba para crear un Prize.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createPeajeTest() throws BusinessLogicException {
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        PeajeEntity result = peajeLogic.createPeaje(newEntity);
        Assert.assertNotNull(result);
        PeajeEntity entity = em.find(PeajeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
    }

    /**
     * Prueba para crear un Prize con organizacion nula.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPeajeConNombreRepetido() throws BusinessLogicException {
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        peajeLogic.createPeaje(newEntity);
    }

}
