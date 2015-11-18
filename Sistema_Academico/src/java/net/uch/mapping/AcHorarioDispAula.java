/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

import java.sql.Time;
import net.uch.mapping.base.BaseAcHorarioDispAula;

/**
 *
 * @author LUIS
 */
public class AcHorarioDispAula extends BaseAcHorarioDispAula implements java.io.Serializable {
    // Constructors
    /** default constructor */
    public AcHorarioDispAula() {
    }
    /** full constructor */
    public AcHorarioDispAula(AcAula acAula,
            String horDia, Time horHini, Time horHfin,
            String horActivo ) {
        super(acAula, horDia, horHini, horHfin, horActivo );
    }
}
