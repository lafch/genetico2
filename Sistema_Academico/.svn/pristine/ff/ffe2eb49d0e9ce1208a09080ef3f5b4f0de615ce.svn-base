/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcTemporal;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author LUIS
 */
public class HSTemporalDAOImpl extends HibernateDaoSupport implements HSTemporalDAO{
    
    @Override
    public void insertarTemporal(AcTemporal tem) throws DataAccessException {
        this.getHibernateTemplate().save(tem);
    } 
     
}
