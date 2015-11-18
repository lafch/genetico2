/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping;

import net.uch.mapping.base.BaseAcEstadoSemestre;

/**
 *
 * @author richard
 */
public class AcEstadoSemestre extends BaseAcEstadoSemestre{
    private static final long serialVersionUID = 1L;
    public AcEstadoSemestre(){
        super();
    }
    public AcEstadoSemestre(Integer estsemId){
        super(estsemId);
    }
    
    public AcEstadoSemestre(AcAlumno acAlumno, AcSemestre acSemestre, AcAlumnoEstado acAlumnoEstado, Integer estsemId) {
        super(acAlumno,acSemestre,acAlumnoEstado,estsemId);
    }
}
