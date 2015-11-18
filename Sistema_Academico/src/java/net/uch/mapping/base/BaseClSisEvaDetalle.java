package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;
import net.uch.mapping.ClSisEvaluacion;

/**
 * AbstractClSisEvaDetalle entity provides the base persistence definition of
 * the ClSisEvaDetalle entity.
 *
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseClSisEvaDetalle implements java.io.Serializable {

    // Fields
    private Integer sisevaDetalleId;
    private Integer sisevaDetOrden;
    private ClSisEvaluacion clSisEvaluacion;
    private String sisevaDetalleCodigo;
    private String sisevaDetalleNombre;
    private Double sisevaDetallePeso;
    private String sisevaDetalleActivo;
    private String sisevaDetalleTipo;
    private String sisevaDetalleSusti;
    private Set clSisEvaPersonalizados = new HashSet( 0 );
    private Set clHorarias = new HashSet( 0 );

    // Constructors
    /**
     * default constructor
     */
    public BaseClSisEvaDetalle() {
    }

    /**
     * Constructor for primary key
     */
    public BaseClSisEvaDetalle( Integer sisevaDetalleId ) {
        this.sisevaDetalleId = sisevaDetalleId;
    }

    /**
     * minimal constructor
     */
    public BaseClSisEvaDetalle( ClSisEvaluacion clSisEvaluacion ) {
        this.clSisEvaluacion = clSisEvaluacion;
    }

    /**
     * full constructor
     */
    public BaseClSisEvaDetalle( ClSisEvaluacion clSisEvaluacion,
            String sisevaDetalleCodigo, String sisevaDetalleNombre,
            Double sisevaDetallePeso, String sisevaDetalleActivo,
            String sisevaDetalleSusti, Set clSisEvaPersonalizados ) {
        this.clSisEvaluacion = clSisEvaluacion;
        this.sisevaDetalleCodigo = sisevaDetalleCodigo;
        this.sisevaDetalleNombre = sisevaDetalleNombre;
        this.sisevaDetallePeso = sisevaDetallePeso;
        this.sisevaDetalleActivo = sisevaDetalleActivo;
        this.sisevaDetalleSusti = sisevaDetalleSusti;
        this.clSisEvaPersonalizados = clSisEvaPersonalizados;
    }

    public BaseClSisEvaDetalle( Integer sisevaDetalleId,
            ClSisEvaluacion clSisEvaluacion, String sisevaDetalleCodigo,
            String sisevaDetalleNombre, Double sisevaDetallePeso,
            String sisevaDetalleActivo, String sisevaDetalleTipo,
            String sisevaDetalleSusti ) {
        this.sisevaDetalleId = sisevaDetalleId;
        this.clSisEvaluacion = clSisEvaluacion;
        this.sisevaDetalleCodigo = sisevaDetalleCodigo;
        this.sisevaDetalleNombre = sisevaDetalleNombre;
        this.sisevaDetallePeso = sisevaDetallePeso;
        this.sisevaDetalleActivo = sisevaDetalleActivo;
        this.sisevaDetalleTipo = sisevaDetalleTipo;
        this.sisevaDetalleSusti = sisevaDetalleSusti;
    }

    // Property accessors
    public Integer getSisevaDetalleId() {
        return this.sisevaDetalleId;
    }

    public void setSisevaDetalleId( Integer sisevaDetalleId ) {
        this.sisevaDetalleId = sisevaDetalleId;
    }

    public ClSisEvaluacion getClSisEvaluacion() {
        return this.clSisEvaluacion;
    }

    public void setClSisEvaluacion( ClSisEvaluacion clSisEvaluacion ) {
        this.clSisEvaluacion = clSisEvaluacion;
    }

    public String getSisevaDetalleCodigo() {
        return this.sisevaDetalleCodigo;
    }

    public void setSisevaDetalleCodigo( String sisevaDetalleCodigo ) {
        this.sisevaDetalleCodigo = sisevaDetalleCodigo;
    }

    public String getSisevaDetalleNombre() {
        return this.sisevaDetalleNombre;
    }

    public void setSisevaDetalleNombre( String sisevaDetalleNombre ) {
        this.sisevaDetalleNombre = sisevaDetalleNombre;
    }

    public Double getSisevaDetallePeso() {
        return this.sisevaDetallePeso;
    }

    public void setSisevaDetallePeso( Double sisevaDetallePeso ) {
        this.sisevaDetallePeso = sisevaDetallePeso;
    }

    public String getSisevaDetalleActivo() {
        return this.sisevaDetalleActivo;
    }

    public void setSisevaDetalleActivo( String sisevaDetalleActivo ) {
        this.sisevaDetalleActivo = sisevaDetalleActivo;
    }

    public String getSisevaDetalleTipo() {
        return this.sisevaDetalleTipo;
    }

    public void setSisevaDetalleTipo( String sisevaDetalleTipo ) {
        this.sisevaDetalleTipo = sisevaDetalleTipo;
    }

    public String getSisevaDetalleSusti() {
        return this.sisevaDetalleSusti;
    }

    public void setSisevaDetalleSusti( String sisevaDetalleSusti ) {
        this.sisevaDetalleSusti = sisevaDetalleSusti;
    }

    public Set getClSisEvaPersonalizados() {
        return this.clSisEvaPersonalizados;
    }

    public void setClSisEvaPersonalizados( Set clSisEvaPersonalizados ) {
        this.clSisEvaPersonalizados = clSisEvaPersonalizados;
    }

    public Integer getSisevaDetOrden() {
        return sisevaDetOrden;
    }

    public void setSisevaDetOrden( Integer sisevaDetOrden ) {
        this.sisevaDetOrden = sisevaDetOrden;
    }

    public Set getClHorarias() {
        return clHorarias;
    }

    public void setClHorarias( Set clHorarias ) {
        this.clHorarias = clHorarias;
    }
}
