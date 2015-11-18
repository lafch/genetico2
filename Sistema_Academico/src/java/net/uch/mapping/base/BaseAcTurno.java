package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_turno table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_turno"
 */

public abstract class BaseAcTurno  implements Serializable {

	public static String REF = "AcTurno";
	public static String PROP_TUR_HORA_INICIO = "TurHoraInicio";
	public static String PROP_TUR_ACTIVO = "TurActivo";
	public static String PROP_TUR_TIEMPO_PERIODO = "TurTiempoPeriodo";
	public static String PROP_TUR_NOMBRE = "TurNombre";
	public static String PROP_TUR_PERIODO = "TurPeriodo";
	public static String PROP_TUR_CODIGO = "TurCodigo";
	public static String PROP_ID = "Id";
	public static String PROP_SEM_ID = "SemId";
	public static String PROP_TUR_VIGENTE = "TurVigente";


	// constructors
	public BaseAcTurno () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcTurno (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcTurno (
		java.lang.Integer id,
		java.lang.Integer semId) {

		this.setId(id);
		this.setSemId(semId);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String turCodigo;
	private java.lang.String turNombre;
	private java.lang.Integer turPeriodo;
	private java.util.Date turHoraInicio;
	private java.lang.String turVigente;
	private java.lang.Integer semId;
	private java.lang.Integer turTiempoPeriodo;
	private java.lang.String turActivo;

	// collections
	private java.util.Set<net.uch.mapping.AcTurnoDetalle> acTurnoDetalles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="tur_id"
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
	 * Return the value associated with the column: tur_codigo
	 */
	public java.lang.String getTurCodigo () {
		return turCodigo;
	}

	/**
	 * Set the value related to the column: tur_codigo
	 * @param turCodigo the tur_codigo value
	 */
	public void setTurCodigo (java.lang.String turCodigo) {
		this.turCodigo = turCodigo;
	}



	/**
	 * Return the value associated with the column: tur_nombre
	 */
	public java.lang.String getTurNombre () {
		return turNombre;
	}

	/**
	 * Set the value related to the column: tur_nombre
	 * @param turNombre the tur_nombre value
	 */
	public void setTurNombre (java.lang.String turNombre) {
		this.turNombre = turNombre;
	}



	/**
	 * Return the value associated with the column: tur_periodo
	 */
	public java.lang.Integer getTurPeriodo () {
		return turPeriodo;
	}

	/**
	 * Set the value related to the column: tur_periodo
	 * @param turPeriodo the tur_periodo value
	 */
	public void setTurPeriodo (java.lang.Integer turPeriodo) {
		this.turPeriodo = turPeriodo;
	}



	/**
	 * Return the value associated with the column: tur_hora_inicio
	 */
	public java.util.Date getTurHoraInicio () {
		return turHoraInicio;
	}

	/**
	 * Set the value related to the column: tur_hora_inicio
	 * @param turHoraInicio the tur_hora_inicio value
	 */
	public void setTurHoraInicio (java.util.Date turHoraInicio) {
		this.turHoraInicio = turHoraInicio;
	}



	/**
	 * Return the value associated with the column: tur_vigente
	 */
	public java.lang.String getTurVigente () {
		return turVigente;
	}

	/**
	 * Set the value related to the column: tur_vigente
	 * @param turVigente the tur_vigente value
	 */
	public void setTurVigente (java.lang.String turVigente) {
		this.turVigente = turVigente;
	}



	/**
	 * Return the value associated with the column: sem_id
	 */
	public java.lang.Integer getSemId () {
		return semId;
	}

	/**
	 * Set the value related to the column: sem_id
	 * @param semId the sem_id value
	 */
	public void setSemId (java.lang.Integer semId) {
		this.semId = semId;
	}



	/**
	 * Return the value associated with the column: tur_tiempo_periodo
	 */
	public java.lang.Integer getTurTiempoPeriodo () {
		return turTiempoPeriodo;
	}

	/**
	 * Set the value related to the column: tur_tiempo_periodo
	 * @param turTiempoPeriodo the tur_tiempo_periodo value
	 */
	public void setTurTiempoPeriodo (java.lang.Integer turTiempoPeriodo) {
		this.turTiempoPeriodo = turTiempoPeriodo;
	}



	/**
	 * Return the value associated with the column: tur_activo
	 */
	public java.lang.String getTurActivo () {
		return turActivo;
	}

	/**
	 * Set the value related to the column: tur_activo
	 * @param turActivo the tur_activo value
	 */
	public void setTurActivo (java.lang.String turActivo) {
		this.turActivo = turActivo;
	}



	/**
	 * Return the value associated with the column: AcTurnoDetalles
	 */
	public java.util.Set<net.uch.mapping.AcTurnoDetalle> getAcTurnoDetalles () {
		return acTurnoDetalles;
	}

	/**
	 * Set the value related to the column: AcTurnoDetalles
	 * @param acTurnoDetalles the AcTurnoDetalles value
	 */
	public void setAcTurnoDetalles (java.util.Set<net.uch.mapping.AcTurnoDetalle> acTurnoDetalles) {
		this.acTurnoDetalles = acTurnoDetalles;
	}

	public void addToAcTurnoDetalles (net.uch.mapping.AcTurnoDetalle acTurnoDetalle) {
		if (null == getAcTurnoDetalles()) setAcTurnoDetalles(new java.util.TreeSet<net.uch.mapping.AcTurnoDetalle>());
		getAcTurnoDetalles().add(acTurnoDetalle);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcTurno)) return false;
		else {
			net.uch.mapping.AcTurno acTurno = (net.uch.mapping.AcTurno) obj;
			if (null == this.getId() || null == acTurno.getId()) return false;
			else return (this.getId().equals(acTurno.getId()));
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