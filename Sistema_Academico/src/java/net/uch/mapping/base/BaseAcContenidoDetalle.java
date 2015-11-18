package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_contenido_detalle table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_contenido_detalle"
 */

public abstract class BaseAcContenidoDetalle  implements Serializable {

	public static String REF = "AcContenidoDetalle";
	public static String PROP_CONDET_CONTENIDO = "CondetContenido";
	public static String PROP_CONDET_SEMANA = "CondetSemana";
	public static String PROP_ID = "Id";
	public static String PROP_CONTEM = "Contem";


	// constructors
	public BaseAcContenidoDetalle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcContenidoDetalle (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcContenidoDetalle (
		java.lang.Integer id,
		net.uch.mapping.AcContenidoTematico contem) {

		this.setId(id);
		this.setContem(contem);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String condetContenido;
	private java.lang.String condetSemana;

	// many to one
	private net.uch.mapping.AcContenidoTematico contem;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="condet_id"
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
	 * Return the value associated with the column: condet_contenido
	 */
	public java.lang.String getCondetContenido () {
		return condetContenido;
	}

	/**
	 * Set the value related to the column: condet_contenido
	 * @param condetContenido the condet_contenido value
	 */
	public void setCondetContenido (java.lang.String condetContenido) {
		this.condetContenido = condetContenido;
	}



	/**
	 * Return the value associated with the column: condet_semana
	 */
	public java.lang.String getCondetSemana () {
		return condetSemana;
	}

	/**
	 * Set the value related to the column: condet_semana
	 * @param condetSemana the condet_semana value
	 */
	public void setCondetSemana (java.lang.String condetSemana) {
		this.condetSemana = condetSemana;
	}



	/**
	 * Return the value associated with the column: contem_id
	 */
	public net.uch.mapping.AcContenidoTematico getContem () {
		return contem;
	}

	/**
	 * Set the value related to the column: contem_id
	 * @param contem the contem_id value
	 */
	public void setContem (net.uch.mapping.AcContenidoTematico contem) {
		this.contem = contem;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcContenidoDetalle)) return false;
		else {
			net.uch.mapping.AcContenidoDetalle acContenidoDetalle = (net.uch.mapping.AcContenidoDetalle) obj;
			if (null == this.getId() || null == acContenidoDetalle.getId()) return false;
			else return (this.getId().equals(acContenidoDetalle.getId()));
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