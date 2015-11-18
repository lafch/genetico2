package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_acta table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_acta"
 */

public abstract class BaseAcActa  implements Serializable {

	public static String REF = "AcActa";
	public static String PROP_ACT_NUMERO = "ActNumero";
	public static String PROP_ACT_CODIGO = "ActCodigo";
        public static String PROP_ACT_TIPO = "ActTipo";
	public static String PROP_ACT_ACTIVO = "ActActivo";
	public static String PROP_ACT_CREACION = "ActCreacion";
	public static String PROP_ACT_NOMBRE = "ActNombre";
	public static String PROP_SEC = "Sec";
	public static String PROP_ID = "Id";


	// constructors
	public BaseAcActa () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcActa (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String actCodigo;
	private java.lang.String actNombre;
	private java.lang.String actNumero;
	private java.util.Date actCreacion;
	private java.lang.String actActivo;
        private java.lang.String actTipo;

	// many to one
	private net.uch.mapping.AcSeccion sec;

	// collections
	private java.util.Set<net.uch.mapping.AcActaDetalle> acActaDetalles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="act_id"
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
	 * Return the value associated with the column: act_codigo
	 */
	public java.lang.String getActCodigo () {
		return actCodigo;
	}

	/**
	 * Set the value related to the column: act_codigo
	 * @param actCodigo the act_codigo value
	 */
	public void setActCodigo (java.lang.String actCodigo) {
		this.actCodigo = actCodigo;
	}



	/**
	 * Return the value associated with the column: act_nombre
	 */
	public java.lang.String getActNombre () {
		return actNombre;
	}

	/**
	 * Set the value related to the column: act_nombre
	 * @param actNombre the act_nombre value
	 */
	public void setActNombre (java.lang.String actNombre) {
		this.actNombre = actNombre;
	}



	/**
	 * Return the value associated with the column: act_numero
	 */
	public java.lang.String getActNumero () {
		return actNumero;
	}

	/**
	 * Set the value related to the column: act_numero
	 * @param actNumero the act_numero value
	 */
	public void setActNumero (java.lang.String actNumero) {
		this.actNumero = actNumero;
	}



	/**
	 * Return the value associated with the column: act_creacion
	 */
	public java.util.Date getActCreacion () {
		return actCreacion;
	}

	/**
	 * Set the value related to the column: act_creacion
	 * @param actCreacion the act_creacion value
	 */
	public void setActCreacion (java.util.Date actCreacion) {
		this.actCreacion = actCreacion;
	}



	/**
	 * Return the value associated with the column: act_activo
	 */
	public java.lang.String getActActivo () {
		return actActivo;
	}

	/**
	 * Set the value related to the column: act_activo
	 * @param actActivo the act_activo value
	 */
	public void setActActivo (java.lang.String actActivo) {
		this.actActivo = actActivo;
	}



	/**
	 * Return the value associated with the column: act_tipo
	 */
	public java.lang.String getActTipo () {
		return actTipo;
	}

	/**
	 * Set the value related to the column: act_activo
	 * @param actActivo the act_tipo value
	 */
	public void setActTipo (java.lang.String actTipo) {
		this.actTipo = actTipo;
	}



        
	/**
	 * Return the value associated with the column: sec_id
	 */
	public net.uch.mapping.AcSeccion getSec () {
		return sec;
	}

	/**
	 * Set the value related to the column: sec_id
	 * @param sec the sec_id value
	 */
	public void setSec (net.uch.mapping.AcSeccion sec) {
		this.sec = sec;
	}



	/**
	 * Return the value associated with the column: AcActaDetalles
	 */
	public java.util.Set<net.uch.mapping.AcActaDetalle> getAcActaDetalles () {
		return acActaDetalles;
	}

	/**
	 * Set the value related to the column: AcActaDetalles
	 * @param acActaDetalles the AcActaDetalles value
	 */
	public void setAcActaDetalles (java.util.Set<net.uch.mapping.AcActaDetalle> acActaDetalles) {
		this.acActaDetalles = acActaDetalles;
	}

	public void addToAcActaDetalles (net.uch.mapping.AcActaDetalle acActaDetalle) {
		if (null == getAcActaDetalles()) setAcActaDetalles(new java.util.TreeSet<net.uch.mapping.AcActaDetalle>());
		getAcActaDetalles().add(acActaDetalle);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcActa)) return false;
		else {
			net.uch.mapping.AcActa acActa = (net.uch.mapping.AcActa) obj;
			if (null == this.getId() || null == acActa.getId()) return false;
			else return (this.getId().equals(acActa.getId()));
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