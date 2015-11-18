package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcPlanCurso;
import net.uch.mapping.AcPreRequisitos;
import org.springframework.dao.DataAccessException;

public interface HSPreRequisitosDAO {

    public void insertarPreRequisitos(AcPreRequisitos alu) throws DataAccessException;

    public void eliminarPreRequisitos(String id) throws DataAccessException;

    public void actualizarPreRequisitos(AcPreRequisitos alu) throws DataAccessException;

    public List seleccionarCiclo(String codigo, int valor) throws DataAccessException, java.sql.SQLException;

    public List seleccionarCurso(int plan, String ciclo) throws DataAccessException, java.sql.SQLException;

    public List seleccionarPreReq(int curso) throws DataAccessException, java.sql.SQLException;

    public List verificarRepitencia(int curso, int req);

    public List cursoDet(int plan) throws DataAccessException, java.sql.SQLException;

    public List<AcPlanCurso> retonarCursosAbiertos(List<Integer> aprobados, List<Integer> desaprobados) throws DataAccessException;

    public List<AcPlanCurso> retonarCursosLibres(List<Integer> list, int esp) throws DataAccessException;
}

