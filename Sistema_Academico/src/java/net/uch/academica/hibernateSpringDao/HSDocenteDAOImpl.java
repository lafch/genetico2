package net.uch.academica.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcHorarioDispDocente;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSDocenteDAOImpl extends HibernateDaoSupport implements HSDocenteDAO {

    @Override
    public void insertarDocente(AcDocente cur) throws DataAccessException {
        getHibernateTemplate().save(cur);
    }

    @Override
    public List seleccionarDocente(String codigo, String nombre) throws DataAccessException, java.sql.SQLException {
        List lista;
        String hqlUpdate = "select DOC.Id,DOC.DocCodigo,DOC.DocNombre,DOC.DocCorreo,DOC.DocTelefono,DOC.DocNacimiento,DOC.DocResidencia, "
                + "DOC.DocPeriodoInicio,DOC.DocSituacion,DOC.DocSexo, DOC.DocDni,DOC.DocAppaterno,DOC.DocApmaterno, DOC.DocNombres,DOC.DocTipo "
                + "from "
                + "AcDocente DOC "
                + "where DOC.DocNombre like :nombre "
                + "and DOC.DocCodigo like :codigo "
                + "and DOC.DocActivo= :activo order by DOC.Id";
        lista = this.getSession().createQuery(hqlUpdate).setString("codigo", "%" + codigo + "%").setString("nombre", "%" + nombre + "%").setString("activo", "1").list();
        return lista;
    }

    @Override
    public List seleccionarDocenteHorario(String codigo, String nombre, int iHorario, int iCurso, String iCiclo, int idCurso,int idTurno) throws DataAccessException, java.sql.SQLException {
        List lista;
        String horario = "";
        String turno="";
        String ciclo = "";
        String filtroCiclo = "";
        String filtroCursoExacto="";
        String filtroHorario = "";
        String filtroTurno="";
        String filtroHorario2 = "";
        String curso = "";
        String filtroCurso = "";
        String filtroCurso2 = "";
        String conector = " and ";
        String union = "";

        if (iHorario == 1) {
            horario = " INNER JOIN ac_horario_disp_docente dp ON dp.doc_id=doc.doc_id \n";
            filtroHorario = conector + " dp.hor_activo=1 ";
            if(idTurno>0){
                turno = " INNER JOIN ac_turno_detalle tdet ON tdet.turdet_id=dp.turdet_id ";
                filtroTurno = conector + " turdet_activo=1 and tur_id="+idTurno;
            }
        } else if (iHorario == 2) {
            horario = " LEFT JOIN ac_horario_disp_docente dp ON dp.doc_id=doc.doc_id \n";
            filtroHorario = conector + " dp.hor_id is null ";
            filtroHorario2 = conector + " dp.hor_activo=0";
        }

        if (iCurso == 1) {
            curso = " INNER JOIN ac_curso_docente ca ON ca.doc_id=doc.doc_id \n";
            if(!"000000".equals(iCiclo)){
                ciclo=" INNER JOIN ac_plan_curso pc ON pc.cur_id = ca.curso_id \n";
                filtroCiclo = conector + " pc.plancur_activo=1 "+ conector + "pc.plancur_ciclo='"+iCiclo+"'";
            }
            if(idCurso !=0){
                filtroCursoExacto = conector + "ca.curso_id="+idCurso+" \n";
            }
            filtroCurso = conector + " ca.activo='1' ";
        } else if (iCurso == 2) {
            curso = " LEFT JOIN ac_curso_docente ca ON ca.doc_id=doc.doc_id  \n";
            filtroCurso = conector + " ca.curdoc_id is null";
            filtroCurso2 = conector + " ca.activo='0' ";
        }

        if (!filtroHorario2.equals("") && !filtroCurso2.equals("")) {
            union = "   UNION \n"
                    + " select doc.* \n"
                    + " FROM ac_docente doc \n"
                    + " INNER JOIN ac_horario_disp_docente dp ON dp.doc_id=doc.doc_id \n"
                    + " INNER JOIN ac_curso_docente ca ON ca.doc_id=doc.doc_id \n"
                    + " WHERE doc.doc_activo=1 and doc.doc_nombre like :nombre and doc.codigo like :codigo "+filtroHorario2+filtroCurso2;
        }

        String hqlUpdate = "select doc.* \n"
                + " FROM ac_docente doc \n"
                + horario+ turno + curso + ciclo
                + " WHERE doc.doc_nombre like :nombre and doc.codigo like :codigo and doc.doc_activo=:activo " + filtroHorario+ filtroTurno + filtroCurso + filtroCiclo + filtroCursoExacto + "\n"
                + union
                + " GROUP BY doc.doc_id "
                + " ORDER BY 3 ";

        lista = this.getSession().createSQLQuery(hqlUpdate).addEntity(AcDocente.class).setString("codigo", "%" + codigo + "%").setString("nombre", "%" + nombre + "%").setString("activo", "1").list();
        return lista;
    }

    @Override
    public void eliminarDocente(String id) throws DataAccessException {
        String hqlUpdate = "update AcDocente set DocActivo = :v_activo where Id = :v_id";
        this.getSession().createQuery(hqlUpdate).setString("v_activo", "0").setString("v_id", id).executeUpdate();
    }

    @Override
    public void actualizarDocente(AcDocente doc) throws DataAccessException {
        getHibernateTemplate().update(doc);
    }

    @Override
    public List seleccionarDocente() throws Exception {
        return this.getSession().createCriteria(AcDocente.class).add(Restrictions.eq("DocActivo", "1")).addOrder(Order.asc("DocNombre")).list();
    }

    @Override
    public List seleccionarDocentePorCurso(int esp_id, int cur_id) throws DataAccessException {

        String sQuery;
        Query query;
        sQuery = " Select d.* \n"
                + " FROM ac_docente d \n"
                + " INNER JOIN ac_curso_docente cd ON cd.doc_id=d.doc_id \n"
                + " INNER JOIN ac_curso c ON c.cur_id=cd.curso_id \n"
                + " WHERE cd.activo=1 and d.doc_activo=1 and c.cur_activo=1 and \n"
                + " c.esp_id=:v_esp_id and c.cur_id=:v_cur_id and cd.estado_asignacion='097002'";

        return this.getSession().createSQLQuery(sQuery).addEntity(AcDocente.class).setInteger("v_esp_id", esp_id).setInteger("v_cur_id", cur_id).list();

        //return this.getSession().createCriteria(AcAlumno.class).add(Expression.eq("Esp.Id", id_esp)).add(Expression.eq("AluTipo", tipo)).add(Expression.eq("AluActivo", "1")).add(Restrictions.eq("SemId", sem_id)).list();
    }

    @Override
    public AcDocente seleccionarDocente(int id) {
        return (AcDocente) this.getSession().createCriteria(AcDocente.class).
                add(Restrictions.eq("DocActivo", "1")).
                add(Restrictions.eq("Id", id)).uniqueResult();
    }

    @Override
    public AcAula seleccionarAula(int id) {
        return (AcAula) this.getSession().createCriteria(AcAula.class).
                add(Restrictions.eq("AulActivo", "1")).
                add(Restrictions.eq("Id", id)).uniqueResult();
    }

    @Override
    public void InsertarDisponibilidad(AcDocente doc) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdate(doc);
    }

    @Override
    public AcDocente buscarDocente(String dni) throws DataAccessException {
        return (AcDocente) this.getSession().createCriteria(AcDocente.class).
                add(Restrictions.eq("DocActivo", "1")).
                add(Restrictions.eq(AcDocente.PROP_DOC_DNI, dni)).uniqueResult();
    }

    @Override
    public AcDocente buscarDocente(int docId) throws DataAccessException {
        return (AcDocente) this.getSession().createCriteria(AcDocente.class).
                add(Restrictions.eq("DocActivo", "1")).
                add(Restrictions.eq(AcDocente.PROP_ID, docId)).uniqueResult();
    }

    @Override
    public List<AcHorarioDispDocente> seleccionarHorario(int doc_id) throws Exception {
        return this.getSession().createCriteria(AcHorarioDispDocente.class).
                add(Restrictions.eq("horActivo", "1")).
                add(Restrictions.eq("acDocente.Id", doc_id)).list();
    }

    @Override
    public void insertarHorario(AcHorarioDispDocente horario) throws Exception {
        this.getHibernateTemplate().save(horario);
    }

    @Override
    public void actualizarHorario(AcHorarioDispDocente horario) throws Exception {
        this.getHibernateTemplate().update(horario);
    }

    @Override
    public void insertarActualizarHorarios(List<AcHorarioDispDocente> horarias) {
        this.getHibernateTemplate().saveOrUpdateAll(horarias);
    }

    @Override
    public void eliminarHorario(int hor_id) throws Exception {
        String hqlUpdate = "update AcHorarioDispDocente set horActivo = :newName where horId = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").
                setString("oldName", "" + hor_id).
                executeUpdate();
    }

    @Override
    public void eliminarHorarios(List<Integer> hor_ids) {
        String hqlUpdate = "update AcHorarioDispDocente "
                + "set horActivo = '0' "
                + "where horId in (:v_id)";

        this.getSession().createQuery(hqlUpdate).
                setParameterList("v_id", hor_ids).executeUpdate();
    }

    @Override
    public void eliminarHorariosPorDocId(int iDocId) {
        String hqlUpdate = "update AcHorarioDispDocente "
                + "set horActivo = '0' "
                + "where AcDocente.Id = :v_id";

        this.getSession().createQuery(hqlUpdate).
                setInteger("v_id", iDocId).executeUpdate();
    }

