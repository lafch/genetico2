/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping.base;

import java.util.Date;
import net.uch.mapping.ClAlumnoDesertor;
import net.uch.mapping.TbUsuario;

/**
 *
 * @author USUARIO
 */
public class BaseClObservacionDesercion {

    private Integer m_iId;
    private ClAlumnoDesertor m_clAlumnoDesertor;
    private Date m_fechaRegistro;
    private Date m_fechaContacto;
    private String m_sObservacion;
    private TbUsuario m_tbUsuario;
    private Integer m_iActivo;

    public BaseClObservacionDesercion() {
    }

    public BaseClObservacionDesercion( Integer iId ) {
        this.m_iId = iId;
    }

    public BaseClObservacionDesercion( Integer iId,
            ClAlumnoDesertor clAlumnoDesertor, Date fechaRegistro, Date fechaContacto, String sObservacion,
            TbUsuario tbUsuario, Integer iActivo ) {
        this.m_iId = iId;
        this.m_clAlumnoDesertor = clAlumnoDesertor;
        this.m_fechaRegistro = fechaRegistro;
        this.m_fechaContacto = fechaContacto;
        this.m_sObservacion = sObservacion;
        this.m_tbUsuario = tbUsuario;
        this.m_iActivo = iActivo;
    }

    public ClAlumnoDesertor getClAlumnoDesertor() {
        return m_clAlumnoDesertor;
    }

    public void setClAlumnoDesertor( ClAlumnoDesertor clAlumnoDesertor ) {
        this.m_clAlumnoDesertor = clAlumnoDesertor;
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

    public Integer getId() {
        return m_iId;
    }

    public void setId( Integer iId ) {
        this.m_iId = iId;
    }

    public String getObservacion() {
        return m_sObservacion;
    }

    public void setObservacion( String sObservacion ) {
        this.m_sObservacion = sObservacion;
    }

    public TbUsuario getTbUsuario() {
        return m_tbUsuario;
    }

    public void setTbUsuario( TbUsuario tbUsuario ) {
        this.m_tbUsuario = tbUsuario;
    }
}
