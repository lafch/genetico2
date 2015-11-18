package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_ampliaciones table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_ampliaciones"
 */

public abstract class BaseAcAmpliaciones  implements Serializable {

	public static String REF = "AcAmpliaciones";
	public static String PROP_ALU = "Alu";
	public static String PROP_AMP_CREDITOS = "AmpCreditos";
	public static String PROP_AMP_ACTIVO = "AmpActivo";
	public static String PROP_ID = "Id";
	public static String PROP_SEM_ID = "SemId";


	// constructors
	public BaseAcAmpliaciones () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcAmpliaciones (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcAmpliaciones (
		java.lang.Integer id,
		net.uch.mapping.AcAlumno alu) {

		this.setId(id);
		this.setAlu(alu);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String ampCreditos;
	private java.lang.Integer semId;
	private java.lang.String ampActivo;

	// many to one
	private net.uch.mapping.AcAlumno alu;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="amp_id"
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
	 * Return the value associated with the column: amp_creditos
	 */
	public java.lang.String getAmpCreditos () {
		return ampCreditos;
	}

	/**
	 * Set the value related to the column: amp_creditos
	 * @param ampCreditos the amp_creditos value
	 */
	public void setAmpCreditos (java.lang.String ampCreditos) {
		this.ampCreditos = ampCreditos;
	}



	/**
	 * Return the value associated with the column: sem_id
	 */
	public java.lang.Integer getSemId () {
		return semId;
	}

	/**
	 * Set the value related to the column: sem_id
	 * @param semId the sem_id value
	 */
	public void setSemId (java.lang.Integer semId) {
		this.semId = semId;
	}



	/**
	 * Return the value associated with the column: amp_activo
	 */
	public java.lang.String getAmpActivo () {
		return ampActivo;
	}

	/**
	 * Set the value related to the column: amp_activo
	 * @param ampActivo the amp_activo value
	 */
	public void setAmpActivo (java.lang.String ampActivo) {
		this.ampActivo = ampActivo;
	}



	/**
	 * Return the value associated with the column: alu_id
	 */
	public net.uch.mapping.AcAlumno getAlu () {
		return alu;
	}

	/**
	 * Set the value related to the column: alu_id
	 * @param alu the alu_id value
	 */
	public void setAlu (net.uch.mapping.AcAlumno alu) {
		this.alu = alu;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcAmpliaciones)) return false;
		else {
			net.uch.mapping.AcAmpliaciones acAmpliaciones = (net.uch.mapping.AcAmpliaciones) obj;
			if (null == this.getId() || null == acAmpliaciones.getId()) return false;
			else return (this.getId().equals(acAmpliaciones.getId()));
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