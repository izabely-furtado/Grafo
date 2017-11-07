/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.LinkedList;
import java.util.List;
import taddic.TADDicEA;

/**
 *
 * @author 20121bsi0040
 */
public class TADGrafoMadjND {

    private final static int N = 80;
    private int[][] mat = new int[N][N];
    private int idGenerator = 0;
    TADDicEA dicVertices = new TADDicEA();
    TADDicEA dicEdges = new TADDicEA();
    LinkedList<Integer> lstDeletados = new LinkedList<>();
    
    
    public int numVertices(){
        return this.dicVertices.size();
    }
    
    public int numEdges(){
        return this.dicEdges.size();
    }
    
    public LinkedList<Vertice> vertices(){
        LinkedList<Vertice> lstVertices = new LinkedList<>();
        List<Object> aux = this.dicVertices.elements();
        for(int i = 0; i < this.dicVertices.size(); i++){
            lstVertices.add((Vertice)aux.get(i));
        }
        return lstVertices;
    }
    
    private boolean deletado(int id){
        return this.lstDeletados.contains(id);
    }
    
    public int degree(Vertice v){
        int grau = 0;
         for(int coluna = 0; coluna<this.dicVertices.size(); coluna++){
            if(mat[v.id][coluna] != -1 && !this.deletado(coluna)){
                grau++;
            }
         }
         return grau;
    }
    
    public LinkedList<Vertice> adjacentesVertices(Vertice v){
        LinkedList<Vertice> lstAdjacentes = new LinkedList<>();
        for(int coluna = 0; coluna < this.dicVertices.size(); coluna++){
            if(this.mat[v.id][coluna] != -1 && !deletado(coluna)){
                Vertice aux = (Vertice)this.dicVertices.findElements(coluna);
                if(aux != null){
                    lstAdjacentes.add(aux);
                }
            }
        }
        return lstAdjacentes;
    }
    
    public LinkedList<Edge> incidenteEdges(Vertice v){
        LinkedList<Edge> lstIncidentes = new LinkedList<>();
        for(int coluna = 0; coluna < this.dicVertices.size(); coluna++){
            if(this.mat[v.id][coluna] != -1 && !deletado(coluna)){
                Edge aux = (Edge)this.dicEdges.findElements(this.mat[v.id][coluna]);
                if(aux != null){
                    lstIncidentes.add(aux);
                }
            }
        }
        return lstIncidentes;
    }
    
    public LinkedList<Vertice> endVertices(Edge a){
        LinkedList<Vertice> vertices = null;
        for(int linha = 0; linha<dicVertices.size(); linha++){
            for(int coluna = 0; coluna<dicVertices.size(); coluna++){
                if(mat[linha][coluna] == a.id){
                    vertices = new LinkedList<>();
                    vertices.add((Vertice) dicVertices.findElements(linha)); 
                    vertices.add((Vertice) dicVertices.findElements(coluna));
                }
            }
        }
        return vertices;
    }
    
    public Vertice opposite(Vertice v, Edge a){
        for(int coluna = 0; coluna<this.mat[v.id].length; coluna++){
            if(mat[v.id][coluna] == a.id){
                return(Vertice) dicVertices.findElements(coluna); 
            }
        }
        return null;
    }
    
    public boolean areaAdjacente(Vertice v, Vertice w){
        return this.mat[v.id][w.id] != -1;
    }
    
    public Vertice insertVertex(Object o){
        Vertice v = new Vertice(this.geraId(), o, "");
        this.dicVertices.insertItem(v.id, v);
        return v;        
    }
    
    public Object removeVertex(Vertice v){
        Object aux = v.dado;
        for(int i = 0; i < TADGrafoMadjND.N; i++){
            if(!this.lstDeletados.contains(i)){
                int idEdge = this.mat[v.id][i];
                this.dicEdges.removeElement(idEdge);
                this.mat[v.id][i] = -1;
                this.mat[i][v.id] = -1;
            }
        }
        this.dicVertices.removeElement(v.id);
        return aux;
    }
    
    public Edge insertEdge(Vertice v, Vertice w, Object o){
        int idEdge = v.id*7 + w.id*11;
        Edge e = new Edge(idEdge, o, "");
        this.mat[v.id][w.id] = idEdge;
        this.mat[w.id][v.id] = idEdge;
        this.dicEdges.insertItem(idEdge, e);
        return e;
    }
    
    private int geraId(){
        int id; // = -1;
        if(!this.lstDeletados.isEmpty()){
            id = this.lstDeletados.pollFirst();
        }
        else {
            id = this.idGenerator;
            this.idGenerator++;
        }
        return id;
    }
    
    public Object removeEdge(Edge e){
        Object aux = e.dado;
        for(int linha = 0; linha < this.dicVertices.size(); linha++){
            for(int coluna = 0; coluna < this.dicVertices.size(); linha++){
                if(e.id == this.mat[linha][coluna]){
                    this.mat[linha][coluna] = -1;
                    this.mat[coluna][linha] = -1;
                }
            }
        }
        return aux;
    }
    
    @Override
    public String toString(){
    	String retorno = "";
    	for (int linha = 0; linha < this.mat.length; linha++){
    		for (int coluna = 0; coluna < this.mat[linha].length; coluna++){
    			//retorno += this.dicEdges.findElements(this.mat[linha][coluna]) + " ";
    			int val = this.mat[linha][coluna];
    			if(val != 0){
    				Edge aresta = (Edge) this.dicEdges.findElements(val);
    				retorno += aresta.dado + " ";
    			}
    			else {
    				retorno += this.mat[linha][coluna] + " ";
    			}
    		}
    		retorno += "\n";
    	}
    	return retorno;
    }
   
   
}
