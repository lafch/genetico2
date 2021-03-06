package net.uch.cursoLibre.managedBeans;

import java.util.*;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.administrativa.hibernateSpringDao.HSConceptoPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanClEstructuraPagosDetalleSecuencia;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClEstructuraPagos;
import net.uch.mapping.ClEstructuraPagosDetalle;
import net.uch.util.CommonDAO;
import net.uch.utilAdministrativo.MetodosAdm;

public class bEstructuraPago {

    //CAMPOS
    private int idCursoRelacionado;
    private SelectItem[] cboCursosRelacionados;
    private SelectItem[] cboTalleresRelacionados;
    private int estpag_id;
    private String sCentroId;
    private SelectItem[] cboCentros;
    private String estpag_nombre;
    private String estpag_abrev;
    private String estpag_activo;
    private int mod_id;
    private String modulo;
    private SelectItem[] areas;
    private int b_are_id = 0;
    private SelectItem[] modulos;
    private int b_mod_id = 0;
    private SelectItem[] cursos;
    private int b_cur_id = 0;
    private SelectItem[] talleres;
    private int b_tall_id = 0;
    private String b_nombre = "";
    private int estpagdet_id;
    private String estpagdet_nombre;
    private String estpagdet_nomimp;
    private int conpag_id;
    private String concepto;
    private float estpagdet_monto;
    private int d_estpag_id;
    private String estpagdet_activo;
    private int tal_id;
    private String taller;
    private List<bEstructuraPago> estructuras = new ArrayList<bEstructuraPago>();
    private List<bEstructuraPago> detalle = new ArrayList<bEstructuraPago>();
    private boolean verDetalle;
    private String verDetImagen;
    private String verDetTitulo;
    private String oncomplete;
    /////////////////////////
    //modulo//
    private int au_conpag_id;
    private String au_concepto;
    private int i_estpag_id;
    private String i_estpag_nombre;
    private String i_estpag_abrev;
    private int i_are_id = 0;
    private SelectItem[] i_areas;
    private int i_mod_id = 0;
    private SelectItem[] i_modulos;
    private int i_cur_id = 0;
    private SelectItem[] i_cursos;
    private int i_tall_id = 0;
    private SelectItem[] i_talleres;
    //detalle//
    private int i_estpagdet_id;
    private String i_estpagdet_des;
    //private String i_estpagdet_nomimp;
    private String sug_concepto;
    private int i_conpag_id;
    private String i_conpag_des;
    private float i_estpagdet_monto;
    private int id_estpag_id;
    private int i_estpagdet_orden = 1;
    private int e_estpagdet_orden;
    //private SelectItem[] i_talleres = new SelectItem[1];
    //private int i_tal_id = 0;
    //private String i_det_taller;
    private boolean detalleVer;
    private boolean detalleEditar;
    private String e_estpagdet_des;
    private String e_estpagdet_nomimp;
    private float e_estpagdet_monto;
    private boolean verEliminarDetalle;
    private List<bEstructuraPago> i_detalle = new ArrayList<bEstructuraPago>();
    private List<Integer> ids_delete = new ArrayList<Integer>();
    int cont1 = 0;
    private String i_tipo_estructura = "068001";
    private String w_ocultar_modulo = "true";
    private ClArbolAcademico inArbCurRel;
    private ClArbolAcademico edArbCurRel;
    //--Enlace de Estructura de Pagos Detalle
    private String centroIniId = "0";
    private int areaIniId;
    SelectItem[] cboAreasIni;
    private int modIniId;
    SelectItem[] cboModsIni;
    private int curIniId;
    SelectItem[] cboCursIni;
    private int tallIniId;
    SelectItem[] cboTallsIni;
    private int estPagIniId;
    SelectItem[] cboEstPagIni;
    private int estPagDetIniId;
    SelectItem[] cboEstPagDetIni;
    private String centroContId = "0";
    private int areaContId;
    SelectItem[] cboAreasCont;
    private int modContId;
    SelectItem[] cboModsCont;
    private int curContId;
    SelectItem[] cboCursCont;
    private int tallContId;
    SelectItem[] cboTallsCont;
    private int estPagContId;
    SelectItem[] cboEstPagCont;
    private int estPagDetContId;
    SelectItem[] cboEstPagDetCont;
    private List<BeanClEstructuraPagosDetalleSecuencia> lstEstPagDetEnlazadas = new ArrayList<BeanClEstructuraPagosDetalleSecuencia>();

    public int getAreaContId() {
        return areaContId;
    }

    public void setAreaContId( int areaContId ) {
        this.areaContId = areaContId;
    }

    public int getAreaIniId() {
        return areaIniId;
    }

    public void setAreaIniId( int areaIniId ) {
        this.areaIniId = areaIniId;
    }

    public SelectItem[] getCboAreasCont() {
        //System.out.println( "[getCboAreasCont] << ENTER" );
        ClArbolAcademico arbArea;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstArbAreas;
        cboAreasCont = new SelectItem[ 1 ];
        if ( !"0".equals( centroContId ) ) {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstArbAreas = arbAcadDAO.AreasXInstitucion( centroContId );
            cboAreasCont = new SelectItem[ lstArbAreas.size() + 1 ];
            for ( int i = 0; i < lstArbAreas.size(); i++ ) {
                arbArea = lstArbAreas.get( i );
                cboAreasCont[i + 1] = new SelectItem( arbArea.getArbId(), arbArea.getArbDescripcion() );
            }
        }
        cboAreasCont[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboAreasCont] >> EXIT" );
        return cboAreasCont;
    }

    public void setCboAreasCont( SelectItem[] cboAreasCont ) {
        this.cboAreasCont = cboAreasCont;
    }

    public SelectItem[] getCboAreasIni() {
        //System.out.println( "[getCboAreasIni] << ENTER" );
        ClArbolAcademico arbArea;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstArbAreas;
        cboAreasIni = new SelectItem[ 1 ];
        if ( !"0".equals( centroIniId ) ) {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstArbAreas = arbAcadDAO.AreasXInstitucion( centroIniId );
            cboAreasIni = new SelectItem[ lstArbAreas.size() + 1 ];
            for ( int i = 0; i < lstArbAreas.size(); i++ ) {
                arbArea = lstArbAreas.get( i );
                cboAreasIni[i + 1] = new SelectItem( arbArea.getArbId(), arbArea.getArbDescripcion() );
            }
        }
        cboAreasIni[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboAreasIni] >> EXIT" );
        return cboAreasIni;
    }

    public void setCboAreasIni( SelectItem[] cboAreasIni ) {
        this.cboAreasIni = cboAreasIni;
    }

    public SelectItem[] getCboEstPagCont() {
        //System.out.println( "[getCboEstPagCont] << ENTER" );
        ClEstructuraPagos estPag;
        HSEstructuraPagoDAO estPagDAO;
        List<ClEstructuraPagos> lstEstPag;
        cboEstPagCont = new SelectItem[ 1 ];
        if ( tallContId != 0 ) {
            estPagDAO = CommonDAO.getClEstructuraPagoDAO();
            try {
                lstEstPag = estPagDAO.seleccionarEstructuras( "", tallContId );
            } catch ( Exception ex ) {
                ex.printStackTrace();
                lstEstPag = new ArrayList<ClEstructuraPagos>();
            }
            cboEstPagCont = new SelectItem[ lstEstPag.size() + 1 ];
            for ( int i = 0; i < lstEstPag.size(); i++ ) {
                estPag = lstEstPag.get( i );
                cboEstPagCont[i + 1] = new SelectItem( estPag.getEstpagId(), estPag.getEstpagNombre() );
            }
        }
        cboEstPagCont[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboEstPagCont] >> EXIT" );
        return cboEstPagCont;
    }

    public void setCboEstPagCont( SelectItem[] cboEstPagCont ) {
        this.cboEstPagCont = cboEstPagCont;
    }

    public SelectItem[] getCboEstPagDetCont() {
        //System.out.println( "[getCboEstPagDetCont] << ENTER" );
        ClEstructuraPagosDetalle estPagDet;
        HSEstructuraPagoDAO estPagDAO;
        List<ClEstructuraPagosDetalle> lstEstPagDet;
        cboEstPagDetCont = new SelectItem[ 1 ];
        if ( estPagContId != 0 ) {
            estPagDAO = CommonDAO.getClEstructuraPagoDAO();
            try {
                lstEstPagDet = estPagDAO.seleccionarDetalle( estPagContId );
            } catch ( Exception ex ) {
                ex.printStackTrace();
                lstEstPagDet = new ArrayList<ClEstructuraPagosDetalle>();
            }
            cboEstPagDetCont = new SelectItem[ lstEstPagDet.size() + 1 ];
            for ( int i = 0; i < lstEstPagDet.size(); i++ ) {
                estPagDet = lstEstPagDet.get( i );
                cboEstPagDetCont[i + 1] = new SelectItem( estPagDet.getEstpagdetId(), " (s/. " + estPagDet.getEstpagdetMonto() + ")" + estPagDet.getEstpagdetNombre() + " / " + estPagDet.getEstpagdetId() );
            }
        }
        cboEstPagDetCont[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboEstPagDetCont] >> EXIT" );
        return cboEstPagDetCont;
    }

    public void setCboEstPagDetCont( SelectItem[] cboEstPagDetCont ) {
        this.cboEstPagDetCont = cboEstPagDetCont;
    }

    public SelectItem[] getCboEstPagDetIni() {
        //System.out.println( "[getCboEstPagDetIni] << ENTER" );
        ClEstructuraPagosDetalle estPagDet;
        HSEstructuraPagoDAO estPagDAO;
        List<ClEstructuraPagosDetalle> lstEstPagDet;
        cboEstPagDetIni = new SelectItem[ 1 ];
        if ( estPagIniId != 0 ) {
            estPagDAO = CommonDAO.getClEstructuraPagoDAO();
            try {
                lstEstPagDet = estPagDAO.seleccionarDetalle( estPagIniId );
            } catch ( Exception ex ) {
                ex.printStackTrace();
                lstEstPagDet = new ArrayList<ClEstructuraPagosDetalle>();
            }
            cboEstPagDetIni = new SelectItem[ lstEstPagDet.size() + 1 ];
            for ( int i = 0; i < lstEstPagDet.size(); i++ ) {
                estPagDet = lstEstPagDet.get( i );
                cboEstPagDetIni[i + 1] = new SelectItem( estPagDet.getEstpagdetId(), " (s/. " + estPagDet.getEstpagdetMonto() + ")" + estPagDet.getEstpagdetNombre() + " / " + estPagDet.getEstpagdetId() );
            }
        }
        cboEstPagDetIni[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboEstPagDetIni] >> EXIT" );
        return cboEstPagDetIni;
    }

