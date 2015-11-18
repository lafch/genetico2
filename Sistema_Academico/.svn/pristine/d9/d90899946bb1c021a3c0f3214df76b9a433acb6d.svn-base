/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.AcCalendario;

/**
 *
 * @author cesardl
 */
public interface HSActividadDAO {

    public void insertarActividad(AcCalendario calendario);

    public void actualizarActividad(AcCalendario calendario);

    public List<AcCalendario> seleccionarActividadDetalle(Date calFecha);

    public AcCalendario seleccionarActividadId(int calId);

    public void actualizarEstadoPublicado(int id, String publicado);

    public void eliminarActividad(int id);

    public void eliminarAlcance(int id);
}
