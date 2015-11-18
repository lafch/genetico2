/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbMenu;
import net.uch.mapping.TbMenuRol;
import net.uch.mapping.TbRoles;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author LUIS
 */
public interface HSRolDAO {

    /*public String getListarRolPorId(int rol_id);*/
    public List<TbRoles> getListarRolPorId2(int rol_id);

    public List<TbRoles> getListarRol(int iRolId);

    public TbRoles traerRolPorId(int iRolId);

    public List seleccionarRoles();

    public List<TbRoles> getRoles();

    public void insertarRol(TbRoles rol) throws DataAccessException;

    public void actualizarMenu(TbRoles rol) throws DataAccessException;

    public void eliminarRol(String id) throws DataAccessException;
    
    public int numeroMenu(int id);
    
    public List<TbMenu> menuPadre(int idrol);
    
    public List<TbMenu> menuHijos(int idrol);
    
    public List<TbMenu> menuPadreActivo(int idrol);
    
    public List<TbMenu> menuHijosActivo(int idrol);
    
    public List<TbMenu> listarArbolPorPadre(int idPadre, int idRol);
    
    public void insertarMenuRol(TbMenuRol menuRol);
    
    

}
