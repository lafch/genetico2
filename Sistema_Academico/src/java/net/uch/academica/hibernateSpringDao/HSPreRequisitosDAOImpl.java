package net.uch.academica.hibernateSpringDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.uch.mapping.AcPlanCurso;
import net.uch.mapping.AcPreRequisitos;
import net.uch.mapping.TbCatalogo;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSPreRequisitosDAOImpl extends HibernateDaoSupport implements HSPreRequisitosDAO {

    @Override
    public void insertarPreRequisitos(AcPreRequisitos alu) throws DataAccessException {
        getHibernateTemplate().save(alu);
    }

    @Override
    public List seleccionarCiclo(String codigo, int valor) throws DataAccessException, java.sql.SQLException {
        return this.getSession().createCriteria(TbCatalogo.class).add(Expression.eq("CatCodigoGrupo", codigo)).add(Expression.not(Expression.eq("CatCodigoElemento", "000"))).add(Expression.eq("CatActivo", "1")).list();
    }

    @Override
    public List cursoDet(int plan) throws DataAccessException, java.sql.SQLException {
        return this.getSession().createCriteria(AcPlanCurso.class).add(Expression.eq("Plan.Id", plan)).addOrder(Order.asc("PlancurCiclo")).addOrder(Order.asc("Id")).add(Expression.eq("PlancurActivo", "1")).list();
    }

    @Override
    public List seleccionarCurso(int plan, String ciclo) throws DataAccessException, java.sql.SQLException {
        return this.getSession().createCriteria(AcPlanCurso.class).add(Expression.eq("Plan.Id", plan)).add(Expression.eq("PlancurCiclo", ciclo)).list();
    }

    @Override
    public void eliminarPreRequisitos(String id) throws DataAccessException {
        String hqlUpdate = "update AcPreRequisitos set PreReqActivo = :v_activo where Id = :v_id";
        this.getSession().createQuery(hqlUpdate).setString("v_activo", "0").setString("v_id", id).executeUpdate();
    }

    @Override
    public List seleccionarPreReq(int curso) throws DataAccessException, java.sql.SQLException {
        List lista;
        String hqlUpdate =
                " select p.Id,  p.Plancur.Id, p.Plancur.Cur.CurNombre, p.PlancurIdRequisito.Id, "
                + " p.PlancurIdRequisito.Cur.CurNombre, "
                + " (  "
                + " 	select c.CatDescripcionElemento from TbCatalogo c, AcPlanCurso x "
                + "     where  x.Id=p.PlancurIdRequisito.Id "
                + "     and concat(c.CatCodigoGrupo,c.CatCodigoElemento)=x.PlancurCiclo "
                + " )  "
                + " from AcPreRequisitos p where p.PreReqActivo = :activo "
                + " and p.Plancur.Id=:curso";
        lista = this.getSession().createQuery(hqlUpdate).setString("curso", "" + curso).setString("activo", "1").list();
        return lista;
    }

    @Override
    public List verificarRepitencia(int curso, int req) {
        List lista;
        String hqlUpdate =
                " select p.Id,  p.Plancur.Id ,p.PlancurIdRequisito "
                + " from AcPreRequisitos p where p.PreReqActivo=1 and p.Plancur.Id = :curso "
                + " and p.PlancurIdRequisito = :req ";
        lista = this.getSession().createQuery(hqlUpdate).setString("curso", "" + curso).setString("req", "" + req).list();
        return lista;
    }

    @Override
    public void actualizarPreRequisitos(AcPreRequisitos alu) throws DataAccessException {
        getHibernateTemplate().update(alu);
    }

    @Override
    public List<AcPlanCurso> retonarCursosAbiertos(List<Integer> aprobados, List<Integer> desaprobados) throws DataAccessException {
        List<AcPlanCurso> lista = new ArrayList<AcPlanCurso>();
        lista = this.getSession().createCriteria(AcPlanCurso.class).add(Restrictions.eq("PlancurActivo", "1")).list();
        List<AcPlanCurso> cursos_abiertos = new ArrayList<AcPlanCurso>();
        List<AcPlanCurso> cursos_finales = new ArrayList<AcPlanCurso>();
        List<AcPlanCurso> cursos_padres = new ArrayList<AcPlanCurso>();
        for (int i = 0; i < lista.size(); i++) {
            Set<AcPreRequisitos> prereq = new LinkedHashSet<AcPreRequisitos>();
            prereq = ((AcPlanCurso) lista.get(i)).getAcPreRequisitosByPlancurIdRequisito();
            List list_p = Collections.synchronizedList(new LinkedList(prereq));

            int contador = 0;
            for (int j = 0; j < list_p.size(); j++) {
                for (int k = 0; k < aprobados.size(); k++) {
                    if (("" + aprobados.get(k)).equals("" + (((AcPreRequisitos) list_p.get(j)).getPlancurIdRequisito().getId()))) {
                        contador++;
                    }
                }
                if (contador == list_p.size()) {
                    cursos_abiertos.add(((AcPreRequisitos) list_p.get(j)).getPlancur());
                    cursos_padres.add(((AcPreRequisitos) list_p.get(j)).getPlancurIdRequisito());
                }
            }
        }

        for (int p = 0; p < cursos_abiertos.size(); p++)//CURSOS ABIERTOS
        {
            int flag = 0;
            for (int q = 0; q < aprobados.size(); q++)//CURSOS APROBADOS
            {
                if (("" + ((AcPlanCurso) cursos_abiertos.get(p)).getId()).equals("" + (Integer) aprobados.get(q))) {
                    flag++;
                    break;
                }
            }
            for (int r = 0; r < desaprobados.size(); r++)//CURSOS DESAPROBADOS
            {
                if (("" + ((AcPlanCurso) cursos_abiertos.get(p)).getId()).equals("" + (Integer) desaprobados.get(r))) {
                    flag++;
                    break;
                }
            }
            if (flag < 1) {
                cursos_finales.add(cursos_abiertos.get(p));
            }
        }

        /*******************************************/
        System.out.println("CURSOS ABIERTOS ANTES DEL FILTRO");
        System.out.println("================================");
        if (cursos_finales.size() > 0) {
            for (int i = 0; i < cursos_finales.size(); i++) {
                System.out.println("PlancurID_" + (i + 1) + " = " + ((AcPlanCurso) cursos_finales.get(i)).getId());
                System.out.println("Curso_" + (i + 1) + " = " + ((AcPlanCurso) cursos_finales.get(i)).getCur().getCurNombre());
            }
        } else {
            System.out.println("No tiene cursos abiertos antes del filtro");
        }
        System.out.println(" ");
        /*******************************************/
        return cursos_finales;
    }

    @Override
    public List<AcPlanCurso> retonarCursosLibres(List<Integer> list, int esp) throws DataAccessException {
        List<AcPlanCurso> cursos_libres = new ArrayList<AcPlanCurso>();
        List<AcPlanCurso> lista = new ArrayList<AcPlanCurso>();

        //se obtiene los plancursos de la especialidad que llega como parametro
        lista = this.getSession().createCriteria(AcPlanCurso.class).add(Restrictions.eq("PlancurActivo", "1")).createCriteria("Plan").add(Restrictions.eq("Esp.Id", esp)).list();

        for (int i = 0; i < lista.size(); i++)//se recorre los plancursos A B C D E F G
        {
            int flag = 0;
            if (((AcPlanCurso) lista.get(i)).getAcPreRequisitosByPlancurIdRequisito().size() == 0)//A C E F
            {
                for (int k = 0; k < list.size(); k++)//A D E F G
                {
                    if (("" + ((AcPlanCurso) lista.get(i)).getId()).equals("" + (Integer) list.get(k))) {
                        flag = 1;
                        break;
                    }
                }

                if (flag == 0) {
                    cursos_libres.add(((AcPlanCurso) lista.get(i)));
                }
            }
        }
        /***********************************************/
        System.out.println("CURSOS LIBRES ANTES DEL FILTRO");
        System.out.println("==============================");
        if (cursos_libres.size() > 0) {
            for (int i = 0; i < cursos_libres.size(); i++) {
                System.out.println("PlancurID_" + (i + 1) + " = " + ((AcPlanCurso) cursos_libres.get(i)).getId());
                System.out.println("Curso_" + (i + 1) + " = " + ((AcPlanCurso) cursos_libres.get(i)).getCur().getCurNombre());
            }
        } else {
            System.out.println("No tiene cursos libres antes del filtro");
        }
        System.out.println(" ");
        /**********************************************/
        return cursos_libres;
    }
}
