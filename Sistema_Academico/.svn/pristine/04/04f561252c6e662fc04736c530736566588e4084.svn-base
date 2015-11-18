package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_alumno_documento table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_alumno_documento"
 */

public abstract class BaseAcAlumnoDocumento  implements Serializable {

	public static String REF = "AcAlumnoDocumento";
	public static String PROP_ALU = "Alu";
	public static String PROP_ALUDOC_DOCUMENTO = "AludocDocumento";
	public static String PROP_ID = "Id";
	public static String PROP_ALUDOC_ACTIVO = "AludocActivo";


	// constructors
	public BaseAcAlumnoDocumento () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcAlumnoDocumento (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String aludocDocumento;
	private java.lang.String aludocActivo;

	// many to one
	private net.uch.mapping.AcAlumno alu;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="aludoc_id"
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
	 * Return the value associated with the column: aludoc_documento
	 */
	public java.lang.String getAludocDocumento () {
		return aludocDocumento;
	}

	/**
	 * Set the value related to the column: aludoc_documento
	 * @param aludocDocumento the aludoc_documento value
	 */
	public void setAludocDocumento (java.lang.String aludocDocumento) {
		this.aludocDocumento = aludocDocumento;
	}



	/**
	 * Return the value associated with the column: aludoc_activo
	 */
	public java.lang.String getAludocActivo () {
		return aludocActivo;
	}

	/**
	 * Set the value related to the column: aludoc_activo
	 * @param aludocActivo the aludoc_activo value
	 */
	public void setAludocActivo (java.lang.String aludocActivo) {
		this.aludocActivo = aludocActivo;
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
		if (!(obj instanceof net.uch.mapping.AcAlumnoDocumento)) return false;
		else {
			net.uch.mapping.AcAlumnoDocumento acAlumnoDocumento = (net.uch.mapping.AcAlumnoDocumento) obj;
			if (null == this.getId() || null == acAlumnoDocumento.getId()) return false;
			else return (this.getId().equals(acAlumnoDocumento.getId()));
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