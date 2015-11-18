package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcEspecialidad;
import org.springframework.dao.DataAccessException;

public interface HSEspecialidadDAO {

    public void insertarEspecialidad(AcEspecialidad es) throws DataAccessException;

    public List seleccionarEspecialidad(String codigo, String nombre, int facultad);

    public List seleccionarEspecialidad(int id_fac) throws DataAccessException, java.sql.SQLException;

    public List seleccionarUnaEspecialidad(int id_esp) throws DataAccessException, java.sql.SQLException;

    public void eliminarEspecialidad(String id) throws DataAccessException;

    public void actualizarEspecialidad(AcEspecialidad esp) throws DataAccessException;

    public List seleccionarEspecialidad();
    
    public List seleccionarEspecialidadInformes(int esp_id);
}
