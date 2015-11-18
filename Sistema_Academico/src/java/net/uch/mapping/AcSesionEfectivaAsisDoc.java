package net.uch.mapping;

import java.sql.Timestamp;

import net.uch.mapping.base.BaseAcSesionEfectivaAsisDoc;


/**
 * AcSesionEfectivaAsisDoc entity. @author MyEclipse Persistence Tools
 */
public class AcSesionEfectivaAsisDoc extends BaseAcSesionEfectivaAsisDoc implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public AcSesionEfectivaAsisDoc() {
    }

	/** minimal constructor */
    public AcSesionEfectivaAsisDoc(AcSesionAsistencia acSesionAsistencia, AcDocente acDocente) {
        super(acSesionAsistencia, acDocente);        
    }
    
    /** full constructor */
    public AcSesionEfectivaAsisDoc(AcSesionAsistencia acSesionAsistencia, AcDocente acDocente, Timestamp sesefeasisdocRegistro, String sesefeasisdocObs, Integer sesefeasisdocTole, String sesefeasisdocActivo, String sesefeasisdocTipo, Timestamp sesefeasisdocSalida) {
        super(acSesionAsistencia, acDocente, sesefeasisdocRegistro, sesefeasisdocObs, sesefeasisdocTole, sesefeasisdocActivo, sesefeasisdocTipo, sesefeasisdocSalida);        
    }
   
}
