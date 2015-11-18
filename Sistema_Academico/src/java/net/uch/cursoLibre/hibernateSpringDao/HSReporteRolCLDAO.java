/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbReporte;
import net.uch.mapping.TbReporteRol;

/**
 *
 * @author cesardl
 */
public interface HSReporteRolCLDAO {

    public List<TbReporteRol> getListRoles(int rep_id);

    public List<TbReporte> getReporte();

}
