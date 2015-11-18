/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping.base;

import java.sql.Time;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcTurnoDetalle;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 *
 * @author LUIS
 */
public class BaseAcHorarioDispDocente implements java.io.Serializable {
    // Fields

    private Integer horId;
    private AcDocente acDocente;
    private AcTurnoDetalle turdet;
    private String horDia;
    private String horDiaCod;
    
    private String horActivo;
    private String horNomDia;

    // Constructors
    /**
     * default constructor
     */
    public BaseAcHorarioDispDocente() {
    }

    /**
     * full constructor
     */
    public BaseAcHorarioDispDocente(
            AcDocente acDocente, String horDia, AcTurnoDetalle acTurnoDetalle,
            String horActivo ) {
        this.acDocente = acDocente;
        this.horDia = horDia;
        this.turdet = acTurnoDetalle;
        this.horActivo = horActivo;

    }

    // Property accessors
    public Integer getHorId() {
        return this.horId;
    }

    public void setHorId( Integer horId ) {
        this.horId = horId;
    }

    public AcDocente getAcDocente() {
        return this.acDocente;
    }

    public void setAcDocente( AcDocente acDocente ) {
        this.acDocente = acDocente;
    }

    public String getHorDia() {
        return this.horDia;
    }

    public void setHorDia( String horDia ) {
        this.horDia = horDia;
    }

    public String getHorDiaCod() {
        return horDiaCod;
    }

    public void setHorDiaCod( String horDiaCod ) {
        this.horDiaCod = horDiaCod;
    }

    
	public net.uch.mapping.AcTurnoDetalle getTurdet () {
		return turdet;
	}

	public void setTurdet (net.uch.mapping.AcTurnoDetalle turdet) {
		this.turdet = turdet;
	}

    public String getHorActivo() {
        return this.horActivo;
    }

    public void setHorActivo( String horActivo ) {
        this.horActivo = horActivo;
    }

    public String getHorNomDia() {
        return horNomDia;
    }

    public void setNomDia( String horNomDia ) {
        this.horNomDia = horNomDia;
    }

    public String traerNomDiaxCodigo( String codDia ) {
        HSCatalogoDAO dao = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        return dao.seleccionarDescripcion( codDia );
    }
}
