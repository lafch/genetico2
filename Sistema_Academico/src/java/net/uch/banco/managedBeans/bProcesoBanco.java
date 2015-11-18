/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.banco.managedBeans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.administrativa.hibernateSpringDao.HSAlumnoTarifaDAO;
import net.uch.administrativa.hibernateSpringDao.HSEdicionEstructPagoDAO;
import net.uch.administrativa.hibernateSpringDao.HSPagoDAO;
import net.uch.banco.hibernateSpringDao.HSBancoDAO;
import net.uch.banco.managedBeans.beans.InfoBanco;
import net.uch.banco.managedBeans.beans.RespuestaBanco;
import net.uch.banco.managedBeans.beans.Detalle;
import net.uch.banco.managedBeans.beans.Cabecera;
import net.uch.banco.managedBeans.beans.Concepto;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdComprobantePago;
import net.uch.mapping.AdConceptoPago;
import net.uch.mapping.AdPago;
import net.uch.mapping.Sp_conceptos_banco;
import net.uch.mapping.Sp_data_banco;
import net.uch.mapping.TbBanco;
import net.uch.mapping.TbBancoEntrada;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbPagoBanco;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.Print;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

/**
 *
 * @author cesardl
 */
public class bProcesoBanco {

    private int contador = 1;

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    private String mensaje;
    private String disable_proceso;
    private String rendered;
    private String oncomplete;
    private String body_class;
    private File file;
    private String file_nombre;
    private long file_dim;
    private boolean genera_comprobante;
    private String efecto;
    private String report_value;
    private String serie;
    private SelectItem[] series;
    private String nroInicio;
    private Date fecha_comprobantes;
    private List<InfoBanco> transacciones;
    private List<RespuestaBanco> respuestas;
    private List<RespuestaBanco> errores;
    private List<TbBancoEntrada> entradas;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new instance of bProcesoBanco
     */
    public bProcesoBanco() {
        this.disable_proceso = "true";
        this.rendered = "false";
        this.genera_comprobante = true;
        this.report_value = "";
        this.serie = "";
        try {
            HSCatalogoDAO catalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List<TbCatalogo> catalogos = catalogoDAO.seleccionarCatalogo("032");
            this.series = new SelectItem[catalogos.size() + 1];
            for (int i = 0; i < catalogos.size(); i++) {
                TbCatalogo cat = catalogos.get(i);
                this.series[i + 1] = new SelectItem(cat.getCatDescripcionElemento(),
                        cat.getCatDescripcionElemento());
            }
        } finally {
            this.series[0] = new SelectItem("", "[Seleccione]");
        }
    }

