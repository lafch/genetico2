package net.uch.banco.managedBeans.beans;

/**
 * Clase que hace referencia a los conceptos de pago
 * Indetificador <b>T</b>
 * @author cesardl
 */
public class Concepto {

    private String tipo_registro;
    private String cuenta_empresa;
    private String secuencia;
    private String codigo_concepto;
    private String descripcion_concepto;
    private String cuenta_abono;
    private String importe;

    /**
     * Inicializa los campos con informacion de un concepto
     * @param cadena
     */
    public Concepto(String cadena) {
        this.tipo_registro = cadena.substring(0, 1);
        this.cuenta_empresa = cadena.substring(1, 15);
        this.secuencia = cadena.substring(15, 18);
        this.codigo_concepto = cadena.substring(18, 20);
        this.descripcion_concepto = cadena.substring(20, 50);
        this.cuenta_abono = cadena.substring(50, 64);
        this.importe = cadena.substring(64, 79);
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipoRegistro) {
        this.tipo_registro = tipoRegistro;
    }

    public String getCuenta_empresa() {
        return cuenta_empresa;
    }

    public void setCuenta_empresa(String cuentaEmpresa) {
        this.cuenta_empresa = cuentaEmpresa;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getCodigo_concepto() {
        return codigo_concepto;
    }

    public void setCodigo_concepto(String codigoConcepto) {
        this.codigo_concepto = codigoConcepto;
    }

    public String getDescripcion_concepto() {
        return descripcion_concepto;
    }

    public void setDescripcion_concepto(String descripcionConcepto) {
        this.descripcion_concepto = descripcionConcepto;
    }

    public String getCuenta_abono() {
        return cuenta_abono;
    }

    public void setCuenta_abono(String cuentaAbono) {
        this.cuenta_abono = cuentaAbono;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return this.tipo_registro + "\t" + this.cuenta_empresa + "\t"
                + this.secuencia + "\t" + this.codigo_concepto + "\t"
                + this.descripcion_concepto + "\t" + this.cuenta_abono + "\t"
                + this.importe;
    }
}
