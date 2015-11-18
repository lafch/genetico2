/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.administrativa.hibernateSpringDao.HSPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoTarifaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSMatriculaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAperturadoClDAO;
import net.uch.mapping.AdPago;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClEstructuraPagos;
import net.uch.mapping.ClEstructuraPagosDetalle;
import net.uch.mapping.ClMatricula;
import net.uch.util.CommonDAO;


/**
 *
 * @author cesardl
 */
public class bEdicionEstructPago {

    private int b_alu_id;
    private String b_codigo;
    private String b_paterno;
    private String b_materno;
    private String b_nombre;
    private String b_alumno;
    private String b_modulo;
    private String b_sub_concepto;
    private float b_sub_monto;
    private int b_sub_alutar_id;
    private String b_sub_alutar_estado;
    private Date b_sub_fecha_pago;
    private Date b_sub_fecha_prorroga;
    private String b_sub_img_candado;
    private String b_sub_edit_disabled;
    private String b_sub_img_klipper;
    private String b_sub_img_calendar;
    private String b_sub_title;
    private String b_sub_msj;
    private List<Integer> b_alutarIds;
    private SelectItem parametroAreas[];
    private int parametroArea;
    private SelectItem parametroModulos[];
    private int parametroModulo;
    //Input del modal nuevo
    private String modal_alumno;
    private int modal_id;
    private int modalArea;
    private int modalModulo;
    private SelectItem modalPlantillas[];
    private int modalPlantilla;
    private Vector<SelectItem> modalTalleres;
    private String modalTaller;
    private SelectItem modalMatriculas[];
    private int modalMatricula;
    //Input del modal editar
    private Date modal_pago;
    private Date modal_prorroga;
    private float modal_monto;
    //Variables para los efectos
    private String oncomplete;
    private String imagenVer;
    private String estadoVer;
    private String sugAlumno;
    private String sugCodigo;
    private boolean mostrar = false;
    private List<bEdicionEstructPago> listaEstructura;
    private List<bEdicionEstructPago> subListaEstructura;
    private int tmpAlutar = 0;
    private int tmpEstado = 0;
    private int tmpCompBloq = 0;

    public bEdicionEstructPago() {
        this.parametroArea = Integer.MAX_VALUE;
    }

    public String getB_modulo() {
        return b_modulo;
    }

    public void setB_modulo(String b_modulo) {
        this.b_modulo = b_modulo;
    }

    public int getParametroModulo() {
        return parametroModulo;
    }

    public void setParametroModulo(int parametroModulo) {
        this.parametroModulo = parametroModulo;
    }

    public int getParametroArea() {
        return parametroArea;
    }

    public void setParametroArea(int parametroArea) {
        this.parametroArea = parametroArea;
    }

    public SelectItem[] getParametroAreas() {
        if (parametroAreas == null) {
            try {
                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                List<ClArbolAcademico> areas = dao.seleccionarArea("");
                parametroAreas = new SelectItem[areas.size() + 1];
                parametroAreas[0] = new SelectItem(Integer.MAX_VALUE, "[Seleccione]");
                for (int i = 0; i < areas.size(); i++) {
                    ClArbolAcademico a = areas.get(i);
                    parametroAreas[i + 1] = new SelectItem(a.getArbId(), a.getArbDescripcion());
                }
            } catch (Exception e) {
                System.err.println("Error al cargar areas");
            }
        }
        return parametroAreas;
        /*if (parametroAreas == null) {
            try {
                HSAreaDAO dao = (HSAreaDAO) ServiceFinder.findBean("SpringHibernateDaoCLArea");
                List<ClArea> areas = dao.seleccionarArea("");
                parametroAreas = new SelectItem[areas.size() + 1];
                parametroAreas[0] = new SelectItem(Integer.MAX_VALUE, "[Seleccione]");
                for (int i = 0; i < areas.size(); i++) {
                    ClArea a = areas.get(i);
                    parametroAreas[i + 1] = new SelectItem(a.getAreId(), a.getAreDescripcion());
                }
            } catch (Exception e) {
                System.err.println("Error al cargar areas");
            }
        }
        return parametroAreas;*/
    }

