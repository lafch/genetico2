/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAreaDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSModuloDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSTallerDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPublicoConsultaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPublicoConsultaDetalleCLDAO;
import net.uch.cursoLibre.logicreport.dynamicJasper;
import net.uch.cursoLibre.managedBeans.beans.BeansRptFechaContacto;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.cursoLibre.utilsAcademico.Metodos;
import net.uch.mapping.ClArea;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClModulo;
import net.uch.mapping.ClPublicoConsulta;
import net.uch.mapping.ClPublicoConsultaDetalle;
import net.uch.mapping.ClTaller;
import net.uch.mapping.TbUsuario;
import net.uch.tablaSistema.hibernateSpringDao.HSUsuarioDAO;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class bFechaContacto2 {

    private HSAreaDAO daoArea;
    private HSModuloDAO daoModulo;
    private HSTallerDAO daoTaller;
    private HSSeccionCLDAO daoSeccion;
    private HSHorarioDAO daoHorario;
    private HSPublicoConsultaCLDAO daoConsulta;
    // VARIABLES DE CABECERA
    private SelectItem[] cboAreas;
    private int w_area_id;
    private SelectItem[] cboModulos;
    private int w_modulo_id;
    private SelectItem[] cboTalleres;
    private int w_taller_id;
    private SelectItem[] cboHorarios;
    private int w_horario_id;
    private Date fechContactoIni;
    private Date fechContactoFin;
    private SelectItem[] cboUsuario;
    private int w_usuario_id;
    // FIN VARIABLES DE CABECERA
    //VARIABLES DE LOGICA
    private String oncomplete;
    private List<BeansRptFechaContacto> listadoRpt;
    private Date fechaContacEdit;//variable para editar las fechas de contacto de las consultas
    private int w_consulta_id = 0;
    private String w_obs_consulta_editar;
    private SelectItem[] cboProcedencia;
    private String w_procedencia="000000";

    public List<ClPublicoConsultaDetalle> listarDetalle= new ArrayList<ClPublicoConsultaDetalle>();

    public SelectItem[] getCboProcedencia() {
        Metodos metodos = new Metodos();
        cboProcedencia = metodos.listarCatalogoGrupo("064","[ TODOS ]");
        return cboProcedencia;
    }

    public String getW_procedencia() {
        return w_procedencia;
    }

    public void setW_procedencia(String w_procedencia) {
        this.w_procedencia = w_procedencia;
    }
    
    public void setCboProcedencia(SelectItem[] cboProcedencia) {
        this.cboProcedencia = cboProcedencia;
    }


    
    public List<ClPublicoConsultaDetalle> getListarDetalle() {
        return listarDetalle;
    }

    public void setListarDetalle(List<ClPublicoConsultaDetalle> listarDetalle) {
        this.listarDetalle = listarDetalle;
    }
    //FIN VARIABLES DE LOGICA

    //FIN SETTERS Y GETTERS DE VARIABLES CABECERA

    
    public SelectItem[] getCboAreas() {
        try {
            daoArea = (HSAreaDAO) ServiceFinder.findBean("SpringHibernateDaoCLArea");
            List<ClArea> lista = daoArea.seleccionarArea("");
            cboAreas = new SelectItem[lista.size() + 1];
            cboAreas[0] = new SelectItem("0", "[Seleccionar área]");
            for (int i = 0; i < (cboAreas.length - 1); i++) {
                cboAreas[i + 1] = new SelectItem(lista.get(i).getAreId(), lista.get(i).getAreDescripcion());
            }
        } catch (Exception ex) {
        }
        return cboAreas;
    }

    public void setCboAreas(SelectItem[] cboAreas) {
        this.cboAreas = cboAreas;
    }

    public int getW_area_id() {
        return w_area_id;
    }

    public void setW_area_id(int w_area_id) {
        this.w_area_id = w_area_id;
    }

    public SelectItem[] getCboTalleres() {
        try {
            if (this.w_modulo_id != 0) {
                daoTaller = (HSTallerDAO) ServiceFinder.findBean("SpringHibernateDaoCLTaller");
                List<ClTaller> lista = daoTaller.seleccionarTalleresXMod(w_modulo_id);
                cboTalleres = new SelectItem[lista.size() + 1];
                cboTalleres[0] = new SelectItem("0", "[Seleccionar taller]");
                for (int i = 0; i < (cboTalleres.length - 1); i++) {
                    cboTalleres[i + 1] = new SelectItem(lista.get(i).getTalId(), lista.get(i).getTalDescripcion());
                }
            } else {
                cboTalleres = new SelectItem[1];
                cboTalleres[0] = new SelectItem("0", "[Seleccionar taller]");
            }
        } catch (Exception ex) {
        }
        return cboTalleres;
    }

    public void setCboTalleres(SelectItem[] cboTalleres) {
        this.cboTalleres = cboTalleres;
    }

    public int getW_taller_id() {
        return w_taller_id;
    }

    public void setW_taller_id(int w_taller_id) {
        this.w_taller_id = w_taller_id;
    }

    public Date getFechContactoFin() {
        fechContactoFin = new Date();
        return fechContactoFin;
    }

    public void setFechContactoFin(Date fechContactoFin) {
        this.fechContactoFin = fechContactoFin;
    }

    public Date getFechContactoIni() {
        fechContactoIni = new Date();
        return fechContactoIni;
    }

    public void setFechContactoIni(Date fechContactoIni) {
        this.fechContactoIni = fechContactoIni;
    }

    public SelectItem[] getCboHorarios() {
        try {
            if (this.w_taller_id != 0) {
                HSHorarioDAO dao = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoCLHorario");
                List<ClHoraria> listaHor = dao.seleccionarHorariosXTallerApe(w_taller_id);
                cboHorarios = new SelectItem[listaHor.size() + 1];
                cboHorarios[0] = new SelectItem("0", "[Seleccionar Horario]");
                for (int i = 0; i < (cboHorarios.length - 1); i++) {
                    cboHorarios[i + 1] = new SelectItem(listaHor.get(i).getHorId(),
                            listaHor.get(i).traerNomDiaxCodigo(listaHor.get(i).getHorDia())+" : "+listaHor.get(i).getHorHini().toString()+" - "+listaHor.get(i).getHorHfin().toString());
                }
            } else {
                cboHorarios = new SelectItem[1];
                cboHorarios[0] = new SelectItem("0", "[Seleccionar Horario]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cboHorarios;
    }

    public void setCboHorarios(SelectItem[] cboHorarios) {
        this.cboHorarios = cboHorarios;
    }

    public int getW_horario_id() {
        return w_horario_id;
    }

    public void setW_horario_id(int w_horario_id) {
        this.w_horario_id = w_horario_id;
    }

    public SelectItem[] getCboModulos() {
        try {
            if (this.w_area_id != 0) {
                daoModulo = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
                List<ClModulo> lista = daoModulo.seleccionarModulos(this.w_area_id, "");
                cboModulos = new SelectItem[lista.size() + 1];
                cboModulos[0] = new SelectItem("0", "[Seleccione]");
                for (int i = 0; i < (cboModulos.length - 1); i++) {
                    ClModulo modulo = lista.get(i);
                    if (modulo.getModActivo().equals("1")) {
                        cboModulos[i + 1] = new SelectItem(modulo.getModId(), modulo.getModDescripcion());
                    }
                }
            } else {
                cboModulos = new SelectItem[1];
                cboModulos[0] = new SelectItem("0", "[Seleccionar módulo]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cboModulos;
    }

    public void setCboModulos(SelectItem[] cboModulos) {
        this.cboModulos = cboModulos;
    }

    public int getW_modulo_id() {
        return w_modulo_id;
    }

    public void setW_modulo_id(int w_modulo_id) {
        this.w_modulo_id = w_modulo_id;
    }

    public SelectItem[] getCboUsuario() {
        try {
            HSUsuarioDAO dao=(HSUsuarioDAO)ServiceFinder.findBean("SpringHibernateDaoUsuario");
            List<TbUsuario> lista=dao.listarUsuarioPorRoles(1);
            System.out.println("la cantidad es -> "+lista.size());
            this.cboUsuario = new SelectItem[lista.size()+1];
            this.cboUsuario[0] = new SelectItem("0", "[Seleccionar usuario]");
           for(int i=0;i<(cboUsuario.length-1); i++){
                this.cboUsuario[i+1] = new SelectItem(lista.get(i).getId(), lista.get(i).getUsuUsuario());
                System.out.println("valo de i ->" +i);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cboUsuario;
    }

    public void setCboUsuario(SelectItem[] cboUsuario) {
        this.cboUsuario = cboUsuario;
    }

    public int getW_usuario_id() {
        return w_usuario_id;
    }

    public void setW_usuario_id(int w_usuario_id) {
        this.w_usuario_id = w_usuario_id;
    }
    //FIN SETTERS Y GETTERS DE VARIABLES CABECERA

    //SETTERS Y GETTERS DE VARIABLES DE LÓGICA
    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public List<BeansRptFechaContacto> getListadoRpt() {
        try {
            if (this.fechContactoIni != null && this.fechContactoFin != null) {
                String area = this.w_area_id == 0 ? "" : "" + this.w_area_id;
                String modulo = this.w_modulo_id == 0 ? "" : "" + this.w_modulo_id;
                Integer taller = this.w_taller_id == 0 ? -1 : this.w_taller_id;
                String horario = this.w_horario_id == 0 ? "" : "" + this.w_horario_id;

                daoConsulta = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
                ArrayList<ClPublicoConsulta> lista;
                lista = new ArrayList(daoConsulta.traerConsultasPorContactar(area, modulo, taller, horario, this.fechContactoIni, this.fechContactoFin,  this.w_procedencia));
               // System.out.println("cantidad de resultado es -> "+lista.size());
                this.listadoRpt = BeansRptFechaContacto.asignarValores(lista);
            }
        } catch (Exception ex) {
            listadoRpt = new ArrayList<BeansRptFechaContacto>();
            ex.printStackTrace();
        }
        return listadoRpt;
    }

    public void setListadoRpt(List<BeansRptFechaContacto> listadoRpt) {
        this.listadoRpt = listadoRpt;

    }

    public Date getFechaContacEdit() {
        return fechaContacEdit;
    }

    public void setFechaContacEdit(Date fechaContacEdit) {
        this.fechaContacEdit = fechaContacEdit;
    }

    public int getW_consulta_id() {
        return w_consulta_id;
    }

    public void setW_consulta_id(int w_consulta_id) {
        this.w_consulta_id = w_consulta_id;
    }

    public String getW_obs_consulta_editar() {
        return w_obs_consulta_editar;
    }

    public void setW_obs_consulta_editar(String w_obs_consulta_editar) {
        this.w_obs_consulta_editar = w_obs_consulta_editar;
    }

    //FIN SETTERS Y GETTERS DE VARIABLES DE LÓGICA
    //MÉTODOS
    public void mostrarCambioFecha(ActionEvent evt) {
        this.w_consulta_id = Integer.parseInt(((UIParameter) evt.getComponent().findComponent("consulta_id")).getValue().toString());
        this.fechaContacEdit = daoConsulta.traerConsultaXConsultaId(this.w_consulta_id).getFechaContacto();
        this.oncomplete = "javascript:Richfaces.showModalPanel('mpCambioFechaContacto');";
    }

    public void editarFechaContacto(ActionEvent evt) {
        if (this.w_consulta_id != 0) {
            try {
                HSPublicoConsultaCLDAO daoCons = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
                ClPublicoConsulta cons = daoCons.traerConsultaXConsultaId(this.w_consulta_id);
                cons.setFechaContacto(this.getFechaContacEdit());
                daoCons.cambiarFechaContactoConsulta(cons);
                this.oncomplete = "javascript:Richfaces.hideModalPanel('mpCambioFechaContacto');alert('La fecha se modificó correctamente')";
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void mostrarObervacion(ActionEvent event) throws Exception {
        this.w_consulta_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("consulta_id")).getValue().toString());
        HSPublicoConsultaCLDAO dao = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
        this.setW_obs_consulta_editar(dao.traerConsultaXConsultaId(this.w_consulta_id).getObsConsulta());
        this.setOncomplete("");
        this.setOncomplete("Richfaces.showModalPanel('mpObservacion')");
    }

    public void actualizarObsConsulta(ActionEvent event) {
        int consulta_id_edit = this.getW_consulta_id();
        String nuevaObs = this.getW_obs_consulta_editar();

        HSPublicoConsultaCLDAO dao = (HSPublicoConsultaCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsulta");
        dao.modificarPublicoConsulta(consulta_id_edit, nuevaObs);
        //this.guardarPublicoConsultaDetalle(new ClPublicoConsultaDetalle(0, new ClPublicoConsulta(consulta_id_edit), nuevaObs, new Date()));
    }

    public void guardarPublicoConsultaDetalle(ClPublicoConsultaDetalle consultaDetalle) {
        HSPublicoConsultaDetalleCLDAO dao = (HSPublicoConsultaDetalleCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsultaDetalle");
        dao.guardarConsultaDetalle(consultaDetalle);
        this.oncomplete = "javascript:Richfaces.hideModalPanel('mpObservacion');alert('Consulta actualizada correctamente')";
    }

    public void filtrar(ActionEvent evt) {
        System.out.println("entro al listado");
        getListadoRpt();
        System.out.println("salio del listado");
    }

    public void exportarExcel(ActionEvent evt) {
        this.oncomplete = "";
        this.oncomplete = "javascript:alert('Aun no Exporta :D')";
    }
    //FIN METODOS



    // metodos crea Reprts.

   public String createQuery() {

     StringBuilder queryp = null;
     String query=  "select concat_ws('  ',cl_alumno.alu_appaterno,cl_alumno.alu_apmaterno,cl_alumno.alu_nombres) as alumno, "+
                    "cl_area.are_descripcion as area, (select cl_modulo.mod_descripcion from cl_modulo where cl_modulo.mod_id=cl_publico_consulta.tipo_area  ) as modulo,   "+
                    "(select cl_taller.tal_descripcion from cl_taller where cl_taller.tal_id =cl_publico_consulta.dias_estudio )  as curso ,  "+
                    "if( cl_publico_consulta.procedencia=1, 'PRESENCIAL','TELEFONICO') as procedencia,  "+
                    "if( cl_publico_consulta.sede=1,'COLONIAL','LOS OLIVOS') as Sede, "+
                    "ifnull(cl_alumno.alu_telefono,'') as telefono,ifnull( cl_alumno.alu_celular,'') as celular,  "+
                    "cl_publico_consulta.fecha_contacto , cl_publico_consulta.fecha_registro ,if(cl_publico_consulta.matriculado=1,'SI','NO') as matriculado  "+
                    "from cl_publico_consulta inner join cl_alumno on cl_alumno.alu_id=cl_publico_consulta.alu_id "+
                    "inner join cl_area on cl_publico_consulta.area=cl_area.are_id Where cl_publico_consulta.fecha_contacto BETWEEN  ";

        try {
            System.out.println(fechContactoIni+" hasta "+fechContactoFin);
            
            if (this.fechContactoIni != null && this.fechContactoFin != null) {
               // System.out.println(fechContactoIni+" hasta "+fechContactoFin);
                String area = this.w_area_id == 0 ? "" : "" + this.w_area_id;
                String modulo = this.w_modulo_id == 0 ? "" : "" + this.w_modulo_id;
                Integer taller = this.w_taller_id == 0 ? -1 : this.w_taller_id;
                String horario = this.w_horario_id == 0 ? "" : "" + this.w_horario_id;

                SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
                String de= formatDate.format(fechContactoIni);
                String has= formatDate.format(fechContactoFin);

                queryp=new StringBuilder(query);
                queryp.append("'" +de);
                queryp.append("' and '");
                queryp.append(has+"' and  ");

                if (!area.isEmpty()) {
                    queryp.append(" cl_publico_consulta.area ="+area);
                }
                if (!modulo.isEmpty()) {
                    String n="";
                    if(!area.isEmpty())
                        n=" and ";
                    queryp.append(n+" cl_publico_consulta.tipo_area ="+modulo);
                }

                /*
                if (!horario.isEmpty()) {
                    query=query+"cl_publico_consulta.tipo_area ="+modulo;
                }*/
                if (taller != -1) {
                    String n="";
                    if(! (area.isEmpty() &&  modulo.isEmpty()) )
                        n=" and ";
                    queryp.append(n+" cl_publico_consulta.dias_estudio ="+taller);
                }

                queryp.append(" order by 2,3 ");

                if((area.isEmpty() &&  modulo.isEmpty() && taller==-1 ) )
                    return "";

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
        System.out.println(queryp.toString());
        return queryp.toString();
    }

   public void cargarReporte(OutputStream out, Object data)
            throws IOException, EOFException, SQLException, JRException, ColumnBuilderException {

            try {
                Style headerStyle = new Style();
                headerStyle.setFont(new Font(7, Font._FONT_VERDANA, true));
                headerStyle.setBorder(Border.THIN);
                headerStyle.setBackgroundColor(Color.darkGray);
                headerStyle.setTextColor(Color.blue);

                Style cellStyle = new Style();
                cellStyle.setFont(new Font(7, Font._FONT_VERDANA, true));
                cellStyle.setBorder(Border.THIN);

                JasperPrint jasperPrint = null;
                FastReportBuilder drb = new FastReportBuilder();


                drb.

                    addColumn("Alumno", "alumno", String.class.getName(), 60,headerStyle).
                    addColumn("Modulo", "modulo", String.class.getName(), 30,headerStyle).
                    addColumn("Curso", "curso", String.class.getName(), 30,headerStyle).
                    addColumn("Procedenc.", "procedencia", String.class.getName(), 20,headerStyle).
                    addColumn("Sede", "Sede", String.class.getName(), 20,headerStyle).
                    addColumn("Celular", "celular", String.class.getName(), 15,headerStyle).
                    addColumn("Telf.", "telefono", String.class.getName(),15,headerStyle).
                    addColumn("Matr.", "matriculado", String.class.getName(),10,headerStyle).
                    setTitle("Listado de Contacto de Alumnos").
                    setQuery(createQuery(), DJConstants.QUERY_LANGUAGE_SQL).
                    //setQuery(oncomplete, oncomplete)
                    setWhenNoData(" HO HAY resultados..... ", headerStyle).
                    setUseFullPageWidth(true);

                DynamicReport dr = drb.build();
                Connection cnx = null;
                DriverManagerDataSource dmds = (DriverManagerDataSource) ServiceFinder.findBean("dataSource");

                try {
                    cnx = dmds.getConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(dynamicJasper.class.getName()).log(Level.SEVERE, null, ex);
                }

                HashMap p=new HashMap();
                jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),cnx,p);
                cnx.close();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, buffer);
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

            catch (ColumnBuilderException ex) {
                Logger.getLogger(bFechaContacto2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(bFechaContacto2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

   public void seleccionarDetalle(ActionEvent event){
       this.setOncomplete("");
       HSPublicoConsultaDetalleCLDAO dao = (HSPublicoConsultaDetalleCLDAO) ServiceFinder.findBean("SpringHibernateDaoPublicoConsultaDetalle");
       int con_id = Integer.parseInt(((UIParameter) event.getComponent().findComponent("consulta_id")).getValue().toString());
       this.setListarDetalle(dao.listarPublicoConsultaDetalle(con_id));
       this.setOncomplete("javascript:Richfaces.showModalPanel('mpConsultaDetalle');");
        
   }
    
}
