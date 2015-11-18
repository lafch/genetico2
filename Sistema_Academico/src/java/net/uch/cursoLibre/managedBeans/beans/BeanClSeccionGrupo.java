/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.Date;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class BeanClSeccionGrupo {

    private boolean m_bVerDetalle;
    private Integer m_iSecGrupId;
    private String m_sNomSecGrupo;
    private String m_sImagenDetalle;
    private String m_sTituloDetalle;
    private Date m_FechaInicio;
    private Date m_FechaFin;
    private List<BeanClSeccion> m_lstSecciones;

    public String getImagenDetalle() {
        return m_sImagenDetalle;
    }

    public void setImagenDetalle( String imagenDetalle ) {
        this.m_sImagenDetalle = imagenDetalle;
    }

    public List<BeanClSeccion> getLstSecciones() {
        return m_lstSecciones;
    }

    public void setLstSecciones( List<BeanClSeccion> lstSecciones ) {
        this.m_lstSecciones = lstSecciones;
    }

    public String getNomSecGrupo() {
        return m_sNomSecGrupo;
    }

    public void setNomSecGrupo( String nomSecGrupo ) {
        this.m_sNomSecGrupo = nomSecGrupo;
    }

    public Integer getSecGrupId() {
        return m_iSecGrupId;
    }

    public void setSecGrupId( Integer secGrupId ) {
        this.m_iSecGrupId = secGrupId;
    }

    public String getTituloDetalle() {
        return m_sTituloDetalle;
    }

    public void setTituloDetalle( String tituloDetalle ) {
        this.m_sTituloDetalle = tituloDetalle;
    }

    public boolean isVerDetalle() {
        return m_bVerDetalle;
    }

    public void setVerDetalle( boolean verDetalle ) {
        this.m_bVerDetalle = verDetalle;
    }

    public Date getFechaFin() {
        return m_FechaFin;
    }

    public void setFechaFin( Date fechaFin ) {
        this.m_FechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return m_FechaInicio;
    }

    public void setFechaInicio( Date fechaInicio ) {
        this.m_FechaInicio = fechaInicio;
    }
    
}
