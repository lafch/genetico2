/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.managedBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcActividadAlcance;
import net.uch.mapping.AcCalendario;
import net.uch.mapping.AcCalendarioActividades;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSActividadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

/**
 *
 * @author cesardl
 */
public class bActividad {

    private int b_id;
    private Date b_fecha;
    private String b_titulo;
    private Date b_fechaIni;
    private Date b_fechaFin;
    private boolean b_publicado;
    private String b_estPubl;
    private String b_alcance;
    private String b_arrayAlcances[];
    private Object b_arrayAlcancesId[][];
    private String b_tipo;
    private String b_descripcion;
    private Blob b_img;
    private String b_mime;
    //para listar las actividades
    private String imagenPublicado;
    private String title;
    private Date paramDate;
    private List<bActividad> listaActividades;
    private SimpleDateFormat formatTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    //para el js
    private String oncomplete;
    //para los check
    private SelectItem itemCheckAlcance[];
    private SelectItem itemComboTipo[];
    //Para los codigos
    private String itemCheckAlcanceCod[];
    private String itemComboTipoCod[];
    //para editar y publicar
    private int editar;
    private int id_cal;
    private String publicado;
    private final Timestamp tfNow;

    public bActividad() {
        tfNow = Timestamp.valueOf(formatTimeStamp.format(new Date()));
    }

    public String[] getB_arrayAlcances() {
        return b_arrayAlcances;
    }

    public void setB_arrayAlcances(String[] b_arrayAlcances) {
        this.b_arrayAlcances = b_arrayAlcances;
    }

    public String getListaAlcance() {
        return concatenar(b_arrayAlcances);
    }

    public Object[][] getB_arrayAlcancesId() {
        return b_arrayAlcancesId;
    }

    public void setB_arrayAlcancesId(Object[][] b_arrayAlcancesId) {
        this.b_arrayAlcancesId = b_arrayAlcancesId;
    }

    public String getB_tipo() {
        return b_tipo;
    }

    public void setB_tipo(String b_tipo) {
        this.b_tipo = b_tipo;
    }

    public Date getB_fecha() {
        return b_fecha;
    }

    public void setB_fecha(Date b_fecha) {
        this.b_fecha = b_fecha;
    }

    public Date getB_fechaFin() {
        return b_fechaFin;
    }

    public void setB_fechaFin(Date b_fechaFin) {
        this.b_fechaFin = b_fechaFin;
    }

    public Date getB_fechaIni() {
        return b_fechaIni;
    }

    public void setB_fechaIni(Date b_fechaIni) {
        this.b_fechaIni = b_fechaIni;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public boolean isB_publicado() {
        return b_publicado;
    }

    public void setB_publicado(boolean b_publicado) {
        this.b_publicado = b_publicado;
    }

    public String getB_alcance() {
        return b_alcance;
    }

    public void setB_alcance(String b_alcance) {
        this.b_alcance = b_alcance;
    }

    public String getB_titulo() {
        return b_titulo;
    }

    public void setB_titulo(String b_titulo) {
        this.b_titulo = b_titulo;
    }

    public String getB_descripcion() {
        return b_descripcion;
    }

    public void setB_descripcion(String b_descripcion) {
        this.b_descripcion = b_descripcion;
    }

    public Blob getB_img() {
        return b_img;
    }

    public void setB_img(Blob b_img) {
        this.b_img = b_img;
    }

    public String getImagenPublicado() {
        return imagenPublicado;
    }

    public String getB_mime() {
        return b_mime;
    }

    public void setB_mime(String b_mime) {
        this.b_mime = b_mime;
    }

    public void setImagenPublicado(String imagenPublicado) {
        this.imagenPublicado = imagenPublicado;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getParamDate() {
        return paramDate;
    }

    public void setParamDate(Date paramDate) {
        this.paramDate = paramDate;
    }

    public List<bActividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<bActividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getB_estPubl() {
        return b_estPubl;
    }

    public void setB_estPubl(String b_estPubl) {
        this.b_estPubl = b_estPubl;
    }

    /**
     * Para la data del check
     * @return
     */
    public SelectItem[] getItemCheckAlcance() {
        try {
            HSCatalogoDAO catalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List<TbCatalogo> list = catalogoDAO.seleccionarCatalogo("052");
            itemCheckAlcance = new SelectItem[list.size()];
            itemCheckAlcanceCod = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                itemCheckAlcance[i] = new SelectItem(list.get(i).getCatDescripcionElemento());
                itemCheckAlcanceCod[i] = new String("052" + list.get(i).getCatCodigoElemento());
            }
        } catch (Exception e) {
            itemCheckAlcance = new SelectItem[0];
            itemCheckAlcanceCod = new String[0];
        }
        return itemCheckAlcance;
    }

    public void setItemCheckAlcance(SelectItem[] itemCheckAlcance) {
        this.itemCheckAlcance = itemCheckAlcance;
    }

    /**
     * Para la data del combo
     * @return
     */
    public SelectItem[] getItemComboTipo() {
        try {
            HSCatalogoDAO catalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
            List<TbCatalogo> list = catalogoDAO.seleccionarCatalogo("053");
            itemComboTipo = new SelectItem[list.size()];
            itemComboTipoCod = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                itemComboTipo[i] = new SelectItem(list.get(i).getCatDescripcionElemento());
                itemComboTipoCod[i] = new String("053" + list.get(i).getCatCodigoElemento());
            }
        } catch (Exception e) {
            itemComboTipo = new SelectItem[0];
            itemComboTipoCod = new String[0];
        }
        return itemComboTipo;
    }

