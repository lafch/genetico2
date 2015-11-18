package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_matricula table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_matricula"
 */

public abstract class BaseAcMatricula  implements Serializable {

	public static String REF = "AcMatricula";
	public static String PROP_ALU = "Alu";
	public static String PROP_MAT_FECHA = "MatFecha";
	public static String PROP_MAT_CODIGO = "MatCodigo";
	public static String PROP_MAT_TIPO = "MatTipo";
	public static String PROP_ID = "Id";
	public static String PROP_MAT_ACTIVO = "MatActivo";
	public static String PROP_USU_ID = "UsuId";
        public static String PROP_SEM_ID = "SemId";


	// constructors
	public BaseAcMatricula () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcMatricula (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcMatricula (
		java.lang.Integer id,
		net.uch.mapping.AcAlumno alu) {

		this.setId(id);
		this.setAlu(alu);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String matCodigo;
	private java.util.Date matFecha;
	private java.lang.String matTipo;
	private java.lang.String matActivo;
	private java.lang.Integer usuId;
        private java.lang.Integer semId;

	// many to one
	private net.uch.mapping.AcAlumno alu;

	// collections
	private java.util.Set<net.uch.mapping.AcMatriculaCurso> acMatriculaCursos;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="mat_id"
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
	 * Return the value associated with the column: mat_codigo
	 */
	public java.lang.String getMatCodigo () {
		return matCodigo;
	}

	/**
	 * Set the value related to the column: mat_codigo
	 * @param matCodigo the mat_codigo value
	 */
	public void setMatCodigo (java.lang.String matCodigo) {
		this.matCodigo = matCodigo;
	}



	/**
	 * Return the value associated with the column: mat_fecha
	 */
	public java.util.Date getMatFecha () {
		return matFecha;
	}

	/**
	 * Set the value related to the column: mat_fecha
	 * @param matFecha the mat_fecha value
	 */
	public void setMatFecha (java.util.Date matFecha) {
		this.matFecha = matFecha;
	}



	/**
	 * Return the value associated with the column: mat_tipo
	 */
	public java.lang.String getMatTipo () {
		return matTipo;
	}

	/**
	 * Set the value related to the column: mat_tipo
	 * @param matTipo the mat_tipo value
	 */
	public void setMatTipo (java.lang.String matTipo) {
		this.matTipo = matTipo;
	}



	/**
	 * Return the value associated with the column: mat_activo
	 */
	public java.lang.String getMatActivo () {
		return matActivo;
	}

	/**
	 * Set the value related to the column: mat_activo
	 * @param matActivo the mat_activo value
	 */
	public void setMatActivo (java.lang.String matActivo) {
		this.matActivo = matActivo;
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
	 * Return the value associated with the column: usu_id
	 */
	public java.lang.Integer getUsuId () {
		return usuId;
	}

	/**
	 * Set the value related to the column: usu_id
	 * @param usuId the usu_id value
	 */
	public void setUsuId (java.lang.Integer usuId) {
		this.usuId = usuId;
	}



	/**
	 * Return the value associated with the column: alu_id
	 */
	public net.uch.mapping.AcAlumno getAlu () {
		return alu;
	}

	/**
	 * Set the value related to the column: alu_id
	 * @param alu the alu_id value
	 */
	public void setAlu (net.uch.mapping.AcAlumno alu) {
		this.alu = alu;
	}



	/**
	 * Return the value associated with the column: AcMatriculaCursos
	 */
	public java.util.Set<net.uch.mapping.AcMatriculaCurso> getAcMatriculaCursos () {
		return acMatriculaCursos;
	}

	/**
	 * Set the value related to the column: AcMatriculaCursos
	 * @param acMatriculaCursos the AcMatriculaCursos value
	 */
	public void setAcMatriculaCursos (java.util.Set<net.uch.mapping.AcMatriculaCurso> acMatriculaCursos) {
		this.acMatriculaCursos = acMatriculaCursos;
	}

	public void addToAcMatriculaCursos (net.uch.mapping.AcMatriculaCurso acMatriculaCurso) {
		if (null == getAcMatriculaCursos()) setAcMatriculaCursos(new java.util.TreeSet<net.uch.mapping.AcMatriculaCurso>());
		getAcMatriculaCursos().add(acMatriculaCurso);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcMatricula)) return false;
		else {
			net.uch.mapping.AcMatricula acMatricula = (net.uch.mapping.AcMatricula) obj;
			if (null == this.getId() || null == acMatricula.getId()) return false;
			else return (this.getId().equals(acMatricula.getId()));
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