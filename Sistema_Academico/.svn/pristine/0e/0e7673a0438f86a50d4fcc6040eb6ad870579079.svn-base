package net.uch.administrativa.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.AdConceptoPago;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSConceptoPagoDAOImpl extends HibernateDaoSupport implements HSConceptoPagoDAO {

    @Override
    public void insertarConceptoPago(AdConceptoPago conPago) throws DataAccessException {
        getHibernateTemplate().save(conPago);
    }

    @Override
    public List seleccionarConceptoPago(String nombre, String codigo, String tipo) throws DataAccessException, java.sql.SQLException {
        if (tipo.equalsIgnoreCase("0")) {
            tipo = "";
        }
        List lista;
        String hqlUpdate = "select CP.Id,CP.ConpagCodigo,CP.ConpagDescripcion, "
                + " CP.ConpagMonto,CP.ConpagTipo,CP.ConpagCreacion, "
                + " CP.ConpagActivo,C.CatDescripcionElemento "
                + " from AdConceptoPago CP, TbCatalogo C "
                + " where CP.ConpagDescripcion like :nombre "
                + " and CP.ConpagCodigo like :codigo "
                + " and CP.ConpagTipo like :tipo "
                + " and concat(C.CatCodigoGrupo,C.CatCodigoElemento)=CP.ConpagTipo "
                + " and CP.ConpagActivo=:activo ";
        lista = this.getSession().createQuery(hqlUpdate).setString("nombre", "%" + nombre + "%").setString("codigo", "%" + codigo + "%").setString("tipo", "%" + tipo + "%").setString("activo", "1").list();
        return lista;

    }

    @Override
    public List seleccionarUnaConceptoPago(int conPag_id) throws DataAccessException, java.sql.SQLException {
        DetachedCriteria criteria = DetachedCriteria.forClass(AdConceptoPago.class);
        criteria.add(Expression.eq("Id", conPag_id));
        criteria.add(Expression.eq("ConpagActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public List seleccionarConceptoPago() {
        DetachedCriteria criteria = DetachedCriteria.forClass(AdConceptoPago.class);
        criteria.add(Expression.eq("ConpagActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public void eliminarConceptoPago(String id) throws DataAccessException {
        String hqlUpdate = "update AdConceptoPago set ConpagActivo = :newName where Id = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", id).executeUpdate();
    }

    @Override
    public void actualizarConceptoPago(AdConceptoPago conPago) throws DataAccessException {
        getHibernateTemplate().update(conPago);
    }

    @Override
    public List seleccionarConceptoPagoGeneral() {
        DetachedCriteria criteria = DetachedCriteria.forClass(AdConceptoPago.class);
        criteria.add(Expression.eq("ConpagTipo", "007002"));
        criteria.add(Expression.eq("ConpagActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public List seleccionarConceptoCursoLibre(String descripcion) {
        return this.getSession().createCriteria(AdConceptoPago.class).add(Restrictions.eq("ConpagActivo", "1")).add(Restrictions.eq("ConpagTipo", "007003")).add(Restrictions.like("ConpagDescripcion", "%" + descripcion + "%")).list();
    }

    @Override
    public List<AdConceptoPago> getListarConceptoRubro(String conpagRubro) {
        List<AdConceptoPago> lista= new ArrayList<AdConceptoPago>();
        lista=this.getSession().createCriteria(AdConceptoPago.class)
                .add(Restrictions.eq("ConpagRubro", conpagRubro))
                .add(Restrictions.eq("ConpagActivo", "1"))
                .addOrder(Order.asc("ConpagDescripcion")).list();
        return lista;
    }
}
