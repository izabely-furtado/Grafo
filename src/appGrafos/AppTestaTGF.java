package appGrafos;

import algoGrafos.TPA2GS;
import tadgrafoLAdj.TADGrafoLadjND;
import tadgrafoMAdjND.TADGrafoMadjND;

public class AppTestaTGF {
	public static void main(String[] args) {

		// apresentando grafo de lista
		TADGrafoMadjND gndMatriz = new TADGrafoMadjND();
		gndMatriz.carrega("tpa_sw_epI_allchar.txt");
		TPA2GS.exibeGrafo(gndMatriz);

		// apresentando grafo de lista
		TADGrafoLadjND gndLista = new TADGrafoLadjND();
		gndLista.carrega("tpa_sw_full_allchar.txt");
		TPA2GS.exibeGrafo(gndLista);

	}
}
