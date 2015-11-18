/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClCurso;

/**
 *
 * @author cesardl
 */
public interface HSCursoCLDAO {

    public List<ClCurso> seleccionarCursos(int mod_id) throws Exception;

    public List verificarEliminarCurso(int cur_id) throws Exception;

    public void eliminarCurso(int cur_id) throws Exception;

    public void actualizarCurso(ClCurso curso) throws Exception;

    public void insertarCurso(ClCurso curso) throws Exception;

    public ClCurso buscarCurso(int cur_id) throws Exception;
}