    public void setItemComboTipo(SelectItem[] itemComboTipo) {
        this.itemComboTipo = itemComboTipo;
    }

    public int getEditar() {
        return editar;
    }

    public void setEditar(int editar) {
        this.editar = editar;
    }

    public int getId_cal() {
        return id_cal;
    }

    public void setId_cal(int id_cal) {
        this.id_cal = id_cal;
    }

    public String getPublicado() {
        return publicado;
    }

    public void setPublicado(String publicado) {
        this.publicado = publicado;
    }

    public void nuevo() {
        this.setB_fecha(null);
        this.setB_fechaIni(null);
        this.setB_fechaFin(null);
        this.setB_arrayAlcances(null);
        this.setB_arrayAlcancesId(null);
        this.setB_publicado(false);
        this.setB_titulo("");
        this.setB_descripcion("");
        this.setB_tipo("");
        this.setB_img(null);
        this.setEditar(0);
        System.out.println("hago nuevo los datos");
    }

    /**
     * Metodo para carga la imagen
     * @param event
     */
    public void cargaImagen(UploadEvent event) {
        try {
            UploadItem item = event.getUploadItem();
            this.setB_mime(item.getContentType());
            this.setB_img(new SerialBlob(fromFiletoByteArray(item.getFile())));
            System.out.println(item.getFile().length());
        } catch (Exception e) {
            System.out.println("FALLA AL CARGAR LA IMAGEN");
        }
    }

    public void limpiarImagenActividad(ActionEvent event) {
        this.setB_img(null);
    }

    public void avatarContacto(OutputStream stream, Object data) throws IOException, SQLException {
        if (data.equals("imgActividad")) {
            if (this.getB_img() != null) {
                int length = (int) this.getB_img().length();
                stream.write(this.getB_img().getBytes(1, length));
            }
        }
    }

    /**
     * Proceso de validacion de datos y seleccion si va a
     * editar o insertar una nueva actividad
     * @param event
     * @throws SQLException
     */
    public void manipularDatos(ActionEvent event) throws SQLException {
        this.setOncomplete("");
        if (this.getB_titulo().trim().length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese un titulo.');");
        } else if (this.getB_fecha() == null
                || this.getB_fechaIni() == null
                || this.getB_fechaFin() == null) {
            this.setOncomplete("javascript:alert('Faltan llenar los campos de las fechas')");
        } else if (this.getB_fechaIni().getTime() >= this.getB_fechaFin().getTime()) {
            this.setOncomplete("javascript:alert('La fecha de fin no puede ser antes de la de inicio');");
        } else if (this.getB_tipo().trim().length() == 0) {
            this.setOncomplete("javascript:alert('Seleccione el tipo de actividad');");
        } else if (this.getB_arrayAlcances().length == 0) {
            this.setOncomplete("javascript:alert('Seleccione al menos un alcance');");
        } else if (this.getB_img() != null && this.getB_img().length() >= 100000000) {
            this.setOncomplete("javascript:alert('Imagen demasiado pesada');");
        } else {
            nuevaActividad();
        }
    }

