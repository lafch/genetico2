package net.uch.mapping.base;

import java.util.Date;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClEstructuraPagosDetalle;
import net.uch.mapping.ClSeccion;

/**
 * AbstractClAlumnoTarifa entity provides the base persistence definition of the
 * ClAlumnoTarifa entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseClAlumnoTarifa implements java.io.Serializable {

	// Fields

	private Integer alutarId;
	private ClAlumno clAlumno;
	private ClEstructuraPagosDetalle clEstructuraPagosDetalle;
	private Float alutarMonto;
        private Float alutarMora;
	private Integer conpagId;
	private Date alutarFechaPago;
	private Date alutarFechaProrroga;
	private String alutarEstado;
	private String alutarActivo;
	private String alutarAluTipo;
	private Integer secId;
        private Integer matId;
        private String alutarObservacionMonto;
        private Integer usuId;
        private Integer modId;
        private String alutarObservacionProrroga;
        private ClSeccion clSeccion;

	// Constructors

	/** default constructor */

	public BaseClAlumnoTarifa() {
	}


	/** minimal constructor */
	public BaseClAlumnoTarifa(Integer alutarId, ClAlumno clAlumno,
			ClEstructuraPagosDetalle clEstructuraPagosDetalle,
			Integer conpagId, Integer secId, Integer matId,
                        String  alutarObservacionMonto, Integer usuId, Integer modId, String alutarObservacionProrroga) {
		this.alutarId = alutarId;
		this.clAlumno = clAlumno;
		this.clEstructuraPagosDetalle = clEstructuraPagosDetalle;
		this.conpagId = conpagId;
		this.secId = secId;
                this.matId = matId;
                this.alutarObservacionMonto=alutarObservacionMonto;
                this.usuId=usuId;
                this.modId=modId;
                this.alutarObservacionProrroga=alutarObservacionProrroga;
	}

	/** full constructor */
	public BaseClAlumnoTarifa(Integer alutarId, ClAlumno clAlumno,
			ClEstructuraPagosDetalle clEstructuraPagosDetalle,
			Float alutarMonto, Integer conpagId, Date alutarFechaPago,
			Date alutarFechaProrroga, String alutarEstado, String alutarActivo,
			String alutarAluTipo, Integer secId, Integer matId,
                        String  alutarObservacionMonto, Integer usuId, Integer modId, 
                        String alutarObservacionProrroga, Float alutarMora) {
		this.alutarId = alutarId;
		this.clAlumno = clAlumno;
		this.clEstructuraPagosDetalle = clEstructuraPagosDetalle;
		this.alutarMonto = alutarMonto;
		this.conpagId = conpagId;
		this.alutarFechaPago = alutarFechaPago;
		this.alutarFechaProrroga = alutarFechaProrroga;
		this.alutarEstado = alutarEstado;
		this.alutarActivo = alutarActivo;
		this.alutarAluTipo = alutarAluTipo;
		this.secId = secId;
                this.matId = matId;
                this.alutarObservacionMonto=alutarObservacionMonto;
                this.usuId=usuId;
                this.modId=modId;
                this.alutarObservacionProrroga=alutarObservacionProrroga;
                this.alutarMora=alutarMora;
	}

	// Property accessors

    public Float getAlutarMora() {
        return alutarMora;
    }

    public void setAlutarMora(Float alutarMora) {
        this.alutarMora = alutarMora;
    }

        
	public Integer getAlutarId() {
		return this.alutarId;
	}

	public void setAlutarId(Integer alutarId) {
		this.alutarId = alutarId;
	}

	public ClAlumno getClAlumno() {
		return this.clAlumno;
	}

	public void setClAlumno(ClAlumno clAlumno) {
		this.clAlumno = clAlumno;
	}

	public ClEstructuraPagosDetalle getClEstructuraPagosDetalle() {
		return this.clEstructuraPagosDetalle;
	}

	public void setClEstructuraPagosDetalle(
			ClEstructuraPagosDetalle clEstructuraPagosDetalle) {
		this.clEstructuraPagosDetalle = clEstructuraPagosDetalle;
	}

	public Float getAlutarMonto() {
		return this.alutarMonto;
	}

	public void setAlutarMonto(Float alutarMonto) {
		this.alutarMonto = alutarMonto;
	}

	public Integer getConpagId() {
		return this.conpagId;
	}

	public void setConpagId(Integer conpagId) {
		this.conpagId = conpagId;
	}

	public Date getAlutarFechaPago() {
		return this.alutarFechaPago;
	}

	public void setAlutarFechaPago(Date alutarFechaPago) {
		this.alutarFechaPago = alutarFechaPago;
	}

	public Date getAlutarFechaProrroga() {
		return this.alutarFechaProrroga;
	}

	public void setAlutarFechaProrroga(Date alutarFechaProrroga) {
		this.alutarFechaProrroga = alutarFechaProrroga;
	}

	public String getAlutarEstado() {
		return this.alutarEstado;
	}

	public void setAlutarEstado(String alutarEstado) {
		this.alutarEstado = alutarEstado;
	}

	public String getAlutarActivo() {
		return this.alutarActivo;
	}

	public void setAlutarActivo(String alutarActivo) {
		this.alutarActivo = alutarActivo;
	}

	public String getAlutarAluTipo() {
		return this.alutarAluTipo;
	}

	public void setAlutarAluTipo(String alutarAluTipo) {
		this.alutarAluTipo = alutarAluTipo;
	}

	public Integer getSecId() {
		return this.secId;
	}

	public void setSecId(Integer secId) {
		this.secId = secId;
	}

    public Integer getMatId() {
        return matId;
    }

    public void setMatId(Integer matId) {
        this.matId = matId;
    }

    public String getAlutarObservacionMonto() {
        return alutarObservacionMonto;
    }

    public void setAlutarObservacionMonto(String alutarObservacionMonto) {
        this.alutarObservacionMonto = alutarObservacionMonto;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    public String getAlutarObservacionProrroga() {
        return alutarObservacionProrroga;
    }

    public void setAlutarObservacionProrroga(String alutarObservacionProrroga) {
        this.alutarObservacionProrroga = alutarObservacionProrroga;
    }

    public ClSeccion getClSeccion() {
        return clSeccion;
    }

    public void setClSeccion(ClSeccion clSeccion) {
        this.clSeccion = clSeccion;
    }

    
    



}