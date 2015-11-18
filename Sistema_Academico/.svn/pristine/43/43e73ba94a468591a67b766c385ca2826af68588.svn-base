package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.academica.managedBeans.beans.BeanCursosMatricular;
import net.uch.mapping.AcMatricula;
import net.uch.mapping.AcMatriculaCurso;
import net.uch.mapping.AcPlanCurso;
import net.uch.mapping.AcSemestre;
import org.springframework.dao.DataAccessException;

public interface HSMatriculaDAO {

    public List seleccionarcursosAprobados(int alu_id) throws DataAccessException;

    public List<AcPlanCurso> seleccionarcursosparaIngresantes(int alumno_id) throws Exception;

    public int numeroMatriculadosporSeccion(int sec_id);
    public int numeroMatriculadosporSeccionFun(int sec_id);

    public int numeroMatriculadosporSeccion_sin_alumno(int sec_id, int alu_id);
    
    public int numeroMatriculadasId(int sem_id, int alu_id);

    public void registrarMatricula(AcMatricula matricula, int tipoMatricula);
    
    public void registrarMatricula(AcMatricula matricula,AcSemestre semestre, int tipoMatricula);

    public void imprimirMatricula();

    public List seleccionarMatriculas(int sec_id);

    public List listarCursosMatriculados(int id, int semestre);

    public List listarCursosPorMatricula(int mat_id);

    public void EliminarMatricula(int mat_id);

    public void RectificarMatricula(int mat_id);

    public void ReservarMatricula(int mat_id);
    
    public void RetiroCiclo(int mat_id);
    
    public void RetiroUniveridad(int mat_id);

    public void RectificarPreMatricula(int mat_id);

    public List GetBloqueoWeb(int sec_id);

    public void ChangeBloqueoWeb(int sec_id);

    public List seleccionarMatriculaLectiva(int alu_id, int sem_id);

    public List seleccionarMatricularRegular(int alu_id, int sem_id);
    
    public int seleccionarIdMatricularRegular(int alu_id, int sem_id);

    public List seleccionarMatriculaReserva(int alu_id, int sem_id);

    public List seleccionarPrematricula(int alu_id, int sem_id);

    public AcMatricula seleccionarMatriculaAlumnoConvalidacion(int alu_id);

    public void insertarMatriculaCurso(List<AcMatriculaCurso>  acMatriculaCurso);

    public void eliminarMatriculaCurso(AcMatriculaCurso acMatriculaCurso);
    
    public List<BeanCursosMatricular> listarCursosAmatricular(int alu_id, int sem_id);
}
