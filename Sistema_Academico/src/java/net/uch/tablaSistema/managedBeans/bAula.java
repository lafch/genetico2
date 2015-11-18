package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.academica.managedBeans.beans.BeanDocente;
import net.uch.academica.managedBeans.beans.BeanHorarioDispAula;
import net.uch.academica.managedBeans.beans.BeanHorarioDispDoc;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcHorarioDispAula;
import net.uch.mapping.AcHorarioDispDocente;
import net.uch.mapping.AcLocal;
import net.uch.mapping.AcPabellon;
import net.uch.mapping.TbCatalogo;
import net.uch.metodo.local.MetodosAca;
import net.uch.tablaSistema.hibernateSpringDao.HSAulaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSEspecialidadDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSLocalDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSPabellonDAO;
import net.uch.util.CommonDAO;

public class bAula {
   private bAula aula;
    public int b_id;
    public String b_des;
    public String b_des_i;
    public String b_des_auxiliar;
    public int b_cap;
    public int b_cap_auxiliar;
    public int b_id_local;
    public String b_desc_local;
    public int b_id_pabellon = 0;
    public int b_id_especialidad = 0;
    public String b_id_tipo_aula = "000000";
    public String b_desc_pabellon;
    public String b_desc_especialidad;
    public String b_desc_tipo_aula;
    public int b_id_local_auxiliar;
    public int b_id_pabellon_auxiliar;
    public int b_id_especialidad_auxiliar;
    public String b_id_tipo_aula_auxiliar;
    public String b_activo;
    private List listaAula;
    private String editable = "false";
    private String view = "true";
    private boolean visible = false;
    private SelectItem[] comboLocales;
    private SelectItem[] comboPabellones;
    private SelectItem[] comboEspecialidad;
    private SelectItem[] comboTipoAula;
    public int b_id_local_f;
    public int b_id_pabellon_f;
    public int b_id_Especialidad_f;
    public String b_id_Tipo_Aula_f;
    private SelectItem[] comboLocales_f;
    private SelectItem[] comboPabellones_f;
    private SelectItem[] comboEspecialidad_f;
    private SelectItem[] comboTipoAula_f;
    private SelectItem[] comboAulas_f;
    private String oncomplete;
    private boolean editable2 = false;
    MetodosAca metodo = new MetodosAca();
    
     //Detalle de horaria
    private BeanHorarioDispAula horaria;
    private List<BeanHorarioDispAula> nListaHoraria;
    private List<BeanHorarioDispAula> qListaHoraria;
    private int aula_id;
    private String aula_detalle;
    private String v_imagen_horario;
    
    

    public void Nuevo() {
        this.setB_des_auxiliar( "" );
        this.setB_des_i( "" );
        this.setB_cap( 0 );
        this.setB_cap_auxiliar( 0 );
        this.setB_id_Especialidad_f( 0 );
        this.setB_id_pabellon( 0 );
        this.setB_id_local( 0 );
        this.setB_id_local_auxiliar( 0 );
        this.setB_id_tipo_aula("000000");
    }

    public bAula() {
        //aula = new bAula();
        this.horaria = new BeanHorarioDispAula( 0 );
        this.nListaHoraria = new ArrayList<BeanHorarioDispAula>();
        this.qListaHoraria = new ArrayList<BeanHorarioDispAula>();
    }

    public String getB_id_tipo_aula() {
        return b_id_tipo_aula;
    }

    public void setB_id_tipo_aula(String b_id_tipo_aula) {
        this.b_id_tipo_aula = b_id_tipo_aula;
    }

    public String getB_desc_tipo_aula() {
        return b_desc_tipo_aula;
    }

    public void setB_desc_tipo_aula(String b_desc_tipo_aula) {
        this.b_desc_tipo_aula = b_desc_tipo_aula;
    }
    
    
    
    

    public String getEditable() {
        return editable;
    }

    public void setEditable( String editable ) {
        this.editable = editable;
    }

    public String getView() {
        return view;
    }

