package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcPagina;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSPaginaDAOImpl extends HibernateDaoSupport implements HSPaginaDAO {

    @Override
    public void insertarPagina( AcPagina pagina ) throws DataAccessException {
        getHibernateTemplate().save( pagina );
    }

    @Override
    public List seleccionarPagina( String descripcion, String url) throws DataAccessException, java.sql.SQLException {
        Criteria c;
        c = this.getSession().createCriteria( AcPagina.class )
                .add(Restrictions.like("PagUrl", "%"+url+"%"))
                .add( Restrictions.eq( "PagActivo", "1" ) );
        c.addOrder( Order.asc( "Id" ) );
        return c.list();
    }

    @Override
    public void eliminarPagina( String id ) throws DataAccessException {
        String hqlUpdate = "update AcPagina set PagActivo = :activo where Id = :id";
        this.getSession().createQuery( hqlUpdate ).setString( "activo", "0" ).setString( "id", id ).executeUpdate();
    }

    @Override
    public void actualizarPagina( AcPagina pagina )
            throws DataAccessException {
        getHibernateTemplate().update( pagina );
    }

    @Override
    public List seleccionarPagina() throws Exception {
        return this.getSession().createCriteria( AcPagina.class ).add( Restrictions.eq( "PagActivo", "1" ) ).list();
    }

    @Override
    public AcPagina buscarPagina( int pag_id ) {
        return (AcPagina)this.getSession().get( AcPagina.class, pag_id );
    }

}
