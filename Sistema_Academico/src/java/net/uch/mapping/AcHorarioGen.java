/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

import net.uch.mapping.base.BaseAcHorarioGen;

/**
 *
 * @author LUIS
 */
public class AcHorarioGen extends BaseAcHorarioGen{
    private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcHorarioGen () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcHorarioGen (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcHorarioGen (
		java.lang.Integer id,
		net.uch.mapping.AcSeccion sec,
		net.uch.mapping.AcAula aul) {

		super (
			id,
			sec,
			aul);
	}
}
