package net.uch.mapping.base;

import net.uch.mapping.AcCalendarioActividades;

/**
 * AbstractAcActividadAlcance entity provides the base persistence definition of
 * the AcActividadAlcance entity. @author MyEclipse Persistence Tools
 */
public abstract class BaseAcActividadAlcance implements
        java.io.Serializable {

    // Fields
    private Integer actalcId;
    private AcCalendarioActividades acCalendarioActividades;
    private String actalcAlcance;
    private String actalcActivo;

    // Constructors
    /** default constructor */
    public BaseAcActividadAlcance() {
    }

    /** minimal constructor */
    public BaseAcActividadAlcance(
            AcCalendarioActividades acCalendarioActividades) {
        this.acCalendarioActividades = acCalendarioActividades;
    }

    /** full constructor */
    public BaseAcActividadAlcance(
            AcCalendarioActividades acCalendarioActividades,
            String actalcAlcance, String actalcActivo) {
        this.acCalendarioActividades = acCalendarioActividades;
        this.actalcAlcance = actalcAlcance;
        this.actalcActivo = actalcActivo;
    }

    // Property accessors
    public Integer getActalcId() {
        return this.actalcId;
    }

    public void setActalcId(Integer actalcId) {
        this.actalcId = actalcId;
    }

    public AcCalendarioActividades getAcCalendarioActividades() {
        return this.acCalendarioActividades;
    }

    public void setAcCalendarioActividades(
            AcCalendarioActividades acCalendarioActividades) {
        this.acCalendarioActividades = acCalendarioActividades;
    }

    public String getActalcAlcance() {
        return this.actalcAlcance;
    }

    public void setActalcAlcance(String actalcAlcance) {
        this.actalcAlcance = actalcAlcance;
    }

    public String getActalcActivo() {
        return this.actalcActivo;
    }

    public void setActalcActivo(String actalcActivo) {
        this.actalcActivo = actalcActivo;
    }

    @Override
    public String toString() {
        return "\tactalcId: " + actalcId
                + "\tacCalendarioActividades: " + acCalendarioActividades
                + "\tactalcAlcance" + actalcAlcance
                + "\tactalcActivo" + actalcActivo;
    }
}
