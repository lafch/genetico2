package net.uch.administrativa.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AdConceptoPago;
import org.springframework.dao.DataAccessException;

public interface HSConceptoPagoDAO {

    public void insertarConceptoPago(AdConceptoPago conPago) throws DataAccessException;

    public List seleccionarConceptoPago(String nombre, String codigo, String tipo) throws DataAccessException, java.sql.SQLException;

    public void eliminarConceptoPago(String id) throws DataAccessException;

    public void actualizarConceptoPago(AdConceptoPago conPago) throws DataAccessException;

    public List seleccionarConceptoPago();

    public List seleccionarUnaConceptoPago(int conPag_id) throws DataAccessException, java.sql.SQLException;

    public List seleccionarConceptoPagoGeneral();

    public List seleccionarConceptoCursoLibre(String descripcion);
    public List<AdConceptoPago> getListarConceptoRubro(String conpagRubro);
}
