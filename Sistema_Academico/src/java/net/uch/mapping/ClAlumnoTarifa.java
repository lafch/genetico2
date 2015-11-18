package net.uch.mapping;

import java.util.Date;
import net.uch.mapping.base.BaseClAlumnoTarifa;

/**
 * ClAlumnoTarifa entity. @author MyEclipse Persistence Tools
 */
public class ClAlumnoTarifa extends BaseClAlumnoTarifa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ClAlumnoTarifa() {
	}

	/** minimal constructor */
	public ClAlumnoTarifa(Integer alutarId, ClAlumno clAlumno,
			ClEstructuraPagosDetalle clEstructuraPagosDetalle,
			Integer conpagId, Integer secId, Integer matId, String alutarObservacionMonto, Integer usuId, Integer modId, String alutarObservacionProrroga) {
		super(alutarId, clAlumno, clEstructuraPagosDetalle, conpagId, secId, matId,alutarObservacionMonto,usuId, modId,alutarObservacionProrroga);
	}

	/** full constructor */
	public ClAlumnoTarifa(Integer alutarId, ClAlumno clAlumno,
			ClEstructuraPagosDetalle clEstructuraPagosDetalle,
			Float alutarMonto, Integer conpagId, Date alutarFechaPago,
			Date alutarFechaProrroga, String alutarEstado, String alutarActivo,
			String alutarAluTipo, Integer secId, Integer matId, String alutarObservacionMonto, Integer usuId,Integer modId, 
                        String alutarObservacionProrroga, Float alutarMora) {
		super(alutarId, clAlumno, clEstructuraPagosDetalle, alutarMonto,
				conpagId, alutarFechaPago, alutarFechaProrroga, alutarEstado,
				alutarActivo, alutarAluTipo, secId, matId, alutarObservacionMonto, usuId, modId,alutarObservacionProrroga, alutarMora);
	}

}
