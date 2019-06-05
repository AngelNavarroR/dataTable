/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.utils.refleccion;

import javax.swing.JOptionPane;

/**
 *
 * @author Angel Navarro
 */
public class Infos {
    /**
     * 
     * @param message
     * @param title 
     */
    public static void messageError(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * 
     * @param message
     * @param title 
     */
    public static void messageInfo(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * 
     * @param message
     * @param title 
     */
    public static void messageWarning(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * 
     * @param message
     * @param title 
     */
    public static void messageFatal(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR);
    }
    
    
}
