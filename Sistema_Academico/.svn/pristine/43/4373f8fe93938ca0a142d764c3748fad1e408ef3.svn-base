package net.uch.academica.hibernateSpringDao;

import java.sql.SQLException;
import java.util.List;
import net.uch.mapping.AcDisponibilidad;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSDisponibilidadDAOImpl extends HibernateDaoSupport implements HSDisponibilidadDAO {

    @Override
    public void insertarDisponibilidad(List<AcDisponibilidad> Dis) throws DataAccessException, SQLException {
        this.getHibernateTemplate().saveOrUpdateAll(Dis);
    }

    @Override
    public void insertarDisponibilidad(net.uch.mapping.AcDisponibilidad dispo) throws DataAccessException, SQLException {
        getHibernateTemplate().save(dispo);
    }

    @Override
    public void actualizarDisponibilidad(net.uch.mapping.AcDisponibilidad dispo) throws DataAccessException, SQLException {
        getHibernateTemplate().update(dispo);
    }

    @Override
    public int verificar_disponibilidad(int doc_id, int turdet_id, String dis_dia) throws DataAccessException, SQLException {
        int bandera = 0;
        List lista = null;
        String hql = "select Id,TurdetId,Doc.Id"
                + " from AcDisponibilidad"
                + " where TurdetId=:turno and DisActivo=:activo and Doc.Id=:d_id and DisDia=:dis_d";
        lista = this.getSession().createQuery(hql).setString("activo", "1").setString("turno", "" + turdet_id).setString("d_id", "" + doc_id).setString("dis_d", "" + dis_dia).list();
        if (lista != null && lista.size() != 0) {
            bandera = 1;
        } else {
            bandera = 0;
        }
        return bandera;
    }

    @Override
    public int verificar_disponibilidad1(int doc_id, int turdet_id, String dis_d) throws DataAccessException, SQLException {
        int bandera = 0;
        List lista = null;
        String hql = "select Id,TurdetId,Doc.Id"
                + " from AcDisponibilidad"
                + " where TurdetId=:turno and DisActivo=:activo and Doc.Id=:d_id and DisDia=:dis_d";
        lista = this.getSession().createQuery(hql).setString("activo", "0").setString("turno", "" + turdet_id).setString("d_id", "" + doc_id).setString("dis_d", "" + dis_d).list();
        if (lista != null && lista.size() != 0) {
            bandera = 1;
        } else {
            bandera = 0;
        }
        return bandera;
    }

    @Override
    public List get_iddis(int doc_id, int turdet_id, String dis_d) throws DataAccessException, SQLException {
        String hql = "select Id,TurdetId,Doc.Id from AcDisponibilidad"
                + " where TurdetId=:turno and Doc.Id=:d_id and DisDia=:dis_d";
        return this.getSession().createQuery(hql).setString("turno", "" + turdet_id).setString("d_id", "" + doc_id).setString("dis_d", "" + dis_d).list();
    }

    @Override
    public List verificar_conf_dis() throws DataAccessException, SQLException {
        String hql = "select c.Id,c.CatValor from TbCatalogo c  where c.CatCodigoGrupo=:grupo and c.CatCodigoElemento=:elem and c.CatActivo=:act";
        return this.getSession().createQuery(hql).setString("grupo", "021").setString("elem", "001").setString("act", "1").list();
    }
}

