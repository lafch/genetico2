/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanInfoMatricula;
import net.uch.mapping.ClConsultaPublico;

/**
 *
 * @author Simion Richa R B
 */
public interface HSConsultaPublico {
    public void agregarConsultaPublico(ClConsultaPublico consultaPublico);
    public List<ClConsultaPublico> listarxAlumnoConsultaPublico(int alu_id);
    public void modificarCampoMatricula(int con_id);

    public List<ClConsultaPublico> listarPublicoConsulta(int are_id, int mod_id,int hor_id,int usu_id,Date fecha_ini, Date fecha_fin,String con_procedencia);
    public void modificarConsultaPublico(int con_id, String con_observacion, Date con_fecha_contacto);

    public List<BeanInfoMatricula> cantidadMatriculadosUsuario(Date fecha_ini, Date fecha_fin);
    public List<ClConsultaPublico> listadoInscritosPorUsuario(int usu_id, Date fechaIni, Date fechaFin, String tipo);

    public ClConsultaPublico seleccionarConsultaPublico(int con_id);

    public List<ClConsultaPublico> seleccionarConsultaPublicoporFecha(Date fecha);
    
}
