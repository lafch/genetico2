/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.mapping.base;

import java.util.Date;
import net.uch.mapping.ClModulo;

/**
 *
 * @author Simion Richa R B
 */
public class BaseClConsultaPublico_{
    private int conId;
   // private int modId;
    private ClModulo clModulo;
    private int medId;
    private int detmedId;
    private String conObservacionMedio;
    private Date conFechaContacto;
    private String conProcedencia;
    private String conObservacionRegistro;
    private int aluId;
    private int usuId;
    private String conActivo;
    private int plaId;
    private String conEstadoMatricula;

    public BaseClConsultaPublico_(){}

    public BaseClConsultaPublico_( ClModulo clModulo, int medId, int detmedId, String conObservacionMedio, Date conFechaContacto,String conProcedencia,
                                String conObservacionRegistro, int usuId, int aluId, String conActivo, int plaId, String conEstadoMatricula){
        this.conFechaContacto=conFechaContacto;
        this.conObservacionMedio=conObservacionMedio;
        this.conObservacionRegistro=conObservacionRegistro;
        this.conProcedencia=conProcedencia;
        this.detmedId=detmedId;
        this.clModulo=clModulo;
        this.medId=medId;
        this.usuId=usuId;
        this.aluId=aluId;
        this.conActivo=conActivo;
        this.plaId=plaId;
        this.conEstadoMatricula=conEstadoMatricula;

    }

    public int getAluId() {
        return aluId;
    }

    public void setAluId(int aluId) {
        this.aluId = aluId;
    }

    public Date getConFechaContacto() {
        return conFechaContacto;
    }

    public void setConFechaContacto(Date conFechaContacto) {
        this.conFechaContacto = conFechaContacto;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getConObservacionMedio() {
        return conObservacionMedio;
    }

    public void setConObservacionMedio(String conObservacionMedio) {
        this.conObservacionMedio = conObservacionMedio;
    }

    public String getConObservacionRegistro() {
        return conObservacionRegistro;
    }

    public void setConObservacionRegistro(String conObservacionRegistro) {
        this.conObservacionRegistro = conObservacionRegistro;
    }

    public String getConProcedencia() {
        return conProcedencia;
    }

    public void setConProcedencia(String conProcedencia) {
        this.conProcedencia = conProcedencia;
    }

    public int getDetmedId() {
        return detmedId;
    }

    public void setDetmedId(int detmedId) {
        this.detmedId = detmedId;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }


    public ClModulo getClModulo() {
        return clModulo;
    }

    public void setClModulo(ClModulo clModulo) {
        this.clModulo = clModulo;
    }

    public int getUsuId() {
        return usuId;
    }

    public void setUsuId(int usuId) {
        this.usuId = usuId;
    }

    public String getConActivo() {
        return conActivo;
    }

    public void setConActivo(String conActivo) {
        this.conActivo = conActivo;
    }

    public int getPlaId() {
        return plaId;
    }

    public void setPlaId(int plaId) {
        this.plaId = plaId;
    }

    public String getConEstadoMatricula() {
        return conEstadoMatricula;
    }

    public void setConEstadoMatricula(String conEstadoMatricula) {
        this.conEstadoMatricula = conEstadoMatricula;
    }

   
}
