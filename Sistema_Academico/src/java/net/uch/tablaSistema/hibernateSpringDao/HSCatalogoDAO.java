package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbCatalogo;
import org.springframework.dao.DataAccessException;

public interface HSCatalogoDAO {

    public List seleccionarCatalogo(String codigo);
    
    public List seleccionarCiclosXPlanCurricular(int planCurricular);
    
     public List seleccionarCiclosXPlanCurricularGenerar(int plan, int semId, int turId);

    public String seleccionarDescripcion(String codigo);

    public String seleccionarValor(String codigo) throws DataAccessException;

    public List<TbCatalogo> seleccionarGrupo(String codigo);

    public List<TbCatalogo> seleccionarGrupoxValor(String codigo);

    public List<TbCatalogo> selecionarFilaCatalogo(String codigo);
}
