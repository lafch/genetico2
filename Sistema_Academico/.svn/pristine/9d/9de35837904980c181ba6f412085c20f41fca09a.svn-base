/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.managedBeans.beans;

import net.uch.mapping.ClAlumnoTarifa;

/**
 *
 * @author richard
 */
public class BeanClAlumnosTarifa{
    private String alutar_style;
    private int alutar_orden;
    private String deu_modulo;
    private String deu_curso;
    private double deu_montopagar;
    private double deu_montopagado;
    private double deu_saldo;
    private ClAlumnoTarifa alumnoTarifa;
    private long alutar_fecha;

    public long getAlutar_fecha() {
        return alutar_fecha;
    }

    public void setAlutar_fecha(long alutar_fecha) {
        this.alutar_fecha = alutar_fecha;
    }

    

    public ClAlumnoTarifa getAlumnoTarifa() {
        return alumnoTarifa;
    }

    public void setAlumnoTarifa(ClAlumnoTarifa alumnoTarifa) {
        if( alumnoTarifa != null ){
            this.alutar_fecha = alumnoTarifa.getAlutarFechaPago().getTime();
            this.deu_montopagar = alumnoTarifa.getAlutarMonto();
        }
        this.alumnoTarifa = alumnoTarifa;
    }

    

    public int getAlutar_orden() {
        return alutar_orden;
    }

    public void setAlutar_orden(int alutar_orden) {
        this.alutar_orden = alutar_orden;
    }

    public String getAlutar_style() {
        return alutar_style;
    }

    public void setAlutar_style(String alutar_style) {
        this.alutar_style = alutar_style;
    }

    public String getDeu_curso() {
        return deu_curso;
    }

    public void setDeu_curso(String deu_curso) {
        this.deu_curso = deu_curso;
    }

    public String getDeu_modulo() {
        return deu_modulo;
    }

    public void setDeu_modulo(String deu_modulo) {
        this.deu_modulo = deu_modulo;
    }

    public double getDeu_montopagado() {
        return deu_montopagado;
    }

    public void setDeu_montopagado(double deu_montopagado) {
        this.deu_montopagado = deu_montopagado;
    }

    public double getDeu_saldo() {
        return deu_saldo;
    }

    public void setDeu_saldo(double deu_saldo) {
        this.deu_saldo = deu_saldo;
    }

    public double getDeu_montopagar() {
        return deu_montopagar;
    }

    public void setDeu_montopagar(double deu_montopagar) {
        this.deu_montopagar = deu_montopagar;
    }

    


}
