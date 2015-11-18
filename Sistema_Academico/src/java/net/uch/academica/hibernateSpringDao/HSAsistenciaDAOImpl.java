package net.uch.academica.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.TbAsistencia;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSAsistenciaDAOImpl extends HibernateDaoSupport implements HSAsistenciaDAO {

    @Override
    public void insertarAsistencia(TbAsistencia asistencia) throws DataAccessException {
    }

    @Override
    public List seleccionarAsistencia(String codigo, Date desde, Date hasta) throws DataAccessException {
        return this.getSession().createCriteria(TbAsistencia.class).add(Restrictions.eq("AsisCodigo", codigo)).add(Restrictions.between("AsisDia", desde, hasta)).list();
    }

    @Override
    public void actualizarAsistencia(TbAsistencia asistencia) throws DataAccessException {
    }

    @Override
    public void eliminarAsistencia(int id) throws DataAccessException {
    }
}
