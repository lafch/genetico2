/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanCLPublico;
import net.uch.cursoLibre.managedBeans.beans.BeanInfoMatricula;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.ClConsultaPublico;
import net.uch.mapping.ClMedioPublicidadDet;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Simion Richa R B
 */
public interface HSConsultaPublicoDAO {

    public void agregarConsultaPublico( ClConsultaPublico consultaPublico );

    public List<ClConsultaPublico> listarxAlumnoConsultaPublico( int alu_id );

    public void modificarCampoMatricula( int con_id );

    public void modificarCampoMatricula( int con_id, int mat_id );

    public List<ClConsultaPublico> listarPublicoConsulta( int iCateg, String sCentroId, int iEspId, int are_id, int mod_id, int cur_id, int hor_id, int usu_id, Date fecha_ini,
            Date fecha_fin, String con_procedencia, String mat_tipo, String sPrioridadId, int tipo, int iMedioId, int iMedioDetId );

    public List<BeanReporte> listarReporteFechaContacto( int iCateg, String sCentroId, int iEspId, int are_id, int mod_id, int cur_id, int hor_id, int usu_id, Date fecha_ini,
            Date fecha_fin, String con_procedencia, String mat_tipo, String sPrioridadId, int tipo, int iMedioId, int iMedioDetId );

    public void modificarConsultaPublico( int con_id, String con_observacion, Date con_fecha_contacto );

    public List<BeanInfoMatricula> cantidadMatriculadosUsuario( Date fecha_ini, Date fecha_fin, String sCentroId, int iAreaId, int iModId, int iCurId );

    public List<ClConsultaPublico> listadoInscritosPorUsuario( int usu_id, Date fechaIni, Date fechaFin, String tipo, int iAreaId, int iModId, int iCurId, String sCentroId );

    public ClConsultaPublico seleccionarConsultaPublico( int con_id );

    public List<ClConsultaPublico> seleccionarConsultaPublicoporFecha( Date fecha, int alu_id );

    public List<ClConsultaPublico> listarPublicoConsultaFechaRegistro( int loc_id, int are_id, int mat_id, int usu_id,
            String distrito, String procedencia, Date fecha_ini, Date fecha_fin );

    public ClMedioPublicidadDet seleccionarMedioPublicidad( int medIddet );

    public void delMatriculaConsulta( int mat_id ) throws Exception;

    public void eliminarConsultacl( String cons_id ) throws DataAccessException;
    
    public void eliminarMatriculacl( int mat_id ) throws DataAccessException;
    
    public List<BeanCLPublico> lstMatUsu( int usu_id, Date fechaIni, Date fechaFin, String tipo, int iAreaId, int iModId, int iCurId, String sCentroId );
    
    public List<BeanCLPublico> lstInfUsu( int usu_id, Date fechaIni, Date fechaFin, String tipo, int iAreaId, int iModId, int iCurId, String sCentroId );
    
}