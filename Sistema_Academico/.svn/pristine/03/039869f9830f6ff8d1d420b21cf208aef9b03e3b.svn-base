package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ad_saldo table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_saldo"
 */

public abstract class BaseAdSaldo  implements Serializable {

	public static String REF = "AdSaldo";
	public static String PROP_ALU = "Alu";
	public static String PROP_SAL_FECHA_INGRESO_MONTO = "SalFechaIngresoMonto";
	public static String PROP_ID = "Id";
	public static String PROP_SAL_CREACION = "SalCreacion";
	public static String PROP_SAL_MONTO = "SalMonto";


	// constructors
	public BaseAdSaldo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdSaldo (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAdSaldo (
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
	private java.lang.Float salMonto;
	private java.util.Date salFechaIngresoMonto;
	private java.util.Date salCreacion;

	// many to one
	private net.uch.mapping.AcAlumno alu;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="sal_id"
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
	 * Return the value associated with the column: sal_monto
	 */
	public java.lang.Float getSalMonto () {
		return salMonto;
	}

	/**
	 * Set the value related to the column: sal_monto
	 * @param salMonto the sal_monto value
	 */
	public void setSalMonto (java.lang.Float salMonto) {
		this.salMonto = salMonto;
	}



	/**
	 * Return the value associated with the column: sal_fecha_ingreso_monto
	 */
	public java.util.Date getSalFechaIngresoMonto () {
		return salFechaIngresoMonto;
	}

	/**
	 * Set the value related to the column: sal_fecha_ingreso_monto
	 * @param salFechaIngresoMonto the sal_fecha_ingreso_monto value
	 */
	public void setSalFechaIngresoMonto (java.util.Date salFechaIngresoMonto) {
		this.salFechaIngresoMonto = salFechaIngresoMonto;
	}



	/**
	 * Return the value associated with the column: sal_creacion
	 */
	public java.util.Date getSalCreacion () {
		return salCreacion;
	}

	/**
	 * Set the value related to the column: sal_creacion
	 * @param salCreacion the sal_creacion value
	 */
	public void setSalCreacion (java.util.Date salCreacion) {
		this.salCreacion = salCreacion;
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
		if (!(obj instanceof net.uch.mapping.AdSaldo)) return false;
		else {
			net.uch.mapping.AdSaldo adSaldo = (net.uch.mapping.AdSaldo) obj;
			if (null == this.getId() || null == adSaldo.getId()) return false;
			else return (this.getId().equals(adSaldo.getId()));
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