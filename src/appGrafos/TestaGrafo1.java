/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appGrafos;

import tadgrafoMAdjND.TADGrafoMadjND;

import java.io.BufferedReader;
import java.io.FileReader;

import abstractGrafo.Vertice;
import tadgrafoMAdjND.VerticeMadj;


/**
 *
 * @author 20121bsi0040
 */
public class TestaGrafo1 {
    ///fazer o método carrega(nome do arquivo do gráfico)
    ///fazer um constructor de carregamento que le de um arquivo
    public static void main(String[] args) throws Exception {
        TADGrafoMadjND grafo = new TADGrafoMadjND();
        boolean acabouVertices = false;
        try (BufferedReader br = new BufferedReader(new FileReader("tpalesmis.txt"))) {
            while (br.ready() && !acabouVertices) {
                String linha = br.readLine();
                if ("#".equals(linha)) {
                    acabouVertices = true;
                } else if (!acabouVertices) {
                    String[] conteudo = linha.split(" ");
                    if(conteudo.length == 3){
                        Vertice v = new VerticeMadj(Integer.parseInt(conteudo[0]), conteudo[1], conteudo[2]);
                        grafo.insertVertex(v);
                    }
                    if(conteudo.length ==2){
                        Vertice v = new VerticeMadj(Integer.parseInt(conteudo[0]), conteudo[1], null);
                        grafo.insertVertex(v);
                    }
                }
            }
            while (br.ready() && acabouVertices) {
             //   String linha = br.readLine();
            //    String[] conteudo = linha.split(" ");
            //    int v1 = Integer.parseInt(conteudo[0]);
             //   int v2 = Integer.parseInt(conteudo[1]);
            //    Vertice vertice1 = (Vertice) appGrafos.dicVertices.findElements(v1);
            //    Vertice vertice2 = (Vertice) appGrafos.dicVertices.findElements(v2);
            //    appGrafos.insertEdge(vertice1, vertice2, 1);
            }
        }
        System.out.println(grafo);
    }
}
