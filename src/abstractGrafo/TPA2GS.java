/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import abstractGrafo.*;
import abstractGrafo.Edge;

/**
 *
 * @author 20121bsi0040
 */
public class TPA2GS {
  private static String styleSheet = "";
  
  public sttaic void exibeGrafo(GrafoTPA g){
    Graph graph = new SingleGraph("Tutorial 1");
    System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    LinkedList<Vertice> lst_Vs = g.vertices();
    
    //alimentando o grafo
    for (int i = 0; i < lst_Vs.size(); i++){
      Node objGSNode = graph.addNode(lst_Vs.get(i).getLabel());
      objGSNode.addAtribute("ui.label", lst_Vs.get(i).getLabel());
    }
    
    linkedList<Edge> lst_Es = e.edges();
    
    //alimentando graf0 GS com as arestas
    for (int i = 0; i < lst_Es.size(); i++){
      Edge etpa = tsp // tá no celular
      Node objGSNode = graph.addNode(lst_Es.get(i).getLabel());
      objGSNode.addAtribute("ui.label", lst_Es.get(i).getLabel());
    }
    
    graph.addAll("ui.stylesheet", styleSheet);
    graph.setAutoCreate(true);
    graph.setStrict(false);
    graph.display();
  }//exibeGarfo
}
