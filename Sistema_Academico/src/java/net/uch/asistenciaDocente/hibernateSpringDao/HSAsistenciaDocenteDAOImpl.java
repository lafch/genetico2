/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.asistenciaDocente.hibernateSpringDao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.uch.asistenciaDocente.managedBeans.beans.AsistenciaDocenteBean;
import net.uch.cursoLibre.managedBeans.beans.AsistenciaDocenteCLBean;
import net.uch.mapping.AcArea;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcSesionAsistencia;
import net.uch.mapping.AcSesionEfectivaAsisDoc;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author cesardl
 */
public class HSAsistenciaDocenteDAOImpl extends HibernateDaoSupport implements HSAsistenciaDocenteDAO {

    @Override
    public List buscarDocente_x_dato(String dato) {
        List docente = new ArrayList();
        docente = this.getSession().createCriteria(AcDocente.class).
                add(Restrictions.like("DocNombre", "%" + dato + "%")).
                add(Restrictions.eq("DocActivo", "1")).list();

        return docente;
    }
    
    @Override
    public List<AcDocente> buscarDocente_x_dato1(String dato) {
        List lista;
        List myList = new ArrayList();
        String hqlSelect="select DocNombre, DocDni, DocCodigo, Id from AcDocente where DocNombre like concat(:v_dato,'%') and DocActivo= :v_activo";
        lista=this.getSession().createQuery(hqlSelect).setString("v_dato", dato).setString("v_activo", "1").list();
        
        for (int i = 0; i < lista.size(); i++) {
            AcDocente d = new AcDocente();
            Object[] objs = (Object[]) lista.get(i);
            d.setDocNombre(objs[0].toString());
            d.setDocDni(objs[1].toString());
            d.setDocCodigo(objs[2].toString());
            d.setId((Integer)objs[3]);
            myList.add(d);
        }
        return myList;
    }
    
