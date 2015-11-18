package net.uch.mapping.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClEstructuraPagos;

/**
 * AbstractClEstructuraPagosDetalle entity provides the base persistence
 * definition of the ClEstructuraPagosDetalle entity. @author MyEclipse
 * Persistence Tools
 */
public abstract class BaseClEstructuraPagosDetalle implements
        java.io.Serializable {

    // Fields
    private Integer estpagdetId;
    private ClEstructuraPagos clEstructuraPagos;
    private AdConceptoPago adConceptoPago;
    private ClArbolAcademico clArbolAcadCurso;
    private String estpagdetNombre;
    private Date estpagdetFechaPago;
    private Float estpagdetMonto;
    private String estpagdetActivo;
    private String estpagdetVisible;
    private String estpagdetNomImp;
    private Integer talId;
    private Integer estpagdetOrden;
    private Set clAlumnoTarifas = new HashSet(0);

    private boolean est_verificar=false;

    public boolean isEst_verificar() {
        return est_verificar;
    }

    public void setEst_verificar(boolean est_verificar) {
        this.est_verificar = est_verificar;
    }

    public ClArbolAcademico getClArbolAcadCurso() {
        return clArbolAcadCurso;
    }

    public void setClArbolAcadCurso(ClArbolAcademico clArbolAcadCurso) {
        this.clArbolAcadCurso = clArbolAcadCurso;
    }

    

    // Constructors
    /** default constructor */
    public BaseClEstructuraPagosDetalle() {
    }

    /** Constructor for primary key */
    public BaseClEstructuraPagosDetalle(Integer estpagdetId) {
        this.estpagdetId = estpagdetId;
    }

    /** minimal constructor */
    public BaseClEstructuraPagosDetalle(
            ClEstructuraPagos clEstructuraPagos, AdConceptoPago adConceptoPago,
            Integer talId) {
        this.clEstructuraPagos = clEstructuraPagos;
        this.adConceptoPago = adConceptoPago;
        this.talId = talId;
    }

    /** full constructor */
    public BaseClEstructuraPagosDetalle(
            ClEstructuraPagos clEstructuraPagos, AdConceptoPago adConceptoPago,
            ClArbolAcademico clArbolAcadCurso, String estpagdetNombre, Date estpagdetFechaPago,
            Float estpagdetMonto, String estpagdetActivo, Integer talId,
            Set clAlumnoTarifas, Integer estpagdetOrden, String estpagdetVisible, String estpagdetNomImp) {
        this.clEstructuraPagos = clEstructuraPagos;
        this.adConceptoPago = adConceptoPago;
        this.clArbolAcadCurso = clArbolAcadCurso;
        this.estpagdetNombre = estpagdetNombre;
        this.estpagdetFechaPago = estpagdetFechaPago;
        this.estpagdetMonto = estpagdetMonto;
        this.estpagdetActivo = estpagdetActivo;
        this.estpagdetVisible = estpagdetVisible;
        this.talId = talId;
        this.clAlumnoTarifas = clAlumnoTarifas;
        this.estpagdetOrden = estpagdetOrden;
        this.estpagdetNomImp=estpagdetNomImp;
    }

    // Property accessors
    public Integer getEstpagdetId() {
        return this.estpagdetId;
    }

    public void setEstpagdetId(Integer estpagdetId) {
        this.estpagdetId = estpagdetId;
    }

    public ClEstructuraPagos getClEstructuraPagos() {
        return this.clEstructuraPagos;
    }

    public void setClEstructuraPagos(ClEstructuraPagos clEstructuraPagos) {
        this.clEstructuraPagos = clEstructuraPagos;
    }

    public AdConceptoPago getAdConceptoPago() {
        return this.adConceptoPago;
    }

    public void setAdConceptoPago(AdConceptoPago adConceptoPago) {
        this.adConceptoPago = adConceptoPago;
    }

    public String getEstpagdetNombre() {
        return this.estpagdetNombre;
    }

    public void setEstpagdetNombre(String estpagdetNombre) {
        this.estpagdetNombre = estpagdetNombre;
    }

    public Date getEstpagdetFechaPago() {
        return this.estpagdetFechaPago;
    }

    public void setEstpagdetFechaPago(Date estpagdetFechaPago) {
        this.estpagdetFechaPago = estpagdetFechaPago;
    }

    public Float getEstpagdetMonto() {
        return this.estpagdetMonto;
    }

    public void setEstpagdetMonto(Float estpagdetMonto) {
        this.estpagdetMonto = estpagdetMonto;
    }

    public String getEstpagdetActivo() {
        return this.estpagdetActivo;
    }

    public void setEstpagdetActivo(String estpagdetActivo) {
        this.estpagdetActivo = estpagdetActivo;
    }

    public String getEstpagdetVisible() {
        return estpagdetVisible;
    }

    public void setEstpagdetVisible(String estpagdetVisible) {
        this.estpagdetVisible = estpagdetVisible;
    }

    public String getEstpagdetNomImp() {
        return estpagdetNomImp;
    }

    public void setEstpagdetNomImp( String estpagdetNomImp ) {
        this.estpagdetNomImp = estpagdetNomImp;
    }
    
    
    public Integer getTalId() {
        return talId;
    }

    public void setTalId(Integer talId) {
        this.talId = talId;
    }

    public Set getClAlumnoTarifas() {
        return clAlumnoTarifas;
    }

    public void setClAlumnoTarifas(Set clAlumnoTarifas) {
        this.clAlumnoTarifas = clAlumnoTarifas;
    }

    public Integer getEstpagdetOrden() {
        return estpagdetOrden;
    }

    public void setEstpagdetOrden(Integer estpagdetOrden) {
        this.estpagdetOrden = estpagdetOrden;
    }

    
}
