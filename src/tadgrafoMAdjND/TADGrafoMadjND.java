/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafoMAdjND;

import java.util.LinkedList;

import abstractGrafo.Edge;
import abstractGrafo.GrafoTPA;
import abstractGrafo.Vertice;

/**
 *
 * @author 20121bsi0040
 */
public class TADGrafoMadjND extends GrafoTPA {
	protected int[][] mat;
	protected LinkedList<Integer> lstDeletados;

	public TADGrafoMadjND() {
		super();
		this.mat = new int[N][N];
		this.lstDeletados = new LinkedList<>();
	}

	public TADGrafoMadjND(int quantidade) {
		super(quantidade);
		this.mat = new int[N][N];
		this.lstDeletados = new LinkedList<>();

	}

	@Override
	public int degree(Vertice v) {
		int grau = 0;
		for (int coluna = 0; coluna < this.dicVertices.size(); coluna++) {
			if (mat[v.getId()][coluna] != 0 && !this.deletado(coluna)) {
				grau++;
			}
		}
		return grau;
	}

	@Override
	public LinkedList<Vertice> adjacenteVertices(Vertice v) {
		LinkedList<Vertice> lstAdjacentes = new LinkedList<>();
		for (int coluna = 0; coluna < this.dicVertices.size(); coluna++) {
			Vertice aux = (VerticeMadj) this.dicVertices.findElement(coluna);
			if (this.mat[v.getId()][coluna] != 0 && !deletado(coluna)) {
				if (aux != null) {
					lstAdjacentes.add(aux);
				}
			}
		}
		return lstAdjacentes;
	}

	@Override
	public LinkedList<Edge> incidenteEdges(Vertice v) {
		LinkedList<Edge> lstIncidentes = new LinkedList<>();
		for (int coluna = 0; coluna < this.dicVertices.size(); coluna++) {
			if (this.mat[v.getId()][coluna] != 0 && !deletado(coluna)) {
				Edge aux = (EdgeMadj) this.dicEdges.findElement(this.mat[v.getId()][coluna]);
				if (aux != null) {
					lstIncidentes.add(aux);
				}
			}
		}
		return lstIncidentes;
	}

	@Override
	public LinkedList<Vertice> endVertices(Edge a) {
		LinkedList<Vertice> vertices = null;
		for (int linha = 0; linha < dicVertices.size(); linha++) {
			for (int coluna = 0; coluna < dicVertices.size(); coluna++) {
				if (mat[linha][coluna] == a.getId()) {
					vertices = new LinkedList<>();
					vertices.add((VerticeMadj) dicVertices.findElement(linha));
					vertices.add((VerticeMadj) dicVertices.findElement(coluna));
				}
			}
		}
		return vertices;
	}

	@Override
	public Vertice opposite(Vertice v, Edge a) {
		for (int coluna = 0; coluna < this.mat[v.getId()].length; coluna++) {
			if (mat[v.getId()][coluna] == a.getId()) {
				return (VerticeMadj) dicVertices.findElement(coluna);
			}
		}
		return null;
	}

	@Override
	public boolean areAdjacent(Vertice v, Vertice w) {
		return this.mat[v.getId()][w.getId()] != 0;
	}

	@Override
	public Vertice insertVertex(Object o) {
		Vertice v = new VerticeMadj(this.geraId(), o, "");
		this.dicVertices.insertItem(v.getId(), v);
		return v;
	}

