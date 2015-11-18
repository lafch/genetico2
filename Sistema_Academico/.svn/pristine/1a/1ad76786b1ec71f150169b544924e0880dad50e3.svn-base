package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClTaller;
import net.uch.mapping.ClTallerAperturado;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSTallerDAOImpl extends HibernateDaoSupport implements HSTallerDAO {

    @Override
    public List seleccionarTalleres(int cur_id) throws Exception {
        return this.getSession().createCriteria(ClTaller.class).
                add(Restrictions.eq("talActivo", "1")).
                add(Restrictions.eq("clCurso.curId", cur_id)).
                addOrder(Order.asc("talNumero")).
                list();
    }

    @Override
    public void insertarTaller(ClTaller taller) throws Exception {
        this.getHibernateTemplate().save(taller);
    }

    @Override
    public void actualizarTaller(ClTaller taller) throws Exception {
        this.getHibernateTemplate().update(taller);
    }

    @Override
    public void eliminarTaller(int tal_id) throws Exception {
        String hqlUpdate = "update ClTaller set talActivo = :newName where talId = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", "" + tal_id).executeUpdate();
    }

    @Override
    public List verificarEliminarTaller(int tal_id) throws Exception {
        return this.getSession().createCriteria(ClTallerAperturado.class).
                add(Restrictions.eq("talapeActivo", "1")).
                add(Restrictions.eq("talapeAperturado", "1")).
                add(Restrictions.eq("clTaller.talId", tal_id)).list();
    }

    @Override
    public ClTaller buscarTaller(int tal_id) throws Exception {
        return (ClTaller) this.getSession().createCriteria(ClTaller.class).
                add(Restrictions.eq("talId", tal_id)).uniqueResult();
    }

    @Override
    public List seleccionarTalleresXMod(int mod_id) throws Exception {
        return this.getSession().createCriteria(ClTaller.class, "ClTaller").
                createCriteria("ClTaller.clCurso", "ClCurso").
                add(Restrictions.eq("ClTaller.talActivo", "1")).
                add(Restrictions.eq("ClCurso.clModulo.modId", mod_id)).
                addOrder(Order.asc("ClTaller.talNumero")).
                list();
    }
}
