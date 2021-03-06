package net.uch.mapping.base;

import java.io.Serializable;
import net.uch.mapping.AcEspecialidad;

/**
 * This is an object that contains data related to the ac_aula table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="ac_aula"
 */
public abstract class BaseAcAula implements Serializable {

    public static String REF = "AcAula";
    public static String PROP_PAB = "Pab";
    public static String PROP_AUL_ACTIVO = "AulActivo";
    public static String PROP_AUL_CAP = "AulCap";
    public static String PROP_ID = "Id";
    public static String PROP_AUL_DES = "AulDes";
    public static String PRO_ESP = "Esp";
    public static String PRO_AUL_TIPO = "AulTipo";
    

    // constructors
    public BaseAcAula() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseAcAula( java.lang.Integer id ) {
        this.setId( id );
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseAcAula(
            java.lang.Integer id,
            net.uch.mapping.AcPabellon pab,
            java.lang.String aulDes,
            java.lang.Integer aulCap,
            java.lang.String aulActivo, net.uch.mapping.AcEspecialidad esp, java.lang.String aulTipo) {

        this.setId( id );
        this.setPab( pab );
        this.setAulDes( aulDes );
        this.setAulCap( aulCap );
        this.setAulActivo( aulActivo );
        this.setAulTipo(aulTipo);
        initialize();
    }

    protected void initialize() {
    }
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer id;
    // fields
    private java.lang.String aulDes;
    private java.lang.Integer aulCap;
    private java.lang.String aulActivo;
    private java.lang.String aulTipo;
    // many to one
    private net.uch.mapping.AcPabellon pab;
    private net.uch.mapping.AcEspecialidad esp;
    // collections
    private java.util.Set<net.uch.mapping.AcHorario> acHorarios;
    private java.lang.String v_imagen_horario;
   

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="native" column="aul_id"
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

    /**
     * Return the value associated with the column: aul_des
     */
    public java.lang.String getAulDes() {
        return aulDes;
    }

    /**
     * Set the value related to the column: aul_des
     *
     * @param aulDes the aul_des value
     */
    public void setAulDes( java.lang.String aulDes ) {
        this.aulDes = aulDes;
    }

    /**
     * Return the value associated with the column: aul_cap
     */
    public java.lang.Integer getAulCap() {
        return aulCap;
    }

    /**
     * Set the value related to the column: aul_cap
     *
     * @param aulCap the aul_cap value
     */
    public void setAulCap( java.lang.Integer aulCap ) {
        this.aulCap = aulCap;
    }

    /**
     * Return the value associated with the column: aul_activo
     */
    public java.lang.String getAulActivo() {
        return aulActivo;
    }

    /**
     * Set the value related to the column: aul_activo
     *
     * @param aulActivo the aul_activo value
     */
    public void setAulActivo( java.lang.String aulActivo ) {
        this.aulActivo = aulActivo;
    }

    public String getAulTipo() {
        return aulTipo;
    }

    public void setAulTipo(String aulTipo) {
        this.aulTipo = aulTipo;
    }

    /**
     * Return the value associated with the column: pab_id
     */
    public net.uch.mapping.AcPabellon getPab() {
        return pab;
    }

    /**
     * Set the value related to the column: pab_id
     *
     * @param pab the pab_id value
     */
    public void setPab( net.uch.mapping.AcPabellon pab ) {
        this.pab = pab;
    }

    public net.uch.mapping.AcEspecialidad getEsp() {
        return esp;
    }

    public void setEsp( net.uch.mapping.AcEspecialidad esp ) {
        this.esp = esp;
    }

    /**
     * Return the value associated with the column: AcHorarios
     */
    public java.util.Set<net.uch.mapping.AcHorario> getAcHorarios() {
        return acHorarios;
    }

    /**
     * Set the value related to the column: AcHorarios
     *
     * @param acHorarios the AcHorarios value
     */
    public void setAcHorarios( java.util.Set<net.uch.mapping.AcHorario> acHorarios ) {
        this.acHorarios = acHorarios;
    }

    public void addToAcHorarios( net.uch.mapping.AcHorario acHorario ) {
        if ( null == getAcHorarios() ) {
            setAcHorarios( new java.util.TreeSet<net.uch.mapping.AcHorario>() );
        }
        getAcHorarios().add( acHorario );
    }

    public boolean equals( Object obj ) {
        if ( null == obj ) {
            return false;
        }
        if ( !( obj instanceof net.uch.mapping.AcAula ) ) {
            return false;
        } else {
            net.uch.mapping.AcAula acAula = (net.uch.mapping.AcAula)obj;
            if ( null == this.getId() || null == acAula.getId() ) {
                return false;
            } else {
                return ( this.getId().equals( acAula.getId() ) );
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
    
     public String getImagen_horario() {
        return v_imagen_horario;
    }

    public void setImagen_horario( String imagen_horario ) {
        this.v_imagen_horario = imagen_horario;
    }
}