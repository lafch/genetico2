package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_semestre table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_semestre"
 */

public abstract class BaseAcSemestre  implements Serializable {

	public static String REF = "AcSemestre";
	public static String PROP_SEM_NUM_SEMANAS = "SemNumSemanas";
	public static String PROP_SEM_NOMBRE = "SemNombre";
	public static String PROP_SEM_VIGENTE = "SemVigente";
	public static String PROP_SEM_ACTIVO = "SemActivo";
	public static String PROP_SEM_FECHA_FIN = "SemFechaFin";
        public static String PROP_SEM_FECHA_RET_INC = "SemFechaRetInc";
	public static String PROP_SEM_CODIGO = "SemCodigo";
	public static String PROP_ID = "Id";
	public static String PROP_SEM_PENSIONES = "SemPensiones";
	public static String PROP_SEM_CREACION = "SemCreacion";
	public static String PROP_SEM_ACTUAL = "SemActual";
	public static String PROP_SEM_FECHA_INICIO = "SemFechaInicio";
        public static String PROP_SEM_ACADEMICO = "SemAcademico";


	// constructors
	public BaseAcSemestre () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcSemestre (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String semCodigo;
	private java.lang.String semNombre;
	private java.util.Date semFechaInicio;
	private java.util.Date semFechaFin;
        private java.util.Date semFechaRetInc;
	private java.lang.String semVigente;
	private java.lang.Integer semNumSemanas;
	private java.lang.String semActivo;
	private java.util.Date semCreacion;
	private java.lang.String semPensiones;
	private java.lang.String semActual;
        private java.lang.String semAcademico;

	// collections
	private java.util.Set<net.uch.mapping.AcCursoAperturado> acCursoAperturados;
	private java.util.Set<net.uch.mapping.AdEstructuraPagos> adEstructuraPagos;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="sem_id"
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
	 * Return the value associated with the column: sem_codigo
	 */
	public java.lang.String getSemCodigo () {
		return semCodigo;
	}

	/**
	 * Set the value related to the column: sem_codigo
	 * @param semCodigo the sem_codigo value
	 */
	public void setSemCodigo (java.lang.String semCodigo) {
		this.semCodigo = semCodigo;
	}



	/**
	 * Return the value associated with the column: sem_nombre
	 */
	public java.lang.String getSemNombre () {
		return semNombre;
	}

	/**
	 * Set the value related to the column: sem_nombre
	 * @param semNombre the sem_nombre value
	 */
	public void setSemNombre (java.lang.String semNombre) {
		this.semNombre = semNombre;
	}



	/**
	 * Return the value associated with the column: sem_fecha_inicio
	 */
	public java.util.Date getSemFechaInicio () {
		return semFechaInicio;
	}

	/**
	 * Set the value related to the column: sem_fecha_inicio
	 * @param semFechaInicio the sem_fecha_inicio value
	 */
	public void setSemFechaInicio (java.util.Date semFechaInicio) {
		this.semFechaInicio = semFechaInicio;
	}
        
        /**
	 * Return the value associated with the column: sem_fecha_inicio
	 */
	public java.util.Date getSemFechaRetInc () {
		return semFechaRetInc;
	}

	/**
	 * Set the value related to the column: sem_fecha_inicio
	 * @param semFechaInicio the sem_fecha_inicio value
	 */
	public void setSemFechaRetInc (java.util.Date semFechaRetInc) {
		this.semFechaRetInc = semFechaRetInc;
	}



	/**
	 * Return the value associated with the column: sem_fecha_fin
	 */
	public java.util.Date getSemFechaFin () {
		return semFechaFin;
	}

	/**
	 * Set the value related to the column: sem_fecha_fin
	 * @param semFechaFin the sem_fecha_fin value
	 */
	public void setSemFechaFin (java.util.Date semFechaFin) {
		this.semFechaFin = semFechaFin;
	}



	/**
	 * Return the value associated with the column: sem_vigente
	 */
	public java.lang.String getSemVigente () {
		return semVigente;
	}

	/**
	 * Set the value related to the column: sem_vigente
	 * @param semVigente the sem_vigente value
	 */
	public void setSemVigente (java.lang.String semVigente) {
		this.semVigente = semVigente;
	}



	/**
	 * Return the value associated with the column: sem_num_semanas
	 */
	public java.lang.Integer getSemNumSemanas () {
		return semNumSemanas;
	}

	/**
	 * Set the value related to the column: sem_num_semanas
	 * @param semNumSemanas the sem_num_semanas value
	 */
	public void setSemNumSemanas (java.lang.Integer semNumSemanas) {
		this.semNumSemanas = semNumSemanas;
	}



	/**
	 * Return the value associated with the column: sem_activo
	 */
	public java.lang.String getSemActivo () {
		return semActivo;
	}

	/**
	 * Set the value related to the column: sem_activo
	 * @param semActivo the sem_activo value
	 */
	public void setSemActivo (java.lang.String semActivo) {
		this.semActivo = semActivo;
	}



	/**
	 * Return the value associated with the column: sem_creacion
	 */
	public java.util.Date getSemCreacion () {
		return semCreacion;
	}

	/**
	 * Set the value related to the column: sem_creacion
	 * @param semCreacion the sem_creacion value
	 */
	public void setSemCreacion (java.util.Date semCreacion) {
		this.semCreacion = semCreacion;
	}



	/**
	 * Return the value associated with the column: sem_pensiones
	 */
	public java.lang.String getSemPensiones () {
		return semPensiones;
	}

	/**
	 * Set the value related to the column: sem_pensiones
	 * @param semPensiones the sem_pensiones value
	 */
	public void setSemPensiones (java.lang.String semPensiones) {
		this.semPensiones = semPensiones;
	}



	/**
	 * Return the value associated with the column: sem_actual
	 */
	public java.lang.String getSemActual () {
		return semActual;
	}

	/**
	 * Set the value related to the column: sem_actual
	 * @param semActual the sem_actual value
	 */
	public void setSemActual (java.lang.String semActual) {
		this.semActual = semActual;
	}



	/**
	 * Return the value associated with the column: AcCursoAperturados
	 */
	public java.util.Set<net.uch.mapping.AcCursoAperturado> getAcCursoAperturados () {
		return acCursoAperturados;
	}

	/**
	 * Set the value related to the column: AcCursoAperturados
	 * @param acCursoAperturados the AcCursoAperturados value
	 */
	public void setAcCursoAperturados (java.util.Set<net.uch.mapping.AcCursoAperturado> acCursoAperturados) {
		this.acCursoAperturados = acCursoAperturados;
	}

	public void addToAcCursoAperturados (net.uch.mapping.AcCursoAperturado acCursoAperturado) {
		if (null == getAcCursoAperturados()) setAcCursoAperturados(new java.util.TreeSet<net.uch.mapping.AcCursoAperturado>());
		getAcCursoAperturados().add(acCursoAperturado);
	}



	/**
	 * Return the value associated with the column: AdEstructuraPagos
	 */
	public java.util.Set<net.uch.mapping.AdEstructuraPagos> getAdEstructuraPagos () {
		return adEstructuraPagos;
	}

	/**
	 * Set the value related to the column: AdEstructuraPagos
	 * @param adEstructuraPagos the AdEstructuraPagos value
	 */
	public void setAdEstructuraPagos (java.util.Set<net.uch.mapping.AdEstructuraPagos> adEstructuraPagos) {
		this.adEstructuraPagos = adEstructuraPagos;
	}

	public void addToAdEstructuraPagos (net.uch.mapping.AdEstructuraPagos adEstructuraPagos) {
		if (null == getAdEstructuraPagos()) setAdEstructuraPagos(new java.util.TreeSet<net.uch.mapping.AdEstructuraPagos>());
		getAdEstructuraPagos().add(adEstructuraPagos);
	}

    public String getSemAcademico() {
        return semAcademico;
    }

    public void setSemAcademico(String semAcademico) {
        this.semAcademico = semAcademico;
    }     



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcSemestre)) return false;
		else {
			net.uch.mapping.AcSemestre acSemestre = (net.uch.mapping.AcSemestre) obj;
			if (null == this.getId() || null == acSemestre.getId()) return false;
			else return (this.getId().equals(acSemestre.getId()));
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