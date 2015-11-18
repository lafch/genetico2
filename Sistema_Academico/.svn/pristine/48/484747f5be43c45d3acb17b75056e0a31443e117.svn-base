package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_equivalencia table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_equivalencia"
 */

public abstract class BaseAcEquivalencia  implements Serializable {

	public static String REF = "AcEquivalencia";
	public static String PROP_EQU_CREACION = "EquCreacion";
	public static String PROP_PLANCUR = "Plancur";
	public static String PROP_EQU_OBSERVACION = "EquObservacion";
	public static String PROP_EQU_ACTIVO = "EquActivo";
	public static String PROP_EQU_CURSO_EQUIVALENTE = "EquCursoEquivalente";
	public static String PROP_ID = "Id";


	// constructors
	public BaseAcEquivalencia () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcEquivalencia (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcEquivalencia (
		java.lang.Integer id,
		net.uch.mapping.AcPlanCurso plancur) {

		this.setId(id);
		this.setPlancur(plancur);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String equCursoEquivalente;
	private java.lang.String equObservacion;
	private java.util.Date equCreacion;
	private java.lang.String equActivo;

	// many to one
	private net.uch.mapping.AcPlanCurso plancur;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="equ_id"
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
	 * Return the value associated with the column: equ_curso_equivalente
	 */
	public java.lang.String getEquCursoEquivalente () {
		return equCursoEquivalente;
	}

	/**
	 * Set the value related to the column: equ_curso_equivalente
	 * @param equCursoEquivalente the equ_curso_equivalente value
	 */
	public void setEquCursoEquivalente (java.lang.String equCursoEquivalente) {
		this.equCursoEquivalente = equCursoEquivalente;
	}



	/**
	 * Return the value associated with the column: equ_observacion
	 */
	public java.lang.String getEquObservacion () {
		return equObservacion;
	}

	/**
	 * Set the value related to the column: equ_observacion
	 * @param equObservacion the equ_observacion value
	 */
	public void setEquObservacion (java.lang.String equObservacion) {
		this.equObservacion = equObservacion;
	}



	/**
	 * Return the value associated with the column: equ_creacion
	 */
	public java.util.Date getEquCreacion () {
		return equCreacion;
	}

	/**
	 * Set the value related to the column: equ_creacion
	 * @param equCreacion the equ_creacion value
	 */
	public void setEquCreacion (java.util.Date equCreacion) {
		this.equCreacion = equCreacion;
	}



	/**
	 * Return the value associated with the column: equ_activo
	 */
	public java.lang.String getEquActivo () {
		return equActivo;
	}

	/**
	 * Set the value related to the column: equ_activo
	 * @param equActivo the equ_activo value
	 */
	public void setEquActivo (java.lang.String equActivo) {
		this.equActivo = equActivo;
	}



	/**
	 * Return the value associated with the column: plancur_Id
	 */
	public net.uch.mapping.AcPlanCurso getPlancur () {
		return plancur;
	}

	/**
	 * Set the value related to the column: plancur_Id
	 * @param plancur the plancur_Id value
	 */
	public void setPlancur (net.uch.mapping.AcPlanCurso plancur) {
		this.plancur = plancur;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcEquivalencia)) return false;
		else {
			net.uch.mapping.AcEquivalencia acEquivalencia = (net.uch.mapping.AcEquivalencia) obj;
			if (null == this.getId() || null == acEquivalencia.getId()) return false;
			else return (this.getId().equals(acEquivalencia.getId()));
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