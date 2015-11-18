/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans.beans;

import net.uch.mapping.Sp_convalidacion_cursos;

/**
 *
 * @author cesardl
 */
public class BeanConvalidacion extends Sp_convalidacion_cursos {

    private Float nota;
    private boolean activar;
    private String disable;
    private String style;
    private boolean existe;

    public BeanConvalidacion(Sp_convalidacion_cursos conv_cur) {
        this.setReg_actdet_id(conv_cur.getReg_actdet_id());
        this.setReg_sec_id(conv_cur.getReg_sec_id());
        this.setReg_sec_nombre(conv_cur.getReg_sec_nombre());
        this.setReg_plancur_codigo(conv_cur.getReg_plancur_codigo());
        this.setReg_cur_nombre(conv_cur.getReg_cur_nombre());
        this.setReg_nota(conv_cur.getReg_nota());
        this.setReg_cur_id(conv_cur.getReg_cur_id());
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public boolean isActivar() {
        return activar;
    }

    public void setActivar(boolean activar) {
        this.activar = activar;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public void cambiarEstado() {
        if (this.activar) {
            this.disable = "false";
            this.style = "";
        } else {
            this.disable = "true";
            this.style = "background: gray;";
        }
    }

    @Override
    public String toString() {
        return "sec_id: " + this.getReg_sec_id() + "\tnota: " + this.getReg_nota();
    }
}
