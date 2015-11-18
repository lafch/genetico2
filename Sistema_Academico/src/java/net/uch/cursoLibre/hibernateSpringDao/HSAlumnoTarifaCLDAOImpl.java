/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.logicreport.AlumnoHistorial;
import net.uch.mapping.*;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import net.uch.util.ObjUpdate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSAlumnoTarifaCLDAOImpl extends HibernateDaoSupport implements HSAlumnoTarifaCLDAO {

    @Override
    public void generarAlumnoTarifa( ClAlumnoTarifa alumnoTarifa ) throws Exception {
        this.getHibernateTemplate().save( alumnoTarifa );
    }

    @Override
    public void generarAlumnosTarifa( List<ClAlumnoTarifa> alumnosTarifa ) {
        this.getHibernateTemplate().saveOrUpdateAll( alumnosTarifa );
    }

    @Override
    public void actualizarDatosAlumnoTarifa( int iAlutarId, float fMonto, Date fechaPago, Date fechaProrroga ) {
        String sHqlUpdate = "update ClAlumnoTarifa set alutarMonto = :v_monto,"
                + " alutarFechaPago = :v_pago,"
                + " alutarFechaProrroga = :v_prorroga"
                + " where alutarId = :v_id";
        this.getSession().createQuery( sHqlUpdate ).
                setFloat( "v_monto", fMonto ).
                setDate( "v_pago", fechaPago ).
                setDate( "v_prorroga", fechaProrroga ).
                setInteger( "v_id", iAlutarId ).executeUpdate();
    }

    @Override
    public void eliminarAlumnoTarifa( int iAlutarId ) throws Exception {
        String sHqlUpdate = "update ClAlumnoTarifa "
                + "set alutarActivo = :activo "
                + "where alutarId = :id";

        this.getSession().createQuery( sHqlUpdate ).
                setString( "activo", "0" ).
                setInteger( "id", iAlutarId ).executeUpdate();
    }

    @Override
    public void eliminarAlumnosTarifa( List<Integer> lstAlutarIds ) {
        String sHqlUpdate = "update ClAlumnoTarifa "
                + "set alutarActivo = :v_activo "
                + "where alutarId in (:v_id)";

        this.getSession().createQuery( sHqlUpdate ).
                setString( "v_activo", "0" ).
                setParameterList( "v_id", lstAlutarIds ).executeUpdate();
    }

    @Override
    public List<ClAlumnoTarifa> seleccionarTarifasXMatricula( int iMatId, int iAluId ) throws Exception {
        return this.getSession().createCriteria( ClAlumnoTarifa.class ).
                /*add( Restrictions.eq( "alutarActivo", "1" ) ).*/
                add( Restrictions.in( "alutarActivo", new String[] { "1", "0" } ) ).
                add( Restrictions.eq( "clAlumno.aluId", iAluId ) ).
                add( Restrictions.eq( "matId", iMatId ) ).list();
        //.addOrder(Order.asc(""))
    }
    @Override
    public List<ClAlumnoTarifa> seleccionarTarifasXMatriculaAlumno( int iMatId, int iAluId ) throws Exception {
        return this.getSession().createCriteria( ClAlumnoTarifa.class ).
                add( Restrictions.eq( "alutarActivo", "1" ) ).
                add( Restrictions.eq( "clAlumno.aluId", iAluId ) ).
                add( Restrictions.eq( "matId", iMatId ) ).list();
        //.addOrder(Order.asc(""))
    }
    @Override
    public int actualizarEstructurasPagoBloque( int iSecId, List<ObjUpdate> lstRestricciones, List<ObjUpdate> lstValores ) {
        String sQuery;
        ObjUpdate objUpd;
        Query query;
        sQuery = "update cl_alumno_tarifa "
                + "inner join cl_alumno on cl_alumno.alu_id = cl_alumno_tarifa.alu_id "
                + "inner join cl_estructura_pagos_detalle on cl_estructura_pagos_detalle.estpagdet_id = cl_alumno_tarifa.estpagdet_id "
                + "inner join cl_estructura_pagos on cl_estructura_pagos.estpag_id = cl_estructura_pagos_detalle.estpag_id "
                + "set ";
        for ( int i = 0; i < lstValores.size(); i++ ) {
            objUpd = lstValores.get( i );
            if ( i == lstValores.size() - 1 ) {
                sQuery = sQuery.concat( objUpd.getKey() + " = " + objUpd.getValue() + " " );
            } else {
                sQuery = sQuery.concat( objUpd.getKey() + " = " + objUpd.getValue() + ", " );
            }
        }

        sQuery = sQuery + "where cl_alumno_tarifa.alutar_activo = '1'"
                + " and cl_alumno.alu_activo = '1'"
                + " and cl_estructura_pagos_detalle.estpagdet_activo = '1'"
                + " and cl_estructura_pagos.estpag_activo='1'"
                + " and cl_alumno_tarifa.alutar_estado <> '2'"
                + " and cl_alumno_tarifa.sec_id = :secId";
        for ( int i = 0; i < lstRestricciones.size(); i++ ) {
            objUpd = lstRestricciones.get( i );
            sQuery = sQuery.concat( " and " + objUpd.getKey() + " = " + objUpd.getValue() );
        }

        query = this.getSession().createSQLQuery( sQuery ).setInteger( "secId", iSecId );
        return query.executeUpdate();
    }

    @Override
    public boolean existenPagosMatricula( int iAluId, int iAlutarId ) throws Exception {
        List lstPagosMat;
        lstPagosMat = this.getSession().createCriteria( AdPago.class, "AdPago" ).
                createCriteria( "AdPago.Compag", "AdComprobantePago" ).
                add( Restrictions.eq( "AdPago.PagActivo", "1" ) ).
                add( Restrictions.eq( "AdComprobantePago.CompagActivo", "1" ) ).
                add( Restrictions.eq( "AdComprobantePago.CompagClienteTipo", "014003" ) ).
                add( Restrictions.eq( "AdComprobantePago.CompagCliente", iAluId ) ).
                add( Restrictions.eq( "AdPago.AlutarId", iAlutarId ) ).list();

        return !lstPagosMat.isEmpty();
    }

    @Override
    public void actualizarFechaProrroga( Date fechaPro, String sFechaObservacion, int iSecId ) {
        
        String hqlUpdate = "update ClAlumnoTarifa "
                + "set alutarFechaProrroga = :v_prorroga, alutarObservacionProrroga= :v_observacion "
                + "where alutarId = :id";

        this.getSession().createQuery( hqlUpdate ).
                setDate( "v_prorroga", fechaPro ).
                setString( "v_observacion", sFechaObservacion ).
                setInteger( "id", iSecId ).executeUpdate();
    }
    
    @Override
    public void actualizarAlumnoTarifa( Date sfechaPago, Date sfechaProrroga,Float sMonto, int iAlutarId,String sMatTipo ) {
        
        /*String hqlUpdate = "update ClAlumnoTarifa "
                + "set alutarFechaPago = :v_pago,alutarFechaProrroga = :v_prorroga, alutarMonto= :v_monto, alutarActivo=1 "
                + "where alutarId = :id";

        this.getSession().createQuery( hqlUpdate ).
                setDate( "v_pago", sfechaPago ).
                setDate( "v_prorroga", sfechaProrroga ).
                setFloat( "v_monto", sMonto ).
                setInteger( "id", iAlutarId ).executeUpdate();
        */
        String sQuery;
        Query query;
        sQuery = "UPDATE cl_alumno_tarifa as alutar "
                + " INNER JOIN cl_matricula as mat on  mat.mat_id=alutar.mat_id "
                + " SET alutar.alutar_fecha_pago=:v_pago, alutar.alutar_fecha_prorroga= :v_prorroga, alutar.alutar_monto= :v_monto, alutar_activo=1,mat.mat_tipo=:v_matTipo "
                + " where alutar.alutar_id=:id ";
        query = this.getSession().createSQLQuery( sQuery ).setDate( "v_pago", sfechaPago ).setDate( "v_prorroga", sfechaProrroga ).setFloat( "v_monto", sMonto ).setString("v_matTipo", sMatTipo).setInteger( "id", iAlutarId );
        query.executeUpdate();
    }

    @Override
    public List<ClAlumnoTarifa> alumnoMonto( int iAluId, int iMatId ) {
        String sHqlQuery;
        Query query;

        sHqlQuery = "FROM ClAlumnoTarifa alutar "
                + "WHERE alutar.clAlumno.aluId= :aluId "
                + "and alutar.matId= :matId";

        query = this.getSession().
                createQuery( sHqlQuery ).
                setInteger( "aluId", iAluId ).
                setInteger( "matId", iMatId );

        return query.list();
    }

    @Override
    public void aumentarMora( int iAlutarId, float fMora ) {
        String sQuery;
        Query query;
        sQuery = "UPDATE cl_alumno_tarifa as alutar INNER JOIN cl_alumno as alu "
                + "SET alutar.alutar_mora=alutar.alutar_mora+ :mora WHERE alutar.alutar_id= :alutarId ";


        query = this.getSession().createSQLQuery( sQuery ).setInteger( "alutarId", iAlutarId ).setFloat( "mora", fMora );

        query.executeUpdate();
    }

    @Override
    public void EditarMonto( int iAluId, int iMatId, float fMonto ) {
        String sQuery;
        Query query;
        sQuery = "UPDATE cl_alumno_tarifa as alutar INNER JOIN cl_alumno as alu "
                + "SET alutar.alutar_monto= :monto WHERE alu.alu_id= :aluId "
                + "and alutar.mat_id= :matId";

        query = this.getSession().createSQLQuery( sQuery ).setInteger( "aluId", iAluId ).setInteger( "matId", iMatId ).setFloat( "monto", fMonto );

        query.executeUpdate();
    }

    @Override
    public void cambiarSeccionAlumnoTarifa( int iMatId, int iSecId ) {
        String sHqlUpdate = "update ClAlumnoTarifa set secId = :sec_id"
                + " where matId = :matId";
        this.getSession().createQuery( sHqlUpdate ).
                setInteger( "matId", iMatId ).
                setInteger( "sec_id", iSecId ).executeUpdate();
    }

    @Override
    public void cerrarPago( int iMatId ) {
        int iSizeAluTar;
        ClAlumnoTarifa clAluTar;
        ClMatricula clMat;
        List<ClAlumnoTarifa> lstAluTarPorCerrar;

        lstAluTarPorCerrar = traerListaPagosPorCerrar( iMatId );

        iSizeAluTar = lstAluTarPorCerrar.size();
        for ( int i = 0; i < iSizeAluTar; i++ ) {
            clAluTar = lstAluTarPorCerrar.get( i );
            clAluTar.setAlutarEstado( "2" );
            this.getHibernateTemplate().update( clAluTar );
            System.out.println( "Actualiza Alumno tarifa" );
            clMat = CommonDAO.getClMatriculaDAO().buscarPorMatId( clAluTar.getMatId() );
            if ( clMat != null ) {
                clMat.setMatTipo( "022001" );
                this.getHibernateTemplate().merge( clMat );
                System.out.println( "Actualiza Matricula" );
            }
        }
    }
    
    @Override
    public void HabilitarMatricula( int iMatId ) {
        int iSizeAluTar;
        ClAlumnoTarifa clAluTar;
        ClMatricula clMat;
        List<ClAlumnoTarifa> lstAluTarPorCerrar;

        lstAluTarPorCerrar = traerListaPagosPorHabilitar( iMatId );

        iSizeAluTar = lstAluTarPorCerrar.size();
        for ( int i = 0; i < iSizeAluTar; i++ ) {
            clAluTar = lstAluTarPorCerrar.get( i );
            /*clAluTar.setAlutarEstado( "0" );
            this.getHibernateTemplate().update( clAluTar );*/
            System.out.println( "Actualiza Alumno tarifa" );
            clMat = CommonDAO.getClMatriculaDAO().buscarPorMatHabilitar( clAluTar.getMatId() );
            if ( clMat != null ) {
                clMat.setMatTipo( "022005" );
                clAluTar.setAlutarActivo("1");
                this.getHibernateTemplate().merge( clMat );
                this.getHibernateTemplate().merge(clAluTar);
                System.out.println("Matricula Habilitada");
            }
        }
    }

    @Override
    public List<ClAlumnoTarifa> traerListaPagosPorCerrar( int iMatId ) {
        int iSumaPagos;
        int iSizeAluTar;
        int iSizePagos;
        AdPago pago;
        ClAlumnoTarifa clAluTar;
        Criteria criteria;
        List<AdPago> lstPagos;
        List<ClAlumnoTarifa> lstAluTar;
        List<ClAlumnoTarifa> lstAluTarPorCerrar;

        lstAluTarPorCerrar = new ArrayList<ClAlumnoTarifa>();
        try {
            criteria = this.getSession().createCriteria( ClAlumnoTarifa.class );
            criteria.add( Restrictions.eq( "alutarActivo", "1" ) );
            criteria.add( Restrictions.eq( "matId", iMatId ) );
            criteria.add( Restrictions.ne( "alutarEstado", "2" ) );
            lstAluTar = criteria.list();
            iSizeAluTar = lstAluTar.size();
            if ( iSizeAluTar > 0 ) {
                for ( int i = 0; i < iSizeAluTar; i++ ) {
                    clAluTar = lstAluTar.get( i );
                    if ( "1".equals( clAluTar.getAlutarEstado() )  ) {
                        lstPagos = CommonDAO.getClAlumnoDAO().listaPagosAlumno( clAluTar.getAlutarId() );
                        iSizePagos = lstPagos.size();
                        iSumaPagos = 0;
                        for ( int j = 0; j < iSizePagos; j++ ) {
                            pago = lstPagos.get( j );
                            iSumaPagos += pago.getPagMonto();
                        }
                        if ( iSumaPagos <= clAluTar.getAlutarMonto() + clAluTar.getAlutarMora() ) {
                            lstAluTarPorCerrar.add( clAluTar );
                        }
                    } else {
                        if ( clAluTar.getAlutarMonto() + clAluTar.getAlutarMora() > 0 ) {
                            lstAluTarPorCerrar.add( clAluTar );
                        }
                    }
                }
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lstAluTarPorCerrar;
    }
    
    
    @Override
    public List<ClAlumnoTarifa> traerListaPagosPorHabilitar( int iMatId ) {
        int iSumaPagos;
        int iSizeAluTar;
        int iSizePagos;
        AdPago pago;
        ClAlumnoTarifa clAluTar;
        Criteria criteria;
        List<AdPago> lstPagos;
        List<ClAlumnoTarifa> lstAluTar;
        //List<ClAlumnoTarifa> lstAluTarPorCerrar;
        List<ClAlumnoTarifa> lstAluTarPorHabilitar;

        lstAluTarPorHabilitar = new ArrayList<ClAlumnoTarifa>();
        try {
            criteria = this.getSession().createCriteria( ClAlumnoTarifa.class );
            /*criteria.add( Restrictions.eq( "alutarActivo", "1" ) );*/
            criteria.add( Restrictions.in( "alutarActivo", new String[] { "0" } ) );
            criteria.add( Restrictions.eq( "matId", iMatId ) );
            criteria.add( Restrictions.ne( "alutarEstado", "2" ) );
            lstAluTar = criteria.list();
            iSizeAluTar = lstAluTar.size();
            if ( iSizeAluTar > 0 ) {
                for ( int i = 0; i < iSizeAluTar; i++ ) {
                    clAluTar = lstAluTar.get( i );
                    if ( "1".equals( clAluTar.getAlutarEstado() )  ) {
                        lstPagos = CommonDAO.getClAlumnoDAO().listaPagosAlumno( clAluTar.getAlutarId() );
                        iSizePagos = lstPagos.size();
                        iSumaPagos = 0;
                        for ( int j = 0; j < iSizePagos; j++ ) {
                            pago = lstPagos.get( j );
                            iSumaPagos += pago.getPagMonto();
                        }
                        if ( iSumaPagos <= clAluTar.getAlutarMonto() + clAluTar.getAlutarMora() ) {
                            lstAluTarPorHabilitar.add( clAluTar );
                        }
                    } else {
                        if ( clAluTar.getAlutarMonto() + clAluTar.getAlutarMora() > 0 ) {
                            lstAluTarPorHabilitar.add( clAluTar );
                        }
                    }
                }
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return lstAluTarPorHabilitar;
    }

    @Override
    public List<AlumnoHistorial> listarMontosPagadosPorSecId( int iSecId ) {
        int iSize;
        double dMonto;
        String sFInicio;
        String sFFin;
        String sMonto;
        String sQuery;
        AlumnoHistorial aluHist;
        ClArbolAcademico clArbAux;
        ClSeccion clSec;
        DateFormat dateFormat;
        SQLQuery sqlQuery;
        Object aobjFila[];
        List<AlumnoHistorial> lstAluHist;
        List lstAux;
        lstAluHist = new ArrayList<AlumnoHistorial>();

        sQuery = "SELECT "
                + "a.alu_codigo,"//0
                + "CONCAT(a.alu_appaterno,' ',a.alu_apmaterno,', ',a.alu_nombres) alumno,"//1
                + "sp_monto_total_por_pagar_cl(a.alu_id,s.sec_id) monto_total,"//2
                + "sp_monto_pagado_cl(a.alu_id,s.sec_id)monto_pagado,"//3
                + "a.alu_id "//4
                + "FROM cl_alumno a "
                + "INNER JOIN cl_matricula m ON(a.alu_id=m.alu_id) "
                + "INNER JOIN cl_matricula_taller mt ON(m.mat_id=mt.mat_id) "
                + "INNER JOIN cl_seccion s ON(mt.sec_id=s.sec_id) "
                + "WHERE a.alu_activo=1 AND m.mat_activo=1 AND mt.mattal_activo=1 "
                + "AND s.sec_activo=1 AND s.sec_id=:sec_id AND m.mat_tipo='022001'"
                + "ORDER BY 2";
        try {
            dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
            clSec = CommonDAO.getClSeccionDAO().seleccionarSeccion( iSecId );
            sqlQuery = this.getSession().createSQLQuery( sQuery );
            sqlQuery.setInteger( "sec_id", iSecId );
            lstAux = sqlQuery.list();
            iSize = lstAux.size();
            for ( int i = 0; i < iSize; i++ ) {
                aobjFila = (Object[]) lstAux.get( i );

                aluHist = new AlumnoHistorial();
                aluHist.setNumorden( i + 1 );
                aluHist.setAlucodigo( CommonWeb.parseObjToString( aobjFila[0] ) );
                aluHist.setAlumno( CommonWeb.parseObjToString( aobjFila[1] ) );
                //Monto total
                sMonto = CommonWeb.parseObjToString( aobjFila[2] );
                dMonto = "-".equals( sMonto ) ? 0 : Double.parseDouble( sMonto );
                aluHist.setMontopagar( dMonto );
                //Monto pagado
                sMonto = CommonWeb.parseObjToString( aobjFila[3] );
                dMonto = "-".equals( sMonto ) ? 0 : Double.parseDouble( sMonto );
                aluHist.setMontopagado( dMonto );
                //Monto saldo
                aluHist.setMontosaldo( aluHist.getMontopagar() - aluHist.getMontopagado() );
                aluHist.setObs(" ");

                aluHist.setIdAlumno( CommonWeb.parseObjToInt( aobjFila[4] ) );
                aluHist.setCantMatriculado( iSize );
                aluHist.setFinicio( clSec == null ? dateFormat.format( new Date() ) : dateFormat.format( clSec.getSecFinicio() ) );
                aluHist.setFfin( clSec == null ? dateFormat.format( new Date() ) : dateFormat.format( clSec.getSecFfin() ) );
                if ( i == 0 ) {
                    clArbAux = clSec.getClArbolAcademico();//taller
                    aluHist.setTaller( clArbAux.getArbDescripcion() );

                    clArbAux = clArbAux.getArbAcadPadre();//curso
                    aluHist.setCurso( clArbAux.getArbDescripcion() );

                    clArbAux = clArbAux.getArbAcadPadre();//modulo
                    aluHist.setModulo( clArbAux.getArbDescripcion() );
                }
                lstAluHist.add( aluHist );

            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return lstAluHist;
    }
    
    
    @Override
    public String usuarioMatricula( int iUsuId ) {
    String sQuery;
    String usuName;
    SQLQuery sqlQuery;
    sQuery = "SELECT "
                + "u.usu_usuario "//0
                + "FROM tb_usuario u "
                + "WHERE u.usu_activo=1 and u.usu_id=:iUsuId";
    sqlQuery = this.getSession().createSQLQuery( sQuery ); 
    sqlQuery.setInteger( "iUsuId", iUsuId );
    usuName = (String)sqlQuery.uniqueResult();
    return usuName;
    
    }
}
