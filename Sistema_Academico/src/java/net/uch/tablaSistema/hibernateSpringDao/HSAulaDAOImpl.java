package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcHorarioDispAula;
import net.uch.mapping.AcHorarioDispDocente;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSAulaDAOImpl extends HibernateDaoSupport implements HSAulaDAO {

    @Override
    public void insertarAula(AcAula aula) throws DataAccessException {
        getHibernateTemplate().save(aula);
    }

    @Override
    public List seleccionarAula(String nombre, int pabellon, int especialidad) throws DataAccessException, java.sql.SQLException {
        Criteria c = this.getSession().createCriteria(AcAula.class).
                add(Restrictions.like("AulDes", "%" + nombre + "%")).
                add(Restrictions.eq("AulActivo", "1"));
        if (pabellon != 0) {
            c.add(Restrictions.eq("Pab.Id", pabellon));
        }
        if (especialidad != 0) {
            c.add(Restrictions.eq("Esp.Id", especialidad));
        }
        c.addOrder(Order.asc("Id"));
        return c.list();
    }

    @Override
    public void eliminarAula(String id) throws DataAccessException {
        String hqlUpdate = "update AcAula set AulActivo = :activo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("activo", "0").setString("id", id).executeUpdate();
    }

    @Override
    public void actualizarAula(AcAula aula)
            throws DataAccessException {
        getHibernateTemplate().update(aula);
    }

    @Override
    public List seleccionarAulaSeccion(int idSeccion) throws Exception {
        String sQuery;
        sQuery = " select au.* from ac_aula au \n"
                + " INNER JOIN ac_pabellon p on au.pab_id=p.pab_id \n"
                + " INNER JOIN ac_local l on p.loc_id=l.loc_id \n"
                + " where au.aul_activo=1 and l.loc_des like '%OLIVOS%' and au.aul_id not in( \n"
                + " select a.aul_id from ac_aula a \n"
                + " INNER JOIN ac_horario horario ON horario.aul_id=a.aul_id \n"
                + " INNER JOIN ac_seccion sec ON sec.sec_id=horario.sec_id \n"
                + " INNER JOIN ac_curso_aperturado ca ON ca.curape_Id=sec.curape_id \n"
                + " where a.aul_activo=1 and horario.hor_activo=1 and sec.sec_activo=1 and ca.curape_activo=1 and a.aul_tipo <> '098002' \n"
                + " and ca.sem_id = (select sem_id from ac_seccion seccion \n"
                + " INNER JOIN ac_curso_aperturado ca ON ca.curape_Id=seccion.curape_id \n"
                + " where seccion.sec_id=:v_sec_id and seccion.sec_activo=1 and ca.curape_activo=1) and a.aul_id not in (select aul_id from ac_horario hor \n"
                + " where hor.sec_id=:v_sec_id and hor.hor_activo=1)   \n"
                + " GROUP BY a.aul_id) and au.aul_tipo not in ('098002','098003');";

        return this.getSession().createSQLQuery(sQuery).addEntity(AcAula.class).setInteger("v_sec_id", idSeccion).list();

    }
    
    @Override
    public List listadoLaboratoriosDisponibles(int idHorario) throws Exception {
        String sQuery;
        sQuery = " call usp_trae_laboratorios_disponibles(:v_hor_id)";

        return this.getSession().createSQLQuery(sQuery).addEntity(AcAula.class).setInteger("v_hor_id", idHorario).list();

    }

    @Override
    public List<AcHorarioDispAula> seleccionarHorario(int aula_id) throws Exception {
        return this.getSession().createCriteria(AcHorarioDispAula.class).
                add(Restrictions.eq("horActivo", "1")).
                add(Restrictions.eq("acAula.Id", aula_id)).list();
    }

    @Override
    public void insertarActualizarHorarios(List<AcHorarioDispAula> horarias) {
        this.getHibernateTemplate().saveOrUpdateAll(horarias);
    }

    @Override
    public void eliminarHorario(int hor_id) throws Exception {
        String hqlUpdate = "update AcHorarioDispAula set horActivo = :newName where horId = :oldName";
        this.getSession().createQuery(hqlUpdate).setString("newName", "0").
                setString("oldName", "" + hor_id).
                executeUpdate();
    }

    @Override
    public void eliminarHorarios(List<Integer> hor_ids) {
        String hqlUpdate = "update AcHorarioDispAula "
                + "set horActivo = '0' "
                + "where horId in (:v_id)";

        this.getSession().createQuery(hqlUpdate).
                setParameterList("v_id", hor_ids).executeUpdate();
    }

    @Override
    public AcAula buscarAula(int aul_id) {
        return (AcAula) this.getSession().get(AcAula.class, aul_id);
    }

    @Override
    public List seleccionarAula(int pab_id) throws Exception {
        Criteria c = this.getSession().createCriteria(AcAula.class).
                add(Restrictions.eq("AulActivo", "1")).
                add(Restrictions.eq("Pab.Id", pab_id));
        // addOrder(Order.asc("aulDes"));

        return c.list();
    }

    @Override
    public List seleccionarAulaPorEspecialidad(int esp_id, int semestre_id, int tur_id) throws DataAccessException {

        String sQuery;
        sQuery = "    select ac_aula.* from ac_aula "
                + "   INNER JOIN ac_pabellon p on ac_aula.pab_id=p.pab_id \n"
                + "   INNER JOIN ac_local l on p.loc_id=l.loc_id \n"
                + "   where aul_activo=1 and l.loc_des like '%OLIVOS%'"
                + "   and esp_id=:v_esp_id and ac_aula.aul_id not in ( \n"
                + "   select aul_id from ac_horario hor"
                + "   INNER JOIN ac_turno_detalle td ON td.turdet_id=hor.turdet_id \n"
                + "   INNER JOIN ac_seccion sec ON sec.sec_id = hor.sec_id \n"
                + "   INNER JOIN ac_curso_aperturado curAper ON curAper.curape_Id = sec.curape_id\n"
                + "   INNER JOIN ac_plan_curso plaCur ON plaCur.plancur_Id= curAper.plancur_id\n"
                + "   INNER JOIN ac_curso cur ON cur.cur_id=plaCur.cur_id\n"
                + "   where sem_id = :v_sem_id and td.turdet_activo=1 and td.tur_id=:v_tur_id \n"
                + "   and hor.hor_activo=1 and sec.sec_activo=1 and curAper.curape_activo=1 \n"
                + "   and plaCur.plancur_activo=1 and cur.cur_activo=1 \n"
                + "   ) and ac_aula.aul_activo=1 and ac_aula.aul_tipo not in ('098002','098003') ";

        return this.getSession().createSQLQuery(sQuery).addEntity(AcAula.class).setInteger("v_esp_id", esp_id).setInteger("v_sem_id", semestre_id).setInteger("v_tur_id", tur_id).list();

        //return this.getSession().createCriteria(AcAlumno.class).add(Expression.eq("Esp.Id", id_esp)).add(Expression.eq("AluTipo", tipo)).add(Expression.eq("AluActivo", "1")).add(Restrictions.eq("SemId", sem_id)).list();
    }
}
