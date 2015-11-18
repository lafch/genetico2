package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcParametro;
import org.springframework.dao.DataAccessException;

public interface HSParametroDAO {

    public void insertarParametro( AcParametro par ) throws DataAccessException;

    public List seleccionarParametro( String codigo, String valor ) throws DataAccessException, java.sql.SQLException;

    public void eliminarParametro( int id ) throws DataAccessException;

    public void actualizarParametro( AcParametro par ) throws DataAccessException;
    
    public AcParametro buscarParametro( String par_cod );

}
