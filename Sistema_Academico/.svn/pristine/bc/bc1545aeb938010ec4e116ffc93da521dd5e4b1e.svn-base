package net.uch.mapping.base;

import java.io.Serializable;
import java.util.Set;
import net.uch.mapping.AcHorarioArea;


/**
 * This is an object that contains data related to the ac_turno_detalle table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_turno_detalle"
 */

public abstract class BaseAcTurnoDetalle  implements Serializable {

	public static String REF = "AcTurnoDetalle";
	public static String PROP_TURDET_HORA = "TurdetHora";
	public static String PROP_TUR = "Tur";
	public static String PROP_TURDET_ACTIVO = "TurdetActivo";
	public static String PROP_ID = "Id";


	// constructors
	public BaseAcTurnoDetalle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcTurnoDetalle (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcTurnoDetalle (
		java.lang.Integer id,
		net.uch.mapping.AcTurno tur) {

		this.setId(id);
		this.setTur(tur);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date turdetHora;
	private java.lang.String turdetActivo;

	// many to one
	private net.uch.mapping.AcTurno tur;

	// collections
	private java.util.Set<net.uch.mapping.AcHorario> acHorarios;
        
        private java.util.Set<net.uch.mapping.AcHorarioArea> acHorarioAreas;

    public Set<AcHorarioArea> getAcHorarioAreas() {
        return acHorarioAreas;
    }

    public void setAcHorarioAreas(Set<AcHorarioArea> acHorarioAreas) {
        this.acHorarioAreas = acHorarioAreas;
    }
        



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="turdet_id"
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
	 * Return the value associated with the column: turdet_hora
	 */
	public java.util.Date getTurdetHora () {
		return turdetHora;
	}

	/**
	 * Set the value related to the column: turdet_hora
	 * @param turdetHora the turdet_hora value
	 */
	public void setTurdetHora (java.util.Date turdetHora) {
		this.turdetHora = turdetHora;
	}



	/**
	 * Return the value associated with the column: turdet_activo
	 */
	public java.lang.String getTurdetActivo () {
		return turdetActivo;
	}

	/**
	 * Set the value related to the column: turdet_activo
	 * @param turdetActivo the turdet_activo value
	 */
	public void setTurdetActivo (java.lang.String turdetActivo) {
		this.turdetActivo = turdetActivo;
	}



	/**
	 * Return the value associated with the column: tur_id
	 */
	public net.uch.mapping.AcTurno getTur () {
		return tur;
	}

	/**
	 * Set the value related to the column: tur_id
	 * @param tur the tur_id value
	 */
	public void setTur (net.uch.mapping.AcTurno tur) {
		this.tur = tur;
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
		if (!(obj instanceof net.uch.mapping.AcTurnoDetalle)) return false;
		else {
			net.uch.mapping.AcTurnoDetalle acTurnoDetalle = (net.uch.mapping.AcTurnoDetalle) obj;
			if (null == this.getId() || null == acTurnoDetalle.getId()) return false;
			else return (this.getId().equals(acTurnoDetalle.getId()));
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