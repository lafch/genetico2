/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.util;

/**
 *
 * @author LUIS
 */
public class Utils {
    
    public static String llamarProcedimiento(String nombreProcedimiento, int numeroParametros) {

        StringBuffer sb = new StringBuffer("{call " + nombreProcedimiento + "(");
        for (int n = 1; n <= numeroParametros; n++) {
            sb.append("?");
            if (n < numeroParametros) {
                sb.append(",");
            }
        }
        return sb.append(")}").toString();
        
    }
    
}
