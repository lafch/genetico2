/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import net.uch.mapping.ClAlumno;

/**
 *
 * @author cesardl
 */
public class BeanClAlumno extends ClAlumno {

    private String alu_style;
    private String alu_obs;
    private String fechaMatricula;
    private int aluContador;
    private boolean aluMatriculado;
    private double alu_nota1;
    private double alu_nota2;
    private double alu_promedio;
    private float total;

    public BeanClAlumno(ClAlumno alumno) {
        this.setAluId(alumno.getAluId());
        this.setAluCodigo(alumno.getAluCodigo());
        this.setAluNombres(alumno.getAluNombres());
        this.setAluAppaterno(alumno.getAluAppaterno());
        this.setAluApmaterno(alumno.getAluApmaterno());
        this.setAluCelular(alumno.getAluCelular());
        this.setAluTelefono(alumno.getAluTelefono());
        this.setAluMail(alumno.getAluMail());
        this.setAluMatriculado(true);
    }
    public BeanClAlumno(int alu_id, String alu_codigo, String alu_appaterno, String alu_apmaterno,
                        String alu_password, String alu_nombres, String alu_celular ,
                        String alu_telefono, String alu_mail, int alu_contador){
        this.setAluId(alu_id);
        this.setAluCodigo(alu_codigo);
        this.setAluNombres(alu_nombres);
        this.setAluAppaterno(alu_appaterno);
        this.setAluApmaterno(alu_apmaterno);
        this.setAluPassword(alu_password);
        this.setAluContador(alu_contador);
        this.setAluCelular(alu_celular);
        this.setAluTelefono(alu_telefono);
        this.setAluMail(alu_mail);
    }
    public BeanClAlumno(){
        
    }

    
    public String getAlu_obs () {
        return alu_obs;
    }

    public void setAlu_obs ( String alu_obs ) {
        this.alu_obs = alu_obs;
    }

    public String getAlu_style() {
        return alu_style;
    }

    public void setAlu_style(String alu_style) {
        this.alu_style = alu_style;
    }
    
    
    public double getAlu_nota1() {
        return alu_nota1;
    }

    public void setAlu_nota1(double alu_nota1) {
        this.alu_nota1 = alu_nota1;
    }

    public double getAlu_nota2() {
        return alu_nota2;
    }

    public void setAlu_nota2(double alu_nota2) {
        this.alu_nota2 = alu_nota2;
    }

    public double getAlu_promedio() {
        return alu_promedio;
    }

    public void setAlu_promedio(double alu_promedio) {
        this.alu_promedio = alu_promedio;
    }
    

    public int getAluContador() {
        return aluContador;
    }

    public void setAluContador(int aluContador) {
        this.aluContador = aluContador;
    }

    public boolean isAluMatriculado() {
        return aluMatriculado;
    }

    public void setAluMatriculado(boolean aluMatriculado) {
        this.aluMatriculado = aluMatriculado;
    }
    public float getTotal(){return total;}

    public void setTotal(float f) {
        total = f;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula( String fechaMatricula ) {
        this.fechaMatricula = fechaMatricula;
    }
    
}
