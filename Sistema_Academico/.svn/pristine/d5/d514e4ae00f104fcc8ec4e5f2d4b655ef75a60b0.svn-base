package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_seccion table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_seccion"
 */

public abstract class BaseAcSeccion  implements Serializable {

	public static String REF = "AcSeccion";
	public static String PROP_SEC_CODIGO = "SecCodigo";
	public static String PROP_SEC_NOMBRE = "SecNombre";
	public static String PROP_SEC_ACTIVO = "SecActivo";
	public static String PROP_SEC_VACANTES = "SecVacantes";
	public static String PROP_CURAPE = "Curape";
	public static String PROP_ID = "Id";


	// constructors
	public BaseAcSeccion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcSeccion (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcSeccion (
		java.lang.Integer id,
		net.uch.mapping.AcCursoAperturado curape) {

		this.setId(id);
		this.setCurape(curape);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String secCodigo;
	private java.lang.String secNombre;
	private java.lang.Integer secVacantes;
	private java.lang.String secActivo;

	// many to one
	private net.uch.mapping.AcCursoAperturado curape;

	// collections
	private java.util.Set<net.uch.mapping.AcMatriculaCurso> acMatriculaCursos;
	private java.util.Set<net.uch.mapping.AcHorario> acHorarios;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="sec_id"
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
	 * Return the value associated with the column: sec_codigo
	 */
	public java.lang.String getSecCodigo () {
		return secCodigo;
	}

	/**
	 * Set the value related to the column: sec_codigo
	 * @param secCodigo the sec_codigo value
	 */
	public void setSecCodigo (java.lang.String secCodigo) {
		this.secCodigo = secCodigo;
	}



	/**
	 * Return the value associated with the column: sec_nombre
	 */
	public java.lang.String getSecNombre () {
		return secNombre;
	}

	/**
	 * Set the value related to the column: sec_nombre
	 * @param secNombre the sec_nombre value
	 */
	public void setSecNombre (java.lang.String secNombre) {
		this.secNombre = secNombre;
	}



	/**
	 * Return the value associated with the column: sec_vacantes
	 */
	public java.lang.Integer getSecVacantes () {
		return secVacantes;
	}

	/**
	 * Set the value related to the column: sec_vacantes
	 * @param secVacantes the sec_vacantes value
	 */
	public void setSecVacantes (java.lang.Integer secVacantes) {
		this.secVacantes = secVacantes;
	}



	/**
	 * Return the value associated with the column: sec_activo
	 */
	public java.lang.String getSecActivo () {
		return secActivo;
	}

	/**
	 * Set the value related to the column: sec_activo
	 * @param secActivo the sec_activo value
	 */
	public void setSecActivo (java.lang.String secActivo) {
		this.secActivo = secActivo;
	}



	/**
	 * Return the value associated with the column: curape_id
	 */
	public net.uch.mapping.AcCursoAperturado getCurape () {
		return curape;
	}

	/**
	 * Set the value related to the column: curape_id
	 * @param curape the curape_id value
	 */
	public void setCurape (net.uch.mapping.AcCursoAperturado curape) {
		this.curape = curape;
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



	/**
	 * Return the value associated with the column: AcHorarios
	 */
	public java.util.Set<net.uch.mapping.AcHorario> getAcHorarios () {
		return acHorarios;
	}

	/**
	 * Set the value related to the column: AcHorarios
	 * @param acHorarios the AcHorarios value
	 */
	public void setAcHorarios (java.util.Set<net.uch.mapping.AcHorario> acHorarios) {
		this.acHorarios = acHorarios;
	}

	public void addToAcHorarios (net.uch.mapping.AcHorario acHorario) {
		if (null == getAcHorarios()) setAcHorarios(new java.util.TreeSet<net.uch.mapping.AcHorario>());
		getAcHorarios().add(acHorario);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcSeccion)) return false;
		else {
			net.uch.mapping.AcSeccion acSeccion = (net.uch.mapping.AcSeccion) obj;
			if (null == this.getId() || null == acSeccion.getId()) return false;
			else return (this.getId().equals(acSeccion.getId()));
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