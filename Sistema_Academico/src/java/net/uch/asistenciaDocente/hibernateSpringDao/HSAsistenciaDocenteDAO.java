/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.asistenciaDocente.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.asistenciaDocente.managedBeans.beans.AsistenciaDocenteBean;
import net.uch.mapping.AcArea;
import net.uch.mapping.AcSesionAsistencia;
import net.uch.mapping.AcSesionEfectivaAsisDoc;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author cesardl
 */
public interface HSAsistenciaDocenteDAO {

    public List buscarDocente_x_dato(String dato);
    
    public List buscarDocente_x_dato1(String dato);
    
    public List buscarDocente_x_dato2(String dato);

    public List<AcSesionAsistencia> listarSessionAsistencia_x_docenteFecha(int doc_id, Date fecha_ini, Date fecha_fin, String tipasis);
    
    public List listarAsistenciaDiaria_x_docenteFecha(int doc_id, Date fecha_ini, int tur_id, int fac_id);
    
    public List listarAsistenciaDiariaCl_x_docenteFecha(int doc_id, Date fecha_ini, int tur_id, String centro);

    public AcArea seleccionarArea(int sec_id);

    /**
     * Lista el detalle de la marcacion de un docente
     * @param ses_id
     * @return
     */
    public AcSesionEfectivaAsisDoc listaMarcadoDeAsistencia_x_Docente(int ses_id);

    public void ingresarAsisteanDocente(AcSesionEfectivaAsisDoc acSesionEfectivaAsisDoc);
    
    public void ingresarSesionAsistencia(AcSesionAsistencia sesasis);

    public AcSesionAsistencia listarSessionAsistencia_x_sesid(int ses_id);

    public List listarEspecialidad();

    /**
     * Actualizar los datos de la tabla AcSesionAsistencia
     * @param ses_id
     * @param doc_id
     * @param hora_ini
     * @param hora_fin
     * @param ses_tipo
     */
    public void actualizarSesionDocente(int ses_id, int doc_id, Date hora_ini, Date hora_fin, String ses_tipo);
    public void actualizarObservacionSesionDocente(int ses_id, String sesEstadoDocTipo,String sesObservacion);
    public void eliminarSesionDocente(String ses_id) throws DataAccessException;
}
