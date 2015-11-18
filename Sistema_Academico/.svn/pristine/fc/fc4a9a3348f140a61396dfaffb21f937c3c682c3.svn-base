package net.uch.mapping.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractClSisEvaluacion entity provides the base persistence definition of
 * the ClSisEvaluacion entity.
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseClSisEvaluacion implements java.io.Serializable {

    // Fields
    private Integer sisevaId;
    private String sisevaCodigo;
    private String sisevaNombre;
    private String sisevaVigente;
    private Date sisevaCreacion;
    private String sisevaActivo;
    private Set clSisEvaDetalles = new HashSet(0);

    // Constructors
    /** default constructor */
    public BaseClSisEvaluacion() {
    }

    /** full constructor */
    public BaseClSisEvaluacion(String sisevaCodigo, String sisevaNombre,
            String sisevaVigente, Date sisevaCreacion, String sisevaActivo,
            Set clSisEvaDetalles) {
        this.sisevaCodigo = sisevaCodigo;
        this.sisevaNombre = sisevaNombre;
        this.sisevaVigente = sisevaVigente;
        this.sisevaCreacion = sisevaCreacion;
        this.sisevaActivo = sisevaActivo;
        this.clSisEvaDetalles = clSisEvaDetalles;
    }

    // Property accessors
    public Integer getSisevaId() {
        return this.sisevaId;
    }

    public void setSisevaId(Integer sisevaId) {
        this.sisevaId = sisevaId;
    }

    public String getSisevaCodigo() {
        return this.sisevaCodigo;
    }

    public void setSisevaCodigo(String sisevaCodigo) {
        this.sisevaCodigo = sisevaCodigo;
    }

    public String getSisevaNombre() {
        return this.sisevaNombre;
    }

    public void setSisevaNombre(String sisevaNombre) {
        this.sisevaNombre = sisevaNombre;
    }

    public String getSisevaVigente() {
        return this.sisevaVigente;
    }

    public void setSisevaVigente(String sisevaVigente) {
        this.sisevaVigente = sisevaVigente;
    }

    public Date getSisevaCreacion() {
        return this.sisevaCreacion;
    }

    public void setSisevaCreacion(Date sisevaCreacion) {
        this.sisevaCreacion = sisevaCreacion;
    }

    public String getSisevaActivo() {
        return this.sisevaActivo;
    }

    public void setSisevaActivo(String sisevaActivo) {
        this.sisevaActivo = sisevaActivo;
    }

    public Set getClSisEvaDetalles() {
        return this.clSisEvaDetalles;
    }

    public void setClSisEvaDetalles(Set clSisEvaDetalles) {
        this.clSisEvaDetalles = clSisEvaDetalles;
    }
}