    public void setView( String view ) {
        this.view = view;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    public List getListaAula() {
        return listaAula;
    }

    public void setListaAula( List listaAula ) {
        this.listaAula = listaAula;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id( int b_id ) {
        this.b_id = b_id;
    }

    public String getB_des() {
        return b_des;
    }

    public void setB_des( String b_des ) {
        this.b_des = b_des;
    }

    public int getB_cap() {
        return b_cap;
    }

    public void setB_cap( int b_cap ) {
        this.b_cap = b_cap;
    }

    public int getB_id_local() {
        return b_id_local;
    }

    public void setB_id_local( int b_id_local ) {
        this.b_id_local = b_id_local;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo( String b_activo ) {
        this.b_activo = b_activo;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public boolean isEditable2() {
        return editable2;
    }

    public void setEditable2( boolean editable2 ) {
        this.editable2 = editable2;
    }

    public String getV_imagen_horario() {
        return v_imagen_horario;
    }

    public void setV_imagen_horario( String v_imagen_horario ) {
        this.v_imagen_horario = v_imagen_horario;
    }
    
    

    public String Grabar() {
        if ( ( this.getB_des_i().trim() ).length()<= 5 || this.getB_cap() <= 0 || this.getB_id_pabellon() == 0  || this.getB_id_especialidad() == 0 
               || !this.getB_desc_tipo_aula().equals("000000")
           ) {
            if((this.getB_des_i().trim()).length()<=5){
                this.setOncomplete( "javascript:alert('Ingrese una descripcion valida (5 carateres como minimo)')" );
            }else if(this.getB_cap() <= 0){
                this.setOncomplete( "javascript:alert('Ingrese una capacidad mayor a 0')" );
            }else if(this.getB_id_pabellon() == 0){
                 this.setOncomplete( "javascript:alert('Seleccione un pabellon')" );
            }else if(this.getB_id_especialidad() ==0){
                 this.setOncomplete( "javascript:alert('Seleccione una especialidad')" );
            }else if(!this.getB_desc_tipo_aula().equals("000000")){
                 this.setOncomplete( "javascript:alert('Seleccione una tipo de aula')" );
            }
            else{
                this.setOncomplete( "javascript:alert('Ingrese todos los datos Requeridos.')" );
            }
            
            return "fail";
        } else {
            HSAulaDAO dao = (HSAulaDAO)ServiceFinder.findBean( "SpringHibernateDaoAula" );
            AcAula aula = new AcAula();
            aula.setId( this.getB_id() );
            aula.setAulDes( this.getB_des_i() );
            aula.setAulCap( this.getB_cap() );
            AcPabellon pab = new AcPabellon();
            AcEspecialidad esp = new AcEspecialidad();
            esp.setId(this.getB_id_especialidad());
            pab.setId( this.getB_id_pabellon() );
            aula.setPab( pab );
            aula.setEsp( esp );
            aula.setAulActivo( "1" );
            aula.setAulTipo(this.getB_id_tipo_aula());
            dao.insertarAula( aula );
            this.setOncomplete( "javascript:alert('Se creo un Aula correctamente.');Richfaces.hideModalPanel('mp')" );
            return "ok";
        }
    }

    public void Seleccionar() throws Exception {
        listaAula = new ArrayList();
        HSCatalogoDAO  daoCat;
        daoCat = CommonDAO.getTbCatalogoDAO();
        HSAulaDAO dao = (HSAulaDAO)ServiceFinder.findBean( "SpringHibernateDaoAula" );
        List<AcAula> lista = dao.seleccionarAula( this.getB_des(), this.getB_id_pabellon_f(), this.getB_id_Especialidad_f() );
        bAula bA;
        if(!lista.isEmpty()){
        for ( int i = 0; i < lista.size(); i++ ) {

            AcAula aula = lista.get( i );
            bA = new bAula();
            bA.setB_id( aula.getId().intValue() );
            bA.setB_des( aula.getAulDes() );
            bA.setB_cap( aula.getAulCap().intValue() );
            bA.setB_id_pabellon( aula.getPab().getId().intValue() );
            bA.setB_desc_pabellon( aula.getPab().getPabDescripcion() );
            bA.setB_desc_especialidad( aula.getEsp().getEspNombre() );
            bA.setB_id_especialidad(aula.getEsp().getId());
            bA.setB_id_local( aula.getPab().getLoc().getId().intValue() );
            bA.setB_id_tipo_aula(aula.getAulTipo());
            bA.setB_desc_local( aula.getPab().getLoc().getLocDes() );
            bA.setB_desc_tipo_aula(daoCat.seleccionarDescripcion( aula.getAulTipo()));
             if ( dao.seleccionarHorario( bA.getB_id()).isEmpty() ) {
                    bA.setV_imagen_horario( "/Imagenes/actions/horario_gris.png" );
                } else {
                    bA.setV_imagen_horario( "/Imagenes/actions/horario.png" );
                    // sProfesor = daoClHor.seleccionarHorario( tmp_sec.getSecId() ).get( 0 ).getAcDocente().getDocNombre();
                }
            listaAula.add( bA );
        }
        }
    }

    public String getB_desc_local() {
        return b_desc_local;
    }

    public void setB_desc_local( String b_desc_local ) {
        this.b_desc_local = b_desc_local;
    }

    public String getB_desc_pabellon() {
        return b_desc_pabellon;
    }

    public void setB_desc_pabellon( String b_desc_pabellon ) {
        this.b_desc_pabellon = b_desc_pabellon;
    }

    public String getB_desc_especialidad() {
        return b_desc_especialidad;
    }

    public void setB_desc_especialidad( String b_desc_especialidad ) {
        this.b_desc_especialidad = b_desc_especialidad;
    }

    public String getB_des_auxiliar() {
        return b_des_auxiliar;
    }

    public void setB_des_auxiliar( String b_des_auxiliar ) {
        this.b_des_auxiliar = b_des_auxiliar;
    }

    public int getB_cap_auxiliar() {
        return b_cap_auxiliar;
    }

    public void setB_cap_auxiliar( int b_cap_auxiliar ) {
        this.b_cap_auxiliar = b_cap_auxiliar;
    }

    public int getB_id_local_auxiliar() {
        return b_id_local_auxiliar;
    }

    public void setB_id_local_auxiliar( int b_id_local_auxiliar ) {
        this.b_id_local_auxiliar = b_id_local_auxiliar;
    }

    public int getB_id_pabellon_auxiliar() {
        return b_id_pabellon_auxiliar;
    }

    public void setB_id_pabellon_auxiliar(int b_id_pabellon_auxiliar) {
        this.b_id_pabellon_auxiliar = b_id_pabellon_auxiliar;
    }
    
    

    public int getB_id_especialidad_auxiliar() {
        return b_id_especialidad_auxiliar;
    }

    public void setB_id_especialidad_auxiliar( int b_id_especialidad_auxiliar ) {
        this.b_id_especialidad_auxiliar = b_id_especialidad_auxiliar;
    }

    public String getB_id_tipo_aula_auxiliar() {
        return b_id_tipo_aula_auxiliar;
    }

    public void setB_id_tipo_aula_auxiliar(String b_id_tipo_aula_auxiliar) {
        this.b_id_tipo_aula_auxiliar = b_id_tipo_aula_auxiliar;
    }
        

    public void Cancelar( ActionEvent event ) {
        this.setB_des( this.getB_des_auxiliar() );
        this.setB_cap( this.getB_cap_auxiliar() );
        this.setB_id_local( this.getB_id_local_auxiliar() );
        this.setB_id_pabellon(this.getB_id_pabellon_auxiliar());
        this.setB_id_especialidad( this.getB_id_especialidad_auxiliar() );
        this.setB_id_tipo_aula(this.getB_id_tipo_aula_auxiliar());
        this.setView( "true" );
        this.setEditable( "false" );
        this.setEditable2( false );
        this.setVisible( false );
    }

    public void EditarFila( ActionEvent event ) {
        this.setB_des_auxiliar( this.getB_des() );
        this.setB_cap_auxiliar( this.getB_cap() );
        this.setB_id_local_auxiliar(this.getB_id_local());
        this.setB_id_pabellon_auxiliar(this.getB_id_pabellon());
        this.setB_id_especialidad_auxiliar( this.getB_id_especialidad() );
        this.setB_id_tipo_aula_auxiliar(this.getB_id_tipo_aula());
        this.setView( "false" );
        this.setEditable( "true" );
        this.setEditable2( true );
        this.setVisible( true );
    }

    public void Editar( ActionEvent event ) {
        UIParameter id = (UIParameter)event.getComponent().findComponent( "id" );
        UIParameter descripcion = (UIParameter)event.getComponent().findComponent( "descripcion" );
        UIParameter capacidad = (UIParameter)event.getComponent().findComponent( "capacidad" );
        UIParameter pabellon = (UIParameter)event.getComponent().findComponent( "pabellon" );
        UIParameter especialidad = (UIParameter)event.getComponent().findComponent( "especialidad" );
        UIParameter tipo = (UIParameter)event.getComponent().findComponent( "tipo" );

        if ( ( descripcion.getValue().toString().trim() ).length() < 5 || Integer.parseInt( capacidad.getValue().toString() ) <= 0 || Integer.parseInt( pabellon.getValue().toString() ) == 0 ) {
            if((descripcion.getValue().toString().trim() ).length() < 5){
                this.setOncomplete( "javascript:alert('Ingrese descripcion de aula mayor a 5 caracteres')" );
            } else if(Integer.parseInt( capacidad.getValue().toString() ) <= 0){
                this.setOncomplete( "javascript:alert('Ingrese un valor numerico en la capacidad mayor a 0')" );
            } else if(Integer.parseInt( pabellon.getValue().toString() ) == 0){
                this.setOncomplete( "javascript:alert('Ingrese un pabellon')" );
            } else{
                this.setOncomplete( "javascript:alert('Ingrese Datos.')" );
            }
            
        } else {
            int aulId = Integer.parseInt( id.getValue().toString() );
            AcAula aula = new AcAula();
            aula.setId( aulId );
            aula.setAulDes( descripcion.getValue().toString() );
            aula.setAulCap( Integer.parseInt( capacidad.getValue().toString() ) );
            aula.setPab( new AcPabellon( Integer.parseInt( pabellon.getValue().toString() ) ) );
            aula.setEsp( new AcEspecialidad( Integer.parseInt( especialidad.getValue().toString() ) ) );
            aula.setAulTipo(tipo.getValue().toString());
            aula.setAulActivo( "1" );

            HSAulaDAO dao = (HSAulaDAO)ServiceFinder.findBean( "SpringHibernateDaoAula" );
            dao.actualizarAula( aula );

            this.setB_desc_pabellon( getDescPabellon() );
            this.setB_desc_especialidad( getDescEspecialidad() );
            this.setB_desc_local( getDescLocal() );
            this.setB_desc_tipo_aula( getDescTipoAula());
            this.setView( "true" );
            this.setEditable( "false" );
            this.setEditable2( false );
            this.setVisible( false );
            this.setOncomplete( "javascript:alert('Se Actualizaron los datos del Aula correctamente.')" );
        }
    }

    public void EliminarFila( ActionEvent event ) throws Exception {
        UIParameter delete = (UIParameter)event.getComponent().findComponent( "delete" );
        Eliminar( delete.getValue().toString() );
    }

    public void Eliminar( String id ) throws Exception {
        HSAulaDAO dao = (HSAulaDAO)ServiceFinder.findBean( "SpringHibernateDaoAula" );
        dao.eliminarAula( id );
    }

    public String getB_des_i() {
        return b_des_i;
    }

    public void setB_des_i( String b_des_i ) {
        this.b_des_i = b_des_i;
    }

    public SelectItem[] getComboPabellones() throws Exception {
        comboPabellones = metodo.listarPabellon( this.b_id_local, "Seleccine" );
        return comboPabellones;
    }

    public String getDescPabellon() {
        for ( int i = 0; i < comboPabellones.length; i++ ) {
            SelectItem se = comboPabellones[i];
            if ( Integer.parseInt( se.getValue().toString() ) == this.b_id_pabellon ) {
                return se.getLabel();
            }
        }
        return "---";
    }

    public String getDescEspecialidad() {
        for ( int i = 0; i < comboEspecialidad.length; i++ ) {
            SelectItem se = comboEspecialidad[i];
            if ( Integer.parseInt( se.getValue().toString() ) == this.b_id_especialidad ) {
                return se.getLabel();
            }
        }
        return "---";
    }
    
    public String getDescTipoAula() {
        for ( int i = 0; i < comboTipoAula.length; i++ ) {
            SelectItem se = comboTipoAula[i];
            if ( se.getValue().toString().equals(this.b_id_tipo_aula) ) {
                return se.getLabel();
            }
        }
        return "---";
    }

    public void setComboPabellones( SelectItem[] comboPabellones ) {
        this.comboPabellones = comboPabellones;
    }

    public SelectItem[] getComboEspecialidad() throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO)ServiceFinder.findBean( "SpringHibernateDaoEspecialidad" );
        List lista = dao.seleccionarEspecialidad();
        if ( !lista.isEmpty() ) {
            comboEspecialidad = new SelectItem[ lista.size()+1 ];
            comboEspecialidad[0] = new SelectItem( 0, "[Seleccione]" );
            for ( int i = 0; i < lista.size(); i++ ) {
                comboEspecialidad[i+1] = new SelectItem( ( (AcEspecialidad)lista.get( i ) ).getId(), ( (AcEspecialidad)lista.get( i ) ).getEspNombre() );
            }
        } else {
            comboEspecialidad = new SelectItem[ 1 ];
            comboEspecialidad[0] = new SelectItem( 0, "[Seleccione]" );
        }


        return comboEspecialidad;
    }

    public void setComboEspecialidad( SelectItem[] comboEspecialidad ) {
        this.comboEspecialidad = comboEspecialidad;
    }

    public SelectItem[] getComboTipoAula() {
         if ( comboTipoAula == null ) {
            try {
                HSCatalogoDAO dao = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                List<TbCatalogo> lstCatAsignacion = dao.seleccionarCatalogo( "098" );
                comboTipoAula = new SelectItem[ lstCatAsignacion.size() + 1 ];
                comboTipoAula[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < lstCatAsignacion.size(); i++ ) {
                    comboTipoAula[i + 1] = new SelectItem( lstCatAsignacion.get( i ).getCatCodigoGrupo() + lstCatAsignacion.get( i ).getCatCodigoElemento(), lstCatAsignacion.get( i ).getCatDescripcionElemento() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los asignacion " + e.getMessage() );
                comboTipoAula = new SelectItem[ 1 ];
                comboTipoAula[0] = new SelectItem( "000000", "[Seleccione]" );
            }
        }
        return comboTipoAula;
    }

    public void setComboTipoAula(SelectItem[] comboTipoAula) {
        this.comboTipoAula = comboTipoAula;
    }

    public SelectItem[] getComboLocales() throws Exception {

        /*if (comboLocales == null) {
         HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
         List<AcLocal> lista = dao.seleccionarLocal("");
         comboLocales = new SelectItem[lista.size() + 1];
         comboLocales[0] = new SelectItem(0, "[Seleccione]");
         for (int i = 0; i < comboLocales.length - 1; i++) {
         comboLocales[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getLocDes());
         }
         }*/
        comboLocales = metodo.listarSedes( "Seleccione" );
        return comboLocales;
    }

    public String getDescLocal() {
        for ( int i = 0; i < comboLocales.length; i++ ) {
            SelectItem se = comboLocales[i];
            if ( Integer.parseInt( se.getValue().toString() ) == this.b_id_local ) {
                return se.getLabel();
            }
        }
        return "---";
    }

    public void setComboLocales( SelectItem[] comboLocales ) {
        this.comboLocales = comboLocales;
    }

    public void Enviar1( ActionEvent event ) throws Exception {
        if ( Grabar().equalsIgnoreCase( "ok" ) ) {
            Seleccionar();
        }
    }

    public int getB_id_pabellon() {
        return b_id_pabellon;
    }

    public void setB_id_pabellon( int b_id_pabellon ) {
        this.b_id_pabellon = b_id_pabellon;
    }

    public int getB_id_especialidad() {
        return b_id_especialidad;
    }

    public void setB_id_especialidad( int b_id_especialidad ) {
        this.b_id_especialidad = b_id_especialidad;
    }
    
    

    public int getB_id_local_f() {
        return b_id_local_f;
    }

    public void setB_id_local_f( int b_id_local_f ) {
        this.b_id_local_f = b_id_local_f;
    }

    public int getB_id_pabellon_f() {
        return b_id_pabellon_f;
    }

    public void setB_id_pabellon_f( int b_id_pabellon_f ) {
        this.b_id_pabellon_f = b_id_pabellon_f;
    }

    public int getB_id_Especialidad_f() {
        return b_id_Especialidad_f;
    }

    public void setB_id_Especialidad_f( int b_id_Especialidad_f ) {
        this.b_id_Especialidad_f = b_id_Especialidad_f;
    }

    public String getB_id_Tipo_Aula_f() {
        return b_id_Tipo_Aula_f;
    }

    public void setB_id_Tipo_Aula_f(String b_id_Tipo_Aula_f) {
        this.b_id_Tipo_Aula_f = b_id_Tipo_Aula_f;
    }
    
    

    public SelectItem[] getComboLocales_f() throws Exception {
        /* if (comboLocales_f == null) {
         HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
         List<AcLocal> lista = dao.seleccionarLocal("");
         comboLocales_f = new SelectItem[lista.size() + 1];
         comboLocales_f[0] = new SelectItem(0, "TODOS");
         for (int i = 0; i < comboLocales_f.length - 1; i++) {
         comboLocales_f[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getLocDes());
         }
         }*/
        comboLocales_f = metodo.listarSedes( "Todos" );
        return comboLocales_f;
    }

    public void setComboLocales_f( SelectItem[] comboLocales_f ) {
        this.comboLocales_f = comboLocales_f;
    }

    public SelectItem[] getComboPabellones_f() throws Exception {
        comboPabellones_f = metodo.listarPabellon( this.b_id_local_f, "Seleccione" );
        return comboPabellones_f;
    }

    public void setComboPabellones_f( SelectItem[] comboPabellones_f ) {
        this.comboPabellones_f = comboPabellones_f;
    }

    public SelectItem[] getComboEspecialidad_f()
            throws Exception {
        HSEspecialidadDAO dao = (HSEspecialidadDAO)ServiceFinder.findBean( "SpringHibernateDaoEspecialidad" );
        List lista = dao.seleccionarEspecialidad();
        if ( lista.size() != 0 ) {
            
            comboEspecialidad_f = new SelectItem[ lista.size()+1];
            comboEspecialidad_f[0] = new SelectItem( 0, "[Seleccione]" );
            for ( int i = 0; i < comboEspecialidad_f.length-1; i++ ) {
                comboEspecialidad_f[i+1] = new SelectItem( ( (AcEspecialidad)lista.get( i ) ).getId(), ( (AcEspecialidad)lista.get( i ) ).getEspNombre() );
            }
        } else {
            comboEspecialidad_f = new SelectItem[ 1 ];
            comboEspecialidad_f[0] = new SelectItem( 0, "[Seleccione]" );
        }

        return comboEspecialidad_f;
    }

    public void setComboEspecialidad_f( SelectItem[] comboEspecialidad_f ) {
        this.comboEspecialidad_f = comboEspecialidad_f;
    }

    public SelectItem[] getComboTipoAula_f() {
        if ( comboTipoAula_f == null ) {
            try {
                HSCatalogoDAO dao = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                List<TbCatalogo> lstCatAsignacion = dao.seleccionarCatalogo( "098" );
                comboTipoAula_f = new SelectItem[ lstCatAsignacion.size() + 1 ];
                comboTipoAula_f[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < lstCatAsignacion.size(); i++ ) {
                    comboTipoAula_f[i + 1] = new SelectItem( lstCatAsignacion.get( i ).getCatCodigoGrupo() + lstCatAsignacion.get( i ).getCatCodigoElemento(), lstCatAsignacion.get( i ).getCatDescripcionElemento() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los asignacion " + e.getMessage() );
                comboTipoAula_f = new SelectItem[ 1 ];
                comboTipoAula_f[0] = new SelectItem( "000000", "[Seleccione]" );
            }
        }
        return comboTipoAula_f;
    }

    public void setComboTipoAula_f(SelectItem[] comboTipoAula_f) {
        this.comboTipoAula_f = comboTipoAula_f;
    }
    
    

    public int getAula_id() {
        return aula_id;
    }

    public void setAula_id( int aula_id ) {
        this.aula_id = aula_id;
    }

    public String getAula_detalle() {
        return aula_detalle;
    }

    public void setAula_detalle( String aula_detalle ) {
        this.aula_detalle = aula_detalle;
    }

    public BeanHorarioDispAula getHoraria() {
         return horaria;
    }

    public void setHoraria( BeanHorarioDispAula horaria ) {
        this.horaria = horaria;
    }

    public List<BeanHorarioDispAula> getnListaHoraria() {
        return nListaHoraria;
    }

    public void setnListaHoraria( List<BeanHorarioDispAula> nListaHoraria ) {
        this.nListaHoraria = nListaHoraria;
    }
    
    public List<BeanHorarioDispAula> getqListaHoraria() {
        return qListaHoraria;
    }

    public void setqListaHoraria( List<BeanHorarioDispAula> qListaHoraria ) {
        this.qListaHoraria = qListaHoraria;
    }
    
    public void addnListaHoraria( BeanHorarioDispAula tmp ) {
        this.nListaHoraria.add( tmp );
    }
    
     public void addqListaHoraria( BeanHorarioDispAula tmp ) {
        this.qListaHoraria.add( tmp );
    }
    
   
    public void cargarHorario( ActionEvent event ) throws Exception {
        int iAulaId;
        String sAulaDetalle;

        BeanHorarioDispAula beanAcAulaHor;
        AcHorarioDispAula acHor;
        HSCatalogoDAO daoCat;
        HSAulaDAO daoAulaHor = (HSAulaDAO)ServiceFinder.findBean( "SpringHibernateDaoAula" );
        List<AcHorarioDispAula> lstHorDispAula;
        daoCat = CommonDAO.getTbCatalogoDAO();
        this.setOncomplete( "" );
        iAulaId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_id" ) ).getValue().toString() );
        sAulaDetalle = ( (UIParameter)event.getComponent().findComponent( "p_aula_detalle" ) ).getValue().toString();
        this.setAula_id( iAulaId );
        this.setAula_detalle( sAulaDetalle );
        this.horaria = new BeanHorarioDispAula( iAulaId );
        // this.horaria.getAcDocente().setId( iDocId );
        this.nListaHoraria = new ArrayList<BeanHorarioDispAula>();
        this.qListaHoraria = new ArrayList<BeanHorarioDispAula>();


        lstHorDispAula = daoAulaHor.seleccionarHorario(iAulaId );

        if ( !lstHorDispAula.isEmpty() ) {
            for ( int i = 0; i < lstHorDispAula.size(); i++ ) {
                acHor = lstHorDispAula.get( i );
                beanAcAulaHor = new BeanHorarioDispAula( acHor );
                beanAcAulaHor.setPosicion( i );
                beanAcAulaHor.setV_hor_dia( daoCat.seleccionarDescripcion( acHor.getHorDia() ) );
                beanAcAulaHor.setView_bool( true );
                beanAcAulaHor.setEditable_bool( false );
                this.addnListaHoraria( beanAcAulaHor );
            }
        }
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpHoraria')" );

    }
    
    
    public void agregarHorario( ActionEvent event ) {
        String sMsg;
        BeanHorarioDispAula bClHorAux;
        this.setOncomplete( "" );
        this.horaria.getAcAula().setId(  this.getAula_id() );
        sMsg = this.horaria.esValido();
        if ( sMsg.isEmpty() ) {
            for ( String sDiaCod : this.horaria.getLstDiasCod() ) {
                bClHorAux = new BeanHorarioDispAula( this.aula_id );
                bClHorAux.setAcAula(new AcAula( horaria.getAcAula().getId() ) );
                bClHorAux.setInicio_h( horaria.getInicio_h() );
                bClHorAux.setInicio_m( horaria.getInicio_m() );
                bClHorAux.setMeridiano_inicio( horaria.getMeridiano_inicio() );
                bClHorAux.setFin_h( horaria.getFin_h() );
                bClHorAux.setFin_m( horaria.getFin_m() );
                bClHorAux.setMeridiano_fin( horaria.getMeridiano_fin() );
                bClHorAux.actualizarDatos( aula_id );
                bClHorAux.setPosicion( this.nListaHoraria.size() );
                bClHorAux.setV_hor_dia( CommonDAO.getTbCatalogoDAO().seleccionarDescripcion( sDiaCod ) );
                bClHorAux.setHorDia( sDiaCod );
                this.addnListaHoraria( bClHorAux );
            }

            this.horaria = new BeanHorarioDispAula( this.getAula_id() );

        } else {
            this.setOncomplete( "javascript:alert('" + sMsg + "')" );
        }
    }

    public void removerHorario( ActionEvent event ) {
        int iSizeHor;
        int iHorPosIndex;
        BeanHorarioDispAula bClHorAux;

        iHorPosIndex = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_hor_pos" ) ).getValue().toString() );
        iSizeHor = nListaHoraria.size();
        for ( int i = iHorPosIndex + 1; i < iSizeHor; i++ ) {
            bClHorAux = nListaHoraria.get( i );
            bClHorAux.setPosicion( bClHorAux.getPosicion() - 1 );
        }
        this.addqListaHoraria( this.nListaHoraria.remove( iHorPosIndex ) );
    }

    public boolean edicionActivada() {
        for ( BeanHorarioDispAula bean : nListaHoraria ) {
            if ( bean.isEdit_active() ) {
                return true;
            }
        }
        return false;
    }

    public void guardarHorarios( ActionEvent event )throws Exception{
        HSAulaDAO daoDocHor = (HSAulaDAO)ServiceFinder.findBean( "SpringHibernateDaoAula" );
        List<Integer> lstIdsHorariosElim;
        List<AcHorarioDispAula> lstHorarios;

        this.setOncomplete( "" );
        if ( this.nListaHoraria.isEmpty() ) {
            this.setOncomplete( "javascript:alert('Debe ingresar los horarios para Guardar.')" );
        } else if ( edicionActivada() ) {
            this.setOncomplete( "javascript:alert('Termine de editar los horarios.')" );
        } else {


            lstHorarios = new ArrayList<AcHorarioDispAula>();
            for ( BeanHorarioDispAula tmp_horaria : nListaHoraria ) {
                lstHorarios.add( tmp_horaria.getAcHorarioDispAula());
            }
            daoDocHor.insertarActualizarHorarios( lstHorarios );
            if ( !qListaHoraria.isEmpty() ) {
                lstIdsHorariosElim = new ArrayList<Integer>();
                for ( BeanHorarioDispAula tmp_horaria : qListaHoraria ) {
                    lstIdsHorariosElim.add( tmp_horaria.getHorId() );
                }
                daoDocHor.eliminarHorarios(lstIdsHorariosElim );
            }
            this.setOncomplete( "javascript:alert('Generacion de horarios satisfactoria.');"
                    + "Richfaces.hideModalPanel('mpHoraria');" );
             Seleccionar();
        }
    }
}
