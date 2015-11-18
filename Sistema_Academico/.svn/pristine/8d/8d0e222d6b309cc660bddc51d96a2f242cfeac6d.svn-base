/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.ClPublicoConsultaDetalle;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Jose Tejada
 */
public class HSPublicoConsultaDetalleCLDAOImpl extends HibernateDaoSupport implements HSPublicoConsultaDetalleCLDAO {

    @Override
    public List<ClPublicoConsultaDetalle> listarPublicoConsultaDetalle(int consultaId) {
        List<ClPublicoConsultaDetalle> lista = new ArrayList<ClPublicoConsultaDetalle>();

        lista = this.getSession().createCriteria(ClPublicoConsultaDetalle.class).add(Restrictions.like("clPublicoConsulta.conId", consultaId)).
                                  addOrder(Order.desc("fecha")).list();
        return lista;
    }

    @Override
    public void guardarConsultaDetalle(ClPublicoConsultaDetalle clPublicoConsultaDetalle){
        this.getHibernateTemplate().save(clPublicoConsultaDetalle);
    }
}
