package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ad_alumno_tarifa table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_alumno_tarifa"
 */

public abstract class BaseAdAlumnoTarifa  implements Serializable {

	public static String REF = "AdAlumnoTarifa";
	public static String PROP_ALU = "Alu";
	public static String PROP_ALUTAR_ACTIVO = "AlutarActivo";
	public static String PROP_CONPAG = "Conpag";
	public static String PROP_ALUTAR_FECHA_PRORROGA = "AlutarFechaProrroga";
	public static String PROP_ALUTAR_MONTO = "AlutarMonto";
        public static String PROP_ALUTAR_MONTO_COPY = "AlutarMontoCopy";
	public static String PROP_ALUTAR_FECHA_PAGO = "AlutarFechaPago";
	public static String PROP_ESTPAGDET = "Estpagdet";
	public static String PROP_ID = "Id";
	public static String PROP_ALUTAR_ESTADO = "AlutarEstado";
        public static String PROP_ALUTAR_MATRICULA = "AlutarMatricula";
        public static String PROP_MAT_ID = "MatId";


	// constructors
	public BaseAdAlumnoTarifa () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdAlumnoTarifa (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAdAlumnoTarifa (
		java.lang.Integer id,
		net.uch.mapping.AcAlumno alu,
		net.uch.mapping.AdConceptoPago conpag) {

		this.setId(id);
		this.setAlu(alu);
		this.setConpag(conpag);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Float alutarMonto;
        private java.lang.Float alutarMontoCopy;
	private java.util.Date alutarFechaPago;
	private java.util.Date alutarFechaProrroga;
	private java.lang.String alutarEstado;
	private java.lang.String alutarActivo;
        private java.lang.String alutarMatricula;
        private java.lang.Integer matId;

	// many to one
	private net.uch.mapping.AcAlumno alu;
	private net.uch.mapping.AdConceptoPago conpag;
	private net.uch.mapping.AdEstructuraPagosDetalle estpagdet;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="alutar_id"
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
	 * Return the value associated with the column: alutar_monto
	 */
	public java.lang.Float getAlutarMonto () {
		return alutarMonto;
	}
        
	/**
	 * Set the value related to the column: alutar_monto
	 * @param alutarMonto the alutar_monto value
	 */
	public void setAlutarMonto (java.lang.Float alutarMonto) {
		this.alutarMonto = alutarMonto;
	}
        
        public java.lang.Float getAlutarMontoCopy () {
		return alutarMontoCopy;
	}

        public void setAlutarMontoCopy (java.lang.Float alutarMontoCopy) {
		this.alutarMontoCopy = alutarMontoCopy;
	}

	/**
	 * Return the value associated with the column: alutar_fecha_pago
	 */
	public java.util.Date getAlutarFechaPago () {
		return alutarFechaPago;
	}

	/**
	 * Set the value related to the column: alutar_fecha_pago
	 * @param alutarFechaPago the alutar_fecha_pago value
	 */
	public void setAlutarFechaPago (java.util.Date alutarFechaPago) {
		this.alutarFechaPago = alutarFechaPago;
	}



	/**
	 * Return the value associated with the column: alutar_fecha_prorroga
	 */
	public java.util.Date getAlutarFechaProrroga () {
		return alutarFechaProrroga;
	}

	/**
	 * Set the value related to the column: alutar_fecha_prorroga
	 * @param alutarFechaProrroga the alutar_fecha_prorroga value
	 */
	public void setAlutarFechaProrroga (java.util.Date alutarFechaProrroga) {
		this.alutarFechaProrroga = alutarFechaProrroga;
	}



	/**
	 * Return the value associated with the column: alutar_estado
	 */
	public java.lang.String getAlutarEstado () {
		return alutarEstado;
	}

	/**
	 * Set the value related to the column: alutar_estado
	 * @param alutarEstado the alutar_estado value
	 */
	public void setAlutarEstado (java.lang.String alutarEstado) {
		this.alutarEstado = alutarEstado;
	}



	/**
	 * Return the value associated with the column: alutar_activo
	 */
	public java.lang.String getAlutarActivo () {
		return alutarActivo;
	}

	/**
	 * Set the value related to the column: alutar_activo
	 * @param alutarActivo the alutar_activo value
	 */
	public void setAlutarActivo (java.lang.String alutarActivo) {
		this.alutarActivo = alutarActivo;
	}



	/**
	 * Return the value associated with the column: alu_id
	 */
	public net.uch.mapping.AcAlumno getAlu () {
		return alu;
	}

    public String getAlutarMatricula() {
        return alutarMatricula;
    }

    public void setAlutarMatricula(String alutarMatricula) {
        this.alutarMatricula = alutarMatricula;
    }

    public Integer getMatId() {
        return matId;
    }

    public void setMatId(Integer matId) {
        this.matId = matId;
    }
        
        

	/**
	 * Set the value related to the column: alu_id
	 * @param alu the alu_id value
	 */
	public void setAlu (net.uch.mapping.AcAlumno alu) {
		this.alu = alu;
	}



	/**
	 * Return the value associated with the column: conpag_id
	 */
	public net.uch.mapping.AdConceptoPago getConpag () {
		return conpag;
	}

	/**
	 * Set the value related to the column: conpag_id
	 * @param conpag the conpag_id value
	 */
	public void setConpag (net.uch.mapping.AdConceptoPago conpag) {
		this.conpag = conpag;
	}



	/**
	 * Return the value associated with the column: estpagdet_id
	 */
	public net.uch.mapping.AdEstructuraPagosDetalle getEstpagdet () {
		return estpagdet;
	}

	/**
	 * Set the value related to the column: estpagdet_id
	 * @param estpagdet the estpagdet_id value
	 */
	public void setEstpagdet (net.uch.mapping.AdEstructuraPagosDetalle estpagdet) {
		this.estpagdet = estpagdet;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AdAlumnoTarifa)) return false;
		else {
			net.uch.mapping.AdAlumnoTarifa adAlumnoTarifa = (net.uch.mapping.AdAlumnoTarifa) obj;
			if (null == this.getId() || null == adAlumnoTarifa.getId()) return false;
			else return (this.getId().equals(adAlumnoTarifa.getId()));
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