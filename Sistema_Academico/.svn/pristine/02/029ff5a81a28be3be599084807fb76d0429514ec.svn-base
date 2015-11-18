package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_local table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_local"
 */

public abstract class BaseAcLocal  implements Serializable {

	public static String REF = "AcLocal";
	public static String PROP_LOC_DES = "LocDes";
	public static String PROP_ID = "Id";
	public static String PROP_LOC_ACTIVO = "LocActivo";


	// constructors
	public BaseAcLocal () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcLocal (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcLocal (
		java.lang.Integer id,
		java.lang.String locActivo) {

		this.setId(id);
		this.setLocActivo(locActivo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String locDes;
	private java.lang.String locActivo;

	// collections
	private java.util.Set<net.uch.mapping.AcPabellon> acPabellons;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="loc_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: loc_des
	 */
	public java.lang.String getLocDes () {
		return locDes;
	}

	/**
	 * Set the value related to the column: loc_des
	 * @param locDes the loc_des value
	 */
	public void setLocDes (java.lang.String locDes) {
		this.locDes = locDes;
	}



	/**
	 * Return the value associated with the column: loc_activo
	 */
	public java.lang.String getLocActivo () {
		return locActivo;
	}

	/**
	 * Set the value related to the column: loc_activo
	 * @param locActivo the loc_activo value
	 */
	public void setLocActivo (java.lang.String locActivo) {
		this.locActivo = locActivo;
	}



	/**
	 * Return the value associated with the column: AcPabellons
	 */
	public java.util.Set<net.uch.mapping.AcPabellon> getAcPabellons () {
		return acPabellons;
	}

	/**
	 * Set the value related to the column: AcPabellons
	 * @param acPabellons the AcPabellons value
	 */
	public void setAcPabellons (java.util.Set<net.uch.mapping.AcPabellon> acPabellons) {
		this.acPabellons = acPabellons;
	}

	public void addToAcPabellons (net.uch.mapping.AcPabellon acPabellon) {
		if (null == getAcPabellons()) setAcPabellons(new java.util.TreeSet<net.uch.mapping.AcPabellon>());
		getAcPabellons().add(acPabellon);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcLocal)) return false;
		else {
			net.uch.mapping.AcLocal acLocal = (net.uch.mapping.AcLocal) obj;
			if (null == this.getId() || null == acLocal.getId()) return false;
			else return (this.getId().equals(acLocal.getId()));
		}
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


	public String toString () {
		return super.toString();
	}


}