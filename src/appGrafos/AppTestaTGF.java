package appGrafos;

import java.util.List;

import abstractGrafo.GrafoTPA;
import abstractGrafo.Vertice;
import algoGrafos.AlgoGrafo;
import algoGrafos.TPA2GS;
import tadgrafoLAdj.TADGrafoLadjND;
import tadgrafoMAdjND.TADGrafoMadjND;

public class AppTestaTGF {

	public static void mostraCaminho(GrafoTPA g, Vertice v1, Vertice v2) {
		List<Vertice> caminho = AlgoGrafo.caminhoMin(g, v1, v2);
		int indiceUltimo = caminho.get(caminho.size() - 1).getId();
		if (indiceUltimo != v2.getId()) {
			System.out.println("Nenhum caminho encontrado");
		}
		for (Vertice vertice : caminho) {
			System.out.print(vertice.getLabel() + " -> ");
		}
		System.out.print("CHEGOU LÁ!!!");
	}


	public static void main(String[] args) {

		// apresentando grafo de lista
		TADGrafoMadjND gndMatriz = new TADGrafoMadjND();
		gndMatriz.carrega("tpa_sw_epI_allchar.txt");
		TPA2GS.exibeGrafo(gndMatriz);

		// apresentando grafo de lista
		TADGrafoLadjND gndLista = new TADGrafoLadjND();
		gndLista.carrega("tpa_sw_full_allchar.txt");
		TPA2GS.exibeGrafo(gndLista);

		// iup
		Vertice v1 = gndLista.vertices().get(3);
		Vertice v2 = gndLista.vertices().get(0);
		AppTestaTGF.mostraCaminho(gndLista, v1, v2);

		System.out.println();
	}
}
