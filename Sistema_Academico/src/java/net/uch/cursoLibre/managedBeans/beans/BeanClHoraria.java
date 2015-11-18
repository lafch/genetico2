/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.*;
import net.uch.metodo.local.MetodosAca;
import net.uch.tablaSistema.hibernateSpringDao.HSAulaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.util.CommonDAO;

/**
 *
 * @author cesardl
 */
public class BeanClHoraria extends ClHoraria {

    private boolean blSeleccionado;

    @Override
    public AcDocente getAcDocente() {
        return super.getAcDocente();
    }
    private int posicion;
    private int sec_id;
    private SelectItem[] aulas;
    private SelectItem[] profesores;
    private SelectItem[] dias;
    private SelectItem[] tipoClase;
    private String inicio_h;
    private String inicio_m;
    private String fin_h;
    private String fin_m;
    private String meridiano_inicio;
    private String meridiano_fin;
    private String v_hor_dia;
    private String v_hor_tipo_clase;
    private SelectItem[] meridianos;
    private String editable = "false";
    private String view = "true";
    private boolean editable_bool = false;
    private boolean view_bool = true;
    private boolean visible = false;
    private boolean edit_active = false;
    private List<String> lstDiasCod;
    //Temporales
    public int aux_aula;
    public int aux_docente;
    public String aux_dia;
    public String aux_tipo_clase;
    public String[] aux_fecha_ini;
    public String[] aux_fecha_fin;
    private String sOncomplete;
    private ClSisEvaDetalle clSisEvaDetDocente;

    /*
     * variables para la sede
     */
    private SelectItem[] cboSede;
    private SelectItem[] cboPabellon;
    private int w_sed_id;
    private int w_pab_id;
    private SelectItem[] cboSede_s;
    private SelectItem[] cboPabellon_s;
    private SelectItem[] cboAula_s;
    private int w_sed_id_s;
    private int w_pab_id_s;
    private int w_sed_id_sBak;
    private int w_pab_id_sBak;
    private int w_aul_id_sBak;
    /*
     * variables para la sede
     */
    MetodosAca metodo = new MetodosAca();
    /**
     * ENLACE ASIGNATURA - DOCENTE PARA PROGRAMA DE AUXILIARES
     */
    private SelectItem[] cboSisEvaDet;

    public SelectItem[] getCboSisEvaDet() {
        int iSize;
        Object[] objObj;
        List lstSisEvaDet;
        cboSisEvaDet = new SelectItem[ 1 ];
        if ( this.sec_id != 0 ) {
            try {
                lstSisEvaDet = CommonDAO.getClSistemaEvaluacionDAO().listarSisEvaDetallePorSecId( this.sec_id );
                iSize = lstSisEvaDet.size();
                cboSisEvaDet = new SelectItem[ iSize + 1 ];
                for ( int i = 0; i < iSize; i++ ) {
                    objObj = (Object[]) lstSisEvaDet.get( i );
                    cboSisEvaDet[i + 1] = new SelectItem( objObj[0], objObj[2].toString() );
                }
            } catch ( Exception ex ) {
                cboSisEvaDet = new SelectItem[ 1 ];
                ex.printStackTrace();
            }
        }
        cboSisEvaDet[0] = new SelectItem( "0", "[Seleccione]" );
        return cboSisEvaDet;
    }

    public void setCboSisEvaDet( SelectItem[] cboSisEvaDet ) {
        this.cboSisEvaDet = cboSisEvaDet;
    }

    public List<String> getLstDiasCod() {
        return lstDiasCod;
    }

    public void setLstDiasCod( List<String> lstDiasCod ) {
        this.lstDiasCod = lstDiasCod;
    }

    public int getW_aul_id_sBak() {
        return w_aul_id_sBak;
    }

    public void setW_aul_id_sBak( int w_aul_id_sBak ) {
        this.w_aul_id_sBak = w_aul_id_sBak;
    }

    public int getW_pab_id_sBak() {
        return w_pab_id_sBak;
    }

    public void setW_pab_id_sBak( int w_pab_id_sBak ) {
        this.w_pab_id_sBak = w_pab_id_sBak;
    }

