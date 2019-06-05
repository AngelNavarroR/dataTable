/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.lazy;

import java.io.Serializable;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author Angel Navarro
 * @param <T>
 */
public abstract class LazyData<T> extends JComponent implements Serializable {

    protected int totalPages = 0;
    protected int pageData = 1;
    protected List list;
    protected List dataTemp;
    protected Integer currentPage = 1;
    protected int rows = 20;
    protected Long countData = 0l;

    /**
     * Metodo a sobreescribir para obtener la consulta en la tabla de base de
     * datos
     *
     * @param initPage primer registro a extraer 
     * @param lastPage ultimo registro a cunsultar 
     * @return lista de registro a consultados.
     */
    public List<T> load(int initPage, int lastPage) {
        throw new UnsupportedOperationException("No se Implemantado el motodo load().");
    }

    /**
     *
     * @param totalPages total de paguinas que ingresan en el Tabla
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPaginaInicial() {
        if (currentPage == 1) {
            pageData = 0;
        } else {
            pageData = (currentPage - 1) * rows;
        }
        return pageData;
    }

    /**
     * Metodo a sobreescribir para obtener la cantidad de registros que hay en
     * la tabla
     *
     * @return
     */
    public Long getCountData() {
        return countData;
    }

    public void setCountData(Long countData) {
        this.countData = countData;
    }

}