    public File getFile() {
        return file;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDisable_proceso() {
        return disable_proceso;
    }

    public void setDisable_proceso(String disable_proceso) {
        this.disable_proceso = disable_proceso;
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getBody_class() {
        return body_class;
    }

    public void setBody_class(String body_class) {
        this.body_class = body_class;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public long getFile_dim() {
        return file_dim;
    }

    public void setFile_dim(long file_dim) {
        this.file_dim = file_dim;
    }

    public boolean isGenera_comprobante() {
        return genera_comprobante;
    }

    public void setGenera_comprobante(boolean genera_comprobante) {
        this.genera_comprobante = genera_comprobante;
    }

    public String getReport_value() {
        return report_value;
    }

    public void setReport_value(String report_value) {
        this.report_value = report_value;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    public String getNroInicio() {
        return nroInicio;
    }

    public void setNroInicio(String nroInicio) {
        this.nroInicio = nroInicio;
    }

    public Date getFecha_comprobantes() {
        return fecha_comprobantes;
    }

    public void setFecha_comprobantes(Date fecha_comprobantes) {
        this.fecha_comprobantes = fecha_comprobantes;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public SelectItem[] getSeries() {
        return series;
    }

    public void setSeries(SelectItem[] series) {
        this.series = series;
    }

    public String getFile_nombre() {
        return file_nombre;
    }

    public void setFile_nombre(String file_nombre) {
        this.file_nombre = file_nombre;
    }

    public List<InfoBanco> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<InfoBanco> transacciones) {
        this.transacciones = transacciones;
    }

    public List<RespuestaBanco> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestaBanco> respuestas) {
        this.respuestas = respuestas;
    }

    public int getTotalDataBanco() {
        if (this.respuestas != null) {
            return this.respuestas.size();
        } else {
            return 0;
        }
    }

    public List<RespuestaBanco> getErrores() {
        return errores;
    }

    public void setErrores(List<RespuestaBanco> errores) {
        this.errores = errores;
    }

    public int getTotalErrores() {
        if (this.errores != null) {
            return this.errores.size();
        } else {
            return 0;
        }
    }

    public List<TbBancoEntrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<TbBancoEntrada> entradas) {
        this.entradas = entradas;
    }

    public void seleccionarEfecto() {
        if (this.isGenera_comprobante()) {
            this.setEfecto("showDiv();");
        } else {
            this.setEfecto("hideDiv({duration:0.7});");
        }
        this.setSerie("");
        this.setNroInicio("");
    }

    public String limpiarTodo() {
        this.disable_proceso = "true";
        this.rendered = "false";
        this.genera_comprobante = true;
        this.file = null;
        this.mensaje = "";
        this.body_class = "";
        this.file_nombre = "";
        this.file_dim = 0;
        this.efecto = "";
        this.nroInicio = "";
        this.transacciones = new ArrayList<InfoBanco>();
        this.entradas = new ArrayList<TbBancoEntrada>();
        this.respuestas = new ArrayList<RespuestaBanco>();

        return "ProcesoBanco";
    }

    public double redondear( double numero, int decimales ) {

    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);

  }

    public void generarArchivoDescargable() {
        HSBancoDAO dao = (HSBancoDAO) ServiceFinder.findBean("SpringHibernateDaoBanco");
        List<TbBanco> banco = dao.listarBanco(1);
        List<Sp_data_banco> lista = dao.listarDataBanco();
        System.out.println("proceso 1");
        double iMontoTotal = 0;
        String fecha_envio = lista.get(0).getReg_fecha_ini();
        String fecha_vigencia = fecha_envio;

        for (int i = 0; i < lista.size(); i++) {
            iMontoTotal += Double.parseDouble(lista.get(i).getReg_total_cobrar().replace(",", ""));
             System.out.println("iMontoTotal" + iMontoTotal);
            if(lista.get(i).getReg_codigo_alu().equals("08030129")){
                System.out.println(lista.get(i).getReg_concepto_cob1()+" -> "+i+"\t"+lista.get(i).getReg_total_cobrar());
            }
        }
       //System.out.println("redondeo del valor" + redondear(iMontoTotal, 2));
        //String valor = String.valueOf(iMontoTotal);
        String valor =String.valueOf(redondear(iMontoTotal, 2));
        String[] Monto = valor.split("\\.");

         if (Monto[1].length() < 2) {
                Monto[1] = Monto[1].concat("0");
            }

        String montoTotal = "000000000000000" + Monto[0] + (Monto[1]).substring(0,2);
        //System.out.println("Monto Totalllll " + montoTotal);
        //String montoTotal = "000000000000000" + Monto[0] + "00"; //valor original

        String nom_file = "Data_entrada_cuota_variable.txt";
        String ruta_file = System.getProperty("user.dir") + File.separator + nom_file;
        System.out.println("proceso 1.1");
        try {
            FileWriter fw = new FileWriter(ruta_file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter writer = new PrintWriter(bw);
            String cantReg = String.valueOf(lista.size()); //CANTIDAD DE REGISTROS

            if (cantReg.length() < 7) {
                cantReg = "0000000".concat(cantReg);
            }

            cantReg = cantReg.substring(cantReg.length() - 7, cantReg.length());

            montoTotal = montoTotal.substring(montoTotal.length() - 17, montoTotal.length());
            String cuenta = banco.get(0).getBanCuentaEmpresa().concat("     ");
            String cuentaEmpe = cuenta.substring(0, 14);
            writer.print("H");
            writer.print(cuentaEmpe);
            writer.print(banco.get(0).getBanSecServicio());
            writer.print(cantReg); //CANTIDAD DE REGISTROS
            writer.print(montoTotal); //TOTAL SOLES

            writer.print("00000000000000000"); //TOTAL DOLARES
            writer.print(banco.get(0).getBanRucEmpresa());
            writer.print(fecha_envio);
            writer.print(fecha_vigencia);
            writer.print("   "); //filler 3
            writer.print(banco.get(0).getBanDiaMora());
            writer.print(banco.get(0).getBanTipoMora());
            writer.print(banco.get(0).getBanMoraFlat());
            writer.print(banco.get(0).getBanPorcenMora());
            writer.print("000000000"); // MONTO FIJO
            writer.print(banco.get(0).getBanTipoDescuento());
            writer.print("000000000"); // MONTO A DESCONTAR
            writer.print(banco.get(0).getBanPorcenDescuento());
            writer.print(banco.get(0).getBanDiasDescuento());

            String filler = new String();
            for (int i = 1; i <= 111; i++) {
                filler = filler.concat(" ");
            }
            writer.print(filler); //filler 111
            writer.print("*");
            writer.print("\r\n");

            /*lista = dao.listarDataBanco();*/

            for (int i = 0; i < lista.size(); i++) {
                String alu_cod = lista.get(i).getReg_codigo_alu().concat("             ");
                String alu_codigo = alu_cod.substring(0, 15);
                String nro_reci = lista.get(i).getReg_numero_rec() + "               ";
                String nro_recibo = nro_reci.substring(0, 15);
                String alu_nom = lista.get(i).getReg_nombre_usu() + "                    ";
                String alu_nombre = alu_nom.substring(0, 20);
                String ref_reci = lista.get(i).getReg_referencia_rec() + "                              ";
                String ref_recibo = ref_reci.substring(0, 30);
                String importe_co = "0000000" + lista.get(i).getReg_importe_cob1().replace(".", "");
                String importe_cobrar = importe_co.substring(importe_co.length() - 9, importe_co.length());
                String dias_pro = "000" + lista.get(i).getReg_dias_prorroga();
                String dias_prorroga = dias_pro.substring(dias_pro.length() - 3, dias_pro.length());

                String importe_co2 = "0000000" + lista.get(i).getReg_importe_cob2().replace(".", "");
                String importe_cobrar2 = importe_co2.substring(importe_co2.length() - 9, importe_co2.length());

                String importe_co3 = "0000000" + lista.get(i).getReg_importe_cob3().replace(".", "");
                String importe_cobrar3 = importe_co3.substring(importe_co3.length() - 9, importe_co3.length());

                String importe_co4 = "0000000" + lista.get(i).getReg_importe_cob4().replace(".", "");
                String importe_cobrar4 = importe_co4.substring(importe_co4.length() - 9, importe_co4.length());

                String importe_co5 = "0000000" + lista.get(i).getReg_importe_cob5().replace(".", "");
                String importe_cobrar5 = importe_co5.substring(importe_co5.length() - 9, importe_co5.length());

                String importe_co6 = "0000000" + lista.get(i).getReg_importe_cob6().replace(".", "");
                String importe_cobrar6 = importe_co6.substring(importe_co6.length() - 9, importe_co6.length());

                String total_co = "0000000000000" + lista.get(i).getReg_total_cobrar().replace(".", "");
                String total_cobrar = total_co.substring(total_co.length() - 15, total_co.length());

                writer.print("D");
                writer.print(cuentaEmpe);
                writer.print(banco.get(0).getBanSecServicio());
                writer.print(alu_codigo); // CODIGO USUARIO -> ALU_COD
                writer.print(nro_recibo); // NUMERO RECIBO -> ALUTAR_ID
                writer.print(banco.get(0).getBanRucEmpresa()); //RUC EMPRESA
                writer.print(lista.get(i).getReg_situacion());
                writer.print(lista.get(i).getReg_moneda()); // SOLES -> 0000
                writer.print(replaces(alu_nombre));// nombre de usuario modificado
                writer.print(replaces(ref_recibo)); //referencia recibo modificado
                writer.print(lista.get(i).getReg_concepto_cob1()); // CONCEPTO A COBRAR 1
                writer.print(importe_cobrar); // IMPORTE A COBRAR 1
                writer.print(lista.get(i).getReg_concepto_cob2()); // CONCEPTO A COBRAR 2
                writer.print(importe_cobrar2); // IMPORTE A COBRAR 2
                writer.print(lista.get(i).getReg_concepto_cob3()); // CONCEPTO A COBRAR 3
                writer.print(importe_cobrar3); // IMPORTE A COBRAR 3
                writer.print(lista.get(i).getReg_concepto_cob4()); // CONCEPTO A COBRAR 4
                writer.print(importe_cobrar4); // IMPORTE A COBRAR 4
                writer.print(lista.get(i).getReg_concepto_cob5()); // CONCEPTO A COBRAR 5
                writer.print(importe_cobrar5); // IMPORTE A COBRAR 5
                writer.print(lista.get(i).getReg_concepto_cob6()); // CONCEPTO A COBRAR 6
                writer.print(importe_cobrar6); // IMPORTE A COBRAR 6 O MORA
                writer.print(total_cobrar); //TOTAL A COBRAR
                writer.print(total_cobrar); //SALDO A DEUDA
                writer.print(lista.get(i).getReg_porcentaje_min());
                writer.print(lista.get(i).getReg_orden_crono());
                writer.print(lista.get(i).getReg_fecha_ini()); //FECHA DE EMISION
                writer.print(lista.get(i).getReg_fecha_venci()); //FECHA DE VENCIMIENTO
                writer.print(dias_prorroga); //DIAS DE PRORROGA
                writer.print("               ");
                writer.print("*");
                writer.print("\r\n");
            }
            this.contador++;

            System.out.println("proceso 1.2");
            System.out.println("el contador es " + this.contador);
            List<Sp_conceptos_banco> conceptos = dao.listarConceptosBanco();
            for (int i = 0; i < conceptos.size(); i++) {
                Sp_conceptos_banco concep = conceptos.get(i);

                writer.print("C");
                writer.print(cuentaEmpe);
                writer.print(banco.get(0).getBanSecServicio());

                //CODIGO CONCEPTO
                String codigo="00"+concep.getReg_concepto();

                String cogigok=codigo.substring(codigo.length()-2, codigo.length());
                /*if (i <= 9) {
                    writer.print("0" + (concep.getReg_concepto()));
                } else {*/
                    writer.print(cogigok);
                //}

                String concep_desc = concep.getReg_descripcion();
                for (int j = concep_desc.length(); j < 30; j++) {
                    concep_desc = concep_desc.concat(" ");
                }
                writer.print(this.replaces(concep_desc));//DESCRIPCION CONCEPTO  30
                writer.print("0");//AFECTO A PAGO PARCIAL  1
                writer.print(cuentaEmpe);//CUENTA DE ABONO

                filler = new String();
                for (int k = 0; k < 188; k++) {
                    filler = filler.concat(" ");
                }
                writer.print(filler);
                writer.print("*");
                writer.print("\r\n");
            }
            writer.close();

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + nom_file.replaceFirst(".txt", "_" + dateFormat.format(new Date()) + ".txt"));
            response.addHeader("Content-Type", "application/force-download");
            FileInputStream archivo = new FileInputStream(ruta_file);
            byte[] datos = new byte[archivo.available()];
            archivo.read(datos);
            archivo.close();
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(datos);
            ouputStream.flush();
            ouputStream.close();
            FacesContext.getCurrentInstance().responseComplete();

            if (new File(ruta_file).delete()) {
                System.out.println("se borro el archivo");
            }
        } catch (IOException ioe) {
            System.err.println("se presento el error: " + ioe.getMessage());
        }
    }

    public void cargarArchivo(UploadEvent event) {
        try {
            UploadItem item = event.getUploadItem();

            this.setFile(item.getFile());
            this.setFile_nombre(item.getFileName());
            this.setFile_dim(item.getFile().length());
            this.setDisable_proceso("false");
        } catch (Exception e) {
            System.err.println("Error al cargar archivo " + e.getMessage());
        }
    }

    public void procesarArchivo() throws Exception {
        List<Detalle> detalles = null;
        List<Concepto> conceptos = null;


        FileReader fr = null;
        BufferedReader br = null;

        boolean bandError = false;
        transacciones = new ArrayList<InfoBanco>();
        errores = new ArrayList<RespuestaBanco>();
        try {
            InfoBanco banco = null;
            int numero_linea = 1;

            fr = new FileReader(this.getFile());
            br = new BufferedReader(fr);
            String cadena = br.readLine();
            while (cadena != null) {
                if (!cadena.endsWith("*")) {
                    bandError = true;
                    errores.add(new RespuestaBanco(numero_linea, null, "No termina con el caracter '*'"));
                    cadena = br.readLine();
                } else if (cadena.length() != 180) {
                    errores.add(new RespuestaBanco(numero_linea, null, "No tiene el numero de caracteres establecidos"));
                    bandError = true;
                    cadena = br.readLine();
                } else {
                    switch (cadena.charAt(0)) {
                        case 'H':
                            banco = new InfoBanco();
                            detalles = new ArrayList<Detalle>();
                            conceptos = new ArrayList<Concepto>();

                            banco.setCabecera(new Cabecera(cadena));
                            break;
                        case 'D':
                            Detalle d = new Detalle(cadena);
                            entradas = new ArrayList<TbBancoEntrada>();

                            HSBancoDAO dao = (HSBancoDAO) ServiceFinder.findBean("SpringHibernateDaoBanco");
                            HSAlumnoTarifaDAO daoat = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
                            HSPagoDAO daopag = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
                            List<TbPagoBanco> pbs = dao.pagosBanco(convertirInt(d.getNumero_recibo().trim()));
                            List<AdAlumnoTarifa> pbsat =  daoat.seleccionarAlumnoTarifaBanco(pbs.listIterator().next().getAlutarId());

                            //int pbspag =  daopag. listarPagosCancelados(pbs.listIterator().next().getAlutarId());
                            int pbspag =  daopag.listarPagosCancelados(pbs.listIterator().next().getAlutarId());
                               //System.out.println("id -->" + pbs.listIterator().next().getAlutarId() );
                            /*if(pbspag==0){
                                 System.out.println("3");
                                    System.out.println("error");
                                }else{
                                     System.out.println("4");
                                System.out.println("oks");
                                }*/
                            // System.out.println(pbsat.listIterator().next().getAlutarEstado());
                             //System.out.println("---->" + d.getNumero_operacion() + "recibo-->" + d.getNumero_recibo());


                             if (dao.pagosBanco(convertirInt(d.getNumero_recibo().trim())).isEmpty()) {
                                errores.add(new RespuestaBanco(numero_linea, d.getNumero_operacion(),
                                        "Presenta datos incorrectos, no se puede procesar"));
                                bandError = true;

                            }else if(pbsat.listIterator().next().getAlutarEstado().equals("2")){
                                errores.add(new RespuestaBanco(numero_linea, d.getNumero_operacion(),
                                        "El registro ya ha sido ingresado"));
                                bandError = true;

                                 }else if(pbspag==0){
                                errores.add(new RespuestaBanco(numero_linea, d.getNumero_operacion(),
                                        "ya existe una boleta emitida"));
                                bandError = true;
                            /*} else if (dao.existeNroOperacion(d.getNumero_operacion(),convertirFecha(d.getFecha_pago())) != null) {*/
                                } else if (dao.existeNroOperacion(d.getNumero_operacion(),convertirFecha(d.getFecha_pago())).size() > 0) {
                                errores.add(new RespuestaBanco(numero_linea, d.getNumero_operacion(),
                                        "El registro ya ha sido procesado"));
                                bandError = true;
                            } else {
                                detalles.add(d);
                            }
                            break;
                        case 'T':
                            conceptos.add(new Concepto(cadena));
                            break;
                    }
                    cadena = br.readLine();
                    if (cadena != null) {
                        if (cadena.charAt(0) == 'H') {
                            if (banco != null) {
                                banco.setDetalles(detalles);
                                banco.setConceptos(conceptos);

                                transacciones.add(banco);
                            }
                        }
                    } else {
                        banco.setDetalles(detalles);
                        banco.setConceptos(conceptos);

                        transacciones.add(banco);
                    }
                }// FIN DE IF-ELSE
                numero_linea++;
            }
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException ioe) {
                System.err.println("Error al cerrar archivo\n\t " + ioe.getMessage() + "\n\t" + ioe.getLocalizedMessage());
            }
        }

        if (bandError) {
            this.setBody_class("error");
            this.setMensaje("<h3>Archivo procesado con errores - Revise las lineas mostradas</h3>");
        } else {
            this.setBody_class("success");
            this.setMensaje("<h3>Archivo procesado con exito - Todos los datos correctos</h3>");
        }

        this.llenarTablaEntradas();
        this.setFile(null);
        this.setDisable_proceso("true");
        this.setRendered("true");
        this.setGenera_comprobante(true);
    }

