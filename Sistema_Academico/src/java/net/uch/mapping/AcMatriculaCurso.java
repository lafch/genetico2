package net.uch.mapping;

import net.uch.mapping.base.BaseAcMatriculaCurso;



public class AcMatriculaCurso extends BaseAcMatriculaCurso {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcMatriculaCurso () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcMatriculaCurso (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcMatriculaCurso (
		java.lang.Integer id,
		net.uch.mapping.AcMatricula mat,
		net.uch.mapping.AcSeccion sec) {

		super (
			id,
			mat,
			sec);
	}

/*[CONSTRUCTOR MARKER END]*/


}