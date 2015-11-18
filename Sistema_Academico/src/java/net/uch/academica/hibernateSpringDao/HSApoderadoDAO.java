/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcApoderado;
import net.uch.mapping.AcApoderadoAlumno;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author richard
 */
public interface HSApoderadoDAO {
    public List<AcApoderado> listarApoderados(String apo_dni, String apo_appaterno, String apo_apmaterno, String apo_nombres) throws DataAccessException;
    public AcApoderado datoApoderado(int apo_id) throws DataAccessException;
    public void agregarApoderado(AcApoderado acApoderado) throws DataAccessException;
    public void eliminarApoderado(int apo_id) throws DataAccessException;
    public void modificarApoderado(AcApoderado acApoderado) throws DataAccessException;
    public AcApoderado datoApoderadoxDni(String apo_dni) throws DataAccessException;

    public List<AcApoderadoAlumno > listarAlumnosCargo(int apo_id) throws DataAccessException;
    public void agregarApoderadoAlumno(AcApoderadoAlumno acApoderadoAlumno) throws DataAccessException;
}
