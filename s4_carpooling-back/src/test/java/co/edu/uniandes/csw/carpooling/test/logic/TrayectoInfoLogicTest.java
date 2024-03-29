/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.TrayectoInfoLogic;
import co.edu.uniandes.csw.carpooling.entities.TrayectoInfoEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.TrayectoInfoPersistence;
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
 * @author Alejo
 */
@RunWith(Arquillian.class)
public class TrayectoInfoLogicTest {

    @Inject
    private TrayectoInfoLogic info;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<TrayectoInfoEntity> data = new ArrayList<TrayectoInfoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TrayectoInfoEntity.class.getPackage())
                .addPackage(TrayectoInfoLogic.class.getPackage())
                .addPackage(TrayectoInfoPersistence.class.getPackage())
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from TrayectoInfoEntity").executeUpdate();
    }

    /**
     * Inserta los datos de prueba.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TrayectoInfoEntity entity = factory.manufacturePojo(TrayectoInfoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba crear la información del trayecto.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createTrayectoInfoTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoInfoEntity newEntity = factory.manufacturePojo(TrayectoInfoEntity.class);
        newEntity.setCosto(Math.abs(newEntity.getCosto()));
        newEntity.setDuracion(Math.abs(newEntity.getDuracion()));
        TrayectoInfoEntity ae = info.createEntity(newEntity);
        Assert.assertNotNull(ae);
        TrayectoInfoEntity entity = em.find(TrayectoInfoEntity.class, ae.getId());
        Assert.assertEquals(newEntity.getCombustible(), entity.getCombustible());
        Assert.assertEquals(newEntity.getHoraFinal().getHours(), entity.getHoraFinal().getHours());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
    }

    /**
     * Prueba crear con costo inválido.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTrayectoInfoTestConCostoInvalido() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoInfoEntity newEntity = factory.manufacturePojo(TrayectoInfoEntity.class);
        newEntity.setCosto(new Integer(-1));
        info.createEntity(newEntity);
    }

    /**
     * Prueba crear con duración inválida.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTrayectoInfoTestConDuracionInvalido() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoInfoEntity newEntity = factory.manufacturePojo(TrayectoInfoEntity.class);
        newEntity.setDuracion(new Integer(-1));
        info.createEntity(newEntity);
    }

    /**
     * Prueba crear con hora inválida.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTrayectoInfoTestConHoraInicialIgualANull() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        TrayectoInfoEntity newEntity = factory.manufacturePojo(TrayectoInfoEntity.class);
        newEntity.setHoraInicial(null);
        info.createEntity(newEntity);
    }

    /**
     * Prueba obtener la información del trayecto.
     */
    @Test
    public void getTrayectoInfTest() {
        TrayectoInfoEntity entity = data.get(0);
        TrayectoInfoEntity newEntity = info.getTrayectoInfo(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCombustible(), entity.getCombustible());
        Assert.assertEquals(newEntity.getHoraFinal().getHours(), entity.getHoraFinal().getHours());
        Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
    }

    /**
     * Prueba borrar la información del trayecto.
     */
    @Test
    public void deleteTrayectoInfoTest() {
        TrayectoInfoEntity entity = data.get(0);
        info.deleteTrayectoInfo(entity.getId());
        TrayectoInfoEntity deleted = em.find(TrayectoInfoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
