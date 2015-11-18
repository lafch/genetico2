/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.uch.cursoLibre.managedBeans.beans.BeanInfoMatricula;
import net.uch.mapping.ClConsultaPublico;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Simion Richa R B
 */
public class HSConsultaPublicoImpl extends HibernateDaoSupport implements HSConsultaPublico {

    @Override
    public void agregarConsultaPublico(ClConsultaPublico consultaPublico) {
        this.getHibernateTemplate().save(consultaPublico);
    }

    @Override
    public List<ClConsultaPublico> listarxAlumnoConsultaPublico(int alu_id) {
        List<ClConsultaPublico> lista = this.getSession().createCriteria(ClConsultaPublico.class, "ClConsultaPublico").add(Restrictions.eq("conActivo", "1")).add(Restrictions.eq("clAlumno.aluId", alu_id)).addOrder(Order.asc("conFechaContacto")).list();
        return lista;
    }

    @Override
    public void modificarCampoMatricula(int con_id) {
        String hqlUpdate = "update ClConsultaPublico set conEstadoMatricula = :v_mat where conId = :v_consulta_id";
        this.getSession().createQuery(hqlUpdate).setString("v_mat", "2").setInteger("v_consulta_id", con_id).executeUpdate();
    }

    @Override
    public List<ClConsultaPublico> listarPublicoConsulta(int are_id, int mod_id, int hor_id, int usu_id, Date fecha_ini, Date fecha_fin, String con_procedencia) {
        System.out.println("la fecha_ini -> " + fecha_ini);
        System.out.println("la fecha_fin -> " + fecha_fin);

        Criteria criteria = this.getSession().createCriteria(ClConsultaPublico.class, "clConsultaPublico").createCriteria("clConsultaPublico.clModulo", "clModulo").createCriteria("clModulo.clArea", "clArea").add(Restrictions.eq("clConsultaPublico.conActivo", "1")).add(Restrictions.eq("clModulo.modActivo", "1")).add(Restrictions.eq("clArea.areActivo", "1")).add(Restrictions.between("clConsultaPublico.conFechaRegistro", fecha_ini, fecha_fin));

        if (are_id > 0) {
            System.out.println("el are_id -> " + are_id);
            criteria.add(Restrictions.eq("clArea.areId", are_id));
        }
        if (mod_id > 0) {
            System.out.println("el mod_id -> " + mod_id);
            criteria.add(Restrictions.eq("clModulo.modId", mod_id));
        }
        if (hor_id > 0) {
            System.out.println("el hor_id -> " + hor_id);
            criteria.add(Restrictions.eq("clConsultaPublico.plaId", hor_id));
        }
        if (usu_id > 0) {
            System.out.println("el usu_id -> " + usu_id);
            criteria.add(Restrictions.eq("clConsultaPublico.usuId", hor_id));
        }
        if (!con_procedencia.equals("000000")) {
            System.out.println("la procedencia -> " + con_procedencia);
            criteria.add(Restrictions.eq("clConsultaPublico.conProcedencia", con_procedencia));
        }
        List<ClConsultaPublico> lista = criteria.list();
        return lista;
    }

    @Override
    public void modificarConsultaPublico(int con_id, String con_observacion, Date con_fecha_contacto) {
        String hqlUpdate = "update ClConsultaPublico set conFechaContacto = :v_fecha, conObservacionRegistro= :v_observacion where conId = :v_conId";
        this.getSession().createQuery(hqlUpdate).setDate("v_fecha", con_fecha_contacto).setString("v_observacion", con_observacion).setInteger("v_conId", con_id).executeUpdate();
    }

