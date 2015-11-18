package net.uch.academica.hibernateSpringDao;

import java.sql.SQLException;
import java.util.List;
import net.uch.mapping.AcDisponibilidad;
import org.springframework.dao.DataAccessException;

public interface HSDisponibilidadDAO {

    public void insertarDisponibilidad(List<AcDisponibilidad> Dis) throws DataAccessException, SQLException;

    public void insertarDisponibilidad(net.uch.mapping.AcDisponibilidad dispo) throws DataAccessException, SQLException;

    public void actualizarDisponibilidad(net.uch.mapping.AcDisponibilidad dispo) throws DataAccessException, SQLException;

    public int verificar_disponibilidad(int doc_id, int turdet_id, String dis_dia) throws DataAccessException, SQLException;

    public List verificar_conf_dis() throws DataAccessException, SQLException;

    public int verificar_disponibilidad1(int doc_id, int turdet_id, String dis_d) throws DataAccessException, SQLException;

    public List get_iddis(int doc_id, int turdet_id, String dis_d) throws DataAccessException, SQLException;
}
