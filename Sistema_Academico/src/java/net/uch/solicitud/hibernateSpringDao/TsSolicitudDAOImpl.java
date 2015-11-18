package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsSolicitud;
import net.uch.mapping.TsSolicitudPersona;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Data access object (DAO) for domain model class TsSolicitud.
 * 
 * @see pe.mapa.uch.TsSolicitud
 * @author MyEclipse Persistence Tools
 */

public class TsSolicitudDAOImpl extends HibernateDaoSupport implements TsSolicitudDAO {
	private static final Log log = LogFactory.getLog(TsSolicitudDAOImpl.class);

    @Override
	public void save(TsSolicitud transientInstance) {
		log.debug("saving TsSolicitud instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    @Override
	public boolean delete(TsSolicitud instance) {
            boolean flag=false;
            log.debug("deleting TsSolicitud instance");
            try {
                List<TsSolicitudPersona> lista=this.getSession().createCriteria(TsSolicitudPersona.class,"TsSolicitudPersona")
                        .add(Restrictions.eq("tsSolicitud.solId", instance.getSolId()))
                        .list();
                if(lista.size()==0){
                    String hql="update TsSolicitud set solActivo=:v_activo where solId=:v_id";
                    this.getSession().createQuery(hql).setString("v_activo", "0").setInteger("v_id", instance.getSolId()).executeUpdate();
                    flag=true;
                }               
                log.debug("delete successful");
            } catch (RuntimeException re) {
                log.error("delete failed", re);
                throw re;
            }
            return flag;
	}

    @Override
	public TsSolicitud findById(java.lang.Integer id) {
		log.debug("getting TsSolicitud instance with id: " + id);
		try {
			TsSolicitud instance = (TsSolicitud) getSession().get(
					"net.uch.mapping.TsSolicitud", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    @Override
	public List findByExample(TsSolicitud instance) {
		log.debug("finding TsSolicitud instance by example");
		try {
			List results = getSession().createCriteria(
					"net.uch.mapping.TsSolicitud").add(Example.create(instance).enableLike(MatchMode.ANYWHERE))
                                        .addOrder(Order.asc("solDescripcion"))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

    @Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TsSolicitud instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TsSolicitud as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

    @Override
	public List findAll() {
		log.debug("finding all TsSolicitud instances");
		try {
			String queryString = "from TsSolicitud";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

    @Override
	public TsSolicitud merge(TsSolicitud detachedInstance) {
		log.debug("merging TsSolicitud instance");
		try {
			TsSolicitud result = (TsSolicitud) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

    @Override
	public void attachDirty(TsSolicitud instance) {
            log.debug("attaching dirty TsSolicitud instance");
            try {
                this.getHibernateTemplate().saveOrUpdate(instance);
                log.debug("attach successful");
            } catch (RuntimeException re) {
                log.error("attach failed", re);
                throw re;
            }
	}

    @Override
	public void attachClean(TsSolicitud instance) {
		log.debug("attaching clean TsSolicitud instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}