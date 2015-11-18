package net.uch.academica.hibernateSpringDao;

import java.io.InputStream;
import java.util.List;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcSemestre;
import net.uch.mapping.Sp_creditosPorCicloByAluId;
import net.uch.mapping.Sp_cursosPorAlumno;
import org.springframework.dao.DataAccessException;

public interface HSAlumnoDAO {

    public void insertarAlumno(AcAlumno alu) throws DataAccessException;

    public List seleccionarAlumno(String codigo, String paterno, String materno, String nombre, int facu, int espe) throws DataAccessException, java.sql.SQLException;

    public void eliminarAlumno(String id) throws DataAccessException;

    public void actualizarAlumno(AcAlumno alu) throws DataAccessException;

    //public List seleccionarAlumnoEstPago(int id_esp, String tipo, int sem_id) throws DataAccessException;
    public List seleccionarAlumnoEstPago(int id_esp, String tipo, int sem_id,int sem_id_actual) throws DataAccessException;
    
    public List seleccionarAlumnoCliente(String buscar) throws DataAccessException;

    public AcAlumno seleccionarAlumno(int id) throws Exception;

    public List seleccionarUnAlumno(int id) throws DataAccessException;

    public List seleccionarEstPagx() throws DataAccessException, java.sql.SQLException;

    public List seleccionarAlumnoEstPagoUnico(int id) throws DataAccessException;

    public String MaximoCodigo() throws DataAccessException;

    public InputStream imprimirAsistencia();

    public List seleccionarAlumnoSeccion(int sec_id);

    public List listarAlumnoxSeccion(int sec_id);

    public AcAlumno buscarAlumnoCodigo(String codigo);

    public List<AcAlumno> listaCoincidencias(String codigo, String paterno, String materno, String nombre);

    public List<AcAlumno> listarAlumnosporDato(String dato);

    public int buscarAlumnoFicha(int alu_id);
    
    public List<Sp_creditosPorCicloByAluId> creditosPorCicloAlumno(Integer aluId);
    public List<Sp_cursosPorAlumno> cursosPorAlumno(Integer aluId);
    
    public List<AcAlumno> buscarAlumnos(AcAlumno acAlumno);
    
}
