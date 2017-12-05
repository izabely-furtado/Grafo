/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taddic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20121bsi0040
 */
public class TADDicChain extends TADDicionario{

    private List<Item>[] vetBulckets;
    
    @SuppressWarnings("unchecked")
	public TADDicChain(int tamanho, HashEngine he) {
        super(tamanho, he);
    	this.vetBulckets = new List[tamanho];
    }

    @SuppressWarnings("unchecked")
	public TADDicChain(HashEngine he) {
    	super(he);
        this.vetBulckets = new List[100];
        this.tamanho = 0;
        this.N = 100;
    }
    
    @SuppressWarnings("unchecked")
	public TADDicChain() {
        super(new HashEngineApp());
        this.vetBulckets = new List[100];
        this.tamanho = 0;
        this.N = 100;
    }
    
    @Override
    public Object findElements(Object chave) {
        int i = this.dicEngine.hashCode(chave);
        List<Item> listc = this.vetBulckets[i];
        if (listc == null) {
            return TADDicChain.NO_SUCH_KEY;
        } else {
            for (Item c : listc) {
                if (chave.equals(c.getChave())) {
                    return c.getElemento();
                }
            }
            return TADDicChain.NO_SUCH_KEY;
        }
    }

    @Override
    public Object insertItem(Object chave, Object obj) {
        int hash = this.dicEngine.hashCode(chave);
        boolean existeChave = false;
        int index = hash % this.vetBulckets.length;
        if (this.vetBulckets[index] == null) {
            this.vetBulckets[index] = new ArrayList<>();
            this.vetBulckets[index].add(new Item(chave, obj, hash));
            this.tamanho ++;
        } else {
            for(Item c : this.vetBulckets[index]){
                if(c.getChave().equals(chave)){
                    existeChave = true;
                    c.setElemento(obj);
                }
            }
            if(!existeChave){
                this.vetBulckets[index].add(new Item(chave, obj, hash));
                this.tamanho++;
            }
        }
        return null;
    }

    @Override
    public Object removeElement(Object chave) {
        int index = this.dicEngine.hashCode(chave);
        Item item = null;
        if(index != -1 && this.vetBulckets[index] != null){
            for(int i = 0; i < this.vetBulckets[index].size(); i++){
                if(this.vetBulckets[index].get(i).getChave().equals(chave)){
                    item = this.vetBulckets[index].get(i);
                    this.vetBulckets[index].remove(i);
                }
            } 
        }
        return item;
    }

    @Override
    public List<Object> keys() {
        List<Object> chaves = new ArrayList<>();
        for(List<Item> listItem : this.vetBulckets){
            if(listItem != null){
                for (Item item : listItem) {
                    if (item != null) {
                        chaves.add(item.getChave());
                    }
                }
            }
        }
        return chaves;
    }

    @Override
    public List<Object> elements() {
        List<Object> elements = new ArrayList<>();
        for(List<Item> listItem : this.vetBulckets){
            if(listItem != null){
                for (Item item : listItem) {
                    if (item != null && item != TADDicionario.NO_SUCH_KEY) {
                        elements.add(item.getElemento());
                    }
                }
            }
        }
        return elements;
    }
}
