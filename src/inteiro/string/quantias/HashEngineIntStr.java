/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteiro.string.quantias;

import taddic.*;

/**
 *
 * @author 20121bsi0040
 */
public class HashEngineIntStr extends HashEngine{
    
    @Override
    public int hashCode(Object k){
        Integer inteiro = (int) k;
        if (inteiro > 999){
            System.out.println("Este número é maior que 99");
            return -1;
        }
        else {
            return inteiro;
        }
    }
}
