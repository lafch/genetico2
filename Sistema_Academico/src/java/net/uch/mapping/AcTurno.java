package net.uch.mapping;

import net.uch.mapping.base.BaseAcTurno;



public class AcTurno extends BaseAcTurno {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcTurno () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcTurno (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcTurno (
		java.lang.Integer id,
		java.lang.Integer semId) {

		super (
			id,
			semId);
	}

/*[CONSTRUCTOR MARKER END]*/


}