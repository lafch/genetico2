package net.uch.mapping.base;

import java.io.Serializable;
import net.uch.mapping.AcSemestre;


/**
 * This is an object that contains data related to the ad_estructura_pagos table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_estructura_pagos"
 */

public abstract class BaseAdEstructuraPagos  implements Serializable {

	public static String REF = "AdEstructuraPagos";
	public static String PROP_ESTPAG_ACTIVO = "EstpagActivo";
	public static String PROP_SEM = "Sem";
	public static String PROP_ESP = "Esp";
	public static String PROP_ESTPAGO_NOMBRE = "EstpagoNombre";
	public static String PROP_ID = "Id";
	public static String PROP_ESTPAGO_PUBLICADO = "EstpagoPublicado";
    public static String PROP_SEM_IN = "SemIn";

	// constructors
	public BaseAdEstructuraPagos () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdEstructuraPagos (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAdEstructuraPagos (
		java.lang.Integer id,
		net.uch.mapping.AcSemestre sem) {

		this.setId(id);
		this.setSem(sem);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String estpagoNombre;
	private java.lang.String estpagoPublicado;
	private java.lang.String estpagActivo;

	// many to one
	private net.uch.mapping.AcEspecialidad esp;
	private net.uch.mapping.AcSemestre sem;

    private net.uch.mapping.AcSemestre semIn;
	// collections
	private java.util.Set<net.uch.mapping.AdEstructuraPagosAfecta> adEstructuraPagosAfectas;
	private java.util.Set<net.uch.mapping.AdEstructuraPagosDetalle> adEstructuraPagosDetalles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="estpag_id"
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
	 * Return the value associated with the column: estpago_nombre
	 */
	public java.lang.String getEstpagoNombre () {
		return estpagoNombre;
	}

	/**
	 * Set the value related to the column: estpago_nombre
	 * @param estpagoNombre the estpago_nombre value
	 */
	public void setEstpagoNombre (java.lang.String estpagoNombre) {
		this.estpagoNombre = estpagoNombre;
	}



	/**
	 * Return the value associated with the column: estpago_publicado
	 */
	public java.lang.String getEstpagoPublicado () {
		return estpagoPublicado;
	}

	/**
	 * Set the value related to the column: estpago_publicado
	 * @param estpagoPublicado the estpago_publicado value
	 */
	public void setEstpagoPublicado (java.lang.String estpagoPublicado) {
		this.estpagoPublicado = estpagoPublicado;
	}



	/**
	 * Return the value associated with the column: estpag_activo
	 */
	public java.lang.String getEstpagActivo () {
		return estpagActivo;
	}

	/**
	 * Set the value related to the column: estpag_activo
	 * @param estpagActivo the estpag_activo value
	 */
	public void setEstpagActivo (java.lang.String estpagActivo) {
		this.estpagActivo = estpagActivo;
	}



	/**
	 * Return the value associated with the column: esp_id
	 */
	public net.uch.mapping.AcEspecialidad getEsp () {
		return esp;
	}

	/**
	 * Set the value related to the column: esp_id
	 * @param esp the esp_id value
	 */
	public void setEsp (net.uch.mapping.AcEspecialidad esp) {
		this.esp = esp;
	}



	/**
	 * Return the value associated with the column: sem_id
	 */
	public net.uch.mapping.AcSemestre getSem () {
		return sem;
	}

	/**
	 * Set the value related to the column: sem_id
	 * @param sem the sem_id value
	 */
	public void setSem (net.uch.mapping.AcSemestre sem) {
		this.sem = sem;
	}

    public AcSemestre getSemIn() {
        return semIn;
    }

    public void setSemIn(AcSemestre semIn) {
        this.semIn = semIn;
    }




	/**
	 * Return the value associated with the column: AdEstructuraPagosAfectas
	 */
	public java.util.Set<net.uch.mapping.AdEstructuraPagosAfecta> getAdEstructuraPagosAfectas () {
		return adEstructuraPagosAfectas;
	}

	/**
	 * Set the value related to the column: AdEstructuraPagosAfectas
	 * @param adEstructuraPagosAfectas the AdEstructuraPagosAfectas value
	 */
	public void setAdEstructuraPagosAfectas (java.util.Set<net.uch.mapping.AdEstructuraPagosAfecta> adEstructuraPagosAfectas) {
		this.adEstructuraPagosAfectas = adEstructuraPagosAfectas;
	}

	public void addToAdEstructuraPagosAfectas (net.uch.mapping.AdEstructuraPagosAfecta adEstructuraPagosAfecta) {
		if (null == getAdEstructuraPagosAfectas()) setAdEstructuraPagosAfectas(new java.util.TreeSet<net.uch.mapping.AdEstructuraPagosAfecta>());
		getAdEstructuraPagosAfectas().add(adEstructuraPagosAfecta);
	}



	/**
	 * Return the value associated with the column: AdEstructuraPagosDetalles
	 */
	public java.util.Set<net.uch.mapping.AdEstructuraPagosDetalle> getAdEstructuraPagosDetalles () {
		return adEstructuraPagosDetalles;
	}

	/**
	 * Set the value related to the column: AdEstructuraPagosDetalles
	 * @param adEstructuraPagosDetalles the AdEstructuraPagosDetalles value
	 */
	public void setAdEstructuraPagosDetalles (java.util.Set<net.uch.mapping.AdEstructuraPagosDetalle> adEstructuraPagosDetalles) {
		this.adEstructuraPagosDetalles = adEstructuraPagosDetalles;
	}

	public void addToAdEstructuraPagosDetalles (net.uch.mapping.AdEstructuraPagosDetalle adEstructuraPagosDetalle) {
		if (null == getAdEstructuraPagosDetalles()) setAdEstructuraPagosDetalles(new java.util.TreeSet<net.uch.mapping.AdEstructuraPagosDetalle>());
		getAdEstructuraPagosDetalles().add(adEstructuraPagosDetalle);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AdEstructuraPagos)) return false;
		else {
			net.uch.mapping.AdEstructuraPagos adEstructuraPagos = (net.uch.mapping.AdEstructuraPagos) obj;
			if (null == this.getId() || null == adEstructuraPagos.getId()) return false;
			else return (this.getId().equals(adEstructuraPagos.getId()));
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