package net.uch.tablaSistema.hibernateSpringDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.TbRoles;
import net.uch.mapping.TbUsuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate.encryptor.HibernatePBEEncryptorRegistry;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSUsuarioDAOImpl extends HibernateDaoSupport implements HSUsuarioDAO {

    @Override
    public TbUsuario validateUser( String strUserName, String password )
            throws DataAccessException, SQLException {

        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword( "jasypt" );

        HibernatePBEEncryptorRegistry registry1 = HibernatePBEEncryptorRegistry.getInstance();
        registry1.registerPBEStringEncryptor( "hibernateStringEncryptor", textEncryptor );

        TbUsuario user = null;
        DetachedCriteria criteria = DetachedCriteria.forClass( TbUsuario.class );
        criteria.add( Expression.eq( "UsuUsuario", strUserName ) );
        criteria.add( Expression.eq( "UsuActivo", "1" ) );
        List<TbUsuario> usuarios = getHibernateTemplate().findByCriteria( criteria );

        if ( usuarios != null ) {
            for ( int i = 0; i < usuarios.size(); i++ ) {
                if ( password.equals( textEncryptor.decrypt( (usuarios.get( i )).getUsuPassword() ) ) && (usuarios.get( i )).getUsuVigente().equals( "1" ) ) {
                    user = usuarios.get( i );
                }
            }
        }
        /*
         * textEncryptor.setAlgorithm("StandardPBEStringEncryptor"); String
         * mensaje=textEncryptor.encrypt("4dm1n"); String
         * mensaje2=textEncryptor.decrypt("dvfrNgZE2atV3dP2L5qPTAbYY8z91G0vFqEsNzekwHfM+OiW+vuOvA==");
         * System.out.println("mensaje -> "+mensaje+"\n mensaje2 -> "+mensaje2);
         */
        return user;
    }

    @Override
    public void crearUsuario( TbUsuario usuario )
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword( "jasypt" );
        HibernatePBEEncryptorRegistry registry1 = HibernatePBEEncryptorRegistry.getInstance();
        registry1.registerPBEStringEncryptor( "hibernateStringEncryptor", textEncryptor );
        String encryptedPassword = textEncryptor.encrypt( usuario.getUsuPassword() );

        usuario.setUsuPassword( encryptedPassword );

        this.getSession().save( usuario );
    }
    
    @Override
    public void editarUsuario( TbUsuario usuario )
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException {

        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword( "jasypt" );
        HibernatePBEEncryptorRegistry registry1 = HibernatePBEEncryptorRegistry.getInstance();
        registry1.registerPBEStringEncryptor( "hibernateStringEncryptor", textEncryptor );
        String encryptedPassword = textEncryptor.encrypt( usuario.getUsuPassword() );

        usuario.setUsuPassword( encryptedPassword );

        getHibernateTemplate().update( usuario );
    }

    @Override
    public List<TbUsuario> getListUsuario( String strUserName )
            throws DataAccessException, SQLException {

        List<TbUsuario> ltbu = new ArrayList();
        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword( "jasypt" );
        HibernatePBEEncryptorRegistry registry1 = HibernatePBEEncryptorRegistry.getInstance();
        registry1.registerPBEStringEncryptor( "hibernateStringEncryptor", textEncryptor );

        DetachedCriteria criteria = DetachedCriteria.forClass( TbUsuario.class );
        if(!strUserName.equals("")){
            criteria.add( Expression.like( "UsuUsuario", strUserName ) );
        }
        
        criteria.add( Expression.eq( "UsuActivo", "1" ) );
        List objs = getHibernateTemplate().findByCriteria( criteria );
        if ( objs != null ) {
            for ( int i = 0; i < objs.size(); i++ ) {
                TbUsuario obj = new TbUsuario();
                obj.setId( ((TbUsuario) objs.get( i )).getId() );
                obj.setUsuUsuario( ((TbUsuario) objs.get( i )).getUsuUsuario() );
                obj.setUsuPassword( textEncryptor.decrypt( ((TbUsuario) objs.get( i )).getUsuPassword() ) );
                obj.setRol( ((TbUsuario) objs.get( i )).getRol() );
                obj.setUsuActivo( ((TbUsuario) objs.get( i )).getUsuActivo() );
                obj.setUsuNivel( ((TbUsuario) objs.get( i )).getUsuNivel() );
                obj.setUsuTipo( ((TbUsuario) objs.get( i )).getUsuTipo() );
                obj.setUsuTipoId( ((TbUsuario) objs.get( i )).getUsuTipoId() );
                obj.setUsuVigente( ((TbUsuario) objs.get( i )).getUsuVigente() );

//                if (password.equals(textEncryptor.decrypt(((TbUsuario) objs.get(i)).getUsuPassword())) && ((TbUsuario) objs.get(i)).getUsuVigente().equals("1")) {
//                    obj = (TbUsuario) objs.get(i);
//                }
                ltbu.add( obj );
            }
        }
        return ltbu;
    }
    
    @Override
    public List<TbUsuario> getListUsuarios( String strUserName, int rolId, int estadoId )
            throws DataAccessException, SQLException {

        List<TbUsuario> ltbu = new ArrayList();
        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword( "jasypt" );
        HibernatePBEEncryptorRegistry registry1 = HibernatePBEEncryptorRegistry.getInstance();
        registry1.registerPBEStringEncryptor( "hibernateStringEncryptor", textEncryptor );

        DetachedCriteria criteria = DetachedCriteria.forClass( TbUsuario.class );
        if(!strUserName.equals("")){
            criteria.add( Expression.like( "UsuUsuario", strUserName ) );
        }
        
        if(rolId>0){
            criteria.add( Expression.eq( "Rol.Id", rolId ) );
        }
        
        if(estadoId == 0 || estadoId==1){
            criteria.add( Expression.eq("UsuActivo", String.valueOf(estadoId) ) );
        }
        
        List objs = getHibernateTemplate().findByCriteria( criteria );
        if ( objs != null ) {
            for ( int i = 0; i < objs.size(); i++ ) {
                TbUsuario obj = new TbUsuario();
                obj.setId( ((TbUsuario) objs.get( i )).getId() );
                obj.setUsuUsuario( ((TbUsuario) objs.get( i )).getUsuUsuario() );
                obj.setUsuPassword( textEncryptor.decrypt( ((TbUsuario) objs.get( i )).getUsuPassword() ) );
                obj.setRol( ((TbUsuario) objs.get( i )).getRol() );
                obj.setUsuActivo( ((TbUsuario) objs.get( i )).getUsuActivo() );
                obj.setUsuNivel( ((TbUsuario) objs.get( i )).getUsuNivel() );
                obj.setUsuTipo( ((TbUsuario) objs.get( i )).getUsuTipo() );
                obj.setUsuTipoId( ((TbUsuario) objs.get( i )).getUsuTipoId() );
                obj.setUsuVigente( ((TbUsuario) objs.get( i )).getUsuVigente() );

//                if (password.equals(textEncryptor.decrypt(((TbUsuario) objs.get(i)).getUsuPassword())) && ((TbUsuario) objs.get(i)).getUsuVigente().equals("1")) {
//                    obj = (TbUsuario) objs.get(i);
//                }
                ltbu.add( obj );
            }
        }
        return ltbu;
    }
    
    
    @Override
    public List<TbUsuario> getListarUsuario() {
        String hql = "select usu.Id, usu.UsuUsuario "
                + "from TbUsuario usu where usu.UsuActivo = '1' "
                + "order by usu.UsuUsuario";

        Query q = this.getSession().createQuery( hql );

        List tmp = q.list();
        List<TbUsuario> usuarios = new ArrayList<TbUsuario>();
        for ( int i = 0; i < tmp.size(); i++ ) {
            Object[] objs = (Object[]) tmp.get( i );
            TbUsuario usu = new TbUsuario();
            usu.setId( Integer.valueOf( objs[0].toString() ) );
            usu.setUsuUsuario( objs[1].toString().toUpperCase() );

            usuarios.add( i, usu );
        }
        return usuarios;
    }

    @Override
    public List<TbRoles> getRoles() {
        String hql = "select rol.Id, rol.RolTipo "
                + "from TbRoles rol where rol.RolActivo = '1' "
                + "order by rol.RolTipo";

        Query q = this.getSession().createQuery( hql );

        List tmp = q.list();
        List<TbRoles> roles = new ArrayList<TbRoles>();
        for ( int i = 0; i < tmp.size(); i++ ) {
            Object[] objs = (Object[]) tmp.get( i );
            TbRoles rol = new TbRoles();
            rol.setId( Integer.valueOf( objs[0].toString() ) );
            rol.setRolTipo( objs[1].toString().toUpperCase() );

            roles.add( i, rol );
        }
        return roles;
    }
    @Override
    public List<TbUsuario> listarUsuarioPorRoles( int rol_id ) {
        List<TbUsuario> lista = this.getSession().createCriteria( TbUsuario.class ).add( Restrictions.eq( "Rol.Id", rol_id ) ).add( Restrictions.eq( "UsuActivo", "1" ) ).addOrder( Order.asc( "UsuUsuario" ) ).list();
        return lista;
    }

    @Override
    public String verUsuario( int usu_id ) {
        /*
         * return (AcDocente) this.getSession().createCriteria(AcDocente.class).
         * add(Restrictions.eq("DocActivo", "1")). add(Restrictions.eq("Id", id)).uniqueResult();
         */
        TbUsuario usuario = (TbUsuario) this.getSession().createCriteria( TbUsuario.class ).add( Restrictions.eq( "Id", usu_id ) ).uniqueResult();
        String usu = usuario.getUsuUsuario();
        return usu;
    }

    @Override
    public TbUsuario traerUsuarioPorId( int iUsuId ) {
        TbUsuario tbUsuario;
        try {
            tbUsuario = (TbUsuario) this.getSession().
                    createCriteria( TbUsuario.class ).
                    add( Restrictions.eq( "Id", iUsuId ) ).
                    uniqueResult();
        } catch ( Exception ex ) {
            ex.printStackTrace();
            tbUsuario = null;
        }
        return tbUsuario;
    }
    
    @Override
    public List<TbUsuario> getListarUsuarioPorId( int iUsuId ) {
        String hql = "select usu.Id, usu.UsuUsuario,usu.Rol "
                + "from TbUsuario usu where usu.UsuActivo = '1' "
                + " and usu.Id=" + iUsuId
                + " order by usu.UsuUsuario";

        Query q = this.getSession().createQuery( hql );

        List tmp = q.list();
        List<TbUsuario> usuarios = new ArrayList<TbUsuario>();
        for ( int i = 0; i < tmp.size(); i++ ) {
            Object[] objs = (Object[]) tmp.get( i );
            TbUsuario usu = new TbUsuario();
            usu.setId( Integer.valueOf( objs[0].toString() ) );
            usu.setUsuUsuario( objs[1].toString().toUpperCase() );
            usuarios.add( i, usu );
        }
        return usuarios;
    
    }
}