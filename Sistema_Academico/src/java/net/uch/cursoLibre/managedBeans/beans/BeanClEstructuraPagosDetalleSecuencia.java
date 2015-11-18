/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.io.Serializable;
import java.sql.Date;
import net.uch.mapping.ClEstructuraPagosDetalle;

/**
 *
 * @author USUARIO
 */
public class BeanClEstructuraPagosDetalleSecuencia implements Serializable {

    private Integer m_iEstpagdetSecId;
    private ClEstructuraPagosDetalle m_estpagdetIni;
    private ClEstructuraPagosDetalle m_estpagdetCont;
//    private Date m_fechaCreacion;
    private Integer m_iActivo;

    public BeanClEstructuraPagosDetalleSecuencia() {
    }

    public BeanClEstructuraPagosDetalleSecuencia( Integer estpagdetSecId ) {
        this.m_iEstpagdetSecId = estpagdetSecId;
    }

    public BeanClEstructuraPagosDetalleSecuencia( Integer estpagdetSecId, ClEstructuraPagosDetalle estpagdetIni, ClEstructuraPagosDetalle estpagdetCont, Date fechaCreacion, Integer activo ) {
        this.m_iEstpagdetSecId = estpagdetSecId;
        this.m_estpagdetIni = estpagdetIni;
        this.m_estpagdetCont = estpagdetCont;
//        this.m_fechaCreacion = fechaCreacion;
        this.m_iActivo = activo;
    }

    public Integer getActivo() {
        return m_iActivo;
    }

    public void setActivo( Integer activo ) {
        this.m_iActivo = activo;
    }

    public ClEstructuraPagosDetalle getEstpagdetCont() {
        return m_estpagdetCont;
    }

    public void setEstpagdetCont( ClEstructuraPagosDetalle estpagdetCont ) {
        this.m_estpagdetCont = estpagdetCont;
    }

    public ClEstructuraPagosDetalle getEstpagdetIni() {
        return m_estpagdetIni;
    }

    public void setEstpagdetIni( ClEstructuraPagosDetalle estpagdetIni ) {
        this.m_estpagdetIni = estpagdetIni;
    }

    public Integer getEstpagdetSecId() {
        return m_iEstpagdetSecId;
    }

    public void setEstpagdetSecId( Integer estpagdetSecId ) {
        this.m_iEstpagdetSecId = estpagdetSecId;
    }

//    public Date getFechaCreacion() {
//        return m_fechaCreacion;
//    }
//
//    public void setFechaCreacion( Date fechaCreacion ) {
//        this.m_fechaCreacion = fechaCreacion;
//    }
}
