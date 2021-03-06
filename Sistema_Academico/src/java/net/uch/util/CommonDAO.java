/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.util;

import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.academica.hibernateSpringDao.HSCursoDAO;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.academica.hibernateSpringDao.HSSistemaEvaluacionDAO;
import net.uch.asistenciaDocente.hibernateSpringDao.HSAsistenciaDocenteDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.*;
import net.uch.tablaSistema.hibernateSpringDao.*;

/**
 *
 * @author USUARIO
 */
public class CommonDAO {

    private static HSDocenteDAO m_acDocenteDAO;
    private static HSAlumnoDAO m_acAlumnoDAO;
    private static HSAsistenciaDocenteDAO m_acAsistenciaDocenteDAO;
    private static HSEspecialidadDAO m_acEspecialidadDAO;
    private static HSAlumnoCLDAO m_clAlumnoDAO;
    private static HSAlumnoDesertorCLDAO m_clAlumnoDesertorDAO;
    private static HSAlumnoTarifaCLDAO m_clAlumnoTarifaDAO;
    private static HSLocalDAO m_acLocalDAO;
    private static HSArbolAcademicoClDao m_clArbolAcadDAO;
    private static HSArbolAperturadoClDAO m_clArbolApeDAO;
    private static HSAsisteciaCLDAO m_clAsistenciaDAO;
    private static HSConsultaPublicoDAO m_clConsultaPublicoDAO;
    private static HSInformacionReferencialDAO m_clInformacionReferencialDAO;
    private static HSEstructuraPagoDAO m_clEstructuraPagoDAO;
    private static HSHorarioDAO m_clHorarioDAO;
    private static HSMatriculaCLDAO m_clMatriculaDAO;
    private static HSMedioPublicidadCLDAO m_clMedioPublDAO;
    private static HSNotasCLDAO m_clNotasDAO;
    private static HSPlantillaHorarioCLDAO m_clPlantillaHorarioDAO;
    private static HSPublicoAlumnoCLDAO m_clPublicoAlumnoDAO;
    private static HSPublicoConsultaCLDAO m_clPublicoConsultaDAO;
    private static HSSeccionCLDAO m_clSeccionDAO;
    private static HSSistemaEvaluacionCLDAO m_clSistemaEvaluacionDAO;
    private static HSSistemaEvaluacionDAO m_SistemaEvaluacionDAO;
    private static HSCatalogoDAO m_tbCatalogoDAO;
    private static HSUsuarioDAO m_tbUsuarioDAO;
    private static HSRolDAO m_tbMenuDAO;
    private static HSUbigeoDAO m_ubigeoDAO;

    private static HSCursoDAO m_acCursoDAO;

    public static HSDocenteDAO getAcDocenteDAO() {
        if (m_acDocenteDAO == null) {
            m_acDocenteDAO = (HSDocenteDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_AC_DOCENTE);
        }
        return m_acDocenteDAO;
    }

    public static HSAlumnoDAO getAcAlumnoDAO() {
        if (m_acAlumnoDAO == null) {
            m_acAlumnoDAO = (HSAlumnoDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_AC_ALUMNO);
        }
        return m_acAlumnoDAO;
    }

    public static HSLocalDAO getAcLocalDAO() {
        if (m_acLocalDAO == null) {
            m_acLocalDAO = (HSLocalDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_AC_LOCAL);
        }
        return m_acLocalDAO;
    }

    public static HSEspecialidadDAO getAcEspecialidadDAO() {
        if (m_acEspecialidadDAO == null) {
            m_acEspecialidadDAO = (HSEspecialidadDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_AC_ESPECIALIDAD);
        }
        return m_acEspecialidadDAO;
    }

    public static HSAlumnoCLDAO getClAlumnoDAO() {
        if (m_clAlumnoDAO == null) {
            m_clAlumnoDAO = (HSAlumnoCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_ALUMNO);
        }
        return m_clAlumnoDAO;
    }

    public static HSAlumnoDesertorCLDAO getClAlumnoDesertorDAO() {
        if (m_clAlumnoDesertorDAO == null) {
            m_clAlumnoDesertorDAO = (HSAlumnoDesertorCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_ALUMNO_DESERTOR);
        }
        return m_clAlumnoDesertorDAO;
    }