    public void setParametroAreas(SelectItem[] parametroAreas) {
        this.parametroAreas = parametroAreas;
    }

    public void ver_modulos() {
        try {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> modulos = dao.seleccionarModulos(this.parametroArea, "");

            parametroModulos = new SelectItem[modulos.size() + 1];
            for (int i = 0; i < modulos.size(); i++) {
                parametroModulos[i + 1] = new SelectItem(modulos.get(i).getArbId(), modulos.get(i).getArbDescripcion());
            }
        } catch (Exception e) {
            parametroModulos = new SelectItem[1];
        } finally {
            parametroModulos[0] = new SelectItem(0, "[Seleccione]");
        }
        /*try {
            HSModuloDAO dao = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
            List<ClModulo> modulos = dao.seleccionarModulos(this.parametroArea, "");

            parametroModulos = new SelectItem[modulos.size() + 1];
            for (int i = 0; i < modulos.size(); i++) {
                parametroModulos[i + 1] = new SelectItem(modulos.get(i).getModId(), modulos.get(i).getModDescripcion());
            }
        } catch (Exception e) {
            parametroModulos = new SelectItem[1];
        } finally {
            parametroModulos[0] = new SelectItem(0, "[Seleccione]");
        }*/
    }

    public SelectItem[] getParametroModulos() {
        ver_modulos();
        return parametroModulos;
    }

    public void setParametroModulos(SelectItem[] parametroModulos) {
        this.parametroModulos = parametroModulos;
    }

    public int getB_alu_id() {
        return b_alu_id;
    }

    public void setB_alu_id(int b_alu_id) {
        this.b_alu_id = b_alu_id;
    }

    public String getB_alumno() {
        this.setB_alumno(this.getB_paterno() + " " + this.getB_materno() + ", " + this.getB_nombre());
        return b_alumno;
    }

    public void setB_alumno(String b_alumno) {
        this.b_alumno = b_alumno;
    }

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo(String b_codigo) {
        this.b_codigo = b_codigo;
    }

    public String getB_materno() {
        return b_materno;
    }

    public void setB_materno(String b_materno) {
        this.b_materno = b_materno;
    }

    public String getB_nombre() {
        return b_nombre;
    }

    public void setB_nombre(String b_nombre) {
        this.b_nombre = b_nombre;
    }

    public String getB_paterno() {
        return b_paterno;
    }

    public void setB_paterno(String b_paterno) {
        this.b_paterno = b_paterno;
    }

    public String getB_sub_concepto() {
        return b_sub_concepto;
    }

    public void setB_sub_concepto(String b_sub_concepto) {
        this.b_sub_concepto = b_sub_concepto;
    }

    public float getB_sub_monto() {
        return b_sub_monto;
    }

    public void setB_sub_monto(float b_sub_monto) {
        this.b_sub_monto = b_sub_monto;
    }

    public int getB_sub_alutar_id() {
        return b_sub_alutar_id;
    }

    public void setB_sub_alutar_id(int b_sub_alutar_id) {
        this.b_sub_alutar_id = b_sub_alutar_id;
    }

    public String getB_sub_alutar_estado() {
        return b_sub_alutar_estado;
    }

    public void setB_sub_alutar_estado(String b_sub_alutar_estado) {
        this.b_sub_alutar_estado = b_sub_alutar_estado;
    }

    public Date getB_sub_fecha_pago() {
        return b_sub_fecha_pago;
    }

    public void setB_sub_fecha_pago(Date b_sub_fecha_pago) {
        this.b_sub_fecha_pago = b_sub_fecha_pago;
    }

    public Date getB_sub_fecha_prorroga() {
        return b_sub_fecha_prorroga;
    }

    public void setB_sub_fecha_prorroga(Date b_sub_fecha_prorroga) {
        this.b_sub_fecha_prorroga = b_sub_fecha_prorroga;
    }

    public String getB_sub_img_candado() {
        return b_sub_img_candado;
    }

    public void setB_sub_img_candado(String b_sub_img_candado) {
        this.b_sub_img_candado = b_sub_img_candado;
    }

    public String getB_sub_edit_disabled() {
        return b_sub_edit_disabled;
    }

