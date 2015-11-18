/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.AcSeccionEspecialidad;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author biblioteca
 */
public class HSSeccionEspecialidadDAOImpl extends HibernateDaoSupport implements HSSeccionEspecialidadDAO{

    @Override
    public List<AcSeccionEspecialidad> getListarSeccionPorEspecialidadCiclo(int esp_id, String secEspCiclo) {
        List<AcSeccionEspecialidad> lista=new ArrayList<AcSeccionEspecialidad>();
        System.out.println("entro al metodo");
        Criteria criteria=this.getSession().createCriteria(AcSeccionEspecialidad.class,"acSeccionEspecialidad")
                .add(Restrictions.eq("acSeccionEspecialidad.acEspecialidad.Id", esp_id))
                .add(Restrictions.eq("acSeccionEspecialidad.secespActivo", "1"))
                .add(Restrictions.eq("acSeccionEspecialidad.secespCiclo", secEspCiclo))
                .addOrder(Order.asc("acSeccionEspecialidad.secespCodigo"));
        if(criteria.list().size()>0){
            lista=criteria.list();
        }
                
        return lista;
    }

    @Override
    public AcSeccionEspecialidad findSeccionEspecialidad(int secespId) {
        return (AcSeccionEspecialidad) this.getSession().createCriteria(AcSeccionEspecialidad.class).
                add(Restrictions.eq("secespId", secespId)).uniqueResult();
    }
    
}
