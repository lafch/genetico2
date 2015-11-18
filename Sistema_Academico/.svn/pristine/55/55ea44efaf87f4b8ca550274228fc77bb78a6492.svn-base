package net.uch.mapping.base;

import java.sql.Time;
import net.uch.commonService.ServiceFinder;

import net.uch.mapping.AcAula;
import net.uch.mapping.AcDocente;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSisEvaDetalle;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 * AbstractClHoraria entity provides the base persistence definition of the
 * ClHoraria entity.
 *
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseClHoraria implements java.io.Serializable {

    // Fields
    private Integer horId;
    private AcAula acAula;
    private ClSeccion clSeccion;
    private AcDocente acDocente;
    private String horDia;
    private String horDiaCod;
    private Time horHini;
    private Time horHfin;
    private String horTipoClase;
    private String horActivo;
    private String horNomDia;
    private ClSisEvaDetalle clSisEvaDetDocente;

    // Constructors
    /**
     * default constructor
     */
    public BaseClHoraria() {
    }

    /**
     * minimal constructor
     */
    public BaseClHoraria( AcAula acAula, ClSeccion clSeccion,
            AcDocente acDocente ) {
        this.acAula = acAula;
        this.clSeccion = clSeccion;
        this.acDocente = acDocente;
    }

    /**
     * full constructor
     */
    public BaseClHoraria( AcAula acAula, ClSeccion clSeccion,
            AcDocente acDocente, String horDia, Time horHini, Time horHfin,
            String horTipoClase, String horActivo, ClSisEvaDetalle clSisEvaDetDocente ) {
        this.acAula = acAula;
        this.clSeccion = clSeccion;
        this.acDocente = acDocente;
        this.horDia = horDia;
        this.horHini = horHini;
        this.horHfin = horHfin;
        this.horTipoClase = horTipoClase;
        this.horActivo = horActivo;
        this.clSisEvaDetDocente=clSisEvaDetDocente;
    }

    // Property accessors
    public Integer getHorId() {
        return this.horId;
    }

    public void setHorId( Integer horId ) {
        this.horId = horId;
    }

    public AcAula getAcAula() {
        return this.acAula;
    }

    public void setAcAula( AcAula acAula ) {
        this.acAula = acAula;
    }

    public ClSeccion getClSeccion() {
        return this.clSeccion;
    }

    public void setClSeccion( ClSeccion clSeccion ) {
        this.clSeccion = clSeccion;
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

    public Time getHorHini() {
        return this.horHini;
    }

    public void setHorHini( Time horHini ) {
        this.horHini = horHini;
    }

    public Time getHorHfin() {
        return this.horHfin;
    }

    public void setHorHfin( Time horHfin ) {
        this.horHfin = horHfin;
    }

    public String getHorTipoClase() {
        return this.horTipoClase;
    }

    public void setHorTipoClase( String horTipoClase ) {
        this.horTipoClase = horTipoClase;
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
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        return dao.seleccionarDescripcion( codDia );
    }

    public ClSisEvaDetalle getClSisEvaDetDocente() {
        return clSisEvaDetDocente;
    }

    public void setClSisEvaDetDocente( ClSisEvaDetalle clSisEvaDetDocente ) {
        this.clSisEvaDetDocente = clSisEvaDetDocente;
    }
}
