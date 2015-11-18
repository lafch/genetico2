/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.util.CommonDAO;

/**
 *
 * @author USUARIO
 */
public class bRegistroAsistenciaAlumnoCL {

    private String m_sCodAlumnoFiltro;
    private String m_sApPaternoAlumnoFiltro;
    private String m_sApMaternoAlumnoFiltro;
    private String m_sNombresAlumnoFiltro;
    private Date m_fechaInicioFiltro;
    private Date m_fechaFinFiltro;
    private List<BeanReporte> m_lstAsistencias;

    /**
     * Creates a new instance of bRegistroAsistenciaAlumnoCL
     */
    public bRegistroAsistenciaAlumnoCL() {
    }

    public String getApMaternoAlumnoFiltro() {
        return m_sApMaternoAlumnoFiltro;
    }

    public void setApMaternoAlumnoFiltro( String sApMaternoAlumnoFiltro ) {
        this.m_sApMaternoAlumnoFiltro = sApMaternoAlumnoFiltro;
    }

    public String getApPaternoAlumnoFiltro() {
        return m_sApPaternoAlumnoFiltro;
    }

    public void setApPaternoAlumnoFiltro( String sApPaternoAlumnoFiltro ) {
        this.m_sApPaternoAlumnoFiltro = sApPaternoAlumnoFiltro;
    }

    public String getCodAlumnoFiltro() {
        return m_sCodAlumnoFiltro;
    }

    public void setCodAlumnoFiltro( String sCodAlumnoFiltro ) {
        this.m_sCodAlumnoFiltro = sCodAlumnoFiltro;
    }

    public String getNombresAlumnoFiltro() {
        return m_sNombresAlumnoFiltro;
    }

    public void setNombresAlumnoFiltro( String sNombresAlumnoFiltro ) {
        this.m_sNombresAlumnoFiltro = sNombresAlumnoFiltro;
    }

    public Date getFechaInicioFiltro() {
        if ( m_fechaInicioFiltro == null ) {
            m_fechaInicioFiltro = new Date();
        }
        return m_fechaInicioFiltro;
    }

    public void setFechaInicioFiltro( Date fechaInicioFiltro ) {
        this.m_fechaInicioFiltro = fechaInicioFiltro;
    }

    public Date getFechaFinFiltro() {
        if ( m_fechaFinFiltro == null ) {
            m_fechaFinFiltro = new Date();
        }
        return m_fechaFinFiltro;
    }

    public void setFechaFinFiltro( Date fechaFinFiltro ) {
        this.m_fechaFinFiltro = fechaFinFiltro;
    }

    public List<BeanReporte> getLstAsistencias() {
        try {
            if ( m_sCodAlumnoFiltro != null && !m_sCodAlumnoFiltro.isEmpty() ) {
                m_lstAsistencias = CommonDAO.getClAlumnoDAO().listarReporteAsistencias( m_sCodAlumnoFiltro, m_fechaInicioFiltro, m_fechaFinFiltro );
            } else {
                m_lstAsistencias = new ArrayList<BeanReporte>();
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            m_lstAsistencias = new ArrayList<BeanReporte>();
        }
        return m_lstAsistencias;
    }

    public void setLstAsistencias( List<BeanReporte> lstAsistencias ) {
        this.m_lstAsistencias = lstAsistencias;
    }
}
