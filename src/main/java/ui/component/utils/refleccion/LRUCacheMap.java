/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.utils.refleccion;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author supergold Provides support for a LRU (least recently used) cache.
 * This cache is not thread safe and would need to wrap with
 * Collections.synchronizedMap() if thread safety is needed.
 */
public class LRUCacheMap<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 4709696324495431394L;

    private final int maxSize;

    /**
     * @param maxSize maximum number of entries.
     */
    public LRUCacheMap(final int maxSize) {
        super(maxSize * 4 / 3, 0.75f, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }
}
