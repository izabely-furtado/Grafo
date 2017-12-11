/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabHash;

/**
 *
 * @author 20121bsi0040
 */
public class ItemTabHash {
	private Object k;
	private Object elem;
	private int cacheCodHash;
	
	public int getCacheCodHash() {
		return cacheCodHash;
	}

	public void setCacheCodHash(int cacheCodHash) {
		this.cacheCodHash = cacheCodHash;
	}

	public ItemTabHash(Object pk, Object pelem){
		k = pk;
		elem = pelem;
	}
	
	public ItemTabHash(Object pk, Object pelem, int cacheCodHash){
		k = pk;
		elem = pelem;
		this.cacheCodHash = cacheCodHash;
	}
	
	public Object getK(){
		return k;
	}
	
	public Object getElem(){
		return elem;
	}
	
	public void setElem(Object pelem){
		elem = pelem;
	}
}

