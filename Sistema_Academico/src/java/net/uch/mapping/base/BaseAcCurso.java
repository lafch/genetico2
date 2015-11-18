package net.uch.mapping.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the ac_curso table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="ac_curso"
 */
public abstract class BaseAcCurso implements Serializable {

    public static String REF = "AcCurso";
    public static String PROP_CUR_CODIGO = "CurCodigo";
    public static String PROP_ESP = "Esp";
    public static String PROP_CUR_ACTIVO = "CurActivo";
    public static String PROP_ID = "Id";
    public static String PROP_CUR_NOMBRE = "CurNombre";
    
    public static String PROP_CUR_HORLAB ="";
    public static String PROP_CUR_HORTEO ="";

    // constructors
    public BaseAcCurso() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseAcCurso( java.lang.Integer id ) {
        this.setId( id );
        initialize();
    }

    protected void initialize() {
    }
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer id;
    // fields
    private java.lang.String curCodigo;
    private java.lang.String curNombre;
    private java.lang.String curActivo;
    // fields agregados para generacion de horarios
    private java.lang.Integer curHorLab;
    private java.lang.Integer curHorTeo;
    // many to one
    private net.uch.mapping.AcEspecialidad esp;
    // collections
    private java.util.Set<net.uch.mapping.AcPlanCurso> acPlanCursos;

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="native" column="cur_id"
     */
    public java.lang.Integer getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param id the new ID
     */
    public void setId( java.lang.Integer id ) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }

    public Integer getCurHorLab() {
        return curHorLab;
    }

    public void setCurHorLab( java.lang.Integer curHorLab ) {
        this.curHorLab = curHorLab;
    }

    public Integer getCurHorTeo() {
        return curHorTeo;
    }

    public void setCurHorTeo( java.lang.Integer curHorTeo ) {
        this.curHorTeo = curHorTeo;
    }

    /**
     * Return the value associated with the column: cur_codigo
     */
    public java.lang.String getCurCodigo() {
        return curCodigo;
    }

    /**
     * Set the value related to the column: cur_codigo
     *
     * @param curCodigo the cur_codigo value
     */
    public void setCurCodigo( java.lang.String curCodigo ) {
        this.curCodigo = curCodigo;
    }

    /**
     * Return the value associated with the column: cur_nombre
     */
    public java.lang.String getCurNombre() {
        return curNombre;
    }

    /**
     * Set the value related to the column: cur_nombre
     *
     * @param curNombre the cur_nombre value
     */
    public void setCurNombre( java.lang.String curNombre ) {
        this.curNombre = curNombre;
    }

    /**
     * Return the value associated with the column: cur_activo
     */
    public java.lang.String getCurActivo() {
        return curActivo;
    }

    /**
     * Set the value related to the column: cur_activo
     *
     * @param curActivo the cur_activo value
     */
    public void setCurActivo( java.lang.String curActivo ) {
        this.curActivo = curActivo;
    }

    /**
     * Return the value associated with the column: esp_id
     */
    public net.uch.mapping.AcEspecialidad getEsp() {
        return esp;
    }

    /**
     * Set the value related to the column: esp_id
     *
     * @param esp the esp_id value
     */
    public void setEsp( net.uch.mapping.AcEspecialidad esp ) {
        this.esp = esp;
    }

    /**
     * Return the value associated with the column: AcPlanCursos
     */
    public java.util.Set<net.uch.mapping.AcPlanCurso> getAcPlanCursos() {
        return acPlanCursos;
    }

    /**
     * Set the value related to the column: AcPlanCursos
     *
     * @param acPlanCursos the AcPlanCursos value
     */
    public void setAcPlanCursos( java.util.Set<net.uch.mapping.AcPlanCurso> acPlanCursos ) {
        this.acPlanCursos = acPlanCursos;
    }

    public void addToAcPlanCursos( net.uch.mapping.AcPlanCurso acPlanCurso ) {
        if ( null == getAcPlanCursos() ) {
            setAcPlanCursos( new java.util.TreeSet<net.uch.mapping.AcPlanCurso>() );
        }
        getAcPlanCursos().add( acPlanCurso );
    }

    public boolean equals( Object obj ) {
        if ( null == obj ) {
            return false;
        }
        if ( !( obj instanceof net.uch.mapping.AcCurso ) ) {
            return false;
        } else {
            net.uch.mapping.AcCurso acCurso = (net.uch.mapping.AcCurso)obj;
            if ( null == this.getId() || null == acCurso.getId() ) {
                return false;
            } else {
                return ( this.getId().equals( acCurso.getId() ) );
            }
        }
    }

    public int hashCode() {
        if ( Integer.MIN_VALUE == this.hashCode ) {
            if ( null == this.getId() ) {
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