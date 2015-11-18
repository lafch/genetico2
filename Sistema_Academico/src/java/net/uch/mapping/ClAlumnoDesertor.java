/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

import java.util.HashSet;
import java.util.Set;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumnoDesertor;
import net.uch.mapping.base.BaseClAlumnoDesertor;

/**
 *
 * @author USUARIO
 */
public class ClAlumnoDesertor extends BaseClAlumnoDesertor {

    public ClAlumnoDesertor( Integer iId, Integer iActivo, Integer iCaptado, Integer iCancelarLlamadas, Integer iGradoInteres, String sDescMotivos, String sMotivos, ClAlumno clAlumno, ClArbolAcademico arbModulo, ClSeccion clSecUlt, Set<ClObservacionDesercion> clObsDesers ) {
        super( iId, iActivo, iCaptado, iCancelarLlamadas,iGradoInteres, sDescMotivos, sMotivos, clAlumno, arbModulo, clSecUlt, clObsDesers );
    }

    public ClAlumnoDesertor( Integer iId ) {
        super( iId );
    }

    public ClAlumnoDesertor() {
    }

    public void setValues( BeanClAlumnoDesertor beanClAlumDeser ) {
        int iSizeMotiv;
        String sMotivo;
        String sMotivos;
        this.setId( beanClAlumDeser.getId() );
        this.setActivo( beanClAlumDeser.getActivo() );
        this.setDescMotivos( beanClAlumDeser.getDescMotivos() );
        this.setCancelarLlamadas( beanClAlumDeser.isCancelarLlamadas() ? 1 : 0 );
        this.setGradoInteres(beanClAlumDeser.isGradoInteres()? 1 : 0 );
        this.setCaptado( beanClAlumDeser.isCaptado() ? 1 : 0 );

        sMotivos = "";
        iSizeMotiv = beanClAlumDeser.getLstMotivos().size();
        for ( int i = 0; i < iSizeMotiv; i++ ) {
            sMotivo = beanClAlumDeser.getLstMotivos().get( i );
            sMotivos += sMotivo;
            if ( i < iSizeMotiv - 1 ) {
                sMotivos += ",";
            }
        }
        this.setMotivos( sMotivos );
        this.setClAlumno( beanClAlumDeser.getClAlumno() );
        this.setArbModulo( beanClAlumDeser.getArbModulo() );
        this.setClSecUlt( beanClAlumDeser.getClSecUlt() );
        this.setClObsDesers( new HashSet<ClObservacionDesercion>( beanClAlumDeser.getLstObsDeser() ) );
    }
}
