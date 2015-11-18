/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.AcImportacionNotas;
import net.uch.mapping.AcSisEvaPersonalizado;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 * 
 */
public class HSImportacionNotasDAOImpl extends HibernateDaoSupport implements HSImportacionNotasDAO {

    @Override
    public List listarSistemaEvaluacionPersonalizado_x_seccion(int siseva_id) throws DataAccessException {
        List lista = this.getSession().createCriteria(AcSisEvaPersonalizado.class, "sisevaper").
                createCriteria("sisevaper.SisevaDetalle", "sisevadet").
                createCriteria("sisevadet.Siseva", "siseva").
                add(Restrictions.eq("sisevadet.SisevaDetalleActivo", "1")).
                add(Restrictions.eq("sisevaper.SisevaPerActivo", "1")).
                add(Restrictions.eq("siseva.SisevaActivo", "1")).
                add(Restrictions.eq("sisevaper.Curape.Id", siseva_id)).list();



        return lista;
    }

    @Override
    public List listarImportacionNota_x_seccion(int sec_id, int per_id) throws DataAccessException {
        List lista = new ArrayList();
        lista = this.getSession().createCriteria(AcImportacionNotas.class, "AcImportacionNotas").
                add(Restrictions.eq("AcImportacionNotas.acSeccion.Id", sec_id)).
                add(Restrictions.eq("AcImportacionNotas.acSisEvaPersonalizado.Id", per_id)).list();
        return lista;
    }

    @Override
    public void agregarImportacionNota(AcImportacionNotas notas) throws DataAccessException {
        this.getHibernateTemplate().save(notas);
    }

    @Override
    public void actualizarImportacionNota(AcImportacionNotas notas) throws DataAccessException {
        this.getHibernateTemplate().update(notas);
    }
}
