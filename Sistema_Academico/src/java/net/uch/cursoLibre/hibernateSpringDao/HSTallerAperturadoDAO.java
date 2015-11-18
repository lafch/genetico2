package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClTallerAperturado;

public interface HSTallerAperturadoDAO {

    public ClTallerAperturado seleccionarTallerAperturado(int talape_id);

    public List<ClTallerAperturado> seleccionarTalleresAperturados() throws Exception;

    public List<ClTallerAperturado> seleccionarTallerAperturadoPorCurso(int cur_id) throws Exception;

    public List<ClTallerAperturado> seleccionarTalleresAperturadosPorTaller(int tal_id);

    public List<ClTallerAperturado> seleccionarTalleresAperturadosPorModulo(int mod_id, int tal_id);

    public void insertar_ActualizarTallerAperturado(ClTallerAperturado talape) throws Exception;

    public void eliminarTallerAperturado(int talape_id);

    public List<ClTallerAperturado> seleccionarTallerAperturadoxModulo(int mod_id) throws Exception;

    // lo puse yo
    public List<ClTallerAperturado> seleccionarTallerxModulo(int mod_id) throws Exception;
    public List<ClTallerAperturado> listarTallerModulo(String _descripcion);
}
