package net.uch.laboratorio.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.TbIncidenciaLab;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Data access object (DAO) for domain model class TbIncidenciaLab.
 *
 * @see pe.censo.uch.entidades.TbIncidenciaLab
 * @author MyEclipse Persistence Tools
 */
public class TbIncidenciaLabDAOImpl extends HibernateDaoSupport implements TbIncidenciaLabDAO {

    private static final Log log = LogFactory.getLog( TbIncidenciaLabDAOImpl.class );

    @Override
    public void save( TbIncidenciaLab transientInstance ) {
        log.debug( "saving TbIncidenciaLab instance" );
        try {
            getSession().save( transientInstance );
            log.debug( "save successful" );
        } catch ( RuntimeException re ) {
            log.error( "save failed", re );
            throw re;
        }
    }

    @Override
    public void delete( TbIncidenciaLab persistentInstance ) {
        log.debug( "deleting TbIncidenciaLab instance" );
        try {
            getSession().delete( persistentInstance );
            log.debug( "delete successful" );
        } catch ( RuntimeException re ) {
            log.error( "delete failed", re );
            throw re;
        }
    }

    @Override
    public TbIncidenciaLab findById( java.lang.Integer id ) {
        log.debug( "getting TbIncidenciaLab instance with id: " + id );
        try {
            TbIncidenciaLab instance = (TbIncidenciaLab) getSession().get(
                    "pe.censo.uch.entidades.TbIncidenciaLab", id );
            return instance;
        } catch ( RuntimeException re ) {
            log.error( "get failed", re );
            throw re;
        }
    }

    @Override
    public List findByExample( TbIncidenciaLab instance ) {
        log.debug( "finding TbIncidenciaLab instance by example" );
        try {
            List results = getSession().createCriteria(
                    "pe.censo.uch.entidades.TbIncidenciaLab" ).add(
                    Example.create( instance ) ).list();
            log.debug( "find by example successful, result size: "
                    + results.size() );
            return results;
        } catch ( RuntimeException re ) {
            log.error( "find by example failed", re );
            throw re;
        }
    }

    @Override
    public List findByProperty( String propertyName, Object value ) {
        log.debug( "finding TbIncidenciaLab instance with property: "
                + propertyName + ", value: " + value );
        try {
            String queryString = "from TbIncidenciaLab as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery( queryString );
            queryObject.setParameter( 0, value );
            return queryObject.list();
        } catch ( RuntimeException re ) {
            log.error( "find by property name failed", re );
            throw re;
        }
    }

    @Override
    public List findAll() {
        log.debug( "finding all TbIncidenciaLab instances" );
        try {
            String queryString = "from TbIncidenciaLab";
            Query queryObject = getSession().createQuery( queryString );
            return queryObject.list();
        } catch ( RuntimeException re ) {
            log.error( "find all failed", re );
            throw re;
        }
    }

    @Override
    public TbIncidenciaLab merge( TbIncidenciaLab detachedInstance ) {
        log.debug( "merging TbIncidenciaLab instance" );
        try {
            TbIncidenciaLab result = (TbIncidenciaLab) getSession().merge(
                    detachedInstance );
            log.debug( "merge successful" );
            return result;
        } catch ( RuntimeException re ) {
            log.error( "merge failed", re );
            throw re;
        }
    }

    @Override
    public void attachDirty( TbIncidenciaLab instance ) {
        log.debug( "attaching dirty TbIncidenciaLab instance" );
        try {
            this.getHibernateTemplate().saveOrUpdate( instance );
            log.debug( "attach successful" );
        } catch ( RuntimeException re ) {
            log.error( "attach failed", re );
            throw re;
        }
    }

    @Override
    public void attachClean( TbIncidenciaLab instance ) {
        log.debug( "attaching clean TbIncidenciaLab instance" );
        try {
            getSession().lock( instance, LockMode.NONE );
            log.debug( "attach successful" );
        } catch ( RuntimeException re ) {
            log.error( "attach failed", re );
            throw re;
        }
    }

    @Override
    public List<TbIncidenciaLab> getListarIncidenciaTipo( String tipo, String desc ) {
        String sQuery;
        Criteria criteria;
        SQLQuery query;
        List<Integer> lst;
        List<TbIncidenciaLab> lstIncidLabBib;

        desc = "%" + desc + "%";
        sQuery = "SELECT inclab_id "
                + "FROM tb_incidencia_lab "
                + "WHERE tipo_incidencia=:tipo AND inclab_activo = 1 AND ( inclab_equipo LIKE :equip "
                + "OR inclab_descripcion_danio LIKE :desc "
                + "OR alu_id IN ( "
                + "SELECT a.alu_id "
                + "FROM ac_alumno a "
                + "WHERE a.alu_nombres LIKE :nom "
                + "OR a.alu_appaterno LIKE :pat "
                + "OR a.alu_apmaterno LIKE :mat))";
        query = this.getSession().createSQLQuery( sQuery );
        query.setParameter( "tipo", tipo );
        query.setParameter( "equip", desc );
        query.setParameter( "desc", desc );
        query.setParameter( "nom", desc );
        query.setParameter( "pat", desc );
        query.setParameter( "mat", desc );
        try {
            lst = query.list();
        } catch ( Exception ex ) {
            System.out.println( "ERROR  :  " + ex.getMessage() );
            ex.printStackTrace();
            lst = new ArrayList<Integer>();
        }

        if( !lst.isEmpty() ){
            criteria = this.getSession().createCriteria( TbIncidenciaLab.class );
            lstIncidLabBib = criteria.add( Restrictions.in( "inclabId", lst ) ).list();
        }else{
            lstIncidLabBib = new ArrayList<TbIncidenciaLab>();
        }
        return lstIncidLabBib;
    }
}