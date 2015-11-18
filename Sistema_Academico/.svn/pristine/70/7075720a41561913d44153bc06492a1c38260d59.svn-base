package net.uch.mapping.base;

import java.io.Serializable;
import java.util.Date;


/**
 * This is an object that contains data related to the ad_comprobante_pago table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ad_comprobante_pago"
 */

public abstract class BaseAdComprobantePago  implements Serializable {

	public static String REF = "AdComprobantePago";
	public static String PROP_COMPAG_ACTIVO = "CompagActivo";
    public static String PROP_COMPAG_TIPO = "CompagTipo";
	public static String PROP_COMPAG_ALU = "CompagAlu";
	public static String PROP_COMPAG_NRO = "CompagNro";
	public static String PROP_COMPAG_PROCEDENCIA = "CompagProcedencia";
	public static String PROP_COMPAG_FECHA = "CompagFecha";
	public static String PROP_COMPAG_MONTO = "CompagMonto";
	public static String PROP_COMPAG_USADO = "CompagUsado";
	public static String PROP_COMPAG_CLIENTE = "CompagCliente";
	public static String PROP_ID = "Id";
	public static String PROP_COMPAG_CLIENTE_TIPO = "CompagClienteTipo";
	public static String PROP_COMPAG_DES = "CompagDes";
	public static String PROP_COMPAG_CREACION = "CompagCreacion";

        public static String PROP_COMPAG_ESTADO_CANCELACION = "compag_estado_cancelacion";
	public static String PROP_COMPAG_FECHA_CANCELACION = "compag_fecha_cancelacion";


    public static String PROP_COMPAG_TIP_DOC = "CompagTipDoc";
    public static String PROP_COMPAG_ALU_TIPO = "CompagAluTipo";
    public static String PROP_COMPAG_VOUCHER_FECHA = "CompagVoucherFecha";
    public static String PROP_COMPAG_VOUCHER_COD_AGENCIA = "CompagVoucherCodAgencia";
    public static String PROP_COMPAG_VOUCHER_NRO_OPERACION = "CompagVoucherNroOperacion";
        


