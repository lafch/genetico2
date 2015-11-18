/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.administrativa.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.administrativa.hibernateSpringDao.HSEdicionEstructPagoDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcSemestre;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.util.ObjUpdate;

/**
 *
 * @author cesardl
 */
public class bEdicionEstructPagoBloque {

    private int p_sem;
    private int p_seming;
    private int p_esp;
    private String p_sems;
    private SelectItem[] p_semings;
    private SelectItem[] p_esps;
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

    public int getP_esp() {
        return p_esp;
    }

    public void setP_esp(int p_esp) {
        this.p_esp = p_esp;
    }

    public SelectItem[] getP_esps() {
        try {
            HSEspecialidadDAO dao = (HSEspecialidadDAO) ServiceFinder.findBean("SpringHibernateDaoEspecialidad");
            List l = dao.seleccionarEspecialidad();
            p_esps = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                AcEspecialidad esp = (AcEspecialidad) l.get(i);
                p_esps[i + 1] = new SelectItem(esp.getId(), esp.getEspNombre());
            }
        } catch (Exception e) {
            p_esps = new SelectItem[1];
        } finally {
            p_esps[0] = new SelectItem(0, "[Seleccione]");
        }
        return p_esps;
    }

    public void setP_esps(SelectItem[] p_esps) {
        this.p_esps = p_esps;
    }

    public int getP_sem() {
        return p_sem;
    }

    public void setP_sem(int p_sem) {
        this.p_sem = p_sem;
    }

    public int getP_seming() {
        return p_seming;
    }

    public void setP_seming(int p_seming) {
        this.p_seming = p_seming;
    }

    public SelectItem[] getP_semings() {
        try {
            HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List l = dao.seleccionarSemestreActivo();
            p_semings = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                AcSemestre sem = (AcSemestre) l.get(i);
                p_semings[i + 1] = new SelectItem(sem.getId(), sem.getSemNombre());
            }
        } catch (Exception e) {
            p_semings = new SelectItem[1];
        } finally {
            p_semings[0] = new SelectItem(0, "[TODOS]");
        }
        return p_semings;
    }

    public void setP_semings(SelectItem[] p_semings) {
        this.p_semings = p_semings;
    }

    public String getP_sems() {
        try {
            HSSemestreDAO dao = (HSSemestreDAO) ServiceFinder.findBean("SpringHibernateDaoSemestre");
            List l = dao.seleccionarSemestreActivo();

            for (int i = 0; i < l.size(); i++) {
                AcSemestre sem = (AcSemestre) l.get(i);
                if (sem.getSemActual().equalsIgnoreCase("1")) {
                    p_sems = sem.getSemNombre();
                    p_sem = sem.getId();
                }
            }
        } catch (Exception e) {
        }
        return p_sems;
    }

    public void setP_sems(String p_sems) {
        this.p_sems = p_sems;
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

    public String getSemestreIngreso() {
        for (int i = 0; i < p_semings.length; i++) {
            SelectItem si = p_semings[i];
            if (Integer.parseInt(String.valueOf(si.getValue())) == this.getP_seming()) {
                return si.getLabel();
            }
        }
        return "[Seleccione]";
    }

    public String getEspecialidad() {
        for (int i = 0; i < p_esps.length; i++) {
            SelectItem si = p_esps[i];
            if (Integer.parseInt(String.valueOf(si.getValue())) == this.getP_esp()) {
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
        if (p_filtro.length == 0) {
            this.setOncomplete("javascript:alert('Debe seleccionar algun campo para buscar');");
        } else if (p_modif.length == 0) {
            this.setOncomplete("javascript:alert('Debe seleccionar algun campo para actualizar');");
        } else if (p_esp == 0) {
            this.setOncomplete("javascript:alert('Debe seleccionar una especialidad');");
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
        //0pago 1prorroga 2mont
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
            String s = m_disable[i];
            if (s.equalsIgnoreCase("false")) {
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
            System.out.println("no hay errro se actualizara");
            actualizarEstructuras();
        } else {
            System.out.println("error!");
        }
    }

    public void actualizarEstructuras() {
        List<ObjUpdate> p_restricciones = new ArrayList<ObjUpdate>();
        if (this.getF_monto() != null) {
            if (this.getF_monto().trim().length() != 0) {
                p_restricciones.add(new ObjUpdate("ad_alumno_tarifa.alutar_monto", aFloat(this.getF_monto())));
            }
        }
        if (this.getF_fecha_pago() != null) {
            p_restricciones.add(new ObjUpdate("ad_alumno_tarifa.alutar_fecha_pago", aDate(this.getF_fecha_pago())));
        }
        if (this.getF_fecha_prorroga() != null) {
            p_restricciones.add(new ObjUpdate("ad_alumno_tarifa.alutar_fecha_prorroga", aDate(this.getF_fecha_prorroga())));
        }
        if (this.getP_seming() != 0) {
            p_restricciones.add(new ObjUpdate("ac_alumno.sem_id", this.getP_seming()));
        }

        List<ObjUpdate> p_valores = new ArrayList<ObjUpdate>();
        if (this.getM_monto() != null) {
            if (this.getM_monto().trim().length() != 0) {
                p_valores.add(new ObjUpdate("ad_alumno_tarifa.alutar_monto", aFloat(this.getM_monto())));
            }
        }
        if (this.getM_fecha_pago() != null) {
            p_valores.add(new ObjUpdate("ad_alumno_tarifa.alutar_fecha_pago", aDate(this.getM_fecha_pago())));
        }
        if (this.getM_fecha_prorroga() != null) {
            p_valores.add(new ObjUpdate("ad_alumno_tarifa.alutar_fecha_prorroga", aDate(this.getM_fecha_prorroga())));
        }

        try {
            HSEdicionEstructPagoDAO dao =
                    (HSEdicionEstructPagoDAO) ServiceFinder.findBean(
                    "SpringHibernateDaoEdicionEstructura");

            int i = dao.actualizarEstructurasPagoBloque(this.getP_sem(), this.getP_esp(), p_restricciones, p_valores);
            System.out.println("hay en total " + i);
            if (i == 0) {
                this.setResultado("No se han actualizado registros.");
            } else {
                this.setResultado("Se han actualizado " + i + " registros de estudiantes"
                        + "<br>Especialidad: " + getEspecialidad()
                        + "<br>Semestre de ingreso: " + getSemestreIngreso());
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
