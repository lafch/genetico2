package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClTallerAperturado;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSTallerAperturadoDAOImpl extends HibernateDaoSupport implements HSTallerAperturadoDAO {

    @Override
    public ClTallerAperturado seleccionarTallerAperturado(int talape_id) {
        return (ClTallerAperturado) this.getSession().createCriteria(ClTallerAperturado.class).
                add(Restrictions.eq("talapeActivo", "1")).
                add(Restrictions.eq("talapeId", talape_id)).
                uniqueResult();
    }

    @Override
    public List<ClTallerAperturado> seleccionarTalleresAperturados() throws Exception {
        return this.getSession().createCriteria(ClTallerAperturado.class).
                add(Restrictions.eq("talapeActivo", "1")).
                add(Restrictions.eq("talapeVigente", "1")).
                add(Restrictions.eq("talapeAperturado", "1")).
                list();
    }

    @Override
    public List<ClTallerAperturado> seleccionarTallerAperturadoPorCurso(int cur_id) throws Exception {
        return this.getSession().createCriteria(ClTallerAperturado.class, "ClTallerAperturado").
                createCriteria("ClTallerAperturado.clTaller", "ClTaller").
                add(Restrictions.eq("ClTallerAperturado.talapeActivo", "1")).
                add(Restrictions.eq("ClTallerAperturado.talapeVigente", "1")).
                add(Restrictions.eq("ClTallerAperturado.talapeAperturado", "1")).
                add(Restrictions.eq("ClTaller.talActivo", "1")).
                add(Restrictions.eq("ClTaller.clCurso.curId", cur_id)).
                list();
    }

    @Override
    public List<ClTallerAperturado> seleccionarTalleresAperturadosPorModulo(int mod_id, int tal_id) {
        return this.getSession().createCriteria(ClTallerAperturado.class, "talape").
                createCriteria("talape.clTaller", "tal").
                createCriteria("tal.clCurso", "cur").
                add(Restrictions.eq("talape.talapeActivo", "1")).
                add(Restrictions.eq("talape.talapeVigente", "1")).
                add(Restrictions.eq("talape.talapeAperturado", "1")).
                add(Restrictions.eq("tal.talActivo", "1")).
                add(Restrictions.ne("tal.talId", tal_id)).
                add(Restrictions.eq("cur.curActivo", "1")).
                add(Restrictions.eq("cur.clModulo.modId", mod_id)).
                list();
    }

    @Override
    public List<ClTallerAperturado> seleccionarTalleresAperturadosPorTaller(int tal_id) {
        Criteria c = this.getSession().createCriteria(ClTallerAperturado.class).
                add(Restrictions.eq("talapeActivo", "1")).
                add(Restrictions.eq("clTaller.talId", tal_id));

        return c.list();
    }

    @Override
    public void insertar_ActualizarTallerAperturado(ClTallerAperturado talape) throws Exception {
        this.getHibernateTemplate().saveOrUpdate(talape);
    }

    @Override
    public void eliminarTallerAperturado(int talape_id) {
        String hqlUpdate = "update ClTallerAperturado set talapeActivo = '0' where talapeId = :talape_id";
        this.getSession().createQuery(hqlUpdate).setString("talape_id", "" + talape_id).executeUpdate();
    }

    @Override
    public List<ClTallerAperturado> seleccionarTallerAperturadoxModulo(int mod_id) throws Exception {
        return this.getSession().createCriteria(ClTallerAperturado.class, "talape").
                createCriteria("talape.clTaller", "tal").
                createCriteria("tal.clCurso", "cur").
                add(Restrictions.eq("talape.talapeActivo", "1")).
                add(Restrictions.eq("talape.talapeVigente", "1")).
                add(Restrictions.eq("talape.talapeAperturado", "1")).
                add(Restrictions.eq("tal.talActivo", "1")).
                add(Restrictions.eq("cur.curActivo", "1")).
                add(Restrictions.eq("cur.clModulo.modId", mod_id)).
                addOrder(Order.asc("talape.talapeDescripcion")).list();
    }



    @Override
    public List<ClTallerAperturado> seleccionarTallerxModulo(int mod_id) throws Exception {
        return this.getSession().createCriteria(ClTallerAperturado.class, "talape").
                createCriteria("talape.clTaller", "tal").
                createCriteria("tal.clCurso", "cur").
                add(Restrictions.eq("talape.talapeActivo", "1")).
                add(Restrictions.eq("tal.talActivo", "1")).
                add(Restrictions.eq("cur.curActivo", "1")).
                add(Restrictions.eq("cur.clModulo.modId", mod_id)).
                addOrder(Order.asc("talape.talapeDescripcion")).list();
    }

    @Override
    public List<ClTallerAperturado> listarTallerModulo(String _descripcion) {
         List<ClTallerAperturado> lista=this.getSession().createCriteria(ClTallerAperturado.class,"clTallerAperturado").
                            createCriteria("clTallerAperturado.clTaller","clTaller").
                            createCriteria("clTaller.clCurso","clCurso")
                            .createCriteria("clCurso.clModulo","clModulo")
                            .add(Restrictions.eq("clTallerAperturado.talapeAperturado", "1"))
                            .add(Restrictions.eq("clTallerAperturado.talapeActivo", "1"))
                            .add(Restrictions.eq("clTaller.talActivo", "1"))
                            .add(Restrictions.eq("clCurso.curActivo", "1"))
                            .add(Restrictions.eq("clModulo.modActivo", "1"))
                            .add(Restrictions.or(Restrictions.like("clModulo.modDescripcion", "%"+_descripcion+"%"),
                                                 Restrictions.like("clTallerAperturado.talapeDescripcion", "%"+_descripcion+"%")))
                            .addOrder(Order.asc("clModulo.modDescripcion")).list();
        return lista;
    }
}
