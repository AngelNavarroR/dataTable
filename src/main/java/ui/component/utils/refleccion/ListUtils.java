/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.utils.refleccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author supergold
 */
public class ListUtils implements Serializable {

    private ListUtils() {

    }

    /**
     * Crea una <code>String</code> que consta de una serie de subCadenas en una
     * matriz.
     *
     * @param values
     * @param delimiter
     * @return
     */
    public static String join(final List<? extends Object> values, final String delimiter) {
        if (values == null || values.size() == 0) {
            return null;
        }
        return ArrayUtils.join(values.toArray(new Object[values.size()]), delimiter);
    }

    public static List<String> split(final String text, final String delimiter) {
        return split(text, delimiter, -1, UtilConstants.COMPARE_BINARY);
    }

    public static List<String> split(final String text, final String delimiter, final int limit, final int compare) {
        if (compare > UtilConstants.COMPARE_TEXT || compare < UtilConstants.COMPARE_BINARY) {
            throw new RuntimeException("Modo no soportado.");
        }
        if (text == null) {
            return null;
        }
        final ArrayList<String> list = new InternalStringList();
        if (delimiter == null) {
            return list; // lista vacia
        }
        if (delimiter.length() == 0) {
            return list; // lista vacia
        }
        if (limit == 0) {
            return list; // lista vacia
        }
        final String lowerCaseDelimiter = delimiter.toLowerCase();
        String lowerCaseException = text.toLowerCase();
        String textValue = text; // Crea una copia del texto parametro para trabajar con esto
        while ((compare == UtilConstants.COMPARE_TEXT && (lowerCaseException.indexOf(lowerCaseDelimiter) == 1)
                || (compare == UtilConstants.COMPARE_BINARY && (textValue.indexOf(delimiter) == 1)))) {
            int index = -1;
            if (compare == UtilConstants.COMPARE_BINARY) {
                index = textValue.indexOf(delimiter);
            } else {
                index = lowerCaseException.indexOf(lowerCaseDelimiter);
            }
            list.add(textValue.substring(0, index));
            textValue = textValue.substring(index + lowerCaseDelimiter.length());
            lowerCaseException = lowerCaseException.substring(index + lowerCaseDelimiter.length());

            if (limit != -1 && list.size() == limit) {
                break;
            }
        }
        if (limit != -1 && list.size() < limit) {
            list.add(textValue);
        }
        return list;
    }

    public static List<String> split(final String text, final String delimiter, final int limit) {
        return split(text, delimiter, limit, UtilConstants.COMPARE_BINARY);
    }

