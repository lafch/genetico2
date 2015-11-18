/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClPublicoConsultaDetalle;

/**
 *
 * @author Jose Tejada
 */
public interface HSPublicoConsultaDetalleCLDAO {

    public List<ClPublicoConsultaDetalle> listarPublicoConsultaDetalle(int consultaId);
    public void guardarConsultaDetalle(ClPublicoConsultaDetalle clPublicoConsultaDetalle);
}
