package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcPabellon;
import org.springframework.dao.DataAccessException;

public interface HSPabellonDAO {

    public void insertarPabellon(AcPabellon pabellon) throws DataAccessException;

    public List seleccionarPabellon(String nombre, int facultad) throws DataAccessException, java.sql.SQLException;

    public void eliminarPabellon(String id) throws DataAccessException;

    public void actualizarPabellon(AcPabellon pabellon) throws DataAccessException;
}
