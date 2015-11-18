/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans.beans;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcHorarioDispDocente;
import net.uch.mapping.ClHoraria;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSisEvaDetalle;
import net.uch.mapping.TbCatalogo;
import net.uch.metodo.local.MetodosAca;
import net.uch.tablaSistema.hibernateSpringDao.HSAulaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.util.CommonDAO;

/**
 *
 * @author LUIS
 */
public class BeanHorarioDispDoc extends AcHorarioDispDocente {

    private boolean blSeleccionado;

    @Override
    public AcDocente getAcDocente() {
        return super.getAcDocente();
    }
    private int posicion;
    private int doc_id;
    private SelectItem[] profesores;
    private SelectItem[] dias;
    private String inicio_h;
    private String inicio_m;
    private String fin_h;
    private String fin_m;
    private String meridiano_inicio;
    private String meridiano_fin;
    private String v_hor_dia;
    private SelectItem[] meridianos;
    private String editable = "false";
    private String view = "true";
    private boolean editable_bool = false;
    private boolean view_bool = true;
    private boolean visible = false;
    private boolean edit_active = false;
    private List<String> lstDiasCod;
    //Temporales
    public int aux_docente;
    public String aux_dia;
    public String[] aux_fecha_ini;
    public String[] aux_fecha_fin;
    private String sOncomplete;
    /*
     * variables para la sede
     */
    MetodosAca metodo = new MetodosAca();
    /**
     * ENLACE ASIGNATURA - DOCENTE PARA PROGRAMA DE AUXILIARES
     */
    private SelectItem[] cboSisEvaDet;

    public List<String> getLstDiasCod() {
        return lstDiasCod;
    }

    public void setLstDiasCod( List<String> lstDiasCod ) {
        this.lstDiasCod = lstDiasCod;
    }

    public BeanHorarioDispDoc( Integer docId ) {
        this.setDoc_id( docId );

        this.setHorDia( "000000" );

        this.setInicio_h( "00" );
        this.setInicio_m( "00" );
        this.setFin_h( "00" );
        this.setFin_m( "00" );

        this.setMeridiano_inicio( "AM" );
        this.setMeridiano_fin( "AM" );


        this.setAcDocente( new AcDocente() );

    }

    public BeanHorarioDispDoc( AcHorarioDispDocente horaria ) {
        this.setDoc_id( horaria.getAcDocente().getId());
        this.setHorId( horaria.getHorId() );
        this.setHorDia( horaria.getHorDia() );
        this.setHorActivo( horaria.getHorActivo() );
        this.setAcDocente( horaria.getAcDocente() );
    }

    public AcHorarioDispDocente getAcHorarioDispDocente() {
        AcHorarioDispDocente horaria = new AcHorarioDispDocente();
        horaria.setHorId( this.getHorId() );
        
        horaria.setAcDocente( new AcDocente( this.doc_id ) );
        horaria.setHorDia( this.getHorDia() );
        horaria.setHorActivo( this.getHorActivo() );
        return horaria;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion( int posicion ) {
        this.posicion = posicion;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id( int doc_id ) {
        this.doc_id = doc_id;
    }

    public String getV_hor_dia() {
        return v_hor_dia;
    }

    public void setV_hor_dia( String v_hor_dia ) {
        this.v_hor_dia = v_hor_dia;
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

    public SelectItem[] getProfesores() {
        if ( profesores == null ) {
            try {
                HSDocenteDAO dao = (HSDocenteDAO)ServiceFinder.findBean( "SpringHibernateDaoDocente" );
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
                HSCatalogoDAO dao = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
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
        this.setAux_docente( this.getAcDocente().getId().intValue() );
        this.setAux_dia( this.getHorDia() );
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
            this.actualizarDatos( this.doc_id );

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

        this.getAcDocente().setId( getAux_docente() );
        this.setHorDia( getAux_dia() );
        this.setView( "true" );
        this.setEditable( "false" );
        this.setView_bool( true );
        this.setEditable_bool( false );
        this.setVisible( false );
        this.setEdit_active( false );
    }


    public String esValido() {
        String sMsg = "";
        if ( convertirFechaTime( this.inicio_h, this.inicio_m, meridiano_inicio ).getTime()
                >= convertirFechaTime( this.fin_h, fin_m, meridiano_fin ).getTime() ) {
            sMsg = "La hora de inicio no debe ser mayor a la hora de fin";
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

    public void actualizarDatos( int docId ) {
        HSDocenteDAO daoD = (HSDocenteDAO)ServiceFinder.findBean( "SpringHibernateDaoDocente" );
        HSAulaDAO daoA = (HSAulaDAO)ServiceFinder.findBean( "SpringHibernateDaoAula" );
        HSCatalogoDAO daoC = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        this.setAcDocente( daoD.seleccionarDocente( docId ) );
        this.setHorActivo( "1" );
//        this.setV_hor_dia( daoC.seleccionarDescripcion( this.getHorDia() ) );
    }

    @Override
    public String toString() {
        return "id " + this.getHorId()
                + "\tdocente " + this.getAcDocente().getId() + " " + this.getAcDocente().getDocNombre()
                + "\tdia_d " + this.getV_hor_dia() + " " + this.getHorDia();
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

}
