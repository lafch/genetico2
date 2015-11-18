package net.uch.mapping;

import java.sql.Timestamp;
import net.uch.mapping.base.BaseAcDocenteCurso;
/**
 * ClHoraria entity.
 * @author MyEclipse Persistence Tools
 */
public class AcDocenteCurso extends BaseAcDocenteCurso implements java.io.Serializable {

    // Constructors
    /** default constructor */
    public AcDocenteCurso() {
    }
    

    /** minimal constructor */
    public AcDocenteCurso(AcDocente acDocente, AcCurso acCurso) {
        super(acDocente, acCurso);
    }

    /** full constructor */
    public AcDocenteCurso(AcDocente acDocente,AcCurso acCurso,
            Timestamp fechaCreacion, Integer activo, String estadoAsignacion ) {
        super(acDocente, acCurso, fechaCreacion, activo, estadoAsignacion);
    }
}
