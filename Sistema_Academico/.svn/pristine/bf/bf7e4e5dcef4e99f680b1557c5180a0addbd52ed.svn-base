package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_contenido_tematico table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_contenido_tematico"
 */

public abstract class BaseAcContenidoTematico  implements Serializable {

	public static String REF = "AcContenidoTematico";
	public static String PROP_CONTEM_ACTIVO = "ContemActivo";
	public static String PROP_PLANCUR = "Plancur";
	public static String PROP_CONTEM_TITULO = "ContemTitulo";
	public static String PROP_CONTEM_DESCRIPCION = "ContemDescripcion";
	public static String PROP_ID = "Id";
	public static String PROP_CONTEM_NRO_SEMANAS = "ContemNroSemanas";
	public static String PROP_CONTEM_VIGENTE = "ContemVigente";


	// constructors
	public BaseAcContenidoTematico () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcContenidoTematico (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcContenidoTematico (
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
	private java.lang.String contemTitulo;
	private java.lang.String contemDescripcion;
	private java.lang.String contemNroSemanas;
	private java.lang.String contemVigente;
	private java.lang.String contemActivo;

	// many to one
	private net.uch.mapping.AcPlanCurso plancur;

	// collections
	private java.util.Set<net.uch.mapping.AcContenidoDetalle> acContenidoDetalles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="contem_id"
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
	 * Return the value associated with the column: contem_titulo
	 */
	public java.lang.String getContemTitulo () {
		return contemTitulo;
	}

	/**
	 * Set the value related to the column: contem_titulo
	 * @param contemTitulo the contem_titulo value
	 */
	public void setContemTitulo (java.lang.String contemTitulo) {
		this.contemTitulo = contemTitulo;
	}



	/**
	 * Return the value associated with the column: contem_descripcion
	 */
	public java.lang.String getContemDescripcion () {
		return contemDescripcion;
	}

	/**
	 * Set the value related to the column: contem_descripcion
	 * @param contemDescripcion the contem_descripcion value
	 */
	public void setContemDescripcion (java.lang.String contemDescripcion) {
		this.contemDescripcion = contemDescripcion;
	}



	/**
	 * Return the value associated with the column: contem_nro_semanas
	 */
	public java.lang.String getContemNroSemanas () {
		return contemNroSemanas;
	}

	/**
	 * Set the value related to the column: contem_nro_semanas
	 * @param contemNroSemanas the contem_nro_semanas value
	 */
	public void setContemNroSemanas (java.lang.String contemNroSemanas) {
		this.contemNroSemanas = contemNroSemanas;
	}



	/**
	 * Return the value associated with the column: contem_vigente
	 */
	public java.lang.String getContemVigente () {
		return contemVigente;
	}

	/**
	 * Set the value related to the column: contem_vigente
	 * @param contemVigente the contem_vigente value
	 */
	public void setContemVigente (java.lang.String contemVigente) {
		this.contemVigente = contemVigente;
	}



	/**
	 * Return the value associated with the column: contem_activo
	 */
	public java.lang.String getContemActivo () {
		return contemActivo;
	}

	/**
	 * Set the value related to the column: contem_activo
	 * @param contemActivo the contem_activo value
	 */
	public void setContemActivo (java.lang.String contemActivo) {
		this.contemActivo = contemActivo;
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
	 * Return the value associated with the column: AcContenidoDetalles
	 */
	public java.util.Set<net.uch.mapping.AcContenidoDetalle> getAcContenidoDetalles () {
		return acContenidoDetalles;
	}

	/**
	 * Set the value related to the column: AcContenidoDetalles
	 * @param acContenidoDetalles the AcContenidoDetalles value
	 */
	public void setAcContenidoDetalles (java.util.Set<net.uch.mapping.AcContenidoDetalle> acContenidoDetalles) {
		this.acContenidoDetalles = acContenidoDetalles;
	}

	public void addToAcContenidoDetalles (net.uch.mapping.AcContenidoDetalle acContenidoDetalle) {
		if (null == getAcContenidoDetalles()) setAcContenidoDetalles(new java.util.TreeSet<net.uch.mapping.AcContenidoDetalle>());
		getAcContenidoDetalles().add(acContenidoDetalle);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcContenidoTematico)) return false;
		else {
			net.uch.mapping.AcContenidoTematico acContenidoTematico = (net.uch.mapping.AcContenidoTematico) obj;
			if (null == this.getId() || null == acContenidoTematico.getId()) return false;
			else return (this.getId().equals(acContenidoTematico.getId()));
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