/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.ClMedioPublicidad;
import net.uch.mapping.ClMedioPublicidadDet;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 */
public class HSMedioPublicidadCLDAOImpl extends HibernateDaoSupport implements HSMedioPublicidadCLDAO {

    @Override
    public List<ClMedioPublicidad> listarMedioPublicidad() {
        List<ClMedioPublicidad> lista=new ArrayList<ClMedioPublicidad>();
        lista=getSession().createCriteria(ClMedioPublicidad.class,"ClMedioPublicidad")
                          .add(Restrictions.eq("ClMedioPublicidad.estado", 1))
                          .addOrder(Order.asc("ClMedioPublicidad.descripcion")).list();
        return lista;
    }
    
    @Override
    public List<ClMedioPublicidad> listarMedioPublicidadDet(String tipo) {
        List<ClMedioPublicidad> lista=new ArrayList<ClMedioPublicidad>();
        lista=getSession().createCriteria(ClMedioPublicidad.class,"ClMedioPublicidad")
                          .add(Restrictions.eq("ClMedioPublicidad.estado", 1))
                          .add(Restrictions.eq("ClMedioPublicidad.tipo", tipo))
                          .addOrder(Order.asc("ClMedioPublicidad.orden")).list();
        return lista;
    }

    @Override
    public List<ClMedioPublicidadDet> listarMediopublicidadDeta_x_med(int med_id) {
        List<ClMedioPublicidadDet> lista= new ArrayList<ClMedioPublicidadDet>();
        lista=getSession().createCriteria(ClMedioPublicidadDet.class,"ClMedioPublicidadDet")
                          .add(Restrictions.eq("ClMedioPublicidadDet.pubDetEst", "1"))
                          .add(Restrictions.eq("ClMedioPublicidadDet.clMedioPublicidad.idPublicidad", med_id))
                          .addOrder(Order.asc("ClMedioPublicidadDet.pubDetDes")).list();
        return lista;
    }

    @Override
    public List<ClMedioPublicidadDet> listarMediopublicidadDeta() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificarMedioPublicidad(ClMedioPublicidad medio) {
        this.getSession().update(medio);
    }

    @Override
    public void agregarMedioPublicidad(ClMedioPublicidad medio) {
        System.out.println("cantidad a agregar -> "+medio.getClMedioPublicidadDets().size());
        this.getHibernateTemplate().save(medio);
    }

    @Override
    public List<ClMedioPublicidad> listarMedioPublicidad(String med_descrpcion) {
        List<ClMedioPublicidad> lista=this.getSession().createCriteria(ClMedioPublicidad.class).
                                        add(Restrictions.like("descripcion","%"+med_descrpcion+"%")).
                                        addOrder(Order.asc("descripcion")).list();

        return lista;
    }

    @Override
    public ClMedioPublicidad seleccionarMedioPublicidad(int med_id) {
        ClMedioPublicidad medio=(ClMedioPublicidad) this.getSession().createCriteria(ClMedioPublicidad.class).
                                add(Restrictions.eq("idPublicidad", med_id)).uniqueResult();
        return medio;
    }
    @Override
    public ClMedioPublicidadDet seleccionarMedioPublicidadDetalle(int meddet_id) {
        ClMedioPublicidadDet medioDet=(ClMedioPublicidadDet) this.getSession().createCriteria(ClMedioPublicidadDet.class).
                                add(Restrictions.eq("idPubDet", meddet_id)).uniqueResult();
        return medioDet;
    }

    @Override
    public void agregarMedioPublicidadDet(ClMedioPublicidadDet detalle) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modificarMedioPublicidadDet(ClMedioPublicidadDet detalle) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminarMedioPublicidadDet(int medDet_id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void guardarMedioPublicidadDet(ClMedioPublicidadDet detalle) {
       // this.getSession().saveOrUpdate(detalle);
        if(detalle.getIdPubDet().intValue()>0){
            this.getSession().update(detalle);
        }
        else{
            this.getSession().save(detalle);
        }
    }

    @Override
    public void modificarMedioPublicidadsql(ClMedioPublicidad medio) {
        String hqlUpdate = "update ClMedioPublicidad set descripcion = :v_des , orden = :v_orden where idPublicidad = :v_id";
         this.getSession().createQuery(hqlUpdate).
                setString("v_des", medio.getDescripcion()).
                setInteger("v_orden",medio.getOrden()).
                setInteger("v_id", medio.getIdPublicidad()).
                executeUpdate();
    }

    

}
