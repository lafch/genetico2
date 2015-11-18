/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSConsultaPublicoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPlantillaHorarioCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPublicoConsultaDetalleCLDAO;
import net.uch.cursoLibre.managedBeans.beans.BeansRptFechaContacto;
import net.uch.cursoLibre.utilsAcademico.Metodos;
import net.uch.mapping.*;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUsuarioDAO;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import net.uch.util.Reporte;
import java.util.HashMap;
import net.uch.tablaSistema.managedBeans.bUsuario;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.uch.cursoLibre.hibernateSpringDao.*;
import net.uch.cursoLibre.logicreport.ReporteFechaContacto;

public class bFechaContacto {

    private SelectItem[] cboCategoria;
    private SelectItem[] cboCentro;
    private SelectItem[] cboEspecialidad;
    private SelectItem[] cboArea;
    private SelectItem[] cboModulo;
    private SelectItem[] cboCurso;
    private SelectItem[] cboHorario;
    private SelectItem[] cboUsuario;
    private SelectItem[] cbomatricula;
    private SelectItem[] cboMedio;
    private SelectItem[] cboMedioDet;
    private SelectItem[] cboProcedencia;
    private SelectItem[] cboPrioridad;
    private String w_categoria_id = "0";
    private String w_centro_id = "0";
    private int w_especialidad_id = 0;
    private int w_esp_id = 0;
    private int w_are_id = 0;
    private int w_mod_id = 0;
    private int iCursoId = 0;
    private int w_hor_id = 0;
    private int w_usu_id = 0;
    private int iMedioId = 0;
    private int iMedioDetId = 0;
    private String sPrioridadId = "000000";
    private String w_mat_tipo = "1";
    private String w_pro_id = "000000";
    private Date w_fecha_ini = new Date();
    private Date w_fecha_fin = new Date();
    private List<BeansRptFechaContacto> listaFechaContacto = new ArrayList<BeansRptFechaContacto>();
    private ClPublicoConsultaDetalle detalleConsulta = new ClPublicoConsultaDetalle();
    private String oncomplete;
    private int w_con_id = 0;
    public List<ClPublicoConsultaDetalle> listarDetalle = new ArrayList<ClPublicoConsultaDetalle>();
    private String tituloReporte = "";
    private String valorRep = "";
    bUsuario usu;

    // SETTER AND GETTER
    public bUsuario getUsu() {
        return usu;
    }

