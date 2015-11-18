/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.logicreport.AlumnoHistorial;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.util.ObjUpdate;

/**
 *
 * @author cesardl
 */
public interface HSAlumnoTarifaCLDAO {

    public void generarAlumnoTarifa( ClAlumnoTarifa alumnoTarifa ) throws Exception;

    public void generarAlumnosTarifa( List<ClAlumnoTarifa> alumnosTarifa );

    public void actualizarDatosAlumnoTarifa( int alutarID, float monto, Date fecha_pago, Date fecha_prorroga );

    public void eliminarAlumnoTarifa( int alutar_id ) throws Exception;

    public void eliminarAlumnosTarifa( List<Integer> alutarIds );

    public List<ClAlumnoTarifa> seleccionarTarifasXMatricula( int mat_id, int alu_id ) throws Exception;
    
    public List<ClAlumnoTarifa> seleccionarTarifasXMatriculaAlumno( int mat_id, int alu_id ) throws Exception;

    public int actualizarEstructurasPagoBloque( int sec, List<ObjUpdate> restricciones, List<ObjUpdate> valores );

    public boolean existenPagosMatricula( int alu_id, int alutar_id ) throws Exception;

    public void actualizarFechaProrroga( Date fecha_pro, String fecha_observacion, int alutar_id );
    
    //public void actualizarAlumnoTarifa( Date fecha_pago, Date fecha_pro,Float monto, int alutar_id );
    public void actualizarAlumnoTarifa( Date fecha_pago, Date fecha_pro,Float monto, int alutar_id,String matTipo );

    // public void agregarMora(int alu_id, int sec_id, float mora);
    public List<ClAlumnoTarifa> alumnoMonto( int alu_id, int mat_id );

    public void aumentarMora( int alutar_id, float mora );

    public void EditarMonto( int alu_id, int mat_id, float mora );

    public void cambiarSeccionAlumnoTarifa( int mat_id, int sec_id );
    //public

    public void cerrarPago( int iMatId );
    
    public void HabilitarMatricula( int iMatId );

    public List<ClAlumnoTarifa> traerListaPagosPorCerrar( int iMatId );
    
    public List<ClAlumnoTarifa> traerListaPagosPorHabilitar( int iMatId );

    public List<AlumnoHistorial> listarMontosPagadosPorSecId( int iSecId );
    
    public String usuarioMatricula(int iUsuarioId);
}