    @Override
    public List<BeanInfoMatricula> cantidadMatriculadosUsuario(Date fecha_ini, Date fecha_fin) {
        List<BeanInfoMatricula> info = new ArrayList<BeanInfoMatricula>();
        List lista = this.getSession().createCriteria(ClConsultaPublico.class).setProjection(Projections.projectionList()
                                                                                .add(Projections.rowCount(), "cantidad")
                                                                                .add(Projections.property("usuId"))
                                                                                .add(Projections.groupProperty("usuId")))
                                       //.add(Restrictions.eq("conEstadoMatricula", "2"))
                                       .add(Restrictions.between("conFechaRegistro", fecha_ini, fecha_fin)).list();
        Object[] obj;
        for (int i = 0; i < lista.size(); i++) {
            obj = (Object[]) lista.get(i);
            List lista2 = this.getSession().createCriteria(ClConsultaPublico.class).setProjection(Projections.projectionList()
                                                                                .add(Projections.rowCount(), "cantidad")
                                                                                .add(Projections.property("usuId"))
                                                                                .add(Projections.groupProperty("usuId")))
                                       .add(Restrictions.eq("conEstadoMatricula", "0"))
                                       .add(Restrictions.eq("usuId", Integer.parseInt(obj[1].toString())))
                                       .add(Restrictions.between("conFechaRegistro", fecha_ini, fecha_fin)).list();
             List lista3 = this.getSession().createCriteria(ClConsultaPublico.class).setProjection(Projections.projectionList()
                                                                                .add(Projections.rowCount(), "cantidad")
                                                                                .add(Projections.property("usuId"))
                                                                                .add(Projections.groupProperty("usuId")))
                                       .add(Restrictions.eq("conEstadoMatricula", "2"))
                                       .add(Restrictions.eq("usuId", Integer.parseInt(obj[1].toString())))
                                       .add(Restrictions.between("conFechaRegistro", fecha_ini, fecha_fin)).list();
            BeanInfoMatricula mat = new BeanInfoMatricula();
            mat.setInf_informes(0);
            Object[] obj2;
            Object[] obj3;
            if (!lista2.isEmpty()) {
                obj2 = (Object[]) lista2.get(0);
                mat.setInf_informes(Integer.parseInt(obj2[0].toString()));

            }
            if (!lista3.isEmpty()) {
                obj3 = (Object[]) lista3.get(0);
                mat.setInf_matriculados(Integer.parseInt(obj3[0].toString()));

            }

            mat.setInf_contador(i + 1);
            //mat.setInf_matriculados(Integer.parseInt(obj[0].toString()));
            mat.setUsu_id(Integer.parseInt(obj[1].toString()));
            info.add(mat);

        }

        return info;
    }

    @Override
    public List<ClConsultaPublico> listadoInscritosPorUsuario(int usu_id, Date fechaIni, Date fechaFin, String tipo) {
        List<ClConsultaPublico> lista=new ArrayList<ClConsultaPublico>();
        lista=this.getSession().createCriteria(ClConsultaPublico.class)
                                .add(Restrictions.eq("usuId", usu_id))
                                .add(Restrictions.between("conFechaRegistro", fechaIni, fechaFin))
                                .add(Restrictions.eq("conEstadoMatricula", tipo)).list();
        return lista;
    }

    @Override
    public ClConsultaPublico seleccionarConsultaPublico(int con_id) {
        ClConsultaPublico clConsultaPublico=(ClConsultaPublico) this.getSession().createCriteria(ClConsultaPublico.class)
                                            .add(Restrictions.eq("conId", con_id)).uniqueResult();
        return clConsultaPublico;
    }

    @Override
    public List<ClConsultaPublico> seleccionarConsultaPublicoporFecha(Date fecha) {
        List<ClConsultaPublico> lista = new ArrayList<ClConsultaPublico>();
        try {
            
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Tfecha = format.format(fecha);
            Date w_fecha = sdf.parse(Tfecha);
            //Date fec=format.parse(fecha);
            lista = this.getSession().createCriteria(ClConsultaPublico.class)
                        .add(Restrictions.eq("conFechaContacto", w_fecha))
                        .add(Restrictions.eq("conActivo", "1")).list();
            
        } catch (ParseException ex) {
            Logger.getLogger(HSConsultaPublicoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
