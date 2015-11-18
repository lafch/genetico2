/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSisEvaDetalle;
import net.uch.mapping.ClSisEvaPersonalizado;
import net.uch.mapping.ClSisEvaluacion;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author cesardl
 */
public interface HSSistemaEvaluacionCLDAO {

    public ClSisEvaPersonalizado seleccionarSisEvaPerXSecId( int iSecId );

    public ClSisEvaPersonalizado seleccionarSisEvaPersonalizadoPorId( int iSisEvaPerId );

    public List<ClSisEvaDetalle> seleccionarSisEvaDet_ClSisEva( int siseva_id );
    public ClSisEvaDetalle seleccionarSisEvaDetetallePorId( int iSisEvaDetId );

    public List<ClSisEvaPersonalizado> seleccionarSisEvaPer_ClTalape( ClArbolAperturado talape );

    public void insertarActualizarSistemaEvaluacion( ClSisEvaluacion siseva );

    public ClSisEvaluacion seleccionarSistemaEvaluacion( int sisevaId );

    public void eliminarSistemasEvaluacionDetalle( List<Integer> sisevaIds );

    public List<ClSisEvaluacion> seleccionarSistemasEvaluacion();

    public List<ClSisEvaluacion> seleccionarSistemasEvaluacion( String nombre );

    public void insertarActualizar_SisEvaPersonalizado( List<ClSisEvaPersonalizado> lPer ) throws DataAccessException;

    public void eliminarSistemaEvaluacionPersonalizado( int id ) throws DataAccessException;

    public List listarSisEvaDetallePorSecId(int iSecId);
    
    public List listarSisEvaDetallePorSecAsig(int iSecId);
    
    public List listarSistemaEvaluacionPorCurso( int arb_id );

    public List listarSistemaEvaPersonPorSeccionYDet( int iSecId, int iIdAsignatura );

    public List<ClSisEvaPersonalizado> listarSisEvaPerPlantilla( int iSisevaId, int iSisevaDetId );

    public int eliminarSistemaEvaluacionPerPlant( int iIdSisEvaPerPlant );

    public int eliminarSistemaEvaluacion( int iIdSisEva );
    
}
