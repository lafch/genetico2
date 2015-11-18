package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcCurso;
import org.springframework.dao.DataAccessException;

public interface HSCursoDAO {

    public void insertarCurso(AcCurso cur) throws DataAccessException;

    public List seleccionarCurso(String codigo, String nombre, int facultad, int especilidad) throws DataAccessException, java.sql.SQLException;

    public List seleccionarCurso(int especilidad) throws DataAccessException, java.sql.SQLException;
    
    public AcCurso seleccionarCursoID( int id_curso );
    
    public List listadoCurso(int plan, String ciclo);
    
    public List listadoCursoAsignado(int plan, String ciclo);

    public void eliminarCurso(String id) throws DataAccessException;

    public void actualizarCurso(AcCurso cur) throws DataAccessException;
}
