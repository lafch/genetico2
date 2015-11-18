/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

/**
 *
 * @author richard
 */
import java.util.List;
import net.uch.mapping.AcNota;
import net.uch.mapping.AcNotasTemporales;

public interface HSNotasTemporalesDAO {

    public List<AcNotasTemporales> listarNotasTemporales(int sec_id, int siseva_per_id);

    public AcNota buscarNotax_notaTemporal(int alu_id, int sec_id, int siseva_per_id);

    public void agregarAcNotas(AcNota nota);

    public void modificarAcNotas(AcNota nota);

    public List listarAlumnos_AcNota(int sec_id, int siseva_per_id);

    public List listarAlumnos_acNotaTemporal(int sec_id, int siseva_per_id);
}
