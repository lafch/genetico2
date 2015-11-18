package net.uch.mapping;

import net.uch.mapping.base.BaseAcAsistencia;



public class AcAsistencia extends BaseAcAsistencia {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcAsistencia () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcAsistencia (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcAsistencia (
		java.lang.Integer id,
		net.uch.mapping.AcAlumno alu) {

		super (
			id,
			alu);
	}

/*[CONSTRUCTOR MARKER END]*/


}