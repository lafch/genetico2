/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClPublicoAlumno;
import net.uch.mapping.ClPublicoConsulta;
import net.uch.mapping.Sp_listarPublicoAlumno;

/**
 *
 * @author richard
 */
public interface HSPublicoAlumnoCLDAO {

    public List<ClPublicoAlumno> listarPublicoAlumno( String paterno );

    public ClPublicoAlumno datoPublicoAlumno( int idAlumno );

    public List<ClPublicoConsulta> listarConsultaPorAlumno( int idAlumno );

    public void agregarPublicoAlumno( ClPublicoAlumno publico );

    public void modificarPublicoAlumno( ClPublicoAlumno publico );

    public List<Sp_listarPublicoAlumno> listarALumnosPorDato( String dato );
}
