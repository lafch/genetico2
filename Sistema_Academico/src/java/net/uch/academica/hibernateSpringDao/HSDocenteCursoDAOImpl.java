package net.uch.academica.hibernateSpringDao;


import java.util.List;

import net.uch.mapping.AcDocenteCurso;

import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSDocenteCursoDAOImpl extends HibernateDaoSupport implements HSDocenteCursoDAO {

    @Override
    public void insertarDocenteCurso( AcDocenteCurso docCur ) throws DataAccessException {
        getHibernateTemplate().save( docCur );
    }
    
    @Override
    public void eliminarDocenteCurso( String id ) throws DataAccessException {
        String hqlUpdate = "update acDocente set activo = :v_activo where curDocId = :v_id";
        this.getSession().createQuery( hqlUpdate ).setString( "v_activo", "0" ).setString( "v_id", id ).executeUpdate();
    }

    @Override
    public void actualizarDocenteCurso( AcDocenteCurso docCur ) throws DataAccessException {
        getHibernateTemplate().update( docCur );
    }
    
    @Override
    public List<AcDocenteCurso> listadoDocenteCurso( int doc_id ) throws Exception{
         return this.getSession().createCriteria( AcDocenteCurso.class ).
                add( Restrictions.eq( "activo", 1 ) ).
                add( Restrictions.eq( "acDocente.Id", doc_id ) ).list();
    }
    
    @Override
    public List<AcDocenteCurso> seleccionarCurso( int doc_id ) throws Exception {
        return this.getSession().createCriteria( AcDocenteCurso.class ).
                add( Restrictions.eq( "activo", 1 ) ).
                add( Restrictions.eq( "acDocente.Id", doc_id ) ).list();
    }
    

    @Override
    public void insertarCursoDocente( AcDocenteCurso cursoDocente ) throws Exception {
        this.getHibernateTemplate().save( cursoDocente );
    }

    @Override
    public void actualizarCursoDocente( AcDocenteCurso cursoDocente ) throws Exception {
        this.getHibernateTemplate().update( cursoDocente );
    }

    @Override
    public void insertarActualizarCursoDocente( List<AcDocenteCurso> cursoDocente ) {
        this.getHibernateTemplate().saveOrUpdateAll( cursoDocente );
    }

    @Override
    public void eliminarHorario( int curDoc_id ) throws Exception {
        String hqlUpdate = "update AcDocenteCurso set activo = :newName where curdoc_id = :oldName";
        this.getSession().createQuery( hqlUpdate ).setString( "newName", "0" ).
                setString( "oldName", "" + curDoc_id ).
                executeUpdate();
    }

    @Override
    public void eliminarCursosDocente( List<Integer> curDoc_ids ) {
        String hqlUpdate = "update AcDocenteCurso "
                + "set activo = 0 "
                + "where curdoc_id in (:v_id)";

        this.getSession().createQuery( hqlUpdate ).
                setParameterList( "v_id", curDoc_ids ).executeUpdate();
    }

    @Override
    public void eliminarCursoDocentePorDocId( int iDocId ) {
        String hqlUpdate = "update AcDocenteCurso "
                + "set activo = 0 "
                + "where AcDocente.Id = :v_id";

        this.getSession().createQuery( hqlUpdate ).
                setInteger( "v_id", iDocId ).executeUpdate();
    }

   



   
}