    public void llenarTablaEntradas() {
        entradas = new ArrayList<TbBancoEntrada>();
        respuestas = new ArrayList<RespuestaBanco>();

        HSBancoDAO dao = (HSBancoDAO) ServiceFinder.findBean("SpringHibernateDaoBanco");
        for (int i = 0; i < transacciones.size(); i++) {
            InfoBanco infoBanco = transacciones.get(i);

            this.setFecha_comprobantes(convertirFecha(
                    infoBanco.getCabecera().getFecha_movimiento()));

            List<Detalle> detalles = infoBanco.getDetalles();
            for (Detalle d : detalles) {
                TbBancoEntrada bancoEntrada = new TbBancoEntrada();

                bancoEntrada.setBanentMonto1(convertirFloat(d.getImporte1()));
                bancoEntrada.setBanentMonto2(convertirFloat(d.getImporte2()));
                bancoEntrada.setBanentMonto3(convertirFloat(d.getImporte3()));
                bancoEntrada.setBanentMonto4(convertirFloat(d.getImporte4()));
                bancoEntrada.setBanentMonto5(convertirFloat(d.getImporte5()));
                bancoEntrada.setBanentMora(convertirFloat(d.getImporte6_mora()));
                bancoEntrada.setBanentFechaMovimiento(convertirFecha(infoBanco.getCabecera().getFecha_movimiento()));
                bancoEntrada.setBanentFechaPago(convertirFecha(d.getFecha_pago()));
                bancoEntrada.setBanentFechaCreacion(Timestamp.valueOf(timeStampFormat.format(new Date())));
                bancoEntrada.setBanentNroOperacion(d.getNumero_operacion());
                bancoEntrada.setBanentProcesado("0");
                bancoEntrada.setBanentActivo("1");
                bancoEntrada.setBanentNroRecibo(new Integer(d.getNumero_recibo().trim()));

                long fechaInicialMs = convertirFecha(d.getFecha_vencimiento()).getTime();
                long fechaFinalMs = convertirFecha(d.getFecha_pago()).getTime();
                long diferencia = fechaFinalMs - fechaInicialMs;
                int dias = (int) Math.floor(diferencia / (1000 * 60 * 60 * 24));

                double montoMo = convertirFloat(d.getImporte1())
                        + convertirFloat(d.getImporte2())
                        + convertirFloat(d.getImporte3())
                        + convertirFloat(d.getImporte4())
                        + convertirFloat(d.getImporte5());



                entradas.add(bancoEntrada);

                String conceptos = "";
                int alu_id = 0;
                List<TbPagoBanco> pb = dao.pagosBanco(convertirInt(d.getNumero_recibo().trim()));
                double montoTo = 0;

                for (int hh = 0; hh < pb.size(); hh++) {
                    TbPagoBanco pagoBanco = pb.get(hh);

                    AdAlumnoTarifa alutar = getAlumnoTarifa(pagoBanco.getAlutarId());
                    String nuevo_concepto = alutar.getEstpagdet().getEstpagdetNombre();
                    if (pagoBanco.getPagbanConcepto().equalsIgnoreCase("06")) {
                        conceptos = "- " + nuevo_concepto + "<br />" + conceptos;
                    } else {
                        conceptos = "- " + conceptos + nuevo_concepto + "<br />";
                    }
                    alu_id = pagoBanco.getAluId();
//                    System.out.println("id del alumno_tarifa: " + alutar.getId()
//                            + "\tid d la tabla d ric: " + alu_id
//                            + "\tconceptos: " + conceptos);
                    montoTo = montoTo + pb.get(hh).getPagbanMonto();
                }
                conceptos = conceptos.substring(0, conceptos.length() - 6);
                RespuestaBanco rb = new RespuestaBanco(bancoEntrada);
                rb.setBanentNomAlumno(getNombreAlumno(alu_id));
                rb.setBanentConceptoPago(conceptos);
                rb.setBanentHabilitar(false);
                /*System.out.println("montoto -> "+montoTo);
                System.out.println("montoMo -> "+montoMo);*/
                if (montoTo == montoMo) {
                    rb.setBanentHabilitar(true);
                }

                rb.setBanentMontoTotal(montoTo);
                respuestas.add(rb);
            }
        }
    }

