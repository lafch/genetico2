package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.academica.managedBeans.beans.CursoDocenteBean;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcHorarioDispDocente;
import org.springframework.dao.DataAccessException;

public interface HSDocenteDAO {

    public void insertarDocente( AcDocente doc ) throws DataAccessException;

    public List seleccionarDocente( String codigo, String nombre ) throws DataAccessException, java.sql.SQLException;

    public void eliminarDocente( String id ) throws DataAccessException;

    public void actualizarDocente( AcDocente doc ) throws DataAccessException;

    public List seleccionarDocente() throws Exception;

    public void InsertarDisponibilidad( AcDocente doc ) throws DataAccessException;

    public AcDocente seleccionarDocente( int id );
    
    public AcAula seleccionarAula( int id );

    public AcDocente buscarDocente( String dni ) throws DataAccessException;
    
    public AcDocente buscarDocente( int docId ) throws DataAccessException;

    public List listarDocentesCursosLibreActivo();

    public AcDocente seleccionarDocentePorSecId( int iSecId );

    public String LocalSeleccionado( int iLocId );
    
    public List seleccionarDocentePorCurso(int esp_id, int cur_id) throws DataAccessException;

//    public CursoDocenteBean buscarCursoDocente( int doc_id, int cur_id );
//
//    public int insertarActualizarCurDoc( CursoDocenteBean curdoc ) throws Exception;

//    public List<CursoDocenteBean> listarCursoDocente( int doc_id );
    

    public List<AcHorarioDispDocente> seleccionarHorario( int doc_id ) throws Exception;

    public void insertarHorario( AcHorarioDispDocente horario ) throws Exception;
    
    public void actualizarHorario( AcHorarioDispDocente horario ) throws Exception;

    public void insertarActualizarHorarios( List<AcHorarioDispDocente> horarias );

    public void eliminarHorario( int hor_id ) throws Exception;

    public void eliminarHorarios( List<Integer> hor_ids );

    public void eliminarHorariosPorDocId( int iDocId );
    
    public List seleccionarDocenteHorario(String codigo, String nombre, int iHorario, int iCurso, String iCiclo, int idCurso, int idTurno) throws DataAccessException, java.sql.SQLException;

}
