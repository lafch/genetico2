package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcCursoAperturado;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSAperturaCursosDAOImpl extends HibernateDaoSupport implements HSAperturaCursosDAO {

    @Override
    public void aperturarCurso(AcCursoAperturado cur) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate(cur);
    }

    @Override
    public List seleccionarCursosAperturados() throws DataAccessException {
        List lista = null;
        return lista;
    }

    @Override
    public List verificarCursoAperturado(int plancur_id, int sem_id) throws DataAccessException {
        return this.getSession().createCriteria(AcCursoAperturado.class).add(Expression.eq("Plancur.Id", plancur_id)).add(Expression.eq("Sem.Id", sem_id)).add(Expression.eq("CurapeActivo", "1")).add(Expression.eq("CurapeAperturado", "1")).list();
    }

    @Override
    public void eliminarCursoAperturado(String id) throws DataAccessException {
        String hqlUpdate = "update AcCursoAperturado set CurapeActivo = :activo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("activo", "0").setString("id", id).executeUpdate();
    }

    @Override
    public void actualizarCursoAperturado(AcCursoAperturado cur) throws DataAccessException {
        getHibernateTemplate().update(cur);
    }

    @Override
    public List seleccionarUnCursosAperturado(int curape_id) throws DataAccessException {
        return this.getSession().createCriteria(AcCursoAperturado.class).
                add(Expression.eq("Id", curape_id)).
                add(Expression.eq("CurapeActivo", "1")).
                createCriteria("AcSisEvaPersonalizados").
                add(Expression.eq("SisevaPerActivo", "1")).
                addOrder(Order.asc("SisevaPerOrden")).list();
    }

    @Override
    public List seleccionarAperturaporPlanCurso(int plancurid) throws DataAccessException {
        return this.getSession().createCriteria(AcCursoAperturado.class).add(Expression.eq("PlancurId", plancurid)).add(Expression.eq("CurapeActivo", "1")).list();
    }

    @Override
    public List seleccionarAperturaporEspecialidad(int esp_id, int sem_id) throws DataAccessException {
        return this.getSession().createCriteria(AcCursoAperturado.class).
                add(Restrictions.eq("CurapeActivo", "1")).
                add(Restrictions.eq("CurapeAperturado", "1")).
                add(Restrictions.eq("Sem.Id", sem_id)).
                createCriteria("Plancur").
                add(Restrictions.in("PlancurTipo", new String[] { "012001", "012002","012005","012006" })).
                addOrder(Order.asc("PlancurCiclo")).
                createCriteria("Cur").
                createCriteria("Esp").
                add(Restrictions.eq("Id", esp_id)).list();
    }
    
    @Override
    public AcCursoAperturado seleccionarCursoAperturadoID( int id_curso ) {
        return (AcCursoAperturado)this.getSession().createCriteria( AcCursoAperturado.class ).
                add( Restrictions.eq( "CurapeActivo", "1" ) ).
                add( Restrictions.eq( "Id", id_curso ) ).uniqueResult();
    }
}
