/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.mapping.base;

import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class BaseAcParametro implements Serializable{
    public static String REF = "AcParametro";
    public static String PROP_ID = "Id";
    public static String PROP_PAR_COD = "ParCod";
    public static String PROP_PAR_VALOR = "ParVal";
    public static String PROP_PAR_DESC = "ParDes";
    public static String PROP_PAR_ACTIVO = "ParActivo";
     public static String PROP_PAR_USUARIO = "ParUsuario";
    

    // constructors
    public BaseAcParametro() {
        initialize();
    }

    /**
     * Constructor for primary key
     * @param id
     */
    public BaseAcParametro( java.lang.Integer id ) {
        this.setId( id );
        initialize();
    }

    /**
     * Constructor for required fields
     * @param id
     * @param pagCod
     * @param pagVal
     * @param parDes
     * @param parActivo
     * @param parUsuario
     */
    public BaseAcParametro(
            java.lang.Integer id,
            java.lang.String pagCod,
            java.lang.String pagVal,
            java.lang.String parDes,
            java.lang.String parActivo,
            java.lang.Integer parUsuario
            ) {

        this.setId( id );
        this.setParCod( pagCod );
        this.setParVal( pagVal );
        this.setParDes( parDes );
        this.setParActivo( parActivo );
        this.setParUsuario(parUsuario);
        initialize();
    }

    protected void initialize() {
    }
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer id;
    // fields
    private java.lang.String parCod;
    private java.lang.String parVal;
    private java.lang.String parDes;
    private java.lang.String parActivo;
    private java.lang.Integer parUsuario;


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
    public java.lang.String getParCod() {
        return parCod;
    }

    public void setParCod( java.lang.String parCod ) {
        this.parCod = parCod;
    }
    
    public java.lang.String getParVal() {
        return parVal;
    }

    public void setParVal( java.lang.String parVal ) {
        this.parVal = parVal;
    }
    
    public java.lang.String getParDes() {
        return parDes;
    }

    public void setParDes( java.lang.String parDes ) {
        this.parDes = parDes;
    }

    public java.lang.String getParActivo() {
        return parActivo;
    }

    public void setParActivo( java.lang.String parActivo ) {
        this.parActivo = parActivo;
    }

    public Integer getParUsuario() {
        return parUsuario;
    }

    public void setParUsuario(Integer parUsuario) {
        this.parUsuario = parUsuario;
    }
    
    

    public boolean equals( Object obj ) {
        if ( null == obj ) {
            return false;
        }
        if ( !( obj instanceof net.uch.mapping.AcAula ) ) {
            return false;
        } else {
            net.uch.mapping.AcParametro acPagina = (net.uch.mapping.AcParametro)obj;
            if ( null == this.getId() || null == acPagina.getId() ) {
                return false;
            } else {
                return ( this.getId().equals( acPagina.getId() ) );
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
