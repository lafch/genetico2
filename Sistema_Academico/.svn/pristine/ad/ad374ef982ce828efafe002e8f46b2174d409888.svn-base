/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumnoDesertor;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.*;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;

/**
 *
 * @author USUARIO
 */
public class bMonitoreoDesercionFechaContacto {

    private int m_iAreaId;
    private int m_iModuloId;
    private String m_sInstId;
    private String m_sNomApeFiltro;
    private String m_sNumTlfFiltro;
    private String m_sOncomplete;
    private String m_sObservacion;
    private Date m_fechaInicio;
    private Date m_fechaFin;
    private Date m_fechaContacto;
    private BeanClAlumnoDesertor m_beanClAlumDeser;
    private ClObservacionDesercion m_obsDesBean;
    private SelectItem[] m_cboAreas;
    private SelectItem[] m_cboInstituciones;
    private SelectItem[] m_cboModulos;
    private SelectItem[] m_chksMotivosDesercion;
    private List<BeanReporte> m_lstDesertores;
    private List<BeanReporte> m_lstCursosLlevados;

    /**
     * Creates a new instance of bMonitoreoDesercionFechaContacto
     */
    public bMonitoreoDesercionFechaContacto() {
    }

    public SelectItem[] getChksMotivosDesercion() {
        int iSizeCat;
        TbCatalogo catMotDes;
        List<TbCatalogo> lstCatMotivDeserc;
        if ( m_chksMotivosDesercion == null ) {
            lstCatMotivDeserc = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "084" );
            iSizeCat = lstCatMotivDeserc.size();
            m_chksMotivosDesercion = new SelectItem[ iSizeCat ];
            for ( int i = 0; i < iSizeCat; i++ ) {
                catMotDes = lstCatMotivDeserc.get( i );
                m_chksMotivosDesercion[i] = new SelectItem( catMotDes.getCatCodigoGrupo() + catMotDes.getCatCodigoElemento(), catMotDes.getCatDescripcionElemento() );
            }
        }
        return m_chksMotivosDesercion;
    }

    public void setChksMotivosDesercion( SelectItem[] chksMotivosDesercion ) {
        this.m_chksMotivosDesercion = chksMotivosDesercion;
    }

    public int getAreaId() {
        return m_iAreaId;
    }

    public void setAreaId( int iAreaId ) {
        this.m_iAreaId = iAreaId;
    }

    public int getModuloId() {
        return m_iModuloId;
    }

    public void setModuloId( int iModuloId ) {
        this.m_iModuloId = iModuloId;
    }

    public String getInstId() {
        return m_sInstId;
    }

    public void setInstId( String iInstId ) {
        this.m_sInstId = iInstId;
    }

    public String getNomApeFiltro() {
        return m_sNomApeFiltro;
    }

    public void setNomApeFiltro( String sNomApeFiltro ) {
        this.m_sNomApeFiltro = sNomApeFiltro;
    }

    public String getNumTlfFiltro() {
        return m_sNumTlfFiltro;
    }

    public void setNumTlfFiltro( String sNumTlfFiltro ) {
        this.m_sNumTlfFiltro = sNumTlfFiltro;
    }

    public String getOncomplete() {
        return m_sOncomplete;
    }

    public void setOncomplete( String m_sOncomplete ) {
        this.m_sOncomplete = m_sOncomplete;
    }

    public String getObservacion() {
        return m_sObservacion;
    }

    public void setObservacion( String sObservacion ) {
        this.m_sObservacion = sObservacion;
    }

    public Date getFechaContacto() {
        return m_fechaContacto;
    }

    public void setFechaContacto( Date fechaContacto ) {
        this.m_fechaContacto = fechaContacto;
    }

    public Date getFechaFin() {
        return m_fechaFin == null ? new Date() : m_fechaFin;
    }

    public void setFechaFin( Date fechaFin ) {
        this.m_fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return m_fechaInicio == null ? new Date() : m_fechaInicio;
    }

    public void setFechaInicio( Date fechaInicio ) {
        this.m_fechaInicio = fechaInicio;
    }

    public BeanClAlumnoDesertor getBeanClAlumDeser() {
        return m_beanClAlumDeser;
    }

    public void setBeanClAlumDeser( BeanClAlumnoDesertor beanClAlumDeser ) {
        this.m_beanClAlumDeser = beanClAlumDeser;
    }

    public ClObservacionDesercion getObsDesBean() {
        return m_obsDesBean;
    }

    public void setObsDesBean( ClObservacionDesercion obsDesBean ) {
        this.m_obsDesBean = obsDesBean;
    }

    public SelectItem[] getCboModulos() {
        int iSizeMod;
        ClArbolAcademico arbMod;
        List<ClArbolAcademico> lstModulos;
        m_cboModulos = new SelectItem[ 1 ];
        if ( m_iAreaId != 0 ) {
            lstModulos = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( m_iAreaId );
            if ( lstModulos != null && !lstModulos.isEmpty() ) {
                m_cboModulos = new SelectItem[ lstModulos.size() + 1 ];
                iSizeMod = lstModulos.size();
                for ( int i = 0; i < iSizeMod; i++ ) {
                    arbMod = lstModulos.get( i );
                    m_cboModulos[ i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
                }
            }
        }
        m_cboModulos[0] = new SelectItem( "0", "[Seleccione]" );
        return m_cboModulos;
    }

    public void setCboModulos( SelectItem[] m_cboModulos ) {
        this.m_cboModulos = m_cboModulos;
    }

    public SelectItem[] getCboAreas() {
        int iSizeAreas;
        ClArbolAcademico arbArea;
        List<ClArbolAcademico> lstAreas;
        m_cboAreas = new SelectItem[ 1 ];
        if ( m_sInstId != null ) {
            lstAreas = CommonDAO.getClArbolAcademicoDAO().AreasXInstitucion( m_sInstId );
            if ( lstAreas != null && !lstAreas.isEmpty() ) {
                m_cboAreas = new SelectItem[ lstAreas.size() + 1 ];
                iSizeAreas = lstAreas.size();
                for ( int i = 0; i < iSizeAreas; i++ ) {
                    arbArea = lstAreas.get( i );
                    m_cboAreas[ i + 1] = new SelectItem( arbArea.getArbId(), arbArea.getArbDescripcion() );
                }
            }
        }
        m_cboAreas[0] = new SelectItem( "0", "[Seleccione]" );
        return m_cboAreas;
    }

    public void setCboAreas( SelectItem[] cboAreas ) {
        this.m_cboAreas = cboAreas;
    }

    public SelectItem[] getCboInstituciones() {
        int iSizeInst;
        String sCodCat;
        TbCatalogo tbCatInst;
        List<TbCatalogo> lstCat;

        m_cboInstituciones = new SelectItem[ 1 ];
        if ( m_cboInstituciones == null || m_cboInstituciones.length == 1 ) {
            lstCat = CommonDAO.getTbCatalogoDAO().seleccionarGrupo( "078" );
            if ( lstCat != null && !lstCat.isEmpty() ) {
                iSizeInst = lstCat.size();
                m_cboInstituciones = new SelectItem[ iSizeInst ];
                for ( int i = 0; i < iSizeInst; i++ ) {
                    tbCatInst = lstCat.get( i );
                    sCodCat = tbCatInst.getCatCodigoGrupo() + tbCatInst.getCatCodigoElemento();
                    if ( !"078004".equals( sCodCat ) ) {
                        m_cboInstituciones[i + 1] = new SelectItem( sCodCat, tbCatInst.getCatDescripcionElemento() );
                    }
                }
            }
        }
        m_cboInstituciones[0] = new SelectItem( "000000", "[Seleccione]" );
        return m_cboInstituciones;
    }

    public void setCboInstituciones( SelectItem[] cboInstituciones ) {
        this.m_cboInstituciones = cboInstituciones;
    }

    public List<BeanReporte> getLstDesertores() {
        try {
            if ( m_iModuloId != 0 ) {
                m_lstDesertores = CommonDAO.getClAlumnoDesertorDAO().listarDesertoresPorFechaContacto( m_iModuloId, m_fechaInicio, m_fechaFin, m_sNomApeFiltro, m_sNumTlfFiltro );
            } else {
                m_lstDesertores = new ArrayList<BeanReporte>();
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            m_lstDesertores = new ArrayList<BeanReporte>();
        }
        return m_lstDesertores;
    }

    public void setLstDesertores( List<BeanReporte> lstDesertores ) {
        this.m_lstDesertores = lstDesertores;
    }

    public List<BeanReporte> getLstCursosLlevados() {
        return m_lstCursosLlevados;
    }

    public void setLstCursosLlevados( List<BeanReporte> m_lstCursosLlevados ) {
        this.m_lstCursosLlevados = m_lstCursosLlevados;
    }

    //Acciones
    public void modificarObservacion( ActionEvent event ) {
        int iIdSecUlt;
        int iIdAlu;
        bUsuario usuLogeado;
        ClAlumnoDesertor clAluDeser;
        m_sOncomplete = "alert('Error al seleccionar Observacion');";
        try {
            usuLogeado = CommonWeb.traerUsuarioLogeado();
            if ( m_iModuloId != 0 && usuLogeado != null ) {
                iIdSecUlt = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "param_sec_id" ) );
                iIdAlu = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "param_alu_id" ) );
                if ( iIdSecUlt > 0 && iIdAlu > 0 ) {
                    clAluDeser = CommonDAO.getClAlumnoDesertorDAO().buscarAlumnoDesertor( iIdAlu, m_iModuloId );
                    m_beanClAlumDeser = new BeanClAlumnoDesertor( false, false,false, null, 1, "", CommonDAO.getClAlumnoDAO().buscarAlumnoPorAluId( iIdAlu ),
                            CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( m_iModuloId ), CommonDAO.getClSeccionDAO().seleccionarSeccion( iIdSecUlt ),
                            new ArrayList<ClObservacionDesercion>(), new ArrayList<String>() );
                    if ( clAluDeser != null ) {
                        m_beanClAlumDeser.setValues( clAluDeser );
                    }
                }

                m_sOncomplete = "Richfaces.showModalPanel('mpModifObs');";
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public void guardarObservacion( ActionEvent event ) {
        int iRet;
        TbUsuario tbUsuario;
        ClAlumnoDesertor alumDeser;
        m_sOncomplete = "alert('Error al guardar');";
        try {
            if ( m_beanClAlumDeser != null ) {

                alumDeser = new ClAlumnoDesertor();
                alumDeser.setValues( m_beanClAlumDeser );
                tbUsuario = CommonDAO.getTbUsuarioDAO().traerUsuarioPorId( CommonWeb.traerUsuarioLogeado().getId_usu() );
                CommonDAO.getClAlumnoDesertorDAO().guardarAlumnoDesertor( alumDeser );
                if ( m_sObservacion != null && !m_sObservacion.trim().isEmpty() ) {
                    m_fechaContacto = m_fechaContacto == null ? new Date() : m_fechaContacto;
                    m_obsDesBean = new ClObservacionDesercion( null, alumDeser, new Date(), m_fechaContacto, m_sObservacion, tbUsuario, 1 );
                    iRet = CommonDAO.getClAlumnoDesertorDAO().guardarObservacionDesercion( m_obsDesBean );
                    if ( iRet == 1 ) {
                        alumDeser = CommonDAO.getClAlumnoDesertorDAO().buscarAlumnoDesertorPorId( alumDeser.getId() );
                        m_beanClAlumDeser = new BeanClAlumnoDesertor();
                        m_beanClAlumDeser.setValues( alumDeser );
                        m_sOncomplete = "alert('Se guardó correctamente');";
                        m_sObservacion = "";
                    }
                } else {
                    m_sOncomplete = "alert('Se guardó correctamente');";
                }
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public void listarCursosLlevados( ActionEvent event ) {
        int iAluId;


        iAluId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "param_alu_id2" ) );
        m_lstCursosLlevados = CommonDAO.getClAlumnoDAO().listarCursosLlevados( iAluId );
        m_sOncomplete = "Richfaces.showModalPanel('mpLstCursos');";
    }
}
