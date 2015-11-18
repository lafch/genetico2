package net.uch.mapping.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the ac_sis_eva_personalizado table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_sis_eva_personalizado"
 */
public abstract class BaseAcSisEvaPersonalizado implements Serializable {

    public static String REF = "AcSisEvaPersonalizado";
    public static String PROP_SISEVA_CODIGO = "SisevaCodigo";
    public static String PROP_SISEVA_PER_NOMBRE = "SisevaPerNombre";
    public static String PROP_SISEVA_DETALLE = "SisevaDetalle";
    public static String PROP_CURAPE = "Curape";
    public static String PROP_ID = "Id";
    public static String PROP_SISEVA_PER_PESO = "SisevaPerPeso";
    public static String PROP_SISEVA_PER_EXA_SEMANA = "SisevaPerExaSemana";
    public static String PROP_SISEVA_PER_ACTIVO = "SisevaPerActivo";
    public static String PROP_SISEVA_PER_ORDEN = "SisevaPerOrden";
    
    public static String PROP_SISEVA_PER_TNOTA = "SisevaPerTipoNota";
    public static String PROP_SISEVA_PER_AGRUPAR = "SisevaPerAgrupar";
    public static String PROP_SISEVA_PER_SETAPA = "SisevaPerSemestreEtapa";
    
    public static String PROP_SISEVA_PER_ALU_TIPO = "SisevaPerAluTipo";
    
    

