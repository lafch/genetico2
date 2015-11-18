/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.administrativa.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdEstructuraPagos;
import net.uch.mapping.AdPago;
import net.uch.mapping.AdProrroga;
import net.uch.util.ObjUpdate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSEdicionEstructPagoDAOImpl extends HibernateDaoSupport implements HSEdicionEstructPagoDAO {

    @Override
    public void insertarAlumnoTarifa(AdAlumnoTarifa alumnoTarifa) {
        this.getHibernateTemplate().save(alumnoTarifa);
    }

    @Override
    public void insertarProrroga(AdProrroga prorroga) {
        this.getHibernateTemplate().save(prorroga);
    }

    @Override
    public List<AcAlumno> listaAlumnoEstructura(String code, String paterno, String materno, String nombres, int semestre)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria c = this.getSession().createCriteria(AcAlumno.class, "AcAlumno").
                createCriteria("AcAlumno.AdAlumnoTarifas", "AdAlumnoTarifa").
                createCriteria("AdAlumnoTarifa.Estpagdet", "AdEstructuraPagosDetalle").
                createCriteria("AdEstructuraPagosDetalle.Estpag", "AdEstructuraPagos").
                add(Restrictions.eq("AcAlumno.AluActivo", "1")).
                add(Restrictions.eq("AdAlumnoTarifa.AlutarActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagosDetalle.EstpagdetActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagos.EstpagActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagos.Sem.id", semestre));

        if (code.trim().length() != 0) {
            c.add(Restrictions.like("AcAlumno.AluCodigo", code + "%"));
        }
        if (paterno.trim().length() != 0) {
            c.add(Restrictions.like("AcAlumno.AluAppaterno", paterno + "%"));
        }
        if (materno.trim().length() != 0) {
            c.add(Restrictions.like("AcAlumno.AluApmaterno", materno + "%"));
        }
        if (nombres.trim().length() != 0) {
            c.add(Restrictions.like("AcAlumno.AluNombres", nombres + "%"));
        }

        c.addOrder(Order.asc("AcAlumno.AluAppaterno")).
                addOrder(Order.asc("AcAlumno.AluApmaterno"));
        return c.list();
    }

    @Override
    public List listaTarifasAlumno(int semestre, int alu_id)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria c = this.getSession().createCriteria(AdAlumnoTarifa.class, "AdAlumnoTarifa").
                createCriteria("AdAlumnoTarifa.Estpagdet", "AdEstructuraPagosDetalle").
                createCriteria("AdEstructuraPagosDetalle.Estpag", "AdEstructuraPagos").
                add(Restrictions.eq("AdAlumnoTarifa.AlutarActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagosDetalle.EstpagdetActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagos.EstpagActivo", "1")).
                add(Restrictions.eq("AdAlumnoTarifa.Alu.id", alu_id)).
                add(Restrictions.eq("AdEstructuraPagos.Sem.id", semestre)).
                addOrder(Order.asc("AdAlumnoTarifa.Estpagdet.id"));

        return c.list();
    }

    @Override
    public List listaAlumnoActivo(String dato)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException, OutOfMemoryError {

        Criteria c = this.getSession().createCriteria(AcAlumno.class).
                add(Restrictions.eq("AluActivo", "1")).
                add(Restrictions.eq("AluTipo", "009001")).
                add(Restrictions.or(Restrictions.like("AluAppaterno", dato + "%"),
                Restrictions.or(Restrictions.like("AluApmaterno", dato + "%"),
                Restrictions.or(Restrictions.like("AluCodigo", dato + "%"),
                Restrictions.like("AluNombres", dato + "%")))));

        return c.list();
     
       /*//List<AcAlumno> lista;
        String hqlSelect="from AcAlumno where concat(AluAppaterno,' ',AluApmaterno) like concat('%',:v_dato,'%') and AluActivo= :v_activo";
        List<AcAlumno> lista=this.getSession().createQuery(hqlSelect).setString("v_dato", dato).setString("v_activo", "1").list();
        return lista;*/
    }

    @Override
    public List<AdEstructuraPagos> listaEstructurasPago(int sem, int esp, int semIngreso)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {
        Criteria c = this.getSession().createCriteria(AdEstructuraPagos.class).
                add(Restrictions.eq("EstpagActivo", "1")).
                /*add(Restrictions.eq("EstpagoPublicado", "1")).*/
                add(Restrictions.eq("Sem.id", sem)).
                add(Restrictions.eq("SemIn.id", semIngreso)).
                add(Restrictions.eq("Esp.id", esp));
        return c.list();
    }

    @Override
    public AdEstructuraPagos estructuraPagoPlantilla(int estpagId)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria c = this.getSession().createCriteria(AdEstructuraPagos.class).
                add(Restrictions.eq("EstpagActivo", "1")).
                add(Restrictions.eq("Id", estpagId));

        return (AdEstructuraPagos) c.uniqueResult();
    }

    @Override
    public List listaEstructurasExistentes(int sem, int esp, int semIngreso)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria c = this.getSession().createCriteria(AdEstructuraPagos.class, "AdEstructuraPagos").
                createCriteria("AdEstructuraPagos.AdEstructuraPagosDetalles", "AdEstructuraPagosDetalle").
                add(Restrictions.eq("AdEstructuraPagos.EstpagActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagosDetalle.EstpagdetActivo", "1")).
                /*add(Restrictions.eq("AdEstructuraPagos.EstpagoPublicado", "1")).*/
                add(Restrictions.eq("AdEstructuraPagos.Sem.id", sem)).
                add(Restrictions.eq("AdEstructuraPagos.Esp.id", esp)).
                add(Restrictions.eq("AdEstructuraPagos.SemIn.id", semIngreso));

        return c.list();
    }

    @Override
    public List listaAlumnoTarifaExistentes(int aluId, int sem, int esp)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        Criteria c = this.getSession().createCriteria(AdAlumnoTarifa.class, "AdAlumnoTarifa").
                createCriteria("AdAlumnoTarifa.Estpagdet", "AdEstructuraPagosDetalle").
                createCriteria("AdEstructuraPagosDetalle.Estpag", "AdEstructuraPagos").
                add(Restrictions.eq("AdAlumnoTarifa.AlutarActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagosDetalle.EstpagdetActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagos.EstpagActivo", "1")).
                add(Restrictions.eq("AdAlumnoTarifa.Alu.id", aluId)).
                add(Restrictions.eq("AdEstructuraPagos.Sem.id", sem)).
                add(Restrictions.eq("AdEstructuraPagos.Esp.id", esp));

        return c.list();
    }

    @Override
    public void actualizarEstadoPago(int alutarId, String estado)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        String hqlUpdate = "update AdAlumnoTarifa set AlutarEstado = :v_estado where Id = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setString("v_estado", estado).
                setInteger("v_id", alutarId).executeUpdate();
    }

    @Override
    public List listaPagosAlumno(int alutarId, int caso) {
        Criteria c = this.getSession().createCriteria(AdPago.class, "AdPago").
                createCriteria("AdPago.Compag", "AdComprobantePago").
                add(Restrictions.eq("AdPago.PagActivo", "1")).
                add(Restrictions.eq("AdComprobantePago.CompagActivo", "1")).
                add(Restrictions.eq("AdPago.AlutarId", alutarId));

        if (caso == 0) {
            c.add(Restrictions.eq("AdComprobantePago.CompagTipo", "037001"));
        }

        return c.list();
    }

    @Override
    public AdAlumnoTarifa montoAlumnoTarifa(int alutarID) {
        Criteria c = this.getSession().createCriteria(AdAlumnoTarifa.class).
                add(Restrictions.eq("AlutarActivo", "1")).
                add(Restrictions.eq("Id", alutarID));

        return (AdAlumnoTarifa) c.uniqueResult();
    }

    @Override
    public void actualizarDatosAlumnoTarifa(int alutarID, float monto, Date fecha_pago, Date fecha_prorroga) {
        String hqlUpdate = "update AdAlumnoTarifa set AlutarMonto = :v_monto,"
                + " AlutarFechaPago = :v_pago,"
                + " AlutarFechaProrroga = :v_prorroga"
                + " where Id = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setFloat("v_monto", monto).
                setDate("v_pago", fecha_pago).
                setDate("v_prorroga", fecha_prorroga).
                setInteger("v_id", alutarID).executeUpdate();
    }
    
    @Override
    public void eliminarPago(int alutarID) {
        String hqlUpdate = "update AdAlumnoTarifa set AlutarActivo = 0"
                + " where Id = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setInteger("v_id", alutarID).executeUpdate();
    }

    @Override
    public void eliminarEstructurasPagoAlumnoTarifa(List<Integer> alutarIds)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        String hqlUpdate = "update AdAlumnoTarifa set AlutarActivo = :v_activo where Id = :v_id";

        for (int i = 0; i < alutarIds.size(); i++) {
            Integer alutarId = alutarIds.get(i);
            this.getSession().createQuery(hqlUpdate).
                    setString("v_activo", "0").
                    setInteger("v_id", alutarId).executeUpdate();
        }
    }

    @Override
    public List listaProrrogas(int alutarID) {
        Criteria c = this.getSession().createCriteria(AdProrroga.class).
                add(Restrictions.eq("proActivo", "1")).
                add(Restrictions.eq("adAlumnoTarifa.id", alutarID)).
                addOrder(Order.asc("proId"));

        return c.list();
    }

    @Override
    public void eliminarProrroga(int proId) {
        String hqlUpdate = "update AdProrroga set proActivo = :v_activo where proId = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setString("v_activo", "0").
                setInteger("v_id", proId).executeUpdate();
    }

    @Override
    public void actualizarNumero(int proId, String num) {
        String hqlUpdate = "update AdProrroga set proNumero = :v_numero where proId = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setString("v_numero", num).
                setInteger("v_id", proId).executeUpdate();
    }

    @Override
    public int actualizarEstructurasPagoBloque(int sem_des, int esp, List<ObjUpdate> restricciones, List<ObjUpdate> valores) {
        String queryString = "update ad_alumno_tarifa "
                + "inner join ac_alumno "
                + "on ac_alumno.alu_id = ad_alumno_tarifa.alu_id "
                + "inner join ad_estructura_pagos_detalle "
                + "on ad_estructura_pagos_detalle.estpagdet_id = ad_alumno_tarifa.estpagdet_id "
                + "inner join ad_estructura_pagos "
                + "on ad_estructura_pagos.estpag_id = ad_estructura_pagos_detalle.estpag_id "
                + "set ";
        for (int i = 0; i < valores.size(); i++) {
            ObjUpdate o = valores.get(i);
            queryString = queryString.concat(o.getKey() + " = " + o.getValue());
            if (i != valores.size() - 1) {
                queryString = queryString.concat(", ");
            } else {
                queryString = queryString.concat(" ");
            }
        }

        queryString = queryString + "where ad_alumno_tarifa.alutar_activo='1' "
                + "and ac_alumno.alu_activo='1' "
                + "and ad_estructura_pagos_detalle.estpagdet_activo='1' "
                + "and ad_estructura_pagos.estpag_activo='1' "
                + "and ad_alumno_tarifa.alutar_estado <> 2 "
                + "and ad_estructura_pagos.sem_id = :semId "
                + "and ac_alumno.esp_id = :espId ";
        for (int i = 0; i < restricciones.size(); i++) {
            ObjUpdate o = restricciones.get(i);
            queryString = queryString.concat("and " + o.getKey() + " = " + o.getValue() + " ");
        }

        Query q = this.getSession().createSQLQuery(queryString).
                setInteger("semId", sem_des).setInteger("espId", esp);
        return q.executeUpdate();
    }
}
