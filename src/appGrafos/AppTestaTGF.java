package appGrafos;

import java.util.List;

import abstractGrafo.GrafoTPA;
import abstractGrafo.Vertice;
import algoGrafos.AlgoGrafo;
import tadgrafoLAdj.TADGrafoLadjND;
import tadgrafoMAdjND.TADGrafoMadjND;

public class AppTestaTGF {

	public static void mostraCaminho(GrafoTPA g, Vertice v1, Vertice v2) {
		List<Vertice> caminho = AlgoGrafo.dijkstra(g, v1, v2);
		int indiceUltimo = caminho.get(caminho.size() - 1).getId();
		if (indiceUltimo != v2.getId()) {
			System.out.println("Nenhum caminho encontrado");
		}
		for (Vertice vertice : caminho) {
			System.out.print(vertice.getLabel() + " -> ");
		}
		System.out.println("CHEGOU LÃ�!!!");
	}

	public static void mostraEmAltura(GrafoTPA g, Vertice v1) {
		List<Vertice> caminho = AlgoGrafo.DFS(g, v1.getLabel());
		for (Vertice vertice : caminho) {
			System.out.print(vertice.getLabel() + " -> ");
		}
		System.out.println("CHEGOU LÃ�!!!");
	}

	public static void mostraEmLargura(GrafoTPA g, Vertice v1) {
		List<Vertice> caminho = AlgoGrafo.BFS(g, v1.getLabel());
		for (Vertice vertice : caminho) {
			System.out.print(vertice.getLabel() + " -> ");
		}
		System.out.println("CHEGOU LA!!!");
	}

	
	public static void main(String[] args) {

		// apresentando grafo de lista
		TADGrafoMadjND gndMatriz = new TADGrafoMadjND();
		gndMatriz.carrega2("judith354.txt");
		//TPA2GS.exibeGrafo(gndMatriz);

		// apresentando grafo de lista
		TADGrafoLadjND gndLista = new TADGrafoLadjND();
		gndLista.carrega2("judith363.txt");
		//TPA2GS.exibeGrafo(gndLista);

		// iup
		Vertice v1 = gndLista.vertices().get(6);
		Vertice v2 = gndLista.vertices().get(3);
		AppTestaTGF.mostraCaminho(gndLista, v1, v2);
		AppTestaTGF.mostraEmAltura(gndLista, v1);
		AppTestaTGF.mostraEmLargura(gndLista, v1);


		
		
		System.out.println();
	}
}
