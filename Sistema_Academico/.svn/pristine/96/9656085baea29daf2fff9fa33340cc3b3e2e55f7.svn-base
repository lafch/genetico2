package net.uch.administrativa.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.AdComprobantePago;
import net.uch.mapping.AdPago;
import net.uch.mapping.AdSaldo;
import net.uch.mapping.ClAlumnoTarifa;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSPagoDAOImpl extends HibernateDaoSupport implements HSPagoDAO {

    @Override
    public int insertarPago(AdComprobantePago compag) throws DataAccessException {
        Transaction tx = this.getSession().beginTransaction();
        this.getSession().save(compag);
        tx.commit();
        this.getSession().refresh(compag);
        return compag.getId();
    }

    @Override
    public void insertarComprobantePago(AdComprobantePago con) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(con);
        tx.commit();
        session.close();
    }

    @Override
    public void insertarComprobantesPago(List<AdComprobantePago> compags) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdateAll(compags);
    }

    @Override
    public void insertarSaldo(AdSaldo sal) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(sal);
        tx.commit();
        session.close();
    }

    @Override
    public void eliminarPago(String id) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        String hqlUpdate = "update AcAlumno set AluActivo = :v_activo where Id = :v_id";
        session.createQuery(hqlUpdate).setString("v_activo", "0").setString("v_id", id).executeUpdate();
        session.close();
    }

    @Override
    public List seleccionarMostrarPagos(int id) throws DataAccessException {
        return this.getSession().
                createCriteria(AdPago.class).
                add(Restrictions.eq("PagActivo", "1")).
                add(Restrictions.eq("AlutarId", id)).
                add(Restrictions.not(Restrictions.eq("Conpag.Id", 49))).
                add(Restrictions.not(Restrictions.eq("Conpag.Id", 23))).list();
    }

    @Override
    public List seleccionarMostrarPagosMora(int id) throws DataAccessException {
        return this.getSession().createCriteria(AdPago.class).add(Restrictions.eq("PagActivo", "1")).add(Restrictions.eq("AlutarId", id)).list();
    }

    @Override
    public List seleccionarMostrarPagosMatExp(int id) throws DataAccessException {
        return this.getSession().createCriteria(AdPago.class).add(Restrictions.eq("PagActivo", "1")).add(Restrictions.eq("AlutarId", id)).list();
    }

    @Override
    public void actualizarPago(AdPago alu) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(alu);
        tx.commit();
        session.close();
    }

    @Override
    public String seleccionarUnComprobantePago(String comp) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        String nroComp = "0";
        List lista = session.createCriteria(AdComprobantePago.class).add(Expression.eq("CompagActivo", "1")).add(Expression.eq("CompagNro", comp)).addOrder(Order.asc("Id")).list();
        if (lista.size() != 0) {
            nroComp = ((AdComprobantePago) lista.get(0)).getCompagNro();
        }
        session.close();
        return nroComp;
    }

    @Override
    public List seleccionarUnComprobantePago(int comp_id) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        List lista = session.createCriteria(AdComprobantePago.class).add(Expression.eq("CompagActivo", "1")).add(Expression.eq("Id", comp_id)).addOrder(Order.asc("Id")).list();
        session.close();
        return lista;
    }

    @Override
    public void ActualizarComprobantePago(int id, String num_com, Date fecha, String proc, String tipCom, String nroVou, Date fecVou, String codAge) throws DataAccessException {
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        String hqlUpdate = "update AdComprobantePago set CompagNro=:v_num_com,CompagFecha=:v_fecha,CompagProcedencia=:v_proc,CompagTipDoc=:v_tipCom,CompagVoucherFecha=:v_fecVou,CompagVoucherCodAgencia=:v_codAge,CompagVoucherNroOperacion=:v_nroVou where Id = :v_id";
        session.createQuery(hqlUpdate).setString("v_num_com", num_com).setDate("v_fecha", fecha).setString("v_proc", proc).setInteger("v_id", id).setString("v_tipCom", tipCom).setDate("v_fecVou", fecVou).setString("v_codAge", codAge).setString("v_nroVou", nroVou).executeUpdate();
        session.close();
    }

    @Override
    public List seleccionarMostrarPagosDet(int id) throws DataAccessException {
        return this.getSession().createCriteria(AdPago.class).add(Restrictions.eq("PagActivo", "1")).add(Restrictions.eq("Id", id)).list();
    }

    @Override
    public String MaximoNroComprobante(String serie) throws DataAccessException {
        List lista;
        String nroMax;
        String hqlUpdate = "select max(CompagNro) from AdComprobantePago where CompagProcedencia!='015004' and CompagActivo='1' and CompagNro like '" + serie + "-%'";
        lista = this.getSession().createQuery(hqlUpdate).list();
        if ((lista.get(0) + "").equals("null")) {
            nroMax = "0";
        } else {
            nroMax = lista.get(0).toString();
        }
        return nroMax;
    }

    @Override
    public List seleccionarMostrarPagosExternos(int id, Date desde, Date hasta, int conpag_id) throws DataAccessException {
        Criteria criteria = this.getSession().createCriteria(AdComprobantePago.class).add(Restrictions.between("CompagFecha", desde, hasta)).add(Restrictions.eq("CompagActivo", "1"));
        if (conpag_id != 0) {
            return criteria.createCriteria("AdPagos").add(Restrictions.eq("Conpag.Id", conpag_id)).add(Restrictions.eq("AlutarId", 0)).list();
        } else {
            return criteria.createCriteria("AdPagos").add(Restrictions.eq("AlutarId", 0)).list();
        }
    }

    @Override
    public List seleccionarPagosExternos(int alu_id, int conpag_id) {
        return this.getSession().createCriteria(AdComprobantePago.class).add(Restrictions.eq("CompagCliente", alu_id)).add(Restrictions.eq("CompagClienteTipo", "014001")).createCriteria("AdPagos").add(Restrictions.eq("AlutarId", 0)).add(Restrictions.eq("Conpag.Id", conpag_id)).list();
    }

    @Override
    public void anularComprobantePago(int compag_id) {
        String hqlUpdate = "update AdComprobantePago set CompagTipo = :tipo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("tipo", "037002").setInteger("id", compag_id).executeUpdate();
    }

    @Override
    public void eliminarComprobantePago(int compag_id) {
        String hqlUpdate = "update AdComprobantePago set CompagActivo = :activo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("activo", "0").setInteger("id", compag_id).executeUpdate();
    }

    @Override
    public List<AdPago> listaPagosAlumno(int alutarId, int caso) {
        Criteria c = this.getSession().createCriteria(AdPago.class, "AdPago").
                createCriteria("AdPago.Compag", "AdComprobantePago").
                createCriteria("AdPago.Conpag", "AdConceptoPago").
                add(Restrictions.eq("AdPago.PagActivo", "1")).
                add(Restrictions.eq("AdComprobantePago.CompagActivo", "1")).
                add(Restrictions.eq("AdConceptoPago.ConpagActivo", "1")).
                add(Restrictions.eq("AdConceptoPago.ConpagTipo", "007003")).
                add(Restrictions.eq("AdPago.AlutarId", alutarId));

        if (caso == 0) {
            c.add(Restrictions.eq("AdComprobantePago.CompagTipo", "037001"));
        }

        return c.list();
    }


    @Override
    public List<AdPago> listaPagosAlumnoLibre(int alutarId) {
        Criteria c = this.getSession().createCriteria(AdPago.class, "AdPago").
                createCriteria("AdPago.Compag", "AdComprobantePago").
                add(Restrictions.eq("AdPago.PagActivo", "1")).
                add(Restrictions.eq("AdComprobantePago.CompagActivo", "1")).
                add(Restrictions.eq("AdComprobantePago.CompagClienteTipo","014003")).
                add(Restrictions.eq("AdPago.AlutarId", alutarId)).
                add(Restrictions.eq("AdComprobantePago.CompagTipo", "037001"));
        return c.list();
    }

    @Override
    public void actualizarEstadoPago(int alutarId, String estado) {
        String hqlUpdate = "update ClAlumnoTarifa set alutarEstado = :v_estado where alutarId = :v_id";
        this.getSession().createQuery(hqlUpdate).
                setString("v_estado", estado).
                setInteger("v_id", alutarId).executeUpdate();
    }

    @Override
    public ClAlumnoTarifa getClAlumnoTarifa(int alutarID) {
        Criteria c = this.getSession().createCriteria(ClAlumnoTarifa.class).
                add(Restrictions.eq("alutarActivo", "1")).
                add(Restrictions.eq("alutarId", alutarID));
        return (ClAlumnoTarifa) c.uniqueResult();
    }

    @Override
    public List<AdPago> listarPagosporAlumno(int alu_id) {

        List<AdPago>  lista= this.getSession().createCriteria(AdPago.class, "AdPago").
                createCriteria("AdPago.Compag", "AdComprobantePago").
                add(Restrictions.eq("AdPago.PagActivo", "1")).
                add(Restrictions.eq("AdComprobantePago.CompagActivo", "1")).
                add(Restrictions.eq("AdComprobantePago.CompagClienteTipo","014003")).
                add(Restrictions.eq("AdComprobantePago.CompagCliente", alu_id)).
                add(Restrictions.eq("AdComprobantePago.CompagTipo", "037001")).list();
        return lista;
    }

    /*nuevo codigo 18-06-2012*/

   
    @Override
    public int listarPagosCancelados(int alutar_id) {
    int valido=0;
    //System.out.println(alutar_id);
        List<AdPago>  lista= this.getSession().createCriteria(AdPago.class, "AdPago").
                createCriteria("AdPago.Compag", "AdComprobantePago").
                add(Restrictions.eq("AdPago.PagActivo", "1")).
                add(Restrictions.eq("AdPago.AlutarId", alutar_id )).
                add(Restrictions.eq("AdComprobantePago.CompagActivo", "1")).
                add(Restrictions.eq("AdComprobantePago.CompagEstadoCancelacion", "066001")).
                add(Restrictions.eq("AdPago.PagEstadoCancelacion", "067001")).
                add(Restrictions.eq("AdComprobantePago.CompagClienteTipo","014001")).
                //add(Restrictions.eq("AdComprobantePago.CompagCliente", alu_id)).
                add(Restrictions.eq("AdComprobantePago.CompagTipo", "037001")).list();

        //System.out.println(lista.size());
        if (lista.size() != 0) {
            valido = 0;
           // System.out.println("entro 0" + lista.size()+ "--->" + valido);
        } else{
            valido=1;
           // System.out.println("entro 1" + lista.size() + "--->" + valido);
        }
        return valido;
    }
}
