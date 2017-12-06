/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractGrafo;

/**
 *
 * @author 20121bsi0040
 */
public abstract class Vertice {
    int id;
    Object dado;
    String label;
    
    public Vertice(){
        
    }
    public Vertice(int id, Object dado, String label){
        this.id = id;
        this.dado = dado;
        this.label = label;
    }
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object getDado() {
		return dado;
	}
	public void setDado(Object dado) {
		this.dado = dado;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Vertice(int id){
        this.id = id;
    }
}
