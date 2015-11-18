/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import java.math.BigDecimal;
import java.util.List;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcNota;
import net.uch.mapping.AcNotasTemporales;
import net.uch.mapping.AcSisEvaPersonalizado;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author richard
 */
public class HSNotasTemporalesDAOImpl extends HibernateDaoSupport implements HSNotasTemporalesDAO {

    @Override
    public List<AcNotasTemporales> listarNotasTemporales(int sec_id, int siseva_per_id) {
        List<AcNotasTemporales> lista = this.getSession().createCriteria(AcNotasTemporales.class, "AcNotasTemporales").
                add(Restrictions.eq("AcNotasTemporales.acSeccion.Id", sec_id)).
                add(Restrictions.eq("AcNotasTemporales.sisevaPerId", siseva_per_id)).
                add(Restrictions.eq("AcNotasTemporales.nottemActivo", "1")).list();
        return lista;
    }

    @Override
    public AcNota buscarNotax_notaTemporal(int alu_id, int sec_id, int siseva_per_id) {
        List<AcNota> lista = this.getSession().createCriteria(AcNota.class, "AcNota").
                add(Restrictions.eq("AcNota.SecId", sec_id)).
                add(Restrictions.eq("AcNota.Alu.Id", alu_id)).
                add(Restrictions.eq("AcNota.SisevaPer.Id", siseva_per_id)).
                add(Restrictions.eq("AcNota.NotActivo", "1")).list();
        AcNota nota = null;
        if (lista.size() > 0) {
            nota = new AcNota();
            for (int i = 0; i < lista.size(); i++) {
                AcAlumno alu = new AcAlumno();
                AcSisEvaPersonalizado per = new AcSisEvaPersonalizado();
                alu.setId(lista.get(i).getAlu().getId());
                per.setId(lista.get(i).getSisevaPer().getId());
                nota.setAlu(alu);
                nota.setSisevaPer(per);
                nota.setId(lista.get(i).getId());
                nota.setNotActivo(lista.get(i).getNotActivo());
                nota.setNotCreacion(lista.get(i).getNotCreacion());
                nota.setNotObservacion(lista.get(i).getNotObservacion());
                nota.setSecId(lista.get(i).getSecId());
            }

        } else {
            nota = null;
        }
        return nota;
    }

    @Override
    public void agregarAcNotas(AcNota nota) {
        this.getSession().save(nota);
    }

    @Override
    public void modificarAcNotas(AcNota nota) {
       // System.out.println("del Dao ID ->"+nota.getId());
        //System.out.println(" nota -> "+nota.getNotNota());
        //System.out.println("nota -> "+nota.getSecId());
        String hqlUpdate = "update AcNota set NotNota = :v_nota where Id = :v_id";
        this.getSession().createQuery(hqlUpdate).setBigDecimal("v_nota", nota.getNotNota()).setInteger("v_id", nota.getId()).executeUpdate();
       // this.getSession().saveOrUpdate(nota);
    }

    @Override
    public List listarAlumnos_AcNota(int sec_id, int siseva_per_id) {

        String sql = "select alu.AluCodigo, alu.AluAppaterno, alu.AluApmaterno, alu.AluNombres, not.NotNota"
                + " from AcAlumno as alu inner join alu.AcNotas as not where not.SecId= :sec_id and "
                + "not.SisevaPer.Id= : siseva_per_id order by alu.AluAppaterno asc";
        List alumno = this.getSession().createQuery(sql).setInteger("sec_id", sec_id).setInteger("siseva_per_id", siseva_per_id).list();
        return alumno;
    }

    @Override
    public List listarAlumnos_acNotaTemporal(int sec_id, int siseva_per_id) {
        List lista = this.getSession().createCriteria(AcAlumno.class, "alu").add(Restrictions.eq("alu.AluActivo", "1")).createCriteria("alu.AcMatriculas", "mat").add(Restrictions.eq("mat.MatTipo", "022001")).add(Restrictions.eq("mat.MatActivo", "1")).createCriteria("mat.AcMatriculaCursos", "matriCur").add(Restrictions.eq("matriCur.Sec.Id", sec_id)).list();
        return lista;
    }
}
