/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.cursoLibre.managedBeans;

/**
 *
 * @author Alumno
 */
public class bPrueba {

    public static void main(String[] args){
        String valor="<div style='float: left;'>MODELO DE RED Y PROTOCOLO, CABLEADO ESTRUCTURADO</div><div style='color:#FFFFFF;position: left;'>-12</div>";
        int valor_=valor.indexOf("-")+1;
        int valor__=valor.indexOf("<", valor_);
        String numeroEs=valor.substring(valor_, valor__);
        System.out.println("el valor es -> "+numeroEs);
        
    }

}
