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
public class AsistenciaDocenteBean {

    int sesefeasisdoc_id;
    int ses_id;
    int doc_id;
    int doc_id_bk;
    Date sesefeasisdoc_fecha;
    int reg_hora;
    int reg_min;
    Date sesefeasisdoc_registro;
    int sal_hora;
    int sal_min;
    Time hora_inicio; //Hora de inicio de la clase
    Time hora_fin; //Hora de termino de la clase
    Time hora_ingreso; //Hora de la marca de entrada del docente
    Time hora_salida; //Hora de la marca de salidad del docente
    String facultad;
    String escuela;
    String aula;
    Date registro;
    Date salida;
    Date sesefeasisdoc_salida;
    String sesefeasisdoc_obs;
    int sesefeasisdoc_tole;
    String sesefeasisdoc_activo;
    String sesefeasisdoc_tipo;
    String doc_nombre;
    String doc_codigo;
    String sec_nombre;
    String cur_nombre;
    String estasis_code;
    SelectItem estasis[];
    
    //nuevos atributos
    String sesEstadoDocTipo;
    String sesObservacion;
    String estasis_code_diario;
    SelectItem estasisdiario[];
    
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
    
    public SelectItem[] getEstasisdiario() {
        try {
            HSCatalogoDAO catalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List l = catalogoDAO.seleccionarCatalogo("046");
            estasisdiario = new SelectItem[l.size() + 1];

            for (int i = 0; i < l.size(); i++) {
                TbCatalogo cat = (TbCatalogo) l.get(i);
                estasisdiario[i + 1] = new SelectItem("046" + cat.getCatCodigoElemento(), cat.getCatDescripcionElemento());
            }
        } catch (Exception e) {
            estasisdiario = new SelectItem[1];
        } finally {
            estasisdiario[0] = new SelectItem(0, "[Seleccione]");
        }
        return estasisdiario;
    }

    public void setEstasisdiario(SelectItem[] estasisdiario) {
        this.estasisdiario = estasisdiario;
    }
    
    public String getEstasis_code_diario() {
        return estasis_code_diario;
    }

    public void setEstasis_code_diario(String estasis_code_diario) {
        this.estasis_code_diario = estasis_code_diario;
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

    public String getSesefeasisdoc_activo() {
        return sesefeasisdoc_activo;
    }

    public void setSesefeasisdoc_activo(String sesefeasisdoc_activo) {
        this.sesefeasisdoc_activo = sesefeasisdoc_activo;
    }

    public int getSesefeasisdoc_id() {
        return sesefeasisdoc_id;
    }

    public void setSesefeasisdoc_id(int sesefeasisdoc_id) {
        this.sesefeasisdoc_id = sesefeasisdoc_id;
    }

    public String getSesefeasisdoc_obs() {
        return sesefeasisdoc_obs;
    }

    public void setSesefeasisdoc_obs(String sesefeasisdoc_obs) {
        this.sesefeasisdoc_obs = sesefeasisdoc_obs;
    }

    public Date getSesefeasisdoc_fecha() {
        return sesefeasisdoc_fecha;
    }

    public void setSesefeasisdoc_fecha(Date sesefeasisdoc_fecha) {
        this.sesefeasisdoc_fecha = sesefeasisdoc_fecha;
    }

    public Date getSesefeasisdoc_registro() {
        return sesefeasisdoc_registro;
    }

    public void setSesefeasisdoc_registro(Date sesefeasisdoc_registro) {
        this.sesefeasisdoc_registro = sesefeasisdoc_registro;
    }

    public Date getSesefeasisdoc_salida() {
        return sesefeasisdoc_salida;
    }

    public void setSesefeasisdoc_salida(Date sesefeasisdoc_salida) {
        this.sesefeasisdoc_salida = sesefeasisdoc_salida;
    }

    public String getSesefeasisdoc_tipo() {
        return sesefeasisdoc_tipo;
    }

    public void setSesefeasisdoc_tipo(String sesefeasisdoc_tipo) {
        this.sesefeasisdoc_tipo = sesefeasisdoc_tipo;
    }

    public int getSesefeasisdoc_tole() {
        return sesefeasisdoc_tole;
    }

    public void setSesefeasisdoc_tole(int sesefeasisdoc_tole) {
        this.sesefeasisdoc_tole = sesefeasisdoc_tole;
    }

    public int getReg_hora() {
        return reg_hora;
    }

    public void setReg_hora(int reg_hora) {
        this.reg_hora = reg_hora;
    }

    public int getReg_min() {
        return reg_min;
    }

    public void setReg_min(int reg_min) {
        this.reg_min = reg_min;
    }

    public int getSal_hora() {
        return sal_hora;
    }

    public void setSal_hora(int sal_hora) {
        this.sal_hora = sal_hora;
    }

    public int getSal_min() {
        return sal_min;
    }

    public void setSal_min(int sal_min) {
        this.sal_min = sal_min;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public Time getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(Time hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }
    
    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
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
                + "\nfecha: " + this.getSesefeasisdoc_fecha()
                + "\nregistro:" + this.getReg_hora() + "-" + this.getReg_min()
                + "\nsalida: " + this.getSal_hora() + "-" + this.getSal_min()
                + "\nregistro:" + this.getSesefeasisdoc_registro()
                + "\nsalida: " + this.getSesefeasisdoc_salida()
                + "\nsesion id: " + this.getSes_id()
                + "\nobs: " + this.getSesefeasisdoc_obs();
    }
}
