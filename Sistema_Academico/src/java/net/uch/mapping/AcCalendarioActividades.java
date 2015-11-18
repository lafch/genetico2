package net.uch.mapping;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Set;

import net.uch.mapping.base.BaseAcCalendarioActividades;

/**
 * AcCalendarioActividades entity. @author MyEclipse Persistence Tools
 */
public class AcCalendarioActividades extends BaseAcCalendarioActividades
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public AcCalendarioActividades() {
	}

	/** minimal constructor */
	public AcCalendarioActividades(AcCalendario acCalendario, Integer usuId) {
		super(acCalendario, usuId);
	}

	/** full constructor */
	public AcCalendarioActividades(AcCalendario acCalendario,
			String calactDesc, String calactActivo, Blob calactImg,
			Timestamp calactCreacion, String calactTipo, Integer usuId,
			String calactTitulo, String calactPublicado,
			Timestamp calactFechaInicio, Timestamp calactFechaFin,
			Set acActividadAlcances) {
		super(acCalendario, calactDesc, calactActivo, calactImg,
				calactCreacion, calactTipo, usuId, calactTitulo,
				calactPublicado, calactFechaInicio, calactFechaFin,
				acActividadAlcances);
	}

}
