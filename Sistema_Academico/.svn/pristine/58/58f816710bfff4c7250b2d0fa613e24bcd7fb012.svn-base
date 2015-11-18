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
public class BaseAcTemporal implements Serializable {
    
        public static String REF = "AcTemporal";
	public static String PROP_USU_ID = "UsuId";
	public static String PROP_MEN_ID = "MenId";
	public static String PROP_TEM_FECHA = "TemFecha";
	public static String PROP_ID = "Id";

    public BaseAcTemporal() {
        initialize();
    }
    
    public BaseAcTemporal (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}
    protected void initialize () {}
    
    private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String UsuId;
	private java.util.Date TemFecha;
	private java.lang.String MenId;

	// collections

    public String getUsuId() {
        return UsuId;
    }

    public void setUsuId(String UsuId) {
        this.UsuId = UsuId;
    }
 
    public String getMenId() {
        return MenId;
    }

    public void setMenId(String MenId) {
        this.MenId = MenId;
    }

    public Date getTemFecha() {
        return TemFecha;
    }

    public void setTemFecha(Date TemFecha) {
        this.TemFecha = TemFecha;
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
