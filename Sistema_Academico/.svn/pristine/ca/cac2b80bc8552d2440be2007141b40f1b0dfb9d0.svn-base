package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_disponibilidad table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_disponibilidad"
 */

public abstract class BaseAcDisponibilidad  implements Serializable {

	public static String REF = "AcDisponibilidad";
	public static String PROP_DIS_OBSERVACION = "DisObservacion";
	public static String PROP_DOC = "Doc";
	public static String PROP_DIS_DIA = "DisDia";
	public static String PROP_DIS_ACTIVO = "DisActivo";
	public static String PROP_ID = "Id";
	public static String PROP_TURDET_ID = "TurdetId";


	// constructors
	public BaseAcDisponibilidad () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcDisponibilidad (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcDisponibilidad (
		java.lang.Integer id,
		net.uch.mapping.AcDocente doc,
		java.lang.Integer turdetId) {

		this.setId(id);
		this.setDoc(doc);
		this.setTurdetId(turdetId);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String disObservacion;
	private java.lang.Integer turdetId;
	private java.lang.String disActivo;
	private java.lang.String disDia;

	// many to one
	private net.uch.mapping.AcDocente doc;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="dis_id"
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
	 * Return the value associated with the column: dis_observacion
	 */
	public java.lang.String getDisObservacion () {
		return disObservacion;
	}

	/**
	 * Set the value related to the column: dis_observacion
	 * @param disObservacion the dis_observacion value
	 */
	public void setDisObservacion (java.lang.String disObservacion) {
		this.disObservacion = disObservacion;
	}



	/**
	 * Return the value associated with the column: turdet_id
	 */
	public java.lang.Integer getTurdetId () {
		return turdetId;
	}

	/**
	 * Set the value related to the column: turdet_id
	 * @param turdetId the turdet_id value
	 */
	public void setTurdetId (java.lang.Integer turdetId) {
		this.turdetId = turdetId;
	}



	/**
	 * Return the value associated with the column: dis_activo
	 */
	public java.lang.String getDisActivo () {
		return disActivo;
	}

	/**
	 * Set the value related to the column: dis_activo
	 * @param disActivo the dis_activo value
	 */
	public void setDisActivo (java.lang.String disActivo) {
		this.disActivo = disActivo;
	}



	/**
	 * Return the value associated with the column: dis_dia
	 */
	public java.lang.String getDisDia () {
		return disDia;
	}

	/**
	 * Set the value related to the column: dis_dia
	 * @param disDia the dis_dia value
	 */
	public void setDisDia (java.lang.String disDia) {
		this.disDia = disDia;
	}



	/**
	 * Return the value associated with the column: doc_id
	 */
	public net.uch.mapping.AcDocente getDoc () {
		return doc;
	}

	/**
	 * Set the value related to the column: doc_id
	 * @param doc the doc_id value
	 */
	public void setDoc (net.uch.mapping.AcDocente doc) {
		this.doc = doc;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcDisponibilidad)) return false;
		else {
			net.uch.mapping.AcDisponibilidad acDisponibilidad = (net.uch.mapping.AcDisponibilidad) obj;
			if (null == this.getId() || null == acDisponibilidad.getId()) return false;
			else return (this.getId().equals(acDisponibilidad.getId()));
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