package net.uch.mapping;

import net.uch.mapping.base.BaseAdComprobantePago;



public class AdComprobantePago extends BaseAdComprobantePago {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AdComprobantePago () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AdComprobantePago (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AdComprobantePago (
		java.lang.Integer id,
		java.lang.Integer compagCliente) {

		super (
			id,
			compagCliente);
	}

/*[CONSTRUCTOR MARKER END]*/


}