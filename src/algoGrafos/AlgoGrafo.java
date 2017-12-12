package algoGrafos;

import java.util.Collections;
import java.util.LinkedList;

import abstractGrafo.Edge;
import abstractGrafo.GrafoTPA;
import abstractGrafo.Vertice;
import tabHash.TabHEA;

public class AlgoGrafo {

	private static Vertice getVertice(GrafoTPA g, String label) {
		Vertice retorno = null;
		for (Vertice v : g.vertices()) {
			if (v.getLabel() == label) {
				retorno = v;
			}
		}
		return retorno;
	}

	public static LinkedList<Vertice> DFS(GrafoTPA g, String lbVOrigem) {
		Vertice vertice = AlgoGrafo.getVertice(g, lbVOrigem);
		//verificando se existe
		if (vertice == null) {
			System.out.println("Label n�o corresponde a um Vertice");
			return null;
		}

		LinkedList<Vertice> visitados = new LinkedList<>();
		LinkedList<Vertice> pilha = new LinkedList<>();
		pilha.add(vertice);
		
		while (!pilha.isEmpty()) {
			Vertice v = pilha.remove(pilha.size() - 1);
			LinkedList<Vertice> adjacentes = g.adjacenteVertices(v);
			//se ainda nao foi visitado... � visitado
			if (!visitados.contains(v)) {
				visitados.add(v);
			}
			//visitando adjacentes
			for (Vertice adjacente : adjacentes) {
				if (!visitados.contains(adjacente)) {
					pilha.add(adjacente);
				}
			}
		}

		return visitados;

	}

	// busca em largura
	public static LinkedList<Vertice> BFS(GrafoTPA g, String lbVOrigem) {
		Vertice vertice = AlgoGrafo.getVertice(g, lbVOrigem);
		
		LinkedList<Vertice> visitados = new LinkedList<>();
		LinkedList<Vertice> filas = new LinkedList<>();

		int ini = 0, fim = 0;
		visitados.add(vertice);
		filas.add(vertice);
		fim++;
		while (ini != fim) {
			Vertice no = filas.get(ini++);

			// Processa_No(no);

			for (Vertice adj : g.adjacenteVertices(no)) {
				if (!visitados.contains(adj)) {
					fim++;
					filas.add(adj);
					visitados.add(adj);
				}
			}
		}

		return visitados;
	}

	// Algoritmo de Dijkstra
	//public static LinkedList<Vertice> dijkstra(GrafoTPA g, Vertice lbVOrigem, Vertice lbVDestino) {
	public static LinkedList<Vertice> dijkstra(GrafoTPA g, String lbVOrigem, String lbVDestino) {

		Vertice vOrigem = AlgoGrafo.getVertice(g, lbVOrigem);
		Vertice vDestino = AlgoGrafo.getVertice(g, lbVDestino);
		
		TabHEA pais = new TabHEA();
		LinkedList<Vertice> menorCaminho = new LinkedList<Vertice>();
		// Variavel que recebe os vertices pertencentes ao menor caminho
		Vertice verticeCaminho;
		Vertice atual;
		Vertice vizinho;
		LinkedList<Vertice> naoVisitados = new LinkedList<>();

		// Adiciona a origem na lista do menor caminho
		menorCaminho.add(vOrigem);

		// Colocando a distancias iniciais
		for (Vertice v : g.vertices()) {
			// o dado, neste caso, esta guardando a distancia
			// Vertice atual tem distancia zero, e todos os outros,
			// 9999("infinita")
			if (v.getId() == vOrigem.getId()) {
				v.setDado(0);
			} else {
				v.setDado(9999);
			}
			// Insere o vertice na lista de vertices nao visitados
			naoVisitados.add(v);
		}
		// ordenando os nao visitados pela distancia / dado
		Collections.sort(naoVisitados);

		// O algoritmo continua ate que todos os vertices sejam visitados
		while (!naoVisitados.isEmpty()) {

			// Toma-se sempre o vertice com menor distancia, que eh o primeiro
			// da lista

			atual = naoVisitados.get(0);
			// System.out.println("Pegou esse vertice: " + atual.getLabel());
			/*
			 * Para cada vizinho (cada aresta), calcula-se a sua possivel
			 * distancia, somando a distancia do vertice atual com a da aresta
			 * correspondente. Se essa distancia for menor que a distancia do
			 * vizinho, esta eh atualizada.
			 */
			for (Edge e : g.incidenteEdges(atual)) {
				vizinho = g.opposite(atual, e);
				// System.out.println("Olhando o vizinho de " + atual + ": " +
				// vizinho);
				if (naoVisitados.contains(vizinho)) {
					// Comparando a distância do vizinho com a possível
					// distância = dado
					// atual.getArestas().get(i).getPeso())) { //0 já que as
					// arestas analisadas nao possuem peso
					if(e.getDado() == null){
						e.setDado(1);
					}
					if (Integer.parseInt(vizinho.getDado() + "") > (Integer.parseInt(atual.getDado() + "")
							+ Integer.parseInt(e.getDado() + ""))) {

						vizinho.setDado(Integer.parseInt(atual.getDado() + "") + Integer.parseInt(e.getDado() + ""));
						// setando pai do vertice... ou... anterior na ordem de
						// passagem
						pais.insertItem(vizinho.getId(), atual);

						/*
						 * Se o vizinho eh o vertice procurado, e foi feita uma
						 * mudanca na distancia, a lista com o menor caminho
						 * anterior eh apagada, pois existe um caminho menor
						 * vertices pais, ate o vertice origem.
						 */
						if (vizinho.getId() == vDestino.getId()) {
							menorCaminho.clear();
							verticeCaminho = vizinho;
							menorCaminho.add(vizinho);
							Vertice pai = (Vertice) pais.findElement(verticeCaminho.getId());
							while (pai.getLabel() != "") {
								menorCaminho.add(pai);
								// pais.removeElement(verticeCaminho.getId());
								verticeCaminho = pai;
								try {
									pai = (Vertice) pais.findElement(verticeCaminho.getId());
								} catch (Exception exc) {
									break;
								}
								// pais.removeElement(verticeCaminho.getId());
							}
							// Ordena a lista do menor caminho, para que ele
							// seja exibido da origem ao destino.
							Collections.sort(menorCaminho);

						}
					}
				}
			}

			// Marca o vertice atual como visitado e o retira da lista de nao
			// visitados
			naoVisitados.remove(atual);
			/*
			 * Ordena a lista, para que o vertice com menor distancia fique na
			 * primeira posicao
			 */

			Collections.sort(naoVisitados);
			// System.out.println("Nao foram visitados ainda:" + naoVisitados);

		}

		return menorCaminho;
	}

}
