package net.uch.administrativa.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdEstructuraPagos;
import net.uch.mapping.AdEstructuraPagosDetalle;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSEstructuraPagoDAOImpl extends HibernateDaoSupport implements HSEstructuraPagoDAO {

    @Override
    public void insertarEstructuraPagos(AdEstructuraPagos estpag) {
        try {
            getHibernateTemplate().save(estpag);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertarAlumnosTarifa(AdAlumnoTarifa aluTa) throws DataAccessException {
        getHibernateTemplate().save(aluTa);
    }

    @Override
    public List seleccionarEstructuraPagos(int esp_id, int sem) throws DataAccessException {
        DetachedCriteria criteria = DetachedCriteria.forClass(AdEstructuraPagos.class);
        if (esp_id != 0 && sem != 0) {
            criteria.add(Expression.eq("Esp.Id", esp_id));
            criteria.add(Expression.eq("Sem.Id", sem));
        } else {
            if (esp_id != 0) {
                criteria.add(Expression.eq("Esp.Id", esp_id));
            }
            if (sem != 0) {
                criteria.add(Expression.eq("Sem.Id", sem));
            }
        }
        criteria.add(Expression.eq("EstpagActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public List seleccionarUnaEstructuraPagos(int id) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        List lista = session.createCriteria(AdEstructuraPagos.class).
                add(Expression.eq("EstpagActivo", "1")).
                add(Expression.eq("Id", id)).list();
        session.close();
        return lista;
    }

    @Override
    public void actualizarEstructuraPagos(AdEstructuraPagos siseva) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate(siseva);
    }

    @Override
    public void eliminarEstructuraPagos(int id) throws DataAccessException {
        String query = "update AdEstructuraPagos set EstpagActivo=:activo where Id= :id";
        this.getSession().createQuery(query).
                setString("id", "" + id).
                setString("activo", "0").executeUpdate();
    }

    @Override
    public List seleccionarUnaEstructuraPagosDet(int id) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        List lista = session.createCriteria(AdEstructuraPagosDetalle.class).
                add(Expression.eq("EstpagdetActivo", "1")).
                add(Expression.eq("Id", id)).list();
        session.close();
        return lista;
    }

    @Override
    public List seleccionarEstructuraPagosDet() throws DataAccessException {
        return this.getSession().createCriteria(AdEstructuraPagosDetalle.class).
                add(Expression.eq("EstpagdetActivo", "1")).
                addOrder(Order.asc("EstpagdetNombre")).list();
    }

    @Override
    public List seleccionarEstructuraPagosEspSem(int esp_id, int sem_id) throws DataAccessException {
        DetachedCriteria dc = DetachedCriteria.forClass(AdEstructuraPagos.class);
        dc.add(Expression.eq("Esp.Id", esp_id));
        dc.add(Expression.eq("Sem.Id", sem_id));
        dc.add(Expression.eq("EstpagActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(dc);
        return lista;
    }
}
