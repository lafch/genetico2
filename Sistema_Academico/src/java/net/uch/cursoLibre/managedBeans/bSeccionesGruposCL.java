/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccion;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccionGrupo;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSeccionGrupo;
import net.uch.util.CommonDAO;
import net.uch.util.ConstantesWeb;

/**
 *
 * @author USUARIO
 */
public class bSeccionesGruposCL {

    private boolean m_bEsNuevo;
    private int m_iAreaId;
    private int m_iModId;
    private int m_iCurId;
    private int m_iTallId;
    private String m_sDescripcion;
    private String m_sOncomplete;
    private String m_sTitModalCrearModifSecGrup;
    private BeanClSeccionGrupo m_clSecGrupo;
    private SelectItem[] m_seAreas;
    private SelectItem[] m_seModulos;
    private SelectItem[] m_seCursos;
    private SelectItem[] m_seTalleres;
    private Map<Integer, BeanClSeccion> m_hmSeccXGrupo;
    private Map<Integer, BeanClSeccion> m_hmSeccXAdd;
    private Map<Integer, BeanClSeccionGrupo> m_hmSeccionesGrupos;

    public boolean isEsNuevo() {
        return m_bEsNuevo;
    }

    public void setEsNuevo( boolean bEsNuevo ) {
        m_bEsNuevo = bEsNuevo;
    }

    public int getAreaId() {
        return m_iAreaId;
    }

    public void setAreaId( int areaId ) {
        m_iAreaId = areaId;
    }

    public int getModId() {
        return m_iModId;
    }

    public void setModId( int modId ) {
        m_iModId = modId;
    }

    public int getCurId() {
        return m_iCurId;
    }

    public void setCurId( int iCurId ) {
        m_iCurId = iCurId;
    }

    public int getTallId() {
        return m_iTallId;
    }

    public void setTallId( int iTallId ) {
        m_iTallId = iTallId;
    }

    public String getDescripcion() {
        return m_sDescripcion;
    }

    public void setDescripcion( String sDescripcion ) {
        m_sDescripcion = sDescripcion;
    }

    public String getOncomplete() {
        return m_sOncomplete;
    }

    public void setOncomplete( String sOncomplete ) {
        this.m_sOncomplete = sOncomplete;
    }

    public String getTitModalCrearModifSecGrup() {
        return m_sTitModalCrearModifSecGrup;
    }

    public void setTitModalCrearModifSecGrup( String sTitModalCrearModifSecGrup ) {
        m_sTitModalCrearModifSecGrup = sTitModalCrearModifSecGrup;
    }

    public SelectItem[] getLstAreas() {
        int iIndex;
        int iSizeAreas;
        ClArbolAcademico arbArea;
        List<ClArbolAcademico> lstAreas;
        try {
            lstAreas = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( 0 );
            iSizeAreas = lstAreas.size() > 0 ? lstAreas.size() : 1;
            m_seAreas = new SelectItem[ iSizeAreas ];
            iIndex = 0;
            for ( int i = 0; i < iSizeAreas; i++ ) {
                arbArea = lstAreas.get( i );
                if ( ConstantesWeb.ID_AREA_PROG_AUX != arbArea.getArbId().intValue() ) {
                    m_seAreas[++iIndex] = new SelectItem( arbArea.getArbId(), arbArea.getArbDescripcion() );
                }
            }
        } catch ( Exception ex ) {
            m_seAreas = new SelectItem[ 1 ];
            ex.printStackTrace();
        }
        m_seAreas[0] = new SelectItem( 0, "Seleccione Área" );
        return m_seAreas;
    }

    public void setLstAreas( SelectItem[] m_seAreas ) {
        this.m_seAreas = m_seAreas;
    }

