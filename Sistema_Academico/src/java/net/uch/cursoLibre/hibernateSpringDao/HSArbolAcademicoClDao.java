/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClSeccion;

/**
 *
 * @author Alumno
 */
public interface HSArbolAcademicoClDao {

    public List listarModulosxAlumno( int iAluId );

    public List listarSeccionesxAlumnoModulo( int iAluId, int iArbModId );

    public List<ClArbolAcademico> listarAreaxAnoYSede( int iAnio, int iSedeId, int iMes );

    public List<ClArbolAcademico> listarModulosxAreaAnoYSede( int iArea, int iAnio, int iSedeId );

    public List<ClArbolAcademico> listarCursosXModAnoYSede( int iModId, int iAnio, int iSedeId );

    public List<ClSeccion> listarSeccionesXCursoAnoYSede( int iCurId, int iAno, int iSedId );
    
    public List<ClSeccion> listarSeccionesXTallerAnoYSede( int iTallId, int iAno, int iSedId, Date fecha_ini, Date fecha_fin );

    public List<ClArbolAcademico> listarSeccionesXAnoYsede( int iAnio, int iSedeId );

    public ClArbolAcademico seleccionarArbol( int iArbId );
    
//    public List<ClArbolAcademico> listarArbolPorPadre( int iIdPadre, String sTipo );
    public List<ClArbolAcademico> listarArbolPorPadre( int iIdPadre );

    public List<ClArbolAcademico> listarArbCursosPorModSedeSedeAnioMes( int iArbModPadreId, int iArbCurId, int iSedeId, int iAnio, int iMes );

    public List<ClArbolAcademico> listarModulos();

    /*
     * Metodos Nuevos de arb_academico --PVS--
     */
    public List<ClArbolAcademico> seleccionarArea( String sDescripcion );

    public void insertarArea( ClArbolAcademico arbArea ) throws Exception;

    public List<ClArbolAcademico> obtenerUltimaArea() throws Exception;

    public ClArbolAcademico buscarArbolArea( int iArbId ) throws Exception;

    public void actualizarArea( ClArbolAcademico area ) throws Exception;

    public void eliminarArbol( int iArbId ) throws Exception;

    public List seleccionarModulos( int iAreId, String sDescripcion ) throws Exception;

    public List seleccionarModulosVisibles( int iAreId, String sDescripcion ) throws Exception;

    public List<ClArbolAcademico> seleccionarCursos( int iModId ) throws Exception;

    public List obtenerDescripcionArea( int iArbId ) throws Exception;

    public ClArbolAcademico buscarModulo( int iModId ) throws Exception;

    public void actualizarCurso( ClArbolAcademico curso ) throws Exception;

    public void eliminarModulo( int iModId ) throws Exception;

    public void insertarModulo( ClArbolAcademico arbMod ) throws Exception;

    public List<ClArbolAcademico> obtenerUltimoModulo( int areaPadre ) throws Exception;

    public void actualizarModulo( ClArbolAcademico arbMod ) throws Exception;

    public void insertarCurso( ClArbolAcademico arbMod ) throws Exception;

    public List<ClArbolAcademico> obtenerUltimoCurso( int iAreaPadre ) throws Exception;

    public List seleccionarTalleres( int cur_id ) throws Exception;

    public ClArbolAcademico buscarCurso( int iCurId ) throws Exception;

    public void actualizarTaller( ClArbolAcademico arbTaller ) throws Exception;

    public void insertarTaller( ClArbolAcademico arbTaller ) throws Exception;

    public List<ClArbolAcademico> obtenerUltimoTaller( int iCurPadre ) throws Exception;

    public List obtenerDescripcionCurso( int iArbId ) throws Exception;

    public void eliminarTaller( int iTallerId ) throws Exception;

    public List verificarEliminarTaller( int iTallerId ) throws Exception;

    public ClArbolAcademico buscarTaller( int iTallerId ) throws Exception;

    public ClArbolAcademico buscarArbolPorId( int iArbId ) throws Exception;

    public List<String> buscarXSeccion( int iSecId ) throws Exception;

    public void insertarListaArbol( List<ClArbolAcademico> lstArbol ) throws Exception;

    public List<ClArbolAcademico> verificarEliminarCurso( int cur_id ) throws Exception;

    public List<ClArbolAcademico> AreasXInstitucion( String inst );
}
