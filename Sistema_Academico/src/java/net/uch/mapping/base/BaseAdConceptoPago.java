package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ad_concepto_pago table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_concepto_pago"
 */

public abstract class BaseAdConceptoPago  implements Serializable {

	public static String REF = "AdConceptoPago";
	public static String PROP_CONPAG_CREACION = "ConpagCreacion";
	public static String PROP_CONPAG_TIPO = "ConpagTipo";
	public static String PROP_CONPAG_MONTO = "ConpagMonto";
	public static String PROP_CONPAG_ACTIVO = "ConpagActivo";
	public static String PROP_CONPAG_DESCRIPCION = "ConpagDescripcion";
	public static String PROP_CONPAG_CODIGO = "ConpagCodigo";
	public static String PROP_ID = "Id";
	public static String PROP_CONPAG_ACTUALIZACION = "ConpagActualizacion";
        public static String PROP_CONPAG_RUBRO = "ConpagRubro";


	// constructors
	public BaseAdConceptoPago () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdConceptoPago (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String conpagCodigo;
	private java.lang.String conpagDescripcion;
	private java.lang.Float conpagMonto;
	private java.lang.String conpagTipo;
	private java.util.Date conpagCreacion;
	private java.util.Date conpagActualizacion;
	private java.lang.String conpagActivo;
        private java.lang.String conpagRubro;

	// collections
	private java.util.Set<net.uch.mapping.AdPago> adPagos;
	private java.util.Set<net.uch.mapping.AdAlumnoTarifa> adAlumnoTarifas;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="conpag_id"
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
	 * Return the value associated with the column: conpag_codigo
	 */
	public java.lang.String getConpagCodigo () {
		return conpagCodigo;
	}

	/**
	 * Set the value related to the column: conpag_codigo
	 * @param conpagCodigo the conpag_codigo value
	 */
	public void setConpagCodigo (java.lang.String conpagCodigo) {
		this.conpagCodigo = conpagCodigo;
	}



	/**
	 * Return the value associated with the column: conpag_descripcion
	 */
	public java.lang.String getConpagDescripcion () {
		return conpagDescripcion;
	}

	/**
	 * Set the value related to the column: conpag_descripcion
	 * @param conpagDescripcion the conpag_descripcion value
	 */
	public void setConpagDescripcion (java.lang.String conpagDescripcion) {
		this.conpagDescripcion = conpagDescripcion;
	}



	/**
	 * Return the value associated with the column: conpag_monto
	 */
	public java.lang.Float getConpagMonto () {
		return conpagMonto;
	}

	/**
	 * Set the value related to the column: conpag_monto
	 * @param conpagMonto the conpag_monto value
	 */
	public void setConpagMonto (java.lang.Float conpagMonto) {
		this.conpagMonto = conpagMonto;
	}



	/**
	 * Return the value associated with the column: conpag_tipo
	 */
	public java.lang.String getConpagTipo () {
		return conpagTipo;
	}

	/**
	 * Set the value related to the column: conpag_tipo
	 * @param conpagTipo the conpag_tipo value
	 */
	public void setConpagTipo (java.lang.String conpagTipo) {
		this.conpagTipo = conpagTipo;
	}



	/**
	 * Return the value associated with the column: conpag_creacion
	 */
	public java.util.Date getConpagCreacion () {
		return conpagCreacion;
	}

	/**
	 * Set the value related to the column: conpag_creacion
	 * @param conpagCreacion the conpag_creacion value
	 */
	public void setConpagCreacion (java.util.Date conpagCreacion) {
		this.conpagCreacion = conpagCreacion;
	}



	/**
	 * Return the value associated with the column: conpag_actualizacion
	 */
	public java.util.Date getConpagActualizacion () {
		return conpagActualizacion;
	}

	/**
	 * Set the value related to the column: conpag_actualizacion
	 * @param conpagActualizacion the conpag_actualizacion value
	 */
	public void setConpagActualizacion (java.util.Date conpagActualizacion) {
		this.conpagActualizacion = conpagActualizacion;
	}



	/**
	 * Return the value associated with the column: conpag_activo
	 */
	public java.lang.String getConpagActivo () {
		return conpagActivo;
	}

	/**
	 * Set the value related to the column: conpag_activo
	 * @param conpagActivo the conpag_activo value
	 */
	public void setConpagActivo (java.lang.String conpagActivo) {
		this.conpagActivo = conpagActivo;
	}

    public String getConpagRubro() {
        return conpagRubro;
    }

    public void setConpagRubro(String conpagRubro) {
        this.conpagRubro = conpagRubro;
    }
        


	/**
	 * Return the value associated with the column: AdPagos
	 */
	public java.util.Set<net.uch.mapping.AdPago> getAdPagos () {
		return adPagos;
	}

	/**
	 * Set the value related to the column: AdPagos
	 * @param adPagos the AdPagos value
	 */
	public void setAdPagos (java.util.Set<net.uch.mapping.AdPago> adPagos) {
		this.adPagos = adPagos;
	}

	public void addToAdPagos (net.uch.mapping.AdPago adPago) {
		if (null == getAdPagos()) setAdPagos(new java.util.TreeSet<net.uch.mapping.AdPago>());
		getAdPagos().add(adPago);
	}



	/**
	 * Return the value associated with the column: AdAlumnoTarifas
	 */
	public java.util.Set<net.uch.mapping.AdAlumnoTarifa> getAdAlumnoTarifas () {
		return adAlumnoTarifas;
	}

	/**
	 * Set the value related to the column: AdAlumnoTarifas
	 * @param adAlumnoTarifas the AdAlumnoTarifas value
	 */
	public void setAdAlumnoTarifas (java.util.Set<net.uch.mapping.AdAlumnoTarifa> adAlumnoTarifas) {
		this.adAlumnoTarifas = adAlumnoTarifas;
	}

	public void addToAdAlumnoTarifas (net.uch.mapping.AdAlumnoTarifa adAlumnoTarifa) {
		if (null == getAdAlumnoTarifas()) setAdAlumnoTarifas(new java.util.TreeSet<net.uch.mapping.AdAlumnoTarifa>());
		getAdAlumnoTarifas().add(adAlumnoTarifa);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AdConceptoPago)) return false;
		else {
			net.uch.mapping.AdConceptoPago adConceptoPago = (net.uch.mapping.AdConceptoPago) obj;
			if (null == this.getId() || null == adConceptoPago.getId()) return false;
			else return (this.getId().equals(adConceptoPago.getId()));
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