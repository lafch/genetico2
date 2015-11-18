package net.uch.solicitud.hibernateSpringDao;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.mapping.TsSolicitudPersona;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import sun.nio.cs.ext.MacHebrew;

/**
 * Data access object (DAO) for domain model class TsSolicitudPersona.
 *
 * @see pe.mapa.uch.TsSolicitudPersona
 * @author MyEclipse Persistence Tools
 */
public class TsSolicitudPersonaDAOImpl extends HibernateDaoSupport implements TsSolicitudPersonaDAO {

    private static final Log log = LogFactory.getLog(TsSolicitudPersonaDAOImpl.class);

    @Override
    public void save(TsSolicitudPersona transientInstance) {
        log.debug("saving TsSolicitudPersona instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    @Override
    public void delete(TsSolicitudPersona persistentInstance) {
        log.debug("deleting TsSolicitudPersona instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    @Override
    public TsSolicitudPersona findById(java.lang.Integer id) {
        log.debug("getting TsSolicitudPersona instance with id: " + id);
        try {
            TsSolicitudPersona instance = (TsSolicitudPersona) getSession().get("net.uch.mapping.TsSolicitudPersona", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    @Override
    public List findByExample(TsSolicitudPersona instance) {
        log.debug("finding TsSolicitudPersona instance by example");
        try {
            List results = getSession().createCriteria(
                    "net.uch.mapping.TsSolicitudPersona").add(
                    Example.create(instance).enableLike(MatchMode.ANYWHERE)).list();
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
        log.debug("finding TsSolicitudPersona instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from TsSolicitudPersona as model where model."
                    + propertyName + "= ? and solperActivo='1'";
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
        log.debug("finding all TsSolicitudPersona instances");
        try {
            String queryString = "from TsSolicitudPersona where solperActivo='1'";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public TsSolicitudPersona merge(TsSolicitudPersona detachedInstance) {
        log.debug("merging TsSolicitudPersona instance");
        try {
            TsSolicitudPersona result = (TsSolicitudPersona) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    @Override
    public void attachDirty(TsSolicitudPersona instance) {
        log.debug("attaching dirty TsSolicitudPersona instance");
        try {
            this.getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    @Override
    public void attachClean(TsSolicitudPersona instance) {
        log.debug("attaching clean TsSolicitudPersona instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    @Override
    public List findFechasEstado(Date fecha_ini, Date fecha_fin, String w_estado) {
        List<TsSolicitudPersona> lista = new ArrayList<TsSolicitudPersona>();
        Criteria criteria = this.getSession().createCriteria(TsSolicitudPersona.class, "tsSolicitudPersona").add(Restrictions.eq("tsSolicitudPersona.solperActivo", "1"));


        if (fecha_ini != null && fecha_fin != null) {
            criteria.add(Restrictions.between("tsSolicitudPersona.solperFechaRegistro", fecha_ini, fecha_fin));
        }
        if (!w_estado.equals("0")) {
            criteria.add(Restrictions.eq("tsSolicitudPersona.solperAprovada", w_estado));
        }
        criteria.addOrder(Order.asc("tsSolicitudPersona.solperFechaRegistro"));
        if (criteria.list().size() > 0) {
            lista = criteria.list();
        }

        return lista;
    }

    public List<File> listarArchivos(String ruta_archivo) {
        String barra = java.io.File.separator;
       // RutaDaoImpl rutaDaoImpl = new RutaDaoImpl();
        File objPlace = new File(ruta_archivo);
        //System.out.println(sec_id +" la ruta "+rutaDaoImpl.rutaDocenteMateriales(sec_id ));
        List<File> listaArchivos = new ArrayList<File>();
        if (objPlace.isDirectory()) {
            String[] arrFileNames = objPlace.list();
            int contador = 1;
            for (int i = 0; i < arrFileNames.length; i++) {
               // ArchivoBean archivoBean = new ArchivoBean();
                String hh = arrFileNames[i].toString();
                //archivoBean.setContador(contador);
                //archivoBean.setNombre_file(hh);
                /**
                 * *****descripcion de archivo*****
                 */
                File objPlace2 = new File(ruta_archivo + barra + hh);
                //System.out.println("ruta de descripcion de archivo : "+sec_id+"\\"+hh);
                /*String name = objPlace2.getName();
                float peso = (objPlace2.length() / 1024);

                long fecha = objPlace2.lastModified();

                Date date = new Date(fecha);
               // DateFormat dataformat = DateFormat.getDateInstance(DateFormat.LONG);
                /*String fecha_f = dataformat.format(date);

                archivoBean.setNombre_file(name);
                archivoBean.setFecha_file(fecha_f);
                archivoBean.setPeso_file(peso);*/

                /**
                 * ********************************
                 */
                listaArchivos.add(objPlace2);
                contador++;

            }
        }
        return listaArchivos;
    }
}