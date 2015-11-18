package net.uch.mapping;

import java.util.Date;
import net.uch.mapping.base.BaseTbPagoBanco;

/**
 * TbPagoBanco entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TbPagoBanco extends BaseTbPagoBanco implements
        java.io.Serializable {

    // Constructors
    /** default constructor */
    public TbPagoBanco() {
    }

    /** minimal constructor */
    public TbPagoBanco(Integer pagbanId) {
        super(pagbanId);
    }

    /** full constructor */
    public TbPagoBanco(Integer pagbanId, String pagbanCodigo, Integer alutarId,
            String pagbanNombrePago, Float pagbanMonto, Integer aluId,
            String pagbanConcepto, Date pagbanFechaVencimiento,
            String pagbanGrupo, Integer pagbanCodigoGrupo, String pagbanActivo,
            Date pagbanFechaRegistro, String conceptoCobrar, Date pagbanFecha, Double pagbanMora) {
        super(pagbanId, pagbanCodigo, alutarId, pagbanNombrePago, pagbanMonto,
                aluId, pagbanConcepto, pagbanFechaVencimiento, pagbanGrupo,
                pagbanCodigoGrupo, pagbanActivo, pagbanFechaRegistro,
                conceptoCobrar, pagbanFecha, pagbanMora);
    }

    @Override
    public String toString() {
        return "codigo_grupo: " + this.getPagbanCodigoGrupo()
                + "\tconcepto: " + this.getPagbanConcepto();
    }
}
