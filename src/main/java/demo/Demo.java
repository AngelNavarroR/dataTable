/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.edisoncor.gui.Dialogo;
import org.edisoncor.gui.varios.TransicionHorzontal;
import ui.component.DialogGrowl;

/**
 * Main de los demos del proyeto
 *
 * @author Angel Navarro.
 * @Date 02/08/2016
 */
public class Demo {

    private static final Logger LOG = Logger.getLogger(Demo.class.getName());

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        try {
            DemoData demo = new DemoData();
            demo.setVisible(true);
            org.edisoncor.gui.Demo d = new org.edisoncor.gui.Demo();
            d.setVisible(true);
            new DialogGrowl(null, "Angel Navarro", "Body Componente", true).setVisible(true);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Arranque de la Aplicacion", e);
            System.exit(0);
        }
    }
}
