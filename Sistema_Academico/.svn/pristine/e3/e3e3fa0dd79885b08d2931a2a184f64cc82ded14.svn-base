package net.uch.cursoLibre.hibernateSpringDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.uch.mapping.ClModulo;
import net.uch.mapping.ClSeccion;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSModuloDAOImpl extends HibernateDaoSupport implements HSModuloDAO {

    @Override
    public List seleccionarModulos( int are_id, String descripcion ) throws Exception {
        Criteria criteria = this.getSession().createCriteria( ClModulo.class ).
                add( Restrictions.eq( "modActivo", "1" ) );

        if ( descripcion.trim().length() != 0 ) {
            criteria.add( Restrictions.like( "modDescripcion", "%" + descripcion + "%" ) );
        }
        if ( are_id > 0 ) {
            criteria.add( Restrictions.eq( "clArea.areId", are_id ) );
        }
        return criteria.list();
    }

    @Override
    public void insertarModulo( ClModulo modulo ) throws Exception {
        this.getHibernateTemplate().save( modulo );
    }

    @Override
    public void eliminarModulo( int mod_id ) throws Exception {
        String hqlUpdate = "update ClModulo set modActivo = :newName where modId = :oldName";
        this.getSession().createQuery( hqlUpdate ).setString( "newName", "0" ).setString( "oldName", "" + mod_id ).executeUpdate();
    }

    @Override
    public void actualizarModulo( ClModulo modulo ) throws Exception {
        this.getHibernateTemplate().update( modulo );
    }

    @Override
    public ClModulo buscarModulo( int mod_id ) throws Exception {
        return (ClModulo)this.getSession().createCriteria( ClModulo.class ).
                add( Restrictions.eq( "modId", mod_id ) ).
                uniqueResult();
    }

    @Override
    public List listarModulos() throws Exception {
        List lista = this.getSession().createCriteria( ClModulo.class ).
                add( Restrictions.eq( "modActivo", "1" ) ).list();
        return lista;
    }

    @Override
    public List listarModulosAperturados() {
        List lista = null;
        try {


            Calendar fecha1 = Calendar.getInstance();
            Calendar fecha2 = Calendar.getInstance();
            fecha1.setTime( new Date() );
            fecha1.add( fecha1.DAY_OF_MONTH, 60 );
            fecha2.setTime( new Date() );
            fecha2.add( fecha2.DAY_OF_MONTH, -90 );
            DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            String TfechaI = format.format( fecha2.getTime() );
            String TfechaF = format.format( fecha1.getTime() );
            Date w_fechaI = sdf.parse( TfechaI );
            Date w_fechaF = sdf.parse( TfechaF );
            System.out.println( "fecha inicio -> " + w_fechaI + " --- fecha fin -> " + w_fechaF );

            Criteria cri = this.getSession().createCriteria( ClSeccion.class, "clSeccion" )
                    .createCriteria( "clSeccion.clTallerAperturado", "clTallerAperturado" )
                    .createCriteria( "clTallerAperturado.clTaller", "clTaller" )
                    .createCriteria( "clTaller.clCurso", "clCurso" )
                    .createCriteria( "clCurso.clModulo", "clModulo" )
                    .add( Restrictions.eq( "clSeccion.secActivo", "1" ) )
                    .add( Restrictions.eq( "clTallerAperturado.talapeActivo", "1" ) )
                    .add( Restrictions.eq( "clTaller.talActivo", "1" ) )
                    .add( Restrictions.eq( "clCurso.curActivo", "1" ) )
                    .add( Restrictions.eq( "clModulo.modActivo", "1" ) )
                    .add( Restrictions.between( "clSeccion.secFinicio", w_fechaI, w_fechaF ) )
                    .setProjection( Projections.projectionList()
                    .add( Projections.rowCount(), "cantidad" )
                    .add( Projections.property( "clModulo.modId" ) )
                    .add( Projections.property( "clModulo.modCodigo" ) )
                    .add( Projections.property( "clModulo.modDescripcion" ) )
                    .add( Projections.property( "clModulo.modActivo" ) )
                    .add( Projections.groupProperty( "clModulo.modId" ) ) );
            lista = cri.list();
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return lista;
    }
}
