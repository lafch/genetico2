package net.uch.mapping;

import net.uch.mapping.base.BaseTbBanco;

/**
 * TbBanco entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TbBanco extends BaseTbBanco {

    // Constructors
    /** default constructor */
    public TbBanco() {
    }

    /** minimal constructor */
    public TbBanco(Integer banId) {
        super(banId);
    }

    /** full constructor */
    public TbBanco(Integer banId, String banCodigo, String banNombre,
            String banCuentaEmpresa, String banSecServicio,
            String banCantiRegistro, String banRucEmpresa, String banDiaMora,
            String banTipoMora, String banMoraFlat, String banPorcenMora,
            String banTipoDescuento, String banPorcenDescuento,
            String banDiasDescuento, String banCuentaAbono) {
        super(banId, banCodigo, banNombre, banCuentaEmpresa, banSecServicio,
                banCantiRegistro, banRucEmpresa, banDiaMora, banTipoMora,
                banMoraFlat, banPorcenMora, banTipoDescuento,
                banPorcenDescuento, banDiasDescuento, banCuentaAbono);
    }
}
