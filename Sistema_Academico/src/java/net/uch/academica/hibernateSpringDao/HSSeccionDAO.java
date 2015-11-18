package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcSeccion;
import net.uch.mapping.Sp_convalidacion_cursos;
import org.springframework.dao.DataAccessException;

public interface HSSeccionDAO {

    public List seleccionarSecciones(int curape) throws DataAccessException;

    public List obtenerSecciones(int plancur_id, int semestre_id) throws DataAccessException;

    public List seleccionarSeccion(int sec_id);
    
    public AcSeccion buscarSeccionID( int secId ) throws DataAccessException;

    public List listarSecciones(int esp_id, int sem_id);
    
    public List listarCursosporSeccion(String sec_nombre, int sem_id);
    
    public List listarSeccionesAdional(int esp_id, int sem_id);

    public List listarCursosporSeccionAdicional(String sec_nombre, int sem_id);

    public List<Sp_convalidacion_cursos> listaConvalidacion(int esp_id, int alu_id);
     public List obtenerSeccionesCurape(int curapeId, int semestre_id);
}
