package net.uch.mapping;

import net.uch.mapping.base.BaseTbDistrito;



public class TbDistrito extends BaseTbDistrito {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TbDistrito () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TbDistrito (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public TbDistrito (
		java.lang.String id,
		java.lang.String depCod) {

		super (
			id,
			depCod);
	}

/*[CONSTRUCTOR MARKER END]*/


}