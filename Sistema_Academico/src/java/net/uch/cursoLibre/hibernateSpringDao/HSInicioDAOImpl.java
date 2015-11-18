/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClInicio;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 */
public class HSInicioDAOImpl extends HibernateDaoSupport implements HSInicioDAO {

    public void guardarInicio(ClInicio inicio)throws DataAccessException {
        //this.getHibernateTemplate().saveOrUpdate(inicio);
       this.getHibernateTemplate().saveOrUpdate(inicio);
    }

    public List<ClInicio> listarIniciosporModulo(int arbId) {

        List<ClInicio> lista=this.getSession().createCriteria(ClInicio.class,"clInicio")
                            .add(Restrictions.eq("clInicio.iniActivo", "1"))
                            .add(Restrictions.eq("clInicio.clArbolAcademico.arbId", arbId))
                            .addOrder(Order.desc("clInicio.iniFechaInicio"))
                            .list();

        return lista;
    }

    public ClInicio listarIniciosporId(int ini_id)throws DataAccessException {
        ClInicio inicio=(ClInicio) this.getSession().createCriteria(ClInicio.class,"clInicio")
                        .add(Restrictions.eq("clInicio.iniId", ini_id)).uniqueResult();
        this.getSession().clear();
        this.getSession().flush();
        return inicio;
    }

}
