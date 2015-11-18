package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the tb_roles table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tb_roles"
 */

public abstract class BaseTbRoles  implements Serializable {

	public static String REF = "TbRoles";
	public static String PROP_ROL_TIPO = "RolTipo";
	public static String PROP_ROL_ACTIVO = "RolActivo";
	public static String PROP_ID = "Id";


	// constructors
	public BaseTbRoles () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTbRoles (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String rolTipo;
	private java.lang.String rolActivo;

	// collections
	private java.util.Set<net.uch.mapping.TbUsuario> tbUsuarios;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="rol_id"
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
	 * Return the value associated with the column: rol_tipo
	 */
	public java.lang.String getRolTipo () {
		return rolTipo;
	}

	/**
	 * Set the value related to the column: rol_tipo
	 * @param rolTipo the rol_tipo value
	 */
	public void setRolTipo (java.lang.String rolTipo) {
		this.rolTipo = rolTipo;
	}



	/**
	 * Return the value associated with the column: rol_activo
	 */
	public java.lang.String getRolActivo () {
		return rolActivo;
	}

	/**
	 * Set the value related to the column: rol_activo
	 * @param rolActivo the rol_activo value
	 */
	public void setRolActivo (java.lang.String rolActivo) {
		this.rolActivo = rolActivo;
	}



	/**
	 * Return the value associated with the column: TbUsuarios
	 */
	public java.util.Set<net.uch.mapping.TbUsuario> getTbUsuarios () {
		return tbUsuarios;
	}

	/**
	 * Set the value related to the column: TbUsuarios
	 * @param tbUsuarios the TbUsuarios value
	 */
	public void setTbUsuarios (java.util.Set<net.uch.mapping.TbUsuario> tbUsuarios) {
		this.tbUsuarios = tbUsuarios;
	}

	public void addToTbUsuarios (net.uch.mapping.TbUsuario tbUsuario) {
		if (null == getTbUsuarios()) setTbUsuarios(new java.util.TreeSet<net.uch.mapping.TbUsuario>());
		getTbUsuarios().add(tbUsuario);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.TbRoles)) return false;
		else {
			net.uch.mapping.TbRoles tbRoles = (net.uch.mapping.TbRoles) obj;
			if (null == this.getId() || null == tbRoles.getId()) return false;
			else return (this.getId().equals(tbRoles.getId()));
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