    public void seleccionarActividad(ActionEvent event) {
        HSActividadDAO dao = (HSActividadDAO) ServiceFinder.findBean("SpringHibernateDaoActividad");
        UIParameter uip = (UIParameter) event.getComponent().findComponent("p_id");
        int id = Integer.parseInt(uip.getValue().toString());

        AcCalendario cal = dao.seleccionarActividadId(id);
        this.setId_cal(cal.getCalId());
        this.setB_fecha(cal.getCalFecha());

        AcCalendarioActividades calact =
                (AcCalendarioActividades) cal.getAcCalendarioActividadeses().toArray()[0];

        this.setB_id(calact.getCalactId());
        this.setEditar(calact.getCalactId());
        this.setB_titulo(calact.getCalactTitulo());
        this.setB_fechaIni(calact.getCalactFechaInicio());
        this.setB_fechaFin(calact.getCalactFechaFin());
        this.setB_descripcion(calact.getCalactDesc());
        this.setB_img(calact.getCalactImg());

        if (calact.getCalactPublicado().equals("1")) {
            this.setB_estPubl("1");
            this.setB_publicado(true);
        } else if (calact.getCalactPublicado().equals("0")) {
            this.setB_estPubl("0");
            this.setB_publicado(false);
        }
        int size = calact.getAcActividadAlcances().size();
        String[] alcances = new String[size];
        Object[][] alcanceDetalle = new Object[size][2];

        Iterator<AcActividadAlcance> it = calact.getAcActividadAlcances().iterator();
        int j = 0;
        while (it.hasNext()) {
            AcActividadAlcance actalc = it.next();
            if (actalc.getActalcActivo().equals("1")) {
                alcances[j] = obtenerAlcanceDescripcion(actalc.getActalcAlcance());
                alcanceDetalle[j][0] = actalc.getActalcId();
                alcanceDetalle[j][1] = alcances[j];
            } else {
                alcances[j] = "";
            }
            j++;
        }
        this.setB_alcance(concatenar(alcances));
        this.setB_arrayAlcances(alcances);
        this.setB_arrayAlcancesId(alcanceDetalle);
    }

