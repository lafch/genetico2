package net.uch.mapping.base;

import java.util.Date;

/**
 * BaseTbPagoBanco entity provides the base persistence definition of the
 * TbPagoBanco entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseTbPagoBanco implements java.io.Serializable {

    // Fields
    private Integer pagbanId;
    private String pagbanCodigo;
    private Integer alutarId;
    private String pagbanNombrePago;
    private Float pagbanMonto;
    private Integer aluId;
    private String pagbanConcepto;
    private Date pagbanFechaVencimiento;
    private String pagbanGrupo;
    private Integer pagbanCodigoGrupo;
    private String pagbanActivo;
    private Date pagbanFechaRegistro;
    private String conceptoCobrar;
    private Date pagbanFecha;
    private Double pagbanMora;

    public Double getPagbanMora() {
        return pagbanMora;
    }

    public void setPagbanMora(Double pagbanMora) {
        this.pagbanMora = pagbanMora;
    }

    
    // Constructors
    /** default constructor */
    public BaseTbPagoBanco() {
    }

    /** minimal constructor */
    public BaseTbPagoBanco(Integer pagbanId) {
        this.pagbanId = pagbanId;
    }

    /** full constructor */
    public BaseTbPagoBanco(Integer pagbanId, String pagbanCodigo,
            Integer alutarId, String pagbanNombrePago, Float pagbanMonto,
            Integer aluId, String pagbanConcepto, Date pagbanFechaVencimiento,
            String pagbanGrupo, Integer pagbanCodigoGrupo, String pagbanActivo,
            Date pagbanFechaRegistro, String conceptoCobrar, Date pagbanFecha, Double pagbanMora) {
        this.pagbanId = pagbanId;
        this.pagbanCodigo = pagbanCodigo;
        this.alutarId = alutarId;
        this.pagbanNombrePago = pagbanNombrePago;
        this.pagbanMonto = pagbanMonto;
        this.aluId = aluId;
        this.pagbanConcepto = pagbanConcepto;
        this.pagbanFechaVencimiento = pagbanFechaVencimiento;
        this.pagbanGrupo = pagbanGrupo;
        this.pagbanCodigoGrupo = pagbanCodigoGrupo;
        this.pagbanActivo = pagbanActivo;
        this.pagbanFechaRegistro = pagbanFechaRegistro;
        this.conceptoCobrar = conceptoCobrar;
        this.pagbanFecha = pagbanFecha;
        this.pagbanMora= pagbanMora;
    }

    // Property accessors
    public Integer getPagbanId() {
        return this.pagbanId;
    }

    public void setPagbanId(Integer pagbanId) {
        this.pagbanId = pagbanId;
    }

    public String getPagbanCodigo() {
        return this.pagbanCodigo;
    }

    public void setPagbanCodigo(String pagbanCodigo) {
        this.pagbanCodigo = pagbanCodigo;
    }

    public Integer getAlutarId() {
        return this.alutarId;
    }

    public void setAlutarId(Integer alutarId) {
        this.alutarId = alutarId;
    }

    public String getPagbanNombrePago() {
        return this.pagbanNombrePago;
    }

    public void setPagbanNombrePago(String pagbanNombrePago) {
        this.pagbanNombrePago = pagbanNombrePago;
    }

    public Float getPagbanMonto() {
        return this.pagbanMonto;
    }

    public void setPagbanMonto(Float pagbanMonto) {
        this.pagbanMonto = pagbanMonto;
    }

    public Integer getAluId() {
        return this.aluId;
    }

    public void setAluId(Integer aluId) {
        this.aluId = aluId;
    }

    public String getPagbanConcepto() {
        return this.pagbanConcepto;
    }

    public void setPagbanConcepto(String pagbanConcepto) {
        this.pagbanConcepto = pagbanConcepto;
    }

    public Date getPagbanFechaVencimiento() {
        return this.pagbanFechaVencimiento;
    }

    public void setPagbanFechaVencimiento(Date pagbanFechaVencimiento) {
        this.pagbanFechaVencimiento = pagbanFechaVencimiento;
    }

    public String getPagbanGrupo() {
        return this.pagbanGrupo;
    }

    public void setPagbanGrupo(String pagbanGrupo) {
        this.pagbanGrupo = pagbanGrupo;
    }

    public Integer getPagbanCodigoGrupo() {
        return this.pagbanCodigoGrupo;
    }

    public void setPagbanCodigoGrupo(Integer pagbanCodigoGrupo) {
        this.pagbanCodigoGrupo = pagbanCodigoGrupo;
    }

    public String getPagbanActivo() {
        return this.pagbanActivo;
    }

    public void setPagbanActivo(String pagbanActivo) {
        this.pagbanActivo = pagbanActivo;
    }

    public Date getPagbanFechaRegistro() {
        return this.pagbanFechaRegistro;
    }

    public void setPagbanFechaRegistro(Date pagbanFechaRegistro) {
        this.pagbanFechaRegistro = pagbanFechaRegistro;
    }

    /**
     * Orden de los importes
     * @return
     */
    public String getConceptoCobrar() {
        return this.conceptoCobrar;
    }

    /**
     * Setea el orden de los pagos para generar el archivo
     * @param conceptoCobrar
     */
    public void setConceptoCobrar(String conceptoCobrar) {
        this.conceptoCobrar = conceptoCobrar;
    }

    public Date getPagbanFecha() {
        return this.pagbanFecha;
    }

    public void setPagbanFecha(Date pagbanFecha) {
        this.pagbanFecha = pagbanFecha;
    }
}
