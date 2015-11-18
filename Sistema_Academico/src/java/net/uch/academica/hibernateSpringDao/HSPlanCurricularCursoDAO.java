package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcPlanCurso;
import org.springframework.dao.DataAccessException;

public interface HSPlanCurricularCursoDAO {

    public void insertarPlanCurricularCurso(AcPlanCurso pc) throws DataAccessException;

    public List seleccionarPlanCurricularCurso(int plan) throws DataAccessException, java.sql.SQLException;

    public void actualizarPlanCurricularCurso(AcPlanCurso pc) throws DataAccessException;

    public void eliminarPlanCurricularCurso(String id) throws DataAccessException;

    public List verificarRepitencia(int plan, int curso, String ciclo) throws DataAccessException;

    public List seleccionarPlancurricularCurso(String plan) throws DataAccessException;

    public List seleccionarCursos(String plan, String ciclo) throws DataAccessException;

    public List seleccionarPlanCurricularCurso(int plan, String ciclo_id) throws DataAccessException, java.sql.SQLException;
}
