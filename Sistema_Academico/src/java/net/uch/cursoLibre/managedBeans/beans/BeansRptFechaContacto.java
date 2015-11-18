/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAreaDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSModuloDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAperturadoClDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSTallerDAO;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClArea;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClModulo;
import net.uch.mapping.ClPublicoConsulta;
import net.uch.mapping.ClTaller;
import net.uch.util.CommonDAO;

/**
 *
 * @author Jose Tejada
 */
public class BeansRptFechaContacto {

    private int consulta_id;
    private int numOrden;
    private String nombreAlumno;
    private String area;
    private String modulo;
    private String curso;
    private String horario;
    private String procedencia;
    private String tlfCelular;
    private String tlfFijo;
    private Date fechReg;
    private Date fechContac;
    private String matriculado;
    private String observacion;
    private static List<ClHoraria> listaHoraria;
    private String detHorario;
    private String alu_distrito;
    private String med_medio;
    private String med_medio_detalle;
    private String usu_usuario;
    private int w_are_id;

    public int getW_are_id() {
        return w_are_id;
    }

    public void setW_are_id(int w_are_id) {
        this.w_are_id = w_are_id;
    }

    

    public String getUsu_usuario() {
        return usu_usuario;
    }

    public void setUsu_usuario(String usu_usuario) {
        this.usu_usuario = usu_usuario;
    }

    

    public String getMed_medio() {
        return med_medio;
    }

    public void setMed_medio(String med_medio) {
        this.med_medio = med_medio;
    }

    public String getMed_medio_detalle() {
        return med_medio_detalle;
    }

    public void setMed_medio_detalle(String med_medio_detalle) {
        this.med_medio_detalle = med_medio_detalle;
    }

    

    public String getAlu_distrito() {
        return alu_distrito;
    }

    public void setAlu_distrito(String alu_distrito) {
        this.alu_distrito = alu_distrito;
    }

    

    public String getDetHorario() {
        return detHorario;
    }

    public void setDetHorario(String detHorario) {
        this.detHorario = detHorario;
    }

    

    public int getConsulta_id() {
        return consulta_id;
    }

