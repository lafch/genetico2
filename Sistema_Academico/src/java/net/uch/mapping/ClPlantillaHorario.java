package net.uch.mapping;

// Generated by MyEclipse Persistence Tools

import java.util.Date;
import java.util.Set;
import net.uch.mapping.base.BaseClPlantillaHorario;

/**
 * ClPlantillaHorario generated by MyEclipse Persistence Tools
 */
public class ClPlantillaHorario extends BaseClPlantillaHorario implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ClPlantillaHorario() {
	}

	/** minimal constructor */
	public ClPlantillaHorario(Integer plaId) {
		super(plaId);
	}

	/** full constructor */
	public ClPlantillaHorario(Integer plaId, String plaDescripcion,
			Date plaFechaCreacion, String palTurno, Integer usuId,
			String plaActivo, Set clPlantillaHorarioDets) {
		super(plaId, plaDescripcion, plaFechaCreacion, palTurno, usuId,
				plaActivo, clPlantillaHorarioDets);
	}

}