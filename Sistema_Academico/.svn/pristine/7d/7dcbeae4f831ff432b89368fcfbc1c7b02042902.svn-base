/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.banco.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.Sp_conceptos_banco;
import net.uch.mapping.Sp_data_banco;
import net.uch.mapping.TbBanco;
import net.uch.mapping.TbBancoEntrada;
import net.uch.mapping.TbPagoBanco;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author richard
 */
public interface HSBancoDAO {

    /**
     * Captura la data del Banco para generar el archivo
     * @param ban_id
     * @return
     */
    public List<TbBanco> listarBanco(int ban_id);

    /**
     * Captura la informacion para los detalles
     * @return
     * @throws DataAccessException
     */
    public List<Sp_data_banco> listarDataBanco();

    /**
     * Captura los conceptos de los detalles para el archivo
     * @return
     */
    public List<Sp_conceptos_banco> listarConceptosBanco();

    /**
     * Salva en la base de datos la informacion que devuelve
     * el banco con los pagos de los alumnos
     * @param entradas
     */
    public void guardarRespuestaBanco(List<TbBancoEntrada> entradas);

    /**
     * Consulta si existe en la base de datos el numero de operacion
     * @param dato
     * @return
     */
    public List<TbPagoBanco> existeNroOperacion(String nroOperacion, Date fechaPago);

    /*public TbBancoEntrada existeNroOperacion(String nroOperacion, Date fechaPago);*/

    /**
     * Obtengo las agrupacion de ALUTAR_ID
     * @param codigo_grupo
     * @return
     */
    public List<TbPagoBanco> pagosBanco(Integer codigo_grupo);
}