    public void setCboEstPagDetIni( SelectItem[] cboEstPagDetIni ) {
        this.cboEstPagDetIni = cboEstPagDetIni;
    }

    public SelectItem[] getCboEstPagIni() {
        //System.out.println( "[getCboEstPagIni] << ENTER" );
        ClEstructuraPagos estPag;
        HSEstructuraPagoDAO estPagDAO;
        List<ClEstructuraPagos> lstEstPag;
        cboEstPagIni = new SelectItem[ 1 ];
        if ( tallIniId != 0 ) {
            estPagDAO = CommonDAO.getClEstructuraPagoDAO();
            try {
                lstEstPag = estPagDAO.seleccionarEstructuras( "", tallIniId );
            } catch ( Exception ex ) {
                ex.printStackTrace();
                lstEstPag = new ArrayList<ClEstructuraPagos>();
            }
            cboEstPagIni = new SelectItem[ lstEstPag.size() + 1 ];
            for ( int i = 0; i < lstEstPag.size(); i++ ) {
                estPag = lstEstPag.get( i );
                cboEstPagIni[i + 1] = new SelectItem( estPag.getEstpagId(), estPag.getEstpagNombre() );
            }
        }
        cboEstPagIni[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboEstPagIni] >> EXIT" );
        return cboEstPagIni;
    }

    public void setCboEstPagIni( SelectItem[] cboEstPagIni ) {
        this.cboEstPagIni = cboEstPagIni;
    }

