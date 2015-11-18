package net.uch.academica.hibernateSpringDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.mapping.AcAlumnoEstado;
import net.uch.mapping.AcEstadoSemestre;
import net.uch.util.BeanParametros;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Data access object (DAO) for domain model class AcAlumnoEstado.
 *
 * @see pe.censo.uch.entidades.AcAlumnoEstado
 * @author MyEclipse Persistence Tools
 */
public class AcAlumnoEstadoDAOImpl extends HibernateDaoSupport implements AcAlumnoEstadoDAO {

    private static final Log log = LogFactory.getLog(AcAlumnoEstadoDAOImpl.class);

    @Override
    public void save(AcAlumnoEstado transientInstance) {
        log.debug("saving AcAlumnoEstado instance");
        try {
            this.getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    @Override
    public void delete(AcAlumnoEstado persistentInstance) {
        log.debug("deleting AcAlumnoEstado instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    @Override
    public AcAlumnoEstado findById(java.lang.Integer id) {
        log.debug("getting AcAlumnoEstado instance with id: " + id);
        try {
            AcAlumnoEstado instance = (AcAlumnoEstado) getSession().get(
                    "net.uch.mapping.AcAlumnoEstado", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    @Override
    public List findByExample(AcAlumnoEstado instance) {
        log.debug("finding AcAlumnoEstado instance by example");
        try {
            List results = getSession().createCriteria(
                    "net.uch.mapping.AcAlumnoEstado").add(
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
        log.debug("finding AcAlumnoEstado instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from AcAlumnoEstado as model where model."
                    + propertyName + "= ? and aluestActivo='1'";
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
        log.debug("finding all AcAlumnoEstado instances");
        try {
            String queryString = "from AcAlumnoEstado";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public AcAlumnoEstado merge(AcAlumnoEstado detachedInstance) {
        log.debug("merging AcAlumnoEstado instance");
        try {
            AcAlumnoEstado result = (AcAlumnoEstado) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    @Override
    public void attachDirty(AcAlumnoEstado instance) {
        log.debug("attaching dirty AcAlumnoEstado instance");
        try {
            this.getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    @Override
    public void attachClean(AcAlumnoEstado instance) {
        log.debug("attaching clean AcAlumnoEstado instance");
        try {
            this.getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    @Override
    public AcAlumnoEstado findAlumnoEstado(AcAlumnoEstado acAlumnoEstado) {
        AcAlumnoEstado alumno=new AcAlumnoEstado();
        alumno = (AcAlumnoEstado) this.getSession().createCriteria(AcAlumnoEstado.class)
                .add(Restrictions.eq("acAlumno.id", acAlumnoEstado.getAcAlumno().getId()))
                .add(Restrictions.eq("acSemestre.id", acAlumnoEstado.getAcSemestre().getId()))
                .add(Restrictions.eq("aluestActivo", "1"))
                .add(Restrictions.eq("aluestEstado", acAlumnoEstado.getAluestEstado()))
                .uniqueResult();
        return alumno;
    }
    
    @Override
    public List findByProperties(List<BeanParametros> listaParametros ){        
            String queryString = "from AcAlumnoEstado where ";
            //        + propertyName + "= ? and aluestActivo='1'";
            for(int i=0; i<listaParametros.size(); i++){
                queryString=queryString+listaParametros.get(i).getProNombre()+"=? "+listaParametros.get(i).getProOperador()+" ";
            }
            Query queryObject = getSession().createQuery(queryString);
            for(int i=0; i<listaParametros.size(); i++){
                queryObject.setParameter(i, listaParametros.get(i).getProValor());
            }
            
        return queryObject.list();
    }

    @Override
    public List<AcEstadoSemestre> seleccionarEstado(Integer aluId, Integer SemId) {
        List<AcEstadoSemestre> lista=new ArrayList<AcEstadoSemestre>();
        Criteria criteria=this.getSession().createCriteria(AcEstadoSemestre.class)
                .add(Restrictions.eq("acAlumno.Id", aluId))
                .add(Restrictions.eq("acSemestre.Id", SemId))
                .createCriteria("acAlumnoEstado")
                .add(Restrictions.eq("aluestActivo", "1"));
        if(criteria.list().size()>0){
            lista=criteria.list();
        }
        return lista;
    }

    @Override
    public void eliminarEstado(Integer aluEstId) {
        String hql="delete from AcEstadoSemestre where acAlumnoEstado.aluestId=:v_id";
        this.getSession().createQuery(hql).setInteger("v_id", aluEstId).executeUpdate();
    }
}