    public void validaComprobante(ActionEvent event) {
        this.setOncomplete("");
        if (this.isGenera_comprobante()) {
            try {
                if (this.getSerie() == null) {
                    this.setOncomplete("javascript:alert('Seleccione una seria para los comprobantes');");
                } else {
                    int nro_inicio = convertirInt(this.getNroInicio().trim());
                    if (nro_inicio == 0) {
                        this.setOncomplete("javascript:alert('Ingrese un numero de inicio correcto');");
                    } else {
                        this.setOncomplete("Richfaces.showModalPanel('mpSalvar',{top:150})");
                    }
                }
            } catch (NumberFormatException nfe) {
                this.setOncomplete("alert('Ingrese un numero de inicio correcto');");
            }
        } else {
            this.setOncomplete("Richfaces.showModalPanel('mpSalvar',{top:150})");
        }
    }

    public String guardarInformacion() {
        HSBancoDAO dao = (HSBancoDAO) ServiceFinder.findBean("SpringHibernateDaoBanco");
        if (this.isGenera_comprobante()) {
            generarComprobantes();
            for (int i = 0; i < entradas.size(); i++) {
                if(respuestas.get(i).getBanentHabilitar()==false){
                   // System.out.println("valor verdad -> "+respuestas.get(i).getBanentHabilitar());

                    TbBancoEntrada tbBancoEntrada = entradas.get(i);
                    tbBancoEntrada.setBanentProcesado("1");
                    entradas.remove(i);
                }
            }
        }
        dao.guardarRespuestaBanco(entradas);

//        System.out.println("INFORMACION SALVADA");
       /* this.entradas = null;
        this.respuestas = null;*/
        this.disable_proceso = "true";
        this.rendered = "false";
        this.file_nombre = "";
        this.file_dim = 0;
        this.serie = "";
        this.nroInicio = "";
        this.genera_comprobante = true;
        this.fecha_comprobantes = new Date();
        return "ProcesoBanco";
    }

