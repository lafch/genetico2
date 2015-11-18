/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.metodo.local;

import java.sql.SQLException;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcAula;
import net.uch.mapping.AcLocal;
import net.uch.mapping.AcPabellon;
import net.uch.tablaSistema.hibernateSpringDao.HSAulaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSLocalDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSPabellonDAO;

/**
 *
 * @author Simion Richa R B
 */
public class MetodosAca {
    public SelectItem[] listarSedes(String texto) throws SQLException{
         SelectItem[] sede=null;
         try{
            if (sede == null) {
            HSLocalDAO dao = (HSLocalDAO) ServiceFinder.findBean("SpringHibernateDaoLocal");
            List<AcLocal> lista = dao.seleccionarLocal("");
            sede = new SelectItem[lista.size() + 1];
            sede[0] = new SelectItem(0, "["+texto+"]");
                for (int i = 0; i < sede.length - 1; i++) {
                    sede[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getLocDes());
                }
            }
         }
         catch(Exception e){
            e.printStackTrace();
         }
         return sede;
    }

    public SelectItem[] listarPabellon(int loc_id,String texto) throws SQLException{
        SelectItem[] pabellon=null;
          if (loc_id == 0) {
            pabellon = new SelectItem[1];
            pabellon[0] = new SelectItem(0, "["+texto+"]");
          } else {
                HSPabellonDAO dao = (HSPabellonDAO) ServiceFinder.findBean("SpringHibernateDaoPabellon");
                List<AcPabellon> lista = dao.seleccionarPabellon("", loc_id);
                pabellon = new SelectItem[lista.size()+1];
                pabellon[0]=new SelectItem("0", "["+texto+"]");
                for (int i = 0; i < (pabellon.length-1); i++) {
                    pabellon[i+1] = new SelectItem(lista.get(i).getId(), lista.get(i).getPabNombre());
                }
          }
        
        return pabellon;
    }
     public SelectItem[] listarAula(int pab_id,String texto){
          SelectItem[] aulas=null;
         try {
                HSAulaDAO dao = (HSAulaDAO) ServiceFinder.findBean("SpringHibernateDaoAula");
                List<AcAula> lista = dao.seleccionarAula(pab_id);
                aulas = new SelectItem[lista.size() + 1];
                aulas[0] = new SelectItem(0, "["+texto+"]");
                for (int i = 0; i < lista.size(); i++) {
                    aulas[i + 1] = new SelectItem(lista.get(i).getId(), lista.get(i).getAulDes());
                   // System.out.println("las aulas son -> "+lista.get(i).getAulDes());
                }
            } catch (Exception e) {
                System.err.println("Error al cargar las aulas " + e.getMessage());
                aulas = new SelectItem[1];
                aulas[0] = new SelectItem(0, "["+texto+"]");
            }

          return aulas;
     }
}
