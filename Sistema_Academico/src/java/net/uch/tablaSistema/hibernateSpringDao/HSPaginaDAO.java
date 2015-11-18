package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcHorarioDispAula;
import net.uch.mapping.AcHorarioDispDocente;
import net.uch.mapping.AcPagina;
import org.springframework.dao.DataAccessException;

public interface HSPaginaDAO {

    public void insertarPagina( AcPagina pag ) throws DataAccessException;

    public List seleccionarPagina( String descripcion, String url) throws DataAccessException, java.sql.SQLException;

    public void eliminarPagina( String id ) throws DataAccessException;

    public void actualizarPagina( AcPagina pag ) throws DataAccessException;

    public List seleccionarPagina() throws Exception;
    
    public AcPagina buscarPagina( int pag_id );

}
