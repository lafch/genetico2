package net.uch.mapping;

import net.uch.mapping.base.BaseAdAlumnoTarifa;



public class AdAlumnoTarifa extends BaseAdAlumnoTarifa {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AdAlumnoTarifa () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AdAlumnoTarifa (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AdAlumnoTarifa (
		java.lang.Integer id,
		net.uch.mapping.AcAlumno alu,
		net.uch.mapping.AdConceptoPago conpag) {

		super (
			id,
			alu,
			conpag);
	}

/*[CONSTRUCTOR MARKER END]*/


}