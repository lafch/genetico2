package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractClArea entity provides the base persistence definition of the ClArea
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseClArea implements java.io.Serializable {

	// Fields

	private Integer areId;
	private String areCod;
	private String areDescripcion;
	private String areActivo;
	private Set clModulos = new HashSet(0);

	// Constructors

	/** default constructor */
	public BaseClArea() {
	}

	/** full constructor */
	public BaseClArea(String areCod, String areDescripcion,
			String areActivo, Set clModulos) {
		this.areCod = areCod;
		this.areDescripcion = areDescripcion;
		this.areActivo = areActivo;
		this.clModulos = clModulos;
	}

	// Property accessors

	public Integer getAreId() {
		return this.areId;
	}

	public void setAreId(Integer areId) {
		this.areId = areId;
	}

	public String getAreCod() {
		return this.areCod;
	}

	public void setAreCod(String areCod) {
		this.areCod = areCod;
	}

	public String getAreDescripcion() {
		return this.areDescripcion;
	}

	public void setAreDescripcion(String areDescripcion) {
		this.areDescripcion = areDescripcion;
	}

	public String getAreActivo() {
		return this.areActivo;
	}

	public void setAreActivo(String areActivo) {
		this.areActivo = areActivo;
	}

	public Set getClModulos() {
		return this.clModulos;
	}

	public void setClModulos(Set clModulos) {
		this.clModulos = clModulos;
	}

}