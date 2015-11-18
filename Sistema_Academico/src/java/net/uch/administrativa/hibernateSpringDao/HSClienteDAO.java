package net.uch.administrativa.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AdClientes;
import org.springframework.dao.DataAccessException;

public interface HSClienteDAO {

    public void insertarCliente(AdClientes cli) throws DataAccessException;

    public List seleccionarCliente(String buscar) throws DataAccessException, java.sql.SQLException;

    public void actualizarCliente(AdClientes alu) throws DataAccessException;

    public int seleccionarUnCliente(String ruc) throws DataAccessException;

    public List seleccionarUnCliente(int id) throws DataAccessException;
}
