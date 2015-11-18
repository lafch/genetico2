package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_curso_aperturado table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_curso_aperturado"
 */

public abstract class BaseAcCursoAperturado  implements Serializable {

	public static String REF = "AcCursoAperturado";
	public static String PROP_CURAPE_ACTIVO = "CurapeActivo";
	public static String PROP_PLANCUR = "Plancur";
	public static String PROP_CURAPE_CODIGO = "CurapeCodigo";
	public static String PROP_SEM = "Sem";
	public static String PROP_SISEVA_ID = "SisevaId";
	public static String PROP_CURAPE_CREACION = "CurapeCreacion";
	public static String PROP_CURAPE_APERTURADO = "CurapeAperturado";
	public static String PROP_ID = "Id";


	// constructors
	public BaseAcCursoAperturado () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcCursoAperturado (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcCursoAperturado (
		java.lang.Integer id,
		net.uch.mapping.AcSemestre sem) {

		this.setId(id);
		this.setSem(sem);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String curapeCodigo;
	private java.lang.Integer sisevaId;
	private java.util.Date curapeCreacion;
	private java.lang.String curapeAperturado;
	private java.lang.String curapeActivo;

	// many to one
	private net.uch.mapping.AcSemestre sem;
	private net.uch.mapping.AcPlanCurso plancur;

	// collections
	private java.util.Set<net.uch.mapping.AcSeccion> acSeccions;
	private java.util.Set<net.uch.mapping.AcSisEvaPersonalizado> acSisEvaPersonalizados;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="curape_Id"
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
	 * Return the value associated with the column: curape_codigo
	 */
	public java.lang.String getCurapeCodigo () {
		return curapeCodigo;
	}

	/**
	 * Set the value related to the column: curape_codigo
	 * @param curapeCodigo the curape_codigo value
	 */
	public void setCurapeCodigo (java.lang.String curapeCodigo) {
		this.curapeCodigo = curapeCodigo;
	}



	/**
	 * Return the value associated with the column: siseva_id
	 */
	public java.lang.Integer getSisevaId () {
		return sisevaId;
	}

	/**
	 * Set the value related to the column: siseva_id
	 * @param sisevaId the siseva_id value
	 */
	public void setSisevaId (java.lang.Integer sisevaId) {
		this.sisevaId = sisevaId;
	}



	/**
	 * Return the value associated with the column: curape_creacion
	 */
	public java.util.Date getCurapeCreacion () {
		return curapeCreacion;
	}

	/**
	 * Set the value related to the column: curape_creacion
	 * @param curapeCreacion the curape_creacion value
	 */
	public void setCurapeCreacion (java.util.Date curapeCreacion) {
		this.curapeCreacion = curapeCreacion;
	}



	/**
	 * Return the value associated with the column: curape_aperturado
	 */
	public java.lang.String getCurapeAperturado () {
		return curapeAperturado;
	}

	/**
	 * Set the value related to the column: curape_aperturado
	 * @param curapeAperturado the curape_aperturado value
	 */
	public void setCurapeAperturado (java.lang.String curapeAperturado) {
		this.curapeAperturado = curapeAperturado;
	}



	/**
	 * Return the value associated with the column: curape_activo
	 */
	public java.lang.String getCurapeActivo () {
		return curapeActivo;
	}

	/**
	 * Set the value related to the column: curape_activo
	 * @param curapeActivo the curape_activo value
	 */
	public void setCurapeActivo (java.lang.String curapeActivo) {
		this.curapeActivo = curapeActivo;
	}



	/**
	 * Return the value associated with the column: sem_id
	 */
	public net.uch.mapping.AcSemestre getSem () {
		return sem;
	}

	/**
	 * Set the value related to the column: sem_id
	 * @param sem the sem_id value
	 */
	public void setSem (net.uch.mapping.AcSemestre sem) {
		this.sem = sem;
	}



	/**
	 * Return the value associated with the column: plancur_id
	 */
	public net.uch.mapping.AcPlanCurso getPlancur () {
		return plancur;
	}

	/**
	 * Set the value related to the column: plancur_id
	 * @param plancur the plancur_id value
	 */
	public void setPlancur (net.uch.mapping.AcPlanCurso plancur) {
		this.plancur = plancur;
	}



	/**
	 * Return the value associated with the column: AcSeccions
	 */
	public java.util.Set<net.uch.mapping.AcSeccion> getAcSeccions () {
		return acSeccions;
	}

	/**
	 * Set the value related to the column: AcSeccions
	 * @param acSeccions the AcSeccions value
	 */
	public void setAcSeccions (java.util.Set<net.uch.mapping.AcSeccion> acSeccions) {
		this.acSeccions = acSeccions;
	}

	public void addToAcSeccions (net.uch.mapping.AcSeccion acSeccion) {
		if (null == getAcSeccions()) setAcSeccions(new java.util.TreeSet<net.uch.mapping.AcSeccion>());
		getAcSeccions().add(acSeccion);
	}



	/**
	 * Return the value associated with the column: AcSisEvaPersonalizados
	 */
	public java.util.Set<net.uch.mapping.AcSisEvaPersonalizado> getAcSisEvaPersonalizados () {
		return acSisEvaPersonalizados;
	}

	/**
	 * Set the value related to the column: AcSisEvaPersonalizados
	 * @param acSisEvaPersonalizados the AcSisEvaPersonalizados value
	 */
	public void setAcSisEvaPersonalizados (java.util.Set<net.uch.mapping.AcSisEvaPersonalizado> acSisEvaPersonalizados) {
		this.acSisEvaPersonalizados = acSisEvaPersonalizados;
	}

	public void addToAcSisEvaPersonalizados (net.uch.mapping.AcSisEvaPersonalizado acSisEvaPersonalizado) {
		if (null == getAcSisEvaPersonalizados()) setAcSisEvaPersonalizados(new java.util.TreeSet<net.uch.mapping.AcSisEvaPersonalizado>());
		getAcSisEvaPersonalizados().add(acSisEvaPersonalizado);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcCursoAperturado)) return false;
		else {
			net.uch.mapping.AcCursoAperturado acCursoAperturado = (net.uch.mapping.AcCursoAperturado) obj;
			if (null == this.getId() || null == acCursoAperturado.getId()) return false;
			else return (this.getId().equals(acCursoAperturado.getId()));
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