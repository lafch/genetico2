package net.uch.mapping;

// Generated by MyEclipse Persistence Tools

import java.util.Set;
import net.uch.mapping.base.BaseClMedioPublicidad;

/**
 * ClMedioPublicidad generated by MyEclipse Persistence Tools
 */
public class ClMedioPublicidad extends BaseClMedioPublicidad implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ClMedioPublicidad() {
	}

	/** minimal constructor */
	public ClMedioPublicidad(Integer idPublicidad) {
		super(idPublicidad);
	}

	/** full constructor */
	public ClMedioPublicidad(Integer idPublicidad, String descripcion,
			Integer estado,String tipo, Integer orden, Set clMedioPublicidadDets) {
		super(idPublicidad, descripcion, estado, tipo, orden, clMedioPublicidadDets);
	}

}