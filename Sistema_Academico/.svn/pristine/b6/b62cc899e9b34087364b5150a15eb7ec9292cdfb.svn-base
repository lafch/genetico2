/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.cursoLibre.hibernateSpringDao.HSSistemaEvaluacionCLDAO;
import net.uch.mapping.ClNota;
import net.uch.mapping.ClSisEvaDetalle;
import net.uch.mapping.ClSisEvaPersonalizado;
import net.uch.mapping.ClSisEvaluacion;
import net.uch.util.CommonDAO;

/**
 *
 * @author cesardl
 */
public class BeanClSisEvaPersonalizado {

    private int id;
    private int id_siseva;
    private int pos;
    private String nombre;
    private int talape_id;
    private String codigo;
    private double peso;
    private String exa_semana;
    private int orden;
    private String alu_tipo;
    private SelectItem[] detalles;
    private String detallen;
    private int detalle;
    private String editable = "false";
    private String view = "true";
    private boolean editable_bool = false;
    private boolean view_bool = true;
    private boolean visible = false;
    private boolean edit_active = false;
    //Temporales
    private int aux_detalle;
    private String aux_codigo;
    private String aux_nombre;
    private double aux_peso;
    private String aux_exa_semana;
    private int aux_orden;

    public BeanClSisEvaPersonalizado() {
        this.alu_tipo = "014003";
    }

    public BeanClSisEvaPersonalizado( int id_siseva ) {
        this.setId_siseva( id_siseva );
        this.alu_tipo = "014003";
    }

    public BeanClSisEvaPersonalizado( ClSisEvaPersonalizado siseva_per, int pos ) {
        this.setId( siseva_per.getSisevaPerId() );
        this.setCodigo( siseva_per.getSisevaCodigo() );
        this.setNombre( siseva_per.getSisevaPerNombre() );
        this.setOrden( siseva_per.getSisevaPerOrden() );
        this.setPeso( siseva_per.getSisevaPerPeso() );
        this.setExa_semana( siseva_per.getSisevaPerExaSemana() );
        this.setDetalle( siseva_per.getClSisEvaDetalle().getSisevaDetalleId() );
        this.setDetallen( siseva_per.getClSisEvaDetalle().getSisevaDetalleNombre() );
        this.setId_siseva( siseva_per.getClSisEvaDetalle().getClSisEvaluacion().getSisevaId() );
        this.initDetalles();
        //this.setTalape_id(siseva_per.getClTallerAperturado().getTalapeId());
        this.setPos( pos );
    }

    public void limpiar() {
        this.setCodigo( "" );
        this.setNombre( "" );
        this.setPeso( 0 );
        this.setExa_semana( "" );
        this.setOrden( 0 );
        this.setDetalle( 0 );
    }

    public int getPos() {
        return pos;
    }

    public void setPos( int pos ) {
        this.pos = pos;
    }

    public int getDetalle() {
        return detalle;
    }

    public void setDetalle( int detalle ) {
        this.detalle = detalle;
    }

    public String getDetallen() {
        return detallen;
    }

    public void setDetallen( String detallen ) {
        this.detallen = detallen;
    }

    public String getNombre_Detalle( int det_id ) {
        for ( int i = 0; i < detalles.length; i++ ) {
            SelectItem se = detalles[i];
            int value = Integer.parseInt( se.getValue().toString() );
            if ( value == det_id ) {
                return se.getLabel();
            }
        }
        return "---";
    }

    private void initDetalles() {
        Iterator<ClSisEvaDetalle> it;
        Iterator<ClSisEvaDetalle> itAux;
        try {
            HSSistemaEvaluacionCLDAO dao = CommonDAO.getClSistemaEvaluacionDAO();
//                    (HSSistemaEvaluacionCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLSistemaEvaluacion");
            ClSisEvaluacion siseva = dao.seleccionarSistemaEvaluacion( this.getId_siseva() );


            it = siseva.getClSisEvaDetalles().iterator();
            itAux = siseva.getClSisEvaDetalles().iterator();

            int i = 0;
            while ( it.hasNext() ) {
                ClSisEvaDetalle siseva_det = it.next();
                if ( "1".equals( siseva_det.getSisevaDetalleTipo() ) ) {
                    i++;
                }
            }
            detalles = new SelectItem[ i + 1 ];
            i = 0;
            while ( itAux.hasNext() ) {
                ClSisEvaDetalle siseva_det = itAux.next();
                if ( "1".equals( siseva_det.getSisevaDetalleTipo() ) ) {
                    detalles[i + 1] = new SelectItem( siseva_det.getSisevaDetalleId(), siseva_det.getSisevaDetalleNombre() );
                    i++;
                }
            }
        } catch ( Exception e ) {
            detalles = new SelectItem[ 1 ];
        } finally {
            detalles[0] = new SelectItem( "0", "[Seleccione]" );
        }
    }

    public SelectItem[] getDetalles() {
        if ( detalles == null ) {
            initDetalles();
        }
        return detalles;
    }

