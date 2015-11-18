package net.uch.banco.managedBeans.beans;

/**
 * Clase que hace referencia a la cabezera
 * Indetificador <b>H</b>
 * @author cesardl
 */
public class Cabecera {

    private String tipo_registro;
    private String cuenta_empresa;
    private String secuencia;
    private String documentos;
    private String total_soles;
    private String total_dolares;
    private String fecha_movimiento;

    /**
     * Inicializa los campos de la cabezera en blanco
     */
    public Cabecera() {
        this.tipo_registro = "";
        this.cuenta_empresa = "";
        this.secuencia = "";
        this.documentos = "";
        this.total_soles = "";
        this.total_dolares = "";
        this.fecha_movimiento = "";
    }

    /**
     * Inicializa los campos con informacion de la cabezera
     * @param cadena
     */
    public Cabecera(String cadena) {
        this.tipo_registro = cadena.substring(0, 1);
        this.cuenta_empresa = cadena.substring(1, 15);
        this.secuencia = cadena.substring(15, 18);
        this.documentos = cadena.substring(18, 25);
        this.total_soles = cadena.substring(25, 40);
        this.total_dolares = cadena.substring(40, 52);
        this.fecha_movimiento = cadena.substring(52, 60);
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

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getTotal_soles() {
        return total_soles;
    }

    public void setTotal_soles(String totalSoles) {
        this.total_soles = totalSoles;
    }

    public String getTotal_dolares() {
        return total_dolares;
    }

    public void setTotal_dolares(String totalDolares) {
        this.total_dolares = totalDolares;
    }

    public String getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(String fechaMovimiento) {
        this.fecha_movimiento = fechaMovimiento;
    }

    @Override
    public String toString() {
        return this.tipo_registro + "\t" + this.cuenta_empresa + "\t"
                + this.secuencia + "\t" + this.documentos + "\t"
                + this.total_soles + "\t" + this.total_dolares + "\t"
                + this.fecha_movimiento;
    }
}
