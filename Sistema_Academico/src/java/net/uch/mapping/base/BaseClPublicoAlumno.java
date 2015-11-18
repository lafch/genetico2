package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractClPublicoAlumno entity provides the base persistence definition of
 * the ClPublicoAlumno entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class BaseClPublicoAlumno implements java.io.Serializable {

	// Fields

	private Integer publicoId;
	private String nombres;
	private String paterno;
	private String materno;
	private Integer distritoId;
	private String telCelular;
	private String telCasa;
	private String email;
	private String alumnosId;
	private String aluCod;
	private Set clPublicoConsultas = new HashSet(0);

	// Constructors

	/** default constructor */
	public BaseClPublicoAlumno() {
	}

	/** minimal constructor */
	public BaseClPublicoAlumno(Integer publicoId) {
		this.publicoId = publicoId;
	}

	/** full constructor */
	public BaseClPublicoAlumno(Integer publicoId, String nombres,
			String paterno, String materno, Integer distritoId,
			String telCelular, String telCasa, String email, String alumnosId,
			String aluCod, Set clPublicoConsultas) {
		this.publicoId = publicoId;
		this.nombres = nombres;
		this.paterno = paterno;
		this.materno = materno;
		this.distritoId = distritoId;
		this.telCelular = telCelular;
		this.telCasa = telCasa;
		this.email = email;
		this.alumnosId = alumnosId;
		this.aluCod = aluCod;
		this.clPublicoConsultas = clPublicoConsultas;
	}

	// Property accessors

	public Integer getPublicoId() {
		return this.publicoId;
	}

	public void setPublicoId(Integer publicoId) {
		this.publicoId = publicoId;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPaterno() {
		return this.paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return this.materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public Integer getDistritoId() {
		return this.distritoId;
	}

	public void setDistritoId(Integer distritoId) {
		this.distritoId = distritoId;
	}

	public String getTelCelular() {
		return this.telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getTelCasa() {
		return this.telCasa;
	}

	public void setTelCasa(String telCasa) {
		this.telCasa = telCasa;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlumnosId() {
		return this.alumnosId;
	}

	public void setAlumnosId(String alumnosId) {
		this.alumnosId = alumnosId;
	}

	public String getAluCod() {
		return this.aluCod;
	}

	public void setAluCod(String aluCod) {
		this.aluCod = aluCod;
	}

	public Set getClPublicoConsultas() {
		return this.clPublicoConsultas;
	}

	public void setClPublicoConsultas(Set clPublicoConsultas) {
		this.clPublicoConsultas = clPublicoConsultas;
	}

}