/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.administrativa.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcArea;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 */
public class HSHorarioAreaDAOImpl extends HibernateDaoSupport implements HSHorarioAreaDAO {

    @Override
    public List listarHorarioArea(int are_id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List listarAreas() {
        List lista = this.getSession().createCriteria(AcArea.class, "AcArea").
                add(Restrictions.eq("AcArea.areActivo", "1")).
                addOrder(Order.asc("AcArea.areNombre")).list();
        return lista;
    }
}
