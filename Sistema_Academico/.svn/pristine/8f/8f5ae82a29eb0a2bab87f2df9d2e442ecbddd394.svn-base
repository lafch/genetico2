package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_acta_detalle table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_acta_detalle"
 */

public abstract class BaseAcActaDetalle  implements Serializable {

	public static String REF = "AcActaDetalle";
	public static String PROP_ALU_ID = "AluId";
	public static String PROP_SISEVA_DETALLE_ID = "SisevaDetalleId";
	public static String PROP_ACT = "Act";
	public static String PROP_ACTDET_NOTA = "ActdetNota";
	public static String PROP_ID = "Id";
	public static String PROP_ACTDET_ACTIVO = "ActdetActivo";
	public static String PROP_ACTDET_TIPO_NOTA = "ActdetTipoNota";


	// constructors
	public BaseAcActaDetalle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcActaDetalle (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcActaDetalle (
		java.lang.Integer id,
		net.uch.mapping.AcActa act) {

		this.setId(id);
		this.setAct(act);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String actdetTipoNota;
	private java.lang.Integer sisevaDetalleId;
	private java.lang.Float actdetNota;
	private java.lang.Integer aluId;
	private java.lang.String actdetActivo;

	// many to one
	private net.uch.mapping.AcActa act;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="actdet_id"
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
	 * Return the value associated with the column: actdet_tipo_nota
	 */
	public java.lang.String getActdetTipoNota () {
		return actdetTipoNota;
	}

	/**
	 * Set the value related to the column: actdet_tipo_nota
	 * @param actdetTipoNota the actdet_tipo_nota value
	 */
	public void setActdetTipoNota (java.lang.String actdetTipoNota) {
		this.actdetTipoNota = actdetTipoNota;
	}



	/**
	 * Return the value associated with the column: siseva_detalle_id
	 */
	public java.lang.Integer getSisevaDetalleId () {
		return sisevaDetalleId;
	}

	/**
	 * Set the value related to the column: siseva_detalle_id
	 * @param sisevaDetalleId the siseva_detalle_id value
	 */
	public void setSisevaDetalleId (java.lang.Integer sisevaDetalleId) {
		this.sisevaDetalleId = sisevaDetalleId;
	}



	/**
	 * Return the value associated with the column: actdet_nota
	 */
	public java.lang.Float getActdetNota () {
		return actdetNota;
	}

	/**
	 * Set the value related to the column: actdet_nota
	 * @param actdetNota the actdet_nota value
	 */
	public void setActdetNota (java.lang.Float actdetNota) {
		this.actdetNota = actdetNota;
	}



	/**
	 * Return the value associated with the column: alu_id
	 */
	public java.lang.Integer getAluId () {
		return aluId;
	}

	/**
	 * Set the value related to the column: alu_id
	 * @param aluId the alu_id value
	 */
	public void setAluId (java.lang.Integer aluId) {
		this.aluId = aluId;
	}



	/**
	 * Return the value associated with the column: actdet_activo
	 */
	public java.lang.String getActdetActivo () {
		return actdetActivo;
	}

	/**
	 * Set the value related to the column: actdet_activo
	 * @param actdetActivo the actdet_activo value
	 */
	public void setActdetActivo (java.lang.String actdetActivo) {
		this.actdetActivo = actdetActivo;
	}



	/**
	 * Return the value associated with the column: act_id
	 */
	public net.uch.mapping.AcActa getAct () {
		return act;
	}

	/**
	 * Set the value related to the column: act_id
	 * @param act the act_id value
	 */
	public void setAct (net.uch.mapping.AcActa act) {
		this.act = act;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcActaDetalle)) return false;
		else {
			net.uch.mapping.AcActaDetalle acActaDetalle = (net.uch.mapping.AcActaDetalle) obj;
			if (null == this.getId() || null == acActaDetalle.getId()) return false;
			else return (this.getId().equals(acActaDetalle.getId()));
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