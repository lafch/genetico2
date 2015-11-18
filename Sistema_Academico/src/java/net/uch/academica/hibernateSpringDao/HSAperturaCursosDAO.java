package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcCursoAperturado;
import org.springframework.dao.DataAccessException;

public interface HSAperturaCursosDAO {

    public void aperturarCurso(AcCursoAperturado cursoAperturado) throws DataAccessException;

    public List seleccionarCursosAperturados() throws DataAccessException, java.sql.SQLException;

    public void eliminarCursoAperturado(String id) throws DataAccessException;

    public void actualizarCursoAperturado(AcCursoAperturado cursoAperturado) throws DataAccessException;

    public List verificarCursoAperturado(int plancur_id, int sem_id);

    public List seleccionarUnCursosAperturado(int id_curape) throws DataAccessException;

    public List seleccionarAperturaporPlanCurso(int plancurid) throws DataAccessException;

    public List seleccionarAperturaporEspecialidad(int esp_id, int sem_id) throws DataAccessException;
    
    public AcCursoAperturado seleccionarCursoAperturadoID( int id_curso );
}
