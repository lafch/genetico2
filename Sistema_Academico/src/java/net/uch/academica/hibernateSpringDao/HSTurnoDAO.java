package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcTurno;
import org.springframework.dao.DataAccessException;

public interface HSTurnoDAO {

    public void insertarTurno(AcTurno turno) throws DataAccessException;

    public List seleccionarTurno(String codigo, String nombre, int semestre) throws DataAccessException;

    public void actualizarTurno(AcTurno turno) throws DataAccessException;

    public void eliminarTurno(int id) throws DataAccessException;

    public List seleccionarTurno(int semestre) throws DataAccessException;

    public AcTurno obtenerTurno(int id) throws DataAccessException;
}
