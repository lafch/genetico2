/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbReporte;
import net.uch.mapping.TbReporteRol;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSReportesRolCLDAOImpl extends HibernateDaoSupport implements HSReporteRolCLDAO {

    @Override
    public List<TbReporteRol> getListRoles(int usu_id) {
        return this.getSession().createCriteria(TbReporteRol.class,"ReportRol")
                    .createCriteria("ReportRol.tbRoles","Rol")
                    .createCriteria("ReportRol.tbReporte","Rep")
                    .add(Restrictions.eq("ReportRol.usuId",usu_id))
                /*.add(Restrictions.eq("Rol.Id",usu_id))*/
                    .add(Restrictions.eq("ReportRol.reporRolActivo",1))
                    .add(Restrictions.eq("Rep.repGrupo","059002"))
                  //  .add(Restrictions.eq("Rep.repTipo","043002"))
                    .list();
                // tabla Reporte repTipo
    }
    



    @Override
    public List<TbReporte> getReporte() {
        return this.getSession().createCriteria(TbReporte.class,"Report")
                    .add(Restrictions.eq("Report.repActivo",1))
                    .add(Restrictions.eq("Report.repGrupo","059002"))
                    .list();
    }


}
