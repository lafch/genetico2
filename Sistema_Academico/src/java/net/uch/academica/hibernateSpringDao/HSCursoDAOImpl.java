package net.uch.academica.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.AcCurso;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSCursoDAOImpl extends HibernateDaoSupport implements HSCursoDAO {

    @Override
    public void insertarCurso(AcCurso cur) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate(cur);
    }

    @Override
    public List seleccionarCurso(String codigo, String nombre, int facultad, int especialidad) throws DataAccessException, java.sql.SQLException {
        String facuS;
        String espeS;
        List lista;
        if (facultad == 0) {
            facuS = "";
        } else {
            facuS = "" + facultad;
        }

        if (especialidad == 0) {
            espeS = "";
        } else {
            espeS = "" + especialidad;
        }

        String hqlUpdate = " select curso.Id,curso.CurNombre,curso.CurCodigo, "
                + " curso.Esp.Id,curso.Esp.EspNombre, "
                + " curso.Esp.Fac.Id,curso.Esp.Fac.FacNombre, "
                + " curso.CurActivo, curso.CurHorLab,CurHorTeo "
                + " from AcCurso as curso "
                + " where curso.CurCodigo like :codigo "
                + " and curso.CurNombre like :nombre  "
                + " and curso.Esp.Fac.Id like :facultad  "
                + " and curso.Esp.Id like :especialidad  "
                + " and curso.CurActivo = :activo ";
        lista = this.getSession().createQuery(hqlUpdate).setString("codigo", "%" + codigo + "%").setString("nombre", "%" + nombre + "%").setString("especialidad", "%" + espeS + "%").setString("facultad", "%" + facuS + "%").setString("activo", "1").list();
        return lista;
    }

    @Override
    public List seleccionarCurso(int especialidad) throws DataAccessException, java.sql.SQLException {
        DetachedCriteria criteria = DetachedCriteria.forClass(AcCurso.class);
        if (especialidad != 0) {
            criteria.add(Expression.eq("Esp.Id", especialidad));
        }
        criteria.add(Expression.eq("CurActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }

    @Override
    public AcCurso seleccionarCursoID(int id_curso) {
        return (AcCurso) this.getSession().createCriteria(AcCurso.class).
                add(Restrictions.eq("CurActivo", "1")).
                add(Restrictions.eq("Id", id_curso)).uniqueResult();
    }

    @Override
    public void eliminarCurso(String id) throws DataAccessException {
        String hqlUpdate = "update AcCurso set CurActivo = :newName where Id = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").setString("oldName", id).executeUpdate();
    }

    @Override
    public void actualizarCurso(AcCurso cur) throws DataAccessException {
        getHibernateTemplate().update(cur);
    }

    @Override
    public List listadoCurso(int plan, String ciclo) {
        int iSize;
        AcCurso cur;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<AcCurso> lista = new ArrayList<AcCurso>();
        String hqlQuery = " SELECT c.* from ac_plan_curso pc \n"
                        + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id\n"
                        + " where pc.plancur_ciclo=:v_ciclo and plan_id=:v_plan and c.cur_activo=1 and pc.plancur_activo='1'";

        sqlQuery = this.getSession().createSQLQuery(hqlQuery);
        sqlQuery.setInteger("v_plan", plan);
        sqlQuery.setString("v_ciclo", ciclo);
        lstAux = sqlQuery.list();
        iSize = lstAux.size();
        for (int i = 0; i < iSize; i++) {
            aobjFila = (Object[]) lstAux.get(i);
            cur = new AcCurso();
            cur.setId(Integer.parseInt(aobjFila[0].toString()));
            cur.setCurCodigo(aobjFila[1].toString());
            cur.setCurNombre(aobjFila[2].toString());
            lista.add(cur);
        }
        return lista;
    }
    
    @Override
    public List listadoCursoAsignado(int plan, String ciclo) {
        int iSize;
        AcCurso cur;
        SQLQuery sqlQuery;
        Object[] aobjFila;
        List lstAux;
        List<AcCurso> lista = new ArrayList<AcCurso>();
        String hqlQuery = " SELECT c.* from ac_plan_curso pc \n"
                        + " INNER JOIN ac_curso c ON c.cur_id=pc.cur_id\n"
                        + " INNER JOIN ac_curso_docente cdoc ON cdoc.curso_id=c.cur_id\n"
                        + " where pc.plancur_ciclo=:v_ciclo and plan_id=:v_plan and c.cur_activo=1 and cdoc.activo='1' and pc.plancur_activo=1 "
                        + " GROUP BY c.cur_id";

        sqlQuery = this.getSession().createSQLQuery(hqlQuery);
        sqlQuery.setInteger("v_plan", plan);
        sqlQuery.setString("v_ciclo", ciclo);
        lstAux = sqlQuery.list();
        iSize = lstAux.size();
        for (int i = 0; i < iSize; i++) {
            aobjFila = (Object[]) lstAux.get(i);
            cur = new AcCurso();
            cur.setId(Integer.parseInt(aobjFila[0].toString()));
            cur.setCurCodigo(aobjFila[1].toString());
            cur.setCurNombre(aobjFila[2].toString());
            lista.add(cur);
        }
        return lista;
    }
}
