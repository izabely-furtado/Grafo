/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tadgrafoLAdj;

import java.util.LinkedList;
import java.util.List;

import abstractGrafo.Edge;
import abstractGrafo.Vertice;
import tabHash.TabHEA;

/**
 *
 * @author 20121bsi0040
 */
public class VerticeLadj extends Vertice{
    //Edge edge;
    TabHEA dicEdges = new TabHEA();
    
    public VerticeLadj(int id, Object dado, String label){
        super(id, dado, label);
    }
    public void setEdge(Edge e){
        this.dicEdges.insertItem(e.getId(), e);
    }
    
    public void removeEdge(Edge e){
        this.dicEdges.removeElement(e);
    }
    
    public LinkedList<Edge> edges(){
        LinkedList<Edge> lstEs = new LinkedList<>();
        List<Object> lstObjs = this.dicEdges.elements();
        for (Object lstObj : lstObjs) {
            lstEs.add((EdgeLadj) lstObj);
        }
        return lstEs;
    }
}
