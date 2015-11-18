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
import net.uch.util.CommonWeb;
import net.uch.util.ConstantesWeb;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Expression;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSHorarioDAOImpl extends HibernateDaoSupport implements HSHorarioDAO {

    @Override
    public void insertarHorarios(List<AcHorario> horarios) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdateAll(horarios);
    }

    @Override
    public void insertarHorariosDispDoc(List<AcHorarioDispDocente> horarios) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdateAll(horarios);
    }

    @Override
    public List seleccionarHorario(int sec) throws DataAccessException {
        return this.getSession().createCriteria(AcHorario.class).
                add(Expression.eq("HorActivo", "1")).
                add(Expression.eq("Sec.Id", sec)).list();
    }

    @Override
    public List seleccionarHorarioSeccion(int sec_id) throws DataAccessException {

        String sQuery;
        sQuery = " select * from ac_horario where sec_id in ( \n"
                + " select sec_id from ac_seccion sec where \n"
                + " sec.sec_nombre=(select sec_nombre from ac_seccion where sec_id=:v_sec_id) \n"
                + " and curape_id in( \n"
                + " select curape_id from ac_curso_aperturado ca \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " INNER JOIN ( \n"
                + " select ca.sem_id,pc.plancur_ciclo,c.esp_id from ac_seccion s \n"
                + " INNER JOIN ac_curso_aperturado  ca on ca.curape_Id=s.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " where s.sec_id=:v_sec_id \n"
                + " ) tblCursoS ON tblCursoS.sem_id=ca.sem_id and tblCursoS.plancur_ciclo=pc.plancur_ciclo and tblCursoS.esp_id=c.esp_id \n"
                + " where ca.curape_activo=1 and pc.plancur_activo=1 and c.cur_activo=1)) \n"
                + " and hor_activo=1 ";

        return this.getSession().createSQLQuery(sQuery).addEntity(AcHorario.class).setInteger("v_sec_id", sec_id).list();

        //return this.getSession().createCriteria(AcAlumno.class).add(Expression.eq("Esp.Id", id_esp)).add(Expression.eq("AluTipo", tipo)).add(Expression.eq("AluActivo", "1")).add(Restrictions.eq("SemId", sem_id)).list();
    }

    @Override
    public List HorarioDisponibleLaboratorio(int sec_id) throws DataAccessException {
        String sQuery;
        sQuery = " select hor_id,hor_dia,hor_activo,aul_id,sec_id,turdet_id,hor_tipo_clase,hor_creacion,doc_id,usu_id \n"
                + " FROM (\n"
                + " select tblDisponibilidadSeccion.*, COUNT(tblLaboratorioAsignado.turdet_id) contar \n"
                + " FROM (select horario.* \n"
                + " from ac_seccion sec \n"
                + " INNER JOIN ac_horario horario ON sec.sec_id=horario.sec_id \n"
                + " INNER JOIN ac_curso_aperturado cap ON cap.curape_Id=sec.curape_id \n"
                + " INNER JOIN ac_plan_curso pcur ON cap.plancur_id=pcur.plancur_Id \n"
                + " INNER JOIN ac_curso cur on cur.cur_id=pcur.cur_id \n"
                + " WHERE sec.sec_activo=1 and horario.hor_activo=1 and cap.curape_activo=1 \n"
                + " and pcur.plancur_activo=1 and cur.cur_activo=1 and sec.sec_id=:v_sec_id \n"
                + " ) tblDisponibilidadSeccion \n"
                + " LEFT JOIN ( \n"
                + " select horario.* \n"
                + " FROM ac_horario horario \n"
                + " INNER JOIN ac_seccion sec ON sec.sec_id=horario.sec_id \n"
                + " INNER JOIN ac_aula aul ON horario.aul_id=aul.aul_id \n"
                + " INNER JOIN ac_curso_aperturado ca ON ca.curape_Id=sec.curape_id \n"
                + " INNER JOIN ( \n"
                + " select tdet.* \n"
                + " from ac_seccion sec \n"
                + " INNER JOIN ac_horario horario ON horario.sec_id=sec.sec_id \n"
                + " INNER JOIN ac_turno_detalle tdet ON horario.turdet_id=tdet.turdet_id \n"
                + " INNER JOIN ac_turno t ON tdet.tur_id=t.tur_id \n"
                + " WHERE sec.sec_activo=1 and tdet.turdet_activo=1 and t.tur_activo=1 and horario.hor_activo=1 and sec.sec_id=:v_sec_id \n"
                + " ) td ON td.turdet_id=horario.turdet_id \n"
                + " INNER JOIN ac_turno t ON t.tur_id=td.tur_id \n"
                + " WHERE horario.hor_activo=1 and sec.sec_activo=1 and aul.aul_activo=1 and ca.curape_activo=1 \n"
                + " and td.turdet_activo=1 and t.tur_activo=1 and aul.aul_tipo='098002'"
                + " GROUP BY aul_id,hor_dia,turdet_id \n"
                + " ) tblLaboratorioAsignado \n"
                + " ON tblLaboratorioAsignado.hor_dia=tblDisponibilidadSeccion.hor_dia and tblLaboratorioAsignado.turdet_id=tblDisponibilidadSeccion.turdet_id \n"
                + " GROUP BY tblDisponibilidadSeccion.hor_dia,tblDisponibilidadSeccion.turdet_id \n"
                + " ) tblDisponibilidadLaboratorio \n"
                + " WHERE tblDisponibilidadLaboratorio.contar <> (select count(a.aul_id) numLab from ac_aula a where a.aul_activo=1 and a.aul_tipo='098002')";

        return this.getSession().createSQLQuery(sQuery).addEntity(AcHorario.class).setInteger("v_sec_id", sec_id).list();
    }

    @Override
    public List HorarioNoDisponibleDocente(int doc_id, int sec_id) throws DataAccessException {
        String sQuery;
        sQuery = " select h.* from ac_horario h \n"
                + " INNER JOIN ac_seccion sec ON h.sec_id=sec.sec_id \n"
                + " INNER JOIN ac_curso_aperturado ac ON ac.curape_Id=sec.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ac.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " INNER JOIN ( \n"
                + " select ca.sem_id,pc.plancur_ciclo,c.esp_id from ac_seccion s \n"
                + " INNER JOIN ac_curso_aperturado  ca on ca.curape_Id=s.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " where s.sec_id=:v_sec_id \n"
                + " ) tblCursoS ON tblCursoS.sem_id=ac.sem_id and tblCursoS.esp_id=c.esp_id \n"
                + " where doc_id=:v_doc_id and hor_activo=1 and concat(RIGHT(h.hor_dia,1),h.turdet_id) not in( \n"
                + " select concat(RIGHT(hor_dia,1),turdet_id) horadia from ac_horario where sec_id in ( \n"
                + " select sec_id from ac_seccion sec where \n"
                + " sec.sec_nombre=(select sec_nombre from ac_seccion where sec_id=:v_sec_id) \n"
                + " and curape_id in( \n"
                + " select curape_id from ac_curso_aperturado ca \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " INNER JOIN ( \n"
                + " select ca.sem_id,pc.plancur_ciclo,c.esp_id from ac_seccion s \n"
                + " INNER JOIN ac_curso_aperturado  ca on ca.curape_Id=s.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " where s.sec_id=:v_sec_id \n"
                + " ) tblCursoS ON tblCursoS.sem_id=ca.sem_id and tblCursoS.plancur_ciclo=pc.plancur_ciclo and tblCursoS.esp_id=c.esp_id \n"
                + " where ca.curape_activo=1 and pc.plancur_activo=1 and c.cur_activo=1) )\n"
                + " and hor_activo=1 \n"
                + " ORDER BY 1);";

        return this.getSession().createSQLQuery(sQuery).addEntity(AcHorario.class).setInteger("v_sec_id", sec_id).setInteger("v_doc_id", doc_id).list();
    }

    @Override
    public List HorarioSiDisponibleDocente(int doc_id, int sec_id, int tur_id) throws DataAccessException {
        String sQuery;
        sQuery = " select hdd.* \n"
                + " from ac_horario_disp_docente hdd\n"
                + " INNER JOIN ac_turno_detalle td ON td.turdet_id=hdd.turdet_id \n"
                + " INNER JOIN ac_turno t ON t.tur_id=td.tur_id \n"
                + " where doc_id=:v_doc_id and hdd.hor_activo=1 and td.turdet_activo=1 \n"
                + " and t.tur_id=:v_tur_id and concat(RIGHT(hdd.hor_dia,1),hdd.turdet_id) not in( \n"
                + " select concat(RIGHT(hor_dia,1),turdet_id) horadia from ac_horario where sec_id in ( \n"
                + " select sec_id from ac_seccion sec where \n"
                + " sec.sec_nombre=(select sec_nombre from ac_seccion where sec_id=:v_sec_id) \n"
                + " and curape_id in( \n"
                + " select curape_id from ac_curso_aperturado ca \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " INNER JOIN ( \n"
                + " select ca.sem_id,pc.plancur_ciclo,c.esp_id from ac_seccion s \n"
                + " INNER JOIN ac_curso_aperturado  ca on ca.curape_Id=s.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id\n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " where s.sec_id=:v_sec_id \n"
                + " ) tblCursoS ON tblCursoS.sem_id=ca.sem_id and tblCursoS.plancur_ciclo=pc.plancur_ciclo and tblCursoS.esp_id=c.esp_id \n"
                + " where ca.curape_activo=1 and pc.plancur_activo=1 and c.cur_activo=1) )\n"
                + " and hor_activo=1 \n"
                + " UNION \n"
                + " select concat(RIGHT(h.hor_dia,1),turdet_id) from ac_horario h \n"
                + " INNER JOIN ac_seccion sec ON h.sec_id=sec.sec_id \n"
                + " INNER JOIN ac_curso_aperturado ac ON ac.curape_Id=sec.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ac.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " INNER JOIN ( \n"
                + " select ca.sem_id,pc.plancur_ciclo,c.esp_id from ac_seccion s \n"
                + " INNER JOIN ac_curso_aperturado  ca on ca.curape_Id=s.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " where s.sec_id=:v_sec_id \n"
                + " ) tblCursoS ON tblCursoS.sem_id=ac.sem_id and tblCursoS.esp_id=c.esp_id \n"
                + " where doc_id=:v_doc_id and hor_activo=1 and concat(RIGHT(h.hor_dia,1),h.turdet_id) not in( \n"
                + " select concat(RIGHT(hor_dia,1),turdet_id) horadia from ac_horario where sec_id in ( \n"
                + " select sec_id from ac_seccion sec where \n"
                + " sec.sec_nombre=(select sec_nombre from ac_seccion where sec_id=:v_sec_id) \n"
                + " and curape_id in( \n"
                + " select curape_id from ac_curso_aperturado ca \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " INNER JOIN ( \n "
                + " select ca.sem_id,pc.plancur_ciclo,c.esp_id from ac_seccion s \n"
                + " INNER JOIN ac_curso_aperturado  ca on ca.curape_Id=s.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " where s.sec_id=:v_sec_id \n"
                + " ) tblCursoS ON tblCursoS.sem_id=ca.sem_id and tblCursoS.plancur_ciclo=pc.plancur_ciclo and tblCursoS.esp_id=c.esp_id \n"
                + " where ca.curape_activo=1 and pc.plancur_activo=1 and c.cur_activo=1) ) \n"
                + " and hor_activo=1 \n"
                + " ORDER BY 1)\n"
                + " )\n"
                + " ORDER BY 2,3,4";

        return this.getSession().createSQLQuery(sQuery).addEntity(AcHorarioDispDocente.class).setInteger("v_sec_id", sec_id).setInteger("v_doc_id", doc_id).setInteger("v_tur_id", tur_id).list();
    }

    @Override
    public List seleccionarHorarioDocente(int doc) throws DataAccessException {
        return this.getSession().createCriteria(AcHorarioDispDocente.class).
                add(Expression.eq("horActivo", "1")).
                add(Expression.eq("acDocente.Id", doc)).list();
    }

    @Override
    public List seleccionarHorarioGen(int sec) throws DataAccessException {
        return this.getSession().createCriteria(AcHorarioGen.class).
                add(Expression.eq("HorActivo", "1")).
                add(Expression.eq("Sec.Id", sec)).list();
    }

    @Override
    public AcHorario seleccionarUnHorario(int id_hor) throws DataAccessException {
        return (AcHorario) this.getSession().createCriteria(AcHorario.class).add(Expression.eq("HorActivo", "1")).add(Expression.eq("Id", id_hor)).uniqueResult();
    }

    @Override
    public List<BeanReporte> listarDocentesPorCicloSemestreFacultad(int semestre, int facultad, int especialidad, String ciclo, int turno, String turnoDes) {
        int iSizeSecc;
        String sSqlQuery;

        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_docente_ciclo(:v_ciclo,:v_sem_id,:v_esp_id,:v_turno,:v_turnodes,1);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery(sSqlQuery).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_esp_id", especialidad).
                    setString("v_ciclo", ciclo).
                    setInteger("v_turno", turno).
                    setString("v_turnodes",turnoDes).
                    list();
        } catch (Exception ex) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for (int i = 0; i < iSizeSecc; i++) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get(i);

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString());
            docentes.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString());

            lstSeccXAlumMod.add(docentes);
        }

        return lstSeccXAlumMod;
    }

    @Override
    public List<BeanReporte> numeroHorasDisponiblesSeccion(int seccion) {
        int iSizeSecc;
        String sSqlQuery;

        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = " select concat((cantidad_horas-cantidad),'') cantidadTotal, '','' from( \n"
                + " select  count(*) cantidad from ac_horario h where h.hor_activo=1 and h.sec_id=:v_sec_id) tblHorasDictadas,\n"
                + " ( \n"
                + " select (c.curhorlab+c.curhorteo) cantidad_horas \n"
                + " from ac_seccion sec \n"
                + " INNER JOIN ac_curso_aperturado ca ON ca.curape_Id=sec.curape_id \n"
                + " INNER JOIN ac_plan_curso pc ON pc.plancur_Id=ca.plancur_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id \n"
                + " WHERE sec.sec_activo=1 and ca.curape_activo=1 and pc.plancur_activo=1 and c.cur_activo=1 and sec.sec_id=:v_sec_id) tblHorasCurso ";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery(sSqlQuery).
                    setInteger("v_sec_id", seccion).
                    list();
        } catch (Exception ex) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for (int i = 0; i < iSizeSecc; i++) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get(i);

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString());

            lstSeccXAlumMod.add(docentes);
        }

        return lstSeccXAlumMod;
    }

    @Override
    public List<BeanReporte> listarCursosPorCicloSemestreFacultad(int semestre, int facultad, int especialidad, String ciclo, String turno) {
        int iSizeSecc;
        String sSqlQuery;

        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_curso_ciclo(:v_sem_id,:v_fac_id,:v_esp_id,:v_ciclo,:v_turno);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery(sSqlQuery).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_fac_id", facultad).
                    setInteger("v_esp_id", especialidad).
                    setString("v_ciclo", ciclo).
                    setString("v_turno", turno).
                    list();
        } catch (Exception ex) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for (int i = 0; i < iSizeSecc; i++) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get(i);

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_ID_GENERADO].toString());
            docentes.setExpr2(obj[ConstantesWeb.INDX_OBJ_ID_DOCENTE].toString());
            docentes.setExpr3(obj[ConstantesWeb.INDX_OBJ_ID_CURSO_SEC].toString());
            docentes.setExpr4(obj[ConstantesWeb.INDX_OBJ_CANT_HORAS_CURSO].toString());
            docentes.setExpr5(obj[ConstantesWeb.INDX_OBJ_ESTADOLAB_CURSO].toString());
            lstSeccXAlumMod.add(docentes);
        }

        return lstSeccXAlumMod;
    }

    @Override
    public List<BeanReporte> listarHorarioPorSemestreTurno(int semestre, int turno) {
        int iSizeSecc;
        String sSqlQuery;

        BeanReporte turnos;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_bloques_horario(:v_sem_id,:v_tur_id);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery(sSqlQuery).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_tur_id", turno).
                    list();
        } catch (Exception ex) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for (int i = 0; i < iSizeSecc; i++) {
            turnos = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get(i);

            turnos.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString());
            turnos.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString());
            turnos.setExpr3(obj[ConstantesWeb.INDX_OBJ_SEC_NOMBRE].toString());

            lstSeccXAlumMod.add(turnos);
        }

        return lstSeccXAlumMod;
    }

    @Override
    public List<BeanReporte> listarHorarioLabCopadoPorTurno(int turno) {
        int iSizeSecc;
        String sSqlQuery;

        BeanReporte horDiaLab;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstHorDiaLab = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_horario_laboratorio_copado(:v_tur_id);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery(sSqlQuery).
                    setInteger("v_tur_id", turno).
                    list();
        } catch (Exception ex) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for (int i = 0; i < iSizeSecc; i++) {
            horDiaLab = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get(i);

            horDiaLab.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString());
            horDiaLab.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString());
            horDiaLab.setExpr3(obj[ConstantesWeb.INDX_OBJ_SEC_NOMBRE].toString());

            lstHorDiaLab.add(horDiaLab);
        }

        return lstHorDiaLab;
    }

    @Override
    public List<BeanReporte> listarDisponibilidadDocentePorCicloSemestreFacultad(int semestre, int facultad, int especialidad, String ciclo, int turno, String tur_desc) {
        int iSizeSecc;
        String sSqlQuery;

        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_docente_disponibilidad(:v_sem_id,:v_fac_id,:v_esp_id,:v_ciclo,:v_turno,:v_tur_desc);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery(sSqlQuery).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_fac_id", facultad).
                    setInteger("v_esp_id", especialidad).
                    setString("v_ciclo", ciclo).
                    setInteger("v_turno", turno).
                    setString("v_tur_desc", tur_desc).
                    list();
        } catch (Exception ex) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for (int i = 0; i < iSizeSecc; i++) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get(i);

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString());
            docentes.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString());

            lstSeccXAlumMod.add(docentes);
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
    public List<BeanReporte> cantidadCursosAperturados(String ciclo, int semestre, int especialidad, String tur_desc) {
        int iSizeSecc;
        String sSqlQuery;

        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_num_cursos_aperturado(:v_ciclo,:v_sem_id,:v_esp_id,:v_tur_desc);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery(sSqlQuery).
                    setString("v_ciclo", ciclo).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_esp_id", especialidad).
                    setString("v_tur_desc", tur_desc).
                    list();
        } catch (Exception ex) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for (int i = 0; i < iSizeSecc; i++) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get(i);

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString());
            docentes.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString());

            lstSeccXAlumMod.add(docentes);
        }

        return lstSeccXAlumMod;
    }

    @Override
    public List<BeanReporte> cantidadCursosAsignados(String ciclo, int semestre, int especialidad, String tur_desc) {
        int iSizeSecc;
        String sSqlQuery;

        BeanReporte docentes;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanReporte> lstSeccXAlumMod = new ArrayList<BeanReporte>();
        sSqlQuery = "call usp_trae_num_cursos_asignado(:v_ciclo,:v_sem_id,:v_esp_id,:v_tur_desc);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery(sSqlQuery).
                    setString("v_ciclo", ciclo).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_esp_id", especialidad).
                    setString("v_tur_desc", tur_desc).
                    list();
        } catch (Exception ex) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for (int i = 0; i < iSizeSecc; i++) {
            docentes = new BeanReporte();
            obj = (Object[]) lstSeccXAlumModAux.get(i);

            docentes.setExpr1(obj[ConstantesWeb.INDX_OBJ_SEC_ID].toString());
            docentes.setExpr2(obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID].toString());

            lstSeccXAlumMod.add(docentes);
        }

        return lstSeccXAlumMod;
    }

    @Override
    public List<BeanReporte> cantidadDocentesDisponibilidad(String ciclo, int semestre, int especialidad, int tur_id, String tur_desc) {
        int iSizeDocDisp;
        int iSizeDocSinDisp;
        String sSqlQueryDisponibles;
        String sSqlQueryNoDisponibles;

        BeanReporte docentes;
        List lstDocentesConDisponibilidad;
        List lstDocentesSinDisponibilidad;
        List<BeanReporte> lstNumDocConySinDis = new ArrayList<BeanReporte>();
//        sSqlQuery = "call usp_trae_num_docentes_sin_disponibilidad(:v_ciclo,:v_sem_id,:v_esp_id,:v_tur_id,:v_tur_desc);";
        sSqlQueryDisponibles  =  "call usp_trae_num_docentes_sin_disponibilidad(:v_ciclo,:v_sem_id,:v_esp_id,:v_tur_id,:v_tur_desc,1)";
        sSqlQueryNoDisponibles = "call usp_trae_num_docentes_sin_disponibilidad(:v_ciclo,:v_sem_id,:v_esp_id,:v_tur_id,:v_tur_desc,0)";
        try {
            lstDocentesConDisponibilidad = this.getSession().
                    createSQLQuery(sSqlQueryDisponibles).
                    setString("v_ciclo", ciclo).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_esp_id", especialidad).
                    setInteger("v_tur_id", tur_id).
                    setString("v_tur_desc", tur_desc).
                    list();
            
        } catch (IllegalStateException ex) {
            lstDocentesConDisponibilidad = new ArrayList();
        } catch (HibernateException ex) {
            lstDocentesConDisponibilidad = new ArrayList();
        } catch (DataAccessResourceFailureException ex) {
            lstDocentesConDisponibilidad = new ArrayList();
        }
        
        try {
            lstDocentesSinDisponibilidad = this.getSession().
                    createSQLQuery(sSqlQueryNoDisponibles).
                    setString("v_ciclo", ciclo).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_esp_id", especialidad).
                    setInteger("v_tur_id", tur_id).
                    setString("v_tur_desc", tur_desc).
                    list();
        } catch (IllegalStateException e) {
            lstDocentesSinDisponibilidad = new ArrayList();
        } catch (HibernateException e) {
            lstDocentesSinDisponibilidad = new ArrayList();
        } catch (DataAccessResourceFailureException e) {
            lstDocentesSinDisponibilidad = new ArrayList();
        }

        iSizeDocDisp    = lstDocentesConDisponibilidad.size();
        iSizeDocSinDisp = lstDocentesSinDisponibilidad.size();

        docentes = new BeanReporte();

        docentes.setExpr1(String.valueOf(iSizeDocDisp));
        docentes.setExpr2(String.valueOf(iSizeDocSinDisp));

        lstNumDocConySinDis.add(docentes);

        return lstNumDocConySinDis;
    }
    
    @Override
    public List<BeanReporte> cantidadCursosSinDisponibilidad(String ciclo, int semestre, int especialidad, int tur_id, String tur_desc) {
        int iSizeDocDisp;
        int iSizeDocSinDisp;
        String sSqlQueryNoDisponibles;

        BeanReporte docentes;
        List lstDocentesSinDisponibilidad;
        List<BeanReporte> lstNumCurSinDis = new ArrayList<BeanReporte>();
//        sSqlQuery = "call usp_trae_num_docentes_sin_disponibilidad(:v_ciclo,:v_sem_id,:v_esp_id,:v_tur_id,:v_tur_desc);";
        sSqlQueryNoDisponibles = "call usp_trae_cursos_sinDisponibilidad(:v_ciclo,:v_sem_id,:v_esp_id,:v_tur_id,:v_tur_desc,1)";
        
        
        try {
            lstDocentesSinDisponibilidad = this.getSession().
                    createSQLQuery(sSqlQueryNoDisponibles).
                    setString("v_ciclo", ciclo).
                    setInteger("v_sem_id", semestre).
                    setInteger("v_esp_id", especialidad).
                    setInteger("v_tur_id", tur_id).
                    setString("v_tur_desc", tur_desc).
                    list();
        } catch (IllegalStateException e) {
            lstDocentesSinDisponibilidad = new ArrayList();
        } catch (HibernateException e) {
            lstDocentesSinDisponibilidad = new ArrayList();
        } catch (DataAccessResourceFailureException e) {
            lstDocentesSinDisponibilidad = new ArrayList();
        }

        iSizeDocSinDisp = lstDocentesSinDisponibilidad.size();

        docentes = new BeanReporte();

        docentes.setExpr1(String.valueOf(iSizeDocSinDisp));

        lstNumCurSinDis.add(docentes);

        return lstNumCurSinDis;
    }

    @Override
    public boolean estadoCursoEntreDocentesDisponibles(String ciclo, int semestre, int especialidad, int tur_id, String tur_desc) {
        String sSqlQuery;

        Integer estadoAprovacion = 0;

        sSqlQuery = "call usp_numProfeDispEntreCur(:v_ciclo,:v_sem_id,:v_esp_id,:v_tur_id,:v_tur_desc,1);";

        Object obj = this.getSession().
                createSQLQuery(sSqlQuery).
                setString("v_ciclo", ciclo).
                setInteger("v_sem_id", semestre).
                setInteger("v_esp_id", especialidad).
                setInteger("v_tur_id", tur_id).
                setString("v_tur_desc", tur_desc).uniqueResult();
        if (obj != null) {
            estadoAprovacion = CommonWeb.parseObjToInt(obj.toString());
        }

        if (estadoAprovacion == 0) {
            return true;
        } else {
            return false;
        }

    }
}
