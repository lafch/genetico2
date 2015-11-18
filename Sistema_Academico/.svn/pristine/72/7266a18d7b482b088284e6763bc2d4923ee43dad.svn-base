/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.Date;
import net.uch.mapping.TbUsuario;

/**
 *
 * @author USUARIO
 */
public class BeanObservacionDesercion {
    private Integer m_iActivo;
    private Integer m_iIdSeguimDesercion;
    private Date m_fechaContacto;
    private BeanClAlumnoDesertor m_bAluDesert;
    private TbUsuario m_tbUsuario;
    private String m_sObservacion;
    private Date m_fechaRegistro;

    public BeanObservacionDesercion() {
    }
    public BeanObservacionDesercion(Integer iIdSeguimDesercion) {
        m_iIdSeguimDesercion = iIdSeguimDesercion;
    }

    public BeanObservacionDesercion( Integer iActivo, Integer iIdSeguimDesercion, BeanClAlumnoDesertor bAluDesert, TbUsuario tbUsuario, String sObservacion, Date fechaRegistro ) {
        this.m_iActivo = iActivo;
        this.m_iIdSeguimDesercion = iIdSeguimDesercion;
        this.m_bAluDesert = bAluDesert;
        this.m_tbUsuario = tbUsuario;
        this.m_sObservacion = sObservacion;
        this.m_fechaRegistro = fechaRegistro;
    }

    public Date getFechaRegistro() {
        return m_fechaRegistro;
    }

    public void setFechaRegistro( Date fechaRegistro ) {
        this.m_fechaRegistro = fechaRegistro;
    }

    public Date getFechaContacto() {
        return m_fechaContacto;
    }

    public void setFechaContacto( Date fechaContacto ) {
        this.m_fechaContacto = fechaContacto;
    }

    public Integer getActivo() {
        return m_iActivo;
    }

    public void setActivo( Integer iActivo ) {
        this.m_iActivo = iActivo;
    }

    public Integer getIdSeguimDesercion() {
        return m_iIdSeguimDesercion;
    }

    public BeanClAlumnoDesertor getAluDesert() {
        return m_bAluDesert;
    }

    public void setAluDesert( BeanClAlumnoDesertor bAluDesert ) {
        this.m_bAluDesert = bAluDesert;
    }

    public void setIdSeguimDesercion( Integer iIdSeguimDesercion ) {
        this.m_iIdSeguimDesercion = iIdSeguimDesercion;
    }

    public TbUsuario getTbUsuario() {
        return m_tbUsuario;
    }

    public void setTbUsuario( TbUsuario tbUsuario ) {
        this.m_tbUsuario = tbUsuario;
    }

    public String getObservacion() {
        return m_sObservacion;
    }

    public void setObservacion( String sObservacion ) {
        this.m_sObservacion = sObservacion;
    }
}
