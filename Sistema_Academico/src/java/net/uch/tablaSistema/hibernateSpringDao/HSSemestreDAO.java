package net.uch.tablaSistema.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.AcSemestre;
import org.springframework.dao.DataAccessException;

public interface HSSemestreDAO {

    public void insertarSemestre(AcSemestre se) throws DataAccessException;

    public List seleccionarSemestre(String codigo, String nombre) throws DataAccessException, java.sql.SQLException;

    public void eliminarSemestre(String id) throws DataAccessException;

    public void actualizarSemestre(AcSemestre se) throws DataAccessException;

    public String seleccionarSemestreVigente(int valor);

    public List seleccionarSemestreVigente();
    
    public List seleccionarSemestreVigenteH();
    public List seleccionarSemestreVigenteH(int idSemestre);
    public List seleccionarSemestreActivo();

    public List seleccionarVigente();

    public List seleccionarSemestreActual();

    public List listarSemestreId(int valor);

    public String seleccionarSemestreAct();

    public AcSemestre getSemestre(Integer sem_id);
    
    public List<AcSemestre> listarSemestreSuperiores(Date Fecha_inicio);
}
