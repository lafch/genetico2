package net.uch.academica.hibernateSpringDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.academica.managedBeans.beans.BeanDuplicadoHorario;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccion;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.AcHorario;
import net.uch.mapping.AcHorarioDispDocente;
import net.uch.mapping.AcHorarioGen;
import net.uch.mapping.ClArbolAcademico;
import net.uch.util.CommonDAO;
import net.uch.util.ConstantesWeb;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSHorarioGenDAOImpl extends HibernateDaoSupport implements HSHorarioGenDAO {

    @Override
    public void insertarHorarios(List<AcHorarioGen> horarios) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdateAll(horarios);
    }
    
    @Override
    public void eliminarHorarios() throws DataAccessException {
        String hqlUpdate = "delete from AcHorarioGen";
        this.getSession().createQuery(hqlUpdate).executeUpdate();
    }
    
    @Override
    public void insertarHorariosDispDoc(List<AcHorarioDispDocente> horarios) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdateAll(horarios);
    }


    
    @Override
    public List seleccionarHorarioDocente(int doc) throws DataAccessException {
        return this.getSession().createCriteria(AcHorarioDispDocente.class).
                add(Expression.eq("horActivo", "1")).
                add(Expression.eq("acDocente.Id", doc)).list();
    }
    
    @Override
    public List seleccionarHorarioGen() throws DataAccessException {
        return  this.getSession().createCriteria( AcHorarioGen.class ).setProjection( Projections.projectionList().
                add( Projections.groupProperty( "generacion" ) ) ).
                add( Restrictions.eq( "HorActivo", "1" ) ).
                list();
//                        add(Projections.groupProperty("usuId"))).list();
    }
    
    @Override
    public List seleccionarHorarioGenPorNumeroGeneracion(int generacion) throws DataAccessException {
        return this.getSession().createCriteria(AcHorarioGen.class).
                add(Expression.eq("HorActivo", "1")).
                add(Expression.eq("generacion", generacion)).list();
    }

    @Override
    public AcHorario seleccionarUnHorario(int id_hor) throws DataAccessException {
        return (AcHorario) this.getSession().createCriteria(AcHorario.class).add(Expression.eq("HorActivo", "1")).add(Expression.eq("Id", id_hor)).uniqueResult();
    }
    
    @Override
    public List<BeanReporte> listarDocentesPorCicloSemestreFacultad( int semestre, int facultad, int especialidad, String ciclo, String turno) {
        int iSizeSecc;
        String sSqlQuery;
      
        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_docente_ciclo(:v_sem_id,:v_fac_id,:v_esp_id,:v_ciclo,:v_turno);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery( sSqlQuery ).
                    setInteger( "v_sem_id", semestre ).
                    setInteger( "v_fac_id", facultad ).
                    setInteger( "v_esp_id", especialidad ).
                    setString("v_ciclo", ciclo ).
                    setString("v_turno", turno ).
                    list();
        } catch ( Exception ex ) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for ( int i = 0; i < iSizeSecc; i++ ) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get( i );

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString() );
            docentes.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString() );




            lstSeccXAlumMod.add( docentes );
        }

        return lstSeccXAlumMod;
    }
    
    
     @Override
    public List<BeanReporte> listarCursosPorCicloSemestreFacultad( int semestre, int facultad, int especialidad, String ciclo, String turno) {
        int iSizeSecc;
        String sSqlQuery;
      
        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_curso_ciclo(:v_sem_id,:v_fac_id,:v_esp_id,:v_ciclo,:v_turno);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery( sSqlQuery ).
                    setInteger( "v_sem_id", semestre ).
                    setInteger( "v_fac_id", facultad ).
                    setInteger( "v_esp_id", especialidad ).
                    setString("v_ciclo", ciclo ).
                    setString("v_turno", turno ).
                    list();
        } catch ( Exception ex ) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for ( int i = 0; i < iSizeSecc; i++ ) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get( i );

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_ID_GENERADO].toString() );
            docentes.setExpr2(obj[ConstantesWeb.INDX_OBJ_ID_DOCENTE].toString() );
            docentes.setExpr3(obj[ConstantesWeb.INDX_OBJ_ID_CURSO_SEC].toString() );
            docentes.setExpr4(obj[ConstantesWeb.INDX_OBJ_CANT_HORAS_CURSO].toString() );
            lstSeccXAlumMod.add( docentes );
        }

        return lstSeccXAlumMod;
    }
    
    
      @Override
    public List<BeanReporte> listarHorarioPorSemestreTurno(int semestre,int turno) {
        int iSizeSecc;
        String sSqlQuery;
      
        BeanReporte turnos;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_bloques_horario(:v_sem_id,:v_tur_id);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery( sSqlQuery ).
                    setInteger( "v_sem_id", semestre ).
                    setInteger( "v_tur_id", turno ).
                    list();
        } catch ( Exception ex ) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for ( int i = 0; i < iSizeSecc; i++ ) {
            turnos = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get( i );

            turnos.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString() );
            turnos.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString() );
            turnos.setExpr3(obj[ConstantesWeb.INDX_OBJ_SEC_NOMBRE].toString() );



            lstSeccXAlumMod.add( turnos );
        }

        return lstSeccXAlumMod;
    }
    @Override
     public List<BeanReporte> listarDisponibilidadDocentePorCicloSemestreFacultad( int semestre, int facultad, int especialidad, String ciclo, int turno, String tur_desc){
          int iSizeSecc;
        String sSqlQuery;
      
        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_docente_disponibilidad(:v_sem_id,:v_fac_id,:v_esp_id,:v_ciclo,:v_turno,:v_tur_desc);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery( sSqlQuery ).
                    setInteger( "v_sem_id", semestre ).
                    setInteger( "v_fac_id", facultad ).
                    setInteger( "v_esp_id", especialidad ).
                    setString("v_ciclo", ciclo ).
                    setInteger("v_turno", turno ).
                    setString("v_tur_desc", tur_desc ).
                    list();
        } catch ( Exception ex ) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for ( int i = 0; i < iSizeSecc; i++ ) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get( i );

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString() );
            docentes.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString() );
            

            lstSeccXAlumMod.add( docentes );
        }

        return lstSeccXAlumMod;
     }
    
    @Override
    public AcHorarioDispDocente seleccionarUnHorario2(int id_hor) throws DataAccessException {
        return (AcHorarioDispDocente) this.getSession().createCriteria(AcHorarioDispDocente.class).add(Expression.eq("horActivo", "1")).add(Expression.eq("horId", id_hor)).uniqueResult();
    }

    @Override
    public List duplicidadHoraria(String sec_ids) throws DataAccessException {
        List<BeanDuplicadoHorario> lista = new ArrayList<BeanDuplicadoHorario>();

        String sql = "select master.hor_dia as horDia, "
                + "master.turdet_hora as turdetHora, "
                + "master.cur_nombre as curNombre, "
                + "master.cadena as candenaCurso, "
                + "master.cantidad as cantidadCurso "
                + "from( "
                + "select tabla.sec_id, tabla.hor_dia, tabla.turdet_hora, tabla.cur_nombre, group_concat(tabla.cur_nombre separator ' <br /> ' ) cadena  ,count(*) cantidad "
                + "from ( "
                + "select ac_seccion.sec_id, ac_seccion.sec_nombre, ac_horario.hor_dia, ac_turno_detalle.turdet_hora, ac_turno_detalle.turdet_id, ac_turno.tur_id, concat(ac_seccion.sec_nombre,' ',ac_curso.cur_nombre) cur_nombre, count(*) cantidad "
                + "from ac_seccion inner join ac_horario on ac_seccion.sec_id=ac_horario.sec_id "
                + "inner join ac_turno_detalle on ac_turno_detalle.turdet_id=ac_horario.turdet_id "
                + "inner join ac_turno on ac_turno.tur_id=ac_turno_detalle.tur_id "
                + "inner join ac_curso_aperturado on ac_curso_aperturado.curape_Id=ac_seccion.curape_id "
                + "inner join ac_plan_curso on ac_plan_curso.plancur_Id=ac_curso_aperturado.plancur_id "
                + "inner join ac_curso on ac_curso.cur_id=ac_plan_curso.cur_id "
                + "inner join ac_semestre on ac_semestre.sem_id=ac_curso_aperturado.sem_id "
                + "where ac_seccion.sec_activo='1' "
                + "and ac_turno_detalle.turdet_activo='1' "
                + "and ac_turno.tur_activo='1' "
                + "and ac_horario.hor_activo='1' "
                + "and ac_curso_aperturado.curape_activo='1' "
                + "and ac_curso_aperturado.curape_aperturado='1' "
                + "and ac_curso_aperturado.sem_id=19 "
                + "and ac_plan_curso.plancur_activo='1' "
                + "and ac_semestre.sem_activo='1' "
                + "and ac_semestre.sem_actual='1' "
                + "and ac_curso.cur_activo='1' "
                + "and ac_seccion.sec_id in(" + sec_ids + ") "
                + "group by ac_seccion.sec_id, ac_horario.hor_dia, ac_turno_detalle.turdet_hora) tabla "
                + "group by tabla.hor_dia, tabla.turdet_hora) master where master.cantidad>1 "
                + "group by master.sec_id "
                + "order by master.hor_dia, master.turdet_hora; ";

        // System.out.println(sql);//master.hor_dia, master.turdet_hora, master.cur_nombre, master.cadena, master.cantidad
        Query q = this.getSession().createSQLQuery(sql).
                addScalar("horDia", Hibernate.STRING).
                addScalar("turdetHora", Hibernate.STRING).
                addScalar("curNombre", Hibernate.STRING).
                addScalar("candenaCurso", Hibernate.STRING);


        List l = q.list();
        for (int i = 0; i < l.size(); i++) {
            BeanDuplicadoHorario dup = new BeanDuplicadoHorario();
            Object[] objs = (Object[]) l.get(i);
            //for (int j = 0; j < objs.length; j++) {
            dup.setDup_dia(objs[0].toString());
            dup.setDup_hora(objs[1].toString());
            dup.setDup_nombreCurso(objs[2].toString());
            dup.setDup_nombreCursos(objs[3].toString());
            dup.setDup_id(i + 1);
            //System.out.print(object.toString() + "\t");
            // }
           /* System.out.println();
            System.out.println("valor");*/
            lista.add(dup);
        }
        // System.out.println("En total" + l.size());
        return lista;

    }

    @Override
    public int SeleccionarHorarioPorDocente(int doc_id, int sem_id) throws DataAccessException {
        String sql = "select count(*) cantidad \n"
                + "from ac_docente inner join ac_horario on ac_docente.doc_id=ac_horario.doc_id \n"
                + "inner join ac_seccion on ac_seccion.sec_id=ac_horario.sec_id \n"
                + "inner join ac_curso_aperturado on ac_curso_aperturado.curape_Id=ac_seccion.curape_id \n"
                + "inner join ac_turno_detalle on ac_turno_detalle.turdet_id=ac_horario.turdet_id \n"
                + "inner join ac_turno on ac_turno.tur_id=ac_turno_detalle.tur_id \n"
                + "where ac_turno_detalle.turdet_activo='1' \n"
                + "and ac_turno.tur_activo='1' \n"
                + "and ac_seccion.sec_activo='1' \n"
                + "and ac_curso_aperturado.curape_activo='1' \n"
                + "and ac_curso_aperturado.curape_aperturado='1' \n"
                + "and ac_curso_aperturado.sem_id=" + sem_id
                + " and ac_docente.doc_id=" + doc_id;
        Query q = this.getSession().createSQLQuery(sql).
                addScalar("cantidad", Hibernate.INTEGER);
        int valor = Integer.parseInt(q.uniqueResult().toString());
        return valor;
    }

    @Override
    public List seleccionarHorarioGen(int sec) throws DataAccessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
