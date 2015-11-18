package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_nota table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_nota"
 */

public abstract class BaseAcNota  implements Serializable {

	public static String REF = "AcNota";
	public static String PROP_ALU = "Alu";
	public static String PROP_NOT_CREACION = "NotCreacion";
	public static String PROP_SISEVA_PER = "SisevaPer";
	public static String PROP_NOT_NOTA = "NotNota";
	public static String PROP_ID = "Id";
	public static String PROP_NOT_OBSERVACION = "NotObservacion";
	public static String PROP_NOT_ACTIVO = "NotActivo";
        public static String PROP_SEC_ID = "SecId";


	// constructors
	public BaseAcNota () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcNota (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcNota (
		java.lang.Integer id,
		net.uch.mapping.AcAlumno alu,
		net.uch.mapping.AcSisEvaPersonalizado sisevaPer) {

		this.setId(id);
		this.setAlu(alu);
		this.setSisevaPer(sisevaPer);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.math.BigDecimal notNota;
	private java.lang.String notObservacion;
	private java.util.Date notCreacion;
	private java.lang.String notActivo;
        private java.lang.Integer secId;

	// many to one
	private net.uch.mapping.AcAlumno alu;
	private net.uch.mapping.AcSisEvaPersonalizado sisevaPer;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="not_id"
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
	 * Return the value associated with the column: not_nota
	 */
	public java.math.BigDecimal getNotNota () {
		return notNota;
	}

	/**
	 * Set the value related to the column: not_nota
	 * @param notNota the not_nota value
	 */
	public void setNotNota (java.math.BigDecimal notNota) {
		this.notNota = notNota;
	}



	/**
	 * Return the value associated with the column: not_observacion
	 */
	public java.lang.String getNotObservacion () {
		return notObservacion;
	}

	/**
	 * Set the value related to the column: not_observacion
	 * @param notObservacion the not_observacion value
	 */
	public void setNotObservacion (java.lang.String notObservacion) {
		this.notObservacion = notObservacion;
	}



	/**
	 * Return the value associated with the column: not_creacion
	 */
	public java.util.Date getNotCreacion () {
		return notCreacion;
	}

	/**
	 * Set the value related to the column: not_creacion
	 * @param notCreacion the not_creacion value
	 */
	public void setNotCreacion (java.util.Date notCreacion) {
		this.notCreacion = notCreacion;
	}



	/**
	 * Return the value associated with the column: not_activo
	 */
	public java.lang.String getNotActivo () {
		return notActivo;
	}

	/**
	 * Set the value related to the column: not_activo
	 * @param notActivo the not_activo value
	 */
	public void setNotActivo (java.lang.String notActivo) {
		this.notActivo = notActivo;
	}

        /**
	 * Return the value associated with the column: sec_id
	 */
	public java.lang.Integer getSecId () {
		return secId;
	}

	/**
	 * Set the value related to the column: sec_id
	 * @param notActivo the not_activo value
	 */
	public void setSecId (java.lang.Integer secId) {
		this.secId = secId;
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



	/**
	 * Return the value associated with the column: siseva_per_id
	 */
	public net.uch.mapping.AcSisEvaPersonalizado getSisevaPer () {
		return sisevaPer;
	}

	/**
	 * Set the value related to the column: siseva_per_id
	 * @param sisevaPer the siseva_per_id value
	 */
	public void setSisevaPer (net.uch.mapping.AcSisEvaPersonalizado sisevaPer) {
		this.sisevaPer = sisevaPer;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcNota)) return false;
		else {
			net.uch.mapping.AcNota acNota = (net.uch.mapping.AcNota) obj;
			if (null == this.getId() || null == acNota.getId()) return false;
			else return (this.getId().equals(acNota.getId()));
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