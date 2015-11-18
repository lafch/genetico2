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
public class BaseAcPagina implements Serializable{
    public static String REF = "AcPagina";
    public static String PROP_PAG_ACTIVO = "PagActivo";
    public static String PROP_PAG_URL = "PagUrl";
    public static String PROP_ID = "Id";
    public static String PROP_PAG_DES = "PagDes";

    // constructors
    public BaseAcPagina() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseAcPagina( java.lang.Integer id ) {
        this.setId( id );
        initialize();
    }

    /**
     * Constructor for required fields
     */
    public BaseAcPagina(
            java.lang.Integer id,
            java.lang.String pagDes,
            java.lang.String pagUrl,
            java.lang.String pagActivo) {

        this.setId( id );

        this.setPagDes( pagDes );
        this.setPagUrl( pagUrl );
        this.setPagActivo( pagActivo );
        initialize();
    }

    protected void initialize() {
    }
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer id;
    // fields
    private java.lang.String pagDes;
    private java.lang.String pagUrl;
    private java.lang.String pagActivo;


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
    public java.lang.String getPagDes() {
        return pagDes;
    }

    /**
     * Set the value related to the column: aul_des
     *
     * @param aulDes the aul_des value
     */
    public void setPagDes( java.lang.String pagDes ) {
        this.pagDes = pagDes;
    }

    /**
     * Return the value associated with the column: aul_cap
     */
    public java.lang.String getPagUrl() {
        return pagUrl;
    }

    /**
     * Set the value related to the column: aul_cap
     *
     * @param aulCap the aul_cap value
     */
    public void setPagUrl( java.lang.String pagUrl ) {
        this.pagUrl = pagUrl;
    }

    /**
     * Return the value associated with the column: aul_activo
     */
    public java.lang.String getPagActivo() {
        return pagActivo;
    }

    /**
     * Set the value related to the column: aul_activo
     *
     * @param aulActivo the aul_activo value
     */
    public void setPagActivo( java.lang.String pagActivo ) {
        this.pagActivo = pagActivo;
    }

    public boolean equals( Object obj ) {
        if ( null == obj ) {
            return false;
        }
        if ( !( obj instanceof net.uch.mapping.AcAula ) ) {
            return false;
        } else {
            net.uch.mapping.AcPagina acPagina = (net.uch.mapping.AcPagina)obj;
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
