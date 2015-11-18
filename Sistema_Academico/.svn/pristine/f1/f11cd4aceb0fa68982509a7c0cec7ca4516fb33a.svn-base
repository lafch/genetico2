/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import net.uch.mapping.ClInformacionReferencial;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Richard R B
 */
public class HSInformacionReferencialDAOImpl extends HibernateDaoSupport implements HSInformacionReferencialDAO {

    @Override
    public void agregarInformacionReferencial( ClInformacionReferencial informacionReferencial ) {
        this.getHibernateTemplate().save( informacionReferencial );
    }
}
