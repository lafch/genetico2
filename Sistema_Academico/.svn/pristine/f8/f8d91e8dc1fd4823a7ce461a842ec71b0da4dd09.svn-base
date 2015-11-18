package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcPabellon;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSPabellonDAOImpl extends HibernateDaoSupport implements HSPabellonDAO {

    @Override
    public void insertarPabellon(AcPabellon pabellon)
            throws DataAccessException {
        getHibernateTemplate().save(pabellon);
    }

    @Override
    public List seleccionarPabellon(String nombre, int facultad) throws DataAccessException, java.sql.SQLException {
        DetachedCriteria criteria = DetachedCriteria.forClass(AcPabellon.class);
        criteria.add(Expression.like("PabDescripcion", "%" + nombre + "%"));
        if (facultad != 0) {
            criteria.add(Expression.eq("Loc.Id", facultad));
        }
        criteria.add(Expression.eq("PabActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public void eliminarPabellon(String id)
            throws DataAccessException {
        String hqlUpdate = "update AcPabellon set PabActivo = :newName where Id = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", id).executeUpdate();
    }

    @Override
    public void actualizarPabellon(AcPabellon pabellon)
            throws DataAccessException {
        getHibernateTemplate().update(pabellon);
    }
}
