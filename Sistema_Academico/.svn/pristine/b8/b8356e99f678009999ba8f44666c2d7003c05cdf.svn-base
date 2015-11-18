package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_facultad table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_facultad"
 */

public abstract class BaseAcFacultad  implements Serializable {

	public static String REF = "AcFacultad";
	public static String PROP_FAC_FECHACREACION = "FacFechacreacion";
	public static String PROP_FAC_ACTIVO = "FacActivo";
	public static String PROP_FAC_RESOLRECTORAL = "FacResolrectoral";
	public static String PROP_ID = "Id";
	public static String PROP_FAC_NOMBRE = "FacNombre";
	public static String PROP_FAC_CODIGO = "FacCodigo";


	// constructors
	public BaseAcFacultad () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcFacultad (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcFacultad (
		java.lang.Integer id,
		java.lang.String facActivo) {

		this.setId(id);
		this.setFacActivo(facActivo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String facCodigo;
	private java.lang.String facNombre;
	private java.util.Date facFechacreacion;
	private java.lang.String facResolrectoral;
	private java.lang.String facActivo;

	// collections
	private java.util.Set<net.uch.mapping.AcEspecialidad> acEspecialidads;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="fac_id"
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
	 * Return the value associated with the column: fac_codigo
	 */
	public java.lang.String getFacCodigo () {
		return facCodigo;
	}

	/**
	 * Set the value related to the column: fac_codigo
	 * @param facCodigo the fac_codigo value
	 */
	public void setFacCodigo (java.lang.String facCodigo) {
		this.facCodigo = facCodigo;
	}



	/**
	 * Return the value associated with the column: fac_nombre
	 */
	public java.lang.String getFacNombre () {
		return facNombre;
	}

	/**
	 * Set the value related to the column: fac_nombre
	 * @param facNombre the fac_nombre value
	 */
	public void setFacNombre (java.lang.String facNombre) {
		this.facNombre = facNombre;
	}



	/**
	 * Return the value associated with the column: fac_fechacreacion
	 */
	public java.util.Date getFacFechacreacion () {
		return facFechacreacion;
	}

	/**
	 * Set the value related to the column: fac_fechacreacion
	 * @param facFechacreacion the fac_fechacreacion value
	 */
	public void setFacFechacreacion (java.util.Date facFechacreacion) {
		this.facFechacreacion = facFechacreacion;
	}



	/**
	 * Return the value associated with the column: fac_resolrectoral
	 */
	public java.lang.String getFacResolrectoral () {
		return facResolrectoral;
	}

	/**
	 * Set the value related to the column: fac_resolrectoral
	 * @param facResolrectoral the fac_resolrectoral value
	 */
	public void setFacResolrectoral (java.lang.String facResolrectoral) {
		this.facResolrectoral = facResolrectoral;
	}



	/**
	 * Return the value associated with the column: fac_activo
	 */
	public java.lang.String getFacActivo () {
		return facActivo;
	}

	/**
	 * Set the value related to the column: fac_activo
	 * @param facActivo the fac_activo value
	 */
	public void setFacActivo (java.lang.String facActivo) {
		this.facActivo = facActivo;
	}



	/**
	 * Return the value associated with the column: AcEspecialidads
	 */
	public java.util.Set<net.uch.mapping.AcEspecialidad> getAcEspecialidads () {
		return acEspecialidads;
	}

	/**
	 * Set the value related to the column: AcEspecialidads
	 * @param acEspecialidads the AcEspecialidads value
	 */
	public void setAcEspecialidads (java.util.Set<net.uch.mapping.AcEspecialidad> acEspecialidads) {
		this.acEspecialidads = acEspecialidads;
	}

	public void addToAcEspecialidads (net.uch.mapping.AcEspecialidad acEspecialidad) {
		if (null == getAcEspecialidads()) setAcEspecialidads(new java.util.TreeSet<net.uch.mapping.AcEspecialidad>());
		getAcEspecialidads().add(acEspecialidad);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcFacultad)) return false;
		else {
			net.uch.mapping.AcFacultad acFacultad = (net.uch.mapping.AcFacultad) obj;
			if (null == this.getId() || null == acFacultad.getId()) return false;
			else return (this.getId().equals(acFacultad.getId()));
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