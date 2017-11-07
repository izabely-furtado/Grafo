/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 20121bsi0040
 */
public class GrafoMAdj {

    int mat[][]; // fazer um redimenciona
    // a matriz armazena o id da aresta e nao o valor... peso... enfim
    LinkedList<Vertice> vetV; // transformar em dicionario
    List<Edge> vetA; //transformar em dicionario 
    // o objeto arresta e o objeto e vertice são armazenados nos seus repectivos dicionarios/listas
    //objetos vertices e arestas nao se comunicam
    List<Integer> deletados;
    
    
    GrafoMAdj(int quantVertices){
        this.vetA = new ArrayList<>();	
        this.vetV = new LinkedList<>();
        this.deletados = new LinkedList<>();
        this.mat = new int[quantVertices][quantVertices];
    }
    
    GrafoMAdj(){
        this.vetA = new ArrayList<>();
        this.vetV = new LinkedList<>();
        this.mat = new int[69][69];
    }
    
    public void set(int v1, int v2, int a){
        this.mat[v1][v2] = this.vetA.get(a).id;
    }
    
    public void setAresta(Edge a){
        this.vetA.add(a.id, a);
    }
    
    public void setVetice(Vertice v){
        if(this.vetV.size() >= v.id){
            this.vetV.add(v);
        }
    }
    
    public void remove(int v1, int v2){
        this.mat[v1][v2] = 0;
    }
    
    public void grauAdjacencia(int v1){
        int[] adj = this.mat[v1];
        int grau = 0;
        for(int a : adj){
            if(a != 0){
                grau++;
            }
        }
    }
    
    public void grauEntrada(int v1){
        int grau = 0;
        for(int[] a : this.mat){
            if(a[v1] != 0){
                grau++;
            }
        }
    }
    
    public void grauSaida(int v1){
        int[] adj = this.mat[v1];
        int grau = 0;
        for(int a : adj){
            if(a != 0){
                grau++;
            }
        }
    }

    public int existe(int v1){
        for (Vertice v : this.vetV){
            if(v.id == v1){
                return v.id;
            }
        }
        return -1;
    }
    
    public boolean adjacente(int v1, int v2){
        return this.mat[v1][v2] != 0;
    }
    
    public boolean adjacente(Vertice v1, Vertice v2){
        return this.mat[v1.id][v2.id] != 0;
    }
    
    public Vertice getVertice(int id){
        for(Vertice v : this.vetV){
            if(v.id == id){
                return v;
            }
        }
        return null;
    }
    
    public int size(){
        return this.vetV.size();
    }
    
    public List<Vertice> adjacenteVertices(Vertice v1){
        List<Vertice> lstAdjacentes = new LinkedList<>();
        for(int vertice : this.mat[v1.id]){
            if(vertice != 0){
                lstAdjacentes.add(this.getVertice(vertice));
            }
        }
        return lstAdjacentes;
    }
    
    //fazer incidentes.. q chegam até o vertice
    
    public boolean insereVertex(Vertice v){
        return false;
    }
    
    public boolean removeVertice(Vertice v){
        return false;
    }
    
    public boolean existeCaminho(int v1, int v2){
        if(adjacente(v1,v2)){
            return true;
        }
        else {
            for(int vertice : this.mat[v1]){
                if(vertice != 0){
                    existeCaminho(vertice, v2);
                }
            }
        }
        return false;
    }
    
    public LinkedList<Vertice> getVertices(){
        return this.vetV;
    }
    
    public List<Edge> getArestas(){
        return this.vetA;
    }
    
    @Override
    public String toString(){
        String retorno = "";
        for (int[] mat1 : this.mat) {
            for (int v2 = 0; v2 < mat1.length; v2++) {
                retorno += mat1[v2] + " ";
            }
            retorno += "\n";
        }
        return retorno;
    }
    
    //retorna a partir do vertices pra quais locais pode-se ir diretamente
    public LinkedList<Vertice> adjacentesVertices(Vertice v){
        LinkedList<Vertice> lstAdjacentes = new LinkedList<>();
        for(int coluna = 0; coluna < dicVertices.size(); coluna++){
            if((this.mat[coluna][v.getId()] != -1) && !deletado(coluna)){
                Edge aux = (Edge)dicEdiges.finElement(coluna);
                if(aux != null){
                    lstAdjacentes.add(aux);
                }
            }
        }
        return lstAdjacentes;
    }
        
    public LinkedList<Vertice> incidentesVertices(Vertice v){
        LinkedList<Vertice> lstIncidentes = new LinkedList<>();
        for(int coluna = 0; coluna < dicEdges.size(); coluna++){
            if((this.mat[v.getId()][coluna] != -1) && !deletado(coluna)){
                Edge aux = (Edge)dicEdiges.finElement(this.mat[v.getId()][coluna]);
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
                    vertices.add(this.getVertice(linha)); // (Vertice) dicVertices.findElement(linha);
                    vertices.add(this.getVertice(coluna));
                }
            }
        }
        return vertices;
    }
    
    public Vertice verticeOposto(Vertice v, Edge a){
        for(int coluna = 0; coluna<this.mat[v.id].length; coluna++){
            if(mat[v.id][coluna] == a.id){
                return this.getVertice(coluna); // (Vertice) dicVertices.findElement(coluna);
            }
        }
        return null;
    }
    
    public boolean existeAdjacente(Vertice v1, Vertice v2){
        return this.mat[v1.id][v2.id] == -1;
    }
    
}
