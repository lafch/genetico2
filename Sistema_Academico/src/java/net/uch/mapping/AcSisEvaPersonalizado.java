package net.uch.mapping;

import net.uch.mapping.base.BaseAcSisEvaPersonalizado;



public class AcSisEvaPersonalizado extends BaseAcSisEvaPersonalizado {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcSisEvaPersonalizado () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcSisEvaPersonalizado (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcSisEvaPersonalizado (
		java.lang.Integer id,
		net.uch.mapping.AcCursoAperturado curape,
		net.uch.mapping.AcSisEvaDetalle sisevaDetalle) {

		super (
			id,
			curape,
			sisevaDetalle);
	}

/*[CONSTRUCTOR MARKER END]*/


}