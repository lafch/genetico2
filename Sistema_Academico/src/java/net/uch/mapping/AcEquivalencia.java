package net.uch.mapping;

import net.uch.mapping.base.BaseAcEquivalencia;



public class AcEquivalencia extends BaseAcEquivalencia {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcEquivalencia () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcEquivalencia (java.lang.Integer id) {
            super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcEquivalencia (
		java.lang.Integer id,
		net.uch.mapping.AcPlanCurso plancur) {

		super (
			id,
			plancur);
	}

/*[CONSTRUCTOR MARKER END]*/


}