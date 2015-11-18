package net.uch.mapping.base;

import java.util.Date;

import net.uch.mapping.AcArea;
import net.uch.mapping.AcTurnoDetalle;

/**
 * AbstractAcHorarioArea entity provides the base persistence definition of the
 * AcHorarioArea entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class BaseAcHorarioArea implements java.io.Serializable {

	// Fields

	private Integer horareId;
	private AcArea acArea;
	private AcTurnoDetalle acTurnoDetalle;
	private String horareDia;
	private String horareActivo;
	private Date horareCreacion;
	private Integer docId;
	private Integer horareOrden;

	// Constructors

	/** default constructor */
	public BaseAcHorarioArea() {
	}

	/** minimal constructor */
	public BaseAcHorarioArea(Integer horareId) {
		this.horareId = horareId;
	}

	/** full constructor */
	public BaseAcHorarioArea(Integer horareId, AcArea acArea,
			AcTurnoDetalle acTurnoDetalle, String horareDia,
			String horareActivo, Date horareCreacion, Integer docId,
			Integer horareOrden) {
		this.horareId = horareId;
		this.acArea = acArea;
		this.acTurnoDetalle = acTurnoDetalle;
		this.horareDia = horareDia;
		this.horareActivo = horareActivo;
		this.horareCreacion = horareCreacion;
		this.docId = docId;
		this.horareOrden = horareOrden;
	}

	// Property accessors

	public Integer getHorareId() {
		return this.horareId;
	}

	public void setHorareId(Integer horareId) {
		this.horareId = horareId;
	}

	public AcArea getAcArea() {
		return this.acArea;
	}

	public void setAcArea(AcArea acArea) {
		this.acArea = acArea;
	}

	public AcTurnoDetalle getAcTurnoDetalle() {
		return this.acTurnoDetalle;
	}

	public void setAcTurnoDetalle(AcTurnoDetalle acTurnoDetalle) {
		this.acTurnoDetalle = acTurnoDetalle;
	}

	public String getHorareDia() {
		return this.horareDia;
	}

	public void setHorareDia(String horareDia) {
		this.horareDia = horareDia;
	}

	public String getHorareActivo() {
		return this.horareActivo;
	}

	public void setHorareActivo(String horareActivo) {
		this.horareActivo = horareActivo;
	}

	public Date getHorareCreacion() {
		return this.horareCreacion;
	}

	public void setHorareCreacion(Date horareCreacion) {
		this.horareCreacion = horareCreacion;
	}

	public Integer getDocId() {
		return this.docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public Integer getHorareOrden() {
		return this.horareOrden;
	}

	public void setHorareOrden(Integer horareOrden) {
		this.horareOrden = horareOrden;
	}

}