    public int getW_sed_id_sBak() {
        return w_sed_id_sBak;
    }

    public void setW_sed_id_sBak( int w_sed_id_sBak ) {
        this.w_sed_id_sBak = w_sed_id_sBak;
    }

    public SelectItem[] getCboAula_s() {
        cboAula_s = metodo.listarAula( w_pab_id_s, "Seleccione" );
        return cboAula_s;
    }

    public void setCboAula_s( SelectItem[] cboAula_s ) {
        this.cboAula_s = cboAula_s;
    }

    public SelectItem[] getCboPabellon_s() throws SQLException {
        cboPabellon_s = metodo.listarPabellon( w_sed_id_s, "Seleccione" );
        return cboPabellon_s;
    }

    public void setCboPabellon_s( SelectItem[] cboPabellon_s ) {
        this.cboPabellon_s = cboPabellon_s;
    }

    public SelectItem[] getCboSede_s() throws SQLException {
        cboSede_s = metodo.listarSedes( "Seleccione" );
        return cboSede_s;
    }

    public void setCboSede_s( SelectItem[] cboSede_s ) {
        this.cboSede_s = cboSede_s;
    }

    public int getW_pab_id_s() {
        return w_pab_id_s;
    }

    public void setW_pab_id_s( int w_pab_id_s ) {
        this.w_pab_id_s = w_pab_id_s;
    }

    public int getW_sed_id_s() {
        return w_sed_id_s;
    }

    public void setW_sed_id_s( int w_sed_id_s ) {
        this.w_sed_id_s = w_sed_id_s;
    }

    public SelectItem[] getCboPabellon() throws SQLException {
        System.out.println( "sed_id: " + this.getW_sed_id() );
        cboPabellon = metodo.listarPabellon( this.getW_sed_id(), "Seleccione" );
        return cboPabellon;
    }

    public void setCboPabellon( SelectItem[] cboPabellon ) {
        this.cboPabellon = cboPabellon;
    }

    public SelectItem[] getCboSede() throws SQLException {
        cboSede = metodo.listarSedes( "Seleccione" );
        return cboSede;
    }

    public void setCboSede( SelectItem[] cboSede ) {
        this.cboSede = cboSede;
    }

    public int getW_pab_id() {
        return w_pab_id;
    }

    public void setW_pab_id( int w_pab_id ) {
        this.w_pab_id = w_pab_id;
    }

    public int getW_sed_id() {
        return w_sed_id;
    }

    public void setW_sed_id( int w_sed_id ) {
        this.w_sed_id = w_sed_id;
    }

    public BeanClHoraria( Integer secId ) {
        this.setSec_id( secId );
        this.setAulas( getAulas() );
        this.setProfesores( getProfesores() );
        this.setDias( getDias() );
        this.setHorDia( "000000" );
        this.setTipoClase( getTipoClase() );
        this.setHorTipoClase( "000000" );
        this.setInicio_h( "00" );
        this.setInicio_m( "00" );
        this.setFin_h( "00" );
        this.setFin_m( "00" );
        this.setW_sed_id( 1 );
        this.setMeridiano_inicio( "AM" );
        this.setMeridiano_fin( "AM" );

        this.setAcAula( new AcAula() );
        this.setAcDocente( new AcDocente() );
        this.setClSeccion( new ClSeccion( secId ) );
    }

    public BeanClHoraria( ClHoraria horaria ) {
        this.setSec_id( horaria.getClSeccion().getSecId() );
        this.setHorId( horaria.getHorId() );

        this.setHorDia( horaria.getHorDia() );
        this.setHorTipoClase( horaria.getHorTipoClase() );

        this.setHorHini( horaria.getHorHini() );
        this.setHorHfin( horaria.getHorHfin() );

        this.setHorActivo( horaria.getHorActivo() );
        this.setAcAula( horaria.getAcAula() );
        // System.out.println("ver el alula "+horaria.getAcAula().getAulDes());
        this.setAcDocente( horaria.getAcDocente() );

        this.setW_pab_id_s( this.getAcAula().getPab().getId() );
        this.setW_sed_id_s( this.getAcAula().getPab().getLoc().getId() );
    }

