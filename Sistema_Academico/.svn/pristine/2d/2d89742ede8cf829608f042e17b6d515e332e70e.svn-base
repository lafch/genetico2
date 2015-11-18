package net.uch.mapping.base;


import java.io.Serializable;
import java.util.Set;
import net.uch.mapping.AcApoderadoAlumno;

/**
 * AbstractAcApoderado entity provides the base persistence definition of the
 * AcApoderado entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class BaseAcApoderado implements Serializable {

	// Fields
 
	private Integer apoId;
	private String apoAppaterno;
	private String apoApmaterno;
	private String apoNombres;
	private String tipoFamiliar;
	private String apoDni;
	private String apoTelefono;
	private String apoCelular;
	private String apoDistrito;
	private String apoDireccion;
	private String apoActivo;
	private String apoPassword;
	private Set<AcApoderadoAlumno> acApoderadoAlumno;

    public Set<AcApoderadoAlumno> getAcApoderadoAlumno() {
        return acApoderadoAlumno;
    }

    public void setAcApoderadoAlumno(Set<AcApoderadoAlumno> acApoderadoAlumno) {
        this.acApoderadoAlumno = acApoderadoAlumno;
    }


	// Constructors

	/** default constructor */
	public BaseAcApoderado() {
	}

	/** minimal constructor */
	public BaseAcApoderado(Integer apoId) {
		this.apoId = apoId;
	}

	/** full constructor */
	public BaseAcApoderado(Integer apoId, String apoAppaterno,
			String apoApmaterno, String apoNombres, String tipoFamiliar,
			String apoDni, String apoTelefono, String apoCelular,
			String apoDistrito, String apoDireccion, String apoActivo,
			String apoPassword, Set<AcApoderadoAlumno> acApoderadoAlumno) {
		this.apoId = apoId;
		this.apoAppaterno = apoAppaterno;
		this.apoApmaterno = apoApmaterno;
		this.apoNombres = apoNombres;
		this.tipoFamiliar = tipoFamiliar;
		this.apoDni = apoDni;
		this.apoTelefono = apoTelefono;
		this.apoCelular = apoCelular;
		this.apoDistrito = apoDistrito;
		this.apoDireccion = apoDireccion;
		this.apoActivo = apoActivo;
		this.apoPassword = apoPassword;
		this.acApoderadoAlumno = acApoderadoAlumno;
	}

	// Property accessors

	public Integer getApoId() {
		return this.apoId;
	}

	public void setApoId(Integer apoId) {
		this.apoId = apoId;
	}

	public String getApoAppaterno() {
		return this.apoAppaterno;
	}

	public void setApoAppaterno(String apoAppaterno) {
		this.apoAppaterno = apoAppaterno;
	}

	public String getApoApmaterno() {
		return this.apoApmaterno;
	}

	public void setApoApmaterno(String apoApmaterno) {
		this.apoApmaterno = apoApmaterno;
	}

	public String getApoNombres() {
		return this.apoNombres;
	}

	public void setApoNombres(String apoNombres) {
		this.apoNombres = apoNombres;
	}

	public String getTipoFamiliar() {
		return this.tipoFamiliar;
	}

	public void setTipoFamiliar(String tipoFamiliar) {
		this.tipoFamiliar = tipoFamiliar;
	}

	public String getApoDni() {
		return this.apoDni;
	}

	public void setApoDni(String apoDni) {
		this.apoDni = apoDni;
	}

	public String getApoTelefono() {
		return this.apoTelefono;
	}

	public void setApoTelefono(String apoTelefono) {
		this.apoTelefono = apoTelefono;
	}

	public String getApoCelular() {
		return this.apoCelular;
	}

	public void setApoCelular(String apoCelular) {
		this.apoCelular = apoCelular;
	}

	public String getApoDistrito() {
		return this.apoDistrito;
	}

	public void setApoDistrito(String apoDistrito) {
		this.apoDistrito = apoDistrito;
	}

	public String getApoDireccion() {
		return this.apoDireccion;
	}

	public void setApoDireccion(String apoDireccion) {
		this.apoDireccion = apoDireccion;
	}

	public String getApoActivo() {
		return this.apoActivo;
	}

	public void setApoActivo(String apoActivo) {
		this.apoActivo = apoActivo;
	}

	public String getApoPassword() {
		return this.apoPassword;
	}

	public void setApoPassword(String apoPassword) {
		this.apoPassword = apoPassword;
	}

	

}