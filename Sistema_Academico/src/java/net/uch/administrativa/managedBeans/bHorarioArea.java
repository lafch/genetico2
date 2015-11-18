/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.administrativa.managedBeans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author richard
 */
public class bHorarioArea {

    private int b_are_id;
    private String b_are_nombre;
    private List<bHorarioArea> listarHorarioArea = new ArrayList<bHorarioArea>();

    public int getB_are_id() {
        return b_are_id;
    }

    public void setB_are_id(int b_are_id) {
        this.b_are_id = b_are_id;
    }

    public String getB_are_nombre() {
        return b_are_nombre;
    }

    public void setB_are_nombre(String b_are_nombre) {
        this.b_are_nombre = b_are_nombre;
    }

    public List<bHorarioArea> getListarHorarioArea() {
        return listarHorarioArea;
    }

    public void setListarHorarioArea(List<bHorarioArea> listarHorarioArea) {
        this.listarHorarioArea = listarHorarioArea;
    }
}
