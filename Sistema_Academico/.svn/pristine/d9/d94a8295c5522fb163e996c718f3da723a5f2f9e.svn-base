package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_plan_curso table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_plan_curso"
 */

public abstract class BaseAcPlanCurso  implements Serializable {

	public static String REF = "AcPlanCurso";
	public static String PROP_PLANCUR_TIPO = "PlancurTipo";
	public static String PROP_CUR = "Cur";
	public static String PROP_PLANCUR_ACTIVO = "PlancurActivo";
	public static String PROP_ID = "Id";
	public static String PROP_PLANCUR_CODIGO = "PlancurCodigo";
	public static String PROP_PLANCUR_CICLO = "PlancurCiclo";
	public static String PROP_PLANCUR_CREDITO = "PlancurCredito";
	public static String PROP_PLAN = "Plan";


	// constructors
	public BaseAcPlanCurso () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcPlanCurso (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcPlanCurso (
		java.lang.Integer id,
		net.uch.mapping.AcCurso cur,
		net.uch.mapping.AcPlancurricular plan) {

		this.setId(id);
		this.setCur(cur);
		this.setPlan(plan);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String plancurCiclo;
	private java.lang.String plancurCredito;
	private java.lang.String plancurTipo;
	private java.lang.String plancurActivo;
	private java.lang.String plancurCodigo;

	// many to one
	private net.uch.mapping.AcCurso cur;
	private net.uch.mapping.AcPlancurricular plan;

	// collections
	private java.util.Set<net.uch.mapping.AcEquivalencia> acEquivalencias;
	private java.util.Set<net.uch.mapping.AcContenidoTematico> acContenidoTematicos;
	private java.util.Set<net.uch.mapping.AcCursoAperturado> acCursoAperturados;
	private java.util.Set<net.uch.mapping.AcPreRequisitos> acPreRequisitosByPlancur;
	private java.util.Set<net.uch.mapping.AcPreRequisitos> acPreRequisitosByPlancurIdRequisito;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="plancur_Id"
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
	 * Return the value associated with the column: plancur_ciclo
	 */
	public java.lang.String getPlancurCiclo () {
		return plancurCiclo;
	}

	/**
	 * Set the value related to the column: plancur_ciclo
	 * @param plancurCiclo the plancur_ciclo value
	 */
	public void setPlancurCiclo (java.lang.String plancurCiclo) {
		this.plancurCiclo = plancurCiclo;
	}



	/**
	 * Return the value associated with the column: plancur_credito
	 */
	public java.lang.String getPlancurCredito () {
		return plancurCredito;
	}

	/**
	 * Set the value related to the column: plancur_credito
	 * @param plancurCredito the plancur_credito value
	 */
	public void setPlancurCredito (java.lang.String plancurCredito) {
		this.plancurCredito = plancurCredito;
	}



	/**
	 * Return the value associated with the column: plancur_tipo
	 */
	public java.lang.String getPlancurTipo () {
		return plancurTipo;
	}

	/**
	 * Set the value related to the column: plancur_tipo
	 * @param plancurTipo the plancur_tipo value
	 */
	public void setPlancurTipo (java.lang.String plancurTipo) {
		this.plancurTipo = plancurTipo;
	}



	/**
	 * Return the value associated with the column: plancur_activo
	 */
	public java.lang.String getPlancurActivo () {
		return plancurActivo;
	}

	/**
	 * Set the value related to the column: plancur_activo
	 * @param plancurActivo the plancur_activo value
	 */
	public void setPlancurActivo (java.lang.String plancurActivo) {
		this.plancurActivo = plancurActivo;
	}



	/**
	 * Return the value associated with the column: plancur_codigo
	 */
	public java.lang.String getPlancurCodigo () {
		return plancurCodigo;
	}

	/**
	 * Set the value related to the column: plancur_codigo
	 * @param plancurCodigo the plancur_codigo value
	 */
	public void setPlancurCodigo (java.lang.String plancurCodigo) {
		this.plancurCodigo = plancurCodigo;
	}



	/**
	 * Return the value associated with the column: cur_id
	 */
	public net.uch.mapping.AcCurso getCur () {
		return cur;
	}

	/**
	 * Set the value related to the column: cur_id
	 * @param cur the cur_id value
	 */
	public void setCur (net.uch.mapping.AcCurso cur) {
		this.cur = cur;
	}



	/**
	 * Return the value associated with the column: plan_id
	 */
	public net.uch.mapping.AcPlancurricular getPlan () {
		return plan;
	}

	/**
	 * Set the value related to the column: plan_id
	 * @param plan the plan_id value
	 */
	public void setPlan (net.uch.mapping.AcPlancurricular plan) {
		this.plan = plan;
	}



	/**
	 * Return the value associated with the column: AcEquivalencias
	 */
	public java.util.Set<net.uch.mapping.AcEquivalencia> getAcEquivalencias () {
		return acEquivalencias;
	}

	/**
	 * Set the value related to the column: AcEquivalencias
	 * @param acEquivalencias the AcEquivalencias value
	 */
	public void setAcEquivalencias (java.util.Set<net.uch.mapping.AcEquivalencia> acEquivalencias) {
		this.acEquivalencias = acEquivalencias;
	}

	public void addToAcEquivalencias (net.uch.mapping.AcEquivalencia acEquivalencia) {
		if (null == getAcEquivalencias()) setAcEquivalencias(new java.util.TreeSet<net.uch.mapping.AcEquivalencia>());
		getAcEquivalencias().add(acEquivalencia);
	}



	/**
	 * Return the value associated with the column: AcContenidoTematicos
	 */
	public java.util.Set<net.uch.mapping.AcContenidoTematico> getAcContenidoTematicos () {
		return acContenidoTematicos;
	}

	/**
	 * Set the value related to the column: AcContenidoTematicos
	 * @param acContenidoTematicos the AcContenidoTematicos value
	 */
	public void setAcContenidoTematicos (java.util.Set<net.uch.mapping.AcContenidoTematico> acContenidoTematicos) {
		this.acContenidoTematicos = acContenidoTematicos;
	}

	public void addToAcContenidoTematicos (net.uch.mapping.AcContenidoTematico acContenidoTematico) {
		if (null == getAcContenidoTematicos()) setAcContenidoTematicos(new java.util.TreeSet<net.uch.mapping.AcContenidoTematico>());
		getAcContenidoTematicos().add(acContenidoTematico);
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
	 * Return the value associated with the column: AcPreRequisitosByPlancur
	 */
	public java.util.Set<net.uch.mapping.AcPreRequisitos> getAcPreRequisitosByPlancur () {
		return acPreRequisitosByPlancur;
	}

	/**
	 * Set the value related to the column: AcPreRequisitosByPlancur
	 * @param acPreRequisitosByPlancur the AcPreRequisitosByPlancur value
	 */
	public void setAcPreRequisitosByPlancur (java.util.Set<net.uch.mapping.AcPreRequisitos> acPreRequisitosByPlancur) {
		this.acPreRequisitosByPlancur = acPreRequisitosByPlancur;
	}

	public void addToAcPreRequisitosByPlancur (net.uch.mapping.AcPreRequisitos acPreRequisitos) {
		if (null == getAcPreRequisitosByPlancur()) setAcPreRequisitosByPlancur(new java.util.TreeSet<net.uch.mapping.AcPreRequisitos>());
		getAcPreRequisitosByPlancur().add(acPreRequisitos);
	}



	/**
	 * Return the value associated with the column: AcPreRequisitosByPlancurIdRequisito
	 */
	public java.util.Set<net.uch.mapping.AcPreRequisitos> getAcPreRequisitosByPlancurIdRequisito () {
		return acPreRequisitosByPlancurIdRequisito;
	}

	/**
	 * Set the value related to the column: AcPreRequisitosByPlancurIdRequisito
	 * @param acPreRequisitosByPlancurIdRequisito the AcPreRequisitosByPlancurIdRequisito value
	 */
	public void setAcPreRequisitosByPlancurIdRequisito (java.util.Set<net.uch.mapping.AcPreRequisitos> acPreRequisitosByPlancurIdRequisito) {
		this.acPreRequisitosByPlancurIdRequisito = acPreRequisitosByPlancurIdRequisito;
	}

	public void addToAcPreRequisitosByPlancurIdRequisito (net.uch.mapping.AcPreRequisitos acPreRequisitos) {
		if (null == getAcPreRequisitosByPlancurIdRequisito()) setAcPreRequisitosByPlancurIdRequisito(new java.util.TreeSet<net.uch.mapping.AcPreRequisitos>());
		getAcPreRequisitosByPlancurIdRequisito().add(acPreRequisitos);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcPlanCurso)) return false;
		else {
			net.uch.mapping.AcPlanCurso acPlanCurso = (net.uch.mapping.AcPlanCurso) obj;
			if (null == this.getId() || null == acPlanCurso.getId()) return false;
			else return (this.getId().equals(acPlanCurso.getId()));
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