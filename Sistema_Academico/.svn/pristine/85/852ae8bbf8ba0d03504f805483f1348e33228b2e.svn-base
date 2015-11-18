/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.AcCalendario;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSActividadDAOImpl extends HibernateDaoSupport implements HSActividadDAO {

    @Override
    public void insertarActividad(AcCalendario calendario) throws DataAccessException {
        this.getHibernateTemplate().save(calendario);
    }

    @Override
    public void actualizarActividad(AcCalendario calendario) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdate(calendario);
    }

    @Override
    public List<AcCalendario> seleccionarActividadDetalle(Date calFecha)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria criteria = this.getSession().createCriteria(AcCalendario.class, "AcCalendario").
                createCriteria("AcCalendario.acCalendarioActividadeses", "AcCalendarioActividades").
                createCriteria("AcCalendarioActividades.acActividadAlcances", "AcActividadAlcance").
                add(Restrictions.eq("AcCalendario.calActivo", "1")).
                add(Restrictions.eq("AcCalendarioActividades.calactActivo", "1")).
                add(Restrictions.eq("AcActividadAlcance.actalcActivo", "1")).
                add(Restrictions.eq("AcCalendario.calFecha", calFecha)).
                addOrder(Order.asc("AcCalendarioActividades.calactId"));

        return criteria.list();
    }

    @Override
    public AcCalendario seleccionarActividadId(int id)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria criteria = this.getSession().createCriteria(AcCalendario.class, "AcCalendario").
                createCriteria("AcCalendario.acCalendarioActividadeses", "AcCalendarioActividades").
                createCriteria("AcCalendarioActividades.acActividadAlcances", "AcActividadAlcance").
                add(Restrictions.eq("AcCalendario.calActivo", "1")).
                add(Restrictions.eq("AcCalendarioActividades.calactActivo", "1")).
                add(Restrictions.eq("AcActividadAlcance.actalcActivo", "1")).
                add(Restrictions.eq("AcCalendarioActividades.calactId", id));

        return (AcCalendario) criteria.uniqueResult();
    }

    @Override
    public void actualizarEstadoPublicado(int id, String publicado)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {
        String hqlUpdate = "update AcCalendarioActividades set calactPublicado = :v_publicado where calactId = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setString("v_publicado", publicado).
                setInteger("v_id", id).executeUpdate();
    }

    @Override
    public void eliminarActividad(int id)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        String hqlUpdate = "update AcCalendarioActividades set calactActivo = :v_activo where calactId = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setString("v_activo", "0").
                setInteger("v_id", id).executeUpdate();
    }

    @Override
    public void eliminarAlcance(int id)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        String hqlUpdate = "update AcActividadAlcance set actalcActivo = :v_activo where actalcId = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setString("v_activo", "0").
                setInteger("v_id", id).executeUpdate();
    }
}