    public void setUsu( bUsuario usu ) {
        this.usu = usu;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte( String tituloReporte ) {
        this.tituloReporte = tituloReporte;
    }

    public String getValorRep() {
        return valorRep;
    }

    public void setValorRep( String valorRep ) {
        this.valorRep = valorRep;
    }

    public String getPrioridadId() {
        return sPrioridadId;
    }

    public void setPrioridadId( String sPrioridadId ) {
        this.sPrioridadId = sPrioridadId;
    }

    public int getMedioId() {
        return iMedioId;
    }

    public void setMedioId( int iMedioId ) {
        this.iMedioId = iMedioId;
    }

    public int getMedioDetId() {
        return iMedioDetId;
    }

    public void setMedioDetId( int iMedioDetId ) {
        this.iMedioDetId = iMedioDetId;
    }

    public SelectItem[] getCboPrioridad() {
        Metodos metodo = new Metodos();
        this.setCboPrioridad( metodo.listarCatalogoGrupo( "079", "[ TODOS ]" ) );
        return cboPrioridad;
    }

    public void setCboPrioridad( SelectItem[] cboPrioridad ) {
        this.cboPrioridad = cboPrioridad;
    }

    public void setCboCurso( SelectItem[] cboCurso ) {
        this.cboCurso = cboCurso;
    }

    public int getiCursoId() {
        return iCursoId;
    }

    public void setiCursoId( int iCursoId ) {
        this.iCursoId = iCursoId;
    }

    public List<ClPublicoConsultaDetalle> getListarDetalle() {
        return listarDetalle;
    }

    public void setListarDetalle( List<ClPublicoConsultaDetalle> listarDetalle ) {
        this.listarDetalle = listarDetalle;
    }

    public int getW_con_id() {
        return w_con_id;
    }

    public void setW_con_id( int w_con_id ) {
        this.w_con_id = w_con_id;
    }

    public bFechaContacto() {
        this.getDetalleConsulta().setFecha( new Date() );
        buscarConsultaPublico( null );
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public ClPublicoConsultaDetalle getDetalleConsulta() {
        return detalleConsulta;
    }

    public void setDetalleConsulta( ClPublicoConsultaDetalle detalleConsulta ) {
        this.detalleConsulta = detalleConsulta;
    }

    public SelectItem[] getCboCategoria() {
        cboCategoria = new SelectItem[ 3 ];
        cboCategoria[0] = new SelectItem( "0", "[Seleccionar Categoria]" );
        cboCategoria[1] = new SelectItem( "1", "CURSOS LIBRES" );
        cboCategoria[2] = new SelectItem( "2", "CARRERAS PROFESIONALES" );
        return cboCategoria;
    }

    public void setCboCategoria( SelectItem[] cboCategoria ) {
        this.cboCategoria = cboCategoria;
    }

    public SelectItem[] getCboEspecialidad() {
        AcEspecialidad especialidad;
        List lstEspecialidades;

        if ( "2".equals( w_categoria_id ) ) {
            lstEspecialidades = CommonDAO.getAcEspecialidadDAO().seleccionarEspecialidad();
        } else {
            lstEspecialidades = new ArrayList();
        }

        cboEspecialidad = new SelectItem[ lstEspecialidades.size() + 1 ];
        cboEspecialidad[0] = new SelectItem( "0", "Seleccione Especialidad" );
        for ( int i = 0; i < lstEspecialidades.size(); i++ ) {
            especialidad = (AcEspecialidad) lstEspecialidades.get( i );
            cboEspecialidad[ i + 1] = new SelectItem( especialidad.getId(), especialidad.getEspNombre() );
        }

        return cboEspecialidad;
    }

    public void setCboEspecialidad( SelectItem[] cboEspecialidad ) {
        this.cboEspecialidad = cboEspecialidad;
    }

    public SelectItem[] getCboCentro() {
        int i;
        int iAdd;
        List<TbCatalogo> lstCentros;

        if ( "1".equals( w_categoria_id ) ) {
            lstCentros = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "078" );
            iAdd = 0;
        } else {
            lstCentros = new ArrayList();
            iAdd = 1;
        }

        cboCentro = new SelectItem[ lstCentros.size() + iAdd ];
        cboCentro[0] = new SelectItem( "0", "Seleccione Centro" );
        i = 0;
        for ( TbCatalogo catCentro : lstCentros ) {
            if ( !"004".equals( catCentro.getCatCodigoElemento() ) ) {
                cboCentro[ ++i] = new SelectItem( catCentro.getCatCodigoGrupo() + catCentro.getCatCodigoElemento(), catCentro.getCatDescripcionElemento() );
            }

        }

        return cboCentro;
    }

    public void setCboCentro( SelectItem[] cboCentro ) {
        this.cboCentro = cboCentro;
    }

    public void setCboArea( SelectItem[] cboArea ) {
        this.cboArea = cboArea;
    }

    public SelectItem[] getCboHorario() {
        HSPlantillaHorarioCLDAO dao = (HSPlantillaHorarioCLDAO) ServiceFinder.findBean( "SpringHibernateDaoPlantillaHorario" );
        List<ClPlantillaHorario> listaP = dao.listarPlantilla();
        this.cboHorario = new SelectItem[ listaP.size() + 1 ];
        this.cboHorario[0] = new SelectItem( 0, "[Seleccione]" );
        for ( int i = 0; i < (cboHorario.length - 1); i++ ) {
            this.cboHorario[i + 1] = new SelectItem( listaP.get( i ).getPlaId(), listaP.get( i ).getPlaDescripcion() );
        }
        return cboHorario;
    }

    public void setCboHorario( SelectItem[] cboHorario ) {
        this.cboHorario = cboHorario;
    }

    public SelectItem[] getCboArea() {
        SelectItem[] siAreas;
        if ( "1".equals( w_categoria_id ) && !"0".equals( w_centro_id ) ) {
            siAreas = CommonWeb.getCboArbolAreaInf( 0, w_centro_id );
        } else {
            siAreas = new SelectItem[ 1 ];
            siAreas[0] = new SelectItem( "0", "[Seleccione]" );
        }
        return siAreas;
    }

    public SelectItem[] getCboModulo() throws Exception {
        return CommonWeb.getCboArbolModInf( w_are_id );
    }

    public SelectItem[] getCboCurso() {
        return CommonWeb.getCboArbolCursoInf( w_mod_id );
    }

    public List<ClArbolAcademico> agregarHijo( int idPadre, String tipo, List<ClArbolAcademico> listaArbol ) {


        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
        List<ClArbolAcademico> lista = daoArbol.listarArbolPorPadre( idPadre );
        for ( int i = 0; i < lista.size(); i++ ) {
            if ( lista.get( i ).getArbTipo().equals( tipo ) ) {
                listaArbol.add( lista.get( i ) );
            } else {
                agregarHijo( lista.get( i ).getArbId(), tipo, listaArbol );
            }
        }

        return listaArbol;
    }

    public void setCboModulo( SelectItem[] cboModulo ) {
        this.cboModulo = cboModulo;
    }

    public SelectItem[] getCboMedio() {
        ClMedioPublicidad medPub;
        try {
            HSMedioPublicidadCLDAO dao = CommonDAO.getClMedioPublDAO();
            List<ClMedioPublicidad> listaMedio = dao.listarMedioPublicidad();
            cboMedio = new SelectItem[ listaMedio.size() + 1 ];
            cboMedio[0] = new SelectItem( "0", "[Seleccione]" );
            for ( int i = 0; i < (cboMedio.length - 1); i++ ) {
                medPub = listaMedio.get( i );
                cboMedio[i + 1] = new SelectItem( medPub.getIdPublicidad(), medPub.getDescripcion() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboMedio;
    }

    public void setCboMedio( SelectItem[] cboMedio ) {
        this.cboMedio = cboMedio;
    }

    public SelectItem[] getCboMedioDet() {
        try {
            HSMedioPublicidadCLDAO dao = CommonDAO.getClMedioPublDAO();
            List<ClMedioPublicidadDet> listaMedio = dao.listarMediopublicidadDeta_x_med( iMedioId );
            if ( !listaMedio.isEmpty() ) {
                cboMedioDet = new SelectItem[ listaMedio.size() + 1 ];
                cboMedioDet[0] = new SelectItem( "0", "[Seleccione]" );
                for ( int i = 0; i < (cboMedioDet.length - 1); i++ ) {
                    cboMedioDet[i + 1] = new SelectItem( listaMedio.get( i ).getIdPubDet(), listaMedio.get( i ).getPubDetDes() );
                }

            } else {
                cboMedioDet = new SelectItem[ 1 ];
                cboMedioDet[0] = new SelectItem( "0", "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboMedioDet;
    }

    public void setCboMedioDet( SelectItem[] cboMedioDet ) {
        this.cboMedioDet = cboMedioDet;
    }

    public SelectItem[] getCboProcedencia() {
        Metodos metodo = new Metodos();
        this.setCboProcedencia( metodo.listarCatalogoGrupo( "064", "[ TODOS ]" ) );
        return cboProcedencia;
    }

    public void setCboProcedencia( SelectItem[] cboProcedencia ) {
        this.cboProcedencia = cboProcedencia;
    }

    public SelectItem[] getCboUsuario() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession( true );
        usu = (bUsuario) session.getAttribute( "usuario" );

        if ( usu.getRol_id() == 37 ) {
            this.cboUsuario = new SelectItem[ 1 ];
            this.cboUsuario[0] = new SelectItem( usu.getId_usu(), usu.getUsu_usu() );
        } else {
            try {
                HSUsuarioDAO dao = (HSUsuarioDAO) ServiceFinder.findBean( "SpringHibernateDaoUsuario" );
                List<TbUsuario> lista = dao.listarUsuarioPorRoles( 38 );
                this.cboUsuario = new SelectItem[ lista.size() + 1 ];
                this.cboUsuario[0] = new SelectItem( "0", "[Seleccionar usuario]" );
                for ( int i = 0; i < (cboUsuario.length - 1); i++ ) {
                    this.cboUsuario[i + 1] = new SelectItem( lista.get( i ).getId(), lista.get( i ).getUsuUsuario() );
                }
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
        return cboUsuario;
    }

    public SelectItem[] getCboMatricula() {

        this.cbomatricula = new SelectItem[ 3 ];
        this.cbomatricula[0] = new SelectItem( "1", "[Seleccionar]" );
        this.cbomatricula[1] = new SelectItem( "0", "NO" );
        this.cbomatricula[2] = new SelectItem( "2", "SI" );
        return cbomatricula;
    }

    public void setCboUsuario( SelectItem[] cboUsuario ) {
        this.cboUsuario = cboUsuario;
    }

    public List<BeansRptFechaContacto> getListaFechaContacto() {
        return listaFechaContacto;
    }

    public void setListaFechaContacto( List<BeansRptFechaContacto> listaFechaContacto ) {
        this.listaFechaContacto = listaFechaContacto;
    }

    public int getW_esp_id() {
        return w_esp_id;
    }

    public void setW_esp_id( int w_esp_id ) {
        this.w_esp_id = w_esp_id;
    }

    public int getW_especialidad_id() {
        return w_especialidad_id;
    }

    public void setW_especialidad_id( int w_especialidad_id ) {
        this.w_especialidad_id = w_especialidad_id;
    }

    public String getW_categoria_id() {
        return w_categoria_id;
    }

    public void setW_categoria_id( String w_categoria_id ) {
        this.w_categoria_id = w_categoria_id;
    }

    public String getW_centro_id() {
        return w_centro_id;
    }

    public void setW_centro_id( String w_centro_id ) {
        this.w_centro_id = w_centro_id;
    }

    public int getW_are_id() {
        return w_are_id;
    }

    public void setW_are_id( int w_are_id ) {
        this.w_are_id = w_are_id;
    }

    public Date getW_fecha_fin() {
        return w_fecha_fin;
    }

    public void setW_fecha_fin( Date w_fecha_fin ) {
        this.w_fecha_fin = w_fecha_fin;
    }

    public Date getW_fecha_ini() {
        return w_fecha_ini;
    }

    public void setW_fecha_ini( Date w_fecha_ini ) {
        this.w_fecha_ini = w_fecha_ini;
    }

    public int getW_hor_id() {
        return w_hor_id;
    }

    public void setW_hor_id( int w_hor_id ) {
        this.w_hor_id = w_hor_id;
    }

    public int getW_mod_id() {
        return w_mod_id;
    }

    public void setW_mod_id( int w_mod_id ) {
        this.w_mod_id = w_mod_id;
    }

    public String getW_pro_id() {
        return w_pro_id;
    }

    public void setW_pro_id( String w_pro_id ) {
        this.w_pro_id = w_pro_id;
    }

    public String getW_mat_tipo() {
        return w_mat_tipo;
    }

    public void setW_mat_tipo( String w_mat_tipo ) {
        this.w_mat_tipo = w_mat_tipo;
    }

    public int getW_usu_id() {
        return w_usu_id;
    }

    public void setW_usu_id( int w_usu_id ) {
        this.w_usu_id = w_usu_id;
    }

    public void buscarConsultaPublico( ActionEvent event ) {
        String area = "";
        String curso = "";
        AcEspecialidad especialidad = null;
        ClConsultaPublico clConPub;
        this.setListaFechaContacto( new ArrayList<BeansRptFechaContacto>() );
        HSConsultaPublicoDAO dao = CommonDAO.getClConsultaPublicoDAO();
        HSArbolAcademicoClDao daoArb = CommonDAO.getClArbolAcademicoDAO();

        List<ClConsultaPublico> lista = dao.listarPublicoConsulta( Integer.parseInt( w_categoria_id ), w_centro_id, w_especialidad_id, this.w_are_id, this.w_mod_id, this.iCursoId, this.w_hor_id, this.w_usu_id,
                this.w_fecha_ini, this.w_fecha_fin, this.w_pro_id, this.w_mat_tipo, this.sPrioridadId, 1, iMedioId, iMedioDetId );
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        HSPlantillaHorarioCLDAO daoP = (HSPlantillaHorarioCLDAO) ServiceFinder.findBean( "SpringHibernateDaoPlantillaHorario" );
        int iCont = 0;
        for ( int i = 0; i < lista.size(); i++ ) {
            clConPub = lista.get( i );
            if ( "1".equals( w_categoria_id ) && !"0".equals( w_centro_id ) ) {
                try {
                    if ( !(w_centro_id.equals( CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( clConPub.getAreaId() ).getArbInstitucion() )) ) {
                        continue;
                    }
                } catch ( Exception ex ) {
                    ex.printStackTrace();
                }
            }
            try {
                if ( clConPub.getEspId() != null && clConPub.getEspId() > 0 ) {
                    area = "CARRERA PROFESIONAL";
                    especialidad = (AcEspecialidad) CommonDAO.getAcEspecialidadDAO().seleccionarUnaEspecialidad( clConPub.getEspId() ).get( 0 );
                    curso = " - ";
                } else {
                    area = daoArb.buscarArbolPorId( clConPub.getClArbolAcademico().getArbIdPadre() ).getArbDescripcion();
                    if ( clConPub.getCurId() != null ) {
                        curso = daoArb.buscarArbolPorId( clConPub.getCurId() ).getArbDescripcion();
                    } else {
                        curso = "NO DETERMINADO";
                    }
                }

            } catch ( Exception ex ) {
                Logger.getLogger( bFechaContacto.class.getName() ).log( Level.SEVERE, null, ex );
            }

            BeansRptFechaContacto contacto = new BeansRptFechaContacto();
            contacto.setNombreAlumno( clConPub.getClAlumno().getAluAppaterno() + " " + clConPub.getClAlumno().getAluApmaterno() + " " + clConPub.getClAlumno().getAluNombres() );
            contacto.setArea( area );
            contacto.setModulo( clConPub.getEspId() != null && clConPub.getEspId() > 0 && especialidad != null ? especialidad.getEspNombre() : clConPub.getClArbolAcademico().getArbDescripcion() );
            contacto.setCurso( curso );
            contacto.setHorario( (daoP.seleccionarPlantilla( clConPub.getPlaId() )).getPlaDescripcion() );
            contacto.setMatriculado( clConPub.getConEstadoMatricula().equals( "2" ) ? "SI" : "NO" );
            contacto.setConsulta_id( clConPub.getConId() );
            contacto.setFechContac( clConPub.getConFechaContacto() );
            contacto.setProcedencia( daoC.seleccionarDescripcion( clConPub.getConProcedencia() ) );
            contacto.setTlfCelular( clConPub.getClAlumno().getAluCelular() );
            contacto.setTlfFijo( clConPub.getClAlumno().getAluTelefono() );
            contacto.setObservacion( clConPub.getConObservacionRegistro() );
            if(clConPub.getMedId().equals("") || clConPub.getMedId() == null ){
                System.out.println( "ingreso-->" + clConPub.getMedId()); 
                contacto.setMed_medio( "NO DETERMINADO" );
            }else{
                
                String sMedsPub;
                String[] asIdsMedPub = clConPub.getMedId().split( "," );
                sMedsPub = "";
                for ( int j = 0; j < asIdsMedPub.length; j++ ) {
                    sMedsPub += CommonDAO.getClMedioPublDAO().seleccionarMedioPublicidad( CommonWeb.parseObjToInt( asIdsMedPub[j] ) ).getDescripcion();
                    if ( j + 1 < asIdsMedPub.length ) {
                        sMedsPub += " / ";
                    }
                }
                contacto.setMed_medio( sMedsPub );
                System.out.println( "no ingresa -->" + clConPub.getMedId() );
            }
          /*  if ( clConPub.getMedId() != null || !clConPub.getMedId().isEmpty() || clConPub.getMedId()=="" ) {
                String sMedsPub;
                String[] asIdsMedPub = clConPub.getMedId().split( "," );
                sMedsPub = "";
                for ( int j = 0; j < asIdsMedPub.length; j++ ) {
                    sMedsPub += CommonDAO.getClMedioPublDAO().seleccionarMedioPublicidad( CommonWeb.parseObjToInt( asIdsMedPub[j] ) ).getDescripcion();
                    if ( j + 1 < asIdsMedPub.length ) {
                        sMedsPub += " / ";
                    }
                }
                contacto.setMed_medio( sMedsPub );
            } else {
                contacto.setMed_medio( "NO DETERMINADO" );
            }*/
            if ( clConPub.getDetmedId() != null ) {
                contacto.setMed_medio_detalle( CommonDAO.getClMedioPublDAO().seleccionarMedioPublicidadDetalle( clConPub.getDetmedId() ).getPubDetDes() );
            } else {
                contacto.setMed_medio_detalle( "NO DETERMINADO" );
            }
            contacto.setNumOrden( ++iCont );
            this.getListaFechaContacto().add( contacto );

        }
    }

    public void actualizarOrservacion( ActionEvent event ) {
        this.setOncomplete( "" );
        HSConsultaPublicoDAO dao = (HSConsultaPublicoDAO) ServiceFinder.findBean( "SpringHibernateDaoConsultaPublico" );

        ClConsultaPublico consulta = new ClConsultaPublico( this.getW_con_id() );

        if ( this.getDetalleConsulta().getObservacion().trim().length() > 4 ) {
            dao.modificarConsultaPublico( this.getW_con_id(), this.getDetalleConsulta().getObservacion(), this.getDetalleConsulta().getFecha() );
            this.getDetalleConsulta().setClPublicoConsulta( consulta );
            this.guardarPublicoConsultaDetalle( this.getDetalleConsulta() );
            buscarConsultaPublico( event );
            //this.setOncomplete("javascript:alert('Consulta actualizada correctamente');Richfaces.hideModalPanel('mpObservacion')");
        } else {
            this.setOncomplete( "javascript:alert('Ingrese observacion')" );
        }
    }

    public void guardarPublicoConsultaDetalle( ClPublicoConsultaDetalle consultaDetalle ) {
        HSPublicoConsultaDetalleCLDAO dao = (HSPublicoConsultaDetalleCLDAO) ServiceFinder.findBean( "SpringHibernateDaoPublicoConsultaDetalle" );
        dao.guardarConsultaDetalle( consultaDetalle );
        this.setOncomplete( "javascript:Richfaces.hideModalPanel('mpObservacion');alert('Consulta actualizada correctamente')" );
    }

    public void abrirObservacion( ActionEvent event ) {
        this.setOncomplete( "" );
        int p_consulta_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_consulta_id" )).getValue().toString() );

        HSConsultaPublicoDAO daoConPub = (HSConsultaPublicoDAO) ServiceFinder.findBean( "SpringHibernateDaoConsultaPublico" );

        this.setW_con_id( p_consulta_id );
        this.setDetalleConsulta( new ClPublicoConsultaDetalle() );
        this.getDetalleConsulta().setFecha( new Date() );
        this.getDetalleConsulta().setObservacion( daoConPub.seleccionarConsultaPublico( p_consulta_id ).getConObservacionRegistro() );
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpObservacion');" );
    }

    public void seleccionarDetalle( ActionEvent event ) {
        this.setOncomplete( "" );
        HSPublicoConsultaDetalleCLDAO dao = (HSPublicoConsultaDetalleCLDAO) ServiceFinder.findBean( "SpringHibernateDaoPublicoConsultaDetalle" );
        int con_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_consulta_id" )).getValue().toString() );
        this.setListarDetalle( dao.listarPublicoConsultaDetalle( con_id ) );
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpConsultaDetalle');" );

    }

    public String irFechaContacto() {

        return "fechaContacto";
    }

    public void ImprimirFicha( ActionEvent event ) throws Exception {
        this.setTituloReporte( "Fecha de Contacto" );
        this.setValorRep( "ficha" );
        //this.setB_sec_id( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
    }

    public void cargarReporte( OutputStream out, Object data ) throws IOException, Exception, EOFException {
        Reporte reporte = new Reporte();
        FacesContext context = FacesContext.getCurrentInstance();
        HashMap parametros = new HashMap();

        //parametros.put( "logo", context.getExternalContext().getResource( "/Imagenes/actions/logo_p.jpg" ) );

        if ( "1".equals( this.w_categoria_id ) ) { //CURSO LIBRE

            parametros.put( "centro_id", "" + this.w_centro_id );
            parametros.put( "are_id", "" + this.w_are_id );
            parametros.put( "mod_id", "" + this.w_mod_id );
            parametros.put( "cur_id", "" + this.iCursoId );
            parametros.put( "hor_id", "" + this.w_hor_id );
            parametros.put( "fec_ini", this.w_fecha_ini );
            parametros.put( "fec_fin", this.w_fecha_fin );
            parametros.put( "usu_id", "" + this.w_usu_id );
            parametros.put( "mat_id", this.w_mat_tipo );
            parametros.put( "pro_id", this.w_pro_id );
            parametros.put( "pri_id", this.sPrioridadId );
            parametros.put( "med_id", "" + this.iMedioId );
            parametros.put( "meddet_id", "" + this.iMedioDetId );
            parametros.put( "total", "" + this.listaFechaContacto.size() );

            reporte.cargarReporte( out, data, parametros, "rep_listado_fecha_contacto_cl.jasper" );
        }

        if ( "2".equals( this.w_categoria_id ) ) { // CARRERA PROFESIONAL

            parametros.put( "esp_id", "" + this.w_especialidad_id );
            parametros.put( "hor_id", "" + this.w_hor_id );
            parametros.put( "fec_ini", this.w_fecha_ini );
            parametros.put( "fec_fin", this.w_fecha_fin );
            parametros.put( "usu_id", "" + this.w_usu_id );
            parametros.put( "pro_id", this.w_pro_id );
            parametros.put( "pri_id", this.sPrioridadId );
            parametros.put( "med_id", "" + this.iMedioId );
            parametros.put( "meddet_id", "" + this.iMedioDetId );
            parametros.put( "total", "" + this.listaFechaContacto.size() );

            reporte.cargarReporte( out, data, parametros, "rep_listado_fecha_contacto_cp.jasper" );
        }

    }

    public void exportToExcel() throws Exception {
        JasperPrint jasperPrint;
        JRXlsExporter exporter;
        String sFileName;
        String sFilePath;
        File outputFile;
        FileInputStream archivo;
        FileOutputStream fos;
        HttpServletResponse response;
        ServletOutputStream ouputStream;
        byte[] datos;
        String[] sheetNames = { "Hoja 1" };
        sFileName = "Reporte.xls";
        sFilePath = System.getProperty( "user.dir" ) + File.separator + sFileName;
        outputFile = new File( sFilePath );
        fos = new FileOutputStream( outputFile );
        jasperPrint = ReporteFechaContacto.generarReporteFechaContacto( Integer.parseInt( w_categoria_id ), w_centro_id, w_especialidad_id, w_are_id,
                w_mod_id, iCursoId, w_hor_id, w_usu_id, w_fecha_ini, w_fecha_fin, w_pro_id, w_mat_tipo, sPrioridadId,
                1, iMedioId, iMedioDetId );

        exporter = new JRXlsExporter();

        //Indicamos el objeto JasperPrint que deseamos exportar
        exporter.setParameter( JRExporterParameter.JASPER_PRINT, jasperPrint );
        //Indicamos el fichero donde vamos a exportar el informe
        exporter.setParameter( JRExporterParameter.OUTPUT_STREAM, fos ); //and output stream
        //Indicamos si queremos una página por Sheet
        exporter.setParameter( JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE );
        //Indicamos si deseamos eliminar los espacios vacíos entre filas
        exporter.setParameter( JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE );
        //Indicamos si quremos mostrar una página en blanco como fondo
        exporter.setParameter( JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE );
        exporter.setParameter( JRXlsExporterParameter.SHEET_NAMES, sheetNames );
        exporter.exportReport();

        response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType( "application/vnd.ms-excel" );
        response.setHeader( "Content-Disposition", "attachment;filename=" + sFileName );
        response.addHeader( "Content-Type", "application/force-download" );

        archivo = new FileInputStream( sFilePath );
        datos = new byte[ archivo.available() ];
        archivo.read( datos );
        archivo.close();
        ouputStream = response.getOutputStream();
        ouputStream.write( datos );
        ouputStream.flush();
        ouputStream.close();
        FacesContext.getCurrentInstance().responseComplete();

        if ( new File( sFilePath ).delete() ) {
            System.out.println( "se borro el archivo" );
        }

    }
}
