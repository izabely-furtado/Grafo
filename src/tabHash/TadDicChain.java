/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabHash;

import java.util.LinkedList;
/**
 *
 * @author 20121bsi0040
 */
public class TadDicChain {
	public static final ItemTabHash NO_SUCH_KEY = new ItemTabHash(null,null);
	private LinkedList<ItemTabHash> [] conteudos;
	private HashEngine hengine = null;
	private int N;
	private int quant = 0;
	
	@SuppressWarnings("unchecked")
	public TadDicChain(int n, HashEngine paramHengine){
		// Fator de carga de 0,85 para tabelas de hashing com encadeamento. Livro texto página 327.
		N = (int)(n/0.85);
		
		// As listas ainda não foram criadas.
		conteudos = (LinkedList<ItemTabHash>[])new LinkedList[N];	
		
		hengine = paramHengine;
	}
	
	@SuppressWarnings("unchecked")
	public TadDicChain(HashEngine paramHengine){
		// Fator de carga de 0,85 para tabelas de hashing com encadeamento. Livro texto página 327.
		N = 2000;
		
		// As listas ainda não foram criadas.
		conteudos = (LinkedList<ItemTabHash>[]) new LinkedList[N];	
		
		hengine = paramHengine;
	}	
	
	@SuppressWarnings("unchecked")
	public TadDicChain(){
		// Fator de carga de 0,85 para tabelas de hashing com encadeamento. Livro texto página 327.
		N = 2000;
		
		// As listas ainda não foram criadas.
		conteudos = (LinkedList<ItemTabHash>[]) new LinkedList[N];	
		
		hengine = new HashEngineDefault();
	}	
	
	// IMPLEMENTE ESTE MÉTODO PARA A PROVA 1 TPA 2017-2
	public boolean insertItem(Object k, Object o){
            int hash = this.hengine.calcCodeHash(k);
            boolean existeChave = false;
            int index = hash % this.conteudos.length;
            if (this.conteudos[index] == null) {
                this.conteudos[index] = new LinkedList<>();
                this.conteudos[index].add(new ItemTabHash(k, o, hash));
                this.quant ++;
                return true;
            } else {
                for(ItemTabHash c : this.conteudos[index]){
                    if(c.getK().equals(k)){
                        existeChave = true;
                        c.setElem(o);
                        return true;
                    }
                }
                if(!existeChave){
                    this.conteudos[index].add(new ItemTabHash(k, o, hash));
                    this.quant++;
                    return true;
                }
            }
            return false;
	} // fim insertItem	
	
	public Object findElement(Object k){
		int pos = hengine.calcCodeHash(k) % N;
		
		if(conteudos[pos] == null)
			return NO_SUCH_KEY;
		
		int i = 0;
		boolean achou = false;
		ItemTabHash aux =  null;
	
		while(i < conteudos[pos].size() && !achou){
			aux = conteudos[pos].get(i);
			achou = (aux.getK()).equals(k);
			i = i + 1;
		}
		
		if(achou)
			return aux.getElem();
		else
			return NO_SUCH_KEY;
	} // fim findElement
	
	public Object removeElement(Object k){
		int pos = hengine.calcCodeHash(k) % N;
		
		if(conteudos[pos] == null)
			return NO_SUCH_KEY;
		else {		
			int i = 0;
			boolean achou = false;
			ItemTabHash aux =  null;
	
			while(i < conteudos[pos].size() && !achou){
				aux = conteudos[pos].get(i);
				achou = (aux.getK()).equals(k);
				i = i + 1;
			}
		
			if(achou){
				Object dado = aux.getElem();
				conteudos[pos].remove(i-1);
				quant = quant - 1;
				return dado;
			}
			else
				return NO_SUCH_KEY;
		}
	} // fim removeElement
	
	public boolean isEmpty(){
		return quant == 0;
	} // fim isEmpty
	
	public int size(){
		return quant;
	} // fim size
	
	public LinkedList<Object> keys(){
		LinkedList<Object> lstKeys = new LinkedList<Object>();
		
		int i = 0;
		ItemTabHash aux =  null;

		while(i < conteudos.length){
			if(conteudos[i] != null){
				int k = 0;
				
				while(k < conteudos[i].size()){
					aux = conteudos[i].get(k);
					lstKeys.add(aux.getK());
					k = k + 1;
				}				
			}
			
			i = i + 1;
		} // while..
			
		return lstKeys;
	} // fim de keys
	
	public LinkedList<Object> elements(){
		LinkedList<Object> lstElements = new LinkedList<Object>();
		
		int i = 0;
		ItemTabHash aux =  null;

		while(i < conteudos.length){
			if(conteudos[i] != null){
				int k = 0;
				
				while(k < conteudos[i].size()){
					aux = conteudos[i].get(k);
					lstElements.add(aux.getElem());
					k = k + 1;
				}				
			}
			
			i = i + 1;
		} // while..
			
		return lstElements;
	} // fim elements
	
} // fim Class TabHChain

