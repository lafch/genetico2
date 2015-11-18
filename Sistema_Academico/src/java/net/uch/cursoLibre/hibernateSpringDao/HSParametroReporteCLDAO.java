/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbParametroReportes;

/**
 *
 * @author cesardl
 */
public interface HSParametroReporteCLDAO {

     public List<TbParametroReportes> getListParam(int usu_id);
}
