/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans.beans;

import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcMatricula;

/**
 *
 * @author richard
 */
public class BeanAlumno {
    private String w_tipo_alumno;
    private AcAlumno acAlumno;
    private String w_semestre;
    private boolean w_ver_deuda=false;
    private String w_imagen="/Imagenes/actions/matricular.gif";
    private boolean w_ver_reporte=false;
    private AcMatricula acMatricula;
    private String w_estado;
    private boolean w_visualizar=false;
    
    public BeanAlumno(){
        
    }

    public AcAlumno getAcAlumno() {
        return acAlumno;
    }

    public void setAcAlumno(AcAlumno acAlumno) {
        this.acAlumno = acAlumno;
    }

    public String getW_semestre() {
        return w_semestre;
    }

    public void setW_semestre(String w_semestre) {
        this.w_semestre = w_semestre;
    }

    

    public String getW_tipo_alumno() {
        return w_tipo_alumno;
    }

    public void setW_tipo_alumno(String w_tipo_alumno) {
        this.w_tipo_alumno = w_tipo_alumno;
    }

    public boolean isW_ver_deuda() {
        return w_ver_deuda;
    }

    public void setW_ver_deuda(boolean w_ver_deuda) {
        this.w_ver_deuda = w_ver_deuda;
    }

    public String getW_imagen() {
        return w_imagen;
    }

    public void setW_imagen(String w_imagen) {
        this.w_imagen = w_imagen;
    }

    public boolean isW_ver_reporte() {
        return w_ver_reporte;
    }

    public void setW_ver_reporte(boolean w_ver_reporte) {
        this.w_ver_reporte = w_ver_reporte;
    }

    public AcMatricula getAcMatricula() {
        return acMatricula;
    }

    public void setAcMatricula(AcMatricula acMatricula) {
        this.acMatricula = acMatricula;
    }

    public String getW_estado() {
        return w_estado;
    }

    public void setW_estado(String w_estado) {
        this.w_estado = w_estado;
    }

    public boolean isW_visualizar() {
        return w_visualizar;
    }

    public void setW_visualizar(boolean w_visualizar) {
        this.w_visualizar = w_visualizar;
    }
    
    
}
