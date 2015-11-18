/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcEspecialidad;
import net.uch.mapping.AcFacultad;
import net.uch.tablaSistema.hibernateSpringDao.HSFacultadDAO;
import net.uch.util.Archivo;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import org.richfaces.model.UploadItem;

/**
 * @author Ilya Shaikovsky
 *
 */
public class FileUploadBean {

    private ArrayList<Archivo> files = new ArrayList<Archivo>();
    private int uploadsAvailable = 5;
    private boolean autoUpload = false;
    private boolean useFlash = false;
     private TreeNode arbol = null;
    private String nombreNodo;
    private List<String> selectedNodeChildren = new ArrayList<String>();

    public int getSize() {
        if (getFiles().size() > 0) {
            return getFiles().size();
        } else {
            return 0;
        }
    }

    public FileUploadBean() {
        cargarArbol();
    }

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer) object).getData());
    }

    public synchronized void listener(UploadEvent event) throws Exception {
        UploadItem item = event.getUploadItem();
        java.io.File file2=item.getFile();
        System.out.println("item -> "+item.isTempFile());
        System.out.println("file -> "+file2);
        System.out.println("-------"+file2.renameTo(new File("D:\\"+item.getFileName())));
        
        Archivo file = new Archivo();
        System.out.println("DATA : " + item.getData());
//        file.setLength(item.getData().length);
        file.setName(item.getFileName());
        file.setData(item.getData());
        files.add(file);
        uploadsAvailable--;
    }

    public String clearUploadData() {
        files.clear();
        setUploadsAvailable(5);
        return null;
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public ArrayList<Archivo> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Archivo> files) {
        this.files = files;
    }

    public int getUploadsAvailable() {
        return uploadsAvailable;
    }

    public void setUploadsAvailable(int uploadsAvailable) {
        this.uploadsAvailable = uploadsAvailable;
    }

    public boolean isAutoUpload() {
        return autoUpload;
    }

    public void setAutoUpload(boolean autoUpload) {
        this.autoUpload = autoUpload;
    }

    public boolean isUseFlash() {
        return useFlash;
    }

    public void setUseFlash(boolean useFlash) {
        this.useFlash = useFlash;
    }

    public TreeNode getArbol() {
        return arbol;
    }

    public void setArbol(TreeNode arbol) {
        this.arbol = arbol;
    }
    
    
    
    private void cargarArbol() {
        try {
            arbol = new TreeNodeImpl();
            agregarHijos(arbol, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNombreNodo() {
        return nombreNodo;
    }

    public void setNombreNodo(String nombreNodo) {
        this.nombreNodo = nombreNodo;
    }

    public List<String> getSelectedNodeChildren() {
        return selectedNodeChildren;
    }

    public void setSelectedNodeChildren(List<String> selectedNodeChildren) {
        this.selectedNodeChildren = selectedNodeChildren;
    }
     public TreeNode getTreeNode() {
        if (arbol == null) {
            cargarArbol();
        }

        return arbol;
    }
    
     private void agregarHijos(TreeNode node, int idPadre) {
        int contNodes = 0;
         HSFacultadDAO dao=(HSFacultadDAO) ServiceFinder.findBean("SpringHibernateDaoFacultad");
        List<AcFacultad> lista=dao.getListarTodas();
        
         for (int i = 0; i < lista.size(); i++) {
            TreeNodeImpl nodeImpl = new TreeNodeImpl(); 
           
            List<AcEspecialidad> listaEspe=new ArrayList<AcEspecialidad>(lista.get(i).getAcEspecialidads());
            int contador=0;
             for (int j = 0; j < listaEspe.size(); j++) {
                 nodeImpl = new TreeNodeImpl();
                        System.out.println("TallerAperturado: " + listaEspe.get(j).getEspNombre());
                        nodeImpl.setData("<div style='float: left;'>" + listaEspe.get(j).getEspNombre() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + listaEspe.get(j).getId() + "</div>");
//                        node.addChild(new Integer(j + 1), nodeImpl);
                        node.addChild(contNodes++, nodeImpl);
             }
              nodeImpl.setData("<div style='float: left;'>" + lista.get(i).getFacNombre() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + 
              lista.get(i).getId() + "</div>");
              node.addChild(new Integer(i + 1), nodeImpl);
         }


        
        /*for (int i = 0; i < listaArbol.size(); i++) {

            TreeNodeImpl nodeImpl = new TreeNodeImpl();


            List<ClArbolAperturado> listaTalape = daoTA.cargarTallerAperturado(listaArbol.get(i).getArbId());

            System.out.println("Cantidad: " + listaTalape.size());
            if (!listaTalape.isEmpty()) {
                System.out.println("Taller: " + listaArbol.get(i).getArbDescripcion());

                for (int j = 0; j < listaTalape.size(); j++) {
                    contNodes++;

                    if (listaTalape.get(j).getArbapeDescripcion() != null) {// esto se le puso xq EN LA TABLA ALGUNSO NO TIENEN DESCRIPCION
                        nodeImpl = new TreeNodeImpl();
                        System.out.println("TallerAperturado: " + listaTalape.get(j).getArbapeDescripcion());
                        nodeImpl.setData("<div style='float: left;'>" + listaTalape.get(j).getArbapeDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + listaTalape.get(j).getArbapeId() + "</div>");
//                        node.addChild(new Integer(j + 1), nodeImpl);
                        node.addChild(contNodes, nodeImpl);
                        agregarHijos(nodeImpl, listaArbol.get(i).getArbId());
                    }


                }
            } else {
                nodeImpl.setData("<div style='float: left;'>" + listaArbol.get(i).getArbDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + listaArbol.get(i).getArbId() + "</div>");
                node.addChild(new Integer(i + 1), nodeImpl);
                agregarHijos(nodeImpl, listaArbol.get(i).getArbId());

            }
           
        }*/
    }
    
      public void seleccionarElemento(NodeSelectedEvent event) {
        HtmlTree tree = (HtmlTree) event.getComponent();
        //String nodos=tree.getRowKeyVar();
        UITree tree2 = (UITree) event.getComponent();
        String id = tree2.getRowKey().toString();
        nombreNodo = ((String) tree.getRowData()) + "  -  " + id;
        System.out.println("el valor es -> " + nombreNodo);
      
        selectedNodeChildren.clear();
        //TreeNode currentNode = tree.getModelTreeNode(tree.getRowKey());
        TreeNode currentNode = tree.getTreeNode(tree.getRowKey());
        if (currentNode.isLeaf()) {
            selectedNodeChildren.add((String) currentNode.getData());
        } else {
            Iterator<Map.Entry<Object, TreeNode>> it = currentNode.getChildren();
            while (it != null && it.hasNext()) {
                Map.Entry<Object, TreeNode> entry = it.next();
                selectedNodeChildren.add(entry.getValue().getData().toString());
            }
        }
    }
}