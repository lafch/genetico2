package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_especialidad table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_especialidad"
 */

public abstract class BaseAcEspecialidad  implements Serializable {

	public static String REF = "AcEspecialidad";
	public static String PROP_ESP_FECHACREACION = "EspFechacreacion";
	public static String PROP_FAC = "Fac";
	public static String PROP_ESP_ACTIVO = "EspActivo";
	public static String PROP_ESP_CREDITOSTOTAL = "EspCreditostotal";
	public static String PROP_ESP_RESOLRECTORAL = "EspResolrectoral";
	public static String PROP_ESP_CODIGO = "EspCodigo";
	public static String PROP_ESP_NOMBRE = "EspNombre";
	public static String PROP_ID = "Id";
	public static String PROP_ESP_NROPERIODOS = "EspNroperiodos";
	public static String PROP_ESP_TIPOPERIODO = "EspTipoperiodo";


	// constructors
	public BaseAcEspecialidad () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcEspecialidad (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String espNombre;
	private java.lang.String espCodigo;
	private java.util.Date espFechacreacion;
	private java.lang.String espResolrectoral;
	private java.lang.String espNroperiodos;
	private java.lang.String espTipoperiodo;
	private java.lang.String espCreditostotal;
	private java.lang.String espActivo;

	// many to one
	private net.uch.mapping.AcFacultad fac;

	// collections
	private java.util.Set<net.uch.mapping.AcAlumno> acAlumnos;
	private java.util.Set<net.uch.mapping.AcPlancurricular> acPlancurriculars;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="esp_id"
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
	 * Return the value associated with the column: esp_nombre
	 */
	public java.lang.String getEspNombre () {
		return espNombre;
	}

	/**
	 * Set the value related to the column: esp_nombre
	 * @param espNombre the esp_nombre value
	 */
	public void setEspNombre (java.lang.String espNombre) {
		this.espNombre = espNombre;
	}



	/**
	 * Return the value associated with the column: esp_codigo
	 */
	public java.lang.String getEspCodigo () {
		return espCodigo;
	}

	/**
	 * Set the value related to the column: esp_codigo
	 * @param espCodigo the esp_codigo value
	 */
	public void setEspCodigo (java.lang.String espCodigo) {
		this.espCodigo = espCodigo;
	}



	/**
	 * Return the value associated with the column: esp_fechacreacion
	 */
	public java.util.Date getEspFechacreacion () {
		return espFechacreacion;
	}

	/**
	 * Set the value related to the column: esp_fechacreacion
	 * @param espFechacreacion the esp_fechacreacion value
	 */
	public void setEspFechacreacion (java.util.Date espFechacreacion) {
		this.espFechacreacion = espFechacreacion;
	}



	/**
	 * Return the value associated with the column: esp_resolrectoral
	 */
	public java.lang.String getEspResolrectoral () {
		return espResolrectoral;
	}

	/**
	 * Set the value related to the column: esp_resolrectoral
	 * @param espResolrectoral the esp_resolrectoral value
	 */
	public void setEspResolrectoral (java.lang.String espResolrectoral) {
		this.espResolrectoral = espResolrectoral;
	}



	/**
	 * Return the value associated with the column: esp_nroperiodos
	 */
	public java.lang.String getEspNroperiodos () {
		return espNroperiodos;
	}

	/**
	 * Set the value related to the column: esp_nroperiodos
	 * @param espNroperiodos the esp_nroperiodos value
	 */
	public void setEspNroperiodos (java.lang.String espNroperiodos) {
		this.espNroperiodos = espNroperiodos;
	}



	/**
	 * Return the value associated with the column: esp_tipoperiodo
	 */
	public java.lang.String getEspTipoperiodo () {
		return espTipoperiodo;
	}

	/**
	 * Set the value related to the column: esp_tipoperiodo
	 * @param espTipoperiodo the esp_tipoperiodo value
	 */
	public void setEspTipoperiodo (java.lang.String espTipoperiodo) {
		this.espTipoperiodo = espTipoperiodo;
	}



	/**
	 * Return the value associated with the column: esp_creditostotal
	 */
	public java.lang.String getEspCreditostotal () {
		return espCreditostotal;
	}

	/**
	 * Set the value related to the column: esp_creditostotal
	 * @param espCreditostotal the esp_creditostotal value
	 */
	public void setEspCreditostotal (java.lang.String espCreditostotal) {
		this.espCreditostotal = espCreditostotal;
	}



	/**
	 * Return the value associated with the column: esp_activo
	 */
	public java.lang.String getEspActivo () {
		return espActivo;
	}

	/**
	 * Set the value related to the column: esp_activo
	 * @param espActivo the esp_activo value
	 */
	public void setEspActivo (java.lang.String espActivo) {
		this.espActivo = espActivo;
	}



	/**
	 * Return the value associated with the column: fac_id
	 */
	public net.uch.mapping.AcFacultad getFac () {
		return fac;
	}

	/**
	 * Set the value related to the column: fac_id
	 * @param fac the fac_id value
	 */
	public void setFac (net.uch.mapping.AcFacultad fac) {
		this.fac = fac;
	}



	/**
	 * Return the value associated with the column: AcAlumnos
	 */
	public java.util.Set<net.uch.mapping.AcAlumno> getAcAlumnos () {
		return acAlumnos;
	}

	/**
	 * Set the value related to the column: AcAlumnos
	 * @param acAlumnos the AcAlumnos value
	 */
	public void setAcAlumnos (java.util.Set<net.uch.mapping.AcAlumno> acAlumnos) {
		this.acAlumnos = acAlumnos;
	}

	public void addToAcAlumnos (net.uch.mapping.AcAlumno acAlumno) {
		if (null == getAcAlumnos()) setAcAlumnos(new java.util.TreeSet<net.uch.mapping.AcAlumno>());
		getAcAlumnos().add(acAlumno);
	}



	/**
	 * Return the value associated with the column: AcPlancurriculars
	 */
	public java.util.Set<net.uch.mapping.AcPlancurricular> getAcPlancurriculars () {
		return acPlancurriculars;
	}

	/**
	 * Set the value related to the column: AcPlancurriculars
	 * @param acPlancurriculars the AcPlancurriculars value
	 */
	public void setAcPlancurriculars (java.util.Set<net.uch.mapping.AcPlancurricular> acPlancurriculars) {
		this.acPlancurriculars = acPlancurriculars;
	}

	public void addToAcPlancurriculars (net.uch.mapping.AcPlancurricular acPlancurricular) {
		if (null == getAcPlancurriculars()) setAcPlancurriculars(new java.util.TreeSet<net.uch.mapping.AcPlancurricular>());
		getAcPlancurriculars().add(acPlancurricular);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcEspecialidad)) return false;
		else {
			net.uch.mapping.AcEspecialidad acEspecialidad = (net.uch.mapping.AcEspecialidad) obj;
			if (null == this.getId() || null == acEspecialidad.getId()) return false;
			else return (this.getId().equals(acEspecialidad.getId()));
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