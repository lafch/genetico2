package net.uch.mapping;

import net.uch.mapping.base.BaseAcHorario;



public class AcHorario extends BaseAcHorario {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcHorario () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcHorario (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcHorario (
		java.lang.Integer id,
		net.uch.mapping.AcSeccion sec,
		net.uch.mapping.AcAula aul) {

		super (
			id,
			sec,
			aul);
	}

/*[CONSTRUCTOR MARKER END]*/


}