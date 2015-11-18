package net.uch.mapping;

import net.uch.mapping.base.BaseAcAula;



public class AcAula extends BaseAcAula {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcAula () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcAula (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcAula (
		java.lang.Integer id,
		net.uch.mapping.AcPabellon pab,
		java.lang.String aulDes,
		java.lang.Integer aulCap,
		java.lang.String aulActivo, net.uch.mapping.AcEspecialidad esp, java.lang.String aulTipo) {

		super (
			id,
			pab,
			aulDes,
			aulCap,
			aulActivo,esp,aulTipo);
	}

/*[CONSTRUCTOR MARKER END]*/


}