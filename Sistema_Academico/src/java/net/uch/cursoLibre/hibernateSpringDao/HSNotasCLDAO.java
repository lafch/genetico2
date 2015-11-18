/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClNota;

/**
 *
 * @author cesardl
 */
public interface HSNotasCLDAO {

    public List<ClNota> seleccionarNotas( int iSecId );

    public Double obtenerPormedio( Integer iSecId, int iAluId );

    public void insertarActualizarNotas( List<ClNota> lstClNotas );

    public List seleccionarNotaAlumnoSeccion( int iAluId, int iSecId, int iPerId );

    public void actualizarNota( int iSecId, int iAluId, int iSisPerId, double dNota );

    public void actualizarPromedio( int sec_id, int alu_id, double not_nota );

    public int recalcularPromedio( int sec_id );

    public List seleccionarNotasPorAlumnoModulo( int iAluId, List<Integer> lstArbCurIds );

    public List seleccionarNotasPorAlumnoModuloProgAux( int iAluId, List<Integer> lstArbCurIds );

    public ClNota notaxAlumno( int aluId, int sec_id );

    public List listarPromSisEvaDetPorSecIdYAluId( int iSecId, int iAluId, List<Integer> lstSisEvaDetIds );
}
