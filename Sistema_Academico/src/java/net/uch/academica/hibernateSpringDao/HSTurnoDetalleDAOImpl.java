package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcTurnoDetalle;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.dao.DataAccessException;

public class HSTurnoDetalleDAOImpl extends HibernateDaoSupport implements HSTurnoDetalleDAO {
    
    @Override
    public AcTurnoDetalle buscarAcTurnoDetalle( int turDetId ) throws DataAccessException {
        return (AcTurnoDetalle)this.getSession().createCriteria( AcTurnoDetalle.class ).
                add( Restrictions.eq( AcTurnoDetalle.PROP_TURDET_ACTIVO, "1" ) ).
                add( Restrictions.eq( AcTurnoDetalle.PROP_ID, turDetId ) ).uniqueResult();
    }

    @Override
    public void insertarTurnoDetalle(AcTurnoDetalle turno_detalle) throws DataAccessException {
        getHibernateTemplate().save(turno_detalle);
    }

    @Override
    public List seleccionarTurnoDetalle(int id_turno) throws DataAccessException {
        String hql = "select Id, TurdetHora, Tur.Id "
                + "from AcTurnoDetalle "
                + "where Tur.Id= :turno and TurdetActivo = :activo ";

        return this.getSession().createQuery(hql).
                setString("activo", "1").
                setString("turno", "" + id_turno).list();
    }

    @Override
    public void actualizarTurnoDetalle(AcTurnoDetalle turno_detalle) throws DataAccessException {
        getHibernateTemplate().update(turno_detalle);
    }

    @Override
    public void eliminarTurnoDetalle(int id) throws DataAccessException {
        String query = "update AcTurnoDetalle set TurdetActivo=:activo where Id= :id";
        this.getSession().createQuery(query).
                setString("id", "" + id).
                setString("activo", "0").executeUpdate();
    }
}