//    @Override
//    public CursoDocenteBean buscarCursoDocente( int doc_id, int cur_id ) {
//        CursoDocenteBean epds = null;
//        String sQuery;
//        SQLQuery sqlQuery;
//        Object[] objFila;
//        List lstAux;
//        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//        HSCursoDAO daoC = (HSCursoDAO)ServiceFinder.findBean( "SpringHibernateDaoCurso" );
//
//        sQuery = "SELECT "
//                + "curdoc_id, "
//                + "doc_id, "
//                + "curso_id, "
//                + "fecha_creacion, "
//                + "activo "
//                + "FROM ac_curso_docente "
//                + "WHERE activo=1 AND doc_id=:epdi_id AND curso_id=:epdc_id LIMIT 1;";
//        try {
//            sqlQuery = this.getSession().createSQLQuery( sQuery );
//            sqlQuery.setInteger( "epdi_id", doc_id );
//            sqlQuery.setInteger( "epdc_id", cur_id );
//            lstAux = sqlQuery.list();
//            for ( Object objAux : lstAux ) {
//                objFila = (Object[]) objAux;
//                epds = new CursoDocenteBean();
//                epds.setCursoDocenteId(CommonWeb.parseObjToInt( objFila[0] ) );
//                epds.setDocente(seleccionarDocente( CommonWeb.parseObjToInt(objFila[1]) ) );
//                epds.setCurso((AcCurso)daoC.seleccionarCursoID(CommonWeb.parseObjToInt( objFila[2] ) ));
//                epds.setActivo( CommonWeb.parseObjToInt( objFila[4] ) );
//                break;
//            }
//        } catch ( Exception ex ) {
//            ex.printStackTrace();
//        }
//        return epds;
//    }
//    @Override
//    public List<CursoDocenteBean> listarCursoDocente( int doc_id) {
//        List<CursoDocenteBean> lista= new ArrayList<CursoDocenteBean>();
//        CursoDocenteBean epds = null;
//        String sQuery;
//        SQLQuery sqlQuery;
//        Object[] objFila;
//        List lstAux;
//        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//        HSCursoDAO daoC = (HSCursoDAO)ServiceFinder.findBean( "SpringHibernateDaoCurso" );
//
//        sQuery = "SELECT "
//                + "curdoc_id, "
//                + "doc_id, "
//                + "curso_id, "
//                + "fecha_creacion, "
//                + "activo "
//                + "FROM ac_curso_docente "
//                + "WHERE activo=1 AND doc_id=:epdi_id";
//        try {
//            sqlQuery = this.getSession().createSQLQuery( sQuery );
//            sqlQuery.setInteger( "epdi_id", doc_id );
//            lstAux = sqlQuery.list();
//            for ( Object objAux : lstAux ) {
//                objFila = (Object[]) objAux;
//                epds = new CursoDocenteBean();
//                epds.setCursoDocenteId(CommonWeb.parseObjToInt( objFila[0] ) );
//                epds.setDocente(seleccionarDocente( CommonWeb.parseObjToInt(objFila[1]) ) );
//                epds.setCurso(daoC.seleccionarCursoID(CommonWeb.parseObjToInt( objFila[2] ) ));
//                epds.setActivo( CommonWeb.parseObjToInt( objFila[4] ) );   
//                lista.add( epds );
//            }
//            
//        } catch ( Exception ex ) {
//            ex.printStackTrace();
//        }
//        return lista;
//    }
//    @Override
//    public int insertarActualizarCurDoc( CursoDocenteBean epds ) throws Exception {
//        String sQueryInserUpdate;
//        SQLQuery sqlQuery;
//        if ( epds.getCursoDocenteId() == null ) {
//            sQueryInserUpdate = "INSERT INTO ac_curso_docente (doc_id,curso_id,fecha_creacion,activo) "
//                    + "VALUES(:docente_id,:curso_id,CURRENT_TIMESTAMP,:activo)";
//        } else {
//            sQueryInserUpdate = "UPDATE ac_curso_docente SET curso_id=:curso_id,activo=:activo "
//                    + "WHERE curdoc_id=:curDoc_id";
//        }
//        sqlQuery = this.getSession().createSQLQuery( sQueryInserUpdate );
//        if ( epds.getCursoDocenteId()!= null ) {
//            sqlQuery.setInteger( "curDoc_id", epds.getCursoDocenteId());
//        }
//        sqlQuery.setInteger( "docente_id", epds.getDocente().getId());
//        sqlQuery.setInteger( "curso_id", epds.getCurso().getId());
//        sqlQuery.setInteger( "activo", epds.getActivo() );
//        return sqlQuery.executeUpdate();
//    }
    @Override
    public List listarDocentesCursosLibreActivo() {
        List lista;
        String hqlQuery = "select acDocente.Id, acDocente.DocNombre, acDocente.DocDni "
                + "from ClHoraria as clHoraria "
                + "inner join clHoraria.acDocente as acDocente "
                + "inner join clHoraria.clSeccion as clSeccion "
                + "inner join clSeccion.clArbolAcademico as clArbolAcademico "
                + "where clHoraria.horActivo='1' "
                + "and clSeccion.secActivo='1' "
                + "and clArbolAcademico.arbActivo='1' "
                + "group by acDocente.Id order by acDocente.DocNombre";
        lista = this.getSession().createQuery(hqlQuery).list();
        return lista;
    }

    @Override
    public AcDocente seleccionarDocentePorSecId(int iSecId) {
        String sQuery;
        AcDocente docente;
        Query query;
        List lstObjDocs;
        Object[] aobjDoc;
        List<AcDocente> lstDocentes;
        sQuery = "SELECT d.doc_id,d.doc_nombre FROM cl_seccion s INNER JOIN cl_horaria h ON(s.sec_id=h.sec_id) INNER JOIN ac_docente d ON(h.doc_id=d.doc_id) "
                + "WHERE s.sec_id=:sec_id AND h.hor_activo=1";
        query = this.getSession().createSQLQuery(sQuery);
        query.setInteger("sec_id", iSecId);
        lstObjDocs = query.list();
        lstDocentes = new ArrayList<AcDocente>();
        if (lstObjDocs != null && !lstObjDocs.isEmpty()) {
            for (Object obj : lstObjDocs) {
                aobjDoc = (Object[]) obj;
                docente = new AcDocente();
                docente.setId(Integer.parseInt(aobjDoc[0] + ""));
                docente.setDocNombre(aobjDoc[1] + "");
                lstDocentes.add(docente);
            }
        }
        if (!lstDocentes.isEmpty()) {
            return lstDocentes.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String LocalSeleccionado(int sem_id) throws DataAccessException {
        SQLQuery sqlQuery;
        String reporte = "";
        String sql = "select loc_des from ac_local l  where l.loc_id=:sem_id";
        sqlQuery = this.getSession().createSQLQuery(sql);
        sqlQuery.setInteger("sem_id", sem_id);
        reporte = (sqlQuery.uniqueResult().toString());
        return reporte;
    }
}
