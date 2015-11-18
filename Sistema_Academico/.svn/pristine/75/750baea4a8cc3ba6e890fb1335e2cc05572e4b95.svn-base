/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.laboratorio.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.laboratorio.hibernateSpringDao.TbIncidenciaLabDAO;
import net.uch.mapping.*;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import org.richfaces.component.html.HtmlSuggestionBox;

/**
 *
 * @author biblioteca
 */
public class MBIncidenciaLab {
    private List<AcAlumno> listaAlumnos;
    private List<BeanIncidenciaLab> listaIncidenciaLabs;
    private String oncomplete;
    private BeanIncidenciaLab objBeanIncidenciaLab;
    private int p_indice_alu;
    private int p_indice_inci;
    private String w_paterno;
    private String w_materno;
    private String w_nombre;
    private SelectItem[] cboCursos;
    private String w_suggestionDato;
    private int w_alu_id=0;
    private SelectItem[] cboEstadoLaboratorio;
    private String w_descripcion;

    public String getW_descripcion() {
        return w_descripcion;
    }

    public void setW_descripcion(String w_descripcion) {
        this.w_descripcion = w_descripcion;
    }
    
    public MBIncidenciaLab() {
        this.listaAlumnos=new ArrayList<AcAlumno>();
        this.listaIncidenciaLabs= new ArrayList<BeanIncidenciaLab>();
        iniciarIncidencia();
    }
    
    public void iniciarIncidencia(){
        this.objBeanIncidenciaLab= new BeanIncidenciaLab();
        this.getObjBeanIncidenciaLab().setTbIncidenciaLab(new TbIncidenciaLab());
        this.getObjBeanIncidenciaLab().getTbIncidenciaLab().setAcAlumno(new AcAlumno());
    }

    public List<AcAlumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<AcAlumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public List<BeanIncidenciaLab> getListaIncidenciaLabs() {
        return listaIncidenciaLabs;
    }

    public void setListaIncidenciaLabs(List<BeanIncidenciaLab> listaIncidenciaLabs) {
        this.listaIncidenciaLabs = listaIncidenciaLabs;
    }

    public BeanIncidenciaLab getObjBeanIncidenciaLab() {
        return objBeanIncidenciaLab;
    }

