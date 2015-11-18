/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.ClPlantillaHorario;
import net.uch.mapping.ClPlantillaHorarioDet;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 */
public class HSPlantillaHorarioCLDAOImpl extends HibernateDaoSupport implements HSPlantillaHorarioCLDAO {

    @Override
    public List<ClPlantillaHorario> listarPlantilla(String pla_descripcion) {
        List<ClPlantillaHorario> lista=new ArrayList<ClPlantillaHorario>();
        lista=this.getSession().createCriteria(ClPlantillaHorario.class).
                add(Restrictions.like("plaDescripcion", "%"+pla_descripcion+"%")).
                add(Restrictions.eq("plaActivo", "1")).list();
        return lista;
    }

    @Override
    public ClPlantillaHorario seleccionarPlantilla(int pla_id) {
        ClPlantillaHorario plantilla= (ClPlantillaHorario) this.getSession().createCriteria(ClPlantillaHorario.class).
                                add(Restrictions.eq("plaId", pla_id)).
                                add(Restrictions.eq("plaActivo", "1")).uniqueResult();
        return plantilla;
    }

    @Override
    public void agregarPlantilla(ClPlantillaHorario plantilla) {
        this.getHibernateTemplate().save(plantilla);
    }

    @Override
    public void modificarPlantilla(ClPlantillaHorario plantilla) {
        //this.getHibernateTemplate().update(plantilla);
        this.getSession().update(plantilla);
    }

    @Override
    public void eliminarPlantilla(int pla_id) {
         String hqlUpdate = "update ClPlantillaHorario set plaActivo = :v_activo where plaId = :v_id";
         this.getSession().createQuery(hqlUpdate).
                setString("v_activo", "1").
                setInteger("v_id", pla_id).
                executeUpdate();
    }

    @Override
    public List<ClPlantillaHorario> listarPlantilla() {
        //System.out.println("entro aqui al listar >");
         List<ClPlantillaHorario> plantilla = this.getSession().createCriteria(ClPlantillaHorario.class).
                                add(Restrictions.eq("plaActivo", "1")).addOrder(Order.asc("plaDescripcion")).list();
        return plantilla;
    }

    @Override
    public List<ClPlantillaHorarioDet> listarDetallePlantilla(int pla_id) {
        List<ClPlantillaHorarioDet> detalle=this.getSession().createCriteria(ClPlantillaHorarioDet.class,"clPlantillaHorarioDet").
                                            add(Restrictions.eq("pladetActivo", "1")).
                                            createCriteria("clPlantillaHorarioDet.clPlantillaHorario", "clPlantillaHorario").
                                            add(Restrictions.eq("clPlantillaHorario.plaId", pla_id)).
                                            addOrder(Order.asc("clPlantillaHorarioDet.pladetDia")).
                                            list();
        return detalle;
    }

}
