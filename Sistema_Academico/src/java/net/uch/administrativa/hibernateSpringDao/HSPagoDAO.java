package net.uch.administrativa.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.AdComprobantePago;
import net.uch.mapping.AdPago;
import net.uch.mapping.AdSaldo;
import net.uch.mapping.ClAlumnoTarifa;
import org.springframework.dao.DataAccessException;

public interface HSPagoDAO {

    public int insertarPago(AdComprobantePago compag) throws DataAccessException;

    public void insertarComprobantesPago(List<AdComprobantePago> compags) throws DataAccessException;

    public void eliminarPago(String id) throws DataAccessException;

    public List seleccionarMostrarPagos(int id) throws DataAccessException;

    public List seleccionarMostrarPagosMora(int id) throws DataAccessException;

    public List seleccionarMostrarPagosMatExp(int id) throws DataAccessException;

    public List seleccionarMostrarPagosExternos(int id, Date desde, Date hasta, int conpag_id) throws DataAccessException;

    public void actualizarPago(AdPago pag) throws DataAccessException;

    public void insertarComprobantePago(AdComprobantePago con) throws DataAccessException;

    public void insertarSaldo(AdSaldo sal) throws DataAccessException;

    public String seleccionarUnComprobantePago(String comp) throws DataAccessException;

    public void ActualizarComprobantePago(int id, String num_com, Date fecha, String proc, String tipCom, String nroVou, Date fecVou, String codAge) throws DataAccessException;

    public List seleccionarMostrarPagosDet(int id) throws DataAccessException;

    public String MaximoNroComprobante(String serie) throws DataAccessException;

    public List seleccionarPagosExternos(int alu_id, int conpag_id);

    public void anularComprobantePago(int compag_id);

    public void eliminarComprobantePago(int compag_id);

    public List seleccionarUnComprobantePago(int comp_id);

    /**
     * Lista los pagos que ha realizado el alumno
     * Caso 0 - el estado de la boleta es 'valido' -> 037001
     * @param alutarId
     * @return
     */
    public List<AdPago> listaPagosAlumno(int alutarId, int caso);

    /**
     * Cambia el estado del candado
     * @param alutarId
     * @param estado
     */
    public void actualizarEstadoPago(int alutarId, String estado);

    /**
     * Devuelve el monto del ClAlumnoTarifa
     * @param alutarID
     * @return
     */
    public ClAlumnoTarifa getClAlumnoTarifa(int alutarID);

    public List<AdPago> listaPagosAlumnoLibre(int alutarId);
    public List<AdPago> listarPagosporAlumno(int alu_id);
   /*nuevo codigo*/
    public int listarPagosCancelados(int alutarId);
    /*cerramos nuevo codigo*/
}