    public void generarComprobantes() {
//        System.out.println("proceso para generar AdComprobantePago");

        List<AdComprobantePago> compags = new ArrayList<AdComprobantePago>();

        int iCompagnro = convertirInt(this.getNroInicio());
        int usu_id = capturarUsuario();

        HSBancoDAO daoBanco = (HSBancoDAO) ServiceFinder.findBean("SpringHibernateDaoBanco");
        HSEdicionEstructPagoDAO daoAlumnoTarifa = (HSEdicionEstructPagoDAO) ServiceFinder.findBean("SpringHibernateDaoEdicionEstructura");

        for (InfoBanco banco : transacciones) {
            for (int j = 0; j < banco.getDetalles().size(); j++) {
                Detalle detalle = banco.getDetalles().get(j);

                AdComprobantePago comprobante = new AdComprobantePago();

                List<TbPagoBanco> pbs = daoBanco.pagosBanco(convertirInt(detalle.getNumero_recibo().trim()));

                String[] importes = detalle.getImportes();
                String tmp = String.valueOf(iCompagnro + j);
                for (int i = tmp.length(); i < 7; i++) {
                    tmp = "0".concat(tmp);
                }
                /* double montoMora=pbs.get(0).getPagbanMora()+pbs.get(0).get;
                double montoTotal=sumatoriaImportes(importes);
                if(montoMora)*/
                String mensaje = "";
                for (int i = 0; i < this.respuestas.size(); i++) {
                     //System.out.println("this.respuestas.get(i).getBanentNroRecibo() dd->" +this.respuestas.get(i).getBanentNroRecibo());
                       // System.out.println("pbs.get(0).getPagbanCodigoGrupo() dd->" +pbs.get(0).getPagbanCodigoGrupo());
                    if ((int)this.respuestas.get(i).getBanentNroRecibo() == (int)pbs.get(0).getPagbanCodigoGrupo()) {
                        //System.out.println("this.respuestas.get(i).getBanentNroRecibo() ->" +this.respuestas.get(i).getBanentNroRecibo());
                       // System.out.println("pbs.get(0).getPagbanCodigoGrupo() ->" +pbs.get(0).getPagbanCodigoGrupo());
                        if (this.respuestas.get(i).getBanentHabilitar()) {
                            mensaje = "ok";
                        }
                    }
                }
                //System.out.println("mensaje -> "+mensaje);

                if (mensaje.equals("ok")) {
                    String compagnro = this.getSerie().concat("-").concat(tmp);
                    comprobante.setCompagNro(compagnro);// compag_nro
                    comprobante.setCompagMonto(sumatoriaImportes(importes));// compag_monto
                    comprobante.setCompagFecha(this.getFecha_comprobantes());// compag_fecha
                    comprobante.setCompagCliente(pbs.get(0).getAluId());// compag_cliente - ALU_ID
                    comprobante.setCompagClienteTipo("014001");// compag_cliente_tipo - ALUMNO CP
                    comprobante.setCompagUsado("1");// compag_usado
                    comprobante.setCompagProcedencia("015007");// compag_procedencia - PAGO BANCO
                    comprobante.setCompagCreacion(new Date());// compag_creacion
                    comprobante.setCompagActivo("1");// compag_activo
                    comprobante.setCompagDes("");// compag_des
                    comprobante.setCompagAlu(0);// compag_alu
                    comprobante.setCompagTipo("037001");// compag_tipo - ESTADO DE BOLETA
                    comprobante.setCompagTipDoc("039001");// compag_tip_doc - BOLETA
                    comprobante.setCompagAluTipo("");// compag_alu_tipo
                    comprobante.setCompagVoucherFecha(new Date());// compag_voucher_fecha
                    comprobante.setCompagVoucherCodAgencia("");// compag_voucher_cod_agencia
                    comprobante.setCompagVoucherNroOperacion("");// compag_voucher_nro_operacion
                    comprobante.setCompagEstadoCancelacion("066002");////////////////
                    comprobante.setCompagFechaCancelacion(this.getFecha_comprobantes());//////////////

                    Set<AdPago> pagos = new HashSet<AdPago>();
                    Integer alutar_id = 0;
                    for (int i = 0; i < pbs.size(); i++) {
                        TbPagoBanco pagban = pbs.get(i);

                        AdAlumnoTarifa alutar = getAlumnoTarifa(pagban.getAlutarId());
                        alutar_id = alutar.getId();

                        AdPago pago = new AdPago();
                        pago.setPagMonto(pagban.getPagbanMonto());// pag_monto
                        pago.setPagFecha(convertirFecha(detalle.getFecha_pago()));// pag_fecha
                        pago.setPagActivo("1");// pag_activo
                        pago.setUsuId(usu_id);// usu_id
                        pago.setCompag(comprobante);// compag_id
                        pago.setConpag(alutar.getConpag());// conpag_id
                        pago.setAlutarId(alutar_id);// alutar_id
                        pago.setConpagDetId(3);
                        pago.setPagEstadoCancelacion("067002");/////////////////////
                        pago.setPagFechaCancelacion(convertirFecha(detalle.getFecha_pago()));////////////////
                        pagos.add(pago);
//                    System.out.println("\tPAGO monto " + pago.getPagMonto() + " alutar_id " + pago.getAlutarId());

                        daoAlumnoTarifa.actualizarEstadoPago(alutar.getId(), "2");
                    }
                    Float monto = convertirFloat(detalle.getImporte6_mora());
                    if (monto.floatValue() != 0 && alutar_id.intValue() != 0) {
                        AdPago pago = new AdPago();
                        pago.setPagMonto(monto);// pag_monto
                        pago.setPagFecha(convertirFecha(detalle.getFecha_pago()));// pag_fecha
                        pago.setPagActivo("1");// pag_activo
                        pago.setUsuId(usu_id);// usu_id
                        pago.setCompag(comprobante);// compag_id
                        pago.setConpag(new AdConceptoPago(49));// conpag_id
                        pago.setConpagDetId(48);
                        pago.setAlutarId(alutar_id);// alutar_id
                        pago.setPagEstadoCancelacion("067002");///////////
                        pago.setPagFechaCancelacion(convertirFecha(detalle.getFecha_pago()));////////

                        pagos.add(pago);
//                    System.out.println("\tPAGO monto " + pago.getPagMonto() + " alutar_id " + pago.getAlutarId());
                    }
                    comprobante.setAdPagos(pagos);

                    compags.add(comprobante);
                }
//                System.out.println("\tCOMPROBANTE total_pagos " + comprobante.getAdPagos().size() + " monto_total " + comprobante.getCompagMonto() + " alu_id " + comprobante.getCompagCliente() + " ");
            }

        }// FIN FOR - LLENAR LISTA DE COMPROBANTES

        HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
        daoPago.insertarComprobantesPago(compags);
    }

