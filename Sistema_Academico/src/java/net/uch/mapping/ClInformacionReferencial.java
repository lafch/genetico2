package net.uch.mapping;

// Generated by MyEclipse Persistence Tools
import java.util.Date;
import net.uch.mapping.base.BaseClConsultaPublico;
import net.uch.mapping.base.BaseClInformacionReferencial;

/**
 * ClConsultaPublico generated by MyEclipse Persistence Tools
 */
public class ClInformacionReferencial extends BaseClInformacionReferencial implements
        java.io.Serializable {

    // Constructors
    /** default constructor */
    public ClInformacionReferencial () {
    }

    public ClInformacionReferencial ( Integer idReferencial ) {
        super( idReferencial );
    }
    /** full constructor */
    public ClInformacionReferencial(Integer idReferencial, Integer conId, String personaRecomendar, String correoTelefono, String gradoInstruccion, String ocupacionActual, String activo, Date fechaCreacion) {
        super(idReferencial, conId, personaRecomendar, correoTelefono, gradoInstruccion, ocupacionActual, activo, fechaCreacion);
    }    
}