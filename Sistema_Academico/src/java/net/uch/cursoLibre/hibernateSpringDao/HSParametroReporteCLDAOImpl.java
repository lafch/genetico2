/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbParametroReportes;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSParametroReporteCLDAOImpl extends HibernateDaoSupport implements HSParametroReporteCLDAO {

    @Override
    public List<TbParametroReportes> getListParam(int rep_id) {
         return this.getSession().createCriteria(TbParametroReportes.class,"ReportParam")
                    .createCriteria("ReportParam.tbReporte","Rep")
                    .add(Restrictions.eq("Rep.repId",rep_id))
                    .list();

    }

}
