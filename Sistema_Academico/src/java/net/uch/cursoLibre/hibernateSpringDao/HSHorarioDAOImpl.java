package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClHoraria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSHorarioDAOImpl extends HibernateDaoSupport implements HSHorarioDAO {

    @Override
    public List<ClHoraria> seleccionarHorario( int sec_id ) throws Exception {
        return this.getSession().createCriteria( ClHoraria.class ).
                add( Restrictions.eq( "horActivo", "1" ) ).
                add( Restrictions.eq( "clSeccion.secId", sec_id ) ).list();
    }

    @Override
    public List<ClHoraria> seleccionarHorarioAsignatura( int sec_id, int asig_id ) throws Exception {
        return this.getSession().createCriteria( ClHoraria.class ).
                add( Restrictions.eq( "horActivo", "1" ) ).
                add( Restrictions.eq( "clSisEvaDetDocente.sisevaDetalleId", asig_id ) ).
                add( Restrictions.eq( "clSeccion.secId", sec_id ) ).list();
    }

    @Override
    public void insertarHorario( ClHoraria horario ) throws Exception {
        this.getHibernateTemplate().save( horario );
    }

    @Override
    public void actualizarHorario( ClHoraria horario ) throws Exception {
        this.getHibernateTemplate().update( horario );
    }

    @Override
    public void insertarActualizarHorarios( List<ClHoraria> horarias ) {
        this.getHibernateTemplate().saveOrUpdateAll( horarias );
    }

    @Override
    public void eliminarHorario( int hor_id ) throws Exception {
        String hqlUpdate = "update ClHoraria set horActivo = :newName where horId = :oldName";
        this.getSession().createQuery( hqlUpdate ).setString( "newName", "0" ).
                setString( "oldName", "" + hor_id ).
                executeUpdate();
    }

    @Override
    public void eliminarHorarios( List<Integer> hor_ids ) {
        String hqlUpdate = "update ClHoraria "
                + "set horActivo = '0' "
                + "where horId in (:v_id)";

        this.getSession().createQuery( hqlUpdate ).
                setParameterList( "v_id", hor_ids ).executeUpdate();
    }

    @Override
    public void eliminarHorariosPorSecId( int iSecId ) {
        String hqlUpdate = "update ClHoraria "
                + "set horActivo = '0' "
                + "where clSeccion.secId = :v_id";

        this.getSession().createQuery( hqlUpdate ).
                setInteger( "v_id", iSecId ).executeUpdate();
    }

    @Override
    public List<ClAlumno> matriculadosSeccion( int sec_id ) {
        ClAlumnoTarifa a=new ClAlumnoTarifa();
        return this.getSession().createCriteria( ClAlumno.class, "ClAlumno" ).
               createCriteria("ClAlumno.clMatriculas","ClMatricula").
               createCriteria( "ClMatricula.clMatriculaTallers", "ClMatriculaTaller" ).
                add( Restrictions.eq( "ClAlumno.aluActivo", "1" ) ).
                add( Restrictions.eq( "ClMatriculaTaller.mattalActivo", "1" ) ).
                add( Restrictions.eq( "ClMatricula.matActivo", "1" ) ).
                add( Restrictions.eq( "ClMatricula.matTipo", "022001" ) ).
                add( Restrictions.eq( "ClMatriculaTaller.clSeccion.secId", sec_id ) ).
                addOrder( Order.asc( "ClAlumno.aluAppaterno" ) ).list();
        
        
                /*createCriteria( "ClAlumno.clMatriculas", "ClMatricula" ).
                createCriteria( "ClMatricula.clMatriculaTallers", "ClMatriculaTaller" ).
                //one y el otro mas y one
                //esto hay en
                createCriteria( "ClAlumno.clAlumnoTarifas", "ClAlumnoTarifa" ).
                //createCriteria( "ClEstructuraPagosDetalle.adConceptoPago", "AdConceptoPago" ).
                //createCriteria("AdConceptoPago.AdAlumnoTarifas","ClAlumnoTarifa").
                createCriteria( "ClAlumnoTarifa.adPagos", "AdPago" ).
                //one y el otro mas y one
                // createCriteria("AdPago.Conpag", "AdConceptoPago").
                createCriteria( "AdPago.Compag", "AdComprobantePago" ).
                add( Restrictions.eq( "ClAlumno.aluActivo", "1" ) ).
                add( Restrictions.eq( "ClMatriculaTaller.mattalActivo", "1" ) ).
                add( Restrictions.eq( "ClMatricula.matActivo", "1" ) ).
                add( Restrictions.eq( "ClAlumnoTarifa.alutarActivo", "1" ) ).
                //add( Restrictions.eq( "ClEstructuraPagosDetalle.estpagdetActivo", "1" ) ).
                //add( Restrictions.eq( "AdConceptoPago.ConpagActivo", "1" ) ).
                add( Restrictions.eq( "AdPago.PagActivo", "1" ) ).
                add(Restrictions.eq( "ClMatricula.matId",a.getAlutarId())).
                add( Restrictions.eq( "AdComprobantePago.CompagActivo", "1" ) ).
                add( Restrictions.eq( "ClMatricula.matTipo", "022001" ) ).
                add( Restrictions.eq( "AdComprobantePago.CompagTipo", "037001" ) ).
                add( Restrictions.eq( "ClMatriculaTaller.clSeccion.secId", sec_id ) ).
                addOrder( Order.asc( "ClAlumno.aluAppaterno" ) ).list();*/

        /*
         select * from cl_alumno
         INNER JOIN cl_matricula ON cl_matricula.alu_id=cl_alumno.alu_id
         INNER JOIN cl_matricula_taller ON cl_matricula_taller.mat_id=cl_matricula.mat_id
         INNER JOIN cl_alumno_tarifa ON cl_alumno_tarifa.alu_id=cl_alumno.alu_id
         INNER JOIN cl_estructura_pagos_detalle ON cl_estructura_pagos_detalle.estpagdet_id=cl_alumno_tarifa.estpagdet_id
         INNER JOIN ad_concepto_pago ON ad_concepto_pago.conpag_id=cl_estructura_pagos_detalle.conpag_id
         INNER JOIN ad_pago ON ad_pago.conpag_id=ad_concepto_pago.conpag_id
         INNER JOIN ad_comprobante_pago ON ad_pago.compag_id=ad_comprobante_pago.compag_id
         where alu_activo=1 and mat_activo=1 and mattal_activo=1 and alutar_activo=1 and estpagdet_activo=1 and conpag_activo=1 and pag_activo=1 and compag_activo=1
         and mat_tipo="022001"    
         and cl_alumno_tarifa.mat_id=cl_matricula.mat_id
         and ad_comprobante_pago.compag_cliente=cl_alumno.alu_id
         and ad_pago.alutar_id=cl_alumno_tarifa.alutar_id
         and cl_alumno_tarifa.sec_id=5233 
         and compag_tipo='037001' 
         and compag_cliente_tipo='014003'
         and cl_alumno.alu_id=17618
         */

    }

    @Override
    public List<ClAlumno> prematriculadosSeccion( int sec_id ) {
        return this.getSession().createCriteria( ClAlumno.class, "ClAlumno" ).
                createCriteria( "ClAlumno.clMatriculas", "ClMatricula" ).
                createCriteria( "ClMatricula.clMatriculaTallers", "ClMatriculaTaller" ).
                add( Restrictions.eq( "ClMatriculaTaller.mattalActivo", "1" ) ).
                add( Restrictions.eq( "ClMatricula.matActivo", "1" ) ).
                add( Restrictions.eq( "ClMatricula.matTipo", "022005" ) ).
                add( Restrictions.eq( "ClAlumno.aluActivo", "1" ) ).
                add( Restrictions.eq( "ClMatriculaTaller.clSeccion.secId", sec_id ) ).
                addOrder( Order.asc( "ClAlumno.aluAppaterno" ) ).list();
    }

    @Override
    public List seleccionarHorariosXTallerApe( int tal_id ) throws Exception {
        List<ClHoraria> l = this.getSession().createCriteria( ClHoraria.class, "ClHoraria" ).
                createCriteria( "ClHoraria.clSeccion", "ClSeccion" ).
                createCriteria( "ClSeccion.clTallerAperturado", "ClTallerAperturado" ).
                add( Restrictions.eq( "ClTallerAperturado.clTaller.talId", tal_id ) ).
                //                add(Restrictions.eq("ClHoraria.horActivo", "1")).
                // addOrder(Order.asc("ClHoraria.horDia")).list();
                addOrder( Order.desc( "ClSeccion.secFinicio" ) ).
                addOrder( Order.asc( "ClSeccion.secNombre" ) ).
                addOrder( Order.asc( "ClHoraria.horDia" ) ).list();

        /*cl_ho sec;
         ClHora*/



        /*String hqlSelect = "SELECT DISTINCT h.horDia,h.horHini,h.horHfin FROM ClHoraria h "
         + "WHERE h.clSeccion.secId  IN(SELECT s.secId FROM ClSeccion s WHERE s.clTallerAperturado.talapeId = 10)";

         /*String hqlSelect = "SELECT DISTINCT c.CatDescripcionElemento,h.horHini,h.horHfin FROM ClHoraria h,TbCatalogo c "
         + "WHERE (h.horDia = CONCAT(c.CatCodigoGrupo,c.CatCodigoElemento))"
         + " AND h.clSeccion.secId  IN(SELECT s.secId FROM ClSeccion s WHERE s.clTallerAperturado.talapeId = 10)";*/


        //List<ClHoraria > l = this.getSession().createQuery(hqlSelect).list();
        /*System.out.println("Tamanio lista : " + l.size());
         if (!l.isEmpty()) {
         ArrayList<ClHoraria> lista = new ArrayList<ClHoraria>(l);

         System.out.println("#JTV : " + lista.get(1).getHorDia() + " - " + lista.get(1).getHorHini().toString() + lista.get(1).getHorHfin().toString());
         }*/

        return l;
        /*return this.getSession().createCriteria(ClHoraria.class,"ClHoraria").
         createCriteria("ClHoraria.clSeccion","ClSeccion").
         createCriteria("ClSeccion.clTallerAperturado","ClTallerAperturado").
         setProjection(
         Projections.distinct(Projections.projectionList())
         ).
         add(Restrictions.eq("ClTallerAperturado.talapeId", talape_id)).
         list();*/

    }
}
