package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_asistencia table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_asistencia"
 */

public abstract class BaseAcAsistencia  implements Serializable {

	public static String REF = "AcAsistencia";
	public static String PROP_ALU = "Alu";
	public static String PROP_CURAPE_ID = "CurapeId";
	public static String PROP_ASI_DIA = "AsiDia";
	public static String PROP_ASI_ACTIVO = "AsiActivo";
	public static String PROP_ID = "Id";
	public static String PROP_SEM_ID = "SemId";


	// constructors
	public BaseAcAsistencia () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcAsistencia (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcAsistencia (
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
	private java.lang.String asiDia;
	private java.lang.Integer semId;
	private java.lang.Integer curapeId;
	private java.lang.String asiActivo;

	// many to one
	private net.uch.mapping.AcAlumno alu;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="asi_id"
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
	 * Return the value associated with the column: asi_dia
	 */
	public java.lang.String getAsiDia () {
		return asiDia;
	}

	/**
	 * Set the value related to the column: asi_dia
	 * @param asiDia the asi_dia value
	 */
	public void setAsiDia (java.lang.String asiDia) {
		this.asiDia = asiDia;
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
	 * Return the value associated with the column: curape_id
	 */
	public java.lang.Integer getCurapeId () {
		return curapeId;
	}

	/**
	 * Set the value related to the column: curape_id
	 * @param curapeId the curape_id value
	 */
	public void setCurapeId (java.lang.Integer curapeId) {
		this.curapeId = curapeId;
	}



	/**
	 * Return the value associated with the column: asi_activo
	 */
	public java.lang.String getAsiActivo () {
		return asiActivo;
	}

	/**
	 * Set the value related to the column: asi_activo
	 * @param asiActivo the asi_activo value
	 */
	public void setAsiActivo (java.lang.String asiActivo) {
		this.asiActivo = asiActivo;
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
		if (!(obj instanceof net.uch.mapping.AcAsistencia)) return false;
		else {
			net.uch.mapping.AcAsistencia acAsistencia = (net.uch.mapping.AcAsistencia) obj;
			if (null == this.getId() || null == acAsistencia.getId()) return false;
			else return (this.getId().equals(acAsistencia.getId()));
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