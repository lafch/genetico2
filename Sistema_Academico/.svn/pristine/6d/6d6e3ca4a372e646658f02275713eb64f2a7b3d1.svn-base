/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.logicreport.dynamicJasper;
import net.uch.cursoLibre.managedBeans.beans.BeanSeccion;
import net.uch.mapping.ClArbolAcademico;
import net.uch.util.CommonDAO;
import net.uch.utilAdministrativo.MetodosAdm;

/**
 *
 * @author Alumno
 */
public class bConsultaAcademicaInicio {

    private SelectItem[] cboSede;
    private SelectItem[] cboAno;
    private SelectItem[] cboModulo;
    private SelectItem[] cboCurso;
    private SelectItem[] cboMes;
    private SelectItem[] cboDocente;
    private int w_sed_id = 0;
    private String w_ano_id = "0";
    private int w_mod_id = 0;
    private int w_cur_id = 0;
    private int w_mes = 0;
    private int doc_id = 0;
    private SelectItem[] rdoOpciones;
    private int w_rad_id;
    private List<BeanSeccion> listarSecciones = new ArrayList<BeanSeccion>();
    private String oncomplete;
    private String b_tipo_centro;
    private String b_articulo;

    public String getB_articulo() {
        return b_articulo;
    }

    public void setB_articulo(String b_articulo) {
        this.b_articulo = b_articulo;
    }

    public String getB_tipo_centro() {
        return b_tipo_centro;
    }

    public void setB_tipo_centro(String b_tipo_centro) {
        this.b_tipo_centro = b_tipo_centro;
    }



    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    

    public List<BeanSeccion> getListarSecciones() {
        return listarSecciones;
    }

    public void setListarSecciones(List<BeanSeccion> listarSecciones) {
        this.listarSecciones = listarSecciones;
    }

    public int getW_rad_id() {
        return w_rad_id;
    }

    public void setW_rad_id(int w_rad_id) {
        this.w_rad_id = w_rad_id;
    }

    public SelectItem[] getCboAno() {
        int anio = Calendar.getInstance().get( Calendar.YEAR );
        this.cboAno = new SelectItem[15];
        this.cboAno[0] = new SelectItem(0, "[[--Seleccione--]]");
        for (int i = 0; i < (this.cboAno.length - 1); i++) {
            this.cboAno[i + 1] = new SelectItem(anio - i, String.valueOf(anio - i));
        }
        return cboAno;
    }

    public void setCboAno(SelectItem[] cboAno) {
        this.cboAno = cboAno;
    }

