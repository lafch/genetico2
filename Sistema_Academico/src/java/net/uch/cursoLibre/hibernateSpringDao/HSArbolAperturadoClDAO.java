package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClArbolAperturado;

public interface HSArbolAperturadoClDAO {

    public ClArbolAperturado seleccionarArbTallerAperturado( int talape_id );

    public List<ClArbolAperturado> seleccionarArbTalleresAperturados() throws Exception;

    public List<ClArbolAperturado> seleccionarArbTalleresAperturadosPorTaller( int tal_id );

    public void insertar_ActualizarArbTallerAperturado( ClArbolAperturado talape ) throws Exception;

    public void eliminarArbTallerAperturado( int talape_id );

    public List<ClArbolAperturado> cargarArbTallerAperturado( int tal_id );

    public List<ClArbolAcademico> findArbTallerXModulo( int mod_id, int tal_id );
    
    public List<ClArbolAcademico> findArbTallerXModuloNuevo( int iModId);
}
