/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.*;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSAlumnoCLDAOImpl extends HibernateDaoSupport implements HSAlumnoCLDAO {

    @Override
    public void insertarAlumnocl( ClAlumno alu ) throws DataAccessException {
        this.getHibernateTemplate().save( alu );
    }

    @Override
    public void modificarAlumnocl( ClAlumno alu ) throws DataAccessException {
        this.getHibernateTemplate().update( alu );
    }

    @Override
    public void eliminarAlumnocl( String alu_id ) throws DataAccessException {
        String hqlUpdate = "update ClAlumno set aluActivo = :v_activo where aluId = :v_id";
        this.getSession().createQuery( hqlUpdate ).setString( "v_activo", "0" ).setString( "v_id", alu_id ).executeUpdate();
    }

    @Override
    public List<ClAlumno> seleccionarAlumnosLibres( String codigo, String paterno, String materno, String nombre ) throws DataAccessException, SQLException {
        Criteria criteria = this.getSession().createCriteria( ClAlumno.class ).
                add( Restrictions.eq( "aluActivo", "1" ) );

        if ( codigo.trim().length() != 0 ) {
            criteria.add( Restrictions.like( "aluCodigo", "%" + codigo + "%" ) );
        }
        if ( paterno.trim().length() != 0 ) {
            criteria.add( Restrictions.like( "aluAppaterno", "%" + paterno + "%" ) );
        }
        if ( materno.trim().length() != 0 ) {
            criteria.add( Restrictions.like( "aluApmaterno", "%" + materno + "%" ) );
        }
        if ( nombre.trim().length() != 0 ) {
            criteria.add( Restrictions.like( "aluNombres", "%" + nombre + "%" ) );
        }

        criteria.addOrder( Order.asc( "aluAppaterno" ) ).
                addOrder( Order.asc( "aluApmaterno" ) ).
                addOrder( Order.asc( "aluNombres" ) );

        return criteria.list();
    }

    @Override
    public String maximoCodigo() throws DataAccessException {
        Query query = this.getSession().getNamedQuery( "sp_Generar_Codigo" );

        return ( (Sp_generar_codigo)query.uniqueResult() ).getCodigocl();
    }

    @Override
    public ClAlumno buscarAlumnoPorAluId( Integer alu_id ) throws DataAccessException {
        return (ClAlumno)this.getSession().get( ClAlumno.class, alu_id );
    }

    @Override
    public List<ClAlumno> seleccionarAlumnosPorCodigo( String codigo ) throws Exception {
        return this.getSession().createCriteria( ClAlumno.class ).
                add( Restrictions.eq( "aluActivo", "1" ) ).
                add( Restrictions.like( "aluCodigo", codigo + "%" ) ).
                addOrder( Order.asc( "aluAppaterno" ) ).
                list();
    }

    @Override
    public List<ClAlumno> seleccionarAlumnosPorApellidos( String apellidos ) throws Exception {
        return this.getSession().createCriteria( ClAlumno.class ).
                add( Restrictions.eq( "aluActivo", "1" ) ).
                add( Restrictions.sqlRestriction(
                "concat({alias}.alu_appaterno,' ',{alias}.alu_apmaterno) like ?", "%" + apellidos + "%", Hibernate.STRING ) ).
                addOrder( Order.asc( "aluAppaterno" ) ).
                list();
    }

    @Override
    public List listarAlumnos_x_Seccion( int sec_id ) throws Exception {
        return this.getSession().createCriteria( ClAlumno.class, "ClAlumno" ).
                createCriteria( "ClAlumno.clMatriculas", "ClMatricula" ).
                add( Restrictions.eq( "ClMatricula.matTipo", "022001" ) ).
                add( Restrictions.eq( "ClMatricula.matActivo", "1" ) ).
                createCriteria( "ClMatricula.clMatriculaTallers", "ClMatriculaTaller" ).
                createCriteria( "ClMatriculaTaller.clSeccion", "ClSeccion" ).
                add( Restrictions.eq( "ClSeccion.secId", sec_id ) ).
                add( Restrictions.eq( "ClSeccion.secActivo", "1" ) ).
                addOrder( Order.asc( "ClAlumno.aluAppaterno" ) ).
                addOrder( Order.asc( "ClAlumno.aluApmaterno" ) ).
                list();
    }

    @Override
    public List<ClAlumno> listaAlumnos_Datos( String code, String paterno, String materno, String nombres, int mod_id ) {

        Criteria c = this.getSession().createCriteria( ClAlumno.class, "alu" ).
                createCriteria( "alu.clAlumnoTarifas", "alutar" ).
                createCriteria( "alutar.clSeccion", "sec" ).
                createCriteria( "sec.clArbolAcademico", "arb" ).
                createCriteria( "alutar.clEstructuraPagosDetalle", "estpagdet" ).
                createCriteria( "estpagdet.clEstructuraPagos", "estpag" ).
                add( Restrictions.eq( "alu.aluActivo", "1" ) ).
                add( Restrictions.eq( "alutar.alutarActivo", "1" ) ).
                add( Restrictions.eq( "estpagdet.estpagdetActivo", "1" ) ).
                add( Restrictions.eq( "estpag.estpagActivo", "1" ) );

        if ( code.trim().length() != 0 ) {
            c.add( Restrictions.like( "alu.aluCodigo", code + "%" ) );
        }
        if ( paterno.trim().length() != 0 ) {
            c.add( Restrictions.like( "alu.aluAppaterno", paterno + "%" ) );
        }
        if ( materno.trim().length() != 0 ) {
            c.add( Restrictions.like( "alu.aluApmaterno", materno + "%" ) );
        }
        if ( nombres.trim().length() != 0 ) {
            c.add( Restrictions.like( "alu.aluNombres", "%" + nombres + "%" ) );
        }
        if ( mod_id != 0 ) {
            c.add( Restrictions.eq( "arb.arbId", mod_id ) );
        }

        c.addOrder( Order.asc( "alu.aluAppaterno" ) ).
                addOrder( Order.asc( "alu.aluApmaterno" ) );
        return c.list();
    }

    @Override
    public List<ClAlumnoTarifa> listaAlumnoTarifa( int alu_id, int mod_id ) {
        Criteria c = this.getSession().createCriteria( ClAlumnoTarifa.class, "alutar" ).
                createCriteria( "alutar.clEstructuraPagosDetalle", "estpagdet" ).
                createCriteria( "estpagdet.clEstructuraPagos", "estpag" ).
                add( Restrictions.eq( "alutar.alutarActivo", "1" ) ).
                add( Restrictions.eq( "alutar.modId", mod_id ) ).
                add( Restrictions.eq( "estpagdet.estpagdetActivo", "1" ) ).
                add( Restrictions.eq( "estpag.estpagActivo", "1" ) ).
                add( Restrictions.eq( "alutar.clAlumno.aluId", alu_id ) ).
                addOrder( Order.asc( "alutar.clEstructuraPagosDetalle.estpagdetId" ) );

        return c.list();
    }

    @Override
    public boolean existeAlumnoUnivRegistrado( Integer id ) {
        return this.getSession().createCriteria( ClAlumno.class ).
                add( Restrictions.and(
                Restrictions.eq( "aluActivo", "1" ),
                Restrictions.eq( "aluCp", id ) ) ).uniqueResult() != null;
    }

    @Override
    public TbDistrito ObtenerDistrito( String dis ) {
        return (TbDistrito)this.getSession().createCriteria( TbDistrito.class ).
                add( Restrictions.eq( "Id", dis ) ).uniqueResult();

    }

    @Override
    public Integer maximoAluId() {
        List lista = new ArrayList();
        ProjectionList pList = Projections.projectionList();
        pList.add( Projections.max( "aluId" ) );
        lista = this.getSession().createCriteria( ClAlumno.class ).setProjection( pList ).list();
        return (Integer)lista.get( 0 );
    }

    @Override
    public List<AdPago> listaPagosAlumno( int alutarId ) {
        Criteria c = this.getSession().createCriteria( AdPago.class, "AdPago" ).
                createCriteria( "AdPago.Compag", "AdComprobantePago" ).
                createCriteria( "AdPago.Conpag", "AdConceptoPago" ).
                add( Restrictions.eq( "AdPago.PagActivo", "1" ) ).
                add( Restrictions.eq( "AdComprobantePago.CompagActivo", "1" ) ).
                add( Restrictions.eq( "AdConceptoPago.ConpagActivo", "1" ) ).
                //add( Restrictions.eq( "AdConceptoPago.ConpagTipo", "007003" ) ).
                add( Restrictions.eq( "AdComprobantePago.CompagTipo", "037001" ) ).
                add( Restrictions.eq( "AdComprobantePago.CompagClienteTipo", "014003" ) ).
                add( Restrictions.eq( "AdPago.AlutarId", alutarId ) );
        return c.list();

    }

    @Override
    public List<ClAlumnoTarifa> consultDeudaALumno( int alu_id ) {
        return this.getSession().createSQLQuery( "select * from cl_matricula mat "
                + " inner join cl_alumno_tarifa alutar  ON mat.mat_id=alutar.mat_id where "
                + " alutar.alu_id=? and mat.mat_activo='1' and mat.mat_tipo='022001' and alutar.alutar_fecha_prorroga<CURDATE() and alutar.alutar_activo='1' "
                + " and alutar.alutar_estado=1" ).addEntity( ClAlumnoTarifa.class ).setInteger( 0, alu_id ).list();
    }

    @Override
    public AdComprobantePago consultFechaMatricula( int mat_id ) {
        String sQuery;
        AdComprobantePago adCompAlumno;
        sQuery = "select ad_comprobante_pago.*  from cl_matricula "
                + " INNER JOIN cl_alumno_tarifa ON cl_alumno_tarifa.mat_id = cl_matricula.mat_id "
                + " INNER JOIN ad_pago ON ad_pago.alutar_id = cl_alumno_tarifa.alutar_id "
                + " INNER JOIN ad_comprobante_pago on ad_comprobante_pago.compag_id = ad_pago.compag_id "
                + " where mat_activo = 1 and mat_tipo = '022001'  and pag_activo = 1 and compag_activo = 1 and alutar_activo = 1 "
                + " and compag_tipo ='037001' and compag_cliente_tipo ='014003' and cl_matricula.mat_id =:p_mat_id "
                + " GROUP BY cl_alumno_tarifa.alutar_id";
        adCompAlumno = (AdComprobantePago)this.getSession().
                createSQLQuery( sQuery ).
                addEntity( AdComprobantePago.class ).
                setInteger( "p_mat_id", mat_id ).uniqueResult();
        return adCompAlumno;

    }

    @Override
    public List listarAlumnosPorSeccion( int sec_id ) {
        List lista;

        String hqlQuery = "select clAlumno.aluAppaterno, clAlumno.aluApmaterno, clAlumno.aluNombres, "
                + "clAlumno.aluId, clAlumno.aluCodigo, clAlumno.aluPassword "
                + "from ClAlumno as clAlumno "
                + "inner join clAlumno.clMatriculas as clMatriculas "
                + "inner join clMatriculas.clMatriculaTallers as clMatriculaTallers "
                + "inner join clMatriculaTallers.clSeccion as clSeccion "
                + "where clAlumno.aluActivo='1' "
                + "and clMatriculas.matTipo='022001' "
                + "and clMatriculas.matActivo='1' "
                + "and clSeccion.secId= :v_sec_id "
                + "order by clAlumno.aluAppaterno, clAlumno.aluApmaterno, clAlumno.aluNombres";
        /*
         * 0->paterno 1->materno 2->nombres 3->alu_id 4->alu_codigo
         * 5->alu_password
         */
        lista = this.getSession().createQuery( hqlQuery ).setInteger( "v_sec_id", sec_id ).list();

        return lista;
    }

    @Override
    public List listarAlumnosPorModulo( List<Integer> lstArbCurIds, int iAnio, int iMes, int iSedeId ) {
        /*
         * 0 alu_id 1 alu_codigo 2 alu_appaterno 3 alu_apmaterno 4 alu_nombre 5
         * alu_dni
         */
        SQLQuery sqlQuery;
        List lista;

        String hqlQuery = "SELECT a.alu_id, a.alu_codigo, a.alu_appaterno, a.alu_apmaterno, a.alu_nombres, a.alu_dni FROM cl_alumno a "
                + "INNER JOIN cl_matricula m ON(a.alu_id = m.alu_id) "
                + "INNER JOIN cl_matricula_taller mt ON(m.mat_id=mt.mat_id) "
                + "INNER JOIN cl_seccion s ON(mt.sec_id = s.sec_id) "
                + "INNER JOIN cl_arbol_academico aacTall ON(s.arb_id=aacTall.arb_id) "
                + "WHERE m.mat_tipo='022001' "
                + "AND m.mat_activo = '1' "
                + "AND s.sec_activo='1' "
                + "AND aacTall.arb_id_padre IN(:p_list) ";
        if ( iAnio != 0 ) {
            hqlQuery += "AND YEAR(s.sec_finicio)=:p_anio ";
        }
        if ( iMes != 0 ) {
            hqlQuery += "AND MONTH(s.sec_finicio) =:p_mes ";
        }
        if ( iSedeId != 0 ) {
            hqlQuery += "AND s.loc_id=:p_sede";
        }


        sqlQuery = this.getSession().createSQLQuery( hqlQuery + " GROUP BY a.alu_id ORDER BY a.alu_appaterno, a.alu_apmaterno, a.alu_nombres" );
        sqlQuery.setParameterList( "p_list", lstArbCurIds );

        if ( iAnio != 0 ) {
            sqlQuery.setParameter( "p_anio", iAnio );
        }
        if ( iMes != 0 ) {
            sqlQuery.setParameter( "p_mes", iMes );
        }
        if ( iSedeId != 0 ) {
            sqlQuery.setParameter( "p_sede", iSedeId );
        }
        lista = sqlQuery.list();
        return lista;
    }

    @Override
    public List<BeanReporte> listarCursosLlevados( int iAluId ) {
        int iSize;
        Double dNota;
        String sQuery;
        BeanReporte repCurso;
        ClArbolAcademico arbTal;
        ClArbolAcademico arbCur;
        ClArbolAcademico arbMod;
        ClArbolAcademico arbArea;
        SQLQuery sqlQuery;
        Object[] objFila;
        List lstAux;
        List<BeanReporte> lstCursosLlevados = new ArrayList<BeanReporte>();

        sQuery = "SELECT \n"
                + "aArea.arb_descripcion, \n"//0
                + "s.arb_id, \n"//1
                + "s.sec_nombre,\n"//2
                + "s.sec_finicio,\n"//3
                + "s.sec_ffin,\n"//4
                //                + "IFNULL(CAST((SELECT n.not_nota FROM cl_nota n WHERE n.alu_id=m.alu_id AND n.sec_id=mt.sec_id AND n.not_tipo='034002' AND n.not_activo=1 LIMIT 1) AS SIGNED),\"SIN PROMEDIO\") promedio \n"//5
                + "s.sec_id \n"//5
                + "FROM cl_matricula m \n"
                + "INNER JOIN cl_matricula_taller mt ON(m.mat_id=mt.mat_id) \n"
                + "INNER JOIN cl_seccion s ON(mt.sec_id=s.sec_id) \n"
                + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) \n"
                + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) \n"
                + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id) \n"
                + "INNER JOIN cl_arbol_academico aArea ON(aMod.arb_id_padre=aArea.arb_id) \n"
                + "WHERE m.alu_id=:alu_id AND m.mat_activo=1 AND m.mat_tipo='022001' AND mt.mattal_activo=1 \n"
                + "AND s.sec_activo=1 AND aTal.arb_activo=1 AND aCur.arb_activo=1 AND aMod.arb_activo=1 \n"
                + "AND aArea.arb_activo=1 \n"
                + "ORDER BY aArea.arb_descripcion,s.sec_finicio DESC\n";
        try {
            sqlQuery = this.getSession().createSQLQuery( sQuery );
            sqlQuery.setInteger( "alu_id", iAluId );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            for ( int i = 0; i < iSize; i++ ) {
                objFila = (Object[])lstAux.get( i );
                arbTal = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( CommonWeb.parseObjToInt( objFila[1] ) );
                arbCur = arbTal.getArbAcadPadre();
                arbMod = arbCur.getArbAcadPadre();
                repCurso = new BeanReporte();
                repCurso.setExpr1( objFila[0] + "" );//Area
                repCurso.setExpr2( arbMod.getArbDescripcion() );//Modulo
                repCurso.setExpr3( arbCur.getArbDescripcion() );//Curso
                repCurso.setExpr4( objFila[2] + "" );//Seccion
                repCurso.setExpr5( objFila[3] + "" );//Fecha inicio
                repCurso.setExpr6( objFila[4] + "" );//Fecha fin
                dNota = CommonDAO.getClNotasDAO().obtenerPormedio( CommonWeb.parseObjToInt( objFila[5] ), iAluId );
                repCurso.setExpr7( dNota == null ? "SIN PROMEDIO" : dNota + "" );//Promedio
                lstCursosLlevados.add( repCurso );
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }


        return lstCursosLlevados;
    }

    @Override
    public List<BeanReporte> listarReporteAsistencias( String sCodAlumno, Date fechaInicio, Date fechaFin ) {
        int iSize;
        BeanReporte repAsist;
        String sQuery;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<BeanReporte> lstAsist;

        sQuery = "SELECT t.* FROM ("
                + "select sa.ses_id,"//1
                + "alu_codigo,"//2
                + "concat_ws(' ', a.alu_appaterno, a.alu_apmaterno, a.alu_nombres) nombres,"//3
                + "s.sec_id,"//4
                + "d.doc_nombre docente,"//5
                + "aMod.arb_descripcion Modulo,"//6
                + "s.sec_nombre,"//7
                + "aCur.arb_descripcion Curso,"//8
                + "sa.ses_fecha_registro,"//9
                + "convert(sa.ses_fecha_inicio, time) inicio, "//10
                + "convert(sa.ses_fecha_fin, time) fin, "//11
                + "if( sa.ses_fecha_registro < convert(m.mat_fecha, date),'NO PRESENTA MATRICULA', "
                + "if( isnull( "
                + "( select c.cat_descripcion_elemento "
                + "from tb_catalogo c "
                + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia) ), "
                + "'NO  SE TOMO ASISTENCIA', "
                + "(select c.cat_descripcion_elemento "
                + "from tb_catalogo c "
                + "where concat(c.cat_codigo_grupo, c.cat_codigo_elemento)=sesefeasis_det_asistencia))"
                + ") asistencia "//12
                + "from cl_alumno a "
                + "inner join cl_matricula m on a.alu_id=m.alu_id "
                + "inner join cl_matricula_taller mt on mt.mat_id=m.mat_id "
                + "inner join cl_seccion s on s.sec_id=mt.sec_id "
                + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) "
                + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) "
                + "INNER JOIN cl_arbol_academico aMod ON(aCur.arb_id_padre=aMod.arb_id) "
                + "inner join ac_sesion_asistencia sa on sa.sec_id=s.sec_id "
                + "inner join ac_docente d on d.doc_id=sa.doc_id "
                + "left join ( "
                + "select "
                + "sea.ses_id, "
                + "sea.sesefeasis_registro, "
                + "sead.alu_id,"
                + "min(sead.sesefeasis_det_asistencia) sesefeasis_det_asistencia "
                + "from ac_sesion_efectiva_asistencia sea "
                + "inner join ac_sesion_efectiva_asis_det_cl sead on (sea.sesefeasis_id=sead.sesefeasis_id)"
                + "inner join cl_alumno a2 on (a2.alu_id=sead.alu_id)"
                + "where sea.sesefeasis_activo='1' "
                + "and sead.sesefeasis_det_activo='1' "
                + "group by sea.ses_id "
                + ") tabla on (tabla.ses_id= sa.ses_id and tabla.alu_id=a.alu_id) "
                + "where m.mat_activo='1' "
                + "and m.mat_tipo='022001' "
                + "and s.sec_activo='1' "
                + "and aTal.arb_activo='1' "
                + "and aCur.arb_activo='1' "
                + "and aMod.arb_activo='1' "
                + "and sa.ses_tipo_asistencia='050003' "
                + "and a.alu_codigo=:alu_codigo "
                + "and (sa.ses_fecha_registro>=:fecha_inicio and sa.ses_fecha_registro<=:fecha_fin) "
                + "group by sa.ses_id order by sa.ses_fecha_registro,s.sec_nombre,inicio)t";

        lstAsist = new ArrayList<BeanReporte>();
        try {
            sqlQuery = this.getSession().createSQLQuery( sQuery );
            sqlQuery.setString( "alu_codigo", sCodAlumno );
            sqlQuery.setDate( "fecha_inicio", fechaInicio );
            sqlQuery.setDate( "fecha_fin", fechaFin );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[])lstAux.get( i );
                repAsist = new BeanReporte();
                repAsist.setExpr1( CommonWeb.parseObjToString( aobjFila[0] ) );
                repAsist.setExpr2( CommonWeb.parseObjToString( aobjFila[1] ) );
                repAsist.setExpr3( CommonWeb.parseObjToString( aobjFila[2] ) );
                repAsist.setExpr4( CommonWeb.parseObjToString( aobjFila[3] ) );
                repAsist.setExpr5( CommonWeb.parseObjToString( aobjFila[4] ) );
                repAsist.setExpr6( CommonWeb.parseObjToString( aobjFila[5] ) );
                repAsist.setExpr7( CommonWeb.parseObjToString( aobjFila[6] ) );
                repAsist.setExpr8( CommonWeb.parseObjToString( aobjFila[7] ) );
                repAsist.setExpr9( CommonWeb.parseObjToString( aobjFila[8] ) );
                repAsist.setExpr10( CommonWeb.parseObjToString( aobjFila[9] ) );
                repAsist.setExpr11( CommonWeb.parseObjToString( aobjFila[10] ) );
                repAsist.setExpr12( CommonWeb.parseObjToString( aobjFila[11] ) );
                repAsist.setExpr13( ( i + 1 ) + "" );
                lstAsist.add( repAsist );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lstAsist;
    }

    @Override
    public List<ClObservacionDesercion> listarLlamadas( int iAluId, int iSecId ) {
        String sQuery;
        SQLQuery sqlQuery;
        List<ClObservacionDesercion> lstObsLlamadas;
        try {
            sQuery = "SELECT od.* FROM cl_alumno_desertor ad "
                    + "INNER JOIN cl_observacion_desercion od ON(ad.id_alumno_desertor=od.id_alumno_desertor) "
                    + "WHERE ad.alu_id=:alu_id AND ad.sec_id_ult=:sec_id AND ad.activo=1 AND od.activo=1 ORDER BY od.fecha_registro DESC";
            sqlQuery = this.getSession().createSQLQuery( sQuery );
            sqlQuery.setInteger( "alu_id", iAluId );
            sqlQuery.setInteger( "sec_id", iSecId );
            lstObsLlamadas = sqlQuery.addEntity( ClObservacionDesercion.class ).list();
        } catch ( Exception ex ) {
            ex.printStackTrace();
            lstObsLlamadas = new ArrayList<ClObservacionDesercion>();
        }

        return lstObsLlamadas;
    }
}