    @Override
    public List buscarDocente_x_dato2(String dato) {
        
                Criteria criteria = this.getSession().createCriteria(AcDocente.class).add(Restrictions.eq("DocActivo", "1")).add(Restrictions.like("DocNombre", "%" + dato + "%"));
                ProjectionList projectionList = Projections.projectionList();
                projectionList.add(Projections.property("DocNombre"));
                projectionList.add(Projections.property("DocDni"));
                projectionList.add(Projections.property("DocCodigo"));
                projectionList.add(Projections.property("Id"));
                
                criteria.setProjection(Projections.distinct(projectionList));
                List myList = new ArrayList();
                List list = criteria.list();
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                        System.out.println("");
                        Object[] objects = (Object[]) iterator.next();
                        for (int i = 0; i < objects.length; i++) {
                            AcDocente d = new AcDocente();
                                System.out.print("|\t" + objects[i] + "\t");
                                d.setDocNombre((String)objects[0]);
                                d.setDocDni((String)objects[1]);
                                d.setDocCodigo((String)objects[2]);
                                d.setId((Integer)objects[3]);
                                myList.add(d);
                        }
                }
        return myList;
    }

    @Override
    public List<AcSesionAsistencia> listarSessionAsistencia_x_docenteFecha(int doc_id, Date fecha_ini, Date fecha_fin, String tipasis) {

        Criteria c = this.getSession().createCriteria(AcSesionAsistencia.class).
                add(Restrictions.between("sesFechaRegistro", fecha_ini, fecha_fin)).
                add(Restrictions.eq("sesActivo", "1")).
                add(Restrictions.eq("sesTipoAsistencia", tipasis));

        if (doc_id != 0) {
            c.add(Restrictions.eq("docId", doc_id));
        }
        c.addOrder(Order.desc("sesFechaInicio"));

        return c.list();
    }

    @Override
    public AcArea seleccionarArea(int sec_id) {
        Criteria c = this.getSession().createCriteria(AcArea.class).
                add(Restrictions.and(Restrictions.eq("areActivo", "1"),
                Restrictions.eq("areId", sec_id)));

        return (AcArea) c.uniqueResult();
    }

    @Override
    public AcSesionEfectivaAsisDoc listaMarcadoDeAsistencia_x_Docente(int ses_id) {
        List<AcSesionEfectivaAsisDoc> l = this.getSession().createCriteria(AcSesionEfectivaAsisDoc.class).
                add(Restrictions.eq("sesefeasisdocActivo", "1")).
                add(Restrictions.eq("acSesionAsistencia.sesId", ses_id)).list();

        if (l.size() == 0) {
            return null;
        } else {
            return l.get(0);
        }
    }
    
    
    @Override
    public List listarAsistenciaDiaria_x_docenteFecha(int p_doc_id, Date p_fecha_ini,int p_tur_id,int p_fac_id) {
        int iSize;
        AsistenciaDocenteBean adb;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<AsistenciaDocenteBean> lista = new ArrayList<AsistenciaDocenteBean>();
        if(p_fecha_ini==null){
            p_fecha_ini=new Date();
        }
        /*String hqlQuery = "select ac_docente.doc_id,doc_dni, doc_nombre, asis_fecha, asis_inicio, asis_fin "
                + "from ac_docente inner join tb_asistencia_docente on ac_docente.doc_id=tb_asistencia_docente.doc_id "
                + " where tb_asistencia_docente.loc_id=26 and tb_asistencia_docente.asis_fecha= :v_fecha_ini ";

        if(p_doc_id>0){
        hqlQuery+=" and ac_docente.doc_id=" +p_doc_id;
        }
                hqlQuery+=" order by doc_nombre,asis_inicio"; */
        
        String hqlQuery="select ses_id,doc_id,doc_dni,seccion,doc_nombre,curso,aula,inicio,fin,ingreso,if(isnull(salida),NULL,salida)salida, "
                + " facultad,escuela from ( select  tabla1.ses_id, tabla1.doc_id, ac_docente.doc_nombre, ac_docente.doc_dni, ac_docente.doc_fac_id,"
                + " (select ac_facultad.fac_nombre from ac_facultad where ac_facultad.fac_id=ac_docente.doc_fac_id)facultad, "
                + " (select ac_especialidad.esp_nombre from ac_especialidad where ac_especialidad.esp_id=ac_docente.doc_esp_id)escuela,"
                + " tabla1.fecha, tabla1.inicio,tabla1.fin,if(tabla1.salida<inicio,NULL,tabla1.marca) as ingreso, "
                + " if(tabla1.salida<inicio,NULL,tabla1.salida)salida, if(marca<=inicio,if(salida>=fin,1,2),if(salida>=fin,3,4)) caso,"
                + " sec_to_time(ac_turno.tur_tiempo_periodo*60)tiempo,tur_tiempo_periodo, tabla1.especialidad,"
                + " tabla1.seccion,tabla1.curso,ac_aula.aul_des as aula, "
                + " round(((hour(timediff(tabla1.fin,tabla1.inicio))*60 + minute(timediff(tabla1.fin,tabla1.inicio)))/ac_turno.tur_tiempo_periodo)) horas, "
                + " tabla1.tardanza tardanza_minutos, if(isnull((select  ac_sesion_asistencia.ses_id from ac_sesion_asistencia "
                + " inner join ( select ses_id, doc_id, 1 as sesefeasisdoc_activo, inicio as sesefeasisdoc_registro, final as sesefeasisdoc_salida, "
                + " if(convert(inicio, time)>ses_inicio,'046002','046001') sesefeasisdoc_tipo from( "
                + " select ses_id, ac_docente.doc_id, ac_docente.doc_nombre, ac_sesion_asistencia.ses_fecha_registro, "
                + " convert(date_format(ses_fecha_inicio,'%H:%i:%m'),time) ses_inicio, convert(date_format(ses_fecha_fin,'%H:%i:%m'),time) ses_fin, "
                + " convert(concat(tb_asistencia_docente.asis_fecha,' ',  min(tb_asistencia_docente.asis_inicio)), datetime) inicio, "
                + " convert(concat(tb_asistencia_docente.asis_fecha,' ',  (select asis.asis_fin from tb_asistencia_docente asis where asis.asis_inicio=max(tb_asistencia_docente.asis_inicio) "
                + " and asis.doc_id=tb_asistencia_docente.doc_id and asis.asis_fecha=:v_fecha_ini limit 1)), datetime) final, "
                + " ses_tipo_asistencia from ac_sesion_asistencia inner join ac_docente on ac_sesion_asistencia.doc_id=ac_docente.doc_id "
                + " inner join tb_asistencia_docente on (tb_asistencia_docente.doc_id=ac_sesion_asistencia.doc_id and tb_asistencia_docente.asis_fecha=ac_sesion_asistencia.ses_fecha_registro) "
                + " where ac_sesion_asistencia.ses_fecha_registro=:v_fecha_ini and tb_asistencia_docente.loc_id=26 "
                + " and ac_sesion_asistencia.ses_tipo_asistencia='050001' and tb_asistencia_docente.asis_inicio<= convert(date_format(ses_fecha_fin,'%H:%i:%s' ), time) "
                + " group by ses_id order by ac_docente.doc_nombre, ses_fecha_inicio, ses_fecha_fin) master1 "
                + " )ac_sesion_efectiva_asis_doc on ac_sesion_efectiva_asis_doc.ses_id=ac_sesion_asistencia.ses_id "
                + " where ac_sesion_asistencia.ses_activo='1'  and ac_sesion_asistencia.ses_estado='044001' and ac_sesion_asistencia.ses_tipo<>'083001'  "
                + " and ac_sesion_asistencia.ses_fecha_registro=tabla1.fecha  and convert(ac_sesion_asistencia.ses_fecha_fin,time)=tabla1.inicio  "
                + " and ac_sesion_efectiva_asis_doc.sesefeasisdoc_activo='1'  and ac_sesion_efectiva_asis_doc.doc_id=tabla1.doc_id  "
                + " group by ac_sesion_asistencia.ses_id limit 0,1)),1,0) estado_tardanza  "
                + " from (select tabla.ses_id,tabla.doc_id,tabla.fecha,tabla.inicio,tabla.fin,tabla.marca,tabla.salida,tabla.sec_id,tabla.seccion, "
                + " tabla.especialidad,tabla.curso,tabla.tardanza from (select  ac_sesion_asistencia.ses_id, "
                + " ac_sesion_asistencia.doc_id, ac_sesion_asistencia.ses_fecha_registro fecha, "
                + " convert(ac_sesion_asistencia.ses_fecha_inicio,time) inicio, convert(ac_sesion_asistencia.ses_fecha_fin,time) fin, "
                + " convert(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_registro),time) as marca, "
                + " convert(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_salida),time) salida, ac_seccion.sec_id, "
                + " ac_seccion.sec_nombre seccion, ac_especialidad.esp_nombre especialidad, ac_curso.cur_nombre curso, "
                + " if(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_registro)>ac_sesion_asistencia.ses_fecha_inicio, "
                + " hour(timediff(convert(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_registro),time),convert(ac_sesion_asistencia.ses_fecha_inicio,time)))*60+ "
                + " minute(timediff(convert(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_registro),time),convert(ac_sesion_asistencia.ses_fecha_inicio,time))), "
                + " 0) tardanza  from  ( select ses_id, doc_id,1 as sesefeasisdoc_activo, inicio as sesefeasisdoc_registro,  "
                + " final as sesefeasisdoc_salida, if(convert(inicio, time)>ses_inicio,'046002','046001') sesefeasisdoc_tipo "
                + " from( select ses_id, ac_docente.doc_id, ac_docente.doc_nombre, "
                + " ac_sesion_asistencia.ses_fecha_registro, convert(date_format(ses_fecha_inicio,'%H:%i:%m'),time) ses_inicio, "
                + " convert(date_format(ses_fecha_fin,'%H:%i:%m'),time) ses_fin, convert(concat(tb_asistencia_docente.asis_fecha,' ',  max(tb_asistencia_docente.asis_inicio)), datetime) inicio, "
                + " convert(concat(tb_asistencia_docente.asis_fecha,' ',  (select asis.asis_fin from tb_asistencia_docente asis where asis.asis_inicio=max(tb_asistencia_docente.asis_inicio) "
                + " and asis.doc_id=tb_asistencia_docente.doc_id and asis.asis_fecha=:v_fecha_ini limit 1)), datetime) final, "
                + " ses_tipo_asistencia from ac_sesion_asistencia inner join ac_docente on ac_sesion_asistencia.doc_id=ac_docente.doc_id "
                + " inner join tb_asistencia_docente on (tb_asistencia_docente.doc_id=ac_sesion_asistencia.doc_id and tb_asistencia_docente.asis_fecha=ac_sesion_asistencia.ses_fecha_registro) "
                + " where ac_sesion_asistencia.ses_fecha_registro=:v_fecha_ini and tb_asistencia_docente.loc_id=26 "
                + " and ac_sesion_asistencia.ses_tipo_asistencia='050001' and tb_asistencia_docente.asis_inicio<= convert(date_format(ses_fecha_fin,'%H:%i:%s' ), time) "
                + " group by ses_id order by ac_docente.doc_nombre, ses_fecha_inicio, ses_fecha_fin) master1 "
                + " )ac_sesion_efectiva_asis_doc  right join ac_sesion_asistencia on ac_sesion_asistencia.ses_id=ac_sesion_efectiva_asis_doc.ses_id  "
                + " inner join ac_seccion on ac_seccion.sec_id=ac_sesion_asistencia.sec_id  "
                + " inner join ac_curso_aperturado on ac_curso_aperturado.curape_Id=ac_seccion.curape_id  "
                + " inner join ac_plan_curso on ac_plan_curso.plancur_Id=ac_curso_aperturado.plancur_id  "
                + " inner join ac_curso on ac_curso.cur_id=ac_plan_curso.cur_id  inner join ac_especialidad on ac_especialidad.esp_id=ac_curso.esp_id  "
                + " where ac_sesion_asistencia.ses_activo='1'  and ac_sesion_asistencia.ses_estado='044001' and ac_sesion_asistencia.ses_tipo_asistencia='050001'  "
                + " and ac_sesion_asistencia.ses_tipo<>'083001' and ac_seccion.sec_activo='1'  "
                + " and date_format(ac_sesion_asistencia.ses_fecha_inicio,'%Y-%m-%d')=:v_fecha_ini "
                + " and ac_curso_aperturado.curape_activo='1' and ac_curso_aperturado.curape_aperturado='1' "
                + " and ac_plan_curso.plancur_activo='1'  and ac_curso.cur_activo='1'  "
                + " and ac_especialidad.esp_activo='1'  group by ac_sesion_asistencia.ses_id  "
                + " order by ac_sesion_asistencia.ses_id) as tabla group by tabla.fecha,tabla.inicio,tabla.doc_id) tabla1  "
                + " inner join ac_horario on ac_horario.sec_id=tabla1.sec_id  inner join ac_aula on ac_aula.aul_id=ac_horario.aul_id "
                + " inner join ac_turno_detalle on ac_turno_detalle.turdet_id=ac_horario.turdet_id "
                + " inner join ac_turno on ac_turno.tur_id=ac_turno_detalle.tur_id "
                + " inner join ac_docente on ac_docente.doc_id=tabla1.doc_id  where (date_format(tabla1.fecha,'%Y-%m-%d')=:v_fecha_ini )  "
                + " and ac_horario.hor_activo='1' and ac_turno_detalle.turdet_activo=1 and ac_turno.tur_activo=1 "
                + " and ac_docente.doc_activo=1 " ;
        System.out.println( "consulta---> "+hqlQuery ); 
        
        if(p_doc_id>0){
        hqlQuery+=" and ac_docente.doc_id=" +p_doc_id;
        }
        if(p_tur_id>0){
            
        hqlQuery+=" and ac_turno.tur_id=" +p_tur_id;
        }
        
        if(p_fac_id>0){
            hqlQuery+=" and ac_docente.doc_fac_id=" +p_fac_id;
            System.out.println("Ingreso el id de facultad-->" +p_fac_id);
        }
        
                hqlQuery+= " and tabla1.doc_id not in(83) group by tabla1.ses_id  "
                + " order by tabla1.fecha,tabla1.inicio )calculo "
                + " order by inicio,fin,doc_fac_id,doc_nombre";
            sqlQuery = this.getSession().createSQLQuery( hqlQuery );
            sqlQuery.setDate( "v_fecha_ini", p_fecha_ini );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[])lstAux.get( i );
                adb = new AsistenciaDocenteBean();
                adb.setSes_id(Integer.parseInt(aobjFila[0].toString()));
                adb.setDoc_id(Integer.parseInt(aobjFila[1].toString())); 
                adb.setDoc_codigo(aobjFila[2].toString());
                adb.setSec_nombre(aobjFila[3].toString());
                adb.setDoc_nombre(aobjFila[4].toString());
                adb.setCur_nombre(aobjFila[5].toString());
                adb.setAula(aobjFila[6].toString());
                adb.setHora_inicio((Time)aobjFila[7]);
                adb.setHora_fin((Time)aobjFila[8]);
                adb.setHora_ingreso((Time)aobjFila[9]);
                adb.setHora_salida((Time)aobjFila[10]);
                adb.setFacultad(aobjFila[11].toString());
                lista.add( adb );
            }

        return lista;
    }
    
    
    @Override
    public List listarAsistenciaDiariaCl_x_docenteFecha(int p_doc_id, Date p_fecha_ini,int p_tur_id,String p_centro) {
        int iSize;
        AsistenciaDocenteCLBean adb;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<AsistenciaDocenteCLBean> lista = new ArrayList<AsistenciaDocenteCLBean>();
        if(p_fecha_ini==null){
            p_fecha_ini=new Date();
        }

        String hqlQuery="select ses_id,doc_id,doc_dni,doc_nombre,centro_des,especialidad,seccion,curso,inicio,fin,marca,salida  from ( "
                + " select ses_id,doc_id,doc_dni,doc_nombre,centro_des,centro,especialidad,inicio,fin,marca,salida,if(isnull(marca) and isnull(salida),horas,0)horas_real, "
                + " seccion,curso,horas from ( select tabla.ses_id,tabla.doc_id,ac_docente.doc_dni,doc_nombre, "
                + " tabla.inicio,tabla.fin, "
                + " tabla.marca, tabla.salida, if(marca<=inicio,if(salida>=fin,1,2),if(salida>=fin,3,4)) caso, sec_to_time( cl_horaria.tur_tiempo_periodo*60 )tiempo, "
                + " tur_tiempo_periodo, tabla.seccion, tabla.curso, (select tb_catalogo.cat_descripcion_elemento from tb_catalogo where concat(tb_catalogo.cat_codigo_grupo,tb_catalogo.cat_codigo_elemento)=tabla.centro)centro_des,tabla.centro, "
                + " tabla.especialidad, round(((hour(timediff(tabla.fin,tabla.inicio))*60 + minute(timediff(tabla.fin,tabla.inicio)))/cl_horaria.tur_tiempo_periodo ),1) horas "
                + " from (select tabla.ses_id,tabla.doc_id,tabla.fecha,tabla.inicio,tabla.fin,tabla.marca,tabla.salida,tabla.sec_id,tabla.seccion,tabla.curso,tabla.tardanza ,especialidad,centro "
                + " from (select ac_sesion_asistencia.ses_id, ac_sesion_asistencia.doc_id, ac_sesion_asistencia.ses_fecha_registro fecha, "
                + " convert(ac_sesion_asistencia.ses_fecha_inicio,time) inicio, convert(ac_sesion_asistencia.ses_fecha_fin,time) fin, "
                + " convert(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_registro),time) marca, convert(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_salida),time) salida, "
                + " cl_seccion.sec_id, cl_seccion.sec_nombre seccion, (SELECT aCur.arb_institucion FROM cl_seccion s  INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) "
                + " INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id) "
                + " INNER JOIN cl_arbol_academico aArea ON(aMod.arb_id_padre=aArea.arb_id) "
                + " WHERE s.sec_id=cl_seccion.sec_id) centro, (SELECT aArea.arb_descripcion FROM cl_seccion s  INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) "
                + " INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id) "
                + " INNER JOIN cl_arbol_academico aArea ON(aMod.arb_id_padre=aArea.arb_id) "
                + " WHERE s.sec_id=cl_seccion.sec_id  )especialidad, (SELECT aCur.arb_descripcion FROM cl_seccion s "
                + " INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) "
                + " INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) "
                + " INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id)  "
                + " INNER JOIN cl_arbol_academico aArea ON(aMod.arb_id_padre=aArea.arb_id) "
                + " WHERE s.sec_id=cl_seccion.sec_id) curso, if(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_registro)>ac_sesion_asistencia.ses_fecha_inicio, "
                + " hour(timediff(convert(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_registro),time),convert(ac_sesion_asistencia.ses_fecha_inicio,time)))*60+ "
                + " minute(timediff(convert(min(ac_sesion_efectiva_asis_doc.sesefeasisdoc_registro),time),convert(ac_sesion_asistencia.ses_fecha_inicio,time))), "
                + " 0) tardanza  from ( select ses_id, doc_id,1 as sesefeasisdoc_activo, inicio as sesefeasisdoc_registro, "
                + " final as sesefeasisdoc_salida, if(convert(inicio, time)>ses_inicio,'046002','046001') sesefeasisdoc_tipo "
                + " from( select ses_id, ac_docente.doc_id, ac_docente.doc_nombre, ac_sesion_asistencia.ses_fecha_registro, convert(date_format(ses_fecha_inicio,'%H:%i:%m'),time) ses_inicio, "
                + " convert(date_format(ses_fecha_fin,'%H:%i:%m'),time) ses_fin, convert(concat(tb_asistencia_docente.asis_fecha,' ',  max(tb_asistencia_docente.asis_inicio)), datetime) inicio, "
                + " convert(concat(tb_asistencia_docente.asis_fecha,' ',  (select asis.asis_fin from tb_asistencia_docente asis where asis.asis_inicio=max(tb_asistencia_docente.asis_inicio) "
                + " and asis.doc_id=tb_asistencia_docente.doc_id and asis.asis_fecha=:v_fecha_ini limit 1)), datetime) final, "
                + " ses_tipo_asistencia from ac_sesion_asistencia inner join ac_docente on ac_sesion_asistencia.doc_id=ac_docente.doc_id "
                + " inner join tb_asistencia_docente on (tb_asistencia_docente.doc_id=ac_sesion_asistencia.doc_id and tb_asistencia_docente.asis_fecha=ac_sesion_asistencia.ses_fecha_registro) "
                + " where ac_sesion_asistencia.ses_fecha_registro= :v_fecha_ini and tb_asistencia_docente.loc_id=26 "
                + " and ac_sesion_asistencia.ses_tipo_asistencia='050003' and tb_asistencia_docente.asis_inicio<= convert(date_format(ses_fecha_fin,'%H:%i:%s' ), time) "
                + " group by ses_id order by ac_docente.doc_nombre, ses_fecha_inicio, ses_fecha_fin) master1 "
                + " )ac_sesion_efectiva_asis_doc  right join ac_sesion_asistencia on ac_sesion_asistencia.ses_id=ac_sesion_efectiva_asis_doc.ses_id  "
                + " inner join cl_seccion on cl_seccion.sec_id=ac_sesion_asistencia.sec_id  where ac_sesion_asistencia.ses_activo='1'  "
                + " and ac_sesion_asistencia.ses_estado='044001'  and ac_sesion_asistencia.ses_tipo_asistencia='050003' "
                + " and cl_seccion.sec_activo='1'  group by ac_sesion_asistencia.ses_id  order by ac_sesion_asistencia.ses_id) as tabla  "
                + " group by tabla.fecha,tabla.inicio,tabla.doc_id) tabla  inner join cl_horaria on cl_horaria.sec_id=tabla.sec_id "
                + " INNER JOIN ac_aula on ac_aula.aul_id=cl_horaria.aul_id  "
                + " inner join ac_docente on ac_docente.doc_id=tabla.doc_id "
                + " where (date_format(tabla.fecha,'%Y-%m-%d')= :v_fecha_ini ) "
                + " and ac_docente.doc_activo=1 ";
        if(p_doc_id>0){
        hqlQuery+=" and ac_docente.doc_id=" +p_doc_id;
        }
        if( !"000000".equals(p_centro)){
            hqlQuery+=" and tabla.centro='" + p_centro + "' ";
        }
        
                 hqlQuery+= " and cl_horaria.hor_activo='1' group by tabla.ses_id  order by tabla.fecha,tabla.inicio"
                + " )calculo )informe order by inicio,fin,doc_nombre" ;
        

            sqlQuery = this.getSession().createSQLQuery( hqlQuery );
            sqlQuery.setDate( "v_fecha_ini", p_fecha_ini );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[])lstAux.get( i );
                adb = new AsistenciaDocenteCLBean();
                adb.setSes_id(Integer.parseInt(aobjFila[0].toString()));
                adb.setDoc_id(Integer.parseInt(aobjFila[1].toString())); 
                adb.setDoc_codigo(aobjFila[2].toString());
                adb.setDoc_nombre(aobjFila[3].toString());
                adb.setCentro(aobjFila[4].toString());
                adb.setEspecialidad(aobjFila[5].toString());
                adb.setSec_nombre(aobjFila[6].toString());
                adb.setCur_nombre(aobjFila[7].toString());
                adb.setHora_inicio((Time)aobjFila[8]);
                adb.setHora_fin((Time)aobjFila[9]);
                adb.setHora_ingreso((Time)aobjFila[10]);
                adb.setHora_salida((Time)aobjFila[11]);
                lista.add( adb );
            }

        return lista;
    }
    

    @Override
    public void ingresarAsisteanDocente(AcSesionEfectivaAsisDoc acSesionEfectivaAsisDoc) {
        this.getHibernateTemplate().saveOrUpdate(acSesionEfectivaAsisDoc);
    }

    @Override
    public AcSesionAsistencia listarSessionAsistencia_x_sesid(int ses_id) {
        List<AcSesionAsistencia> l = this.getSession().createCriteria(AcSesionAsistencia.class).
                add(Restrictions.eq("sesId", ses_id)).list();

        return l.get(0);
    }

    @Override
    public List listarEspecialidad() {
        List listar = this.getSession().createCriteria(AcEspecialidad.class).
                add(Restrictions.eq("EspActivo", "1")).
                addOrder(Order.asc("EspNombre")).list();
        return listar;
    }

    @Override
    public void actualizarSesionDocente(int ses_id, int doc_id, Date hora_ini, Date hora_fin, String ses_tipo) {
        String hqlUpdate = "UPDATE AcSesionAsistencia SET docId = :v_doc_id, "
                + " sesFechaInicio = :v_hora_ini, "
                + " sesFechaFin = :v_hora_fin, "
                + " sesTipo = :v_ses_tipo "
                + " WHERE sesId = :v_id";

        this.getSession().createQuery(hqlUpdate).
                setInteger("v_doc_id", doc_id).
                setTimestamp("v_hora_ini", hora_ini).
                setTimestamp("v_hora_fin", hora_fin).
                setString("v_ses_tipo", ses_tipo).
                setInteger("v_id", ses_id).
                executeUpdate();
    }
    
    @Override
    public void actualizarObservacionSesionDocente(int ses_id, String sesEstadoDocTipo,String sesObservacion) {

        String hqlUpdate = "UPDATE AcSesionAsistencia SET sesEstadoDocTipo = :v_ses_estado_doc_tipo, "
                + " sesObservacion = :v_ses_observacion "
                + " WHERE sesId = :v_id";

        this.getSession().createQuery(hqlUpdate).
                setString("v_ses_estado_doc_tipo", sesEstadoDocTipo).
                setString("v_ses_observacion", sesObservacion).
                setInteger("v_id", ses_id).
                executeUpdate();
    }

    @Override
    public void ingresarSesionAsistencia(AcSesionAsistencia sesasis) {
        this.getHibernateTemplate().save(sesasis);
    }

    @Override
    public void eliminarSesionDocente(String ses_id) {
        System.out.println("Entro a eliminarSesionDocente");
        String hqlUpdate = "UPDATE AcSesionAsistencia SET sesActivo = 0 "
                + " WHERE sesId = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setString("v_id", ses_id).
                executeUpdate();
    }
}
