/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping.base;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author LUIS
 */
public class BaseAdConceptoPagoDetalle implements Serializable {
    
    public static String REF = "AdConceptoPagoDetalle";
	public static String PROP_CONPAG_MONTO_DET = "ConpagMontoDet";
	public static String PROP_CONPAG_ACTIVO_DET = "ConpagActivoDet";
        public static String PROP_CONPAG_VISIBLE_DET = "ConpagAVisibleDet";
	public static String PROP_CONPAG_FECHA_DET = "ConpagFechaDet";
	public static String PROP_CONPAG_DESC_DET = "ConpagDescDet";
	public static String PROP_ID = "Id";

    public BaseAdConceptoPagoDetalle() {
        initialize();
    }
    
    public BaseAdConceptoPagoDetalle (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}
    protected void initialize () {}
    
    private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String conpagMontoDet;
	private java.lang.Float conpagActivoDet;
	private java.lang.String conpagVisibleDet;
	private java.util.Date conpagFechaDet;
	private java.lang.String conpagDescDet;

	// collections


    public Float getConpagActivoDet() {
        return conpagActivoDet;
    }

    public void setConpagActivoDet(Float conpagActivoDet) {
        this.conpagActivoDet = conpagActivoDet;
    }

    public String getConpagDescDet() {
        return conpagDescDet;
    }

    public void setConpagDescDet(String conpagDescDet) {
        this.conpagDescDet = conpagDescDet;
    }

    public Date getConpagFechaDet() {
        return conpagFechaDet;
    }

    public void setConpagFechaDet(Date conpagFechaDet) {
        this.conpagFechaDet = conpagFechaDet;
    }

    public String getConpagMontoDet() {
        return conpagMontoDet;
    }

    public void setConpagMontoDet(String conpagMontoDet) {
        this.conpagMontoDet = conpagMontoDet;
    }

    public String getConpagVisibleDet() {
        return conpagVisibleDet;
    }

    public void setConpagVisibleDet(String conpagVisibleDet) {
        this.conpagVisibleDet = conpagVisibleDet;
    }

    public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
        public String toString () {
		return super.toString();
	}
	
    
        
}
