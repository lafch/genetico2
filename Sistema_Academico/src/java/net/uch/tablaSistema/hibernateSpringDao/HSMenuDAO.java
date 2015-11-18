/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcAula;
import net.uch.mapping.TbMenu;
import net.uch.mapping.TbMenuRol;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author cesardl
 */
public interface HSMenuDAO {

    public List<TbMenu> seleccionToolBar(int rol_id);

    public List<TbMenu> seleccionHijos(int men_id, int rol_id);
    
    public List seleccionarMenuI();
    
    public List seleccionarMenuHijo(int men_id, String nivel);
    
    public List seleccionarMenuHijos(int men_id, String nivel);
    
    public String hijosMenuEliminar(String men_id);
    
    public List paginas();
    
    public List<TbMenu> menuPadres();
    
    public List<TbMenu> niveles(int id_padre);
    
    public String buscarMenu(int men_id);
    
    public String hijosMenu(int men_id);
    
    public String codigoMenu();
    
    public void insertarMenu( TbMenu menu ) throws DataAccessException;
    
    public void insertarActualizarMenu( TbMenu menu ) throws DataAccessException;
    
    public void eliminarMenu( String id ) throws DataAccessException;

    public void actualizarMenu( TbMenu aul ) throws DataAccessException;
    
    public boolean listaAgregarMenuParaRol(int menId,int nivel, int rolId);
    
    public List seleccionarMenuRol(int rolId,int menuId);
    
    public TbMenuRol  seleccionarMenuRolClase( int rolId,int menuId);
    
    public List seleccionarMenuId(int idMenu);
}
