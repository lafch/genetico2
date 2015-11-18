package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanClEstructuraPagosDetalleSecuencia;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClEstructuraPagos;
import net.uch.mapping.ClEstructuraPagosDetalle;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessResourceFailureException;

public interface HSEstructuraPagoDAO {

    public List seleccionarEstructuras( String descripcion, int mod_id ) throws Exception;

    public List seleccionarDetalle( int estpag_id ) throws Exception;

    public List<ClEstructuraPagos> seleccionarEstructurasXModulo( int mod_id );

    public List<ClEstructuraPagos> seleccionarEstructurasPagos( int mod_id );

    public List seleccionarEstructurasXTaller( int tal_id );

    public void insertarEstructuraPagos( ClEstructuraPagos estructura ) throws Exception;

    public void actualizarEstructuraPagos( ClEstructuraPagos estructura ) throws Exception;

    public void eliminarEstructuraPagos( int estpag_id ) throws Exception;

    public ClEstructuraPagos buscarEstructuraPagos( int estpag_id ) throws Exception;

    public void insertarEstructuraPagoDetalle( ClEstructuraPagosDetalle estDetalle ) throws Exception;

    public void actualizarEstructuraPagoDetalle( ClEstructuraPagosDetalle estDetalle ) throws Exception;

    public void eliminarEstructuraPagosDetalle( int estpagdet_id ) throws Exception;

    public List verificarEliminar( int estpag_id ) throws Exception;

    public List verificarEliminarDetalle( int estpagdet_id ) throws Exception;

    public List<ClEstructuraPagosDetalle> listaEstructurasExistentes( int mod_id, int estpag_id, int tal_id )
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException;

    public List<ClEstructuraPagosDetalle> listarEstructuraDetalle( int estpag_id );
    
    public List<ClEstructuraPagosDetalle> listarEstructuraDetalleBloque( int estpag_id );

    public List<ClAlumnoTarifa> listaAlumnoTarifaExistentes( int aluId, int modId, int estpag_id )
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException;

    public ClArbolAcademico buscarModulo( int mod_id ) throws Exception;

    public ClEstructuraPagosDetalle buscarEstructuraPagosDet( int estpagdet_id ) throws Exception;

    public BeanClEstructuraPagosDetalleSecuencia buscarEstPagDetSec( int estPagDetIniId, int estPagDetContId );

    public int insertarActualizarContinuacionEstPagDet( BeanClEstructuraPagosDetalleSecuencia epds ) throws Exception;

    public List<BeanClEstructuraPagosDetalleSecuencia> listarEstPagDetSecuencia( int modIniId, int estPagIniId );
}
