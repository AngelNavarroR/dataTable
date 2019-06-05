/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentsUtils;

/**
 *
 * @author Angel Navarro
 */
public class UtilsString {

    public static boolean isEmpetyString(String text) {
        return text == null || text.length() == 0;
    }

    public static boolean isEmpetyObject(Object obj) {
        return obj == null;
    }

    /**
     * Reemplaza la primera letra de en mayuscula desde la segunda palabra
     *
     * @param col texto a poner la primera letra de cada palabra
     * @return texto modificado
     */
    public static String upperFirstCharWork(String col) {
        if (UtilsString.isEmpetyString(col)) {
            throw new RuntimeException("Texto nulo");
        }
        String[] s = null;
        if (col.contains(" ")) {
            s = col.split(" ");
        } else if (col.contains("_")) {
            s = col.split("_");
        }
        StringBuilder buffer = new StringBuilder();
        int i = 0;
        if (s != null) {
            for (String string : s) {
                if (i != 0) {
                    Character letterOne = string.charAt(0);
                    String substring = string.substring(1, string.length());
                    buffer.append(letterOne.toString().toUpperCase());
                    buffer.append(substring);
                } else {
                    buffer.append(string);
                }
                i++;
            }
        }
        return buffer.toString();
    }

}
