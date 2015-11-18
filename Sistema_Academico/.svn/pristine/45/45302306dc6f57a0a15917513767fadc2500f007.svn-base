package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_pre_requisitos table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_pre_requisitos"
 */

public abstract class BaseAcPreRequisitos  implements Serializable {

	public static String REF = "AcPreRequisitos";
	public static String PROP_PLANCUR = "Plancur";
	public static String PROP_PLANCUR_ID_REQUISITO = "PlancurIdRequisito";
	public static String PROP_ID = "Id";
	public static String PROP_PRE_REQ_ACTIVO = "PreReqActivo";


	// constructors
	public BaseAcPreRequisitos () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcPreRequisitos (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcPreRequisitos (
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
	private java.lang.String preReqActivo;

	// many to one
	private net.uch.mapping.AcPlanCurso plancur;
	private net.uch.mapping.AcPlanCurso plancurIdRequisito;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="pre_req_id"
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
	 * Return the value associated with the column: pre_req_activo
	 */
	public java.lang.String getPreReqActivo () {
		return preReqActivo;
	}

	/**
	 * Set the value related to the column: pre_req_activo
	 * @param preReqActivo the pre_req_activo value
	 */
	public void setPreReqActivo (java.lang.String preReqActivo) {
		this.preReqActivo = preReqActivo;
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



	/**
	 * Return the value associated with the column: plancur_id_requisito
	 */
	public net.uch.mapping.AcPlanCurso getPlancurIdRequisito () {
		return plancurIdRequisito;
	}

	/**
	 * Set the value related to the column: plancur_id_requisito
	 * @param plancurIdRequisito the plancur_id_requisito value
	 */
	public void setPlancurIdRequisito (net.uch.mapping.AcPlanCurso plancurIdRequisito) {
		this.plancurIdRequisito = plancurIdRequisito;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcPreRequisitos)) return false;
		else {
			net.uch.mapping.AcPreRequisitos acPreRequisitos = (net.uch.mapping.AcPreRequisitos) obj;
			if (null == this.getId() || null == acPreRequisitos.getId()) return false;
			else return (this.getId().equals(acPreRequisitos.getId()));
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