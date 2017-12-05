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
import taddic.TADDicChain;

/**
 *
 * @author 20121bsi0040
 */
public class EdgeLadj extends Edge{
    TADDicChain dicVertices = new TADDicChain();
    
    public EdgeLadj(int id, Object dado, String label){
        super(id, dado, label);
    }
    
    public EdgeLadj setVertexes(Vertice v1, Vertice v2){
        this.dicVertices.insertItem(v1.getId(), v1);
        this.dicVertices.insertItem(v2.getId(), v2);
        return this;
    }
    
    public LinkedList<Vertice> vertices(){
        LinkedList<Vertice> lstVs = new LinkedList<>();
        List<Object> lstObjs = this.dicVertices.elements();
        for (Object lstObj : lstObjs) {
            lstVs.add((VerticeLadj) lstObj);
        }
        return lstVs;
    }
    
}
