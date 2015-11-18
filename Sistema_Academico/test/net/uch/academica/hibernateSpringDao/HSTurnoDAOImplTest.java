/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcTurno;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LUIS
 */
public class HSTurnoDAOImplTest {
    
    public HSTurnoDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertarTurno method, of class HSTurnoDAOImpl.
     */
    @Test
    public void testInsertarTurno() {
        System.out.println( "insertarTurno" );
        AcTurno turno = null;
        HSTurnoDAOImpl instance = new HSTurnoDAOImpl();
        instance.insertarTurno( turno );
//        // TODO review the generated test code and remove the default call to fail.
//        fail( "The test case is a prototype." );
    }

    /**
     * Test of seleccionarTurno method, of class HSTurnoDAOImpl.
     */
    @Test
    public void testSeleccionarTurno_3args() {
        System.out.println( "seleccionarTurno" );
        String codigo = "";
        String nombre = "";
        int semestre = 0;
        HSTurnoDAOImpl instance = new HSTurnoDAOImpl();
        List expResult = null;
        List result = instance.seleccionarTurno( codigo, nombre, semestre );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
//        fail( "The test case is a prototype." );
    }

    /**
     * Test of actualizarTurno method, of class HSTurnoDAOImpl.
     */
    @Test
    public void testActualizarTurno() {
        System.out.println( "actualizarTurno" );
        AcTurno turno = null;
        HSTurnoDAOImpl instance = new HSTurnoDAOImpl();
        instance.actualizarTurno( turno );
        // TODO review the generated test code and remove the default call to fail.
//        fail( "The test case is a prototype." );
    }

    /**
     * Test of eliminarTurno method, of class HSTurnoDAOImpl.
     */
    @Test
    public void testEliminarTurno() {
        System.out.println( "eliminarTurno" );
        int id = 0;
        HSTurnoDAOImpl instance = new HSTurnoDAOImpl();
        instance.eliminarTurno( id );
        // TODO review the generated test code and remove the default call to fail.
//        fail( "The test case is a prototype." );
    }

    /**
     * Test of seleccionarTurno method, of class HSTurnoDAOImpl.
     */
    @Test
    public void testSeleccionarTurno_int() {
        System.out.println( "seleccionarTurno" );
        int semestre = 0;
        HSTurnoDAOImpl instance = new HSTurnoDAOImpl();
        List expResult = null;
        List result = instance.seleccionarTurno( semestre );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
//        fail( "The test case is a prototype." );
    }

    /**
     * Test of obtenerTurno method, of class HSTurnoDAOImpl.
     */
    @Test
    public void testObtenerTurno() {
        System.out.println( "obtenerTurno" );
        int id = 0;
        HSTurnoDAOImpl instance = new HSTurnoDAOImpl();
        AcTurno expResult = null;
        AcTurno result = instance.obtenerTurno( id );
        assertEquals( expResult, result );
        // TODO review the generated test code and remove the default call to fail.
//        fail( "The test case is a prototype." );
    }
}