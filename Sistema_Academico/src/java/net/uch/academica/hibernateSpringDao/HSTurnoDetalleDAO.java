package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcTurnoDetalle;
import org.springframework.dao.DataAccessException;

public interface HSTurnoDetalleDAO {

    public void insertarTurnoDetalle(AcTurnoDetalle turno_detalle) throws DataAccessException;

    public List seleccionarTurnoDetalle(int id_turno) throws DataAccessException;

    public void actualizarTurnoDetalle(AcTurnoDetalle turno_detalle) throws DataAccessException;

    public void eliminarTurnoDetalle(int id) throws DataAccessException;
    
    public AcTurnoDetalle buscarAcTurnoDetalle( int turDetId ) throws DataAccessException;
}
