package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsRequisitoSolicitud;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Data access object (DAO) for domain model class TsRequisitoSolicitud.
 * 
 * @see pe.mapa.uch.TsRequisitoSolicitud
 * @author MyEclipse Persistence Tools
 */

public class TsRequisitoSolicitudDAOImpl extends HibernateDaoSupport implements TsRequisitoSolicitudDAO {
	private static final Log log = LogFactory
			.getLog(TsRequisitoSolicitudDAOImpl.class);

    @Override
	public void save(TsRequisitoSolicitud transientInstance) {
		log.debug("saving TsRequisitoSolicitud instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    @Override
	public void delete(TsRequisitoSolicitud instance) {
            log.debug("deleting TsRequisitoSolicitud instance");
            try {
                String hql="update TsRequisitoSolicitud set reqsolActivo=:v_activo where tsSolicitud.solId=:v_solId";
                this.getSession().createQuery(hql).setString("v_activo", "0").setInteger("v_solId", instance.getTsSolicitud().getSolId()).executeUpdate();
                    
                    log.debug("delete successful");
            } catch (RuntimeException re) {
                    log.error("delete failed", re);
                    throw re;
            }
	}

    @Override
	public TsRequisitoSolicitud findById(java.lang.Integer id) {
		log.debug("getting TsRequisitoSolicitud instance with id: " + id);
		try {
			TsRequisitoSolicitud instance = (TsRequisitoSolicitud) getSession()
					.get("net.uch.mapping.TsRequisitoSolicitud", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    @Override
	public List findByExample(TsRequisitoSolicitud instance) {
		log.debug("finding TsRequisitoSolicitud instance by example");
		try {
			List results = getSession().createCriteria(
					"net.uch.mapping.TsRequisitoSolicitud").add(
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
		log.debug("finding TsRequisitoSolicitud instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TsRequisitoSolicitud as model where model."
					+ propertyName + "= ? and reqsolActivo='1'";
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
		log.debug("finding all TsRequisitoSolicitud instances");
		try {
			String queryString = "from TsRequisitoSolicitud";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

    @Override
	public TsRequisitoSolicitud merge(TsRequisitoSolicitud detachedInstance) {
		log.debug("merging TsRequisitoSolicitud instance");
		try {
			TsRequisitoSolicitud result = (TsRequisitoSolicitud) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

    @Override
	public void attachDirty(TsRequisitoSolicitud instance) {
		log.debug("attaching dirty TsRequisitoSolicitud instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    @Override
	public void attachClean(TsRequisitoSolicitud instance) {
		log.debug("attaching clean TsRequisitoSolicitud instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}