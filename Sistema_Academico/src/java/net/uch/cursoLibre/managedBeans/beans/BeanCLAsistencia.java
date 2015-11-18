/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LUIS
 */
public class BeanCLAsistencia {
    private int m_iSesId;
    private int m_iAlumId;
    private String m_sAlumCodigo;
    private String m_sAlumNom;
    private String m_sAlumAsistencia;
    private String m_sEstilo;
    

    public String getEstilo() {
        return m_sEstilo;
    }

    public void setEstilo( String sEstilo ) {
        this.m_sEstilo = sEstilo;
    }
    private Map<Integer, String> hmAsistencias;
    
    
    
    private int m_iSesDetId;

    

    public BeanCLAsistencia( int m_iSesId, int m_iAlumId, String m_sAlumCodigo, String m_sAlumNom, String m_sAlumAsistencia, Map<Integer, String> lstAsistencias, int m_iSesDetId ) {
        this.m_iSesId = m_iSesId;
        this.m_iAlumId = m_iAlumId;
        this.m_sAlumCodigo = m_sAlumCodigo;
        this.m_sAlumNom = m_sAlumNom;
        this.m_sAlumAsistencia = m_sAlumAsistencia;
        this.hmAsistencias = lstAsistencias;
        this.m_iSesDetId = m_iSesDetId;
    }

    public int getSesDetId() {
        return m_iSesDetId;
    }

    public void setSesDetId( int iSesDetId ) {
        this.m_iSesDetId = iSesDetId;
    }

    public BeanCLAsistencia() {
    }

    public int getSesId() {
        return m_iSesId;
    }

    public void setSesId( int iSesId ) {
        this.m_iSesId = iSesId;
    }

    public int getAlumId() {
        return m_iAlumId;
    }

    public void setAlumId( int iAlumId ) {
        this.m_iAlumId = iAlumId;
    }

    public String getAlumCodigo() {
        return m_sAlumCodigo;
    }

    public void setAlumCodigo( String sAlumCodigo ) {
        this.m_sAlumCodigo = sAlumCodigo;
    }

    public String getAlumNom() {
        return m_sAlumNom;
    }

    public void setAlumNom( String sAlumNom ) {
        this.m_sAlumNom = sAlumNom;
    }

    public String getAlumAsistencia() {
        return m_sAlumAsistencia;
    }

    public void setAlumAsistencia( String sAlumAsistencia ) {
        this.m_sAlumAsistencia = sAlumAsistencia;
    }

    public Map<Integer, String> getHmAsistencias() {
        return hmAsistencias;
    }

    public void setHmAsistencias( Map<Integer, String> hmAsistencias ) {
        this.hmAsistencias = hmAsistencias;
    }
    public List<String> getLstAsistencias() {
        if ( hmAsistencias == null ) {
            hmAsistencias = new HashMap<Integer, String>();
        }
        return new ArrayList<String>( hmAsistencias.values() );
    }

    public void setLstAsistencias( Map<Integer, String> hmAsistencias ) {
    }
        }
