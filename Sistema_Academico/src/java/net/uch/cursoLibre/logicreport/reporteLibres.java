/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.logicreport;

import java.util.Date;

/**
 *
 * @author Alumno
 */
public class reporteLibres {
    private int rep_nro;
    private String rep_curso;
    private String rep_seccion;
    private Date rep_fecha_ini;
    private Date rep_fecha_fin;
    private int rep_cantidad_mat;
    
    //para el reporte cursos que inician

    private String rep_dato_completo;
    private String rep_nro_horas;

    public String getRep_dato_completo() {
        return rep_dato_completo;
    }

    public void setRep_dato_completo(String rep_dato_completo) {
        this.rep_dato_completo = rep_dato_completo;
    }

    public String getRep_nro_horas() {
        return rep_nro_horas;
    }

    public void setRep_nro_horas(String rep_nro_horas) {
        this.rep_nro_horas = rep_nro_horas;
    }

    public reporteLibres(String rep_dato_completo, String rep_nro_horas){
        this.rep_dato_completo=rep_dato_completo;
        this.rep_nro_horas=rep_nro_horas;
    }



    public reporteLibres(int rep_nro, String rep_curso, String rep_seccion, Date rep_fecha_ini,
                         Date rep_fecha_fin, int rep_cantidad_mat){
        this.rep_nro=rep_nro;
        this.rep_cantidad_mat=rep_cantidad_mat;
        this.rep_curso=rep_curso;
        this.rep_fecha_fin=rep_fecha_fin;
        this.rep_fecha_ini=rep_fecha_ini;
        this.rep_seccion=rep_seccion;
    }

    public int getRep_cantidad_mat() {
        return rep_cantidad_mat;
    }

    public void setRep_cantidad_mat(int rep_cantidad_mat) {
        this.rep_cantidad_mat = rep_cantidad_mat;
    }

    public String getRep_curso() {
        return rep_curso;
    }

    public void setRep_curso(String rep_curso) {
        this.rep_curso = rep_curso;
    }

    public Date getRep_fecha_fin() {
        return rep_fecha_fin;
    }

    public void setRep_fecha_fin(Date rep_fecha_fin) {
        this.rep_fecha_fin = rep_fecha_fin;
    }

    public Date getRep_fecha_ini() {
        return rep_fecha_ini;
    }

    public void setRep_fecha_ini(Date rep_fecha_ini) {
        this.rep_fecha_ini = rep_fecha_ini;
    }

    public int getRep_nro() {
        return rep_nro;
    }

    public void setRep_nro(int rep_nro) {
        this.rep_nro = rep_nro;
    }

    public String getRep_seccion() {
        return rep_seccion;
    }

    public void setRep_seccion(String rep_seccion) {
        this.rep_seccion = rep_seccion;
    }


}
