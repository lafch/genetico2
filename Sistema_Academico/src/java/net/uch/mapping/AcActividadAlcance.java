package net.uch.mapping;

import net.uch.mapping.base.BaseAcActividadAlcance;

/**
 * AcActividadAlcance entity. @author MyEclipse Persistence Tools
 */
public class AcActividadAlcance extends BaseAcActividadAlcance implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AcActividadAlcance() {
	}

	/** minimal constructor */
	public AcActividadAlcance(AcCalendarioActividades acCalendarioActividades) {
		super(acCalendarioActividades);
	}

	/** full constructor */
	public AcActividadAlcance(AcCalendarioActividades acCalendarioActividades,
			String actalcAlcance, String actalcActivo) {
		super(acCalendarioActividades, actalcAlcance, actalcActivo);
	}

}
