package net.uch.mapping;

// Generated by MyEclipse Persistence Tools

import java.sql.Blob;
import java.util.Date;
import java.util.Set;
import net.uch.mapping.base.BaseClAlumno;

/**
 * ClAlumno generated by MyEclipse Persistence Tools
 */
public class ClAlumno extends BaseClAlumno implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ClAlumno() {
	}

	/** minimal constructor */
	public ClAlumno(Integer aluId) {
		super(aluId);
	}

	/** full constructor */
	public ClAlumno(Integer aluId, String aluCodigo, String aluAppaterno,
			String aluApmaterno, String aluNombres, String aluDni,
			String aluMail, String aluTipo, Blob aluFoto, String aluActivo,
			String aluDuplicado, String aluProcedencia, String aluPassword,
			Integer aluCp, String aluTemporal, String aluSexo,
			String aluDistrito, String aluDireccion, String aluTelefono,
			String aluCelular, Date aluFechaNacimiento, String aluUnidad,
			String aluFormaPago, String aluNombreFamiliar,
			String aluParentesco, Integer aluIdBk, Integer aluIdCopia,
			 Set clMatriculas, Set clAlumnoTarifas,
			Set clNotas) {
		super(aluId, aluCodigo, aluAppaterno, aluApmaterno, aluNombres, aluDni,
				aluMail, aluTipo, aluFoto, aluActivo, aluDuplicado,
				aluProcedencia, aluPassword, aluCp, aluTemporal, aluSexo,
				aluDistrito, aluDireccion, aluTelefono, aluCelular,
				aluFechaNacimiento, aluUnidad, aluFormaPago, aluNombreFamiliar,
				aluParentesco, aluIdBk, aluIdCopia, 
				clMatriculas, clAlumnoTarifas, clNotas);
	}

}