package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcDocenteCurso;
import org.springframework.dao.DataAccessException;

public interface HSDocenteCursoDAO {

    public void insertarDocenteCurso( AcDocenteCurso docCur ) throws DataAccessException;

    public void eliminarDocenteCurso( String id ) throws DataAccessException;

    public void actualizarDocenteCurso( AcDocenteCurso docCur ) throws DataAccessException;
    
    public List<AcDocenteCurso> listadoDocenteCurso( int doc_id ) throws Exception;
    
    
    public List<AcDocenteCurso> seleccionarCurso( int doc_id ) throws Exception;

    public void insertarCursoDocente( AcDocenteCurso cursoDocente ) throws Exception;
    
    public void actualizarCursoDocente( AcDocenteCurso cursoDocente ) throws Exception;

    public void insertarActualizarCursoDocente( List<AcDocenteCurso> horarias );

    public void eliminarHorario( int hor_id ) throws Exception;

    public void eliminarCursosDocente( List<Integer> curDoc_ids );

    public void eliminarCursoDocentePorDocId( int iDocId );
    
    
   

}
