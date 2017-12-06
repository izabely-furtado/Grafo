/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoGrafos;

import java.util.LinkedList;

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
  
  public static void exibeGrafo(GrafoTPA g){
    Graph graph = new SingleGraph("Tutorial 1");
    System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    LinkedList<Vertice> lst_Vs = g.vertices();
    
    //alimentando o grafo
    for (int i = 0; i < lst_Vs.size(); i++){
      Node objGSNode = graph.addNode(lst_Vs.get(i).getLabel());
      objGSNode.addAttribute("ui.label", lst_Vs.get(i).getLabel());
    }
    
    LinkedList<Edge> lst_Es = g.edges();
    
    //alimentando grafo GS com as arestas do grafo TPA
    for (int i = 0; i < lst_Es.size(); i++){
      Edge etpa = lst_Es.get(i); 
      LinkedList<Vertice> lst_ends = g.endVertices(etpa);
      String labelE = etpa.getLabel();
      String labelV = lst_ends.get(0).getLabel();
      String labelW = lst_ends.get(1).getLabel();
      if(labelE.trim().equals("")){
    	  labelE = labelV + labelW + i;
      }
      org.graphstream.graph.Edge objGSEdge = graph.addEdge(labelE, labelV, labelW);
      objGSEdge.addAttribute("ui.label", labelE);
      //Node objGSNode = graph.addNode(lst_Es.get(i).getLabel());
      //objGSNode.addAttribute("ui.label", lst_Es.get(i).getLabel());
    }
    
    graph.addAttribute("ui.stylesheet", styleSheet);
    graph.setAutoCreate(true);
    graph.setStrict(false);
    graph.display();
  }//exibeGarfo
}
