/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSeccionGrupo;

/**
 *
 * @author cesardl
 */
public interface HSSeccionCLDAO {

    public ClSeccion seleccionarSeccion( Integer sec_id );

    public List<ClSeccion> seleccionarSeccionesxTaller( int talid );

    public List<ClSeccion> seleccionarSecciones( int talape_id );
    
    public List<ClSeccion> seleccionarAsignaturas(Integer sec_id);

    public ClSeccion obtenerSeccion( int matId, int talId );

    public List<ClSeccion> listarSeccionXTaller( int tal_id );

    public List<ClSeccion> listarSeccionesXModulo( int arbId );

    public void insertarSeccion( ClSeccion sec );

    public void actualizarSeccion( ClSeccion sec );

    public void eliminarSeccion( int sec_id );

    public boolean existenMatriculasSeccion( int sec_id );

    public List listarSeccionesModulo( int arb_id );

    public List listarcantidadadMatriculadosxSeccion( int arb_id, int cur_id, int anio, int mes );

    public List<ClSeccion> listarSeccionesPorInicio( int ini_id );

    public void guardarSecciones( List<ClSeccion> secciones );

    public List<ClSeccion> listarTodasSeccionesXCurso( int arbId );

    public List<ClSeccion> listarSeccionesXCurso( int arbId );
    
    public List listarSeccionesXDocente(int docId);

    public List traerSeccionesValidasPorModuloId( int iArbModId, int iAluId, int iArbCurId );

    public int traerAreaxSeccionId( int secId );

    //-----------------------------------------------------
    public List<ClSeccionGrupo> listarSeccionGrupos( String sNomGrup );

    public ClSeccionGrupo traerClSeccionGrupo( ClSeccion clSeccion );

    public ClSeccionGrupo traerClSeccionGrupoXId( int iIdSecGrup );

    public void actualizarSeccionGrupo( ClSeccionGrupo clSecGrup );

    public void insertarSeccionGrupo( ClSeccionGrupo clSecGrupo );

    public ClSeccionGrupo traerClSeccionGrupoXDescripcion( String sDescSecGrup );

    public void registrarSeccionesPrecedentes( ClSeccion secTmp, List<ClSeccion> lstSeccionesPrecedentes );

    public List<ClSeccion> listarSeccionesPrecedentes( int iSecId );
    
    public void actualizarFechaProrroga( Date fecha_pro, String fecha_observacion, int alutar_id );
}