    public void setDetalles( SelectItem[] detalles ) {
        this.detalles = detalles;
    }

    public int getId_siseva() {
        return id_siseva;
    }

    public void setId_siseva( int id_siseva ) {
        this.id_siseva = id_siseva;
    }

    public String getAlu_tipo() {
        return alu_tipo;
    }

    public void setAlu_tipo( String alu_tipo ) {
        this.alu_tipo = alu_tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo( String codigo ) {
        this.codigo = codigo;
    }

    public String getExa_semana() {
        return exa_semana;
    }

    public void setExa_semana( String exa_semana ) {
        this.exa_semana = exa_semana;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden( int orden ) {
        this.orden = orden;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso( double peso ) {
        this.peso = peso;
    }

    public int getTalape_id() {
        return talape_id;
    }

    public void setTalape_id( int talape_id ) {
        this.talape_id = talape_id;
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

    public int getAux_detalle() {
        return aux_detalle;
    }

    public void setAux_detalle( int aux_detalle ) {
        this.aux_detalle = aux_detalle;
    }

    public String getAux_codigo() {
        return aux_codigo;
    }

    public void setAux_codigo( String aux_codigo ) {
        this.aux_codigo = aux_codigo;
    }

    public String getAux_nombre() {
        return aux_nombre;
    }

    public void setAux_nombre( String aux_nombre ) {
        this.aux_nombre = aux_nombre;
    }

    public int getAux_orden() {
        return aux_orden;
    }

    public void setAux_orden( int aux_orden ) {
        this.aux_orden = aux_orden;
    }

    public double getAux_peso() {
        return aux_peso;
    }

    public void setAux_peso( double aux_peso ) {
        this.aux_peso = aux_peso;
    }

    public String getAux_exa_semana() {
        return aux_exa_semana;
    }

    public void setAux_exa_semana( String aux_exa_semana ) {
        this.aux_exa_semana = aux_exa_semana;
    }

    public ClSisEvaPersonalizado nuevoSisEvaPer( BeanClSisEvaPersonalizado tmp ) {
        ClSisEvaPersonalizado siseva_per = new ClSisEvaPersonalizado();

        siseva_per.setSisevaPerNombre( tmp.getNombre() );
        siseva_per.setSisevaPerExaSemana( tmp.getExa_semana() );
        siseva_per.setClSisEvaDetalle( new ClSisEvaDetalle( tmp.getDetalle() ) );
        //siseva_per.setClTallerAperturado(new ClTallerAperturado(tmp.getTalape_id()));
        siseva_per.setSisevaCodigo( tmp.getCodigo() );
        siseva_per.setSisevaPerPeso( 1d );
        siseva_per.setSisevaPerOrden( tmp.getOrden() );
        siseva_per.setSisevaPerActivo( "1" );
        siseva_per.setClNotas( new LinkedHashSet<ClNota>() );

        return siseva_per;
    }

    public void edit( ActionEvent event ) {
        this.setAux_detalle( this.detalle );
        this.setAux_codigo( this.codigo );
        this.setAux_nombre( this.nombre );
        this.setAux_peso( this.peso );
        this.setAux_exa_semana( this.exa_semana );
        this.setAux_orden( this.orden );

        this.setView( "false" );
        this.setEditable( "true" );
        this.setView_bool( false );
        this.setEditable_bool( true );
        this.setVisible( true );
        this.setEdit_active( true );
    }

    public void aceptar( ActionEvent event ) {
        if ( this.detalle != 0 ) {
            this.setDetallen( getNombre_Detalle( this.detalle ) );

            this.setView( "true" );
            this.setEditable( "false" );
            this.setView_bool( true );
            this.setEditable_bool( false );
            this.setVisible( false );
            this.setEdit_active( false );
        }
    }

    public void cancelar( ActionEvent event ) {
        this.setDetalle( this.aux_detalle );
        this.setCodigo( this.aux_codigo );
        this.setNombre( this.aux_nombre );
        this.setPeso( this.aux_peso );
        this.setExa_semana( this.aux_exa_semana );
        this.setOrden( this.aux_orden );

        this.setView( "true" );
        this.setEditable( "false" );
        this.setView_bool( true );
        this.setEditable_bool( false );
        this.setVisible( false );
        this.setEdit_active( false );
    }

    @Override
    public String toString() {
        return "\tid: " + this.getId() + "\n"
                + "\tid_siseva: " + this.getId_siseva() + "\n"
                + "\ti_siseva_det: " + this.getDetalle() + "\n"
                + "\ti_talape_id: " + this.getTalape_id() + "\n"
                + "\tcodigo: " + this.getCodigo() + "\n"
                + "\texa_semana: " + this.getExa_semana() + "\n"
                + "\tnombre: " + this.getNombre() + "\n"
                + "\torden: " + this.getOrden() + "\n"
                + "\tpeso: " + this.getPeso() + "\n";
    }
}
