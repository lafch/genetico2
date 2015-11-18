package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsFut;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * Data access object (DAO) for domain model class TsFut.
 * 
 * @see pe.mapa.uch.TsFut
 * @author MyEclipse Persistence Tools
 */

public class TsFutDAOImpl extends HibernateDaoSupport implements TsFutDAO {
	private static final Log log = LogFactory.getLog(TsFutDAOImpl.class);

    @Override
	public void save(TsFut transientInstance) {
		log.debug("saving TsFut instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    @Override
	public void delete(TsFut persistentInstance) {
		log.debug("deleting TsFut instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    @Override
	public TsFut findById(java.lang.Integer id) {
		log.debug("getting TsFut instance with id: " + id);
		try {
			TsFut instance = (TsFut) getSession().get("net.uch.mapping.TsFut", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    @Override
	public List findByExample(TsFut instance) {
		log.debug("finding TsFut instance by example");
		try {
			List results = getSession().createCriteria("net.uch.mapping.TsFut")
					.add(Example.create(instance)).list();
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
		log.debug("finding TsFut instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TsFut as model where model."
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
		log.debug("finding all TsFut instances");
		try {
			String queryString = "from TsFut";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

    @Override
	public TsFut merge(TsFut detachedInstance) {
		log.debug("merging TsFut instance");
		try {
			TsFut result = (TsFut) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

    @Override
	public void attachDirty(TsFut instance) {
		log.debug("attaching dirty TsFut instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    @Override
	public void attachClean(TsFut instance) {
		log.debug("attaching clean TsFut instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}