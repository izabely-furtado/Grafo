package algoGrafos;

import java.util.Collections;
import java.util.LinkedList;
import abstractGrafo.Edge;
import abstractGrafo.GrafoTPA;
import abstractGrafo.Vertice;
import taddic.TadDicEA;

public class AlgoGrafo {

	// busca em altura AINDA EM CONSTRUÇÂO
	public static LinkedList<Vertice> buscaDFS(GrafoTPA g, Vertice v) {
		LinkedList<Vertice> visitados = new LinkedList<>();
		LinkedList<Vertice> filas = new LinkedList<>();

		int ini = 0, fim = 0;
		visitados.add(v);
		filas.add(v);
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

	// busca em largura
	public static LinkedList<Vertice> buscaBFS(GrafoTPA g, Vertice v) {
		LinkedList<Vertice> visitados = new LinkedList<>();
		LinkedList<Vertice> filas = new LinkedList<>();

		int ini = 0, fim = 0;
		visitados.add(v);
		filas.add(v);
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
	public static LinkedList<Vertice> caminhoMin(GrafoTPA g, Vertice v1, Vertice v2) {

		TadDicEA pais = new TadDicEA();
		LinkedList<Vertice> menorCaminho = new LinkedList<Vertice>();
		// Variavel que recebe os vertices pertencentes ao menor caminho
		Vertice verticeCaminho;
		Vertice atual;
		Vertice vizinho;
		LinkedList<Vertice> naoVisitados = new LinkedList<>();

		// Adiciona a origem na lista do menor caminho
		menorCaminho.add(v1);

		// Colocando a distancias iniciais
		for (Vertice v : g.vertices()) {
			// o dado, neste caso, esta guardando a distancia
			// Vertice atual tem distancia zero, e todos os outros,
			// 9999("infinita")
			if (v.getId() == v1.getId()) {
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
					if (Integer.parseInt(vizinho.getDado() + "") > (Integer.parseInt(atual.getDado() + "") + 1)) {

						vizinho.setDado(Integer.parseInt(atual.getDado() + "") + 1);
						// setando pai do vertice... ou... anterior na ordem de
						// passagem
						pais.insertItem(vizinho.getId(), atual);

						/*
						 * Se o vizinho eh o vertice procurado, e foi feita uma
						 * mudanca na distancia, a lista com o menor caminho
						 * anterior eh apagada, pois existe um caminho menor
						 * vertices pais, ate o vertice origem.
						 */
						if (vizinho.getId() == v2.getId()) {
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
