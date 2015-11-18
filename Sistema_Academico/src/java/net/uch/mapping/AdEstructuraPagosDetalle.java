package net.uch.mapping;

import net.uch.mapping.base.BaseAdEstructuraPagosDetalle;



public class AdEstructuraPagosDetalle extends BaseAdEstructuraPagosDetalle {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AdEstructuraPagosDetalle () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AdEstructuraPagosDetalle (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AdEstructuraPagosDetalle (
		java.lang.Integer id,
		net.uch.mapping.AdEstructuraPagos estpag) {

		super (
			id,
			estpag);
	}

/*[CONSTRUCTOR MARKER END]*/


}