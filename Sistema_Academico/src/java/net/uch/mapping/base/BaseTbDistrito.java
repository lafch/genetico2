package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the tb_distrito table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tb_distrito"
 */

public abstract class BaseTbDistrito  implements Serializable {

	public static String REF = "TbDistrito";
	public static String PROP_DIS_DES = "DisDes";
	public static String PROP_PRO_COD = "ProCod";
	public static String PROP_ID = "Id";
	public static String PROP_DEP_COD = "DepCod";


	// constructors
	public BaseTbDistrito () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTbDistrito (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseTbDistrito (
		java.lang.String id,
		java.lang.String depCod) {

		this.setId(id);
		this.setDepCod(depCod);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.String disDes;
	private java.lang.String proCod;
	private java.lang.String depCod;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="dis_cod"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: dis_des
	 */
	public java.lang.String getDisDes () {
		return disDes;
	}

	/**
	 * Set the value related to the column: dis_des
	 * @param disDes the dis_des value
	 */
	public void setDisDes (java.lang.String disDes) {
		this.disDes = disDes;
	}



	/**
	 * Return the value associated with the column: pro_cod
	 */
	public java.lang.String getProCod () {
		return proCod;
	}

	/**
	 * Set the value related to the column: pro_cod
	 * @param proCod the pro_cod value
	 */
	public void setProCod (java.lang.String proCod) {
		this.proCod = proCod;
	}



	/**
	 * Return the value associated with the column: dep_cod
	 */
	public java.lang.String getDepCod () {
		return depCod;
	}

	/**
	 * Set the value related to the column: dep_cod
	 * @param depCod the dep_cod value
	 */
	public void setDepCod (java.lang.String depCod) {
		this.depCod = depCod;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.TbDistrito)) return false;
		else {
			net.uch.mapping.TbDistrito tbDistrito = (net.uch.mapping.TbDistrito) obj;
			if (null == this.getId() || null == tbDistrito.getId()) return false;
			else return (this.getId().equals(tbDistrito.getId()));
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