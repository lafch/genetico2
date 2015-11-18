/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClPlantillaHorario;
import net.uch.mapping.ClPlantillaHorarioDet;

/**
 *
 * @author richard
 */
public interface HSPlantillaHorarioCLDAO {

    public List<ClPlantillaHorario> listarPlantilla(String pla_descripcion);

    public ClPlantillaHorario seleccionarPlantilla(int pla_id);
    public void agregarPlantilla(ClPlantillaHorario plantilla);
    public void modificarPlantilla(ClPlantillaHorario plantilla);
    public void eliminarPlantilla(int pla_id);
    public List<ClPlantillaHorario> listarPlantilla();
    public List<ClPlantillaHorarioDet> listarDetallePlantilla(int pla_id);

}
