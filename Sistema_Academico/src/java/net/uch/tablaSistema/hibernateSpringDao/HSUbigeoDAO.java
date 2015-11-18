package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import org.springframework.dao.DataAccessException;

public interface HSUbigeoDAO {

    public List seleccionarDepartamento() throws DataAccessException, java.sql.SQLException;

    public List seleccionarProvincia(String codigoDepartamento) throws DataAccessException, java.sql.SQLException;

    public List seleccionarDistrito(String codigoDepartamento, String codigoProvincia) throws DataAccessException, java.sql.SQLException;

    public String seleccionarDescripcion(String codigo) throws Exception;
}