    public SelectItem[] getCboModsCont() {
        //System.out.println( "[getCboModsCont] << ENTER" );
        ClArbolAcademico arbMod;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstArbMods;
        cboModsCont = new SelectItem[ 1 ];
        if ( areaContId != 0 ) {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstArbMods = arbAcadDAO.listarArbolPorPadre( areaContId );
            cboModsCont = new SelectItem[ lstArbMods.size() + 1 ];
            for ( int i = 0; i < lstArbMods.size(); i++ ) {
                arbMod = lstArbMods.get( i );
                cboModsCont[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
            }
        }
        cboModsCont[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboModsCont] >> EXIT" );
        return cboModsCont;
    }

    public void setCboModsCont( SelectItem[] cboModsCont ) {
        this.cboModsCont = cboModsCont;
    }

    public int getCurContId() {
        return curContId;
    }

    public void setCurContId( int curContId ) {
        this.curContId = curContId;
    }

    public SelectItem[] getCboCursCont() {
        ClArbolAcademico arbMod;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstArbMods;
        cboCursCont = new SelectItem[ 1 ];
        if ( modContId != 0 ) {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstArbMods = arbAcadDAO.listarArbolPorPadre( modContId );
            cboCursCont = new SelectItem[ lstArbMods.size() + 1 ];
            for ( int i = 0; i < lstArbMods.size(); i++ ) {
                arbMod = lstArbMods.get( i );
                cboCursCont[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
            }
        }
        cboCursCont[0] = new SelectItem( 0, "[Seleccione]" );
        return cboCursCont;
    }

    public void setCboCursCont( SelectItem[] cboCursCont ) {
        this.cboCursCont = cboCursCont;
    }

    public int getTallContId() {
        return tallContId;
    }

    public void setTallContId( int tallContId ) {
        this.tallContId = tallContId;
    }

    public SelectItem[] getCboTallsCont() {
        ClArbolAcademico arbMod;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstArbMods;
        cboTallsCont = new SelectItem[ 1 ];
        if ( curContId != 0 ) {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstArbMods = arbAcadDAO.listarArbolPorPadre( curContId );
            cboTallsCont = new SelectItem[ lstArbMods.size() + 1 ];
            for ( int i = 0; i < lstArbMods.size(); i++ ) {
                arbMod = lstArbMods.get( i );
                cboTallsCont[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
            }
        }
        cboTallsCont[0] = new SelectItem( 0, "[Seleccione]" );
        return cboTallsCont;
    }

    public void setCboTallsCont( SelectItem[] cboTallsCont ) {
        this.cboTallsCont = cboTallsCont;
    }

    public SelectItem[] getCboModsIni() {
        //System.out.println( "[getCboModsIni] << ENTER" );
        ClArbolAcademico arbMod;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstArbMods;
        cboModsIni = new SelectItem[ 1 ];
        if ( areaIniId != 0 ) {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstArbMods = arbAcadDAO.listarArbolPorPadre( areaIniId );
            cboModsIni = new SelectItem[ lstArbMods.size() + 1 ];
            for ( int i = 0; i < lstArbMods.size(); i++ ) {
                arbMod = lstArbMods.get( i );
                cboModsIni[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
            }
        }
        cboModsIni[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboModsIni] >> EXIT" );
        return cboModsIni;
    }

    public void setCboModsIni( SelectItem[] cboModsIni ) {
        this.cboModsIni = cboModsIni;
    }

    //relacion entre estructuras inicio
    public int getCurIniId() {
        return curIniId;
    }

    public void setCurIniId( int curIniId ) {
        this.curIniId = curIniId;
    }

    public SelectItem[] getCboCursIni() {
        ClArbolAcademico arbMod;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstArbMods;
        cboCursIni = new SelectItem[ 1 ];
        if ( modIniId != 0 ) {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstArbMods = arbAcadDAO.listarArbolPorPadre( modIniId );
            cboCursIni = new SelectItem[ lstArbMods.size() + 1 ];
            for ( int i = 0; i < lstArbMods.size(); i++ ) {
                arbMod = lstArbMods.get( i );
                cboCursIni[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
            }
        }
        cboCursIni[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboModsIni] >> EXIT" );
        return cboCursIni;
    }

    public void setCboCursIni( SelectItem[] cboCursIni ) {
        this.cboCursIni = cboCursIni;
    }

    public int getTallIniId() {
        return tallIniId;
    }

    public void setTallIniId( int tallIniId ) {
        this.tallIniId = tallIniId;
    }

    public SelectItem[] getCboTallsIni() {
        ClArbolAcademico arbMod;
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstArbMods;
        cboTallsIni = new SelectItem[ 1 ];
        if ( curIniId != 0 ) {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstArbMods = arbAcadDAO.listarArbolPorPadre( curIniId );
            cboTallsIni = new SelectItem[ lstArbMods.size() + 1 ];
            for ( int i = 0; i < lstArbMods.size(); i++ ) {
                arbMod = lstArbMods.get( i );
                cboTallsIni[i + 1] = new SelectItem( arbMod.getArbId(), arbMod.getArbDescripcion() );
            }
        }
        cboTallsIni[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getCboModsIni] >> EXIT" );
        return cboTallsIni;
    }

    public void setCboTallsIni( SelectItem[] cboTallsIni ) {
        this.cboTallsIni = cboTallsIni;
    }

    public String getCentroContId() {
        return centroContId;
    }

    public void setCentroContId( String centroContId ) {
        this.centroContId = centroContId;
    }

    public String getCentroIniId() {
        return centroIniId;
    }

    public void setCentroIniId( String centroIniId ) {
        this.centroIniId = centroIniId;
    }

    public int getEstPagContId() {
        return estPagContId;
    }

    public void setEstPagContId( int estPagContId ) {
        this.estPagContId = estPagContId;
    }

    public int getEstPagDetContId() {
        return estPagDetContId;
    }

    public void setEstPagDetContId( int estPagDetContId ) {
        this.estPagDetContId = estPagDetContId;
    }

    public int getEstPagDetIniId() {
        return estPagDetIniId;
    }

    public void setEstPagDetIniId( int estPagDetIniId ) {
        this.estPagDetIniId = estPagDetIniId;
    }

    public int getEstPagIniId() {
        return estPagIniId;
    }

    public void setEstPagIniId( int estPagIniId ) {
        this.estPagIniId = estPagIniId;
    }

    public int getModContId() {
        return modContId;
    }

    public void setModContId( int modContId ) {
        this.modContId = modContId;
    }

    public int getModIniId() {
        return modIniId;
    }

    public void setModIniId( int modIniId ) {
        this.modIniId = modIniId;
    }

    public List<BeanClEstructuraPagosDetalleSecuencia> getLstEstPagDetEnlazadas() {
        if ( lstEstPagDetEnlazadas == null ) {
            lstEstPagDetEnlazadas = new ArrayList<BeanClEstructuraPagosDetalleSecuencia>();
        }
        return lstEstPagDetEnlazadas;
    }

    public void setLstEstPagDetEnlazadas( List<BeanClEstructuraPagosDetalleSecuencia> lstEstPagDetEnlazadas ) {
        this.lstEstPagDetEnlazadas = lstEstPagDetEnlazadas;
    }

    public void listarEnlaces( ActionEvent event ) {
        /* if ( modIniId != 0 ) {
         lstEstPagDetEnlazadas = CommonDAO.getClEstructuraPagoDAO().listarEstPagDetSecuencia( modIniId, estPagIniId );
         }*/
        if ( tallIniId != 0 ) {
            lstEstPagDetEnlazadas = CommonDAO.getClEstructuraPagoDAO().listarEstPagDetSecuencia( tallIniId, estPagIniId );
        }
    }

    public void enlazarEstPagDet( ActionEvent event ) {
        BeanClEstructuraPagosDetalleSecuencia epds;
        ClEstructuraPagosDetalle epdIni;
        ClEstructuraPagosDetalle epdCont;
        HSEstructuraPagoDAO estPagDAO;
        oncomplete = "";
        try {
            estPagDAO = CommonDAO.getClEstructuraPagoDAO();
            if ( estPagDAO.buscarEstPagDetSec( estPagDetIniId, estPagDetContId ) != null ) {
                oncomplete = "alert( 'Ya existe esa continuación en el sistema' );Richfaces.hideModalPanel('mpConfirmarEnlazar');";
                return;
            }

            epdIni = estPagDAO.buscarEstructuraPagosDet( estPagDetIniId );
            epdCont = estPagDAO.buscarEstructuraPagosDet( estPagDetContId );

            epds = new BeanClEstructuraPagosDetalleSecuencia();
            epds.setEstpagdetIni( epdIni );
            epds.setEstpagdetCont( epdCont );
//            epds.setFechaCreacion( new java.sql.Date( Calendar.getInstance().getTimeInMillis() ) );
            epds.setActivo( 1 );
            if ( estPagDAO.insertarActualizarContinuacionEstPagDet( epds ) == 1 ) {
                oncomplete = "alert( 'Ingresado correctamente.' );";
                lstEstPagDetEnlazadas.add( epds );
            } else {
                oncomplete = "alert( 'Error al ingresar continuación seleccionada.' );";
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            oncomplete = "alert( 'Error al ingresar continuación seleccionada.' );";
        }
        oncomplete += "Richfaces.hideModalPanel('mpConfirmarEnlazar')";
    }
    //--Fin Enlace de Estructura de Pagos Detalle

    public ClArbolAcademico getInArbCurRel() {
        //System.out.println( "[getInArbCurRel] << ENTER" );
        //System.out.println( "[getInArbCurRel] >> EXIT" );
        return inArbCurRel;
    }

    public void setInArbCurRel( ClArbolAcademico arbCurRel ) {
        //System.out.println( "[setInArbCurRel] << ENTER" );
        this.inArbCurRel = arbCurRel;
        //System.out.println( "[setInArbCurRel] >> EXIT" );
    }

    public ClArbolAcademico getEdArbCurRel() {
        //System.out.println( "[getEdArbCurRel] << ENTER" );
        //System.out.println( "[getEdArbCurRel] >> EXIT" );
        return edArbCurRel;
    }

    public void setEdArbCurRel( ClArbolAcademico edArbCurRel ) {
        //System.out.println( "[setEdArbCurRel] << ENTER" );
        this.edArbCurRel = edArbCurRel;
        //System.out.println( "[setEdArbCurRel] >> EXIT" );
    }

    public String getI_tipo_estructura() {
        //System.out.println( "[getI_tipo_estructura] << ENTER" );
        //System.out.println( "[getI_tipo_estructura] >> EXIT" );
        return i_tipo_estructura;
    }

    public void setI_tipo_estructura( String i_tipo_estructura ) {
        //System.out.println( "[setI_tipo_estructura] << ENTER" );
        this.i_tipo_estructura = i_tipo_estructura;
        //System.out.println( "[setI_tipo_estructura] >> EXIT" );
    }

    public String getW_ocultar_modulo() {
        //System.out.println( "[getW_ocultar_modulo] << ENTER" );
        //System.out.println( "[getW_ocultar_modulo] >> EXIT" );
        return w_ocultar_modulo;
    }

    public void setW_ocultar_modulo( String w_ocultar_modulo ) {
        //System.out.println( "[setW_ocultar_modulo] << ENTER" );
        this.w_ocultar_modulo = w_ocultar_modulo;
        //System.out.println( "[setW_ocultar_modulo] >> EXIT" );
    }

    public int getI_estpagdet_orden() {
        //System.out.println( "[getI_estpagdet_orden] << ENTER" );
        //System.out.println( "[getI_estpagdet_orden] >> EXIT" );
        return i_estpagdet_orden;
    }

    public void setI_estpagdet_orden( int i_estpagdet_orden ) {
        //System.out.println( "[setI_estpagdet_orden] << ENTER" );
        this.i_estpagdet_orden = i_estpagdet_orden;
        //System.out.println( "[setI_estpagdet_orden] >> EXIT" );
    }

    public int getE_estpagdet_orden() {
        //System.out.println( "[getE_estpagdet_orden] << ENTER" );
        //System.out.println( "[getE_estpagdet_orden] >> EXIT" );
        return e_estpagdet_orden;
    }

    public void setE_estpagdet_orden( int e_estpagdet_orden ) {
        //System.out.println( "[setE_estpagdet_orden] << ENTER" );
        this.e_estpagdet_orden = e_estpagdet_orden;
        //System.out.println( "[setE_estpagdet_orden] >> EXIT" );
    }

    public SelectItem[] getCboCursosRelacionados() {
        //System.out.println( "[getCboCursosRelacionados] << ENTER" );
        int iSize;
        ClArbolAcademico arbCur;
        List<ClArbolAcademico> lstArbCursos;
        if ( i_are_id != 0 && i_mod_id != 0 && i_cur_id != 0 && i_tall_id != 0 ) {
            lstArbCursos = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( i_cur_id );
            iSize = lstArbCursos.size();
            cboCursosRelacionados = new SelectItem[ iSize + 1 ];
            for ( int i = 0; i < iSize; i++ ) {
                arbCur = lstArbCursos.get( i );
                cboCursosRelacionados[i + 1] = new SelectItem( arbCur.getArbId(), arbCur.getArbDescripcion() );
            }
        } else {
            cboCursosRelacionados = new SelectItem[ 1 ];
        }
        cboCursosRelacionados[0] = new SelectItem( "0", "[Seleccione]" );
        //System.out.println( "[getCboCursosRelacionados] >> EXIT" );
        return cboCursosRelacionados;
    }

    public void setCboCursosRelacionados( SelectItem[] cboCursosRelacionados ) {
        //System.out.println( "[setCboCursosRelacionados] << ENTER" );
        this.cboCursosRelacionados = cboCursosRelacionados;
        //System.out.println( "[setCboCursosRelacionados] >> EXIT" );
    }

    /*
     public SelectItem[] getCboTalleresRelacionados() {
     int iSize;
     ClArbolAcademico arbCur;
     List<ClArbolAcademico> lstArbTaller;
     if ( i_are_id != 0 && i_mod_id != 0 && i_cur_id != 0 && i_tall_id != 0 ) {
     lstArbTaller = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( i_cur_id );
            
     iSize = lstArbTaller.size();
     cboTalleresRelacionados = new SelectItem[ iSize + 1 ];
     for ( int i = 0; i < iSize; i++ ) {
     arbCur = lstArbTaller.get( i );
     cboTalleresRelacionados[i + 1] = new SelectItem( arbCur.getArbId(), arbCur.getArbDescripcion() );
     }
     } else {
     cboTalleresRelacionados = new SelectItem[ 1 ];
     }
     cboTalleresRelacionados[0] = new SelectItem( "0", "[Seleccione]" );
     //System.out.println( "[getCboCursosRelacionados] >> EXIT" );
     return cboTalleresRelacionados;
     }

     public void setCboTalleresRelacionados( SelectItem[] cboTalleresRelacionados ) {
     this.cboTalleresRelacionados = cboTalleresRelacionados;
     }
     */
    public int getIdCursoRelacionado() {
        //System.out.println( "[getIdCursoRelacionado] << ENTER" );
        //System.out.println( "[getIdCursoRelacionado] >> EXIT" );
        return idCursoRelacionado;
    }

    public void setIdCursoRelacionado( int idCursoRelacionado ) {
        //System.out.println( "[setIdCursoRelacionado] << ENTER" );
        this.idCursoRelacionado = idCursoRelacionado;
        //System.out.println( "[setIdCursoRelacionado] >> EXIT" );
    }

    //CONSTRUCTORES
    public bEstructuraPago() {
        //System.out.println( "[bEstructuraPago] << ENTER" );
        //System.out.println( "[bEstructuraPago] >> EXIT" );
    }

    public bEstructuraPago( int p ) {
        //System.out.println( "[bEstructuraPago(p)] << ENTER" );
        //System.out.println( "[bEstructuraPago(p)] >> EXIT" );
    }

    //METODOS
    public List<bEstructuraPago> autocomplete( Object suggest ) throws Exception {
        //System.out.println( "[autocomplete] << ENTER" );
        HSConceptoPagoDAO daoConcepto = (HSConceptoPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoConceptoPago" );
        String pref = (String)suggest;
        List<bEstructuraPago> conceptos = new ArrayList<bEstructuraPago>();
        if ( daoConcepto.seleccionarConceptoCursoLibre( pref ).size() > 0 ) {
            List lista = daoConcepto.seleccionarConceptoCursoLibre( pref );
            for ( int i = 0; i < lista.size(); i++ ) {
                AdConceptoPago cp = (AdConceptoPago)lista.get( i );
                bEstructuraPago c = new bEstructuraPago( i );
                c.setAu_conpag_id( cp.getId() );
                c.setAu_concepto( cp.getConpagDescripcion() );
                conceptos.add( c );
            }
        }
        //System.out.println( "[autocomplete] >> EXIT" );
        return conceptos;
    }

    public String seleccionar() throws Exception {
        //System.out.println( "[seleccionar] << ENTER" );
        ClArbolAcademico arbCurRel;
        HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
        // HSTallerDAO daoTaller = (HSTallerDAO) ServiceFinder.findBean("SpringHibernateDaoCLTaller");
        this.setEstructuras( new ArrayList<bEstructuraPago>() );
        if ( this.getB_tall_id() > 0 || this.getB_nombre().trim().length() >= 3 ) {
            if ( dao.seleccionarEstructuras( this.getB_nombre(), this.getB_tall_id() ).size() > 0 ) {
                List<ClEstructuraPagos> lista = dao.seleccionarEstructuras( this.getB_nombre(), this.getB_tall_id() );
                for ( int i = 0; i < lista.size(); i++ ) {
                    ClEstructuraPagos est = lista.get( i );
                    bEstructuraPago e = new bEstructuraPago( i );
                    e.setEstpag_id( est.getEstpagId() );
                    e.setEstpag_nombre( est.getEstpagNombre() );
                    e.setEstpag_abrev( est.getEstpagAbrev() );
                    e.setEstpag_activo( est.getEstpagActivo() );
                    if ( est.getClArbolAcademico() == null ) {
                        e.setMod_id( 0 );
                        e.setModulo( "Estructura Libre" );
                    } else {
                        e.setMod_id( est.getClArbolAcademico().getArbId() );
                        e.setModulo( est.getClArbolAcademico().getArbDescripcion() );
                    }


                    e.setDetalle( new ArrayList<bEstructuraPago>() );
                    List<ClEstructuraPagosDetalle> lDetalle = dao.seleccionarDetalle( est.getEstpagId() );
                    for ( int j = 0; j < lDetalle.size(); j++ ) {
                        ClEstructuraPagosDetalle det = lDetalle.get( j );
                        bEstructuraPago d = new bEstructuraPago( j );

                        d.setEstpagdet_id( det.getEstpagdetId() );
                        d.setEstpagdet_nombre( det.getEstpagdetNombre() );
                        d.setConpag_id( det.getAdConceptoPago().getId() );
                        d.setConcepto( det.getAdConceptoPago().getConpagDescripcion() );
                        d.setEstpagdet_monto( det.getEstpagdetMonto() );
                        d.setD_estpag_id( det.getClEstructuraPagos().getEstpagId() );
                        d.setTal_id( det.getTalId() );
                        arbCurRel = det.getClArbolAcadCurso();
                        d.setInArbCurRel( arbCurRel );
                        //d.setTaller(daoTaller.buscarTaller(det.getTalId()).getTalDescripcion());
                        e.getDetalle().add( d );
                    }

                    e.setVerDetalle( false );
                    e.setVerDetImagen( "/Imagenes/actions/down.png" );
                    e.setVerDetTitulo( "Ver Detalle" );

                    this.getEstructuras().add( e );
                }
            } //fin
        }
        //System.out.println( "[seleccionar] >> EXIT" );
        return "";
    }

    public void mostrarDetalle( ActionEvent event ) throws Exception {
        //System.out.println( "[mostrarDetalle] << ENTER" );
        bEstructuraPago espPagSelec;
        int p_id_ver = Integer.valueOf( ( (UIParameter)event.getComponent().findComponent( "id_ver" ) ).getValue().toString() );
        for ( int i = 0; i < this.getEstructuras().size(); i++ ) {
            espPagSelec = this.getEstructuras().get( i );
            if ( espPagSelec.getEstpag_id() == p_id_ver ) {
                if ( espPagSelec.isVerDetalle() ) {
                    espPagSelec.setVerDetalle( false );
                    espPagSelec.setVerDetImagen( "/Imagenes/actions/down.png" );
                    espPagSelec.setVerDetTitulo( "Mostrar Detalle" );
                } else {
                    espPagSelec.setVerDetalle( true );
                    espPagSelec.setVerDetImagen( "/Imagenes/actions/up.png" );
                    espPagSelec.setVerDetTitulo( "Ocultar Detalle" );
                }
                i = this.getEstructuras().size();
            }
        }
        //System.out.println( "[mostrarDetalle] >> EXIT" );
    }

    public void nuevo( ActionEvent event ) throws Exception {
        //System.out.println( "[nuevo] << ENTER" );
        this.setOncomplete( "" );
        this.setI_estpagdet_orden( 1 );
        this.setE_estpagdet_orden( 1 );
        this.setI_estpag_id( 0 );
        this.setI_estpag_nombre( "" );
        this.setI_estpag_abrev( "" );
        this.setI_modulos( getI_modulos() );
        this.setI_are_id( 0 );
        this.setI_mod_id( 0 );
        this.setI_cur_id( 0 );
        this.setI_tall_id( 0 );
        this.setIdCursoRelacionado( 0 );
        this.setI_detalle( new ArrayList<bEstructuraPago>() );

        this.setSug_concepto( "" );
        this.setI_conpag_id( 0 );
        this.setI_conpag_des( "" );

        this.setI_estpagdet_des( "" );
        this.setEstpagdet_nomimp( "" );
        this.setI_estpagdet_monto( 0 );
        cont1 = 0;
        this.setIds_delete( new ArrayList<Integer>() );
        this.setI_tipo_estructura( "068001" );
        this.setW_ocultar_modulo( "true" );

        this.setOncomplete( "Richfaces.showModalPanel('mpUpdate')" );
        //System.out.println( "[nuevo] >> EXIT" );
    }

    public void agregarDetalle( ActionEvent event ) throws Exception {
        //System.out.println( "[agregarDetalle] << ENTER" );
        this.setOncomplete( "" );
        HSConceptoPagoDAO daoConcepto = (HSConceptoPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoConceptoPago" );

        if ( this.getI_conpag_id() > 0
                && this.getI_estpagdet_des().trim().length() > 0
                && this.getI_estpagdet_monto() > 0 && idCursoRelacionado > 0 ) {
            bEstructuraPago det = new bEstructuraPago( 0 );
            det.setI_estpagdet_id( cont1 );
            det.setI_conpag_id( this.getI_conpag_id() );
            det.setI_conpag_des( ( (AdConceptoPago)daoConcepto.seleccionarUnaConceptoPago( this.getI_conpag_id() ).get( 0 ) ).getConpagDescripcion() );
            det.setI_estpagdet_des( this.getI_estpagdet_des().trim().toUpperCase() );
            det.setEstpagdet_nomimp( this.getEstpagdet_nomimp().trim().toUpperCase() );
            det.setI_estpagdet_monto( this.getI_estpagdet_monto() );
            det.setI_estpagdet_orden( this.getI_estpagdet_orden() );
            det.setE_estpagdet_des( "" );

            det.setE_estpagdet_monto( 0 );
            det.setE_estpagdet_orden( 1 );
            det.setInArbCurRel( CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( idCursoRelacionado ) );
            det.setDetalleEditar( false );
            det.setDetalleVer( true );
            //this.getI_estpagdet_orden()=this.getI_estpagdet_orden()+1;
            this.setI_estpagdet_orden( i_estpagdet_orden + 1 );
            this.getI_detalle().add( det );
            cont1--;
        } else {
            this.setOncomplete( "javascript:alert('Todos los campos son Obligatorios.')" );
        }
        //System.out.println( "[agregarDetalle] >> EXIT" );
    }

    public void editarDetalle( ActionEvent event ) throws Exception {
        //System.out.println( "[editarDetalle] << ENTER" );
        int id_det_update = Integer.valueOf( ( (UIParameter)event.getComponent().findComponent( "id_det_update" ) ).getValue().toString() );
        ClArbolAcademico arbCurso;

        for ( int i = 0; i < this.getI_detalle().size(); i++ ) {
            if ( this.getI_detalle().get( i ).getI_estpagdet_id() == id_det_update ) {
                arbCurso = this.getI_detalle().get( i ).getInArbCurRel() == null ? new ClArbolAcademico( 0 ) : this.getI_detalle().get( i ).getInArbCurRel();
                this.getI_detalle().get( i ).setE_estpagdet_des( this.getI_detalle().get( i ).getI_estpagdet_des() );
                this.getI_detalle().get( i ).setE_estpagdet_nomimp( this.getI_detalle().get( i ).getEstpagdet_nomimp() );
                this.getI_detalle().get( i ).setE_estpagdet_monto( this.getI_detalle().get( i ).getI_estpagdet_monto() );
                this.getI_detalle().get( i ).setE_estpagdet_orden( this.getI_detalle().get( i ).getI_estpagdet_orden() );
//                this.getI_detalle().get( i ).setEdArbCurRel( this.getI_detalle().get( i ).getInArbCurRel() );
                this.getI_detalle().get( i ).setInArbCurRel( arbCurso );
                this.getI_detalle().get( i ).setDetalleEditar( true );
                this.getI_detalle().get( i ).setDetalleVer( false );
                i = this.getI_detalle().size();
            }
        }
        //System.out.println( "[editarDetalle] >> EXIT" );
    }

    public void guardarDetalle( ActionEvent event ) throws Exception {
        //System.out.println( "[guardarDetalle] << ENTER" );
        this.setOncomplete( "" );
        int id_det_update = Integer.valueOf( ( (UIParameter)event.getComponent().findComponent( "id_det_update" ) ).getValue().toString() );
        for ( int i = 0; i < this.getI_detalle().size(); i++ ) {
            if ( this.getI_detalle().get( i ).getI_estpagdet_id() == id_det_update ) {
                if ( this.getI_detalle().get( i ).getE_estpagdet_des().trim().length() > 0
                        && this.getI_detalle().get( i ).getE_estpagdet_monto() > 0 ) {
                    this.getI_detalle().get( i ).setI_estpagdet_des( this.getI_detalle().get( i ).getE_estpagdet_des().trim().toUpperCase() );
                    this.getI_detalle().get( i ).setEstpagdet_nomimp( this.getI_detalle().get( i ).getE_estpagdet_nomimp().trim().toUpperCase() );

                    if ( this.getI_detalle().get( i ).getInArbCurRel() != null && this.getI_detalle().get( i ).getInArbCurRel().getArbId() > 0 ) {
                        this.getI_detalle().get( i ).setInArbCurRel( CommonDAO.getClArbolAcademicoDAO().buscarArbolPorId( this.getI_detalle().get( i ).getInArbCurRel().getArbId() ) );
                    }

                    this.getI_detalle().get( i ).setI_estpagdet_monto( this.getI_detalle().get( i ).getE_estpagdet_monto() );
                    this.getI_detalle().get( i ).setI_estpagdet_orden( this.getI_detalle().get( i ).getE_estpagdet_orden() );

                    this.getI_detalle().get( i ).setDetalleEditar( false );
                    this.getI_detalle().get( i ).setDetalleVer( true );
                } else {
                    this.setOncomplete( "javascript:alert('Ingrese ambos campos para Guardar.')" );
                }
                i = this.getI_detalle().size();
            }
        }
        //System.out.println( "[guardarDetalle] >> EXIT" );
    }

    public void cancelarDetalle( ActionEvent event ) throws Exception {
        //System.out.println( "[cancelarDetalle] << ENTER" );
        int id_det_update = Integer.valueOf( ( (UIParameter)event.getComponent().findComponent( "id_det_update" ) ).getValue().toString() );
        for ( int i = 0; i < this.getI_detalle().size(); i++ ) {
            if ( this.getI_detalle().get( i ).getI_estpagdet_id() == id_det_update ) {
                this.getI_detalle().get( i ).setDetalleEditar( false );
                this.getI_detalle().get( i ).setDetalleVer( true );

                i = this.getI_detalle().size();
            }
        }
        //System.out.println( "[cancelarDetalle] >> EXIT" );
    }

    public void eliminarDetalle( ActionEvent event ) throws Exception {
        //System.out.println( "[eliminarDetalle] << ENTER" );
        this.setOncomplete( "" );
        int id_det_delete = Integer.valueOf( ( (UIParameter)event.getComponent().findComponent( "id_det_delete" ) ).getValue().toString() );
        HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );

        /*
         * if (dao.verificarEliminarDetalle(id_det_delete).size() > 0) {
         * this.setOncomplete("javascript:alert('Este detalle ya fue
         * Utilizado.')"); } else {
         */
        for ( int i = 0; i < this.getI_detalle().size(); i++ ) {
            if ( this.getI_detalle().get( i ).getI_estpagdet_id() == id_det_delete ) {
                if ( this.getI_detalle().get( i ).getI_estpagdet_id() > 0 ) {
                    this.getIds_delete().add( this.getI_detalle().get( i ).getI_estpagdet_id() );
                }
                this.getI_detalle().remove( i );

                i = this.getI_detalle().size();
            }
        }
        //System.out.println( "[eliminarDetalle] >> EXIT" );
    }

    public void actualizarEstructura( ActionEvent event ) throws Exception {
        //System.out.println( "[actualizarEstructura] << ENTER" );
        this.setOncomplete( "" );

        int id_update = Integer.valueOf( ( (UIParameter)event.getComponent().findComponent( "id_update" ) ).getValue().toString() );
        HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
        //HSTallerDAO daoTaller = (HSTallerDAO) ServiceFinder.findBean("SpringHibernateDaoCLTaller");
        ClEstructuraPagos est = dao.buscarEstructuraPagos( id_update );
        this.setI_estpag_id( est.getEstpagId() );
        this.setI_estpag_nombre( est.getEstpagNombre() );
        this.setI_estpag_abrev( est.getEstpagAbrev() );
        //System.out.println( "ID_MODULO: " + est.getClArbolAcademico().getArbId() );
        /*
         * this.setI_mod_id(est.getClArbolAcademico().getArbId());*
         */
        ClArbolAcademico l = dao.buscarModulo( est.getClArbolAcademico().getArbId() );
        //System.out.println( "ID_AREA: " + l.getArbIdPadre() + "nombre: " + l.getArbDescripcion() );
        this.setI_cur_id( l.getArbIdPadre() );
        //this.setI_are_id(est.getClModulo().getClArea().getAreId());
        if ( est.getClArbolAcademico() == null ) {
            this.setW_ocultar_modulo( "false" );
            this.setI_tipo_estructura( "068002" );
            this.setI_modulos( getI_modulos() );
            this.setI_cur_id( 0 );
            //System.out.println( "modulo nulo" );
        } else {
            this.setI_are_id( this.getB_are_id() );
            this.setI_mod_id( this.getB_mod_id() );
            // this.setI_modulos( getI_modulos() );
            //this.setI_cur_id( est.getClArbolAcademico().getArbIdPadre() );
            this.setI_tall_id( est.getClArbolAcademico().getArbId() );
            this.getCboCursosRelacionados();
            this.setIdCursoRelacionado( est.getClArbolAcademico().getArbId() );
            //System.out.println( "modulo ok" );
        }


        this.setI_detalle( new ArrayList<bEstructuraPago>() );
        this.setSug_concepto( "" );
        this.setI_conpag_id( 0 );
        this.setI_conpag_des( "" );
        //this.setI_tal_id(0);
        this.setI_estpagdet_des( "" );
        this.setEstpagdet_nomimp( "" );
        this.setI_estpagdet_monto( 0 );
        cont1 = 0;
        this.setIds_delete( new ArrayList<Integer>() );

        List<ClEstructuraPagosDetalle> lista = dao.seleccionarDetalle( est.getEstpagId() );
        if ( !lista.isEmpty() ) {
            for ( int i = 0; i < lista.size(); i++ ) {
                ClEstructuraPagosDetalle det = lista.get( i );
                bEstructuraPago e = new bEstructuraPago( i );
                e.setI_estpagdet_id( det.getEstpagdetId() );
                e.setI_conpag_id( det.getAdConceptoPago().getId() );
                e.setI_conpag_des( det.getAdConceptoPago().getConpagDescripcion() );
                //e.setI_tal_id(det.getTalId());
                //e.setI_det_taller(daoTaller.buscarTaller(det.getTalId()).getTalDescripcion());
                e.setI_estpagdet_des( det.getEstpagdetNombre() );
                e.setEstpagdet_nomimp( det.getEstpagdetNomImp() );
                e.setI_estpagdet_monto( det.getEstpagdetMonto() );
                e.setI_estpagdet_orden( det.getEstpagdetOrden() );
                e.setInArbCurRel( det.getClArbolAcadCurso() );
                e.setE_estpagdet_des( "" );
                e.setE_estpagdet_nomimp( "" );
                e.setE_estpagdet_monto( 0 );
                e.setE_estpagdet_orden( 1 );
                e.setDetalleEditar( false );
                e.setDetalleVer( true );

                this.getI_detalle().add( e );
            }
        }


        this.setOncomplete( "Richfaces.showModalPanel('mpUpdate')" );
        //System.out.println( "[actualizarEstructura] >> EXIT" );
    }

    public void guardarEstructura( ActionEvent event ) throws Exception {
        //System.out.println( "[guardarEstructura] << ENTER" );
        this.setOncomplete( "" );
        HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
        if ( this.getI_estpag_nombre().trim().length() > 0
                && this.getI_estpag_abrev().trim().length() > 0 ) {
            //if(this.getI_mod_id()>0 && this.getI_tipo_estructura().equals("068001")){

            //ClModulo mod = new ClModulo();
            //mod.setModId(this.getI_mod_id());
            ClArbolAcademico arbol = new ClArbolAcademico( this.getI_tall_id() );
            if ( this.getI_tipo_estructura().equals( "068002" ) ) {
                arbol = null;
            }

            if ( this.getI_estpag_id() > 0 ) {
                ClEstructuraPagos estructura = new ClEstructuraPagos();
                estructura.setEstpagId( this.getI_estpag_id() );
                //System.out.println("nombre estructura -> "+this.getI_estpag_nombre());
                estructura.setEstpagNombre( this.getI_estpag_nombre().trim().toUpperCase() );
                //System.out.println("nombre estructua 2 -> "+estructura.getEstpagNombre());
                estructura.setEstpagAbrev( this.getI_estpag_abrev().trim().toUpperCase() );
                estructura.setEstpagActivo( "1" );
                //estructura.setClModulo(mod);
                estructura.setEstpagTipo( this.getI_tipo_estructura() );
                estructura.setClArbolAcademico( arbol );

                if ( this.getI_detalle().size() > 0 ) {
                    for ( int i = 0; i < this.getI_detalle().size(); i++ ) {
                        if ( this.getI_detalle().get( i ).getI_estpagdet_id() > 0 ) {
                            ClEstructuraPagosDetalle det = new ClEstructuraPagosDetalle();
                            det.setEstpagdetId( this.getI_detalle().get( i ).getI_estpagdet_id() );
                            det.setEstpagdetNombre( this.getI_detalle().get( i ).getI_estpagdet_des().trim().toUpperCase() );
                            det.setEstpagdetNomImp( this.getI_detalle().get( i ).getEstpagdet_nomimp().trim().toUpperCase() );

                            AdConceptoPago conpag = new AdConceptoPago();
                            conpag.setId( this.getI_detalle().get( i ).getI_conpag_id() );
                            det.setAdConceptoPago( conpag );
                            det.setEstpagdetOrden( this.getI_detalle().get( i ).getI_estpagdet_orden() );
                            det.setEstpagdetMonto( this.getI_detalle().get( i ).getI_estpagdet_monto() );

                            if ( this.getI_detalle().get( i ).getInArbCurRel() != null && this.getI_detalle().get( i ).getInArbCurRel().getArbId() != null && this.getI_detalle().get( i ).getInArbCurRel().getArbId().intValue() > 0 ) {
                                det.setClArbolAcadCurso( new ClArbolAcademico( this.getI_detalle().get( i ).getInArbCurRel().getArbId() ) );
                            } else {
                                det.setClArbolAcadCurso( null );
                            }
                            det.setEstpagdetFechaPago( new Date() );
                            det.setClEstructuraPagos( estructura );
                            det.setEstpagdetActivo( "1" );
                            det.setEstpagdetVisible( "1" );
                            det.setTalId( 0 );

                            dao.actualizarEstructuraPagoDetalle( det );
                        } else {
                            ClEstructuraPagosDetalle det = new ClEstructuraPagosDetalle();
                            det.setEstpagdetNombre( this.getI_detalle().get( i ).getI_estpagdet_des().trim().toUpperCase() );
                            det.setEstpagdetNomImp( this.getI_detalle().get( i ).getEstpagdet_nomimp().trim().toUpperCase() );
                            AdConceptoPago conpag = new AdConceptoPago();
                            conpag.setId( this.getI_detalle().get( i ).getI_conpag_id() );
                            det.setAdConceptoPago( conpag );
                            det.setEstpagdetFechaPago( new Date() );
                            det.setEstpagdetMonto( this.getI_detalle().get( i ).getI_estpagdet_monto() );
                            det.setEstpagdetOrden( this.getI_detalle().get( i ).getI_estpagdet_orden() );
                            det.setClEstructuraPagos( estructura );
                            det.setEstpagdetActivo( "1" );
                            det.setEstpagdetVisible( "1" );
                            det.setTalId( 0 );
                            if ( this.getI_detalle().get( i ).getInArbCurRel() != null && this.getI_detalle().get( i ).getInArbCurRel().getArbId() != null && this.getI_detalle().get( i ).getInArbCurRel().getArbId().intValue() > 0 ) {
                                det.setClArbolAcadCurso( new ClArbolAcademico( this.getI_detalle().get( i ).getInArbCurRel().getArbId() ) );
                            } else {
                                det.setClArbolAcadCurso( null );
                            }
                            dao.insertarEstructuraPagoDetalle( det );
                        }
                    }
                }
                for ( int i = 0; i < this.getIds_delete().size(); i++ ) {
                    dao.eliminarEstructuraPagosDetalle( this.getIds_delete().get( i ) );
                }

                dao.actualizarEstructuraPagos( estructura );
                this.setOncomplete( "javascript:alert('Actualización Satisfactoria.');"
                        + "Richfaces.hideModalPanel('mpUpdate');" );
            } else {
                ClEstructuraPagos estructura = new ClEstructuraPagos();
                estructura.setEstpagNombre( this.getI_estpag_nombre().trim().toUpperCase() );
                estructura.setEstpagAbrev( this.getI_estpag_abrev().trim().toUpperCase() );
                estructura.setEstpagActivo( "1" );
                estructura.setClArbolAcademico( arbol );
                estructura.setEstpagTipo( this.getI_tipo_estructura() );

                Set<ClEstructuraPagosDetalle> sDetalle = new LinkedHashSet<ClEstructuraPagosDetalle>();
                if ( this.getI_detalle().size() > 0 ) {
                    for ( int i = 0; i < this.getI_detalle().size(); i++ ) {
                        ClEstructuraPagosDetalle det = new ClEstructuraPagosDetalle();
                        det.setEstpagdetNombre( this.getI_detalle().get( i ).getI_estpagdet_des().trim().toUpperCase() );
                        det.setEstpagdetNomImp( this.getI_detalle().get( i ).getEstpagdet_nomimp().trim().toUpperCase() );

                        AdConceptoPago conpag = new AdConceptoPago();
                        conpag.setId( this.getI_detalle().get( i ).getI_conpag_id() );
                        det.setAdConceptoPago( conpag );
                        det.setEstpagdetFechaPago( new Date() );
                        det.setEstpagdetOrden( this.getI_detalle().get( i ).getI_estpagdet_orden() );
                        det.setEstpagdetMonto( this.getI_detalle().get( i ).getI_estpagdet_monto() );
                        det.setClEstructuraPagos( estructura );
                        det.setEstpagdetActivo( "1" );
                        det.setEstpagdetVisible( "1" );
                        det.setTalId( 0 );
                        sDetalle.add( det );
                        if ( this.getI_detalle().get( i ).getInArbCurRel() != null && this.getI_detalle().get( i ).getInArbCurRel().getArbId() != null && this.getI_detalle().get( i ).getInArbCurRel().getArbId().intValue() > 0 ) {
                            det.setClArbolAcadCurso( new ClArbolAcademico( this.getI_detalle().get( i ).getInArbCurRel().getArbId() ) );
                        } else {
                            det.setClArbolAcadCurso( null );
                        }
                    }
                }
                estructura.setClEstructuraPagosDetalles( sDetalle );

                dao.insertarEstructuraPagos( estructura );
                this.setOncomplete( "javascript:alert('Registro Satisfactorio.');Richfaces.hideModalPanel('mpUpdate')" );
            }
            // fin de terminar
        } else {
            this.setOncomplete( "javascript:alert('Los datos de Estructura de Pagos son obligatorios.')" );
        }
        seleccionar();
        //System.out.println( "[guardarEstructura] >> EXIT" );
    }

    public void eliminarEstructura( ActionEvent event ) throws Exception {
        //System.out.println( "[eliminarEstructura] << ENTER" );
        this.setOncomplete( "" );
        int id_delete = Integer.valueOf( ( (UIParameter)event.getComponent().findComponent( "id_delete" ) ).getValue().toString() );
        HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
        if ( dao.verificarEliminar( id_delete ).size() > 0 ) {
            this.setOncomplete( "javascript:alert('Esta estructura ya ha sido utilizada y no puede eliminarse.')" );
        } else {
            dao.eliminarEstructuraPagos( id_delete );
        }
        //System.out.println( "[eliminarEstructura] >> EXIT" );
    }

    public String getCentroId() {
        //System.out.println( "[getCentroId] << ENTER" );
        // System.out.println( "[getCentroId] >> EXIT" );
        return sCentroId;
    }

    public void setCentroId( String sCentroId ) {
        //System.out.println( "[setCentroId] << ENTER" );
        this.sCentroId = sCentroId;
        //System.out.println( "[setCentroId] >> EXIT" );
    }

    public SelectItem[] getCboCentros() {
        //System.out.println( "[getCboCentros] << ENTER" );
        MetodosAdm metodo = new MetodosAdm();
        try {
            cboCentros = metodo.listarCentros( "[Seleccione]" );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        //System.out.println( "[getCboCentros] >> EXIT" );
        return cboCentros;
    }

    public void setCboCentros( SelectItem[] cboCentros ) {
        //System.out.println( "[setCboCentros] << ENTER" );
        this.cboCentros = cboCentros;
        //System.out.println( "[setCboCentros] >> EXIT" );
    }

    public SelectItem[] getAreas() throws Exception {
        //System.out.println( "[getAreas] << ENTER" );
        HSArbolAcademicoClDao clArbDAO;
        List<ClArbolAcademico> lstArbAreas;
        try {
            if ( sCentroId != null && !"0".equals( sCentroId ) ) {
                clArbDAO = CommonDAO.getClArbolAcademicoDAO();
                lstArbAreas = clArbDAO.AreasXInstitucion( sCentroId );
                if ( lstArbAreas.size() > 0 ) {
                    areas = new SelectItem[ lstArbAreas.size() + 1 ];
                    areas[0] = new SelectItem( 0, "[Seleccione]" );
                    for ( int i = 0; i < lstArbAreas.size(); i++ ) {
                        areas[i + 1] = new SelectItem( ( (ClArbolAcademico)lstArbAreas.get( i ) ).getArbId(), ( (ClArbolAcademico)lstArbAreas.get( i ) ).getArbDescripcion() );
                    }
                } else {
                    areas = new SelectItem[ 1 ];
                    areas[0] = new SelectItem( 0, "[Seleccione]" );
                }
            } else {
                areas = new SelectItem[ 1 ];
                areas[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        //System.out.println( "[getAreas] >> EXIT" );
        return areas;
    }

    public void setAreas( SelectItem[] areas ) {
        //System.out.println( "[setAreas] << ENTER" );
        this.areas = areas;
        //System.out.println( "[setAreas] >> EXIT" );
    }

    public int getB_are_id() {
        //System.out.println( "[getB_are_id] << ENTER" );
        //System.out.println( "[getB_are_id] >> EXIT" );
        return b_are_id;
    }

    public void setB_are_id( int b_are_id ) {
        //System.out.println( "[setB_are_id] << ENTER" );
        this.b_are_id = b_are_id;
        //System.out.println( "[setB_are_id] >> EXIT" );
    }

    //GETTERS AND SETTERS
    public int getB_mod_id() {
        //System.out.println( "[getB_mod_id] << ENTER" );
        //System.out.println( "[getB_mod_id] >> EXIT" );
        return b_mod_id;
    }

    public void setB_mod_id( int b_mod_id ) {
        //System.out.println( "[setB_mod_id] << ENTER" );
        this.b_mod_id = b_mod_id;
        //System.out.println( "[setB_mod_id] >> EXIT" );
    }

    public SelectItem[] getModulos() throws Exception {
        //System.out.println( "[getModulos] << ENTER" );
        if ( this.b_are_id == 0 ) {
            modulos = new SelectItem[ 1 ];
        } else {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> lista = dao.seleccionarModulos( this.b_are_id, "" );
            modulos = new SelectItem[ lista.size() + 1 ];

            for ( int i = 0; i < lista.size(); i++ ) {
                modulos[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
            }
        }
        modulos[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getModulos] >> EXIT" );
        return modulos;
    }

    public void setModulos( SelectItem[] modulos ) {
        //System.out.println( "[setModulos] << ENTER" );
        this.modulos = modulos;
        //System.out.println( "[setModulos] >> EXIT" );
    }

    public SelectItem[] getCursos() throws Exception {
        if ( this.b_mod_id == 0 ) {
            cursos = new SelectItem[ 1 ];
        } else {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> lista = dao.seleccionarModulos( this.b_mod_id, "" );
            cursos = new SelectItem[ lista.size() + 1 ];

            for ( int i = 0; i < lista.size(); i++ ) {
                cursos[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
            }
        }
        cursos[0] = new SelectItem( 0, "[Seleccione]" );
        return cursos;
    }

    public void setCursos( SelectItem[] cursos ) {
        this.cursos = cursos;
    }

    public int getB_cur_id() {
        return b_cur_id;
    }

    public void setB_cur_id( int b_cur_id ) {
        this.b_cur_id = b_cur_id;
    }

    public SelectItem[] getTalleres() throws Exception {
        if ( this.b_cur_id == 0 ) {
            talleres = new SelectItem[ 1 ];
        } else {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> lista = dao.seleccionarModulos( this.b_cur_id, "" );
            talleres = new SelectItem[ lista.size() + 1 ];
            for ( int i = 0; i < lista.size(); i++ ) {
                talleres[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
            }
        }
        talleres[0] = new SelectItem( 0, "[Seleccione]" );
        return talleres;
    }

    public void setTalleres( SelectItem[] talleres ) {
        this.talleres = talleres;
    }

    public int getB_tall_id() {
        return b_tall_id;
    }

    public void setB_tall_id( int b_tall_id ) {
        this.b_tall_id = b_tall_id;
    }

    public List<bEstructuraPago> getEstructuras() {
        //System.out.println( "[getEstructuras] << ENTER" );
        //System.out.println( "[getEstructuras] >> EXIT" );
        return estructuras;
    }

    public void setEstructuras( List<bEstructuraPago> estructuras ) {
        //System.out.println( "[setEstructuras] << ENTER" );
        this.estructuras = estructuras;
        //System.out.println( "[setEstructuras] >> EXIT" );
    }

    public String getEstpag_abrev() {
        //System.out.println( "[getEstpag_abrev] << ENTER" );
        //System.out.println( "[getEstpag_abrev] >> EXIT" );
        return estpag_abrev;
    }

    public void setEstpag_abrev( String estpag_abrev ) {
        //System.out.println( "[setEstpag_abrev] << ENTER" );
        this.estpag_abrev = estpag_abrev;
        //System.out.println( "[setEstpag_abrev] >> EXIT" );
    }

    public String getEstpag_activo() {
        //System.out.println( "[getEstpag_activo] << ENTER" );
        //System.out.println( "[getEstpag_activo] >> EXIT" );
        return estpag_activo;
    }

    public void setEstpag_activo( String estpag_activo ) {
        //System.out.println( "[setEstpag_activo] << ENTER" );
        this.estpag_activo = estpag_activo;
        //System.out.println( "[setEstpag_activo] >> EXIT" );
    }

    public int getEstpag_id() {
        //System.out.println( "[getEstpag_id] << ENTER" );
        //System.out.println( "[getEstpag_id] >> EXIT" );
        return estpag_id;
    }

    public void setEstpag_id( int estpag_id ) {
        //System.out.println( "[setEstpag_id] << ENTER" );
        this.estpag_id = estpag_id;
        //System.out.println( "[setEstpag_id] >> EXIT" );
    }

    public String getEstpag_nombre() {
        //System.out.println( "[getEstpag_nombre] << ENTER" );
        //System.out.println( "[getEstpag_nombre] >> EXIT" );
        return estpag_nombre;
    }

    public void setEstpag_nombre( String estpag_nombre ) {
        //System.out.println( "[setEstpag_nombre] << ENTER" );
        this.estpag_nombre = estpag_nombre;
        //System.out.println( "[setEstpag_nombre] >> EXIT" );
    }

    public int getMod_id() {
        // System.out.println( "[getMod_id] << ENTER" );
        //System.out.println( "[getMod_id] >> EXIT" );
        return mod_id;
    }

    public void setMod_id( int mod_id ) {
        //System.out.println( "[setMod_id] << ENTER" );
        this.mod_id = mod_id;
        //System.out.println( "[setMod_id] >> EXIT" );
    }

    public String getModulo() {
        //System.out.println( "[getModulo] << ENTER" );
        //System.out.println( "[getModulo] >> EXIT" );
        return modulo;
    }

    public void setModulo( String modulo ) {
        //System.out.println( "[setModulo] << ENTER" );
        this.modulo = modulo;
        //System.out.println( "[setModulo] >> EXIT" );
    }

    public String getB_nombre() {
        //System.out.println( "[getB_nombre] << ENTER" );
        //System.out.println( "[getB_nombre] >> EXIT" );
        return b_nombre;
    }

    public void setB_nombre( String b_nombre ) {
        //System.out.println( "[setB_nombre] << ENTER" );
        this.b_nombre = b_nombre;
        //System.out.println( "[setB_nombre] >> EXIT" );
    }

    public int getEstpagdet_id() {
        //System.out.println( "[getEstpagdet_id] << ENTER" );
        //System.out.println( "[getEstpagdet_id] >> EXIT" );
        return estpagdet_id;
    }

    public void setEstpagdet_id( int estpagdet_id ) {
        //System.out.println( "[setEstpagdet_id] << ENTER" );
        this.estpagdet_id = estpagdet_id;
        //System.out.println( "[setEstpagdet_id] >> EXIT" );
    }

    public String getEstpagdet_nombre() {
        // System.out.println( "[getEstpagdet_nombre] << ENTER" );
        //System.out.println( "[getEstpagdet_nombre] >> EXIT" );
        return estpagdet_nombre;
    }

    public void setEstpagdet_nombre( String estpagdet_nombre ) {
        //System.out.println( "[setEstpagdet_nombre] << ENTER" );
        this.estpagdet_nombre = estpagdet_nombre;
        //System.out.println( "[setEstpagdet_nombre] >> EXIT" );
    }

    public int getConpag_id() {
        //System.out.println( "[getConpag_id] << ENTER" );
        //System.out.println( "[getConpag_id] >> EXIT" );
        return conpag_id;
    }

    public void setConpag_id( int conpag_id ) {
        //System.out.println( "[setConpag_id] << ENTER" );
        this.conpag_id = conpag_id;
        //System.out.println( "[setConpag_id] >> EXIT" );
    }

    public String getConcepto() {
        //System.out.println( "[getConcepto] << ENTER" );
        //System.out.println( "[getConcepto] >> EXIT" );
        return concepto;
    }

    public void setConcepto( String concepto ) {
        //System.out.println( "[setConcepto] << ENTER" );
        this.concepto = concepto;
        //System.out.println( "[setConcepto] >> EXIT" );
    }

    public float getEstpagdet_monto() {
        //System.out.println( "[getEstpagdet_monto] << ENTER" );
        //System.out.println( "[getEstpagdet_monto] >> EXIT" );
        return estpagdet_monto;
    }

    public void setEstpagdet_monto( float estpagdet_monto ) {
        //System.out.println( "[setEstpagdet_monto] << ENTER" );
        this.estpagdet_monto = estpagdet_monto;
        //System.out.println( "[setEstpagdet_monto] >> EXIT" );
    }

    public int getD_estpag_id() {
        //System.out.println( "[getD_estpag_id] << ENTER" );
        //System.out.println( "[getD_estpag_id] >> EXIT" );
        return d_estpag_id;
    }

    public void setD_estpag_id( int d_estpag_id ) {
        //System.out.println( "[setD_estpag_id] << ENTER" );
        this.d_estpag_id = d_estpag_id;
        //System.out.println( "[setD_estpag_id] >> EXIT" );
    }

    public String getEstpagdet_activo() {
        //System.out.println( "[getEstpagdet_activo] << ENTER" );
        //System.out.println( "[getEstpagdet_activo] >> EXIT" );
        return estpagdet_activo;
    }

    public void setEstpagdet_activo( String estpagdet_activo ) {
        //System.out.println( "[setEstpagdet_activo] << ENTER" );
        this.estpagdet_activo = estpagdet_activo;
        //System.out.println( "[setEstpagdet_activo] >> EXIT" );
    }

    public int getTal_id() {
        //System.out.println( "[getTal_id] << ENTER" );
        //System.out.println( "[getTal_id] >> EXIT" );
        return tal_id;
    }

    public void setTal_id( int tal_id ) {
        //System.out.println( "[setTal_id] << ENTER" );
        this.tal_id = tal_id;
        //System.out.println( "[setTal_id] >> EXIT" );
    }

    public List<bEstructuraPago> getDetalle() {
        //System.out.println( "[getDetalle] << ENTER" );
        //System.out.println( "[getDetalle] >> EXIT" );
        return detalle;
    }

    public void setDetalle( List<bEstructuraPago> detalle ) {
        //System.out.println( "[setDetalle] << ENTER" );
        this.detalle = detalle;
        //System.out.println( "[setDetalle] >> EXIT" );
    }

    public boolean isVerDetalle() {
        //System.out.println( "[isVerDetalle] << ENTER" );
        //System.out.println( "[isVerDetalle] >> EXIT" );
        return verDetalle;
    }

    public void setVerDetalle( boolean verDetalle ) {
        //System.out.println( "[setVerDetalle] << ENTER" );
        this.verDetalle = verDetalle;
        //System.out.println( "[setVerDetalle] >> EXIT" );
    }

    public String getVerDetImagen() {
        //System.out.println( "[getVerDetImagen] << ENTER" );
        //System.out.println( "[getVerDetImagen] >> EXIT" );
        return verDetImagen;
    }

    public void setVerDetImagen( String verDetImagen ) {
        //System.out.println( "[setVerDetImagen] << ENTER" );
        this.verDetImagen = verDetImagen;
        //System.out.println( "[setVerDetImagen] >> EXIT" );
    }

    public String getVerDetTitulo() {
        //System.out.println( "[getVerDetTitulo] << ENTER" );
        //System.out.println( "[getVerDetTitulo] >> EXIT" );
        return verDetTitulo;
    }

    public void setVerDetTitulo( String verDetTitulo ) {
        //System.out.println( "[setVerDetTitulo] << ENTER" );
        this.verDetTitulo = verDetTitulo;
        //System.out.println( "[setVerDetTitulo] >> EXIT" );
    }

    public String getTaller() {
        //System.out.println( "[getTaller] << ENTER" );
        //System.out.println( "[getTaller] >> EXIT" );
        return taller;
    }

    public void setTaller( String taller ) {
        //System.out.println( "[setTaller] << ENTER" );
        this.taller = taller;
        //System.out.println( "[setTaller] >> EXIT" );
    }

    public int getI_estpag_id() {
        //System.out.println( "[getI_estpag_id] << ENTER" );
        //System.out.println( "[getI_estpag_id] >> EXIT" );
        return i_estpag_id;
    }

    public void setI_estpag_id( int i_estpag_id ) {
        //System.out.println( "[setI_estpag_id] << ENTER" );
        this.i_estpag_id = i_estpag_id;
        //System.out.println( "[setI_estpag_id] >> EXIT" );
    }

    public String getI_estpag_nombre() {
        //System.out.println( "[getI_estpag_nombre] << ENTER" );
        //System.out.println( "[getI_estpag_nombre] >> EXIT" );
        return i_estpag_nombre;
    }

    public void setI_estpag_nombre( String i_estpag_nombre ) {
        //System.out.println( "[setI_estpag_nombre] << ENTER" );
        this.i_estpag_nombre = i_estpag_nombre;
        //System.out.println( "[setI_estpag_nombre] >> EXIT" );
    }

    public String getI_estpag_abrev() {
        //System.out.println( "[getI_estpag_abrev] << ENTER" );
        //System.out.println( "[getI_estpag_abrev] >> EXIT" );
        return i_estpag_abrev;
    }

    public void setI_estpag_abrev( String i_estpag_abrev ) {
        //System.out.println( "[setI_estpag_abrev] << ENTER" );
        this.i_estpag_abrev = i_estpag_abrev;
        //System.out.println( "[setI_estpag_abrev] >> EXIT" );
    }

    public int getI_are_id() {
        //System.out.println( "[getI_are_id] << ENTER" );
        //System.out.println( "[getI_are_id] >> EXIT" );
        return i_are_id;
    }

    public void setI_are_id( int i_are_id ) {
        //System.out.println( "[setI_are_id] << ENTER" );
        this.i_are_id = i_are_id;
        //System.out.println( "[setI_are_id] >> EXIT" );
    }

    public SelectItem[] getI_areas() throws Exception {
        //System.out.println( "[getI_areas] << ENTER" );
        if ( i_areas == null ) {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> t_areas = dao.seleccionarArea( "" );
            i_areas = new SelectItem[ t_areas.size() + 1 ];
            i_areas[0] = new SelectItem( 0, "[Seleccione]" );
            for ( int i = 0; i < t_areas.size(); i++ ) {
                ClArbolAcademico a = t_areas.get( i );
                i_areas[i + 1] = new SelectItem( a.getArbId(), a.getArbDescripcion() );
            }
        }
        //System.out.println( "[getI_areas] >> EXIT" );
        return i_areas;
    }

    public void setI_areas( SelectItem[] i_areas ) {
        //System.out.println( "[setI_areas] << ENTER" );
        this.i_areas = i_areas;
        //System.out.println( "[setI_areas] >> EXIT" );
    }

    public int getI_mod_id() {
        //System.out.println( "[getI_mod_id] << ENTER" );
        // System.out.println( "[getI_mod_id] >> EXIT" );
        return i_mod_id;
    }

    public void setI_mod_id( int i_mod_id ) {
        //System.out.println( "[setI_mod_id] << ENTER" );
        this.i_mod_id = i_mod_id;
        //System.out.println( "[setI_mod_id] >> EXIT" );
    }

    public SelectItem[] getI_modulos() throws Exception {
        //System.out.println( "[getI_modulos] << ENTER" );
        if ( this.i_are_id == 0 ) {
            i_modulos = new SelectItem[ 1 ];
        } else {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> lista = dao.seleccionarModulos( this.i_are_id, "" );
            i_modulos = new SelectItem[ lista.size() + 1 ];

            for ( int i = 0; i < lista.size(); i++ ) {
                i_modulos[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
            }
        }
        i_modulos[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getI_modulos] >> EXIT" );
        return i_modulos;
    }

    public void setI_modulos( SelectItem[] i_modulos ) {
        //System.out.println( "[setI_modulos] << ENTER" );
        this.i_modulos = i_modulos;
        //System.out.println( "[setI_modulos] >> EXIT" );
    }

    public SelectItem[] getI_cursos() throws Exception {
        if ( this.i_mod_id == 0 ) {
            i_cursos = new SelectItem[ 1 ];
        } else {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> lista = dao.seleccionarModulos( this.i_mod_id, "" );
            i_cursos = new SelectItem[ lista.size() + 1 ];

            for ( int i = 0; i < lista.size(); i++ ) {
                i_cursos[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
            }
        }
        i_cursos[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getI_modulos] >> EXIT" );
        return i_cursos;
    }

    public void setI_cursos( SelectItem[] i_cursos ) {
        this.i_cursos = i_cursos;
    }

    public int getI_cur_id() {
        return i_cur_id;
    }

    public void setI_cur_id( int i_cur_id ) {
        this.i_cur_id = i_cur_id;
    }

    public SelectItem[] getI_talleres() throws Exception {
        if ( this.i_cur_id == 0 ) {
            i_talleres = new SelectItem[ 1 ];
        } else {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> lista = dao.seleccionarModulos( this.i_cur_id, "" );
            i_talleres = new SelectItem[ lista.size() + 1 ];

            for ( int i = 0; i < lista.size(); i++ ) {
                i_talleres[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
            }
        }
        i_talleres[0] = new SelectItem( 0, "[Seleccione]" );
        //System.out.println( "[getI_modulos] >> EXIT" );
        return i_talleres;
    }

    public void setI_talleres( SelectItem[] i_talleres ) {
        this.i_talleres = i_talleres;
    }

    public int getI_tall_id() {
        return i_tall_id;
    }

    public void setI_tall_id( int i_tall_id ) {
        this.i_tall_id = i_tall_id;
    }

    public String getOncomplete() {
        //System.out.println( "[getOncomplete] << ENTER" );
        //System.out.println( "[getOncomplete] >> EXIT" );
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        //System.out.println( "[setOncomplete] << ENTER" );
        this.oncomplete = oncomplete;
        //System.out.println( "[setOncomplete] >> EXIT" );
    }

    public List<bEstructuraPago> getI_detalle() {
        //System.out.println( "[getI_detalle] << ENTER" );
        //System.out.println( "[getI_detalle] >> EXIT" );
        return i_detalle;
    }

    public void setI_detalle( List<bEstructuraPago> i_detalle ) {
        //System.out.println( "[setI_detalle] << ENTER" );
        this.i_detalle = i_detalle;
        //System.out.println( "[setI_detalle] >> EXIT" );
    }

    public int getI_estpagdet_id() {
        //System.out.println( "[getI_estpagdet_id] << ENTER" );
        //System.out.println( "[getI_estpagdet_id] >> EXIT" );
        return i_estpagdet_id;
    }

    public void setI_estpagdet_id( int i_estpagdet_id ) {
        //System.out.println( "[setI_estpagdet_id] << ENTER" );
        this.i_estpagdet_id = i_estpagdet_id;
        //System.out.println( "[setI_estpagdet_id] >> EXIT" );
    }

    public String getSug_concepto() {
        //System.out.println( "[getSug_concepto] << ENTER" );
        //System.out.println( "[getSug_concepto] >> EXIT" );
        return sug_concepto;
    }

    public void setSug_concepto( String sug_concepto ) {
        //System.out.println( "[setSug_concepto] << ENTER" );
        this.sug_concepto = sug_concepto;
        //System.out.println( "[setSug_concepto] >> EXIT" );
    }

    public int getI_conpag_id() {
        //System.out.println( "[getI_conpag_id] << ENTER" );
        //System.out.println( "[getI_conpag_id] >> EXIT" );
        return i_conpag_id;
    }

    public void setI_conpag_id( int i_conpag_id ) {
        //System.out.println( "[setI_conpag_id] << ENTER" );
        this.i_conpag_id = i_conpag_id;
        //System.out.println( "[setI_conpag_id] >> EXIT" );
    }

    public float getI_estpagdet_monto() {
        //System.out.println( "[getI_estpagdet_monto] << ENTER" );
        // System.out.println( "[getI_estpagdet_monto] >> EXIT" );
        return i_estpagdet_monto;
    }

    public void setI_estpagdet_monto( float i_estpagdet_monto ) {
        //System.out.println( "[setI_estpagdet_monto] << ENTER" );
        this.i_estpagdet_monto = i_estpagdet_monto;
        //System.out.println( "[setI_estpagdet_monto] >> EXIT" );
    }

    public int getId_estpag_id() {
        //System.out.println( "[getId_estpag_id] << ENTER" );
        //System.out.println( "[getId_estpag_id] >> EXIT" );
        return id_estpag_id;
    }

    public void setId_estpag_id( int id_estpag_id ) {
        //System.out.println( "[setId_estpag_id] << ENTER" );
        this.id_estpag_id = id_estpag_id;
        //System.out.println( "[setId_estpag_id] >> EXIT" );
    }

    public int getAu_conpag_id() {
        //System.out.println( "[getAu_conpag_id] << ENTER" );
        //System.out.println( "[getAu_conpag_id] >> EXIT" );
        return au_conpag_id;
    }

    public void setAu_conpag_id( int au_conpag_id ) {
        //System.out.println( "[setAu_conpag_id] << ENTER" );
        this.au_conpag_id = au_conpag_id;
        //System.out.println( "[setAu_conpag_id] >> EXIT" );
    }

    public String getAu_concepto() {
        //System.out.println( "[getAu_concepto] << ENTER" );
        //System.out.println( "[getAu_concepto] >> EXIT" );
        return au_concepto;
    }

    public void setAu_concepto( String au_concepto ) {
        //System.out.println( "[setAu_concepto] << ENTER" );
        this.au_concepto = au_concepto;
        //System.out.println( "[setAu_concepto] >> EXIT" );
    }

    public String getI_conpag_des() {
        //System.out.println( "[getI_conpag_des] << ENTER" );
        //System.out.println( "[getI_conpag_des] >> EXIT" );
        return i_conpag_des;
    }

    public void setI_conpag_des( String i_conpag_des ) {
        //System.out.println( "[setI_conpag_des] << ENTER" );
        this.i_conpag_des = i_conpag_des;
        //System.out.println( "[setI_conpag_des] >> EXIT" );
    }

    public String getI_estpagdet_des() {
        //System.out.println( "[getI_estpagdet_des] << ENTER" );
        try {
        } catch ( Exception e ) {
            return "";
        }
        //System.out.println( "[getI_estpagdet_des] >> EXIT" );
        return i_estpagdet_des;
    }

    public void setI_estpagdet_des( String i_estpagdet_des ) {
        //System.out.println( "[setI_estpagdet_des] << ENTER" );
        this.i_estpagdet_des = i_estpagdet_des;
        //System.out.println( "[setI_estpagdet_des] >> EXIT" );
    }

    public String getEstpagdet_nomimp() {
        // System.out.println( "[getEstpagdet_nombre] << ENTER" );
        //System.out.println( "[getEstpagdet_nombre] >> EXIT" );
        try {
        } catch ( Exception e ) {
            return "";
        }
        return estpagdet_nomimp;
    }

    public void setEstpagdet_nomimp( String estpagdet_nomimp ) {
        //System.out.println( "[setEstpagdet_nombre] << ENTER" );
        this.estpagdet_nomimp = estpagdet_nomimp;
        //System.out.println( "[setEstpagdet_nombre] >> EXIT" );
    }

    public String getE_estpagdet_des() {
        //System.out.println( "[getE_estpagdet_des] << ENTER" );
        //System.out.println( "[getE_estpagdet_des] >> EXIT" );
        return e_estpagdet_des;
    }

    public void setE_estpagdet_des( String e_estpagdet_des ) {
        //System.out.println( "[setE_estpagdet_des] << ENTER" );
        this.e_estpagdet_des = e_estpagdet_des;
        //System.out.println( "[setE_estpagdet_des] >> EXIT" );
    }

    public String getE_estpagdet_nomimp() {
        //System.out.println( "[getE_estpagdet_des] << ENTER" );
        //System.out.println( "[getE_estpagdet_des] >> EXIT" );
        return e_estpagdet_nomimp;
    }

    public void setE_estpagdet_nomimp( String e_estpagdet_nomimp ) {
        //System.out.println( "[setE_estpagdet_des] << ENTER" );
        this.e_estpagdet_nomimp = e_estpagdet_nomimp;
        //System.out.println( "[setE_estpagdet_des] >> EXIT" );
    }

    public float getE_estpagdet_monto() {
        //System.out.println( "[getE_estpagdet_monto] << ENTER" );
        //System.out.println( "[getE_estpagdet_monto] >> EXIT" );
        return e_estpagdet_monto;
    }

    public void setE_estpagdet_monto( float e_estpagdet_monto ) {
        //System.out.println( "[setE_estpagdet_monto] << ENTER" );
        this.e_estpagdet_monto = e_estpagdet_monto;
        //System.out.println( "[setE_estpagdet_monto] >> EXIT" );
    }

    public boolean isDetalleVer() {
        //System.out.println( "[isDetalleVer] << ENTER" );
        //System.out.println( "[isDetalleVer] >> EXIT" );
        return detalleVer;
    }

    public void setDetalleVer( boolean detalleVer ) {
        //System.out.println( "[setDetalleVer] << ENTER" );
        this.detalleVer = detalleVer;
        //System.out.println( "[setDetalleVer] >> EXIT" );
    }

    public boolean isDetalleEditar() {
        //System.out.println( "[isDetalleEditar] << ENTER" );
        //System.out.println( "[isDetalleEditar] >> EXIT" );
        return detalleEditar;
    }

    public void setDetalleEditar( boolean detalleEditar ) {
        //System.out.println( "[setDetalleEditar] << ENTER" );
        this.detalleEditar = detalleEditar;
        //System.out.println( "[setDetalleEditar] >> EXIT" );
    }

    public List<Integer> getIds_delete() {
        //System.out.println( "[getIds_delete] << ENTER" );
        //System.out.println( "[getIds_delete] >> EXIT" );
        return ids_delete;
    }

    public void setIds_delete( List<Integer> ids_delete ) {
        //System.out.println( "[setIds_delete] << ENTER" );
        this.ids_delete = ids_delete;
        //System.out.println( "[setIds_delete] >> EXIT" );
    }

    public boolean isVerEliminarDetalle() {
        //System.out.println( "[isVerEliminarDetalle] << ENTER" );
        //System.out.println( "[isVerEliminarDetalle] >> EXIT" );
        return verEliminarDetalle;
    }

    public void setVerEliminarDetalle( boolean verEliminarDetalle ) {
        //System.out.println( "[setVerEliminarDetalle] << ENTER" );
        this.verEliminarDetalle = verEliminarDetalle;
        //System.out.println( "[setVerEliminarDetalle] >> EXIT" );
    }

    public void definirTipo( ActionEvent event ) {
        //System.out.println( "[definirTipo] << ENTER" );
        if ( this.getI_tipo_estructura().equals( "068001" ) ) {
            this.setW_ocultar_modulo( "true" );
            //System.out.println( "entro al propio" );

        } else {
            this.setW_ocultar_modulo( "false" );
            //System.out.println( "entro al libre" );
        }
        // System.out.println( "[definirTipo] >> EXIT" );
    }

    public void prepararEnlaceEstPagDet( ActionEvent event ) {
        oncomplete = "Richfaces.showModalPanel('mpEnlaceEstPagDet');";
    }
}
