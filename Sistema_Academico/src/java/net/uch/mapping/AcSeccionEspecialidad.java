package net.uch.mapping;

import net.uch.mapping.base.BaseAcSeccionEspecialidad;

// Generated by MyEclipse Persistence Tools

/**
 * AcSeccionEspecialidad generated by MyEclipse Persistence Tools
 */
public class AcSeccionEspecialidad extends BaseAcSeccionEspecialidad
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public AcSeccionEspecialidad() {
	}

	/** minimal constructor */
	public AcSeccionEspecialidad(Integer secespId) {
		super(secespId);
	}

	/** full constructor */
	public AcSeccionEspecialidad(Integer secespId,
			AcEspecialidad acEspecialidad, String secespCodigo,
			String secespNombre, String secespCiclo, String secespActivo) {
		super(secespId, acEspecialidad, secespCodigo, secespNombre,
				secespCiclo, secespActivo);
	}

}
