/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans.beans;

import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSSeccionDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcSeccion;

/**
 *
 * @author richard
 */
public class BeanCursosMatricular {

    private Integer curapeId;
    private String planCodigo;
    private String plancurCodigo;
    private Integer plancurId;
    private String planTipo;
    private String ciclo;
    private String plancurCiclo;
    private String curNombre;
    private Integer espId;
    private Integer plancurCredito;
    private String pre;
    private Integer aluId;
    private Integer repitencia;
    private Integer secId;
    private SelectItem[] cboSecciones;
    private Integer semId;

    public Integer getAluId() {
        return aluId;
    }

    public Integer getSemId() {
        return semId;
    }

    public void setSemId(Integer semId) {
        this.semId = semId;
    }

    public void setAluId(Integer aluId) {
        this.aluId = aluId;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getCurNombre() {
        return curNombre;
    }

    public void setCurNombre(String curNombre) {
        this.curNombre = curNombre;
    }

    public Integer getCurapeId() {
        return curapeId;
    }

    public void setCurapeId(Integer curapeId) {
        this.curapeId = curapeId;
    }

    public Integer getEspId() {
        return espId;
    }

    public void setEspId(Integer espId) {
        this.espId = espId;
    }

    public String getPlanCodigo() {
        return planCodigo;
    }

    public void setPlanCodigo(String planCodigo) {
        this.planCodigo = planCodigo;
    }

    public String getPlanTipo() {
        return planTipo;
    }

    public void setPlanTipo(String planTipo) {
        this.planTipo = planTipo;
    }

    public String getPlancurCiclo() {
        return plancurCiclo;
    }

    public void setPlancurCiclo(String plancurCiclo) {
        this.plancurCiclo = plancurCiclo;
    }

    public String getPlancurCodigo() {
        return plancurCodigo;
    }

    public void setPlancurCodigo(String plancurCodigo) {
        this.plancurCodigo = plancurCodigo;
    }

    public Integer getPlancurCredito() {
        return plancurCredito;
    }

    public void setPlancurCredito(Integer plancurCredito) {
        this.plancurCredito = plancurCredito;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public Integer getRepitencia() {
        return repitencia;
    }

    public void setRepitencia(Integer repitencia) {
        this.repitencia = repitencia;
    }

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        this.secId = secId;
    }

    public SelectItem[] getCboSecciones() {
        return cboSecciones;
    }

    public void setCboSecciones(SelectItem[] cboSecciones) {
        this.cboSecciones = cboSecciones;
    }

    public SelectItem[] llenarSecciones(int curapeId, int sem_id) {
        SelectItem[] select = null;
        HSSeccionDAO dao = (HSSeccionDAO) ServiceFinder.findBean("HibernateSpringDaoSeccion");
        List lista = dao.obtenerSeccionesCurape(curapeId, sem_id);
        select = new SelectItem[lista.size() + 1];
        select[0] = new SelectItem(0, "[-Seleccione-]");
        for (int i = 0; i < (select.length - 1); i++) {
            select[i + 1] = new SelectItem(((AcSeccion) lista.get(i)).getId(), ((AcSeccion) lista.get(i)).getSecNombre());
        }
        return select;
    }

    public Integer getPlancurId() {
        return plancurId;
    }

    public void setPlancurId(Integer plancurId) {
        this.plancurId = plancurId;
    }
}
