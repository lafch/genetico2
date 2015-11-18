/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClCurso;
import net.uch.mapping.ClTaller;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSCursoCLDAOImpl extends HibernateDaoSupport implements HSCursoCLDAO {

    @Override
    public List<ClCurso> seleccionarCursos(int mod_id) throws Exception {
        Criteria criteria = this.getSession().createCriteria(ClCurso.class).
                add(Restrictions.eq("curActivo", "1"));
        if (mod_id > 0) {
            System.out.println("ModuloDAO: "+mod_id);
            criteria.add(Restrictions.eq("clModulo.modId", mod_id));
        }
        return criteria.list();
    }

    @Override
    public List verificarEliminarCurso(int cur_id) throws Exception {
        return this.getSession().createCriteria(ClTaller.class).add(Restrictions.eq("talActivo", "1")).add(Restrictions.eq("clCurso.curId", cur_id)).list();
    }

    @Override
    public void eliminarCurso(int cur_id) throws Exception {
        String hqlUpdate = "update ClCurso set curActivo = :newName where curId = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", "" + cur_id).executeUpdate();
    }

    @Override
    public void actualizarCurso(ClCurso curso) throws Exception {
        this.getHibernateTemplate().update(curso);
    }

    @Override
    public void insertarCurso(ClCurso curso) throws Exception {
        this.getHibernateTemplate().save(curso);
    }

    @Override
    public ClCurso buscarCurso(int cur_id) throws Exception {
        return (ClCurso) this.getSession().createCriteria(ClCurso.class).
                add(Restrictions.eq("curId", cur_id)).
                uniqueResult();
    }
}