    public SelectItem[] getCboCurso() {
        try {
            List<ClArbolAcademico> listaCurso = new ArrayList<ClArbolAcademico>();
            List<ClArbolAcademico> lista = new ArrayList<ClArbolAcademico>();
            if (this.getW_mod_id() > 0) {
                lista = agregarHijo(this.getW_mod_id(), "069003", listaCurso);
            }
            this.cboCurso = new SelectItem[lista.size() + 1];
            this.cboCurso[0] = new SelectItem("0", "[[--Seleccione--]])");
            for (int i = 0; i < (this.cboCurso.length - 1); i++) {
                this.cboCurso[i + 1] = new SelectItem(lista.get(i).getArbId(), lista.get(i).getArbDescripcion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboCurso;
    }

    public void setCboCurso(SelectItem[] cboCurso) {
        this.cboCurso = cboCurso;
    }

    public SelectItem[] getCboDocente() {
        try {
            HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean("SpringHibernateDaoDocente");
            List lista = dao.listarDocentesCursosLibreActivo();
            this.cboDocente = new SelectItem[lista.size() + 1];
            this.cboDocente[0] = new SelectItem(0, "[[--Seleccione--]]");
            for (int i = 0; i < (this.cboDocente.length - 1); i++) {
                Object[] obj = (Object[]) lista.get(i);
                this.cboDocente[i + 1] = new SelectItem(Integer.parseInt(obj[0].toString()), obj[1].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboDocente;
    }

    public void setCboDocente(SelectItem[] cboDocente) {
        this.cboDocente = cboDocente;
    }

    public SelectItem[] getCboMes() {
        this.cboMes = new SelectItem[13];
        this.cboMes[0] = new SelectItem(0, "[[--TODOS--]]");
        this.cboMes[1] = new SelectItem(1, "[[--ENERO--]]");
        this.cboMes[2] = new SelectItem(2, "[[--FEBRERO--]]");
        this.cboMes[3] = new SelectItem(3, "[[--MARZO--]]");
        this.cboMes[4] = new SelectItem(4, "[[--ABRIL--]]");
        this.cboMes[5] = new SelectItem(5, "[[--MAYO--]]");
        this.cboMes[6] = new SelectItem(6, "[[--JUNIO--]]");
        this.cboMes[7] = new SelectItem(7, "[[--JULIO--]]");
        this.cboMes[8] = new SelectItem(8, "[[--AGOSTO--]]");
        this.cboMes[9] = new SelectItem(9, "[[--SETIEMBRE--]]");
        this.cboMes[10] = new SelectItem(10, "[[--OCTUBRE--]]");
        this.cboMes[11] = new SelectItem(11, "[[--NOVIEMBRE--]]");
        this.cboMes[12] = new SelectItem(12, "[[--DICIEMBRE--]]");
        return cboMes;
    }

    public void setCboMes(SelectItem[] cboMes) {
        this.cboMes = cboMes;
    }

    public SelectItem[] getCboModulo() {
        try {
            HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
            List lista = daoArbol.listarAreaxAnoYSede(Integer.parseInt(this.w_ano_id), this.w_sed_id, 0);
            this.cboModulo = new SelectItem[lista.size() + 1];
            this.cboModulo[0] = new SelectItem(0, "[[Seleccione Modulo]]");
            for (int i = 0; i < (this.cboModulo.length - 1); i++) {
                Object[] obj = (Object[]) lista.get(i);
                this.cboModulo[i + 1] = new SelectItem(Integer.parseInt(obj[0].toString()), obj[3].toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboModulo;
    }

    public void setCboModulo(SelectItem[] cboModulo) {
        this.cboModulo = cboModulo;
    }

    public SelectItem[] getCboSede() throws SQLException {
        MetodosAdm metodo = new MetodosAdm();
        this.setCboSede(metodo.listarSedes("[[--Seleccione--]]"));
        return cboSede;
    }

    public void setCboSede(SelectItem[] cboSede) {
        this.cboSede = cboSede;
    }

    public SelectItem[] getRdoOpciones() {
        this.rdoOpciones = new SelectItem[2];
        this.rdoOpciones[0] = new SelectItem("1", "CURSOS QUE INICIAN");
        this.rdoOpciones[1] = new SelectItem("2", "CURSOS QUE TERMINAN");
        return rdoOpciones;
    }

    public void setRdoOpciones(SelectItem[] rdoOpciones) {
        this.rdoOpciones = rdoOpciones;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getW_ano_id() {
        return w_ano_id;
    }

    public void setW_ano_id(String w_ano_id) {
        this.w_ano_id = w_ano_id;
    }

    public int getW_cur_id() {
        return w_cur_id;
    }

    public void setW_cur_id(int w_cur_id) {
        this.w_cur_id = w_cur_id;
    }

    public int getW_mes() {
        return w_mes;
    }

    public void setW_mes(int w_mes) {
        this.w_mes = w_mes;
    }

    public int getW_mod_id() {
        return w_mod_id;
    }

    public void setW_mod_id(int w_mod_id) {
        this.w_mod_id = w_mod_id;
    }

    public int getW_sed_id() {
        return w_sed_id;
    }

    public void setW_sed_id(int w_sed_id) {
        this.w_sed_id = w_sed_id;
    }

    public void seleccionarSecciones(ActionEvent event) throws Exception {
        this.setListarSecciones(new ArrayList<BeanSeccion>());
        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLSeccion");
        //List lista=daoSec.listarSeccionesModulo(this.w_cur_id);
        List lista = daoSec.listarcantidadadMatriculadosxSeccion(this.w_mod_id, this.w_cur_id, Integer.parseInt(this.w_ano_id), this.w_mes);
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < lista.size(); i++) {
            Object[] obj = (Object[]) lista.get(i);
            BeanSeccion seccion = new BeanSeccion(Integer.parseInt(obj[0].toString()), obj[1].toString(),
                    obj[4].toString(), formatoDelTexto.parse(obj[2].toString()),
                    formatoDelTexto.parse(obj[3].toString()), (i + 1),
                    Integer.parseInt(obj[5].toString()));
            this.getListarSecciones().add(seccion);
        }

    }

    public List<ClArbolAcademico> agregarHijo(int idPadre, String tipo, List<ClArbolAcademico> listaArbol) {


        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
        List<ClArbolAcademico> lista = daoArbol.listarArbolPorPadre( idPadre );
        System.out.println("el ultimo padre -> " + idPadre);
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getArbTipo().equals(tipo)) {
                listaArbol.add(lista.get(i));
            } else {
                agregarHijo(lista.get(i).getArbId(), tipo, listaArbol);
            }
        }

        return listaArbol;
    }
    public void ImprimirFicha(ActionEvent event) throws Exception {
        
        setOncomplete("");
        
        //setTituloReporte("Ficha de Matricula");
        //setValorRep("ficha");
        //HashMap xparameters=loadParameters();


        //if(xparameters!=null)
            this.setOncomplete("javascript:Richfaces.showModalPanel('mp7')");
        //else
          //  this.setOncomplete("javascript:alert('Hace falta especificar algunos parametros ..!!!')");

    }

    public void cargarReporte(OutputStream out, Object data) throws IOException, Exception, EOFException {
       //if(getTipoReporte().contains("043")){
         //   HashMap xparameters=loadParameters();
           // if(xparameters!=null)
                cargarReporteDynamic(out);
       //}
   }

    public void cargarReporteDynamic(OutputStream out) throws IOException, Exception, EOFException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(cargar_reporteDynamic(1), buffer);
        //JasperExportManager.exportReportToPdf(cargar_reporteDynamic(w_sed_id));
        byte[] bytes = buffer.toByteArray();
        InputStream input = new ByteArrayInputStream(bytes);
        int size = input.available();
        byte[] pdf = new byte[size];
        input.read(pdf);
        out.write(pdf);
        buffer.flush();
        buffer.close();
        input.close();
        out.flush();
        out.close();

    }

    public JasperPrint cargar_reporteDynamic(int tipreport) {
        try {
            JasperPrint jasperPrint = null;
            dynamicJasper dina = new dynamicJasper();
            jasperPrint = dina.generarReporteCursosQInician(this.w_mod_id, this.w_cur_id, Integer.parseInt(this.w_ano_id), this.w_mes);
            return jasperPrint;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
