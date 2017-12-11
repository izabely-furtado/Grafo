/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractGrafo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import tabHash.TabHEA;

/**
 *
 * @author 20121bsi0040
 */
public abstract class GrafoTPA {
	protected int N;
	protected int idGenerator = 0;
	protected TabHEA dicVertices;
	protected TabHEA dicEdges;

	public GrafoTPA() {
		this.N = 60;
		this.idGenerator = 0;
		this.dicEdges = new TabHEA();
		this.dicVertices = new TabHEA();
	}

	public GrafoTPA(int quantidade) {
		this.N = quantidade;
		this.idGenerator = 0;
		this.dicEdges = new TabHEA();
		this.dicVertices = new TabHEA();
	}

	public int numVertices() {
		return this.dicVertices.size();
	}

	public int numEdges() {
		return this.dicEdges.size();
	}

	public LinkedList<Vertice> vertices() {
		LinkedList<Vertice> lstVertices = new LinkedList<>();
		List<Object> aux = this.dicVertices.elements();
		for (int i = 0; i < this.dicVertices.size(); i++) {
			lstVertices.add((Vertice) aux.get(i));
		}
		return lstVertices;
	}

	public LinkedList<Edge> edges() {
		LinkedList<Edge> lstEdges = new LinkedList<>();
		List<Object> aux = this.dicEdges.elements();
		for (int i = 0; i < this.dicEdges.size(); i++) {
			lstEdges.add((Edge) aux.get(i));
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
	// public abstract void carrega(String nome_arq_grafo);

	public void carrega(String arq_grafo_tga) {
		BufferedReader br = null;
		// GrafoTPA g = new GrafoTPA

		try {
			br = new BufferedReader(new FileReader(arq_grafo_tga));
			String linha = br.readLine();
			String[] vet_split = null;

			// carrega todos os vertices do arquivo de grafo no formato TGA
			while ((linha != null) && !linha.trim().equals("#")) {
				vet_split = linha.split(" ", 2);
				Vertice v = this.insertVertex(vet_split[0]);// vet_split[1]
				v.setLabel(vet_split[1]);// vet_split[0]
				linha = br.readLine();
			}

			// carrega todos as arestas do arquivo de grafo no formato TGA

			linha = br.readLine();
			while (linha != null) {
				vet_split = linha.split(" ", 3);
				try {
					Vertice v = (Vertice) dicVertices.findElement(Integer.parseInt(vet_split[0])-1);
					Vertice w = (Vertice) dicVertices.findElement(Integer.parseInt(vet_split[1])-1);
					Edge e = this.insertEdge(v, w, null);
					if (vet_split.length == 3) {
						e.setLabel(vet_split[2]);
					}
				} catch (Exception ex) {
					
				}
				linha = br.readLine();
			} // while
			br.close();
		} catch (IOException ex) {
			throw new RuntimeException("arquivo nao encontrado");
		} // fim catch
	}// fim carrega

	public void carrega2(String arq_grafo_tga) {
		BufferedReader br = null;
		// GrafoTPA g = new GrafoTPA

		try {
			br = new BufferedReader(new FileReader(arq_grafo_tga));
			String linha = br.readLine();
			String[] vet_split = null;

			// carrega todos os vertices do arquivo de grafo no formato TGA
			while ((linha != null) && !linha.trim().equals("#")) {
				vet_split = linha.split(" ", 2);
				Vertice v = this.insertVertex(vet_split[0]);// vet_split[1]
				v.setLabel(vet_split[1]);// vet_split[0]
				linha = br.readLine();
			}

			// carrega todos as arestas do arquivo de grafo no formato TGA

			linha = br.readLine();
			while (linha != null) {
				vet_split = linha.split(" ", 3);
				try {
					Vertice v = (Vertice) dicVertices.findElement(Integer.parseInt(vet_split[0])-1);
					Vertice w = (Vertice) dicVertices.findElement(Integer.parseInt(vet_split[1])-1);
					Edge e = this.insertEdge(v, w, null);
					if (vet_split.length == 3) {
						e.setDado(vet_split[2]);
					}
				} catch (Exception ex) {
					
				}
				linha = br.readLine();
			} // while
			br.close();
		} catch (IOException ex) {
			throw new RuntimeException("arquivo nao encontrado");
		} // fim catch
	}// fim carrega

	public void salva(String arq_grafo_tga) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(arq_grafo_tga, "UTF-8");
			// escreve todos os vertices do arquivo de grafo no formato TGA
			for (Vertice v : this.vertices()) {
				writer.println(v.getId() + " " + v.getLabel());
			}
			writer.println("#");
			// escreve todos as arestas do arquivo de grafo no formato TGA
			for (Edge e : this.edges()) {
				LinkedList<Vertice> ends = this.endVertices(e);
				writer.println(ends.get(0).getId() + " " + ends.get(1).getId() + " " + e.getLabel());
			}
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
