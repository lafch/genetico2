/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.util.ObjUpdate;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoTarifaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAperturadoClDAO;
import net.uch.mapping.ClArbolAperturado;
import net.uch.mapping.ClSeccion;
import net.uch.util.CommonDAO;

/**
 *
 * @author cesardl
 */
public class bEdicionEstructPagoBloque {

    private int p_sec;
    private int p_talape;
    private SelectItem[] p_secs;
    private SelectItem[] p_talapes;
    private SelectItem[] p_filtros;
    private SelectItem[] p_modifs;
    private String[] p_filtro;
    private String[] p_modif;
    private Date f_fecha_pago;
    private Date f_fecha_prorroga;
    private String f_monto;
    private String[] f_disable = new String[3];
    private Date m_fecha_pago;
    private Date m_fecha_prorroga;
    private String m_monto;
    private String[] m_disable = new String[3];
    private String resultado;
    private String oncomplete;

    public int getP_talape() {
        return p_talape;
    }

    public void setP_talape(int p_talape) {
        this.p_talape = p_talape;
    }

    public SelectItem[] getP_talapes() {
        try {
            HSArbolAperturadoClDAO arbApeClDAO = CommonDAO.getClArbolAperturadoClDAO();
            List<ClArbolAperturado> l = arbApeClDAO.seleccionarArbTalleresAperturados();
            p_talapes = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                ClArbolAperturado talape = l.get(i);
                p_talapes[i + 1] = new SelectItem(talape.getArbapeId(), talape.getArbapeDescripcion());
            }
        } catch (Exception e) {
            p_talapes = new SelectItem[1];
        } finally {
            p_talapes[0] = new SelectItem(0, "[Seleccione]");
        }
        return p_talapes;
    }

    public void setP_talapes(SelectItem[] p_talapes) {
        this.p_talapes = p_talapes;
    }

    public void parametroSeccion() {
        try {
            HSSeccionCLDAO dao = (HSSeccionCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLSeccion");
            List<ClSeccion> l = dao.seleccionarSecciones(this.getP_talape());
            p_secs = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                ClSeccion sec = l.get(i);
                p_secs[i + 1] = new SelectItem(sec.getSecId(), sec.getSecNombre());
            }
        } catch (Exception e) {
            p_secs = new SelectItem[1];
        } finally {
            p_secs[0] = new SelectItem(0, "[Seleccione]");
        }
    }

    public int getP_sec() {
        return p_sec;
    }

    public void setP_sec(int p_sec) {
        this.p_sec = p_sec;
    }

    public SelectItem[] getP_secs() {
        parametroSeccion();
        return p_secs;
    }

    public void setP_secs(SelectItem[] p_secs) {
        this.p_secs = p_secs;
    }

    public String[] getF_disable() {
        return f_disable;
    }

    public void setF_disable(String[] f_disable) {
        this.f_disable = f_disable;
    }

    public String[] getM_disable() {
        return m_disable;
    }

    public void setM_disable(String[] m_disable) {
        this.m_disable = m_disable;
    }

    public String[] getP_filtro() {
        return p_filtro;
    }

    public void setP_filtro(String[] p_filtro) {
        this.p_filtro = p_filtro;
    }

    public String[] getP_modif() {
        return p_modif;
    }

    public void setP_modif(String[] p_modif) {
        this.p_modif = p_modif;
    }

    public SelectItem[] getP_filtros() {
        p_filtros = new SelectItem[3];
        p_filtros[0] = new SelectItem("AlutarFechaPago", "Fecha de Pago");
        p_filtros[1] = new SelectItem("AlutarFechaProrroga", "Fecha de Prorroga");
        p_filtros[2] = new SelectItem("AlutarMonto", "Monto");
        return p_filtros;
    }

    public void setP_filtros(SelectItem[] p_filtros) {
        this.p_filtros = p_filtros;
    }

    public SelectItem[] getP_modifs() {
        p_modifs = new SelectItem[3];
        p_modifs[0] = new SelectItem("AlutarFechaPago", "Fecha de Pago");
        p_modifs[1] = new SelectItem("AlutarFechaProrroga", "Fecha de Prorroga");
        p_modifs[2] = new SelectItem("AlutarMonto", "Monto");
        return p_modifs;
    }

    public void setP_modifs(SelectItem[] p_modifs) {
        this.p_modifs = p_modifs;
    }

    public String getSeccion() {
        for (int i = 0; i < p_secs.length; i++) {
            SelectItem si = p_secs[i];
            if (Integer.parseInt(String.valueOf(si.getValue())) == this.getP_sec()) {
                return si.getLabel();
            }
        }
        return "[Seleccione]";
    }

    public String getTallerAperturado() {
        for (int i = 0; i < p_talapes.length; i++) {
            SelectItem si = p_talapes[i];
            if (Integer.parseInt(String.valueOf(si.getValue())) == this.getP_talape()) {
                return si.getLabel();
            }
        }
        return "[Seleccione]";
    }

    public Date getF_fecha_pago() {
        return f_fecha_pago;
    }

    public void setF_fecha_pago(Date f_fecha_pago) {
        this.f_fecha_pago = f_fecha_pago;
    }

    public Date getF_fecha_prorroga() {
        return f_fecha_prorroga;
    }

    public void setF_fecha_prorroga(Date f_fecha_prorroga) {
        this.f_fecha_prorroga = f_fecha_prorroga;
    }

    public String getF_monto() {
        return f_monto;
    }

    public void setF_monto(String f_monto) {
        this.f_monto = f_monto;
    }

    public Date getM_fecha_pago() {
        return m_fecha_pago;
    }

    public void setM_fecha_pago(Date m_fecha_pago) {
        this.m_fecha_pago = m_fecha_pago;
    }

    public Date getM_fecha_prorroga() {
        return m_fecha_prorroga;
    }

    public void setM_fecha_prorroga(Date m_fecha_prorroga) {
        this.m_fecha_prorroga = m_fecha_prorroga;
    }

    public String getM_monto() {
        return m_monto;
    }

    public void setM_monto(String m_monto) {
        this.m_monto = m_monto;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public void buscar() {
        if (p_talape == 0) {
            this.setOncomplete("javascript:alert('Debe seleccionar un taller aperturado');");
        } else if (p_sec == 0) {
            this.setOncomplete("javascript:alert('Debe seleccionar una seccion');");
        } else if (p_filtro.length == 0) {
            this.setOncomplete("javascript:alert('Debe seleccionar algun campo para buscar');");
        } else if (p_modif.length == 0) {
            this.setOncomplete("javascript:alert('Debe seleccionar algun campo para actualizar');");
        } else {
            this.setF_fecha_pago(null);
            this.setF_fecha_prorroga(null);
            this.setF_monto("");
            this.setM_fecha_pago(null);
            this.setM_fecha_prorroga(null);
            this.setM_monto("");
            this.setOncomplete("Richfaces.showModalPanel('mpBloque',{width:600, top:100})");

            for (int k = 0; k < f_disable.length; k++) {
                f_disable[k] = "true";
            }
            for (int i = 0; i < p_filtros.length; i++) {
                String si = String.valueOf(p_filtros[i].getValue());
                boolean existe = false;
                int j = 0;
                int pos = 0;
                while (j < p_filtro.length && !existe) {
                    String str = p_filtro[j];
                    if (si.equalsIgnoreCase(str)) {
                        existe = true;
                        j = p_filtro.length;
                        pos = i;
                    }
                    j++;
                }
                if (existe) {
                    f_disable[pos] = "false";
                }
            }

            for (int k = 0; k < m_disable.length; k++) {
                m_disable[k] = "true";
            }
            for (int i = 0; i < p_modifs.length; i++) {
                String si = String.valueOf(p_modifs[i].getValue());
                boolean existe = false;
                int j = 0;
                int pos = 0;
                while (j < p_modif.length && !existe) {
                    String str = p_modif[j];
                    if (si.equalsIgnoreCase(str)) {
                        existe = true;
                        j = p_modif.length;
                        pos = i;
                    }
                    j++;
                }
                if (existe) {
                    m_disable[pos] = "false";
                }
            }
        }
    }

    public void validarActualizacion() {
        //0 pago - 1 prorroga - 2 mont
        boolean error = false;
        for (int i = 0; i < f_disable.length; i++) {
            String s = f_disable[i];
            if (s.equalsIgnoreCase("false")) {
                switch (i) {
                    case 0:
                        if (this.getF_fecha_pago() == null) {
                            error = true;
                        }
                        break;
                    case 1:
                        if (this.getF_fecha_prorroga() == null) {
                            error = true;
                        }
                        break;
                    case 2:
                        if (this.aFloat(this.getF_monto()) == -1) {
                            error = true;
                        }
                        break;
                }
            }
        }
        for (int i = 0; i < m_disable.length; i++) {
            String disable = m_disable[i];
            if (disable.equalsIgnoreCase("false")) {
                switch (i) {
                    case 0:
                        if (this.getM_fecha_pago() == null) {
                            error = true;
                        }
                        break;
                    case 1:
                        if (this.getM_fecha_prorroga() == null) {
                            error = true;
                        }
                        break;
                    case 2:
                        if (this.aFloat(this.getM_monto()) == -1) {
                            error = true;
                        }
                        break;
                }
            }
        }
        if (!error) {
            System.out.println("no hay errores, se actualizara");
            actualizarEstructuras();
        } else {
            System.out.println("error!");
        }
    }

    public void actualizarEstructuras() {
        List<ObjUpdate> p_restricciones = new ArrayList<ObjUpdate>();
        if (this.getF_monto() != null) {
            if (this.getF_monto().trim().length() != 0) {
                p_restricciones.add(new ObjUpdate("cl_alumno_tarifa.alutar_monto", aFloat(this.getF_monto())));
            }
        }
        if (this.getF_fecha_pago() != null) {
            p_restricciones.add(new ObjUpdate("cl_alumno_tarifa.alutar_fecha_pago", aDate(this.getF_fecha_pago())));
        }
        if (this.getF_fecha_prorroga() != null) {
            p_restricciones.add(new ObjUpdate("cl_alumno_tarifa.alutar_fecha_prorroga", aDate(this.getF_fecha_prorroga())));
        }

        List<ObjUpdate> p_valores = new ArrayList<ObjUpdate>();
        if (this.getM_monto() != null) {
            if (this.getM_monto().trim().length() != 0) {
                p_valores.add(new ObjUpdate("cl_alumno_tarifa.alutar_monto", aFloat(this.getM_monto())));
            }
        }
        if (this.getM_fecha_pago() != null) {
            p_valores.add(new ObjUpdate("cl_alumno_tarifa.alutar_fecha_pago", aDate(this.getM_fecha_pago())));
        }
        if (this.getM_fecha_prorroga() != null) {
            p_valores.add(new ObjUpdate("cl_alumno_tarifa.alutar_fecha_prorroga", aDate(this.getM_fecha_prorroga())));
        }

        try {
            HSAlumnoTarifaCLDAO dao = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean("SpringHibernateDaoCLAlumnoTarifa");
            int registros = dao.actualizarEstructurasPagoBloque(this.getP_sec(), p_restricciones, p_valores);

            System.out.println("hay en total " + registros);
            if (registros == 0) {
                this.setResultado("No se han actualizado registros.");
            } else {
                this.setResultado("Se han actualizado " + registros + " registros de estudiantes"
                        + "<br>Taller aperturado: " + getTallerAperturado()
                        + "<br>Seccion: " + getSeccion());
            }
            this.setOncomplete("Richfaces.hideModalPanel('mpBloque')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float aFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String aDate(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return "'" + sdf.format(date) + "'";
    }
}
