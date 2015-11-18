package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcPlanCurso;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSPlanCurricularCursoDAOImpl extends HibernateDaoSupport implements HSPlanCurricularCursoDAO {

    @Override
    public void insertarPlanCurricularCurso(AcPlanCurso pc) throws DataAccessException {
        getHibernateTemplate().save(pc);
    }

    @Override
    public List seleccionarPlanCurricularCurso(int plan) throws DataAccessException, java.sql.SQLException {
        String hql = "select pcc.Id,pcc.Plan.Id,cur.CurNombre, pcc.PlancurCiclo, cat.CatDescripcionElemento,pcc.PlancurCredito, "
                + "( "
                + " select CatDescripcionElemento from TbCatalogo "
                + " where concat(CatCodigoGrupo,CatCodigoElemento)=pcc.PlancurTipo "
                + " ) ,pcc.PlancurCodigo "
                + "from AcPlanCurso pcc, AcCurso cur, TbCatalogo cat "
                + "where cur.Id =pcc.Cur.Id "
                + "and pcc.Plan.Id = :plan "
                + "and pcc.PlancurCiclo = concat(cat.CatCodigoGrupo,cat.CatCodigoElemento) "
                + "and pcc.PlancurActivo= :activo order by pcc.Id asc ";
        List lista;
        lista = this.getSession().createQuery(hql).setString("plan", "" + plan).setString("activo", "1").list();
        return lista;
    }

    @Override
    public List verificarRepitencia(int plan, int curso, String ciclo) {
        List lista;
        String hql = "select pcc.Id "
                + "from "
                + "AcPlanCurso pcc "
                + "where pcc.Plan.Id = :plan "
                + "and pcc.Cur= :curso "
                + "and pcc.PlancurCiclo= :ciclo "
                + "and pcc.PlancurActivo= :activo";
        lista = this.getSession().createQuery(hql).setString("plan", "" + plan).setString("curso", "" + curso).setString("ciclo", ciclo).setString("activo", "1").list();
        return lista;
    }

    @Override
    public void eliminarPlanCurricularCurso(String id) throws DataAccessException {
        String hqlUpdate = "update AcPlanCurso set PlancurActivo = :activo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("activo", "0").setString("id", id).executeUpdate();
    }

    @Override
    public void actualizarPlanCurricularCurso(AcPlanCurso pc) throws DataAccessException {
        getHibernateTemplate().update(pc);
    }

    @Override
    public List seleccionarPlancurricularCurso(String id) throws DataAccessException {
        return this.getSession().createCriteria(AcPlanCurso.class).add(Expression.eq("Cur.Id", id)).add(Expression.eq("PlancurActivo", "1")).list();
    }

    @Override
    public List seleccionarCursos(String plan, String ciclo) throws DataAccessException {
        //  System.out.println("CICLO " + ciclo);
        //return this.getSession().createCriteria(AcPlanCurso.class).add(Expression.eq("Plan.Id", Integer.parseInt(plan))).add(Expression.eq("PlancurCiclo", ciclo)).add(Expression.eq("PlancurActivo", "1")).list();
        return this.getSession().createCriteria(AcPlanCurso.class).add(Expression.eq("Plan.Id", Integer.parseInt(plan))).add(Expression.eq("PlancurCiclo", ciclo)).add(Expression.eq("PlancurActivo", "1")).add(Restrictions.in("PlancurTipo", new String[] { "012001", "012002" })).list();
    }

    @Override
    public List seleccionarPlanCurricularCurso(int plan, String ciclo_id) throws DataAccessException, java.sql.SQLException {
        String hql = "select pcc.Id,pcc.Plan.Id,cur.CurNombre, pcc.PlancurCiclo, cat.CatDescripcionElemento,pcc.PlancurCredito, "
                + "( "
                + " select CatDescripcionElemento from TbCatalogo "
                + " where concat(CatCodigoGrupo,CatCodigoElemento)=pcc.PlancurTipo "
                + " ) "
                + "from AcPlanCurso pcc, AcCurso cur, TbCatalogo cat "
                + "where cur.Id =pcc.Cur.Id "
                + "and pcc.Plan.Id = :plan "
                + "and pcc.PlancurCiclo = :ciclo "
                + "and pcc.PlancurCiclo = concat(cat.CatCodigoGrupo,cat.CatCodigoElemento) "
                + "and pcc.PlancurActivo= :activo order by pcc.Id asc ";
        return this.getSession().createQuery(hql).setString("plan", "" + plan).setString("activo", "1").setString("ciclo", "" + ciclo_id).list();
    }
}
