package net.uch.administrativa.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AdClientes;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.criterion.Order;

public class HSClienteDAOImpl extends HibernateDaoSupport implements HSClienteDAO {

    @Override
    public void insertarCliente(AdClientes cli) throws DataAccessException {
        getHibernateTemplate().save(cli);
    }

    @Override
    public List seleccionarCliente(String buscar) throws DataAccessException {
        return this.getSession().createCriteria(AdClientes.class).
                add(Restrictions.eq("CliActivo", "1")).
                add(Restrictions.like("CliRazonSocial", "%" + buscar + "%")).
                addOrder(Order.asc("Id")).list();
    }

    @Override
    public int seleccionarUnCliente(String ruc) throws DataAccessException {
        int x = 0;
        DetachedCriteria criteria = DetachedCriteria.forClass(AdClientes.class);
        criteria.add(Expression.eq("CliActivo", "1"));
        criteria.add(Expression.eq("CliRazonSocial", ruc));
        criteria.addOrder(Order.asc("Id"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        if (lista.size() != 0) {
            x = ((AdClientes) lista.get(0)).getId();
        }
        return x;
    }

    @Override
    public List seleccionarUnCliente(int id) throws DataAccessException {
        return this.getSession().createCriteria(AdClientes.class).
                add(Restrictions.eq("CliActivo", "1")).
                add(Restrictions.eq("Id", id)).
                addOrder(Order.asc("Id")).list();
    }

    @Override
    public void actualizarCliente(AdClientes cli)
            throws DataAccessException {
        getHibernateTemplate().update(cli);
    }
}

