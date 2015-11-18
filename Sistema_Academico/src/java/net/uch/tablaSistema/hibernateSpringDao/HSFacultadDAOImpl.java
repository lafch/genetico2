package net.uch.tablaSistema.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.AcFacultad;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSFacultadDAOImpl extends HibernateDaoSupport implements HSFacultadDAO {

    @Override
    public void insertarFacultad(AcFacultad fac)
            throws DataAccessException {
        getHibernateTemplate().save(fac);
    }

    @Override
    public List seleccionarFacultad(String codigo, String nombre) {
//        System.out.println("empezarafacu");
        DetachedCriteria criteria = DetachedCriteria.forClass(AcFacultad.class);
        if (!codigo.equals("")) {
            criteria.add(Expression.eq("FacCodigo", codigo));
        }
        criteria.add(Expression.like("FacNombre", "%" + nombre + "%"));
        criteria.add(Expression.eq("FacActivo", "1"));
        //criteria.setFetchMode("AcEspecialidads", FetchMode.JOIN);
        //criteria.setFetchMode("AcPlancurriculars", FetchMode.JOIN).setFetchMode("AcPlanCursos", FetchMode.JOIN).setFetchMode("AcCursoAperturados", FetchMode.JOIN).setFetchMode("AcSeccions", FetchMode.JOIN).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        List lista = getHibernateTemplate().findByCriteria(criteria);
//        System.out.println("terminarafacu");
        return lista;
        //return this.getSession().createCriteria(AcFacultad.class).add(Expression.eq("FacCodigo", codigo)).add(Expression.like("FacNombre", "%"+nombre+"%")).add(Expression.eq("FacActivo", "1")).setFetchMode("AcEspecialidads", FetchMode.JOIN).list();
    }
    
    @Override
    public List seleccionarFacultadId(int idFacultad) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AcFacultad.class);
        criteria.add(Expression.eq("Id",idFacultad));
        criteria.add(Expression.eq("FacActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }
    
     @Override
    public List seleccionarFacultadId() {
         return this.getSession().createCriteria(AcFacultad.class).add(Restrictions.eq("FacActivo", "1")).list();
    }

    @Override
    public void eliminarFacultad(String id)
            throws DataAccessException {
        String hqlUpdate = "update AcFacultad set FacActivo = :newName where Id = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", id).executeUpdate();
    }

    @Override
    public void actualizarFacultad(AcFacultad facultad)
            throws DataAccessException {
        getHibernateTemplate().update(facultad);
    }

    @Override
    public List<AcFacultad> getListarTodas() {
        List<AcFacultad> lista= new ArrayList<AcFacultad>();
        Criteria criteria=this.getSession().createCriteria(AcFacultad.class)
                .add(Restrictions.eq("FacActivo", "1"))
                .addOrder(Order.asc("FacNombre"));
        if(criteria.list().size()>0){
            lista=criteria.list();
        }
        return lista;
    }
}

