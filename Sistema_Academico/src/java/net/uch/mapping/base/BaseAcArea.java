package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAcArea entity provides the base persistence definition of the AcArea
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class BaseAcArea implements java.io.Serializable {

	// Fields

	private Integer areId;
	private String areCodigo;
	private String areNombre;
	private String areActivo;
	private String areTipo;
	private Integer areRespId;
	private Set acAreaDocentes = new HashSet(0);
	private Set acHorarioAreas = new HashSet(0);

	// Constructors

	/** default constructor */
	public BaseAcArea() {
	}

	/** minimal constructor */
	public BaseAcArea(Integer areId, Integer areRespId) {
		this.areId = areId;
		this.areRespId = areRespId;
	}

	/** full constructor */
	public BaseAcArea(Integer areId, String areCodigo, String areNombre,
			String areActivo, String areTipo, Integer areRespId,
			Set acAreaDocentes, Set acHorarioAreas) {
		this.areId = areId;
		this.areCodigo = areCodigo;
		this.areNombre = areNombre;
		this.areActivo = areActivo;
		this.areTipo = areTipo;
		this.areRespId = areRespId;
		this.acAreaDocentes = acAreaDocentes;
		this.acHorarioAreas = acHorarioAreas;
	}

	// Property accessors

	public Integer getAreId() {
		return this.areId;
	}

	public void setAreId(Integer areId) {
		this.areId = areId;
	}

	public String getAreCodigo() {
		return this.areCodigo;
	}

	public void setAreCodigo(String areCodigo) {
		this.areCodigo = areCodigo;
	}

	public String getAreNombre() {
		return this.areNombre;
	}

	public void setAreNombre(String areNombre) {
		this.areNombre = areNombre;
	}

	public String getAreActivo() {
		return this.areActivo;
	}

	public void setAreActivo(String areActivo) {
		this.areActivo = areActivo;
	}

	public String getAreTipo() {
		return this.areTipo;
	}

	public void setAreTipo(String areTipo) {
		this.areTipo = areTipo;
	}

	public Integer getAreRespId() {
		return this.areRespId;
	}

	public void setAreRespId(Integer areRespId) {
		this.areRespId = areRespId;
	}

	public Set getAcAreaDocentes() {
		return this.acAreaDocentes;
	}

	public void setAcAreaDocentes(Set acAreaDocentes) {
		this.acAreaDocentes = acAreaDocentes;
	}

	public Set getAcHorarioAreas() {
		return this.acHorarioAreas;
	}

	public void setAcHorarioAreas(Set acHorarioAreas) {
		this.acHorarioAreas = acHorarioAreas;
	}

}