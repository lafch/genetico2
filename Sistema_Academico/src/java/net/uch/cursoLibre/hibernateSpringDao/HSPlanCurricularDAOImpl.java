package net.uch.cursoLibre.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClPlancurricular;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSPlanCurricularDAOImpl extends HibernateDaoSupport implements HSPlanCurricularDAO {

    @Override
    public List seleccionarPlanCurricular(int mod_id, String descripcion) throws Exception {
        Criteria criteria = this.getSession().createCriteria(ClPlancurricular.class).
                add(Restrictions.eq("plaActivo", "1")).
                add(Restrictions.like("plaDescripcion", "%" + descripcion + "%"));
        if (mod_id != 0) {
            criteria.add(Restrictions.eq("clArbolAcademico.arbId", mod_id));
        }
        return criteria.list();
    }

    @Override
    public void insertarPlanCurricular(ClPlancurricular planCurricular) throws Exception {
        this.getHibernateTemplate().save(planCurricular);
    }

    @Override
    public void actualizarPlanCurricular(ClPlancurricular planCurricular) throws Exception {
        this.getHibernateTemplate().update(planCurricular);
    }

    @Override
    public void eliminarPlanCurricular(int id) throws Exception {
        String hqlUpdate = "update ClPlancurricular set plaActivo = :newName where plaId = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").
                setString("oldName", "" + id).executeUpdate();
    }

    @Override
    public ClPlancurricular buscarPlanCurricular(int id) throws Exception {
        return (ClPlancurricular) this.getSession().createCriteria(ClPlancurricular.class).
                add(Restrictions.eq("plaId", id)).uniqueResult();
    }
    
    @Override
    public ClArbolAcademico buscarModulo(int mod_id) throws Exception {
        return (ClArbolAcademico) this.getSession().createCriteria(ClArbolAcademico.class, "arbIdPadre").
                add(Restrictions.eq("arbId", mod_id)).
                add(Restrictions.eq("arbNivel", 2)).
                uniqueResult();
    }

    @Override
    public void desactualizarPlanes(int mod_id) throws Exception {
        String hqlUpdate = "update ClPlancurricular set plaActual = :newName where clArbolAcademico.arbId = :id";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").
                setString("id", "" + mod_id).executeUpdate();
    }

    @Override
    public ClPlancurricular buscarPlanActual(int cur_id) throws Exception {
        return (ClPlancurricular) this.getSession().createCriteria(ClPlancurricular.class).
                add(Restrictions.eq("plaActivo", "1")).
                add(Restrictions.eq("plaVigente", "1")).
                add(Restrictions.eq("plaActual", "1")).
                add(Restrictions.eq("clArbolAcademico.arbId", cur_id)).
                uniqueResult();
    }
    
    @Override
    public ClPlancurricular buscarPlan(int cur_id) throws Exception {
        return (ClPlancurricular) this.getSession().createCriteria(ClPlancurricular.class).
                add(Restrictions.eq("plaActivo", "1")).
                add(Restrictions.eq("plaVigente", "1")).
                add(Restrictions.eq("plaActual", "1")).
                add(Restrictions.eq("clArbolAcademico.arbId", cur_id)).
                uniqueResult();
    }
}