    // constructors
    public BaseAcSisEvaPersonalizado() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseAcSisEvaPersonalizado(java.lang.Integer id) {
        this.setId(id);
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseAcSisEvaPersonalizado(
            java.lang.Integer id,
            net.uch.mapping.AcCursoAperturado curape,
            net.uch.mapping.AcSisEvaDetalle sisevaDetalle) {

        this.setId(id);
        this.setCurape(curape);
        this.setSisevaDetalle(sisevaDetalle);
        initialize();
    }

    protected void initialize() {
    }
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer id;
    // fields
    private java.lang.String sisevaPerNombre;
    private java.lang.String sisevaPerExaSemana;
    private java.lang.String sisevaCodigo;
    private java.lang.String sisevaPerPeso;
    private java.lang.String sisevaPerActivo;
    private java.lang.Integer sisevaPerOrden;
    private java.lang.String sisevaPerAluTipo;
    
    private java.lang.String sisevaPerTipoNota;
    private java.lang.String sisevaPerAgrupar;
    private java.lang.String sisevaPerSemestreEtapa;
    // many to one
    private net.uch.mapping.AcCursoAperturado curape;
    private net.uch.mapping.AcSisEvaDetalle sisevaDetalle;
    // collections
    private java.util.Set<net.uch.mapping.AcNota> acNotas;
    private java.util.Set<net.uch.mapping.ClNota> clNotas;

    /**
     * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="siseva_per_id"
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
     * Return the value associated with the column: siseva_per_nombre
     */
    public java.lang.String getSisevaPerNombre() {
        return sisevaPerNombre;
    }

    /**
     * Set the value related to the column: siseva_per_nombre
     * @param sisevaPerNombre the siseva_per_nombre value
     */
    public void setSisevaPerNombre(java.lang.String sisevaPerNombre) {
        this.sisevaPerNombre = sisevaPerNombre;
    }

    /**
     * Return the value associated with the column: siseva_per_exa_semana
     */
    public java.lang.String getSisevaPerExaSemana() {
        return sisevaPerExaSemana;
    }

    /**
     * Set the value related to the column: siseva_per_exa_semana
     * @param sisevaPerExaSemana the siseva_per_exa_semana value
     */
    public void setSisevaPerExaSemana(java.lang.String sisevaPerExaSemana) {
        this.sisevaPerExaSemana = sisevaPerExaSemana;
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
     * Return the value associated with the column: siseva_per_peso
     */
    public java.lang.String getSisevaPerPeso() {
        return sisevaPerPeso;
    }

    /**
     * Set the value related to the column: siseva_per_peso
     * @param sisevaPerPeso the siseva_per_peso value
     */
    public void setSisevaPerPeso(java.lang.String sisevaPerPeso) {
        this.sisevaPerPeso = sisevaPerPeso;
    }

    /**
     * Return the value associated with the column: siseva_per_activo
     */
    public java.lang.String getSisevaPerActivo() {
        return sisevaPerActivo;
    }

    /**
     * Set the value related to the column: siseva_per_activo
     * @param sisevaPerActivo the siseva_per_activo value
     */
    public void setSisevaPerActivo(java.lang.String sisevaPerActivo) {
        this.sisevaPerActivo = sisevaPerActivo;
    }

    /**
     * Return the value associated with the column: siseva_per_orden
     */
    public java.lang.Integer getSisevaPerOrden() {
        return sisevaPerOrden;
    }

    /**
     * Set the value related to the column: siseva_per_orden
     * @param sisevaPerOrden the siseva_per_orden value
     */
    public void setSisevaPerOrden(java.lang.Integer sisevaPerOrden) {
        this.sisevaPerOrden = sisevaPerOrden;
    }

    /**
     * Return the value associated with the column: siseva_per_alu_tipo
     */
    public String getSisevaPerAluTipo() {
        return sisevaPerAluTipo;
    }

    /**
     * Set the value related to the column: siseva_per_alu_tipo
     * @param sisevaPerOrden the siseva_per_alu_tipo value
     */
    public void setSisevaPerAluTipo(String sisevaPerAluTipo) {
        this.sisevaPerAluTipo = sisevaPerAluTipo;
    }

    /*Implementado para nuevo sistema de evaluacion */
    public String getSisevaPerTipoNota() {
        return sisevaPerTipoNota;
    }

    public void setSisevaPerTipoNota( String sisevaPerTipoNota ) {
        this.sisevaPerTipoNota = sisevaPerTipoNota;
    }

    public String getSisevaPerAgrupar() {
        return sisevaPerAgrupar;
    }

    public void setSisevaPerAgrupar( String sisevaPerAgrupar ) {
        this.sisevaPerAgrupar = sisevaPerAgrupar;
    }

    public String getSisevaPerSemestreEtapa() {
        return sisevaPerSemestreEtapa;
    }

    public void setSisevaPerSemestreEtapa( String sisevaPerSemestreEtapa ) {
        this.sisevaPerSemestreEtapa = sisevaPerSemestreEtapa;
    }
    
    
    

    /**
     * Return the value associated with the column: curape_id
     */
    public net.uch.mapping.AcCursoAperturado getCurape() {
        return curape;
    }

    /**
     * Set the value related to the column: curape_id
     * @param curape the curape_id value
     */
    public void setCurape(net.uch.mapping.AcCursoAperturado curape) {
        this.curape = curape;
    }

    /**
     * Return the value associated with the column: siseva_detalle_id
     */
    public net.uch.mapping.AcSisEvaDetalle getSisevaDetalle() {
        return sisevaDetalle;
    }

    /**
     * Set the value related to the column: siseva_detalle_id
     * @param sisevaDetalle the siseva_detalle_id value
     */
    public void setSisevaDetalle(net.uch.mapping.AcSisEvaDetalle sisevaDetalle) {
        this.sisevaDetalle = sisevaDetalle;
    }

    /**
     * Return the value associated with the column: AcNotas
     */
    public java.util.Set<net.uch.mapping.AcNota> getAcNotas() {
        return acNotas;
    }

    /**
     * Set the value related to the column: AcNotas
     * @param acNotas the AcNotas value
     */
    public void setAcNotas(java.util.Set<net.uch.mapping.AcNota> acNotas) {
        this.acNotas = acNotas;
    }

    /**
     * Return the value associated with the column: ClNotas
     */
    public java.util.Set<net.uch.mapping.ClNota> getClNotas() {
        return clNotas;
    }

    public void addToAcNotas(net.uch.mapping.AcNota acNota) {
        if (null == getAcNotas()) {
            setAcNotas(new java.util.TreeSet<net.uch.mapping.AcNota>());
        }
        getAcNotas().add(acNota);
    }

    /**
     * Set the value related to the column: ClNotas
     * @param clNotas the ClNotas value
     */
    public void setClNotas(java.util.Set<net.uch.mapping.ClNota> clNotas) {
        this.clNotas = clNotas;
    }

    public void addToClNotas(net.uch.mapping.ClNota clNota) {
        if (null == getClNotas()) {
            setClNotas(new java.util.TreeSet<net.uch.mapping.ClNota>());
        }
        getClNotas().add(clNota);
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof net.uch.mapping.AcSisEvaPersonalizado)) {
            return false;
        } else {
            net.uch.mapping.AcSisEvaPersonalizado acSisEvaPersonalizado = (net.uch.mapping.AcSisEvaPersonalizado) obj;
            if (null == this.getId() || null == acSisEvaPersonalizado.getId()) {
                return false;
            } else {
                return (this.getId().equals(acSisEvaPersonalizado.getId()));
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
