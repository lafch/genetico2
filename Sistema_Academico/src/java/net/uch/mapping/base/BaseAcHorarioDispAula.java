/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.mapping.base;

import java.sql.Time;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcSeccion;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 *
 * @author LUIS
 */
public class BaseAcHorarioDispAula implements java.io.Serializable {
    // Fields

    private Integer horId;
    private AcAula acAula;
    private String horDia;
    private String horDiaCod;
    private Time horHini;
    private Time horHfin;
    private String horActivo;
    private String horNomDia;

    // Constructors
    /**
     * default constructor
     */
    public BaseAcHorarioDispAula() {
    }

    /**
     * full constructor
     */
    public BaseAcHorarioDispAula(
            AcAula acAula, String horDia, Time horHini, Time horHfin,
            String horActivo ) {
        this.acAula = acAula;
        this.horDia = horDia;
        this.horHini = horHini;
        this.horHfin = horHfin;
        this.horActivo = horActivo;

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

    public void setAcAula( AcAula acAula) {
        this.acAula = acAula;
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
