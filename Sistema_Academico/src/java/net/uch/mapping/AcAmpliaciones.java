package net.uch.mapping;

import net.uch.mapping.base.BaseAcAmpliaciones;



public class AcAmpliaciones extends BaseAcAmpliaciones {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcAmpliaciones () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcAmpliaciones (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcAmpliaciones (
		java.lang.Integer id,
		net.uch.mapping.AcAlumno alu) {

		super (
			id,
			alu);
	}

/*[CONSTRUCTOR MARKER END]*/


}