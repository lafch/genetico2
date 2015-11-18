package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ad_estructura_pagos_detalle table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_estructura_pagos_detalle"
 */

public abstract class BaseAdEstructuraPagosDetalle  implements Serializable {

	public static String REF = "AdEstructuraPagosDetalle";
	public static String PROP_ESTPAGDET_FECHA_PAGO = "EstpagdetFechaPago";
	public static String PROP_CONPAG_ID = "ConpagId";
	public static String PROP_ESTPAGDET_ACTIVO = "EstpagdetActivo";
	public static String PROP_ESTPAGDET_MONTO = "EstpagdetMonto";
	public static String PROP_ESTPAG = "Estpag";
	public static String PROP_ID = "Id";
	public static String PROP_ESTPAGDET_NOMBRE = "EstpagdetNombre";


	// constructors
	public BaseAdEstructuraPagosDetalle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdEstructuraPagosDetalle (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAdEstructuraPagosDetalle (
		java.lang.Integer id,
		net.uch.mapping.AdEstructuraPagos estpag) {

		this.setId(id);
		this.setEstpag(estpag);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String estpagdetNombre;
	private java.lang.Integer conpagId;
	private java.util.Date estpagdetFechaPago;
	private java.lang.Float estpagdetMonto;
	private java.lang.String estpagdetActivo;

	// many to one
	private net.uch.mapping.AdEstructuraPagos estpag;

	// collections
	private java.util.Set<net.uch.mapping.AdAlumnoTarifa> adAlumnoTarifas;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="estpagdet_id"
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
	 * Return the value associated with the column: estpagdet_nombre
	 */
	public java.lang.String getEstpagdetNombre () {
		return estpagdetNombre;
	}

	/**
	 * Set the value related to the column: estpagdet_nombre
	 * @param estpagdetNombre the estpagdet_nombre value
	 */
	public void setEstpagdetNombre (java.lang.String estpagdetNombre) {
		this.estpagdetNombre = estpagdetNombre;
	}



	/**
	 * Return the value associated with the column: conpag_id
	 */
	public java.lang.Integer getConpagId () {
		return conpagId;
	}

	/**
	 * Set the value related to the column: conpag_id
	 * @param conpagId the conpag_id value
	 */
	public void setConpagId (java.lang.Integer conpagId) {
		this.conpagId = conpagId;
	}



	/**
	 * Return the value associated with the column: estpagdet_fecha_pago
	 */
	public java.util.Date getEstpagdetFechaPago () {
		return estpagdetFechaPago;
	}

	/**
	 * Set the value related to the column: estpagdet_fecha_pago
	 * @param estpagdetFechaPago the estpagdet_fecha_pago value
	 */
	public void setEstpagdetFechaPago (java.util.Date estpagdetFechaPago) {
		this.estpagdetFechaPago = estpagdetFechaPago;
	}



	/**
	 * Return the value associated with the column: estpagdet_monto
	 */
	public java.lang.Float getEstpagdetMonto () {
		return estpagdetMonto;
	}

	/**
	 * Set the value related to the column: estpagdet_monto
	 * @param estpagdetMonto the estpagdet_monto value
	 */
	public void setEstpagdetMonto (java.lang.Float estpagdetMonto) {
		this.estpagdetMonto = estpagdetMonto;
	}



	/**
	 * Return the value associated with the column: estpagdet_activo
	 */
	public java.lang.String getEstpagdetActivo () {
		return estpagdetActivo;
	}

	/**
	 * Set the value related to the column: estpagdet_activo
	 * @param estpagdetActivo the estpagdet_activo value
	 */
	public void setEstpagdetActivo (java.lang.String estpagdetActivo) {
		this.estpagdetActivo = estpagdetActivo;
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



	/**
	 * Return the value associated with the column: AdAlumnoTarifas
	 */
	public java.util.Set<net.uch.mapping.AdAlumnoTarifa> getAdAlumnoTarifas () {
		return adAlumnoTarifas;
	}

	/**
	 * Set the value related to the column: AdAlumnoTarifas
	 * @param adAlumnoTarifas the AdAlumnoTarifas value
	 */
	public void setAdAlumnoTarifas (java.util.Set<net.uch.mapping.AdAlumnoTarifa> adAlumnoTarifas) {
		this.adAlumnoTarifas = adAlumnoTarifas;
	}

	public void addToAdAlumnoTarifas (net.uch.mapping.AdAlumnoTarifa adAlumnoTarifa) {
		if (null == getAdAlumnoTarifas()) setAdAlumnoTarifas(new java.util.TreeSet<net.uch.mapping.AdAlumnoTarifa>());
		getAdAlumnoTarifas().add(adAlumnoTarifa);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AdEstructuraPagosDetalle)) return false;
		else {
			net.uch.mapping.AdEstructuraPagosDetalle adEstructuraPagosDetalle = (net.uch.mapping.AdEstructuraPagosDetalle) obj;
			if (null == this.getId() || null == adEstructuraPagosDetalle.getId()) return false;
			else return (this.getId().equals(adEstructuraPagosDetalle.getId()));
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