package net.uch.mapping;

import net.uch.mapping.base.BaseAdEstructuraPagos;



public class AdEstructuraPagos extends BaseAdEstructuraPagos {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AdEstructuraPagos () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AdEstructuraPagos (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AdEstructuraPagos (
		java.lang.Integer id,
		net.uch.mapping.AcSemestre sem) {

		super (
			id,
			sem);
	}

/*[CONSTRUCTOR MARKER END]*/


}