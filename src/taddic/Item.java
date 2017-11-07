/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taddic;

import java.util.Objects;

/**
 *
 * @author 20121bsi0040
 */
public class Item {
    private Object chave;
    private Object elemento;
    private int calculohash;

    public void setCalculohash(int calculohash) {
        this.calculohash = calculohash;
    }

    public int getCalculohash() {
        return calculohash;
    }
    
    public Item(Object chave, Object elemento, int calculoHash){
        this.chave = chave;
        this.elemento = elemento;
    }

    public Object getChave() {
        return chave;
    }

    public void setChave(Object chave){
        this.chave = chave;
    }
    
    public Object getElemento() {
        return elemento;
    }
    
    public void setElemento(Object elemento){
        this.elemento = elemento;
    }

    @Override
    public boolean equals(Object obj) {
        Item item2 = (Item) obj;
        return this.getChave() == item2.getChave() && this.getElemento() == item2.getElemento();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.chave);
        hash = 37 * hash + Objects.hashCode(this.elemento);
        return hash;
    }

    @Override
    public String toString(){
        return "Chave: " + this.chave + " - Elemento: " + this.elemento;
    }
    
    
}
