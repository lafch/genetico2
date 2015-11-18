package net.uch.cursoLibre.hibernateSpringDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanInfoMatricula;
import net.uch.mapping.ClPublicoConsulta;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSPublicoConsultaCLDAOImpl extends HibernateDaoSupport implements HSPublicoConsultaCLDAO {

    @Override
    public List<ClPublicoConsulta> listarPublicoConsulta(int aluId) {
        List<ClPublicoConsulta> lista = new ArrayList<ClPublicoConsulta>();
        //System.out.println("el id del alumno es -> "+aluId);
        lista = this.getSession().createCriteria(ClPublicoConsulta.class).add(Restrictions.like("clAlumno.aluId", aluId)).addOrder(Order.desc("fechaContacto")).list();
        return lista;
    }

    @Override
    public void agregarPublicoConsulta(ClPublicoConsulta consulta) {
        this.getHibernateTemplate().save(consulta);
    }

    @Override
    public ClPublicoConsulta traerConsultaXConsultaId(int consultaId) {
        ClPublicoConsulta consulta = (ClPublicoConsulta) this.getSession().createCriteria(ClPublicoConsulta.class).add(Restrictions.eq("consultaId", consultaId)).uniqueResult();
        return consulta;
    }

    

    @Override
    public List traerUltimaConsultaXPubId(int aluId) {
        List lista = new ArrayList();
        ProjectionList pList = Projections.projectionList();
        pList.add(Projections.max("consultaId"));
        lista = this.getSession().createCriteria(ClPublicoConsulta.class).add(Restrictions.like("clAlumno.aluId", aluId)).setProjection(pList).list();
        return lista;
    }

    @Override
    public void modificarPublicoConsulta(Integer consultaId, String nuevaObs) {
//        this.getHibernateTemplate().update(consulta);
        String hqlUpdate = "update ClPublicoConsulta set obsConsulta = :v_nueva_obs where consultaId = :v_consulta_id";
        this.getSession().createQuery(hqlUpdate).setString("v_nueva_obs", nuevaObs).setString("v_consulta_id", consultaId+"").executeUpdate();
    }

    @Override
    public List<ClPublicoConsulta> traerConsultasPorContactar(String area, String modulo, Integer taller, String horario, Date fechContactoIni, Date fechContactoFin, String procedencia) {
        Criteria c = this.getSession().createCriteria(ClPublicoConsulta.class);
      /*
        System.out.println("area -> "+area);
        System.out.println("modulo -> "+modulo);
        System.out.println("taller -> "+taller);
        System.out.println("fecha ini -> "+fechContactoIni);
        System.out.println("fecha fin -> "+fechContactoFin);
      */

        c.add(Restrictions.between("fechaContacto", fechContactoIni, fechContactoFin));
        if (!area.isEmpty()) {
            c.add(Restrictions.like("area", area));
        }
        if (!modulo.isEmpty()) {
            c.add(Restrictions.like("tipoArea", modulo));
        }
        if (!horario.isEmpty()) {
            c.add(Restrictions.like("horarios", horario));
        }
        if (taller != -1) {
            c.add(Restrictions.like("diasEstudio", taller));
        }
        if(!procedencia.equals("000000")){
            //System.out.println("entro a procedencia => "+procedencia);
            c.add(Restrictions.like("procedencia", procedencia));
        }
        c.addOrder(Order.desc("fechaContacto"));
        return c.list();
    }
    @Override
    public void cambiarFechaContactoConsulta(ClPublicoConsulta consulta) throws Exception {
//        this.getHibernateTemplate().update(consulta);
        String hqlUpdate = "update ClPublicoConsulta set fechaContacto = :v_fech_cont where consultaId = :v_consulta_id";
        this.getSession().createQuery(hqlUpdate).setDate("v_fech_cont", consulta.getFechaContacto()).setString("v_consulta_id", consulta.getConsultaId() + "").executeUpdate();
    }

    @Override
    public void modificarMatricula(int consultaId) {
        String hqlUpdate = "update ClPublicoConsulta set matriculado = :v_mat where consultaId = :v_consulta_id";
        this.getSession().createQuery(hqlUpdate).setInteger("v_mat", 1).setInteger("v_consulta_id", consultaId).executeUpdate();
    }

    @Override
    public List cantidadesMatriculadosPorUsuario(int usu_id) {
        List lista=new ArrayList();
        lista=this.getSession().createCriteria(ClPublicoConsulta.class).
                add(Restrictions.eq("usuCod",String.valueOf(usu_id))).
                add(Restrictions.eq("matriculado", "1")).
                setProjection(Projections.projectionList().
                                                          add(Projections.rowCount(),"cantidad").
                                                          add(Projections.property("usuCod")).
                                                          add(Projections.groupProperty("usuCod"))).
                list();

        return lista;
    }

     @Override
    public List<ClPublicoConsulta> listadoMatriculadosPorUsuario(int usu_id, Date fechaIni, Date fechaFin) {
        List<ClPublicoConsulta> lista=this.getSession().createCriteria(ClPublicoConsulta.class).
                                add(Restrictions.eq("matriculado", 1)).
                                add(Restrictions.between("fechaRegistro", fechaIni, fechaFin)).
                                add(Restrictions.eq("usuCod", usu_id)).list();

        return lista;
    }

    @Override
    public List<BeanInfoMatricula> cantidadDematriculadosPorUsuario(Date fechaIni, Date fechaFin) {
        List<BeanInfoMatricula> info=new ArrayList<BeanInfoMatricula>();
          List lista=this.getSession().createCriteria(ClPublicoConsulta.class).setProjection(Projections.projectionList().
                                                                        add(Projections.rowCount(),"cantidad").
                                                                        add(Projections.property("usuCod")).
                                                                        add(Projections.groupProperty("usuCod"))).
                    add(Restrictions.eq("matriculado", 1)).
                    add(Restrictions.between("fechaContacto", fechaIni, fechaFin)).
                    list();
          Object[] obj;
          for(int i=0; i<lista.size(); i++){
               obj = (Object[]) lista.get(i);
                List lista2=this.getSession().createCriteria(ClPublicoConsulta.class).setProjection(Projections.projectionList().
                                                                        add(Projections.rowCount(),"cantidad").
                                                                        add(Projections.property("usuCod")).
                                                                        add(Projections.groupProperty("usuCod"))).
                    add(Restrictions.eq("matriculado", 0)).
                    add(Restrictions.eq("usuCod", Integer.parseInt(obj[1].toString()))).
                    add(Restrictions.between("fechaContacto", fechaIni, fechaFin)).list();
                BeanInfoMatricula mat=new BeanInfoMatricula();
                mat.setInf_informes(0);
                Object[] obj2;
                if(!lista2.isEmpty()){
                    obj2= (Object[]) lista2.get(0);
                    mat.setInf_informes(Integer.parseInt(obj2[0].toString()));
                    
                }

                mat.setInf_contador(i+1);
                mat.setInf_matriculados(Integer.parseInt(obj[0].toString()));
                mat.setUsu_id(Integer.parseInt(obj[1].toString()));
                info.add(mat);
          }
        return info;
    }

    @Override
    public List<ClPublicoConsulta> listadoInformesPorUsuario(int usu_id, Date fechaIni, Date fechaFin) {
         List<ClPublicoConsulta> lista=this.getSession().createCriteria(ClPublicoConsulta.class).
                                add(Restrictions.eq("matriculado", 0)).
                                add(Restrictions.between("fechaRegistro", fechaIni, fechaFin)).
                                add(Restrictions.eq("usuCod", usu_id)).list();

        return lista;
    }
}
