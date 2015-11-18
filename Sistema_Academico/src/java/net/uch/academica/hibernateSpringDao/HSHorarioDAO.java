package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.AcHorario;
import net.uch.mapping.AcHorarioDispDocente;
import org.springframework.dao.DataAccessException;

public interface HSHorarioDAO {

    public void insertarHorarios(List<AcHorario> cur) throws DataAccessException;
    
    public void insertarHorariosDispDoc(List<AcHorarioDispDocente> cur) throws DataAccessException;

    public List seleccionarHorario(int sec) throws DataAccessException;
    
    public List seleccionarHorarioSeccion(int sec) throws DataAccessException;
    
    public List<BeanReporte> numeroHorasDisponiblesSeccion(int seccion);
    
    public List seleccionarHorarioDocente(int doc) throws DataAccessException;
    
    public List HorarioNoDisponibleDocente(int doc,int sec) throws DataAccessException;
    public List HorarioSiDisponibleDocente(int doc,int sec, int tur_id) throws DataAccessException;
    
    public List seleccionarHorarioGen(int sec) throws DataAccessException;

    public AcHorario seleccionarUnHorario(int id_hor) throws DataAccessException;
    
    public List HorarioDisponibleLaboratorio(int sec_id) throws DataAccessException;
    
    public AcHorarioDispDocente seleccionarUnHorario2(int id_hor) throws DataAccessException;

    public List duplicidadHoraria(String sec_ids) throws DataAccessException;

    public int SeleccionarHorarioPorDocente(int doc_id, int sem_id) throws DataAccessException;
    
    public List<BeanReporte> listarDocentesPorCicloSemestreFacultad(int semestre, int facultad, int especialidad, String ciclo, int turno, String turnoDes);
    
    public List<BeanReporte> listarCursosPorCicloSemestreFacultad( int semestre, int facultad, int especialidad, String ciclo, String turno);
    
    public List<BeanReporte> listarHorarioPorSemestreTurno(int semestre,int turno);
    
    public List<BeanReporte> listarHorarioLabCopadoPorTurno(int turno);
    
    public List<BeanReporte> listarDisponibilidadDocentePorCicloSemestreFacultad( int semestre, int facultad, int especialidad, String ciclo, int turno,String tur_desc);
     
    public List<BeanReporte> cantidadCursosAperturados(  String ciclo,int semestre,int especialidad, String tur_desc );
    
    public List<BeanReporte> cantidadCursosAsignados(  String ciclo,int semestre,int especialidad, String tur_desc );
    
    public List<BeanReporte> cantidadDocentesDisponibilidad(  String ciclo,int semestre,int especialidad, int tur_id ,String tur_desc);
    
    public List<BeanReporte> cantidadCursosSinDisponibilidad(String ciclo, int semestre, int especialidad, int tur_id, String tur_desc);
    
    
    public boolean estadoCursoEntreDocentesDisponibles(String ciclo, int semestre, int especialidad, int tur_id, String tur_desc);
}
