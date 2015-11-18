/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.*;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author cesardl
 */
public interface HSAlumnoCLDAO {

    public void insertarAlumnocl( ClAlumno alu ) throws DataAccessException;

    public void modificarAlumnocl( ClAlumno alu ) throws DataAccessException;

    public void eliminarAlumnocl( String alu_id ) throws DataAccessException;

    public List<ClAlumno> seleccionarAlumnosLibres( String codigo, String paterno, String materno, String nombre ) throws DataAccessException, java.sql.SQLException;

    public String maximoCodigo() throws DataAccessException;

    public ClAlumno buscarAlumnoPorAluId( Integer alu_id ) throws DataAccessException;

    public TbDistrito ObtenerDistrito( String dis );

    public List<ClAlumno> seleccionarAlumnosPorCodigo( String codigo ) throws Exception;

    public List<ClAlumno> seleccionarAlumnosPorApellidos( String apellidos ) throws Exception;

    public List listarAlumnos_x_Seccion( int sec_id ) throws Exception;

    public List<ClAlumno> listaAlumnos_Datos( String code, String paterno, String materno, String nombres, int mod_id );

    public List<ClAlumnoTarifa> listaAlumnoTarifa( int alu_id, int mod_id );

    public boolean existeAlumnoUnivRegistrado( Integer id );

    public Integer maximoAluId();

    public List<AdPago> listaPagosAlumno( int alutarId );

    public List<ClAlumnoTarifa> consultDeudaALumno( int alu_id );
    
    public AdComprobantePago consultFechaMatricula( int mat_id );

    public List listarAlumnosPorSeccion( int sec_id );

    public List listarAlumnosPorModulo( List<Integer> lstArbCurIds, int iAnio, int iMes, int iSedeId );
    //public List<ClAlumno> alumnosListaXmodulo(String code, String paterno, String materno, String nombres, int mod_id);

    public List<BeanReporte> listarCursosLlevados( int iAluId );

    public List<BeanReporte> listarReporteAsistencias( String sCodAlumno, Date fechaInicio, Date fechaFin );

    public List<ClObservacionDesercion> listarLlamadas( int iAluId, int iSecId );

}