    public void setB_sub_edit_disabled(String b_sub_edit_disabled) {
        this.b_sub_edit_disabled = b_sub_edit_disabled;
    }

    public String getB_sub_img_klipper() {
        return b_sub_img_klipper;
    }

    public void setB_sub_img_klipper(String b_sub_img_klipper) {
        this.b_sub_img_klipper = b_sub_img_klipper;
    }

    public String getB_sub_img_calendar() {
        return b_sub_img_calendar;
    }

    public void setB_sub_img_calendar(String b_sub_img_calendar) {
        this.b_sub_img_calendar = b_sub_img_calendar;
    }

    public String getB_sub_title() {
        return b_sub_title;
    }

    public void setB_sub_title(String b_sub_title) {
        this.b_sub_title = b_sub_title;
    }

    public String getB_sub_msj() {
        return b_sub_msj;
    }

    public void setB_sub_msj(String b_sub_msj) {
        this.b_sub_msj = b_sub_msj;
    }

    public List<Integer> getB_alutarIds() {
        return b_alutarIds;
    }

    public void setB_alutarIds(List<Integer> b_alutarIds) {
        this.b_alutarIds = b_alutarIds;
    }

    public List<bEdicionEstructPago> getListaEstructura() {
        return listaEstructura;
    }

    public void setListaEstructura(List<bEdicionEstructPago> listaEstructura) {
        this.listaEstructura = listaEstructura;
    }

    public List<bEdicionEstructPago> getSubListaEstructura() {
        return subListaEstructura;
    }

    public void setSubListaEstructura(List<bEdicionEstructPago> subListaEstructura) {
        this.subListaEstructura = subListaEstructura;
    }

    public float getModal_monto() {
        return modal_monto;
    }

    public void setModal_monto(float modal_monto) {
        this.modal_monto = modal_monto;
    }

    public int getTmpAlutar() {
        return tmpAlutar;
    }

    public void setTmpAlutar(int tmpAlutar) {
        this.tmpAlutar = tmpAlutar;
    }

    public int getTmpCompBloq() {
        return tmpCompBloq;
    }

    public void setTmpCompBloq(int tmpCompBloq) {
        this.tmpCompBloq = tmpCompBloq;
    }

    public int getTmpEstado() {
        return tmpEstado;
    }

    public void setTmpEstado(int tmpEstado) {
        this.tmpEstado = tmpEstado;
    }

    public Date getModal_pago() {
        return modal_pago;
    }

    public void setModal_pago(Date modal_pago) {
        this.modal_pago = modal_pago;
    }

    public Date getModal_prorroga() {
        return modal_prorroga;
    }

    public void setModal_prorroga(Date modal_prorroga) {
        this.modal_prorroga = modal_prorroga;
    }

    public SelectItem[] getModalPlantillas() {
        this.parametrosPlantilla();
        return modalPlantillas;
    }

