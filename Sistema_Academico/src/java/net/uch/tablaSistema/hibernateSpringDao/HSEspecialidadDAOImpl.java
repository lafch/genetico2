package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcEspecialidad;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSEspecialidadDAOImpl extends HibernateDaoSupport implements HSEspecialidadDAO {

    @Override
    public void insertarEspecialidad(AcEspecialidad esp) throws DataAccessException {
        getHibernateTemplate().save(esp);
    }

    @Override
    public List seleccionarEspecialidad(String codigo, String nombre, int facultad) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AcEspecialidad.class);
        if (!codigo.equals("")) {
            criteria.add(Expression.eq("EspCodigo", codigo));
        }
        criteria.add(Expression.like("EspNombre", "%" + nombre + "%"));
        if (facultad != 0) {
            criteria.add(Expression.eq("Fac.Id", facultad));
        }
        criteria.add(Expression.eq("EspActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public List seleccionarEspecialidad(int fac_id) {
        List lista = this.getSession().createCriteria(AcEspecialidad.class).add(Expression.eq("Fac.Id", fac_id)).add(Expression.eq("EspActivo", "1")).list();
        return lista;
    }
    
    @Override
    public List seleccionarEspecialidadInformes(int esp_id) {
        List lista = this.getSession().createCriteria(AcEspecialidad.class).add(Expression.eq("Id", esp_id)).add(Expression.eq("EspActivo", "1")).list();
        return lista;
    }

    @Override
    public List seleccionarUnaEspecialidad(int esp_id) throws DataAccessException, java.sql.SQLException {
        return this.getSession().createCriteria(AcEspecialidad.class).add(Expression.eq("Id", esp_id)).add(Expression.eq("EspActivo", "1")).list();
    }

    @Override
    public void eliminarEspecialidad(String id) throws DataAccessException {
        String hqlUpdate = "update AcEspecialidad set EspActivo = :newName where Id = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", id).executeUpdate();
    }

    @Override
    public void actualizarEspecialidad(AcEspecialidad especialidad) throws DataAccessException {
        getHibernateTemplate().update(especialidad);
    }

    @Override
    public List seleccionarEspecialidad() {
        return this.getSession().createCriteria(AcEspecialidad.class).add(Restrictions.eq("EspActivo", "1")).list();
    }
}
