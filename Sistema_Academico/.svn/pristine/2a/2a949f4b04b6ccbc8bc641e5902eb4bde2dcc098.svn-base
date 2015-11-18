package net.uch.cursoLibre.hibernateSpringDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClMatricula;
import net.uch.mapping.ClMatriculaTaller;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HSMatriculaCLDAOImpl extends HibernateDaoSupport implements HSMatriculaCLDAO {

    @Override
    public void insertarMatricula ( ClMatricula matricula ) throws Exception {
        this.getHibernateTemplate().save( matricula );
    }

    @Override
    public void insertarMatriculas ( List<ClMatricula> matriculas ) throws Exception {
        this.getHibernateTemplate().saveOrUpdateAll( matriculas );
    }

    @Override
    public void anularMatricula ( int mat_id ) throws Exception {
        String hqlUpdate = "update ClMatricula set matActivo = :activo where matId = :id";
        this.getSession().createQuery( hqlUpdate ).
                setString( "activo", "0" ).
                setInteger( "id", mat_id ).executeUpdate();
    }
    @Override
    public void anularMatriculaDevolucion ( int mat_id ) throws Exception {
        String hqlUpdate = "update ClMatricula set matTipo = :mTipo where matId = :id";
        this.getSession().createQuery( hqlUpdate ).
                setString( "mTipo", "022010" ).
                setInteger( "id", mat_id ).executeUpdate();
    }

    @Override
    public List<ClMatricula> totalMatriculadosSeccion ( int sec_id, int talape_id, int caso ) {
        Criteria c = this.getSession().createCriteria( ClMatricula.class, "mat" ).
                createCriteria( "mat.clMatriculaTallers", "mattal" ).
                createCriteria( "mat.clAlumno", "alu" ).
                createCriteria( "mattal.clSeccion", "sec" ).
                createCriteria( "sec.clArbolAperturado", "arbapeId" ).
                add( Restrictions.eq( "mat.matActivo", "1" ) ).
                add( Restrictions.eq( "alu.aluActivo", "1" ) ).
                add( Restrictions.eq( "mattal.mattalActivo", "1" ) ).
                add( Restrictions.eq( "mat.matTipo", "022001" ) ).
                add( Restrictions.eq( "sec.secActivo", "1" ) ).
                add( Restrictions.eq( "sec.secId", sec_id ) );

        switch ( caso ) {
            case 0:
                c.add( Restrictions.eq( "ClArbolAperturado.arbapeId", talape_id ) );
                break;
            case 1:
                c.addOrder( Order.asc( "alu.aluAppaterno" ) ).
                        addOrder( Order.asc( "alu.aluApmaterno" ) ).
                        addOrder( Order.asc( "alu.aluNombres" ) );
                break;
        }

        return c.list();
    }

    @Override
    public List<ClMatricula> listarMatriculado_Taller ( int alu_id, int tal_id ) {
        return this.getSession().createCriteria( ClMatricula.class, "mat" ).
                createCriteria( "mat.clMatriculaTallers", "mattal" ).
                createCriteria( "mattal.clSeccion", "sec" ).
                createCriteria( "sec.clTallerAperturado", "talape" ).
                add( Restrictions.eq( "mat.matActivo", "1" ) ).
                add( Restrictions.eq( "mattal.mattalActivo", "1" ) ).
                add( Restrictions.eq( "sec.secActivo", "1" ) ).
                add( Restrictions.eq( "talape.talapeActivo", "1" ) ).
                add( Restrictions.eq( "mat.clAlumno.aluId", alu_id ) ).
                add( Restrictions.eq( "talape.clTaller.talId", tal_id ) ).
                list();
    }

    @Override
    public boolean estaMatriculado ( int alu_id, int sec_id ) {
        return !this.getSession().createCriteria( ClAlumno.class, "alu" ).
                createCriteria( "alu.clMatriculas", "mat" ).
                createCriteria( "mat.clMatriculaTallers", "mattal" ).
                createCriteria( "mattal.clSeccion", "sec" ).
                add( Restrictions.eq( "alu.aluActivo", "1" ) ).
                add( Restrictions.eq( "mat.matActivo", "1" ) ).
                add( Restrictions.eq( "mattal.mattalActivo", "1" ) ).
                add( Restrictions.eq( "sec.secActivo", "1" ) ).
                add( Restrictions.eq( "alu.aluId", alu_id ) ).
                add( Restrictions.eq( "sec.secId", sec_id ) ).
                list().isEmpty();
    }

    @Override
    public int actualizarEstadoMatricula ( String mat_tipo, Integer mat_id ) {
        Query hql = this.getSession().createQuery(
                "update ClMatricula set matTipo = :mat_tipo "
                + "where matId = :mat_id and matActivo = '1'" );

        hql.setString( "mat_tipo", mat_tipo ).setInteger( "mat_id", mat_id );

        return hql.executeUpdate();
    }

    @Override
    public List<ClMatriculaTaller> listadoMatriculados ( int usu_id ) {
        List<ClMatriculaTaller> lista = new ArrayList<ClMatriculaTaller>();
        lista = this.getSession().createCriteria( ClMatriculaTaller.class, "clMatriculaTaller" ).
                add( Restrictions.eq( "clMatriculaTaller.mattalActivo", "1" ) ).
                createCriteria( "clMatriculaTaller.clMatricula", "clMatricula" ).
                add( Restrictions.eq( "clMatricula.matTipo", "022001" ) ).
                add( Restrictions.eq( "clMatricula.matActivo", "1" ) ).
                add( Restrictions.eq( "clMatricula.usuId", usu_id ) ).
                createCriteria( "clMatriculaTaller.clSeccion", "clSeccion" ).
                add( Restrictions.eq( "clSeccion.secActivo", "1" ) ).
                createCriteria( "clSeccion.clTallerAperturado", "clTallerAperturado" ).
                add( Restrictions.eq( "clTallerAperturado.talapeActivo", "1" ) ).
                createCriteria( "clMatricula.clAlumno", "clAlumno" ).
                addOrder( Order.asc( "clTallerAperturado.talapeDescripcion" ) ).
                addOrder( Order.asc( "clAlumno.aluAppaterno" ) ).
                addOrder( Order.asc( "clAlumno.aluApmaterno" ) ).
                list();

        return lista;
    }

    @Override
    public List<ClMatriculaTaller> cantidadMatriculados ( Date fechIni, Date fechaFin ) {
        /*List lista=this.getSession().createCriteria(ClMatriculaTaller.class).
        createCriteria("clMatricula").setProjection(Projections.projectionList().
        add(Projections.rowCount(),"cantidad").
        add(Projections.groupProperty("usuId"))).
        add(Restrictions.eq("matTipo", "022001")).
        add(Restrictions.eq("matActivo", "1")).
        add(Restrictions.between("matFecha", fechIni, fechaFin)).list();*/
        List lista = this.getSession().createCriteria( ClMatricula.class ).setProjection( Projections.projectionList().
                add( Projections.rowCount(), "cantidad" ).
                add( Projections.property( "usuId" ) ).
                add( Projections.groupProperty( "usuId" ) ) ).
                add( Restrictions.eq( "matTipo", "022001" ) ).
                add( Restrictions.eq( "matActivo", "1" ) ).
                add( Restrictions.between( "matFecha", fechIni, fechaFin ) ).
                list();

        return lista;
    }

    @Override
    public ClMatricula BuscarMatricula ( int sec_id, int alu_id ) {
        System.out.println("ingreso aca");
        Criteria c = this.getSession().createCriteria( ClMatricula.class, "mat" ).
                createCriteria( "mat.clMatriculaTallers", "mattal" ).
                createCriteria( "mat.clAlumno", "alu" ).
                createCriteria( "mattal.clSeccion", "sec" ).
                add( Restrictions.eq( "mat.matActivo", "1" ) ).
                add( Restrictions.eq( "alu.aluActivo", "1" ) ).
                add( Restrictions.eq( "mattal.mattalActivo", "1" ) ).
                //add(Restrictions.eq("mat.matTipo", "022001")).
                add( Restrictions.eq( "alu.aluId", alu_id ) ).
                add( Restrictions.eq( "sec.secId", sec_id ) );

        return (ClMatricula) c.uniqueResult();
    }

    @Override
    public void cambiarSeccionMatriculaTaller ( int mat_id, int sec_id ) throws Exception {
        String hqlUpdate = "update ClMatriculaTaller set clSeccion.secId = :sec_id where clMatricula.matId = :mat";
        this.getSession().createQuery( hqlUpdate ).
                setInteger( "sec_id", sec_id ).
                setInteger( "mat", mat_id ).executeUpdate();
    }
    @Override
    public ClMatricula buscarPorMatId( Integer iMatId ){
        Criteria criteria;
        System.out.println("buscarPorMatId");
        criteria = this.getSession().createCriteria( ClMatricula.class );
        /*criteria.add( Restrictions.eq( "matActivo", "1" ) );*/
        criteria.add( Restrictions.in( "matActivo", new String[] { "1", "0" } ) );
        /*criteria.add(Restrictions.in("matTipo", new String[] { "022001","022005", "022009" }));*/
        criteria.add( Restrictions.eq( "matId", iMatId) );
        
        return (ClMatricula) criteria.uniqueResult();
    }
    
     @Override
    public ClMatricula buscarPorMatHabilitar( Integer iMatId ){
        Criteria criteria;
        
        criteria = this.getSession().createCriteria( ClMatricula.class );
        /*criteria.add( Restrictions.eq( "matActivo", "0" ) );*/
        criteria.add( Restrictions.eq( "matTipo", "022009" ) );
        criteria.add( Restrictions.eq( "matId", iMatId) );
        
        return (ClMatricula) criteria.uniqueResult();
    }
}
