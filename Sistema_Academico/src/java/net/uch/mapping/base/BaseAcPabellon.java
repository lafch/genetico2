package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_pabellon table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_pabellon"
 */

public abstract class BaseAcPabellon  implements Serializable {

	public static String REF = "AcPabellon";
	public static String PROP_PAB_DESCRIPCION = "PabDescripcion";
	public static String PROP_PAB_NOMBRE = "PabNombre";
	public static String PROP_LOC = "Loc";
	public static String PROP_ID = "Id";
	public static String PROP_PAB_ACTIVO = "PabActivo";


	// constructors
	public BaseAcPabellon () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcPabellon (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcPabellon (
		java.lang.Integer id,
		net.uch.mapping.AcLocal loc) {

		this.setId(id);
		this.setLoc(loc);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String pabNombre;
	private java.lang.String pabDescripcion;
	private java.lang.String pabActivo;

	// many to one
	private net.uch.mapping.AcLocal loc;

	// collections
	private java.util.Set<net.uch.mapping.AcAula> acAulas;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="pab_id"
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
	 * Return the value associated with the column: pab_nombre
	 */
	public java.lang.String getPabNombre () {
		return pabNombre;
	}

	/**
	 * Set the value related to the column: pab_nombre
	 * @param pabNombre the pab_nombre value
	 */
	public void setPabNombre (java.lang.String pabNombre) {
		this.pabNombre = pabNombre;
	}



	/**
	 * Return the value associated with the column: pab_descripcion
	 */
	public java.lang.String getPabDescripcion () {
		return pabDescripcion;
	}

	/**
	 * Set the value related to the column: pab_descripcion
	 * @param pabDescripcion the pab_descripcion value
	 */
	public void setPabDescripcion (java.lang.String pabDescripcion) {
		this.pabDescripcion = pabDescripcion;
	}



	/**
	 * Return the value associated with the column: pab_activo
	 */
	public java.lang.String getPabActivo () {
		return pabActivo;
	}

	/**
	 * Set the value related to the column: pab_activo
	 * @param pabActivo the pab_activo value
	 */
	public void setPabActivo (java.lang.String pabActivo) {
		this.pabActivo = pabActivo;
	}



	/**
	 * Return the value associated with the column: loc_id
	 */
	public net.uch.mapping.AcLocal getLoc () {
		return loc;
	}

	/**
	 * Set the value related to the column: loc_id
	 * @param loc the loc_id value
	 */
	public void setLoc (net.uch.mapping.AcLocal loc) {
		this.loc = loc;
	}



	/**
	 * Return the value associated with the column: AcAulas
	 */
	public java.util.Set<net.uch.mapping.AcAula> getAcAulas () {
		return acAulas;
	}

	/**
	 * Set the value related to the column: AcAulas
	 * @param acAulas the AcAulas value
	 */
	public void setAcAulas (java.util.Set<net.uch.mapping.AcAula> acAulas) {
		this.acAulas = acAulas;
	}

	public void addToAcAulas (net.uch.mapping.AcAula acAula) {
		if (null == getAcAulas()) setAcAulas(new java.util.TreeSet<net.uch.mapping.AcAula>());
		getAcAulas().add(acAula);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcPabellon)) return false;
		else {
			net.uch.mapping.AcPabellon acPabellon = (net.uch.mapping.AcPabellon) obj;
			if (null == this.getId() || null == acPabellon.getId()) return false;
			else return (this.getId().equals(acPabellon.getId()));
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