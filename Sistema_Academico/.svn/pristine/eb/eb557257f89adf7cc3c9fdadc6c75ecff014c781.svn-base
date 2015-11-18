package net.uch.cursoLibre.managedBeans.beans;

import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSModuloDAO;
import net.uch.mapping.ClPublicoConsulta;


public class BeanClConsulta extends ClPublicoConsulta {

    //---- PARA ALMACENAR DATOS DE NUEVA CONSULTA
    
    //---- PARA MOSTRAR CONSULTAS REALIZADAS
    private int num;
    private String nomArea;
    private String fechReg;
    private String promotor;
    private String nomProcedencia;
    private String matricular;
    private String observacion;
    private String img_matricular;
    private String img_observacion;
    private String texto_matric;
    private String texto_observacion;

    public String getFechReg() {
        return fechReg;
    }

    public String getMatricular() {
        return matricular;
    }

    public String getNomArea() {
        return nomArea;
    }

    public String getNomProcedencia() {
        return nomProcedencia;
    }

    public int getNum() {
        return num;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getPromotor() {
        return promotor;
    }

    public String getImg_matricular() {
        return img_matricular;
    }

    public String getImg_observacion() {
        return img_observacion;
    }

    public String getTexto_matric() {
        return texto_matric;
    }

    public String getTexto_observacion() {
        return texto_observacion;
    }

    public void setFechReg(String fechReg) {
        this.fechReg = fechReg;
    }

    public void setMatricular(String matricular) {
        this.matricular = matricular;
    }

    /**
     *
     * @param codArea Código del módulo,viene del campo tipo_area de la tabla cl_publico_consulta
     */
    public void setNomArea(String codArea) {
        try {
            HSModuloDAO dao = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
            this.nomArea = dao.buscarModulo(Integer.parseInt(codArea.trim())).getModDescripcion();
        } catch (Exception ex) {
//            Logger.getLogger(BeanClConsulta.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void setNomProcedencia(String nomProcedencia) {
        this.nomProcedencia = nomProcedencia;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setPromotor(String promotor) {
        this.promotor = promotor;
    }

    public void setImg_matricular(String img_matricular) {
        this.img_matricular = img_matricular;
    }

    public void setImg_observacion(String img_observacion) {
        this.img_observacion = img_observacion;
    }

    public void setTexto_matric(String texto_matric) {
        this.texto_matric = texto_matric;
    }

    public void setTexto_observacion(String texto_observacion) {
        this.texto_observacion = texto_observacion;
    }

    public BeanClConsulta() {
    }

    public BeanClConsulta(int n) {
    }
}
