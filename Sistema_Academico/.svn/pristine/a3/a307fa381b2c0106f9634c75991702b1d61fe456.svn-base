/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanCLAsistencia;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;

/**
 *
 * @author LUIS
 */
public interface HSAsisteciaCLDAO {
    public List<BeanReporte> listarSesionesPorSecId(int iSecId);
    public List<BeanCLAsistencia> listarAsitenciaPorSecId(int iSecId);
    public List<BeanCLAsistencia> listarAsitenciaPorAlumId(int iSecId, boolean blModular, int iAlumId);
    public List<BeanReporte>      listarAsitenciaPorSecIdReporte( int iSecId );
    public List<BeanReporte>      listarAsitenciaPorAlumIdReporte( int iSecId, int iAlumId );
}
