package net.uch.mapping.base;


import java.sql.Timestamp;
import java.util.Date;
import net.uch.commonService.ServiceFinder;


import net.uch.mapping.AcCurso;
import net.uch.mapping.AcDocente;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;

/**
 * AbstractClHoraria entity provides the base persistence definition of the
 * ClHoraria entity.
 *
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseAcDocenteCurso implements java.io.Serializable {

    // Fields
    private Integer curdoc_id;
    private AcDocente acDocente;
    private AcCurso acCurso;
    private Timestamp fechaCreacion;
    private Integer activo;
    private String estadoAsignacion;
    private Integer idEspecialidad;


    // Constructors
    /**
     * default constructor
     */
    public BaseAcDocenteCurso() {
    }

    /**
     * minimal constructor
     */
    public BaseAcDocenteCurso( 
            AcDocente acDocente,AcCurso acCurso ) {
        this.acDocente = acDocente;
        this.acCurso = acCurso;
    }

    /**
     * full constructor
     */
    public BaseAcDocenteCurso( AcDocente acDocente,AcCurso acCurso,
            Timestamp fechaCreacion, Integer activo, String estadoAsignacion ) {
        this.acDocente = acDocente;
        this.acCurso = acCurso;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
        this.estadoAsignacion = estadoAsignacion;
    }

    // Property accessors
    public Integer getCurdoc_id() {
        return curdoc_id;
    }

    public void setCurdoc_id(Integer curdoc_id) {
        this.curdoc_id = curdoc_id;
    }

    public AcDocente getAcDocente() {
        return acDocente;
    }

    public void setAcDocente(AcDocente acDocente) {
        this.acDocente = acDocente;
    }

    public AcCurso getAcCurso() {
        return acCurso;
    }

    public void setAcCurso(AcCurso acCurso) {
        this.acCurso = acCurso;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getEstadoAsignacion() {
        return estadoAsignacion;
    }

    public void setEstadoAsignacion(String estadoAsignacion) {
        this.estadoAsignacion = estadoAsignacion;
    }

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
    
    


    public String traerNomAsignacionxCodigo( String codAsignacion ) {
        HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        return dao.seleccionarDescripcion( codAsignacion );
    }
}
