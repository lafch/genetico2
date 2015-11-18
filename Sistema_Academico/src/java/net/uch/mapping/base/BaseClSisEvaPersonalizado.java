package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClSisEvaDetalle;

/**
 * AbstractClSisEvaPersonalizado entity provides the base persistence definition
 * of the ClSisEvaPersonalizado entity.
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseClSisEvaPersonalizado implements
        java.io.Serializable {

    // Fields
    private Integer sisevaPerId;
    private ClSisEvaDetalle clSisEvaDetalle;
    private String sisevaPerNombre;
    private String sisevaPerExaSemana;
    private ClArbolAperturado clArbolAperturado;
    private String sisevaCodigo;
    private Double sisevaPerPeso;
    private Integer sisevaPerOrden;
    private String sisevaPerActivo;
    private Integer arbId;
    private Set clNotas = new HashSet(0);

    public ClArbolAperturado getClArbolAperturado() {
        return clArbolAperturado;
    }

    public void setClArbolAperturado(ClArbolAperturado clArbolrAperturado) {
        this.clArbolAperturado = clArbolrAperturado;
    }

    // Constructors
    
    /** default constructor */
    public BaseClSisEvaPersonalizado() {
    }

    /** minimal constructor */
    public BaseClSisEvaPersonalizado(ClSisEvaDetalle clSisEvaDetalle,
             ClArbolAperturado clArbolAperturado) {
        this.clSisEvaDetalle = clSisEvaDetalle;
        this.clArbolAperturado = clArbolAperturado;
    }

    /** full constructor */
    public BaseClSisEvaPersonalizado(ClSisEvaDetalle clSisEvaDetalle,
            String sisevaPerNombre, String sisevaPerExaSemana,
            ClArbolAperturado clArbolAperturado , String sisevaCodigo, Double sisevaPerPeso,
            Integer sisevaPerOrden, String sisevaPerActivo,Integer arbId, Set clNotas) {
        this.clSisEvaDetalle = clSisEvaDetalle;
        this.sisevaPerNombre = sisevaPerNombre;
        this.sisevaPerExaSemana = sisevaPerExaSemana;
        this.clArbolAperturado = clArbolAperturado;
        this.sisevaCodigo = sisevaCodigo;
        this.sisevaPerPeso = sisevaPerPeso;
        this.sisevaPerOrden = sisevaPerOrden;
        this.sisevaPerActivo = sisevaPerActivo;
        this.arbId=arbId;
        this.clNotas = clNotas;
    }

    // Property accessors
    public Integer getSisevaPerId() {
        return this.sisevaPerId;
    }

    public void setSisevaPerId(Integer sisevaPerId) {
        this.sisevaPerId = sisevaPerId;
    }

    public ClSisEvaDetalle getClSisEvaDetalle() {
        return this.clSisEvaDetalle;
    }

    public void setClSisEvaDetalle(ClSisEvaDetalle clSisEvaDetalle) {
        this.clSisEvaDetalle = clSisEvaDetalle;
    }

    public String getSisevaPerNombre() {
        return this.sisevaPerNombre;
    }

    public void setSisevaPerNombre(String sisevaPerNombre) {
        this.sisevaPerNombre = sisevaPerNombre;
    }

    public String getSisevaPerExaSemana() {
        return this.sisevaPerExaSemana;
    }

    public void setSisevaPerExaSemana(String sisevaPerExaSemana) {
        this.sisevaPerExaSemana = sisevaPerExaSemana;
    }


    public String getSisevaCodigo() {
        return this.sisevaCodigo;
    }

    public void setSisevaCodigo(String sisevaCodigo) {
        this.sisevaCodigo = sisevaCodigo;
    }

    public Double getSisevaPerPeso() {
        return this.sisevaPerPeso;
    }

    public void setSisevaPerPeso(Double sisevaPerPeso) {
        this.sisevaPerPeso = sisevaPerPeso;
    }

    public Integer getSisevaPerOrden() {
        return this.sisevaPerOrden;
    }

    public void setSisevaPerOrden(Integer sisevaPerOrden) {
        this.sisevaPerOrden = sisevaPerOrden;
    }

    public String getSisevaPerActivo() {
        return this.sisevaPerActivo;
    }

    public void setSisevaPerActivo(String sisevaPerActivo) {
        this.sisevaPerActivo = sisevaPerActivo;
    }

    public Set getClNotas() {
        return this.clNotas;
    }

    public void setClNotas(Set clNotas) {
        this.clNotas = clNotas;
    }

    public Integer getArbId() {
        return arbId;
    }

    public void setArbId(Integer arbId) {
        this.arbId = arbId;
    }
    
}