    public ClHoraria getClHoraria() {
        ClHoraria horaria = new ClHoraria();
        horaria.setHorId( this.getHorId() );
        horaria.setAcAula( this.getAcAula() );
        horaria.setAcDocente( this.getAcDocente() );
        horaria.setClSeccion( new ClSeccion( this.sec_id ) );
        horaria.setHorDia( this.getHorDia() );
        horaria.setHorHini( this.getHorHini() );
        horaria.setHorHfin( this.getHorHfin() );
        horaria.setHorTipoClase( this.getHorTipoClase() );
        horaria.setHorActivo( this.getHorActivo() );
        horaria.setClSisEvaDetDocente( this.getClSisEvaDetDocente().getSisevaDetalleId() == null || this.getClSisEvaDetDocente().getSisevaDetalleId().intValue() == 0 ? null : this.getClSisEvaDetDocente() );

        return horaria;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion( int posicion ) {
        this.posicion = posicion;
    }

    public int getSec_id() {
        return sec_id;
    }

    public void setSec_id( int sec_id ) {
        this.sec_id = sec_id;
    }

    public String getV_hor_dia() {
        return v_hor_dia;
    }

    public void setV_hor_dia( String v_hor_dia ) {
        this.v_hor_dia = v_hor_dia;
    }

    public String getV_hor_tipo_clase() {
        return v_hor_tipo_clase;
    }

    public void setV_hor_tipo_clase( String v_hor_tipo_clase ) {
        this.v_hor_tipo_clase = v_hor_tipo_clase;
    }

    public SelectItem[] getMeridianos() {
        meridianos = new SelectItem[ 2 ];
        meridianos[0] = new SelectItem( "AM", "A.M." );
        meridianos[1] = new SelectItem( "PM", "P.M." );
        return meridianos;
    }

    public void setMeridianos( SelectItem[] meridianos ) {
        this.meridianos = meridianos;
    }

    public SelectItem[] getAulas() {
        aulas = metodo.listarAula( this.getW_pab_id(), "Seleccione" );
        return aulas;
    }

    public void setAulas( SelectItem[] aulas ) {
        this.aulas = aulas;
    }

    public SelectItem[] getProfesores() {
        if ( profesores == null ) {
            try {
                HSDocenteDAO dao = (HSDocenteDAO) ServiceFinder.findBean( "SpringHibernateDaoDocente" );
                List<AcDocente> docentes = dao.seleccionarDocente();
                profesores = new SelectItem[ docentes.size() + 1 ];
                for ( int i = 0; i < docentes.size(); i++ ) {
                    profesores[i + 1] = new SelectItem( docentes.get( i ).getId(), docentes.get( i ).getDocNombre() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los docentes " + e.getMessage() );
                profesores = new SelectItem[ 1 ];
            } finally {
                profesores[0] = new SelectItem( 0, "[Seleccione]" );
            }
        }
        return profesores;
    }

    public void setProfesores( SelectItem[] profesores ) {
        this.profesores = profesores;
    }

    public SelectItem[] getDias() {
        if ( dias == null ) {
            try {
                HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                List<TbCatalogo> lista = dao.seleccionarCatalogo( "016" );
                dias = new SelectItem[ lista.size() + 1 ];
                dias[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < lista.size(); i++ ) {
                    dias[i + 1] = new SelectItem( lista.get( i ).getCatCodigoGrupo() + lista.get( i ).getCatCodigoElemento(), lista.get( i ).getCatDescripcionElemento() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los dias " + e.getMessage() );
                dias = new SelectItem[ 1 ];
                dias[0] = new SelectItem( "000000", "[Seleccione]" );
            }
        }
        return dias;
    }

    public void setDias( SelectItem[] dias ) {
        this.dias = dias;
    }

    public SelectItem[] getTipoClase() {
        if ( tipoClase == null ) {
            try {
                HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                List<TbCatalogo> lista = dao.seleccionarCatalogo( "018" );
                tipoClase = new SelectItem[ lista.size() + 1 ];
                tipoClase[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < lista.size(); i++ ) {
                    tipoClase[i + 1] = new SelectItem( lista.get( i ).getCatCodigoGrupo() + lista.get( i ).getCatCodigoElemento(), lista.get( i ).getCatDescripcionElemento() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los tipos de clase " + e.getMessage() );
                tipoClase = new SelectItem[ 1 ];
                tipoClase[0] = new SelectItem( "000000", "[Seleccione]" );
            }
        }
        return tipoClase;
    }

    public void setTipoClase( SelectItem[] tipoClase ) {
        this.tipoClase = tipoClase;
    }

    public String getInicio_h() {
        return inicio_h;
    }

    public void setInicio_h( String inicio_h ) {
        this.inicio_h = inicio_h;
    }

    public String getInicio_m() {
        return inicio_m;
    }

    public void setInicio_m( String inicio_m ) {
        this.inicio_m = inicio_m;
    }

    public String getFin_h() {
        return fin_h;
    }

    public void setFin_h( String fin_h ) {
        this.fin_h = fin_h;
    }

    public String getFin_m() {
        return fin_m;
    }

    public void setFin_m( String fin_m ) {
        this.fin_m = fin_m;
    }

    public String getMeridiano_fin() {
        return meridiano_fin;
    }

    public void setMeridiano_fin( String meridiano_fin ) {
        this.meridiano_fin = meridiano_fin;
    }

    public String getMeridiano_inicio() {
        return meridiano_inicio;
    }

    public void setMeridiano_inicio( String meridiano_inicio ) {
        this.meridiano_inicio = meridiano_inicio;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable( String editable ) {
        this.editable = editable;
    }

    public boolean isEditable_bool() {
        return editable_bool;
    }

    public void setEditable_bool( boolean editable_bool ) {
        this.editable_bool = editable_bool;
    }

    public String getView() {
        return view;
    }

    public void setView( String view ) {
        this.view = view;
    }

    public boolean isView_bool() {
        return view_bool;
    }

    public void setView_bool( boolean view_bool ) {
        this.view_bool = view_bool;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    public boolean isEdit_active() {
        return edit_active;
    }

    public void setEdit_active( boolean edit_active ) {
        this.edit_active = edit_active;
    }

    public int getAux_aula() {
        return aux_aula;
    }

    public void setAux_aula( int aux_aula ) {
        this.aux_aula = aux_aula;
    }

    public String getAux_dia() {
        return aux_dia;
    }

    public void setAux_dia( String aux_dia ) {
        this.aux_dia = aux_dia;
    }

    public int getAux_docente() {
        return aux_docente;
    }

    public void setAux_docente( int aux_docente ) {
        this.aux_docente = aux_docente;
    }

    public String getAux_tipo_clase() {
        return aux_tipo_clase;
    }

    public void setAux_tipo_clase( String aux_tipo_clase ) {
        this.aux_tipo_clase = aux_tipo_clase;
    }

    public String[] getAux_fecha_ini() {
        if ( aux_fecha_ini == null ) {
            aux_fecha_ini = new String[ 2 ];
            aux_fecha_ini[0] = "";
            aux_fecha_ini[1] = "";
            aux_fecha_ini[2] = "";
        }
        return aux_fecha_ini;
    }

    public void setAux_fecha_ini( String[] aux_fecha_ini ) {
        this.aux_fecha_ini = aux_fecha_ini;
    }

    public String[] getAux_fecha_fin() {
        if ( aux_fecha_fin == null ) {
            aux_fecha_fin = new String[ 2 ];
            aux_fecha_fin[0] = "";
            aux_fecha_fin[1] = "";
            aux_fecha_fin[2] = "";
        }
        return aux_fecha_fin;
    }

    public void setAux_fecha_fin( String[] aux_fecha_fin ) {
        this.aux_fecha_fin = aux_fecha_fin;
    }

    public SelectItem[] getLstSeDias() {
        SelectItem[] lstSeDias;
        int iCatSize;
        TbCatalogo catDia;
        List<TbCatalogo> lstCatDias;

        lstCatDias = CommonDAO.getTbCatalogoDAO().seleccionarGrupo( "016" );
        iCatSize = lstCatDias.size();
        lstSeDias = new SelectItem[ iCatSize ];
        for ( int i = 0; i < iCatSize; i++ ) {
            catDia = lstCatDias.get( i );
            lstSeDias[i] = new SelectItem( catDia.getCatCodigoGrupo() + catDia.getCatCodigoElemento(), catDia.getCatDescripcionElemento() );
        }
        return lstSeDias;
    }

    public void edit( ActionEvent event ) {
        this.setAux_fecha_ini( convertirTimeString( this.getHorHini() ) );
        this.setAux_fecha_fin( convertirTimeString( this.getHorHfin() ) );
        this.setAux_aula( this.getAcAula().getId().intValue() );
        this.setAux_docente( this.getAcDocente().getId().intValue() );
        this.setAux_dia( this.getHorDia() );
        this.setAux_tipo_clase( this.getHorTipoClase() );
        this.setW_pab_id_sBak( this.getAcAula().getPab().getId().intValue() );
        this.setW_aul_id_sBak( this.getAcAula().getId().intValue() );
        this.setW_sed_id_sBak( this.getAcAula().getPab().getLoc().getId().intValue() );
        this.setInicio_h( aux_fecha_ini[0] );
        this.setInicio_m( aux_fecha_ini[1] );
        this.setMeridiano_inicio( aux_fecha_ini[2] );
        this.setFin_h( aux_fecha_fin[0] );
        this.setFin_m( aux_fecha_fin[1] );
        this.setMeridiano_fin( aux_fecha_fin[2] );

        this.setView( "false" );
        this.setEditable( "true" );
        this.setView_bool( false );
        this.setEditable_bool( true );
        this.setVisible( true );
        this.setEdit_active( true );
    }

    public void aceptar( ActionEvent event ) {
        String sMsg;
        sMsg = this.esValido();
        if ( sMsg.isEmpty() ) {
            this.actualizarDatos( this.sec_id );

            this.setView( "true" );
            this.setEditable( "false" );
            this.setView_bool( true );
            this.setEditable_bool( false );
            this.setVisible( false );
            this.setEdit_active( false );
            sOncomplete = "";
        } else {
            sOncomplete = "alert('" + sMsg + "')";
        }
    }

    public void cancelar( ActionEvent event ) {

        this.setW_pab_id_s( w_pab_id_sBak );
        this.setW_sed_id_s( w_sed_id_sBak );
        this.getAcAula().setId( w_aul_id_sBak );
        this.setHorHini( convertirFechaTime( aux_fecha_ini[0], aux_fecha_ini[1], aux_fecha_ini[2] ) );
        this.setHorHfin( convertirFechaTime( aux_fecha_fin[0], aux_fecha_fin[1], aux_fecha_fin[2] ) );
        this.getAcAula().setId( getAux_aula() );
        this.getAcDocente().setId( getAux_docente() );
        this.setHorDia( getAux_dia() );
        this.setHorTipoClase( getAux_tipo_clase() );

        
        this.setView( "true" );
        this.setEditable( "false" );
        this.setView_bool( true );
        this.setEditable_bool( false );
        this.setVisible( false );
        this.setEdit_active( false );
    }

//    public boolean esValido() {
//        return ((lstDiasCod == null || !lstDiasCod.isEmpty())
//                && !(getHorTipoClase().equalsIgnoreCase( "000000" ))
//                && (convertirFechaTime( this.inicio_h, this.inicio_m, meridiano_inicio ).getTime()
//                < convertirFechaTime( this.fin_h, fin_m, meridiano_fin ).getTime())
//                && getAcAula().getId().intValue() != 0
//                && getAcDocente().getId().intValue() != 0);
//    }
    public String esValido() {
        String sMsg = "";
        if ( getHorTipoClase().equalsIgnoreCase( "000000" ) ) {
            sMsg = "Seleccionar el Tipo de Clase";
        } else if ( convertirFechaTime( this.inicio_h, this.inicio_m, meridiano_inicio ).getTime()
                >= convertirFechaTime( this.fin_h, fin_m, meridiano_fin ).getTime() ) {
            sMsg = "La hora de inicio no debe ser mayor a la hora de fin";
        } else if ( getAcAula().getId().intValue() == 0 ) {
            sMsg = "Seleccionar Área";
        } else if ( getAcDocente().getId().intValue() == 0 ) {
            sMsg = "Seleccionar Docente";
        } else if ( lstDiasCod != null && lstDiasCod.isEmpty() ) {
            sMsg = "Seleccionar Día(s).";
        }
        return sMsg;
    }

    public String[] convertirTimeString( Time time ) {
        String[] fecha = new String[ 3 ];
        SimpleDateFormat sdf = new SimpleDateFormat( "hh:mm a" );
        Date fec = new Date( time.getTime() );
        String var = sdf.format( fec );
        fecha[0] = var.substring( 0, 2 );
        fecha[1] = var.substring( 3, 5 );
        fecha[2] = var.substring( 6, 8 );
        return fecha;
    }

    public Time convertirFechaTime( String hora, String minuto, String meridiano ) {
        try {
            Date fecha;
            String fec = hora.trim() + ":" + minuto.trim() + " " + meridiano.trim();
            SimpleDateFormat sdf = new SimpleDateFormat( "hh:mm a" );
            fecha = sdf.parse( fec );

            Time time = null;
            time = new Time( fecha.getTime() );

            return time;
        } catch ( Exception e ) {
            System.err.println( "Hubo un problema en la conversion a Time: " + e );
        }
        return null;
    }

    public void actualizarDatos( int secId ) {
        HSDocenteDAO daoD = (HSDocenteDAO) ServiceFinder.findBean( "SpringHibernateDaoDocente" );
        HSAulaDAO daoA = (HSAulaDAO) ServiceFinder.findBean( "SpringHibernateDaoAula" );
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );

        this.setClSeccion( new ClSeccion( secId ) );
        this.setAcDocente( daoD.seleccionarDocente( this.getAcDocente().getId() ) );
        this.setAcAula( daoA.buscarAula( this.getAcAula().getId() ) );
        this.setHorHini( convertirFechaTime( this.inicio_h, this.inicio_m, this.meridiano_inicio ) );
        this.setHorHfin( convertirFechaTime( this.fin_h, this.fin_m, this.meridiano_fin ) );
        this.setHorActivo( "1" );
//        this.setV_hor_dia( daoC.seleccionarDescripcion( this.getHorDia() ) );
        this.setV_hor_tipo_clase( daoC.seleccionarDescripcion( this.getHorTipoClase() ) );
    }

    @Override
    public String toString() {
        return "id " + this.getHorId()
                + "\tini " + this.getHorHini()
                + "\tfin " + this.getHorHfin()
                + "\taula " + this.getAcAula().getId() + " " + this.getAcAula().getAulDes()
                + "\tdocente " + this.getAcDocente().getId() + " " + this.getAcDocente().getDocNombre()
                + "\tdia_d " + this.getV_hor_dia() + " " + this.getHorDia()
                + "\tt_clase_d " + this.getV_hor_tipo_clase() + " " + this.getHorTipoClase();
    }

    public boolean isSeleccionado() {
        return this.blSeleccionado;
    }

    public void setSeleccionado( boolean blSeleccionado ) {
        this.blSeleccionado = blSeleccionado;
    }

    public String getOncomplete() {
        return sOncomplete;
    }

    public void setOncomplete( String sOncomplete ) {
        this.sOncomplete = sOncomplete;
    }

    @Override
    public ClSisEvaDetalle getClSisEvaDetDocente() {
        if ( clSisEvaDetDocente != null && clSisEvaDetDocente.getSisevaDetalleId() != null && clSisEvaDetDocente.getSisevaDetalleId().intValue() != 0) {
            clSisEvaDetDocente = CommonDAO.getClSistemaEvaluacionDAO().seleccionarSisEvaDetetallePorId( clSisEvaDetDocente.getSisevaDetalleId() );
        }
        return clSisEvaDetDocente == null ? new ClSisEvaDetalle() : clSisEvaDetDocente;
    }

    @Override
    public void setClSisEvaDetDocente( ClSisEvaDetalle clSisEvaDetDocente ) {
        this.clSisEvaDetDocente = clSisEvaDetDocente;
    }
}
