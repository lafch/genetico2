package net.uch.mapping;

import net.uch.mapping.base.BaseAcPlanCurso;



public class AcPlanCurso extends BaseAcPlanCurso {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcPlanCurso () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcPlanCurso (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcPlanCurso (
		java.lang.Integer id,
		net.uch.mapping.AcCurso cur,
		net.uch.mapping.AcPlancurricular plan) {

		super (
			id,
			cur,
			plan);
	}

/*[CONSTRUCTOR MARKER END]*/


}