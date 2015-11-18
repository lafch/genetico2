package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcPlancurricular;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSPlanCurricularDAOImpl extends HibernateDaoSupport implements HSPlanCurricularDAO {

    @Override
    public void insertarPlanCurricular(AcPlancurricular pc) throws DataAccessException {
        getHibernateTemplate().save(pc);
    }

    @Override
    public List seleccionarPlanCurricular(String codigo, String descripcion, int facultad, int especialidad) throws DataAccessException, java.sql.SQLException {
        String hqlUpdate = "select pc.Id,"
                + "pc.PlanCodigo,"
                + "pc.PlanDescripcion,"
                + "pc.PlanCreacion,"
                + "fac.Id,"
                + "pc.Esp.Id,"
                + "pc.PlanResolucion,"
                + "pc.PlanActivo,"
                + "fac.FacNombre,"
                + "esp.EspNombre "
                + "from AcPlancurricular pc, AcEspecialidad esp, AcFacultad fac "
                + "where "
                + "pc.Esp.Id =esp.Id "
                + "and pc.PlanCodigo like :codigo "
                + "and pc.PlanDescripcion like :descripcion ";
        if (facultad != 0) {
            hqlUpdate += "and fac.Id = :facultad ";
            if (especialidad != 0) {
                hqlUpdate += "and pc.Esp = :especialidad ";
            }
        }
        hqlUpdate += "and esp.Fac.Id=fac.Id and pc.PlanActivo = :activo";
        List lista = null;
        if (facultad != 0) {
            if (especialidad != 0) {
                lista = this.getSession().createQuery(hqlUpdate).setString("codigo", "%" + codigo + "%").setString("descripcion", "%" + descripcion + "%").setString("facultad", "" + facultad).setString("especialidad", "" + especialidad).setString("activo", "1").list();
            } else {
                lista = this.getSession().createQuery(hqlUpdate).setString("codigo", "%" + codigo + "%").setString("descripcion", "%" + descripcion + "%").setString("facultad", "" + facultad).setString("activo", "1").list();
            }
        } else {
            lista = this.getSession().createQuery(hqlUpdate).setString("codigo", "%" + codigo + "%").setString("descripcion", "%" + descripcion + "%").setString("activo", "1").list();
        }
        return lista;
    }

    @Override
    public List seleccionarPlanActivo(int esp) throws DataAccessException, java.sql.SQLException {
        return this.getSession().createCriteria(AcPlancurricular.class).add(Expression.eq("PlanActivo", "1")).add(Expression.eq("Esp.Id", esp)).addOrder(Order.desc("PlanCreacion")).list();
    }

    @Override
    public void eliminarPlanCurricular(String id) throws DataAccessException {
        String hqlUpdate = "update AcPlancurricular set PlanActivo = :newName where Id = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setInteger("oldName", Integer.parseInt(id)).executeUpdate();
    }

    @Override
    public void actualizarPlanCurricular(AcPlancurricular pc) throws DataAccessException {
        getHibernateTemplate().update(pc);
    }

    @Override
    public List seleccionarPlanCurricular(int especialidad) {
        return this.getSession().createCriteria(AcPlancurricular.class).add(Restrictions.eq("Esp.Id", especialidad)).add(Restrictions.eq("PlanActivo", "1")).list();
    }
}
