package net.uch.mapping;
// Generated 14/02/2011 09:37:59 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * TbReporte generated by hbm2java
 */
public class TbReporte  implements java.io.Serializable {


     private Integer repId;
     private String repDescripcion;
     private String repLink;
     private String repTipo;
     private Integer rol;
     private Character repActivo;
     private String repGrupo;
     private Set<TbReporteRol> tbReporteRols = new HashSet<TbReporteRol>(0);
     private Set<TbParametroReportes> tbParametroReporteses = new HashSet<TbParametroReportes>(0);

    public TbReporte() {
    }

    public TbReporte(String repDescripcion, String repLink, String repTipo, Integer rol, Character repActivo, String repGrupo, Set<TbReporteRol> tbReporteRols, Set<TbParametroReportes> tbParametroReporteses) {
       this.repDescripcion = repDescripcion;
       this.repLink = repLink;
       this.repTipo = repTipo;
       this.rol = rol;
       this.repActivo = repActivo;
       this.repGrupo = repGrupo;
       this.tbReporteRols = tbReporteRols;
       this.tbParametroReporteses = tbParametroReporteses;
    }
   
    public Integer getRepId() {
        return this.repId;
    }
    
    public void setRepId(Integer repId) {
        this.repId = repId;
    }
    public String getRepDescripcion() {
        return this.repDescripcion;
    }
    
    public void setRepDescripcion(String repDescripcion) {
        this.repDescripcion = repDescripcion;
    }
    public String getRepLink() {
        return this.repLink;
    }
    
    public void setRepLink(String repLink) {
        this.repLink = repLink;
    }
    public String getRepTipo() {
        return this.repTipo;
    }
    
    public void setRepTipo(String repTipo) {
        this.repTipo = repTipo;
    }
    public Integer getRol() {
        return this.rol;
    }
    
    public void setRol(Integer rol) {
        this.rol = rol;
    }
    public Character getRepActivo() {
        return this.repActivo;
    }
    
    public void setRepActivo(Character repActivo) {
        this.repActivo = repActivo;
    }
    public String getRepGrupo() {
        return this.repGrupo;
    }
    
    public void setRepGrupo(String repGrupo) {
        this.repGrupo = repGrupo;
    }
    public Set<TbReporteRol> getTbReporteRols() {
        return this.tbReporteRols;
    }
    
    public void setTbReporteRols(Set<TbReporteRol> tbReporteRols) {
        this.tbReporteRols = tbReporteRols;
    }
    public Set<TbParametroReportes> getTbParametroReporteses() {
        return this.tbParametroReporteses;
    }
    
    public void setTbParametroReporteses(Set<TbParametroReportes> tbParametroReporteses) {
        this.tbParametroReporteses = tbParametroReporteses;
    }




}


