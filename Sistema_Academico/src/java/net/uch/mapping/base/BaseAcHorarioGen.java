/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping.base;

import java.io.Serializable;

/**
 *
 * @author LUIS
 */
public class BaseAcHorarioGen implements Serializable {

	public static String REF = "AcHorarioGen";
	public static String PROP_HOR_TIPO_CLASE = "HorTipoClase";
	public static String PROP_DOC = "Doc";
	public static String PROP_TURDET = "Turdet";
	public static String PROP_HOR_CREACION = "HorCreacion";
	public static String PROP_AUL = "Aul";
	public static String PROP_HOR_DIA = "HorDia";
	public static String PROP_ID = "Id";
	public static String PROP_SEC = "Sec";
	public static String PROP_HOR_ACTIVO = "HorActivo";


	// constructors
	public BaseAcHorarioGen () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcHorarioGen (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcHorarioGen (
		java.lang.Integer id,
		net.uch.mapping.AcSeccion sec,
		net.uch.mapping.AcAula aul) {

		this.setId(id);
		this.setSec(sec);
		this.setAul(aul);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String horDia;
	private java.lang.String horActivo;
	private java.lang.String horTipoClase;
	private java.util.Date horCreacion;
        private java.lang.Integer usuario_id;
        private java.lang.Integer generacion;

	// many to one
	private net.uch.mapping.AcSeccion sec;
	private net.uch.mapping.AcDocente doc;
	private net.uch.mapping.AcTurnoDetalle turdet;
	private net.uch.mapping.AcAula aul;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="hor_id"
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
	 * Return the value associated with the column: hor_dia
	 */
	public java.lang.String getHorDia () {
		return horDia;
	}

	/**
	 * Set the value related to the column: hor_dia
	 * @param horDia the hor_dia value
	 */
	public void setHorDia (java.lang.String horDia) {
		this.horDia = horDia;
	}



	/**
	 * Return the value associated with the column: hor_activo
	 */
	public java.lang.String getHorActivo () {
		return horActivo;
	}

	/**
	 * Set the value related to the column: hor_activo
	 * @param horActivo the hor_activo value
	 */
	public void setHorActivo (java.lang.String horActivo) {
		this.horActivo = horActivo;
	}



	/**
	 * Return the value associated with the column: hor_tipo_clase
	 */
	public java.lang.String getHorTipoClase () {
		return horTipoClase;
	}

	/**
	 * Set the value related to the column: hor_tipo_clase
	 * @param horTipoClase the hor_tipo_clase value
	 */
	public void setHorTipoClase (java.lang.String horTipoClase) {
		this.horTipoClase = horTipoClase;
	}



	/**
	 * Return the value associated with the column: hor_creacion
	 */
	public java.util.Date getHorCreacion () {
		return horCreacion;
	}

	/**
	 * Set the value related to the column: hor_creacion
	 * @param horCreacion the hor_creacion value
	 */
	public void setHorCreacion (java.util.Date horCreacion) {
		this.horCreacion = horCreacion;
	}
        
        public void setAul (net.uch.mapping.AcAula aul) {
		this.aul = aul;
	}

        public Integer getUsuario_id() {
            return usuario_id;
        }

        public void setUsuario_id(Integer usuario_id) {
            this.usuario_id = usuario_id;
        }

        public Integer getGeneracion() {
            return generacion;
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



	/**
	 * Return the value associated with the column: doc_id
	 */
	public net.uch.mapping.AcDocente getDoc () {
		return doc;
	}

	/**
	 * Set the value related to the column: doc_id
	 * @param doc the doc_id value
	 */
	public void setDoc (net.uch.mapping.AcDocente doc) {
		this.doc = doc;
	}



	/**
	 * Return the value associated with the column: turdet_id
	 */
	public net.uch.mapping.AcTurnoDetalle getTurdet () {
		return turdet;
	}

	/**
	 * Set the value related to the column: turdet_id
	 * @param turdet the turdet_id value
	 */
	public void setTurdet (net.uch.mapping.AcTurnoDetalle turdet) {
		this.turdet = turdet;
	}



	/**
	 * Return the value associated with the column: aul_id
	 */
	public net.uch.mapping.AcAula getAul () {
		return aul;
	}

	/**
	 * Set the value related to the column: aul_id
	 * @param aul the aul_id value
	 */


        public void setGeneracion(Integer generacion) {
            this.generacion = generacion;
        }
        
        




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcHorario)) return false;
		else {
			net.uch.mapping.AcHorario acHorario = (net.uch.mapping.AcHorario) obj;
			if (null == this.getId() || null == acHorario.getId()) return false;
			else return (this.getId().equals(acHorario.getId()));
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

