package net.uch.mapping;

import net.uch.mapping.base.BaseAcSeccion;



public class AcSeccion extends BaseAcSeccion {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcSeccion () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcSeccion (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcSeccion (
		java.lang.Integer id,
		net.uch.mapping.AcCursoAperturado curape) {

		super (
			id,
			curape);
	}

/*[CONSTRUCTOR MARKER END]*/


}