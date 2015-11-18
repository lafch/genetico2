/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

import java.io.Serializable;

/**
 *
 * @author cesardl
 */
public class Sp_conceptos_banco implements Serializable {

    Integer reg_concepto;
    String reg_descripcion;

    public Integer getReg_concepto() {
        return reg_concepto;
    }

    public void setReg_concepto(Integer reg_concepto) {
        this.reg_concepto = reg_concepto;
    }

    public String getReg_descripcion() {
        return reg_descripcion;
    }

    public void setReg_descripcion(String reg_descripcion) {
        this.reg_descripcion = reg_descripcion;
    }
}
