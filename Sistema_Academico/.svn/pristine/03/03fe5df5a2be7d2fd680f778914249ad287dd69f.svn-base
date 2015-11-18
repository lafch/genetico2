package net.uch.mapping.base;

import net.uch.mapping.AcSeccion;
import net.uch.mapping.AcSisEvaPersonalizado;

/**
 * AbstractAcImportacionNotas entity provides the base persistence definition of
 * the AcImportacionNotas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class BaseAcImportacionNotas implements
		java.io.Serializable {

	// Fields

	private Integer impnotId;
	private AcSisEvaPersonalizado acSisEvaPersonalizado;
	private AcSeccion acSeccion;
	private String impnotActivo;
	private String impnotEstado;

	// Constructors

	/** default constructor */
	public BaseAcImportacionNotas() {
	}

	/** minimal constructor */
	public BaseAcImportacionNotas(Integer impnotId) {
		this.impnotId = impnotId;
	}

	/** full constructor */
	public BaseAcImportacionNotas(Integer impnotId,
			AcSisEvaPersonalizado acSisEvaPersonalizado, AcSeccion acSeccion,
			String impnotActivo, String impnotEstado) {
		this.impnotId = impnotId;
		this.acSisEvaPersonalizado = acSisEvaPersonalizado;
		this.acSeccion = acSeccion;
		this.impnotActivo = impnotActivo;
		this.impnotEstado = impnotEstado;
	}

	// Property accessors

	public Integer getImpnotId() {
		return this.impnotId;
	}

	public void setImpnotId(Integer impnotId) {
		this.impnotId = impnotId;
	}

	public AcSisEvaPersonalizado getAcSisEvaPersonalizado() {
		return this.acSisEvaPersonalizado;
	}

	public void setAcSisEvaPersonalizado(
			AcSisEvaPersonalizado acSisEvaPersonalizado) {
		this.acSisEvaPersonalizado = acSisEvaPersonalizado;
	}

	public AcSeccion getAcSeccion() {
		return this.acSeccion;
	}

	public void setAcSeccion(AcSeccion acSeccion) {
		this.acSeccion = acSeccion;
	}

	public String getImpnotActivo() {
		return this.impnotActivo;
	}

	public void setImpnotActivo(String impnotActivo) {
		this.impnotActivo = impnotActivo;
	}

	public String getImpnotEstado() {
		return this.impnotEstado;
	}

	public void setImpnotEstado(String impnotEstado) {
		this.impnotEstado = impnotEstado;
	}

}