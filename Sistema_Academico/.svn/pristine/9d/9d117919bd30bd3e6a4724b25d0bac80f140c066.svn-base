package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ad_clientes table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_clientes"
 */

public abstract class BaseAdClientes  implements Serializable {

	public static String REF = "AdClientes";
	public static String PROP_CLI_TIPO = "CliTipo";
	public static String PROP_CLI_RUC = "CliRuc";
	public static String PROP_CLI_TELEFONO = "CliTelefono";
	public static String PROP_CLI_ACTIVO = "CliActivo";
	public static String PROP_CLI_RAZON_SOCIAL = "CliRazonSocial";
	public static String PROP_CLI_DIRECCION = "CliDireccion";
	public static String PROP_ID = "Id";
	public static String PROP_CLI_MAIL = "CliMail";


	// constructors
	public BaseAdClientes () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdClientes (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String cliRuc;
	private java.lang.String cliRazonSocial;
	private java.lang.String cliDireccion;
	private java.lang.String cliTelefono;
	private java.lang.String cliMail;
	private java.lang.String cliTipo;
	private java.lang.String cliActivo;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="cli_id"
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
	 * Return the value associated with the column: cli_ruc
	 */
	public java.lang.String getCliRuc () {
		return cliRuc;
	}

	/**
	 * Set the value related to the column: cli_ruc
	 * @param cliRuc the cli_ruc value
	 */
	public void setCliRuc (java.lang.String cliRuc) {
		this.cliRuc = cliRuc;
	}



	/**
	 * Return the value associated with the column: cli_razon_social
	 */
	public java.lang.String getCliRazonSocial () {
		return cliRazonSocial;
	}

	/**
	 * Set the value related to the column: cli_razon_social
	 * @param cliRazonSocial the cli_razon_social value
	 */
	public void setCliRazonSocial (java.lang.String cliRazonSocial) {
		this.cliRazonSocial = cliRazonSocial;
	}



	/**
	 * Return the value associated with the column: cli_direccion
	 */
	public java.lang.String getCliDireccion () {
		return cliDireccion;
	}

	/**
	 * Set the value related to the column: cli_direccion
	 * @param cliDireccion the cli_direccion value
	 */
	public void setCliDireccion (java.lang.String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}



	/**
	 * Return the value associated with the column: cli_telefono
	 */
	public java.lang.String getCliTelefono () {
		return cliTelefono;
	}

	/**
	 * Set the value related to the column: cli_telefono
	 * @param cliTelefono the cli_telefono value
	 */
	public void setCliTelefono (java.lang.String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}



	/**
	 * Return the value associated with the column: cli_mail
	 */
	public java.lang.String getCliMail () {
		return cliMail;
	}

	/**
	 * Set the value related to the column: cli_mail
	 * @param cliMail the cli_mail value
	 */
	public void setCliMail (java.lang.String cliMail) {
		this.cliMail = cliMail;
	}



	/**
	 * Return the value associated with the column: cli_tipo
	 */
	public java.lang.String getCliTipo () {
		return cliTipo;
	}

	/**
	 * Set the value related to the column: cli_tipo
	 * @param cliTipo the cli_tipo value
	 */
	public void setCliTipo (java.lang.String cliTipo) {
		this.cliTipo = cliTipo;
	}



	/**
	 * Return the value associated with the column: cli_activo
	 */
	public java.lang.String getCliActivo () {
		return cliActivo;
	}

	/**
	 * Set the value related to the column: cli_activo
	 * @param cliActivo the cli_activo value
	 */
	public void setCliActivo (java.lang.String cliActivo) {
		this.cliActivo = cliActivo;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AdClientes)) return false;
		else {
			net.uch.mapping.AdClientes adClientes = (net.uch.mapping.AdClientes) obj;
			if (null == this.getId() || null == adClientes.getId()) return false;
			else return (this.getId().equals(adClientes.getId()));
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