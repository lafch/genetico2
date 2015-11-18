package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClModulo;

public interface HSModuloDAO {

    public List seleccionarModulos(int are_id, String descripcion) throws Exception;

    public void insertarModulo(ClModulo modulo) throws Exception;

    public void eliminarModulo(int mod_id) throws Exception;

    public void actualizarModulo(ClModulo modulo) throws Exception;

    public ClModulo buscarModulo(int mod_id) throws Exception;

    public List listarModulos() throws Exception;

    public List listarModulosAperturados();
}
