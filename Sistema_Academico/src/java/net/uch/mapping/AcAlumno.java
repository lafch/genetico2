package net.uch.mapping;

import net.uch.mapping.base.BaseAcAlumno;



public class AcAlumno extends BaseAcAlumno {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcAlumno () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcAlumno (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcAlumno (
		java.lang.Integer id,
		net.uch.mapping.AcEspecialidad esp) {

		super (
			id,
			esp);
	}

/*[CONSTRUCTOR MARKER END]*/


}