package net.uch.tablaSistema.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbRoles;
import net.uch.mapping.TbUsuario;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

public interface HSUsuarioDAO {

    public TbUsuario validateUser(String strUserName, String password)
            throws DataAccessException, java.sql.SQLException;

    /**
     * Crea un usuario encriptando su contraseña
     *
     * @param usuario
     * @throws DataAccessResourceFailureException
     * @throws HibernateException
     * @throws IllegalStateException
     */
    public void crearUsuario(TbUsuario usuario)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException;
    
     public void editarUsuario(TbUsuario usuario)
            throws DataAccessResourceFailureException, HibernateException, IllegalStateException;

    public List<TbUsuario> getListUsuario(String strUserName)
            throws DataAccessException, java.sql.SQLException;
    public List<TbUsuario> getListUsuarios( String strUserName, int rolId, int estadoId ) throws DataAccessException, java.sql.SQLException;

    public List<TbUsuario> getListarUsuario();

    public List<TbUsuario> getListarUsuarioPorId(int usu_id);

    /**
     * Devuelve los posibles roles para el usuario
     *
     * @return
     */
    public List<TbRoles> getRoles();

    public List<TbUsuario> listarUsuarioPorRoles(int rol_id);

    public String verUsuario(int usu_id);

    public TbUsuario traerUsuarioPorId(int iUsuId);

   
}
