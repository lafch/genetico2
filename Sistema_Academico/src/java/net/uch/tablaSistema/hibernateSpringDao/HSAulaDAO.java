package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcHorarioDispAula;
import net.uch.mapping.AcHorarioDispDocente;
import org.springframework.dao.DataAccessException;

public interface HSAulaDAO {

    public void insertarAula( AcAula aul ) throws DataAccessException;

    public List seleccionarAula( String nombre, int pabellon, int especialidad ) throws DataAccessException, java.sql.SQLException;

    public void eliminarAula( String id ) throws DataAccessException;

    public void actualizarAula( AcAula aul ) throws DataAccessException;

    public List seleccionarAulaSeccion(int idSeccion) throws Exception;
    
    public List listadoLaboratoriosDisponibles(int idHorario) throws Exception;

    public List seleccionarAula( int pab_id ) throws Exception;

    public List<AcHorarioDispAula> seleccionarHorario( int aula_id ) throws Exception;

    public void insertarActualizarHorarios( List<AcHorarioDispAula> horarias );

    public void eliminarHorario( int hor_id ) throws Exception;

    public void eliminarHorarios( List<Integer> hor_ids );

    public AcAula buscarAula( int aul_id );
    
    public List seleccionarAulaPorEspecialidad(int esp_id, int semestre_id, int tur_id) throws DataAccessException;
}
