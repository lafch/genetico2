package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcSisEvaDetalle;
import net.uch.mapping.AcSisEvaPersonalizado;
import net.uch.mapping.AcSisEvaluacion;
import org.springframework.dao.DataAccessException;

public interface HSSistemaEvaluacionDAO {

    public void insertarSistemaEvaluacion( AcSisEvaluacion siseva ) throws DataAccessException;

    public List seleccionarSistemaEvaluacion( String descripcion ) throws DataAccessException;

    public List seleccionarSistemaEvaluacion( int id ) throws DataAccessException;

    public void actualizarSistemaEvaluacion( AcSisEvaluacion siseva ) throws DataAccessException;

    public void eliminarSistemaEvaluacion( int id ) throws DataAccessException;

    public List seleccionarComboSistemaEvaluacion() throws DataAccessException;

    public List<AcSisEvaPersonalizado> listarSisEvaPerPlantilla( int iSisevaId, int iSisevaDetId );

    public AcSisEvaDetalle seleccionarSisEvaDetetallePorId( int iSisEvaDetId );
}
