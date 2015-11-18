/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping.base;

import java.util.Date;
import java.util.Set;
import net.uch.mapping.ClSeccion;

/**
 *
 * @author USUARIO
 */
public class BaseClSeccionGrupo  implements java.io.Serializable {
    private Integer m_iSecGrupoId;
    private String sNomSecGrupo;
    private String m_sActivo;
    private Date m_fechaInicio;
    private Date m_fechaFin;
    private Set<ClSeccion> m_clSeccions;

    public BaseClSeccionGrupo() {
    }

    public BaseClSeccionGrupo( Integer iSecGrupoId ) {
        this.m_iSecGrupoId = iSecGrupoId;
    }

    public BaseClSeccionGrupo( Integer iSecGrupoId, Set<ClSeccion> clSeccions ) {
        m_iSecGrupoId = iSecGrupoId;
        m_clSeccions = clSeccions;
    }

    public String getNomSecGrupo() {
        return sNomSecGrupo;
    }

    public void setNomSecGrupo( String sNomSecGrupo ) {
        this.sNomSecGrupo = sNomSecGrupo;
    }

    public Set<ClSeccion> getClSecciones() {
        return m_clSeccions;
    }

    public void setClSecciones( Set<ClSeccion> clSeccions ) {
        this.m_clSeccions = clSeccions;
    }

    public Integer getSecGrupoId() {
        return m_iSecGrupoId;
    }

    public void setSecGrupoId( Integer iSecGrupoId ) {
        this.m_iSecGrupoId = iSecGrupoId;
    }

    public String getActivo() {
        return m_sActivo;
    }

    public void setActivo( String sActivo ) {
        m_sActivo = sActivo;
    }

    public Date getFechaFin() {
        return m_fechaFin;
    }

    public void setFechaFin( Date fechaFin ) {
        m_fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return m_fechaInicio;
    }

    public void setFechaInicio( Date fechaInicio ) {
        m_fechaInicio = fechaInicio;
    }
    
    
}
