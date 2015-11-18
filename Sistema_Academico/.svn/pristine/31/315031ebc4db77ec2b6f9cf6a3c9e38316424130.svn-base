package net.uch.mapping.base;

import java.io.Serializable;
import java.util.Date;


/**
 * This is an object that contains data related to the ad_pago table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_pago"
 */

public abstract class BaseAdPago  implements Serializable {

	public static String REF = "AdPago";
	public static String PROP_ALUTAR_ID = "AlutarId";
	public static String PROP_CONPAG = "Conpag";
	public static String PROP_COMPAG = "Compag";
	public static String PROP_PAG_ACTIVO = "PagActivo";
	public static String PROP_USU_ID = "UsuId";
	public static String PROP_PAG_MONTO = "PagMonto";
	public static String PROP_PAG_FECHA = "PagFecha";
        public static String PROP_PAG_FECHA_CANCELACION = "PagFechaCancelacion";
        public static String PROP_PAG_ESTADO_CANCELACION = "PagEstadoCancelacion";
	public static String PROP_ID = "Id";
        public static String PRO_COMPAG_DET_ID="ConpagDetId";


	// constructors
	public BaseAdPago () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdPago (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Float pagMonto;
	private java.util.Date pagFecha;
	private java.lang.Integer alutarId;
	private java.lang.String pagActivo;
	private java.lang.Integer usuId;
        private java.lang.String pagEstadoCancelacion;
        private java.util.Date pagFechaCancelacion;
	// many to one
	private net.uch.mapping.AdConceptoPago conpag;
	private net.uch.mapping.AdComprobantePago compag;
        private java.lang.Integer conpagDetId;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="pag_id"
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
	 * Return the value associated with the column: pag_monto
	 */
	public java.lang.Float getPagMonto () {
		return pagMonto;
	}

	/**
	 * Set the value related to the column: pag_monto
	 * @param pagMonto the pag_monto value
	 */
	public void setPagMonto (java.lang.Float pagMonto) {
		this.pagMonto = pagMonto;
	}



	/**
	 * Return the value associated with the column: pag_fecha
	 */
	public java.util.Date getPagFecha () {
		return pagFecha;
	}

	/**
	 * Set the value related to the column: pag_fecha
	 * @param pagFecha the pag_fecha value
	 */
	public void setPagFecha (java.util.Date pagFecha) {
		this.pagFecha = pagFecha;
	}



	/**
	 * Return the value associated with the column: alutar_id
	 */
	public java.lang.Integer getAlutarId () {
		return alutarId;
	}

	/**
	 * Set the value related to the column: alutar_id
	 * @param alutarId the alutar_id value
	 */
	public void setAlutarId (java.lang.Integer alutarId) {
		this.alutarId = alutarId;
	}



	/**
	 * Return the value associated with the column: pag_activo
	 */
	public java.lang.String getPagActivo () {
		return pagActivo;
	}

	/**
	 * Set the value related to the column: pag_activo
	 * @param pagActivo the pag_activo value
	 */
	public void setPagActivo (java.lang.String pagActivo) {
		this.pagActivo = pagActivo;
	}



	/**
	 * Return the value associated with the column: usu_id
	 */
	public java.lang.Integer getUsuId () {
		return usuId;
	}

	/**
	 * Set the value related to the column: usu_id
	 * @param usuId the usu_id value
	 */
	public void setUsuId (java.lang.Integer usuId) {
		this.usuId = usuId;
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
	 * Return the value associated with the column: compag_id
	 */
	public net.uch.mapping.AdComprobantePago getCompag () {
		return compag;
	}

	/**
	 * Set the value related to the column: compag_id
	 * @param compag the compag_id value
	 */
	public void setCompag (net.uch.mapping.AdComprobantePago compag) {
		this.compag = compag;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AdPago)) return false;
		else {
			net.uch.mapping.AdPago adPago = (net.uch.mapping.AdPago) obj;
			if (null == this.getId() || null == adPago.getId()) return false;
			else return (this.getId().equals(adPago.getId()));
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

    public String getPagEstadoCancelacion() {
        return pagEstadoCancelacion;
    }

    public void setPagEstadoCancelacion(String pagEstadoCancelacion) {
        this.pagEstadoCancelacion = pagEstadoCancelacion;
    }

    public Date getPagFechaCancelacion() {
        return pagFechaCancelacion;
    }

    public void setPagFechaCancelacion(Date pagFechaCancelacion) {
        this.pagFechaCancelacion = pagFechaCancelacion;
    }

	public String toString () {
		return super.toString();
	}

    public Integer getConpagDetId() {
        return conpagDetId;
    }

    public void setConpagDetId(Integer conpagDetId) {
        this.conpagDetId = conpagDetId;
    }


}