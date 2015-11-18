/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcImportacionNotas;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author richard
 */
public interface HSImportacionNotasDAO {

    public List listarSistemaEvaluacionPersonalizado_x_seccion(int sec_id) throws DataAccessException;

    public List listarImportacionNota_x_seccion(int sec_id, int per_id) throws DataAccessException;

    public void agregarImportacionNota(AcImportacionNotas notas) throws DataAccessException;

    public void actualizarImportacionNota(AcImportacionNotas notas) throws DataAccessException;
}
