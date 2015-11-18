package net.uch.mapping;

import net.uch.mapping.base.BaseAcFacultad;



public class AcFacultad extends BaseAcFacultad {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcFacultad () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcFacultad (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcFacultad (
		java.lang.Integer id,
		java.lang.String facActivo) {

		super (
			id,
			facActivo);
	}

/*[CONSTRUCTOR MARKER END]*/


}