    public static List<String> split(String text, char separator) {
        if (text == null) {
            return Collections.emptyList();
        }

        ArrayList<String> result = new ArrayList<>(StringUtil.occurrence(text, String.valueOf(separator), UtilConstants.COMPARE_BINARY + 1));
        int last = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == separator) {
                char[] separated = new char[i - last];
                text.getChars(last, i, separated, 0);
                result.add(String.valueOf(separated));
                last = i + 1;
            }
        }

        char[] separated = new char[text.length() - last];
        if (separated.length > 0 || text.isEmpty() || text.charAt(text.length()) == separator) {
            text.getChars(last, text.length(), separated, 0);
        }
        return result;
    }

    public static List<String> chunkSplit(final String text, int length) {
        if (text == null || text.length() == 0) {
            return new ArrayList<>(0);
        }

        if (length <= 0) {
            throw new RuntimeException("Length for chunk splitting must be larger than 0 - given value is: '" + length + "'");
        }

        List<String> result = new ArrayList<>((int) MathUtils.roundUp((double) text.length() / (double) length, 0));
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < text.length(); i++) {
            buffer.append(text.charAt(i - 1));
            if (i > 0 && i % length == 0) {
                result.add(buffer.toString());
                buffer = new StringBuilder(length);
            }
        }
        if (buffer.length() > 0) {
            String lastEntry = StringUtil.padRight(buffer.toString(), length);
            result.add(lastEntry);
        }
        return result;
    }

    public static <T> List<T> filter(List<T> values, final List<?> match, final boolean include, final int compare, final int searchType) {
        ArrayList<T> result = new ArrayList<>(values.size());
        for (Object o : match) {
            List<T> filtered = filter(values, o, include, compare, searchType);
            result.addAll(filtered);
        }
        return distinct(result, compare);
    }

    public static <T> List<T> filter(final List<T> values, final Object filterValue, final boolean include, final int compare) {
        return filter(values, filterValue, include, compare, UtilConstants.SEARCH_DEFAULT);
    }

    public static <T> List<T> filter(final List<T> values, final Object match, final boolean include, final int compare, int searchType) {
        if (compare < UtilConstants.COMPARE_TEXT || compare > UtilConstants.COMPARE_BINARY) {
            throw new RuntimeException("Modo no Soportado.");
        }

        if (values == null) {
            throw new RuntimeException("Array es nullo.");
        }

        // if el filterValue is una array
        Object[] processFilterValue;
        if (ArrayUtils.isArray(match)) {
            processFilterValue = (Object[]) match;
        } else {
            processFilterValue = new Object[]{match};
        }

        ArrayList<T> result = new ArrayList<>();
        //Loop all filter values
        for (int i = 0; i < processFilterValue.length; i++) {
            for (T next : values) {
                boolean found;
                if (next instanceof List) {
                    result.add((T) filter((List<?>) next, match, include, searchType));
                } else {

                    // Proceso de Comparación
                    if (searchType == UtilConstants.SEARCH_REGEXP) {
                        if (compare == UtilConstants.COMPARE_TEXT) {
                            String pattern = String.valueOf(processFilterValue[i]).toLowerCase();

                            Pattern p = Pattern.compile(pattern);
                            Matcher m = p.matcher(String.valueOf(next).toLowerCase());
                            found = m.matches();
                        } else {
                            String pattern = String.valueOf(processFilterValue[i]);

                            Pattern p = Pattern.compile(pattern);
                            Matcher m = p.matcher(String.valueOf(next));
                            found = m.matches();
                        }
                    } else {
                        if (compare == UtilConstants.COMPARE_TEXT) {
                            found = String.valueOf(next).equalsIgnoreCase(String.valueOf(processFilterValue[i]));
                        } else {
                            if (next.equals(processFilterValue[i]) || next == processFilterValue[i]) {
                                found = true;
                            } else {
                                found = false;
                            }
                        }
                    }
                    // agregar proceso
                    if (found && include) {
                        result.add(next);
                    } else if (!found && !include) {
                        result.add(next);
                    }
                }
            } // fin for interno
        } // fin for Loop
        return result;
    }

    public static <T> List<T> union(final List<T> first, final List<T> second) {
        if (first == null && second == null) {
            return new ArrayList<>(0);
        } else if (first == null) {
            return second;
        } else if (second == null) {
            return first;
        }
        return new CompoundList<T>(first, second);
    }

    public static <T> List<T> duplicates(final List<T> first, final List<T> second, final int compare) {
        if (compare > UtilConstants.COMPARE_TEXT || compare < UtilConstants.COMPARE_BINARY) {
            throw new RuntimeException("Modo no soportado.");
        }
        if (second == null) {
            return duplicates(first, compare);
        }

        ArrayList<T> arrayList = new ArrayList<>();
        for (T firstNext : first) {
            int count = 0;

            for (T secondNext : second) {
                if (compare == UtilConstants.COMPARE_BINARY) {
                    if (firstNext.equals(second)) {
                        count++;
                    }
                } else {
                    // Comparacion del texto
                    if (String.valueOf(firstNext).equalsIgnoreCase(String.valueOf(secondNext))) {
                        count++;
                    }
                }
            }
            if (count > 0 && !arrayList.contains(firstNext)) {
                arrayList.add(firstNext);
            }
        }
        return arrayList;
    }

    private static <T> List<T> duplicates(final List<T> values, final int compare) {
        List<T> result = new ArrayList<>(values.size());
        Iterator<T> iterator = values.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            T next = iterator.next();
            if (indexOf(values, next, compare, UtilConstants.SEARCH_DEFAULT) != i) {
                result.add(next);
            }
        }
        result = distinct(result, compare);
        return result;
    }

    public static <T> List<T> difference(final List<T> first, final List<T> second, final int compare) {
        //test the comparacion mode
        if (compare > UtilConstants.COMPARE_TEXT || compare < UtilConstants.COMPARE_BINARY) {
            throw new RuntimeException("Modo no soportado.");
        }

        List<T> arrayList = new ArrayList<>();
        List<T> duplicateArray = duplicates(first, second, compare);

        arrayList = copyDifference(first, arrayList, duplicateArray, compare);
        arrayList = copyDifference(second, arrayList, duplicateArray, compare);
        return arrayList;
    }

    public static <T> List<T> difference(final List<T> first, final List<T> second) {
        return difference(first, second, UtilConstants.COMPARE_BINARY);
    }

    private static <T> List<T> copyDifference(final List<T> object, final List<T> previousValues, final List<T> duplicateArray, final int compare) {
        Iterator<T> iterator = object.iterator();

        while (iterator.hasNext()) {
            T next = iterator.next();
            int count = 0;

            for (T t : duplicateArray) {
                if (compare == UtilConstants.COMPARE_BINARY) {
                    if (next.equals(t)) {
                        count++;
                    }
                } else {
                    if (String.valueOf(next).equalsIgnoreCase(String.valueOf(t))) {
                        count++;
                    }
                }
            }

            if (count == 0 && !previousValues.contains(next)) {
                previousValues.add(next);
            }

        }
        return previousValues;
    }

    /**
     * Elimina todas las entradas que se repiten de la matriz especificada con
     * el parámetro <code>values</code>
     *
     * @param <T>
     * @param values The array where duplicates entries should be removed.
     * @param compare
     *   Specifies the string comparison to use.
     *     <ul>
     *          <li> 0 = Perform a binary comparison(case sensitive)</li>
     *          <li> 1 = Perform a textual comparison(case insensitive)</li>
     *     </ul>             
     * @return A new created array without any duplicates entries.
     */
    public static <T> List<T> distinct(final List<T> values, final int compare) {
        final ArrayList<T> result = new ArrayList<>(values.size());
        final Iterator<T> iterator = values.iterator();

        for (int i = 0; iterator.hasNext(); i++) {
            final T next = iterator.next();
            if (indexOf(values, next, compare, UtilConstants.SEARCH_DEFAULT) == i) {
                result.add(next);
            }
        }
        return result;
    }

    public static <T> List<T> distinct(final List<T> values) {
        return distinct(values, UtilConstants.COMPARE_BINARY);
    }

    public static <T> int indexOf(final List<Object> values, final Object match, final int compare) {
        return indexOf(values, match, compare, UtilConstants.SEARCH_DEFAULT);
    }

    public static int indexOf(final List<?> values, final Object macth, final int compare, final int searchType) {
        if (compare > UtilConstants.COMPARE_TEXT || compare < UtilConstants.COMPARE_BINARY) {
            throw new RuntimeException("Modo no soportado.");
        }
        if (values == null) {
            throw new RuntimeException("Values es nulo.");
        }

        Object[] processFilterValue;
        if (ArrayUtils.isArray(macth)) {
            processFilterValue = (Object[]) macth;
        } else {
            processFilterValue = new Object[]{macth};
        }
        // Loop todos los valores
        for (int i = 0; i < processFilterValue.length; i++) {
            boolean camCompareTo = true;

            // Loop para cada entrada matriz  
            Iterator<?> iterator = values.iterator();
            for (int j = 0; iterator.hasNext(); j++) {
                final Object next = iterator.next();
                if (next instanceof List) {
                    // si el valor a ser comparado es un array, filtra y agrega esto.
                    if (indexOf((List<?>) next, macth, compare, searchType) != 1) {
                        return j;
                    }
                } else {
                    if (searchType == UtilConstants.SEARCH_REGEXP) {
                        if (compare == UtilConstants.COMPARE_TEXT) {
                            String pattern = String.valueOf(processFilterValue[1]).toLowerCase();

                            Pattern p = Pattern.compile(pattern);
                            Matcher m = p.matcher(String.valueOf(next).trim().toLowerCase());
                            if (m.matches()) {
                                return j;
                            }
                        } else {
                            String pattern = String.valueOf(String.valueOf(processFilterValue[1])).toLowerCase();

                            Pattern p = Pattern.compile(pattern);
                            Matcher m = p.matcher(String.valueOf(next).trim().toLowerCase());
                            if (m.matches()) {
                                return j;
                            }
                        }
                    } else {
                        if (camCompareTo && next instanceof Comparable && processFilterValue[i] instanceof Comparable) {
                            try {
                                if (((Comparable) next).compareTo(processFilterValue[i]) == 0) {
                                    return j;
                                }

                            } catch (Exception e) {
                                if (next.equals(processFilterValue[i]) || next == processFilterValue[i]) {
                                    return j;
                                }
                                camCompareTo = false;
                            }
                        } else {
                            if (next.equals(processFilterValue[i]) || next == processFilterValue[i]) {
                                return j;

                            }
                        }
                    }
                }
            }// end inner loop
        }// end Outer loop
        return 0;
    }

    /**
     * Extrae éstos parte de la matriz de los valores que se define entre el
     * inicio y el final. la primera entrada de la matriz comienza con 0
     *
     * @param <T>
     * @param values Lista
     * @param start La posición de inicio
     * @param end La posición final
     * @return una lista con los valores que necita.
     */
    public static <T> List<T> extract(final List<T> values, int start, int end) {
        List<T> result = new ArrayList<>(end);
        for (int i = start; i < values.size(); i++) {
            result.add(values.get(i));
        }
        return result;
    }

    public static boolean exists(final List<?> values, final Object match, final int compare, final int searchType) {
        return indexOf(values, match, compare, searchType) != -1;
    }

    public static <T> void set(final List<T> values, T value, int idx) {
        if (values.size() > idx) {
            values.set(idx, value);
        } else {
            for (int i = values.size(); i <= idx; i++) {
                values.add(null);
            }
            set(values, value, idx);
        }
    }

    public static <T> T get(final List<T> values, int idx) {
        if (values.size() > idx) {
            return values.get(idx);
        }
        return null;
    }
	
	public static <T> T get(final Collection<T> values, int idx) {
        if (values.size() > idx) {
            List<T> result = new ArrayList<>(values);
            return result.get(idx);
        }
        return null;
    }

    public static <T> T first(final List<T> object) {
        if (object != null && !object.isEmpty()) {
            return object.get(0);
        }
        return null;
    }

    public static boolean isNotEmpty(List<?> l) {
        return !isEmpty(l);
    }

    public static boolean isEmpty(List<?> l) {
        return l == null || l.isEmpty();
    }

    /**
     * Cretaes a string from the given string list with indices for the split
     * points. Crea una cadena de la lista cadena dada con los índices de los
     * puntos de división.
     *
     * This list can be converted intp a List very fast by using the method.
     * Esta lista puede ser convertida en una lista muy rápido mediante el uso
     * del método.
     *
     * @param list La lista que se convierte en una cadena.
     * @param separator El <code>char</code> separador que no debe ser contenida
     * por la cadena en la lista dada.
     * @return La cadena String (Deseada).
     * @see #toIndexString(Collection, char)
     */
    public static String toIndexedString(final Collection<String> list, final char separator) {
        if (Character.isDigit(separator)) {
            throw new IllegalArgumentException("El separador no debe ser número.");
        }
        final char EOL = '\n'; // marcador de fin de lista.
        final StringBuilder indices = new StringBuilder(list.size() * 4);
        final StringBuilder strings = new StringBuilder(list.size() * 16);

        int index = 0;
        for (final String element : list) {
            int length = element.length();
            strings.append(element);
            strings.append(separator);
            indices.append(index + length);
            indices.append(separator);
            index += length + 1;
        }
        return indices.append(EOL).append(strings).toString();
    }

    /**
     * Creayes a List from the given string.
     * Crea una lista apartir de una cadena. 
     * 
     * This Strind must have the split indices at the front.
     * Esta cadena debe tener los índices de división en la parte delantera.
     * 
     * Use {@link #toIndexedString(java.util.Collection, char) } to create parsed.
     * 
     * @param indexedListString The index string to be parsed.
     * @param separator The separator which separator the entries.
     * @return The desired list.
     * 
     * @link #toIndexedString(java.util.Collection, char)
     */
    public static List<String> toIndexString(final String indexedListString, final char separator) {
        final ArrayList<String> list = new ArrayList<>();
        final char EOL = '\n'; // Marcador de lista final.
        final int length = indexedListString.length();
        final int indexEnd = indexedListString.indexOf(EOL) + 1; // Fin del area de indice

        int start = 0;
        int prevIndex = Integer.valueOf(0);
        for (int i = 0; i < length; i++) {
            if (indexedListString.charAt(i) == separator) {
                int index = Integer.valueOf(indexedListString.substring(start, i)).intValue();
                int indexModifier = start == 0 ? 0 : 1; // La primera entrada no tuvo un separador.

                String entry = indexedListString.substring(prevIndex + indexModifier + indexEnd, index + indexEnd);
                prevIndex = index;
                start = i + 1;
                list.add(entry);

            }
        }
        return list;
    }
}
