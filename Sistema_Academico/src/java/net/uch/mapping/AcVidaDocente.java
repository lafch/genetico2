package net.uch.mapping;

import net.uch.mapping.base.BaseAcVidaDocente;



public class AcVidaDocente extends BaseAcVidaDocente {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcVidaDocente () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcVidaDocente (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcVidaDocente (
		java.lang.Integer id,
		net.uch.mapping.AcDocente doc) {

		super (
			id,
			doc);
	}

/*[CONSTRUCTOR MARKER END]*/


}