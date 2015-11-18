package net.uch.mapping;

import net.uch.mapping.base.BaseAcNota;



public class AcNota extends BaseAcNota {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcNota () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcNota (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcNota (
		java.lang.Integer id,
		net.uch.mapping.AcAlumno alu,
		net.uch.mapping.AcSisEvaPersonalizado sisevaPer) {

		super (
			id,
			alu,
			sisevaPer);
	}

/*[CONSTRUCTOR MARKER END]*/


}