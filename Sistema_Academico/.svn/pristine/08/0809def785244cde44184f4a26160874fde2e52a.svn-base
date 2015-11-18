/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcSisEvaPersonalizado;

/**
 *
 * @author LUIS
 */
public interface HSAlumnoNotaDAO { 
    
    public List<AcAlumno> listarAlumnosPorCursoSisEvaPer( int sec_id, List<Integer> lstSisEvaPer, String etapa ) throws Exception;
    public List<Integer> listarSisEvaPerPorEtapa( String sEtapaEvaluacion, int iSecId ) throws Exception;
    public List<AcSisEvaPersonalizado> listarSisEvaPorSeccion( int sec_id, String sEtapaEvaluacion ) throws Exception;
    public int grabarNotasNuevo( List<AcAlumno> lstAlumnos, List<List<String>> lstAlumnosNotas, int iSecId, List<AcSisEvaPersonalizado> lstSisPer, int doc_id ) throws Exception;
    public int recalcularPromedio( List<AcAlumno> lstAlumnos, int iSecId, List<AcSisEvaPersonalizado> lstSisPer, int doc_id ) throws Exception;
    public void actualizarPromedio( int iSecId, int iAluId, double dNota, String etapa, int doc_id, int sSisEvaProm ) throws Exception;
    public int obtenerIdNotaPromedio( int iSecId, int iAluId, int iSisEvaPerId ) throws Exception;
    public void calcularPromedioConsutitutorio( int sec_id ) throws Exception;
    public void generarPromedioN( int sec_id );
}
