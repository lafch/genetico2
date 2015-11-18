package net.uch.academica.hibernateSpringDao;

import java.util.List;

import net.uch.mapping.AcAlumnoDato;
import net.uch.mapping.AcAlumnoDocumento;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSAlumnoDatoDAOImpl extends HibernateDaoSupport implements HSAlumnoDatoDAO {

    @Override
    public void insertarAlumnoDato(AcAlumnoDato aludat) throws DataAccessException {
        getHibernateTemplate().save(aludat);
    }

    @Override
    public List seleccionarAlumnoDato(int alu_id) {
        return this.getSession().createCriteria(AcAlumnoDato.class).add(Restrictions.eq("AluId", alu_id)).list();
    }

    @Override
    public void actualizarAlumnoDato(AcAlumnoDato aludat) {
        getHibernateTemplate().update(aludat);
    }

    @Override
    public List seleccionarDocumentos(int alu_id) {
        return this.getSession().createCriteria(AcAlumnoDocumento.class).add(Restrictions.eq("Alu.Id", alu_id)).list();
    }

    @Override
    public void insertarDocumentos(AcAlumnoDocumento aluDoc) throws Exception {
        this.getSession().save(aluDoc);
    }

    @Override
    public void eliminarDocumentos(int b_id_alumno) throws Exception {
        String hqlUpdate = "delete from AcAlumnoDocumento where Alu.Id = :v_id";
        this.getSession().createQuery(hqlUpdate).setInteger("v_id", b_id_alumno).executeUpdate();
    }
}
