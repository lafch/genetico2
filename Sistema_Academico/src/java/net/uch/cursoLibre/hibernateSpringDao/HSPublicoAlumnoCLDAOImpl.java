/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClPublicoAlumno;
import net.uch.mapping.ClPublicoConsulta;
import net.uch.mapping.Sp_listarPublicoAlumno;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 */
public class HSPublicoAlumnoCLDAOImpl extends HibernateDaoSupport implements HSPublicoAlumnoCLDAO {

    @Override
    public List<ClPublicoAlumno> listarPublicoAlumno( String paterno ) {
        List<ClPublicoAlumno> lista;
        lista = this.getSession().
                createCriteria( ClPublicoAlumno.class ).
                add( Restrictions.like( "paterno", paterno ) ).
                addOrder( Order.asc( "paterno" ) ).list();
        return lista;
    }

    @Override
    public ClPublicoAlumno datoPublicoAlumno( int idAlumno ) {

        return (ClPublicoAlumno) this.getSession().
                createCriteria( ClPublicoAlumno.class ).
                add( Restrictions.eq( "publicoId", idAlumno ) ).
                uniqueResult();

    }

    @Override
    public List<ClPublicoConsulta> listarConsultaPorAlumno( int idAlumno ) {
        List<ClPublicoConsulta> lista;
        lista = this.getSession().
                createCriteria( ClPublicoConsulta.class ).
                add( Restrictions.eq( "clPublicoAlumno.publicoId", idAlumno ) ).
                addOrder( Order.desc( "consultaId" ) ).
                list();

        return lista;
    }

    @Override
    public void agregarPublicoAlumno( ClPublicoAlumno publico ) {
        this.getHibernateTemplate().save( publico );
    }

    @Override
    public void modificarPublicoAlumno( ClPublicoAlumno publico ) {
        this.getHibernateTemplate().update( publico );
    }

    @Override
    public List<Sp_listarPublicoAlumno> listarALumnosPorDato( String dato ) {
        Query query = this.getSession().getNamedQuery( "sp_listarPublicoAlumno" ).
                setString( "p_dato", dato );

        return query.list();
    }
}
