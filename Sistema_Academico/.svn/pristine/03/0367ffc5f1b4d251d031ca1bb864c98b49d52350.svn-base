/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSPlantillaHorarioCLDAO;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClPlantillaHorario;
import net.uch.mapping.ClSeccion;
import net.uch.metodo.local.MetodosAca;

/**
 *
 * @author cesardl
 */
public class BeanClSeccion_1 extends ClSeccion {

    private SelectItem[] turnos;
    private String v_imagen_horario;
    private int cantMatriculados;
     MetodosAca metodo=new MetodosAca();
     private SelectItem[] cboLocal;
    private int w_loc_id;
    private String w_turno;
    private String w_sede;

    public String getW_sede() {
        return w_sede;
    }

    public void setW_sede(String w_sede) {
        this.w_sede = w_sede;
    }

    public String getW_turno() {
        return w_turno;
    }

    public void setW_turno(String w_turno) {
        this.w_turno = w_turno;
    }

    
    
    public SelectItem[] getCboLocal()throws SQLException {
        cboLocal=metodo.listarSedes("Seleccione");
        return cboLocal;
    }

    public void setCboLocal(SelectItem[] cboLocal) {
        this.cboLocal = cboLocal;
    }

    public int getW_loc_id() {
        return w_loc_id;
    }

    public void setW_loc_id(int w_loc_id) {
        this.w_loc_id = w_loc_id;
    }

    public BeanClSeccion_1() {
    }

    public BeanClSeccion_1(int talape_id) {
        //this.setClTallerAperturado(new ClTallerAperturado(talape_id));

        this.setSecId(0);
        this.setSecCodigo("");
        this.setSecNombre("");
        this.setSecTurno("X");
        this.setSecVacMax(0);
        this.setSecVacMin(0);

        this.setSecActivo("1");

        this.setSecFfin(null);
        this.setSecFfinMatricula(null);
        this.setSecFinicio(null);
        this.setSecFinicioMatricula(null);
        this.setClArbolAcademico(new ClArbolAcademico(talape_id));
    }

    public BeanClSeccion_1(ClSeccion seccion) {
        //this.setClTallerAperturado(seccion.getClTallerAperturado());

        this.setSecId(seccion.getSecId());
        this.setSecCodigo(seccion.getSecCodigo());
        this.setSecNombre(seccion.getSecNombre());
        this.setSecTurno(seccion.getSecTurno());
        this.setSecVacMax(seccion.getSecVacMax());
        this.setSecVacMin(seccion.getSecVacMin());

        this.setSecActivo(seccion.getSecActivo());

        this.setSecFfin(seccion.getSecFfin());
        this.setSecFfinMatricula(seccion.getSecFfinMatricula());
        this.setSecFinicio(seccion.getSecFinicio());
        this.setSecFinicioMatricula(seccion.getSecFinicioMatricula());
        this.setLocId(seccion.getLocId());
        this.setPlaId(seccion.getPlaId());
        this.setClArbolAcademico(seccion.getClArbolAcademico());
        //this.setClInicio(seccion.getClInicio());
       // System.out.println("la cantidad de horarios -> "+seccion.getClHorarias().size());
        this.setV_imagen_horario("/Imagenes/actions/calendar_gris.png");
        if(seccion.getClHorarias().size() > 0) {
            this.setV_imagen_horario("/Imagenes/actions/calendar.gif");
        }
    }

    public SelectItem[] getTurnos() {
       /* if (turnos == null) {
            turnos = new SelectItem[4];
            turnos[0] = new SelectItem("X", "[Seleccione]");
            turnos[1] = new SelectItem("M", "MAÑANA");
            turnos[2] = new SelectItem("T", "TARDE");
            turnos[3] = new SelectItem("N", "NOCHE");
        }*/
        HSPlantillaHorarioCLDAO dao=(HSPlantillaHorarioCLDAO) ServiceFinder.findBean("SpringHibernateDaoPlantillaHorario");
        List<ClPlantillaHorario> listaP=dao.listarPlantilla();
        System.out.println("BeanClSeccion_1 -> cntidaad de la lista -> "+listaP.size());
        this.turnos=new SelectItem[listaP.size()+1];
        this.turnos[0]=new SelectItem(0, "[Seleccione]");
        for(int i=0; i<(turnos.length-1); i++){
            this.turnos[i+1]=new SelectItem(listaP.get(i).getPlaId(), listaP.get(i).getPlaDescripcion());
        }
        return turnos;
    }

    public void setTurnos(SelectItem[] turnos) {
        this.turnos = turnos;
    }

    public boolean esValido() {
        return !(this.getSecCodigo().trim().length() == 0
                || this.getSecNombre().trim().length() == 0
                || this.getSecVacMax().intValue() == 0
                || this.getSecVacMin().intValue() == 0
                || this.getSecFinicio() == null
                || this.getSecFfin() == null
                || this.getSecFinicioMatricula() == null
                || this.getSecFfinMatricula() == null);
    }

    public String getV_imagen_horario() {
        return v_imagen_horario;
    }

    public void setV_imagen_horario(String v_imagen_horario) {
        this.v_imagen_horario = v_imagen_horario;
    }

    public int getCantMatriculados() {
        return cantMatriculados;
    }

    public void setCantMatriculados(int cantMatriculados) {
        this.cantMatriculados = cantMatriculados;
    }

    public ClSeccion getSeccion() {
        ClSeccion sec = new ClSeccion();
       // sec.setClTallerAperturado(this.getClTallerAperturado());
        sec.setClMatriculaTallers(new LinkedHashSet());
        sec.setClHorarias(new LinkedHashSet());

        sec.setSecId(this.getSecId());
        sec.setSecCodigo(this.getSecCodigo());
        sec.setSecNombre(this.getSecNombre());
        sec.setSecTurno(this.getSecTurno());
        sec.setSecVacMax(this.getSecVacMax());
        sec.setSecVacMin(this.getSecVacMin());

        sec.setSecActivo(this.getSecActivo());

        sec.setSecFfin(this.getSecFfin());
        sec.setSecFfinMatricula(this.getSecFfinMatricula());
        sec.setSecFinicio(this.getSecFinicio());
        sec.setSecFinicioMatricula(this.getSecFinicioMatricula());
        sec.setLocId(this.getLocId());
        sec.setPlaId(this.getPlaId());
        sec.setClArbolAcademico(this.getClArbolAcademico());
        //sec.setClInicio(this.getClInicio());

        return sec;
    }
}