    public void invocarReporte(ActionEvent event) {
        this.setReport_value("reporteBanco");
    }

    public void reportePagosBanco(OutputStream out, Object data)
            throws IOException, EOFException, SQLException, JRException {

        if (data.equals("reporteBanco")) {
            String jasper = "/WEB-INF/Reportes/data_respuesta_banco.jasper";

            Print print = new Print();
            ByteArrayOutputStream buffer = print.cargar_reporte(jasper, null);

            byte[] bytes = buffer.toByteArray();

            InputStream input = new ByteArrayInputStream(bytes);
            int size = input.available();

            byte[] pdf = new byte[size];
            input.read(pdf);
            out.write(pdf);

            buffer.flush();
            buffer.close();
            input.close();
            out.flush();
            out.close();
        }
        this.setReport_value("");
    }

    private String replaces(String cadena) {
        cadena = cadena.toUpperCase();
        cadena = cadena.replace('Ñ', 'N').
                replace('Á', 'A').replace('Ä', 'A').replace('À', 'A').
                replace('É', 'E').replace('Ë', 'E').replace('È', 'E').
                replace('Í', 'I').replace('Ï', 'I').replace('Ì', 'I').
                replace('Ó', 'O').replace('Ö', 'O').replace('Ò', 'O').
                replace('Ú', 'U').replace('Ü', 'U').replace('Ù', 'U');
        return cadena;
    }