	// constructors
	public BaseAdComprobantePago () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAdComprobantePago (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAdComprobantePago (
		java.lang.Integer id,
		java.lang.Integer compagCliente) {

		this.setId(id);
		this.setCompagCliente(compagCliente);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String compagNro;
	private java.lang.Float compagMonto;
	private java.util.Date compagFecha;
	private java.lang.Integer compagCliente;
	private java.lang.String compagClienteTipo;
	private java.lang.String compagUsado;
	private java.lang.String compagProcedencia;
	private java.util.Date compagCreacion;
	private java.lang.String compagActivo;
    private java.lang.String compagTipo;
	private java.lang.String compagDes;
	private java.lang.Integer compagAlu;
    private java.lang.String compagTipDoc;
    private java.lang.String compagAluTipo;
    private java.util.Date compagVoucherFecha;
    private java.lang.String compagVoucherCodAgencia;
    private java.lang.String compagVoucherNroOperacion;

    private java.lang.String compagEstadoCancelacion;
    private java.util.Date compagFechaCancelacion;

	// collections
	private java.util.Set<net.uch.mapping.AdPago> adPagos;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="compag_id"
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
	 * Return the value associated with the column: compag_nro
	 */
	public java.lang.String getCompagNro () {
		return compagNro;
	}

	/**
	 * Set the value related to the column: compag_nro
	 * @param compagNro the compag_nro value
	 */
	public void setCompagNro (java.lang.String compagNro) {
		this.compagNro = compagNro;
	}



	/**
	 * Return the value associated with the column: compag_monto
	 */
	public java.lang.Float getCompagMonto () {
		return compagMonto;
	}

	/**
	 * Set the value related to the column: compag_monto
	 * @param compagMonto the compag_monto value
	 */
	public void setCompagMonto (java.lang.Float compagMonto) {
		this.compagMonto = compagMonto;
	}



	/**
	 * Return the value associated with the column: compag_fecha
	 */
	public java.util.Date getCompagFecha () {
		return compagFecha;
	}

	/**
	 * Set the value related to the column: compag_fecha
	 * @param compagFecha the compag_fecha value
	 */
	public void setCompagFecha (java.util.Date compagFecha) {
		this.compagFecha = compagFecha;
	}



	/**
	 * Return the value associated with the column: compag_cliente
	 */
	public java.lang.Integer getCompagCliente () {
		return compagCliente;
	}

	/**
	 * Set the value related to the column: compag_cliente
	 * @param compagCliente the compag_cliente value
	 */
	public void setCompagCliente (java.lang.Integer compagCliente) {
		this.compagCliente = compagCliente;
	}



	/**
	 * Return the value associated with the column: compag_cliente_tipo
	 */
	public java.lang.String getCompagClienteTipo () {
		return compagClienteTipo;
	}

	/**
	 * Set the value related to the column: compag_cliente_tipo
	 * @param compagClienteTipo the compag_cliente_tipo value
	 */
	public void setCompagClienteTipo (java.lang.String compagClienteTipo) {
		this.compagClienteTipo = compagClienteTipo;
	}



	/**
	 * Return the value associated with the column: compag_usado
	 */
	public java.lang.String getCompagUsado () {
		return compagUsado;
	}

	/**
	 * Set the value related to the column: compag_usado
	 * @param compagUsado the compag_usado value
	 */
	public void setCompagUsado (java.lang.String compagUsado) {
		this.compagUsado = compagUsado;
	}



	/**
	 * Return the value associated with the column: compag_procedencia
	 */
	public java.lang.String getCompagProcedencia () {
		return compagProcedencia;
	}

	/**
	 * Set the value related to the column: compag_procedencia
	 * @param compagProcedencia the compag_procedencia value
	 */
	public void setCompagProcedencia (java.lang.String compagProcedencia) {
		this.compagProcedencia = compagProcedencia;
	}



	/**
	 * Return the value associated with the column: compag_creacion
	 */
	public java.util.Date getCompagCreacion () {
		return compagCreacion;
	}

	/**
	 * Set the value related to the column: compag_creacion
	 * @param compagCreacion the compag_creacion value
	 */
	public void setCompagCreacion (java.util.Date compagCreacion) {
		this.compagCreacion = compagCreacion;
	}



	/**
	 * Return the value associated with the column: compag_activo
	 */
	public java.lang.String getCompagActivo () {
		return compagActivo;
	}

	/**
	 * Set the value related to the column: compag_activo
	 * @param compagActivo the compag_activo value
	 */
	public void setCompagActivo (java.lang.String compagActivo) {
		this.compagActivo = compagActivo;
	}


        
	/**
	 * Return the value associated with the column: compag_activo
	 */
	public java.lang.String getCompagTipo () {
		return compagTipo;
	}

	/**
	 * Set the value related to the column: compag_activo
	 * @param compagActivo the compag_activo value
	 */
	public void setCompagTipo (java.lang.String compagTipo) {
		this.compagTipo = compagTipo;
	}


        

	/**
	 * Return the value associated with the column: compag_des
	 */
	public java.lang.String getCompagDes () {
		return compagDes;
	}

	/**
	 * Set the value related to the column: compag_des
	 * @param compagDes the compag_des value
	 */
	public void setCompagDes (java.lang.String compagDes) {
		this.compagDes = compagDes;
	}



	/**
	 * Return the value associated with the column: compag_alu
	 */
	public java.lang.Integer getCompagAlu () {
		return compagAlu;
	}

	/**
	 * Set the value related to the column: compag_alu
	 * @param compagAlu the compag_alu value
	 */
	public void setCompagAlu (java.lang.Integer compagAlu) {
		this.compagAlu = compagAlu;
	}

        public String getCompagAluTipo() {
            return compagAluTipo;
        }

        public void setCompagAluTipo(String compagAluTipo) {
            this.compagAluTipo = compagAluTipo;
        }

        public String getCompagTipDoc() {
            return compagTipDoc;
        }

        public void setCompagTipDoc(String compagTipDoc) {
            this.compagTipDoc = compagTipDoc;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AdComprobantePago)) return false;
		else {
			net.uch.mapping.AdComprobantePago adComprobantePago = (net.uch.mapping.AdComprobantePago) obj;
			if (null == this.getId() || null == adComprobantePago.getId()) return false;
			else return (this.getId().equals(adComprobantePago.getId()));
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

    public String getCompagVoucherCodAgencia() {
        return compagVoucherCodAgencia;
    }

    public void setCompagVoucherCodAgencia(String compagVoucherCodAgencia) {
        this.compagVoucherCodAgencia = compagVoucherCodAgencia;
    }

    public Date getCompagVoucherFecha() {
        return compagVoucherFecha;
    }

    public void setCompagVoucherFecha(Date compagVoucherFecha) {
        this.compagVoucherFecha = compagVoucherFecha;
    }

    public String getCompagVoucherNroOperacion() {
        return compagVoucherNroOperacion;
    }

    public void setCompagVoucherNroOperacion(String compagVoucherNroOperacion) {
        this.compagVoucherNroOperacion = compagVoucherNroOperacion;
    }

    public String getCompagEstadoCancelacion() {
        return compagEstadoCancelacion;
    }

    public void setCompagEstadoCancelacion(String compagEstadoCancelacion) {
        this.compagEstadoCancelacion = compagEstadoCancelacion;
    }

    public Date getCompagFechaCancelacion() {
        return compagFechaCancelacion;
    }

    public void setCompagFechaCancelacion(Date compagFechaCancelacion) {
        this.compagFechaCancelacion = compagFechaCancelacion;
    }

    


}