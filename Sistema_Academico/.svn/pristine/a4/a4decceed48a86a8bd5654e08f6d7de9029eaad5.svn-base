package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the tb_paises table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tb_paises"
 */

public abstract class BaseTbPaises  implements Serializable {

	public static String REF = "TbPaises";
	public static String PROP_PAIS_DESC = "PaisDesc";
	public static String PROP_ID = "Id";


	// constructors
	public BaseTbPaises () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTbPaises (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseTbPaises (
		java.lang.Integer id,
		java.lang.String paisDesc) {

		this.setId(id);
		this.setPaisDesc(paisDesc);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String paisDesc;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="pais_id"
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
	 * Return the value associated with the column: pais_desc
	 */
	public java.lang.String getPaisDesc () {
		return paisDesc;
	}

	/**
	 * Set the value related to the column: pais_desc
	 * @param paisDesc the pais_desc value
	 */
	public void setPaisDesc (java.lang.String paisDesc) {
		this.paisDesc = paisDesc;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.TbPaises)) return false;
		else {
			net.uch.mapping.TbPaises tbPaises = (net.uch.mapping.TbPaises) obj;
			if (null == this.getId() || null == tbPaises.getId()) return false;
			else return (this.getId().equals(tbPaises.getId()));
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