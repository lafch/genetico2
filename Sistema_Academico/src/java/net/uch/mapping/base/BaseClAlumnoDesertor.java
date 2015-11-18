/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping.base;

import java.util.List;
import java.util.Set;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClObservacionDesercion;
import net.uch.mapping.ClSeccion;

/**
 *
 * @author USUARIO
 */
public class BaseClAlumnoDesertor implements java.io.Serializable {

    private Integer m_iId;
    private Integer m_iActivo;
    private Integer m_iCaptado;
    private Integer m_iCancelarLlamadas;
    private Integer m_iGradoInteres;
    private String m_sDescMotivos;
    private String m_sMotivos;
    private ClAlumno m_clAlumno;
    private ClArbolAcademico m_arbModulo;
    private ClSeccion m_clSecUlt;
    private Set<ClObservacionDesercion> m_clObsDesers;

    public BaseClAlumnoDesertor() {
    }

    public BaseClAlumnoDesertor( Integer iId ) {
        this.m_iId = iId;
    }

    public BaseClAlumnoDesertor( Integer iId, Integer iActivo, Integer iCaptado, Integer iCancelarLlamadas,Integer iGradoInteres, String sDescMotivos,
            String sMotivos, ClAlumno clAlumno, ClArbolAcademico arbModulo,
            ClSeccion clSecUlt, Set<ClObservacionDesercion> clObsDesers ) {
        this.m_iId = iId;
        this.m_iActivo = iActivo;
        this.m_iCaptado = iCaptado;
        this.m_iCancelarLlamadas = iCancelarLlamadas;
        this.m_iGradoInteres=iGradoInteres;
        this.m_sDescMotivos = sDescMotivos;
        this.m_sMotivos = sMotivos;
        this.m_clAlumno = clAlumno;
        this.m_arbModulo = arbModulo;
        this.m_clSecUlt = clSecUlt;
        this.m_clObsDesers = clObsDesers;
    }

    public ClArbolAcademico getArbModulo() {
        return m_arbModulo;
    }

    public void setArbModulo( ClArbolAcademico arbModulo ) {
        this.m_arbModulo = arbModulo;
    }

    public ClAlumno getClAlumno() {
        return m_clAlumno;
    }

    public void setClAlumno( ClAlumno clAlumno ) {
        this.m_clAlumno = clAlumno;
    }

    public ClSeccion getClSecUlt() {
        return m_clSecUlt;
    }

    public void setClSecUlt( ClSeccion clSecUlt ) {
        this.m_clSecUlt = clSecUlt;
    }

    public Integer getActivo() {
        return m_iActivo;
    }

    public void setActivo( Integer iActivo ) {
        this.m_iActivo = iActivo;
    }

    public Integer getCaptado() {
        return m_iCaptado;
    }

    public void setCaptado( Integer iCaptado ) {
        this.m_iCaptado = iCaptado;
    }

    public Integer getId() {
        return m_iId;
    }

    public void setId( Integer iId ) {
        this.m_iId = iId;
    }

    public Integer getCancelarLlamadas() {
        return m_iCancelarLlamadas;
    }

    public void setCancelarLlamadas( Integer iCancelarLlamadas ) {
        this.m_iCancelarLlamadas = iCancelarLlamadas;
    }
    
     public Integer getGradoInteres() {
        return m_iGradoInteres;
    }

    public void setGradoInteres( Integer iGradoInteres ) {
        this.m_iGradoInteres = iGradoInteres;
    }

    public String getMotivos() {
        return m_sMotivos;
    }

    public void setMotivos( String sMotivos ) {
        this.m_sMotivos = sMotivos;
    }

    public Set<ClObservacionDesercion> getClObsDesers() {
        return m_clObsDesers;
    }

    public void setClObsDesers( Set<ClObservacionDesercion> clObsDesers ) {
        this.m_clObsDesers = clObsDesers;
    }

    public String getDescMotivos() {
        return m_sDescMotivos;
    }

    public void setDescMotivos( String sDescMotivos ) {
        this.m_sDescMotivos = sDescMotivos;
    }
}