    public void parametrosPlantilla() {
        try {
            HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoCLEstructuraPago");
            List<ClEstructuraPagos> l = dao.seleccionarEstructurasXModulo(this.getModalModulo());
            modalPlantillas = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                ClEstructuraPagos tmp = l.get(i);
                modalPlantillas[i + 1] = new SelectItem(tmp.getEstpagId(), tmp.getEstpagNombre());
            }
        } catch (Exception e) {
            modalPlantillas = new SelectItem[1];
        } finally {
            modalPlantillas[0] = new SelectItem(0, "[Seleccione]");
        }
    }

    public void setModalPlantillas(SelectItem[] modalPlantillas) {
        this.modalPlantillas = modalPlantillas;
    }

    public int getModalModulo() {
        return modalModulo;
    }

    public void setModalModulo(int modalModulo) {
        this.modalModulo = modalModulo;
    }

    public int getModalArea() {
        return modalArea;
    }

    public void setModalArea(int modalArea) {
        this.modalArea = modalArea;
    }

    public int getModalPlantilla() {
        return modalPlantilla;
    }

    public void setModalPlantilla(int modalPlantilla) {
        this.modalPlantilla = modalPlantilla;
    }

    public String getModalTaller() {
        return modalTaller;
    }

    public void setModalTaller(String modalTaller) {
        this.modalTaller = modalTaller;
    }

    public Vector<SelectItem> getModalTalleres() {
        this.parametrosTaller();
        return modalTalleres;
    }

    public void setModalTalleres(Vector<SelectItem> modalTalleres) {
        this.modalTalleres = modalTalleres;
    }

    public void parametrosTaller() {
        try {
            HSEstructuraPagoDAO daoEstPag = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoCLEstructuraPago");
            HSArbolAperturadoClDAO daoTalape = CommonDAO.getClArbolAperturadoClDAO();

            List<ClEstructuraPagosDetalle> lEstruc = daoEstPag.listaEstructurasExistentes(this.getModalModulo(), this.getModalPlantilla(), -1);

            modalTalleres = new Vector<SelectItem>();
            for (int i = 0; i < lEstruc.size(); i++) {
                ClEstructuraPagosDetalle tmp = lEstruc.get(i);
                List<ClArbolAperturado> talapes = daoTalape.seleccionarArbTalleresAperturadosPorTaller(tmp.getTalId());
                for (ClArbolAperturado talape : talapes) {
                    modalTalleres.addElement(new SelectItem(talape.getClArbolAcademico().getArbId(), talape.getArbapeId() + " - " + talape.getClArbolAcademico().getArbDescripcion()));
                }
            }
        } catch (Exception e) {
            modalTalleres = new Vector<SelectItem>(1);
        } finally {
            modalTalleres.add(0, new SelectItem(0, "[Seleccione]"));
        }
    }

    public int getModalMatricula() {
        return modalMatricula;
    }

    public void setModalMatricula(int modalMatricula) {
        this.modalMatricula = modalMatricula;
    }

    public void parametrosMatricula() {
        try {
            HSMatriculaCLDAO daoMat = (HSMatriculaCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLMatricula");
            List<ClMatricula> l = daoMat.listarMatriculado_Taller(this.getModal_id(), Integer.parseInt(this.getModalTaller()));
            modalMatriculas = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                ClMatricula mat = l.get(i);
                modalMatriculas[i + 1] = new SelectItem(mat.getMatId(), mat.getMatCodigo().concat(" " + String.valueOf(mat.getMatFecha())));
            }
        } catch (Exception e) {
            modalMatriculas = new SelectItem[1];
        } finally {
            modalMatriculas[0] = new SelectItem(0, "[Seleccione]");
        }
    }

    public SelectItem[] getModalMatriculas() {
        this.parametrosMatricula();
        return modalMatriculas;
    }

    public void setModalMatriculas(SelectItem[] modalMatriculas) {
        this.modalMatriculas = modalMatriculas;
    }

    public String getModal_alumno() {
        return modal_alumno;
    }

    public void setModal_alumno(String modal_alumno) {
        this.modal_alumno = modal_alumno;
    }

    public int getModal_id() {
        return modal_id;
    }

    public void setModal_id(int modal_id) {
        this.modal_id = modal_id;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getImagenVer() {
        return imagenVer;
    }

    public void setImagenVer(String imagenVer) {
        this.imagenVer = imagenVer;
    }

    public String getEstadoVer() {
        return estadoVer;
    }

    public void setEstadoVer(String estadoVer) {
        this.estadoVer = estadoVer;
    }

    public String getSugAlumno() {
        return sugAlumno;
    }

    public void setSugAlumno(String sugAlumno) {
        this.sugAlumno = sugAlumno;
    }

    public String getSugCodigo() {
        return sugCodigo;
    }

    public void setSugCodigo(String sugCodigo) {
        this.sugCodigo = sugCodigo;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public void nuevo() {
        this.setModalArea(0);
        this.setModalModulo(0);
        this.setModalPlantilla(0);
        this.setModal_id(0);
        this.setModal_alumno("");
        this.setOncomplete("");
        this.setSugAlumno("");
        this.setTmpAlutar(0);
        this.setTmpEstado(0);
    }

    public void validarBusqueda() {
        this.setOncomplete("");
        if (this.getB_codigo().trim().length() == 0 && this.getB_paterno().trim().length() == 0
                && this.getB_materno().trim().length() == 0 && this.getB_nombre().trim().length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese algun parametro para la busqueda');");
        } else if (this.getParametroModulo() == 0) {
            this.setOncomplete("javascript:alert('Seleccione algun modulo');");
        } else {
            realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                    this.getB_materno(), this.getB_nombre(), this.getParametroModulo());
        }
    }

    /*private String nomModulo(int mod_id) {
        try {
            HSModuloDAO daoMod = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");

            ClModulo mod = daoMod.buscarModulo(mod_id);
            if (mod == null) {
                return "---";
            } else {
                return mod.getModDescripcion();
            }
        } catch (Exception e) {
            return null;
        }
    }*/

    public void realizarBusqueda(String code, String paterno, String materno, String nombre, int modulo) {
        try {
            System.out.println("ingreso a realizar busqueda");
            this.setListaEstructura(null);
            HSAlumnoCLDAO dao = (HSAlumnoCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumno");

            //alumnosListaXmodulo
            List<ClAlumno> l = dao.listaAlumnos_Datos(code, paterno, materno, nombre, modulo);
            List<bEdicionEstructPago> estructuras = new ArrayList<bEdicionEstructPago>();
            for (int i = 0; i < l.size();) {
                ClAlumno alumno = l.get(i);

                bEdicionEstructPago tmpMantEstPag = new bEdicionEstructPago();
                tmpMantEstPag.setB_alu_id(alumno.getAluId());
                tmpMantEstPag.setB_codigo(alumno.getAluCodigo());
                tmpMantEstPag.setB_paterno(alumno.getAluAppaterno());
                tmpMantEstPag.setB_materno(alumno.getAluApmaterno());
                tmpMantEstPag.setB_nombre(alumno.getAluNombres());
                //tmpMantEstPag.setB_modulo(this.nomModulo(modulo));

                tmpMantEstPag.setImagenVer("/Imagenes/actions/down.png");
                tmpMantEstPag.setEstadoVer("0");

                List<ClAlumnoTarifa> lTarifas = dao.listaAlumnoTarifa(alumno.getAluId(), modulo);
                List<bEdicionEstructPago> subEstructuras = new ArrayList<bEdicionEstructPago>();
                List<Integer> vAtids = new ArrayList<Integer>();

                int j = 0;

                for (; j < lTarifas.size(); j++) {
                    ClAlumnoTarifa alumnoTarifa = lTarifas.get(j);

                    bEdicionEstructPago subTmp = new bEdicionEstructPago();
                    subTmp.setB_sub_alutar_id(alumnoTarifa.getAlutarId());
                    subTmp.setB_sub_alutar_estado(alumnoTarifa.getAlutarEstado());
                    subTmp.setB_sub_fecha_pago(alumnoTarifa.getAlutarFechaPago());
                    subTmp.setB_sub_fecha_prorroga(alumnoTarifa.getAlutarFechaProrroga());
                    subTmp.setB_sub_monto(alumnoTarifa.getAlutarMonto());
                    subTmp.setB_sub_concepto(alumnoTarifa.getClEstructuraPagosDetalle().getEstpagdetNombre());

                    if (alumnoTarifa.getAlutarEstado().equals("2")) {
                        subTmp.setB_sub_img_candado("/Imagenes/actions/cerrado.png");
                        subTmp.setB_sub_title("Abrir pago");
                        subTmp.setB_sub_msj("de abrir el candado?");
                        subTmp.setB_sub_edit_disabled("true");
                        subTmp.setB_sub_img_klipper("/Imagenes/actions/klipper_dock_gris.png");
                        subTmp.setB_sub_img_calendar("/Imagenes/actions/calendar_gris.png");
                    } else {
                        subTmp.setB_sub_img_candado("/Imagenes/actions/abierto.png");
                        subTmp.setB_sub_title("Cerrar pago");
                        subTmp.setB_sub_msj("cerrar el candado? la siguiente accion se realizara bajo su responsabilidad?");
                        subTmp.setB_sub_edit_disabled("false");
                        subTmp.setB_sub_img_klipper("/Imagenes/actions/klipper_dock.png");
                        subTmp.setB_sub_img_calendar("/Imagenes/actions/calendar.gif");
                    }
                    subEstructuras.add(subTmp);
                    vAtids.add(alumnoTarifa.getAlutarId());
                }
                tmpMantEstPag.setSubListaEstructura(subEstructuras);
                tmpMantEstPag.setB_alutarIds(vAtids);
                estructuras.add(tmpMantEstPag);
                i += j;
            }
            this.setListaEstructura(new ArrayList<bEdicionEstructPago>(estructuras));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvaEditar(ActionEvent event) {
        Object alutar_monto = ((UIParameter) event.getComponent().findComponent("alutar_monto")).getValue();
        Object alutar_pago = ((UIParameter) event.getComponent().findComponent("alutar_pago")).getValue();
        Object alutar_prorroga = ((UIParameter) event.getComponent().findComponent("alutar_prorroga")).getValue();
        Object alutar_id = ((UIParameter) event.getComponent().findComponent("alutar_id")).getValue();

        this.setModal_monto(Float.parseFloat(String.valueOf(alutar_monto)));
        this.setModal_pago((Date) alutar_pago);
        this.setModal_prorroga((Date) alutar_prorroga);
        this.setTmpAlutar(Integer.parseInt(String.valueOf(alutar_id)));
    }

    public void editar() {
        this.setOncomplete("");
        try {
            HSAlumnoTarifaCLDAO dao = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumnoTarifa");
            dao.actualizarDatosAlumnoTarifa(this.getTmpAlutar(), this.getModal_monto(),
                    this.getModal_pago(), this.getModal_prorroga());

            realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                    this.getB_materno(), this.getB_nombre(), this.getParametroModulo());

            this.setOncomplete("javascript:alert('SE ACTUALIZO CORRECTAMENTE LOS DATOS');"
                    + "Richfaces.hideModalPanel('mpEditar')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void elimParam(ActionEvent event) {
        UIParameter estpag_alutarIds = (UIParameter) event.getComponent().findComponent("estpag_alutarIds");
        List<Integer> vAlutarIds = (List<Integer>) estpag_alutarIds.getValue();

        this.setB_alutarIds(vAlutarIds);
    }

    public void eliminarEstructura() {
        try {
            this.setOncomplete("");
            HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");
            List<Integer> vAlutarIds = this.getB_alutarIds();
            //Suma los montos para un alutarId de AdPago
            float montoTotal = 0;
            for (int i = 0; i < vAlutarIds.size(); i++) {
                List<AdPago> ltmp = daoPago.listaPagosAlumno(vAlutarIds.get(i), 1);

                for (int j = 0; j < ltmp.size(); j++) {
                    montoTotal += ltmp.get(j).getPagMonto();
                }
            }
            if (montoTotal == 0) {
                HSAlumnoTarifaCLDAO dao = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumnoTarifa");
                dao.eliminarAlumnosTarifa(vAlutarIds);

                realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                        this.getB_materno(), this.getB_nombre(), this.getParametroModulo());

                this.setOncomplete("javascript:alert('Se eliminaron las estructuras, no habia pagos registrados');"
                        + "Richfaces.hideModalPanel('mpEliminar')");
            } else {
                this.setOncomplete("javascript:alert('No se puede eliminar, hay pagos realizados');"
                        + "Richfaces.hideModalPanel('mpEliminar')");
            }
        } catch (Exception e) {
        }
    }

    public void mostrarDetalle(ActionEvent event) {
        UIParameter ui = (UIParameter) event.getComponent().findComponent("p_ver");
        String p_ver = String.valueOf(ui.getValue());
        if (p_ver.equals("0")) {
            this.setMostrar(true);
            this.setEstadoVer("1");
            this.setImagenVer("/Imagenes/actions/up.png");
        } else {
            this.setMostrar(false);
            this.setEstadoVer("0");
            this.setImagenVer("/Imagenes/actions/down.png");
        }
    }

    public void salvaParam(ActionEvent event) {
        UIParameter mensaje = (UIParameter) event.getComponent().findComponent("mensaje");
        UIParameter alutarId = (UIParameter) event.getComponent().findComponent("alutar_id");
        UIParameter alutarEstado = (UIParameter) event.getComponent().findComponent("alutar_estado");

        this.setTmpAlutar(Integer.parseInt(String.valueOf(alutarId.getValue())));
        this.setTmpEstado(Integer.parseInt(String.valueOf(alutarEstado.getValue())));
        this.setB_sub_msj(String.valueOf(mensaje.getValue()));
    }

    public void actualizarEstado() {
        this.setOncomplete("");

        HSPagoDAO daoPago = (HSPagoDAO) ServiceFinder.findBean("SpringHibernateDaoPago");

        /**
         * Monto de AdPago, lo que ha cancelado
         */
        List<AdPago> ltmp = daoPago.listaPagosAlumno(this.getTmpAlutar(), 0);
        float montoTotal = 0;
        for (AdPago pag : ltmp) {
            montoTotal += pag.getPagMonto();
        }
        String mensaje = "";
        if (this.getTmpEstado() == 2) {
            if (montoTotal == 0) {
                daoPago.actualizarEstadoPago(this.getTmpAlutar(), "0");
            } else {
                daoPago.actualizarEstadoPago(this.getTmpAlutar(), "1");
            }
            mensaje = "Se abre el candado";
        } else {
            //Monto que debe pagar, el que se genera en la Estructura
            ClAlumnoTarifa tAlutar = daoPago.getClAlumnoTarifa(this.getTmpAlutar());
            float montoExiste = tAlutar.getAlutarMonto();
            if (montoTotal >= montoExiste) {
                mensaje = "se cerrara el candado, tiene los pagos al dia";
                daoPago.actualizarEstadoPago(this.getTmpAlutar(), "2");

                HSMatriculaCLDAO daoMatricula = (HSMatriculaCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLMatricula");
                daoMatricula.actualizarEstadoMatricula("022001", tAlutar.getMatId());
            } else if (montoTotal > 0 && montoTotal < montoExiste) {
                mensaje = "Aun presenta deudas";
                daoPago.actualizarEstadoPago(this.getTmpAlutar(), "1");
            } else {
                mensaje = "No se puede cerrar el candado, presenta deudas";
            }
        }
        realizarBusqueda(this.getB_codigo(), this.getB_paterno(),
                this.getB_materno(), this.getB_nombre(), this.getParametroModulo());

        this.setOncomplete("javascript:alert('" + mensaje + "');"
                + "Richfaces.hideModalPanel('mpCandado');");
    }

    public List<bEdicionEstructPago> autocomplete(Object suggest) {
        String ref = String.valueOf(suggest);
        List<bEdicionEstructPago> l = new ArrayList<bEdicionEstructPago>();

        try {
            HSAlumnoCLDAO dao = (HSAlumnoCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumno");

            List<ClAlumno> alumnos = dao.seleccionarAlumnosPorApellidos(ref);
            for (int i = 0; i < alumnos.size(); i++) {
                ClAlumno alu = alumnos.get(i);
                bEdicionEstructPago tmp = new bEdicionEstructPago();
                tmp.setB_alu_id(alu.getAluId());
                tmp.setB_codigo(alu.getAluCodigo());
                tmp.setB_paterno(alu.getAluAppaterno());
                tmp.setB_materno(alu.getAluApmaterno());
                tmp.setB_nombre(alu.getAluNombres());
                l.add(tmp);
            }
        } catch (Exception e) {
        }
        return l;
    }

    public void validarEstructuras(ActionEvent event) {
        this.setOncomplete("");
        int aluId = this.getModal_id();
        int modId = this.getModalModulo();
        int estpagId = this.getModalPlantilla();
        int talId = Integer.parseInt(this.getModalTaller());
        int matId = this.getModalMatricula();
        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLSeccion");
        int secId = daoSec.obtenerSeccion(matId, talId).getSecId().intValue();
        
        if (aluId == 0) {
            this.setOncomplete("javascript:alert('SELECCIONE UN ALUMNO');");
        } else if (modId == 0) {
            this.setOncomplete("javascript:alert('SELECCIONE UN MODULO');");
        } else if (estpagId == 0) {
            this.setOncomplete("javascript:alert('SELECCIONE UNA PLANTILLA');");
        } else if (talId == 0) {
            this.setOncomplete("javascript:alert('SELECCIONE UN TALLER');");
        } else if (matId == 0) {
            this.setOncomplete("javascript:alert('SELECCIONE UNA MATRICULA DEL ALUMNO');");
        } else if (secId == 0) {
            this.setOncomplete("javascript:alert('NO SE ENCUENTRA SECCION');");
        } else {
            try {
                HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO) ServiceFinder.findBean("SpringHibernateDaoCLEstructuraPago");
                List<ClEstructuraPagosDetalle> lEstruc = dao.listaEstructurasExistentes(modId, estpagId, talId);
                List<ClAlumnoTarifa> lAlutar = dao.listaAlumnoTarifaExistentes(aluId, modId, estpagId);

                int iEstruc = lEstruc.size();
                int iAlutar = lAlutar.size();

                if (iEstruc == iAlutar) {
                    this.setOncomplete("javascript:alert('ESTAN COMPLETAS LAS ESTRUCTURAS');"
                            + "Richfaces.hideModalPanel('mpEstructuraNuevo')");
                } else {
                    this.generarEstructuras(lEstruc, lAlutar, matId, secId);
                }
            } catch (Exception e) {
            }
        }
    }

    private void generarEstructuras(List<ClEstructuraPagosDetalle> lEstpag, List<ClAlumnoTarifa> lAlutar, int matId, int secId) throws Exception {
        this.setOncomplete("");
        HSAlumnoTarifaCLDAO dao = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumnoTarifa");
        if (lAlutar.size() == 0) {
            for (int i = 0; i < lEstpag.size(); i++) {
                ClEstructuraPagosDetalle estpagdet = lEstpag.get(i);

                ClAlumnoTarifa alutar = new ClAlumnoTarifa();

                alutar.setAlutarMonto(estpagdet.getEstpagdetMonto());
                alutar.setAlutarFechaPago(estpagdet.getEstpagdetFechaPago());
                alutar.setAlutarFechaProrroga(estpagdet.getEstpagdetFechaPago());
                alutar.setAlutarActivo("1");
                alutar.setAlutarEstado("0");
                alutar.setAlutarAluTipo("014003");
                alutar.setMatId(matId);
                alutar.setSecId(secId);
                alutar.setConpagId(estpagdet.getAdConceptoPago().getId());
                alutar.setClEstructuraPagosDetalle(estpagdet);
                alutar.setClAlumno(new ClAlumno(this.getModal_id()));

                dao.generarAlumnoTarifa(alutar);
            }
        } else {
            for (int i = 0; i < lEstpag.size(); i++) {
                ClEstructuraPagosDetalle estpagdet = lEstpag.get(i);
                boolean encontrado = false;
                int j;
                for (j = 0; j < lAlutar.size() && !encontrado; j++) {
                    ClAlumnoTarifa alutar = lAlutar.get(j);

                    int iat = alutar.getClEstructuraPagosDetalle().getEstpagdetId();
                    int iep = estpagdet.getEstpagdetId();

                    if (iat == iep) {
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    ClAlumnoTarifa alutar = new ClAlumnoTarifa();

                    alutar.setAlutarMonto(estpagdet.getEstpagdetMonto());
                    alutar.setAlutarFechaPago(estpagdet.getEstpagdetFechaPago());
                    alutar.setAlutarFechaProrroga(estpagdet.getEstpagdetFechaPago());
                    alutar.setAlutarActivo("1");
                    alutar.setAlutarEstado("0");
                    alutar.setAlutarAluTipo("014003");
                    alutar.setMatId(matId);
                    alutar.setSecId(secId);
                    alutar.setConpagId(estpagdet.getAdConceptoPago().getId());
                    alutar.setClEstructuraPagosDetalle(estpagdet);
                    ClAlumno alu = new ClAlumno();
                    alu.setAluId(this.getModal_id());
                    alutar.setClAlumno(alu);

                    dao.generarAlumnoTarifa(alutar);
                }
            }
        }

        this.setOncomplete("javascript:alert('SE GENERARON CORRECTAMENTE LAS ESTRUCTURAS');"
                + "Richfaces.hideModalPanel('mpEstructuraNuevo')");
    }
}