    /**
     * Para Mostrar en la tabla los datos
     */
    public void mostrarPorFecha() {
        this.setListaActividades(null);
        try {
            HSActividadDAO dao = (HSActividadDAO) ServiceFinder.findBean("SpringHibernateDaoActividad");
            java.sql.Date tfParam = null;
            try {
                tfParam = java.sql.Date.valueOf(formatDate.format(this.getParamDate()));
            } catch (NullPointerException npe) {
                try {
                    tfParam = java.sql.Date.valueOf(formatDate.format(this.getB_fecha()));
                } catch (Exception e) {
                    tfParam = null;
                }
            }
            if (tfParam != null) {
                List<AcCalendario> calendarios = dao.seleccionarActividadDetalle(tfParam);

                int i = 0;
                List<bActividad> lTmp = new ArrayList<bActividad>();
                while (i < calendarios.size()) {
                    bActividad tmp = new bActividad();
                    AcCalendario cal = calendarios.get(i);
                    tmp.setB_fecha(cal.getCalFecha());

                    AcCalendarioActividades calact =
                            (AcCalendarioActividades) cal.getAcCalendarioActividadeses().toArray()[0];

                    tmp.setB_id(calact.getCalactId());
                    tmp.setB_titulo(calact.getCalactTitulo());
                    tmp.setB_fechaIni(calact.getCalactFechaInicio());
                    tmp.setB_fechaFin(calact.getCalactFechaFin());
                    tmp.setB_descripcion(calact.getCalactDesc());
                    tmp.setB_img(calact.getCalactImg());
                    if (calact.getCalactPublicado().equals("1")) {
                        tmp.setImagenPublicado("/Imagenes/actions/activar.png");
                        tmp.setTitle("desea desactivar la actividad? dame click!");
                        tmp.setB_estPubl("1");
                        tmp.setB_publicado(true);
                    } else if (calact.getCalactPublicado().equals("0")) {
                        tmp.setImagenPublicado("/Imagenes/actions/desactivar.png");
                        tmp.setTitle("desea activar la actividad? dame click!");
                        tmp.setB_estPubl("0");
                        tmp.setB_publicado(false);
                    }
                    Object[] objActAlc = calact.getAcActividadAlcances().toArray();
                    String[] alcances = new String[objActAlc.length];
                    Object[][] mAlcances = new Object[objActAlc.length][2];
                    int act = 0;
                    for (int j = 0; j < objActAlc.length; j++) {
                        AcActividadAlcance actalc = (AcActividadAlcance) objActAlc[j];
                        if (actalc.getActalcActivo().equals("1")) {
                            alcances[j] = obtenerAlcanceDescripcion(actalc.getActalcAlcance());
                            mAlcances[j][0] = actalc.getActalcId();
                            mAlcances[j][1] = alcances[j];
                            act++;
                        } else {
                            alcances[j] = "";
                        }
                    }
                    tmp.setB_alcance(concatenar(alcances));
                    tmp.setB_arrayAlcances(alcances);
                    tmp.setB_arrayAlcancesId(mAlcances);
                    lTmp.add(tmp);
                    i = i + act;
                }
                this.setListaActividades(new ArrayList<bActividad>(lTmp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int capturarUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        int id_usu = ((bUsuario) session.getAttribute("usuario")).getId_usu();
        return id_usu;
    }

    private void nuevaActividad() {
        try {
            HSActividadDAO dao = (HSActividadDAO) ServiceFinder.findBean("SpringHibernateDaoActividad");
            int id_usu = capturarUsuario();

            AcCalendario cal = new AcCalendario();

            cal.setCalFecha(this.getB_fecha());
            cal.setCalActivo("1");
            cal.setCalFechaCreacion(tfNow);

            //AcCalendario tiene varios AcCalendarioActividades - ver MER
            Set<AcCalendarioActividades> setCalAct = new LinkedHashSet<AcCalendarioActividades>();

            //Siempre va a ser un AcCalendario par aun AcCalendarioActividad
            AcCalendarioActividades calAct = new AcCalendarioActividades();
            if (this.getEditar() != 0) {
                cal.setCalId(this.getId_cal());
                calAct.setCalactId(this.getEditar());
            }
            calAct.setCalactDesc(this.getB_descripcion());
            calAct.setCalactActivo("1");
            calAct.setCalactImg(this.getB_img());//RUTA DE LA IMAGEN

            calAct.setCalactCreacion(tfNow);
            calAct.setCalactTipo(this.obtenerTipoCatalogo(this.getB_tipo()));
            calAct.setUsuId(id_usu);
            calAct.setCalactTitulo(this.getB_titulo());
            calAct.setCalactPublicado(this.obtenerEstadoPublicado(this.isB_publicado()));
            Timestamp tfIni = Timestamp.valueOf(formatTimeStamp.format(this.getB_fechaIni()));
            Timestamp tfFin = Timestamp.valueOf(formatTimeStamp.format(this.getB_fechaFin()));
            calAct.setCalactFechaInicio(tfIni);
            calAct.setCalactFechaFin(tfFin);
            calAct.setCalactImg(this.getB_img());
            //Como AcCalendarioActividades tiene un AcCalendario.cal_id lo linkeo
            calAct.setAcCalendario(cal);

            //AcCalendarioActividad tiene varios AcActividadAlcance - ver MER
            Set<AcActividadAlcance> setActAcl = new LinkedHashSet<AcActividadAlcance>();
            //Puede ser varios alcances
            int[] mEncontrado = new int[this.getB_arrayAlcances().length];
            java.util.Vector tmp = new java.util.Vector();
            if (this.getEditar() != 0) {
                String alcances[] = this.getB_arrayAlcances();
                Object objs[][] = this.getB_arrayAlcancesId();

                for (int i = 0; i < objs.length; i++) {
                    try {
                        int id = Integer.parseInt(String.valueOf(objs[i][0]));
                        String desc = String.valueOf(objs[i][1]);
                        boolean encontrado = false;

                        for (int j = 0; j < alcances.length; j++) {
                            if (desc.equals(alcances[j])) {
                                mEncontrado[j] = id;
                                j = alcances.length;
                                encontrado = true;
                            }
                        }
                        if (!encontrado) {
                            tmp.addElement(id);
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR NULO MATRIZ " + e.getMessage());
                    }
                }
                eliminarAlcances(tmp);
            }
            for (int i = 0; i < this.getB_arrayAlcances().length; i++) {
                String alcance = this.getB_arrayAlcances()[i];

                AcActividadAlcance actalc = new AcActividadAlcance();
                if (this.getEditar() != 0) {
                    if (mEncontrado[i] != 0) {
                        actalc.setActalcId(mEncontrado[i]);
                    }
                }
                actalc.setActalcActivo("1");
                actalc.setActalcAlcance(this.obtenerAlcanceCatalogo(alcance));//modificar el alcance por numero
                actalc.setAcCalendarioActividades(calAct);
                //Aniado cada AcActividadAlcance al Set
                setActAcl.add(actalc);
            }
            //El CalendarioActividad tieen su set de ActividadAlcance, lo seteo ya lleno
            calAct.setAcActividadAlcances(setActAcl);

            //Como calActi tiene un set, ya tiene q estar lleno, por eso al final lo
            //agrego al set del mismo
            setCalAct.add(calAct);

            //El AcCalendario tiene su setCalAct, ahora lleno lo seteo
            cal.setAcCalendarioActividadeses(setCalAct);

            if (this.getEditar() == 0) {
                dao.insertarActividad(cal);
                this.setOncomplete("javascript:alert('Se creo una Actividad correctamente.');"
                        + "Richfaces.hideModalPanel('mpActividadNuevo')");
            } else {
                dao.actualizarActividad(cal);
                this.setOncomplete("javascript:alert('Se actualizo una Actividad correctamente.');"
                        + "Richfaces.hideModalPanel('mpActividadNuevo')");

            }
            mostrarPorFecha();
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Captura los parametros para cambiar el estado publicado
     * @param event
     * @throws NumberFormatException
     */
    public void setearParametros(ActionEvent event) throws NumberFormatException {
        UIParameter c1 = (UIParameter) event.getComponent().findComponent("p_id");
        UIParameter c2 = (UIParameter) event.getComponent().findComponent("p_est_pub");
        this.setEditar(Integer.parseInt(String.valueOf(c1.getValue())));
        this.setPublicado(String.valueOf(c2.getValue()));
    }

    /**
     * Metodo que cambia el estado de la actividad - publicado/no publicar
     */
    public void publicarActividad() {
        try {
            HSActividadDAO dao = (HSActividadDAO) ServiceFinder.findBean("SpringHibernateDaoActividad");
            if (this.getPublicado().equals("1")) {
                this.setPublicado("0");
            } else if (this.getPublicado().equals("0")) {
                this.setPublicado("1");
            }
            dao.actualizarEstadoPublicado(this.getEditar(), this.getPublicado());
            mostrarPorFecha();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarActividad(ActionEvent event) {
        try {
            HSActividadDAO dao = (HSActividadDAO) ServiceFinder.findBean("SpringHibernateDaoActividad");
            UIParameter uip = (UIParameter) event.getComponent().findComponent("p_id");
            int id = Integer.parseInt(uip.getValue().toString());
            dao.eliminarActividad(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    private void eliminarAlcances(java.util.Vector v) {
        try {
            for (int i = 0; i < v.size(); i++) {
                int actalcId = Integer.parseInt(String.valueOf(v.get(i)));
                HSActividadDAO dao = (HSActividadDAO) ServiceFinder.findBean("SpringHibernateDaoActividad");
                dao.eliminarAlcance(actalcId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String obtenerTipoCatalogo(String str) {
        String codTipo = "";
        for (int i = 0; i < itemComboTipo.length; i++) {
            String value = String.valueOf(itemComboTipo[i].getValue());
            if (value.equals(str)) {
                codTipo = itemComboTipoCod[i];
                break;
            }
        }
        return codTipo;
    }

    /**
     * Devuelve el ID del alcance 052001 por ejemplo
     * @param str
     * @return
     */
    private String obtenerAlcanceCatalogo(String str) {
        String codAlcance = "";
        for (int i = 0; i < itemCheckAlcance.length; i++) {
            String value = String.valueOf(itemCheckAlcance[i].getValue());
            if (value.equals(str)) {
                codAlcance = itemCheckAlcanceCod[i];
                break;
            }
        }
        return codAlcance;
    }

    private String obtenerAlcanceDescripcion(String str) {
        String descAlcance = "";
        for (int i = 0; i < itemCheckAlcanceCod.length; i++) {
            String value = itemCheckAlcanceCod[i];
            if (value.equals(str)) {
                descAlcance = String.valueOf(itemCheckAlcance[i].getValue());
                break;
            }
        }
        return descAlcance;
    }

    private String obtenerEstadoPublicado(boolean band) {
        if (band) {
            return "1";
        } else {
            return "0";
        }
    }

    private String concatenar(Object[] valores) {
        if (valores == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < valores.length; ++i) {
            if (!valores[i].equals("")) {
                sb.append(valores[i].toString() + ", ");
            } else {
                sb.append(valores[i].toString());
            }
        }
        String str = new String(sb).trim();
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    private byte[] fromFiletoByteArray(File file) throws IOException {
        FileInputStream fin = new FileInputStream(file);

        byte fileContent[] = new byte[(int) file.length()];
        int read = fin.read(fileContent);
        fin.close();
        if (read == -1) {
            return null;
        } else {
            return fileContent;
        }
    }
}
