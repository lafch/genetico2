package net.uch.administrativa.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdSaldo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.criterion.Order;

public class HSAlumnoTarifaDAOImpl extends HibernateDaoSupport implements HSAlumnoTarifaDAO {

    @Override
    public void insertarAlumno(AdAlumnoTarifa alu) throws DataAccessException {
        getHibernateTemplate().save(alu);
    }

    @Override
    public void insertarTotal(List<AdAlumnoTarifa> listaT) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdateAll(listaT);
    }

    @Override
    public List seleccionarAlumnoTarifa(String codAlu, String idEstado, int idEsp, int idFac, int idConPag, String apPaterno, String apMaterno, int sem_id) throws DataAccessException {
        Criteria criteria = this.getSession().createCriteria(AdAlumnoTarifa.class, "AdAlumnoTarifa").
                createCriteria("AdAlumnoTarifa.Estpagdet", "AdEstructuraPagosDetalle").
                createCriteria("AdEstructuraPagosDetalle.Estpag", "AdEstructuraPagos").
                createCriteria("AdAlumnoTarifa.Alu", "Alu").
                add(Restrictions.eq("AdEstructuraPagosDetalle.EstpagdetActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagos.EstpagActivo", "1")).
                add(Restrictions.eq("AdAlumnoTarifa.AlutarActivo", "1")).
                addOrder(Order.asc("Alu.Id")).
                addOrder(Order.asc("AdAlumnoTarifa.AlutarFechaPago"));


        if (apPaterno.trim().length() != 0) {
            criteria.add(Restrictions.like("Alu.AluAppaterno", "%" + apPaterno + "%"));
        }
        if (apMaterno.trim().length() != 0) {
            criteria.add(Restrictions.like("Alu.AluApmaterno", "%" + apMaterno + "%"));
        }
        if (codAlu.trim().length() != 0) {
            criteria.add(Restrictions.eq("Alu.AluCodigo", codAlu));
        }
        if (idConPag != 0) {
            criteria.add(Restrictions.eq("AdAlumnoTarifa.Conpag.Id", idConPag));
        }
        if (idEsp != 0 && idFac != 0) {
            criteria.add(Restrictions.eq("Alu.Esp.Id", idEsp));
        }
        if (!idEstado.equals("-1")) {
            criteria.add(Restrictions.eq("AdAlumnoTarifa.AlutarEstado", idEstado));
        }
        if (sem_id != 0) {
            criteria.add(Restrictions.eq("AdEstructuraPagos.Sem.Id", sem_id));
        }
        return criteria.list();
    }

    @Override
    public AdAlumnoTarifa seleccionarUnAlumnoTarifa(int alutar_id) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        AdAlumnoTarifa alutar = (AdAlumnoTarifa) session.createCriteria(AdAlumnoTarifa.class).
                add(Expression.eq("AlutarActivo", "1")).
                add(Expression.eq("Id", alutar_id)).
                addOrder(Order.asc("Id")).uniqueResult();
        session.close();
        return alutar;
    }

    @Override
    public List seleccionarUnSaldo(int id) throws DataAccessException {
        return this.getSession().createCriteria(AdSaldo.class).add(Restrictions.eq("Alu.Id", id)).list();
    }

    @Override
    public void cambiarEstado(int id, String estado) throws DataAccessException {
        String query = "update AdAlumnoTarifa set AlutarEstado=:estado where Id= :id";
        this.getSession().createQuery(query).setString("id", "" + id).setString("estado", estado).executeUpdate();
    }
    
     @Override
    public void cambiarActivoRetiro(int id,int sem_id) throws DataAccessException {
        /*String query = "update AdAlumnoTarifa set AlutarActivo=:activo where Alu.Id= :id and Sem.Id=:v_sem_id and AlutarEstado=0 ";
         System.out.println("--->" + query);
        this.getSession().createQuery(query).setString("id", "" + id).setString("activo", "0").setInteger("v_sem_id",sem_id).executeUpdate();
        * */
        String sQuery;
        Query query;
        sQuery = "UPDATE ad_alumno_tarifa as alutar "
                + " INNER JOIN ad_estructura_pagos_detalle as aepd on  aepd.estpagdet_id=alutar.estpagdet_id "
                + " inner join ad_estructura_pagos aep on aep.estpag_id=aepd.estpag_id "
                + " SET alutar.alutar_activo= :activo WHERE alutar.alu_id= :v_alu_id and aep.sem_id=:v_sem_id and aep.estpag_activo=1 "
                + " and alutar.alutar_estado=0 and alutar.alutar_fecha_pago>=now() ";

        query = this.getSession().createSQLQuery( sQuery ).setInteger( "v_alu_id", id ).setInteger( "v_sem_id", sem_id ).setString("activo", "0");
        query.executeUpdate();
    }
      @Override
    public void cambiarActivoReserva(int id) throws DataAccessException {
        String query = "update AdAlumnoTarifa set AlutarActivo=:activo where Alu.Id= :id and AlutarEstado=0";
        this.getSession().createQuery(query).setString("id", "" + id).setString("activo", "0").executeUpdate();
    }

    @Override
    public List verificarPago(int alu_id, int con_pag, int sem_id) {
        List lista = this.getSession().createCriteria(AdAlumnoTarifa.class).add(Restrictions.eq("Alu.Id", alu_id)).add(Restrictions.eq("Conpag.Id", con_pag)).add(Restrictions.eq("AlutarActivo", "1")).createCriteria("Estpagdet").createCriteria("Estpag").add(Restrictions.eq("Sem.Id", sem_id)).list();
        return lista;
    }

    @Override
    public void actualizarAlumnoTarifa(AdAlumnoTarifa alu) throws DataAccessException {
        getHibernateTemplate().update(alu);
    }

    @Override
    public String seleccionarEstructuraPagosAlumno(int alu_id) throws DataAccessException {
        List lista;
        String hqlUpdate =
                " select a.Alu.Id,p.Estpag.Sem.Id "
                + " from AdAlumnoTarifa a,AdEstructuraPagosDetalle p "
                + " where a.Alu.Id=:alu_id "
                + " and a.Estpagdet.Id=p.Id "
                + " and p.Estpag.Sem.Id=( "
                + " 	select Id  "
                + " 	from AcSemestre "
                + " 	where SemVigente=1 and SemActivo=1 and SemActual=1 "
                + " ) ";
        lista = this.getSession().createQuery(hqlUpdate).setInteger("alu_id", alu_id).list();
        String numReg = "0";
        System.out.println("numero de registros en HS " + lista.size());
        if (lista.size() != 0) {
            numReg = "1";
        } else {
            numReg = "0";
        }
        return numReg;
    }

    @Override
    public List seleccionarAlumnoTarifa(int alu_id) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        List lista = session.createCriteria(AdAlumnoTarifa.class, "AdAlumnoTarifa").
                createCriteria("AdAlumnoTarifa.Estpagdet", "AdEstructuraPagosDetalle").
                createCriteria("AdEstructuraPagosDetalle.Estpag", "AdEstructuraPagos").
                add(Expression.eq("AdAlumnoTarifa.Alu.Id", alu_id)).
                add(Restrictions.eq("AdEstructuraPagosDetalle.EstpagdetActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagos.EstpagActivo", "1")).
                add(Restrictions.eq("AdAlumnoTarifa.AlutarActivo", "1")).
                addOrder(Order.asc("Id")).list();
        session.close();
        return lista;
    }

    @Override
    public List<AdAlumnoTarifa> seleccionarAlumnoTarifa(int alu_id, int sem_id) throws DataAccessException {
         List<AdAlumnoTarifa> lista = this.getSession().createCriteria(AdAlumnoTarifa.class, "AdAlumnoTarifa").
                createCriteria("AdAlumnoTarifa.Estpagdet", "AdEstructuraPagosDetalle").
                createCriteria("AdEstructuraPagosDetalle.Estpag", "AdEstructuraPagos").
                 add(Expression.eq("AdEstructuraPagos.Sem.Id", sem_id)).
                add(Expression.eq("AdAlumnoTarifa.Alu.Id", alu_id)).
                add(Restrictions.eq("AdEstructuraPagosDetalle.EstpagdetActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagos.EstpagActivo", "1")).
                add(Restrictions.eq("AdAlumnoTarifa.AlutarActivo", "1")).
                addOrder(Order.asc("Id")).list();
        return lista;
    }
    
     @Override
    public List<AdAlumnoTarifa> seleccionarAlumnoTarifaGeneral(int alu_id, int sem_id) throws DataAccessException {
         List<AdAlumnoTarifa> lista = this.getSession().createCriteria(AdAlumnoTarifa.class, "AdAlumnoTarifa").
                createCriteria("AdAlumnoTarifa.Estpagdet", "AdEstructuraPagosDetalle").
                createCriteria("AdEstructuraPagosDetalle.Estpag", "AdEstructuraPagos").
                add(Expression.eq("AdEstructuraPagos.Sem.Id", sem_id)).
                add(Expression.eq("AdAlumnoTarifa.Alu.Id", alu_id)).
                add(Expression.eq("AdAlumnoTarifa.Conpag.Id", 3)).
                add(Restrictions.eq("AdEstructuraPagosDetalle.EstpagdetActivo", "1")).
                add(Restrictions.eq("AdEstructuraPagos.EstpagActivo", "1")).
                add(Restrictions.eq("AdAlumnoTarifa.AlutarActivo", "1")).
                addOrder(Order.asc("Id")).list();
        return lista;
    }
    
     @Override
    public List seleccionarAlumnoTarifaBanco(int alutar_id) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        List lista = session.createCriteria(AdAlumnoTarifa.class, "AdAlumnoTarifa").
                add(Expression.eq("AlutarActivo", "1")).
                add(Expression.eq("Id", alutar_id)).
                addOrder(Order.asc("Id")).list();
        session.close();
        return lista;
    }
}

