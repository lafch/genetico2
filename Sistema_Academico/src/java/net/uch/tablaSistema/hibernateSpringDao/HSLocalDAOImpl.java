package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcLocal;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSLocalDAOImpl extends HibernateDaoSupport implements HSLocalDAO {

    @Override
    public void insertarLocal(AcLocal local)
            throws DataAccessException {
        getHibernateTemplate().save(local);
    }

    @Override
    public List seleccionarLocal(String nombre) throws DataAccessException, java.sql.SQLException {
        DetachedCriteria criteria = DetachedCriteria.forClass(AcLocal.class);
        criteria.add(Expression.like("LocDes", "%" + nombre + "%"));
        criteria.add(Expression.eq("LocActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);


        return lista;
    }

    @Override
    public void eliminarLocal(String id)
            throws DataAccessException {
        String hqlUpdate = "update AcLocal set LocActivo = :newName where Id = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", id).executeUpdate();
    }

    @Override
    public void actualizarLocal(AcLocal local)
            throws DataAccessException {
        getHibernateTemplate().update(local);
    }

    @Override
    public AcLocal seleccionarLocal(int loc_id) throws DataAccessException {
        AcLocal local=(AcLocal) this.getSession().createCriteria(AcLocal.class,"acLocal")
                        .add(Restrictions.eq("acLocal.Id", loc_id)).uniqueResult();
        return local;
    }
}
