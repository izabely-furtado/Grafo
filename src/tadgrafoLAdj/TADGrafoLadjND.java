/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafoLAdj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import abstractGrafo.Edge;
import abstractGrafo.TADGrafoAbs;
import abstractGrafo.Vertice;
import taddic.Item;
import taddic.TADDicChain;

/**
 *
 * @author 20121bsi0040
 */
public class TADGrafoLadjND extends TADGrafoAbs {

	public TADGrafoLadjND(int quantVertices) {
		super(quantVertices);
	}

	public TADGrafoLadjND() {
		super();
	}

	@Override
	public int degree(Vertice v) {
		VerticeLadj aux = (VerticeLadj) v;
		return aux.dicEdges.size();
	}

	@Override
	public LinkedList<Vertice> adjacenteVertices(Vertice v) {
		LinkedList<Vertice> lstAdjacentes = new LinkedList<>();
		VerticeLadj aux = (VerticeLadj) v;
		for (Object obj : aux.dicEdges.elements()) {
			EdgeLadj edge = (EdgeLadj) obj;
			// existem 2
			List<Object> verticesAresta = edge.dicVertices.elements();
			Vertice v2 = (VerticeLadj) verticesAresta.get(0);
			if (v2.getId() == v.getId()) {
				v2 = (VerticeLadj) verticesAresta.get(1);
			}
			lstAdjacentes.add(v2);
			
		}
		return lstAdjacentes;
	}

	// que chegam até o vertice
	@Override
	public LinkedList<Edge> incidenteEdges(Vertice v) {
		LinkedList<Edge> lstIncidentes = new LinkedList<>();
		VerticeLadj aux = (VerticeLadj) v;
		for (Object obj : aux.dicEdges.elements()) {
			EdgeLadj edge = (EdgeLadj) obj;
			lstIncidentes.add(edge);
		}
		return lstIncidentes;
	}

	@Override
	public LinkedList<Vertice> endVertices(Edge e) {
		LinkedList<Vertice> lstEnds = new LinkedList<>();
		EdgeLadj edge = (EdgeLadj) e;
		for (Object obj : edge.dicVertices.elements()) {
			VerticeLadj aux = (VerticeLadj) obj;
			lstEnds.add(aux);
		}
		return lstEnds;
	}

	@Override
	public Vertice opposite(Vertice v, Edge e) {
		LinkedList<Vertice> lstEnds = this.endVertices(e);
		if (lstEnds.size() == 2 && !lstEnds.contains(v)) {
			Vertice v2 = (VerticeLadj) lstEnds.get(0);
			if (v2.getId() == v.getId()) {
				v2 = (VerticeLadj) lstEnds.get(1);
			}
			return v2;
		} else {
			throw new RuntimeException("O vertice não é ligado a aresta, portanto, não possui vertice oposto");
		}
	}

	@Override
	public boolean areAdjacent(Vertice v, Vertice w) {
		return this.adjacenteVertices(v).contains(w);
	}

	@Override
	public Vertice insertVertex(Object o) {
		Vertice v = new VerticeLadj(this.geraId(), o, "");
		this.dicVertices.insertItem(v.getId(), v);
		return v;
	}

	@Override
	public Object removeVertex(Vertice v) {
		Object aux = v.getDado();
		VerticeLadj vaux = (VerticeLadj) v;
		// removendo as arestas do vertice
		for (Object e : vaux.dicEdges.elements()) {
			EdgeLadj eaux = (EdgeLadj) e;
			this.removeEdge(eaux);
		}
		// removendo o vertice em si
		this.dicVertices.removeElement(v.getId());
		return aux;
	}

	@Override
	public Edge insertEdge(Vertice v, Vertice w, Object o) {
		//criando a aresta
		EdgeLadj e = new EdgeLadj(this.geraId(), o, "");
		e.setVertexes(v, w);
		
		//atrelando a aresta criada aos vertices
		VerticeLadj vaux = (VerticeLadj) this.dicVertices.findElements(v.getId());
		vaux.dicEdges.insertItem(e.getId(), e);
		//this.dicVertices.removeElement(v.getId());
		this.insertVertex(vaux);
		VerticeLadj waux = (VerticeLadj) this.dicVertices.findElements(w.getId());
		waux.dicEdges.insertItem(e.getId(), e);
		//this.dicVertices.removeElement(w.getId());
		this.insertVertex(waux);
		//inserindo a aresta em si
		this.dicEdges.insertItem(e.getId(), e);
		return e;
	}

	@Override
	public Object removeEdge(Edge e) {
		Object aux = e.getDado();
		EdgeLadj eaux = (EdgeLadj) e;
		// removendo a representação da aresta nos vertices vinculados
		for (Object v : eaux.dicVertices.elements()) {
			VerticeLadj vaux = (VerticeLadj) v;
			vaux.dicEdges.removeElement(e.getId());
		}
		// removendo a aresta em si
		this.dicEdges.removeElement(e.getId());
		return aux;

	}

	@Override
	public void carrega(String nomeArquivo) {
		boolean acabouVertices = false;
		try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
			while (br.ready() && !acabouVertices) {
				String linha = br.readLine();
				if ("#".equals(linha)) {
					acabouVertices = true;
				} else if (!acabouVertices) {
					String[] conteudo = linha.split(" ");
					if (conteudo.length == 3) {
						Vertice v = new VerticeLadj(Integer.parseInt(conteudo[0]), conteudo[1], conteudo[2]);
						this.insertVertex(v);
					}
					if (conteudo.length == 2) {
						Vertice v = new VerticeLadj(Integer.parseInt(conteudo[0]), conteudo[1], null);
						this.insertVertex(v);
					}
				}
			}
			while (br.ready() && acabouVertices) {
				String linha = br.readLine();
				String[] conteudo = linha.split(" ");
				int v1 = Integer.parseInt(conteudo[0]);
				int v2 = Integer.parseInt(conteudo[1]);
				Vertice vertice1 = (Vertice) this.dicVertices.findElements(v1);
				Vertice vertice2 = (Vertice) this.dicVertices.findElements(v2);
				this.insertEdge(vertice1, vertice2, 1);
			}
		} catch (IOException ex) {
			Logger.getLogger(TADGrafoLadjND.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println(this);
	}

	protected int geraId() {
		int id; // = 0;
		id = this.idGenerator;
		this.idGenerator++;
		return id;
	}
	/*
	 * public String toString(){ String retorno = ""; for (int[] mat1 :
	 * this.mat) { for (int v2 = 0; v2 < mat1.length; v2++) { retorno +=
	 * mat1[v2] + " "; } retorno += "\n"; } return retorno; }
	 */
}
