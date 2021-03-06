package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTbMenu generated by MyEclipse Persistence Tools
 */
public abstract class BaseTbMenu implements java.io.Serializable {

    // Fields
    private Integer menId;
    private String menCodigo;
    private String menDescripcion;
    private String menOpc;
    private String menAccion;
    private String menActivo;
    private Integer menIdPadre;
    private boolean seleccionado;
    private Set tbMenuRols = new HashSet(0);

    // Constructors
    /** default constructor */
    public BaseTbMenu() {
    }

    /** minimal constructor */
    public BaseTbMenu(Integer menId) {
        this.menId = menId;
    }

    /** full constructor */
    public BaseTbMenu(Integer menId, String menCodigo,
            String menDescripcion, String menOpc, String menAccion,
            String menActivo, Integer menIdPadre, Set tbMenuRols) {
        this.menId = menId;
        this.menCodigo = menCodigo;
        this.menDescripcion = menDescripcion;
        this.menOpc = menOpc;
        this.menAccion = menAccion;
        this.menActivo = menActivo;
        this.menIdPadre = menIdPadre;
        this.tbMenuRols = tbMenuRols;
    }

    // Property accessors
    public Integer getMenId() {
        return this.menId;
    }

    public void setMenId(Integer menId) {
        this.menId = menId;
    }

    public String getMenCodigo() {
        return this.menCodigo;
    }

    public void setMenCodigo(String menCodigo) {
        this.menCodigo = menCodigo;
    }

    public String getMenDescripcion() {
        return this.menDescripcion;
    }

    public void setMenDescripcion(String menDescripcion) {
        this.menDescripcion = menDescripcion;
    }

    public String getMenOpc() {
        return this.menOpc;
    }

    public void setMenOpc(String menOpc) {
        this.menOpc = menOpc;
    }

    public String getMenAccion() {
        return this.menAccion;
    }

    public void setMenAccion(String menAccion) {
        this.menAccion = menAccion;
    }

    public String getMenActivo() {
        return this.menActivo;
    }

    public void setMenActivo(String menActivo) {
        this.menActivo = menActivo;
    }

    public Integer getMenIdPadre() {
        return this.menIdPadre;
    }

    public void setMenIdPadre(Integer menIdPadre) {
        this.menIdPadre = menIdPadre;
    }

    public Set getTbMenuRols() {
        return this.tbMenuRols;
    }

    public void setTbMenuRols(Set tbMenuRols) {
        this.tbMenuRols = tbMenuRols;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    
    public String toString(){
        return "Menu [menu_id=" + menId + ", nombre=" + menDescripcion + ", padre=" + menIdPadre + ", seleccionado=" + seleccionado + "]";

    }
    
    
}
