package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcLocal;
import org.springframework.dao.DataAccessException;

public interface HSLocalDAO {

    public void insertarLocal(AcLocal local) throws DataAccessException;

    public List seleccionarLocal(String nombre) throws DataAccessException, java.sql.SQLException;

    public void eliminarLocal(String id) throws DataAccessException;

    public void actualizarLocal(AcLocal local) throws DataAccessException;
    public AcLocal seleccionarLocal(int loc_id)throws DataAccessException;
}
