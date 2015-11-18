package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;
import net.uch.mapping.ClArbolAcademico;


/**
 * AbstractClEstructuraPagos entity provides the base persistence definition of
 * the ClEstructuraPagos entity. @author MyEclipse Persistence Tools
 */
public abstract class BaseClEstructuraPagos implements java.io.Serializable {

    // Fields
    private Integer estpagId;
    private ClArbolAcademico clArbolAcademico;
    private String estpagNombre;
    private String estpagAbrev;
    private String estpagActivo;
    private Set clEstructuraPagosDetalles = new HashSet(0);
    private String estpagTipo;

    // Constructors
    /** default constructor */
    public BaseClEstructuraPagos() {
    }

    /** full constructor */
    public BaseClEstructuraPagos(ClArbolAcademico clArbolAcademico, String estpagNombre,
            String estpagAbrev, String estpagActivo, Set clEstructuraPagosDetalles, String estpagTipo) {
        this.clArbolAcademico = clArbolAcademico;
        this.estpagNombre = estpagNombre;
        this.estpagAbrev = estpagAbrev;
        this.estpagActivo = estpagActivo;
        this.clEstructuraPagosDetalles = clEstructuraPagosDetalles;
        this.estpagTipo = estpagTipo;
    }

    // Property accessors
    public Integer getEstpagId() {
        return this.estpagId;
    }

    public void setEstpagId(Integer estpagId) {
        this.estpagId = estpagId;
    }

    public ClArbolAcademico getClArbolAcademico() {
        return clArbolAcademico;
    }

    public void setClArbolAcademico(ClArbolAcademico clArbolAcademico) {
        this.clArbolAcademico = clArbolAcademico;
    }

    public String getEstpagNombre() {
        return this.estpagNombre;
    }

    public void setEstpagNombre(String estpagNombre) {
        this.estpagNombre = estpagNombre;
    }

    public String getEstpagActivo() {
        return this.estpagActivo;
    }

    public void setEstpagActivo(String estpagActivo) {
        this.estpagActivo = estpagActivo;
    }

    public Set getClEstructuraPagosDetalles() {
        return this.clEstructuraPagosDetalles;
    }

    public void setClEstructuraPagosDetalles(Set clEstructuraPagosDetalles) {
        this.clEstructuraPagosDetalles = clEstructuraPagosDetalles;
    }

    public String getEstpagAbrev() {
        return estpagAbrev;
    }

    public void setEstpagAbrev(String estpagAbrev) {
        this.estpagAbrev = estpagAbrev;
    }

    public String getEstpagTipo() {
        return estpagTipo;
    }

    public void setEstpagTipo(String estpagTipo) {
        this.estpagTipo = estpagTipo;
    }
}
