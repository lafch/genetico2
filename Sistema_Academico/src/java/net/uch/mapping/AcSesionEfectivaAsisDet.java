package net.uch.mapping;

import net.uch.mapping.base.BaseAcSesionEfectivaAsisDet;



/**
 * AcSesionEfectivaAsisDet entity. @author MyEclipse Persistence Tools
 */
public class AcSesionEfectivaAsisDet extends BaseAcSesionEfectivaAsisDet implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public AcSesionEfectivaAsisDet() {
    }

	/** minimal constructor */
    public AcSesionEfectivaAsisDet(AcAlumno acAlumno, AcSesionEfectivaAsistencia acSesionEfectivaAsistencia) {
        super(acAlumno, acSesionEfectivaAsistencia);        
    }
    
    /** full constructor */
    public AcSesionEfectivaAsisDet(AcAlumno acAlumno, AcSesionEfectivaAsistencia acSesionEfectivaAsistencia, String sesefeasisDetActivo, String sesefeasisDetAsistencia) {
        super(acAlumno, acSesionEfectivaAsistencia, sesefeasisDetActivo, sesefeasisDetAsistencia);        
    }
   
}
