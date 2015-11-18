package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcAlumnoDato;
import net.uch.mapping.AcAlumnoDocumento;
import org.springframework.dao.DataAccessException;

public interface HSAlumnoDatoDAO {

    public void insertarAlumnoDato(AcAlumnoDato alu) throws DataAccessException;

    public List seleccionarAlumnoDato(int alu_id) throws DataAccessException;

    public void actualizarAlumnoDato(AcAlumnoDato aludat);

    public void eliminarDocumentos(int b_id_alumno) throws Exception;

    public void insertarDocumentos(AcAlumnoDocumento aluDoc) throws Exception;

    public List seleccionarDocumentos(int alu_id);
}
