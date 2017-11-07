/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 *
 * @author 20121bsi0040
 */
public class TestaGrafo1 {

    public static void main(String[] args) throws Exception {
        TADGrafoMadjND grafo = new TADGrafoMadjND();
        boolean acabouVertices = false;
        //LinkedList<Vertice> lstVertices = new LinkedList<>();
        //LinkedList<Edge> lstArestas = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("tpalesmis.txt"))) {
            while (br.ready() && !acabouVertices) {
                String linha = br.readLine();
                if ("#".equals(linha)) {
                    acabouVertices = true;
                } else if (!acabouVertices) {
                    Vertice v = new Vertice();
                    v.id = Integer.parseInt(linha.split(" ")[0]);
                    v.dado = linha.split(" ")[1];
                    //lstVertices.add(v);
                    //grafo.dicVertices.insertItem(v.id, v.dado);
                    grafo.insertVertex(v);
                }
            }
//            if (acabouVertices) {
//                grafo = new TADGrafoMadjND(lstVertices.size());
//            }
            while (br.ready() && acabouVertices) {
                String linha = br.readLine();
                int v1 = Integer.parseInt(linha.split(" ")[0]);
                int v2 = Integer.parseInt(linha.split(" ")[1]);
                Vertice vertice1 = (Vertice) grafo.dicVertices.findElements(v1);
                Vertice vertice2 = (Vertice) grafo.dicVertices.findElements(v2);
                //grafo.mat[v1][v2] = 1;
                grafo.insertEdge(vertice1, vertice2, 1);
            }
        }
        System.out.println(grafo);
    }
}
