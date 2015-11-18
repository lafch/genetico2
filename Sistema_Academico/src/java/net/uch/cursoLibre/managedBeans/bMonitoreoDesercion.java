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
public class bMonitoreoDesercion {

    private int m_iAreaId;
    private int m_iModuloId;
    private int m_iCursoId;
    private String m_sInstId;
    private String m_sOncomplete;
    private String m_sObservacion;
    private String m_sNomApeFiltro;
    private String m_sNumTlfFiltro;
    private String m_sNumActivo;
    private Date m_fechaContacto;
    private BeanClAlumnoDesertor m_beanClAlumDeser;
    private ClObservacionDesercion m_obsDesBean;
    private SelectItem[] m_cboAreas;
    private SelectItem[] m_cboInstituciones;
    private SelectItem[] m_cboModulos;
    private SelectItem[] m_cboCurso;

    
    private SelectItem[] m_chksMotivosDesercion;
    private List<BeanReporte> m_lstDesertores;
    private List<BeanReporte> m_lstCursosLlevados;
    private List<String> selectedItems = new ArrayList<String>();
    private List<String> selectedItems2 = new ArrayList<String>();

    /**
     * Creates a new instance of bMonitoreoDesercion
     */
    public bMonitoreoDesercion() {
        m_sObservacion = "";
        m_beanClAlumDeser = new BeanClAlumnoDesertor();
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

    public String getObservacion() {
        return m_sObservacion;
    }

    public void setObservacion( String sObservacion ) {
        this.m_sObservacion = sObservacion;
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

    public String getNumActivo() {
        return m_sNumActivo;
    }

    public void setNumActivo( String sNumActivo ) {
        this.m_sNumActivo = sNumActivo;
    }
    
    public List<String> getSelectedItems() { 
        return selectedItems; 
    }
    
    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems; 
    }
    
    public List<String> getSelectedItems2() { 
        return selectedItems2; 
    }
    
    public void setSelectedItems2(List<String> selectedItems2) {
        this.selectedItems2 = selectedItems2; 
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
    public int getCursoId() {
        return m_iCursoId;
    }

    public void setCursoId( int iCursoId ) {
        this.m_iCursoId = iCursoId;
    }

    public String getInstId() {
        return m_sInstId;
    }

    public void setInstId( String sInstId ) {
        this.m_sInstId = sInstId;
    }

    public String getOncomplete() {
        return m_sOncomplete;
    }

    public void setOncomplete( String m_sOncomplete ) {
        this.m_sOncomplete = m_sOncomplete;
    }

    public Date getFechaContacto() {
        if( m_fechaContacto == null ){
            m_fechaContacto = new Date();
        }
        return m_fechaContacto;
    }

    public void setFechaContacto( Date fechaContacto ) {
        this.m_fechaContacto = fechaContacto;
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
    
    public SelectItem[] getcboCurso() {
        int iSizeCur;
        ClArbolAcademico arbCur;
        List<ClArbolAcademico> lstCursos;
        m_cboCurso=new SelectItem[1];
        if(m_iModuloId!=0){
            lstCursos=CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( m_iModuloId );
            if(lstCursos!=null && !lstCursos.isEmpty()){
                m_cboCurso = new SelectItem[ lstCursos.size() + 1 ];
                iSizeCur=lstCursos.size();
                for ( int i = 0; i < iSizeCur; i++ ) {
                   arbCur=lstCursos.get( i );
                   m_cboCurso[i + 1]=new SelectItem(arbCur.getArbId(), arbCur.getArbDescripcion());
                    
                }
            } 
        }
        m_cboCurso[0]=new SelectItem("0","[Selecciona]");
        return m_cboCurso;
    }

    public void setcboCurso( SelectItem[] m_cboCurso ) {
        this.m_cboCurso = m_cboCurso;
    }

    public List<BeanReporte> getLstDesertores() {

        try {
            if ( m_iModuloId != 0 && m_iCursoId!=0) {
                
                m_lstDesertores = CommonDAO.getClAlumnoDesertorDAO().listarDesertores(m_sNomApeFiltro, m_sNumTlfFiltro, m_iModuloId,m_iCursoId, selectedItems,selectedItems2);
            } else {
                m_lstDesertores = new ArrayList<BeanReporte>();
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
            m_lstDesertores = new ArrayList<BeanReporte>();
        }
        return m_lstDesertores;
    }

    public List<BeanReporte> getLstCursosLlevados() {
        return m_lstCursosLlevados;
    }

    public void setLstCursosLlevados( List<BeanReporte> lstCursosLlevados ) {
        this.m_lstCursosLlevados = lstCursosLlevados;
    }

    public void setLstDesertores( List<BeanReporte> lstDesertores ) {
        this.m_lstDesertores = lstDesertores;
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

    public void listarCursosLlevados( ActionEvent event ){
        int iAluId;
        
        
        iAluId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "param_alu_id2") );
        m_lstCursosLlevados = CommonDAO.getClAlumnoDAO().listarCursosLlevados( iAluId );
        m_sOncomplete = "Richfaces.showModalPanel('mpLstCursos');";
    }
}
