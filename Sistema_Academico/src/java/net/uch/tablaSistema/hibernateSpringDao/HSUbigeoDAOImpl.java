package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbDistrito;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSUbigeoDAOImpl extends HibernateDaoSupport implements HSUbigeoDAO {

    @Override
    public List seleccionarDepartamento() throws DataAccessException, java.sql.SQLException {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbDistrito.class);
        criteria.add(Expression.like("Id", "%__0000%"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public List seleccionarProvincia(String codigoDepartamento) throws DataAccessException, java.sql.SQLException {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbDistrito.class);
        codigoDepartamento = codigoDepartamento.substring(0, 2);
        criteria.add(Expression.like("Id", "%" + codigoDepartamento + "__" + "00%"));
        criteria.add(Expression.not(Expression.like("Id", "%__" + "0000%")));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public List seleccionarDistrito(String codigoDepartamento, String codigoProvincia) throws DataAccessException, java.sql.SQLException {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbDistrito.class);
        criteria.add(Expression.like("Id", "%" + codigoDepartamento.substring(0, 2) + codigoProvincia.substring(2, 4) + "__%"));
        criteria.add(Expression.not(Expression.like("Id", "%__" + "0000%")));
        criteria.add(Expression.not(Expression.like("Id", "%____" + "00%")));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public String seleccionarDescripcion(String codigo) throws Exception {
        List lista = this.getSession().createCriteria(TbDistrito.class).add(Restrictions.eq("Id", codigo)).list();
        return ((TbDistrito) lista.get(0)).getDisDes();
    }
}
