package net.uch.mapping;

import net.uch.mapping.base.BaseAcContenidoTematico;



public class AcContenidoTematico extends BaseAcContenidoTematico {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcContenidoTematico () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcContenidoTematico (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcContenidoTematico (
		java.lang.Integer id,
		net.uch.mapping.AcPlanCurso plancur) {

		super (
			id,
			plancur);
	}

/*[CONSTRUCTOR MARKER END]*/


}