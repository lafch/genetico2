package net.uch.mapping.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the ac_sis_evaluacion table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_sis_evaluacion"
 */
public abstract class BaseAcSisEvaluacion implements Serializable {

    public static String REF = "AcSisEvaluacion";
    public static String PROP_SISEVA_NOMBRE = "SisevaNombre";
    public static String PROP_SISEVA_CODIGO = "SisevaCodigo";
    public static String PROP_SISEVA_CREACION = "SisevaCreacion";
    public static String PROP_ID = "Id";
    public static String PROP_SISEVA_ACTIVO = "SisevaActivo";
    public static String PROP_SISEVA_VIGENTE = "SisevaVigente";
    public static String PROP_SISEVA_ALU_TIPO = "SisevaAluTipo";

    // constructors
    public BaseAcSisEvaluacion() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseAcSisEvaluacion(java.lang.Integer id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer id;
    // fields
    private java.lang.String sisevaCodigo;
    private java.lang.String sisevaNombre;
    private java.lang.String sisevaVigente;
    private java.util.Date sisevaCreacion;
    private java.lang.String sisevaActivo;
    private java.lang.String sisevaAluTipo;
    // collections
    private java.util.Set<net.uch.mapping.AcSisEvaDetalle> acSisEvaDetalles;

    /**
     * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="siseva_id"
     */
    public java.lang.Integer getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     * @param id the new ID
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }

    /**
     * Return the value associated with the column: siseva_codigo
     */
    public java.lang.String getSisevaCodigo() {
        return sisevaCodigo;
    }

    /**
     * Set the value related to the column: siseva_codigo
     * @param sisevaCodigo the siseva_codigo value
     */
    public void setSisevaCodigo(java.lang.String sisevaCodigo) {
        this.sisevaCodigo = sisevaCodigo;
    }

    /**
     * Return the value associated with the column: siseva_nombre
     */
    public java.lang.String getSisevaNombre() {
        return sisevaNombre;
    }

    /**
     * Set the value related to the column: siseva_nombre
     * @param sisevaNombre the siseva_nombre value
     */
    public void setSisevaNombre(java.lang.String sisevaNombre) {
        this.sisevaNombre = sisevaNombre;
    }

    /**
     * Return the value associated with the column: siseva_vigente
     */
    public java.lang.String getSisevaVigente() {
        return sisevaVigente;
    }

    /**
     * Set the value related to the column: siseva_vigente
     * @param sisevaVigente the siseva_vigente value
     */
    public void setSisevaVigente(java.lang.String sisevaVigente) {
        this.sisevaVigente = sisevaVigente;
    }

    /**
     * Return the value associated with the column: siseva_creacion
     */
    public java.util.Date getSisevaCreacion() {
        return sisevaCreacion;
    }

    /**
     * Set the value related to the column: siseva_creacion
     * @param sisevaCreacion the siseva_creacion value
     */
    public void setSisevaCreacion(java.util.Date sisevaCreacion) {
        this.sisevaCreacion = sisevaCreacion;
    }

    /**
     * Return the value associated with the column: siseva_activo
     */
    public java.lang.String getSisevaActivo() {
        return sisevaActivo;
    }

    /**
     * Set the value related to the column: siseva_activo
     * @param sisevaActivo the siseva_activo value
     */
    public void setSisevaActivo(java.lang.String sisevaActivo) {
        this.sisevaActivo = sisevaActivo;
    }

    /**
     * Return the value associated with the column: siseva_alu_tipo
     */
    public String getSisevaAluTipo() {
        return sisevaAluTipo;
    }

    /**
     * Set the value related to the column: siseva_alu_tipo
     * @param sisevaAluTipo the siseva_alu_tipo
     */
    public void setSisevaAluTipo(String sisevaAluTipo) {
        this.sisevaAluTipo = sisevaAluTipo;
    }

    /**
     * Return the value associated with the column: AcSisEvaDetalles
     */
    public java.util.Set<net.uch.mapping.AcSisEvaDetalle> getAcSisEvaDetalles() {
        return acSisEvaDetalles;
    }

    /**
     * Set the value related to the column: AcSisEvaDetalles
     * @param acSisEvaDetalles the AcSisEvaDetalles value
     */
    public void setAcSisEvaDetalles(java.util.Set<net.uch.mapping.AcSisEvaDetalle> acSisEvaDetalles) {
        this.acSisEvaDetalles = acSisEvaDetalles;
    }

    public void addToAcSisEvaDetalles(net.uch.mapping.AcSisEvaDetalle acSisEvaDetalle) {
        if (null == getAcSisEvaDetalles()) {
            setAcSisEvaDetalles(new java.util.TreeSet<net.uch.mapping.AcSisEvaDetalle>());
        }
        getAcSisEvaDetalles().add(acSisEvaDetalle);
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof net.uch.mapping.AcSisEvaluacion)) {
            return false;
        } else {
            net.uch.mapping.AcSisEvaluacion acSisEvaluacion = (net.uch.mapping.AcSisEvaluacion) obj;
            if (null == this.getId() || null == acSisEvaluacion.getId()) {
                return false;
            } else {
                return (this.getId().equals(acSisEvaluacion.getId()));
            }
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getId()) {
                return super.hashCode();
            } else {
                String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }

    public String toString() {
        return super.toString();
    }
}
