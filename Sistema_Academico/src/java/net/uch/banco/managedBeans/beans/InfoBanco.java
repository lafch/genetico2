/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.banco.managedBeans.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cesardl
 */
public class InfoBanco {

    private Cabecera cabecera;
    private List<Detalle> detalles;
    private List<Concepto> conceptos;

    public InfoBanco() {
        cabecera = new Cabecera();
        detalles = new ArrayList<Detalle>();
        conceptos = new ArrayList<Concepto>();
    }

    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    public List<Concepto> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<Concepto> conceptos) {
        this.conceptos = conceptos;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return cabecera
                + "\n\tHay " + detalles.size() + " detalles\n" + detalles
                + "\n\tHay " + conceptos.size() + " conceptos\n" + conceptos;
    }
}
