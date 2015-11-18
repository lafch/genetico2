package net.uch.academica.hibernateSpringDao;

import java.sql.SQLException;
import java.util.List;
import net.uch.mapping.AcContenidoDetalle;
import net.uch.mapping.AcContenidoTematico;
import org.hibernate.criterion.Expression;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSContenidoTematicoDAOImpl extends HibernateDaoSupport implements HSContenidoTematicoDAO {

    @Override
    public void insertarContenidoTematico(AcContenidoTematico Con) throws DataAccessException, SQLException {
        this.getHibernateTemplate().saveOrUpdate(Con);
    }

    @Override
    public void eliminarContenidoTematicoDetalle(List<AcContenidoDetalle> det) throws DataAccessException, SQLException {
        this.getHibernateTemplate().deleteAll(det);

    }

    @Override
    public List get_contem_id(int plancur_id) throws SQLException, Exception {
        return this.getSession().createCriteria(AcContenidoTematico.class).add(Expression.eq("Plancur.Id", plancur_id)).add(Expression.eq("ContemActivo", "1")).list();
    }

    @Override
    public List seleccionarContenidoTematico(int contem_id) throws SQLException, Exception {
        return this.getSession().createCriteria(AcContenidoTematico.class).add(Expression.eq("Id", contem_id)).add(Expression.eq("ContemActivo", "1")).list();
    }

    @Override
    public List listar_detalle(int contem_id) throws SQLException, Exception {
        return this.getSession().createCriteria(AcContenidoDetalle.class).add(Expression.eq("Contem.Id", contem_id)).list();
    }

    @Override
    public List seleccionarDatosCT(int plancur_id) throws SQLException, Exception {
        List lista = null;
        String hqlSelect = "select pc.Id,pc.PlancurCiclo,pcu.Id,pcu.PlanDescripcion"
                + " ,e.EspNombre,e.Fac.FacNombre,ca.CatDescripcionElemento,pc.Cur.CurNombre"
                + " from AcPlanCurso pc"
                + " ,AcPlancurricular pcu,AcEspecialidad e,"
                + " TbCatalogo ca"
                + " where pc.Plan=pcu.Id"
                + " and pcu.Esp=e.Id"
                + " and ca.CatCodigoGrupo=substring(pc.PlancurCiclo,1,3)"
                + " and ca.CatCodigoElemento=substring(pc.PlancurCiclo,4,6)"
                + " and pc.Id=:pc_id";
        lista = this.getSession().createQuery(hqlSelect).setInteger("pc_id", plancur_id).list();
        return lista;

    }
}
