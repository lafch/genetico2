/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.mapping.ClArbolAcademico;
import net.uch.util.CommonDAO;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

/**
 *
 *
 *
 * @author Richard Rondinel Bustamante
 *
 *
 *
 */
public class bMantenimientoEstructura {

    private TreeNode arbol = null;
    private List<String> selectedNodeChildren = new ArrayList<String>();
    private String nombreNodo;

    private void agregarHijos( TreeNode node, int idPadre ) {

        HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
        List<ClArbolAcademico> listaArbol = daoArbol.listarArbolPorPadre( idPadre );


        for ( int i = 0; i < listaArbol.size(); i++ ) {
            TreeNodeImpl nodeImpl = new TreeNodeImpl();
            nodeImpl.setData( "<div style='float: left;'>" + listaArbol.get( i ).getArbDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>-" + listaArbol.get( i ).getArbId() + "</div>" );

            node.addChild( new Integer( i + 1 ), nodeImpl );
            agregarHijos( nodeImpl, listaArbol.get( i ).getArbId() );
            //menNodo=menNodo+":"+(1+i);
            //System.out.println("nodo -> "+menNodo);
        }
    }

    private void cargarArbol() {
        try {
            arbol = new TreeNodeImpl();
            agregarHijos( arbol, 0 );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void seleccionarElemento( NodeSelectedEvent event ) {
        HtmlTree tree = (HtmlTree) event.getComponent();
        //String nodos=tree.getRowKeyVar();
        UITree tree2 = (UITree) event.getComponent();
        String id = tree2.getRowKey().toString();
        nombreNodo = ((String) tree.getRowData()) + "  -  " + id;
        System.out.println( "el valor es -> " + devolverId( nombreNodo ) );

        selectedNodeChildren.clear();
        //TreeNode currentNode = tree.getModelTreeNode(tree.getRowKey());
        TreeNode currentNode = tree.getTreeNode( tree.getRowKey() );
        if ( currentNode.isLeaf() ) {
            selectedNodeChildren.add( (String) currentNode.getData() );
        } else {
            Iterator<Map.Entry<Object, TreeNode>> it = currentNode.getChildren();
            while ( it != null && it.hasNext() ) {
                Map.Entry<Object, TreeNode> entry = it.next();
                selectedNodeChildren.add( entry.getValue().getData().toString() );
            }
        }
    }

    public TreeNode getTreeNode() {
        if ( arbol == null ) {
            cargarArbol();
        }

        return arbol;
    }

    public String getNombreNodo() {
        return nombreNodo;
    }

    public void setNombreNodo( String nombreNodo ) {
        this.nombreNodo = nombreNodo;
    }

    public int devolverId( String texto ) {
        int id = 0;
        int valor_ = texto.indexOf( "-" ) + 1;
        int valor__ = texto.indexOf( "<", valor_ );
        String numeroEs = texto.substring( valor_, valor__ );
        id = Integer.parseInt( numeroEs );
        return id;
    }
}
