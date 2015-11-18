package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcTurno;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSTurnoDAOImpl extends HibernateDaoSupport implements HSTurnoDAO {

    @Override
    public void insertarTurno(AcTurno turno) throws DataAccessException {
        getHibernateTemplate().save(turno);
    }

    @Override
    public List seleccionarTurno(String codigo, String nombre, int semestre) throws DataAccessException {
        String query = "select Id,TurCodigo,TurNombre,TurPeriodo,TurHoraInicio,TurVigente,SemId,TurTiempoPeriodo from AcTurno where TurCodigo like :codigo and TurNombre like :nombre and TurActivo= :activo ";
        if (semestre == 0) {
            return this.getSession().createQuery(query).setString("codigo", "%" + codigo + "%").setString("nombre", "%" + nombre + "%").setString("activo", "1").list();
        } else {
            query += "and SemId= :semestre";
            return this.getSession().createQuery(query).setString("codigo", "%" + codigo + "%").setString("nombre", "%" + nombre + "%").setString("activo", "1").setString("semestre", "" + semestre).list();
        }
    }

    @Override
    public void actualizarTurno(AcTurno turno) throws DataAccessException {
        getHibernateTemplate().update(turno);
    }

    @Override
    public void eliminarTurno(int id) throws DataAccessException {
        String query = "update AcTurno set TurActivo=:activo where Id= :id";
        this.getSession().createQuery(query).setString("id", "" + id).setString("activo", "0").executeUpdate();
    }

    @Override
    public List seleccionarTurno(int semestre) throws DataAccessException {
        return this.getSession().createCriteria(AcTurno.class).add(Restrictions.eq("SemId", semestre)).add(Restrictions.eq("TurActivo", "1")).list();
    }

    @Override
    public AcTurno obtenerTurno(int id) throws DataAccessException {
        return (AcTurno) this.getSession().createCriteria(AcTurno.class).
                add(Restrictions.eq("Id", id)).
                add(Restrictions.eq("TurActivo", "1")).uniqueResult();
    }
}
