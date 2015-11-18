package net.uch.mapping.base;

import net.uch.mapping.ClTaller;


/**
 * AbstractClPreRequisitos entity provides the base persistence definition of
 * the ClPreRequisitos entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseClPreRequisitos implements java.io.Serializable {

	// Fields

	private Integer preReqId;
	private ClTaller clTallerByTalIdRequisito;
	private ClTaller clTallerByTalId;
	private String preReqActivo;

	// Constructors

	/** default constructor */
	public BaseClPreRequisitos() {
	}

	/** minimal constructor */
	public BaseClPreRequisitos(ClTaller clTallerByTalId) {
		this.clTallerByTalId = clTallerByTalId;
	}

	/** full constructor */
	public BaseClPreRequisitos(ClTaller clTallerByTalIdRequisito,
			ClTaller clTallerByTalId, String preReqActivo) {
		this.clTallerByTalIdRequisito = clTallerByTalIdRequisito;
		this.clTallerByTalId = clTallerByTalId;
		this.preReqActivo = preReqActivo;
	}

	// Property accessors

	public Integer getPreReqId() {
		return this.preReqId;
	}

	public void setPreReqId(Integer preReqId) {
		this.preReqId = preReqId;
	}

	public ClTaller getClTallerByTalIdRequisito() {
		return this.clTallerByTalIdRequisito;
	}

	public void setClTallerByTalIdRequisito(ClTaller clTallerByTalIdRequisito) {
		this.clTallerByTalIdRequisito = clTallerByTalIdRequisito;
	}

	public ClTaller getClTallerByTalId() {
		return this.clTallerByTalId;
	}

	public void setClTallerByTalId(ClTaller clTallerByTalId) {
		this.clTallerByTalId = clTallerByTalId;
	}

	public String getPreReqActivo() {
		return this.preReqActivo;
	}

	public void setPreReqActivo(String preReqActivo) {
		this.preReqActivo = preReqActivo;
	}

}