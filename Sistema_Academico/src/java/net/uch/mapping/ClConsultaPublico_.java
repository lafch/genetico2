/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.mapping;

import java.io.Serializable;
import java.util.Date;
import net.uch.mapping.base.BaseClConsultaPublico_;


/**
 *
 * @author Simion Richa R B
 */
public class ClConsultaPublico_ extends BaseClConsultaPublico_ implements Serializable {
    public ClConsultaPublico_(){}

    public ClConsultaPublico_( ClModulo clModulo, int med_id, int detmed_id, String con_observacion_medio, Date con_fecha_contacto,
                                    String con_procedencia, String con_observacion_registro, int usu_id, int alu_id, String conActivo, int plaId, String conEstadoMatricula){
        
        super(clModulo, med_id, detmed_id, con_observacion_medio, con_fecha_contacto,
                con_procedencia, con_observacion_registro, usu_id, alu_id, conActivo, plaId,conEstadoMatricula);

    }
}
