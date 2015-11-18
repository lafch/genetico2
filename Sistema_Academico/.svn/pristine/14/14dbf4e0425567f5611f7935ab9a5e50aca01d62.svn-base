/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.academica.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.AcApoderado;
import net.uch.mapping.AcApoderadoAlumno;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 */
public class HSApoderadoDAOImpl extends HibernateDaoSupport implements HSApoderadoDAO {

    @Override
    public List<AcApoderado> listarApoderados(String apo_dni, String apo_appaterno, String apo_apmaterno, String apo_nombres) {
        List<AcApoderado> lista= new ArrayList<AcApoderado>();
        lista=this.getSession().createCriteria(AcApoderado.class).
                add(Restrictions.like("apoDni", "%"+apo_dni+"%")).
                add(Restrictions.like("apoAppaterno", "%"+apo_appaterno+"%")).
                add(Restrictions.like("apoApmaterno", "%"+apo_apmaterno+"%")).
                add(Restrictions.like("apoNombres", "%"+apo_nombres+"%")).
                add(Restrictions.eq("apoActivo", "1")).
                addOrder(Order.asc("apoAppaterno")).
                addOrder(Order.asc("apoApmaterno")).list();
        return lista;
    }

    @Override
    public AcApoderado datoApoderado(int apo_id) throws DataAccessException {
        AcApoderado acApoderado=(AcApoderado)this.getSession().createCriteria(AcApoderado.class).
                add(Restrictions.and(Restrictions.eq("apoId", apo_id), Restrictions.eq("apoActivo", "1"))).uniqueResult();
        
        return acApoderado;
    }

    @Override
    public void agregarApoderado(AcApoderado acApoderado) {
        this.getHibernateTemplate().save(acApoderado);
    }

    @Override
    public void eliminarApoderado(int apo_id) {
       
        String hqlUpdate="update AcApoderado set apoActivo= :v_activo where apoId = :v_id";
        this.getSession().createQuery(hqlUpdate).setString("v_activo", "0").setString("v_id", String.valueOf(apo_id)).executeUpdate();
        
    }

    @Override
    public void modificarApoderado(AcApoderado acApoderado) {
        this.getHibernateTemplate().update(acApoderado);
    }

    @Override
    public List<AcApoderadoAlumno> listarAlumnosCargo(int apo_id) {
        List<AcApoderadoAlumno > lista=this.getSession().createCriteria(AcApoderadoAlumno.class,"acApoderadoAlumno").
                                        createCriteria("acApoderadoAlumno.acApoderado","acApoderado").
                                        add(Restrictions.eq("acApoderadoAlumno.apoaluActivo", "1")).
                                        add(Restrictions.eq("acApoderado.apoId", apo_id)).list();
        return lista;
    }

    @Override
    public void agregarApoderadoAlumno(AcApoderadoAlumno acApoderadoAlumno) throws DataAccessException {
        this.getHibernateTemplate().save(acApoderadoAlumno);
    }

    @Override
    public AcApoderado datoApoderadoxDni(String apo_dni) throws DataAccessException {
         AcApoderado acApoderado=(AcApoderado)this.getSession().createCriteria(AcApoderado.class).
                add(Restrictions.and(Restrictions.eq("apoDni", apo_dni), Restrictions.eq("apoActivo", "1"))).uniqueResult();

        return acApoderado;
    }

}
