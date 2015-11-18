package net.uch.academica.hibernateSpringDao;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.uch.academica.managedBeans.beans.BeanCursosMatricular;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcActa;
import net.uch.mapping.AcActaDetalle;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcMatricula;
import net.uch.mapping.AcMatriculaCurso;
import net.uch.mapping.AcPlanCurso;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.TbBloqueoWeb;
import net.uch.util.Print;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSMatriculaDAOImpl extends HibernateDaoSupport implements HSMatriculaDAO 
{

    @Override
    public List seleccionarcursosAprobados(int alu_id) throws DataAccessException 
    {
        List evaluacion = new ArrayList();
        List<Integer> aprobados = new ArrayList<Integer>();
        List<Integer> desaprobados_id = new ArrayList<Integer>();
        List<AcPlanCurso> desaprobados = new ArrayList<AcPlanCurso>();
        List<AcPlanCurso> aprobados_ = new ArrayList<AcPlanCurso>();

//        System.out.println("SQL-SQL-SQL-SQL-SQL");
        Criteria criteria = this.getSession().createCriteria(AcActa.class, "AcActa").
                createCriteria("AcActa.AcActaDetalles", "AcActaDetalles").
                add(Restrictions.eq("AcActaDetalles.AluId", alu_id)).
                add(Restrictions.disjunction().
                add(Restrictions.eq("AcActaDetalles.ActdetTipoNota", "034002")).
                add(Restrictions.eq("AcActaDetalles.ActdetTipoNota", "034003"))).
                add(Restrictions.eq("AcActa.ActActivo", "1")).
                add(Restrictions.eq("AcActaDetalles.ActdetActivo", "1")).
                createCriteria("AcActa.Sec", "Sec").
                createCriteria("Sec.Curape", "Curape").
                addOrder(Order.desc("Curape.Plancur.Id"));

        ProjectionList projection = Projections.projectionList();
        projection.add(Projections.property("Curape.Plancur.Id"));//obj[0]
        projection.add(Projections.property("AcActaDetalles.ActdetNota"));//obj[1]
        projection.add(Projections.count("Sec.Id"));//obj[2]
        projection.add(Projections.property("AcActa.Id"));//obj[3]
        projection.add(Projections.property("Curape.Plancur"));//obj[4]
        projection.add(Projections.max("Sec.Id"));//obj[5]
        projection.add(Projections.groupProperty("Curape.Plancur.Id"));
        criteria.setProjection(projection);
        List lista = criteria.list();
//        System.out.println("SQL-SQL-SQL-SQL-SQL");
//        System.out.println("lista.size() - " + lista.size());

        Object[] obj;
        AcPlanCurso plancur;
        for (int i = 0; i < lista.size(); i++) 
        {
            obj = (Object[]) lista.get(i);
            if ((Integer) obj[2] > 1) 
            {
                List li = this.getSession().createCriteria(AcActa.class).add(Restrictions.eq("Sec.Id", (Integer) obj[5])).add(Restrictions.eq("ActActivo", "1")).addOrder(Order.desc("ActTipo")).createCriteria("AcActaDetalles", "AcActaDetalles").add(Restrictions.eq("AcActaDetalles.AluId", alu_id)).add(Expression.eq("AcActaDetalles.ActdetTipoNota", "034002")).add(Restrictions.eq("AcActaDetalles.ActdetActivo", "1")).list();
                List list = Collections.synchronizedList(new LinkedList(((AcActa) li.get(0)).getAcActaDetalles()));
                for (int k = 0; k < list.size(); k++) 
                {
                    if (((AcActaDetalle) list.get(k)).getActdetTipoNota().equals("034002") && ((AcActaDetalle) list.get(k)).getAluId().equals(alu_id)) 
                    {
                        obj[1] = ((AcActaDetalle) list.get(k)).getActdetNota();
                    }
                }
            }
            if ((Float) obj[1] >= 10.5) 
            {
                aprobados.add((Integer) obj[0]);
            } else 
            {
                plancur = new AcPlanCurso();
                plancur = (AcPlanCurso) obj[4];
                desaprobados.add(plancur);
                desaprobados_id.add(((AcPlanCurso) obj[4]).getId());
            }
        }
        if (lista.size() == 0 || aprobados.size() == 0) 
        {
            aprobados.add(-1);
        }
        evaluacion.add(aprobados);
        evaluacion.add(desaprobados);
        evaluacion.add(desaprobados_id);
        return evaluacion;
    }

    @Override
    public List<AcPlanCurso> seleccionarcursosparaIngresantes(int alumno_id) throws Exception 
    {
        List list = this.getSession().createCriteria(AcAlumno.class).add(Restrictions.eq("Id", alumno_id)).list();
        List<AcPlanCurso> lista = new ArrayList<AcPlanCurso>();
        if (list.size() != 0) 
        {
            lista = this.getSession().createCriteria(AcPlanCurso.class).add(Restrictions.eq("PlancurCiclo", "006001")).add(Restrictions.eq("Plan.Id", ((AcAlumno) list.get(0)).getPlanIdActual())).add(Restrictions.eq("PlancurActivo", "1")).list();
        }
        return lista;
    }

    @Override
    public int numeroMatriculadosporSeccion(int sec_id) 
    {
        return this.getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("MatActivo", "1")).add(Restrictions.disjunction().add(Restrictions.eq("MatTipo", "022001")).add(Restrictions.eq("MatTipo", "022005"))).createCriteria("AcMatriculaCursos").add(Restrictions.eq("Sec.Id", sec_id)).add(Restrictions.eq("MatcurActivo", "1")).list().size();
    }

    @Override
    public void registrarMatricula(AcMatricula matricula, int tipoMatricula) 
    {
        getHibernateTemplate().saveOrUpdate(matricula);
        try 
        {
            if(matricula.getSemId()!=matricula.getAlu().getSemId())
                {
                    if (tipoMatricula == 1) 
                        { //1 para matricula
                            actualizarMonto(matricula.getAlu().getId(), matricula.getId());
                        } 
                    else if (tipoMatricula == 0) //0 --> para rectificacion de matricula
                        {
                        actualizarMonto(matricula.getAlu().getId(), matricula.getId());
                        }
                }  
        }
        catch (Exception ex) 
        {
            Logger.getLogger(HSMatriculaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void registrarMatricula(AcMatricula matricula,AcSemestre semestre, int tipoMatricula) 
    {
        DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        Date fecha_sistema;
        fecha_sistema= new Date();
         String fechaPlazo = format.format( fecha_sistema );
         
         if ( fecha_sistema.before(semestre.getSemFechaRetInc()) ) 
             System.out.println(" fecha_sistema es anterior a fechaPlazo.");
            else 
             System.out.println(" fecha_sistema NO es anterior a fechaPlazo.");

        getHibernateTemplate().saveOrUpdate(matricula);
        try 
        {
            if(matricula.getSemId()!=matricula.getAlu().getSemId())
                {
                    if (tipoMatricula == 1 && fecha_sistema.before(semestre.getSemFechaRetInc())) 
                        { //1 para matricula
                            actualizarMonto(matricula.getAlu().getId(), matricula.getId());
                        } 
                    else if (tipoMatricula == 0 && fecha_sistema.before(semestre.getSemFechaRetInc())) //0 --> para rectificacion de matricula
                        {
                            actualizarMonto(matricula.getAlu().getId(), matricula.getId());
                        }
                }  
        }
        catch (Exception ex) 
        {
            Logger.getLogger(HSMatriculaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarMonto(int p_alu_id, int p_mat_id) throws Exception 
    {
        Connection con = null;
        CallableStatement cs = null;
        DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");
        java.sql.Connection cn = dmds.getConnection();
        try 
        {
            cs = (CallableStatement) cn.prepareCall("call sp_actualizar_monto(?,?)");
            // Cargamos los parametros de entrada
            cs.setInt(1, p_alu_id);
            cs.setInt(2, p_mat_id);
            // Ejecutamos
            cs.execute();
            cs.close();
        } 
        catch (Exception e) 
        {
            //throw new Exception(e); //Propagamos la Excepcion
        } 
        finally 
        {
            if (cs != null) //Finalizamos cerrando el CallableStatement
            {
                cs.close();
            }
            if (con != null) //Finalizamos cerrando la conexion
            {
                con.close();
            }
        }
    }

    @Override
    public void imprimirMatricula() 
    {
//        List alumnos = this.getSession().createCriteria(AcAlumno.class).list();
//        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(alumnos);
        HashMap parameters = new HashMap();
        parameters.put("especialidad", new String("123"));
        Print print = new Print();
        byte[] bytes = print.cargar_reporte("/WEB-INF/reportes/hola.jasper", parameters).toByteArray();
        InputStream in = new ByteArrayInputStream(bytes);
        print.imprimir(in);
    }

    @Override
    public List seleccionarMatriculas(int sec_id) {
        return this.getSession().createCriteria(AcMatricula.class, "matricula").
                add(Restrictions.eq("MatActivo", "1")).
                add(Restrictions.eq("MatTipo", "022001")).
                createCriteria("matricula.AcMatriculaCursos").
                add(Restrictions.eq("Sec.Id", sec_id)).
                createCriteria("matricula.Alu", "alumno").
                add(Restrictions.eq("alumno.AluTipo", "009001")).
                addOrder(Order.asc("alumno.AluAppaterno")).
                addOrder(Order.asc("alumno.AluApmaterno")).
                addOrder(Order.asc("alumno.AluNombres")).list();
    }

    @Override
    public List listarCursosMatriculados(int id, int semestre) 
    {
        return getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("Alu.Id", id)).add(Restrictions.eq("MatActivo", "1")).add(Restrictions.not(Restrictions.eq("MatTipo", "022004"))).add(Restrictions.eq("semId", semestre)).list();
    }

    @Override
    public List listarCursosPorMatricula(int mat_id)
    {
        return getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("Id", mat_id)).add(Restrictions.eq("MatActivo", "1")).list();
    }

    /*
     * public List listarCursosPorMatricula(int mat_id) { return
     * getSession().createCriteria(AcMatricula.class,"matricula").
     * add(Restrictions.eq("Id", mat_id)).
     * add(Restrictions.eq("matricula.MatActivo", "1")).
     * createCriteria("AcMatriculaCursos"). add(Restrictions.eq("MatcurActivo",
     * "0")).list();
    }
     */
    @Override
    public void EliminarMatricula(int mat_id) 
    {
        String hqlUpdate = "update AcMatricula set MatActivo = :activo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("activo", "0").setInteger("id", mat_id).executeUpdate();
    }

    @Override
    public void RectificarMatricula(int mat_id) 
    {
        String hqlUpdate = "update AcMatricula set MatTipo = :tipo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("tipo", "022004").setInteger("id", mat_id).executeUpdate();
    }

    @Override
    public void RectificarPreMatricula(int mat_id) 
    {
        String hqlUpdate = "update AcMatricula set MatTipo = :tipo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("tipo", "022006").setInteger("id", mat_id).executeUpdate();
    }

    @Override
    public void ReservarMatricula(int mat_id) 
    {
        String hqlUpdate = "update AcMatricula set MatTipo = :tipo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("tipo", "022007").setInteger("id", mat_id).executeUpdate();
    }

    @Override
    public void RetiroCiclo(int mat_id) 
    {
        String hqlUpdate = "update AcMatricula set MatTipo = :tipo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("tipo", "022008").setInteger("id", mat_id).executeUpdate();
    }

    @Override
    public void RetiroUniveridad(int mat_id) 
    {
        String hqlUpdate = "update AcMatricula set MatTipo = :tipo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("tipo", "022003").setInteger("id", mat_id).executeUpdate();
    }

    @Override
    public List GetBloqueoWeb(int sec_id) 
    {
        return this.getSession().createCriteria(TbBloqueoWeb.class).add(Restrictions.eq("sec_id", sec_id)).list();
    }

    @Override
    public void ChangeBloqueoWeb(int sec_id) 
    {
        TbBloqueoWeb tb = (TbBloqueoWeb) (this.getSession().createCriteria(TbBloqueoWeb.class).add(Restrictions.eq("sec_id", sec_id)).add(Restrictions.eq("bloweb_activo", "1")).list().get(0));
        if (tb != null) 
        {
            if (tb.getBloweb_estado().equals("038001")) 
            {
                tb.setBloweb_estado("038002");
            } else 
            {
                tb.setBloweb_estado("038001");
            }
        }
        getHibernateTemplate().update(tb);
    }

    @Override
    public List seleccionarMatriculaLectiva(int alu_id, int sem_id) 
    {
        return this.getSession().createCriteria(AcMatricula.class).add(Restrictions.not(Restrictions.eq("MatTipo", "022004"))).add(Restrictions.eq("Alu.Id", alu_id)).add(Restrictions.eq("semId", sem_id)).add(Restrictions.eq("MatActivo", "1")).list();
    }

    @Override
    public List seleccionarMatricularRegular(int alu_id, int sem_id) 
    {
        return this.getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("MatTipo", "022001")).add(Restrictions.eq("Alu.Id", alu_id)).add(Restrictions.eq("semId", sem_id)).add(Restrictions.eq("MatActivo", "1")).list();
    }

    @Override
    public int seleccionarIdMatricularRegular(int alu_id, int sem_id) 
    {
        int id = 0;
        AcMatricula matricula = (AcMatricula) this.getSession().createCriteria(AcMatricula.class).
                add(Restrictions.eq("Alu.Id", alu_id)).
                add(Restrictions.eq("semId", sem_id)).
                add(Restrictions.eq("MatActivo", "1")).
                add(Restrictions.eq("MatTipo", "022001")).uniqueResult();

        if (matricula != null) 
        {
            id = matricula.getId();
        }

        return id;
    }

    @Override
    public List seleccionarMatriculaReserva(int alu_id, int sem_id) 
    {
        return this.getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("MatTipo", "022002")).add(Restrictions.eq("Alu.Id", alu_id)).add(Restrictions.eq("semId", sem_id)).add(Restrictions.eq("MatActivo", "1")).list();
    }

    @Override
    public List seleccionarPrematricula(int alu_id, int sem_id) 
    {
        return this.getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("MatTipo", "022005")).add(Restrictions.eq("Alu.Id", alu_id)).add(Restrictions.eq("semId", sem_id)).add(Restrictions.eq("MatActivo", "1")).list();
    }

    @Override
    public int numeroMatriculadosporSeccion_sin_alumno(int sec_id, int alu_id) 
    {
        return this.getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("MatActivo", "1")).add(Restrictions.disjunction().add(Restrictions.eq("MatTipo", "022001")).add(Restrictions.eq("MatTipo", "022005"))).add(Restrictions.not(Restrictions.eq("Alu.Id", alu_id))).createCriteria("AcMatriculaCursos").add(Restrictions.eq("Sec.Id", sec_id)).add(Restrictions.eq("MatcurActivo", "1")).list().size();
    }

    @Override
    public AcMatricula seleccionarMatriculaAlumnoConvalidacion(int alu_id) 
    {
        return (AcMatricula) this.getSession().createCriteria(AcMatricula.class).
                add(Restrictions.eq("MatActivo", "1")).
                add(Restrictions.eq("MatTipo", "022001")).
                add(Restrictions.eq("semId", 5)).
                add(Restrictions.eq("Alu.Id", alu_id)).uniqueResult();
    }

    @Override
    public void insertarMatriculaCurso(List<AcMatriculaCurso> acMatriculaCurso) 
    {
        this.getHibernateTemplate().saveOrUpdateAll(acMatriculaCurso);
    }

    @Override
    public void eliminarMatriculaCurso(AcMatriculaCurso acMatriculaCurso) 
    {
        String hql = "update AcMatriculaCurso set MatcurActivo= :v_activo where Mat.Id = :v_mat_id and Sec.Id = :v_sec_id";
        this.getSession().createQuery(hql).setString("v_activo", "0").setInteger("v_mat_id", acMatriculaCurso.getMat().getId()).
                setInteger("v_sec_id", acMatriculaCurso.getSec().getId()).executeUpdate();
    }

    @Override
    public List<BeanCursosMatricular> listarCursosAmatricular(int alu_id, int sem_id) 
    {
        List<BeanCursosMatricular> listaCu = new ArrayList<BeanCursosMatricular>();
        String hql = "call sp_cursos_a_matricular(:v_alu_id,:v_sem_id)";
        List lista = this.getSession().createSQLQuery(hql).setInteger("v_alu_id", alu_id).setInteger("v_sem_id", sem_id).list();
        for (int i = 0; i < lista.size(); i++) 
        {
            Object[] obj = (Object[]) lista.get(i);
            BeanCursosMatricular cursos = new BeanCursosMatricular();
            cursos.setCurapeId((Integer) obj[0]);
            cursos.setPlanCodigo(obj[1].toString());
            cursos.setPlancurCodigo(obj[2].toString());
            //System.out.println("setPlancurCodigo -> " +obj[2]+" \t"+obj[2].toString()+"\t -> "+obj);
            cursos.setPlanTipo(obj[3].toString());
            cursos.setCiclo(obj[4].toString());
            cursos.setPlancurCiclo(obj[5].toString());
            cursos.setCurNombre(obj[6].toString());
            cursos.setEspId((Integer) obj[7]);
            cursos.setPlancurCredito(Integer.parseInt(obj[8].toString()));
            cursos.setPre("");
            if (obj[9].toString() != null) 
            {
                cursos.setPre(obj[9].toString());
            }

            cursos.setAluId(Integer.parseInt(obj[10].toString()));
            cursos.setRepitencia(Integer.parseInt(obj[11].toString()));
            cursos.setSecId(Integer.parseInt(obj[12].toString()));
            cursos.setPlancurId(Integer.parseInt(obj[13].toString()));
            cursos.setSemId(sem_id);
            // cursos.setCboSecciones();
            listaCu.add(cursos);
        }
        return listaCu;
    }

    @Override
    public int numeroMatriculadosporSeccionFun(int sec_id) 
    {
        int cantidad = 0;
        String hql = "select fun_cantidad_por_seccion(:v_secId)";
        Object obj = this.getSession().createSQLQuery(hql).setInteger("v_secId", sec_id).uniqueResult();
        cantidad = Integer.parseInt(obj.toString());
        return cantidad;
    }

    public int numeroMatriculadasId(int alu_id, int sem_id) 
    {
        //return this.getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("MatActivo", "1")).add(Restrictions.disjunction().add(Restrictions.eq("MatTipo", "022001")).add(Restrictions.eq("MatTipo", "022005"))).list().size();
        return this.getSession().createCriteria(AcMatricula.class).add(Restrictions.eq("MatTipo", "022001")).add(Restrictions.eq("Alu.Id", alu_id)).add(Restrictions.eq("semId", sem_id)).add(Restrictions.eq("MatActivo", "1")).list().size();
    }
}
