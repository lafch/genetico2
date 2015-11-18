package net.uch.mapping;

import java.sql.Time;

import net.uch.mapping.base.BaseClHoraria;

/**
 * ClHoraria entity.
 * @author MyEclipse Persistence Tools
 */
public class ClHoraria extends BaseClHoraria implements java.io.Serializable {

    // Constructors
    /** default constructor */
    public ClHoraria() {
    }

    /** minimal constructor */
    public ClHoraria(AcAula acAula, ClSeccion clSeccion, AcDocente acDocente) {
        super(acAula, clSeccion, acDocente);
    }

    /** full constructor */
    public ClHoraria(AcAula acAula, ClSeccion clSeccion, AcDocente acDocente,
            String horDia, Time horHini, Time horHfin, String horTipoClase,
            String horActivo,ClSisEvaDetalle clSisEvaDetDocente ) {
        super(acAula, clSeccion, acDocente, horDia, horHini, horHfin,
                horTipoClase, horActivo,clSisEvaDetDocente);
    }
}
