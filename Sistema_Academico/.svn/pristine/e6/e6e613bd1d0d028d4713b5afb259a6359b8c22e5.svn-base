package net.uch.academica.hibernateSpringDao;

import java.sql.SQLException;
import java.util.List;
import net.uch.mapping.AcContenidoDetalle;
import net.uch.mapping.AcContenidoTematico;
import org.springframework.dao.DataAccessException;

public interface HSContenidoTematicoDAO {

    public void insertarContenidoTematico(AcContenidoTematico Con) throws DataAccessException, SQLException;

    public List listar_detalle(int contem_id) throws SQLException, Exception;

    public List get_contem_id(int plancur_id) throws SQLException, Exception;

    public List seleccionarContenidoTematico(int contem_id) throws SQLException, Exception;

    public List seleccionarDatosCT(int plncur_id) throws SQLException, Exception;

    public void eliminarContenidoTematicoDetalle(List<AcContenidoDetalle> det) throws DataAccessException, SQLException;
}
