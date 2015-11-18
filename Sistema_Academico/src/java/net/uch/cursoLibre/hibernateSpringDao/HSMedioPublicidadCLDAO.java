/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClMedioPublicidad;
import net.uch.mapping.ClMedioPublicidadDet;

/**
 *
 * @author richard
 */
public interface HSMedioPublicidadCLDAO {
    public List<ClMedioPublicidad> listarMedioPublicidad();
    public List<ClMedioPublicidad> listarMedioPublicidadDet(String tipo);
    public List<ClMedioPublicidadDet> listarMediopublicidadDeta_x_med(int med_id);
    public List<ClMedioPublicidadDet> listarMediopublicidadDeta();

    public void modificarMedioPublicidad(ClMedioPublicidad medio);
    public void agregarMedioPublicidad(ClMedioPublicidad medio);
    public List<ClMedioPublicidad> listarMedioPublicidad(String med_descrpcion);

    public ClMedioPublicidad seleccionarMedioPublicidad(int med_id);
    public ClMedioPublicidadDet seleccionarMedioPublicidadDetalle(int meddet_id);

    public void agregarMedioPublicidadDet(ClMedioPublicidadDet detalle);
    public void modificarMedioPublicidadDet(ClMedioPublicidadDet detalle);
    public void eliminarMedioPublicidadDet(int medDet_id);
    public void guardarMedioPublicidadDet(ClMedioPublicidadDet detalle);

    public void modificarMedioPublicidadsql(ClMedioPublicidad medio);

}
