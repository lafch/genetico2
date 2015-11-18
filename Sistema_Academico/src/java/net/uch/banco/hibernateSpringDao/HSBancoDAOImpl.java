/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.banco.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.Sp_conceptos_banco;
import net.uch.mapping.Sp_data_banco;
import net.uch.mapping.TbBanco;
import net.uch.mapping.TbBancoEntrada;
import net.uch.mapping.TbPagoBanco;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 */
public class HSBancoDAOImpl extends HibernateDaoSupport implements HSBancoDAO {

    public List<TbBanco> listarBanco(int ban_id) {
        return this.getSession().createCriteria(TbBanco.class).
                add(Restrictions.eq("banId", ban_id)).list();
    }

    public List<Sp_data_banco> listarDataBanco() {
        List list = null;

        try {
            Query query = this.getSession().getNamedQuery("sp_data_banco");
            list = query.list();
        } catch (DataAccessResourceFailureException darfee) {
            System.err.println("error de acceso al recurso" + darfee.getMessage());
            throw darfee;
        } catch (HibernateException he) {
            System.err.println("error de hibernate " + he.getMessage());
            throw he;
        } catch (IllegalStateException ise) {
            System.err.println("error de estado ilegal " + ise.getMessage());
            throw ise;
        }
        return list;
    }

    public List<Sp_conceptos_banco> listarConceptosBanco() {
        try {
            Query query = this.getSession().getNamedQuery("sp_conceptos_banco");
            return query.list();
        } catch (DataAccessResourceFailureException darfee) {
            System.err.println("error de acceso al recurso" + darfee.getMessage());
            throw darfee;
        } catch (HibernateException he) {
            System.err.println("error de hibernate " + he.getMessage());
            throw he;
        } catch (IllegalStateException ise) {
            System.err.println("error de estado ilegal " + ise.getMessage());
            throw ise;
        }
    }

    public void guardarRespuestaBanco(List<TbBancoEntrada> entradas) {
        try {
            getHibernateTemplate().saveOrUpdateAll(entradas);
        } catch (DataAccessException dae) {
            System.err.println("Error de acceso a datos " + dae.getMessage());
        }
    }

    public List<TbPagoBanco> existeNroOperacion(String nroOperacion,Date fechaPago) {
        return this.getSession().createCriteria(TbBancoEntrada.class).
                add(Restrictions.eq("banentActivo", "1")).
                add(Restrictions.eq("banentNroOperacion", nroOperacion)).
                add(Restrictions.eq("banentFechaPago", fechaPago)).
                list();
    }
    /*
     public TbBancoEntrada existeNroOperacion(String nroOperacion,Date fechaPago) {
        return (TbBancoEntrada) this.getSession().createCriteria(TbBancoEntrada.class).
                add(Restrictions.eq("banentActivo", "1")).
                add(Restrictions.eq("banentNroOperacion", nroOperacion)).
                add(Restrictions.eq("banentFechaPago", fechaPago)).
                uniqueResult();
    }
     */

    public List<TbPagoBanco> pagosBanco(Integer codigo_grupo) {
        return this.getSession().createCriteria(TbPagoBanco.class).
                add(Restrictions.eq("pagbanActivo", "1")).
                add(Restrictions.eq("pagbanCodigoGrupo", codigo_grupo)).
                list();
    }
}
