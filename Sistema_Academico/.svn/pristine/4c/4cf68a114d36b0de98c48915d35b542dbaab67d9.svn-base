package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_actas table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_actas"
 */

public abstract class BaseAcActas  implements Serializable {

	public static String REF = "AcActas";
	public static String PROP_ACT_VIGENTE = "ActVigente";
	public static String PROP_ACT_DESCRIPCION = "ActDescripcion";
	public static String PROP_ACT_CREACION = "ActCreacion";
	public static String PROP_ID = "Id";
	public static String PROP_ACT_ACTIVO = "ActActivo";


	// constructors
	public BaseAcActas () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcActas (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String actVigente;
	private java.util.Date actCreacion;
	private java.lang.String actDescripcion;
	private java.lang.String actActivo;

	// collections
	private java.util.Set<net.uch.mapping.AcActaDetalle> acActaDetalles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="act_id"
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
	 * Return the value associated with the column: act_vigente
	 */
	public java.lang.String getActVigente () {
		return actVigente;
	}

	/**
	 * Set the value related to the column: act_vigente
	 * @param actVigente the act_vigente value
	 */
	public void setActVigente (java.lang.String actVigente) {
		this.actVigente = actVigente;
	}



	/**
	 * Return the value associated with the column: act_creacion
	 */
	public java.util.Date getActCreacion () {
		return actCreacion;
	}

	/**
	 * Set the value related to the column: act_creacion
	 * @param actCreacion the act_creacion value
	 */
	public void setActCreacion (java.util.Date actCreacion) {
		this.actCreacion = actCreacion;
	}



	/**
	 * Return the value associated with the column: act_descripcion
	 */
	public java.lang.String getActDescripcion () {
		return actDescripcion;
	}

	/**
	 * Set the value related to the column: act_descripcion
	 * @param actDescripcion the act_descripcion value
	 */
	public void setActDescripcion (java.lang.String actDescripcion) {
		this.actDescripcion = actDescripcion;
	}



	/**
	 * Return the value associated with the column: act_activo
	 */
	public java.lang.String getActActivo () {
		return actActivo;
	}

	/**
	 * Set the value related to the column: act_activo
	 * @param actActivo the act_activo value
	 */
	public void setActActivo (java.lang.String actActivo) {
		this.actActivo = actActivo;
	}



	/**
	 * Return the value associated with the column: AcActaDetalles
	 */
	public java.util.Set<net.uch.mapping.AcActaDetalle> getAcActaDetalles () {
		return acActaDetalles;
	}

	/**
	 * Set the value related to the column: AcActaDetalles
	 * @param acActaDetalles the AcActaDetalles value
	 */
	public void setAcActaDetalles (java.util.Set<net.uch.mapping.AcActaDetalle> acActaDetalles) {
		this.acActaDetalles = acActaDetalles;
	}

	public void addToAcActaDetalles (net.uch.mapping.AcActaDetalle acActaDetalle) {
		if (null == getAcActaDetalles()) setAcActaDetalles(new java.util.TreeSet<net.uch.mapping.AcActaDetalle>());
		getAcActaDetalles().add(acActaDetalle);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcActas)) return false;
		else {
			net.uch.mapping.AcActas acActas = (net.uch.mapping.AcActas) obj;
			if (null == this.getId() || null == acActas.getId()) return false;
			else return (this.getId().equals(acActas.getId()));
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