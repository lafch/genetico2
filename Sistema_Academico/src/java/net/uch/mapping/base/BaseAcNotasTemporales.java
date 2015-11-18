package net.uch.mapping.base;

import java.math.BigDecimal;
import java.util.Date;
import net.uch.mapping.AcSeccion;

/**
 * AbstractAcNotasTemporales entity provides the base persistence definition of
 * the AcNotasTemporales entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class BaseAcNotasTemporales implements java.io.Serializable {

	// Fields

	private Integer nottemId;
	private AcSeccion acSeccion;
	private Date nottemFecha;
	private BigDecimal nottemNota;
	private Integer sisevaPerId;
	private Integer aluId;
	private String nottemActivo;

	// Constructors

	/** default constructor */
	public BaseAcNotasTemporales() {
	}

	/** minimal constructor */
	public BaseAcNotasTemporales(Integer nottemId) {
		this.nottemId = nottemId;
	}

	/** full constructor */
	public BaseAcNotasTemporales(Integer nottemId, AcSeccion acSeccion,
			Date nottemFecha, BigDecimal nottemNota, Integer sisevaPerId,
			Integer aluId, String nottemActivo) {
		this.nottemId = nottemId;
		this.acSeccion = acSeccion;
		this.nottemFecha = nottemFecha;
		this.nottemNota = nottemNota;
		this.sisevaPerId = sisevaPerId;
		this.aluId = aluId;
		this.nottemActivo = nottemActivo;
	}

	// Property accessors

	public Integer getNottemId() {
		return this.nottemId;
	}

	public void setNottemId(Integer nottemId) {
		this.nottemId = nottemId;
	}

	public AcSeccion getAcSeccion() {
		return this.acSeccion;
	}

	public void setAcSeccion(AcSeccion acSeccion) {
		this.acSeccion = acSeccion;
	}

	public Date getNottemFecha() {
		return this.nottemFecha;
	}

	public void setNottemFecha(Date nottemFecha) {
		this.nottemFecha = nottemFecha;
	}

	public BigDecimal getNottemNota() {
		return this.nottemNota;
	}

	public void setNottemNota(BigDecimal nottemNota) {
		this.nottemNota = nottemNota;
	}

	public Integer getSisevaPerId() {
		return this.sisevaPerId;
	}

	public void setSisevaPerId(Integer sisevaPerId) {
		this.sisevaPerId = sisevaPerId;
	}

	public Integer getAluId() {
		return this.aluId;
	}

	public void setAluId(Integer aluId) {
		this.aluId = aluId;
	}

	public String getNottemActivo() {
		return this.nottemActivo;
	}

	public void setNottemActivo(String nottemActivo) {
		this.nottemActivo = nottemActivo;
	}

}