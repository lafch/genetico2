/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

/**
 *
 * @author cesardl
 */
public class Sp_convalidacion_cursos {

    private int reg_sec_id;
    private String reg_sec_nombre;
    private String reg_plancur_codigo;
    private String reg_cur_nombre;
    private Integer reg_actdet_id;
    private String reg_nota;
    private Integer reg_cur_id;

    public Sp_convalidacion_cursos() {
    }

    public String getReg_cur_nombre() {
        return reg_cur_nombre;
    }

    public void setReg_cur_nombre(String reg_cur_nombre) {
        this.reg_cur_nombre = reg_cur_nombre;
    }

    public Integer getReg_actdet_id() {
        return reg_actdet_id;
    }

    public void setReg_actdet_id(Integer reg_actdet_id) {
        this.reg_actdet_id = reg_actdet_id;
    }

    public String getReg_nota() {
        return reg_nota;
    }

    public void setReg_nota(String reg_nota) {
        this.reg_nota = reg_nota;
    }

    public String getReg_plancur_codigo() {
        return reg_plancur_codigo;
    }

    public void setReg_plancur_codigo(String reg_plancur_codigo) {
        this.reg_plancur_codigo = reg_plancur_codigo;
    }

    public int getReg_sec_id() {
        return reg_sec_id;
    }

    public void setReg_sec_id(int reg_sec_id) {
        this.reg_sec_id = reg_sec_id;
    }

    public String getReg_sec_nombre() {
        return reg_sec_nombre;
    }

    public void setReg_sec_nombre(String reg_sec_nombre) {
        this.reg_sec_nombre = reg_sec_nombre;
    }

    public Integer getReg_cur_id() {
        return reg_cur_id;
    }

    public void setReg_cur_id(Integer reg_cur_id) {
        this.reg_cur_id = reg_cur_id;
    }
    

    @Override
    public String toString() {
        return this.reg_sec_nombre + "\t" + this.reg_cur_nombre + "\t" + this.reg_nota;
    }
}
