package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcDocente;
import net.uch.mapping.Sp_convalidacion_cursos;
import net.uch.mapping.AcSeccion;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSSeccionDAOImpl extends HibernateDaoSupport implements HSSeccionDAO {

    @Override
    public List seleccionarSecciones(int curape) throws DataAccessException {
        return this.getSession().createCriteria(AcSeccion.class).
                add(Restrictions.eq("SecActivo", "1")).
                add(Restrictions.eq("Curape.Id", curape)).list();
    }

    @Override
    public List obtenerSecciones(int plancur_id, int semestre_id) throws DataAccessException {
        return this.getSession().createCriteria(AcSeccion.class).
                add(Restrictions.eq("SecActivo", "1")).
                createCriteria("Curape").
                add(Restrictions.eq("CurapeActivo", "1")).
                add(Restrictions.eq("CurapeAperturado", "1")).
                add(Restrictions.eq("Sem.Id", semestre_id)).
                createCriteria("Plancur").
                add(Restrictions.eq("PlancurActivo", "1")).
                add(Restrictions.eq("Id", plancur_id)).list();
    }
    
    @Override
    public List obtenerSeccionesCurape(int curapeId, int semestre_id){
        return this.getSession().createCriteria(AcSeccion.class).
                add(Restrictions.eq("SecActivo", "1")).
                createCriteria("Curape").
                add(Restrictions.eq("CurapeActivo", "1")).
                add(Restrictions.eq("CurapeAperturado", "1")).
                add(Restrictions.eq("Id", curapeId)).
                add(Restrictions.eq("Sem.Id", semestre_id)).list();
    }

    @Override
    public List seleccionarSeccion(int sec_id) {
        return this.getSession().createCriteria(AcSeccion.class).add(Restrictions.eq("Id", sec_id)).add(Restrictions.eq("SecActivo", "1")).list();
    }
    
    @Override
    public AcSeccion buscarSeccionID( int secId ) throws DataAccessException {
        return (AcSeccion)this.getSession().createCriteria( AcSeccion.class ).
                add( Restrictions.eq( AcSeccion.PROP_SEC_ACTIVO, "1" ) ).
                add( Restrictions.eq( AcSeccion.PROP_ID, secId ) ).uniqueResult();
    }

    @Override
    public List listarSecciones(int esp_id, int sem_id) {
        List lista = this.getSession().createCriteria(AcSeccion.class, "AcSeccion").
                add(Restrictions.eq("AcSeccion.SecActivo", "1")).
                createCriteria("AcSeccion.Curape", "AcCursoAperturado").
                add(Restrictions.eq("AcCursoAperturado.Sem.Id", sem_id)).
                add(Restrictions.eq("AcCursoAperturado.CurapeAperturado", "1")).
                add(Restrictions.eq("AcCursoAperturado.CurapeActivo", "1")).
                createCriteria("AcCursoAperturado.Plancur", "AcPlanCurso").
                add(Restrictions.eq("AcPlanCurso.PlancurActivo", "1")).
                createCriteria("AcPlanCurso.Cur", "AcCurso").
                add(Restrictions.eq("AcCurso.CurActivo", "1")).
                add(Restrictions.eq("AcCurso.Esp.Id", esp_id)).
                setProjection(Projections.projectionList().add(Projections.groupProperty("AcSeccion.SecNombre"))).list();

        return lista;
    }
    
    @Override
    public List listarCursosporSeccion(String sec_nombre, int sem_id) {
        List lista = this.getSession().createCriteria(AcSeccion.class, "AcSeccion").
                add(Restrictions.eq("AcSeccion.SecActivo", "1")).
                add(Restrictions.eq("AcSeccion.SecNombre", sec_nombre)).
                createCriteria("AcSeccion.Curape", "AcCursoAperturado").
                add(Restrictions.eq("AcCursoAperturado.Sem.Id", sem_id)).
                add(Restrictions.eq("AcCursoAperturado.CurapeAperturado", "1")).
                add(Restrictions.eq("AcCursoAperturado.CurapeActivo", "1")).
                createCriteria("AcCursoAperturado.Plancur", "AcPlanCurso").
                add(Restrictions.eq("AcPlanCurso.PlancurActivo", "1")).
                createCriteria("AcPlanCurso.Cur", "AcCurso").
                add(Restrictions.eq("AcCurso.CurActivo", "1")).list();
        return lista;
    }
    
    @Override
    public List listarSeccionesAdional(int esp_id, int sem_id) {
        List lista = this.getSession().createCriteria(AcSeccion.class, "AcSeccion").
                add(Restrictions.eq("AcSeccion.SecActivo", "1")).
                createCriteria("AcSeccion.Curape", "AcCursoAperturado").
                add(Restrictions.eq("AcCursoAperturado.Sem.Id", sem_id)).
                add(Restrictions.eq("AcCursoAperturado.CurapeAperturado", "1")).
                add(Restrictions.eq("AcCursoAperturado.CurapeActivo", "1")).
                createCriteria("AcCursoAperturado.Plancur", "AcPlanCurso").
                add(Restrictions.eq("AcPlanCurso.PlancurActivo", "1")).
                add(Restrictions.eq("AcPlanCurso.PlancurTipo", "012004")).
                createCriteria("AcPlanCurso.Cur", "AcCurso").
                add(Restrictions.eq("AcCurso.CurActivo", "1")).
                add(Restrictions.eq("AcCurso.Esp.Id", esp_id)).
                setProjection(Projections.projectionList().add(Projections.groupProperty("AcSeccion.SecNombre"))).list();

        return lista;
    }
    
    @Override
    public List listarCursosporSeccionAdicional(String sec_nombre, int sem_id) {
        List lista = this.getSession().createCriteria(AcSeccion.class, "AcSeccion").
                add(Restrictions.eq("AcSeccion.SecActivo", "1")).
                add(Restrictions.eq("AcSeccion.SecNombre", sec_nombre)).
                createCriteria("AcSeccion.Curape", "AcCursoAperturado").
                add(Restrictions.eq("AcCursoAperturado.Sem.Id", sem_id)).
                add(Restrictions.eq("AcCursoAperturado.CurapeAperturado", "1")).
                add(Restrictions.eq("AcCursoAperturado.CurapeActivo", "1")).
                createCriteria("AcCursoAperturado.Plancur", "AcPlanCurso").
                add(Restrictions.eq("AcPlanCurso.PlancurActivo", "1")).
                add(Restrictions.eq("AcPlanCurso.PlancurTipo", "012004")).
                createCriteria("AcPlanCurso.Cur", "AcCurso").
                add(Restrictions.eq("AcCurso.CurActivo", "1")).list();
        return lista;
    }

    @Override
    public List<Sp_convalidacion_cursos> listaConvalidacion(int esp_id, int alu_id) {
        Query query = this.getSession().getNamedQuery("sp_convalidacion_cursos").
                setInteger("p_esp_id", esp_id).
                setInteger("p_alu_id", alu_id);

        return query.list();
    }
}
