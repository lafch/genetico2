package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.AcHorario;
import net.uch.mapping.AcHorarioDispDocente;
import net.uch.mapping.AcHorarioGen;
import org.springframework.dao.DataAccessException;

public interface HSHorarioGenDAO {

    public void eliminarHorarios() throws DataAccessException;

    public void insertarHorarios(List<AcHorarioGen> cur) throws DataAccessException;

    public void insertarHorariosDispDoc(List<AcHorarioDispDocente> cur) throws DataAccessException;

    public List seleccionarHorarioGen() throws DataAccessException;
    
    public List seleccionarHorarioGenPorNumeroGeneracion(int generacion) throws DataAccessException;

    public List seleccionarHorarioDocente(int doc) throws DataAccessException;

    public List seleccionarHorarioGen(int sec) throws DataAccessException;

    public AcHorario seleccionarUnHorario(int id_hor) throws DataAccessException;

    public AcHorarioDispDocente seleccionarUnHorario2(int id_hor) throws DataAccessException;

    public List duplicidadHoraria(String sec_ids) throws DataAccessException;

    public int SeleccionarHorarioPorDocente(int doc_id, int sem_id) throws DataAccessException;

    public List<BeanReporte> listarDocentesPorCicloSemestreFacultad(int semestre, int facultad, int especialidad, String ciclo, String turno);

    public List<BeanReporte> listarCursosPorCicloSemestreFacultad(int semestre, int facultad, int especialidad, String ciclo, String turno);

    public List<BeanReporte> listarHorarioPorSemestreTurno(int semestre, int turno);

    public List<BeanReporte> listarDisponibilidadDocentePorCicloSemestreFacultad(int semestre, int facultad, int especialidad, String ciclo, int turno, String tur_desc);
}
