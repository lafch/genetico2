package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcParametro;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSParametroDAOImpl extends HibernateDaoSupport implements HSParametroDAO {

    @Override
    public void insertarParametro( AcParametro parametro ) throws DataAccessException {
        getHibernateTemplate().save( parametro );
    }

    @Override
    public List seleccionarParametro( String codigo, String valor) throws DataAccessException, java.sql.SQLException {
        Criteria c;
        c = this.getSession().createCriteria( AcParametro.class ).add( Restrictions.eq( "ParActivo", "1" ) );
        if(codigo.trim().length()>0){
                c.add( Restrictions.like("ParCod", "%"+codigo+"%"));
        }
        if(valor.trim().length()>0){
            c.add( Restrictions.like("ParVal", "%"+valor+"%"));
        }
        c.addOrder( Order.asc( "ParCod" ) );
        return c.list();
    }

    @Override
    public void eliminarParametro( int id ) throws DataAccessException {
        String hqlUpdate = "update AcParametro set ParActivo = :activo where Id = :id";
        this.getSession().createQuery( hqlUpdate ).setString( "activo", "0" ).setInteger("id", id ).executeUpdate();
    }

    @Override
    public void actualizarParametro( AcParametro parametro )
            throws DataAccessException {
        getHibernateTemplate().update( parametro );
    }

    @Override
    public AcParametro buscarParametro( String par_cod ) {
        Criteria c;
        c = this.getSession().createCriteria( AcParametro.class ).add(Restrictions.eq("ParCod", par_cod)).add( Restrictions.eq( "ParActivo", "1" ) );
        return (AcParametro) c.uniqueResult();
    }

}
