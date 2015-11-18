/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.text.SimpleDateFormat;
import java.util.*;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccion;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClSeccion;
import net.uch.util.CommonDAO;
import net.uch.util.ConstantesWeb;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Alumno
 */
public class HSArbolAcademicoClDaoImpl extends HibernateDaoSupport implements HSArbolAcademicoClDao {

    @Override
    public List<ClArbolAcademico> listarModulosxAlumno( int iAluId ) {
        String sSqlQuery;
        List lstModXAluIdAux;
        List<ClArbolAcademico> lstModXAluId;

        sSqlQuery = "SELECT * FROM cl_arbol_academico ac2 "
                + "INNER JOIN ( (select ac.arb_id_padre, ac.arb_id,ac.arb_nivel from cl_arbol_academico ac "
                + "INNER JOIN ( SELECT ac.arb_id,ac.arb_id_padre,ac.arb_nivel, ac.arb_descripcion FROM cl_alumno a "
                + "INNER JOIN cl_matricula m ON( a.alu_id = m.alu_id ) "
                + "INNER JOIN cl_matricula_taller mt ON(m.mat_id=mt.mat_id) "
                + "INNER JOIN cl_seccion s ON(mt.sec_id = s.sec_id) "
                + "INNER JOIN cl_arbol_academico ac ON(s.arb_id = ac.arb_id) "
                + "WHERE a.alu_id=:v_alu_id AND ac.arb_activo=1 "
                + "AND a.alu_activo=1 AND m.mat_activo=1 ) "
                + "as tb_tall_x_alu_id ON(ac.arb_id=tb_tall_x_alu_id.arb_id_padre) ) "
                + "tb_cursos_tmp ) ON(ac2.arb_id=tb_cursos_tmp.arb_id_padre) "
                + "GROUP BY ac2.arb_id";

        lstModXAluIdAux = this.getSession().
                createSQLQuery( sSqlQuery ).
                setInteger( "v_alu_id", iAluId ).
                list();

        lstModXAluId = new ArrayList<ClArbolAcademico>();
        cargarListaArbol( lstModXAluId, lstModXAluIdAux );

        return lstModXAluId;
    }

    @Override
    public List listarSeccionesxAlumnoModulo( int iAluId, int iArbModId ) {
        int iSizeSecc;
        String sSqlQuery;
        ClArbolAcademico arbCurso;
        ClArbolAcademico arbTaller;
        Date fechaInicio;
        Date fechaFin;
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        BeanClSeccion clSeccionBean;
        Object[] obj;
        List lstSeccXAlumModAux;
        List<BeanClSeccion> lstSeccXAlumMod = new ArrayList<BeanClSeccion>();
        sSqlQuery = "call sp_secciones_x_mod_id_y_alu_id(:v_alu_id,:v_arb_id);";
        try {
            lstSeccXAlumModAux = this.getSession().
                    createSQLQuery( sSqlQuery ).
                    setInteger( "v_alu_id", iAluId ).
                    setInteger( "v_arb_id", iArbModId ).
                    list();
        } catch ( Exception ex ) {
            lstSeccXAlumModAux = new ArrayList();
        }

        iSizeSecc = lstSeccXAlumModAux.size();
        for ( int i = 0; i < iSizeSecc; i++ ) {
            clSeccionBean = new BeanClSeccion();
            obj = (Object[]) lstSeccXAlumModAux.get( i );

            clSeccionBean.setSecId( (Integer) obj[ConstantesWeb.INDX_OBJ_SEC_ID] );
            clSeccionBean.setTallId( (Integer) obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID] );
            clSeccionBean.setSecNombre( obj[ConstantesWeb.INDX_OBJ_SEC_NOMBRE].toString() );

            try {
                fechaInicio = dateFormat.parse( obj[ConstantesWeb.INDX_OBJ_SEC_FINICIO].toString() );
            } catch ( Exception ex ) {
                fechaInicio = null;
            }
            try {
                fechaFin = dateFormat.parse( obj[ConstantesWeb.INDX_OBJ_SEC_FFIN].toString() );
            } catch ( Exception ex ) {
                fechaFin = null;
            }

            clSeccionBean.setSecFinicio( fechaInicio );
            clSeccionBean.setSecFfin( fechaFin );

            clSeccionBean.setLocId( (Integer) obj[ConstantesWeb.INDX_OBJ_LOC_ID] );
            try {
                arbTaller = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( (Integer) obj[ConstantesWeb.INDX_OBJ_ARB_TALL_ID] );
                arbCurso = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( arbTaller.getArbIdPadre() );
            } catch ( Exception ex ) {
                arbTaller = null;
                arbCurso = null;
            }

            clSeccionBean.setNomCurso( arbCurso == null ? "" : arbCurso.getArbDescripcion() );
            clSeccionBean.setClArbolAcademico( arbTaller );

            lstSeccXAlumMod.add( clSeccionBean );
        }

