/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.uch.mapping.*;

/**
 *
 * @author USUARIO
 */
public class BeanClAlumnoDesertor {

    private boolean m_bCancelarLlamadas;
    private boolean m_bGradoInteres;
    private boolean m_bCaptado;
    private Integer m_iId;
    private Integer m_iActivo;
    private String m_sDescMotivos;
    private ClAlumno m_clAlumno;
    private ClArbolAcademico m_arbModulo;
    private ClSeccion m_clSecUlt;
    private List<String> m_lstMotivos;
    private List<ClObservacionDesercion> m_lstObsDesers;

    public BeanClAlumnoDesertor() {
    }

    public BeanClAlumnoDesertor( boolean bCancelarLladas,boolean bGradoInteres, boolean bCaptado, Integer iId, Integer iActivo, String sDescMotivos, ClAlumno clAlumno, ClArbolAcademico arbModulo,
            ClSeccion clSecUlt, List<ClObservacionDesercion> lstObsDeser, List<String> lstMotivos ) {
        this.m_iId = iId;
        this.m_iActivo = iActivo;
        this.m_bCaptado = bCaptado;
        this.m_sDescMotivos = sDescMotivos;
        this.m_clAlumno = clAlumno;
        this.m_arbModulo = arbModulo;
        this.m_clSecUlt = clSecUlt;
        this.m_lstMotivos = lstMotivos;
        this.m_lstObsDesers = lstObsDeser;
    }

    public boolean isCancelarLlamadas() {
        return m_bCancelarLlamadas;
    }

    public void setCancelarLlamadas( boolean bCancelarLlamadas ) {
        this.m_bCancelarLlamadas = bCancelarLlamadas;
    }
    
    public boolean isGradoInteres() {
        return m_bGradoInteres;
    }

    public void setGradoInteres( boolean bGradoInteres ) {
        this.m_bGradoInteres = bGradoInteres;
    }

    public Integer getId() {
        return m_iId;
    }

    public void setId( Integer iId ) {
        this.m_iId = iId;
    }

    public Integer getActivo() {
        return m_iActivo;
    }

    public void setActivo( Integer iActivo ) {
        this.m_iActivo = iActivo;
    }

    public boolean isCaptado() {
        return m_bCaptado;
    }

    public void setCaptado( boolean bCaptado ) {
        this.m_bCaptado = bCaptado;
    }

    public String getDescMotivos() {
        return m_sDescMotivos;
    }

    public void setDescMotivos( String sDescMotivos ) {
        this.m_sDescMotivos = sDescMotivos;
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

    public List<String> getLstMotivos() {
        return m_lstMotivos;
    }

    public void setLstMotivos( List<String> lstMotivos ) {
        this.m_lstMotivos = lstMotivos;
    }

    public List<ClObservacionDesercion> getLstObsDeser() {
        return m_lstObsDesers;
    }

    public void setLstObsDeser( List<ClObservacionDesercion> lstObsDesers ) {
        this.m_lstObsDesers = lstObsDesers;
    }

    public void setValues( ClAlumnoDesertor clAluDeser ) {
        String[] asMotivos;
        Iterator<ClObservacionDesercion> it;
        this.m_iId = clAluDeser.getId();
        this.m_iActivo = clAluDeser.getActivo();
        this.m_bCaptado = !(clAluDeser.getCaptado() == null || clAluDeser.getCaptado().intValue() == 0);
        this.m_sDescMotivos = clAluDeser.getDescMotivos();
        this.m_clAlumno = clAluDeser.getClAlumno();
        this.m_arbModulo = clAluDeser.getArbModulo();
        this.m_clSecUlt = clAluDeser.getClSecUlt();
        this.m_bCancelarLlamadas = !(clAluDeser.getCancelarLlamadas() == null || clAluDeser.getCancelarLlamadas().intValue() == 0);
        this.m_bGradoInteres = !(clAluDeser.getGradoInteres()== null || clAluDeser.getGradoInteres().intValue() == 0);

        this.m_lstMotivos = new ArrayList<String>();
        if ( clAluDeser.getMotivos() != null ) {
            asMotivos = clAluDeser.getMotivos().split( "," );
            for ( String sMotiv : asMotivos ) {
                if ( !sMotiv.trim().isEmpty() ) {
                    this.m_lstMotivos.add( sMotiv );
                }
            }
        }
        this.m_lstObsDesers = new ArrayList<ClObservacionDesercion>();
        if ( clAluDeser.getClObsDesers() != null ) {
            it = clAluDeser.getClObsDesers().iterator();
            while ( it.hasNext() ) {
                this.m_lstObsDesers.add( it.next() );
            }
        }
    }
}