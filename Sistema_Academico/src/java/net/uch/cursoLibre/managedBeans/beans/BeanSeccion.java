/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.managedBeans.beans;

import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClSeccion;

/**
 *
 * @author Alumno
 */
public class BeanSeccion extends ClSeccion {
    private int sec_contador;
    private int sec_canti_matricula;
    private String sec_curso;
    private String sec_horario;
    private String sec_horario_corto;
//    HSAlumnoCLDAO dao = (HSAlumnoCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumno");
    HSHorarioDAO daoHor = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoCLHorario");

    public BeanSeccion(int sec_id, String sec_nombre, String sec_curso,
                        Date sec_fecha_ini, Date sec_fecha_fin, 
                        int sec_contador, int sec_canti_matricula) throws Exception{
       // List<ClAlumno> lista=dao.listarAlumnos_x_Seccion(sec_id);
        this.setSecId(sec_id);
        this.setSecNombre(sec_nombre);
        this.sec_curso=sec_curso;
        this.setSecFinicio(sec_fecha_ini);
        this.setSecFfin(sec_fecha_fin);
        this.sec_contador=sec_contador;
        this.sec_canti_matricula=sec_canti_matricula;
        List<ClHoraria> lista=daoHor.seleccionarHorario(sec_id);
        this.sec_horario="Horario no Definido";
        this.sec_horario_corto="Horario no Definido";
        if(lista.size()>0){
            this.sec_horario="";
            this.sec_horario_corto="";
        }
        for(int i=0; i<lista.size(); i++){
            int dia=Integer.parseInt(lista.get(i).getHorDia());
            String w_dia="";
            switch(dia){
                case 16001: w_dia="Lunes "; break;
                case 16002: w_dia="Martes "; break;
                case 16003: w_dia="Miercoles "; break;
                case 16004: w_dia="Jueves "; break;
                case 16005: w_dia="Viernes "; break;
                case 16006: w_dia="Sabado "; break;
                case 16007: w_dia="Domingo "; break;
            }
            this.sec_horario=this.sec_horario+" "+w_dia+"  =>  "+lista.get(i).getHorHini()+"  a  "+lista.get(i).getHorHfin()+"<br />";
        }
        if(lista.size()>0){
            this.sec_horario_corto=this.sec_horario.substring(1, 15);
        }
    }

    public int getSec_contador() {
        return sec_contador;
    }

    public void setSec_contador(int sec_contador) {
        this.sec_contador = sec_contador;
    }

    public int getSec_canti_matricula() {
        return sec_canti_matricula;
    }

    public void setSec_canti_matricula(int sec_canti_matricula) {
        this.sec_canti_matricula = sec_canti_matricula;
    }

    public String getSec_curso() {
        return sec_curso;
    }

    public void setSec_curso(String sec_curso) {
        this.sec_curso = sec_curso;
    }

    public String getSec_horario() {
        return sec_horario;
    }

    public void setSec_horario(String sec_horario) {
        this.sec_horario = sec_horario;
    }

    public String getSec_horario_corto() {
        return sec_horario_corto;
    }

    public void setSec_horario_corto(String sec_horario_corto) {
        this.sec_horario_corto = sec_horario_corto;
    }

    
    
}