    public void setObjBeanIncidenciaLab(BeanIncidenciaLab objBeanIncidenciaLab) {
        this.objBeanIncidenciaLab = objBeanIncidenciaLab;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public int getP_indice_alu() {
        return p_indice_alu;
    }

    public void setP_indice_alu(int p_indice_alu) {
        this.p_indice_alu = p_indice_alu;
    }

    public int getP_indice_inci() {
        return p_indice_inci;
    }

    public void setP_indice_inci(int p_indice_inci) {
        this.p_indice_inci = p_indice_inci;
    }

    public String getW_nombre() {
        return w_nombre;
    }

    public void setW_nombre(String w_nombre) {
        this.w_nombre = w_nombre;
    }
    

    public String getW_materno() {
        return w_materno;
    }

    public void setW_materno(String w_materno) {
        this.w_materno = w_materno;
    }

    public String getW_paterno() {
        return w_paterno;
    }

    public void setW_paterno(String w_paterno) {
        this.w_paterno = w_paterno;
    }

    public String getW_suggestionDato() {
        return w_suggestionDato;
    }

    public void setW_suggestionDato(String w_suggestionDato) {
        this.w_suggestionDato = w_suggestionDato;
    }

    public int getW_alu_id() {
        return w_alu_id;
    }

    public void setW_alu_id(int w_alu_id) {
        this.w_alu_id = w_alu_id;
    }
    
    public SelectItem[] getCboCursos() {
        if(this.w_alu_id>0){
            HSAlumnoDAO dao=(HSAlumnoDAO)ServiceFinder.findBean("SpringHibernateDaoAlumno");
            List<Sp_cursosPorAlumno> lista=dao.cursosPorAlumno(this.getObjBeanIncidenciaLab().getTbIncidenciaLab().getAcAlumno().getId());
            this.cboCursos=new SelectItem[lista.size()+1];
            this.cboCursos[0]= new SelectItem(0, "[-Seleccione-]");
            for(int i=0; i<(this.cboCursos.length - 1) ; i++){
                this.cboCursos[i+1]=new SelectItem(lista.get(i).getCurId(), lista.get(i).getCurNombre());
            }
        }
        else{
            this.cboCursos=new SelectItem[1];
            this.cboCursos[0]= new SelectItem(0, "[-Seleccione-]");
        }
        return cboCursos;
    }

    public void setCboCursos(SelectItem[] cboCursos) {
        this.cboCursos = cboCursos;
    }

    public SelectItem[] getCboEstadoLaboratorio() {
        HSCatalogoDAO dao=(HSCatalogoDAO)ServiceFinder.findBean("SpringHibernateDaoCatalogo");
        List<TbCatalogo> lista=dao.seleccionarGrupo("074");
        this.cboEstadoLaboratorio= new SelectItem[lista.size()+1];
        this.cboEstadoLaboratorio[0]= new SelectItem("0", "[-Seleccione-]");
        for(int i=0; i<(this.cboEstadoLaboratorio.length - 1); i++){
            this.cboEstadoLaboratorio[i+1]= new SelectItem(lista.get(i).getCatCodigoGrupo().concat(lista.get(i).getCatCodigoElemento()),lista.get(i).getCatDescripcionElemento());
        }
        return cboEstadoLaboratorio;
    }

    public void setCboEstadoLaboratorio(SelectItem[] cboEstadoLaboratorio) {
        this.cboEstadoLaboratorio = cboEstadoLaboratorio;
    }
    
    
   
    public void nuevo(){
        this.setOncomplete("");
        this.setObjBeanIncidenciaLab(new BeanIncidenciaLab());
        this.getObjBeanIncidenciaLab().setTbIncidenciaLab(new TbIncidenciaLab());
        this.getObjBeanIncidenciaLab().getTbIncidenciaLab().setAcTurno(new AcTurno());
        this.setW_alu_id(0);
        this.w_suggestionDato="";
        this.setOncomplete("Richfaces.showModalPanel('mpIncidencia')");
        
    }
    
    public void seleccionar() throws Exception{
        this.setOncomplete("");
        this.w_suggestionDato="";
        this.setObjBeanIncidenciaLab(new BeanIncidenciaLab());
        this.setObjBeanIncidenciaLab(this.getListaIncidenciaLabs().get(this.p_indice_inci));
        this.w_alu_id=this.getObjBeanIncidenciaLab().getTbIncidenciaLab().getAcAlumno().getId();
        HSAlumnoDAO dao=(HSAlumnoDAO)ServiceFinder.findBean("SpringHibernateDaoAlumno");
       List<Sp_cursosPorAlumno> lista=dao.cursosPorAlumno(this.getObjBeanIncidenciaLab().getTbIncidenciaLab().getAcAlumno().getId());
       Sp_cursosPorAlumno objeto=new Sp_cursosPorAlumno();
       for(int i=0; i<lista.size(); i++){
           if(lista.get(i).getCurId().equals(this.getObjBeanIncidenciaLab().getTbIncidenciaLab().getCurId())){
               objeto=lista.get(i);
           }
       }
       AcAlumno alumno=dao.seleccionarAlumno(this.w_alu_id);
       this.setW_suggestionDato(alumno.getAluAppaterno()+" "+alumno.getAluApmaterno()+" "+alumno.getAluNombres());
       this.getObjBeanIncidenciaLab().setWinc_ciclo(objeto.getpCicloDes());
        this.setOncomplete("Richfaces.showModalPanel('mpIncidencia')");
    }
    
    public void grabar(){
        this.setOncomplete("");
        TbIncidenciaLabDAO dao=(TbIncidenciaLabDAO) ServiceFinder.findBean("TbIncidenciaLabDAOHS");
        this.objBeanIncidenciaLab.getTbIncidenciaLab().setTipoIncidencia("077001");
        dao.attachDirty(this.objBeanIncidenciaLab.getTbIncidenciaLab());
        this.setOncomplete("Richfaces.hideModalPanel('mpIncidencia')");
    }
    
    
    public List<AcAlumno> listaSuggestion(Object dato){
        listaAlumnos=new ArrayList<AcAlumno>();
        List lista=new ArrayList();
        String datos=(String) dato;
        if(datos.trim().length()>1){
            HSAlumnoDAO dao=(HSAlumnoDAO)ServiceFinder.findBean("SpringHibernateDaoAlumno");
            lista=dao.listarAlumnosporDato(datos);
            for(int i=0; i<lista.size(); i++){
                Object[] obj=(Object[])lista.get(i);
                AcAlumno alumno= new AcAlumno();
                alumno.setId(Integer.parseInt(obj[0].toString()));
                alumno.setAluCodigo(obj[1].toString());
                alumno.setAluAppaterno(obj[2].toString());
                alumno.setAluApmaterno(obj[3].toString());
                alumno.setAluNombres(obj[4].toString());
                System.out.println("el valor del codigo  -> "+obj[1].toString()+"\t paterno "+obj[2].toString());
                listaAlumnos.add(alumno);
            }
        }
        
        return listaAlumnos;
    }
    
    public void seleccionarAlumnoSugg(ActionEvent event) throws Exception{
        this.w_alu_id=0;
       this.getObjBeanIncidenciaLab().setTbIncidenciaLab(new TbIncidenciaLab()); 
        final HtmlSuggestionBox sbox = (HtmlSuggestionBox) event.getComponent().getParent();
              System.out.println(sbox);
              System.out.println(sbox.getRowIndex());
       AcAlumno autocompli = ((List<AcAlumno>)sbox.getValue()).get(sbox.getRowIndex());
       HSAlumnoDAO dao=(HSAlumnoDAO)ServiceFinder.findBean("SpringHibernateDaoAlumno");
       AcAlumno alumno=dao.seleccionarAlumno(autocompli.getId());
       //this.getObjBeanIncidenciaLab().getTbIncidenciaLab().setInclabCicloAlu(dao.creditosPorCicloAlumno(autocompli.getId()).get(0).getpCodigoCiclo());
       //this.getObjBeanIncidenciaLab().setWinc_curso(dao.creditosPorCicloAlumno(autocompli.getId()).get(0).getpCicloDes());
       //this.getObjBeanIncidenciaLab().setTbIncidenciaLab(new TbIncidenciaLab());
       this.getObjBeanIncidenciaLab().getTbIncidenciaLab().setInclabActivo("1");
       this.getObjBeanIncidenciaLab().getTbIncidenciaLab().setCurId(0);
       this.getObjBeanIncidenciaLab().getTbIncidenciaLab().setAcAlumno(alumno);
       this.getObjBeanIncidenciaLab().getTbIncidenciaLab().setInclabCicloAlu(dao.creditosPorCicloAlumno(autocompli.getId()).get(0).getpCodigoCiclo());
       this.w_alu_id=alumno.getId();
    }
    
    public void seleccionarCurso(){
       HSAlumnoDAO dao=(HSAlumnoDAO)ServiceFinder.findBean("SpringHibernateDaoAlumno");
       List<Sp_cursosPorAlumno> lista=dao.cursosPorAlumno(this.getObjBeanIncidenciaLab().getTbIncidenciaLab().getAcAlumno().getId());
       Sp_cursosPorAlumno objeto=new Sp_cursosPorAlumno();
       for(int i=0; i<lista.size(); i++){
           if(lista.get(i).getCurId().equals(this.getObjBeanIncidenciaLab().getTbIncidenciaLab().getCurId())){
               objeto=lista.get(i);
           }
       }
       AcTurno turno= new AcTurno();
       turno.setTurNombre(objeto.getTurNombre());
       turno.setId(objeto.getTurId());
       this.objBeanIncidenciaLab.getTbIncidenciaLab().setAcTurno(turno);
       this.getObjBeanIncidenciaLab().setWinc_curso(objeto.getCurNombre());
       this.getObjBeanIncidenciaLab().setWinc_ciclo(objeto.getpCicloDes());
       this.getObjBeanIncidenciaLab().setWinc_turno(objeto.getTurNombre());
       this.getObjBeanIncidenciaLab().getTbIncidenciaLab().setSecId(objeto.getSecId());
    }
    
    public synchronized void buscar(){
        this.setListaIncidenciaLabs(new ArrayList<BeanIncidenciaLab>());
        TbIncidenciaLabDAO dao=(TbIncidenciaLabDAO) ServiceFinder.findBean("TbIncidenciaLabDAOHS");
        List<TbIncidenciaLab> lista=dao.getListarIncidenciaTipo("077001",this.w_descripcion);
        for(int i=0; i<lista.size(); i++){
            BeanIncidenciaLab objeto=new BeanIncidenciaLab();
            objeto.setTbIncidenciaLab(lista.get(i));
            this.getListaIncidenciaLabs().add(objeto);
        }
    }
}
