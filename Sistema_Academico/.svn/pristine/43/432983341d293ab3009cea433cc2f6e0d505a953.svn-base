package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcActa;
import net.uch.mapping.AcActaDetalle;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSActaDAOImpl extends HibernateDaoSupport implements HSActaDAO {

    @Override
    public void insertarActa(AcActa acta) {
        try {
//            System.out.println("save or update");
            this.getHibernateTemplate().saveOrUpdate(acta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertarActaDetalles(List<AcActaDetalle> actas_detalle) {
        this.getHibernateTemplate().saveOrUpdateAll(actas_detalle);
    }

    @Override
    public AcActa seleccionarActa(int sec, String acta_tipo) {
        return (AcActa) this.getSession().createCriteria(AcActa.class).
                add(Restrictions.eq("Sec.Id", sec)).
                add(Restrictions.eq("ActTipo", acta_tipo)).
                add(Restrictions.eq("ActActivo", "1")).uniqueResult();
    }

    @Override
    public List seleccionarAlumnosActa(int sec) {
        Criteria crit = this.getSession().createCriteria(AcActa.class, "Acta").add(Restrictions.eq("Acta.ActActivo", "1")).add(Restrictions.eq("Sec.Id", sec)).createCriteria("AcActaDetalles", "AcActaDetalles").add(Restrictions.eq("AcActaDetalles.ActdetActivo", "1"));
        ProjectionList projList = Projections.projectionList();
        projList.add(Projections.groupProperty("AcActaDetalles.AluId"), "alumno");
        crit.setProjection(projList);
        crit.addOrder(Order.asc("alumno"));
        return crit.list();
    }

    @Override
    public void eliminarActa(AcActa acta) {
        this.getHibernateTemplate().delete(acta);
    }

    @Override
    public void eliminarActaDetalle(AcActaDetalle detalle) {
        String hqlUpdate = "update AcActaDetalle set ActdetActivo = :v_activo  where Act.Id = :v_actId and AluId = :v_alu_id";
        this.getSession().createQuery(hqlUpdate).setString("v_activo", "0").setInteger("v_actId", detalle.getAct().getId()).
                                                 setInteger("v_alu_id", detalle.getAluId()).executeUpdate();
    }
}
