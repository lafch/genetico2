 package net.uch.mapping;

import java.util.Date;

import net.uch.mapping.base.BaseAcHorarioArea;

/**
 * AcHorarioArea entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class AcHorarioArea extends BaseAcHorarioArea implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AcHorarioArea() {
	}

	/** minimal constructor */
	public AcHorarioArea(Integer horareId) {
		super(horareId);
	}

	/** full constructor */
	public AcHorarioArea(Integer horareId, AcArea acArea,
			AcTurnoDetalle acTurnoDetalle, String horareDia,
			String horareActivo, Date horareCreacion, Integer docId,
			Integer horareOrden) {
		super(horareId, acArea, acTurnoDetalle, horareDia, horareActivo,
				horareCreacion, docId, horareOrden);
	}

}
