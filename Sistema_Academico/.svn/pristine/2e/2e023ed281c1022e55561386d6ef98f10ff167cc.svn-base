package net.uch.academica.hibernateSpringDao;

import java.io.InputStream;
import java.util.List;
import net.uch.mapping.AcNota;
import org.springframework.dao.DataAccessException;

public interface HSNotaDAO {

    public void insertarNotas( List<AcNota> notas ) throws DataAccessException;

    public List seleccionarNotas( int seccion ) throws DataAccessException;

    public InputStream imprimirTodo( String seccion ) throws DataAccessException;

    public List listarNotaxAlumno( int alu_id, int sec_id, int siseva_per_id );

    public List listarNotaAlumno( int alu_id, int sec_id ) throws DataAccessException;

    
}
