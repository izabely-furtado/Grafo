/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antigo;

/**
 *
 * @author 20121bsi0040
 */
public class Aresta {
    private int v1, v2, peso;
    public Aresta (int v1, int v2, int peso) {
      this.v1 = v1; this.v2 = v2; this.peso = peso;
    }
    public int peso () { return this.peso; }
    public int v1 () { return this.v1; }
    public int v2 () { return this.v2; }
  
}