        return lstSeccXAlumMod;
    }

    @Override
    public List<ClArbolAcademico> listarAreaxAnoYSede( int iAnio, int iSedeId, int iMes ) {
        int iIndxSecId = 0;
        int iIndxArbId = 1;
        int iIndxArbnivel = 2;
        Integer iArbId;
        ClArbolAcademico clArbTall;
        ClArbolAcademico clArbCur;
        ClArbolAcademico clArbMod;
        ClArbolAcademico clArbArea;
        String sHqlQuery;
        Object[] objSecArb;
        List<ClArbolAcademico> lstAreasXAnioSede;
        Map<Integer, ClArbolAcademico> hmAreasXAnioSede;
        List lstSeccionesXAnioSede;

        lstAreasXAnioSede = new ArrayList<ClArbolAcademico>();
        hmAreasXAnioSede = new HashMap<Integer, ClArbolAcademico>();

        lstSeccionesXAnioSede = this.listarSeccionesXAnoYsede( iAnio, iSedeId );

        if ( lstSeccionesXAnioSede != null ) {
            for ( Object objAux : lstSeccionesXAnioSede ) {
                objSecArb = (Object[]) objAux;
                iArbId = Integer.parseInt( String.valueOf( objSecArb[iIndxArbId] ) );
                try {
                    clArbTall = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( iArbId );
                    if ( clArbTall != null && "1".equals( clArbTall.getArbActivo() ) ) {
                        clArbCur = clArbTall.getArbAcadPadre();
                        if ( clArbCur != null && "1".equals( clArbCur.getArbActivo() ) ) {
                            clArbMod = clArbCur.getArbAcadPadre();
                            if ( clArbMod != null && "1".equals( clArbMod.getArbActivo() ) ) {
                                clArbArea = clArbMod.getArbAcadPadre();
                                if ( clArbArea != null && !hmAreasXAnioSede.containsKey( clArbArea.getArbId() ) && "1".equals( clArbArea.getArbActivo() ) ) {
                                    hmAreasXAnioSede.put( clArbArea.getArbId(), clArbArea );
                                }
                            }
                        }
                    }
                } catch ( Exception ex ) {
                }
            }
            lstAreasXAnioSede = new ArrayList<ClArbolAcademico>( hmAreasXAnioSede.values() );
        }

        return lstAreasXAnioSede;
    }

    @Override
    public List<ClArbolAcademico> listarModulosxAreaAnoYSede( int iAreaId, int iAnio, int iSedeId ) {
        int iIndxSecId = 0;
        int iIndxArbId = 1;
        int iIndxArbnivel = 2;
        Integer iArbId;
        ClArbolAcademico clArbTall;
        ClArbolAcademico clArbCur;
        ClArbolAcademico clArbMod;
        Object[] objSecArb;
        List<ClArbolAcademico> lstModulosXAnioSede;
        Map<Integer, ClArbolAcademico> hmModulosXAnioSede;
        List lstSeccionesXAnioSede;

        lstModulosXAnioSede = new ArrayList<ClArbolAcademico>();
        hmModulosXAnioSede = new HashMap<Integer, ClArbolAcademico>();


        lstSeccionesXAnioSede = this.listarSeccionesXAnoYsede( iAnio, iSedeId );

        if ( lstSeccionesXAnioSede != null ) {
            for ( Object objAux : lstSeccionesXAnioSede ) {
                objSecArb = (Object[]) objAux;
                iArbId = Integer.parseInt( String.valueOf( objSecArb[iIndxArbId] ) );
                try {
                    clArbTall = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( iArbId );
                    if ( clArbTall != null && "1".equals( clArbTall.getArbActivo() ) ) {
                        clArbCur = clArbTall.getArbAcadPadre();
                        if ( clArbCur != null && "1".equals( clArbCur.getArbActivo() ) ) {
                            clArbMod = clArbCur.getArbAcadPadre();
                            if ( clArbMod != null && "1".equals( clArbMod.getArbActivo() ) && clArbMod.getArbIdPadre().intValue() == iAreaId ) {
                                if ( !hmModulosXAnioSede.containsKey( clArbMod.getArbId() ) ) {
                                    hmModulosXAnioSede.put( clArbMod.getArbId(), clArbMod );
                                }
                            }
                        }
                    }
                } catch ( Exception ex ) {
                }
            }
            lstModulosXAnioSede = new ArrayList<ClArbolAcademico>( hmModulosXAnioSede.values() );
        }

        return lstModulosXAnioSede;
    }

    @Override
    public List<ClArbolAcademico> listarCursosXModAnoYSede( int iModId, int iAnio, int iSedeId ) {
        int iIndxSecId = 0;
        int iIndxArbId = 1;
        int iIndxArbnivel = 2;
        Integer iArbId;
        ClArbolAcademico clArbTall;
        ClArbolAcademico clArbCur;
        Object[] objSecArb;
        List<ClArbolAcademico> lstCursosXAnioSede;
        Map<Integer, ClArbolAcademico> hmCursosXAnioSede;
        List lstSeccionesXAnioSede;

        lstCursosXAnioSede = new ArrayList<ClArbolAcademico>();
        hmCursosXAnioSede = new HashMap<Integer, ClArbolAcademico>();


        lstSeccionesXAnioSede = this.listarSeccionesXAnoYsede( iAnio, iSedeId );

        if ( lstSeccionesXAnioSede != null ) {
            for ( Object objAux : lstSeccionesXAnioSede ) {
                objSecArb = (Object[]) objAux;
                iArbId = Integer.parseInt( String.valueOf( objSecArb[iIndxArbId] ) );
                try {
                    clArbTall = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( iArbId );
                    if ( clArbTall != null && "1".equals( clArbTall.getArbActivo() ) ) {
                        clArbCur = clArbTall.getArbAcadPadre();
                        if ( clArbCur != null && "1".equals( clArbCur.getArbActivo() ) && clArbCur.getArbIdPadre().intValue() == iModId ) {
                            if ( !hmCursosXAnioSede.containsKey( clArbCur.getArbId() ) ) {
                                hmCursosXAnioSede.put( clArbCur.getArbId(), clArbCur );
                            }
                        }
                    }
                } catch ( Exception ex ) {
                }
            }
            lstCursosXAnioSede = new ArrayList<ClArbolAcademico>( hmCursosXAnioSede.values() );
        }

        return lstCursosXAnioSede;
    }

