package net.uch.cursoLibre.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.ClMatricula;
import net.uch.mapping.ClMatriculaTaller;

public interface HSMatriculaCLDAO {

    public void insertarMatricula(ClMatricula matricula) throws Exception;

    public void insertarMatriculas(List<ClMatricula> matriculas) throws Exception;

    public void anularMatricula(int mat_id) throws Exception;
    
    public void anularMatriculaDevolucion(int mat_id) throws Exception;
    

    /**
     * caso 0 -> total matriculados, caso 1 -> lista alumnos
     * @param sec_id
     * @param talape_id
     * @param caso
     * @return
     **/
    public List<ClMatricula> totalMatriculadosSeccion(int sec_id, int talape_id, int caso);

    /**
     * Muestra a las matriculas del alumno de acuerdo a su Id y al ClTaller
     * @param alu_id
     * @param tal_id
     * @return
     */
    public List<ClMatricula> listarMatriculado_Taller(int alu_id, int tal_id);

    /**
     * Revisa si el alumno esta matriculado en la seccion seleccionada
     * @param alu_id
     * @param sec_id
     * @return Consulta a la base de datos y pregunta si el total de registros es '0'
     */
    public boolean estaMatriculado(int alu_id, int sec_id);

    /**
     * Actualiza el estado de la matricula de Pre-matricula a Regular
     * @param mat_tipo
     * @param mat_id
     * @return
     */
    public int actualizarEstadoMatricula(String mat_tipo, Integer mat_id);

    public List<ClMatriculaTaller> listadoMatriculados(int usu_id);

    public List<ClMatriculaTaller> cantidadMatriculados(Date fechIni, Date fechaFin);
    
    public ClMatricula BuscarMatricula(int sec_id, int alu_id);
    
    public void cambiarSeccionMatriculaTaller ( int mat_id, int sec_id ) throws Exception;

    public ClMatricula buscarPorMatId( Integer matId );
    
    public ClMatricula buscarPorMatHabilitar( Integer matId );
}
