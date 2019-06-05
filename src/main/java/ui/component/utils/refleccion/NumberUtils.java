/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.utils.refleccion;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * Clas permite limitar el numero de caracteres y tipo de caracteres que se
 * pueden ingresar en campo de entrada de texto
 *
 *
 * @author Angel Navarro Date Jun 19, 2016
 */
public class NumberUtils {

    /**
     * Metodo solo permite el ingreso de numeros en la caja de texto
     *
     * @param textComponent {@link JTextComponent} todos los componentes que
     * extiendan de esta clase pueden ser validados con este metodo.
     */
    public static void limitarNumeros(JTextComponent textComponent) {
        InputTextUtils zipCodeFilter = new InputTextUtils(InputTextUtils.DIGITS);
        textComponent.setDocument(zipCodeFilter);
    }
    
    /**
     * Metodo solo permite el ingreso de numeros en la caja de texto y limita la cantidad de caracteres que pueden ingresar
     *
     * @param textComponent {@link JTextComponent} todos los componentes que
     * extiendan de esta clase pueden ser validados con este metodo.
     */
    public static void limitarNumeros(JTextComponent textComponent, int lent) {
        InputTextUtils zipCodeFilter = new InputTextUtils(InputTextUtils.DIGITS, lent);
        textComponent.setDocument(zipCodeFilter);
    }
    
    /**
     * Verifica si el componete esta vacio
     * @param textComponent Campo de texto
     * @return true se esta vacia o es nulo el campo de texto caso contrario false
     */
    public static boolean isEmpetyFiel(JTextComponent textComponent){
        if(textComponent.getText() == null){
            return true;
        }
        
        return textComponent.getText().trim().isEmpty();
    }
    
    public static boolean isNotEmpetyFiel(JTextComponent textComponent){
        return !isEmpetyFiel(textComponent);
    }
        
}
