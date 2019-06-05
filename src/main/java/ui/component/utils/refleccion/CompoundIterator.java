/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.component.utils.refleccion;

import java.util.Iterator;

/**
 * Maps two iterators into one without copy. 
 * @param <E>
 */ 
public class CompoundIterator<E> implements Iterator<E> { 
 
 private final Iterator<E> first; 
  
 private final Iterator<E> second; 
  
 private boolean processFirst = true; 
  
 public CompoundIterator(Iterator<E> first, Iterator<E> second) { 
  this.first = first; 
  this.second = second; 
 } 
  
 @Override 
 public boolean hasNext() { 
  if(processFirst && first.hasNext()) { 
   return true; 
  } else if(second.hasNext()) { 
   processFirst = false; 
   return true; 
  } 
  return false; 
 } 
 
 @Override 
 public E next() { 
  if(processFirst && first.hasNext()) { 
   final E next = first.next(); 
   if(!first.hasNext()) { 
    processFirst = false; 
   } 
   return next; 
  } else if(second.hasNext()) { 
   return second.next(); 
  } 
  return null; 
 } 
 
 @Override 
 public void remove() { 
  if(processFirst) { 
   first.remove(); 
  } else  { 
   second.remove(); 
  }   
 } 
 
}
