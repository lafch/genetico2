package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClArea;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSAreaDAOImpl extends HibernateDaoSupport implements HSAreaDAO {

    @Override
    public List seleccionarArea(String descripcion) throws Exception {
        Criteria c = this.getSession().createCriteria(ClArea.class).
                add(Restrictions.eq("areActivo", "1"));

        if (descripcion.trim().length() != 0) {
            c.add(Restrictions.like("areDescripcion", "%" + descripcion + "%"));
        }
        return c.list();
    }

    @Override
    public void eliminarArea(int id) throws Exception {
        String hqlUpdate = "update ClArea set areActivo = :newName where areId = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").
                setString("oldName", "" + id).
                executeUpdate();
    }

    @Override
    public void insertarArea(ClArea area) throws Exception {
        this.getHibernateTemplate().save(area);
    }

    @Override
    public void actualizarArea(ClArea area) throws Exception {
        this.getHibernateTemplate().update(area);
    }

    @Override
    public ClArea buscarArea(int id) throws Exception {
        return (ClArea) this.getSession().createCriteria(ClArea.class).
                add(Restrictions.eq("areId", id)).uniqueResult();
    }
}