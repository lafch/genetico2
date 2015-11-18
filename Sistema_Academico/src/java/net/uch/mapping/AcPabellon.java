package net.uch.mapping;

import net.uch.mapping.base.BaseAcPabellon;



public class AcPabellon extends BaseAcPabellon {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcPabellon () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcPabellon (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcPabellon (
		java.lang.Integer id,
		net.uch.mapping.AcLocal loc) {

		super (
			id,
			loc);
	}

/*[CONSTRUCTOR MARKER END]*/


}