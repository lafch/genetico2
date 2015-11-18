/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.administrativa.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.AdEstructuraPagos;
import net.uch.mapping.AdProrroga;
import net.uch.util.ObjUpdate;

/**
 *
 * @author cesardl
 */
public interface HSEdicionEstructPagoDAO {

    /**
     * Inserta los alumno tarifa generados
     * @param alumnoTarifa
     */
    public void insertarAlumnoTarifa(AdAlumnoTarifa alumnoTarifa);

    /**
     * Inserta una prorroga generada
     * @param prorroga
     */
    public void insertarProrroga(AdProrroga prorroga);

    /**
     * Retira las prorrogas generadas
     * @param proId
     */
    public void eliminarProrroga(int proId);

    /**
     * Actualiza los numeros despues de eliminar los compromisos que habian sido ingresados
     * @param proId
     * @param num
     */
    public void actualizarNumero(int proId, String num);

    /**
     * Lista los Alumnos Tarifa y sus detalles para ser mostrados  en la tabla maestra
     * @param code
     * @param paterno
     * @param materno
     * @param nombre
     * @param semestre
     * @return
     */
    public List<AcAlumno> listaAlumnoEstructura(String code, String paterno, String materno, String nombre, int semestre);

    /**
     * Lista el detalle del alumno y su estructuras de pago para ser mostrados en la subtabla
     * @param semestre
     * @param alu_id
     * @return
     */
    public List listaTarifasAlumno(int semestre, int alu_id);

    /**
     * Lista datos de los alumnos para el rich:suggestionBox
     * @param dato
     * @return
     */
    public List listaAlumnoActivo(String dato);

    /**
     * Lista las estructuras de pago para el comboBox de la plantilla
     * @param sem
     * @param esp
     * @param semIngreso
     * @return
     */
    public List<AdEstructuraPagos> listaEstructurasPago(int sem, int esp, int semIngreso);

    /**
     * Devuelve la estructura de pago con la que se generara los nuevos<br>
     * de alumnoTarifa
     * @param estpagId
     * @return
     */
    public AdEstructuraPagos estructuraPagoPlantilla(int estpagId);

    /**
     * Lista las estructuras de pago existentes para el semestre
     * @param sem
     * @param esp
     * @param semIngreso
     * @return
     */
    public List listaEstructurasExistentes(int sem, int esp, int semIngreso);

    /**
     * Lista las estructuras de pago que se le generaron al alumno
     * @param aluId
     * @param sem
     * @param esp
     * @return
     */
    public List listaAlumnoTarifaExistentes(int aluId, int sem, int esp);

    /**
     * Cambia el estado del candado
     * @param alutarId
     * @param estado
     */
    public void actualizarEstadoPago(int alutarId, String estado);

    /**
     * Lista los pagos que ha realizado el alumno
     * Caso 0 - el estado de la boleta es 'valido' -> 037001
     * @param alutarId
     * @return
     */
    public List listaPagosAlumno(int alutarId, int caso);

    /**
     * Devuelve el monto del AdAlumnoTarifa
     * @param alutarID
     * @return
     */
    public AdAlumnoTarifa montoAlumnoTarifa(int alutarID);

    /**
     * Actualiza los datos del alumno Tarifa
     * @param alutarID
     * @param monto
     * @param fecha_pago
     * @param fecha_prorroga
     */
    public void actualizarDatosAlumnoTarifa(int alutarID, float monto, Date fecha_pago, Date fecha_prorroga);
    
    public void eliminarPago(int alutarID);

    /**
     * Eliminar las estructuras de pago para los alutarIds;
     * @param alutarID
     */
    public void eliminarEstructurasPagoAlumnoTarifa(List<Integer> alutarIds);

    /**
     * Me lista las prorrogas para un determinado alumno tarifa
     * @param alutarID
     * @return
     */
    public List listaProrrogas(int alutarID);

    /**
     * Actualiza los AdAlumnoTarifa a modificar
     * @param sem_des
     * @param esp
     * @return
     */
    public int actualizarEstructurasPagoBloque(int sem_des, int esp, List<ObjUpdate> restricciones, List<ObjUpdate> valores);
}
