/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.asistenciaDocente.managedBeans.beans;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 *
 * @author richard
 */
public class AsistenciaDocenteDiariaBean {

    int ses_id;
    int doc_id;
    int doc_id_bk;
    String facultad;
    String escuela;
    String aula;
    String sesEstadoDocTipo;
    String sesObservacion;
    String doc_nombre;
    String doc_codigo;
    String sec_nombre;
    String cur_nombre;
    String estasis_code;
    SelectItem estasis[];

    public SelectItem[] getEstasis() {
        try {
            HSCatalogoDAO catalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List l = catalogoDAO.seleccionarCatalogo("046");
            estasis = new SelectItem[l.size() + 1];

            for (int i = 0; i < l.size(); i++) {
                TbCatalogo cat = (TbCatalogo) l.get(i);
                estasis[i + 1] = new SelectItem("046" + cat.getCatCodigoElemento(), cat.getCatDescripcionElemento());
            }
        } catch (Exception e) {
            estasis = new SelectItem[1];
        } finally {
            estasis[0] = new SelectItem(0, "[Seleccione]");
        }
        return estasis;
    }

    public void setEstasis(SelectItem[] estasis) {
        this.estasis = estasis;
    }

    public String getEstasis_code() {
        return estasis_code;
    }

    public void setEstasis_code(String estasis_code) {
        this.estasis_code = estasis_code;
    }

    public String getCur_nombre() {
        return cur_nombre;
    }

    public void setCur_nombre(String cur_nombre) {
        this.cur_nombre = cur_nombre;
    }

    public String getSec_nombre() {
        return sec_nombre;
    }

    public void setSec_nombre(String sec_nombre) {
        this.sec_nombre = sec_nombre;
    }

    public String getDoc_codigo() {
        return doc_codigo;
    }

    public void setDoc_codigo(String doc_codigo) {
        this.doc_codigo = doc_codigo;
    }

    public String getDoc_nombre() {
        return doc_nombre;
    }

    public void setDoc_nombre(String doc_nombre) {
        this.doc_nombre = doc_nombre;
    }

    public int getDoc_id_bk() {
        return doc_id_bk;
    }

    public void setDoc_id_bk(int doc_id_bk) {
        this.doc_id_bk = doc_id_bk;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getSes_id() {
        return ses_id;
    }

    public void setSes_id(int ses_id) {
        this.ses_id = ses_id;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getSesEstadoDocTipo() {
        return sesEstadoDocTipo;
    }

    public void setSesEstadoDocTipo(String sesEstadoDocTipo) {
        this.sesEstadoDocTipo = sesEstadoDocTipo;
    }

    public String getSesObservacion() {
        return sesObservacion;
    }

    public void setSesObservacion(String sesObservacion) {
        this.sesObservacion = sesObservacion;
    }
       

    @Override
    public String toString() {
        return "curso: " + this.getCur_nombre()
                + "\ndocente: " + this.getDoc_nombre()
                + "\nest asis: " + this.getEstasis_code()
                + "\nseccion: " + this.getSec_nombre()
                + "\nsesion id: " + this.getSes_id();
    }
}
