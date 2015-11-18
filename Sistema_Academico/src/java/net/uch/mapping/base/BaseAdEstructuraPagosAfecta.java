package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ad_estructura_pagos_afecta table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_estructura_pagos_afecta"
 */

public abstract class BaseAdEstructuraPagosAfecta  implements Serializable {

	public static String REF = "AdEstructuraPagosAfecta";
	public static String PROP_ESTPAGDETAFE_ACTIVO = "EstpagdetafeActivo";
	public static String PROP_ESTPAGDETAFE_DES = "EstpagdetafeDes";
	public static String PROP_ESTPAGDETAFE_TIPO = "EstpagdetafeTipo";
	public static String PROP_ID = "Id";
	public static String PROP_ESTPAG = "Estpag";


	// constructors
	public BaseAdEstructuraPagosAfecta () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdEstructuraPagosAfecta (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String estpagdetafeTipo;
	private java.lang.String estpagdetafeDes;
	private java.lang.String estpagdetafeActivo;

	// many to one
	private net.uch.mapping.AdEstructuraPagos estpag;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="estpagdetafe_id"
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
	 * Return the value associated with the column: estpagdetafe_tipo
	 */
	public java.lang.String getEstpagdetafeTipo () {
		return estpagdetafeTipo;
	}

	/**
	 * Set the value related to the column: estpagdetafe_tipo
	 * @param estpagdetafeTipo the estpagdetafe_tipo value
	 */
	public void setEstpagdetafeTipo (java.lang.String estpagdetafeTipo) {
		this.estpagdetafeTipo = estpagdetafeTipo;
	}



	/**
	 * Return the value associated with the column: estpagdetafe_des
	 */
	public java.lang.String getEstpagdetafeDes () {
		return estpagdetafeDes;
	}

	/**
	 * Set the value related to the column: estpagdetafe_des
	 * @param estpagdetafeDes the estpagdetafe_des value
	 */
	public void setEstpagdetafeDes (java.lang.String estpagdetafeDes) {
		this.estpagdetafeDes = estpagdetafeDes;
	}



	/**
	 * Return the value associated with the column: estpagdetafe_activo
	 */
	public java.lang.String getEstpagdetafeActivo () {
		return estpagdetafeActivo;
	}

	/**
	 * Set the value related to the column: estpagdetafe_activo
	 * @param estpagdetafeActivo the estpagdetafe_activo value
	 */
	public void setEstpagdetafeActivo (java.lang.String estpagdetafeActivo) {
		this.estpagdetafeActivo = estpagdetafeActivo;
	}



	/**
	 * Return the value associated with the column: estpag_id
	 */
	public net.uch.mapping.AdEstructuraPagos getEstpag () {
		return estpag;
	}

	/**
	 * Set the value related to the column: estpag_id
	 * @param estpag the estpag_id value
	 */
	public void setEstpag (net.uch.mapping.AdEstructuraPagos estpag) {
		this.estpag = estpag;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AdEstructuraPagosAfecta)) return false;
		else {
			net.uch.mapping.AdEstructuraPagosAfecta adEstructuraPagosAfecta = (net.uch.mapping.AdEstructuraPagosAfecta) obj;
			if (null == this.getId() || null == adEstructuraPagosAfecta.getId()) return false;
			else return (this.getId().equals(adEstructuraPagosAfecta.getId()));
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