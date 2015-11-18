package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClArea;

public interface HSAreaDAO {

    public List seleccionarArea(String descripcion) throws Exception;

    public void eliminarArea(int id) throws Exception;

    public void insertarArea(ClArea area) throws Exception;

    public void actualizarArea(ClArea area) throws Exception;

    public ClArea buscarArea(int id) throws Exception;
}