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
     * Prueba para crear un peaje.
     *
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
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
     * Prueba para crear un peaje con nombre repetido.
     *
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPeajeConNombreRepetido() throws BusinessLogicException {
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        peajeLogic.createPeaje(newEntity);
    }

    /**
     * Prueba para crear peaje sin costo.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPeajeSinCosto() throws BusinessLogicException {
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setCosto(null);
        peajeLogic.createPeaje(newEntity);
    }

    /**
     * Prueba para crear peaje sin latitud.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPeajeSinLatitud() throws BusinessLogicException {
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setLatitud(null);
        peajeLogic.createPeaje(newEntity);
    }

    /**
     * Prueba para crear peaje sin longitud.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPeajeSinLongitud() throws BusinessLogicException {
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setLongitud(null);
        peajeLogic.createPeaje(newEntity);
    }

    /**
     * Prueba para crear peaje sin nombre.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPeajeSinNombre() throws BusinessLogicException {
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setNombre(null);
        peajeLogic.createPeaje(newEntity);
    }

    /**
     * Prueba para obtener una lista de peajes..
     */
    @Test
    public void getPeajeTest() {
        List<PeajeEntity> list = peajeLogic.get();
        Assert.assertEquals(data.size(), list.size());
        for (PeajeEntity ent : list) {
            boolean found = false;
            for (PeajeEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para obtener un peaje.
     */
    @Test
    public void findPeajeTest() {
        PeajeEntity entity = data.get(0);
        PeajeEntity newEntity = peajeLogic.get(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para borrar un peaje.
     */
    @Test
    public void deletePeajeTest() {
        PeajeEntity entity = data.get(0);
        peajeLogic.delete(entity.getId());
        PeajeEntity deleted = em.find(PeajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba actualizar un peaje.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updatePeajetest() throws BusinessLogicException {
        PeajeEntity peaje = data.get(0);
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        PeajeEntity result = peajeLogic.update(peaje.getId(), newEntity);
        Assert.assertNotNull(result);
        PeajeEntity entity = em.find(PeajeEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
    }

    /**
     * Prueba para actualizar un peaje con el mismo nombre.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePeajeMismoNombre() throws BusinessLogicException {
        PeajeEntity peaje = data.get(0);
        PeajeEntity peaje2 = data.get(1);
        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setNombre(peaje2.getNombre());
        PeajeEntity result = peajeLogic.update(peaje.getId(), newEntity);

    }

    /**
     * Prueba actualizar peaje sin nombre.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePeajeSinNombre() throws BusinessLogicException {
        PeajeEntity peaje = data.get(0);

        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setNombre(null);
        PeajeEntity result = peajeLogic.update(peaje.getId(), newEntity);
    }

    /**
     * Prueba actualizar peaje sin latitud.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePeajeSinLatitud() throws BusinessLogicException {
        PeajeEntity peaje = data.get(0);

        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setLatitud(null);
        PeajeEntity result = peajeLogic.update(peaje.getId(), newEntity);
    }

    /**
     * Prueba actualizar peaje sin longitud.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePeajeSinLongitud() throws BusinessLogicException {
        PeajeEntity peaje = data.get(0);

        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setLongitud(null);
        PeajeEntity result = peajeLogic.update(peaje.getId(), newEntity);
    }

    /**
     * Prueba actualizar peaje sin costo.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updatePeajeSinCosto() throws BusinessLogicException {
        PeajeEntity peaje = data.get(0);

        PeajeEntity newEntity = factory.manufacturePojo(PeajeEntity.class);
        newEntity.setCosto(null);
        PeajeEntity result = peajeLogic.update(peaje.getId(), newEntity);
    }

}
