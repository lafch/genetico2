package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_matricula_curso table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_matricula_curso"
 */

public abstract class BaseAcMatriculaCurso  implements Serializable {

	public static String REF = "AcMatriculaCurso";
	public static String PROP_MATCUR_OBS = "MatcurObs";
	public static String PROP_ID = "Id";
	public static String PROP_MATCUR_ACTIVO = "MatcurActivo";
	public static String PROP_MATCUR_ESTADO = "MatcurEstado";
	public static String PROP_MAT = "Mat";
	public static String PROP_SEC = "Sec";


	// constructors
	public BaseAcMatriculaCurso () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcMatriculaCurso (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcMatriculaCurso (
		java.lang.Integer id,
		net.uch.mapping.AcMatricula mat,
		net.uch.mapping.AcSeccion sec) {

		this.setId(id);
		this.setMat(mat);
		this.setSec(sec);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String matcurActivo;
	private java.lang.String matcurEstado;
	private java.lang.String matcurObs;

	// many to one
	private net.uch.mapping.AcMatricula mat;
	private net.uch.mapping.AcSeccion sec;

	// collections



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="matcur_id"
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
	 * Return the value associated with the column: matcur_activo
	 */
	public java.lang.String getMatcurActivo () {
		return matcurActivo;
	}

	/**
	 * Set the value related to the column: matcur_activo
	 * @param matcurActivo the matcur_activo value
	 */
	public void setMatcurActivo (java.lang.String matcurActivo) {
		this.matcurActivo = matcurActivo;
	}



	/**
	 * Return the value associated with the column: matcur_estado
	 */
	public java.lang.String getMatcurEstado () {
		return matcurEstado;
	}

	/**
	 * Set the value related to the column: matcur_estado
	 * @param matcurEstado the matcur_estado value
	 */
	public void setMatcurEstado (java.lang.String matcurEstado) {
		this.matcurEstado = matcurEstado;
	}



	/**
	 * Return the value associated with the column: matcur_obs
	 */
	public java.lang.String getMatcurObs () {
		return matcurObs;
	}

	/**
	 * Set the value related to the column: matcur_obs
	 * @param matcurObs the matcur_obs value
	 */
	public void setMatcurObs (java.lang.String matcurObs) {
		this.matcurObs = matcurObs;
	}



	/**
	 * Return the value associated with the column: mat_id
	 */
	public net.uch.mapping.AcMatricula getMat () {
		return mat;
	}

	/**
	 * Set the value related to the column: mat_id
	 * @param mat the mat_id value
	 */
	public void setMat (net.uch.mapping.AcMatricula mat) {
		this.mat = mat;
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


	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcMatriculaCurso)) return false;
		else {
			net.uch.mapping.AcMatriculaCurso acMatriculaCurso = (net.uch.mapping.AcMatriculaCurso) obj;
			if (null == this.getId() || null == acMatriculaCurso.getId()) return false;
			else return (this.getId().equals(acMatriculaCurso.getId()));
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