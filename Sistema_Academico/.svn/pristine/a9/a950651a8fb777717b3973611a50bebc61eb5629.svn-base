package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_plancurricular table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_plancurricular"
 */

public abstract class BaseAcPlancurricular  implements Serializable {

	public static String REF = "AcPlancurricular";
	public static String PROP_PLAN_VIGENTE = "PlanVigente";
	public static String PROP_ESP = "Esp";
	public static String PROP_PLAN_ACTIVO = "PlanActivo";
	public static String PROP_PLAN_CREACION = "PlanCreacion";
	public static String PROP_PLAN_DESCRIPCION = "PlanDescripcion";
	public static String PROP_PLAN_CODIGO = "PlanCodigo";
	public static String PROP_PLAN_ACTUAL = "PlanActual";
	public static String PROP_ID = "Id";
	public static String PROP_PLAN_RESOLUCION = "PlanResolucion";


	// constructors
	public BaseAcPlancurricular () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcPlancurricular (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcPlancurricular (
		java.lang.Integer id,
		net.uch.mapping.AcEspecialidad esp) {

		this.setId(id);
		this.setEsp(esp);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String planCodigo;
	private java.lang.String planDescripcion;
	private java.util.Date planCreacion;
	private java.lang.String planResolucion;
	private java.lang.String planVigente;
	private java.lang.String planActual;
	private java.lang.String planActivo;

	// many to one
	private net.uch.mapping.AcEspecialidad esp;

	// collections
	private java.util.Set<net.uch.mapping.AcPlanCurso> acPlanCursos;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="plan_id"
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
	 * Return the value associated with the column: plan_codigo
	 */
	public java.lang.String getPlanCodigo () {
		return planCodigo;
	}

	/**
	 * Set the value related to the column: plan_codigo
	 * @param planCodigo the plan_codigo value
	 */
	public void setPlanCodigo (java.lang.String planCodigo) {
		this.planCodigo = planCodigo;
	}



	/**
	 * Return the value associated with the column: plan_descripcion
	 */
	public java.lang.String getPlanDescripcion () {
		return planDescripcion;
	}

	/**
	 * Set the value related to the column: plan_descripcion
	 * @param planDescripcion the plan_descripcion value
	 */
	public void setPlanDescripcion (java.lang.String planDescripcion) {
		this.planDescripcion = planDescripcion;
	}



	/**
	 * Return the value associated with the column: plan_creacion
	 */
	public java.util.Date getPlanCreacion () {
		return planCreacion;
	}

	/**
	 * Set the value related to the column: plan_creacion
	 * @param planCreacion the plan_creacion value
	 */
	public void setPlanCreacion (java.util.Date planCreacion) {
		this.planCreacion = planCreacion;
	}



	/**
	 * Return the value associated with the column: plan_resolucion
	 */
	public java.lang.String getPlanResolucion () {
		return planResolucion;
	}

	/**
	 * Set the value related to the column: plan_resolucion
	 * @param planResolucion the plan_resolucion value
	 */
	public void setPlanResolucion (java.lang.String planResolucion) {
		this.planResolucion = planResolucion;
	}



	/**
	 * Return the value associated with the column: plan_vigente
	 */
	public java.lang.String getPlanVigente () {
		return planVigente;
	}

	/**
	 * Set the value related to the column: plan_vigente
	 * @param planVigente the plan_vigente value
	 */
	public void setPlanVigente (java.lang.String planVigente) {
		this.planVigente = planVigente;
	}



	/**
	 * Return the value associated with the column: plan_actual
	 */
	public java.lang.String getPlanActual () {
		return planActual;
	}

	/**
	 * Set the value related to the column: plan_actual
	 * @param planActual the plan_actual value
	 */
	public void setPlanActual (java.lang.String planActual) {
		this.planActual = planActual;
	}



	/**
	 * Return the value associated with the column: plan_activo
	 */
	public java.lang.String getPlanActivo () {
		return planActivo;
	}

	/**
	 * Set the value related to the column: plan_activo
	 * @param planActivo the plan_activo value
	 */
	public void setPlanActivo (java.lang.String planActivo) {
		this.planActivo = planActivo;
	}



	/**
	 * Return the value associated with the column: esp_id
	 */
	public net.uch.mapping.AcEspecialidad getEsp () {
		return esp;
	}

	/**
	 * Set the value related to the column: esp_id
	 * @param esp the esp_id value
	 */
	public void setEsp (net.uch.mapping.AcEspecialidad esp) {
		this.esp = esp;
	}



	/**
	 * Return the value associated with the column: AcPlanCursos
	 */
	public java.util.Set<net.uch.mapping.AcPlanCurso> getAcPlanCursos () {
		return acPlanCursos;
	}

	/**
	 * Set the value related to the column: AcPlanCursos
	 * @param acPlanCursos the AcPlanCursos value
	 */
	public void setAcPlanCursos (java.util.Set<net.uch.mapping.AcPlanCurso> acPlanCursos) {
		this.acPlanCursos = acPlanCursos;
	}

	public void addToAcPlanCursos (net.uch.mapping.AcPlanCurso acPlanCurso) {
		if (null == getAcPlanCursos()) setAcPlanCursos(new java.util.TreeSet<net.uch.mapping.AcPlanCurso>());
		getAcPlanCursos().add(acPlanCurso);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcPlancurricular)) return false;
		else {
			net.uch.mapping.AcPlancurricular acPlancurricular = (net.uch.mapping.AcPlancurricular) obj;
			if (null == this.getId() || null == acPlancurricular.getId()) return false;
			else return (this.getId().equals(acPlancurricular.getId()));
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