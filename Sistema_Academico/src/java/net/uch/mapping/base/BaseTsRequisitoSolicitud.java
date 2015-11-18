package net.uch.mapping.base;

import net.uch.mapping.TsRequisito;
import net.uch.mapping.TsSolicitud;

/**
 * AbstractTsRequisitoSolicitud generated by MyEclipse Persistence Tools
 */

public abstract class BaseTsRequisitoSolicitud implements
		java.io.Serializable {

	// Fields

	private Integer reqsolId;
	private TsSolicitud tsSolicitud;
	private TsRequisito tsRequisito;
	private String reqsolActivo;

	// Constructors

	/** default constructor */
	public BaseTsRequisitoSolicitud() {
	}

	/** minimal constructor */
	public BaseTsRequisitoSolicitud(Integer reqsolId) {
		this.reqsolId = reqsolId;
	}

	/** full constructor */
	public BaseTsRequisitoSolicitud(Integer reqsolId,
			TsSolicitud tsSolicitud, TsRequisito tsRequisito,
			String reqsolActivo) {
		this.reqsolId = reqsolId;
		this.tsSolicitud = tsSolicitud;
		this.tsRequisito = tsRequisito;
		this.reqsolActivo = reqsolActivo;
	}

	// Property accessors

	public Integer getReqsolId() {
		return this.reqsolId;
	}

	public void setReqsolId(Integer reqsolId) {
		this.reqsolId = reqsolId;
	}

	public TsSolicitud getTsSolicitud() {
		return this.tsSolicitud;
	}

	public void setTsSolicitud(TsSolicitud tsSolicitud) {
		this.tsSolicitud = tsSolicitud;
	}

	public TsRequisito getTsRequisito() {
		return this.tsRequisito;
	}

	public void setTsRequisito(TsRequisito tsRequisito) {
		this.tsRequisito = tsRequisito;
	}

	public String getReqsolActivo() {
		return this.reqsolActivo;
	}

	public void setReqsolActivo(String reqsolActivo) {
		this.reqsolActivo = reqsolActivo;
	}

}