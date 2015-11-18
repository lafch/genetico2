/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

import java.io.Serializable;
import java.util.Set;
import net.uch.mapping.base.BaseClSeccionGrupo;

/**
 *
 * @author USUARIO
 */
public class ClSeccionGrupo extends BaseClSeccionGrupo implements Serializable {

    public ClSeccionGrupo( Integer iSecGrupoId, Set<ClSeccion> clSeccions ) {
        super( iSecGrupoId, clSeccions );
    }

    public ClSeccionGrupo( Integer iSecGrupoId ) {
        super( iSecGrupoId );
    }

    public ClSeccionGrupo() {
    }
}
