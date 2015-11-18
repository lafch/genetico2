package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsRequisito;
import net.uch.mapping.TsRequisitoSolicitud;
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
 * Data access object (DAO) for domain model class TsRequisito.
 * 
 * @see pe.mapa.uch.TsRequisito
 * @author MyEclipse Persistence Tools
 */

public class TsRequisitoDAOImpl extends HibernateDaoSupport implements TsRequisitoDAO {
	private static final Log log = LogFactory.getLog(TsRequisitoDAOImpl.class);

    @Override
	public void save(TsRequisito transientInstance) {
		log.debug("saving TsRequisito instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    @Override
	public boolean delete(TsRequisito instance) {
            boolean flag=false;
		log.debug("deleting TsRequisito instance");
		try {
                    List<TsRequisitoSolicitud> lista=this.getSession().createCriteria(TsRequisitoSolicitud.class,"tsRequisitoSolicitud")
                            .add(Restrictions.eq("tsRequisitoSolicitud.reqsolActivo", "1"))
                            .createCriteria("tsRequisitoSolicitud.tsRequisito", "tsRequisito")
                            .add(Restrictions.eq("tsRequisito.reqId",instance.getReqId()))
                            .list();
			if(lista.size()==0){
                            String hql="update TsRequisito set reqActivo=:v_activo where reqId=:v_id";
                            this.getSession().createQuery(hql).setString("v_activo", "0").setInteger("v_id", instance.getReqId()).executeUpdate();
                            flag=true;
                        }
			log.debug("delete successful");
		} catch (RuntimeException re) {
                     flag=true;
			log.error("delete failed", re);
			throw re;
		}
                return flag;
	}

    @Override
	public TsRequisito findById(java.lang.Integer id) {
		log.debug("getting TsRequisito instance with id: " + id);
		try {
			TsRequisito instance = (TsRequisito) getSession().get(
					"net.uch.mapping.TsRequisito", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

    @Override
	public List findByExample(TsRequisito instance) {
		log.debug("finding TsRequisito instance by example");
		try {
			List results = getSession().createCriteria(
					"net.uch.mapping.TsRequisito").add(Example.create(instance).enableLike(MatchMode.ANYWHERE))
                                        .addOrder(Order.asc("reqDescripcion"))
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
		log.debug("finding TsRequisito instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TsRequisito as model where model."
					+ propertyName + "= ? order by reqDescripcion ";
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
		log.debug("finding all TsRequisito instances");
		try {
			String queryString = "from TsRequisito";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

    @Override
	public TsRequisito merge(TsRequisito detachedInstance) {
		log.debug("merging TsRequisito instance");
		try {
			TsRequisito result = (TsRequisito) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

    @Override
	public void attachDirty(TsRequisito instance) {
		log.debug("attaching dirty TsRequisito instance");
		try {
                        System.out.println("imprimir el id -> "+instance.getReqId());
			//getSession().saveOrUpdate(instance);
                        this.getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    @Override
	public void attachClean(TsRequisito instance) {
		log.debug("attaching clean TsRequisito instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}