/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import net.uch.academica.hibernateSpringDao.HSImportacionNotasDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcSisEvaPersonalizado;

/**
 *
 * @author richard
 */
public class bImportarNotas {

    private int curape_id;
    private int w_siseva_per_id;
    private String w_siseva_per_codigo;
    private String w_siseva_per_nombre;
    private Boolean w_marcarSis;

    public Boolean getW_marcarSis() {
        return w_marcarSis;
    }

    public void setW_marcarSis(Boolean w_marcarSis) {
        this.w_marcarSis = w_marcarSis;
    }
    private List w_sistemaEvaluacion = new ArrayList();

    public List getW_sistemaEvaluacion() {
        return w_sistemaEvaluacion;
    }

    public void setW_sistemaEvaluacion(List w_sistemaEvaluacion) {
        this.w_sistemaEvaluacion = w_sistemaEvaluacion;
    }

    public String getW_siseva_per_codigo() {
        return w_siseva_per_codigo;
    }

    public void setW_siseva_per_codigo(String w_siseva_per_codigo) {
        this.w_siseva_per_codigo = w_siseva_per_codigo;
    }

    public int getW_siseva_per_id() {
        return w_siseva_per_id;
    }

    public void setW_siseva_per_id(int w_siseva_per_id) {
        this.w_siseva_per_id = w_siseva_per_id;
    }

    public String getW_siseva_per_nombre() {
        return w_siseva_per_nombre;
    }

    public void setW_siseva_per_nombre(String w_siseva_per_nombre) {
        this.w_siseva_per_nombre = w_siseva_per_nombre;
    }

    public bImportarNotas() {
    }

    public bImportarNotas(int b) {
    }

    public int getCurape_id() {
        return curape_id;
    }

    public void setCurape_id(int curape_id) {
        this.curape_id = curape_id;
    }

    public void SeleccionarSec(ActionEvent event) {
        System.out.println("el valor es: " + this.getCurape_id());
        HSImportacionNotasDAO dao = (HSImportacionNotasDAO) ServiceFinder.findBean("SpringHibernateDaoBloquearNotas");
        List<AcSisEvaPersonalizado> lista = dao.listarSistemaEvaluacionPersonalizado_x_seccion(this.getCurape_id());
        System.out.println("cantidad -> " + lista.size());
        for (int i = 0; i < lista.size(); i++) {
            bImportarNotas importe = new bImportarNotas(0);
            //System.out.println("siseva_per_codigo -> "+lista.get(i).getSisevaCodigo()) ;
            importe.setW_siseva_per_id(lista.get(i).getId());
            importe.setW_siseva_per_codigo(lista.get(i).getSisevaCodigo());
            importe.setW_siseva_per_nombre(lista.get(i).getSisevaPerNombre());
            w_sistemaEvaluacion.add(importe);
        }
    }
}