    private AdAlumnoTarifa getAlumnoTarifa(int alutar_id) {
        HSAlumnoTarifaDAO dao = (HSAlumnoTarifaDAO) ServiceFinder.findBean("SpringHibernateDaoAlumnoTarifa");
        return dao.seleccionarUnAlumnoTarifa(alutar_id);
    }

    private String getNombreAlumno(int alu_id) {
        String nombre = "";
        try {
            HSAlumnoDAO daoAc = (HSAlumnoDAO) ServiceFinder.findBean("SpringHibernateDaoAlumno");
            AcAlumno alumno = daoAc.seleccionarAlumno(alu_id);
            nombre = alumno.getAluAppaterno().trim() + " "
                    + alumno.getAluApmaterno().trim() + " "
                    + alumno.getAluNombres().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombre.toUpperCase();
    }

    private int convertirInt(String cadena) {
        return Integer.parseInt(cadena.trim());
    }

    private Date convertirFecha(String cadena) {
        int year = Integer.parseInt(cadena.substring(0, 4).trim());
        int month = Integer.parseInt(cadena.substring(4, 6).trim());
        int dayOfMonth = Integer.parseInt(cadena.substring(6, 8).trim());

        return new GregorianCalendar(year, month - 1, dayOfMonth).getTime();
    }

    private Float convertirFloat(String cadena) {
        float integer = Float.parseFloat(cadena.substring(0, 7).trim());
        float decimal = Float.parseFloat(cadena.substring(7, 9).trim()) / 100;

        return new Float(integer + decimal);
    }

    private Float sumatoriaImportes(String... importes) {
        float sumatoria = 0;
        for (String str : importes) {
            sumatoria += convertirFloat(str).floatValue();
        }
        return new Float(sumatoria);
    }

    private int capturarUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        int id_usu = ((bUsuario) session.getAttribute("usuario")).getId_usu();
        return id_usu;
    }
}