    public static HSAlumnoTarifaCLDAO getClAlumnoTarifaDAO() {
        if (m_clAlumnoTarifaDAO == null) {
            m_clAlumnoTarifaDAO = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_ALUMNO_TARIFA);
        }
        return m_clAlumnoTarifaDAO;
    }

    public static HSArbolAcademicoClDao getClArbolAcademicoDAO() {
        if (m_clArbolAcadDAO == null) {
            m_clArbolAcadDAO = (HSArbolAcademicoClDao) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_ARBOL_ACADEMICO);
        }
        return m_clArbolAcadDAO;
    }

    public static HSArbolAperturadoClDAO getClArbolAperturadoClDAO() {
        if (m_clArbolApeDAO == null) {
            m_clArbolApeDAO = (HSArbolAperturadoClDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_ARBOL_APERTURADO);
        }
        return m_clArbolApeDAO;
    }

    public static HSAsisteciaCLDAO getClAsistenciaDAO() {
        if (m_clAsistenciaDAO == null) {
            m_clAsistenciaDAO = (HSAsisteciaCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_ASISTENCIA);
        }
        return m_clAsistenciaDAO;
    }

    public static HSConsultaPublicoDAO getClConsultaPublicoDAO() {
        if (m_clConsultaPublicoDAO == null) {
            m_clConsultaPublicoDAO = (HSConsultaPublicoDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_CONSULTA_PUBLICO);
        }
        return m_clConsultaPublicoDAO;
    }

    public static HSEstructuraPagoDAO getClEstructuraPagoDAO() {
        if (m_clEstructuraPagoDAO == null) {
            m_clEstructuraPagoDAO = (HSEstructuraPagoDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_ESTRUCTURA_PAGO);
        }
        return m_clEstructuraPagoDAO;
    }

    public static HSHorarioDAO getClHorarioDAO() {
        if (m_clHorarioDAO == null) {
            m_clHorarioDAO = (HSHorarioDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_HORARIO);
        }
        return m_clHorarioDAO;
    }

    public static HSMatriculaCLDAO getClMatriculaDAO() {
        if (m_clMatriculaDAO == null) {
            m_clMatriculaDAO = (HSMatriculaCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_MATRICULA);
        }
        return m_clMatriculaDAO;
    }

    public static HSMedioPublicidadCLDAO getClMedioPublDAO() {
        if (m_clMedioPublDAO == null) {
            m_clMedioPublDAO = (HSMedioPublicidadCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_MEDIO_PUBL);
        }
        return m_clMedioPublDAO;
    }

    public static HSNotasCLDAO getClNotasDAO() {
        if (m_clNotasDAO == null) {
            m_clNotasDAO = (HSNotasCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_NOTAS);
        }
        return m_clNotasDAO;
    }

    public static HSPlantillaHorarioCLDAO getClPlantillaHorarioDAO() {
        if (m_clPlantillaHorarioDAO == null) {
            m_clPlantillaHorarioDAO = (HSPlantillaHorarioCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_PLANTILLA_HORARIO);
        }
        return m_clPlantillaHorarioDAO;
    }

    public static HSPublicoAlumnoCLDAO getClPublicoAlumnoDAO() {
        if (m_clPublicoAlumnoDAO == null) {
            m_clPublicoAlumnoDAO = (HSPublicoAlumnoCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_PUBLICO_ALUMNO);
        }
        return m_clPublicoAlumnoDAO;
    }

    public static HSPublicoConsultaCLDAO getClPublicoConsultaDAO() {
        if (m_clPublicoConsultaDAO == null) {
            m_clPublicoConsultaDAO = (HSPublicoConsultaCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_PUBLICO_CONSULTA);
        }
        return m_clPublicoConsultaDAO;
    }

    public static HSInformacionReferencialDAO getClInformacionReferencialDAO() {
        if (m_clInformacionReferencialDAO == null) {
            m_clInformacionReferencialDAO = (HSInformacionReferencialDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_INFORMACION_REFERENCIAL);
        }
        return m_clInformacionReferencialDAO;
    }

    public static HSSeccionCLDAO getClSeccionDAO() {
        if (m_clSeccionDAO == null) {
            m_clSeccionDAO = (HSSeccionCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_SECCION);
        }
        return m_clSeccionDAO;
    }

    public static HSSistemaEvaluacionCLDAO getClSistemaEvaluacionDAO() {
        if (m_clSistemaEvaluacionDAO == null) {
            m_clSistemaEvaluacionDAO = (HSSistemaEvaluacionCLDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_CL_SISTEMA_EVALUACION);
        }
        return m_clSistemaEvaluacionDAO;
    }

    public static HSSistemaEvaluacionDAO getSistemaEvaluacionDAO() {
        if (m_SistemaEvaluacionDAO == null) {
            m_SistemaEvaluacionDAO = (HSSistemaEvaluacionDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_SISTEMA_EVALUACION);
        }
        return m_SistemaEvaluacionDAO;
    }

    public static HSCursoDAO getAcCursoDAO() {
        if (m_acCursoDAO == null) {
            m_acCursoDAO = (HSCursoDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_AC_CURSO);
        }
        return m_acCursoDAO;
    }

    public static HSCatalogoDAO getTbCatalogoDAO() {
        if (m_tbCatalogoDAO == null) {
            m_tbCatalogoDAO = (HSCatalogoDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_TB_CATALOGO);
        }
        return m_tbCatalogoDAO;
    }

    public static HSRolDAO getTbMenuDAO() {
        if (m_tbMenuDAO == null) {
            m_tbMenuDAO = (HSRolDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_TB_MENU);
        }
        return m_tbMenuDAO;
    }

    public static HSUsuarioDAO getTbUsuarioDAO() {
        if (m_tbUsuarioDAO == null) {
            m_tbUsuarioDAO = (HSUsuarioDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_TB_USUARIO);
        }
        return m_tbUsuarioDAO;
    }

    public static HSUbigeoDAO getUbigeoDAO() {
        if (m_ubigeoDAO == null) {
            m_ubigeoDAO = (HSUbigeoDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_TB_UBIGEO);
        }
        return m_ubigeoDAO;
    }

    public static HSAsistenciaDocenteDAO getAsistenciaDocenteDAO() {
        if (m_acAsistenciaDocenteDAO == null) {
            m_acAsistenciaDocenteDAO = (HSAsistenciaDocenteDAO) ServiceFinder.findBean(ConstantesWeb.SHDAO_AC_ASISTENCIA_DOCENTE);
        }
        return m_acAsistenciaDocenteDAO;
    }
}