    public SelectItem[] getLstCursos() {
        int iSizeCursos;
        ClArbolAcademico arbCur;
        List<ClArbolAcademico> lstCur;
        try {
            if ( m_iModId > 0 ) {
                lstCur = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( m_iModId );
                iSizeCursos = lstCur.size() > 0 ? lstCur.size() : 1;
                m_seCursos = new SelectItem[ iSizeCursos + 1 ];
                for ( int i = 0; i < iSizeCursos; i++ ) {
                    arbCur = lstCur.get( i );
                    m_seCursos[i + 1] = new SelectItem( arbCur.getArbId(), arbCur.getArbDescripcion() );
                }
            } else {
                m_seCursos = new SelectItem[ 1 ];
            }
        } catch ( Exception ex ) {
            m_seCursos = new SelectItem[ 1 ];
            ex.printStackTrace();
        }
        m_seCursos[0] = new SelectItem( 0, "Seleccione Curso" );
        return m_seCursos;
    }

    public void setLstCursos( SelectItem[] m_seCursos ) {
        this.m_seCursos = m_seCursos;
    }

    public SelectItem[] getLstModulos() {
        int iSizeModulos;
        ClArbolAcademico arbMod;
        List<ClArbolAcademico> lstMod;
        try {
            if ( m_iAreaId > 0 ) {
                lstMod = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( m_iAreaId );
                iSizeModulos = lstMod.size() > 0 ? lstMod.size() : 1;
                m_seModulos = new SelectItem[ iSizeModulos + 1 ];
                for ( int i = 0; i < iSizeModulos; i++ ) {
                    arbMod = lstMod.get( i );
                    m_seModulos[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
                }
            } else {
                m_seModulos = new SelectItem[ 1 ];
            }
        } catch ( Exception ex ) {
            m_seModulos = new SelectItem[ 1 ];
            ex.printStackTrace();
        }
        m_seModulos[0] = new SelectItem( 0, "Seleccione Módulo" );
        return m_seModulos;
    }

    public void setLstModulos( SelectItem[] m_seModulos ) {
        this.m_seModulos = m_seModulos;
    }

    public SelectItem[] getLstTalleres() {
        int iSizeTall;
        ClArbolAcademico arbTall;
        List<ClArbolAcademico> lstTall;
        try {
            if ( m_iCurId > 0 ) {
                lstTall = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( m_iCurId );
                iSizeTall = lstTall.size() > 0 ? lstTall.size() : 1;
                m_seTalleres = new SelectItem[ iSizeTall + 1 ];
                for ( int i = 0; i < iSizeTall; i++ ) {
                    arbTall = lstTall.get( i );
                    m_seTalleres[i + 1] = new SelectItem( arbTall.getArbId(), arbTall.getArbDescripcion() );
                }
            } else {
                m_seTalleres = new SelectItem[ 1 ];
            }
        } catch ( Exception ex ) {
            m_seTalleres = new SelectItem[ 1 ];
            ex.printStackTrace();
        }
        m_seTalleres[0] = new SelectItem( 0, "Seleccione Taller" );
        return m_seTalleres;
    }

    public void setLstTalleres( SelectItem[] m_seTalleres ) {
        this.m_seTalleres = m_seTalleres;
    }

    public Map<Integer, BeanClSeccion> getHmSeccXAdd() {
        return m_hmSeccXAdd;
    }

    public void setHmSeccXAdd( Map<Integer, BeanClSeccion> hmSeccXAdd ) {
        m_hmSeccXAdd = hmSeccXAdd;
    }

    public List<BeanClSeccion> getLstSeccXAdd() {
        List<BeanClSeccion> lstBClSec;

        if ( m_hmSeccXAdd != null ) {
            lstBClSec = new ArrayList<BeanClSeccion>( m_hmSeccXAdd.values() );
        } else {
            lstBClSec = new ArrayList<BeanClSeccion>();
        }
        return lstBClSec;
    }

    public void setLstSeccXAdd( List<BeanClSeccion> lstSeccXAdd ) {
    }

    public Map<Integer, BeanClSeccion> getHmSeccXGrupo() {
        return m_hmSeccXGrupo;
    }

    public void setHmSeccXGrupo( Map<Integer, BeanClSeccion> lstSeccXGrupo ) {
        m_hmSeccXGrupo = lstSeccXGrupo;
    }

    public List<BeanClSeccion> getLstSeccXGrupo() {
        List<BeanClSeccion> lstSeccXGrup;
        if ( m_hmSeccXGrupo != null ) {
            lstSeccXGrup = new ArrayList<BeanClSeccion>( m_hmSeccXGrupo.values() );
        } else {
            lstSeccXGrup = new ArrayList<BeanClSeccion>();
        }
        return lstSeccXGrup;
    }

    public void setLstSeccXGrupo( List<BeanClSeccion> lstSeccXGrupo ) {
        //Metodo necesario pq en interfaz se requiere el método setLstSeccXGrupo
        //No eliminar.
    }

    public List<BeanClSeccionGrupo> getLstSeccionesGrupos() {
        List<BeanClSeccionGrupo> lstSecGrup;
        if ( m_hmSeccionesGrupos == null ) {
            m_hmSeccionesGrupos = new HashMap<Integer, BeanClSeccionGrupo>();
        }
        lstSecGrup = new ArrayList<BeanClSeccionGrupo>( m_hmSeccionesGrupos.values() );
        return lstSecGrup;
    }

    public Map<Integer, BeanClSeccionGrupo> getHmSeccionesGrupos() {
        return m_hmSeccionesGrupos;
    }

    public void setHmSeccionesGrupos( Map<Integer, BeanClSeccionGrupo> hmSeccionesGrupos ) {
        m_hmSeccionesGrupos = hmSeccionesGrupos;
    }

    public void setLstSeccionesGrupos( List<BeanClSeccionGrupo> lstSeccionesGrupos ) {
    }

    public BeanClSeccionGrupo getClSecGrupo() {
        return m_clSecGrupo;
    }

    public void setClSecGrupo( BeanClSeccionGrupo clSecGrupo ) {
        this.m_clSecGrupo = clSecGrupo;
    }

//--- FIN SETTER Y GETTER
    public List<BeanClSeccion> listarSeccXTall() {
        int iSizeSec;
        ClSeccion clSec;
        BeanClSeccion bClSec;
        List<ClSeccion> lstClSec;
        List<BeanClSeccion> lstBClSec;
        m_hmSeccXAdd = new HashMap<Integer, BeanClSeccion>();
        if ( m_iTallId > 0 ) {
            lstClSec = CommonDAO.getClSeccionDAO().seleccionarSeccionesxTaller( m_iTallId );
            iSizeSec = lstClSec.size();
            for ( int i = 0; i < iSizeSec; i++ ) {
                clSec = lstClSec.get( i );
                if ( clSec.getClSecGrup() == null && !m_hmSeccXGrupo.containsKey( clSec.getSecId() ) ) {
                    bClSec = new BeanClSeccion( clSec );
                    m_hmSeccXAdd.put( bClSec.getSecId(), bClSec );
                }
            }
        }
//        listarSeccXGrupo();
        lstBClSec = new ArrayList<BeanClSeccion>( m_hmSeccXAdd.values() );
        return lstBClSec;
    }

    public void listarSeccXGrupo() {
        int iSizeSecs;
        BeanClSeccion bClSec;
        List<BeanClSeccion> lstBClSecTmp;

        m_hmSeccXGrupo = new HashMap<Integer, BeanClSeccion>();
        lstBClSecTmp = m_clSecGrupo.getLstSecciones();
        iSizeSecs = lstBClSecTmp.size();
        for ( int i = 0; i < iSizeSecs; i++ ) {
            bClSec = lstBClSecTmp.get( i );
            m_hmSeccXGrupo.put( bClSec.getSecId(), bClSec.getCopia() );
        }
    }

    public void nuevoGrupo( ActionEvent event ) {
        m_bEsNuevo = true;
        m_clSecGrupo = new BeanClSeccionGrupo();
        m_hmSeccXGrupo = new HashMap<Integer, BeanClSeccion>();
        m_hmSeccXAdd = new HashMap<Integer, BeanClSeccion>();
        m_iAreaId = 0;
        m_iModId = 0;
        m_iCurId = 0;
        m_iTallId = 0;
        m_sTitModalCrearModifSecGrup = "Crear Grupo";
        m_sOncomplete = "Richfaces.showModalPanel('mpMantSecGrupo')";
    }

    public void actualizarGrupo( ActionEvent event ) {
        int iIdSecGrup;
        ClArbolAcademico arbTal, arbCur, arbMod, arbArea;
        ClSeccion clSec;
        BeanClSeccion bClSec;
        ClSeccionGrupo secGrup;
        Iterator<ClSeccion> it;

        m_bEsNuevo = false;

        iIdSecGrup = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "idSecGrup" )).getValue().toString() );
        secGrup = CommonDAO.getClSeccionDAO().traerClSeccionGrupoXId( iIdSecGrup );
        if ( secGrup != null ) {
            m_clSecGrupo = new BeanClSeccionGrupo();
            m_clSecGrupo.setNomSecGrupo( secGrup.getNomSecGrupo() );
            m_clSecGrupo.setSecGrupId( secGrup.getSecGrupoId() );
            m_clSecGrupo.setFechaInicio( secGrup.getFechaInicio() );
            m_clSecGrupo.setFechaFin( secGrup.getFechaFin() );
            m_clSecGrupo.setLstSecciones( new ArrayList<BeanClSeccion>() );
            it = secGrup.getClSecciones().iterator();
            while ( it.hasNext() ) {
                clSec = it.next();
                bClSec = new BeanClSeccion( clSec );
                arbTal = bClSec.getClArbolAcademico();
                arbCur = arbTal.getArbAcadPadre();
                arbMod = arbCur.getArbAcadPadre();
                arbArea = arbMod.getArbAcadPadre();
                bClSec.setNomTaller( arbTal.getArbDescripcion() );
                bClSec.setNomCurso( arbCur.getArbDescripcion() );
                bClSec.setNomModulo( arbMod.getArbDescripcion() );
                bClSec.setNomArea( arbArea.getArbDescripcion() );
                m_clSecGrupo.getLstSecciones().add( bClSec );
            }
            m_iAreaId = m_iModId = m_iCurId = m_iTallId = 0;
            listarSeccXTall();
            listarSeccXGrupo();
            m_sTitModalCrearModifSecGrup = "Modificar Grupo";
            m_sOncomplete = "Richfaces.showModalPanel('mpMantSecGrupo')";
        }
    }

    public void agregarSeccAGrupo( ActionEvent event ) {//iIdSecAGrup
        int iIdSecAGrup;
        Integer iKey = null;
        try {
            iIdSecAGrup = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "idSecAGrup" )).getValue().toString() );
            for ( BeanClSeccion bClSec : m_hmSeccXAdd.values() ) {
                if ( bClSec.getSecId().intValue() == iIdSecAGrup && !m_hmSeccXGrupo.containsKey( bClSec.getSecId() ) ) {
                    iKey = bClSec.getSecId();
                    m_hmSeccXGrupo.put( iKey, bClSec.getCopia() );
                    break;
                }
            }
            if ( iKey != null ) {
                m_hmSeccXAdd.remove( iKey );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public void listar() {
        int iSizeSecGrup;
        ClArbolAcademico arbTmp;
        BeanClSeccion bClSeccion;
        BeanClSeccionGrupo bSecGrup;
        ClSeccion clSec;
        ClSeccionGrupo secGrup;
        Iterator<ClSeccion> it;
        List<ClSeccionGrupo> lstSecGrup;

        m_sDescripcion = m_sDescripcion == null ? "" : m_sDescripcion;
        lstSecGrup = CommonDAO.getClSeccionDAO().listarSeccionGrupos( m_sDescripcion.trim() );
        iSizeSecGrup = lstSecGrup == null ? 0 : lstSecGrup.size();
        m_hmSeccionesGrupos = new HashMap();
        for ( int i = 0; i < iSizeSecGrup; i++ ) {
            secGrup = lstSecGrup.get( i );

            bSecGrup = new BeanClSeccionGrupo();
            bSecGrup.setSecGrupId( secGrup.getSecGrupoId() );
            bSecGrup.setNomSecGrupo( secGrup.getNomSecGrupo() );
            bSecGrup.setFechaInicio( secGrup.getFechaInicio() );
            bSecGrup.setFechaFin( secGrup.getFechaFin() );
            bSecGrup.setImagenDetalle( "/Imagenes/actions/down.png" );
            bSecGrup.setLstSecciones( new ArrayList<BeanClSeccion>() );
            bSecGrup.setTituloDetalle( "Ver Secciones" );

            bSecGrup.setLstSecciones( new ArrayList<BeanClSeccion>() );
            it = secGrup.getClSecciones().iterator();
            while ( it.hasNext() ) {
                clSec = it.next();
                bClSeccion = new BeanClSeccion( clSec );
                bSecGrup.getLstSecciones().add( bClSeccion );
            }
            m_hmSeccionesGrupos.put( bSecGrup.getSecGrupId(), bSecGrup );
        }

    }

    public void mostrarSecciones( ActionEvent event ) {
        int iIdSecGrupDet;
        BeanClSeccionGrupo bSecGrup;

        iIdSecGrupDet = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "idSecGrupDet" )).getValue().toString() );
        bSecGrup = m_hmSeccionesGrupos.get( iIdSecGrupDet );
        if ( bSecGrup != null ) {
            if ( bSecGrup.isVerDetalle() ) {
                bSecGrup.setVerDetalle( false );
                bSecGrup.setImagenDetalle( "/Imagenes/actions/down.png" );
                bSecGrup.setTituloDetalle( "Ver Secciones" );
            } else {
                bSecGrup.setVerDetalle( true );
                bSecGrup.setImagenDetalle( "/Imagenes/actions/up.png" );
                bSecGrup.setTituloDetalle( "Ocultar Secciones" );
            }
        }
    }

    public void eliminarSeccionGrupo( ActionEvent event ) {
        int iIdSecGrup;
        ClSeccionGrupo clSecGrup;
        BeanClSeccionGrupo bClSecGrup;

        m_sOncomplete = "alert('Error al intentar eliminar el Grupo.');";

        iIdSecGrup = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "idSecGrupDel" )).getValue().toString() );
        bClSecGrup = m_hmSeccionesGrupos.get( iIdSecGrup );
        if ( bClSecGrup != null ) {
            if ( bClSecGrup.getLstSecciones() == null || bClSecGrup.getLstSecciones().isEmpty() ) {
                clSecGrup = CommonDAO.getClSeccionDAO().traerClSeccionGrupoXId( bClSecGrup.getSecGrupId() );
                clSecGrup.setActivo( "0" );
                CommonDAO.getClSeccionDAO().actualizarSeccionGrupo( clSecGrup );
                m_sOncomplete = "alert('El grupo se eliminó correctamente');";
            } else {
                m_sOncomplete = "alert('El grupo contiene secciones asignadas, por lo tanto, no puede eliminarse.');";
            }
        }
    }

    public void eliminarSeccionDetalle( ActionEvent event ) {
        int iIdSecDet;
        int iIdGrupoDetalle;
        BeanClSeccionGrupo bClSecGrup;
        ClSeccion clSec;
        try {
            m_sOncomplete = "alert('Error al quitar sección del grupo.')";
            iIdSecDet = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_detalle_delete" )).getValue().toString() );
            iIdGrupoDetalle = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_grupo_detalle" )).getValue().toString() );
            clSec = CommonDAO.getClSeccionDAO().seleccionarSeccion( iIdSecDet );
            if ( clSec != null ) {
                clSec.setClSecGrup( null );//quito grupo
                CommonDAO.getClSeccionDAO().actualizarSeccion( clSec );//actualizo seccion en bd
                listar();//Genera nuevamente la lista con las secciones correspondientes
                bClSecGrup = m_hmSeccionesGrupos.get( iIdGrupoDetalle );
                if ( bClSecGrup != null ) {
                    bClSecGrup.setVerDetalle( true );
                }
                m_sOncomplete = "alert('Se quitó la sección del grupo correctamente.')";
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public void insertarActualizarSeccionGrupo( ActionEvent event ) {
        boolean blAgrup = false;
        String sAccion;
        ClSeccion clSec;
        ClSeccionGrupo clSecGrup;

        if ( m_clSecGrupo.getNomSecGrupo() == null || m_clSecGrupo.getNomSecGrupo().trim().isEmpty() ) {
            m_sOncomplete = "alert('Ingrese un nombre de grupo correcto.');";
            return;
        } else if ( m_clSecGrupo.getFechaInicio() == null || m_clSecGrupo.getFechaFin() == null ) {
            m_sOncomplete = "alert('La fecha de Inicio y Fin son necesarias.');";
            return;
        } else if ( !esNomGrupoValido() ) {
            m_sOncomplete = "alert('El nombre usado ya existe');";
            return;
        }

        clSecGrup = new ClSeccionGrupo();
        clSecGrup.setActivo( "1" );
        clSecGrup.setNomSecGrupo( m_clSecGrupo.getNomSecGrupo() );
        clSecGrup.setFechaInicio( m_clSecGrupo.getFechaInicio() );
        clSecGrup.setFechaFin( m_clSecGrupo.getFechaFin() );
        if ( m_clSecGrupo.getSecGrupId() == null ) {//NUEVO
            CommonDAO.getClSeccionDAO().insertarSeccionGrupo( clSecGrup );
            m_clSecGrupo.setSecGrupId( clSecGrup.getSecGrupoId() );
            sAccion = "creó";
        } else {
            clSecGrup.setSecGrupoId( m_clSecGrupo.getSecGrupId() );
            CommonDAO.getClSeccionDAO().actualizarSeccionGrupo( clSecGrup );
            sAccion = "actualizó";
        }

        for ( BeanClSeccion bClSecXGrup : m_hmSeccXGrupo.values() ) {
            if ( bClSecXGrup.getClSecGrup() == null ) {
                clSecGrup = new ClSeccionGrupo( m_clSecGrupo.getSecGrupId() );
                clSec = CommonDAO.getClSeccionDAO().seleccionarSeccion( bClSecXGrup.getSecId() );

                clSec.setClSecGrup( clSecGrup );
                CommonDAO.getClSeccionDAO().actualizarSeccion( clSec );
                System.out.println( "SE AGREGA " + bClSecXGrup.getSecId() + ".- " + bClSecXGrup.getSecNombre() + " AL GRUPO." );
                if ( !blAgrup ) {
                    blAgrup = true;
                }
            } else {
                System.out.println( "LA SECCION " + bClSecXGrup.getSecId() + ".- " + bClSecXGrup.getSecNombre() + " YA PERTENECE AL GRUPO." );
            }
        }

        listar();
        m_hmSeccionesGrupos.get( m_clSecGrupo.getSecGrupId() ).setVerDetalle( blAgrup );
        m_sOncomplete = !blAgrup ? "alert('Se " + sAccion + " correctamente el Grupo.');" : "alert('Se agruparon correctamente.');";
        m_sOncomplete += " Richfaces.hideModalPanel('mpMantSecGrupo');";
    }

    private boolean esNomGrupoValido() {
        boolean blValido;
        String sDescSecGrup;
        ClSeccionGrupo clSecGrup;

        blValido = true;

        sDescSecGrup = m_clSecGrupo.getNomSecGrupo() == null ? "" : m_clSecGrupo.getNomSecGrupo().trim().toUpperCase();

        clSecGrup = CommonDAO.getClSeccionDAO().traerClSeccionGrupoXDescripcion( sDescSecGrup );
        if ( m_clSecGrupo.getSecGrupId() == null && clSecGrup != null ) {//El que se esta manipulando es nuevo pero ya existe uno con el mismo nombre
            blValido = false;
        } else if ( m_clSecGrupo.getSecGrupId() != null ) {
            if ( clSecGrup != null && m_clSecGrupo.getSecGrupId().intValue() != clSecGrup.getSecGrupoId().intValue() ) {
                blValido = false;
            }
        }
        return blValido;
    }
}
