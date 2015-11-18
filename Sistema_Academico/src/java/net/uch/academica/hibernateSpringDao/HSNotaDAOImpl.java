package net.uch.academica.hibernateSpringDao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.uch.mapping.AcNota;
import net.uch.util.Print;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSNotaDAOImpl extends HibernateDaoSupport implements HSNotaDAO {

    @Override
    public void insertarNotas( List<AcNota> notas ) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdateAll( notas );
    }

    @Override
    public List seleccionarNotas( int seccion ) throws DataAccessException {
        return this.getSession().createCriteria( AcNota.class ).add( Restrictions.eq( "SecId", seccion ) ).list();
    }

    @Override
    public InputStream imprimirTodo( String seccion ) {
        if ( seccion.length() > 1 ) {
            List alumnos = this.getSession().createCriteria( AcNota.class ).list();
//            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(alumnos);
            HashMap parameters = new HashMap();
            parameters.put( "SECCION", new String( seccion ) );
            Print print = new Print();
            byte[] bytes = null;
            if ( seccion.substring( 0, 2 ).equals( "IS" ) ) {
                bytes = print.cargar_reporte( "/WEB-INF/Reportes/notas_de_todos_alumnos_sistemas.jasper", parameters ).toByteArray();
            } else if ( seccion.substring( 0, 2 ).equals( "IE" ) ) {
                bytes = print.cargar_reporte( "/WEB-INF/Reportes/notas_de_todos_alumnos_electronica.jasper", parameters ).toByteArray();
            } else if ( seccion.substring( 0, 2 ).equals( "CF" ) ) {
                bytes = print.cargar_reporte( "/WEB-INF/Reportes/notas_de_todos_alumnos_contabilidad.jasper", parameters ).toByteArray();
            } else if ( seccion.substring( 0, 2 ).equals( "EP" ) ) {
                bytes = print.cargar_reporte( "/WEB-INF/Reportes/notas_de_todos_alumnos_educacion.jasper", parameters ).toByteArray();
            } else if ( seccion.substring( 0, 2 ).equals( "EN" ) ) {
                bytes = print.cargar_reporte( "/WEB-INF/Reportes/notas_de_todos_alumnos_enfermeria.jasper", parameters ).toByteArray();
            }
            InputStream in = new ByteArrayInputStream( bytes );
            return in;
        } else {
            return null;
        }

    }

    @Override
    public List listarNotaxAlumno( int alu_id, int sec_id, int siseva_per_id ) {
        return this.getSession().createCriteria( AcNota.class ).add( Restrictions.eq( "NotActivo", "1" ) ).
                add( Restrictions.eq( "Alu.Id", alu_id ) ).
                add( Restrictions.eq( "SisevaPer.Id", siseva_per_id ) ).
                add( Restrictions.eq( "SecId", sec_id ) ).list();

    }

    @Override
    public List listarNotaAlumno( int alu_id, int sec_id ) throws DataAccessException {
        return this.getSession().createCriteria( AcNota.class ).add( Restrictions.eq( "NotActivo", "1" ) ).
                add( Restrictions.eq( "Alu.Id", alu_id ) ).
                add( Restrictions.eq( "SecId", sec_id ) ).
                setProjection( Projections.projectionList().add( Projections.groupProperty( "SisevaPer.Id" ) ) ).list();

    }
}
