/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.uch.mapping.ClSisEvaDetalle;
import net.uch.mapping.ClSisEvaluacion;

/**
 *
 * @author cesardl
 */
public class BeanClSisEvaluacion extends ClSisEvaluacion {

    private boolean sisevaBoolVigente;
    private String sisevaImgVigente;
    private List<BeanClSisEvaDetalle> siseva_detalles;
    private boolean verDetalle;
    private String m_imagen_mostrar;
    private String m_texto_mostrar;

    public BeanClSisEvaluacion() {
        this.setSisevaId(new Integer(0));
    }

    public BeanClSisEvaluacion(ClSisEvaluacion siseva) {
        this.setSisevaId(siseva.getSisevaId());
        this.setSisevaActivo(siseva.getSisevaActivo());
        this.setSisevaCodigo(siseva.getSisevaCodigo());
        this.setSisevaCreacion(siseva.getSisevaCreacion());
        this.setSisevaId(siseva.getSisevaId());
        this.setSisevaNombre(siseva.getSisevaNombre());
        this.setSisevaVigente(siseva.getSisevaVigente());
        if (siseva.getSisevaVigente().equalsIgnoreCase("1")) {
            this.setSisevaImgVigente("/Imagenes/actions/activar.png");
            this.setSisevaBoolVigente(true);
        } else {
            this.setSisevaImgVigente("/Imagenes/actions/desactivar.png");
            this.setSisevaBoolVigente(false);
        }
        Iterator<ClSisEvaDetalle> it = siseva.getClSisEvaDetalles().iterator();
        this.siseva_detalles = new ArrayList<BeanClSisEvaDetalle>();
        while (it.hasNext()) {
            ClSisEvaDetalle siseva_det = it.next();
            this.siseva_detalles.add(new BeanClSisEvaDetalle(siseva_det));
        }
        this.setM_imagen_mostrar("/Imagenes/actions/down.png");
    }

    public ClSisEvaluacion getClSisEvaluacion() {
        ClSisEvaluacion siseva = new ClSisEvaluacion();

        siseva.setSisevaId(this.getSisevaId());
        siseva.setSisevaCodigo(this.getSisevaCodigo().toUpperCase());
        siseva.setSisevaNombre(this.getSisevaNombre().toUpperCase());
        if (this.isSisevaBoolVigente()) {
            siseva.setSisevaVigente("1");
        } else {
            siseva.setSisevaVigente("0");
        }
        siseva.setSisevaActivo("1");
        siseva.setSisevaCreacion(new Date());
        return siseva;
    }

    public boolean esValido() {
        return this.getSisevaCodigo().trim().length() != 0
                && this.getSisevaNombre().trim().length() != 0;
    }

    public boolean isSisevaBoolVigente() {
        return sisevaBoolVigente;
    }

    public void setSisevaBoolVigente(boolean sisevaBoolVigente) {
        this.sisevaBoolVigente = sisevaBoolVigente;
    }

    public String getSisevaImgVigente() {
        return sisevaImgVigente;
    }

    public void setSisevaImgVigente(String sisevaImgVigente) {
        this.sisevaImgVigente = sisevaImgVigente;
    }

    public List<BeanClSisEvaDetalle> getSiseva_detalles() {
        return siseva_detalles;
    }

    public void setSiseva_detalles(List<BeanClSisEvaDetalle> siseva_detalles) {
        this.siseva_detalles = siseva_detalles;
    }

    public String getM_imagen_mostrar() {
        return m_imagen_mostrar;
    }

    public void setM_imagen_mostrar(String m_imagen_mostrar) {
        this.m_imagen_mostrar = m_imagen_mostrar;
    }

    public String getM_texto_mostrar() {
        return m_texto_mostrar;
    }

    public void setM_texto_mostrar(String m_texto_mostrar) {
        this.m_texto_mostrar = m_texto_mostrar;
    }

    public boolean isVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle(boolean verDetalle) {
        this.verDetalle = verDetalle;
    }
}
