package net.uch.administrativa.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdEstructuraPagos;
import org.springframework.dao.DataAccessException;

public interface HSEstructuraPagoDAO {

    public void insertarEstructuraPagos(AdEstructuraPagos siseva);

    public List seleccionarEstructuraPagos(int esp_id, int sem) throws DataAccessException;

    public List seleccionarUnaEstructuraPagos(int id) throws DataAccessException;

    public void actualizarEstructuraPagos(AdEstructuraPagos siseva) throws DataAccessException;

    public void eliminarEstructuraPagos(int id) throws DataAccessException;

    public void insertarAlumnosTarifa(AdAlumnoTarifa aluTa) throws DataAccessException;

    public List seleccionarUnaEstructuraPagosDet(int id) throws DataAccessException;

    public List seleccionarEstructuraPagosDet() throws DataAccessException;

    public List seleccionarEstructuraPagosEspSem(int esp_id, int sem_id) throws DataAccessException;
}
