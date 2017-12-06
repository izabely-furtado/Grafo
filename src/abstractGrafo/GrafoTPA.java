/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractGrafo;

import java.util.LinkedList;
import java.util.List;
import taddic.TADDicChain;

/**
 *
 * @author 20121bsi0040
 */
public abstract class TADGrafoAbs {
    protected int N;
    protected int idGenerator = 0;
    protected TADDicChain dicVertices;
    protected TADDicChain dicEdges;
    

    public TADGrafoAbs(){
        this.N = 60;
        this.idGenerator = 0;
        this.dicEdges = new TADDicChain();
        this.dicVertices = new TADDicChain();
    }
    
    public TADGrafoAbs(int quantidade){
        this.N = quantidade;
        this.idGenerator = 0;
        this.dicEdges = new TADDicChain();
        this.dicVertices = new TADDicChain();
    }
    
    public int numVertices(){
        return this.dicVertices.size();
    }
    
    public int numEdges(){
        return this.dicVertices.size();
    }
    
    public LinkedList<Vertice> vertices(){
        LinkedList<Vertice> lstVertices = new LinkedList<>();
        List<Object> aux = this.dicVertices.elements();
        for(int i = 0; i < this.dicVertices.size(); i++){
            lstVertices.add((Vertice)aux.get(i));
        }
        return lstVertices;
    }
    
    public LinkedList<Edge> edges(){
        LinkedList<Edge> lstEdges = new LinkedList<>();
        List<Object> aux = this.dicEdges.elements();
        for(int i = 0; i < this.dicEdges.size(); i++){
        	lstEdges.add((Edge)aux.get(i));
        }
        return lstEdges;
    }
    
    
    public abstract int degree(Vertice v);	
    public abstract LinkedList<Vertice> adjacenteVertices(Vertice v);
    public abstract LinkedList<Edge> incidenteEdges(Vertice v);
    public abstract LinkedList<Vertice> endVertices(Edge e);
    public abstract Vertice opposite(Vertice v, Edge e);
    public abstract boolean areAdjacent(Vertice v, Vertice w);
    public abstract Vertice insertVertex(Object o);
    public abstract Object removeVertex(Vertice v);
    public abstract Edge insertEdge(Vertice v, Vertice w, Object o);
    public abstract Object removeEdge(Edge e);
    public abstract void carrega(String nome_arq_grafo);
    
    
    public void carrega(String arq_grafo_tga){
        BufferedReader br = null;
        String[] vet;
        //GrafoTPA g = new GrafoTPA
        
        try {
            br = new BufferedReader(new FileReader(arq_grafo_tga));
            String linha = br.readline();
            String vet_split = null;
            
            //carrega todos os vertices do arquivo de grafo no formato TGA
            while ((linha != null) && !linha.trim().equals("#")) {
                vet_split =linha.split(" ", 2);
                Vertice v = this.insertVertex(null);//vet_split[1]
                v.setLabel(vet_split[1]);//vet_split[0]
                linha = br.readline();
            }
            
            //carrega todos as arestas do arquivo de grafo no formato TGA
            
            linha = br.readline();
            while (linha != null) {
                vet_split = linha.split(" ", 3);
                Vertice v = (Vertice)dicvertices.findElement(Integer.parseInt(vet_split[0]));
                Vertice w = (Vertice)dicvertices.findElement(Integer.parseInt(vet_split[1]));
                Edge e = this.insertEdge(v, w, null);
                if(vet_split.len == 3){
                    e.setLabel(vet_split[2]);
                }
                linha = br.readline();
            }// while
            br.close();
        }
        catch (IOException ex) {
            throw new RuntimeException("arquivo nao encontrado");
        }//fim catch
        
    }//fim carrega
        
      
            
    
}