    public void setConsulta_id(int consulta_id) {
        this.consulta_id = consulta_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Date getFechContac() {
        return fechContac;
    }

    public void setFechContac(Date fechContac) {
        this.fechContac = fechContac;
    }

    public Date getFechReg() {
        return fechReg;
    }

    public void setFechReg(Date fechReg) {
        this.fechReg = fechReg;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(String matriculado) {
        this.matriculado = matriculado;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getTlfCelular() {
        return tlfCelular;
    }

    public void setTlfCelular(String tlfCelular) {
        this.tlfCelular = tlfCelular;
    }

    public String getTlfFijo() {
        return tlfFijo;
    }

    public void setTlfFijo(String tlfFijo) {
        this.tlfFijo = tlfFijo;
    }

    public List<ClHoraria> getListaHoraria() {
        return listaHoraria;
    }

    public void setListaHoraria(List<ClHoraria> listaHoraria) {
        this.listaHoraria = listaHoraria;
    }

    public BeansRptFechaContacto() {
    }

    public BeansRptFechaContacto(int consulta_id) {
        this.consulta_id = consulta_id;
    }

    public BeansRptFechaContacto(int consulta_id, int numOrden, String nombreAlumno, String area, String modulo, String curso, String horario, String procedencia, String tlfCelular, String tlfFijo, Date fechReg, Date fechContac, String matriculado, String observacion, String obsHorario) {
        this.consulta_id = consulta_id;
        this.numOrden = numOrden;
        this.nombreAlumno = nombreAlumno;
        this.area = area;
        this.modulo = modulo;
        this.curso = curso;
        this.horario = horario;
        this.procedencia = procedencia;
        this.tlfCelular = tlfCelular;
        this.tlfFijo = tlfFijo;
        this.fechReg = fechReg;
        this.fechContac = fechContac;
        this.matriculado = matriculado;
        this.observacion = observacion;
        this.detHorario=obsHorario;
    }

    public static List<BeansRptFechaContacto> asignarValores(ArrayList<ClPublicoConsulta> lista) {
        HSAreaDAO areDAO = (HSAreaDAO) ServiceFinder.findBean("SpringHibernateDaoCLArea");
        HSModuloDAO modDAO = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
        HSArbolAperturadoClDAO talapeDAO = CommonDAO.getClArbolAperturadoClDAO();
        HSSeccionCLDAO secDAO = (HSSeccionCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLSeccion");
        HSTallerDAO daoTaller = (HSTallerDAO) ServiceFinder.findBean("SpringHibernateDaoCLTaller");
        List<BeansRptFechaContacto> listaRpt = new ArrayList<BeansRptFechaContacto>();
        int nro = 0;
        
        for (ClPublicoConsulta cons : lista) {
            try {
                //FALTA DARLE VALORES A LA LISTA DEL REPORTE SEGUN LOS VALORES
                //EXTRAÍDOS DE LA TABLA publico_consulta QUE ESTAN EN lista QUE VIENE COMO PARÁMETRO
                ClArea are = areDAO.buscarArea(Integer.parseInt(cons.getArea()));
                ClModulo mod = modDAO.buscarModulo(Integer.parseInt(cons.getTipoArea()));
               // ClTallerAperturado talape = talapeDAO.seleccionarTallerAperturado(cons.getDiasEstudio());
                ClTaller tal=daoTaller.buscarTaller(cons.getDiasEstudio());
                

                String horario = traerSecciones(cons.getDiasEstudio());
                String detHo=traerHorario(cons.getDiasEstudio());
                ClAlumno alu = cons.getClAlumno();
                String nomAlu = alu.getAluAppaterno() + " " + alu.getAluApmaterno() + ", " + alu.getAluNombres();
//                listaRpt.add(new BeansRptFechaContacto(cons.getConsultaId(), ++nro, nomAlu, are.getAreDescripcion(), mod.getModDescripcion(), tal.getTalDescripcion(), "", cons.getProcedencia().equals("1") ? "Presencial" : "Telefónica", cons.getClAlumno().getAluCelular(), cons.getClAlumno().getAluTelefono(), cons.getFechaRegistro(), cons.getFechaContacto(), cons.getMatriculado() == 1 ? "SI" : "NO", cons.getObsConsulta()));
                listaRpt.add(new BeansRptFechaContacto(cons.getConsultaId(), ++nro, nomAlu, are.getAreDescripcion(), mod.getModDescripcion(), tal != null ? tal.getTalDescripcion() : "ERROR, TALLER NO EXISTENTE", horario, cons.getProcedencia().equals("064001") ? "Presencial" : "Telefónica", cons.getClAlumno().getAluCelular(), cons.getClAlumno().getAluTelefono(), cons.getFechaRegistro(), cons.getFechaContacto(), cons.getMatriculado() == 1 ? "SI" : "NO", cons.getObsConsulta(),detHo));//traerHorario(listaSec)
            } catch (Exception ex) {
                Logger.getLogger(BeansRptFechaContacto.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        return listaRpt;
    }

    private static String traerHorario(Integer tal_id) {
        String detalleHorario = "";
       
        try {
            HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoCLHorario");
            List<ClHoraria> listaH = dao.seleccionarHorariosXTallerApe(tal_id);
            for (int i = 0; i < listaH.size(); i++) {
                ClHoraria h = new ClHoraria();
                detalleHorario+=listaH.get(i).getClSeccion().getSecNombre()+" - ";
                detalleHorario += h.traerNomDiaxCodigo(listaH.get(i).getHorDia()) + " : ";
                detalleHorario += listaH.get(i).getHorHini() + " a ";
                detalleHorario += listaH.get(i).getHorHfin() + "<br />";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalleHorario;
    }

     private static String traerSecciones(Integer tal_id) {
        String detalleHorario = "";

        try {
            HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoCLHorario");
            List<ClHoraria> listaH = dao.seleccionarHorariosXTallerApe(tal_id);
            int sec=0;
            for (int i = 0; i < listaH.size(); i++) {
                if(sec!=listaH.get(i).getClSeccion().getSecId().intValue()){
                    detalleHorario+=listaH.get(i).getClSeccion().getSecNombre()+"<br />";
                    
                }
                sec=listaH.get(i).getClSeccion().getSecId().intValue();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return detalleHorario;
    }
   
}
