/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carpooling.test.logic;

import co.edu.uniandes.csw.carpooling.ejb.InfoTCLogic;
import co.edu.uniandes.csw.carpooling.ejb.PagoLogic;
import co.edu.uniandes.csw.carpooling.entities.InfoTCEntity;
import co.edu.uniandes.csw.carpooling.entities.PagoEntity;
import co.edu.uniandes.csw.carpooling.entities.UsuarioEntity;
import co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carpooling.persistence.PagoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
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
 * Pruebas de logica de Pagos.
 *
 * @author jf.garcia
 */
@RunWith(Arquillian.class)
public class PagoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PagoLogic pagoLogic;

    @Inject
    private InfoTCLogic infoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<InfoTCEntity> infoData = new ArrayList<>();

    private List<PagoEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(InfoTCEntity.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
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
        em.createQuery("delete from PagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            InfoTCEntity infoEntity = factory.manufacturePojo(InfoTCEntity.class);
            em.persist(infoEntity);
            entity.setInfoTC(infoEntity);
            em.persist(entity);
            data.add(entity);
            infoData.add(infoEntity);
        }
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        em.persist(usuario);
        usuario.setPagoAHacer(data.get(2));
        data.get(2).setUsuarioHace(usuario);
    }

    /**
     * Prueba para crear un pago.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createPagoTest() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        InfoTCEntity newInfoEntity = factory.manufacturePojo(InfoTCEntity.class);

        newInfoEntity = infoLogic.createInfoTC(newInfoEntity);
        newEntity.setInfoTC(newInfoEntity);
        PagoEntity result = pagoLogic.createPago(newEntity);
        Assert.assertNotNull(result);
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getValor(), entity.getValor());
        Assert.assertEquals(newEntity.getInfoTC(), entity.getInfoTC());
    }

    /**
     * Prueba para crear un pago con información nula.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPagoInformacionInvalida1Test() throws BusinessLogicException {
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setInfoTC(null);
        pagoLogic.createPago(newEntity);
    }

    /**
     * Prueba para consultar la lista de pagos.
     */
    @Test
    public void getPagosTest() {
        List<PagoEntity> list = pagoLogic.getPagos();
        Assert.assertEquals(data.size(), list.size());
        for (PagoEntity entity : list) {
            boolean found = false;
            for (PagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un pago.
     */
    @Test
    public void getPagoTest() {
        PagoEntity entity = data.get(0);
        PagoEntity resultEntity = pagoLogic.getPago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getValor(), resultEntity.getValor());
    }

    /**
     * Prueba para actualizar un pago.
     * @throws co.edu.uniandes.csw.carpooling.exceptions.BusinessLogicException
     */
    @Test
    public void updatePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);

        pojoEntity.setId(entity.getId());

        pagoLogic.updatePago(pojoEntity.getId(), pojoEntity);

        PagoEntity resp = em.find(PagoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getValor(), resp.getValor());
    }

    /**
     * Prueba para eliminar un pago.
     *
     * @throws BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deletePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(2);
        pagoLogic.deletePago(entity.getId());
    }

}
