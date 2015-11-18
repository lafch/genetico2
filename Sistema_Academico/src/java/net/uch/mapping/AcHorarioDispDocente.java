/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

import java.sql.Time;
import net.uch.mapping.base.BaseAcHorarioDispDocente;

/**
 *
 * @author LUIS
 */
public class AcHorarioDispDocente extends BaseAcHorarioDispDocente implements java.io.Serializable {
    // Constructors
    /** default constructor */
    public AcHorarioDispDocente() {
    }
    /** full constructor */
    public AcHorarioDispDocente(AcDocente acDocente,
            String horDia, AcTurnoDetalle acTurnoDetalle,
            String horActivo ) {
        super(acDocente, horDia, acTurnoDetalle, horActivo );
    }
    
}
