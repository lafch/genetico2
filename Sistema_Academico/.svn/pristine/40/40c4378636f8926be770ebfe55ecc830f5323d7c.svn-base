package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClArbolAperturado;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSArbolAperturadoClDAOImpl extends HibernateDaoSupport implements HSArbolAperturadoClDAO {

    @Override
    public ClArbolAperturado seleccionarArbTallerAperturado( int iArbTalapeId ) {
        return (ClArbolAperturado) this.getSession().createCriteria( ClArbolAperturado.class ).
                add( Restrictions.eq( "arbapeActivo", "1" ) ).
                add( Restrictions.eq( "arbapeId", iArbTalapeId ) ).
                uniqueResult();
    }

    @Override
    public List<ClArbolAperturado> seleccionarArbTalleresAperturados() throws Exception {
        return this.getSession().createCriteria( ClArbolAperturado.class ).
                add( Restrictions.eq( "arbapeActivo", "1" ) ).
                add( Restrictions.eq( "arbapeVigente", "1" ) ).
                add( Restrictions.eq( "arbapeAperturado", "1" ) ).
                list();
    }

    @Override
    public List<ClArbolAperturado> seleccionarArbTalleresAperturadosPorTaller( int iArbTalId ) {
        Criteria c = this.getSession().createCriteria( ClArbolAperturado.class ).
                add( Restrictions.eq( "arbapeActivo", "1" ) ).
                add( Restrictions.eq( "clArbolAcademico.arbId", iArbTalId ) );

        return c.list();
    }

    @Override
    public void insertar_ActualizarArbTallerAperturado( ClArbolAperturado arbTalape ) throws Exception {
        this.getHibernateTemplate().saveOrUpdate( arbTalape );
    }

    @Override
    public void eliminarArbTallerAperturado( int iArbTalapeId ) {
        String hqlUpdate = "update ClArbolAperturado set arbapeActivo = '0' where arbapeId = :arbape_id";
        this.getSession().createQuery( hqlUpdate ).setInteger( "arbape_id", iArbTalapeId ).executeUpdate();
    }

    @Override
    public List<ClArbolAperturado> cargarArbTallerAperturado( int iTalId ) {

        return this.getSession().createCriteria( ClArbolAperturado.class, "arbape" ).
                /*
                 * createCriteria("arbape.clArbolAcademico", "tal").
                 * add(Restrictions.eq("tal.arbActivo", "1")).
                 */
                add( Restrictions.eq( "arbape.arbapeActivo", "1" ) ).
                add( Restrictions.eq( "arbape.arbapeVigente", "1" ) ).
                add( Restrictions.eq( "arbape.arbapeAperturado", "1" ) ).
                add( Restrictions.eq( "clArbolAcademico.arbId", iTalId ) ).
                addOrder( Order.asc( "arbape.arbapeDescripcion" ) ).list();
    }

    @Override
    public List<ClArbolAcademico> findArbTallerXModulo( int iModId, int iArbTalId ) {

        List<ClArbolAcademico> lstArbTalleres;
        String hqlQuery = "FROM ClArbolAcademico "
                + "where arbActivo='1' AND arbIdPadre IN(SELECT arbId FROM ClArbolAcademico "
                + "where arbIdPadre=:arb_id "
                + "and arbActivo='1') "
                + "and arb_id!=:tal_id";

        lstArbTalleres = this.getSession().createQuery( hqlQuery ).setInteger( "arb_id", iModId ).setInteger( "tal_id", iArbTalId ).list();
        return lstArbTalleres;

    }
    
    @Override
    public List<ClArbolAcademico> findArbTallerXModuloNuevo( int iModId) {

        List<ClArbolAcademico> lstArbTalleres;
        String hqlQuery = "FROM ClArbolAcademico "
                + "where arbActivo='1' AND arbIdPadre IN(SELECT arbId FROM ClArbolAcademico "
                + "where arbIdPadre=:arb_id "
                + "and arbActivo='1') ";

        lstArbTalleres = this.getSession().createQuery( hqlQuery ).setInteger( "arb_id", iModId ).list();
        return lstArbTalleres;

    }
}
