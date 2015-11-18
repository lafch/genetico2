/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.*;
import net.uch.mapping.*;
import net.uch.util.CommonDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSNotasCLDAOImpl extends HibernateDaoSupport implements HSNotasCLDAO {

    @Override
    public List<ClNota> seleccionarNotas( int sec_id ) {
        Criteria c = this.getSession().createCriteria( ClNota.class ).
                add( Restrictions.eq( "notActivo", "1" ) ).
                add( Restrictions.eq( "secId", sec_id ) );
        return c.list();
    }

    @Override
    public void insertarActualizarNotas( List<ClNota> lista ) {
        this.getHibernateTemplate().saveOrUpdateAll( lista );
    }

    @Override
    public List seleccionarNotaAlumnoSeccion( int alu_id, int sec_id, int per_id ) {
        List lista;
        String hqlQuery = "select clNota.notId, clNota.notNota  "
                + "from ClNota as clNota "
                + "inner join clNota.clAlumno as clAlumno "
                + "inner join clNota.clSisEvaPersonalizado as clSisEvaPersonalizado "
                + "inner join clNota.clSeccion as clSeccion "
                + "where clSeccion.secId= :v_sec_id "
                + "and clSisEvaPersonalizado.sisevaPerId= :v_per_id "
                + "and clAlumno.aluId= :v_alu_id "
                //                + "and clNota.notTipo='034002' "
                + "and clNota.notActivo='1'";
        lista = this.getSession().createQuery( hqlQuery ).setInteger( "v_sec_id", sec_id ).setInteger( "v_per_id", per_id ).setInteger( "v_alu_id", alu_id ).list();

        return lista;
    }

    @Override
    public Double obtenerPormedio( Integer iSecId, int iAluId ) {
        Double dPromedio;
        String sSqlQuery;
        List lstNotas;
        sSqlQuery = "SELECT not_nota FROM cl_nota WHERE alu_id=:p_alu_id AND sec_id =:p_sec_id AND not_tipo=:p_tipo_prom_final AND not_activo=1";
        try {
            lstNotas = this.getSession().
                    createSQLQuery( sSqlQuery ).
                    setInteger( "p_alu_id", iAluId ).
                    setInteger( "p_sec_id", iSecId ).
                    setString( "p_tipo_prom_final", "034002" ).
                    list();
            if ( !lstNotas.isEmpty() ) {
                dPromedio = Double.parseDouble( lstNotas.get( 0 ).toString() );
            } else {
                dPromedio = null;
            }
        } catch ( Exception ex ) {
            dPromedio = null;
        }

        return dPromedio;
    }

    @Override
    public void actualizarNota( int sec_id, int alu_id, int sis_per_id, double not_nota ) {
        ClAlumno clAlu;
        ClNota clNota;
        ClSeccion clSeccion;
        ClSisEvaPersonalizado sisEvaPer;
        List listaN = seleccionarNotaAlumnoSeccion( alu_id, sec_id, sis_per_id );
        if ( listaN.size() > 0 ) {

            String hqlQuery = "update ClNota set notNota= :v_nota where clAlumno.aluId = :v_alu_id and clSisEvaPersonalizado.sisevaPerId = :v_per_id "
                    + "and clSeccion.secId=:v_sec_id and notActivo='1'";
            this.getSession().createQuery( hqlQuery ).setInteger( "v_alu_id", alu_id ).setInteger( "v_per_id", sis_per_id ).
                    setInteger( "v_sec_id", sec_id ).setDouble( "v_nota", not_nota ).executeUpdate();
        } else {
            sisEvaPer = new ClSisEvaPersonalizado();
            sisEvaPer.setSisevaPerId( sis_per_id );
            clAlu = new ClAlumno( alu_id );
            clNota = new ClNota();
            clSeccion = new ClSeccion( sec_id );
            clNota.setClAlumno( clAlu );
            clNota.setClSisEvaPersonalizado( sisEvaPer );
            clNota.setNotActivo( "1" );
            clNota.setNotCreacion( new Date() );
            clNota.setNotNota( Float.parseFloat( String.valueOf( not_nota ) ) );
            clNota.setNotObservacion( "-" );
            clNota.setClSeccion( clSeccion );

            this.getSession().save( clNota );
        }
    }

    @Override
    public void actualizarPromedio( int iSecId, int iAluId, double dNota ) {
        String hqlQuery;
        ClAlumno clAlumno;
        ClNota clNota;
        ClSeccion clSeccion;
        ClSisEvaPersonalizado sisEvaPer;
        if ( obtenerPormedio( iSecId, iAluId ) == null ) {
            sisEvaPer = CommonDAO.getClSistemaEvaluacionDAO().seleccionarSisEvaPerXSecId( iSecId );
            if ( sisEvaPer != null ) {
                clNota = new ClNota();
                clAlumno = new ClAlumno( iAluId );
                clSeccion = new ClSeccion( iSecId );
                clNota.setClAlumno( clAlumno );
                clNota.setClSisEvaPersonalizado( sisEvaPer );
                clNota.setNotActivo( "1" );
                clNota.setNotCreacion( new Date() );
                clNota.setNotNota( Float.parseFloat( String.valueOf( dNota ) ) );
                clNota.setNotObservacion( "-" );
                clNota.setClSeccion( clSeccion );
                clNota.setNotTipo( "034002" );
                this.getSession().save( clNota );
            }
        } else {

            hqlQuery = "update ClNota set notNota= :v_nota where clAlumno.aluId = :v_alu_id and notTipo = :v_nota_tipo "
                    + "and clSeccion.secId=:v_sec_id and notActivo='1'";
            this.getSession().createQuery( hqlQuery ).
                    setInteger( "v_alu_id", iAluId ).
                    setString( "v_nota_tipo", "034002" ).
                    setInteger( "v_sec_id", iSecId ).
                    setDouble( "v_nota", dNota ).
                    executeUpdate();
        }
    }

    @Override
    public int recalcularPromedio( int iSecId ) {
        int iSumPeso;
        double dPesoNota;
        double dNota;
        double dPromedioFinal;
        double dSumNotasPromFinal;
        double dSumPesosDet;
        double dSubPromedio;
        ClAlumno clAlumno;
        ClSisEvaPersonalizado sisEvaPer;
        ClSisEvaDetalle sed;
        Integer iKey;
        Iterator<Integer> it;
        Object[] aobjNota;
        Object[] aoSisEvaDetPers;
        List lstAlumnoXSecc;
        List lstClNota;
        List lstTipoPromedios;
        Map<Integer, List<Integer>> hmSisEvaDetPers;

        hmSisEvaDetPers = new HashMap<Integer, List<Integer>>();
        lstTipoPromedios = traerTiposPromedios( iSecId );
        for ( Object obj : lstTipoPromedios ) {
            aoSisEvaDetPers = (Object[]) obj;
            iKey = (Integer) aoSisEvaDetPers[0];

            if ( !hmSisEvaDetPers.containsKey( iKey ) ) {
                hmSisEvaDetPers.put( iKey, new ArrayList<Integer>() );
                hmSisEvaDetPers.get( iKey ).add( (Integer) aoSisEvaDetPers[1] );
            } else {
                hmSisEvaDetPers.get( iKey ).add( (Integer) aoSisEvaDetPers[1] );
            }
        }

        try {
            lstAlumnoXSecc = CommonDAO.getClAlumnoDAO().listarAlumnos_x_Seccion( iSecId );
        } catch ( Exception ex ) {
            ex.printStackTrace();
            lstAlumnoXSecc = new ArrayList();
        }
        for ( Object objClAlum : lstAlumnoXSecc ) {
            clAlumno = (ClAlumno) objClAlum;
            dSumNotasPromFinal = 0;
            dSumPesosDet = 0;
            it = hmSisEvaDetPers.keySet().iterator();
            while ( it.hasNext() ) {
//            for ( List<Integer> lstSisPerIds : hmSisEvaDetPers.values() ) {
                iKey = it.next();
                sed = CommonDAO.getClSistemaEvaluacionDAO().seleccionarSisEvaDetetallePorId( iKey );
                List<Integer> lstSisPerIds = hmSisEvaDetPers.get( iKey );
                iSumPeso = 0;
                dSubPromedio = 0;
                for ( Integer iSisEvaPerId : lstSisPerIds ) {
                    
                    sisEvaPer = CommonDAO.getClSistemaEvaluacionDAO().seleccionarSisEvaPersonalizadoPorId( iSisEvaPerId );
                    lstClNota = CommonDAO.getClNotasDAO().seleccionarNotaAlumnoSeccion( clAlumno.getAluId(), iSecId, iSisEvaPerId );
                    if ( !lstClNota.isEmpty() ) {
                        aobjNota = (Object[]) lstClNota.get( 0 );
                        if ( aobjNota.length > 0 ) {
                            dNota = Double.parseDouble( aobjNota[1].toString() );
                            dPesoNota = sisEvaPer.getSisevaPerPeso();
                            dSubPromedio += dPesoNota * dNota;
                            System.out.println( "subPromedio "+dSubPromedio + " ="+dPesoNota+" * "+dNota );
                            iSumPeso += dPesoNota;
                            System.out.println( " iSumPeso" + iSumPeso );
                        }
                    }
                    
//                    if((int)iSisEvaPerId.intValue() !=1192){
//                        iSumPeso=1;
//                    }
                }
                System.out.println( "sed.getSisevaDetallePeso()"+sed.getSisevaDetallePeso());
                dSumPesosDet+=sed.getSisevaDetallePeso();
                System.out.println( "dSumPesosDet"+dSumPesosDet+"=" );
                dSumNotasPromFinal += (dSubPromedio / (double) iSumPeso)*sed.getSisevaDetallePeso();
                System.out.println( "dSumNotasPromFinal (" + dSubPromedio + "/"+ iSumPeso +")"+"*"+sed.getSisevaDetallePeso());
            }
            
            int iIdArea = CommonDAO.getClSeccionDAO().traerAreaxSeccionId( iSecId );
            if ( iIdArea == 5 || iIdArea == 1146 ) { // CASO INGLES
                dPromedioFinal = hmSisEvaDetPers.size() > 0 ? dSumNotasPromFinal : 0;
                System.out.println( "dpromediofinal" + dPromedioFinal );
                CommonDAO.getClNotasDAO().actualizarPromedio( iSecId, clAlumno.getAluId(), Math.round( dPromedioFinal ) );
            } else {         
                dPromedioFinal = dSumPesosDet > 0 ? dSumNotasPromFinal / dSumPesosDet : 0;
//                dPromedioFinal = hmSisEvaDetPers.size() > 0 ? dSumNotasPromFinal / hmSisEvaDetPers.size() : 0;
                CommonDAO.getClNotasDAO().actualizarPromedio( iSecId, clAlumno.getAluId(), Math.round( dPromedioFinal ) );
            }
            
        }

        return -1;
    }

    public List traerTiposPromedios( int iSecId ) {
        String sSql;
        List lstTiposPromedios;
        sSql = "SELECT sed.siseva_detalle_id, sep.siseva_per_id FROM cl_seccion s "
                + "INNER JOIN cl_arbol_aperturado aape ON(s.arbape_id=aape.arbape_id) "
                + "INNER JOIN cl_sis_evaluacion se ON(aape.siseva_id=se.siseva_id) "
                + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                + "INNER JOIN cl_sis_eva_personalizado sep ON(sed.siseva_detalle_id=sep.siseva_detalle_id AND aape.arbape_id=sep.arbape_id) "
                + "WHERE s.sec_activo=1 AND sed.siseva_detalle_tipo=1 "
                + "AND s.sec_id=:p_sec_id AND se.siseva_activo=1 AND sed.siseva_detalle_activo=1 AND sep.siseva_per_activo=1";
        lstTiposPromedios = this.getSession().
                createSQLQuery( sSql ).
                setInteger( "p_sec_id", iSecId ).
                list();
        return lstTiposPromedios;
    }

    @Override
    public List seleccionarNotasPorAlumnoModulo( int iAluId, List<Integer> lArbCurIds ) {
        List lstClNotas;
        String hqlQuery = "select n.not_nota, a.alu_id, aacTall.arb_id_padre, aacTall.arb_id "
                + "FROM cl_nota n "
                + "INNER JOIN cl_seccion s ON(n.sec_id=s.sec_id) "
                + "INNER JOIN cl_arbol_academico aacTall ON(s.arb_id=aacTall.arb_id) "
                + "INNER JOIN cl_alumno a ON(n.alu_id=a.alu_id) "
                + "INNER JOIN cl_matricula m ON(a.alu_id=m.alu_id) "
                + "INNER JOIN cl_matricula_taller mt ON(m.mat_id=mt.mat_id AND s.sec_id=mt.sec_id) "
                + "WHERE n.not_tipo='034002' "
                + "AND n.not_activo=1 "
                + "AND s.sec_activo=1 "
                + "AND a.alu_id=:v_alu_id "
                + "AND aacTall.arb_id_padre IN(:v_lista) "
                + "AND m.mat_tipo='022001'"
                + "AND m.mat_activo='1' "
                + "AND mt.mattal_activo='1' "
                + "ORDER BY aacTall.arb_descripcion";

        lstClNotas = this.getSession().createSQLQuery( hqlQuery ).setInteger( "v_alu_id", iAluId ).setParameterList( "v_lista", lArbCurIds ).list();
//        lista = this.getSession().createQuery( hqlQuery ).setInteger( "v_alu_id", alu_id ).setParameterList( "v_lista", listaArbId ).list();
        return lstClNotas;
    }

    @Override
    public List seleccionarNotasPorAlumnoModuloProgAux( int iAluId, List<Integer> lstArbCurIds ) {
        List lstClNotas;
        String hqlQuery = "SELECT ROUND(AVG(t.prom_asig)) prom_curso,t.alu_id,t.cur_id,t.tal_id,t.sec_id FROM( "
                + "SELECT s.sec_id,sed.siseva_detalle_nombre,(sum(n.not_nota*sep.siseva_per_peso)/sum(sep.siseva_per_peso))*sed.siseva_detalle_peso prom_asig, "
                + "n.alu_id, aCur.arb_id cur_id, aTal.arb_id tal_id "
                + "FROM cl_seccion s "
                + "INNER JOIN cl_arbol_aperturado aape ON(s.arbape_id=aape.arbape_id) "
                + "INNER JOIN cl_sis_evaluacion se ON(aape.siseva_id=se.siseva_id) "
                + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                + "INNER JOIN cl_sis_eva_personalizado sep ON(sed.siseva_detalle_id=sep.siseva_detalle_id) "
                + "INNER JOIN cl_nota n ON(sep.siseva_per_id=n.siseva_per_id AND s.sec_id=n.sec_id) "
                + "INNER JOIN cl_arbol_academico aTal ON(s.arb_id=aTal.arb_id) "
                + "INNER JOIN cl_arbol_academico aCur ON(aTal.arb_id_padre=aCur.arb_id) "
                + "WHERE aCur.arb_id IN(:v_lista) AND n.alu_id=:v_alu_id AND s.sec_activo=1 AND aape.arbape_activo=1  "
                + "AND se.siseva_activo=1 AND sed.siseva_detalle_activo=1 AND sep.siseva_per_activo=1 "
                + "GROUP BY sed.siseva_detalle_id ) t "
                + "GROUP BY t.sec_id";

        lstClNotas = this.getSession().createSQLQuery( hqlQuery ).setInteger( "v_alu_id", iAluId ).setParameterList( "v_lista", lstArbCurIds ).list();
//        lista = this.getSession().createQuery( hqlQuery ).setInteger( "v_alu_id", alu_id ).setParameterList( "v_lista", listaArbId ).list();
        return lstClNotas;
    }

    @Override
    public ClNota notaxAlumno( int aluId, int sec_id ) {

        ClNota clnota;

        clnota = (ClNota) this.getSession().createCriteria( ClNota.class ).
                add( Restrictions.eq( "clAlumno.aluId", aluId ) ).
                add( Restrictions.eq( "clSeccion.secId", sec_id ) ).
                add(Restrictions.eq( "notActivo", "1") ).
                add( Restrictions.eq( "notTipo", "034002" ) ).uniqueResult();

        return clnota;
    }

    @Override
    public List listarPromSisEvaDetPorSecIdYAluId( int iSecId, int iAluId, List<Integer> lstSisEvaDetIds ) {
        String sQuery;
        String sSisEvaDetIds;
        List lstPromedios = new ArrayList();

        sSisEvaDetIds = "(";
        for ( int i = 0; i < lstSisEvaDetIds.size(); i++ ) {
            sSisEvaDetIds += lstSisEvaDetIds.get( i );
            if ( i < lstSisEvaDetIds.size() - 1 ) {
                sSisEvaDetIds += ",";
            }
        }

        sSisEvaDetIds += ")";

        sQuery = "SELECT sep.siseva_detalle_id,round(cast(AVG(n.not_nota) as decimal)) FROM cl_seccion s "
                + "INNER JOIN cl_arbol_aperturado aape ON(s.arbape_id=aape.arbape_id) "
                + "INNER JOIN cl_sis_evaluacion se ON(aape.siseva_id=se.siseva_id) "
                + "INNER JOIN cl_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) "
                + "INNER JOIN cl_sis_eva_personalizado sep ON(sed.siseva_detalle_id=sep.siseva_detalle_id) "
                + "INNER JOIN cl_nota n ON(sep.siseva_per_id=n.siseva_per_id AND s.sec_id=n.sec_id) "
                + "WHERE s.sec_activo=1 AND aape.arbape_activo=1 AND se.siseva_activo=1 "
                + "AND sed.siseva_detalle_activo=1 AND sep.siseva_per_activo=1 AND n.not_activo=1 "
                + "AND s.sec_id=:p_sec_id AND n.alu_id=:p_alu_id AND sed.siseva_detalle_id IN " + sSisEvaDetIds
                + " GROUP BY sep.siseva_detalle_id";
        try {
            lstPromedios = this.getSession().
                    createSQLQuery( sQuery ).
                    setInteger( "p_sec_id", iSecId ).
                    setInteger( "p_alu_id", iAluId ).
                    list();
        } catch ( Exception ex ) {
            System.out.println( "EX MSJ " + ex.getMessage() );
            System.out.println( "EX " + ex );
            ex.printStackTrace();
        }

        return lstPromedios;
    }
}
