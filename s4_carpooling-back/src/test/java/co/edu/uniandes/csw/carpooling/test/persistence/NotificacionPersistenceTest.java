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

    List<NotificacionEntity> data = new ArrayList<>();
    @Inject
    private NotificacionPersistence np;
    @PersistenceContext
    private EntityManager em;
    @Inject
    UserTransaction utx;

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(NotificacionEntity.class.getPackage())
                .addPackage(NotificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
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

    /**
     * Limpiar las tablas implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from NotificacionEntity").executeUpdate();
    }

    /**
     * Crear los datos de prueba.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            NotificacionEntity entity = factory.manufacturePojo(NotificacionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Test para crear una notificación.
     */
    @Test
    public void createNotificacionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        NotificacionEntity newEntity = factory.manufacturePojo(NotificacionEntity.class);
        NotificacionEntity ee = np.create(newEntity);
        Assert.assertNotNull(np);
        NotificacionEntity entity = em.find(NotificacionEntity.class, ee.getId());
        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje());
    }

    /**
     * Test para actualizar una notificación.
     */
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
