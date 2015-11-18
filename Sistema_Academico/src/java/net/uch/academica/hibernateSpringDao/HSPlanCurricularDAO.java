package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcPlancurricular;
import org.springframework.dao.DataAccessException;

public interface HSPlanCurricularDAO {

    public void insertarPlanCurricular(AcPlancurricular pc) throws DataAccessException;

    public List seleccionarPlanCurricular(String codigo, String nombre, int facultad, int especialidad) throws DataAccessException, java.sql.SQLException;

    public void actualizarPlanCurricular(AcPlancurricular pc) throws DataAccessException;

    public void eliminarPlanCurricular(String id) throws DataAccessException;

    public List seleccionarPlanActivo(int esp) throws DataAccessException, java.sql.SQLException;

    public List seleccionarPlanCurricular(int especialidad);
}
