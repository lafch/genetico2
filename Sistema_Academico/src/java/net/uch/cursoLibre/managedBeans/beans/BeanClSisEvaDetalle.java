/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.LinkedHashSet;
import javax.faces.event.ActionEvent;
import net.uch.mapping.ClSisEvaDetalle;

/**
 *
 * @author cesardl
 */
public class BeanClSisEvaDetalle extends ClSisEvaDetalle {

    private int posicion;
    private boolean sisevaDetalleBoolSusti;
    private String sisevaDetalleDescSusti;
    //Para edicion
    private String editable = "false";
    private String view = "true";
    private boolean editable_bool = false;
    private boolean view_bool = true;
    private boolean visible = false;
    private boolean edit_active = false;
    //Temporales
    private String aux_codigo;
    private String aux_nombre;
    private double aux_peso;
    private boolean aux_susti;

    public BeanClSisEvaDetalle() {
        this.setSisevaDetalleId(new Integer(0));
        this.setSisevaDetalleBoolSusti(false);
    }

    public BeanClSisEvaDetalle(ClSisEvaDetalle siseva_det) {
        this.setSisevaDetalleId(siseva_det.getSisevaDetalleId());
        this.setSisevaDetalleActivo(siseva_det.getSisevaDetalleActivo());
        this.setSisevaDetalleCodigo(siseva_det.getSisevaDetalleCodigo());
        this.setSisevaDetalleId(siseva_det.getSisevaDetalleId());
        this.setSisevaDetalleNombre(siseva_det.getSisevaDetalleNombre());
        this.setSisevaDetallePeso(siseva_det.getSisevaDetallePeso());
        this.setSisevaDetalleSusti(siseva_det.getSisevaDetalleSusti());
        if (siseva_det.getSisevaDetalleSusti().equalsIgnoreCase("1")) {
            this.setSisevaDetalleBoolSusti(true);
            this.setSisevaDetalleDescSusti("SI");
        } else {
            this.setSisevaDetalleBoolSusti(false);
            this.setSisevaDetalleDescSusti("NO");
        }
    }

    public ClSisEvaDetalle getClSisEvaDetalle() {
        ClSisEvaDetalle siseva_det = new ClSisEvaDetalle();

        siseva_det.setSisevaDetalleId(this.getSisevaDetalleId());
        siseva_det.setSisevaDetalleCodigo(this.getSisevaDetalleCodigo().toUpperCase());
        siseva_det.setSisevaDetalleNombre(this.getSisevaDetalleNombre().toUpperCase());
        siseva_det.setSisevaDetallePeso(this.getSisevaDetallePeso());
        siseva_det.setSisevaDetalleSusti(this.getSisevaDetalleSusti());
        siseva_det.setSisevaDetalleActivo("1");
        return siseva_det;
    }

    public boolean esValido() {
        try {
            return this.getSisevaDetalleCodigo().trim().length() != 0
                    && this.getSisevaDetalleNombre().trim().length() != 0
                    && this.getSisevaDetallePeso().intValue() != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void completarDatos() {
        this.setSisevaDetalleCodigo(this.getSisevaDetalleCodigo().toUpperCase());
        this.setSisevaDetalleNombre(this.getSisevaDetalleNombre().toUpperCase());
        if (this.isSisevaDetalleBoolSusti()) {
            this.setSisevaDetalleSusti("1");
            this.setSisevaDetalleDescSusti("SI");
        } else {
            this.setSisevaDetalleSusti("0");
            this.setSisevaDetalleDescSusti("NO");
        }
        this.setClSisEvaPersonalizados(new LinkedHashSet());
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean isSisevaDetalleBoolSusti() {
        return sisevaDetalleBoolSusti;
    }

    public void setSisevaDetalleBoolSusti(boolean sisevaDetalleBoolSusti) {
        this.sisevaDetalleBoolSusti = sisevaDetalleBoolSusti;
    }

    public String getSisevaDetalleDescSusti() {
        return sisevaDetalleDescSusti;
    }

    public void setSisevaDetalleDescSusti(String sisevaDetalleDescSusti) {
        this.sisevaDetalleDescSusti = sisevaDetalleDescSusti;
    }

    public boolean isEdit_active() {
        return edit_active;
    }

    public void setEdit_active(boolean edit_active) {
        this.edit_active = edit_active;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public boolean isEditable_bool() {
        return editable_bool;
    }

    public void setEditable_bool(boolean editable_bool) {
        this.editable_bool = editable_bool;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isView_bool() {
        return view_bool;
    }

    public void setView_bool(boolean view_bool) {
        this.view_bool = view_bool;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getAux_codigo() {
        return aux_codigo;
    }

    public void setAux_codigo(String aux_codigo) {
        this.aux_codigo = aux_codigo;
    }

    public String getAux_nombre() {
        return aux_nombre;
    }

    public void setAux_nombre(String aux_nombre) {
        this.aux_nombre = aux_nombre;
    }

    public double getAux_peso() {
        return aux_peso;
    }

    public void setAux_peso(double aux_peso) {
        this.aux_peso = aux_peso;
    }

    public boolean isAux_susti() {
        return aux_susti;
    }

    public void setAux_susti(boolean aux_susti) {
        this.aux_susti = aux_susti;
    }

    public void edit(ActionEvent event) {
        this.setAux_codigo(this.getSisevaDetalleCodigo());
        this.setAux_nombre(this.getSisevaDetalleNombre());
        this.setAux_peso(this.getSisevaDetallePeso());
        this.setAux_susti(this.getSisevaDetalleSusti().equalsIgnoreCase("1") ? true : false);

        this.setView("false");
        this.setEditable("true");
        this.setView_bool(false);
        this.setEditable_bool(true);
        this.setVisible(true);
        this.setEdit_active(true);
    }

    public void aceptar(ActionEvent event) {
        this.setSisevaDetalleDescSusti(this.sisevaDetalleBoolSusti ? "SI" : "NO");
        this.setSisevaDetalleSusti(this.sisevaDetalleBoolSusti ? "1" : "0");

        this.setView("true");
        this.setEditable("false");
        this.setView_bool(true);
        this.setEditable_bool(false);
        this.setVisible(false);
        this.setEdit_active(false);
    }

    public void cancelar(ActionEvent event) {
        this.setSisevaDetalleCodigo(this.aux_codigo);
        this.setSisevaDetalleNombre(this.aux_nombre);
        this.setSisevaDetallePeso(this.aux_peso);
        this.setSisevaDetalleSusti(this.aux_susti ? "1" : "0");

        this.setView("true");
        this.setEditable("false");
        this.setView_bool(true);
        this.setEditable_bool(false);
        this.setVisible(false);
        this.setEdit_active(false);
    }
}
