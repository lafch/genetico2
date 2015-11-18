package net.uch.tablaSistema.hibernateSpringDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.mapping.AcSemestre;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSSemestreDAOImpl extends HibernateDaoSupport implements HSSemestreDAO {

    @Override
    public void insertarSemestre(AcSemestre semestre) throws DataAccessException {
        this.getHibernateTemplate().save(semestre);
    }

    @Override
    public List seleccionarSemestre(String codigo, String nombre) throws DataAccessException {
        DetachedCriteria criteria = DetachedCriteria.forClass(AcSemestre.class);
        if (!codigo.equals("")) {
            criteria.add(Expression.eq("SemCodigo", codigo));
        }
        criteria.add(Expression.like("SemNombre", "%" + nombre + "%"));
        criteria.add(Expression.eq("SemActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public void eliminarSemestre(String id) throws DataAccessException {
        String hqlUpdate = "update AcSemestre set SemActivo = :newName where Id = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", id).executeUpdate();
    }

    @Override
    public void actualizarSemestre(AcSemestre semestre) throws DataAccessException {
        getHibernateTemplate().update(semestre);
    }

    @Override
    public String seleccionarSemestreVigente(int valor) {
        String hqlSelect = "";
        if (valor == 0) {
            hqlSelect += "select semestre.Id ";
        } else if (valor == 1) {
            hqlSelect += "select semestre.SemNombre ";
        }
        hqlSelect += "from AcSemestre as semestre "
                + "where semestre.SemVigente = :vigente "
                + "and semestre.SemActivo = :activo and semestre.SemActual = :actual";
        List lista = null;
        lista = this.getSession().createQuery(hqlSelect).setString("vigente", "1").setString("activo", "1").setString("actual", "1").list();
        String codigo = "0";
        if (lista.size() != 0) {
            codigo = lista.get(0) + "";
        }
        return codigo;
    }

    @Override
    public List seleccionarSemestreActivo() {
        return this.getSession().createCriteria(AcSemestre.class).add(Expression.eq("SemActivo", "1")).addOrder(Order.desc("SemFechaInicio")).list();
    }

    @Override
    public List seleccionarSemestreVigente() {
        int sem=21;
        return this.getSession().createCriteria(AcSemestre.class).add(Restrictions.eq("SemVigente", "1")).add(Restrictions.eq("SemActivo", "1")).add(Restrictions.eq("Id", sem)).addOrder(Order.desc("Id")).list();
        //return this.getSession().createCriteria(AcSemestre.class).add(Restrictions.eq("SemVigente", "1")).setMaxResults(5).add(Restrictions.eq("SemActivo", "1")).addOrder(Order.desc("Id")).list();
    }
    
    @Override
    public List seleccionarSemestreVigenteH() {
        //return this.getSession().createCriteria(AcSemestre.class).add(Restrictions.eq("SemVigente", "1")).add(Restrictions.eq("SemActivo", "1")).addOrder(Order.desc("Id")).list();
        return this.getSession().createCriteria(AcSemestre.class).add(Restrictions.eq("SemVigente", "1")).setMaxResults(3).add(Restrictions.eq("SemActivo", "1")).addOrder(Order.desc("Id")).list();
    }
    
    @Override
    public List seleccionarSemestreVigenteH(int idSemestre) {
        //return this.getSession().createCriteria(AcSemestre.class).add(Restrictions.eq("SemVigente", "1")).add(Restrictions.eq("SemActivo", "1")).addOrder(Order.desc("Id")).list();
        return this.getSession().createCriteria(AcSemestre.class).add(Restrictions.eq("SemVigente", "1")).add(Restrictions.eq("SemActivo", "1")).add(Restrictions.eq("Id",idSemestre)).addOrder(Order.desc("Id")).list();
    }

    @Override
    public List seleccionarVigente() {
        return this.getSession().createCriteria(AcSemestre.class).add(Restrictions.eq("SemVigente", "1")).add(Restrictions.eq("SemActivo", "1")).addOrder(Order.asc("Id")).list();
    }

    @Override
    public List seleccionarSemestreActual() {
        return this.getSession().createCriteria(AcSemestre.class).add(Restrictions.eq("SemActivo", "1")).add(Restrictions.eq("SemActual", "1")).addOrder(Order.desc("Id")) .list();
    }

    @Override
    public String seleccionarSemestreAct() {
        String hqlSelect = "";
        hqlSelect += "select semestre.Id ";
        hqlSelect += "from AcSemestre as semestre "
                + "where semestre.SemVigente = :vigente "
                + "and semestre.SemActual = :actual "
                + "and semestre.SemActivo = :activo ";
        List lista = null;
        lista = this.getSession().createQuery(hqlSelect).setString("vigente", "1").setString("actual", "1").setString("activo", "1").list();
        String id_sem = "0";
        if (lista.size() != 0) {
            id_sem = lista.get(0) + "";
        }
        return id_sem;
    }

    @Override
    public List<AcSemestre> listarSemestreId(int valor) {
        List<AcSemestre> lista= new ArrayList<AcSemestre>();
        Criteria criteria=this.getSession().createCriteria(AcSemestre.class)
                .add(Restrictions.eq("SemActivo", "1"))
                .add(Restrictions.ge("Id", valor))
                .addOrder(Order.asc("SemFechaInicio"));
        if(criteria.list().size()>0){
            lista=criteria.list();
        }
        return lista;
    }

    @Override
    public AcSemestre getSemestre(Integer sem_id) {
        return (AcSemestre) this.getSession().get(AcSemestre.class, sem_id);
    }

    @Override
    public List<AcSemestre> listarSemestreSuperiores(Date Fecha_inicio) {
        List<AcSemestre> lista= new ArrayList<AcSemestre>();
        Criteria criteria=this.getSession().createCriteria(AcSemestre.class)
                .add(Restrictions.eq("SemAcademico", "1"))
                .add(Restrictions.eq("SemActivo", "1"))
                .add(Restrictions.ge("SemFechaInicio", Fecha_inicio))
                .addOrder(Order.asc("SemFechaInicio"));
        if(criteria.list().size()>0){
            lista=criteria.list();
        }
        return lista;
    }
}

