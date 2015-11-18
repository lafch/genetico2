package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsResponsable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Data access object (DAO) for domain model class TsResponsable.
 * 
 * @see pe.censo.uch.entidades.TsResponsable
 * @author MyEclipse Persistence Tools
 */

public class TsResponsableDAOImpl extends HibernateDaoSupport implements TsResponsableDAO {
	private static final Log log = LogFactory.getLog(TsResponsableDAOImpl.class);

    @Override
	public void save(TsResponsable transientInstance) {
		log.debug("saving TsResponsable instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    @Override
	public void delete(TsResponsable persistentInstance) {
		log.debug("deleting TsResponsable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    @Override
	public TsResponsable findById(java.lang.Integer id) {
		log.debug("getting TsResponsable instance with id: " + id);
		try {
			TsResponsable instance = (TsResponsable) getSession().get(
					"net.uch.mapping.TsResponsable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    @Override
	public List findByExample(TsResponsable instance) {
		log.debug("finding TsResponsable instance by example");
		try {
			List results = getSession().createCriteria(
					"net.uch.mapping.TsResponsable").add(
					Example.create(instance)).list();
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
		log.debug("finding TsResponsable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TsResponsable as model where model."
					+ propertyName + "= ? and respActivo='1'";
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
		log.debug("finding all TsResponsable instances");
		try {
			String queryString = "from TsResponsable where respActivo='1' order by respPaterno, respMaterno";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

    @Override
	public TsResponsable merge(TsResponsable detachedInstance) {
		log.debug("merging TsResponsable instance");
		try {
			TsResponsable result = (TsResponsable) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

    @Override
	public void attachDirty(TsResponsable instance) {
		log.debug("attaching dirty TsResponsable instance");
		try {
			this.getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    @Override
	public void attachClean(TsResponsable instance) {
		log.debug("attaching clean TsResponsable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}