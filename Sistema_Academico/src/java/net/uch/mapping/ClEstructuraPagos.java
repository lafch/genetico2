package net.uch.mapping;

import java.util.Set;

import net.uch.mapping.base.BaseClEstructuraPagos;

/**
 * ClEstructuraPagos entity. @author MyEclipse Persistence Tools
 */
public class ClEstructuraPagos extends BaseClEstructuraPagos implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ClEstructuraPagos() {
	}

	/** full constructor */
	public ClEstructuraPagos(ClArbolAcademico clArbolAcademico, String estpagNombre,
                        String estpagAbrev, String estpagActivo, Set clEstructuraPagosDetalles, String estpagTipo) {
		super(clArbolAcademico, estpagNombre, estpagAbrev, estpagActivo, clEstructuraPagosDetalles,estpagTipo);
	}

}