//    @Override
//    public List<ClSeccion> listarSeccionesXCursoAnoYSede( int iCurId, int iAno, int iSedId ) {
//        int iIndxSecId = 0;
//        int iIndxArbId = 1;
//        int iIndxArbnivel = 2;
//        Integer iArbId;
//        Integer iSecId;
//        ClArbolAcademico clArbCur;
//        ClSeccion clSeccion;
//        Object[] objSecArb;
//        List<ClSeccion> lstSeccionesXCursoAnioSede;
//        Map<Integer, ClSeccion> hmSeccionesXCursoAnioSede;
//        List lstSeccionesXAnioSede;
//
//        lstSeccionesXCursoAnioSede = new ArrayList<ClSeccion>();
//        hmSeccionesXCursoAnioSede = new HashMap<Integer, ClSeccion>();
//
//
//        //-----
//        String sHqlQuery;
//
//        sHqlQuery = "SELECT s.sec_id,aa.arb_id,aa.arb_nivel "
//                + "FROM cl_seccion s INNER JOIN cl_arbol_academico aa ON(s.arb_id = aa.arb_id) "
//                + "WHERE s.sec_activo=1 AND s.loc_id=:v_sed_id AND year(s.sec_finicio)=:v_anio GROUP BY s.sec_id ORDER BY s.sec_id";
//
//        lstSeccionesXAnioSede = this.getSession().
//                createSQLQuery( sHqlQuery ).
//                setInteger( "v_anio", iAno ).
//                setInteger( "v_sed_id", iSedId ).
//                list();
//        //-----
//
////        lstSeccionesXAnioSede = this.listarSeccionesXAnoYsede( iAno, iSedId );
//
//        if ( lstSeccionesXAnioSede != null ) {
//            for ( Object objAux : lstSeccionesXAnioSede ) {
//                objSecArb = (Object[]) objAux;
//                iArbId = Integer.parseInt( String.valueOf( objSecArb[iIndxArbId] ) );
//                iSecId = Integer.parseInt( String.valueOf( objSecArb[iIndxSecId] ) );
//                try {
//                    clSeccion = CommonDAO.getClSeccionDAO().seleccionarSeccion( iSecId );
//                    clArbCur = CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( iArbId ).getArbAcadPadre();
//                    if ( clSeccion != null && clArbCur.getArbId().intValue() == iCurId ) {
//                        lstSeccionesXCursoAnioSede.add( clSeccion );
//                    }
//                } catch ( Exception ex ) {
//                }
//            }
////            lstSeccionesXCursoAnioSede = new ArrayList<ClSeccion>( hmSeccionesXCursoAnioSede.values() );
//        }
//
//        return lstSeccionesXCursoAnioSede;
//    }
    @Override
    public List<ClSeccion> listarSeccionesXCursoAnoYSede( int iCurId, int iAno, int iSedId ) {
        int iIndxSecId = 0;
        int iIndxArbId = 1;
        int iIndxArbnivel = 2;
        Integer iArbId;
        Integer iSecId;
        ClArbolAcademico clArbCur;
        ClSeccion clSeccion;
        Object[] objSecArb;
        List<ClSeccion> lstSeccionesXCursoAnioSede;
        Map<Integer, ClSeccion> hmSeccionesXCursoAnioSede;
        List lstSeccionesXAnioSede;

        lstSeccionesXCursoAnioSede = new ArrayList<ClSeccion>();
        hmSeccionesXCursoAnioSede = new HashMap<Integer, ClSeccion>();


        //-----
        String sHqlQuery;

        sHqlQuery = "SELECT s.* FROM cl_seccion s "
                + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) "
                + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) "
                + "WHERE s.sec_activo=1 AND aTal.arb_activo=1 AND aCur.arb_activo=1 "
                + "AND aCur.arb_id=:v_cur_id AND year(s.sec_finicio)=:v_anio AND s.loc_id=:v_sed_id";

         lstSeccionesXAnioSede = this.getSession().
                createSQLQuery( sHqlQuery ).
                setInteger( "v_anio", iAno ).
                setInteger( "v_sed_id", iSedId ).
                setInteger( "v_cur_id", iCurId ).
                list();
        //-----

