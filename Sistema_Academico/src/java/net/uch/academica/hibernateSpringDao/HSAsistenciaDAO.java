package net.uch.academica.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.TbAsistencia;
import org.springframework.dao.DataAccessException;

public interface HSAsistenciaDAO {

    public void insertarAsistencia(TbAsistencia asistencia) throws DataAccessException;

    public List seleccionarAsistencia(String codigo, Date desde, Date hasta) throws DataAccessException;

    public void actualizarAsistencia(TbAsistencia asistencia) throws DataAccessException;

    public void eliminarAsistencia(int id) throws DataAccessException;
}
