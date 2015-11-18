package net.uch.mapping.base;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAcCalendario entity provides the base persistence definition of the
 * AcCalendario entity. @author MyEclipse Persistence Tools
 */
public abstract class BaseAcCalendario implements java.io.Serializable {

    // Fields
    private Integer calId;
    private Date calFecha;
    private String calActivo;
    private Timestamp calFechaCreacion;
    private Set acCalendarioActividadeses = new HashSet(0);

    // Constructors
    /** default constructor */
    public BaseAcCalendario() {
    }

    /** full constructor */
    public BaseAcCalendario(Date calFecha, String calActivo,
            Timestamp calFechaCreacion, Set acCalendarioActividadeses) {
        this.calFecha = calFecha;
        this.calActivo = calActivo;
        this.calFechaCreacion = calFechaCreacion;
        this.acCalendarioActividadeses = acCalendarioActividadeses;
    }

    // Property accessors
    public Integer getCalId() {
        return this.calId;
    }

    public void setCalId(Integer calId) {
        this.calId = calId;
    }

    public Date getCalFecha() {
        return this.calFecha;
    }

    public void setCalFecha(Date calFecha) {
        this.calFecha = calFecha;
    }

    public String getCalActivo() {
        return this.calActivo;
    }

    public void setCalActivo(String calActivo) {
        this.calActivo = calActivo;
    }

    public Timestamp getCalFechaCreacion() {
        return this.calFechaCreacion;
    }

    public void setCalFechaCreacion(Timestamp calFechaCreacion) {
        this.calFechaCreacion = calFechaCreacion;
    }

    public Set getAcCalendarioActividadeses() {
        return this.acCalendarioActividadeses;
    }

    public void setAcCalendarioActividadeses(Set acCalendarioActividadeses) {
        this.acCalendarioActividadeses = acCalendarioActividadeses;
    }

    @Override
    public String toString() {
        return "\tcalId:" + calId
                + "\tcalFecha: " + calFecha
                + "\tcalActivo:" + calActivo
                + "\tcalFechaCreacion:" + calFechaCreacion
                + "\tacCalendarioActividadeses:" + acCalendarioActividadeses.size();
    }
}