//        lstSeccionesXAnioSede = this.listarSeccionesXAnoYsede( iAno, iSedId );

        for ( Object objAux : lstSeccionesXAnioSede ) {
            objSecArb = (Object[]) objAux;
            clSeccion = new ClSeccion( Integer.parseInt( objSecArb[0].toString() ) );
            clSeccion.setSecNombre( objSecArb[2].toString() );
            lstSeccionesXCursoAnioSede.add( clSeccion );
        }
//            lstSeccionesXCursoAnioSede = new ArrayList<ClSeccion>( hmSeccionesXCursoAnioSede.values() );

        return lstSeccionesXCursoAnioSede;
    }
    
    @Override
    public List<ClSeccion> listarSeccionesXTallerAnoYSede( int iTallId, int iAno, int iSedId, Date fecha_ini, Date fecha_fin ) {
        int iIndxSecId = 0;
        int iIndxArbId = 1;
        int iIndxArbnivel = 2;
        Integer iArbId;
        Integer iSecId;
        ClArbolAcademico clArbCur;
        ClSeccion clSeccion;
        Object[] objSecArb;
        List<ClSeccion> lstSeccionesXCursoAnioSede;
        Map<Integer, ClSeccion> hmSeccionesXCursoAnioSede;
        List lstSeccionesXAnioSede;

        lstSeccionesXCursoAnioSede = new ArrayList<ClSeccion>();
        hmSeccionesXCursoAnioSede = new HashMap<Integer, ClSeccion>();


        //-----
        String sHqlQuery;

        sHqlQuery = "SELECT s.* FROM cl_seccion s "
                + " INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) "
                + " WHERE s.sec_activo=1 AND aTal.arb_activo=1 "
                + " AND aTal.arb_id=:v_tall_id AND year(s.sec_finicio)=:v_anio AND s.loc_id=:v_sed_id AND "
                + " s.sec_finicio>=:finicio AND s.sec_ffin<=:ffin ";
         System.out.println( "entro" );
         lstSeccionesXAnioSede = this.getSession().
                createSQLQuery( sHqlQuery ).
                setInteger( "v_anio", iAno ).
                setInteger( "v_sed_id", iSedId ).
                setInteger( "v_tall_id", iTallId ).
                setDate( "finicio", fecha_ini).
                setDate( "ffin", fecha_fin).
                list();
        //-----

//        lstSeccionesXAnioSede = this.listarSeccionesXAnoYsede( iAno, iSedId );

        for ( Object objAux : lstSeccionesXAnioSede ) {
            objSecArb = (Object[]) objAux;
            clSeccion = new ClSeccion( Integer.parseInt( objSecArb[0].toString() ) );
            clSeccion.setSecNombre( objSecArb[2].toString() );
            lstSeccionesXCursoAnioSede.add( clSeccion );
        }
//            lstSeccionesXCursoAnioSede = new ArrayList<ClSeccion>( hmSeccionesXCursoAnioSede.values() );

        return lstSeccionesXCursoAnioSede;
    }
    

    @Override
    public List<ClArbolAcademico> listarSeccionesXAnoYsede( int iAnio, int iSedeId ) {
        String sHqlQuery;
        List lstSeccionesXAnioSede;

        sHqlQuery = "SELECT s.sec_id,aa.arb_id,aa.arb_nivel "
                + "FROM cl_seccion s INNER JOIN cl_arbol_academico aa ON(s.arb_id = aa.arb_id) "
                + "WHERE s.sec_activo=1 AND s.loc_id=:v_sed_id AND year(s.sec_finicio)=:v_anio GROUP BY aa.arb_id";

        lstSeccionesXAnioSede = this.getSession().
                createSQLQuery( sHqlQuery ).
                setInteger( "v_anio", iAnio ).
                setInteger( "v_sed_id", iSedeId ).
                list();
        return lstSeccionesXAnioSede;
    }

    @Override
    public ClArbolAcademico seleccionarArbol( int iArbId ) {
        ClArbolAcademico clArbolAcademico;

        clArbolAcademico = (ClArbolAcademico) this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbId", iArbId ) ).
                uniqueResult();
        return clArbolAcademico;
    }

    @Override
    public List<ClArbolAcademico> listarArbolPorPadre( int iIdPadre ) {
        List<ClArbolAcademico> lstArbolXPadre;

        lstArbolXPadre = this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) ).
                add( Restrictions.eq( "arbIdPadre", iIdPadre ) ).
                addOrder( Order.asc( "arbDescripcion" ) ).
                list();
        this.getSession().flush();
        return lstArbolXPadre;
    }
    
    @Override
    public List<ClArbolAcademico> AreasXInstitucion( String sInst ) {
        List<ClArbolAcademico> lstArea;

        lstArea = this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbNivel", 1 ) ).
                add( Restrictions.eq( "arbActivo", "1" ) ).
                add( Restrictions.like( "arbInstitucion", "%" + sInst + "%" ) ).
                addOrder( Order.asc( "arbDescripcion" ) ).
                list();

        return lstArea;
    }

    public List<ClArbolAcademico> listarArbCursosPorModSedeSedeAnioMes( int iArbModPadreId, int iArbCurId, int iSedeId, int iAnio, int iMes ) {
        Criteria criteriaArb;
        List<ClArbolAcademico> lstArbCurXModPadre;

//        sQuery = "SELECT aacCur.* FROM cl_seccion s INNER JOIN cl_arbol_academico aacTall ON(s.arb_id=aacTall.arb_id) "
//                + "INNER JOIN cl_arbol_academico aacCur ON(aacTall.arb_id_padre=aacCur.arb_id) "
//                + "WHERE s.sec_activo=1 AND aacTall.arb_activo=1 AND aacCur.arb_activo=1 "
//                + "AND aacCur.arb_id_padre=1567 "
//                + "AND YEAR(s.sec_finicio)=2012 AND MONTH(s.sec_finicio)=9 AND s.loc_id=1";

//        sQuery = "SELECT aacCur FROM ClSeccion s "
//                + "INNER JOIN s.clArbolAcademico aacTall "
//                + "INNER JOIN aacTall.clArbolAcademico aacCur "
//                + "WHERE s.secActivo=1 AND aacTall.arbActivo=1 AND aacCur.arbActivo=1 "
//                + "AND aacCur.arbIdPadre=1567 "
//                + "AND YEAR(s.secFinicio)=2012 AND MONTH(s.secFinicio)=9 AND s.locId=1";

        criteriaArb = this.getSession().createCriteria( ClArbolAcademico.class );
        criteriaArb.add( Restrictions.eq( "arbActivo", "1" ) );
        criteriaArb.add( Restrictions.eq( "arbIdPadre", iArbModPadreId ) );
        if ( iArbCurId != 0 ) {
            criteriaArb.add( Restrictions.eq( "arbId", iArbCurId ) );
        }
        lstArbCurXModPadre = criteriaArb.list();

        this.getSession().flush();
        return lstArbCurXModPadre;
    }

    @Override
    public List<ClArbolAcademico> listarModulos() {
        List<ClArbolAcademico> lstModulos;

        lstModulos = this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) ).
                add( Restrictions.eq( "arbTipo", "069001" ) ).
                addOrder( Order.asc( "arbDescripcion" ) ).
                list();
        this.getSession().flush();
        return lstModulos;
    }



    @Override
    public List<ClArbolAcademico> seleccionarArea( String sDescripcion ) {
        List<ClArbolAcademico> lstArea;

        lstArea = this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbNivel", 1 ) ).
                add( Restrictions.eq( "arbActivo", "1" ) ).
                add( Restrictions.like( "arbDescripcion", "%" + sDescripcion + "%" ) ).
                addOrder( Order.asc( "arbDescripcion" ) ).
                list();

        return lstArea;
    }

    @Override
    public void insertarArea( ClArbolAcademico arbArea ) throws Exception {
        List<ClArbolAcademico> lstArbArea = new ArrayList<ClArbolAcademico>();
        lstArbArea.add( arbArea );
        insertarListaArbol( lstArbArea );
    }

    @Override
    public List<ClArbolAcademico> obtenerUltimaArea() {
        List<ClArbolAcademico> lstUltimArea;
        lstUltimArea = this.getSession().
                createCriteria( ClArbolAcademico.class, "arbOpcion" ).
                add( Restrictions.eq( "arbNivel", 1 ) ).
                addOrder( Order.desc( "arbId" ) ).
                list();

        return lstUltimArea;
    }

    @Override
    public ClArbolAcademico buscarArbolArea( int iArbId ) throws Exception {
        return (ClArbolAcademico) this.getSession().createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbId", iArbId ) ).uniqueResult();
    }

    @Override
    public void actualizarArea( ClArbolAcademico area ) throws Exception {
        String sHqlUpdate;
        /*
         * this.getHibernateTemplate().update(area);
         */
        sHqlUpdate = "update ClArbolAcademico set arbCodigo = :codigo, "
                + "arbDescripcion = :descripcion, "
                + "arbActivo = :activo, arbVisibleArbol=:visible "
                + "where arbId = :id";
        this.getSession().createQuery( sHqlUpdate ).
                setString( "codigo", area.getArbCodigo() ).
                setString( "descripcion", area.getArbDescripcion() ).
                setString( "activo", "1" ).
                setInteger( "visible", area.getArbVisibleArbol() ).
                setInteger( "id", area.getArbId() ).
                executeUpdate();
    }

    @Override
    public void eliminarArbol( int iArbId ) throws Exception {
        String sHqlUpdate;

        sHqlUpdate = "update ClArbolAcademico set arbActivo = :activo where arbId = :id";
        this.getSession().createQuery( sHqlUpdate ).setString( "activo", "0" ).
                setInteger( "id", iArbId ).
                executeUpdate();
    }

    @Override
    public List seleccionarModulos( int iAreId, String sDescripcion ) throws Exception {
        Criteria criteria;

        criteria = this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) );

        if ( !sDescripcion.trim().isEmpty() ) {
            criteria.add( Restrictions.like( "arbDescripcion", "%" + sDescripcion + "%" ) );
        }
        if ( iAreId > 0 ) {
            criteria.add( Restrictions.eq( "arbIdPadre", iAreId ) );
        }
        return criteria.list();
    }
    
    @Override
    public List seleccionarModulosVisibles( int iAreId, String sDescripcion ) throws Exception {
        Criteria criteria;

        criteria = this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) ).
                add( Restrictions.eq( "arbVisible", 1 ));

        if ( !sDescripcion.trim().isEmpty() ) {
            criteria.add( Restrictions.like( "arbDescripcion", "%" + sDescripcion + "%" ) );
        }
        if ( iAreId > 0 ) {
            criteria.add( Restrictions.eq( "arbIdPadre", iAreId ) );
        }
        return criteria.list();
    }

    @Override
    public List obtenerDescripcionArea( int iArbId ) throws Exception {
        Criteria criteria;

        criteria = this.getSession().createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) );

        if ( iArbId > 0 ) {
            criteria.add( Restrictions.eq( "arbId", iArbId ) ).add( Restrictions.eq( "arbNivel", 1 ) );
        }
        return criteria.list();
    }

    @Override
    public List<ClArbolAcademico> seleccionarCursos( int iModId ) throws Exception {
        Criteria criteria;
        criteria = this.getSession().createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) );
        if ( iModId > 0 ) {
            criteria.add( Restrictions.eq( "arbIdPadre", iModId ) );
        }
        return criteria.list();
    }

    @Override
    public ClArbolAcademico buscarModulo( int iModId ) throws Exception {
        return (ClArbolAcademico) this.getSession().createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbId", iModId ) ).
                add( Restrictions.eq( "arbNivel", 2 ) ).
                uniqueResult();
    }

    @Override
    public void actualizarCurso( ClArbolAcademico curso ) throws Exception {
        String sHqlUpdate;

        sHqlUpdate = "update ClArbolAcademico set arbCodigo = :codigo, "
                + "arbDescripcion = :descripcion, "
                + "arbActivo = :activo, arbVisibleArbol=:visible "
                + "where arbId = :id";
        this.getSession().createQuery( sHqlUpdate ).
                setString( "codigo", curso.getArbCodigo() ).
                setString( "descripcion", curso.getArbDescripcion() ).
                setString( "activo", "1" ).
                setInteger( "visible", curso.getArbVisibleArbol() ).
                setInteger( "id", curso.getArbId() ).
                executeUpdate();
    }

    /*
     * @Override public List verificarEliminarCurso(int cur_id) throws Exception
     * { return
     * this.getSession().createCriteria(ClTaller.class).add(Restrictions.eq("talActivo",
     * "1")).add(Restrictions.eq("clCurso.curId", cur_id)).list(); }
     */
    @Override
    public List<ClArbolAcademico> verificarEliminarCurso( int cur_id ) throws Exception {
        Criteria criteria = this.getSession().createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) );
        if ( cur_id > 0 ) {
            criteria.add( Restrictions.eq( "arbIdPadre", cur_id ) );
        }
        return criteria.list();
    }

    @Override
    public void eliminarModulo( int iModId ) throws Exception {
        String sHqlUpdate;

        sHqlUpdate = "update ClArbolAcademico set arbActivo = :activo where arbId = :id";
        this.getSession().
                createQuery( sHqlUpdate ).
                setString( "activo", "0" ).
                setInteger( "id", iModId ).
                executeUpdate();
    }

    @Override
    public void insertarModulo( ClArbolAcademico arbMod ) throws Exception {
        this.getHibernateTemplate().save( arbMod );
    }

    @Override
    public List<ClArbolAcademico> obtenerUltimoModulo( int iAreaPadre ) {
        List<ClArbolAcademico> lstUltimoModulo;

        lstUltimoModulo = this.getSession().
                createCriteria( ClArbolAcademico.class, "arbOpcion" ).
                add( Restrictions.eq( "arbNivel", 2 ) ).
                add( Restrictions.eq( "arbIdPadre", iAreaPadre ) ).
                addOrder( Order.desc( "arbId" ) ).
                list();


        return lstUltimoModulo;

    }

    @Override
    public void actualizarModulo( ClArbolAcademico arbMod ) throws Exception {
        String sHqlUpdate;

        sHqlUpdate = "update ClArbolAcademico set arbCodigo = :codigo, "
                + "arbDescripcion = :descripcion, "
                + "arbActivo = :activo , arbVisibleArbol=:visible "
                + "where arbId = :id";

        this.getSession().createQuery( sHqlUpdate ).
                setString( "codigo", arbMod.getArbCodigo() ).
                setString( "descripcion", arbMod.getArbDescripcion() ).
                setString( "activo", "1" ).
                setInteger( "visible", arbMod.getArbVisibleArbol() ).
                setInteger( "id", arbMod.getArbId() ).
                executeUpdate();
    }

    @Override
    public void insertarCurso( ClArbolAcademico arbMod ) throws Exception {
        this.getHibernateTemplate().save( arbMod );
    }

    @Override
    public List<ClArbolAcademico> obtenerUltimoCurso( int iModPadre ) {
        List<ClArbolAcademico> lstUltimoCurso;

        lstUltimoCurso = this.getSession().
                createCriteria( ClArbolAcademico.class, "arbOpcion" ).
                add( Restrictions.eq( "arbNivel", 3 ) ).
                add( Restrictions.eq( "arbIdPadre", iModPadre ) ).
                addOrder( Order.desc( "arbId" ) ).
                list();

        return lstUltimoCurso;
    }

    @Override
    public List<ClArbolAcademico> seleccionarTalleres( int cur_id ) throws Exception {
        Criteria criteria = this.getSession().createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) );
        if ( cur_id > 0 ) {
            criteria.add( Restrictions.eq( "arbIdPadre", cur_id ) ).add( Restrictions.eq( "arbNivel", 4 ) );
        }
        return criteria.list();

    }

    @Override
    public ClArbolAcademico buscarCurso( int iCurId ) throws Exception {
        return (ClArbolAcademico) this.getSession().createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbId", iCurId ) ).
                add( Restrictions.eq( "arbNivel", 3 ) ).
                uniqueResult();
    }

    @Override
    public void actualizarTaller( ClArbolAcademico arbTaller ) throws Exception {
        String sHqlUpdate;

        sHqlUpdate = "update ClArbolAcademico set arbCodigo = :codigo, "
                + "arbDescripcion = :descripcion, "
                + "arbActivo = :activo, "
                + "talNumero = :numero "
                + "where arbId = :id";
        this.getSession().createQuery( sHqlUpdate ).
                setString( "codigo", arbTaller.getArbCodigo() ).
                setString( "descripcion", arbTaller.getArbDescripcion() ).
                setString( "activo", "1" ).
                setString( "numero", arbTaller.getTalNumero() ).
                setInteger( "id", arbTaller.getArbId() ).
                executeUpdate();
    }

    @Override
    public void insertarTaller( ClArbolAcademico arbTaller ) throws Exception {
        this.getHibernateTemplate().save( arbTaller );
    }

    @Override
    public List<ClArbolAcademico> obtenerUltimoTaller( int iCurPadre ) throws Exception {
        List<ClArbolAcademico> lstUltimoTaller;

        lstUltimoTaller = this.getSession().
                createCriteria( ClArbolAcademico.class, "arbOpcion" ).
                add( Restrictions.eq( "arbNivel", 4 ) ).
                add( Restrictions.eq( "arbIdPadre", iCurPadre ) ).
                addOrder( Order.desc( "arbId" ) ).
                list();

        return lstUltimoTaller;
    }

    @Override
    public List obtenerDescripcionCurso( int iArbId ) throws Exception {
        Criteria criteria;

        criteria = this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbActivo", "1" ) );

        if ( iArbId > 0 ) {
            criteria.add( Restrictions.eq( "arbId", iArbId ) ).
                    add( Restrictions.eq( "arbNivel", 3 ) );
        }
        return criteria.list();
    }

    @Override
    public void eliminarTaller( int iTallerId ) throws Exception {
        String hqlUpdate = "update ClArbolAcademico set arbActivo = :activo where arbId = :id";
        this.getSession().createQuery( hqlUpdate ).setString( "activo", "0" ).setInteger( "id", iTallerId ).executeUpdate();
    }

    @Override
    public List verificarEliminarTaller( int iTallerId ) throws Exception {
        return this.getSession().
                createCriteria( ClArbolAperturado.class ).
                add( Restrictions.eq( "arbapeActivo", "1" ) ).
                add( Restrictions.eq( "arbapeAperturado", "1" ) ).
                add( Restrictions.eq( "clArbolAcademico.arbId", iTallerId ) ).list();
    }

    @Override
    public ClArbolAcademico buscarTaller( int iTallerId ) throws Exception {
        return (ClArbolAcademico) this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbId", iTallerId ) ).
                add( Restrictions.eq( "arbNivel", 4 ) ).
                uniqueResult();
    }

    @Override
    public ClArbolAcademico buscarArbolPorId( int iArbId ) throws Exception {
        return (ClArbolAcademico) this.getSession().
                createCriteria( ClArbolAcademico.class ).
                add( Restrictions.eq( "arbId", iArbId ) ).
                uniqueResult();
    }

    @Override
    public List<String> buscarXSeccion( int iSecId ) throws Exception {
        String sHqlQuery;
        sHqlQuery = "SELECT clArbolAcademico.arbDescripcion FROM ClArbolAcademico as clArbolAcademico WHERE "
                + " clArbolAcademico.arbId =(SELECT clArbolAcademico.arbIdPadre FROM ClSeccion as clSeccion "
                + " INNER JOIN clSeccion.clArbolAcademico as clArbolAcademico"
                + " WHERE clSeccion.secId= :sec_id)";

        return this.getSession().
                createQuery( sHqlQuery ).
                setInteger( "sec_id", iSecId ).
                list();

    }

    @Override
    public void insertarListaArbol( List<ClArbolAcademico> lstArbol ) throws Exception {
        for ( ClArbolAcademico arbol : lstArbol ) {
            arbol.setArbVisible( 1 );
            this.getHibernateTemplate().save( arbol );
        }

    }

    private void cargarListaArbol( List<ClArbolAcademico> lstArbol, List lstArbolObjs ) {
        int iSizeArb;
        Object[] obj;
        iSizeArb = lstArbolObjs.size();
        ClArbolAcademico clArbol;
        for ( int i = 0; i < iSizeArb; i++ ) {
            obj = (Object[]) lstArbolObjs.get( i );
            clArbol = new ClArbolAcademico();
            clArbol.setArbId( (Integer) obj[ConstantesWeb.INDX_ARB_ID] );
            clArbol.setArbOpcion( String.valueOf( obj[ConstantesWeb.INDX_ARB_OPCION] ) );
            clArbol.setArbCodigo( String.valueOf( obj[ConstantesWeb.INDX_ARB_CODIGO] ) );
            clArbol.setArbDescripcion( String.valueOf( obj[ConstantesWeb.INDX_ARB_DESCRIPCION] ) );
            clArbol.setArbTipo( String.valueOf( obj[ConstantesWeb.INDX_ARB_TIPO] ) );
            clArbol.setArbNivel( (Integer) obj[ConstantesWeb.INDX_ARB_NIVEL] );
            clArbol.setArbActivo( String.valueOf( obj[ConstantesWeb.INDX_ARB_ACTIVO] ) );
            clArbol.setArbIdPadre( (Integer) obj[ConstantesWeb.INDX_ARB_ID_PADRE] );
            clArbol.setArbFlag( String.valueOf( obj[ConstantesWeb.INDX_ARB_FLAG] ) );
            clArbol.setArbIdbak( (Integer) obj[ConstantesWeb.INDX_ARB_IDBAK] );
            lstArbol.add( clArbol );
        }
    }
}