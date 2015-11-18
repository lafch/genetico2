/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.mapping;

import net.uch.mapping.base.BaseAcPagina;

/**
 *
 * @author Alumno
 */
public class AcPagina extends BaseAcPagina{
    	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcPagina () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcPagina (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcPagina (
		java.lang.Integer id,
		java.lang.String pagDes,
		java.lang.String pagUrl,
		java.lang.String pagActivo) {

		super (
			id,
			pagDes,
			pagUrl,
			pagActivo);
	}
}