	@Override
	public Object removeVertex(Vertice v) {
		Object aux = v.getDado();
		for (int i = 0; i < this.N; i++) {
			if (!this.lstDeletados.contains(i)) {
				int idEdge = this.mat[v.getId()][i];
				this.dicEdges.removeElement(idEdge);
				this.mat[v.getId()][i] = -1;
				this.mat[i][v.getId()] = -1;
			}
		}
		//removendo da lista de vertices
		this.dicVertices.removeElement(v.getId());
		return aux;
	}

	
	@Override
	public Edge insertEdge(Vertice v, Vertice w, Object o) {
		if (this.idGenerator < this.mat.length * 0.8) {
			int idEdge = v.getId() * 7 + w.getId() * 11;
			Edge e = new EdgeMadj(idEdge, o, "");
			this.mat[v.getId()][w.getId()] = idEdge;
			this.mat[w.getId()][v.getId()] = idEdge;
			this.dicEdges.insertItem(idEdge, e);
			return e;
		} else {
			this.redimensiona();
			return this.insertEdgeSemRedimensiona(v, w, o);
		}
	}

	@Override
	public Object removeEdge(Edge e) {
		Object aux = e.getDado();
		//removendo da matriz
		for (int linha = 0; linha < this.dicVertices.size(); linha++) {
			for (int coluna = 0; coluna < this.dicVertices.size(); linha++) {
				if (e.getId() == this.mat[linha][coluna]) {
					//excluindo da matriz
					this.mat[linha][coluna] = -1;
					this.mat[coluna][linha] = -1;
				}
			}
		}
		//removendo da lista de 
		return aux;
	}
	
	
	private void redimensiona() {
		int[][] matrizAntiga = this.mat;
		if (this.idGenerator > this.mat.length * 0.8) {
			this.N = (int) (N * 1.5);
			this.mat = new int[N][N];
			// this.idGenerator = 0;
			for (int linha = 0; linha < matrizAntiga.length; linha++) {
				System.arraycopy(matrizAntiga[linha], 0, this.mat[linha], 0, matrizAntiga[linha].length);
			}
		}
	}

	private Edge insertEdgeSemRedimensiona(Vertice v, Vertice w, Object o) {
		int idEdge = v.getId() * 7 + w.getId() * 11;
		Edge e = new EdgeMadj(idEdge, o, "");
		this.mat[v.getId()][w.getId()] = idEdge;
		this.mat[w.getId()][v.getId()] = idEdge;
		this.dicEdges.insertItem(idEdge, e);
		return e;
	}

	// MÉTODOS USADOS POR FUNÇÕES PRINCIPAIS
	protected boolean deletado(int id) {
		return this.lstDeletados.contains(id);
	}
	
	/* EM PROCESSO */
	// busca em altura 
	public LinkedList<Vertice> buscaDFS(GrafoTPA g, Vertice s) {
		LinkedList<Vertice> visitados = new LinkedList<>();
		
		return visitados;
	}

	// busca em largura
	public LinkedList<Vertice> buscaBFS(GrafoTPA g, Vertice s) {
		LinkedList<Vertice> visitados = new LinkedList<>();

		return visitados;
	}

	protected int geraId() {
		int id; // = 0;
		if (!this.lstDeletados.isEmpty()) {
			id = this.lstDeletados.pollFirst();
		} else {
			id = this.idGenerator;
			this.idGenerator++;
		}
		return id;
	}
	/*
	 * @Override public String toString(){ String retorno = ""; for (int linha =
	 * 0; linha < this.mat.length; linha++){ for (int coluna = 0; coluna <
	 * this.mat[linha].length; coluna++){ //retorno +=
	 * this.dicEdges.findElements(this.mat[linha][coluna]) + " "; int val =
	 * this.mat[linha][coluna]; if(val != 0){ try{ Edge aresta = (Edge)
	 * this.dicEdges.findElements(val); retorno += aresta.getDado() + " "; }
	 * catch(Exception e){ Item it = (Item) this.dicEdges.findElements(val);
	 * retorno += it.getElemento(); } //Edge aresta = (Edge)
	 * this.dicEdges.findElements(val); //Item it = (Item)
	 * this.dicEdges.findElements(val); //retorno +=
	 * this.dicEdges.findElements(val) + " "; } else { retorno +=
	 * this.mat[linha][coluna] + " "; } } retorno += "\n"; } return retorno; }
	 */

}
