package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AdAreaInstitucion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * Data access object (DAO) for domain model class AdAreaInstitucion.
 * 
 * @see pe.mapa.uch.AdAreaInstitucion
 * @author MyEclipse Persistence Tools
 */

public class AdAreaInstitucionDAOImpl extends  HibernateDaoSupport implements AdAreaInstitucionDAO{
	private static final Log log = LogFactory
			.getLog(AdAreaInstitucionDAOImpl.class);

    @Override
	public void save(AdAreaInstitucion transientInstance) {
		log.debug("saving AdAreaInstitucion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    @Override
	public void delete(AdAreaInstitucion persistentInstance) {
		log.debug("deleting AdAreaInstitucion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    @Override
	public AdAreaInstitucion findById(java.lang.Integer id) {
		log.debug("getting AdAreaInstitucion instance with id: " + id);
		try {
			AdAreaInstitucion instance = (AdAreaInstitucion) getSession().get(
					"net.uch.mapping.AdAreaInstitucion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    @Override
	public List findByExample(AdAreaInstitucion instance) {
		log.debug("finding AdAreaInstitucion instance by example");
		try {
			List results = getSession().createCriteria(
					"net.uch.mapping.AdAreaInstitucion").add(
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
		log.debug("finding AdAreaInstitucion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AdAreaInstitucion as model where model."
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
		log.debug("finding all AdAreaInstitucion instances");
		try {
			String queryString = "from AdAreaInstitucion where areinsActivo='1' order by areinsDescripcion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

    @Override
	public AdAreaInstitucion merge(AdAreaInstitucion detachedInstance) {
		log.debug("merging AdAreaInstitucion instance");
		try {
			AdAreaInstitucion result = (AdAreaInstitucion) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

    @Override
	public void attachDirty(AdAreaInstitucion instance) {
		log.debug("attaching dirty AdAreaInstitucion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    @Override
	public void attachClean(AdAreaInstitucion instance) {
		log.debug("attaching clean AdAreaInstitucion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}