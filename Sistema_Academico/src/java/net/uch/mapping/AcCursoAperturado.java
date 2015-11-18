package net.uch.mapping;

import net.uch.mapping.base.BaseAcCursoAperturado;



public class AcCursoAperturado extends BaseAcCursoAperturado {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcCursoAperturado () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcCursoAperturado (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcCursoAperturado (
		java.lang.Integer id,
		net.uch.mapping.AcSemestre sem) {

		super (
			id,
			sem);
	}

/*[CONSTRUCTOR MARKER END]*/


}