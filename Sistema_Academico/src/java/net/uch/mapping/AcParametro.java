/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

import net.uch.mapping.base.BaseAcParametro;

/**
 *
 * @author Alumno
 */
public class AcParametro extends BaseAcParametro {

    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public AcParametro() {
        super();
    }

    /**
     * Constructor for primary key
     *
     * @param id
     */
    public AcParametro(java.lang.Integer id) {
        super(id);
    }

    /**
     * Constructor for required fields
     *
     * @param id
     * @param parCod
     * @param parVal
     * @param parDes
     * @param parActivo
     * @param parUsuario
     */
    public AcParametro(
            java.lang.Integer id,
            java.lang.String parCod,
            java.lang.String parVal,
            java.lang.String parDes,
            java.lang.String parActivo,
            java.lang.Integer parUsuario
     ) {

        super(
                id,
                parCod,
                parVal,
                parDes,
                parActivo,
                parUsuario);
    }
}
